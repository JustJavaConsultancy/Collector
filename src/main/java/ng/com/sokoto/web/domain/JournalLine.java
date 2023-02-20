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

    private String walletNumber;
    private Double amount;
    private DebitCreditEnum debitCredit;

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

    public String getWalletNumber() {
        return walletNumber;
    }

    public void setWalletNumber(String walletNumber) {
        this.walletNumber = walletNumber;
    }

    public static JournalLine createJournalLiine(Double amount, Wallet wallet, DebitCreditEnum debitCredit) {
        JournalLine journalLine = new JournalLine();
        if (amount < 0) throw new RuntimeException("Invalid Amount: Amount Cannot be negative");

        /*
        if(debitCredit==DebitCreditEnum.Credit)
            wallet.setAmount(wallet.getAmount()+amount);
        if(debitCredit==DebitCreditEnum.Debit)
            wallet.setAmount(wallet.getAmount()-amount);
*/

        journalLine.setAmount(amount);
        journalLine.setWalletNumber(wallet.getWalletNumber());
        journalLine.setDebitCredit(debitCredit);
        return journalLine;
    }

    @Override
    public String toString() {
        return (
            "JournalLine{" +
            "id='" +
            id +
            '\'' +
            ", walletNumber='" +
            walletNumber +
            '\'' +
            ", amount=" +
            amount +
            ", debitCredit=" +
            debitCredit +
            '}'
        );
    }
}
