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

package im.turms.server.common.property.env.service;

import com.fasterxml.jackson.annotation.JsonView;
import im.turms.server.common.property.env.service.business.GroupProperties;
import im.turms.server.common.property.env.service.business.NotificationProperties;
import im.turms.server.common.property.env.service.business.StorageProperties;
import im.turms.server.common.property.env.service.business.activity.ActivityLoggingProperties;
import im.turms.server.common.property.env.service.business.conversation.ConversationProperties;
import im.turms.server.common.property.env.service.business.message.MessageProperties;
import im.turms.server.common.property.env.service.business.user.UserProperties;
import im.turms.server.common.property.env.service.env.FakeProperties;
import im.turms.server.common.property.env.service.env.adminapi.AdminApiProperties;
import im.turms.server.common.property.env.service.env.clientapi.ClientApiProperties;
import im.turms.server.common.property.env.service.env.database.MongoProperties;
import im.turms.server.common.property.env.service.env.redis.TurmsRedisProperties;
import im.turms.server.common.property.metadata.view.MutablePropertiesView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author James Chen
 */
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
public class ServiceProperties {

    // Env

    @JsonView(MutablePropertiesView.class)
    @NestedConfigurationProperty
    private AdminApiProperties adminApi = new AdminApiProperties();

    @JsonView(MutablePropertiesView.class)
    @NestedConfigurationProperty
    private ClientApiProperties clientApi = new ClientApiProperties();

    @JsonView(MutablePropertiesView.class)
    @NestedConfigurationProperty
    private FakeProperties fake = new FakeProperties();

    @JsonView(MutablePropertiesView.class)
    @NestedConfigurationProperty
    private MongoProperties mongo = new MongoProperties();

    @JsonView(MutablePropertiesView.class)
    @NestedConfigurationProperty
    private TurmsRedisProperties redis = new TurmsRedisProperties();

    // Business

    @JsonView(MutablePropertiesView.class)
    @NestedConfigurationProperty
    private ConversationProperties conversation = new ConversationProperties();

    @JsonView(MutablePropertiesView.class)
    @NestedConfigurationProperty
    private MessageProperties message = new MessageProperties();

    @JsonView(MutablePropertiesView.class)
    @NestedConfigurationProperty
    private GroupProperties group = new GroupProperties();

    @JsonView(MutablePropertiesView.class)
    @NestedConfigurationProperty
    private UserProperties user = new UserProperties();

    @JsonView(MutablePropertiesView.class)
    @NestedConfigurationProperty
    private StorageProperties storage = new StorageProperties();

    @JsonView(MutablePropertiesView.class)
    @NestedConfigurationProperty
    private NotificationProperties notification = new NotificationProperties();

    @JsonView(MutablePropertiesView.class)
    @NestedConfigurationProperty
    private ActivityLoggingProperties activityLogging = new ActivityLoggingProperties();

}