package pl.example.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration
@SpringBootApplication
@EnableJpaRepositories(basePackages = "pl.example.spring.security")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
