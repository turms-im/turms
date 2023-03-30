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

package im.turms.server.common.infra.property.env.service.business.conversation;

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
public class ReadReceiptProperties {

    @Description("Whether to allow to update the last read date")
    @GlobalProperty
    @MutableProperty
    private boolean enabled = true;

    @Description("Whether to allow to move the last read date forward")
    @GlobalProperty
    @MutableProperty
    private boolean allowMoveReadDateForward;

    @Description("Whether to use the server time to set the last read date when updating")
    @GlobalProperty
    @MutableProperty
    private boolean useServerTime = true;

    @Description("Whether to update the read date when a user queries messages")
    @GlobalProperty
    @MutableProperty
    private boolean updateReadDateWhenUserQueryingMessage;

    @Description("Whether to update the read date after a user sent a message")
    @GlobalProperty
    @MutableProperty
    private boolean updateReadDateAfterMessageSent = true;

}