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

package im.turms.gateway.infra.fake;

import com.google.common.collect.Iterators;
import com.google.common.collect.Range;
import im.turms.gateway.access.client.tcp.TcpUserSessionAssembler;
import im.turms.gateway.infra.thread.ThreadNameConst;
import im.turms.server.common.access.client.dto.constant.DeviceType;
import im.turms.server.common.access.client.dto.constant.UserStatus;
import im.turms.server.common.access.client.dto.notification.TurmsNotification;
import im.turms.server.common.access.client.dto.request.TurmsRequest;
import im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequest;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.infra.client.TurmsClient;
import im.turms.server.common.infra.context.JobShutdownOrder;
import im.turms.server.common.infra.context.TurmsApplicationContext;
import im.turms.server.common.infra.exception.ThrowableUtil;
import im.turms.server.common.infra.fake.RandomProtobufGenerator;
import im.turms.server.common.infra.fake.RandomRequestFactory;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.property.env.gateway.FakeProperties;
import im.turms.server.common.infra.proto.ProtoFormatter;
import im.turms.server.common.infra.thread.NamedThreadFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.netty.resources.LoopResources;
import reactor.util.retry.Retry;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author James Chen
 */
@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class ClientFakingManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientFakingManager.class);

    private final boolean enabled;
    private final FakeProperties fakeProperties;
    private final TcpUserSessionAssembler tcpUserSessionDispatcher;
    private Thread thread;

    public ClientFakingManager(TcpUserSessionAssembler tcpUserSessionDispatcher,
                               TurmsApplicationContext context,
                               TurmsPropertiesManager propertiesManager) {
        this.tcpUserSessionDispatcher = tcpUserSessionDispatcher;
        fakeProperties = propertiesManager.getLocalProperties()
                .getGateway()
                .getFake();
        enabled = !context.isProduction()
                && fakeProperties.isEnabled()
                && fakeProperties.getUserCount() > 0
                && fakeProperties.getRequestCountPerInterval() > 0;
        if (!tcpUserSessionDispatcher.isEnabled()) {
            throw new IllegalStateException("Cannot run clients because the TCP server is disabled");
        }
        context.addShutdownHook(JobShutdownOrder.CLOSE_FAKE_CLIENTS, timeoutMillis -> {
            shutdown(timeoutMillis);
            return Mono.empty();
        });
    }

    @PostConstruct
    private void init() {
        if (enabled) {
            prepareClients(fakeProperties.getFirstUserId(), fakeProperties.getUserCount())
                    .doOnSuccess(clients -> startSendingRandomRequests(clients,
                            fakeProperties.getFirstUserId(),
                            fakeProperties.getUserCount(),
                            fakeProperties.getRequestIntervalMillis(),
                            fakeProperties.getRequestCountPerInterval()))
                    .subscribe(null, t -> LOGGER.error("Failed to prepare clients", t));
        }
    }

    private void shutdown(long timeoutMillis) {
        if (thread != null) {
            thread.interrupt();
            try {
                thread.join(timeoutMillis);
            } catch (InterruptedException e) {
                // ignore
            }
        }
    }

    /**
     * The main reason to prepare clients in an async way is
     * to recover from "Connection reset" to retry to connect
     * without blocking the server
     */
    private Mono<List<TurmsClient>> prepareClients(long firstUserId, int userCount) {
        LOGGER.info("Preparing clients");
        LoopResources loopResources = LoopResources.create(ThreadNameConst.FAKE_CLIENT);
        List<Mono<TurmsNotification>> results = new ArrayList<>(userCount);
        ConcurrentLinkedQueue<TurmsClient> clients = new ConcurrentLinkedQueue<>();
        for (int i = 0; i < userCount; i++) {
            TurmsClient client = TurmsClient.tcp();
            long userId = firstUserId + i;
            Mono<TurmsNotification> loginResult = client.connect(tcpUserSessionDispatcher.getHost(), tcpUserSessionDispatcher.getPort(), loopResources)
                    .retryWhen(Retry.backoff(5, Duration.ofSeconds(15))
                            // e.g. "Connection reset"
                            .filter(ThrowableUtil::isDisconnectedClientError))
                    .then(Mono.defer(() -> client.login(userId, DeviceType.DESKTOP, "123")))
                    .doOnSuccess(notification -> {
                        if (ResponseStatusCode.isSuccessCode(notification.getCode())) {
                            clients.add(client);
                        } else {
                            LOGGER.error("The session {} failed to log in: {}", client.getSessionId(),
                                    ProtoFormatter.toJSON5(notification, 128));
                        }
                    })
                    .onErrorResume(t -> {
                        LOGGER.error("The session {} failed to log in", client.getSessionId(), t);
                        return Mono.empty();
                    });
            results.add(loginResult);
        }
        return Mono.when(results)
                .then(Mono.fromCallable(() -> {
                    ArrayList<TurmsClient> list = new ArrayList<>(clients);
                    LOGGER.info("Prepared clients. Expected: {}. Prepared: {}", userCount, list.size());
                    return list;
                }));
    }

    private void startSendingRandomRequests(List<TurmsClient> clients,
                                            long firstUserId,
                                            int userCount,
                                            int requestIntervalMillis,
                                            int requestCountPerInterval) {
        LOGGER.info("Start sending random requests from clients");
        Range<Long> userIdRange = Range.closedOpen(firstUserId, firstUserId + userCount);
        int jitter = userCount / 10;
        Range<Long> fakedNumberRange = Range
                .closedOpen(Math.max(0, userIdRange.lowerEndpoint() - jitter), userIdRange.upperEndpoint() + jitter);
        thread = NamedThreadFactory.newThread(ThreadNameConst.FAKE_CLIENT_MANAGER, true, () -> {
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
                        TurmsRequest.Builder request = generateRandomRequest(excludedRequestNames, fakedNumberRange);
                        client.sendRequest(request)
                                .subscribe(null, t -> {
                                    LOGGER.error("Caught an internal error while sending request: {}",
                                            ProtoFormatter.toLogString(request), t);
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
                                .subscribe(null, t -> LOGGER.error("Caught an error while the client with the user ID [{}] logging out",
                                        client.getUserId(), t));
                    }
                    break;
                }
            }
            LOGGER.warn("All fake clients has been closed");
        });
        thread.start();
    }

    private TurmsRequest.Builder generateRandomRequest(Set<String> excludedRequestNames, Range<Long> fakedNumberRange) {
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
        return builder;
    }

    private void removeCurrentClient(Iterator<TurmsClient> clients, TurmsClient client) {
        clients.remove();
        LOGGER.warn("The session {} has been closed and removed", client.getSessionId());
    }

}
