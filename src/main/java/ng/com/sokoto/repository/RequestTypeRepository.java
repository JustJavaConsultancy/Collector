package ng.com.sokoto.repository;

import ng.com.sokoto.web.domain.RequestType;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestTypeRepository extends MongoRepository<RequestType, String> {}
