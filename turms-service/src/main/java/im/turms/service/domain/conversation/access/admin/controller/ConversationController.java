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

package im.turms.service.domain.conversation.access.admin.controller;

import java.util.List;
import java.util.Set;

import com.mongodb.client.result.DeleteResult;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import im.turms.server.common.access.admin.dto.response.DeleteResultDTO;
import im.turms.server.common.access.admin.dto.response.HttpHandlerResult;
import im.turms.server.common.access.admin.dto.response.ResponseDTO;
import im.turms.server.common.access.admin.permission.AdminPermission;
import im.turms.server.common.access.admin.permission.RequiredPermission;
import im.turms.server.common.access.admin.web.annotation.DeleteMapping;
import im.turms.server.common.access.admin.web.annotation.GetMapping;
import im.turms.server.common.access.admin.web.annotation.PutMapping;
import im.turms.server.common.access.admin.web.annotation.QueryParam;
import im.turms.server.common.access.admin.web.annotation.RequestBody;
import im.turms.server.common.access.admin.web.annotation.RestController;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.collection.CollectorUtil;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.reactor.PublisherPool;
import im.turms.server.common.storage.mongo.operation.OperationResultConvertor;
import im.turms.service.domain.common.access.admin.controller.BaseController;
import im.turms.service.domain.conversation.access.admin.dto.request.UpdateConversationDTO;
import im.turms.service.domain.conversation.access.admin.dto.response.ConversationsDTO;
import im.turms.service.domain.conversation.po.GroupConversation;
import im.turms.service.domain.conversation.po.PrivateConversation;
import im.turms.service.domain.conversation.service.ConversationService;
import im.turms.service.storage.mongo.OperationResultPublisherPool;

/**
 * @author James Chen
 */
@RestController("conversations")
public class ConversationController extends BaseController {

    private final ConversationService conversationService;

    public ConversationController(
            TurmsPropertiesManager propertiesManager,
            ConversationService conversationService) {
        super(propertiesManager);
        this.conversationService = conversationService;
    }

    @GetMapping
    @RequiredPermission(AdminPermission.CONVERSATION_QUERY)
    public Mono<HttpHandlerResult<ResponseDTO<ConversationsDTO>>> queryConversations(
            @QueryParam(required = false) List<PrivateConversation.Key> privateConversationKeys,
            @QueryParam(required = false) Set<Long> ownerIds,
            @QueryParam(required = false) Set<Long> groupIds) {
        Flux<PrivateConversation> privateConversationsFlux;
        int privateConversationsSize = 0;
        if (CollectionUtil.isEmpty(privateConversationKeys)) {
            privateConversationsFlux = Flux.empty();
        } else {
            privateConversationsFlux = conversationService
                    .queryPrivateConversations(CollectionUtil.newSet(privateConversationKeys));
            privateConversationsSize += privateConversationKeys.size();
        }
        if (ownerIds != null && !ownerIds.isEmpty()) {
            privateConversationsSize += ownerIds.size();
            privateConversationsFlux = privateConversationsFlux
                    .concatWith(conversationService.queryPrivateConversationsByOwnerIds(ownerIds));
        }
        Mono<List<PrivateConversation>> privateConversations =
                privateConversationsFlux.collect(CollectorUtil.toList(privateConversationsSize));
        Mono<List<GroupConversation>> groupConversations = groupIds == null || groupIds.isEmpty()
                ? PublisherPool.emptyList()
                : conversationService.queryGroupConversations(groupIds)
                        .collect(CollectorUtil.toList(groupIds.size()));
        Mono<ConversationsDTO> conversationsMono = privateConversations.zipWith(groupConversations)
                .map(tuple -> new ConversationsDTO(tuple.getT1(), tuple.getT2()));
        return HttpHandlerResult.okIfTruthy(conversationsMono);
    }

    @DeleteMapping
    @RequiredPermission(AdminPermission.CONVERSATION_DELETE)
    public Mono<HttpHandlerResult<ResponseDTO<DeleteResultDTO>>> deleteConversations(
            @QueryParam(required = false) List<PrivateConversation.Key> privateConversationKeys,
            @QueryParam(required = false) Set<Long> ownerIds,
            @QueryParam(required = false) Set<Long> groupIds) {
        Mono<DeleteResult> resultMono = CollectionUtil.isEmpty(privateConversationKeys)
                ? OperationResultPublisherPool.ACKNOWLEDGED_DELETE_RESULT
                : conversationService
                        .deletePrivateConversations(CollectionUtil.newSet(privateConversationKeys));
        if (!CollectionUtils.isEmpty(ownerIds)) {
            resultMono = resultMono
                    .zipWith(conversationService.deletePrivateConversations(ownerIds, null))
                    .map(tuple -> OperationResultConvertor.merge(tuple.getT1(), tuple.getT2()));
        }
        if (!CollectionUtils.isEmpty(groupIds)) {
            resultMono = resultMono
                    .zipWith(conversationService.deleteGroupConversations(groupIds, null))
                    .map(tuple -> OperationResultConvertor.merge(tuple.getT1(), tuple.getT2()));
        }
        return HttpHandlerResult.deleteResult(resultMono);
    }

    @PutMapping
    @RequiredPermission(AdminPermission.CONVERSATION_UPDATE)
    public Mono<HttpHandlerResult<ResponseDTO<Void>>> updateConversations(
            @QueryParam(required = false) List<PrivateConversation.Key> privateConversationKeys,
            @QueryParam(
                    required = false) List<GroupConversation.GroupConversionMemberKey> groupConversationMemberKeys,
            @RequestBody UpdateConversationDTO updateConversationDTO) {
        Mono<Void> updatePrivateConversions = CollectionUtil.isEmpty(privateConversationKeys)
                ? Mono.empty()
                : conversationService.upsertPrivateConversationsReadDate(
                        CollectionUtil.newSet(privateConversationKeys),
                        updateConversationDTO.readDate());
        Mono<Void> updateGroupConversationsMono =
                CollectionUtil.isEmpty(groupConversationMemberKeys)
                        ? Mono.empty()
                        : conversationService.upsertGroupConversationsReadDate(
                                CollectionUtil.newSet(groupConversationMemberKeys),
                                updateConversationDTO.readDate());
        return Mono.whenDelayError(updatePrivateConversions, updateGroupConversationsMono)
                .thenReturn(HttpHandlerResult.RESPONSE_OK);
    }

}