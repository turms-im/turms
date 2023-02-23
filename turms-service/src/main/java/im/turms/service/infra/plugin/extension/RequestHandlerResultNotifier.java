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

package im.turms.service.infra.plugin.extension;

import im.turms.server.common.access.client.dto.constant.DeviceType;
import im.turms.server.common.infra.plugin.ExtensionPoint;
import im.turms.service.access.servicerequest.dto.RequestHandlerResult;
import reactor.core.publisher.Mono;

import jakarta.validation.constraints.NotNull;

/**
 * @author James Chen
 */
public interface RequestHandlerResultNotifier extends ExtensionPoint {

    Mono<RequestHandlerResult> notify(@NotNull RequestHandlerResult result,
                                      @NotNull Long requesterId,
                                      @NotNull DeviceType requesterDevice);

}