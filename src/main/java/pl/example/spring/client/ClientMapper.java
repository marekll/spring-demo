package pl.example.spring.client;

import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pl.example.spring.api.ClientAddRequest;
import pl.example.spring.api.ClientResponse;

/**
 * Mapper interface for converting between {@link Client} entities and their corresponding DTOs.
 * This interface uses MapStruct to automatically generate mapping implementations at compile time.
 * The `componentModel = "spring"` attribute ensures that the generated mapper is a Spring bean.
 *
 * <p>Key mappings:</p>
 * <ul>
 *     <li>Converts a {@link Client} entity to a {@link ClientResponse} DTO.</li>
 *     <li>Converts a {@link ClientAddRequest} DTO to a {@link Client} entity.</li>
 * </ul>
 */
@Mapper(componentModel = "spring")
public interface ClientMapper {

    /**
     * Converts a {@link Client} entity to a {@link ClientResponse} DTO.
     *
     * @param client The {@link Client} entity to convert.
     * @return The corresponding {@link ClientResponse} DTO.
     */
    @Mapping(target = "city", source = "address.city")
    ClientResponse toResponse(Client client);

    /**
     * Converts a {@link ClientAddRequest} DTO to a {@link Client} entity.
     * The `id` field is ignored during mapping, and the `address` field is populated from the request.
     *
     * @param client The {@link ClientAddRequest} DTO to convert.
     * @return The corresponding {@link Client} entity.
     * @throws IllegalArgumentException if the provided request data is invalid.
     */
    @Mappings({
            @Mapping(target = "id", ignore = true), // Ignore the ID field during mapping
            @Mapping(target = "address", source = "client"), // Map address fields from the request
    })
    Client toClient(@Valid ClientAddRequest client);
}
