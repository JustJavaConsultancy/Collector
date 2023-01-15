package ng.com.sokoto.dto;

import io.swagger.annotations.ApiModel;
import ng.com.sokoto.web.domain.DateInterval;

@ApiModel
public class FacilityTypeDto extends AbstractDto<Long> {

    private long serialVersionUID;
    private String id;
    private String name;
    private DateInterval gracePeriod;

    public FacilityTypeDto() {}

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

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setGracePeriod(DateInterval gracePeriod) {
        this.gracePeriod = gracePeriod;
    }

    public DateInterval getGracePeriod() {
        return this.gracePeriod;
    }
}
