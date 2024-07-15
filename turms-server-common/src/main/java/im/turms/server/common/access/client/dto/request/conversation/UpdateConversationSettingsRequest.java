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

package im.turms.server.common.access.client.dto.request.conversation;

/**
 * Protobuf type {@code im.turms.proto.UpdateConversationSettingsRequest}
 */
public final class UpdateConversationSettingsRequest extends com.google.protobuf.GeneratedMessage
        implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.UpdateConversationSettingsRequest)
        UpdateConversationSettingsRequestOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 27,
                /* patch= */ 2,
                /* suffix= */ "",
                UpdateConversationSettingsRequest.class.getName());
    }

    // Use UpdateConversationSettingsRequest.newBuilder() to construct.
    private UpdateConversationSettingsRequest(
            com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private UpdateConversationSettingsRequest() {
        customAttributes_ = java.util.Collections.emptyList();
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.server.common.access.client.dto.request.conversation.UpdateConversationSettingsRequestOuterClass.internal_static_im_turms_proto_UpdateConversationSettingsRequest_descriptor;
    }

    @SuppressWarnings({"rawtypes"})
    @java.lang.Override
    protected com.google.protobuf.MapFieldReflectionAccessor internalGetMapFieldReflection(
            int number) {
        return switch (number) {
            case 3 -> internalGetSettings();
            default -> throw new RuntimeException(
                    "Invalid map field number: "
                            + number);
        };
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.server.common.access.client.dto.request.conversation.UpdateConversationSettingsRequestOuterClass.internal_static_im_turms_proto_UpdateConversationSettingsRequest_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.server.common.access.client.dto.request.conversation.UpdateConversationSettingsRequest.class,
                        im.turms.server.common.access.client.dto.request.conversation.UpdateConversationSettingsRequest.Builder.class);
    }

    private int bitField0_;
    public static final int USER_ID_FIELD_NUMBER = 1;
    private long userId_ = 0L;

    /**
     * <pre>
     * Query filter
     * </pre>
     *
     * <code>optional int64 user_id = 1;</code>
     *
     * @return Whether the userId field is set.
     */
    @java.lang.Override
    public boolean hasUserId() {
        return ((bitField0_ & 0x00000001) != 0);
    }

    /**
     * <pre>
     * Query filter
     * </pre>
     *
     * <code>optional int64 user_id = 1;</code>
     *
     * @return The userId.
     */
    @java.lang.Override
    public long getUserId() {
        return userId_;
    }

    public static final int GROUP_ID_FIELD_NUMBER = 2;
    private long groupId_ = 0L;

    /**
     * <code>optional int64 group_id = 2;</code>
     *
     * @return Whether the groupId field is set.
     */
    @java.lang.Override
    public boolean hasGroupId() {
        return ((bitField0_ & 0x00000002) != 0);
    }

    /**
     * <code>optional int64 group_id = 2;</code>
     *
     * @return The groupId.
     */
    @java.lang.Override
    public long getGroupId() {
        return groupId_;
    }

    public static final int SETTINGS_FIELD_NUMBER = 3;

    private static final class SettingsDefaultEntryHolder {
        static final com.google.protobuf.MapEntry<java.lang.String, im.turms.server.common.access.client.dto.model.common.Value> defaultEntry =
                com.google.protobuf.MapEntry.newDefaultInstance(
                        im.turms.server.common.access.client.dto.request.conversation.UpdateConversationSettingsRequestOuterClass.internal_static_im_turms_proto_UpdateConversationSettingsRequest_SettingsEntry_descriptor,
                        com.google.protobuf.WireFormat.FieldType.STRING,
                        "",
                        com.google.protobuf.WireFormat.FieldType.MESSAGE,
                        im.turms.server.common.access.client.dto.model.common.Value
                                .getDefaultInstance());
    }

    @SuppressWarnings("serial")
    private com.google.protobuf.MapField<java.lang.String, im.turms.server.common.access.client.dto.model.common.Value> settings_;

    private com.google.protobuf.MapField<java.lang.String, im.turms.server.common.access.client.dto.model.common.Value> internalGetSettings() {
        if (settings_ == null) {
            return com.google.protobuf.MapField
                    .emptyMapField(SettingsDefaultEntryHolder.defaultEntry);
        }
        return settings_;
    }

    public int getSettingsCount() {
        return internalGetSettings().getMap()
                .size();
    }

    /**
     * <pre>
     * Update
     * </pre>
     *
     * <code>map&lt;string, .im.turms.proto.Value&gt; settings = 3;</code>
     */
    @java.lang.Override
    public boolean containsSettings(java.lang.String key) {
        if (key == null) {
            throw new NullPointerException("map key");
        }
        return internalGetSettings().getMap()
                .containsKey(key);
    }

    /**
     * Use {@link #getSettingsMap()} instead.
     */
    @java.lang.Override
    @java.lang.Deprecated
    public java.util.Map<java.lang.String, im.turms.server.common.access.client.dto.model.common.Value> getSettings() {
        return getSettingsMap();
    }

    /**
     * <pre>
     * Update
     * </pre>
     *
     * <code>map&lt;string, .im.turms.proto.Value&gt; settings = 3;</code>
     */
    @java.lang.Override
    public java.util.Map<java.lang.String, im.turms.server.common.access.client.dto.model.common.Value> getSettingsMap() {
        return internalGetSettings().getMap();
    }

    /**
     * <pre>
     * Update
     * </pre>
     *
     * <code>map&lt;string, .im.turms.proto.Value&gt; settings = 3;</code>
     */
    @java.lang.Override
    public /* nullable */
    im.turms.server.common.access.client.dto.model.common.Value getSettingsOrDefault(
            java.lang.String key,
            /* nullable */
            im.turms.server.common.access.client.dto.model.common.Value defaultValue) {
        if (key == null) {
            throw new NullPointerException("map key");
        }
        java.util.Map<java.lang.String, im.turms.server.common.access.client.dto.model.common.Value> map =
                internalGetSettings().getMap();
        return map.getOrDefault(key, defaultValue);
    }

    /**
     * <pre>
     * Update
     * </pre>
     *
     * <code>map&lt;string, .im.turms.proto.Value&gt; settings = 3;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.common.Value getSettingsOrThrow(
            java.lang.String key) {
        if (key == null) {
            throw new NullPointerException("map key");
        }
        java.util.Map<java.lang.String, im.turms.server.common.access.client.dto.model.common.Value> map =
                internalGetSettings().getMap();
        if (!map.containsKey(key)) {
            throw new java.lang.IllegalArgumentException();
        }
        return map.get(key);
    }

    public static final int CUSTOM_ATTRIBUTES_FIELD_NUMBER = 15;
    @SuppressWarnings("serial")
    private java.util.List<im.turms.server.common.access.client.dto.model.common.Value> customAttributes_;

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.server.common.access.client.dto.model.common.Value> getCustomAttributesList() {
        return customAttributes_;
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    @java.lang.Override
    public java.util.List<? extends im.turms.server.common.access.client.dto.model.common.ValueOrBuilder> getCustomAttributesOrBuilderList() {
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
    public im.turms.server.common.access.client.dto.model.common.Value getCustomAttributes(
            int index) {
        return customAttributes_.get(index);
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.common.ValueOrBuilder getCustomAttributesOrBuilder(
            int index) {
        return customAttributes_.get(index);
    }

    private byte memoizedIsInitialized = -1;

    @java.lang.Override
    public boolean isInitialized() {
        byte isInitialized = memoizedIsInitialized;
        if (isInitialized == 1) {
            return true;
        }
        if (isInitialized == 0) {
            return false;
        }

        memoizedIsInitialized = 1;
        return true;
    }

    @java.lang.Override
    public void writeTo(com.google.protobuf.CodedOutputStream output) throws java.io.IOException {
        if (((bitField0_ & 0x00000001) != 0)) {
            output.writeInt64(1, userId_);
        }
        if (((bitField0_ & 0x00000002) != 0)) {
            output.writeInt64(2, groupId_);
        }
        com.google.protobuf.GeneratedMessage.serializeStringMapTo(output,
                internalGetSettings(),
                SettingsDefaultEntryHolder.defaultEntry,
                3);
        for (im.turms.server.common.access.client.dto.model.common.Value value : customAttributes_) {
            output.writeMessage(15, value);
        }
        getUnknownFields().writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
        int size = memoizedSize;
        if (size != -1) {
            return size;
        }

        size = 0;
        if (((bitField0_ & 0x00000001) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(1, userId_);
        }
        if (((bitField0_ & 0x00000002) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(2, groupId_);
        }
        for (java.util.Map.Entry<java.lang.String, im.turms.server.common.access.client.dto.model.common.Value> entry : internalGetSettings()
                .getMap()
                .entrySet()) {
            com.google.protobuf.MapEntry<java.lang.String, im.turms.server.common.access.client.dto.model.common.Value> settings__ =
                    SettingsDefaultEntryHolder.defaultEntry.newBuilderForType()
                            .setKey(entry.getKey())
                            .setValue(entry.getValue())
                            .build();
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(3, settings__);
        }
        for (im.turms.server.common.access.client.dto.model.common.Value value : customAttributes_) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(15, value);
        }
        size += getUnknownFields().getSerializedSize();
        memoizedSize = size;
        return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof UpdateConversationSettingsRequest other)) {
            return super.equals(obj);
        }

        if (hasUserId() != other.hasUserId()) {
            return false;
        }
        if (hasUserId()) {
            if (getUserId() != other.getUserId()) {
                return false;
            }
        }
        if (hasGroupId() != other.hasGroupId()) {
            return false;
        }
        if (hasGroupId()) {
            if (getGroupId() != other.getGroupId()) {
                return false;
            }
        }
        if (!internalGetSettings().equals(other.internalGetSettings())) {
            return false;
        }
        return getCustomAttributesList().equals(other.getCustomAttributesList())
                && getUnknownFields().equals(other.getUnknownFields());
    }

    @java.lang.Override
    public int hashCode() {
        if (memoizedHashCode != 0) {
            return memoizedHashCode;
        }
        int hash = 41;
        hash = (19 * hash) + getDescriptor().hashCode();
        if (hasUserId()) {
            hash = (37 * hash) + USER_ID_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getUserId());
        }
        if (hasGroupId()) {
            hash = (37 * hash) + GROUP_ID_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getGroupId());
        }
        if (!internalGetSettings().getMap()
                .isEmpty()) {
            hash = (37 * hash) + SETTINGS_FIELD_NUMBER;
            hash = (53 * hash) + internalGetSettings().hashCode();
        }
        if (getCustomAttributesCount() > 0) {
            hash = (37 * hash) + CUSTOM_ATTRIBUTES_FIELD_NUMBER;
            hash = (53 * hash) + getCustomAttributesList().hashCode();
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.server.common.access.client.dto.request.conversation.UpdateConversationSettingsRequest parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.conversation.UpdateConversationSettingsRequest parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.conversation.UpdateConversationSettingsRequest parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.conversation.UpdateConversationSettingsRequest parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.conversation.UpdateConversationSettingsRequest parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.conversation.UpdateConversationSettingsRequest parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.conversation.UpdateConversationSettingsRequest parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.conversation.UpdateConversationSettingsRequest parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.conversation.UpdateConversationSettingsRequest parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.conversation.UpdateConversationSettingsRequest parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.conversation.UpdateConversationSettingsRequest parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.conversation.UpdateConversationSettingsRequest parseFrom(
            com.google.protobuf.CodedInputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    @java.lang.Override
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(
            im.turms.server.common.access.client.dto.request.conversation.UpdateConversationSettingsRequest prototype) {
        return DEFAULT_INSTANCE.toBuilder()
                .mergeFrom(prototype);
    }

    @java.lang.Override
    public Builder toBuilder() {
        return this == DEFAULT_INSTANCE
                ? new Builder()
                : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
        return new Builder(parent);
    }

    /**
     * Protobuf type {@code im.turms.proto.UpdateConversationSettingsRequest}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.UpdateConversationSettingsRequest)
            im.turms.server.common.access.client.dto.request.conversation.UpdateConversationSettingsRequestOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.server.common.access.client.dto.request.conversation.UpdateConversationSettingsRequestOuterClass.internal_static_im_turms_proto_UpdateConversationSettingsRequest_descriptor;
        }

        @SuppressWarnings({"rawtypes"})
        protected com.google.protobuf.MapFieldReflectionAccessor internalGetMapFieldReflection(
                int number) {
            return switch (number) {
                case 3 -> internalGetSettings();
                default -> throw new RuntimeException(
                        "Invalid map field number: "
                                + number);
            };
        }

        @SuppressWarnings({"rawtypes"})
        protected com.google.protobuf.MapFieldReflectionAccessor internalGetMutableMapFieldReflection(
                int number) {
            return switch (number) {
                case 3 -> internalGetMutableSettings();
                default -> throw new RuntimeException(
                        "Invalid map field number: "
                                + number);
            };
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.server.common.access.client.dto.request.conversation.UpdateConversationSettingsRequestOuterClass.internal_static_im_turms_proto_UpdateConversationSettingsRequest_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.server.common.access.client.dto.request.conversation.UpdateConversationSettingsRequest.class,
                            im.turms.server.common.access.client.dto.request.conversation.UpdateConversationSettingsRequest.Builder.class);
        }

        // Construct using
        // im.turms.server.common.access.client.dto.request.conversation.UpdateConversationSettingsRequest.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            userId_ = 0L;
            groupId_ = 0L;
            internalGetMutableSettings().clear();
            if (customAttributesBuilder_ == null) {
                customAttributes_ = java.util.Collections.emptyList();
            } else {
                customAttributes_ = null;
                customAttributesBuilder_.clear();
            }
            bitField0_ &= ~0x00000008;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.server.common.access.client.dto.request.conversation.UpdateConversationSettingsRequestOuterClass.internal_static_im_turms_proto_UpdateConversationSettingsRequest_descriptor;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.conversation.UpdateConversationSettingsRequest getDefaultInstanceForType() {
            return im.turms.server.common.access.client.dto.request.conversation.UpdateConversationSettingsRequest
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.conversation.UpdateConversationSettingsRequest build() {
            im.turms.server.common.access.client.dto.request.conversation.UpdateConversationSettingsRequest result =
                    buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.conversation.UpdateConversationSettingsRequest buildPartial() {
            im.turms.server.common.access.client.dto.request.conversation.UpdateConversationSettingsRequest result =
                    new im.turms.server.common.access.client.dto.request.conversation.UpdateConversationSettingsRequest(
                            this);
            buildPartialRepeatedFields(result);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartialRepeatedFields(
                im.turms.server.common.access.client.dto.request.conversation.UpdateConversationSettingsRequest result) {
            if (customAttributesBuilder_ == null) {
                if (((bitField0_ & 0x00000008) != 0)) {
                    customAttributes_ = java.util.Collections.unmodifiableList(customAttributes_);
                    bitField0_ &= ~0x00000008;
                }
                result.customAttributes_ = customAttributes_;
            } else {
                result.customAttributes_ = customAttributesBuilder_.build();
            }
        }

        private void buildPartial0(
                im.turms.server.common.access.client.dto.request.conversation.UpdateConversationSettingsRequest result) {
            int from_bitField0_ = bitField0_;
            int to_bitField0_ = 0;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.userId_ = userId_;
                to_bitField0_ |= 0x00000001;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.groupId_ = groupId_;
                to_bitField0_ |= 0x00000002;
            }
            if (((from_bitField0_ & 0x00000004) != 0)) {
                result.settings_ =
                        internalGetSettings().build(SettingsDefaultEntryHolder.defaultEntry);
            }
            result.bitField0_ |= to_bitField0_;
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.server.common.access.client.dto.request.conversation.UpdateConversationSettingsRequest) {
                return mergeFrom(
                        (im.turms.server.common.access.client.dto.request.conversation.UpdateConversationSettingsRequest) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.server.common.access.client.dto.request.conversation.UpdateConversationSettingsRequest other) {
            if (other == im.turms.server.common.access.client.dto.request.conversation.UpdateConversationSettingsRequest
                    .getDefaultInstance()) {
                return this;
            }
            if (other.hasUserId()) {
                setUserId(other.getUserId());
            }
            if (other.hasGroupId()) {
                setGroupId(other.getGroupId());
            }
            internalGetMutableSettings().mergeFrom(other.internalGetSettings());
            bitField0_ |= 0x00000004;
            if (customAttributesBuilder_ == null) {
                if (!other.customAttributes_.isEmpty()) {
                    if (customAttributes_.isEmpty()) {
                        customAttributes_ = other.customAttributes_;
                        bitField0_ &= ~0x00000008;
                    } else {
                        ensureCustomAttributesIsMutable();
                        customAttributes_.addAll(other.customAttributes_);
                    }
                    onChanged();
                }
            } else {
                if (!other.customAttributes_.isEmpty()) {
                    if (customAttributesBuilder_.isEmpty()) {
                        customAttributesBuilder_.dispose();
                        customAttributesBuilder_ = null;
                        customAttributes_ = other.customAttributes_;
                        bitField0_ &= ~0x00000008;
                        customAttributesBuilder_ =
                                com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders
                                        ? getCustomAttributesFieldBuilder()
                                        : null;
                    } else {
                        customAttributesBuilder_.addAllMessages(other.customAttributes_);
                    }
                }
            }
            this.mergeUnknownFields(other.getUnknownFields());
            onChanged();
            return this;
        }

        @java.lang.Override
        public boolean isInitialized() {
            return true;
        }

        @java.lang.Override
        public Builder mergeFrom(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            if (extensionRegistry == null) {
                throw new java.lang.NullPointerException();
            }
            try {
                boolean done = false;
                while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0 -> done = true;
                        case 8 -> {
                            userId_ = input.readInt64();
                            bitField0_ |= 0x00000001;
                        } // case 8
                        case 16 -> {
                            groupId_ = input.readInt64();
                            bitField0_ |= 0x00000002;
                        } // case 16
                        case 26 -> {
                            com.google.protobuf.MapEntry<String, im.turms.server.common.access.client.dto.model.common.Value> settings__ =
                                    input.readMessage(
                                            SettingsDefaultEntryHolder.defaultEntry
                                                    .getParserForType(),
                                            extensionRegistry);
                            internalGetMutableSettings().ensureBuilderMap()
                                    .put(settings__.getKey(), settings__.getValue());
                            bitField0_ |= 0x00000004;
                        } // case 26
                        case 122 -> {
                            im.turms.server.common.access.client.dto.model.common.Value m =
                                    input.readMessage(
                                            im.turms.server.common.access.client.dto.model.common.Value
                                                    .parser(),
                                            extensionRegistry);
                            if (customAttributesBuilder_ == null) {
                                ensureCustomAttributesIsMutable();
                                customAttributes_.add(m);
                            } else {
                                customAttributesBuilder_.addMessage(m);
                            }
                        } // case 122
                        default -> {
                            if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                                done = true; // was an endgroup tag
                            }
                        } // default:
                    } // switch (tag)
                } // while (!done)
            } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                throw e.unwrapIOException();
            } finally {
                onChanged();
            } // finally
            return this;
        }

        private int bitField0_;

        private long userId_;

        /**
         * <pre>
         * Query filter
         * </pre>
         *
         * <code>optional int64 user_id = 1;</code>
         *
         * @return Whether the userId field is set.
         */
        @java.lang.Override
        public boolean hasUserId() {
            return ((bitField0_ & 0x00000001) != 0);
        }

        /**
         * <pre>
         * Query filter
         * </pre>
         *
         * <code>optional int64 user_id = 1;</code>
         *
         * @return The userId.
         */
        @java.lang.Override
        public long getUserId() {
            return userId_;
        }

        /**
         * <pre>
         * Query filter
         * </pre>
         *
         * <code>optional int64 user_id = 1;</code>
         *
         * @param value The userId to set.
         * @return This builder for chaining.
         */
        public Builder setUserId(long value) {

            userId_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * Query filter
         * </pre>
         *
         * <code>optional int64 user_id = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearUserId() {
            bitField0_ &= ~0x00000001;
            userId_ = 0L;
            onChanged();
            return this;
        }

        private long groupId_;

        /**
         * <code>optional int64 group_id = 2;</code>
         *
         * @return Whether the groupId field is set.
         */
        @java.lang.Override
        public boolean hasGroupId() {
            return ((bitField0_ & 0x00000002) != 0);
        }

        /**
         * <code>optional int64 group_id = 2;</code>
         *
         * @return The groupId.
         */
        @java.lang.Override
        public long getGroupId() {
            return groupId_;
        }

        /**
         * <code>optional int64 group_id = 2;</code>
         *
         * @param value The groupId to set.
         * @return This builder for chaining.
         */
        public Builder setGroupId(long value) {

            groupId_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>optional int64 group_id = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearGroupId() {
            bitField0_ &= ~0x00000002;
            groupId_ = 0L;
            onChanged();
            return this;
        }

        private static final class SettingsConverter implements
                com.google.protobuf.MapFieldBuilder.Converter<java.lang.String, im.turms.server.common.access.client.dto.model.common.ValueOrBuilder, im.turms.server.common.access.client.dto.model.common.Value> {
            @java.lang.Override
            public im.turms.server.common.access.client.dto.model.common.Value build(
                    im.turms.server.common.access.client.dto.model.common.ValueOrBuilder val) {
                if (val instanceof im.turms.server.common.access.client.dto.model.common.Value) {
                    return (im.turms.server.common.access.client.dto.model.common.Value) val;
                }
                return ((im.turms.server.common.access.client.dto.model.common.Value.Builder) val)
                        .build();
            }

            @java.lang.Override
            public com.google.protobuf.MapEntry<java.lang.String, im.turms.server.common.access.client.dto.model.common.Value> defaultEntry() {
                return SettingsDefaultEntryHolder.defaultEntry;
            }
        }

        private static final SettingsConverter settingsConverter = new SettingsConverter();

        private com.google.protobuf.MapFieldBuilder<java.lang.String, im.turms.server.common.access.client.dto.model.common.ValueOrBuilder, im.turms.server.common.access.client.dto.model.common.Value, im.turms.server.common.access.client.dto.model.common.Value.Builder> settings_;

        private com.google.protobuf.MapFieldBuilder<java.lang.String, im.turms.server.common.access.client.dto.model.common.ValueOrBuilder, im.turms.server.common.access.client.dto.model.common.Value, im.turms.server.common.access.client.dto.model.common.Value.Builder> internalGetSettings() {
            if (settings_ == null) {
                return new com.google.protobuf.MapFieldBuilder<>(settingsConverter);
            }
            return settings_;
        }

        private com.google.protobuf.MapFieldBuilder<java.lang.String, im.turms.server.common.access.client.dto.model.common.ValueOrBuilder, im.turms.server.common.access.client.dto.model.common.Value, im.turms.server.common.access.client.dto.model.common.Value.Builder> internalGetMutableSettings() {
            if (settings_ == null) {
                settings_ = new com.google.protobuf.MapFieldBuilder<>(settingsConverter);
            }
            bitField0_ |= 0x00000004;
            onChanged();
            return settings_;
        }

        public int getSettingsCount() {
            return internalGetSettings().ensureBuilderMap()
                    .size();
        }

        /**
         * <pre>
         * Update
         * </pre>
         *
         * <code>map&lt;string, .im.turms.proto.Value&gt; settings = 3;</code>
         */
        @java.lang.Override
        public boolean containsSettings(java.lang.String key) {
            if (key == null) {
                throw new NullPointerException("map key");
            }
            return internalGetSettings().ensureBuilderMap()
                    .containsKey(key);
        }

        /**
         * Use {@link #getSettingsMap()} instead.
         */
        @java.lang.Override
        @java.lang.Deprecated
        public java.util.Map<java.lang.String, im.turms.server.common.access.client.dto.model.common.Value> getSettings() {
            return getSettingsMap();
        }

        /**
         * <pre>
         * Update
         * </pre>
         *
         * <code>map&lt;string, .im.turms.proto.Value&gt; settings = 3;</code>
         */
        @java.lang.Override
        public java.util.Map<java.lang.String, im.turms.server.common.access.client.dto.model.common.Value> getSettingsMap() {
            return internalGetSettings().getImmutableMap();
        }

        /**
         * <pre>
         * Update
         * </pre>
         *
         * <code>map&lt;string, .im.turms.proto.Value&gt; settings = 3;</code>
         */
        @java.lang.Override
        public /* nullable */
        im.turms.server.common.access.client.dto.model.common.Value getSettingsOrDefault(
                java.lang.String key,
                /* nullable */
                im.turms.server.common.access.client.dto.model.common.Value defaultValue) {
            if (key == null) {
                throw new NullPointerException("map key");
            }
            java.util.Map<java.lang.String, im.turms.server.common.access.client.dto.model.common.ValueOrBuilder> map =
                    internalGetMutableSettings().ensureBuilderMap();
            return map.containsKey(key)
                    ? settingsConverter.build(map.get(key))
                    : defaultValue;
        }

        /**
         * <pre>
         * Update
         * </pre>
         *
         * <code>map&lt;string, .im.turms.proto.Value&gt; settings = 3;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.common.Value getSettingsOrThrow(
                java.lang.String key) {
            if (key == null) {
                throw new NullPointerException("map key");
            }
            java.util.Map<java.lang.String, im.turms.server.common.access.client.dto.model.common.ValueOrBuilder> map =
                    internalGetMutableSettings().ensureBuilderMap();
            if (!map.containsKey(key)) {
                throw new java.lang.IllegalArgumentException();
            }
            return settingsConverter.build(map.get(key));
        }

        public Builder clearSettings() {
            bitField0_ &= ~0x00000004;
            internalGetMutableSettings().clear();
            return this;
        }

        /**
         * <pre>
         * Update
         * </pre>
         *
         * <code>map&lt;string, .im.turms.proto.Value&gt; settings = 3;</code>
         */
        public Builder removeSettings(java.lang.String key) {
            if (key == null) {
                throw new NullPointerException("map key");
            }
            internalGetMutableSettings().ensureBuilderMap()
                    .remove(key);
            return this;
        }

        /**
         * Use alternate mutation accessors instead.
         */
        @java.lang.Deprecated
        public java.util.Map<java.lang.String, im.turms.server.common.access.client.dto.model.common.Value> getMutableSettings() {
            bitField0_ |= 0x00000004;
            return internalGetMutableSettings().ensureMessageMap();
        }

        /**
         * <pre>
         * Update
         * </pre>
         *
         * <code>map&lt;string, .im.turms.proto.Value&gt; settings = 3;</code>
         */
        public Builder putSettings(
                java.lang.String key,
                im.turms.server.common.access.client.dto.model.common.Value value) {
            if (key == null) {
                throw new NullPointerException("map key");
            }
            if (value == null) {
                throw new NullPointerException("map value");
            }
            internalGetMutableSettings().ensureBuilderMap()
                    .put(key, value);
            bitField0_ |= 0x00000004;
            return this;
        }

        /**
         * <pre>
         * Update
         * </pre>
         *
         * <code>map&lt;string, .im.turms.proto.Value&gt; settings = 3;</code>
         */
        public Builder putAllSettings(
                java.util.Map<java.lang.String, im.turms.server.common.access.client.dto.model.common.Value> values) {
            for (java.util.Map.Entry<java.lang.String, im.turms.server.common.access.client.dto.model.common.Value> e : values
                    .entrySet()) {
                if (e.getKey() == null || e.getValue() == null) {
                    throw new NullPointerException();
                }
            }
            internalGetMutableSettings().ensureBuilderMap()
                    .putAll(values);
            bitField0_ |= 0x00000004;
            return this;
        }

        /**
         * <pre>
         * Update
         * </pre>
         *
         * <code>map&lt;string, .im.turms.proto.Value&gt; settings = 3;</code>
         */
        public im.turms.server.common.access.client.dto.model.common.Value.Builder putSettingsBuilderIfAbsent(
                java.lang.String key) {
            java.util.Map<java.lang.String, im.turms.server.common.access.client.dto.model.common.ValueOrBuilder> builderMap =
                    internalGetMutableSettings().ensureBuilderMap();
            im.turms.server.common.access.client.dto.model.common.ValueOrBuilder entry =
                    builderMap.get(key);
            if (entry == null) {
                entry = im.turms.server.common.access.client.dto.model.common.Value.newBuilder();
                builderMap.put(key, entry);
            }
            if (entry instanceof im.turms.server.common.access.client.dto.model.common.Value) {
                entry = ((im.turms.server.common.access.client.dto.model.common.Value) entry)
                        .toBuilder();
                builderMap.put(key, entry);
            }
            return (im.turms.server.common.access.client.dto.model.common.Value.Builder) entry;
        }

        private java.util.List<im.turms.server.common.access.client.dto.model.common.Value> customAttributes_ =
                java.util.Collections.emptyList();

        private void ensureCustomAttributesIsMutable() {
            if ((bitField0_ & 0x00000008) == 0) {
                customAttributes_ = new java.util.ArrayList<>(customAttributes_);
                bitField0_ |= 0x00000008;
            }
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.server.common.access.client.dto.model.common.Value, im.turms.server.common.access.client.dto.model.common.Value.Builder, im.turms.server.common.access.client.dto.model.common.ValueOrBuilder> customAttributesBuilder_;

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public java.util.List<im.turms.server.common.access.client.dto.model.common.Value> getCustomAttributesList() {
            if (customAttributesBuilder_ == null) {
                return java.util.Collections.unmodifiableList(customAttributes_);
            } else {
                return customAttributesBuilder_.getMessageList();
            }
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public int getCustomAttributesCount() {
            if (customAttributesBuilder_ == null) {
                return customAttributes_.size();
            } else {
                return customAttributesBuilder_.getCount();
            }
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public im.turms.server.common.access.client.dto.model.common.Value getCustomAttributes(
                int index) {
            if (customAttributesBuilder_ == null) {
                return customAttributes_.get(index);
            } else {
                return customAttributesBuilder_.getMessage(index);
            }
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder setCustomAttributes(
                int index,
                im.turms.server.common.access.client.dto.model.common.Value value) {
            if (customAttributesBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureCustomAttributesIsMutable();
                customAttributes_.set(index, value);
                onChanged();
            } else {
                customAttributesBuilder_.setMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder setCustomAttributes(
                int index,
                im.turms.server.common.access.client.dto.model.common.Value.Builder builderForValue) {
            if (customAttributesBuilder_ == null) {
                ensureCustomAttributesIsMutable();
                customAttributes_.set(index, builderForValue.build());
                onChanged();
            } else {
                customAttributesBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder addCustomAttributes(
                im.turms.server.common.access.client.dto.model.common.Value value) {
            if (customAttributesBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureCustomAttributesIsMutable();
                customAttributes_.add(value);
                onChanged();
            } else {
                customAttributesBuilder_.addMessage(value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder addCustomAttributes(
                int index,
                im.turms.server.common.access.client.dto.model.common.Value value) {
            if (customAttributesBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureCustomAttributesIsMutable();
                customAttributes_.add(index, value);
                onChanged();
            } else {
                customAttributesBuilder_.addMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder addCustomAttributes(
                im.turms.server.common.access.client.dto.model.common.Value.Builder builderForValue) {
            if (customAttributesBuilder_ == null) {
                ensureCustomAttributesIsMutable();
                customAttributes_.add(builderForValue.build());
                onChanged();
            } else {
                customAttributesBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder addCustomAttributes(
                int index,
                im.turms.server.common.access.client.dto.model.common.Value.Builder builderForValue) {
            if (customAttributesBuilder_ == null) {
                ensureCustomAttributesIsMutable();
                customAttributes_.add(index, builderForValue.build());
                onChanged();
            } else {
                customAttributesBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder addAllCustomAttributes(
                java.lang.Iterable<? extends im.turms.server.common.access.client.dto.model.common.Value> values) {
            if (customAttributesBuilder_ == null) {
                ensureCustomAttributesIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(values, customAttributes_);
                onChanged();
            } else {
                customAttributesBuilder_.addAllMessages(values);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder clearCustomAttributes() {
            if (customAttributesBuilder_ == null) {
                customAttributes_ = java.util.Collections.emptyList();
                bitField0_ &= ~0x00000008;
                onChanged();
            } else {
                customAttributesBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder removeCustomAttributes(int index) {
            if (customAttributesBuilder_ == null) {
                ensureCustomAttributesIsMutable();
                customAttributes_.remove(index);
                onChanged();
            } else {
                customAttributesBuilder_.remove(index);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public im.turms.server.common.access.client.dto.model.common.Value.Builder getCustomAttributesBuilder(
                int index) {
            return getCustomAttributesFieldBuilder().getBuilder(index);
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public im.turms.server.common.access.client.dto.model.common.ValueOrBuilder getCustomAttributesOrBuilder(
                int index) {
            if (customAttributesBuilder_ == null) {
                return customAttributes_.get(index);
            } else {
                return customAttributesBuilder_.getMessageOrBuilder(index);
            }
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public java.util.List<? extends im.turms.server.common.access.client.dto.model.common.ValueOrBuilder> getCustomAttributesOrBuilderList() {
            if (customAttributesBuilder_ != null) {
                return customAttributesBuilder_.getMessageOrBuilderList();
            } else {
                return java.util.Collections.unmodifiableList(customAttributes_);
            }
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public im.turms.server.common.access.client.dto.model.common.Value.Builder addCustomAttributesBuilder() {
            return getCustomAttributesFieldBuilder()
                    .addBuilder(im.turms.server.common.access.client.dto.model.common.Value
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public im.turms.server.common.access.client.dto.model.common.Value.Builder addCustomAttributesBuilder(
                int index) {
            return getCustomAttributesFieldBuilder().addBuilder(index,
                    im.turms.server.common.access.client.dto.model.common.Value
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public java.util.List<im.turms.server.common.access.client.dto.model.common.Value.Builder> getCustomAttributesBuilderList() {
            return getCustomAttributesFieldBuilder().getBuilderList();
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.server.common.access.client.dto.model.common.Value, im.turms.server.common.access.client.dto.model.common.Value.Builder, im.turms.server.common.access.client.dto.model.common.ValueOrBuilder> getCustomAttributesFieldBuilder() {
            if (customAttributesBuilder_ == null) {
                customAttributesBuilder_ = new com.google.protobuf.RepeatedFieldBuilder<>(
                        customAttributes_,
                        ((bitField0_ & 0x00000008) != 0),
                        getParentForChildren(),
                        isClean());
                customAttributes_ = null;
            }
            return customAttributesBuilder_;
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.UpdateConversationSettingsRequest)
    }

    // @@protoc_insertion_point(class_scope:im.turms.proto.UpdateConversationSettingsRequest)
    private static final im.turms.server.common.access.client.dto.request.conversation.UpdateConversationSettingsRequest DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE =
                new im.turms.server.common.access.client.dto.request.conversation.UpdateConversationSettingsRequest();
    }

    public static im.turms.server.common.access.client.dto.request.conversation.UpdateConversationSettingsRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<UpdateConversationSettingsRequest> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public UpdateConversationSettingsRequest parsePartialFrom(
                        com.google.protobuf.CodedInputStream input,
                        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                        throws com.google.protobuf.InvalidProtocolBufferException {
                    Builder builder = newBuilder();
                    try {
                        builder.mergeFrom(input, extensionRegistry);
                    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(builder.buildPartial());
                    } catch (com.google.protobuf.UninitializedMessageException e) {
                        throw e.asInvalidProtocolBufferException()
                                .setUnfinishedMessage(builder.buildPartial());
                    } catch (java.io.IOException e) {
                        throw new com.google.protobuf.InvalidProtocolBufferException(e)
                                .setUnfinishedMessage(builder.buildPartial());
                    }
                    return builder.buildPartial();
                }
            };

    public static com.google.protobuf.Parser<UpdateConversationSettingsRequest> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<UpdateConversationSettingsRequest> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.conversation.UpdateConversationSettingsRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}