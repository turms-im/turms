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

package im.turms.server.common.property.env.service.business.user;

import com.fasterxml.jackson.annotation.JsonView;
import im.turms.server.common.property.metadata.annotation.Description;
import im.turms.server.common.property.metadata.view.MutablePropertiesView;
import lombok.Data;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author James Chen
 */
@Data
public class UserProperties {

    @JsonView(MutablePropertiesView.class)
    @NestedConfigurationProperty
    private FriendRequestProperties friendRequest = new FriendRequestProperties();

    @JsonView(MutablePropertiesView.class)
    @Description("Whether to respond to client with the OFFLINE status if a user is in INVISIBLE status")
    private boolean respondOfflineIfInvisible = false;

    @JsonView(MutablePropertiesView.class)
    @Description("Whether to delete the two-sided relationships when a user requests to delete a relationship")
    private boolean deleteTwoSidedRelationships = false;

    @JsonView(MutablePropertiesView.class)
    @Description("Whether to delete a user logically")
    private boolean deleteUserLogically = true;

    @Description("Whether to activate a user when added by default")
    private boolean activateUserWhenAdded = true;

}