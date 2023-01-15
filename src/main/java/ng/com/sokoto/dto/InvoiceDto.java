package ng.com.sokoto.dto;

import io.swagger.annotations.ApiModel;
import java.time.LocalDate;
import ng.com.sokoto.annotation.CheckDate;
import ng.com.sokoto.web.domain.Subscription;

@ApiModel
public class InvoiceDto extends AbstractDto<String> {

    private String id;
    private String invoiceNumber;
    private String description;
    private Double totalCost;

    @CheckDate
    private LocalDate dueDate;

    private Subscription subscription;

    public InvoiceDto() {}

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getInvoiceNumber() {
        return this.invoiceNumber;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public Double getTotalCost() {
        return this.totalCost;
    }

    public void setDueDate(java.time.LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public java.time.LocalDate getDueDate() {
        return this.dueDate;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    public Subscription getSubscription() {
        return this.subscription;
    }
}
