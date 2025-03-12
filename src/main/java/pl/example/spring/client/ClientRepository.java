package pl.example.spring.client;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing {@link Client} entities.
 * This interface provides methods for performing CRUD (Create, Read, Update, Delete) operations
 * on client data, as well as pagination and flushing capabilities.
 *
 * <p>This interface is typically implemented by a Spring Data JPA repository, which automatically
 * provides the implementation for these methods at runtime.</p>
 */
public interface ClientRepository {

    /**
     * Retrieves a client by their unique identifier.
     *
     * @param id The unique identifier of the client.
     * @return An {@link Optional} containing the {@link Client} if found, or empty otherwise.
     */
    Optional<Client> findById(long id);

    /**
     * Retrieves a list of all clients.
     *
     * @return A {@link List} of all {@link Client} entities.
     */
    List<Client> findAll();

    /**
     * Retrieves a paginated list of all clients.
     *
     * @param pageable Pagination information (e.g., page number, page size).
     * @return A {@link Page} of {@link Client} entities.
     */
    Page<Client> findAll(Pageable pageable);

    /**
     * Deletes a client from the database.
     *
     * @param client The {@link Client} entity to delete.
     */
    void delete(Client client);

    /**
     * Saves a client to the database.
     *
     * @param client The {@link Client} entity to save.
     * @return The saved {@link Client} entity.
     */
    Client save(Client client);

    /**
     * Flushes all pending changes to the database.
     * This method ensures that any changes made to the entities are immediately written to the database.
     */
    void flush();

    /**
     * Saves a client to the database and flushes the changes immediately.
     *
     * @param client The {@link Client} entity to save.
     * @return The saved {@link Client} entity.
     */
    Client saveAndFlush(Client client);
}
