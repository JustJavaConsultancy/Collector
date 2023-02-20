package ng.com.sokoto.web.dto;

import io.swagger.annotations.ApiModel;
import ng.com.sokoto.web.domain.User;

@ApiModel
public class ApprovalLevelDto extends AbstractDto<String> {

    private String id;
    private String approver;
    private Integer level;

    public ApprovalLevelDto() {}

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public String getApprover() {
        return this.approver;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getLevel() {
        return this.level;
    }
}
