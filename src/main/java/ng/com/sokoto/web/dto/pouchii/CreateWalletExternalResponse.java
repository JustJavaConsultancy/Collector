package ng.com.sokoto.web.dto.pouchii;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;
import javax.annotation.Generated;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "code", "message", "data", "metadata" })
@Data
@Generated("jsonschema2pojo")
public class CreateWalletExternalResponse {

    @JsonProperty("code")
    private String code;

    @JsonProperty("message")
    private String message;

    @JsonProperty("data")
    private List<Datum> data;

    @JsonProperty("metadata")
    private Object metadata;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public Object getMetadata() {
        return metadata;
    }

    public void setMetadata(Object metadata) {
        this.metadata = metadata;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder(
        {
            "createdDate",
            "lastModifiedDate",
            "id",
            "profileID",
            "pin",
            "deviceNotificationToken",
            "phoneNumber",
            "gender",
            "dateOfBirth",
            "address",
            "secretQuestion",
            "secretAnswer",
            "photo",
            "photoContentType",
            "bvn",
            "validID",
            "validDocType",
            "nin",
            "profilePicture",
            "totalBonus",
            "myDevices",
            "paymentTransactions",
            "billerTransactions",
            "customersubscriptions",
            "user",
            "bonusPoints",
            "approvalGroup",
            "profileType",
            "kyc",
            "beneficiaries",
            "addresses",
            "profileStatus",
            "alternativePhoneNumber1",
            "alternativePhoneNumber2",
            "pinActive",
            "fullName",
        }
    )
    @Data
    @Generated("jsonschema2pojo")
    public static class AccountOwner {

        @JsonProperty("createdDate")
        private Object createdDate;

        @JsonProperty("lastModifiedDate")
        private String lastModifiedDate;

        @JsonProperty("id")
        private Integer id;

        @JsonProperty("profileID")
        private Object profileID;

        @JsonProperty("pin")
        private Object pin;

        @JsonProperty("deviceNotificationToken")
        private Object deviceNotificationToken;

        @JsonProperty("phoneNumber")
        private Object phoneNumber;

        @JsonProperty("gender")
        private Object gender;

        @JsonProperty("dateOfBirth")
        private Object dateOfBirth;

        @JsonProperty("address")
        private Object address;

        @JsonProperty("secretQuestion")
        private Object secretQuestion;

        @JsonProperty("secretAnswer")
        private Object secretAnswer;

        @JsonProperty("photo")
        private Object photo;

        @JsonProperty("photoContentType")
        private Object photoContentType;

        @JsonProperty("bvn")
        private Object bvn;

        @JsonProperty("validID")
        private Object validID;

        @JsonProperty("validDocType")
        private String validDocType;

        @JsonProperty("nin")
        private Object nin;

        @JsonProperty("profilePicture")
        private Object profilePicture;

        @JsonProperty("totalBonus")
        private Double totalBonus;

        @JsonProperty("myDevices")
        private List<Object> myDevices;

        @JsonProperty("paymentTransactions")
        private List<Object> paymentTransactions;

        @JsonProperty("billerTransactions")
        private List<Object> billerTransactions;

        @JsonProperty("customersubscriptions")
        private List<Object> customersubscriptions;

        @JsonProperty("user")
        private Object user;

        @JsonProperty("bonusPoints")
        private List<Object> bonusPoints;

        @JsonProperty("approvalGroup")
        private Object approvalGroup;

        @JsonProperty("profileType")
        private Object profileType;

        @JsonProperty("kyc")
        private Object kyc;

        @JsonProperty("beneficiaries")
        private List<Object> beneficiaries;

        @JsonProperty("addresses")
        private List<Object> addresses;

        @JsonProperty("profileStatus")
        private Object profileStatus;

        @JsonProperty("alternativePhoneNumber1")
        private Object alternativePhoneNumber1;

        @JsonProperty("alternativePhoneNumber2")
        private Object alternativePhoneNumber2;

        @JsonProperty("pinActive")
        private Boolean pinActive;

        @JsonProperty("fullName")
        private String fullName;

        public Object getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(Object createdDate) {
            this.createdDate = createdDate;
        }

        public String getLastModifiedDate() {
            return lastModifiedDate;
        }

        public void setLastModifiedDate(String lastModifiedDate) {
            this.lastModifiedDate = lastModifiedDate;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Object getProfileID() {
            return profileID;
        }

        public void setProfileID(Object profileID) {
            this.profileID = profileID;
        }

        public Object getPin() {
            return pin;
        }

        public void setPin(Object pin) {
            this.pin = pin;
        }

        public Object getDeviceNotificationToken() {
            return deviceNotificationToken;
        }

        public void setDeviceNotificationToken(Object deviceNotificationToken) {
            this.deviceNotificationToken = deviceNotificationToken;
        }

        public Object getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(Object phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public Object getGender() {
            return gender;
        }

        public void setGender(Object gender) {
            this.gender = gender;
        }

        public Object getDateOfBirth() {
            return dateOfBirth;
        }

        public void setDateOfBirth(Object dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
        }

        public Object getAddress() {
            return address;
        }

        public void setAddress(Object address) {
            this.address = address;
        }

        public Object getSecretQuestion() {
            return secretQuestion;
        }

        public void setSecretQuestion(Object secretQuestion) {
            this.secretQuestion = secretQuestion;
        }

        public Object getSecretAnswer() {
            return secretAnswer;
        }

        public void setSecretAnswer(Object secretAnswer) {
            this.secretAnswer = secretAnswer;
        }

        public Object getPhoto() {
            return photo;
        }

        public void setPhoto(Object photo) {
            this.photo = photo;
        }

        public Object getPhotoContentType() {
            return photoContentType;
        }

        public void setPhotoContentType(Object photoContentType) {
            this.photoContentType = photoContentType;
        }

        public Object getBvn() {
            return bvn;
        }

        public void setBvn(Object bvn) {
            this.bvn = bvn;
        }

        public Object getValidID() {
            return validID;
        }

        public void setValidID(Object validID) {
            this.validID = validID;
        }

        public String getValidDocType() {
            return validDocType;
        }

        public void setValidDocType(String validDocType) {
            this.validDocType = validDocType;
        }

        public Object getNin() {
            return nin;
        }

        public void setNin(Object nin) {
            this.nin = nin;
        }

        public Object getProfilePicture() {
            return profilePicture;
        }

        public void setProfilePicture(Object profilePicture) {
            this.profilePicture = profilePicture;
        }

        public Double getTotalBonus() {
            return totalBonus;
        }

        public void setTotalBonus(Double totalBonus) {
            this.totalBonus = totalBonus;
        }

        public List<Object> getMyDevices() {
            return myDevices;
        }

        public void setMyDevices(List<Object> myDevices) {
            this.myDevices = myDevices;
        }

        public List<Object> getPaymentTransactions() {
            return paymentTransactions;
        }

        public void setPaymentTransactions(List<Object> paymentTransactions) {
            this.paymentTransactions = paymentTransactions;
        }

        public List<Object> getBillerTransactions() {
            return billerTransactions;
        }

        public void setBillerTransactions(List<Object> billerTransactions) {
            this.billerTransactions = billerTransactions;
        }

        public List<Object> getCustomersubscriptions() {
            return customersubscriptions;
        }

        public void setCustomersubscriptions(List<Object> customersubscriptions) {
            this.customersubscriptions = customersubscriptions;
        }

        public Object getUser() {
            return user;
        }

        public void setUser(Object user) {
            this.user = user;
        }

        public List<Object> getBonusPoints() {
            return bonusPoints;
        }

        public void setBonusPoints(List<Object> bonusPoints) {
            this.bonusPoints = bonusPoints;
        }

        public Object getApprovalGroup() {
            return approvalGroup;
        }

        public void setApprovalGroup(Object approvalGroup) {
            this.approvalGroup = approvalGroup;
        }

        public Object getProfileType() {
            return profileType;
        }

        public void setProfileType(Object profileType) {
            this.profileType = profileType;
        }

        public Object getKyc() {
            return kyc;
        }

        public void setKyc(Object kyc) {
            this.kyc = kyc;
        }

        public List<Object> getBeneficiaries() {
            return beneficiaries;
        }

        public void setBeneficiaries(List<Object> beneficiaries) {
            this.beneficiaries = beneficiaries;
        }

        public List<Object> getAddresses() {
            return addresses;
        }

        public void setAddresses(List<Object> addresses) {
            this.addresses = addresses;
        }

        public Object getProfileStatus() {
            return profileStatus;
        }

        public void setProfileStatus(Object profileStatus) {
            this.profileStatus = profileStatus;
        }

        public Object getAlternativePhoneNumber1() {
            return alternativePhoneNumber1;
        }

        public void setAlternativePhoneNumber1(Object alternativePhoneNumber1) {
            this.alternativePhoneNumber1 = alternativePhoneNumber1;
        }

        public Object getAlternativePhoneNumber2() {
            return alternativePhoneNumber2;
        }

        public void setAlternativePhoneNumber2(Object alternativePhoneNumber2) {
            this.alternativePhoneNumber2 = alternativePhoneNumber2;
        }

        public Boolean getPinActive() {
            return pinActive;
        }

        public void setPinActive(Boolean pinActive) {
            this.pinActive = pinActive;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder(
        {
            "id",
            "accountName",
            "accountNumber",
            "currentBalance",
            "nubanAccountNo",
            "trackingRef",
            "dateOpened",
            "status",
            "actualBalance",
            "walletLimit",
            "scheme",
            "walletAccountType",
            "accountOwner",
            "parent",
            "subWallets",
            "accountFullName",
        }
    )
    @Data
    @Generated("jsonschema2pojo")
    public static class Datum {

        @JsonProperty("id")
        private Integer id;

        @JsonProperty("accountName")
        private String accountName;

        @JsonProperty("accountNumber")
        private String accountNumber;

        @JsonProperty("currentBalance")
        private String currentBalance;

        @JsonProperty("nubanAccountNo")
        private String nubanAccountNo;

        @JsonProperty("trackingRef")
        private Object trackingRef;

        @JsonProperty("dateOpened")
        private String dateOpened;

        @JsonProperty("status")
        private String status;

        @JsonProperty("actualBalance")
        private String actualBalance;

        @JsonProperty("walletLimit")
        private String walletLimit;

        @JsonProperty("scheme")
        private Scheme scheme;

        @JsonProperty("walletAccountType")
        private WalletAccountType walletAccountType;

        @JsonProperty("accountOwner")
        private AccountOwner accountOwner;

        @JsonProperty("parent")
        private Object parent;

        @JsonProperty("subWallets")
        private List<Object> subWallets;

        @JsonProperty("accountFullName")
        private String accountFullName;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getAccountName() {
            return accountName;
        }

        public void setAccountName(String accountName) {
            this.accountName = accountName;
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        public void setAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
        }

        public String getCurrentBalance() {
            return currentBalance;
        }

        public void setCurrentBalance(String currentBalance) {
            this.currentBalance = currentBalance;
        }

        public String getNubanAccountNo() {
            return nubanAccountNo;
        }

        public void setNubanAccountNo(String nubanAccountNo) {
            this.nubanAccountNo = nubanAccountNo;
        }

        public Object getTrackingRef() {
            return trackingRef;
        }

        public void setTrackingRef(Object trackingRef) {
            this.trackingRef = trackingRef;
        }

        public String getDateOpened() {
            return dateOpened;
        }

        public void setDateOpened(String dateOpened) {
            this.dateOpened = dateOpened;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getActualBalance() {
            return actualBalance;
        }

        public void setActualBalance(String actualBalance) {
            this.actualBalance = actualBalance;
        }

        public String getWalletLimit() {
            return walletLimit;
        }

        public void setWalletLimit(String walletLimit) {
            this.walletLimit = walletLimit;
        }

        public Scheme getScheme() {
            return scheme;
        }

        public void setScheme(Scheme scheme) {
            this.scheme = scheme;
        }

        public WalletAccountType getWalletAccountType() {
            return walletAccountType;
        }

        public void setWalletAccountType(WalletAccountType walletAccountType) {
            this.walletAccountType = walletAccountType;
        }

        public AccountOwner getAccountOwner() {
            return accountOwner;
        }

        public void setAccountOwner(AccountOwner accountOwner) {
            this.accountOwner = accountOwner;
        }

        public Object getParent() {
            return parent;
        }

        public void setParent(Object parent) {
            this.parent = parent;
        }

        public List<Object> getSubWallets() {
            return subWallets;
        }

        public void setSubWallets(List<Object> subWallets) {
            this.subWallets = subWallets;
        }

        public String getAccountFullName() {
            return accountFullName;
        }

        public void setAccountFullName(String accountFullName) {
            this.accountFullName = accountFullName;
        }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder(
        {
            "createdDate",
            "lastModifiedDate",
            "id",
            "schemeID",
            "scheme",
            "bankCode",
            "accountNumber",
            "apiKey",
            "secretKey",
            "callBackUrl",
            "schemeCategory",
            "charges",
            "schemeType",
            "services",
            "regStage",
            "productType",
            "callbackUrl",
        }
    )
    @Data
    @Generated("jsonschema2pojo")
    public static class Scheme {

        @JsonProperty("createdDate")
        private String createdDate;

        @JsonProperty("lastModifiedDate")
        private String lastModifiedDate;

        @JsonProperty("id")
        private Integer id;

        @JsonProperty("schemeID")
        private Object schemeID;

        @JsonProperty("scheme")
        private Object scheme;

        @JsonProperty("bankCode")
        private Object bankCode;

        @JsonProperty("accountNumber")
        private Object accountNumber;

        @JsonProperty("apiKey")
        private Object apiKey;

        @JsonProperty("secretKey")
        private Object secretKey;

        @JsonProperty("callBackUrl")
        private Object callBackUrl;

        @JsonProperty("schemeCategory")
        private Object schemeCategory;

        @JsonProperty("charges")
        private List<Object> charges;

        @JsonProperty("schemeType")
        private Object schemeType;

        @JsonProperty("services")
        private List<Object> services;

        @JsonProperty("regStage")
        private Object regStage;

        @JsonProperty("productType")
        private Object productType;

        @JsonProperty("callbackUrl")
        private Object callbackUrl;

        public String getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(String createdDate) {
            this.createdDate = createdDate;
        }

        public String getLastModifiedDate() {
            return lastModifiedDate;
        }

        public void setLastModifiedDate(String lastModifiedDate) {
            this.lastModifiedDate = lastModifiedDate;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Object getSchemeID() {
            return schemeID;
        }

        public void setSchemeID(Object schemeID) {
            this.schemeID = schemeID;
        }

        public Object getScheme() {
            return scheme;
        }

        public void setScheme(Object scheme) {
            this.scheme = scheme;
        }

        public Object getBankCode() {
            return bankCode;
        }

        public void setBankCode(Object bankCode) {
            this.bankCode = bankCode;
        }

        public Object getAccountNumber() {
            return accountNumber;
        }

        public void setAccountNumber(Object accountNumber) {
            this.accountNumber = accountNumber;
        }

        public Object getApiKey() {
            return apiKey;
        }

        public void setApiKey(Object apiKey) {
            this.apiKey = apiKey;
        }

        public Object getSecretKey() {
            return secretKey;
        }

        public void setSecretKey(Object secretKey) {
            this.secretKey = secretKey;
        }

        public Object getCallBackUrl() {
            return callBackUrl;
        }

        public void setCallBackUrl(Object callBackUrl) {
            this.callBackUrl = callBackUrl;
        }

        public Object getSchemeCategory() {
            return schemeCategory;
        }

        public void setSchemeCategory(Object schemeCategory) {
            this.schemeCategory = schemeCategory;
        }

        public List<Object> getCharges() {
            return charges;
        }

        public void setCharges(List<Object> charges) {
            this.charges = charges;
        }

        public Object getSchemeType() {
            return schemeType;
        }

        public void setSchemeType(Object schemeType) {
            this.schemeType = schemeType;
        }

        public List<Object> getServices() {
            return services;
        }

        public void setServices(List<Object> services) {
            this.services = services;
        }

        public Object getRegStage() {
            return regStage;
        }

        public void setRegStage(Object regStage) {
            this.regStage = regStage;
        }

        public Object getProductType() {
            return productType;
        }

        public void setProductType(Object productType) {
            this.productType = productType;
        }

        public Object getCallbackUrl() {
            return callbackUrl;
        }

        public void setCallbackUrl(Object callbackUrl) {
            this.callbackUrl = callbackUrl;
        }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({ "id", "accountypeID", "walletAccountType" })
    @Data
    @Generated("jsonschema2pojo")
    public static class WalletAccountType {

        @JsonProperty("id")
        private Integer id;

        @JsonProperty("accountypeID")
        private Object accountypeID;

        @JsonProperty("walletAccountType")
        private Object walletAccountType;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Object getAccountypeID() {
            return accountypeID;
        }

        public void setAccountypeID(Object accountypeID) {
            this.accountypeID = accountypeID;
        }

        public Object getWalletAccountType() {
            return walletAccountType;
        }

        public void setWalletAccountType(Object walletAccountType) {
            this.walletAccountType = walletAccountType;
        }
    }
}
