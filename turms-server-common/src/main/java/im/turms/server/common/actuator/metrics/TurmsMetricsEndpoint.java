package im.turms.server.common.actuator.metrics;

import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * We implement the metrics endpoint for better performance and fine-grained control
 * (the MetricsEndpoint of Spring has a terrible performance)
 *
 * @author James Chen
 * @see org.springframework.boot.actuate.autoconfigure.metrics.MetricsEndpointAutoConfiguration
 * @see org.springframework.boot.actuate.endpoint.web.reactive.AbstractWebFluxEndpointHandlerMapping
 * @see org.springframework.boot.actuate.metrics.MetricsEndpoint
 */
@Endpoint(id = "metrics")
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class TurmsMetricsEndpoint {

    private final MetricsPool pool;

    public TurmsMetricsEndpoint(MeterRegistry registry) {
        pool = new MetricsPool(registry);
    }

    @ReadOperation
    public ListNamesResponse listNames() {
        Set<String> names = pool.collectNames();
        return new ListNamesResponse(names);
    }

    /**
     * @implNote Note that @Nullable must be org.springframework.lang.Nullable
     */
    @ReadOperation
    public MetricResponse metric(@Selector String requiredMetricName,
                                 @Nullable List<String> tags,
                                 @Nullable Boolean returnDescription,
                                 @Nullable Boolean returnAvailableTags) {
        List<Meter> meters = pool.findFirstMatchingMeters(requiredMetricName, tags);
        if (meters.isEmpty()) {
            return null;
        }
        Map<String, Double> samples = pool.getSamples(meters);
        Meter.Id meterId = meters.iterator().next().getId();
        String description = returnDescription != null && returnDescription
                ? meterId.getDescription()
                : null;
        Map<String, Set<String>> availableTags = returnAvailableTags != null && returnAvailableTags
                ? pool.getAvailableTags(meters)
                : null;
        return new MetricResponse(requiredMetricName, description, meterId.getBaseUnit(), samples, availableTags);
    }

}