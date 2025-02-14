package pl.example.spring.client;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.example.spring.client.api.ClientAddRequest;
import pl.example.spring.client.api.ClientResponse;

import java.util.List;
import java.util.NoSuchElementException;

import static java.lang.String.format;

@Slf4j
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    public @NotNull Page<@NotNull ClientResponse> getAllAbbreviatedData(Pageable pageable) {
        return clientRepository.findAll(pageable).map(clientMapper::toResponse);
    }

    @Override
    public @NotNull Client get(long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(format("Cannot find client with id: %d", id)));
    }

    @Override
    public @NotNull Page<@NotNull Client> getPage(Pageable pageable) {
        return clientRepository.findAll(pageable);
    }

    @Override
    public @NotNull List<@NotNull Client> getAll() {
        return clientRepository.findAll();
    }

    @Override
    public void delete(long id) {
        clientRepository.delete(clientRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(format("Cannot find client with id: %d", id))));
        clientRepository.flush();
    }

    @Override
    public void create(@NotNull ClientAddRequest client) {
        clientRepository.saveAndFlush(clientMapper.toClient(client));
    }

    @Override
    public void edit(@Valid @NotNull ClientAddRequest request, long id) {
        Client client = get(id);
        editClient(request, client);
    }

    @Override
    public void updateName(@NotBlank String name, long id) {
        Client client = get(id);
        client.setName(name);
        clientRepository.save(client);
    }

    /**
     * Edits client data and saves changes to database
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
