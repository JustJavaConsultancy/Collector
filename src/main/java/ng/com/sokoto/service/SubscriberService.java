package ng.com.sokoto.service;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import ng.com.sokoto.dto.SubscriberDto;
import ng.com.sokoto.mapper.SubscriberMapper;
import ng.com.sokoto.repository.SubscriberRepository;
import ng.com.sokoto.service.Exception.ResourceNotFoundException;
import ng.com.sokoto.web.domain.Subscriber;
import ng.com.sokoto.web.domain.User;
import ng.com.sokoto.web.domain.Wallet;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class SubscriberService {

    private final SubscriberRepository repository;
    private final SubscriberMapper subscriberMapper;

    private final UserService userService;
    private final MailService mailService;
    private final PasswordEncoder passwordEncoder;

    public SubscriberService(
        SubscriberRepository repository,
        SubscriberMapper subscriberMapper,
        UserService userService,
        MailService mailService,
        PasswordEncoder passwordEncoder
    ) {
        this.repository = repository;
        this.subscriberMapper = subscriberMapper;
        this.userService = userService;
        this.mailService = mailService;
        this.passwordEncoder = passwordEncoder;
    }

    public SubscriberDto save(SubscriberDto subscriberDto) {
        Subscriber entity = subscriberMapper.toEntity(subscriberDto);
        Wallet wallet = Wallet.createNewWallet(subscriberDto.getFirstName(), subscriberDto.getPhoneNumber());
        entity.setWallet(wallet);
        User user = new User();
        user.setId(subscriberDto.getLogin());
        user.setPassword(passwordEncoder.encode("password"));
        user.setEmail(subscriberDto.getEmail());
        user.setFirstName(subscriberDto.getFirstName());
        user.setLogin(subscriberDto.getLogin());
        user.setLastName(subscriberDto.getLastName());
        user.setLangKey("EN");
        /*        ManagedUserVM managedUserVM = new ManagedUserVM();
        managedUserVM.setPassword("password");
        managedUserVM.setEmail(subscriberDto.getEmail());
        managedUserVM.setFirstName(subscriberDto.getFirstName());
        managedUserVM.setLangKey("EN");
        managedUserVM.setLastName(subscriberDto.getLastName());
        managedUserVM.setLogin(subscriberDto.getLogin());
        System.out.println(" About to save the user now...................");
        Mono<User> user = userService.registerUser(managedUserVM,managedUserVM.getPassword());//.doOnSuccess(mailService::sendActivationEmail).then();
    */
        //System.out.println(" After saving the user now..................."+user.block());
        entity.setUser(user);
        entity.setId(subscriberDto.getLogin());
        return subscriberMapper.toDto(repository.save(entity));
    }

    public Subscriber save(Subscriber subscriber) {
        return repository.save(subscriber);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    public SubscriberDto findById(String id) {
        return subscriberMapper.toDto(repository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    public Subscriber finEntitydById(String id) {
        return repository.findByLogin(id).orElseThrow(ResourceNotFoundException::new);
    }

    public Subscriber finSubscriberByLogin(String login) {
        return repository.findByLogin(login).orElseThrow(ResourceNotFoundException::new);
    }

    public Page<SubscriberDto> findByCondition(SubscriberDto subscriberDto, Pageable pageable) {
        Page<Subscriber> entityPage = repository.findAll(pageable);
        List<Subscriber> entities = entityPage.getContent();
        return new PageImpl<>(subscriberMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public SubscriberDto update(SubscriberDto subscriberDto, String id) {
        SubscriberDto data = findById(id);
        Subscriber entity = subscriberMapper.toEntity(subscriberDto);
        BeanUtils.copyProperties(data, entity);
        return save(subscriberMapper.toDto(entity));
    }
}
