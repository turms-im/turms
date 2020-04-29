package im.turms.plugin.impl;

import org.springframework.core.env.Environment;
import software.amazon.awssdk.regions.Region;

public class MinioProperties {

    private static final String PREFIX = "turms-plugin.minio.";

    private boolean enabled;
    private String endpoint;
    private String region;
    private String accessKey;
    private String secretKey;

    private Retry retry;

    public MinioProperties(Environment env) {
        enabled = env.getProperty(PREFIX + "enabled", Boolean.class, true);
        endpoint = env.getProperty(PREFIX + "endpoint", String.class, "http://localhost:9000");
        region = env.getProperty(PREFIX + "region", String.class, Region.AWS_GLOBAL.toString());
        accessKey = env.getProperty(PREFIX + "accessKey", String.class, "minioadmin");
        secretKey = env.getProperty(PREFIX + "secretKey", String.class, "minioadmin");

        retry = new Retry(env);
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Retry getRetry() {
        return retry;
    }

    public void setRetry(Retry retry) {
        this.retry = retry;
    }

    public static class Retry {

        private boolean enabled;
        private int initialInterval;
        private int interval;
        private int maxAttempts;

        public Retry(Environment env) {
            enabled = env.getProperty(PREFIX + "retry.enabled", Boolean.class, true);
            initialInterval = env.getProperty(PREFIX + "retry.initialInterval", Integer.class, 30);
            interval = env.getProperty(PREFIX + "retry.interval", Integer.class, 30);
            maxAttempts = env.getProperty(PREFIX + "retry.maxAttempts", Integer.class, 3);
        }

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public int getInitialInterval() {
            return initialInterval;
        }

        public void setInitialInterval(int initialInterval) {
            this.initialInterval = initialInterval;
        }

        public int getInterval() {
            return interval;
        }

        public void setInterval(int interval) {
            this.interval = interval;
        }

        public int getMaxAttempts() {
            return maxAttempts;
        }

        public void setMaxAttempts(int maxAttempts) {
            this.maxAttempts = maxAttempts;
        }
    }
}