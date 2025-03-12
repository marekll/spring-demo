package pl.example.spring.client;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.concurrent.atomic.AtomicLong;

public class ClientAdapter extends Client {
    private final static AtomicLong nextId = new AtomicLong(1L);

    private long id = nextId.getAndIncrement();

    public ClientAdapter(@Email @NotBlank String mail, @NotBlank String name, @NotNull Address address) {
        this.setMail(mail);
        this.setName(name);
        this.setAddress(address);
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public static ClientAdapterBuilder builder() {
        return new ClientAdapterBuilder();
    }

    public static class ClientAdapterBuilder extends Client.ClientBuilder {
        public ClientAdapter build() {
            var client = super.build();
            return new ClientAdapter(client.getMail(), client.getName(), client.getAddress());
        }
    }
}
