package uk.co.huntersix.spring.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException() {
        super();
    }
    public PersonNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    public PersonNotFoundException(String message) {
        super(message);
    }
    public PersonNotFoundException(Throwable cause) {
        super(cause);
    }
}