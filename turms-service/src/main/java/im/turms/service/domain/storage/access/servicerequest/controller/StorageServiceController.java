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

import im.turms.server.common.access.client.dto.ClientMessagePool;
import im.turms.server.common.access.client.dto.constant.StorageResourceType;
import im.turms.server.common.access.client.dto.request.storage.DeleteResourceRequest;
import im.turms.server.common.access.client.dto.request.storage.QueryResourceDownloadInfoRequest;
import im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequest;
import im.turms.service.access.servicerequest.dispatcher.ClientRequestHandler;
import im.turms.service.access.servicerequest.dispatcher.ServiceRequestMapping;
import im.turms.service.access.servicerequest.dto.RequestHandlerResultFactory;
import im.turms.service.domain.common.access.servicerequest.controller.BaseServiceController;
import im.turms.service.domain.storage.service.StorageService;
import im.turms.service.infra.proto.ProtoModelConvertor;
import org.springframework.stereotype.Controller;

import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.DELETE_RESOURCE_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.QUERY_RESOURCE_DOWNLOAD_INFO_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.QUERY_RESOURCE_UPLOAD_INFO_REQUEST;

/**
 * @author James Chen
 */
@Controller
public class StorageServiceController extends BaseServiceController {

    private final StorageService storageService;

    public StorageServiceController(StorageService storageService) {
        this.storageService = storageService;
    }

    @ServiceRequestMapping(QUERY_RESOURCE_DOWNLOAD_INFO_REQUEST)
    public ClientRequestHandler handleQueryResourceDownloadInfoRequest() {
        return clientRequest -> {
            QueryResourceDownloadInfoRequest queryResourceDownloadInfoRequest = clientRequest.turmsRequest().getQueryResourceDownloadInfoRequest();
            StorageResourceType resourceType = queryResourceDownloadInfoRequest.getType();
            String resourceKeyStr = queryResourceDownloadInfoRequest.hasKeyStr() ? queryResourceDownloadInfoRequest.getKeyStr() : null;
            Long resourceKeyNum = queryResourceDownloadInfoRequest.hasKeyNum() ? queryResourceDownloadInfoRequest.getKeyNum() : null;
            return storageService.queryResourceDownloadInfo(clientRequest.userId(), resourceType, resourceKeyStr, resourceKeyNum)
                    .map(info -> RequestHandlerResultFactory.get(ClientMessagePool
                            .getTurmsNotificationDataBuilder()
                            .setStringsWithVersion(ClientMessagePool.getStringsWithVersionBuilder()
                                    .addAllStrings(ProtoModelConvertor.toList(info))
                                    .build())
                            .build()));
        };
    }

    @ServiceRequestMapping(QUERY_RESOURCE_UPLOAD_INFO_REQUEST)
    public ClientRequestHandler handleQueryResourceUploadInfoRequest() {
        return clientRequest -> {
            QueryResourceUploadInfoRequest queryResourceUploadInfoRequest = clientRequest.turmsRequest().getQueryResourceUploadInfoRequest();
            StorageResourceType resourceType = queryResourceUploadInfoRequest.getType();
            String resourceKeyStr = queryResourceUploadInfoRequest.hasKeyStr() ? queryResourceUploadInfoRequest.getKeyStr() : null;
            Long resourceKeyNum = queryResourceUploadInfoRequest.hasKeyNum() ? queryResourceUploadInfoRequest.getKeyNum() : null;
            return storageService.queryResourceUploadInfo(clientRequest.userId(), resourceType, resourceKeyStr, resourceKeyNum)
                    .map(info -> RequestHandlerResultFactory.get(ClientMessagePool
                            .getTurmsNotificationDataBuilder()
                            .setStringsWithVersion(ClientMessagePool.getStringsWithVersionBuilder()
                                    .addAllStrings(ProtoModelConvertor.toList(info)))
                            .build()));
        };
    }

    @ServiceRequestMapping(DELETE_RESOURCE_REQUEST)
    public ClientRequestHandler handleDeleteResourceRequest() {
        return clientRequest -> {
            DeleteResourceRequest deleteResourceRequest = clientRequest.turmsRequest().getDeleteResourceRequest();
            StorageResourceType resourceType = deleteResourceRequest.getType();
            String resourceKeyStr = deleteResourceRequest.hasKeyStr() ? deleteResourceRequest.getKeyStr() : null;
            Long resourceKeyNum = deleteResourceRequest.hasKeyNum() ? deleteResourceRequest.getKeyNum() : null;
            return storageService.deleteResource(clientRequest.userId(), resourceType, resourceKeyStr, resourceKeyNum)
                    .thenReturn(RequestHandlerResultFactory.OK);
        };
    }

}