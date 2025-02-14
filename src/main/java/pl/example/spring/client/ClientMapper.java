package pl.example.spring.client;

import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pl.example.spring.client.api.ClientAddRequest;
import pl.example.spring.client.api.ClientResponse;

@Mapper(componentModel = "spring")
interface ClientMapper {
    @Mapping(target = "city", source = "address.city")
    ClientResponse toResponse(Client client);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "address", source = "client"),
    })
    Client toClient(@Valid ClientAddRequest client);
}
