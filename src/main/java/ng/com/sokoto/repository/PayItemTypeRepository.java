package ng.com.sokoto.repository;

import ng.com.sokoto.web.domain.PayItemType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayItemTypeRepository extends MongoRepository<PayItemType, Long> {}
