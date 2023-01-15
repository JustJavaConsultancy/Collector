package ng.com.sokoto.web.domain;

import java.io.Serializable;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A SubscriberWallet.
 */
public class SubscriberWallet implements Serializable {

    private static final long serialVersionUID = 1L;

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

    public SubscriberWallet name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWalletNumber() {
        return this.walletNumber;
    }

    public SubscriberWallet walletNumber(String walletNumber) {
        this.setWalletNumber(walletNumber);
        return this;
    }

    public void setWalletNumber(String walletNumber) {
        this.walletNumber = walletNumber;
    }

    public Double getAmount() {
        return this.amount;
    }

    public SubscriberWallet amount(Double amount) {
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
        if (!(o instanceof SubscriberWallet)) {
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
        return "SubscriberWallet{" +
            ", name='" + getName() + "'" +
            ", walletNumber='" + getWalletNumber() + "'" +
            ", amount='" + getAmount() + "'" +
            "}";
    }
}
