package ng.com.sokoto.dto;

import io.swagger.annotations.ApiModel;
import java.time.LocalDate;
import ng.com.sokoto.annotation.CheckDate;
import ng.com.sokoto.web.domain.Facility;
import ng.com.sokoto.web.domain.Subscriber;
import ng.com.sokoto.web.domain.enumeration.RentCycleEnum;
import ng.com.sokoto.web.dto.AbstractDto;

@ApiModel
public class SubscriptionDto extends AbstractDto<String> {

    private String id;
    private String subscriber;
    private String facility;

    @CheckDate
    private LocalDate applicationDate;

    private String allocationRefNumber;
    private RentCycleEnum rentCycle;

    private String note;

    public SubscriptionDto() {}

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setSubscriber(String subscriber) {
        this.subscriber = subscriber;
    }

    public String getSubscriber() {
        return this.subscriber;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public String getFacility() {
        return this.facility;
    }

    public void setApplicationDate(java.time.LocalDate applicationDate) {
        this.applicationDate = applicationDate;
    }

    public java.time.LocalDate getApplicationDate() {
        return this.applicationDate;
    }

    public void setAllocationRefNumber(String allocationRefNumber) {
        this.allocationRefNumber = allocationRefNumber;
    }

    public String getAllocationRefNumber() {
        return this.allocationRefNumber;
    }

    public void setRentCycle(RentCycleEnum rentCycle) {
        this.rentCycle = rentCycle;
    }

    public RentCycleEnum getRentCycle() {
        return this.rentCycle;
    }
}
