package pl.example.spring.client.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static pl.example.spring.client.api.ResponseStatus.CREATE_CLIENT_ERROR;

@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
public class CreateClientException extends ApiError {
    public CreateClientException(String message) {
        super(message, CREATE_CLIENT_ERROR);
    }
}
