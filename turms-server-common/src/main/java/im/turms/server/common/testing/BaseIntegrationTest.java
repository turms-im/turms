package im.turms.server.common.testing;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
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

    protected String readText(String path) {
        InputStream resource = BaseIntegrationTest.class.getClassLoader().getResourceAsStream(path);
        if (resource == null) {
            throw new IllegalStateException("Cannot find resource " + path);
        }
        try {
            return new String(resource.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}