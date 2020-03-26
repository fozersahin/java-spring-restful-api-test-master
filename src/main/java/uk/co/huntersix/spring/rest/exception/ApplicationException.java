package uk.co.huntersix.spring.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ApplicationException extends RuntimeException {
    public ApplicationException() {
        super();
    }
    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
    public ApplicationException(String message) {
        super(message);
    }
    public ApplicationException(Throwable cause) {
        super(cause);
    }
}