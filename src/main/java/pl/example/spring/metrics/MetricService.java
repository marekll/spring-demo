package pl.example.spring.metrics;


import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

public class MetricService {
    private final Counter counter;

    public MetricService(MeterRegistry registry) {
        counter = Counter.builder("get counter")
                .description("Number of visits to the page")
                .register(registry);
    }

    public void incrementCount() {
        counter.increment();
    }

    public double getResult() {
        return counter.count();
    }
}
