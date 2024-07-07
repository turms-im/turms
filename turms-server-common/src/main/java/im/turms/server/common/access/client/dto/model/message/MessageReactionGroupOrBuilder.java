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

package im.turms.server.common.access.client.dto.model.message;

public interface MessageReactionGroupOrBuilder extends
        // @@protoc_insertion_point(interface_extends:im.turms.proto.MessageReactionGroup)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <code>int32 reaction_type = 1;</code>
     *
     * @return The reactionType.
     */
    int getReactionType();

    /**
     * <code>int32 reaction_count = 2;</code>
     *
     * @return The reactionCount.
     */
    int getReactionCount();

    /**
     * <code>repeated .im.turms.proto.UserInfo user_infos = 3;</code>
     */
    java.util.List<im.turms.server.common.access.client.dto.model.user.UserInfo> getUserInfosList();

    /**
     * <code>repeated .im.turms.proto.UserInfo user_infos = 3;</code>
     */
    im.turms.server.common.access.client.dto.model.user.UserInfo getUserInfos(int index);

    /**
     * <code>repeated .im.turms.proto.UserInfo user_infos = 3;</code>
     */
    int getUserInfosCount();

    /**
     * <code>repeated .im.turms.proto.UserInfo user_infos = 3;</code>
     */
    java.util.List<? extends im.turms.server.common.access.client.dto.model.user.UserInfoOrBuilder> getUserInfosOrBuilderList();

    /**
     * <code>repeated .im.turms.proto.UserInfo user_infos = 3;</code>
     */
    im.turms.server.common.access.client.dto.model.user.UserInfoOrBuilder getUserInfosOrBuilder(
            int index);

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    java.util.List<im.turms.server.common.access.client.dto.model.common.Value> getCustomAttributesList();

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    im.turms.server.common.access.client.dto.model.common.Value getCustomAttributes(int index);

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    int getCustomAttributesCount();

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    java.util.List<? extends im.turms.server.common.access.client.dto.model.common.ValueOrBuilder> getCustomAttributesOrBuilderList();

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    im.turms.server.common.access.client.dto.model.common.ValueOrBuilder getCustomAttributesOrBuilder(
            int index);
}
