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

package im.turms.server.common.access.client.dto.model.user;

/**
 * Protobuf type {@code im.turms.proto.UserSettings}
 */
public final class UserSettings extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.UserSettings)
        UserSettingsOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 27,
                /* patch= */ 0,
                /* suffix= */ "",
                UserSettings.class.getName());
    }

    // Use UserSettings.newBuilder() to construct.
    private UserSettings(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private UserSettings() {
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.server.common.access.client.dto.model.user.UserSettingsOuterClass.internal_static_im_turms_proto_UserSettings_descriptor;
    }

    @SuppressWarnings({"rawtypes"})
    @java.lang.Override
    protected com.google.protobuf.MapFieldReflectionAccessor internalGetMapFieldReflection(
            int number) {
        if (number == 1) {
            return internalGetSettings();
        }
        throw new RuntimeException(
                "Invalid map field number: "
                        + number);
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.server.common.access.client.dto.model.user.UserSettingsOuterClass.internal_static_im_turms_proto_UserSettings_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.server.common.access.client.dto.model.user.UserSettings.class,
                        im.turms.server.common.access.client.dto.model.user.UserSettings.Builder.class);
    }

    private int bitField0_;
    public static final int SETTINGS_FIELD_NUMBER = 1;

    private static final class SettingsDefaultEntryHolder {
        static final com.google.protobuf.MapEntry<java.lang.String, im.turms.server.common.access.client.dto.model.common.Value> defaultEntry =
                com.google.protobuf.MapEntry.newDefaultInstance(
                        im.turms.server.common.access.client.dto.model.user.UserSettingsOuterClass.internal_static_im_turms_proto_UserSettings_SettingsEntry_descriptor,
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
     * <code>map&lt;string, .im.turms.proto.Value&gt; settings = 1;</code>
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
     * <code>map&lt;string, .im.turms.proto.Value&gt; settings = 1;</code>
     */
    @java.lang.Override
    public java.util.Map<java.lang.String, im.turms.server.common.access.client.dto.model.common.Value> getSettingsMap() {
        return internalGetSettings().getMap();
    }

    /**
     * <code>map&lt;string, .im.turms.proto.Value&gt; settings = 1;</code>
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
     * <code>map&lt;string, .im.turms.proto.Value&gt; settings = 1;</code>
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

    public static final int LAST_UPDATED_DATE_FIELD_NUMBER = 2;
    private long lastUpdatedDate_ = 0L;

    /**
     * <code>optional int64 last_updated_date = 2;</code>
     *
     * @return Whether the lastUpdatedDate field is set.
     */
    @java.lang.Override
    public boolean hasLastUpdatedDate() {
        return ((bitField0_ & 0x00000001) != 0);
    }

    /**
     * <code>optional int64 last_updated_date = 2;</code>
     *
     * @return The lastUpdatedDate.
     */
    @java.lang.Override
    public long getLastUpdatedDate() {
        return lastUpdatedDate_;
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
        com.google.protobuf.GeneratedMessage.serializeStringMapTo(output,
                internalGetSettings(),
                SettingsDefaultEntryHolder.defaultEntry,
                1);
        if (((bitField0_ & 0x00000001) != 0)) {
            output.writeInt64(2, lastUpdatedDate_);
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
        for (java.util.Map.Entry<java.lang.String, im.turms.server.common.access.client.dto.model.common.Value> entry : internalGetSettings()
                .getMap()
                .entrySet()) {
            com.google.protobuf.MapEntry<java.lang.String, im.turms.server.common.access.client.dto.model.common.Value> settings__ =
                    SettingsDefaultEntryHolder.defaultEntry.newBuilderForType()
                            .setKey(entry.getKey())
                            .setValue(entry.getValue())
                            .build();
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(1, settings__);
        }
        if (((bitField0_ & 0x00000001) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(2, lastUpdatedDate_);
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
        if (!(obj instanceof UserSettings other)) {
            return super.equals(obj);
        }

        if (!internalGetSettings().equals(other.internalGetSettings())) {
            return false;
        }
        if (hasLastUpdatedDate() != other.hasLastUpdatedDate()) {
            return false;
        }
        if (hasLastUpdatedDate()) {
            if (getLastUpdatedDate() != other.getLastUpdatedDate()) {
                return false;
            }
        }
        return getUnknownFields().equals(other.getUnknownFields());
    }

    @java.lang.Override
    public int hashCode() {
        if (memoizedHashCode != 0) {
            return memoizedHashCode;
        }
        int hash = 41;
        hash = (19 * hash) + getDescriptor().hashCode();
        if (!internalGetSettings().getMap()
                .isEmpty()) {
            hash = (37 * hash) + SETTINGS_FIELD_NUMBER;
            hash = (53 * hash) + internalGetSettings().hashCode();
        }
        if (hasLastUpdatedDate()) {
            hash = (37 * hash) + LAST_UPDATED_DATE_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getLastUpdatedDate());
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.server.common.access.client.dto.model.user.UserSettings parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserSettings parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserSettings parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserSettings parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserSettings parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserSettings parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserSettings parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserSettings parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserSettings parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserSettings parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserSettings parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.user.UserSettings parseFrom(
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
            im.turms.server.common.access.client.dto.model.user.UserSettings prototype) {
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
     * Protobuf type {@code im.turms.proto.UserSettings}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.UserSettings)
            im.turms.server.common.access.client.dto.model.user.UserSettingsOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.server.common.access.client.dto.model.user.UserSettingsOuterClass.internal_static_im_turms_proto_UserSettings_descriptor;
        }

        @SuppressWarnings({"rawtypes"})
        protected com.google.protobuf.MapFieldReflectionAccessor internalGetMapFieldReflection(
                int number) {
            if (number == 1) {
                return internalGetSettings();
            }
            throw new RuntimeException(
                    "Invalid map field number: "
                            + number);
        }

        @SuppressWarnings({"rawtypes"})
        protected com.google.protobuf.MapFieldReflectionAccessor internalGetMutableMapFieldReflection(
                int number) {
            if (number == 1) {
                return internalGetMutableSettings();
            }
            throw new RuntimeException(
                    "Invalid map field number: "
                            + number);
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.server.common.access.client.dto.model.user.UserSettingsOuterClass.internal_static_im_turms_proto_UserSettings_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.server.common.access.client.dto.model.user.UserSettings.class,
                            im.turms.server.common.access.client.dto.model.user.UserSettings.Builder.class);
        }

        // Construct using
        // im.turms.server.common.access.client.dto.model.user.UserSettings.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            internalGetMutableSettings().clear();
            lastUpdatedDate_ = 0L;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.server.common.access.client.dto.model.user.UserSettingsOuterClass.internal_static_im_turms_proto_UserSettings_descriptor;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.user.UserSettings getDefaultInstanceForType() {
            return im.turms.server.common.access.client.dto.model.user.UserSettings
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.user.UserSettings build() {
            im.turms.server.common.access.client.dto.model.user.UserSettings result =
                    buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.user.UserSettings buildPartial() {
            im.turms.server.common.access.client.dto.model.user.UserSettings result =
                    new im.turms.server.common.access.client.dto.model.user.UserSettings(this);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartial0(
                im.turms.server.common.access.client.dto.model.user.UserSettings result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.settings_ =
                        internalGetSettings().build(SettingsDefaultEntryHolder.defaultEntry);
            }
            int to_bitField0_ = 0;
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.lastUpdatedDate_ = lastUpdatedDate_;
                to_bitField0_ |= 0x00000001;
            }
            result.bitField0_ |= to_bitField0_;
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.server.common.access.client.dto.model.user.UserSettings) {
                return mergeFrom(
                        (im.turms.server.common.access.client.dto.model.user.UserSettings) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.server.common.access.client.dto.model.user.UserSettings other) {
            if (other == im.turms.server.common.access.client.dto.model.user.UserSettings
                    .getDefaultInstance()) {
                return this;
            }
            internalGetMutableSettings().mergeFrom(other.internalGetSettings());
            bitField0_ |= 0x00000001;
            if (other.hasLastUpdatedDate()) {
                setLastUpdatedDate(other.getLastUpdatedDate());
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
                        case 10 -> {
                            com.google.protobuf.MapEntry<String, im.turms.server.common.access.client.dto.model.common.Value> settings__ =
                                    input.readMessage(
                                            SettingsDefaultEntryHolder.defaultEntry
                                                    .getParserForType(),
                                            extensionRegistry);
                            internalGetMutableSettings().ensureBuilderMap()
                                    .put(settings__.getKey(), settings__.getValue());
                            bitField0_ |= 0x00000001;
                        } // case 10
                        case 16 -> {
                            lastUpdatedDate_ = input.readInt64();
                            bitField0_ |= 0x00000002;
                        } // case 16
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
            bitField0_ |= 0x00000001;
            onChanged();
            return settings_;
        }

        public int getSettingsCount() {
            return internalGetSettings().ensureBuilderMap()
                    .size();
        }

        /**
         * <code>map&lt;string, .im.turms.proto.Value&gt; settings = 1;</code>
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
         * <code>map&lt;string, .im.turms.proto.Value&gt; settings = 1;</code>
         */
        @java.lang.Override
        public java.util.Map<java.lang.String, im.turms.server.common.access.client.dto.model.common.Value> getSettingsMap() {
            return internalGetSettings().getImmutableMap();
        }

        /**
         * <code>map&lt;string, .im.turms.proto.Value&gt; settings = 1;</code>
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
         * <code>map&lt;string, .im.turms.proto.Value&gt; settings = 1;</code>
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
            bitField0_ &= ~0x00000001;
            internalGetMutableSettings().clear();
            return this;
        }

        /**
         * <code>map&lt;string, .im.turms.proto.Value&gt; settings = 1;</code>
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
            bitField0_ |= 0x00000001;
            return internalGetMutableSettings().ensureMessageMap();
        }

        /**
         * <code>map&lt;string, .im.turms.proto.Value&gt; settings = 1;</code>
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
            bitField0_ |= 0x00000001;
            return this;
        }

        /**
         * <code>map&lt;string, .im.turms.proto.Value&gt; settings = 1;</code>
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
            bitField0_ |= 0x00000001;
            return this;
        }

        /**
         * <code>map&lt;string, .im.turms.proto.Value&gt; settings = 1;</code>
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

        private long lastUpdatedDate_;

        /**
         * <code>optional int64 last_updated_date = 2;</code>
         *
         * @return Whether the lastUpdatedDate field is set.
         */
        @java.lang.Override
        public boolean hasLastUpdatedDate() {
            return ((bitField0_ & 0x00000002) != 0);
        }

        /**
         * <code>optional int64 last_updated_date = 2;</code>
         *
         * @return The lastUpdatedDate.
         */
        @java.lang.Override
        public long getLastUpdatedDate() {
            return lastUpdatedDate_;
        }

        /**
         * <code>optional int64 last_updated_date = 2;</code>
         *
         * @param value The lastUpdatedDate to set.
         * @return This builder for chaining.
         */
        public Builder setLastUpdatedDate(long value) {

            lastUpdatedDate_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>optional int64 last_updated_date = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearLastUpdatedDate() {
            bitField0_ &= ~0x00000002;
            lastUpdatedDate_ = 0L;
            onChanged();
            return this;
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.UserSettings)
    }

    // @@protoc_insertion_point(class_scope:im.turms.proto.UserSettings)
    private static final im.turms.server.common.access.client.dto.model.user.UserSettings DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE = new im.turms.server.common.access.client.dto.model.user.UserSettings();
    }

    public static im.turms.server.common.access.client.dto.model.user.UserSettings getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<UserSettings> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public UserSettings parsePartialFrom(
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

    public static com.google.protobuf.Parser<UserSettings> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<UserSettings> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.user.UserSettings getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}