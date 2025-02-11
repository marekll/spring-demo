package pl.example.spring.client;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.jetbrains.annotations.Nullable;

/** Class representing polish address */

@Getter
@Setter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {
    @Column
    @Nullable
    private String street;

    @Column
    private int buildingNumber;

    @Column
    @Nullable
    private Integer apartmentNumber;

    @Column(nullable = false)
    @NotBlank
    private String city;

    @Column(nullable = false)
    @NotBlank
    private String postalCode;
}