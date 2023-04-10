package ng.com.sokoto.web.dto.pouchii;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "message", "code", "token", "walletAccount" })
@Generated("jsonschema2pojo")
public class AuthResponse {

    @JsonProperty("message")
    private String message;

    @JsonProperty("code")
    private String code;

    @JsonProperty("token")
    private String token;

    @JsonProperty("walletAccount")
    private WalletAccount walletAccount;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public WalletAccount getWalletAccount() {
        return walletAccount;
    }

    public void setWalletAccount(WalletAccount walletAccount) {
        this.walletAccount = walletAccount;
    }

    @Override
    public String toString() {
        return (
            "AuthResponse{" +
            "message='" +
            message +
            '\'' +
            ", code='" +
            code +
            '\'' +
            ", token='" +
            token +
            '\'' +
            ", walletAccount=" +
            walletAccount +
            '}'
        );
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder(
        {
            "id",
            "accountNumber",
            "currentBalance",
            "dateOpened",
            "schemeId",
            "schemeName",
            "walletAccountTypeId",
            "accountOwnerId",
            "accountOwnerName",
            "accountOwnerPhoneNumber",
            "accountName",
            "status",
            "actualBalance",
            "walletLimit",
            "trackingRef",
            "nubanAccountNo",
            "accountFullName",
            "totalCustomerBalances",
        }
    )
    @Generated("jsonschema2pojo")
    public static class WalletAccount {

        @JsonProperty("id")
        private Integer id;

        @JsonProperty("accountNumber")
        private String accountNumber;

        @JsonProperty("currentBalance")
        private Double currentBalance;

        @JsonProperty("dateOpened")
        private String dateOpened;

        @JsonProperty("schemeId")
        private Integer schemeId;

        @JsonProperty("schemeName")
        private String schemeName;

        @JsonProperty("walletAccountTypeId")
        private Integer walletAccountTypeId;

        @JsonProperty("accountOwnerId")
        private Integer accountOwnerId;

        @JsonProperty("accountOwnerName")
        private String accountOwnerName;

        @JsonProperty("accountOwnerPhoneNumber")
        private String accountOwnerPhoneNumber;

        @JsonProperty("accountName")
        private String accountName;

        @JsonProperty("status")
        private String status;

        @JsonProperty("actualBalance")
        private Double actualBalance;

        @JsonProperty("walletLimit")
        private String walletLimit;

        @JsonProperty("trackingRef")
        private Object trackingRef;

        @JsonProperty("nubanAccountNo")
        private String nubanAccountNo;

        @JsonProperty("accountFullName")
        private String accountFullName;

        @JsonProperty("totalCustomerBalances")
        private Double totalCustomerBalances;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        public void setAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
        }

        public Double getCurrentBalance() {
            return currentBalance;
        }

        public void setCurrentBalance(Double currentBalance) {
            this.currentBalance = currentBalance;
        }

        public String getDateOpened() {
            return dateOpened;
        }

        public void setDateOpened(String dateOpened) {
            this.dateOpened = dateOpened;
        }

        public Integer getSchemeId() {
            return schemeId;
        }

        public void setSchemeId(Integer schemeId) {
            this.schemeId = schemeId;
        }

        public String getSchemeName() {
            return schemeName;
        }

        public void setSchemeName(String schemeName) {
            this.schemeName = schemeName;
        }

        public Integer getWalletAccountTypeId() {
            return walletAccountTypeId;
        }

        public void setWalletAccountTypeId(Integer walletAccountTypeId) {
            this.walletAccountTypeId = walletAccountTypeId;
        }

        public Integer getAccountOwnerId() {
            return accountOwnerId;
        }

        public void setAccountOwnerId(Integer accountOwnerId) {
            this.accountOwnerId = accountOwnerId;
        }

        public String getAccountOwnerName() {
            return accountOwnerName;
        }

        public void setAccountOwnerName(String accountOwnerName) {
            this.accountOwnerName = accountOwnerName;
        }

        public String getAccountOwnerPhoneNumber() {
            return accountOwnerPhoneNumber;
        }

        public void setAccountOwnerPhoneNumber(String accountOwnerPhoneNumber) {
            this.accountOwnerPhoneNumber = accountOwnerPhoneNumber;
        }

        public String getAccountName() {
            return accountName;
        }

        public void setAccountName(String accountName) {
            this.accountName = accountName;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Double getActualBalance() {
            return actualBalance;
        }

        public void setActualBalance(Double actualBalance) {
            this.actualBalance = actualBalance;
        }

        public String getWalletLimit() {
            return walletLimit;
        }

        public void setWalletLimit(String walletLimit) {
            this.walletLimit = walletLimit;
        }

        public Object getTrackingRef() {
            return trackingRef;
        }

        public void setTrackingRef(Object trackingRef) {
            this.trackingRef = trackingRef;
        }

        public String getNubanAccountNo() {
            return nubanAccountNo;
        }

        public void setNubanAccountNo(String nubanAccountNo) {
            this.nubanAccountNo = nubanAccountNo;
        }

        public String getAccountFullName() {
            return accountFullName;
        }

        public void setAccountFullName(String accountFullName) {
            this.accountFullName = accountFullName;
        }

        public Double getTotalCustomerBalances() {
            return totalCustomerBalances;
        }

        public void setTotalCustomerBalances(Double totalCustomerBalances) {
            this.totalCustomerBalances = totalCustomerBalances;
        }

        @Override
        public String toString() {
            return (
                "WalletAccount{" +
                "id=" +
                id +
                ", accountNumber='" +
                accountNumber +
                '\'' +
                ", currentBalance=" +
                currentBalance +
                ", dateOpened='" +
                dateOpened +
                '\'' +
                ", schemeId=" +
                schemeId +
                ", schemeName='" +
                schemeName +
                '\'' +
                ", walletAccountTypeId=" +
                walletAccountTypeId +
                ", accountOwnerId=" +
                accountOwnerId +
                ", accountOwnerName='" +
                accountOwnerName +
                '\'' +
                ", accountOwnerPhoneNumber='" +
                accountOwnerPhoneNumber +
                '\'' +
                ", accountName='" +
                accountName +
                '\'' +
                ", status='" +
                status +
                '\'' +
                ", actualBalance=" +
                actualBalance +
                ", walletLimit='" +
                walletLimit +
                '\'' +
                ", trackingRef=" +
                trackingRef +
                ", nubanAccountNo='" +
                nubanAccountNo +
                '\'' +
                ", accountFullName='" +
                accountFullName +
                '\'' +
                ", totalCustomerBalances=" +
                totalCustomerBalances +
                '}'
            );
        }
    }
}
