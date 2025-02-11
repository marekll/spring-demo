package pl.example.spring.client.api.exceptions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Base class for all API errors, errors generally caused by an incorrect request.
 * <p>
 * By default, API error responses with HTTP status 400 BAD REQUEST. To change
 * this behavior, annotate inheriting class with [ResponseStatus]
 */
@NoArgsConstructor
@AllArgsConstructor
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
@JsonIgnoreProperties({ "cause", "stackTrace", "suppressed", "localizedMessage", "message" })
public class ApiError extends RuntimeException {
    private String message = null;
    private Throwable cause = null;
    private pl.example.spring.client.api.ResponseStatus status = null;

    protected ApiError(String message, pl.example.spring.client.api.ResponseStatus status) {
        this.message = message;
        this.status = status;
    }

    public String getDescription() {
        return message;
    }
}

