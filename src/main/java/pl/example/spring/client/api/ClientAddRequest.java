package pl.example.spring.client.api;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Builder
public record ClientAddRequest(
        @NotBlank(message = "Name cannot be blank")
        String name,

        @NotNull
        @Email(message = "Invalid email address")
        String mail,

        @Nullable
        String street,

        int buildingNumber,

        @Nullable
        Integer apartmentNumber,

        @NotBlank(message = "City cannot be blank")
        String city,

        @Pattern(
                regexp = "\\d{2}-\\d{3}",
                message = "Invalid postal code. Postal code must be in format \"dd-ddd\", where \"d\" is a digit"
        )
        @NotBlank(message = "Postal code cannot be blank")
        String postalCode
) {
}
