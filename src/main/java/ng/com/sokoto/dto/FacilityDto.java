package ng.com.sokoto.dto;

import io.swagger.annotations.ApiModel;
import java.time.LocalDate;
import java.util.Collection;
import ng.com.sokoto.annotation.CheckDate;
import ng.com.sokoto.web.domain.PayItemType;
import ng.com.sokoto.web.domain.enumeration.RentCycleEnum;
import ng.com.sokoto.web.domain.enumeration.StatusEnum;
import ng.com.sokoto.web.domain.enumeration.UserTypeEnum;

@ApiModel
public class FacilityDto extends AbstractDto<String> {

    private long serialVersionUID;
    private String id;
    private String description;
    private UserTypeEnum userType;
    private String rentCycle;
    private String location;
    private String refNumber;
    private Double size;

    @CheckDate
    private LocalDate dateRegistered;

    private StatusEnum status;
    private String category;

    private Collection<String> payItemTypes;

    public FacilityDto() {}

    public void setSerialVersionUID(long serialVersionUID) {
        this.serialVersionUID = serialVersionUID;
    }

    public long getSerialVersionUID() {
        return this.serialVersionUID;
    }

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

    public void setUserType(UserTypeEnum userType) {
        this.userType = userType;
    }

    public UserTypeEnum getUserType() {
        return this.userType;
    }

    public void setRentCycle(String rentCycle) {
        this.rentCycle = rentCycle;
    }

    public String getRentCycle() {
        return this.rentCycle;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return this.location;
    }

    public void setRefNumber(String refNumber) {
        this.refNumber = refNumber;
    }

    public String getRefNumber() {
        return this.refNumber;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public Double getSize() {
        return this.size;
    }

    public void setDateRegistered(java.time.LocalDate dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public java.time.LocalDate getDateRegistered() {
        return this.dateRegistered;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public StatusEnum getStatus() {
        return this.status;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return this.category;
    }

    public Collection<String> getPayItemTypes() {
        return payItemTypes;
    }

    public void setPayItemTypes(Collection<String> payItemTypes) {
        this.payItemTypes = payItemTypes;
    }
}
