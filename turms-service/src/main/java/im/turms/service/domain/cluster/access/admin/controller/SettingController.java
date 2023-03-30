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

import java.util.Map;

import io.swagger.v3.oas.annotations.media.Schema;
import reactor.core.publisher.Mono;

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
import im.turms.service.domain.cluster.access.admin.dto.response.SettingsDTO;
import im.turms.service.domain.common.access.admin.controller.BaseController;

import static im.turms.server.common.access.admin.permission.AdminPermission.CLUSTER_SETTING_QUERY;
import static im.turms.server.common.access.admin.permission.AdminPermission.CLUSTER_SETTING_UPDATE;
import static im.turms.server.common.infra.property.TurmsPropertiesConvertor.mergeMetadataWithPropertyValue;
import static im.turms.server.common.infra.property.TurmsPropertiesInspector.convertPropertiesToValueMap;

/**
 * @author James Chen
 * @implNote These APIs should be designed to work for the cluster settings instead of the local
 *           node settings by default consistently because it is "cluster/settings".
 */
@RestController("cluster/settings")
public class SettingController extends BaseController {

    public SettingController(TurmsPropertiesManager propertiesManager) {
        super(propertiesManager);
    }

    @GetMapping
    @RequiredPermission(CLUSTER_SETTING_QUERY)
    public HttpHandlerResult<ResponseDTO<SettingsDTO>> queryClusterSettings(
            boolean queryLocalSettings,
            boolean onlyMutable) {
        TurmsProperties properties = queryLocalSettings
                ? propertiesManager.getLocalProperties()
                : propertiesManager.getGlobalProperties();
        return HttpHandlerResult.okIfTruthy(new SettingsDTO(
                TurmsProperties.SCHEMA_VERSION,
                convertPropertiesToValueMap(properties, onlyMutable)));
    }

    /**
     * @implNote Do NOT declare turmsProperties as TurmsProperties because TurmsProperties has
     *           default values
     */
    @PutMapping
    @RequiredPermission(CLUSTER_SETTING_UPDATE)
    @Schema(implementation = TurmsProperties.class)
    public Mono<HttpHandlerResult<ResponseDTO<Void>>> updateClusterSettings(
            boolean reset,
            boolean updateLocalSettings,
            @RequestBody(required = false) Map<String, Object> turmsProperties) {
        if (updateLocalSettings) {
            propertiesManager.updateLocalProperties(reset, turmsProperties);
            return Mono.just(HttpHandlerResult.RESPONSE_OK);
        } else {
            return propertiesManager.updateGlobalProperties(reset, turmsProperties)
                    .thenReturn(HttpHandlerResult.RESPONSE_OK);
        }
    }

    @GetMapping("metadata")
    @RequiredPermission(CLUSTER_SETTING_QUERY)
    public HttpHandlerResult<ResponseDTO<SettingsDTO>> queryClusterConfigMetadata(
            boolean queryLocalSettings,
            boolean onlyMutable,
            boolean withValue) {
        Map<String, Object> metadata = onlyMutable
                ? TurmsPropertiesInspector.ONLY_MUTABLE_METADATA
                : TurmsPropertiesInspector.METADATA;
        Map<String, Object> settings = withValue
                ? mergeMetadataWithPropertyValue(metadata,
                        queryLocalSettings
                                ? propertiesManager.getLocalProperties()
                                : propertiesManager.getGlobalProperties())
                : metadata;
        return HttpHandlerResult
                .okIfTruthy(new SettingsDTO(TurmsProperties.SCHEMA_VERSION, settings));
    }

}