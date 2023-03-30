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

package im.turms.server.common.domain.observation.rpc;

import lombok.Data;
import org.springframework.context.ApplicationContext;

import im.turms.server.common.domain.observation.service.IStatisticsService;
import im.turms.server.common.infra.cluster.service.rpc.NodeTypeToHandleRpc;
import im.turms.server.common.infra.cluster.service.rpc.dto.RpcRequest;

/**
 * @author James Chen
 */
@Data
public class CountOnlineUsersRequest extends RpcRequest<Integer> {

    private static final String NAME = "countOnlineUsers";
    private static IStatisticsService statisticsService;

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public NodeTypeToHandleRpc nodeTypeToRequest() {
        return NodeTypeToHandleRpc.SERVICE;
    }

    @Override
    public NodeTypeToHandleRpc nodeTypeToRespond() {
        return NodeTypeToHandleRpc.GATEWAY;
    }

    @Override
    public boolean isAsync() {
        return false;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        super.setApplicationContext(applicationContext);
        if (statisticsService == null) {
            statisticsService = getBean(IStatisticsService.class);
        }
    }

    @Override
    public Integer call() {
        return statisticsService.countLocalOnlineUsers();
    }

}
