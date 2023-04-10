package ng.com.sokoto.web.dto.pouchii;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(
    {
        "id",
        "changes",
        "dueDate",
        "paymentType",
        "reference",
        "memo",
        "narration",
        "externalRef",
        "transDate",
        "transactionStatus",
        "userComment",
        "amount",
        "displayMemo",
    }
)
@Generated("jsonschema2pojo")
public class PaymentTransactionDTO {

    @JsonProperty("id")
    public Integer id;

    @JsonProperty("changes")
    public Double changes;

    @JsonProperty("dueDate")
    public String dueDate;

    @JsonProperty("paymentType")
    public String paymentType;

    @JsonProperty("reference")
    public String reference;

    @JsonProperty("memo")
    public String memo;

    @JsonProperty("narration")
    public String narration;

    @JsonProperty("externalRef")
    public Object externalRef;

    @JsonProperty("transDate")
    public String transDate;

    @JsonProperty("transactionStatus")
    public String transactionStatus;

    @JsonProperty("userComment")
    public String userComment;

    @JsonProperty("amount")
    public Double amount;

    @JsonProperty("displayMemo")
    public String displayMemo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getChanges() {
        return changes;
    }

    public void setChanges(Double changes) {
        this.changes = changes;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public Object getExternalRef() {
        return externalRef;
    }

    public void setExternalRef(Object externalRef) {
        this.externalRef = externalRef;
    }

    public String getTransDate() {
        return transDate;
    }

    public void setTransDate(String transDate) {
        this.transDate = transDate;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getUserComment() {
        return userComment;
    }

    public void setUserComment(String userComment) {
        this.userComment = userComment;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDisplayMemo() {
        return displayMemo;
    }

    public void setDisplayMemo(String displayMemo) {
        this.displayMemo = displayMemo;
    }

    @Override
    public String toString() {
        return (
            "PaymentTransactionDTO{" +
            "id=" +
            id +
            ", changes=" +
            changes +
            ", dueDate='" +
            dueDate +
            '\'' +
            ", paymentType='" +
            paymentType +
            '\'' +
            ", reference='" +
            reference +
            '\'' +
            ", memo='" +
            memo +
            '\'' +
            ", narration='" +
            narration +
            '\'' +
            ", externalRef=" +
            externalRef +
            ", transDate='" +
            transDate +
            '\'' +
            ", transactionStatus='" +
            transactionStatus +
            '\'' +
            ", userComment='" +
            userComment +
            '\'' +
            ", amount=" +
            amount +
            ", displayMemo='" +
            displayMemo +
            '\'' +
            '}'
        );
    }
}
