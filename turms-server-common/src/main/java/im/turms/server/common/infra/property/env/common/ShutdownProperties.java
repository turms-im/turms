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

package im.turms.server.common.infra.property.env.common;

import jakarta.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import im.turms.server.common.infra.property.metadata.Description;

/**
 * @author James Chen
 */
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
public class ShutdownProperties {

    @Description("The graceful shutdown timeout in milliseconds. After the timeout, the job will try to shut down gracefully in a more aggressive way "
            + "before being forced to be shutdown when reaching \"jobForcedTimeoutMillis\"")
    @Min(0)
    protected long jobGracefulTimeoutMillis = 60 * 1000;

    @Description("The forced shutdown timeout in milliseconds. After the timeout, the job will be forced to be shutdown immediately no matter "
            + "whether it is running or not. If this value is equal to or less than \"jobGracefulTimeoutMillis\", the job will be forced to be shutdown when "
            + "reaching \"jobGracefulTimeoutMillis\"")
    @Min(0)
    protected long jobForcedTimeoutMillis = 90 * 1000;

}