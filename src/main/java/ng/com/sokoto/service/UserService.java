package ng.com.sokoto.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;
import ng.com.sokoto.config.Constants;
import ng.com.sokoto.repository.AuthorityRepository;
import ng.com.sokoto.repository.UserRepository;
import ng.com.sokoto.security.AuthoritiesConstants;
import ng.com.sokoto.security.SecurityUtils;
import ng.com.sokoto.service.dto.AdminUserDTO;
import ng.com.sokoto.service.dto.PasswordChangeDTO;
import ng.com.sokoto.service.dto.UserDTO;
import ng.com.sokoto.web.domain.Authority;
import ng.com.sokoto.web.domain.User;
import ng.com.sokoto.web.dto.pouchii.ChangePinDTO;
import ng.com.sokoto.web.dto.pouchii.CreateWalletExternal;
import ng.com.sokoto.web.dto.pouchii.CreateWalletExternalResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import tech.jhipster.security.RandomUtil;

/**
 * Service class for managing users.
 */
@Service
public class UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthorityRepository authorityRepository;
    private final PouchiiClient pouchiiClient;

    public UserService(
        UserRepository userRepository,
        PasswordEncoder passwordEncoder,
        AuthorityRepository authorityRepository,
        PouchiiClient pouchiiClient
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityRepository = authorityRepository;
        this.pouchiiClient = pouchiiClient;
    }

    public Mono<User> activateRegistration(String key) {
        log.debug("Activating user for activation key {}", key);
        return userRepository
            .findOneByActivationKey(key)
            .flatMap(user -> {
                // activate given user for the registration key.
                user.setActivated(true);
                user.setActivationKey(null);
                return saveUser(user);
            })
            .doOnNext(user -> log.debug("Activated user: {}", user));
    }

    public Mono<User> completePasswordReset(String newPassword, String key) {
        log.debug("Reset user password for reset key {}", key);
        return userRepository
            .findOneByResetKey(key)
            .filter(user -> user.getResetDate().isAfter(Instant.now().minus(1, ChronoUnit.DAYS)))
            .publishOn(Schedulers.boundedElastic())
            .map(user -> {
                user.setPassword(passwordEncoder.encode(newPassword));
                user.setResetKey(null);
                user.setResetDate(null);
                return user;
            })
            .flatMap(this::saveUser);
    }

    public Mono<User> requestPasswordReset(String mail) {
        return userRepository
            .findOneByEmailIgnoreCase(mail)
            .filter(User::isActivated)
            .publishOn(Schedulers.boundedElastic())
            .map(user -> {
                user.setResetKey(RandomUtil.generateResetKey());
                user.setResetDate(Instant.now());
                return user;
            })
            .flatMap(this::saveUser);
    }

    public Mono<User> registerUser(AdminUserDTO userDTO, String password) {
        log.info(" The sent AdminUserDTO===" + userDTO);
        return userRepository
            .findOneByLogin(userDTO.getLogin().toLowerCase())
            .flatMap(existingUser -> {
                if (!existingUser.isActivated()) {
                    return userRepository.delete(existingUser);
                } else {
                    return Mono.error(new UsernameAlreadyUsedException());
                }
            })
            .then(userRepository.findOneByEmailIgnoreCase(userDTO.getEmail()))
            .flatMap(existingUser -> {
                if (!existingUser.isActivated()) {
                    return userRepository.delete(existingUser);
                } else {
                    return Mono.error(new EmailAlreadyUsedException());
                }
            })
            .publishOn(Schedulers.boundedElastic())
            .then(
                Mono.fromCallable(() -> {
                    User newUser = new User();
                    String encryptedPassword = passwordEncoder.encode(password);
                    newUser.setLogin(userDTO.getLogin().toLowerCase());
                    // new user gets initially a generated password
                    newUser.setPassword(encryptedPassword);
                    newUser.setFirstName(userDTO.getFirstName());
                    newUser.setLastName(userDTO.getLastName());
                    if (userDTO.getEmail() != null) {
                        newUser.setEmail(userDTO.getEmail().toLowerCase());
                    }
                    newUser.setImageUrl(userDTO.getImageUrl());
                    newUser.setLangKey(userDTO.getLangKey());
                    // new user is not active
                    newUser.setActivated(true);
                    newUser.setPhoneNumber(userDTO.getPhoneNumber());
                    newUser.setGender(userDTO.getGender());
                    // new user gets registration key
                    //newUser.setActivationKey(RandomUtil.generateActivationKey());
                    return newUser;
                })
            )
            .flatMap(newUser -> {
                Set<Authority> authorities = new HashSet<>();
                return authorityRepository
                    .findById(AuthoritiesConstants.USER)
                    .map(authorities::add)
                    .thenReturn(newUser)
                    .doOnNext(user -> user.setAuthorities(authorities))
                    .flatMap(this::saveUser)
                    .doOnNext(user -> log.debug("Created Information for User: {}", user));
            })
            .flatMap(user ->
                createWallet(userDTO)
                    .map(walletExternalResponse -> {
                        user.setBalance(Double.valueOf(walletExternalResponse.getData().get(0).getCurrentBalance()));
                        user.setNuban(walletExternalResponse.getData().get(0).getNubanAccountNo());
                        user.setWalletAccount(walletExternalResponse.getData().get(0).getAccountNumber());

                        return user;
                    })
                    .flatMap(this::saveUser)
            );
    }

    private Mono<CreateWalletExternalResponse> createWallet(AdminUserDTO userDTO) {
        CreateWalletExternal walletExternal = new CreateWalletExternal();
        walletExternal.setPhoneNumber(userDTO.getPhoneNumber());
        walletExternal.setFirstName(userDTO.getFirstName());
        walletExternal.setLastName(userDTO.getLastName());
        walletExternal.setPassword(userDTO.getPassword());
        walletExternal.setPhoneNumber(userDTO.getPhoneNumber());
        walletExternal.setPin(userDTO.getPin());
        walletExternal.setDateOfBirth(userDTO.getDateOfBirth().toString());
        walletExternal.setGender(userDTO.getGender().getName());
        walletExternal.setState(userDTO.getState());
        walletExternal.setLocalGovt(userDTO.getLocalGovt());
        walletExternal.setAddress(userDTO.getAddress());
        walletExternal.setAccountName(userDTO.getFirstName() + userDTO.getLastName());
        walletExternal.setEmail(userDTO.getEmail());

        return pouchiiClient
            .createWallet(walletExternal)
            .doOnNext(walletExternalResponse -> {
                String code = walletExternalResponse.getCode();
                if (!("00".equalsIgnoreCase(code) || "success".equalsIgnoreCase(code))) {
                    throw new RuntimeException("Error creating wallet");
                }
            });
    }

    public Mono<User> createUser(AdminUserDTO userDTO) {
        User user = new User();
        user.setLogin(userDTO.getLogin().toLowerCase());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        if (userDTO.getEmail() != null) {
            user.setEmail(userDTO.getEmail().toLowerCase());
        }
        user.setImageUrl(userDTO.getImageUrl());
        if (userDTO.getLangKey() == null) {
            user.setLangKey(Constants.DEFAULT_LANGUAGE); // default language
        } else {
            user.setLangKey(userDTO.getLangKey());
        }
        return Flux
            .fromIterable(userDTO.getAuthorities() != null ? userDTO.getAuthorities() : new HashSet<>())
            .flatMap(authorityRepository::findById)
            .doOnNext(authority -> user.getAuthorities().add(authority))
            .then(Mono.just(user))
            .publishOn(Schedulers.boundedElastic())
            .map(newUser -> {
                String encryptedPassword = passwordEncoder.encode(RandomUtil.generatePassword());
                newUser.setPassword(encryptedPassword);
                newUser.setResetKey(RandomUtil.generateResetKey());
                newUser.setResetDate(Instant.now());
                newUser.setActivated(true);
                newUser.setPhoneNumber(userDTO.getPhoneNumber());
                newUser.setGender(userDTO.getGender());
                return newUser;
            })
            .flatMap(this::saveUser)
            .doOnNext(user1 -> log.debug("Created Information for User: {}", user1));
    }

    /**
     * Update all information for a specific user, and return the modified user.
     *
     * @param userDTO user to update.
     * @return updated user.
     */
    public Mono<AdminUserDTO> updateUser(AdminUserDTO userDTO) {
        return userRepository
            .findById(userDTO.getId())
            .flatMap(user -> {
                user.setLogin(userDTO.getLogin().toLowerCase());
                user.setFirstName(userDTO.getFirstName());
                user.setLastName(userDTO.getLastName());
                if (userDTO.getEmail() != null) {
                    user.setEmail(userDTO.getEmail().toLowerCase());
                }
                user.setImageUrl(userDTO.getImageUrl());
                user.setActivated(userDTO.isActivated());
                user.setLangKey(userDTO.getLangKey());
                Set<Authority> managedAuthorities = user.getAuthorities();
                managedAuthorities.clear();
                return Flux
                    .fromIterable(userDTO.getAuthorities())
                    .flatMap(authorityRepository::findById)
                    .map(managedAuthorities::add)
                    .then(Mono.just(user));
            })
            .flatMap(this::saveUser)
            .doOnNext(user -> log.debug("Changed Information for User: {}", user))
            .map(AdminUserDTO::new);
    }

    public Mono<Void> deleteUser(String login) {
        return userRepository
            .findOneByLogin(login)
            .flatMap(user -> userRepository.delete(user).thenReturn(user))
            .doOnNext(user -> log.debug("Deleted User: {}", user))
            .then();
    }

    /**
     * Update basic information (first name, last name, email, language) for the current user.
     *
     * @param firstName first name of user.
     * @param lastName  last name of user.
     * @param email     email id of user.
     * @param langKey   language key.
     * @param imageUrl  image URL of user.
     * @return a completed {@link Mono}.
     */
    public Mono<Void> updateUser(String firstName, String lastName, String email, String langKey, String imageUrl) {
        return SecurityUtils
            .getCurrentUserLogin()
            .flatMap(userRepository::findOneByLogin)
            .flatMap(user -> {
                user.setFirstName(firstName);
                user.setLastName(lastName);
                if (email != null) {
                    user.setEmail(email.toLowerCase());
                }
                user.setLangKey(langKey);
                user.setImageUrl(imageUrl);
                return saveUser(user);
            })
            .doOnNext(user -> log.debug("Changed Information for User: {}", user))
            .then();
    }

    private Mono<User> saveUser(User user) {
        log.info(" Inside saveUser...............................................");
        return SecurityUtils
            .getCurrentUserLogin()
            .switchIfEmpty(Mono.just(Constants.SYSTEM))
            .flatMap(login -> {
                if (user.getCreatedBy() == null) {
                    user.setCreatedBy(login);
                }
                user.setLastModifiedBy(login);
                log.info(" Saving the User Here........................");
                return userRepository.save(user);
            });
    }

    public Mono<Void> changePassword(Mono<String> login, String currentClearTextPassword, String newPassword) {
        log.info(" Inside changePassword...." + SecurityUtils.getCurrentUserJWT());
        /*        Mono<String> jwt = SecurityUtils.getCurrentUserLogin();
        jwt.subscribe(x-> System.out.println(" The login Here ==========================="+x));*/
        login
            .flatMap(userRepository::findOneByLogin)
            .map(user -> {
                log.info(" The User in here....................." + user);
                String currentEncryptedPassword = user.getPassword();
                if (!passwordEncoder.matches(currentClearTextPassword, currentEncryptedPassword)) {
                    throw new InvalidPasswordException();
                }
                String encryptedPassword = passwordEncoder.encode(newPassword);
                user.setPassword(encryptedPassword);
                log.info(" About to return user to the next stage in the pipe.......................");

                pouchiiClient
                    .changePassword(new PasswordChangeDTO(currentClearTextPassword, newPassword), user.getPouchiiToken())
                    .subscribeOn(Schedulers.parallel())
                    .subscribe();

                return user;
            })
            .flatMap(this::saveUser)
            .doOnNext(u -> log.info(" The user finally saved=====" + u))
            .subscribe();

        return null;
    }

    public void changePin(Mono<String> loginUser, ChangePinDTO changePinDTO) {
        log.info(" Inside changePin...." + SecurityUtils.getCurrentUserJWT().subscribe());

        loginUser
            .flatMap(userRepository::findOneByLogin)
            .flatMap(user ->
                pouchiiClient.changePin(changePinDTO, user.getPouchiiToken()).doOnSuccess(response -> log.info("Pin changed successfully!"))
            )
            .subscribe();
    }

    public Flux<AdminUserDTO> getAllManagedUsers(Pageable pageable) {
        return userRepository.findAllByIdNotNull(pageable).map(AdminUserDTO::new);
    }

    public Flux<UserDTO> getAllPublicUsers(Pageable pageable) {
        return userRepository.findAllByIdNotNullAndActivatedIsTrue(pageable).map(UserDTO::new);
    }

    public Mono<Long> countManagedUsers() {
        return userRepository.count();
    }

    public Mono<User> getUserWithAuthoritiesByLogin(String login) {
        return userRepository.findOneByLogin(login);
    }

    public Mono<User> getUserWithAuthorities() {
        return SecurityUtils.getCurrentUserLogin().flatMap(userRepository::findOneByLogin);
    }

    /**
     * Not activated users should be automatically deleted after 3 days.
     * <p>
     * This is scheduled to get fired everyday, at 01:00 (am).
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void removeNotActivatedUsers() {
        removeNotActivatedUsersReactively().blockLast();
    }

    public Flux<User> removeNotActivatedUsersReactively() {
        return userRepository
            .findAllByActivatedIsFalseAndActivationKeyIsNotNullAndCreatedDateBefore(Instant.now().minus(3, ChronoUnit.DAYS))
            .flatMap(user -> userRepository.delete(user).thenReturn(user))
            .doOnNext(user -> log.debug("Deleted User: {}", user));
    }

    /**
     * Gets a list of all the authorities.
     *
     * @return a list of all the authorities.
     */
    public Flux<String> getAuthorities() {
        return authorityRepository.findAll().map(Authority::getName);
    }
}
