package pl.example.spring.client;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.jetbrains.annotations.Nullable;


/**
 * Represents a Polish address.
 * This class is used to store address-related information, such as street, building number, apartment number, city, and
 * postal code. It is designed to be embedded within other entities (e.g., a `Client` entity) using JPA's `@Embeddable`
 * annotation.
 *
 * <p>Fields in this class are validated using constraints such as `@NotBlank` and `@Nullable` to ensure data
 * integrity.</p>
 */
@Getter
@Setter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {

    /**
     * The name of the street.
     * This field is optional and can be null.
     */
    @Column
    @Nullable
    private String street;

    /**
     * The building number.
     * This field is required and must be a valid integer.
     */
    @Column
    private int buildingNumber;

    /**
     * The apartment number.
     * This field is optional and can be null.
     */
    @Column
    @Nullable
    private Integer apartmentNumber;

    /**
     * The name of the city.
     * This field is required and must not be blank.
     */
    @Column(nullable = false)
    @NotBlank
    private String city;

    /**
     * The postal code of the address.
     * This field is required and must not be blank.
     * The format should follow Polish postal code conventions (e.g., "00-001").
     */
    @Column(nullable = false)
    @NotBlank
    private String postalCode;
}