package ng.com.sokoto.web.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import ng.com.sokoto.web.domain.InternalAccount;
import ng.com.sokoto.web.domain.enumeration.DebitCreditEnum;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "journal-line")
public class JournalLine {

    @Id
    private String id;

    private InternalAccount account;
    private Double amount;
    private DebitCreditEnum debitCredit;

    public InternalAccount getAccount() {
        return account;
    }

    public void setAccount(InternalAccount account) {
        this.account = account;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public DebitCreditEnum getDebitCredit() {
        return debitCredit;
    }

    public void setDebitCredit(DebitCreditEnum debitCredit) {
        this.debitCredit = debitCredit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
