package ng.com.sokoto.web.domain;

import io.github.kaiso.relmongo.annotation.CascadeType;
import io.github.kaiso.relmongo.annotation.FetchType;
import io.github.kaiso.relmongo.annotation.JoinProperty;
import io.github.kaiso.relmongo.annotation.OneToOne;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.time.LocalDate;
import ng.com.sokoto.web.domain.enumeration.SubscriberStatusEnum;
import ng.com.sokoto.web.domain.enumeration.SubscriberTypeEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Subscriber.
 */
@Document(collection = "subscriber")
public class Subscriber implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("last_name")
    private String lastName;

    @Field("first_name")
    private String firstName;

    /**
     * category SubscriberCategory,
     */
    @Schema(description = "category SubscriberCategory,")
    @Field("middle_name")
    private String middleName;

    @Field("type")
    private SubscriberTypeEnum type;

    @Field("date_of_birth")
    private LocalDate dateOfBirth;

    @Field("phone_number")
    private String phoneNumber;

    @Field("id_number")
    private String idNumber;

    @Field("status")
    private SubscriberStatusEnum status;

    @Field("address")
    private String address;

    @Field("email")
    private String email;

    @Field("date_registered")
    private LocalDate dateRegistered;

    private IdType idDocument;
    private NextOfKin nextOfKin;
    private Wallet wallet;

    private String login;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @DBRef
    private User user;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Subscriber id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastName() {
        return this.lastName;
    }

    public Subscriber lastName(String lastName) {
        this.setLastName(lastName);
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public Subscriber firstName(String firstName) {
        this.setFirstName(firstName);
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return this.middleName;
    }

    public Subscriber middleName(String middleName) {
        this.setMiddleName(middleName);
        return this;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public SubscriberTypeEnum getType() {
        return this.type;
    }

    public Subscriber type(SubscriberTypeEnum type) {
        this.setType(type);
        return this;
    }

    public void setType(SubscriberTypeEnum type) {
        this.type = type;
    }

    public LocalDate getDateOfBirth() {
        return this.dateOfBirth;
    }

    public Subscriber dateOfBirth(LocalDate dateOfBirth) {
        this.setDateOfBirth(dateOfBirth);
        return this;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public Subscriber phoneNumber(String phoneNumber) {
        this.setPhoneNumber(phoneNumber);
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIdNumber() {
        return this.idNumber;
    }

    public Subscriber idNumber(String idNumber) {
        this.setIdNumber(idNumber);
        return this;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public SubscriberStatusEnum getStatus() {
        return this.status;
    }

    public Subscriber status(SubscriberStatusEnum status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(SubscriberStatusEnum status) {
        this.status = status;
    }

    public String getAddress() {
        return this.address;
    }

    public Subscriber address(String address) {
        this.setAddress(address);
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return this.email;
    }

    public Subscriber email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateRegistered() {
        return this.dateRegistered;
    }

    public Subscriber dateRegistered(LocalDate dateRegistered) {
        this.setDateRegistered(dateRegistered);
        return this;
    }

    public void setDateRegistered(LocalDate dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public IdType getIdDocument() {
        return idDocument;
    }

    public void setIdDocument(IdType idDocument) {
        this.idDocument = idDocument;
    }

    public NextOfKin getNextOfKin() {
        return nextOfKin;
    }

    public void setNextOfKin(NextOfKin nextOfKin) {
        this.nextOfKin = nextOfKin;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Subscriber)) {
            return false;
        }
        return id != null && id.equals(((Subscriber) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Subscriber{" +
            "id=" + getId() +
            ", lastName='" + getLastName() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", middleName='" + getMiddleName() + "'" +
            ", type='" + getType() + "'" +
            ", dateOfBirth='" + getDateOfBirth() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", idNumber='" + getIdNumber() + "'" +
            ", status='" + getStatus() + "'" +
            ", address='" + getAddress() + "'" +
            ", email='" + getEmail() + "'" +
            ", dateRegistered='" + getDateRegistered() + "'" +
            "}";
    }
}
