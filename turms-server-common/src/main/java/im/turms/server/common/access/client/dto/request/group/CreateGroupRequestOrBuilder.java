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

package im.turms.server.common.access.client.dto.request.group;

public interface CreateGroupRequestOrBuilder extends
        // @@protoc_insertion_point(interface_extends:im.turms.proto.CreateGroupRequest)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <code>string name = 1;</code>
     *
     * @return The name.
     */
    java.lang.String getName();

    /**
     * <code>string name = 1;</code>
     *
     * @return The bytes for name.
     */
    com.google.protobuf.ByteString getNameBytes();

    /**
     * <code>optional string intro = 2;</code>
     *
     * @return Whether the intro field is set.
     */
    boolean hasIntro();

    /**
     * <code>optional string intro = 2;</code>
     *
     * @return The intro.
     */
    java.lang.String getIntro();

    /**
     * <code>optional string intro = 2;</code>
     *
     * @return The bytes for intro.
     */
    com.google.protobuf.ByteString getIntroBytes();

    /**
     * <code>optional string announcement = 3;</code>
     *
     * @return Whether the announcement field is set.
     */
    boolean hasAnnouncement();

    /**
     * <code>optional string announcement = 3;</code>
     *
     * @return The announcement.
     */
    java.lang.String getAnnouncement();

    /**
     * <code>optional string announcement = 3;</code>
     *
     * @return The bytes for announcement.
     */
    com.google.protobuf.ByteString getAnnouncementBytes();

    /**
     * <code>optional int32 min_score = 4;</code>
     *
     * @return Whether the minScore field is set.
     */
    boolean hasMinScore();

    /**
     * <code>optional int32 min_score = 4;</code>
     *
     * @return The minScore.
     */
    int getMinScore();

    /**
     * <code>optional int64 type_id = 5;</code>
     *
     * @return Whether the typeId field is set.
     */
    boolean hasTypeId();

    /**
     * <code>optional int64 type_id = 5;</code>
     *
     * @return The typeId.
     */
    long getTypeId();

    /**
     * <code>optional int64 mute_end_date = 6;</code>
     *
     * @return Whether the muteEndDate field is set.
     */
    boolean hasMuteEndDate();

    /**
     * <code>optional int64 mute_end_date = 6;</code>
     *
     * @return The muteEndDate.
     */
    long getMuteEndDate();

    /**
     * <code>map&lt;string, .im.turms.proto.Value&gt; user_defined_attributes = 7;</code>
     */
    int getUserDefinedAttributesCount();

    /**
     * <code>map&lt;string, .im.turms.proto.Value&gt; user_defined_attributes = 7;</code>
     */
    boolean containsUserDefinedAttributes(java.lang.String key);

    /**
     * Use {@link #getUserDefinedAttributesMap()} instead.
     */
    @java.lang.Deprecated
    java.util.Map<java.lang.String, im.turms.server.common.access.client.dto.model.common.Value> getUserDefinedAttributes();

    /**
     * <code>map&lt;string, .im.turms.proto.Value&gt; user_defined_attributes = 7;</code>
     */
    java.util.Map<java.lang.String, im.turms.server.common.access.client.dto.model.common.Value> getUserDefinedAttributesMap();

    /**
     * <code>map&lt;string, .im.turms.proto.Value&gt; user_defined_attributes = 7;</code>
     */
    /* nullable */
    im.turms.server.common.access.client.dto.model.common.Value getUserDefinedAttributesOrDefault(
            java.lang.String key,
            /* nullable */
            im.turms.server.common.access.client.dto.model.common.Value defaultValue);

    /**
     * <code>map&lt;string, .im.turms.proto.Value&gt; user_defined_attributes = 7;</code>
     */
    im.turms.server.common.access.client.dto.model.common.Value getUserDefinedAttributesOrThrow(
            java.lang.String key);

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
