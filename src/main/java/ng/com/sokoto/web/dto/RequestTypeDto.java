package ng.com.sokoto.web.dto;

import io.swagger.annotations.ApiModel;
import ng.com.sokoto.web.domain.ApprovalRoute;

@ApiModel
public class RequestTypeDto extends AbstractDto<String> {

    private String id;
    private String description;
    private ApprovalRouteDto approvalRoute;

    public RequestTypeDto() {}

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setApprovalRoute(ApprovalRouteDto approvalRoute) {
        this.approvalRoute = approvalRoute;
    }

    public ApprovalRouteDto getApprovalRoute() {
        return this.approvalRoute;
    }
}
