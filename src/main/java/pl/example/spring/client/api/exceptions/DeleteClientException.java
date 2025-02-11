package pl.example.spring.client.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static pl.example.spring.client.api.ResponseStatus.DELETE_CLIENT_ERROR;

@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
public class DeleteClientException extends ApiError {
    public DeleteClientException(String message) {
        super(message, DELETE_CLIENT_ERROR);
    }
}
