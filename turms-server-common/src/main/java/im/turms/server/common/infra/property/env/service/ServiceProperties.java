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

package im.turms.server.common.infra.property.env.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import im.turms.server.common.infra.property.env.service.business.NotificationProperties;
import im.turms.server.common.infra.property.env.service.business.conversation.ConversationProperties;
import im.turms.server.common.infra.property.env.service.business.group.GroupProperties;
import im.turms.server.common.infra.property.env.service.business.message.MessageProperties;
import im.turms.server.common.infra.property.env.service.business.storage.StorageProperties;
import im.turms.server.common.infra.property.env.service.business.user.UserProperties;
import im.turms.server.common.infra.property.env.service.env.FakeProperties;
import im.turms.server.common.infra.property.env.service.env.StatisticsProperties;
import im.turms.server.common.infra.property.env.service.env.adminapi.AdminApiProperties;
import im.turms.server.common.infra.property.env.service.env.clientapi.ClientApiProperties;
import im.turms.server.common.infra.property.env.service.env.database.MongoProperties;
import im.turms.server.common.infra.property.env.service.env.push.PushNotificationProperties;
import im.turms.server.common.infra.property.env.service.env.redis.TurmsRedisProperties;

/**
 * @author James Chen
 */
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
public class ServiceProperties {

    // Env

    @NestedConfigurationProperty
    private AdminApiProperties adminApi = new AdminApiProperties();

    @NestedConfigurationProperty
    private ClientApiProperties clientApi = new ClientApiProperties();

    @NestedConfigurationProperty
    private FakeProperties fake = new FakeProperties();

    @NestedConfigurationProperty
    private MongoProperties mongo = new MongoProperties();

    @NestedConfigurationProperty
    private PushNotificationProperties pushNotification = new PushNotificationProperties();

    @NestedConfigurationProperty
    private TurmsRedisProperties redis = new TurmsRedisProperties();

    @NestedConfigurationProperty
    private StatisticsProperties statistics = new StatisticsProperties();

    // Business

    @NestedConfigurationProperty
    private ConversationProperties conversation = new ConversationProperties();

    @NestedConfigurationProperty
    private MessageProperties message = new MessageProperties();

    @NestedConfigurationProperty
    private GroupProperties group = new GroupProperties();

    @NestedConfigurationProperty
    private UserProperties user = new UserProperties();

    @NestedConfigurationProperty
    private StorageProperties storage = new StorageProperties();

    @NestedConfigurationProperty
    private NotificationProperties notification = new NotificationProperties();

}