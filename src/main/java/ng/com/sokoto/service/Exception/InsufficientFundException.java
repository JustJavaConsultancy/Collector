package ng.com.sokoto.service.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class InsufficientFundException extends RuntimeException {

    public InsufficientFundException() {
        super();
    }

    public InsufficientFundException(String message, Throwable cause) {
        super(message, cause);
    }

    public InsufficientFundException(String message) {
        super(message);
    }

    public InsufficientFundException(Throwable cause) {
        super(cause);
    }
}
