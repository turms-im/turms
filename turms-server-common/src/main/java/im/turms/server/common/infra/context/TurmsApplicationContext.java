/*
 * Copyright (C) 2019 The Turms Project
 * https://github.com/turms-im/turms
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package im.turms.server.common.infra.context;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import jakarta.annotation.Nullable;

import io.lettuce.core.RedisException;
import lombok.Getter;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Hooks;
import reactor.core.publisher.Mono;

import im.turms.server.common.infra.cluster.node.NodeType;
import im.turms.server.common.infra.io.InputOutputException;
import im.turms.server.common.infra.io.ResourceNotFoundException;
import im.turms.server.common.infra.lang.StringUtil;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.property.env.common.ShutdownProperties;
import im.turms.server.common.infra.suggestion.OsConfigurationAdvisor;
import im.turms.server.common.infra.thread.NamedThreadFactory;
import im.turms.server.common.infra.thread.ThreadNameConst;

/**
 * @author James Chen
 */
@Component
@Getter
public class TurmsApplicationContext {

    private static final Logger LOGGER = LoggerFactory.getLogger(TurmsApplicationContext.class);

    private static final String BUILD_INFO_PROPS_PATH = "git.properties";
    private static final String PROPERTY_BUILD_VERSION = "git.build.version";
    private static final String PROPERTY_BUILD_TIME = "git.build.time";
    private static final String PROPERTY_COMMIT_ID = "git.commit.id.full";
    private static final String DEFAULT_VERSION = "0.0.0";

    private boolean isClosing;

    private final Path home;
    private final String configDir;
    private final boolean isProduction;
    private final boolean isDevOrLocalTest;
    private final String activeEnvProfile;
    private final BuildProperties buildProperties;

    private long shutdownJobTimeoutMillis;
    private final TreeMap<JobShutdownOrder, ShutdownHook> shutdownHooks = new TreeMap<>();

    public TurmsApplicationContext(Environment environment, NodeType nodeType) {
        LoggerFactory.bindContext(this);

        String homeDir = nodeType == NodeType.SERVICE
                ? System.getenv("TURMS_SERVICE_HOME")
                : System.getenv("TURMS_GATEWAY_HOME");
        home = homeDir == null
                ? Path.of("")
                        .toAbsolutePath()
                : Path.of(homeDir)
                        .toAbsolutePath();

        // The property should be passed from "bin/run.sh"
        String configDir = System.getProperty("spring.config.location");
        if (configDir == null || configDir.isBlank()) {
            LOGGER.warn("The property \"spring.config.location\" is empty");
            configDir = "./config";
        }
        this.configDir = configDir;

        List<String> devEnvs = List.of("dev", "development", "local");
        List<String> localTestEnvs = List.of("test", "testing");
        List<String> testEnvs = List.of("qa", "stg", "uat", "quality", "staging", "demo");
        List<String> prodEnvs = List.of("prod", "production");
        String[] activeProfiles = environment.getActiveProfiles();

        activeEnvProfile =
                getActiveEnvProfile(activeProfiles, devEnvs, localTestEnvs, testEnvs, prodEnvs);
        isDevOrLocalTest = isInProfiles(devEnvs, activeProfiles)
                || isInProfiles(localTestEnvs, activeProfiles);
        // Prefer "isProduction" to be true to avoid getting trouble in production
        isProduction = !isDevOrLocalTest && !isInProfiles(testEnvs, activeProfiles);
        BuildProperties tempBuildProperties = getGitBuildProperties();
        if (tempBuildProperties == null) {
            if (isProduction) {
                throw new ResourceNotFoundException(
                        "The file ("
                                + BUILD_INFO_PROPS_PATH
                                + ") must exist in production");
            }
            // We allow "git.properties" not exist in non-production
            // environments for a better development experience
            LOGGER.warn("Could not find the file ("
                    + BUILD_INFO_PROPS_PATH
                    + "), fall back to the default version "
                    + DEFAULT_VERSION
                    + " in non-production environments. Fix it by running \"mvn compile\"");
            tempBuildProperties = new BuildProperties(DEFAULT_VERSION, "", "");
        }
        buildProperties = tempBuildProperties;

        LOGGER.info(
                "The local node with the build properties {version={}, commitId={}, buildTime={}} is running in a {} environment",
                buildProperties.version(),
                buildProperties.commitId(),
                buildProperties.buildTime(),
                isProduction
                        ? "production"
                        : "non-production");

        setupErrorHandlerContext();
        printOsConfigurationSuggestions();
    }

    @EventListener(classes = ContextRefreshedEvent.class)
    public void handleContextRefreshedEvent(ContextRefreshedEvent event) {
        TurmsPropertiesManager propertiesManager = event.getApplicationContext()
                .getBean(TurmsPropertiesManager.class);
        ShutdownProperties properties = propertiesManager.getLocalProperties()
                .getShutdown();
        shutdownJobTimeoutMillis = properties.getJobTimeoutMillis();
    }

