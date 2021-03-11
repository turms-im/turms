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

package im.turms.server.common.property.env.service.business.conversation;

import com.fasterxml.jackson.annotation.JsonView;
import im.turms.server.common.property.metadata.annotation.Description;
import im.turms.server.common.property.metadata.annotation.GlobalProperty;
import im.turms.server.common.property.metadata.view.MutablePropertiesView;
import lombok.Data;

/**
 * @author James Chen
 */
@Data
public class ReadReceiptProperties {

    @Description("Whether to allow to update the last read date")
    @GlobalProperty
    @JsonView(MutablePropertiesView.class)
    private boolean enabled = true;

    @Description("Whether to allow to move the last read date forward")
    @GlobalProperty
    @JsonView(MutablePropertiesView.class)
    private boolean allowMoveReadDateForward;

    @Description("Whether to use the server time to set the last read date when updating")
    @GlobalProperty
    @JsonView(MutablePropertiesView.class)
    private boolean useServerTime = true;

    @JsonView(MutablePropertiesView.class)
    @Description("Whether to update the read date when a user queries messages")
    private boolean updateReadDateWhenUserQueryingMessage;

    @Description("Whether to update the read date after a user sent a message")
    @GlobalProperty
    @JsonView(MutablePropertiesView.class)
    private boolean updateReadDateAfterMessageSent = true;

}