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

package im.turms.server.common.infra.property.env.service.business.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

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
public class UserProperties {

    @NestedConfigurationProperty
    protected FriendRequestProperties friendRequest = new FriendRequestProperties();

    @Description("Whether to respond to client with the OFFLINE status if a user is in INVISIBLE status")
    @GlobalProperty
    @MutableProperty
    protected boolean respondOfflineIfInvisible;

    @Description("Whether to delete the two-sided relationships when a user requests to delete a relationship")
    @GlobalProperty
    @MutableProperty
    protected boolean deleteTwoSidedRelationships;

    @Description("Whether to delete a user logically")
    @GlobalProperty
    @MutableProperty
    protected boolean deleteUserLogically = true;

    @Description("Whether to activate a user when added by default")
    @GlobalProperty
    @MutableProperty
    protected boolean activateUserWhenAdded = true;

    @NestedConfigurationProperty
    protected UserInfoProperties info = new UserInfoProperties();

    @NestedConfigurationProperty
    protected UserSettingsProperties settings = new UserSettingsProperties();

}