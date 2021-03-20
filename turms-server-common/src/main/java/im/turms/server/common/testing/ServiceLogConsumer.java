package im.turms.server.common.testing;

import lombok.extern.log4j.Log4j2;
import org.testcontainers.containers.output.BaseConsumer;
import org.testcontainers.containers.output.OutputFrame;

/**
 * @author James Chen
 */
@Log4j2
public class ServiceLogConsumer extends BaseConsumer<ServiceLogConsumer> {

    private final String serviceName;

    public ServiceLogConsumer(String serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public void accept(OutputFrame frame) {
        switch (frame.getType()) {
            case STDOUT:
                log.info("[" + serviceName + "]: " + frame.getUtf8String());
                break;
            case STDERR:
                log.error("[" + serviceName + "]: " + frame.getUtf8String());
                break;
            case END:
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + frame.getType());
        }
    }

}
