package ng.com.sokoto.web.dto.pouchii;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(
    {
        "destAccountNumber",
        "amount",
        "channel",
        "sourceBankCode",
        "sourceAccountNumber",
        "destBankCode",
        "pin",
        "transRef",
        "phoneNumber",
        "narration",
        "beneficiaryName",
    }
)
@Generated("jsonschema2pojo")
public class SendMoneyDTO {

    @JsonProperty("destAccountNumber")
    private String destAccountNumber;

    @JsonProperty("amount")
    private Integer amount;

    @JsonProperty("channel")
    private String channel;

    @JsonProperty("sourceBankCode")
    private String sourceBankCode;

    @JsonProperty("sourceAccountNumber")
    private String sourceAccountNumber;

    @JsonProperty("destBankCode")
    private String destBankCode;

    @JsonProperty("pin")
    private String pin;

    @JsonProperty("transRef")
    private String transRef;

    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @JsonProperty("narration")
    private String narration;

    @JsonProperty("beneficiaryName")
    private String beneficiaryName;

    public String getDestAccountNumber() {
        return destAccountNumber;
    }

    public void setDestAccountNumber(String destAccountNumber) {
        this.destAccountNumber = destAccountNumber;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getSourceBankCode() {
        return sourceBankCode;
    }

    public void setSourceBankCode(String sourceBankCode) {
        this.sourceBankCode = sourceBankCode;
    }

    public String getSourceAccountNumber() {
        return sourceAccountNumber;
    }

    public void setSourceAccountNumber(String sourceAccountNumber) {
        this.sourceAccountNumber = sourceAccountNumber;
    }

    public String getDestBankCode() {
        return destBankCode;
    }

    public void setDestBankCode(String destBankCode) {
        this.destBankCode = destBankCode;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getTransRef() {
        return transRef;
    }

    public void setTransRef(String transRef) {
        this.transRef = transRef;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public String getBeneficiaryName() {
        return beneficiaryName;
    }

    public void setBeneficiaryName(String beneficiaryName) {
        this.beneficiaryName = beneficiaryName;
    }

    @Override
    public String toString() {
        return (
            "SendMoneyDTO{" +
            "destAccountNumber='" +
            destAccountNumber +
            '\'' +
            ", amount=" +
            amount +
            ", channel='" +
            channel +
            '\'' +
            ", sourceBankCode='" +
            sourceBankCode +
            '\'' +
            ", sourceAccountNumber='" +
            sourceAccountNumber +
            '\'' +
            ", destBankCode='" +
            destBankCode +
            '\'' +
            ", pin='" +
            pin +
            '\'' +
            ", transRef='" +
            transRef +
            '\'' +
            ", phoneNumber='" +
            phoneNumber +
            '\'' +
            ", narration='" +
            narration +
            '\'' +
            ", beneficiaryName='" +
            beneficiaryName +
            '\'' +
            '}'
        );
    }
}
