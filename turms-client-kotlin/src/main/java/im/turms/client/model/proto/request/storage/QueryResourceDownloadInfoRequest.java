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

package im.turms.client.model.proto.request.storage;

/**
 * Protobuf type {@code im.turms.proto.QueryResourceDownloadInfoRequest}
 */
public final class QueryResourceDownloadInfoRequest extends
        com.google.protobuf.GeneratedMessageLite<QueryResourceDownloadInfoRequest, QueryResourceDownloadInfoRequest.Builder>
        implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.QueryResourceDownloadInfoRequest)
        QueryResourceDownloadInfoRequestOrBuilder {
    private QueryResourceDownloadInfoRequest() {
        idStr_ = "";
        customAttributes_ = emptyProtobufList();
    }

    private int bitField0_;
    public static final int TYPE_FIELD_NUMBER = 1;
    private int type_;

    /**
     * <code>.im.turms.proto.StorageResourceType type = 1;</code>
     *
     * @return The enum numeric value on the wire for type.
     */
    @java.lang.Override
    public int getTypeValue() {
        return type_;
    }

    /**
     * <code>.im.turms.proto.StorageResourceType type = 1;</code>
     *
     * @return The type.
     */
    @java.lang.Override
    public im.turms.client.model.proto.constant.StorageResourceType getType() {
        im.turms.client.model.proto.constant.StorageResourceType result =
                im.turms.client.model.proto.constant.StorageResourceType.forNumber(type_);
        return result == null
                ? im.turms.client.model.proto.constant.StorageResourceType.UNRECOGNIZED
                : result;
    }

    /**
     * <code>.im.turms.proto.StorageResourceType type = 1;</code>
     *
     * @param value The enum numeric value on the wire for type to set.
     */
    private void setTypeValue(int value) {
        type_ = value;
    }

    /**
     * <code>.im.turms.proto.StorageResourceType type = 1;</code>
     *
     * @param value The type to set.
     */
    private void setType(im.turms.client.model.proto.constant.StorageResourceType value) {
        type_ = value.getNumber();

    }

    /**
     * <code>.im.turms.proto.StorageResourceType type = 1;</code>
     */
    private void clearType() {

        type_ = 0;
    }

    public static final int ID_NUM_FIELD_NUMBER = 2;
    private long idNum_;

    /**
     * <code>optional int64 id_num = 2;</code>
     *
     * @return Whether the idNum field is set.
     */
    @java.lang.Override
    public boolean hasIdNum() {
        return ((bitField0_ & 0x00000001) != 0);
    }

    /**
     * <code>optional int64 id_num = 2;</code>
     *
     * @return The idNum.
     */
    @java.lang.Override
    public long getIdNum() {
        return idNum_;
    }

    /**
     * <code>optional int64 id_num = 2;</code>
     *
     * @param value The idNum to set.
     */
    private void setIdNum(long value) {
        bitField0_ |= 0x00000001;
        idNum_ = value;
    }

    /**
     * <code>optional int64 id_num = 2;</code>
     */
    private void clearIdNum() {
        bitField0_ &= ~0x00000001;
        idNum_ = 0L;
    }

    public static final int ID_STR_FIELD_NUMBER = 3;
    private java.lang.String idStr_;

    /**
     * <code>optional string id_str = 3;</code>
     *
     * @return Whether the idStr field is set.
     */
    @java.lang.Override
    public boolean hasIdStr() {
        return ((bitField0_ & 0x00000002) != 0);
    }

    /**
     * <code>optional string id_str = 3;</code>
     *
     * @return The idStr.
     */
    @java.lang.Override
    public java.lang.String getIdStr() {
        return idStr_;
    }

    /**
     * <code>optional string id_str = 3;</code>
     *
     * @return The bytes for idStr.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getIdStrBytes() {
        return com.google.protobuf.ByteString.copyFromUtf8(idStr_);
    }

    /**
     * <code>optional string id_str = 3;</code>
     *
     * @param value The idStr to set.
     */
    private void setIdStr(java.lang.String value) {
        java.lang.Class<?> valueClass = value.getClass();
        bitField0_ |= 0x00000002;
        idStr_ = value;
    }

    /**
     * <code>optional string id_str = 3;</code>
     */
    private void clearIdStr() {
        bitField0_ &= ~0x00000002;
        idStr_ = getDefaultInstance().getIdStr();
    }

    /**
     * <code>optional string id_str = 3;</code>
     *
     * @param value The bytes for idStr to set.
     */
    private void setIdStrBytes(com.google.protobuf.ByteString value) {
        checkByteStringIsUtf8(value);
        idStr_ = value.toStringUtf8();
        bitField0_ |= 0x00000002;
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

    public static im.turms.client.model.proto.request.storage.QueryResourceDownloadInfoRequest parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.storage.QueryResourceDownloadInfoRequest parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.storage.QueryResourceDownloadInfoRequest parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.storage.QueryResourceDownloadInfoRequest parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.storage.QueryResourceDownloadInfoRequest parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.storage.QueryResourceDownloadInfoRequest parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.storage.QueryResourceDownloadInfoRequest parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.storage.QueryResourceDownloadInfoRequest parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.storage.QueryResourceDownloadInfoRequest parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.storage.QueryResourceDownloadInfoRequest parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.storage.QueryResourceDownloadInfoRequest parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.storage.QueryResourceDownloadInfoRequest parseFrom(
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
            im.turms.client.model.proto.request.storage.QueryResourceDownloadInfoRequest prototype) {
        return DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /**
     * Protobuf type {@code im.turms.proto.QueryResourceDownloadInfoRequest}
     */
    public static final class Builder extends
            com.google.protobuf.GeneratedMessageLite.Builder<im.turms.client.model.proto.request.storage.QueryResourceDownloadInfoRequest, Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.QueryResourceDownloadInfoRequest)
            im.turms.client.model.proto.request.storage.QueryResourceDownloadInfoRequestOrBuilder {
        // Construct using
        // im.turms.client.model.proto.request.storage.QueryResourceDownloadInfoRequest.newBuilder()
        private Builder() {
            super(DEFAULT_INSTANCE);
        }

        /**
         * <code>.im.turms.proto.StorageResourceType type = 1;</code>
         *
         * @return The enum numeric value on the wire for type.
         */
        @java.lang.Override
        public int getTypeValue() {
            return instance.getTypeValue();
        }

        /**
         * <code>.im.turms.proto.StorageResourceType type = 1;</code>
         *
         * @param value The type to set.
         * @return This builder for chaining.
         */
        public Builder setTypeValue(int value) {
            copyOnWrite();
            instance.setTypeValue(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.StorageResourceType type = 1;</code>
         *
         * @return The type.
         */
        @java.lang.Override
        public im.turms.client.model.proto.constant.StorageResourceType getType() {
            return instance.getType();
        }

        /**
         * <code>.im.turms.proto.StorageResourceType type = 1;</code>
         *
         * @param value The enum numeric value on the wire for type to set.
         * @return This builder for chaining.
         */
        public Builder setType(im.turms.client.model.proto.constant.StorageResourceType value) {
            copyOnWrite();
            instance.setType(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.StorageResourceType type = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearType() {
            copyOnWrite();
            instance.clearType();
            return this;
        }

        /**
         * <code>optional int64 id_num = 2;</code>
         *
         * @return Whether the idNum field is set.
         */
        @java.lang.Override
        public boolean hasIdNum() {
            return instance.hasIdNum();
        }

        /**
         * <code>optional int64 id_num = 2;</code>
         *
         * @return The idNum.
         */
        @java.lang.Override
        public long getIdNum() {
            return instance.getIdNum();
        }

        /**
         * <code>optional int64 id_num = 2;</code>
         *
         * @param value The idNum to set.
         * @return This builder for chaining.
         */
        public Builder setIdNum(long value) {
            copyOnWrite();
            instance.setIdNum(value);
            return this;
        }

        /**
         * <code>optional int64 id_num = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearIdNum() {
            copyOnWrite();
            instance.clearIdNum();
            return this;
        }

        /**
         * <code>optional string id_str = 3;</code>
         *
         * @return Whether the idStr field is set.
         */
        @java.lang.Override
        public boolean hasIdStr() {
            return instance.hasIdStr();
        }

        /**
         * <code>optional string id_str = 3;</code>
         *
         * @return The idStr.
         */
        @java.lang.Override
        public java.lang.String getIdStr() {
            return instance.getIdStr();
        }

        /**
         * <code>optional string id_str = 3;</code>
         *
         * @return The bytes for idStr.
         */
        @java.lang.Override
        public com.google.protobuf.ByteString getIdStrBytes() {
            return instance.getIdStrBytes();
        }

        /**
         * <code>optional string id_str = 3;</code>
         *
         * @param value The idStr to set.
         * @return This builder for chaining.
         */
        public Builder setIdStr(java.lang.String value) {
            copyOnWrite();
            instance.setIdStr(value);
            return this;
        }

        /**
         * <code>optional string id_str = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearIdStr() {
            copyOnWrite();
            instance.clearIdStr();
            return this;
        }

        /**
         * <code>optional string id_str = 3;</code>
         *
         * @param value The bytes for idStr to set.
         * @return This builder for chaining.
         */
        public Builder setIdStrBytes(com.google.protobuf.ByteString value) {
            copyOnWrite();
            instance.setIdStrBytes(value);
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

        // @@protoc_insertion_point(builder_scope:im.turms.proto.QueryResourceDownloadInfoRequest)
    }

    @java.lang.Override
    @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
    protected java.lang.Object dynamicMethod(
            com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
            java.lang.Object arg0,
            java.lang.Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE: {
                return new im.turms.client.model.proto.request.storage.QueryResourceDownloadInfoRequest();
            }
            case NEW_BUILDER: {
                return new Builder();
            }
            case BUILD_MESSAGE_INFO: {
                java.lang.Object[] objects = new java.lang.Object[]{"bitField0_",
                        "type_",
                        "idNum_",
                        "idStr_",
                        "customAttributes_",
                        im.turms.client.model.proto.model.common.Value.class,};
                java.lang.String info =
                        "\u0000\u0004\u0000\u0001\u0001\u000f\u0004\u0000\u0001\u0000\u0001\f\u0002\u1002"
                                + "\u0000\u0003\u1208\u0001\u000f\u001b";
                return newMessageInfo(DEFAULT_INSTANCE, info, objects);
            }
            // fall through
            case GET_DEFAULT_INSTANCE: {
                return DEFAULT_INSTANCE;
            }
            case GET_PARSER: {
                com.google.protobuf.Parser<im.turms.client.model.proto.request.storage.QueryResourceDownloadInfoRequest> parser =
                        PARSER;
                if (parser == null) {
                    synchronized (im.turms.client.model.proto.request.storage.QueryResourceDownloadInfoRequest.class) {
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

    // @@protoc_insertion_point(class_scope:im.turms.proto.QueryResourceDownloadInfoRequest)
    private static final im.turms.client.model.proto.request.storage.QueryResourceDownloadInfoRequest DEFAULT_INSTANCE;
    static {
        QueryResourceDownloadInfoRequest defaultInstance = new QueryResourceDownloadInfoRequest();
        // New instances are implicitly immutable so no need to make
        // immutable.
        DEFAULT_INSTANCE = defaultInstance;
        com.google.protobuf.GeneratedMessageLite
                .registerDefaultInstance(QueryResourceDownloadInfoRequest.class, defaultInstance);
    }

    public static im.turms.client.model.proto.request.storage.QueryResourceDownloadInfoRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static volatile com.google.protobuf.Parser<QueryResourceDownloadInfoRequest> PARSER;

    public static com.google.protobuf.Parser<QueryResourceDownloadInfoRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}