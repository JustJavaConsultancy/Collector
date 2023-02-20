package ng.com.sokoto.web.domain;

import io.github.kaiso.relmongo.annotation.JoinProperty;
import io.github.kaiso.relmongo.annotation.OneToOne;
import javax.persistence.Id;
import ng.com.sokoto.dto.SubscriptionDto;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "requesttype")
public class RequestType {

    @Id
    private String id;

    private String description;

    //@OneToOne
    //@JoinProperty(name = "approvalRoute")
    private ApprovalRoute approvalRoute;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ApprovalRoute getApprovalRoute() {
        return approvalRoute;
    }

    public void setApprovalRoute(ApprovalRoute approvalRoute) {
        this.approvalRoute = approvalRoute;
    }

    public RequestType addApprovalRoute(ApprovalRoute approvalRoute) {
        setApprovalRoute(approvalRoute);
        return this;
    }

    public Request initiateRequest(Subscription subscription) {
        if (getApprovalRoute() == null) throw new RuntimeException("Approval Route Not Set");
        if (getApprovalRoute().getApprovalLevels() == null || getApprovalRoute().getApprovalLevels().isEmpty()) throw new RuntimeException(
            "Approval Level Is Empty"
        );
        Request request = new Request();
        request.setCurrentLevel(0);
        request.setRequestType(getId());
        request.setPayload(subscription);
        String approver = getApprovalRoute().getApprovalLevels().get(0).getApprover();
        request.setStatus("Awaiting");
        request.setCurrentApprover(approver);
        request.setRequestOwner(subscription.getSubscriber().getLogin());
        return request;
    }

    public Request approveRequest(Request request) {
        if (getApprovalRoute() == null) throw new RuntimeException("Approval Route Not Set");
        if (getApprovalRoute().getApprovalLevels() == null || getApprovalRoute().getApprovalLevels().isEmpty()) throw new RuntimeException(
            "Approval Level Is Empty"
        );
        Integer currentLevel = request.getCurrentLevel() + 1;
        if (currentLevel < getApprovalRoute().getApprovalLevels().size()) {
            String nextApprover = getApprovalRoute().getApprovalLevels().get(currentLevel).getApprover();
            request.setCurrentLevel(currentLevel);
            request.setCurrentApprover(nextApprover);
            return request;
        }
        request.setStatus("approved");
        request.setCurrentApprover(null);
        return request;
    }
}
