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

package im.turms.gateway.domain.session.access.admin.controller;

import java.util.List;
import java.util.Set;

import reactor.core.publisher.Mono;

import im.turms.gateway.domain.session.service.SessionService;
import im.turms.server.common.access.admin.dto.response.DeleteResultDTO;
import im.turms.server.common.access.admin.dto.response.HttpHandlerResult;
import im.turms.server.common.access.admin.dto.response.ResponseDTO;
import im.turms.server.common.access.admin.permission.AdminPermission;
import im.turms.server.common.access.admin.permission.RequiredPermission;
import im.turms.server.common.access.admin.web.annotation.DeleteMapping;
import im.turms.server.common.access.admin.web.annotation.QueryParam;
import im.turms.server.common.access.admin.web.annotation.RestController;
import im.turms.server.common.domain.session.bo.CloseReason;
import im.turms.server.common.domain.session.bo.SessionCloseStatus;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.net.InetAddressUtil;

/**
 * @author James Chen
 */
@RestController("sessions")
public class SessionController {

    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @RequiredPermission(AdminPermission.SESSION_DELETE)
    @DeleteMapping
    public Mono<HttpHandlerResult<ResponseDTO<DeleteResultDTO>>> deleteSessions(
            @QueryParam(required = false) Set<Long> ids,
            @QueryParam(required = false) Set<String> ips) {
        CloseReason closeReason = CloseReason.get(SessionCloseStatus.DISCONNECTED_BY_ADMIN);
        Mono<Integer> mono;
        if (CollectionUtil.isEmpty(ids)) {
            if (CollectionUtil.isEmpty(ips)) {
                mono = sessionService.closeAllLocalSessions(closeReason);
            } else {
                List<byte[]> ipList =
                        CollectionUtil.transformAsList(ips, InetAddressUtil::ipStringToBytes);
                mono = sessionService.closeLocalSessions(ipList, closeReason);
            }
        } else {
            if (CollectionUtil.isEmpty(ips)) {
                mono = sessionService.closeLocalSessions(ids, closeReason);
            } else {
                List<byte[]> ipList =
                        CollectionUtil.transformAsList(ips, InetAddressUtil::ipStringToBytes);
                mono = Mono.zip(sessionService.closeLocalSessions(ids, closeReason),
                        Mono.defer(() -> sessionService.closeLocalSessions(ipList, closeReason)),
                        Integer::sum);
            }
        }
        return HttpHandlerResult
                .okIfTruthy(mono.map(count -> new DeleteResultDTO(count.longValue())));
    }

}
