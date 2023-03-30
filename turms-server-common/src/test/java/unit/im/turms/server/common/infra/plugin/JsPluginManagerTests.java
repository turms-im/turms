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

package unit.im.turms.server.common.infra.plugin;

import java.nio.file.Path;
import java.time.Duration;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.context.ApplicationContext;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import im.turms.server.common.access.client.dto.notification.TurmsNotification;
import im.turms.server.common.infra.context.TurmsApplicationContext;
import im.turms.server.common.infra.logging.core.logger.AsyncLogger;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.plugin.PluginManager;
import im.turms.server.common.infra.plugin.script.ScriptExecutionException;
import im.turms.server.common.infra.property.TurmsProperties;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.property.env.common.plugin.PluginProperties;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author James Chen
 */
class JsPluginManagerTests {

    @Test
    void testBool_forPlainValue() {
        MyExtensionPointForJs extensionPoint = createExtensionPoint();
        assertThat(extensionPoint.testBool()).isTrue();
    }

    @Test
    void testNotification_forComplexObject() {
        MyExtensionPointForJs extensionPoint = createExtensionPoint();
        Mono<List<TurmsNotification>> actual =
                extensionPoint.testNotification(List.of(TurmsNotification.newBuilder()));
        StepVerifier.create(actual)
                .expectNextMatches(notifications -> {
                    assertThat(notifications).hasSize(1);
                    TurmsNotification notification = notifications.get(0);
                    assertThat(notifications).hasSize(1);
                    assertThat(notification.getCode()).isEqualTo(123);
                    assertThat(notification.getReason()).isEqualTo("reason");
                    return true;
                })
                .verifyComplete();
    }

    @Test
    void testFetch() {
        MyExtensionPointForJs extensionPoint = createExtensionPoint();
        String actual = extensionPoint.testFetch()
                .block(Duration.ofSeconds(15));
        assertThat(actual).isEqualTo("turms-im/turms");
    }

    @Test
    void testLog() {
        Logger logger;
        try (MockedStatic<LoggerFactory> factory =
                mockStatic(LoggerFactory.class, CALLS_REAL_METHODS)) {
            logger = mock(AsyncLogger.class);
            when(logger.isInfoEnabled()).thenReturn(true);
            factory.when(() -> LoggerFactory.getLogger(anyString()))
                    .thenReturn(logger);
            MyExtensionPointForJs extensionPoint = createExtensionPoint();
            extensionPoint.testLog();
        }

        verify(logger, times(1)).info("A log from plugin.js");
    }

    @Test
    void testError() {
        MyExtensionPointForJs extensionPoint = createExtensionPoint();
        assertThatThrownBy(extensionPoint::testError).isInstanceOf(ScriptExecutionException.class)
                .cause()
                .hasMessageContaining("An error from plugin.js");
    }

    @Test
    void testNotImplemented_shouldNotThrow() {
        MyExtensionPointForJs extensionPoint = createExtensionPoint();
        extensionPoint.testNotImplemented();
    }

    private MyExtensionPointForJs createExtensionPoint() {
        ApplicationContext context = mock(ApplicationContext.class);
        TurmsApplicationContext applicationContext = mock(TurmsApplicationContext.class);
        when(applicationContext.getHome()).thenReturn(Path.of("./src/test/resources"));
        TurmsPropertiesManager propertiesManager = mock(TurmsPropertiesManager.class);
        when(propertiesManager.getLocalProperties()).thenReturn(new TurmsProperties().toBuilder()
                .plugin(new PluginProperties().toBuilder()
                        .enabled(true)
                        .dir(".")
                        .build())
                .build());
        PluginManager manager = new PluginManager(
                context,
                applicationContext,
                propertiesManager,
                Collections.emptySet());
        List<MyExtensionPointForJs> list = manager.getExtensionPoints(MyExtensionPointForJs.class);
        assertThat(list).hasSize(1);
        return list.get(0);
    }

}