package pl.example.spring.client;


import jakarta.validation.Valid;
import pl.example.spring.api.ClientAddRequest;
import pl.example.spring.api.ClientResponse;

public class ClientMapperAdapter implements ClientMapper {
    @Override
    public ClientResponse toResponse(Client client) {
        return new ClientResponse(client.getId(), client.getName(), client.getMail(), client.getAddress().getCity());
    }

    @Override
    public ClientAdapter toClient(@Valid ClientAddRequest client) {
        return ((ClientAdapter.ClientAdapterBuilder) ClientAdapter.builder()
                .name(client.name())
                .address(
                        Address.builder()
                                .city(client.city())
                                .apartmentNumber(client.apartmentNumber())
                                .buildingNumber(client.buildingNumber())
                                .postalCode(client.postalCode())
                                .street(client.street()).build()
                )
                .mail(client.mail()))
                .build();
    }
}
