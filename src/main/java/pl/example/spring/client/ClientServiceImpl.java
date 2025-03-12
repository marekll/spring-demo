package pl.example.spring.client;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.example.spring.api.ClientAddRequest;
import pl.example.spring.api.ClientResponse;

import java.util.List;
import java.util.NoSuchElementException;

import static java.lang.String.format;

/**
 * Implementation of the {@link ClientService} interface.
 * This class provides methods for managing client data, including retrieval, creation, updating, and deletion.
 * It uses a {@link ClientRepository} for database operations and a {@link ClientMapper} for mapping between DTOs and entities.
 */
@Slf4j
@AllArgsConstructor
@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    /**
     * Retrieves a paginated list of abbreviated client data.
     *
     * @param pageable Pagination information (e.g., page number, page size).
     * @return A {@link Page} of {@link ClientResponse} objects containing abbreviated client data.
     * @throws IllegalArgumentException if the provided {@link Pageable} is invalid.
     */
    @Override
    public @NotNull Page<@NotNull ClientResponse> getAllAbbreviatedData(Pageable pageable) {
        return clientRepository.findAll(pageable).map(clientMapper::toResponse);
    }

    /**
     * Retrieves a client by their unique identifier.
     *
     * @param id The unique identifier of the client.
     * @return The {@link Client} object corresponding to the provided ID.
     * @throws NoSuchElementException if no client is found with the provided ID.
     */
    @Override
    public @NotNull Client get(long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(format("Cannot find client with id: %d", id)));
    }

    /**
     * Retrieves a paginated list of all clients.
     *
     * @param pageable Pagination information (e.g., page number, page size).
     * @return A {@link Page} of {@link Client} objects.
     * @throws IllegalArgumentException if the provided {@link Pageable} is invalid.
     */
    @Override
    public @NotNull Page<@NotNull Client> getPage(Pageable pageable) {
        return clientRepository.findAll(pageable);
    }

    /**
     * Retrieves a list of all clients.
     *
     * @return A {@link List} of all {@link Client} objects.
     */
    @Override
    public @NotNull List<@NotNull Client> getAll() {
        return clientRepository.findAll();
    }

    /**
     * Deletes a client by their unique identifier.
     *
     * @param id The unique identifier of the client to delete.
     * @throws NoSuchElementException if no client is found with the provided ID.
     */
    @Override
    public void delete(long id) {
        clientRepository.delete(clientRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(format("Cannot find client with id: %d", id))));
        clientRepository.flush();
    }

    /**
     * Creates a new client using the provided request data.
     *
     * @param client The {@link ClientAddRequest} object containing the client data to create.
     * @throws IllegalArgumentException if the provided request data is invalid.
     * @throws ConstraintViolationException if the provided data violates validation constraints.
     */
    @Override
    public void create(@NotNull ClientAddRequest client) {
        clientRepository.saveAndFlush(clientMapper.toClient(client));
    }

    /**
     * Updates an existing client's data using the provided request data.
     *
     * @param request The {@link ClientAddRequest} object containing the updated client data.
     * @param id The unique identifier of the client to update.
     * @throws NoSuchElementException if no client is found with the provided ID.
     * @throws IllegalArgumentException if the provided request data is invalid.
     * @throws ConstraintViolationException if the provided data violates validation constraints.
     */
    @Override
    public void edit(@Valid @NotNull ClientAddRequest request, long id) {
        Client client = get(id);
        editClient(request, client);
    }

    /**
     * Updates the name of an existing client.
     *
     * @param name The new name to assign to the client.
     * @param id The unique identifier of the client to update.
     * @throws NoSuchElementException if no client is found with the provided ID.
     * @throws IllegalArgumentException if the provided name is invalid.
     */
    @Override
    public void updateName(@NotBlank String name, long id) {
        Client client = get(id);
        client.setName(name);
        clientRepository.save(client);
    }

    /**
     * Helper method to edit client data and save changes to the database.
     *
     * @param request The {@link ClientAddRequest} object containing the updated client data.
     * @param client The {@link Client} object to update.
     * @throws IllegalArgumentException if the provided request data is invalid.
     * @throws ConstraintViolationException if the provided data violates validation constraints.
     */
    private void editClient(
            @Valid @NotNull ClientAddRequest request,
            @NotNull Client client
    ) {
        client.setName(request.name());
        client.setMail(request.mail());
        var address = client.getAddress();
        address.setCity(request.city());
        address.setStreet(request.street());
        address.setApartmentNumber(request.apartmentNumber());
        address.setPostalCode(request.postalCode());
        address.setBuildingNumber(request.buildingNumber());
        clientRepository.saveAndFlush(client);
    }
}
