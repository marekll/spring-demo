package pl.example.spring.client;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class ClientRepositoryAdapter implements ClientRepository {
    private final Map<Long, Client> clients = new ConcurrentHashMap<>();

    @Override
    public Optional<Client> findById(long id) {
        var client = clients.get(id);
        if (client == null) {
            return Optional.empty();
        }
        return Optional.of(client);
    }

    @Override
    public List<Client> findAll() {
        return clients.values().stream().toList();
    }

    @Override
    public Page<Client> findAll(Pageable pageable) {
        return new PageImpl<>(clients.values().stream().toList(), pageable, 1);
    }

    @Override
    public void delete(Client client) {
        clients.remove(client.getId());
    }

    @Override
    public Client save(Client client) {
        var values = clients.values();
        if (values.stream()
                .filter(it -> client.getId() != it.getId())
                .anyMatch( it -> it.getName().equals(client.getName()))) {
            throw new DataIntegrityViolationException("");
        }
        return clients.put(client.getId(), client);
    }

    @Override
    public void flush() {

    }

    @Override
    public Client saveAndFlush(Client client) {
        return save(client);
    }

    public void deleteAll() {
        clients.clear();
    }
}
