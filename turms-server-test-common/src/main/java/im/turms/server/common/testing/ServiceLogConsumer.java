package im.turms.server.common.testing;

import lombok.extern.slf4j.Slf4j;
import org.testcontainers.containers.output.BaseConsumer;
import org.testcontainers.containers.output.OutputFrame;

/**
 * @author James Chen
 */
@Slf4j
public class ServiceLogConsumer extends BaseConsumer<ServiceLogConsumer> {

    private final String serviceName;

    public ServiceLogConsumer(String serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public void accept(OutputFrame frame) {
        switch (frame.getType()) {
            case STDOUT -> log.info("[" + serviceName + "]: " + frame.getUtf8String());
            case STDERR -> log.error("[" + serviceName + "]: " + frame.getUtf8String());
            case END -> {
            }
        }
    }

}
