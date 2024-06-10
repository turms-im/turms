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

package im.turms.server.common.domain.debug.access.admin.controller;

import reactor.core.publisher.Mono;

import im.turms.server.common.access.admin.dto.response.HttpHandlerResult;
import im.turms.server.common.access.admin.dto.response.ResponseDTO;
import im.turms.server.common.access.admin.web.annotation.PostMapping;
import im.turms.server.common.access.admin.web.annotation.RequestBody;
import im.turms.server.common.access.admin.web.annotation.RestController;
import im.turms.server.common.domain.debug.access.admin.dto.request.CreateMethodCallDTO;
import im.turms.server.common.domain.debug.access.admin.service.DebugService;

/**
 * @author James Chen
 */
@RestController("debug")
public class DebugController {

    private final DebugService debugService;

    public DebugController(DebugService debugService) {
        this.debugService = debugService;
    }

    @PostMapping("method-calls")
    public Mono<HttpHandlerResult<ResponseDTO<Object>>> callMethod(
            @RequestBody CreateMethodCallDTO request) {
        Mono<Object> returnValue = debugService.callMethod(request.beanName(),
                request.className(),
                request.methodName(),
                request.params());
        return HttpHandlerResult.okIfTruthy(returnValue);
    }

}