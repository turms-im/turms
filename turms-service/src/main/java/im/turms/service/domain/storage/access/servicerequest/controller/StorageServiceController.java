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

package im.turms.service.domain.storage.access.servicerequest.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import im.turms.server.common.access.client.dto.ClientMessagePool;
import im.turms.server.common.access.client.dto.constant.StorageResourceType;
import im.turms.server.common.access.client.dto.request.storage.DeleteResourceRequest;
import im.turms.server.common.access.client.dto.request.storage.QueryMessageAttachmentInfosRequest;
import im.turms.server.common.access.client.dto.request.storage.QueryResourceDownloadInfoRequest;
import im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequest;
import im.turms.server.common.access.client.dto.request.storage.UpdateMessageAttachmentInfoRequest;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.time.DateRange;
import im.turms.service.access.servicerequest.dispatcher.ClientRequestHandler;
import im.turms.service.access.servicerequest.dispatcher.ServiceRequestMapping;
import im.turms.service.access.servicerequest.dto.RequestHandlerResultFactory;
import im.turms.service.domain.common.access.servicerequest.controller.BaseServiceController;
import im.turms.service.domain.storage.bo.StorageResourceInfo;
import im.turms.service.domain.storage.service.StorageService;
import im.turms.service.infra.proto.ProtoModelConvertor;

import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.DELETE_RESOURCE_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.QUERY_MESSAGE_ATTACHMENT_INFOS_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.QUERY_RESOURCE_DOWNLOAD_INFO_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.QUERY_RESOURCE_UPLOAD_INFO_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.UPDATE_MESSAGE_ATTACHMENT_INFO_REQUEST;

/**
 * @author James Chen
 */
@Controller
public class StorageServiceController extends BaseServiceController {

    private final StorageService storageService;

    public StorageServiceController(StorageService storageService) {
        this.storageService = storageService;
    }

    @ServiceRequestMapping(DELETE_RESOURCE_REQUEST)
    public ClientRequestHandler handleDeleteResourceRequest() {
        return clientRequest -> {
            DeleteResourceRequest request = clientRequest.turmsRequest()
                    .getDeleteResourceRequest();
            StorageResourceType resourceType = request.getType();
            Long resourceIdNum = request.hasIdNum()
                    ? request.getIdNum()
                    : null;
            String resourceIdStr = request.hasIdStr()
                    ? request.getIdStr()
                    : null;
            Map<String, String> extra = request.getExtraMap();
            return storageService
                    .deleteResource(clientRequest
                            .userId(), resourceType, resourceIdNum, resourceIdStr, extra)
                    .thenReturn(RequestHandlerResultFactory.OK);
        };
    }

    @ServiceRequestMapping(QUERY_RESOURCE_UPLOAD_INFO_REQUEST)
    public ClientRequestHandler handleQueryResourceUploadInfoRequest() {
        return clientRequest -> {
            QueryResourceUploadInfoRequest request = clientRequest.turmsRequest()
                    .getQueryResourceUploadInfoRequest();
            StorageResourceType resourceType = request.getType();
            Long resourceIdNum = request.hasIdNum()
                    ? request.getIdNum()
                    : null;
            // Reserved ID
            // String resourceIdStr = request.hasIdStr() ? request.getIdStr() : null;
            String name = request.hasName()
                    ? request.getName()
                    : null;
            String mediaType = request.hasMediaType()
                    ? request.getMediaType()
                    : null;
            Map<String, String> extra = request.getExtraMap();
            return storageService
                    .queryResourceUploadInfo(clientRequest
                            .userId(), resourceType, resourceIdNum, name, mediaType, extra)
                    .map(info -> RequestHandlerResultFactory.get(ClientMessagePool
                            .getTurmsNotificationDataBuilder()
                            .setStringsWithVersion(ClientMessagePool.getStringsWithVersionBuilder()
                                    .addAllStrings(ProtoModelConvertor.toList(info)))
                            .build()));
        };
    }

    @ServiceRequestMapping(QUERY_RESOURCE_DOWNLOAD_INFO_REQUEST)
    public ClientRequestHandler handleQueryResourceDownloadInfoRequest() {
        return clientRequest -> {
            QueryResourceDownloadInfoRequest request = clientRequest.turmsRequest()
                    .getQueryResourceDownloadInfoRequest();
            StorageResourceType resourceType = request.getType();
            Long resourceIdNum = request.hasIdNum()
                    ? request.getIdNum()
                    : null;
            String resourceIdStr = request.hasIdStr()
                    ? request.getIdStr()
                    : null;
            Map<String, String> extra = request.getExtraMap();
            return storageService
                    .queryResourceDownloadInfo(clientRequest
                            .userId(), resourceType, resourceIdNum, resourceIdStr, extra)
                    .map(info -> RequestHandlerResultFactory.get(ClientMessagePool
                            .getTurmsNotificationDataBuilder()
                            .setStringsWithVersion(ClientMessagePool.getStringsWithVersionBuilder()
                                    .addAllStrings(ProtoModelConvertor.toList(info))
                                    .build())
                            .build()));
        };
    }

