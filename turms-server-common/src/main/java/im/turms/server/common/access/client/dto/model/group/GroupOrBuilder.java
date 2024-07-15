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

package im.turms.server.common.access.client.dto.model.group;

public interface GroupOrBuilder extends
        // @@protoc_insertion_point(interface_extends:im.turms.proto.Group)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <code>optional int64 id = 1;</code>
     *
     * @return Whether the id field is set.
     */
    boolean hasId();

    /**
     * <code>optional int64 id = 1;</code>
     *
     * @return The id.
     */
    long getId();

    /**
     * <code>optional int64 type_id = 2;</code>
     *
     * @return Whether the typeId field is set.
     */
    boolean hasTypeId();

    /**
     * <code>optional int64 type_id = 2;</code>
     *
     * @return The typeId.
     */
    long getTypeId();

    /**
     * <code>optional int64 creator_id = 3;</code>
     *
     * @return Whether the creatorId field is set.
     */
    boolean hasCreatorId();

    /**
     * <code>optional int64 creator_id = 3;</code>
     *
     * @return The creatorId.
     */
    long getCreatorId();

    /**
     * <code>optional int64 owner_id = 4;</code>
     *
     * @return Whether the ownerId field is set.
     */
    boolean hasOwnerId();

    /**
     * <code>optional int64 owner_id = 4;</code>
     *
     * @return The ownerId.
     */
    long getOwnerId();

    /**
     * <code>optional string name = 5;</code>
     *
     * @return Whether the name field is set.
     */
    boolean hasName();

    /**
     * <code>optional string name = 5;</code>
     *
     * @return The name.
     */
    java.lang.String getName();

    /**
     * <code>optional string name = 5;</code>
     *
     * @return The bytes for name.
     */
    com.google.protobuf.ByteString getNameBytes();

    /**
     * <code>optional string intro = 6;</code>
     *
     * @return Whether the intro field is set.
     */
    boolean hasIntro();

    /**
     * <code>optional string intro = 6;</code>
     *
     * @return The intro.
     */
    java.lang.String getIntro();

    /**
     * <code>optional string intro = 6;</code>
     *
     * @return The bytes for intro.
     */
    com.google.protobuf.ByteString getIntroBytes();

    /**
     * <code>optional string announcement = 7;</code>
     *
     * @return Whether the announcement field is set.
     */
    boolean hasAnnouncement();

    /**
     * <code>optional string announcement = 7;</code>
     *
     * @return The announcement.
     */
    java.lang.String getAnnouncement();

    /**
     * <code>optional string announcement = 7;</code>
     *
     * @return The bytes for announcement.
     */
    com.google.protobuf.ByteString getAnnouncementBytes();

    /**
     * <code>optional int64 creation_date = 8;</code>
     *
     * @return Whether the creationDate field is set.
     */
    boolean hasCreationDate();

    /**
     * <code>optional int64 creation_date = 8;</code>
     *
     * @return The creationDate.
     */
    long getCreationDate();

    /**
     * <code>optional int64 last_updated_date = 9;</code>
     *
     * @return Whether the lastUpdatedDate field is set.
     */
    boolean hasLastUpdatedDate();

    /**
     * <code>optional int64 last_updated_date = 9;</code>
     *
     * @return The lastUpdatedDate.
     */
    long getLastUpdatedDate();

    /**
     * <code>optional int64 mute_end_date = 10;</code>
     *
     * @return Whether the muteEndDate field is set.
     */
    boolean hasMuteEndDate();

    /**
     * <code>optional int64 mute_end_date = 10;</code>
     *
     * @return The muteEndDate.
     */
    long getMuteEndDate();

    /**
     * <code>optional bool active = 11;</code>
     *
     * @return Whether the active field is set.
     */
    boolean hasActive();

    /**
     * <code>optional bool active = 11;</code>
     *
     * @return The active.
     */
    boolean getActive();

    /**
     * <code>map&lt;string, .im.turms.proto.Value&gt; user_defined_attributes = 12;</code>
     */
    int getUserDefinedAttributesCount();

    /**
     * <code>map&lt;string, .im.turms.proto.Value&gt; user_defined_attributes = 12;</code>
     */
    boolean containsUserDefinedAttributes(java.lang.String key);

    /**
     * Use {@link #getUserDefinedAttributesMap()} instead.
     */
    @java.lang.Deprecated
    java.util.Map<java.lang.String, im.turms.server.common.access.client.dto.model.common.Value> getUserDefinedAttributes();

    /**
     * <code>map&lt;string, .im.turms.proto.Value&gt; user_defined_attributes = 12;</code>
     */
    java.util.Map<java.lang.String, im.turms.server.common.access.client.dto.model.common.Value> getUserDefinedAttributesMap();

    /**
     * <code>map&lt;string, .im.turms.proto.Value&gt; user_defined_attributes = 12;</code>
     */
    /* nullable */
    im.turms.server.common.access.client.dto.model.common.Value getUserDefinedAttributesOrDefault(
            java.lang.String key,
            /* nullable */
            im.turms.server.common.access.client.dto.model.common.Value defaultValue);

    /**
     * <code>map&lt;string, .im.turms.proto.Value&gt; user_defined_attributes = 12;</code>
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
