package pl.example.spring.client;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.example.spring.api.ClientAddRequest;
import pl.example.spring.api.ClientResponse;

import java.util.List;

/**
 * Service interface for managing client-related operations.
 * This interface defines methods for retrieving, creating, updating, and deleting client data.
 */
@Service
public interface ClientService {

    /**
     * Retrieves a paginated list of abbreviated client data.
     *
     * @param pageable Pagination information (e.g., page number, page size).
     * @return A {@link Page} containing {@link ClientResponse} objects with abbreviated client data.
     * @throws IllegalArgumentException if the provided {@link Pageable} is invalid.
     */
    @NotNull
    Page<ClientResponse> getAllAbbreviatedData(Pageable pageable);

    /**
     * Retrieves a client by their unique identifier.
     *
     * @param id The unique identifier of the client.
     * @return The {@link Client} object corresponding to the provided ID.
     * @throws IllegalArgumentException if the provided ID is invalid or the client is not found.
     */
    @NotNull
    Client get(long id);

    /**
     * Retrieves a paginated list of all clients.
     *
     * @param pageable Pagination information (e.g., page number, page size).
     * @return A {@link Page} containing {@link Client} objects.
     * @throws IllegalArgumentException if the provided {@link Pageable} is invalid.
     */
    @NotNull
    Page<Client> getPage(Pageable pageable);

    /**
     * Retrieves a list of all clients.
     *
     * @return A {@link List} of all {@link Client} objects.
     */
    @NotNull
    List<Client> getAll();

    /**
     * Deletes a client by their unique identifier.
     *
     * @param id The unique identifier of the client to delete.
     * @throws IllegalArgumentException if the provided ID is invalid or the client is not found.
     */
    void delete(long id);

    /**
     * Creates a new client using the provided request data.
     *
     * @param client The {@link ClientAddRequest} object containing the client data to create.
     * @throws IllegalArgumentException if the provided request data is invalid.
     * @throws ConstraintViolationException if the provided data violates validation constraints.
     */
    void create(@Valid @NotNull ClientAddRequest client);

    /**
     * Updates an existing client's data using the provided request data.
     *
     * @param client The {@link ClientAddRequest} object containing the updated client data.
     * @param id The unique identifier of the client to update.
     * @throws IllegalArgumentException if the provided ID or request data is invalid.
     * @throws ConstraintViolationException if the provided data violates validation constraints.
     */
    void edit(@Valid @NotNull ClientAddRequest client, long id);

    /**
     * Updates the name of an existing client.
     *
     * @param name The new name to assign to the client.
     * @param id The unique identifier of the client to update.
     * @throws IllegalArgumentException if the provided ID or name is invalid.
     * @throws ConstraintViolationException if the provided name violates validation constraints.
     */
    void updateName(@NotBlank String name, long id);
}
