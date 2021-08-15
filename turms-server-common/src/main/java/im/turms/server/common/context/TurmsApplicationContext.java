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

package im.turms.server.common.context;

import io.lettuce.core.RedisException;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Hooks;

import java.io.IOException;
import java.util.List;
import java.util.TimeZone;

/**
 * @author James Chen
 */
@Component
@Getter
@Log4j2
public class TurmsApplicationContext {

    private static final String BUILD_INFO_PROPS_PATH = "classpath:META-INF/build-info.properties";
    private static final String DEFAULT_VERSION = "0.0.0";

    private boolean isClosing;

    private final boolean isProduction;
    private final boolean isDevOrLocalTest;
    private final String activeEnvProfile;
    private final String version;

    public TurmsApplicationContext(Environment environment,
                                   @Autowired(required = false) BuildProperties buildProperties) {
        // Don't allow developers to configure it because it's a bad practice to use other zone
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

        List<String> devEnvs = List.of("dev", "development",
                "local");
        List<String> localTestEnvs = List.of("test", "testing");
        List<String> testEnvs = List.of("qa", "stg", "uat",
                "quality", "staging",
                "demo");
        List<String> prodEnvs = List.of("prod", "production");
        String[] activeProfiles = environment.getActiveProfiles();

        activeEnvProfile = getActiveEnvProfile(activeProfiles, devEnvs, localTestEnvs, testEnvs, prodEnvs);
        isDevOrLocalTest = isInProfiles(devEnvs, activeProfiles) || isInProfiles(localTestEnvs, activeProfiles);
        // Prefer isProduction to be true to avoid getting trouble in production environment
        isProduction = !isDevOrLocalTest && !isInProfiles(testEnvs, activeProfiles);
        version = getVersion(isProduction, buildProperties);

        log.info("The local node with version {} is running in a {} environment",
                version,
                isProduction ? "production" : "non-production");

        setupErrorHandlerContext();
    }

    @EventListener(classes = ContextClosedEvent.class)
    public void handleContextClosedEvent() {
        isClosing = true;
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

    private String getVersion(boolean isProduction, BuildProperties buildProperties) {
        if (isProduction) {
            if (buildProperties == null) {
                throw new IllegalStateException(BUILD_INFO_PROPS_PATH + " must exist in production");
            }
            return buildProperties.getVersion();
        }
        if (buildProperties == null) {
            // We allow build-info.properties not exist in non-production
            // environments for a better development experience
            log.warn("Cannot find " + BUILD_INFO_PROPS_PATH +
                    ", fall back to the default version " + DEFAULT_VERSION +
                    " in non-production environments." +
                    " Fix it by running \"mvn compile\"");
            return DEFAULT_VERSION;
        }
        return buildProperties.getVersion();
    }

    private void setupErrorHandlerContext() {
        Hooks.onErrorDropped(t -> {
            // throwable is always the instance of ErrorCallbackNotImplemented
            Throwable cause = t.getCause();
            if (isReadFromForciblyClosedConnectionException(cause)) {
                // Ignore the exception in production env because it should not have side effects,
                // and we cannot avoid the exception completely because of its root cause.
                // Log the exception only in non-production env, so we can try to optimize the code of
                // client to close the connection with a 4-way handshake on the client side
                if (!this.isProduction) {
                    log.warn(t);
                }
            } else {
                if (isClosing) {
                    if (isRedisConnectionClosedException(t) || isMongoConnectionClosedException(t)) {
                        return;
                    }
                }
                log.error("Unhandled exception", t);
            }
        });
    }

    /**
     * The exception occurs when a socket tries to read from a closed connection without a 4-way handshake
     */
    private boolean isReadFromForciblyClosedConnectionException(Throwable throwable) {
        if (throwable instanceof IOException) {
            StackTraceElement[] stackTrace = throwable.getStackTrace();
            if (stackTrace.length > 0) {
                StackTraceElement traceElement = stackTrace[0];
                return traceElement.getClassName().endsWith("SocketDispatcher") && traceElement.getMethodName().startsWith("read");
            }
        }
        return false;
    }

    private boolean isMongoConnectionClosedException(Throwable throwable) {
        Throwable cause = throwable.getCause();
        if (cause instanceof IllegalStateException) {
            String message = cause.getMessage();
            return "state should be: open".equals(message) || "state should be: server session pool is open".equals(message);
        }
        return false;
    }

    private boolean isRedisConnectionClosedException(Throwable throwable) {
        Throwable cause = throwable.getCause();
        return cause instanceof RedisException && "Connection closed".equals(cause.getMessage());
    }

}