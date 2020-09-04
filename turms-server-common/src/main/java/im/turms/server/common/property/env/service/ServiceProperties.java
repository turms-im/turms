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
import im.turms.server.common.property.env.service.business.activity.ActivityLoggingProperties;
import im.turms.server.common.property.env.service.business.StorageProperties;
import im.turms.server.common.property.env.service.business.message.MessageProperties;
import im.turms.server.common.property.env.service.business.user.UserProperties;
import im.turms.server.common.property.env.service.env.*;
import im.turms.server.common.property.metadata.view.MutablePropertiesView;
import lombok.Data;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author James Chen
 */
@Data
public class ServiceProperties {

    // Env

    @JsonView(MutablePropertiesView.class)
    @NestedConfigurationProperty
    private AdminApiProperties adminApi = new AdminApiProperties();

    @JsonView(MutablePropertiesView.class)
    @NestedConfigurationProperty
    private DatabaseProperties database = new DatabaseProperties();

    @JsonView(MutablePropertiesView.class)
    @NestedConfigurationProperty
    private DiscoveryProperties discovery = new DiscoveryProperties();

    @JsonView(MutablePropertiesView.class)
    @NestedConfigurationProperty
    private LogProperties log = new LogProperties();

    @JsonView(MutablePropertiesView.class)
    @NestedConfigurationProperty
    private MockProperties mock = new MockProperties();

    @JsonView(MutablePropertiesView.class)
    @NestedConfigurationProperty
    private TurmsRedisProperties redis = new TurmsRedisProperties();

    // Business

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
