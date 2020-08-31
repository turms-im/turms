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

package im.turms.server.common.property.env.service.business.message;

import com.fasterxml.jackson.annotation.JsonView;
import im.turms.server.common.property.metadata.annotation.Description;
import im.turms.server.common.property.metadata.view.MutablePropertiesView;
import lombok.Data;

/**
 * @author James Chen
 */
@Data
public class ReadReceiptProperties {

    @JsonView(MutablePropertiesView.class)
    @Description("Whether to allow to update the read date of messages")
    private boolean enabled = true;

    @JsonView(MutablePropertiesView.class)
    @Description("Whether to allow to update the read status to \"unread\" from \"read\"")
    private boolean allowMarkingReadMessagesAsUnread = false;

    @JsonView(MutablePropertiesView.class)
    @Description("Whether to use server time to set the read date of messages")
    private boolean useServerTime = true;

}
