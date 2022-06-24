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

package im.turms.server.common.domain.notification.service;

import im.turms.server.common.access.client.dto.constant.DeviceType;
import im.turms.server.common.infra.tracing.TracingContext;
import io.netty.buffer.ByteBuf;

import javax.annotation.Nullable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * @author James Chen
 */
public interface INotificationService {

    boolean sendNotificationToLocalClients(
            @NotNull TracingContext context,
            @NotNull ByteBuf notificationData,
            @NotEmpty Set<Long> recipientIds,
            @Nullable DeviceType excludedDeviceType);

}
