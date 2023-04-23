package ng.com.sokoto.web.dto.pouchii;

import javax.validation.constraints.NotBlank;

public class ChangePinDTO {

    @NotBlank(message = "Current Pin is Required")
    private String currentPin;

    @NotBlank(message = "New Pin is Required")
    private String newPin;

    public String getCurrentPin() {
        return currentPin;
    }

    public void setCurrentPin(String currentPin) {
        this.currentPin = currentPin;
    }

    public String getNewPin() {
        return newPin;
    }

    public void setNewPin(String newPin) {
        this.newPin = newPin;
    }
}
