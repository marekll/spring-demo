package pl.example.spring.client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.example.spring.client.api.ClientAddRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
class ClientTest {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientService clientService;

    /// / private DataSource dataSource;

    @BeforeEach
    public void setup() {
//        dataSource = TestUtils.getDataSource();
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
