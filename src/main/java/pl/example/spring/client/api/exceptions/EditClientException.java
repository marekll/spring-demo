package pl.example.spring.client.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static pl.example.spring.client.api.ResponseStatus.EDIT_CLIENT_ERROR;

@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
public class EditClientException extends ApiError {
    public EditClientException(String message) {
        super(message, EDIT_CLIENT_ERROR);
    }
}
