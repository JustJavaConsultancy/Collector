package ng.com.sokoto.web.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import java.util.concurrent.ExecutionException;
import ng.com.sokoto.repository.UserRepository;
import ng.com.sokoto.security.jwt.JWTFilter;
import ng.com.sokoto.security.jwt.TokenProvider;
import ng.com.sokoto.service.PouchiiClient;
import ng.com.sokoto.service.UserService;
import ng.com.sokoto.service.kafka.PouchiiLoginProducer;
import ng.com.sokoto.web.domain.User;
import ng.com.sokoto.web.rest.vm.LoginVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserJWTService {

    private final Logger log = LoggerFactory.getLogger(UserJWTService.class);
    private final TokenProvider tokenProvider;

    private final ReactiveAuthenticationManager authenticationManager;
    private final PouchiiClient pouchiiClient;
    private final UserRepository userRepository;
    private final UserService userService;
    private final PouchiiLoginProducer pouchiiLoginProducer;

    public UserJWTService(
        TokenProvider tokenProvider,
        ReactiveAuthenticationManager authenticationManager,
        PouchiiClient pouchiiClient,
        UserRepository userRepository,
        UserService userService,
        PouchiiLoginProducer pouchiiLoginProducer
    ) {
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
        this.pouchiiClient = pouchiiClient;
        this.userRepository = userRepository;
        this.userService = userService;
        this.pouchiiLoginProducer = pouchiiLoginProducer;
    }

    public Mono<ResponseEntity<JWTToken>> authenticate(Mono<LoginVM> loginVM) {
        return loginVM
            .map(login -> {
                try {
                    pouchiiLoginProducer.send(new Gson().toJson(login));
                } catch (ExecutionException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return login;
            })
            .flatMap(login ->
                authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()))
                    .flatMap(auth -> Mono.fromCallable(() -> tokenProvider.createToken(auth, login.isRememberMe())))
            )
            .map(jwt -> {
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
                return new ResponseEntity<>(new JWTToken(jwt), httpHeaders, HttpStatus.OK);
            });
    }

    public Mono<User> authenticatePouchii(Mono<LoginVM> loginVM) {
        log.info("Inside authenticatePouchii...");
        return loginVM
            .flatMap(login ->
                userRepository
                    .findOneByLogin(login.getUsername())
                    .flatMap(user ->
                        pouchiiClient
                            .login(login)
                            .map(authResponse -> {
                                user.setPouchiiToken(authResponse.getToken());
                                user.setBalance(authResponse.getWalletAccount().getActualBalance());

                                return userRepository.save(user).doOnNext(u -> log.info("Logged in user on pouchii: {}", user));
                            })
                    )
            )
            .flatMap(mono -> mono);
    }

    /**
     * Object to return as body in JWT Authentication.
     */
    public static class JWTToken {

        private String idToken;

        JWTToken(String idToken) {
            this.idToken = idToken;
        }

        @JsonProperty("id_token")
        String getIdToken() {
            return idToken;
        }

        void setIdToken(String idToken) {
            this.idToken = idToken;
        }
    }
}
