package im.turms.server.common;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.async.AsyncLogger;
import org.springframework.boot.SpringApplication;

import java.util.concurrent.TimeUnit;

import static org.apache.logging.log4j.LogManager.getLogger;

/**
 * @author James Chen
 */
public abstract class BaseTurmsApplication {

    static {
        System.setProperty("log4j2.contextSelector", "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");
    }

    protected static void bootstrap(Class<?> applicationClass, String[] args) {
        try {
            SpringApplication.run(applicationClass, args);
        } catch (Exception e) {
            // Note that org.springframework.boot.SpringApplication.handleRunFailure may not trigger
            // im.turms.service.context.ApplicationContextConfig.handleContextClosedEvent
            // if the context hadn't been initialized.

            Logger log = getLogger(BaseTurmsApplication.class);
            log.error(e);
            // Flush
            AsyncLogger logger = (AsyncLogger) log;
            logger.getContext().stop(1, TimeUnit.MINUTES);

            // Make sure turms can exit if SpringApplication failed to bootstrap
            // (e.g. PortInUseException) because there are may still some non-daemon
            // threads preventing from exiting after the context has been closed
            System.exit(1);
        }
    }

}
