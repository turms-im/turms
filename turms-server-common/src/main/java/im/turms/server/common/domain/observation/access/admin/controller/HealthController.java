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

package im.turms.server.common.domain.observation.access.admin.controller;

import im.turms.server.common.access.admin.dto.response.ResponseDTO;
import im.turms.server.common.access.admin.web.annotation.GetMapping;
import im.turms.server.common.access.admin.web.annotation.RestController;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.domain.observation.access.admin.dto.response.HealthDTO;

/**
 * @author James Chen
 */
@RestController("health")
public class HealthController {

    private static final ResponseDTO<HealthDTO> HEALTH_RESPONSE =
            new ResponseDTO<>(ResponseStatusCode.OK, new HealthDTO(HealthDTO.Status.UP));

    @GetMapping
    public ResponseDTO<HealthDTO> getHealthStatus() {
        return HEALTH_RESPONSE;
    }

}