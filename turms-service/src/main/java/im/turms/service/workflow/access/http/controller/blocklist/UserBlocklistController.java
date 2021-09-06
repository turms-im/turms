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

package im.turms.service.workflow.access.http.controller.blocklist;

import im.turms.server.common.access.http.dto.response.PaginationDTO;
import im.turms.server.common.access.http.dto.response.ResponseDTO;
import im.turms.server.common.access.http.dto.response.ResponseFactory;
import im.turms.server.common.service.blocklist.BlocklistService;
import im.turms.service.workflow.access.http.dto.request.blocklist.AddBlockedUserIdsDTO;
import im.turms.service.workflow.access.http.permission.RequiredPermission;
import im.turms.service.workflow.access.http.util.PageUtil;
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

import java.util.Collection;
import java.util.List;
import java.util.Set;

import static im.turms.service.workflow.access.http.permission.AdminPermission.CLIENT_BLOCKLIST_CREATE;
import static im.turms.service.workflow.access.http.permission.AdminPermission.CLIENT_BLOCKLIST_DELETE;
import static im.turms.service.workflow.access.http.permission.AdminPermission.CLIENT_BLOCKLIST_QUERY;

/**
 * @author James Chen
 */
@RestController
@RequestMapping("/blocked-clients/users")
public class UserBlocklistController {

    private final BlocklistService blocklistService;
    private final PageUtil pageUtil;

    public UserBlocklistController(BlocklistService blocklistService, PageUtil pageUtil) {
        this.blocklistService = blocklistService;
        this.pageUtil = pageUtil;
    }

    @PostMapping
    @RequiredPermission(CLIENT_BLOCKLIST_CREATE)
    public Mono<ResponseEntity<ResponseDTO<Void>>> addBlockedUserIds(
            @RequestBody AddBlockedUserIdsDTO addBlockedUserIdsDTO) {
        Mono<Void> result = blocklistService.blockUserIds(addBlockedUserIdsDTO.ids(),
                addBlockedUserIdsDTO.blockTime());
        return ResponseFactory.okIfTruthy(result);
    }

    @GetMapping
    @RequiredPermission(CLIENT_BLOCKLIST_QUERY)
    public ResponseEntity<ResponseDTO<Collection<Long>>> queryBlockedUserIds(
            @RequestParam Set<Long> ids) {
        List<Long> blockedUserIds = blocklistService.getBlockedUserIds(ids);
        return ResponseFactory.okIfTruthy(blockedUserIds);
    }

    @GetMapping("/page")
    @RequiredPermission(CLIENT_BLOCKLIST_QUERY)
    public ResponseEntity<ResponseDTO<PaginationDTO<Long>>> queryBlockedUserIds(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(required = false) Integer size) {
        size = pageUtil.getSize(size);
        int blockUserCount = blocklistService.countBlockUsers();
        List<Long> blockedUserIds = blocklistService.getBlockedUserIds(page, size);
        return ResponseFactory.page(blockUserCount, blockedUserIds);
    }

    @DeleteMapping
    @RequiredPermission(CLIENT_BLOCKLIST_DELETE)
    public Mono<ResponseEntity<ResponseDTO<Void>>> deleteBlockedUserIds(
            @RequestParam(required = false) Set<Long> ids,
            @RequestParam(defaultValue = "false") Boolean deleteAll) {
        Mono<Void> result = Mono.empty();
        if (Boolean.TRUE.equals(deleteAll)) {
            result = blocklistService.unblockAllUserIds();
        } else if (!CollectionUtils.isEmpty(ids)) {
            result = result
                    .then(blocklistService.unblockUserIds(ids));
        }
        return ResponseFactory.okIfTruthy(result);
    }

}
