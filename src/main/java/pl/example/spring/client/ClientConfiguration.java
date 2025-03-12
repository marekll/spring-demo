package pl.example.spring.client;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Configuration class for setting up client-related components in the application.
 * This class enables JPA repositories and conditionally imports service implementations
 * if no existing `ClientService` bean is found.
 *
 * <p>Key configurations:</p>
 * <ul>
 *     <li>Enables JPA repositories for the {@link ClientRepositorySql} class.</li>
 *     <li>Conditionally imports the {@link ClientServiceImpl} class if no `ClientService` bean is present.</li>
 * </ul>
 */
@Configuration
@EnableJpaRepositories(basePackageClasses = { ClientRepositorySql.class })
public class ClientConfiguration {

    /**
     * Nested configuration class for setting up the client service.
     * This class is conditionally activated only if no existing `ClientService` bean is found in the application context.
     * It imports the {@link ClientServiceImpl} class to provide a default implementation of the `ClientService` interface.
     */
    @ConditionalOnMissingBean(ClientService.class)
    @Import({ClientServiceImpl.class})
    public static class ServiceConfiguration { }
}
