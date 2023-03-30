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

package im.turms.service.domain.cluster.access.admin.dto.request;

import java.util.Date;

import im.turms.server.common.domain.common.access.dto.ControllerDTO;
import im.turms.server.common.infra.cluster.node.NodeType;

/**
 * @author James Chen
 */
public record AddMemberDTO(
        String nodeId,
        String zone,
        NodeType nodeType,
        String version,
        boolean isSeed,
        boolean isLeaderEligible,
        Date registrationDate,
        int priority,
        String memberHost,
        int memberPort,
        String adminApiAddress,
        String wsAddress,
        String tcpAddress,
        String udpAddress,
        boolean isActive,
        boolean isHealthy
) implements ControllerDTO {
}