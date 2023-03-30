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

package im.turms.server.common.infra.property.env.service.business.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import im.turms.server.common.infra.property.metadata.Description;
import im.turms.server.common.infra.property.metadata.GlobalProperty;

/**
 * @author James Chen
 */
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
public class SequenceIdProperties {

    @Description("Whether to use the sequence ID for group conversations so that the client can be aware of the loss of messages. "
            + "Note that the property has a significant impact on performance")
    @GlobalProperty
    boolean useSequenceIdForGroupConversation;

    @Description("Whether to use the sequence ID for private conversations so that the client can be aware of the loss of messages. "
            + "Note that the property has a significant impact on performance")
    @GlobalProperty
    boolean useSequenceIdForPrivateConversation;

}