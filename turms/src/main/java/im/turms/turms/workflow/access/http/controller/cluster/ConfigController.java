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

package im.turms.turms.workflow.access.http.controller.cluster;

import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.exception.TurmsBusinessException;
import im.turms.server.common.property.TurmsProperties;
import im.turms.server.common.property.TurmsPropertiesManager;
import im.turms.server.common.util.PropertiesUtil;
import im.turms.turms.workflow.access.http.dto.response.ResponseDTO;
import im.turms.turms.workflow.access.http.dto.response.ResponseFactory;
import im.turms.turms.workflow.access.http.permission.RequiredPermission;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

import static im.turms.turms.workflow.access.http.permission.AdminPermission.CLUSTER_CONFIG_UPDATE;
import static im.turms.turms.workflow.access.http.permission.AdminPermission.CLUSTER_INFO_QUERY;

/**
 * @author James Chen
 */
@Log4j2
@RestController
@RequestMapping("/cluster/config")
public class ConfigController {

    private final Node node;
    private final TurmsPropertiesManager turmsPropertiesManager;

    public ConfigController(Node node, TurmsPropertiesManager turmsPropertiesManager) {
        this.node = node;
        this.turmsPropertiesManager = turmsPropertiesManager;
    }

    @GetMapping
    @RequiredPermission(CLUSTER_INFO_QUERY)
    public ResponseEntity<ResponseDTO<Map<String, Object>>> queryClusterConfig(@RequestParam(defaultValue = "false") Boolean onlyMutable) {
        try {
            return ResponseFactory.okIfTruthy(PropertiesUtil.getPropertyValueMap(node.getSharedProperties(), onlyMutable));
        } catch (IOException e) {
            throw TurmsBusinessException.get(TurmsStatusCode.SERVER_INTERNAL_ERROR);
        }
    }

    /**
     * @implNote Do NOT declare turmsProperties as TurmsProperties because TurmsProperties has default values
     */
    @PutMapping
    @RequiredPermission(CLUSTER_CONFIG_UPDATE)
    @Operation(description = "Do not call this method frequently because it costs a lot of resources",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(schema = @Schema(implementation = TurmsProperties.class))))
    public Mono<ResponseEntity<ResponseDTO<Void>>> updateClusterConfig(
            @RequestParam(defaultValue = "false") Boolean reset,
            @RequestParam(defaultValue = "false") Boolean updateGlobalProperties,
            @RequestBody(required = false) Map<String, Object> turmsProperties) throws IOException {
        if (updateGlobalProperties) {
            Mono<Void> updatePropertiesMono = turmsPropertiesManager.updateGlobalConfig(reset, turmsProperties);
            return updatePropertiesMono.thenReturn(ResponseFactory.OK);
        } else {
            turmsPropertiesManager.updateLocalConfig(reset, turmsProperties);
            return Mono.just(ResponseFactory.OK);
        }
    }

    @GetMapping("/metadata")
    @RequiredPermission(CLUSTER_INFO_QUERY)
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