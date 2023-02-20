package ng.com.sokoto.repository;

import java.util.Optional;
import ng.com.sokoto.web.domain.Subscriber;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriberRepository extends MongoRepository<Subscriber, String> {
    Optional<Subscriber> findByLogin(String login);
}
