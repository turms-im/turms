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

package im.turms.server.common.property.env.common.cluster;

import im.turms.server.common.property.metadata.annotation.Description;
import lombok.Data;

import javax.validation.constraints.Min;

/**
 * @author James Chen
 */
@Data
public class RpcProperties {

    @Description("The timeout for RPC in milliseconds")
    @Min(1)
    private int timeoutInMillis = 15 * 1000;

    private int inputThreadNumber = Math.max(Runtime.getRuntime().availableProcessors() / 2, 1);

    private int outputThreadNumber = Math.max(Runtime.getRuntime().availableProcessors() / 2, 1);

}