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

package im.turms.client.model.proto.request.user;

/**
 * Protobuf type {@code im.turms.proto.QueryUserSettingsRequest}
 */
public final class QueryUserSettingsRequest extends
        com.google.protobuf.GeneratedMessageLite<QueryUserSettingsRequest, QueryUserSettingsRequest.Builder>
        implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.QueryUserSettingsRequest)
        QueryUserSettingsRequestOrBuilder {
    private QueryUserSettingsRequest() {
        names_ = com.google.protobuf.GeneratedMessageLite.emptyProtobufList();
        customAttributes_ = emptyProtobufList();
    }

    private int bitField0_;
    public static final int NAMES_FIELD_NUMBER = 1;
    private com.google.protobuf.Internal.ProtobufList<java.lang.String> names_;

    /**
     * <code>repeated string names = 1;</code>
     *
     * @return A list containing the names.
     */
    @java.lang.Override
    public java.util.List<java.lang.String> getNamesList() {
        return names_;
    }

    /**
     * <code>repeated string names = 1;</code>
     *
     * @return The count of names.
     */
    @java.lang.Override
    public int getNamesCount() {
        return names_.size();
    }

    /**
     * <code>repeated string names = 1;</code>
     *
     * @param index The index of the element to return.
     * @return The names at the given index.
     */
    @java.lang.Override
    public java.lang.String getNames(int index) {
        return names_.get(index);
    }

    /**
     * <code>repeated string names = 1;</code>
     *
     * @param index The index of the value to return.
     * @return The bytes of the names at the given index.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getNamesBytes(int index) {
        return com.google.protobuf.ByteString.copyFromUtf8(names_.get(index));
    }

    private void ensureNamesIsMutable() {
        com.google.protobuf.Internal.ProtobufList<java.lang.String> tmp = names_;
        if (!tmp.isModifiable()) {
            names_ = com.google.protobuf.GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /**
     * <code>repeated string names = 1;</code>
     *
     * @param index The index to set the value at.
     * @param value The names to set.
     */
    private void setNames(int index, java.lang.String value) {
        java.lang.Class<?> valueClass = value.getClass();
        ensureNamesIsMutable();
        names_.set(index, value);
    }

    /**
     * <code>repeated string names = 1;</code>
     *
     * @param value The names to add.
     */
    private void addNames(java.lang.String value) {
        java.lang.Class<?> valueClass = value.getClass();
        ensureNamesIsMutable();
        names_.add(value);
    }

    /**
     * <code>repeated string names = 1;</code>
     *
     * @param values The names to add.
     */
    private void addAllNames(java.lang.Iterable<java.lang.String> values) {
        ensureNamesIsMutable();
        com.google.protobuf.AbstractMessageLite.addAll(values, names_);
    }

    /**
     * <code>repeated string names = 1;</code>
     */
    private void clearNames() {
        names_ = com.google.protobuf.GeneratedMessageLite.emptyProtobufList();
    }

    /**
     * <code>repeated string names = 1;</code>
     *
     * @param value The bytes of the names to add.
     */
    private void addNamesBytes(com.google.protobuf.ByteString value) {
        checkByteStringIsUtf8(value);
        ensureNamesIsMutable();
        names_.add(value.toStringUtf8());
    }

    public static final int LAST_UPDATED_DATE_START_FIELD_NUMBER = 2;
    private long lastUpdatedDateStart_;

    /**
     * <code>optional int64 last_updated_date_start = 2;</code>
     *
     * @return Whether the lastUpdatedDateStart field is set.
     */
    @java.lang.Override
    public boolean hasLastUpdatedDateStart() {
        return ((bitField0_ & 0x00000001) != 0);
    }

    /**
     * <code>optional int64 last_updated_date_start = 2;</code>
     *
     * @return The lastUpdatedDateStart.
     */
    @java.lang.Override
    public long getLastUpdatedDateStart() {
        return lastUpdatedDateStart_;
    }

    /**
     * <code>optional int64 last_updated_date_start = 2;</code>
     *
     * @param value The lastUpdatedDateStart to set.
     */
    private void setLastUpdatedDateStart(long value) {
        bitField0_ |= 0x00000001;
        lastUpdatedDateStart_ = value;
    }

    /**
     * <code>optional int64 last_updated_date_start = 2;</code>
     */
    private void clearLastUpdatedDateStart() {
        bitField0_ &= ~0x00000001;
        lastUpdatedDateStart_ = 0L;
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

    public static im.turms.client.model.proto.request.user.QueryUserSettingsRequest parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.user.QueryUserSettingsRequest parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.user.QueryUserSettingsRequest parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.user.QueryUserSettingsRequest parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.user.QueryUserSettingsRequest parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.user.QueryUserSettingsRequest parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.user.QueryUserSettingsRequest parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.user.QueryUserSettingsRequest parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.user.QueryUserSettingsRequest parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.user.QueryUserSettingsRequest parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.user.QueryUserSettingsRequest parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.user.QueryUserSettingsRequest parseFrom(
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
            im.turms.client.model.proto.request.user.QueryUserSettingsRequest prototype) {
        return DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /**
     * Protobuf type {@code im.turms.proto.QueryUserSettingsRequest}
     */
    public static final class Builder extends
            com.google.protobuf.GeneratedMessageLite.Builder<im.turms.client.model.proto.request.user.QueryUserSettingsRequest, Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.QueryUserSettingsRequest)
            im.turms.client.model.proto.request.user.QueryUserSettingsRequestOrBuilder {
        // Construct using
        // im.turms.client.model.proto.request.user.QueryUserSettingsRequest.newBuilder()
        private Builder() {
            super(DEFAULT_INSTANCE);
        }

        /**
         * <code>repeated string names = 1;</code>
         *
         * @return A list containing the names.
         */
        @java.lang.Override
        public java.util.List<java.lang.String> getNamesList() {
            return java.util.Collections.unmodifiableList(instance.getNamesList());
        }

        /**
         * <code>repeated string names = 1;</code>
         *
         * @return The count of names.
         */
        @java.lang.Override
        public int getNamesCount() {
            return instance.getNamesCount();
        }

        /**
         * <code>repeated string names = 1;</code>
         *
         * @param index The index of the element to return.
         * @return The names at the given index.
         */
        @java.lang.Override
        public java.lang.String getNames(int index) {
            return instance.getNames(index);
        }

        /**
         * <code>repeated string names = 1;</code>
         *
         * @param index The index of the value to return.
         * @return The bytes of the names at the given index.
         */
        @java.lang.Override
        public com.google.protobuf.ByteString getNamesBytes(int index) {
            return instance.getNamesBytes(index);
        }

        /**
         * <code>repeated string names = 1;</code>
         *
         * @param index The index to set the value at.
         * @param value The names to set.
         * @return This builder for chaining.
         */
        public Builder setNames(int index, java.lang.String value) {
            copyOnWrite();
            instance.setNames(index, value);
            return this;
        }

        /**
         * <code>repeated string names = 1;</code>
         *
         * @param value The names to add.
         * @return This builder for chaining.
         */
        public Builder addNames(java.lang.String value) {
            copyOnWrite();
            instance.addNames(value);
            return this;
        }

        /**
         * <code>repeated string names = 1;</code>
         *
         * @param values The names to add.
         * @return This builder for chaining.
         */
        public Builder addAllNames(java.lang.Iterable<java.lang.String> values) {
            copyOnWrite();
            instance.addAllNames(values);
            return this;
        }

        /**
         * <code>repeated string names = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearNames() {
            copyOnWrite();
            instance.clearNames();
            return this;
        }

        /**
         * <code>repeated string names = 1;</code>
         *
         * @param value The bytes of the names to add.
         * @return This builder for chaining.
         */
        public Builder addNamesBytes(com.google.protobuf.ByteString value) {
            copyOnWrite();
            instance.addNamesBytes(value);
            return this;
        }

        /**
         * <code>optional int64 last_updated_date_start = 2;</code>
         *
         * @return Whether the lastUpdatedDateStart field is set.
         */
        @java.lang.Override
        public boolean hasLastUpdatedDateStart() {
            return instance.hasLastUpdatedDateStart();
        }

        /**
         * <code>optional int64 last_updated_date_start = 2;</code>
         *
         * @return The lastUpdatedDateStart.
         */
        @java.lang.Override
        public long getLastUpdatedDateStart() {
            return instance.getLastUpdatedDateStart();
        }

        /**
         * <code>optional int64 last_updated_date_start = 2;</code>
         *
         * @param value The lastUpdatedDateStart to set.
         * @return This builder for chaining.
         */
        public Builder setLastUpdatedDateStart(long value) {
            copyOnWrite();
            instance.setLastUpdatedDateStart(value);
            return this;
        }

        /**
         * <code>optional int64 last_updated_date_start = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearLastUpdatedDateStart() {
            copyOnWrite();
            instance.clearLastUpdatedDateStart();
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

        // @@protoc_insertion_point(builder_scope:im.turms.proto.QueryUserSettingsRequest)
    }

    @java.lang.Override
    @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
    protected java.lang.Object dynamicMethod(
            com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
            java.lang.Object arg0,
            java.lang.Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE: {
                return new im.turms.client.model.proto.request.user.QueryUserSettingsRequest();
            }
            case NEW_BUILDER: {
                return new Builder();
            }
            case BUILD_MESSAGE_INFO: {
                java.lang.Object[] objects = new java.lang.Object[]{"bitField0_",
                        "names_",
                        "lastUpdatedDateStart_",
                        "customAttributes_",
                        im.turms.client.model.proto.model.common.Value.class,};
                java.lang.String info =
                        "\u0000\u0003\u0000\u0001\u0001\u000f\u0003\u0000\u0002\u0000\u0001\u021a\u0002\u1002"
                                + "\u0000\u000f\u001b";
                return newMessageInfo(DEFAULT_INSTANCE, info, objects);
            }
            // fall through
            case GET_DEFAULT_INSTANCE: {
                return DEFAULT_INSTANCE;
            }
            case GET_PARSER: {
                com.google.protobuf.Parser<im.turms.client.model.proto.request.user.QueryUserSettingsRequest> parser =
                        PARSER;
                if (parser == null) {
                    synchronized (im.turms.client.model.proto.request.user.QueryUserSettingsRequest.class) {
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

    // @@protoc_insertion_point(class_scope:im.turms.proto.QueryUserSettingsRequest)
    private static final im.turms.client.model.proto.request.user.QueryUserSettingsRequest DEFAULT_INSTANCE;
    static {
        QueryUserSettingsRequest defaultInstance = new QueryUserSettingsRequest();
        // New instances are implicitly immutable so no need to make
        // immutable.
        DEFAULT_INSTANCE = defaultInstance;
        com.google.protobuf.GeneratedMessageLite
                .registerDefaultInstance(QueryUserSettingsRequest.class, defaultInstance);
    }

    public static im.turms.client.model.proto.request.user.QueryUserSettingsRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static volatile com.google.protobuf.Parser<QueryUserSettingsRequest> PARSER;

    public static com.google.protobuf.Parser<QueryUserSettingsRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}