package ng.com.sokoto.service.dto;

import javax.validation.constraints.NotBlank;

/**
 * A DTO representing a password change required data - current and new password.
 */
public class PasswordChangeDTO {

    @NotBlank(message = "Current Password is Required")
    private String currentPassword;

    @NotBlank(message = "New Password is Required")
    private String newPassword;

    private String phoneNumber;
    private String pin;

    public PasswordChangeDTO() {
        // Empty constructor needed for Jackson.
    }

    public PasswordChangeDTO(String currentPassword, String newPassword) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }

    public PasswordChangeDTO(String currentPassword, String newPassword, String phoneNumber, String pin) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
        this.phoneNumber = phoneNumber;
        this.pin = pin;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}
