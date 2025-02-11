package pl.example.spring.client.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static pl.example.spring.client.api.ResponseStatus.CLIENT_NOT_FOUND;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class NoSuchClientException extends ApiError {
    public NoSuchClientException(String message) {
        super(message, CLIENT_NOT_FOUND);
    }
}
