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

package im.turms.service.domain.blocklist.access.admin.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Mono;

import im.turms.server.common.access.admin.dto.response.HttpHandlerResult;
import im.turms.server.common.access.admin.dto.response.PaginationDTO;
import im.turms.server.common.access.admin.dto.response.ResponseDTO;
import im.turms.server.common.access.admin.permission.RequiredPermission;
import im.turms.server.common.access.admin.web.annotation.DeleteMapping;
import im.turms.server.common.access.admin.web.annotation.GetMapping;
import im.turms.server.common.access.admin.web.annotation.PostMapping;
import im.turms.server.common.access.admin.web.annotation.QueryParam;
import im.turms.server.common.access.admin.web.annotation.RequestBody;
import im.turms.server.common.access.admin.web.annotation.RestController;
import im.turms.server.common.domain.blocklist.bo.BlockedClient;
import im.turms.server.common.domain.blocklist.service.BlocklistService;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.service.domain.blocklist.access.admin.dto.request.AddBlockedUserIdsDTO;
import im.turms.service.domain.blocklist.access.admin.dto.response.BlockedUserDTO;
import im.turms.service.domain.common.access.admin.controller.BaseController;

import static im.turms.server.common.access.admin.permission.AdminPermission.CLIENT_BLOCKLIST_CREATE;
import static im.turms.server.common.access.admin.permission.AdminPermission.CLIENT_BLOCKLIST_DELETE;
import static im.turms.server.common.access.admin.permission.AdminPermission.CLIENT_BLOCKLIST_QUERY;

/**
 * @author James Chen
 */
@RestController("blocked-clients/users")
public class UserBlocklistController extends BaseController {

    private final BlocklistService blocklistService;

    public UserBlocklistController(
            TurmsPropertiesManager propertiesManager,
            BlocklistService blocklistService) {
        super(propertiesManager);
        this.blocklistService = blocklistService;
    }

    @PostMapping
    @RequiredPermission(CLIENT_BLOCKLIST_CREATE)
    public Mono<HttpHandlerResult<ResponseDTO<Void>>> addBlockedUserIds(
            @RequestBody AddBlockedUserIdsDTO addBlockedUserIdsDTO) {
        Mono<Void> result = blocklistService.blockUserIds(addBlockedUserIdsDTO.ids(),
                addBlockedUserIdsDTO.blockDurationSeconds());
        return HttpHandlerResult.okIfTruthy(result);
    }

    @GetMapping
    @RequiredPermission(CLIENT_BLOCKLIST_QUERY)
    public HttpHandlerResult<ResponseDTO<Collection<BlockedUserDTO>>> queryBlockedUsers(
            Set<Long> ids) {
        List<BlockedClient<Long>> blockedUsers = blocklistService.getBlockedUsers(ids);
        return HttpHandlerResult.okIfTruthy(clients2users(blockedUsers));
    }

    @GetMapping("page")
    @RequiredPermission(CLIENT_BLOCKLIST_QUERY)
    public HttpHandlerResult<ResponseDTO<PaginationDTO<BlockedUserDTO>>> queryBlockedUsers(
            int page,
            @QueryParam(required = false) Integer size) {
        size = getPageSize(size);
        int blockUserCount = blocklistService.countBlockUsers();
        List<BlockedClient<Long>> blockedUsers = blocklistService.getBlockedUsers(page, size);
        return HttpHandlerResult.page(blockUserCount, clients2users(blockedUsers));
    }

    @DeleteMapping
    @RequiredPermission(CLIENT_BLOCKLIST_DELETE)
    public Mono<HttpHandlerResult<ResponseDTO<Void>>> deleteBlockedUserIds(
            @QueryParam(required = false) Set<Long> ids,
            boolean deleteAll) {
        Mono<Void> result = Mono.empty();
        if (deleteAll) {
            result = blocklistService.unblockAllUserIds();
        } else if (!CollectionUtils.isEmpty(ids)) {
            result = result.then(blocklistService.unblockUserIds(ids));
        }
        return HttpHandlerResult.okIfTruthy(result);
    }

    private List<BlockedUserDTO> clients2users(Collection<BlockedClient<Long>> blockedClients) {
        List<BlockedUserDTO> items = new ArrayList<>(blockedClients.size());
        for (BlockedClient<Long> blockedClient : blockedClients) {
            items.add(new BlockedUserDTO(
                    blockedClient.id(),
                    new Date(blockedClient.blockEndTimeMillis())));
        }
        return items;
    }

}