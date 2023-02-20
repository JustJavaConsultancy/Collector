package ng.com.sokoto.web.domain;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "approvalroute")
//@Entity
public class ApprovalRoute {

    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ApprovalLevel> getApprovalLevels() {
        return approvalLevels;
    }

    public void setApprovalLevels(List<ApprovalLevel> approvalLevels) {
        this.approvalLevels = approvalLevels;
    }

    private List<ApprovalLevel> approvalLevels;
}
