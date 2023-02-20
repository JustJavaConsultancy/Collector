package ng.com.sokoto.web.domain;

import java.io.Serializable;
import java.util.List;
import ng.com.sokoto.web.domain.enumeration.DebitCreditEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Wallet.
 */
@Document(collection = "wallet")
public class Wallet implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("name")
    private String name;

    @Field("wallet_number")
    private String walletNumber;

    @Field("amount")
    private Double amount;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getName() {
        return this.name;
    }

    public Wallet name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWalletNumber() {
        return this.walletNumber;
    }

    public Wallet walletNumber(String walletNumber) {
        this.setWalletNumber(walletNumber);
        return this;
    }

    public void setWalletNumber(String walletNumber) {
        this.walletNumber = walletNumber;
    }

    public Double getAmount() {
        return this.amount;
    }

    public Wallet amount(Double amount) {
        this.setAmount(amount);
        return this;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Wallet)) {
            return false;
        }
        return false;
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Wallet{" +
            ", name='" + getName() + "'" +
            ", walletNumber='" + getWalletNumber() + "'" +
            ", amount='" + getAmount() + "'" +
            "}";
    }

    public static Wallet createNewWallet(String name, String number) {
        Wallet wallet = new Wallet();
        wallet.setId(number);
        wallet.setWalletNumber(number);
        wallet.setAmount(0.00D);
        wallet.setName(name);
        return wallet;
    }

    public JournalLine debit(Double amount) {
        setAmount(getAmount() - amount);
        return JournalLine.createJournalLiine(amount, this, DebitCreditEnum.Debit);
    }

    public JournalLine credit(Double amount) {
        setAmount(getAmount() + amount);
        return JournalLine.createJournalLiine(amount, this, DebitCreditEnum.Credit);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
