package ng.com.sokoto.repository;

import ng.com.sokoto.web.domain.Facility;
import ng.com.sokoto.web.domain.enumeration.StatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacilityRepository extends MongoRepository<Facility, String> {
    Page<Facility> findDistinctByStatusOrderByCategory_IdAsc(StatusEnum status, Pageable pageable);
}
