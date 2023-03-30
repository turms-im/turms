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

package integration.access;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.stubbing.OngoingStubbing;
import reactor.core.publisher.Mono;
import reactor.netty.Connection;
import reactor.netty.DisposableServer;
import reactor.netty.tcp.TcpClient;

import im.turms.gateway.access.client.common.connection.ConnectionListener;
import im.turms.gateway.access.client.tcp.TcpServerFactory;
import im.turms.gateway.domain.session.service.SessionService;
import im.turms.server.common.domain.blocklist.service.BlocklistService;
import im.turms.server.common.infra.healthcheck.ServerStatusManager;
import im.turms.server.common.infra.property.env.gateway.TcpProperties;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static im.turms.server.common.infra.unit.ByteSizeUnit.KB;

class TcpServerIT {

    private static final ConnectionListener NEVER_CLOSE =
            (connection, isWebSocketConnection, in, out, onClose) -> Mono.never();

    @Test
    void shouldCloseOrAcceptConnection_accordingTo_ServerStatusManager_isActive()
            throws InterruptedException {
        TcpProperties tcpProperties = new TcpProperties();
        tcpProperties.getSsl()
                .setEnabled(false);
        tcpProperties.setPort(0);
        tcpProperties.setWiretap(true);

        BlocklistService blocklistService = mock(BlocklistService.class);
        when(blocklistService.isIpBlocked(any(byte[].class))).thenReturn(false);

        ServerStatusManager serverStatusManager = mock(ServerStatusManager.class);
        List<Boolean> isActiveReturnValues = List.of(true, false, true, false);
        OngoingStubbing<Boolean> isActiveStubbing = when(serverStatusManager.isActive());
        for (Boolean returnValue : isActiveReturnValues) {
            isActiveStubbing = isActiveStubbing.thenReturn(returnValue);
        }

        SessionService sessionService = mock(SessionService.class);
        DisposableServer server = TcpServerFactory.create(tcpProperties,
                blocklistService,
                serverStatusManager,
                sessionService,
                NEVER_CLOSE,
                8 * KB);

        int i = 0;
        for (Boolean isActive : isActiveReturnValues) {
            System.out.printf("The client with index (%d) is connecting", i);
            Connection connection = TcpClient.create()
                    .host(server.host())
                    .port(server.port())
                    .handle((in, out) -> Mono.never())
                    .connectNow();
            // Must try to read data from the channel,
            // or the socket channel will reflect an outdated connection state.
            // It will throw "java.io.IOException: An existing connection was forcibly closed by the
            // remote host"
            // if the connection is closed.
            connection.inbound()
                    .receive()
                    .subscribe();
            // Wait for the server to close the connection
            Thread.sleep(200);
            boolean isConnected = !connection.isDisposed();
            assertThat(isConnected).isEqualTo(isActive);
            i++;
        }
    }

}
