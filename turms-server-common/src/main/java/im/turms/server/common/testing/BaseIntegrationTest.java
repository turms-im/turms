package im.turms.server.common.testing;

import java.time.Duration;

/**
 * @author James Chen
 */
public abstract class BaseIntegrationTest {

    protected static final Duration DEFAULT_IO_TIMEOUT = Duration.ofSeconds(15);

    protected static final TestingEnvContainer ENV = new TestingEnvContainer();

    static {
        ENV.start();
    }

}