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

package im.turms.server.common.infra.property.env.gateway.session;

import java.util.Collections;
import java.util.List;
import jakarta.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import im.turms.server.common.infra.property.metadata.Description;
import im.turms.server.common.infra.property.metadata.GlobalProperty;
import im.turms.server.common.infra.property.metadata.MutableProperty;

/**
 * @author James Chen
 */
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
public class DeviceDetailsProperties {

    @Description("Device details information will expire after the specified time has elapsed. 0 means never expire")
    // TODO: Support @GlobalProperty
    @Min(0)
    private int expireAfterSeconds = 30 * 24 * 3600;

    @GlobalProperty
    @MutableProperty
    private List<DeviceDetailsItemProperties> items = Collections.emptyList();

}