package ng.com.sokoto.dto;

import io.swagger.annotations.ApiModel;
import ng.com.sokoto.web.domain.enumeration.RateTypeEnum;
import ng.com.sokoto.web.domain.enumeration.TypeEnum;

@ApiModel
public class PayItemTypeDto extends AbstractDto<Long> {

    private long serialVersionUID;
    private Long id;
    private String description;
    private Double rate;
    private TypeEnum type;
    private RateTypeEnum rateType;
    private Boolean rentComponent;

    public PayItemTypeDto() {}

    public void setSerialVersionUID(long serialVersionUID) {
        this.serialVersionUID = serialVersionUID;
    }

    public long getSerialVersionUID() {
        return this.serialVersionUID;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getRate() {
        return this.rate;
    }

    public void setType(TypeEnum type) {
        this.type = type;
    }

    public TypeEnum getType() {
        return this.type;
    }

    public void setRateType(RateTypeEnum rateType) {
        this.rateType = rateType;
    }

    public RateTypeEnum getRateType() {
        return this.rateType;
    }

    public void setRentComponent(Boolean rentComponent) {
        this.rentComponent = rentComponent;
    }

    public Boolean getRentComponent() {
        return this.rentComponent;
    }
}
