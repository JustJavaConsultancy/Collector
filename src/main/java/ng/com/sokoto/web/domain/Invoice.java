package ng.com.sokoto.web.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Entity;
import ng.com.sokoto.web.domain.enumeration.RentCycleEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "invoice")
public class Invoice implements Serializable {

    @Id
    private String id;

    private String invoiceNumber;
    private String description;
    private Double totalCost;
    private LocalDate dueDate;

    private Integer cycle;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCycle() {
        return cycle;
    }

    public void setCycle(Integer cycle) {
        this.cycle = cycle;
    }

    private LocalDate getNextDueDate(Subscription subscription) {
        cycle = 0;
        RentCycleEnum rentCycle = subscription.getRentCycle();
        LocalDate applicationDate = subscription.getApplicationDate();
        LocalDate nextDueDate = applicationDate;
        while (nextDueDate.isBefore(LocalDate.now())) {
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
            cycle = cycle + 1;
        }
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
        cycle = cycle + 1;
        return nextDueDate;
    }

    public Invoice createInvoice(Subscription subscription) {
        setInvoiceNumber(String.valueOf(System.currentTimeMillis()));
        setTotalCost(subscription.getFacility().calculateTotalAmount());
        setDueDate(getNextDueDate(subscription));
        setId("INV_" + subscription.getId());
        return this;
    }

    @Override
    public String toString() {
        return (
            "Invoice{" +
            "invoiceNumber='" +
            invoiceNumber +
            '\'' +
            ", description='" +
            description +
            '\'' +
            ", totalCost=" +
            totalCost +
            ", dueDate=" +
            dueDate +
            '}'
        );
    }
}
