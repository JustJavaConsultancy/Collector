package ng.com.sokoto.web.dto;

import io.swagger.annotations.ApiModel;
import java.util.List;
import ng.com.sokoto.web.domain.ApprovalLevel;

@ApiModel
public class ApprovalRouteDto extends AbstractDto<String> {

    private String id;
    private String requestTypeID;

    public String getRequestTypeID() {
        return requestTypeID;
    }

    public void setRequestTypeID(String requestTypeID) {
        this.requestTypeID = requestTypeID;
    }

    private List<ApprovalLevelDto> approvalLevels;

    public ApprovalRouteDto() {}

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setApprovalLevels(java.util.List<ApprovalLevelDto> approvalLevels) {
        this.approvalLevels = approvalLevels;
    }

    public java.util.List<ApprovalLevelDto> getApprovalLevels() {
        return this.approvalLevels;
    }
}
