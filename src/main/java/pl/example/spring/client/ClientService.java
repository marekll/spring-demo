package pl.example.spring.client;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.example.spring.client.api.ClientAddRequest;
import pl.example.spring.client.api.ClientResponse;

import java.util.List;

@Service
public interface ClientService {
    @NotNull
    Page<ClientResponse> getAllAbbreviatedData(Pageable pageable);

    @NotNull
    Client get(long id);

    @NotNull
    Page<Client> getPage(Pageable pageable);

    @NotNull
    List<Client> getAll();

    void delete(long id);

    void create(@Valid @NotNull ClientAddRequest client);

    void edit(@Valid @NotNull ClientAddRequest client, long id);

    void updateName(@NotBlank String name, long id);
}
