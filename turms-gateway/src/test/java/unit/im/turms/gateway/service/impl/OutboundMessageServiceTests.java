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

package unit.im.turms.gateway.service.impl;

import im.turms.common.constant.DeviceType;
import im.turms.gateway.manager.UserSessionsManager;
import im.turms.gateway.plugin.extension.NotificationHandler;
import im.turms.gateway.plugin.manager.TurmsPluginManager;
import im.turms.gateway.pojo.bo.session.UserSession;
import im.turms.gateway.pojo.bo.session.connection.TcpConnection;
import im.turms.gateway.service.impl.OutboundMessageService;
import im.turms.gateway.service.impl.SessionService;
import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.property.TurmsProperties;
import im.turms.server.common.property.env.common.PluginProperties;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.UnpooledByteBufAllocator;
import org.junit.jupiter.api.Test;
import org.springframework.data.geo.Point;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author James Chen
 */
class OutboundMessageServiceTests {

    @Test
    void constructor_shouldReturnInstance() {
        OutboundMessageService outboundMessageService = new OutboundMessageService(null, null, null);
        assertThat(outboundMessageService).isNotNull();
    }

    @Test
    void sendNotificationToLocalClients_shouldReleaseAndReturnTrue_ifRecipientsAreOnline() {
        UserSessionsManager sessionsManager = mock(UserSessionsManager.class);
        TcpConnection connection = mock(TcpConnection.class);
        UserSession session = new UserSession(1, 1L, DeviceType.ANDROID, new Point(1F, 1F));
        session.setConnection(connection);
        Sinks.One<ByteBuf> sink = Sinks.one();
        session.setNotificationConsumer(sink::tryEmitValue);
        Mono<ByteBuf> result = sink.asMono()
                .flatMap(byteBuf -> Mono
                        // Wait 1s to simulate the async process
                        .delay(Duration.ofSeconds(1))
                        .then(Mono.fromRunnable(byteBuf::release))
                        .thenReturn(byteBuf));
        when(sessionsManager.getSessionMap())
                .thenReturn(Map.of(DeviceType.ANDROID, session));
        OutboundMessageService outboundMessageService = newOutboundMessageService(sessionsManager);

        ByteBuf byteBuf = UnpooledByteBufAllocator.DEFAULT.directBuffer();
        Set<Long> recipientIds = Set.of(1L);
        boolean sent = outboundMessageService.sendNotificationToLocalClients(byteBuf, recipientIds);

        assertThat(byteBuf.refCnt())
                .as("Buffer should not be released if the notification hasn't been sent")
                .isPositive();
        StepVerifier.create(result)
                .expectNextMatches(buf -> buf.refCnt() == 0)
                .as("Buffer should be released if the notification is sent")
                .verifyComplete();
        assertThat(sent)
                .as("Notification is sent or queued")
                .isTrue();
    }

    @Test
    void sendNotificationToLocalClients_shouldReleaseAndReturnFalse_ifRecipientsAreOffline() {
        OutboundMessageService outboundMessageService = newOutboundMessageService(null);

        ByteBuf byteBuf = UnpooledByteBufAllocator.DEFAULT.directBuffer();
        Set<Long> recipientIds = Set.of(1L);
        boolean sent = outboundMessageService.sendNotificationToLocalClients(byteBuf, recipientIds);

        assertThat(byteBuf.refCnt())
                .as("Buffer should be released if recipients are offline")
                .isZero();
        assertThat(sent)
                .as("Notification isn't sent or queued")
                .isFalse();
    }

    private OutboundMessageService newOutboundMessageService(UserSessionsManager userSessionsManager) {
        Node node = mock(Node.class);
        TurmsProperties properties = new TurmsProperties().toBuilder()
                .plugin(new PluginProperties().toBuilder()
                        .enabled(true)
                        .build())
                .build();
        when(node.getSharedProperties())
                .thenReturn(properties);

        NotificationHandler handler = mock(NotificationHandler.class);
        when(handler.handle(any(), any(), any()))
                .thenReturn(Mono.empty());
        TurmsPluginManager pluginManager = mock(TurmsPluginManager.class);
        when(pluginManager.getNotificationHandlerList())
                .thenReturn(List.of(handler));

        SessionService sessionService = mock(SessionService.class);
        when(sessionService.getUserSessionsManager(any()))
                .thenReturn(userSessionsManager);
        return new OutboundMessageService(node, sessionService, pluginManager);
    }

}