package ng.com.sokoto.service.kafka;

import java.util.concurrent.ExecutionException;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

@Component
public class PouchiiLoginProducer {

    private final Logger log = LoggerFactory.getLogger(PouchiiLoginProducer.class);
    private final KafkaTemplate<String, String> kafkaTemplate;

    public PouchiiLoginProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String message) throws ExecutionException, InterruptedException {
        log.debug("Producer got message: {}", message);
        ProducerRecord<String, String> record = new ProducerRecord<>(PouchiiTopics.LOGIN, message);
        kafkaTemplate.send(record);
    }
}
