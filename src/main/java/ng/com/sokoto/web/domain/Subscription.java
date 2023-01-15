package ng.com.sokoto.web.domain;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Id;
import ng.com.sokoto.web.domain.enumeration.RentCycleEnum;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "subscription")
public class Subscription {

    @Id
    private String id;

    private Subscriber subscriber;
    private Facility facility;
    private LocalDate applicationDate;
    private String allocationRefNumber;
    private RentCycleEnum rentCycle;

    private String note;

    private Invoice invoice;

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    public Facility getFacility() {
        return facility;
    }

    public void setFacility(Facility facility) {
        this.facility = facility;
    }

    public LocalDate getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDate applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getAllocationRefNumber() {
        return allocationRefNumber;
    }

    public void setAllocationRefNumber(String allocationRefNumber) {
        this.allocationRefNumber = allocationRefNumber;
    }

    public RentCycleEnum getRentCycle() {
        return rentCycle;
    }

    public void setRentCycle(RentCycleEnum rentCycle) {
        this.rentCycle = rentCycle;
    }

    public Subscription raiseInvoice() {
        setInvoice(new Invoice().createInvoice(this));
        return this;
    }
}
