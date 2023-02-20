package ng.com.sokoto.web.domain;

import io.github.kaiso.relmongo.annotation.CascadeType;
import io.github.kaiso.relmongo.annotation.JoinProperty;
import io.github.kaiso.relmongo.annotation.OneToOne;
import java.time.LocalDate;
import ng.com.sokoto.web.domain.enumeration.RentCycleEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "subscription")
public class Subscription {

    @Transient
    public static final String SEQUENCE_NAME = "subsciption_sequence";

    @Id
    private String id;

    //@OneToOne//(cascade=CascadeType.PERSIST)
    //@JoinProperty(name = "subscriber")
    @DBRef
    private Subscriber subscriber;

    //@OneToOne//(cascade = CascadeType.ALL)
    //@JoinProperty(name = "facility")
    @DBRef
    private Facility facility;

    private LocalDate applicationDate;
    private String allocationRefNumber;
    private RentCycleEnum rentCycle;

    private String note;

    /*    @OneToOne
    @JoinProperty(name = "invoice")*/
    private Invoice invoice;

    /*    @OneToOne
    @JoinProperty(name = "receivable")*/
    private Wallet receivable;

    /*    @OneToOne
    @JoinProperty(name = "revenue")*/
    private Wallet revenue;

    /*    @OneToOne
    @JoinProperty(name = "cash")*/
    private Wallet cash;

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

    public Wallet getReceivable() {
        return receivable;
    }

    public void setReceivable(Wallet receivable) {
        this.receivable = receivable;
    }

    public Wallet getRevenue() {
        return revenue;
    }

    public void setRevenue(Wallet revenue) {
        this.revenue = revenue;
    }

    public Wallet getCash() {
        return cash;
    }

    public void setCash(Wallet cash) {
        this.cash = cash;
    }

    public Subscription raiseInvoice() {
        if (getInvoice() == null) {
            setInvoice(new Invoice().createInvoice(this));
            setReceivable(Wallet.createNewWallet("Receivable Account", "RECEIVABLE_" + getId()));
            setRevenue(Wallet.createNewWallet("Revenue Account", "REVENUE_" + getId()));
            setCash(Wallet.createNewWallet("Cash Account", "CASH_" + getId()));
            return this;
        }
        getInvoice().setCycle(1);
        return this;
    }

    @Override
    public String toString() {
        return (
            "Subscription{" +
            "id='" +
            id +
            '\'' +
            ", subscriber=" +
            subscriber +
            ", facility=" +
            facility +
            ", applicationDate=" +
            applicationDate +
            ", allocationRefNumber='" +
            allocationRefNumber +
            '\'' +
            ", rentCycle=" +
            rentCycle +
            ", note='" +
            note +
            '\'' +
            ", invoice=" +
            invoice +
            ", receivable=" +
            receivable +
            ", revenue=" +
            revenue +
            ", cash=" +
            cash +
            '}'
        );
    }
}
