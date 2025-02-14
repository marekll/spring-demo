package pl.example.spring;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.example.spring.metrics.MetricService;

@AllArgsConstructor
@RestController
public class VisitCounterController {

    private final MetricService metricService;

    @GetMapping("/metrics/page")
    public double getVisitCount() {
        return metricService.getResult();
    }
}
