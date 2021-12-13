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

package im.turms.service.workflow.access.http.controller.cluster;

import im.turms.server.common.access.http.dto.response.ResponseDTO;
import im.turms.server.common.access.http.dto.response.ResponseFactory;
import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.exception.TurmsBusinessException;
import im.turms.server.common.property.PropertiesUtil;
import im.turms.server.common.property.TurmsProperties;
import im.turms.server.common.property.TurmsPropertiesManager;
import im.turms.service.workflow.access.http.permission.RequiredPermission;
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

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

import static im.turms.service.workflow.access.http.permission.AdminPermission.CLUSTER_SETTING_QUERY;
import static im.turms.service.workflow.access.http.permission.AdminPermission.CLUSTER_SETTING_UPDATE;

/**
 * @author James Chen
 */
@RestController
@RequestMapping("/cluster/settings")
public class SettingController {

    private final Node node;
    private final TurmsPropertiesManager turmsPropertiesManager;

    public SettingController(Node node, TurmsPropertiesManager turmsPropertiesManager) {
        this.node = node;
        this.turmsPropertiesManager = turmsPropertiesManager;
    }

    @GetMapping
    @RequiredPermission(CLUSTER_SETTING_QUERY)
    public ResponseEntity<ResponseDTO<Map<String, Object>>> queryClusterSettings(@RequestParam(defaultValue = "false") Boolean onlyMutable)
            throws IOException {
        return ResponseFactory.okIfTruthy(PropertiesUtil.getPropertyValueMap(node.getSharedProperties(), onlyMutable));
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
            @RequestParam(defaultValue = "false") Boolean reset,
            @RequestParam(defaultValue = "false") Boolean updateGlobalSettings,
            @RequestBody(required = false) Map<String, Object> turmsProperties) throws IOException {
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
            @RequestParam(defaultValue = "false") Boolean onlyMutable,
            @RequestParam(defaultValue = "false") Boolean withValue) {
        Map<String, Object> metadata = onlyMutable ? PropertiesUtil.ONLY_MUTABLE_METADATA : PropertiesUtil.METADATA;
        if (withValue) {
            try {
                Map<String, Object> propertyValueMap = PropertiesUtil.getPropertyValueMap(node.getSharedProperties(), onlyMutable)
                        .entrySet()
                        .stream()
                        .filter(entry -> metadata.containsKey(entry.getKey()))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                Map<String, Object> propertiesWithMetadata = PropertiesUtil.mergePropertiesWithMetadata(propertyValueMap, metadata);
                return ResponseFactory.okIfTruthy(propertiesWithMetadata);
            } catch (IOException e) {
                throw TurmsBusinessException.get(TurmsStatusCode.SERVER_INTERNAL_ERROR, e);
            }
        } else {
            return ResponseFactory.okIfTruthy(metadata);
        }
    }

}