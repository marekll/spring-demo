package pl.example.spring.api;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * Represents a response object for client data.
 * This record is used to return client information to the client (e.g., via an API response).
 * It includes fields for the client's ID, name, email, and city, along with validation constraints
 * to ensure the data is valid.
 *
 * <p>Validation constraints:</p>
 * <ul>
 *     <li>`id`: The unique identifier of the client.</li>
 *     <li>`name`: Cannot be blank.</li>
 *     <li>`mail`: Must be a valid email address and cannot be blank.</li>
 *     <li>`city`: Cannot be blank.</li>
 * </ul>
 */
public record ClientResponse(
        /**
         * The unique identifier of the client.
         */
        long id,

        /**
         * The name of the client.
         * This field is required and cannot be blank.
         */
        @Column(nullable = false)
        @NotBlank
        String name,

        /**
         * The email address of the client.
         * This field is required, must be a valid email address, and cannot be blank.
         */
        @Column(nullable = false)
        @NotBlank
        @Email
        String mail,

        /**
         * The city of the client's address.
         * This field is required and cannot be blank.
         */
        @Column(name = "city", nullable = false)
        @NotBlank
        String city) {
}
