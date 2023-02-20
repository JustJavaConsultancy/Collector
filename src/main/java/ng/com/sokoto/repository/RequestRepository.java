package ng.com.sokoto.repository;

import java.util.List;
import ng.com.sokoto.web.domain.Request;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends MongoRepository<Request, String> {
    List<Request> findByRequestOwner(String requestOwner);

    List<Request> findByCurrentApprover(String currentApprover);
}
