package ng.com.sokoto.service.kafka;

import com.google.gson.Gson;
import ng.com.sokoto.web.rest.vm.LoginVM;
import ng.com.sokoto.web.service.UserJWTService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class PouchiiLoginConsumer {

    private final Logger log = LoggerFactory.getLogger(PouchiiLoginConsumer.class);
    private final UserJWTService userJWTService;

    public PouchiiLoginConsumer(UserJWTService userJWTService) {
        this.userJWTService = userJWTService;
    }

    @KafkaListener(topics = PouchiiTopics.LOGIN, groupId = "pouchii")
    public void consume(String message) {
        log.debug("Got message from kafka stream: {}", message);

        LoginVM loginVM = new Gson().fromJson(message, LoginVM.class);
        userJWTService.authenticatePouchii(Mono.just(loginVM)).subscribe();
    }
}
