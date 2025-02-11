package pl.example.spring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.example.spring.client.ClientConfiguration;
import pl.example.spring.client.ClientRepository;
import pl.example.spring.client.ClientService;
import pl.example.spring.client.api.ClientAddRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ComponentScan(basePackages = "pl.example.spring")
@ContextConfiguration(classes = {ClientConfiguration.class})
@WebAppConfiguration
@ActiveProfiles("test")
@AutoConfigureMockMvc
@EntityScan("pl.example.spring")
public class ClientControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientRepository clientRepository;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
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
    void testSuccessfulGetAllClients() {
        MvcResult mvcResult = null;
        try {
            mvcResult = this.mockMvc
                .perform(get("/api/client/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        } catch (Exception e) {
            assertNull(e);
        }
        assertEquals("application/json", mvcResult.getResponse().getContentType());
    }
}
