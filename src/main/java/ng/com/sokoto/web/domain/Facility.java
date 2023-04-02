package ng.com.sokoto.web.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import javax.persistence.Entity;
import ng.com.sokoto.web.domain.enumeration.RentCycleEnum;
import ng.com.sokoto.web.domain.enumeration.StatusEnum;
import ng.com.sokoto.web.domain.enumeration.UserTypeEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Facility.
 */
@Document(collection = "facility")
public class Facility implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("description")
    private String description;

    @Field("user_type")
    private UserTypeEnum userType;

    @Field("rent_cycle")
    private RentCycleEnum rentCycle;

    @Field("location")
    private String location;

    @Field("ref_number")
    private String refNumber;

    @Field("size")
    private Double size;

    @Field("date_registered")
    private LocalDate dateRegistered;

    @Field("status")
    private StatusEnum status;

    @DBRef
    private FacilityType category;

    private Collection<PayItemType> payItemTypes;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Facility id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public Facility description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserTypeEnum getUserType() {
        return this.userType;
    }

    public Facility userType(UserTypeEnum userType) {
        this.setUserType(userType);
        return this;
    }

    public void setUserType(UserTypeEnum userType) {
        this.userType = userType;
    }

    public RentCycleEnum getRentCycle() {
        return this.rentCycle;
    }

    public Facility rentCycle(RentCycleEnum rentCycle) {
        this.setRentCycle(rentCycle);
        return this;
    }

    public void setRentCycle(RentCycleEnum rentCycle) {
        this.rentCycle = rentCycle;
    }

    public String getLocation() {
        return this.location;
    }

    public Facility location(String location) {
        this.setLocation(location);
        return this;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRefNumber() {
        return this.refNumber;
    }

    public Facility refNumber(String refNumber) {
        this.setRefNumber(refNumber);
        return this;
    }

    public void setRefNumber(String refNumber) {
        this.refNumber = refNumber;
    }

    public Double getSize() {
        return this.size;
    }

    public Facility size(Double size) {
        this.setSize(size);
        return this;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public LocalDate getDateRegistered() {
        return this.dateRegistered;
    }

    public Facility dateRegistered(LocalDate dateRegistered) {
        this.setDateRegistered(dateRegistered);
        return this;
    }

    public void setDateRegistered(LocalDate dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public StatusEnum getStatus() {
        return this.status;
    }

    public Facility status(StatusEnum status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    public FacilityType getCategory() {
        return category;
    }

    public void setCategory(FacilityType category) {
        this.category = category;
    }

    public Collection<PayItemType> getPayItemTypes() {
        return payItemTypes;
    }

    public void setPayItemTypes(Collection<PayItemType> payItemTypes) {
        this.payItemTypes = payItemTypes;
    }

    public Double calculateTotalAmount() {
        Double amount = 0.00;

        System.out.println(" Payment Type==" + payItemTypes);
        for (PayItemType payItem : payItemTypes) {
            amount += payItem.calculateActualAmount(amount);
        }
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Facility)) {
            return false;
        }
        return id != null && id.equals(((Facility) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore

    @Override
    public String toString() {
        return "Facility{" +
            "id='" + id + '\'' +
            ", description='" + description + '\'' +
            ", userType=" + userType +
            ", rentCycle=" + rentCycle +
            ", location='" + location + '\'' +
            ", refNumber='" + refNumber + '\'' +
            ", size=" + size +
            ", dateRegistered=" + dateRegistered +
            ", status=" + status +
            ", category=" + category +
            '}';
    }
}
