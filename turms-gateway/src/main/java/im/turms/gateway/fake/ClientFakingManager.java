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

package im.turms.gateway.fake;

import com.google.common.collect.Iterators;
import com.google.common.collect.Range;
import im.turms.common.constant.DeviceType;
import im.turms.common.constant.UserStatus;
import im.turms.common.model.dto.notification.TurmsNotification;
import im.turms.common.model.dto.request.TurmsRequest;
import im.turms.common.model.dto.request.user.UpdateUserOnlineStatusRequest;
import im.turms.gateway.access.tcp.TcpDispatcher;
import im.turms.server.common.client.TurmsClient;
import im.turms.server.common.context.TurmsApplicationContext;
import im.turms.server.common.fake.RandomProtobufGenerator;
import im.turms.server.common.fake.RandomRequestFactory;
import im.turms.server.common.logging.core.logger.Logger;
import im.turms.server.common.logging.core.logger.LoggerFactory;
import im.turms.server.common.property.TurmsPropertiesManager;
import im.turms.server.common.property.env.gateway.FakeProperties;
import im.turms.server.common.util.ProtoUtil;
import im.turms.server.common.util.ThrowableUtil;
import io.netty.util.concurrent.DefaultThreadFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.netty.resources.LoopResources;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author James Chen
 */
@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class ClientFakingManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientFakingManager.class);

    private final List<TurmsClient> clients;
    private final FakeProperties fakeProperties;
    private final TcpDispatcher tcpDispatcher;
    private Thread thread;

    public ClientFakingManager(TcpDispatcher tcpDispatcher,
                               TurmsApplicationContext context,
                               TurmsPropertiesManager propertiesManager) throws InterruptedException {
        this.tcpDispatcher = tcpDispatcher;
        fakeProperties = propertiesManager.getLocalProperties()
                .getGateway()
                .getFake();
        if (!fakeProperties.isEnabled() || context.isProduction()) {
            clients = Collections.emptyList();
            return;
        }
        if (!tcpDispatcher.isEnabled()) {
            throw new IllegalStateException("Cannot run clients because the TCP server is disabled");
        }
        LOGGER.info("Preparing clients");
        // Though the local TCP server has just been set up,
        // we wait to ensure it's ready for connections.
        // Otherwise, clients will fail to connect due to "Connection reset"
        Thread.sleep(1000);
        try {
            clients = prepareClients(fakeProperties.getFirstUserId(), fakeProperties.getUserCount())
                    .block(Duration.ofSeconds(15));
        } catch (Exception e) {
            throw new IllegalStateException("Failed to prepare clients", e);
        }
    }

    @PostConstruct
    private void init() {
        LOGGER.info("Start sending random requests from clients");
        startSendingRandomRequests(clients,
                fakeProperties.getFirstUserId(),
                fakeProperties.getUserCount(),
                fakeProperties.getRequestIntervalMillis(),
                fakeProperties.getRequestCountPerInterval());
    }

    @PreDestroy
    private void shutdown() {
        thread.interrupt();
    }

    private Mono<List<TurmsClient>> prepareClients(long firstUserId, int userCount) {
        LoopResources loopResources = LoopResources.create("turms-client");
        List<Mono<TurmsNotification>> results = new ArrayList<>(userCount);
        List<TurmsClient> clients = new ArrayList<>(userCount);
        for (int i = 0; i < userCount; i++) {
            TurmsClient client = TurmsClient.tcp();
            clients.add(client);
            long userId = firstUserId + i;
            Mono<TurmsNotification> loginResult = client.connect(tcpDispatcher.getHost(), tcpDispatcher.getPort(), loopResources)
                    .then(Mono.defer(() -> client.login(userId, DeviceType.DESKTOP, "123")));
            results.add(loginResult);
        }
        return Mono.when(results).thenReturn(clients);
    }

    private void startSendingRandomRequests(List<TurmsClient> clients,
                                            long firstUserId,
                                            int userCount,
                                            int requestIntervalMillis,
                                            int requestCountPerInterval) {
        if (clients.isEmpty() || thread != null) {
            return;
        }
        Range<Long> userIdRange = Range.closedOpen(firstUserId, firstUserId + userCount);
        int jitter = userCount / 10;
        Range<Long> fakedNumberRange =
                Range.closedOpen(Math.max(0, userIdRange.lowerEndpoint() - jitter), userIdRange.upperEndpoint() + jitter);
        DefaultThreadFactory threadFactory = new DefaultThreadFactory("turms-client-manager", true);
        thread = threadFactory.newThread(() -> {
            Iterator<TurmsClient> clientIterator = Iterators.cycle(clients);
            Set<String> excludedRequestNames = Set.of(RandomRequestFactory.CREATE_SESSION_REQUEST_FILED_NAME,
                    RandomRequestFactory.DELETE_SESSION_REQUEST_FILED_NAME);
            while (!Thread.currentThread().isInterrupted() && clientIterator.hasNext()) {
                int sentRequestCount = 0;
                while (sentRequestCount < requestCountPerInterval) {
                    TurmsClient client = clientIterator.next();
                    try {
                        if (!client.isOpen()) {
                            removeCurrentClient(clientIterator, client);
                            continue;
                        }
                        RandomProtobufGenerator.GeneratorOptions options = new RandomProtobufGenerator
                                .GeneratorOptions(1, 1, fakedNumberRange);
                        TurmsRequest.Builder builder = RandomRequestFactory.create(excludedRequestNames, options);
                        if (builder.hasUpdateUserOnlineStatusRequest()) {
                            UpdateUserOnlineStatusRequest updateStatusRequest = builder.getUpdateUserOnlineStatusRequest();
                            if (updateStatusRequest.getUserStatus() == UserStatus.OFFLINE) {
                                builder.setUpdateUserOnlineStatusRequest(updateStatusRequest.toBuilder()
                                        .setUserStatus(UserStatus.INVISIBLE));
                            }
                        }
                        client.sendRequest(builder)
                                .subscribe(null, t -> {
                                    LOGGER.error("Caught an internal error while sending request: {}",
                                            ProtoUtil.toLogString(builder.build()),
                                            t);
                                    if (ThrowableUtil.isDisconnectedClientError(t)) {
                                        removeCurrentClient(clientIterator, client);
                                    }
                                });
                    } catch (Exception e) {
                        LOGGER.error("Caught an internal error while sending request", e);
                    }
                    sentRequestCount++;
                }
                try {
                    Thread.sleep(Math.max(1, requestIntervalMillis));
                } catch (InterruptedException e) {
                    for (TurmsClient client : clients) {
                        client.logout()
                                .subscribe(null, t -> LOGGER.error("Caught an error while the client with the user ID [{}] logging out", client.getUserId(), t));
                    }
                    break;
                }
            }
            LOGGER.warn("All fake clients has been closed");
        });
        thread.start();
    }

    private void removeCurrentClient(Iterator<TurmsClient> clientIterator, TurmsClient client) {
        clientIterator.remove();
        LOGGER.warn("The session {} has been closed and removed", client.getSessionId());
    }

}
