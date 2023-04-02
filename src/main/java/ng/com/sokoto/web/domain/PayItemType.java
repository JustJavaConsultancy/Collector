package ng.com.sokoto.web.domain;

import java.io.Serializable;
import javax.persistence.Id;
import ng.com.sokoto.web.domain.enumeration.RateTypeEnum;
import ng.com.sokoto.web.domain.enumeration.TypeEnum;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A PayItemType.
 */
@Document(collection = "payitem-type")
public class PayItemType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @Field("description")
    private String description;

    @Field("rate")
    private Double rate;

    @Field("type")
    private TypeEnum type;

    @Field("rate_type")
    private RateTypeEnum rateType;

    @Field("rent_component")
    private Boolean rentComponent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getDescription() {
        return this.description;
    }

    public PayItemType description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getRate() {
        return this.rate;
    }

    public PayItemType rate(Double rate) {
        this.setRate(rate);
        return this;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public TypeEnum getType() {
        return this.type;
    }

    public PayItemType type(TypeEnum type) {
        this.setType(type);
        return this;
    }

    public void setType(TypeEnum type) {
        this.type = type;
    }

    public RateTypeEnum getRateType() {
        return this.rateType;
    }

    public PayItemType rateType(RateTypeEnum rateType) {
        this.setRateType(rateType);
        return this;
    }

    public void setRateType(RateTypeEnum rateType) {
        this.rateType = rateType;
    }

    public Boolean getRentComponent() {
        return this.rentComponent;
    }

    public PayItemType rentComponent(Boolean rentComponent) {
        this.setRentComponent(rentComponent);
        return this;
    }

    public void setRentComponent(Boolean rentComponent) {
        this.rentComponent = rentComponent;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here
    public Double calculateActualAmount(Double amount) {
        Double actualAmount = 0.00;
        if (type == TypeEnum.Amount) {
            actualAmount = getRate();
        }
        if (type == TypeEnum.Percentage) {
            actualAmount = getRate() * amount;
        }
        return actualAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PayItemType)) {
            return false;
        }
        return false;
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PayItemType{" +
            ", description='" + getDescription() + "'" +
            ", rate=" + getRate() +
            ", type='" + getType() + "'" +
            ", rateType='" + getRateType() + "'" +
            ", rentComponent='" + getRentComponent() + "'" +
            "}";
    }
}
