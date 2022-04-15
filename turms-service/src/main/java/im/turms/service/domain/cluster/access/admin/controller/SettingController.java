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

import im.turms.server.common.access.admin.permission.RequiredPermission;
import im.turms.server.common.domain.common.dto.response.ResponseDTO;
import im.turms.server.common.domain.common.dto.response.ResponseFactory;
import im.turms.server.common.infra.cluster.node.Node;
import im.turms.server.common.infra.property.TurmsProperties;
import im.turms.server.common.infra.property.TurmsPropertiesInspector;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.service.domain.common.access.admin.controller.BaseController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;

import static im.turms.server.common.access.admin.permission.AdminPermission.CLUSTER_SETTING_QUERY;
import static im.turms.server.common.access.admin.permission.AdminPermission.CLUSTER_SETTING_UPDATE;
import static im.turms.server.common.infra.property.TurmsPropertiesConvertor.mergeMetadataWithPropertyValue;
import static im.turms.server.common.infra.property.TurmsPropertiesInspector.getPropertyToValueMap;

/**
 * @author James Chen
 */
@RestController
@RequestMapping("/cluster/settings")
public class SettingController extends BaseController {

    private final Node node;
    private final TurmsPropertiesManager turmsPropertiesManager;

    public SettingController(Node node, TurmsPropertiesManager turmsPropertiesManager) {
        super(node);
        this.node = node;
        this.turmsPropertiesManager = turmsPropertiesManager;
    }

    @GetMapping
    @RequiredPermission(CLUSTER_SETTING_QUERY)
    public ResponseEntity<ResponseDTO<Map<String, Object>>> queryClusterSettings(
            @RequestParam(defaultValue = "false") boolean onlyMutable) {
        return ResponseFactory.okIfTruthy(getPropertyToValueMap(node.getSharedProperties(), onlyMutable));
    }

    /**
     * @implNote Do NOT declare turmsProperties as TurmsProperties because TurmsProperties has default values
     */
    @PutMapping
    @RequiredPermission(CLUSTER_SETTING_UPDATE)
    @Operation(description = "Do not call this method frequently because it costs a lot of resources",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content =
            @Content(schema = @Schema(implementation = TurmsProperties.class))))
    public Mono<ResponseEntity<ResponseDTO<Void>>> updateClusterSettings(
            @RequestParam(defaultValue = "false") boolean reset,
            @RequestParam(defaultValue = "false") boolean updateGlobalSettings,
            @RequestBody(required = false) Map<String, Object> turmsProperties) {
        if (updateGlobalSettings) {
            Mono<Void> updatePropertiesMono = turmsPropertiesManager.updateGlobalProperties(reset, turmsProperties);
            return updatePropertiesMono.thenReturn(ResponseFactory.OK);
        } else {
            turmsPropertiesManager.updateLocalProperties(reset, turmsProperties);
            return Mono.just(ResponseFactory.OK);
        }
    }

    @GetMapping("/metadata")
    @RequiredPermission(CLUSTER_SETTING_QUERY)
    public ResponseEntity<ResponseDTO<Map<String, Object>>> queryClusterConfigMetadata(
            @RequestParam(defaultValue = "false") boolean onlyMutable,
            @RequestParam(defaultValue = "false") boolean withValue) {
        Map<String, Object> metadata = onlyMutable
                ? TurmsPropertiesInspector.ONLY_MUTABLE_METADATA
                : TurmsPropertiesInspector.METADATA;
        if (withValue) {
            return ResponseFactory.okIfTruthy(mergeMetadataWithPropertyValue(metadata, node.getSharedProperties()));
        }
        return ResponseFactory.okIfTruthy(metadata);
    }

}