package pl.example.spring.api;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a request object for adding a new client.
 * This record is used to capture client data from the client (e.g., via an API request) and
 * includes validation constraints to ensure the data is valid before processing.
 *
 * <p>The record uses Lombok's `@Builder` annotation to provide a builder pattern for creating instances.</p>
 *
 * <p>Validation constraints:</p>
 * <ul>
 *     <li>`name`: Cannot be blank.</li>
 *     <li>`mail`: Must be a valid email address and cannot be null.</li>
 *     <li>`street`: Optional (can be null).</li>
 *     <li>`buildingNumber`: Required (must be a valid integer).</li>
 *     <li>`apartmentNumber`: Optional (can be null).</li>
 *     <li>`city`: Cannot be blank.</li>
 *     <li>`postalCode`: Must match the format "dd-ddd" (e.g., "00-001") and cannot be blank.</li>
 * </ul>
 *
 * <p>Example usage:</p>
 * <pre>
 * {
 *   "name": "John Doe",
 *   "mail": "john.doe@example.com",
 *   "street": "Main Street",
 *   "buildingNumber": 10,
 *   "apartmentNumber": 5,
 *   "city": "Warsaw",
 *   "postalCode": "00-001"
 * }
 * </pre>
 */
@Builder
public record ClientAddRequest(
        /**
         * The name of the client.
         * This field is required and cannot be blank.
         */
        @NotBlank(message = "Name cannot be blank")
        String name,

        /**
         * The email address of the client.
         * This field is required, must be a valid email address, and cannot be null.
         */
        @NotNull
        @Email(message = "Invalid email address")
        String mail,

        /**
         * The street name of the client's address.
         * This field is optional and can be null.
         */
        @Nullable
        String street,

        /**
         * The building number of the client's address.
         * This field is required and must be a valid integer.
         */
        int buildingNumber,

        /**
         * The apartment number of the client's address.
         * This field is optional and can be null.
         */
        @Nullable
        Integer apartmentNumber,

        /**
         * The city of the client's address.
         * This field is required and cannot be blank.
         */
        @NotBlank(message = "City cannot be blank")
        String city,

        /**
         * The postal code of the client's address.
         * This field is required, must match the format "dd-ddd" (e.g., "00-001"), and cannot be blank.
         */
        @Pattern(
                regexp = "\\d{2}-\\d{3}",
                message = "Invalid postal code. Postal code must be in format \"dd-ddd\", where \"d\" is a digit"
        )
        @NotBlank(message = "Postal code cannot be blank")
        String postalCode
) {
}