package pl.example.spring;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pl.example.spring.client.Client;
import pl.example.spring.client.ClientService;
import pl.example.spring.api.ClientAddRequest;
import pl.example.spring.api.ClientResponse;
import pl.example.spring.metrics.MetricService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * REST controller for managing client-related operations.
 * This controller provides endpoints for retrieving, creating, updating, and deleting client data.
 * It uses the {@link ClientService} to perform business logic and the {@link MetricService} to track metrics.
 *
 * <p>All endpoints are transactional, ensuring data consistency during updates and deletions.</p>
 */
@AllArgsConstructor
@RestController
@RequestMapping("/api/client")
@Tag(name = "Client API", description = "API for managing clients")
public class ClientController {
    private final ClientService clientService;
    private final MetricService metricService;

    /**
     * Retrieves a list of all clients.
     *
     * @return A {@link List} of all {@link Client} entities.
     */
    @Operation(summary = "Get all clients", description = "Retrieves a list of all clients in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of clients"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(value = "/all", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Client> getClients() {
        return clientService.getAll();
    }

    /**
     * Retrieves a paginated list of abbreviated client data.
     *
     * @param pageable Pagination information (e.g., page number, page size).
     * @return A {@link Page} of {@link ClientResponse} objects containing abbreviated client data.
     */
    @Operation(
            summary = "Get paginated client data",
            description = "Retrieves a paginated list of clients with abbreviated data."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved paginated client data"),
            @ApiResponse(responseCode = "400", description = "Invalid pagination parameters"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(value = "/page", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Page<ClientResponse> getClients(
            @Parameter(description = "Pagination information (page number, page size, sorting)")
            Pageable pageable
    ) {
        metricService.incrementCount();
        return clientService.getAllAbbreviatedData(pageable);
    }

    /**
     * Retrieves full details of a single client by their unique identifier.
     *
     * @param id The unique identifier of the client.
     * @return The {@link Client} entity corresponding to the provided ID.
     */
    @Operation(
            summary = "Get client by ID",
            description = "Retrieves full details of a single client by their unique identifier."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved client details"),
            @ApiResponse(responseCode = "404", description = "Client not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Client getClient(
            @Parameter(description = "The unique identifier of the client", required = true)
            @PathVariable long id
    ) {
        return clientService.get(id);
    }

    /**
     * Deletes a client by their unique identifier.
     *
     * @param id The unique identifier of the client to delete.
     */
    @Operation(summary = "Delete client by ID", description = "Deletes a client with the specified unique identifier.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Client not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public void deleteClient(
            @Parameter(description = "The unique identifier of the client", required = true)
            @PathVariable long id
    ) {
        clientService.delete(id);
    }

    /**
     * Creates a new client using the provided details.
     *
     * @param client The {@link ClientAddRequest} object containing the client data to create.
     */
    @Operation(summary = "Create a new client", description = "Creates a new client using the provided details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Client successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid client data provided"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public void addClient(
            @Parameter(description = "Client data to create", required = true)
            @RequestBody ClientAddRequest client
    ) {
        clientService.create(client);
    }

    /**
     * Updates an existing client with the provided details.
     *
     * @param id The unique identifier of the client to update.
     * @param client The {@link ClientAddRequest} object containing the updated client data.
     */
    @Operation(summary = "Update client by ID", description = "Updates an existing client with the provided details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Client successfully updated"),
            @ApiResponse(responseCode = "400", description = "Invalid client data provided"),
            @ApiResponse(responseCode = "404", description = "Client not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public void updateClient(
            @Parameter(description = "The unique identifier of the client", required = true)
            @PathVariable long id,
            @Parameter(description = "Updated client data", required = true)
            @NotNull @RequestBody ClientAddRequest client
    ) {
        clientService.edit(client, id);
    }

    /**
     * Updates the name of an existing client.
     *
     * @param id The unique identifier of the client to update.
     * @param name The new name to assign to the client.
     */
    @Operation(summary = "Update client name by ID", description = "Updates the name of an existing client.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client name successfully updated"),
            @ApiResponse(responseCode = "400", description = "Invalid name provided"),
            @ApiResponse(responseCode = "404", description = "Client not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PatchMapping("/updateName/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public void updateClientName(
            @Parameter(description = "The unique identifier of the client", required = true)
            @PathVariable long id,
            @Parameter(description = "The new name for the client", required = true)
            @NotBlank @RequestBody String name
    ) {
        clientService.updateName(name, id);
    }
}