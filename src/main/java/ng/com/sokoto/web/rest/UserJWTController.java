package ng.com.sokoto.web.rest;

import java.util.concurrent.ExecutionException;
import ng.com.sokoto.web.domain.User;
import ng.com.sokoto.web.rest.vm.LoginVM;
import ng.com.sokoto.web.service.UserJWTService;
import ng.com.sokoto.web.service.UserJWTService.JWTToken;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * Controller to authenticate users.
 */
@RestController
@RequestMapping("/api")
public class UserJWTController {

    private final UserJWTService userJWTService;

    public UserJWTController(UserJWTService userJWTService) {
        this.userJWTService = userJWTService;
    }

    @PostMapping(value = "/authenticate", consumes = "application/json;charset=utf-8", produces = "application/json;charset=utf-8")
    public Mono<ResponseEntity<JWTToken>> authorize(@RequestBody Mono<LoginVM> loginVM) throws ExecutionException, InterruptedException {
        //        userJWTService.authenticatePouchii(loginVM).subscribe();
        return userJWTService.authenticate(loginVM);
    }

    @PostMapping("/authenticate1")
    public Mono<ResponseEntity<JWTToken>> authorize1(@RequestBody Mono<LoginVM> loginVM) throws ExecutionException, InterruptedException {
        return userJWTService.authenticate(loginVM);
    }

    @PostMapping("/authenticate2")
    public Mono<User> authorize2(@RequestBody Mono<LoginVM> loginVM) {
        return userJWTService.authenticatePouchii(loginVM);
    }
}
