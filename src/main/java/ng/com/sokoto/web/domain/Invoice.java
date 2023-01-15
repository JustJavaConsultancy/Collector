package ng.com.sokoto.web.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.Id;
import ng.com.sokoto.web.domain.enumeration.RentCycleEnum;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "invoice")
public class Invoice {

    @Id
    private String id;

    private String invoiceNumber;
    private String description;
    private Double totalCost;
    private LocalDate dueDate;
    private Subscription subscription;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    private LocalDate getNextDueDate(Subscription subscription) {
        RentCycleEnum rentCycle = subscription.getRentCycle();
        LocalDate nextDueDate = dueDate;
        switch (rentCycle) {
            case Daily:
                nextDueDate = nextDueDate.plusDays(1);
                break;
            case Weekly:
                nextDueDate = nextDueDate.plusWeeks(1);
                break;
            case Monthly:
                nextDueDate = nextDueDate.plusMonths(1);
                break;
            case Yearly:
                nextDueDate = nextDueDate.plusYears(1);
                break;
        }
        return nextDueDate;
    }

    public Invoice createInvoice(Subscription subscription) {
        setInvoiceNumber(String.valueOf(System.currentTimeMillis()));
        setTotalCost(subscription.getFacility().calculateTotalAmount());
        setDueDate(getNextDueDate(subscription));
        return this;
    }

    public Journal createJournal() {
        Journal journal = new Journal();
        Collection<JournalLine> journalLines = new ArrayList<JournalLine>();
        //journalLines.add();
        return journal;
    }
}
