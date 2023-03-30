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

package im.turms.service.domain.observation.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.math.MathFlux;

import im.turms.server.common.domain.observation.rpc.CountOnlineUsersRequest;
import im.turms.server.common.infra.cluster.node.Node;
import im.turms.server.common.infra.cluster.service.rpc.RpcErrorCode;
import im.turms.server.common.infra.cluster.service.rpc.exception.RpcException;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.reactor.PublisherPool;
import im.turms.server.common.infra.task.TaskManager;

/**
 * @author James Chen
 */

@Service
public class StatisticsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatisticsService.class);

    private final Node node;

    private static final String ONLINE_USERS_NUMBER_LOGGING_FORMAT =
            "The current online users number is: {}";

    public StatisticsService(
            Node node,
            TurmsPropertiesManager propertiesManager,
            TaskManager taskManager) {
        this.node = node;
        taskManager.reschedule("onlineUsersNumberLogging",
                propertiesManager.getLocalProperties()
                        .getService()
                        .getStatistics()
                        .getOnlineUsersNumberLoggingCron(),
                () -> {
                    if (node.isLocalNodeLeader()
                            && propertiesManager.getGlobalProperties()
                                    .getService()
                                    .getStatistics()
                                    .isLogOnlineUsersNumber()) {
                        countOnlineUsers().subscribe(
                                count -> LOGGER.info(ONLINE_USERS_NUMBER_LOGGING_FORMAT, count),
                                t -> LOGGER.error("Failed to count online users", t));
                    }
                });
    }

    /**
     * @implNote Note that the count requests are sent by turms but all responses should come from
     *           turms-gateway so we don't need to count the local online users like ".map(count ->
     *           count + countLocalOnlineUsers())"
     */
    public Mono<Map<String, Integer>> countOnlineUsersByNodes() {
        CountOnlineUsersRequest request = new CountOnlineUsersRequest();
        return node.getRpcService()
                .requestResponsesAsMapFromOtherMembers(request, false)
                .onErrorResume(
                        throwable -> RpcException.isErrorCode(throwable,
                                RpcErrorCode.MEMBER_NOT_FOUND),
                        throwable -> PublisherPool.emptyMap());
    }

    public Mono<Integer> countOnlineUsers() {
        CountOnlineUsersRequest request = new CountOnlineUsersRequest();
        Flux<Integer> responses = node.getRpcService()
                .requestResponsesFromOtherMembers(request, true)
                .onErrorResume(
                        throwable -> RpcException.isErrorCode(throwable,
                                RpcErrorCode.MEMBER_NOT_FOUND),
                        throwable -> Mono.just(0));
        return MathFlux.sumInt(responses);
    }

}