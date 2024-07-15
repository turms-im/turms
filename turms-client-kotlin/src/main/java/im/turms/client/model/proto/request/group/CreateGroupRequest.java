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

package im.turms.client.model.proto.request.group;

/**
 * Protobuf type {@code im.turms.proto.CreateGroupRequest}
 */
public final class CreateGroupRequest extends
        com.google.protobuf.GeneratedMessageLite<CreateGroupRequest, CreateGroupRequest.Builder>
        implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.CreateGroupRequest)
        CreateGroupRequestOrBuilder {
    private CreateGroupRequest() {
        name_ = "";
        intro_ = "";
        announcement_ = "";
        customAttributes_ = emptyProtobufList();
    }

    private int bitField0_;
    public static final int NAME_FIELD_NUMBER = 1;
    private java.lang.String name_;

    /**
     * <code>string name = 1;</code>
     *
     * @return The name.
     */
    @java.lang.Override
    public java.lang.String getName() {
        return name_;
    }

    /**
     * <code>string name = 1;</code>
     *
     * @return The bytes for name.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getNameBytes() {
        return com.google.protobuf.ByteString.copyFromUtf8(name_);
    }

    /**
     * <code>string name = 1;</code>
     *
     * @param value The name to set.
     */
    private void setName(java.lang.String value) {
        java.lang.Class<?> valueClass = value.getClass();

        name_ = value;
    }

    /**
     * <code>string name = 1;</code>
     */
    private void clearName() {

        name_ = getDefaultInstance().getName();
    }

    /**
     * <code>string name = 1;</code>
     *
     * @param value The bytes for name to set.
     */
    private void setNameBytes(com.google.protobuf.ByteString value) {
        checkByteStringIsUtf8(value);
        name_ = value.toStringUtf8();

    }

    public static final int INTRO_FIELD_NUMBER = 2;
    private java.lang.String intro_;

    /**
     * <code>optional string intro = 2;</code>
     *
     * @return Whether the intro field is set.
     */
    @java.lang.Override
    public boolean hasIntro() {
        return ((bitField0_ & 0x00000001) != 0);
    }

    /**
     * <code>optional string intro = 2;</code>
     *
     * @return The intro.
     */
    @java.lang.Override
    public java.lang.String getIntro() {
        return intro_;
    }

    /**
     * <code>optional string intro = 2;</code>
     *
     * @return The bytes for intro.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getIntroBytes() {
        return com.google.protobuf.ByteString.copyFromUtf8(intro_);
    }

    /**
     * <code>optional string intro = 2;</code>
     *
     * @param value The intro to set.
     */
    private void setIntro(java.lang.String value) {
        java.lang.Class<?> valueClass = value.getClass();
        bitField0_ |= 0x00000001;
        intro_ = value;
    }

    /**
     * <code>optional string intro = 2;</code>
     */
    private void clearIntro() {
        bitField0_ &= ~0x00000001;
        intro_ = getDefaultInstance().getIntro();
    }

    /**
     * <code>optional string intro = 2;</code>
     *
     * @param value The bytes for intro to set.
     */
    private void setIntroBytes(com.google.protobuf.ByteString value) {
        checkByteStringIsUtf8(value);
        intro_ = value.toStringUtf8();
        bitField0_ |= 0x00000001;
    }

    public static final int ANNOUNCEMENT_FIELD_NUMBER = 3;
    private java.lang.String announcement_;

    /**
     * <code>optional string announcement = 3;</code>
     *
     * @return Whether the announcement field is set.
     */
    @java.lang.Override
    public boolean hasAnnouncement() {
        return ((bitField0_ & 0x00000002) != 0);
    }

    /**
     * <code>optional string announcement = 3;</code>
     *
     * @return The announcement.
     */
    @java.lang.Override
    public java.lang.String getAnnouncement() {
        return announcement_;
    }

    /**
     * <code>optional string announcement = 3;</code>
     *
     * @return The bytes for announcement.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getAnnouncementBytes() {
        return com.google.protobuf.ByteString.copyFromUtf8(announcement_);
    }

    /**
     * <code>optional string announcement = 3;</code>
     *
     * @param value The announcement to set.
     */
    private void setAnnouncement(java.lang.String value) {
        java.lang.Class<?> valueClass = value.getClass();
        bitField0_ |= 0x00000002;
        announcement_ = value;
    }

    /**
     * <code>optional string announcement = 3;</code>
     */
    private void clearAnnouncement() {
        bitField0_ &= ~0x00000002;
        announcement_ = getDefaultInstance().getAnnouncement();
    }

    /**
     * <code>optional string announcement = 3;</code>
     *
     * @param value The bytes for announcement to set.
     */
    private void setAnnouncementBytes(com.google.protobuf.ByteString value) {
        checkByteStringIsUtf8(value);
        announcement_ = value.toStringUtf8();
        bitField0_ |= 0x00000002;
    }

    public static final int MIN_SCORE_FIELD_NUMBER = 4;
    private int minScore_;

    /**
     * <code>optional int32 min_score = 4;</code>
     *
     * @return Whether the minScore field is set.
     */
    @java.lang.Override
    public boolean hasMinScore() {
        return ((bitField0_ & 0x00000004) != 0);
    }

    /**
     * <code>optional int32 min_score = 4;</code>
     *
     * @return The minScore.
     */
    @java.lang.Override
    public int getMinScore() {
        return minScore_;
    }

    /**
     * <code>optional int32 min_score = 4;</code>
     *
     * @param value The minScore to set.
     */
    private void setMinScore(int value) {
        bitField0_ |= 0x00000004;
        minScore_ = value;
    }

    /**
     * <code>optional int32 min_score = 4;</code>
     */
    private void clearMinScore() {
        bitField0_ &= ~0x00000004;
        minScore_ = 0;
    }

    public static final int TYPE_ID_FIELD_NUMBER = 5;
    private long typeId_;

    /**
     * <code>optional int64 type_id = 5;</code>
     *
     * @return Whether the typeId field is set.
     */
    @java.lang.Override
    public boolean hasTypeId() {
        return ((bitField0_ & 0x00000008) != 0);
    }

    /**
     * <code>optional int64 type_id = 5;</code>
     *
     * @return The typeId.
     */
    @java.lang.Override
    public long getTypeId() {
        return typeId_;
    }

    /**
     * <code>optional int64 type_id = 5;</code>
     *
     * @param value The typeId to set.
     */
    private void setTypeId(long value) {
        bitField0_ |= 0x00000008;
        typeId_ = value;
    }

    /**
     * <code>optional int64 type_id = 5;</code>
     */
    private void clearTypeId() {
        bitField0_ &= ~0x00000008;
        typeId_ = 0L;
    }

    public static final int MUTE_END_DATE_FIELD_NUMBER = 6;
    private long muteEndDate_;

    /**
     * <code>optional int64 mute_end_date = 6;</code>
     *
     * @return Whether the muteEndDate field is set.
     */
    @java.lang.Override
    public boolean hasMuteEndDate() {
        return ((bitField0_ & 0x00000010) != 0);
    }

    /**
     * <code>optional int64 mute_end_date = 6;</code>
     *
     * @return The muteEndDate.
     */
    @java.lang.Override
    public long getMuteEndDate() {
        return muteEndDate_;
    }

    /**
     * <code>optional int64 mute_end_date = 6;</code>
     *
     * @param value The muteEndDate to set.
     */
    private void setMuteEndDate(long value) {
        bitField0_ |= 0x00000010;
        muteEndDate_ = value;
    }

    /**
     * <code>optional int64 mute_end_date = 6;</code>
     */
    private void clearMuteEndDate() {
        bitField0_ &= ~0x00000010;
        muteEndDate_ = 0L;
    }

    public static final int USER_DEFINED_ATTRIBUTES_FIELD_NUMBER = 7;

    private static final class UserDefinedAttributesDefaultEntryHolder {
        static final com.google.protobuf.MapEntryLite<java.lang.String, im.turms.client.model.proto.model.common.Value> defaultEntry =
                com.google.protobuf.MapEntryLite.newDefaultInstance(
                        com.google.protobuf.WireFormat.FieldType.STRING,
                        "",
                        com.google.protobuf.WireFormat.FieldType.MESSAGE,
                        im.turms.client.model.proto.model.common.Value.getDefaultInstance());
    }

    private com.google.protobuf.MapFieldLite<java.lang.String, im.turms.client.model.proto.model.common.Value> userDefinedAttributes_ =
            com.google.protobuf.MapFieldLite.emptyMapField();

    private com.google.protobuf.MapFieldLite<java.lang.String, im.turms.client.model.proto.model.common.Value> internalGetUserDefinedAttributes() {
        return userDefinedAttributes_;
    }

    private com.google.protobuf.MapFieldLite<java.lang.String, im.turms.client.model.proto.model.common.Value> internalGetMutableUserDefinedAttributes() {
        if (!userDefinedAttributes_.isMutable()) {
            userDefinedAttributes_ = userDefinedAttributes_.mutableCopy();
        }
        return userDefinedAttributes_;
    }

    @java.lang.Override

    public int getUserDefinedAttributesCount() {
        return internalGetUserDefinedAttributes().size();
    }

    /**
     * <code>map&lt;string, .im.turms.proto.Value&gt; user_defined_attributes = 7;</code>
     */
    @java.lang.Override

    public boolean containsUserDefinedAttributes(java.lang.String key) {
        java.lang.Class<?> keyClass = key.getClass();
        return internalGetUserDefinedAttributes().containsKey(key);
    }

    /**
     * Use {@link #getUserDefinedAttributesMap()} instead.
     */
    @java.lang.Override
    @java.lang.Deprecated
    public java.util.Map<java.lang.String, im.turms.client.model.proto.model.common.Value> getUserDefinedAttributes() {
        return getUserDefinedAttributesMap();
    }

    /**
     * <code>map&lt;string, .im.turms.proto.Value&gt; user_defined_attributes = 7;</code>
     */
    @java.lang.Override

    public java.util.Map<java.lang.String, im.turms.client.model.proto.model.common.Value> getUserDefinedAttributesMap() {
        return java.util.Collections.unmodifiableMap(internalGetUserDefinedAttributes());
    }

    /**
     * <code>map&lt;string, .im.turms.proto.Value&gt; user_defined_attributes = 7;</code>
     */
    @java.lang.Override

    public /* nullable */
    im.turms.client.model.proto.model.common.Value getUserDefinedAttributesOrDefault(
            java.lang.String key,
            /* nullable */
            im.turms.client.model.proto.model.common.Value defaultValue) {
        java.lang.Class<?> keyClass = key.getClass();
        java.util.Map<java.lang.String, im.turms.client.model.proto.model.common.Value> map =
                internalGetUserDefinedAttributes();
        return map.getOrDefault(key, defaultValue);
    }

    /**
     * <code>map&lt;string, .im.turms.proto.Value&gt; user_defined_attributes = 7;</code>
     */
    @java.lang.Override

    public im.turms.client.model.proto.model.common.Value getUserDefinedAttributesOrThrow(
            java.lang.String key) {
        java.lang.Class<?> keyClass = key.getClass();
        java.util.Map<java.lang.String, im.turms.client.model.proto.model.common.Value> map =
                internalGetUserDefinedAttributes();
        if (!map.containsKey(key)) {
            throw new java.lang.IllegalArgumentException();
        }
        return map.get(key);
    }

    /**
     * <code>map&lt;string, .im.turms.proto.Value&gt; user_defined_attributes = 7;</code>
     */
    private java.util.Map<java.lang.String, im.turms.client.model.proto.model.common.Value> getMutableUserDefinedAttributesMap() {
        return internalGetMutableUserDefinedAttributes();
    }

    public static final int CUSTOM_ATTRIBUTES_FIELD_NUMBER = 15;
    private com.google.protobuf.Internal.ProtobufList<im.turms.client.model.proto.model.common.Value> customAttributes_;

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.client.model.proto.model.common.Value> getCustomAttributesList() {
        return customAttributes_;
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    public java.util.List<? extends im.turms.client.model.proto.model.common.ValueOrBuilder> getCustomAttributesOrBuilderList() {
        return customAttributes_;
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    @java.lang.Override
    public int getCustomAttributesCount() {
        return customAttributes_.size();
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.model.common.Value getCustomAttributes(int index) {
        return customAttributes_.get(index);
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    public im.turms.client.model.proto.model.common.ValueOrBuilder getCustomAttributesOrBuilder(
            int index) {
        return customAttributes_.get(index);
    }

    private void ensureCustomAttributesIsMutable() {
        com.google.protobuf.Internal.ProtobufList<im.turms.client.model.proto.model.common.Value> tmp =
                customAttributes_;
        if (!tmp.isModifiable()) {
            customAttributes_ = com.google.protobuf.GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    private void setCustomAttributes(
            int index,
            im.turms.client.model.proto.model.common.Value value) {
        value.getClass();
        ensureCustomAttributesIsMutable();
        customAttributes_.set(index, value);
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    private void addCustomAttributes(im.turms.client.model.proto.model.common.Value value) {
        value.getClass();
        ensureCustomAttributesIsMutable();
        customAttributes_.add(value);
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    private void addCustomAttributes(
            int index,
            im.turms.client.model.proto.model.common.Value value) {
        value.getClass();
        ensureCustomAttributesIsMutable();
        customAttributes_.add(index, value);
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    private void addAllCustomAttributes(
            java.lang.Iterable<? extends im.turms.client.model.proto.model.common.Value> values) {
        ensureCustomAttributesIsMutable();
        com.google.protobuf.AbstractMessageLite.addAll(values, customAttributes_);
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    private void clearCustomAttributes() {
        customAttributes_ = emptyProtobufList();
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    private void removeCustomAttributes(int index) {
        ensureCustomAttributesIsMutable();
        customAttributes_.remove(index);
    }

    public static im.turms.client.model.proto.request.group.CreateGroupRequest parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.group.CreateGroupRequest parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.group.CreateGroupRequest parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.group.CreateGroupRequest parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.group.CreateGroupRequest parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.group.CreateGroupRequest parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.group.CreateGroupRequest parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.group.CreateGroupRequest parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.group.CreateGroupRequest parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.group.CreateGroupRequest parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.group.CreateGroupRequest parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.group.CreateGroupRequest parseFrom(
            com.google.protobuf.CodedInputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(
            im.turms.client.model.proto.request.group.CreateGroupRequest prototype) {
        return DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /**
     * Protobuf type {@code im.turms.proto.CreateGroupRequest}
     */
    public static final class Builder extends
            com.google.protobuf.GeneratedMessageLite.Builder<im.turms.client.model.proto.request.group.CreateGroupRequest, Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.CreateGroupRequest)
            im.turms.client.model.proto.request.group.CreateGroupRequestOrBuilder {
        // Construct using im.turms.client.model.proto.request.group.CreateGroupRequest.newBuilder()
        private Builder() {
            super(DEFAULT_INSTANCE);
        }

        /**
         * <code>string name = 1;</code>
         *
         * @return The name.
         */
        @java.lang.Override
        public java.lang.String getName() {
            return instance.getName();
        }

        /**
         * <code>string name = 1;</code>
         *
         * @return The bytes for name.
         */
        @java.lang.Override
        public com.google.protobuf.ByteString getNameBytes() {
            return instance.getNameBytes();
        }

        /**
         * <code>string name = 1;</code>
         *
         * @param value The name to set.
         * @return This builder for chaining.
         */
        public Builder setName(java.lang.String value) {
            copyOnWrite();
            instance.setName(value);
            return this;
        }

        /**
         * <code>string name = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearName() {
            copyOnWrite();
            instance.clearName();
            return this;
        }

        /**
         * <code>string name = 1;</code>
         *
         * @param value The bytes for name to set.
         * @return This builder for chaining.
         */
        public Builder setNameBytes(com.google.protobuf.ByteString value) {
            copyOnWrite();
            instance.setNameBytes(value);
            return this;
        }

        /**
         * <code>optional string intro = 2;</code>
         *
         * @return Whether the intro field is set.
         */
        @java.lang.Override
        public boolean hasIntro() {
            return instance.hasIntro();
        }

        /**
         * <code>optional string intro = 2;</code>
         *
         * @return The intro.
         */
        @java.lang.Override
        public java.lang.String getIntro() {
            return instance.getIntro();
        }

        /**
         * <code>optional string intro = 2;</code>
         *
         * @return The bytes for intro.
         */
        @java.lang.Override
        public com.google.protobuf.ByteString getIntroBytes() {
            return instance.getIntroBytes();
        }

        /**
         * <code>optional string intro = 2;</code>
         *
         * @param value The intro to set.
         * @return This builder for chaining.
         */
        public Builder setIntro(java.lang.String value) {
            copyOnWrite();
            instance.setIntro(value);
            return this;
        }

        /**
         * <code>optional string intro = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearIntro() {
            copyOnWrite();
            instance.clearIntro();
            return this;
        }

        /**
         * <code>optional string intro = 2;</code>
         *
         * @param value The bytes for intro to set.
         * @return This builder for chaining.
         */
        public Builder setIntroBytes(com.google.protobuf.ByteString value) {
            copyOnWrite();
            instance.setIntroBytes(value);
            return this;
        }

        /**
         * <code>optional string announcement = 3;</code>
         *
         * @return Whether the announcement field is set.
         */
        @java.lang.Override
        public boolean hasAnnouncement() {
            return instance.hasAnnouncement();
        }

        /**
         * <code>optional string announcement = 3;</code>
         *
         * @return The announcement.
         */
        @java.lang.Override
        public java.lang.String getAnnouncement() {
            return instance.getAnnouncement();
        }

        /**
         * <code>optional string announcement = 3;</code>
         *
         * @return The bytes for announcement.
         */
        @java.lang.Override
        public com.google.protobuf.ByteString getAnnouncementBytes() {
            return instance.getAnnouncementBytes();
        }

        /**
         * <code>optional string announcement = 3;</code>
         *
         * @param value The announcement to set.
         * @return This builder for chaining.
         */
        public Builder setAnnouncement(java.lang.String value) {
            copyOnWrite();
            instance.setAnnouncement(value);
            return this;
        }

        /**
         * <code>optional string announcement = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearAnnouncement() {
            copyOnWrite();
            instance.clearAnnouncement();
            return this;
        }

        /**
         * <code>optional string announcement = 3;</code>
         *
         * @param value The bytes for announcement to set.
         * @return This builder for chaining.
         */
        public Builder setAnnouncementBytes(com.google.protobuf.ByteString value) {
            copyOnWrite();
            instance.setAnnouncementBytes(value);
            return this;
        }

        /**
         * <code>optional int32 min_score = 4;</code>
         *
         * @return Whether the minScore field is set.
         */
        @java.lang.Override
        public boolean hasMinScore() {
            return instance.hasMinScore();
        }

        /**
         * <code>optional int32 min_score = 4;</code>
         *
         * @return The minScore.
         */
        @java.lang.Override
        public int getMinScore() {
            return instance.getMinScore();
        }

        /**
         * <code>optional int32 min_score = 4;</code>
         *
         * @param value The minScore to set.
         * @return This builder for chaining.
         */
        public Builder setMinScore(int value) {
            copyOnWrite();
            instance.setMinScore(value);
            return this;
        }

        /**
         * <code>optional int32 min_score = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearMinScore() {
            copyOnWrite();
            instance.clearMinScore();
            return this;
        }

        /**
         * <code>optional int64 type_id = 5;</code>
         *
         * @return Whether the typeId field is set.
         */
        @java.lang.Override
        public boolean hasTypeId() {
            return instance.hasTypeId();
        }

        /**
         * <code>optional int64 type_id = 5;</code>
         *
         * @return The typeId.
         */
        @java.lang.Override
        public long getTypeId() {
            return instance.getTypeId();
        }

        /**
         * <code>optional int64 type_id = 5;</code>
         *
         * @param value The typeId to set.
         * @return This builder for chaining.
         */
        public Builder setTypeId(long value) {
            copyOnWrite();
            instance.setTypeId(value);
            return this;
        }

        /**
         * <code>optional int64 type_id = 5;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearTypeId() {
            copyOnWrite();
            instance.clearTypeId();
            return this;
        }

        /**
         * <code>optional int64 mute_end_date = 6;</code>
         *
         * @return Whether the muteEndDate field is set.
         */
        @java.lang.Override
        public boolean hasMuteEndDate() {
            return instance.hasMuteEndDate();
        }

        /**
         * <code>optional int64 mute_end_date = 6;</code>
         *
         * @return The muteEndDate.
         */
        @java.lang.Override
        public long getMuteEndDate() {
            return instance.getMuteEndDate();
        }

        /**
         * <code>optional int64 mute_end_date = 6;</code>
         *
         * @param value The muteEndDate to set.
         * @return This builder for chaining.
         */
        public Builder setMuteEndDate(long value) {
            copyOnWrite();
            instance.setMuteEndDate(value);
            return this;
        }

        /**
         * <code>optional int64 mute_end_date = 6;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearMuteEndDate() {
            copyOnWrite();
            instance.clearMuteEndDate();
            return this;
        }

        @java.lang.Override

        public int getUserDefinedAttributesCount() {
            return instance.getUserDefinedAttributesMap()
                    .size();
        }

        /**
         * <code>map&lt;string, .im.turms.proto.Value&gt; user_defined_attributes = 7;</code>
         */
        @java.lang.Override

        public boolean containsUserDefinedAttributes(java.lang.String key) {
            java.lang.Class<?> keyClass = key.getClass();
            return instance.getUserDefinedAttributesMap()
                    .containsKey(key);
        }

        public Builder clearUserDefinedAttributes() {
            copyOnWrite();
            instance.getMutableUserDefinedAttributesMap()
                    .clear();
            return this;
        }

        /**
         * <code>map&lt;string, .im.turms.proto.Value&gt; user_defined_attributes = 7;</code>
         */

        public Builder removeUserDefinedAttributes(java.lang.String key) {
            java.lang.Class<?> keyClass = key.getClass();
            copyOnWrite();
            instance.getMutableUserDefinedAttributesMap()
                    .remove(key);
            return this;
        }

        /**
         * Use {@link #getUserDefinedAttributesMap()} instead.
         */
        @java.lang.Override
        @java.lang.Deprecated
        public java.util.Map<java.lang.String, im.turms.client.model.proto.model.common.Value> getUserDefinedAttributes() {
            return getUserDefinedAttributesMap();
        }

        /**
         * <code>map&lt;string, .im.turms.proto.Value&gt; user_defined_attributes = 7;</code>
         */
        @java.lang.Override
        public java.util.Map<java.lang.String, im.turms.client.model.proto.model.common.Value> getUserDefinedAttributesMap() {
            return java.util.Collections.unmodifiableMap(instance.getUserDefinedAttributesMap());
        }

        /**
         * <code>map&lt;string, .im.turms.proto.Value&gt; user_defined_attributes = 7;</code>
         */
        @java.lang.Override

        public /* nullable */
        im.turms.client.model.proto.model.common.Value getUserDefinedAttributesOrDefault(
                java.lang.String key,
                /* nullable */
                im.turms.client.model.proto.model.common.Value defaultValue) {
            java.lang.Class<?> keyClass = key.getClass();
            java.util.Map<java.lang.String, im.turms.client.model.proto.model.common.Value> map =
                    instance.getUserDefinedAttributesMap();
            return map.getOrDefault(key, defaultValue);
        }

        /**
         * <code>map&lt;string, .im.turms.proto.Value&gt; user_defined_attributes = 7;</code>
         */
        @java.lang.Override

        public im.turms.client.model.proto.model.common.Value getUserDefinedAttributesOrThrow(
                java.lang.String key) {
            java.lang.Class<?> keyClass = key.getClass();
            java.util.Map<java.lang.String, im.turms.client.model.proto.model.common.Value> map =
                    instance.getUserDefinedAttributesMap();
            if (!map.containsKey(key)) {
                throw new java.lang.IllegalArgumentException();
            }
            return map.get(key);
        }

        /**
         * <code>map&lt;string, .im.turms.proto.Value&gt; user_defined_attributes = 7;</code>
         */
        public Builder putUserDefinedAttributes(
                java.lang.String key,
                im.turms.client.model.proto.model.common.Value value) {
            java.lang.Class<?> keyClass = key.getClass();
            java.lang.Class<?> valueClass = value.getClass();
            copyOnWrite();
            instance.getMutableUserDefinedAttributesMap()
                    .put(key, value);
            return this;
        }

        /**
         * <code>map&lt;string, .im.turms.proto.Value&gt; user_defined_attributes = 7;</code>
         */
        public Builder putAllUserDefinedAttributes(
                java.util.Map<java.lang.String, im.turms.client.model.proto.model.common.Value> values) {
            copyOnWrite();
            instance.getMutableUserDefinedAttributesMap()
                    .putAll(values);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        @java.lang.Override
        public java.util.List<im.turms.client.model.proto.model.common.Value> getCustomAttributesList() {
            return java.util.Collections.unmodifiableList(instance.getCustomAttributesList());
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        @java.lang.Override
        public int getCustomAttributesCount() {
            return instance.getCustomAttributesCount();
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.model.common.Value getCustomAttributes(int index) {
            return instance.getCustomAttributes(index);
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder setCustomAttributes(
                int index,
                im.turms.client.model.proto.model.common.Value value) {
            copyOnWrite();
            instance.setCustomAttributes(index, value);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder setCustomAttributes(
                int index,
                im.turms.client.model.proto.model.common.Value.Builder builderForValue) {
            copyOnWrite();
            instance.setCustomAttributes(index, builderForValue.build());
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder addCustomAttributes(im.turms.client.model.proto.model.common.Value value) {
            copyOnWrite();
            instance.addCustomAttributes(value);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder addCustomAttributes(
                int index,
                im.turms.client.model.proto.model.common.Value value) {
            copyOnWrite();
            instance.addCustomAttributes(index, value);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder addCustomAttributes(
                im.turms.client.model.proto.model.common.Value.Builder builderForValue) {
            copyOnWrite();
            instance.addCustomAttributes(builderForValue.build());
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder addCustomAttributes(
                int index,
                im.turms.client.model.proto.model.common.Value.Builder builderForValue) {
            copyOnWrite();
            instance.addCustomAttributes(index, builderForValue.build());
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder addAllCustomAttributes(
                java.lang.Iterable<? extends im.turms.client.model.proto.model.common.Value> values) {
            copyOnWrite();
            instance.addAllCustomAttributes(values);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder clearCustomAttributes() {
            copyOnWrite();
            instance.clearCustomAttributes();
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder removeCustomAttributes(int index) {
            copyOnWrite();
            instance.removeCustomAttributes(index);
            return this;
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.CreateGroupRequest)
    }

    @java.lang.Override
    @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
    protected java.lang.Object dynamicMethod(
            com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
            java.lang.Object arg0,
            java.lang.Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE: {
                return new im.turms.client.model.proto.request.group.CreateGroupRequest();
            }
            case NEW_BUILDER: {
                return new Builder();
            }
            case BUILD_MESSAGE_INFO: {
                java.lang.Object[] objects = new java.lang.Object[]{"bitField0_",
                        "name_",
                        "intro_",
                        "announcement_",
                        "minScore_",
                        "typeId_",
                        "muteEndDate_",
                        "userDefinedAttributes_",
                        UserDefinedAttributesDefaultEntryHolder.defaultEntry,
                        "customAttributes_",
                        im.turms.client.model.proto.model.common.Value.class,};
                java.lang.String info =
                        "\u0000\b\u0000\u0001\u0001\u000f\b\u0001\u0001\u0000\u0001\u0208\u0002\u1208\u0000"
                                + "\u0003\u1208\u0001\u0004\u1004\u0002\u0005\u1002\u0003\u0006\u1002\u0004\u00072\u000f"
                                + "\u001b";
                return newMessageInfo(DEFAULT_INSTANCE, info, objects);
            }
            // fall through
            case GET_DEFAULT_INSTANCE: {
                return DEFAULT_INSTANCE;
            }
            case GET_PARSER: {
                com.google.protobuf.Parser<im.turms.client.model.proto.request.group.CreateGroupRequest> parser =
                        PARSER;
                if (parser == null) {
                    synchronized (im.turms.client.model.proto.request.group.CreateGroupRequest.class) {
                        parser = PARSER;
                        if (parser == null) {
                            parser = new DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                            PARSER = parser;
                        }
                    }
                }
                return parser;
            }
            case GET_MEMOIZED_IS_INITIALIZED: {
                return (byte) 1;
            }
            case SET_MEMOIZED_IS_INITIALIZED: {
                return null;
            }
        }
        throw new UnsupportedOperationException();
    }

    // @@protoc_insertion_point(class_scope:im.turms.proto.CreateGroupRequest)
    private static final im.turms.client.model.proto.request.group.CreateGroupRequest DEFAULT_INSTANCE;
    static {
        CreateGroupRequest defaultInstance = new CreateGroupRequest();
        // New instances are implicitly immutable so no need to make
        // immutable.
        DEFAULT_INSTANCE = defaultInstance;
        com.google.protobuf.GeneratedMessageLite.registerDefaultInstance(CreateGroupRequest.class,
                defaultInstance);
    }

    public static im.turms.client.model.proto.request.group.CreateGroupRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static volatile com.google.protobuf.Parser<CreateGroupRequest> PARSER;

    public static com.google.protobuf.Parser<CreateGroupRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}