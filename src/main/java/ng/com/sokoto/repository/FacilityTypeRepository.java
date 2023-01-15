package ng.com.sokoto.repository;

import ng.com.sokoto.web.domain.FacilityType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacilityTypeRepository extends MongoRepository<FacilityType, String> {}
