package pl.example.spring.client;


import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.NotBlank;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pl.example.spring.client.api.ClientAddRequest;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@AllArgsConstructor
@RestController
@RequestMapping("/api/client")
public class ClientController {
    private final ClientService clientService;

    @Operation(summary = "Returns a list of all clients")
    @GetMapping(value = "/all", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Client> getClients() {
        return clientService.getAll();
    }

    @Operation(summary = "Returns a page with abbreviated client data")
    @GetMapping(value = "/page", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Page<Client> getClients(Pageable pageable) {
        return clientService.getPage(pageable);
    }

    @Operation(summary = "Returns full details of a single client")
    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Client getClient(
            @PathVariable long id
    ) {
        return clientService.get(id);
    }

    @Operation(summary = "Deletes client with given id")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public void deleteClient(
            @PathVariable long id
    ) {
        clientService.delete(id);
    }

    @Operation(summary = "Creates a new client using provided details")
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public void addClient(
            @RequestParam("client") ClientAddRequest client
    ) {
        clientService.create(client);
    }

    @Operation(summary = "Updates client with given id")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public void updateClient(
            @PathVariable long id,
            @NotNull @RequestParam("client") ClientAddRequest client
    ) {
        clientService.edit(client, id);
    }

    @Operation(summary = "Updates the name of the client with given id")
    @PatchMapping("/updateName/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public void updateClientName(
            @PathVariable long id,
            @NotBlank @PathParam("name") String name
    ) {
        clientService.updateName(name, id);
    }
}
