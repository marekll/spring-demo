package pl.example.spring.client;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackageClasses = ClientRepository.class)
public class ClientConfiguration {
    @ConditionalOnMissingBean(ClientService.class)
    @Import({ClientServiceImpl.class})
    public static class ServiceConfiguration { }
}
