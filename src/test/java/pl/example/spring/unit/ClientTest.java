package pl.example.spring.unit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataIntegrityViolationException;
import pl.example.spring.api.ClientAddRequest;
import pl.example.spring.client.ClientMapperAdapter;
import pl.example.spring.client.ClientRepositoryAdapter;
import pl.example.spring.client.ClientService;
import pl.example.spring.client.ClientServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class ClientTest {
    private final ClientRepositoryAdapter clientRepository = new ClientRepositoryAdapter();

    private ClientService clientService;

    @BeforeEach
    public void setup() {
        clientService = new ClientServiceImpl(clientRepository, new ClientMapperAdapter());
        clientRepository.deleteAll();

        clientService.create(ClientAddRequest.builder()
                .name("abc")
                .mail("bbc@abc.com")
                .street("ulica")
                .buildingNumber(1)
                .apartmentNumber(null)
                .city("Warszawa")
                .postalCode("01-222")
                .build()
        );
    }

    @Test
    public void createTwoValidClients() {
        var clients = clientService.getAll();
        var client = clients.getFirst();
        var initialSize = clients.size();

        clientService.create(
                ClientAddRequest.builder()
                        .name(client.getName() + 2)
                        .mail("bbc@abc.com")
                        .street("ulica")
                        .buildingNumber(1)
                        .apartmentNumber(null)
                        .city("Warszawa")
                        .postalCode("01-000")
                        .build()
        );

        clients = clientRepository.findAll();
        assertEquals(initialSize + 1, clients.size());
    }

    @Test
    public void createTwoClientsWithSameName() {
        assertThrows(DataIntegrityViolationException.class,
                () -> clientService.create(
                        ClientAddRequest.builder()
                                .name("abc")
                                .mail("bbc@abc.com")
                                .street("ulica")
                                .buildingNumber(1)
                                .apartmentNumber(null)
                                .city("Warszawa")
                                .postalCode("01-000")
                                .build()
                ));
        assertEquals(1, clientService.getAll().size());
    }

    @Test
    public void deleteExistingClient() {
        var clients = clientService.getAll();
        var client = clients.getFirst();
        var initialSize = clients.size();

        clientService.delete(client.getId());

        clients = clientService.getAll();
        assertEquals(initialSize - 1, clients.size());
    }

    @Test
    public void updateNameWithValidName() {
        var clients = clientService.getAll();
        var client = clients.getFirst();
        var initialSize = clients.size();
        var name = client.getName() + " - new valid name2";

        clientService.updateName(name, client.getId());

        clients = clientService.getAll();
        client = clientService.get(client.getId());
        assertEquals(initialSize, clients.size());
        assertEquals(client.getName(), name);
    }
}
