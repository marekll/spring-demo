package pl.example.spring.client.api;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ClientResponse(
        long id,

        @Column(nullable = false)
        @NotBlank
        String name,

        @Column(nullable = false)
        @NotBlank
        @Email
        String mail,

        @Column(name = "city", nullable = false)
        @NotBlank
        String city) {
}
