package ng.com.sokoto.web.dto.pouchii;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(
    {
        "phoneNumber",
        "firstName",
        "lastName",
        "password",
        "pin",
        "dateOfBirth",
        "gender",
        "state",
        "localGovt",
        "latitude",
        "longitude",
        "address",
        "scheme",
        "accountName",
        "email",
        "photo",
    }
)
@Generated("jsonschema2pojo")
public class CreateWalletExternal {

    @JsonProperty("phoneNumber")
    public String phoneNumber;

    @JsonProperty("firstName")
    public String firstName;

    @JsonProperty("lastName")
    public String lastName;

    @JsonProperty("password")
    public String password;

    @JsonProperty("pin")
    public String pin;

    @JsonProperty("dateOfBirth")
    public String dateOfBirth;

    @JsonProperty("gender")
    public String gender;

    @JsonProperty("state")
    public String state;

    @JsonProperty("localGovt")
    public String localGovt;

    @JsonProperty("latitude")
    public String latitude;

    @JsonProperty("longitude")
    public String longitude;

    @JsonProperty("address")
    public String address;

    @JsonProperty("scheme")
    public String scheme;

    @JsonProperty("accountName")
    public String accountName;

    @JsonProperty("email")
    public String email;

    @JsonProperty("photo")
    public String photo;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLocalGovt() {
        return localGovt;
    }

    public void setLocalGovt(String localGovt) {
        this.localGovt = localGovt;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
