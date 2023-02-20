package ng.com.sokoto.web.dto;

import io.swagger.annotations.ApiModel;
import ng.com.sokoto.web.domain.RequestType;
import ng.com.sokoto.web.domain.User;

@ApiModel
public class RequestDto<T> extends AbstractDto<String> {

    private String id;

    private String requestOwner;
    private Integer currentLevel;
    private String currentApprover;
    private String requestType;
    private T payload;

    public RequestDto() {}

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setCurrentLevel(Integer currentLevel) {
        this.currentLevel = currentLevel;
    }

    public Integer getCurrentLevel() {
        return this.currentLevel;
    }

    public void setCurrentApprover(String currentApprover) {
        this.currentApprover = currentApprover;
    }

    public String getCurrentApprover() {
        return this.currentApprover;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getRequestType() {
        return this.requestType;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    public T getPayload() {
        return this.payload;
    }

    public String getRequestOwner() {
        return requestOwner;
    }

    public void setRequestOwner(String requestOwner) {
        this.requestOwner = requestOwner;
    }
}
