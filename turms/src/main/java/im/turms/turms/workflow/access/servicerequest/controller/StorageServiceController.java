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

package im.turms.turms.workflow.access.servicerequest.controller;

import com.google.protobuf.StringValue;
import im.turms.common.constant.ContentType;
import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.exception.TurmsBusinessException;
import im.turms.common.model.dto.notification.TurmsNotification;
import im.turms.common.model.dto.request.storage.DeleteResourceRequest;
import im.turms.common.model.dto.request.storage.QuerySignedGetUrlRequest;
import im.turms.common.model.dto.request.storage.QuerySignedPutUrlRequest;
import im.turms.turms.workflow.access.servicerequest.dispatcher.ClientRequestHandler;
import im.turms.turms.workflow.access.servicerequest.dispatcher.ServiceRequestMapping;
import im.turms.turms.workflow.access.servicerequest.dto.RequestHandlerResultFactory;
import im.turms.turms.workflow.service.impl.storage.StorageService;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import static im.turms.common.model.dto.request.TurmsRequest.KindCase.*;

/**
 * @author James Chen
 */
@Controller
public class StorageServiceController {

    private final StorageService storageService;

    public StorageServiceController(StorageService storageService) {
        this.storageService = storageService;
    }

    @ServiceRequestMapping(QUERY_SIGNED_GET_URL_REQUEST)
    public ClientRequestHandler handleQuerySignedGetUrlRequest() {
        return clientRequest -> {
            QuerySignedGetUrlRequest querySignedGetUrlRequest = clientRequest.getTurmsRequest().getQuerySignedGetUrlRequest();
            ContentType contentType = querySignedGetUrlRequest.getContentType();
            if (contentType != ContentType.UNRECOGNIZED) {
                String keyStr = querySignedGetUrlRequest.hasKeyStr() ? querySignedGetUrlRequest.getKeyStr().getValue() : null;
                Long keyNum = querySignedGetUrlRequest.hasKeyNum() ? querySignedGetUrlRequest.getKeyNum().getValue() : null;
                return storageService.queryPresignedGetUrl(clientRequest.getUserId(), contentType, keyStr, keyNum)
                        .map(url -> RequestHandlerResultFactory.get(TurmsNotification.Data.newBuilder()
                                .setUrl(StringValue.newBuilder().setValue(url).build())
                                .build()));
            } else {
                return Mono.error(TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENT, "The content type must not be UNRECOGNIZED"));
            }
        };
    }

    @ServiceRequestMapping(QUERY_SIGNED_PUT_URL_REQUEST)
    public ClientRequestHandler handleQuerySignedPutUrlRequest() {
        return clientRequest -> {
            QuerySignedPutUrlRequest querySignedPutUrlRequest = clientRequest.getTurmsRequest().getQuerySignedPutUrlRequest();
            ContentType contentType = querySignedPutUrlRequest.getContentType();
            if (contentType != ContentType.UNRECOGNIZED) {
                long contentLength = querySignedPutUrlRequest.getContentLength();
                String keyStr = querySignedPutUrlRequest.hasKeyStr() ? querySignedPutUrlRequest.getKeyStr().getValue() : null;
                Long keyNum = querySignedPutUrlRequest.hasKeyNum() ? querySignedPutUrlRequest.getKeyNum().getValue() : null;
                return storageService.queryPresignedPutUrl(clientRequest.getUserId(), contentType, keyStr, keyNum, contentLength)
                        .map(url -> RequestHandlerResultFactory.get(TurmsNotification.Data.newBuilder()
                                .setUrl(StringValue.newBuilder().setValue(url).build())
                                .build()));
            } else {
                return Mono.error(TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENT, "The content type must not be UNRECOGNIZED"));
            }
        };
    }

    @ServiceRequestMapping(DELETE_RESOURCE_REQUEST)
    public ClientRequestHandler handleDeleteResourceRequest() {
        return clientRequest -> {
            DeleteResourceRequest deleteResourceRequest = clientRequest.getTurmsRequest().getDeleteResourceRequest();
            ContentType contentType = deleteResourceRequest.getContentType();
            if (contentType != ContentType.UNRECOGNIZED) {
                String keyStr = deleteResourceRequest.hasKeyStr() ? deleteResourceRequest.getKeyStr().getValue() : null;
                Long keyNum = deleteResourceRequest.hasKeyNum() ? deleteResourceRequest.getKeyNum().getValue() : null;
                return storageService.deleteResource(clientRequest.getUserId(), contentType, keyStr, keyNum)
                        .thenReturn(RequestHandlerResultFactory.OK);
            } else {
                return Mono.error(TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENT, "The content type must not be UNRECOGNIZED"));
            }
        };
    }

}