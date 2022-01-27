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

package unit.im.turms.server.common.plugin;

import im.turms.common.model.dto.notification.TurmsNotification;
import im.turms.plugin.MyExtensionPoint;
import im.turms.server.common.plugin.PluginManager;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

/**
 * @author James Chen
 */
class JsPluginManagerTests {

    @Test
    void test() {
        ApplicationContext context = mock(ApplicationContext.class);
        PluginManager manager = new PluginManager(Path.of("./src/test/resources"), context);
        List<MyExtensionPoint> list = manager.getExtensionPoints(MyExtensionPoint.class);
        assertThat(list).hasSize(1);
        MyExtensionPoint extensionPoint = list.get(0);
        assertThat(extensionPoint.testBool()).isTrue();
        Mono<List<TurmsNotification>> actual = extensionPoint.testNotification(List.of(TurmsNotification.newBuilder()));
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
}