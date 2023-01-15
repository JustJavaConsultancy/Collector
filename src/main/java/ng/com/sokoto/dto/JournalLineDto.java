package ng.com.sokoto.dto;

import io.swagger.annotations.ApiModel;
import ng.com.sokoto.web.domain.InternalAccount;
import ng.com.sokoto.web.domain.enumeration.DebitCreditEnum;

@ApiModel
public class JournalLineDto extends AbstractDto<String> {

    private String id;
    private InternalAccount account;
    private Double amount;
    private DebitCreditEnum debitCredit;

    public JournalLineDto() {}

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setAccount(InternalAccount account) {
        this.account = account;
    }

    public InternalAccount getAccount() {
        return this.account;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getAmount() {
        return this.amount;
    }

    public void setDebitCredit(DebitCreditEnum debitCredit) {
        this.debitCredit = debitCredit;
    }

    public DebitCreditEnum getDebitCredit() {
        return this.debitCredit;
    }
}
