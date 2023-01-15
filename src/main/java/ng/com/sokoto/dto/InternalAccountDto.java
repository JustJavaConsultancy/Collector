package ng.com.sokoto.dto;

import io.swagger.annotations.ApiModel;

@ApiModel
public class InternalAccountDto extends AbstractDto<Long> {

    private Long id;
    private String number;
    private String name;
    private Double amount;

    public InternalAccountDto() {}

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return this.number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getAmount() {
        return this.amount;
    }
}