    @EventListener(classes = ContextClosedEvent.class)
    public void handleContextClosedEvent() {
        isClosing = true;
        ExecutorService executor = Executors
                .newSingleThreadExecutor(new NamedThreadFactory(ThreadNameConst.SHUTDOWN, false));
        for (Map.Entry<JobShutdownOrder, ShutdownHook> orderAndJob : shutdownHooks.entrySet()) {
            JobShutdownOrder key = orderAndJob.getKey();
            String jobName = key.name();
            boolean isClosingLogProcessor = key == JobShutdownOrder.CLOSE_LOG_PROCESSOR;
            Future<Mono<Void>> shutdownFuture = executor.submit(() -> orderAndJob.getValue()
                    .run(shutdownJobTimeoutMillis));
            try {
                long time = System.currentTimeMillis();
                Mono<Void> mono =
                        shutdownFuture.get(shutdownJobTimeoutMillis, TimeUnit.MILLISECONDS);
                if (mono == null) {
                    throw new IllegalArgumentException(
                            "The result of the job \""
                                    + jobName
                                    + "\" must not be null");
                }
                time = shutdownJobTimeoutMillis - (System.currentTimeMillis() - time);
                if (time <= 0) {
                    throw new TimeoutException();
                }
                mono.block(Duration.ofMillis(time));
            } catch (TimeoutException e) {
                shutdownFuture.cancel(true);
                if (!isClosingLogProcessor) {
                    LOGGER.error("Failed to run the shutdown job \""
                            + jobName
                            + "\" in time");
                }
            } catch (Exception e) {
                if (!isClosingLogProcessor) {
                    LOGGER.error("Caught an error while running the shutdown job: \""
                            + jobName
                            + "\"", e);
                }
            }
        }
        executor.shutdownNow();
    }

    public synchronized void addShutdownHook(JobShutdownOrder order, ShutdownHook job) {
        if (shutdownHooks.putIfAbsent(order, job) != null) {
            String formatted = "Failed to add a shutdown hook for the job \""
                    + order.name()
                    + "\" because it has been registered";
            throw new IllegalStateException(formatted);
        }
    }

    private String getActiveEnvProfile(String[] activeProfiles, List<String>... knownEnvProfiles) {
        for (String profile : activeProfiles) {
            if (profile.endsWith("-latest")) {
                continue;
            }
            for (List<String> envProfiles : knownEnvProfiles) {
                for (String envProfile : envProfiles) {
                    if (profile.equalsIgnoreCase(envProfile)) {
                        return envProfile.toLowerCase();
                    }
                }
            }
        }
        return null;
    }

    private boolean isInProfiles(List<String> profiles, String[] activeProfiles) {
        for (String profile : profiles) {
            for (String activeProfile : activeProfiles) {
                if (profile.equalsIgnoreCase(activeProfile)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Nullable
    private BuildProperties getGitBuildProperties() {
        InputStream resourceAsStream = TurmsApplicationContext.class.getClassLoader()
                .getResourceAsStream(BUILD_INFO_PROPS_PATH);
        if (resourceAsStream == null) {
            return null;
        }
        Properties properties = new Properties();
        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            throw new InputOutputException(
                    "Failed to load properties: "
                            + BUILD_INFO_PROPS_PATH,
                    e);
        }
        String version = properties.getProperty(PROPERTY_BUILD_VERSION);
        String buildTime = properties.getProperty(PROPERTY_BUILD_TIME);
        String commitId = properties.getProperty(PROPERTY_COMMIT_ID);
        if (StringUtil.isBlank(version)) {
            throw new IllegalArgumentException(
                    "The property \""
                            + PROPERTY_BUILD_VERSION
                            + "\" must exist and not be blank");
        }
        if (StringUtil.isBlank(buildTime)) {
            throw new IllegalArgumentException(
                    "The property \""
                            + PROPERTY_BUILD_TIME
                            + "\" must exist and not be blank");
        }
        if (StringUtil.isBlank(commitId)) {
            throw new IllegalArgumentException(
                    "The property \""
                            + PROPERTY_COMMIT_ID
                            + "\" must exist and not be blank");
        }
        return new BuildProperties(version, commitId, buildTime);
    }

    private void setupErrorHandlerContext() {
        Hooks.onErrorDropped(t -> {
            // throwable is always the instance of ErrorCallbackNotImplemented
            Throwable cause = t.getCause();
            if (isReadFromForciblyClosedConnectionException(cause)) {
                // Ignore the exception in production because it should not have side effects,
                // and we cannot avoid the exception completely because of its root cause.
                // Log the exception only in non-production env, so we can try to optimize the code
                // of
                // client to close the connection with a 4-way handshake on the client side
                if (!this.isProduction) {
                    LOGGER.warn("Failed to read from a forcibly closed connection", t);
                }
            } else {
                if (isClosing) {
                    if (isRedisConnectionClosedException(t)
                            || isMongoConnectionClosedException(t)) {
                        return;
                    }
                }
                LOGGER.error("Unhandled exception", t);
            }
        });
    }

    /**
     * The exception occurs when a socket tries to read from a closed connection without a 4-way
     * handshake
     */
    private boolean isReadFromForciblyClosedConnectionException(Throwable throwable) {
        if (throwable instanceof IOException) {
            StackTraceElement[] stackTrace = throwable.getStackTrace();
            if (stackTrace.length > 0) {
                StackTraceElement traceElement = stackTrace[0];
                return traceElement.getClassName()
                        .endsWith("SocketDispatcher")
                        && traceElement.getMethodName()
                                .startsWith("read");
            }
        }
        return false;
    }

    private boolean isMongoConnectionClosedException(Throwable throwable) {
        Throwable cause = throwable.getCause();
        if (cause instanceof IllegalStateException) {
            String message = cause.getMessage();
            return "state should be: open".equals(message)
                    || "state should be: server session pool is open".equals(message);
        }
        return false;
    }

    private boolean isRedisConnectionClosedException(Throwable throwable) {
        Throwable cause = throwable.getCause();
        return cause instanceof RedisException && "Connection closed".equals(cause.getMessage());
    }

    private void printOsConfigurationSuggestions() {
        List<String> suggestions = OsConfigurationAdvisor.getSuggestions();
        for (String suggestion : suggestions) {
            LOGGER.warn(suggestion);
        }
    }

}