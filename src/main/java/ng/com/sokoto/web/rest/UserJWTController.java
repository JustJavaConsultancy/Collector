package ng.com.sokoto.web.rest;

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
    public Mono<ResponseEntity<JWTToken>> authorize(@RequestBody Mono<LoginVM> loginVM) {
        return userJWTService.authenticate(loginVM);
    }
}
