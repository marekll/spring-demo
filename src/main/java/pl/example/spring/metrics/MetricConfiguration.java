package pl.example.spring.metrics;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
public class MetricConfiguration {
    @ConditionalOnMissingBean(MetricService.class)
    @Import({MetricService.class})
    public static class ServiceConfiguration { }
}
