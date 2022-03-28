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

import im.turms.server.common.access.client.dto.constant.ContentType;
import im.turms.server.common.access.client.dto.notification.TurmsNotification;
import im.turms.server.common.access.client.dto.request.storage.DeleteResourceRequest;
import im.turms.server.common.access.client.dto.request.storage.QuerySignedGetUrlRequest;
import im.turms.server.common.access.client.dto.request.storage.QuerySignedPutUrlRequest;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.infra.cluster.node.Node;
import im.turms.server.common.infra.exception.ResponseException;
import im.turms.service.access.servicerequest.dispatcher.ClientRequestHandler;
import im.turms.service.access.servicerequest.dispatcher.ServiceRequestMapping;
import im.turms.service.access.servicerequest.dto.RequestHandlerResultFactory;
import im.turms.service.domain.common.access.admin.controller.BaseController;
import im.turms.service.domain.storage.service.StorageService;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.DELETE_RESOURCE_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.QUERY_SIGNED_GET_URL_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.QUERY_SIGNED_PUT_URL_REQUEST;

/**
 * @author James Chen
 */
@Controller
public class StorageServiceController extends BaseController {

    private final StorageService storageService;

    public StorageServiceController(Node node, StorageService storageService) {
        super(node);
        this.storageService = storageService;
    }

    @ServiceRequestMapping(QUERY_SIGNED_GET_URL_REQUEST)
    public ClientRequestHandler handleQuerySignedGetUrlRequest() {
        return clientRequest -> {
            QuerySignedGetUrlRequest querySignedGetUrlRequest = clientRequest.turmsRequest().getQuerySignedGetUrlRequest();
            ContentType contentType = querySignedGetUrlRequest.getContentType();
            if (contentType == ContentType.UNRECOGNIZED) {
                return Mono
                        .error(ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT, "The content type must not be UNRECOGNIZED"));
            }
            String keyStr = querySignedGetUrlRequest.hasKeyStr() ? querySignedGetUrlRequest.getKeyStr() : null;
            Long keyNum = querySignedGetUrlRequest.hasKeyNum() ? querySignedGetUrlRequest.getKeyNum() : null;
            return storageService.queryPresignedGetUrl(clientRequest.userId(), contentType, keyStr, keyNum)
                    .map(url -> RequestHandlerResultFactory.get(TurmsNotification.Data.newBuilder()
                            .setUrl(url)
                            .build()));
        };
    }

    @ServiceRequestMapping(QUERY_SIGNED_PUT_URL_REQUEST)
    public ClientRequestHandler handleQuerySignedPutUrlRequest() {
        return clientRequest -> {
            QuerySignedPutUrlRequest querySignedPutUrlRequest = clientRequest.turmsRequest().getQuerySignedPutUrlRequest();
            ContentType contentType = querySignedPutUrlRequest.getContentType();
            if (contentType == ContentType.UNRECOGNIZED) {
                return Mono
                        .error(ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT, "The content type must not be UNRECOGNIZED"));
            }
            long contentLength = querySignedPutUrlRequest.getContentLength();
            String keyStr = querySignedPutUrlRequest.hasKeyStr() ? querySignedPutUrlRequest.getKeyStr() : null;
            Long keyNum = querySignedPutUrlRequest.hasKeyNum() ? querySignedPutUrlRequest.getKeyNum() : null;
            return storageService.queryPresignedPutUrl(clientRequest.userId(), contentType, keyStr, keyNum, contentLength)
                    .map(url -> RequestHandlerResultFactory.get(TurmsNotification.Data.newBuilder()
                            .setUrl(url)
                            .build()));
        };
    }

    @ServiceRequestMapping(DELETE_RESOURCE_REQUEST)
    public ClientRequestHandler handleDeleteResourceRequest() {
        return clientRequest -> {
            DeleteResourceRequest deleteResourceRequest = clientRequest.turmsRequest().getDeleteResourceRequest();
            ContentType contentType = deleteResourceRequest.getContentType();
            if (contentType == ContentType.UNRECOGNIZED) {
                return Mono
                        .error(ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT, "The content type must not be UNRECOGNIZED"));
            }
            String keyStr = deleteResourceRequest.hasKeyStr() ? deleteResourceRequest.getKeyStr() : null;
            Long keyNum = deleteResourceRequest.hasKeyNum() ? deleteResourceRequest.getKeyNum() : null;
            return storageService.deleteResource(clientRequest.userId(), contentType, keyStr, keyNum)
                    .thenReturn(RequestHandlerResultFactory.OK);
        };
    }

}