/*
 * Copyright (C) 2019 The Turms Project
 * https://github.com/turms-im/turms
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package im.turms.turms.workflow.access.http.controller.blocklist;

import im.turms.server.common.access.http.dto.response.ResponseDTO;
import im.turms.server.common.access.http.dto.response.ResponseFactory;
import im.turms.server.common.service.blocklist.BlocklistService;
import im.turms.turms.workflow.access.http.dto.request.blocklist.AddBlockedClientsDTO;
import im.turms.turms.workflow.access.http.dto.response.BlockedClientsDTO;
import im.turms.turms.workflow.access.http.permission.RequiredPermission;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static im.turms.turms.workflow.access.http.permission.AdminPermission.CLIENT_BLOCKLIST_CREATE;
import static im.turms.turms.workflow.access.http.permission.AdminPermission.CLIENT_BLOCKLIST_DELETE;
import static im.turms.turms.workflow.access.http.permission.AdminPermission.CLIENT_BLOCKLIST_QUERY;

/**
 * @author James Chen
 */
@RestController
@RequestMapping("/blocked-clients")
public class ClientBlocklistController {

    private final BlocklistService blocklistService;

    public ClientBlocklistController(BlocklistService blocklistService) {
        this.blocklistService = blocklistService;
    }

    @PostMapping
    @RequiredPermission(CLIENT_BLOCKLIST_CREATE)
    public Mono<ResponseEntity<ResponseDTO<Void>>> addBlockedClients(
            @RequestBody AddBlockedClientsDTO addBlockedClientsDTO) {
        Mono<Void> result = Mono.empty();
        if (!CollectionUtils.isEmpty(addBlockedClientsDTO.ips())) {
            result = blocklistService.blockIpStrings(addBlockedClientsDTO.ips(),
                    addBlockedClientsDTO.blockTime());
        }
        if (!CollectionUtils.isEmpty(addBlockedClientsDTO.userIds())) {
            result = result.then(blocklistService.blockUserIds(addBlockedClientsDTO.userIds(),
                    addBlockedClientsDTO.blockTime()));
        }
        return ResponseFactory.okIfTruthy(result);
    }

    @GetMapping
    @RequiredPermission(CLIENT_BLOCKLIST_QUERY)
    public ResponseEntity<ResponseDTO<BlockedClientsDTO>> queryBlockedClients(
            @RequestParam(required = false) Set<String> ips,
            @RequestParam(required = false) Set<Long> userIds) {
        List<String> blockedIps = CollectionUtils.isEmpty(ips)
                ? Collections.emptyList()
                : blocklistService.getBlockedIpStrings(ips);
        List<Long> blockedUserIds = CollectionUtils.isEmpty(userIds)
                ? Collections.emptyList()
                : blocklistService.getBlockedUserIds(userIds);
        return ResponseFactory.okIfTruthy(new BlockedClientsDTO(blockedIps, blockedUserIds));
    }

    @DeleteMapping
    @RequiredPermission(CLIENT_BLOCKLIST_DELETE)
    public Mono<ResponseEntity<ResponseDTO<Void>>> deleteBlockedClients(
            @RequestParam(required = false) Set<String> ips,
            @RequestParam(required = false) Set<Long> userIds,
            @RequestParam(defaultValue = "false") Boolean deleteAll) {
        Mono<Void> result = Mono.empty();
        boolean isIpsEmpty = CollectionUtils.isEmpty(ips);
        if (!isIpsEmpty) {
            result = blocklistService.unblockIpStrings(ips);
        }
        boolean isUserIdsEmpty = CollectionUtils.isEmpty(userIds);
        if (!isUserIdsEmpty) {
            result = result
                    .then(blocklistService.unblockUserIds(userIds));
        }
        if (Boolean.TRUE.equals(deleteAll) && isIpsEmpty && isUserIdsEmpty) {
            result = Mono.when(blocklistService.unblockAllIps(), blocklistService.unblockAllUserIds());
        }
        return ResponseFactory.okIfTruthy(result);
    }

}
