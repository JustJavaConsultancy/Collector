package ng.com.sokoto.repository;

import ng.com.sokoto.web.domain.ApprovalRoute;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApprovalRouteRepository extends MongoRepository<ApprovalRoute, String> {}