    // Message-attachment-specific endpoints

    @ServiceRequestMapping(UPDATE_MESSAGE_ATTACHMENT_INFO_REQUEST)
    public ClientRequestHandler handleUpdateMessageAttachmentInfoRequest() {
        return clientRequest -> {
            UpdateMessageAttachmentInfoRequest request = clientRequest.turmsRequest()
                    .getUpdateMessageAttachmentInfoRequest();
            Long requesterId = clientRequest.userId();
            Long attachmentIdNum = request.getAttachmentIdNum();
            String attachmentIdStr = request.getAttachmentIdStr();
            Mono<Void> result;
            if (request.hasUserIdToShareWith()) {
                result = storageService.shareMessageAttachmentWithUser(requesterId,
                        attachmentIdNum,
                        attachmentIdStr,
                        request.getUserIdToShareWith());
            } else if (request.hasGroupIdToShareWith()) {
                result = storageService.shareMessageAttachmentWithGroup(requesterId,
                        attachmentIdNum,
                        attachmentIdStr,
                        request.getGroupIdToShareWith());
            } else if (request.hasUserIdToUnshareWith()) {
                result = storageService.unshareMessageAttachmentWithUser(requesterId,
                        attachmentIdNum,
                        attachmentIdStr,
                        request.getUserIdToUnshareWith());
            } else if (request.hasGroupIdToUnshareWith()) {
                result = storageService.unshareMessageAttachmentWithGroup(requesterId,
                        attachmentIdNum,
                        attachmentIdStr,
                        request.getGroupIdToUnshareWith());
            } else {
                result = Mono.empty();
            }
            return result.thenReturn(RequestHandlerResultFactory.OK);
        };
    }

    @ServiceRequestMapping(QUERY_MESSAGE_ATTACHMENT_INFOS_REQUEST)
    public ClientRequestHandler handleQueryMessageAttachmentInfosRequest() {
        return clientRequest -> {
            QueryMessageAttachmentInfosRequest request = clientRequest.turmsRequest()
                    .getQueryMessageAttachmentInfosRequest();
            Long requesterId = clientRequest.userId();
            Mono<List<StorageResourceInfo>> result;
            Date creationDateStart = request.hasCreationDateStart()
                    ? new Date(request.getCreationDateStart())
                    : null;
            Date creationDateEnd = request.hasCreationDateEnd()
                    ? new Date(request.getCreationDateEnd())
                    : null;
            DateRange creationDateRange = DateRange.of(creationDateStart, creationDateEnd);
            if (request.hasInPrivateConversation()) {
                if (request.getInPrivateConversation()) {
                    Boolean areSharedByMe = request.hasAreSharedByMe()
                            ? request.getAreSharedByMe()
                            : null;
                    result = storageService.queryMessageAttachmentInfosInPrivateConversations(
                            requesterId,
                            null,
                            creationDateRange,
                            areSharedByMe);
                } else {
                    Set<Long> userIds = request.getUserIdsCount() > 0
                            ? CollectionUtil.newSet(request.getUserIdsList())
                            : null;
                    result = storageService.queryMessageAttachmentInfosInGroupConversations(
                            requesterId,
                            null,
                            userIds,
                            creationDateRange);
                }
            } else {
                boolean hasUserIds = request.getUserIdsCount() > 0;
                Set<Long> userIds = hasUserIds
                        ? CollectionUtil.newSet(request.getUserIdsList())
                        : null;
                if (request.getGroupIdsCount() > 0) {
                    Set<Long> groupIds = CollectionUtil.newSet(request.getGroupIdsList());
                    result = storageService.queryMessageAttachmentInfosInGroupConversations(
                            requesterId,
                            groupIds,
                            userIds,
                            creationDateRange);
                } else if (hasUserIds) {
                    Boolean areSharedByMe = request.hasAreSharedByMe()
                            ? request.getAreSharedByMe()
                            : null;
                    result = storageService.queryMessageAttachmentInfosInPrivateConversations(
                            requesterId,
                            userIds,
                            creationDateRange,
                            areSharedByMe);
                } else {
                    result = storageService.queryMessageAttachmentInfosUploadedByRequester(
                            requesterId,
                            creationDateRange);
                }
            }
            return result.map(infos -> RequestHandlerResultFactory.get(ClientMessagePool
                    .getTurmsNotificationDataBuilder()
                    .setStorageResourceInfos(ClientMessagePool.getStorageResourceInfosBuilder()
                            .addAllInfos(CollectionUtil.transformAsList(infos,
                                    ProtoModelConvertor::storageResourceInfo2proto))
                            .build())
                    .build()));
        };
    }

}