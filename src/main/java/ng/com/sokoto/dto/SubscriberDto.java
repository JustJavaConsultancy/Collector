package ng.com.sokoto.dto;

import io.swagger.annotations.ApiModel;
import java.time.LocalDate;
import ng.com.sokoto.annotation.CheckDate;
import ng.com.sokoto.annotation.CheckEmail;
import ng.com.sokoto.annotation.CheckMobile;
import ng.com.sokoto.web.domain.IdType;
import ng.com.sokoto.web.domain.NextOfKin;
import ng.com.sokoto.web.domain.Wallet;
import ng.com.sokoto.web.domain.enumeration.SubscriberStatusEnum;
import ng.com.sokoto.web.domain.enumeration.SubscriberTypeEnum;

@ApiModel
public class SubscriberDto extends AbstractDto<String> {

    private long serialVersionUID;
    private String id;
    private String lastName;
    private String firstName;
    private String middleName;
    private SubscriberTypeEnum type;

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    private Wallet wallet;

    @CheckDate
    private LocalDate dateOfBirth;

    @CheckMobile
    private String phoneNumber;

    private String idNumber;
    private SubscriberStatusEnum status;
    private String address;

    @CheckEmail
    private String email;

    @CheckDate
    private LocalDate dateRegistered;

    private IdType idDocument;
    private NextOfKin nextOfKin;
    //private Wallet wallet;
    private String login;

    public SubscriberDto() {}

    public void setSerialVersionUID(long serialVersionUID) {
        this.serialVersionUID = serialVersionUID;
    }

    public long getSerialVersionUID() {
        return this.serialVersionUID;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getMiddleName() {
        return this.middleName;
    }

    public void setType(SubscriberTypeEnum type) {
        this.type = type;
    }

    public SubscriberTypeEnum getType() {
        return this.type;
    }

    public void setDateOfBirth(java.time.LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public java.time.LocalDate getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getIdNumber() {
        return this.idNumber;
    }

    public void setStatus(SubscriberStatusEnum status) {
        this.status = status;
    }

    public SubscriberStatusEnum getStatus() {
        return this.status;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return this.address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setDateRegistered(java.time.LocalDate dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public java.time.LocalDate getDateRegistered() {
        return this.dateRegistered;
    }

    public void setIdDocument(IdType idDocument) {
        this.idDocument = idDocument;
    }

    public IdType getIdDocument() {
        return this.idDocument;
    }

    public void setNextOfKin(NextOfKin nextOfKin) {
        this.nextOfKin = nextOfKin;
    }

    public NextOfKin getNextOfKin() {
        return this.nextOfKin;
    }

    /*
    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public Wallet getWallet() {
        return this.wallet;
    }
*/

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return this.login;
    }
}
