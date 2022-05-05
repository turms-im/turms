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

package im.turms.service.domain.cluster.access.admin.controller;

import im.turms.server.common.access.admin.dto.response.HttpHandlerResult;
import im.turms.server.common.access.admin.dto.response.ResponseDTO;
import im.turms.server.common.access.admin.permission.RequiredPermission;
import im.turms.server.common.access.admin.web.annotation.GetMapping;
import im.turms.server.common.access.admin.web.annotation.PutMapping;
import im.turms.server.common.access.admin.web.annotation.RequestBody;
import im.turms.server.common.access.admin.web.annotation.RestController;
import im.turms.server.common.infra.property.TurmsProperties;
import im.turms.server.common.infra.property.TurmsPropertiesInspector;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.service.domain.common.access.admin.controller.BaseController;
import io.swagger.v3.oas.annotations.media.Schema;
import reactor.core.publisher.Mono;

import java.util.Map;

import static im.turms.server.common.access.admin.permission.AdminPermission.CLUSTER_SETTING_QUERY;
import static im.turms.server.common.access.admin.permission.AdminPermission.CLUSTER_SETTING_UPDATE;
import static im.turms.server.common.infra.property.TurmsPropertiesConvertor.mergeMetadataWithPropertyValue;
import static im.turms.server.common.infra.property.TurmsPropertiesInspector.getPropertyToValueMap;

/**
 * @author James Chen
 */
@RestController("cluster/settings")
public class SettingController extends BaseController {

    public SettingController(TurmsPropertiesManager propertiesManager) {
        super(propertiesManager);
    }

    @GetMapping
    @RequiredPermission(CLUSTER_SETTING_QUERY)
    public HttpHandlerResult<ResponseDTO<Map<String, Object>>> queryClusterSettings(
            boolean onlyMutable) {
        return HttpHandlerResult.okIfTruthy(getPropertyToValueMap(propertiesManager.getGlobalProperties(), onlyMutable));
    }

    /**
     * @implNote Do NOT declare turmsProperties as TurmsProperties because TurmsProperties has default values
     */
    @PutMapping
    @RequiredPermission(CLUSTER_SETTING_UPDATE)
    @Schema(implementation = TurmsProperties.class)
    public Mono<HttpHandlerResult<ResponseDTO<Void>>> updateClusterSettings(
            boolean reset,
            boolean updateGlobalSettings,
            @RequestBody(required = false) Map<String, Object> turmsProperties) {
        if (updateGlobalSettings) {
            Mono<Void> updatePropertiesMono = propertiesManager.updateGlobalProperties(reset, turmsProperties);
            return updatePropertiesMono.thenReturn(HttpHandlerResult.RESPONSE_OK);
        } else {
            propertiesManager.updateLocalProperties(reset, turmsProperties);
            return Mono.just(HttpHandlerResult.RESPONSE_OK);
        }
    }

    @GetMapping("metadata")
    @RequiredPermission(CLUSTER_SETTING_QUERY)
    public HttpHandlerResult<ResponseDTO<Map<String, Object>>> queryClusterConfigMetadata(
            boolean onlyMutable,
            boolean withValue) {
        Map<String, Object> metadata = onlyMutable
                ? TurmsPropertiesInspector.ONLY_MUTABLE_METADATA
                : TurmsPropertiesInspector.METADATA;
        if (withValue) {
            return HttpHandlerResult.okIfTruthy(mergeMetadataWithPropertyValue(metadata, propertiesManager.getGlobalProperties()));
        }
        return HttpHandlerResult.okIfTruthy(metadata);
    }

}