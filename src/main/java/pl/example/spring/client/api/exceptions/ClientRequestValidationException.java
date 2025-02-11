package pl.example.spring.client.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static pl.example.spring.client.api.ResponseStatus.CLIENT_REQUEST_VALIDATION_ERROR;

@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
public class ClientRequestValidationException extends ApiError {
    public ClientRequestValidationException(String message) {
        super(message, CLIENT_REQUEST_VALIDATION_ERROR);
    }
}
