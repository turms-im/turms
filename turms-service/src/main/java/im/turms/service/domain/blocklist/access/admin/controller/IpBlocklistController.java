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
import im.turms.server.common.infra.lang.ByteArrayWrapper;
import im.turms.server.common.infra.net.InetAddressUtil;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.service.domain.blocklist.access.admin.dto.request.AddBlockedIpsDTO;
import im.turms.service.domain.blocklist.access.admin.dto.response.BlockedIpDTO;
import im.turms.service.domain.common.access.admin.controller.BaseController;

import static im.turms.server.common.access.admin.permission.AdminPermission.CLIENT_BLOCKLIST_CREATE;
import static im.turms.server.common.access.admin.permission.AdminPermission.CLIENT_BLOCKLIST_DELETE;
import static im.turms.server.common.access.admin.permission.AdminPermission.CLIENT_BLOCKLIST_QUERY;

/**
 * @author James Chen
 */
@RestController("blocked-clients/ips")
public class IpBlocklistController extends BaseController {

    private final BlocklistService blocklistService;

    public IpBlocklistController(
            TurmsPropertiesManager propertiesManager,
            BlocklistService blocklistService) {
        super(propertiesManager);
        this.blocklistService = blocklistService;
    }

    @PostMapping
    @RequiredPermission(CLIENT_BLOCKLIST_CREATE)
    public Mono<HttpHandlerResult<ResponseDTO<Void>>> addBlockedIps(
            @RequestBody AddBlockedIpsDTO addBlockedIpsDTO) {
        Mono<Void> result = blocklistService.blockIpStrings(addBlockedIpsDTO.ids(),
                addBlockedIpsDTO.blockDurationSeconds());
        return HttpHandlerResult.okIfTruthy(result);
    }

    @GetMapping
    @RequiredPermission(CLIENT_BLOCKLIST_QUERY)
    public HttpHandlerResult<ResponseDTO<Collection<BlockedIpDTO>>> queryBlockedIps(
            Set<String> ids) {
        List<BlockedClient<ByteArrayWrapper>> blockedIps =
                blocklistService.getBlockedIpStrings(ids);
        return HttpHandlerResult.okIfTruthy(clients2ips(blockedIps));
    }

    @GetMapping("page")
    @RequiredPermission(CLIENT_BLOCKLIST_QUERY)
    public HttpHandlerResult<ResponseDTO<PaginationDTO<BlockedIpDTO>>> queryBlockedIps(
            int page,
            @QueryParam(required = false) Integer size) {
        size = getPageSize(size);
        int blockedIpCount = blocklistService.countBlockIps();
        List<BlockedClient<ByteArrayWrapper>> blockedIps =
                blocklistService.getBlockedIps(page, size);
        return HttpHandlerResult.page(blockedIpCount, clients2ips(blockedIps));
    }

    @DeleteMapping
    @RequiredPermission(CLIENT_BLOCKLIST_DELETE)
    public Mono<HttpHandlerResult<ResponseDTO<Void>>> deleteBlockedIps(
            @QueryParam(required = false) Set<String> ids,
            boolean deleteAll) {
        Mono<Void> result = Mono.empty();
        if (deleteAll) {
            result = blocklistService.unblockAllIps();
        } else if (!CollectionUtils.isEmpty(ids)) {
            result = result.then(blocklistService.unblockIpStrings(ids));
        }
        return HttpHandlerResult.okIfTruthy(result);
    }

    private List<BlockedIpDTO> clients2ips(
            Collection<BlockedClient<ByteArrayWrapper>> blockedClients) {
        List<BlockedIpDTO> items = new ArrayList<>(blockedClients.size());
        for (BlockedClient<ByteArrayWrapper> blockedClient : blockedClients) {
            items.add(new BlockedIpDTO(
                    InetAddressUtil.ipBytesToString(blockedClient.id()
                            .getBytes()),
                    new Date(blockedClient.blockEndTimeMillis())));
        }
        return items;
    }

}