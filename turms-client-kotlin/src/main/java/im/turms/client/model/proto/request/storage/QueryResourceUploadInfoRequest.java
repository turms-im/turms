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
 * Protobuf type {@code im.turms.proto.QueryResourceUploadInfoRequest}
 */
public final class QueryResourceUploadInfoRequest extends
        com.google.protobuf.GeneratedMessageLite<QueryResourceUploadInfoRequest, QueryResourceUploadInfoRequest.Builder>
        implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.QueryResourceUploadInfoRequest)
        QueryResourceUploadInfoRequestOrBuilder {
    private QueryResourceUploadInfoRequest() {
        idStr_ = "";
        name_ = "";
        mediaType_ = "";
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

    public static final int NAME_FIELD_NUMBER = 4;
    private java.lang.String name_;

    /**
     * <code>optional string name = 4;</code>
     *
     * @return Whether the name field is set.
     */
    @java.lang.Override
    public boolean hasName() {
        return ((bitField0_ & 0x00000004) != 0);
    }

    /**
     * <code>optional string name = 4;</code>
     *
     * @return The name.
     */
    @java.lang.Override
    public java.lang.String getName() {
        return name_;
    }

    /**
     * <code>optional string name = 4;</code>
     *
     * @return The bytes for name.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getNameBytes() {
        return com.google.protobuf.ByteString.copyFromUtf8(name_);
    }

    /**
     * <code>optional string name = 4;</code>
     *
     * @param value The name to set.
     */
    private void setName(java.lang.String value) {
        java.lang.Class<?> valueClass = value.getClass();
        bitField0_ |= 0x00000004;
        name_ = value;
    }

    /**
     * <code>optional string name = 4;</code>
     */
    private void clearName() {
        bitField0_ &= ~0x00000004;
        name_ = getDefaultInstance().getName();
    }

    /**
     * <code>optional string name = 4;</code>
     *
     * @param value The bytes for name to set.
     */
    private void setNameBytes(com.google.protobuf.ByteString value) {
        checkByteStringIsUtf8(value);
        name_ = value.toStringUtf8();
        bitField0_ |= 0x00000004;
    }

    public static final int MEDIA_TYPE_FIELD_NUMBER = 5;
    private java.lang.String mediaType_;

    /**
     * <code>optional string media_type = 5;</code>
     *
     * @return Whether the mediaType field is set.
     */
    @java.lang.Override
    public boolean hasMediaType() {
        return ((bitField0_ & 0x00000008) != 0);
    }

    /**
     * <code>optional string media_type = 5;</code>
     *
     * @return The mediaType.
     */
    @java.lang.Override
    public java.lang.String getMediaType() {
        return mediaType_;
    }

    /**
     * <code>optional string media_type = 5;</code>
     *
     * @return The bytes for mediaType.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getMediaTypeBytes() {
        return com.google.protobuf.ByteString.copyFromUtf8(mediaType_);
    }

    /**
     * <code>optional string media_type = 5;</code>
     *
     * @param value The mediaType to set.
     */
    private void setMediaType(java.lang.String value) {
        java.lang.Class<?> valueClass = value.getClass();
        bitField0_ |= 0x00000008;
        mediaType_ = value;
    }

    /**
     * <code>optional string media_type = 5;</code>
     */
    private void clearMediaType() {
        bitField0_ &= ~0x00000008;
        mediaType_ = getDefaultInstance().getMediaType();
    }

    /**
     * <code>optional string media_type = 5;</code>
     *
     * @param value The bytes for mediaType to set.
     */
    private void setMediaTypeBytes(com.google.protobuf.ByteString value) {
        checkByteStringIsUtf8(value);
        mediaType_ = value.toStringUtf8();
        bitField0_ |= 0x00000008;
    }

    public static final int EXTRA_FIELD_NUMBER = 6;

    private static final class ExtraDefaultEntryHolder {
        static final com.google.protobuf.MapEntryLite<java.lang.String, java.lang.String> defaultEntry =
                com.google.protobuf.MapEntryLite.<java.lang.String, java.lang.String>newDefaultInstance(
                        com.google.protobuf.WireFormat.FieldType.STRING,
                        "",
                        com.google.protobuf.WireFormat.FieldType.STRING,
                        "");
    }

    private com.google.protobuf.MapFieldLite<java.lang.String, java.lang.String> extra_ =
            com.google.protobuf.MapFieldLite.emptyMapField();

    private com.google.protobuf.MapFieldLite<java.lang.String, java.lang.String> internalGetExtra() {
        return extra_;
    }

    private com.google.protobuf.MapFieldLite<java.lang.String, java.lang.String> internalGetMutableExtra() {
        if (!extra_.isMutable()) {
            extra_ = extra_.mutableCopy();
        }
        return extra_;
    }

    @java.lang.Override

    public int getExtraCount() {
        return internalGetExtra().size();
    }

    /**
     * <code>map&lt;string, string&gt; extra = 6;</code>
     */
    @java.lang.Override

    public boolean containsExtra(java.lang.String key) {
        java.lang.Class<?> keyClass = key.getClass();
        return internalGetExtra().containsKey(key);
    }

    /**
     * Use {@link #getExtraMap()} instead.
     */
    @java.lang.Override
    @java.lang.Deprecated
    public java.util.Map<java.lang.String, java.lang.String> getExtra() {
        return getExtraMap();
    }

    /**
     * <code>map&lt;string, string&gt; extra = 6;</code>
     */
    @java.lang.Override

    public java.util.Map<java.lang.String, java.lang.String> getExtraMap() {
        return java.util.Collections.unmodifiableMap(internalGetExtra());
    }

    /**
     * <code>map&lt;string, string&gt; extra = 6;</code>
     */
    @java.lang.Override

    public /* nullable */
    java.lang.String getExtraOrDefault(
            java.lang.String key,
            /* nullable */
            java.lang.String defaultValue) {
        java.lang.Class<?> keyClass = key.getClass();
        java.util.Map<java.lang.String, java.lang.String> map = internalGetExtra();
        return map.containsKey(key)
                ? map.get(key)
                : defaultValue;
    }

    /**
     * <code>map&lt;string, string&gt; extra = 6;</code>
     */
    @java.lang.Override

    public java.lang.String getExtraOrThrow(java.lang.String key) {
        java.lang.Class<?> keyClass = key.getClass();
        java.util.Map<java.lang.String, java.lang.String> map = internalGetExtra();
        if (!map.containsKey(key)) {
            throw new java.lang.IllegalArgumentException();
        }
        return map.get(key);
    }

    /**
     * <code>map&lt;string, string&gt; extra = 6;</code>
     */
    private java.util.Map<java.lang.String, java.lang.String> getMutableExtraMap() {
        return internalGetMutableExtra();
    }

    public static im.turms.client.model.proto.request.storage.QueryResourceUploadInfoRequest parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.storage.QueryResourceUploadInfoRequest parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.storage.QueryResourceUploadInfoRequest parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.storage.QueryResourceUploadInfoRequest parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.storage.QueryResourceUploadInfoRequest parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.storage.QueryResourceUploadInfoRequest parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.storage.QueryResourceUploadInfoRequest parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.storage.QueryResourceUploadInfoRequest parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.storage.QueryResourceUploadInfoRequest parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.storage.QueryResourceUploadInfoRequest parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.storage.QueryResourceUploadInfoRequest parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.storage.QueryResourceUploadInfoRequest parseFrom(
            com.google.protobuf.CodedInputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(
            im.turms.client.model.proto.request.storage.QueryResourceUploadInfoRequest prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /**
     * Protobuf type {@code im.turms.proto.QueryResourceUploadInfoRequest}
     */
    public static final class Builder extends
            com.google.protobuf.GeneratedMessageLite.Builder<im.turms.client.model.proto.request.storage.QueryResourceUploadInfoRequest, Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.QueryResourceUploadInfoRequest)
            im.turms.client.model.proto.request.storage.QueryResourceUploadInfoRequestOrBuilder {
        // Construct using
        // im.turms.client.model.proto.request.storage.QueryResourceUploadInfoRequest.newBuilder()
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
         * <code>optional string name = 4;</code>
         *
         * @return Whether the name field is set.
         */
        @java.lang.Override
        public boolean hasName() {
            return instance.hasName();
        }

        /**
         * <code>optional string name = 4;</code>
         *
         * @return The name.
         */
        @java.lang.Override
        public java.lang.String getName() {
            return instance.getName();
        }

        /**
         * <code>optional string name = 4;</code>
         *
         * @return The bytes for name.
         */
        @java.lang.Override
        public com.google.protobuf.ByteString getNameBytes() {
            return instance.getNameBytes();
        }

        /**
         * <code>optional string name = 4;</code>
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
         * <code>optional string name = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearName() {
            copyOnWrite();
            instance.clearName();
            return this;
        }

        /**
         * <code>optional string name = 4;</code>
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
         * <code>optional string media_type = 5;</code>
         *
         * @return Whether the mediaType field is set.
         */
        @java.lang.Override
        public boolean hasMediaType() {
            return instance.hasMediaType();
        }

        /**
         * <code>optional string media_type = 5;</code>
         *
         * @return The mediaType.
         */
        @java.lang.Override
        public java.lang.String getMediaType() {
            return instance.getMediaType();
        }

        /**
         * <code>optional string media_type = 5;</code>
         *
         * @return The bytes for mediaType.
         */
        @java.lang.Override
        public com.google.protobuf.ByteString getMediaTypeBytes() {
            return instance.getMediaTypeBytes();
        }

        /**
         * <code>optional string media_type = 5;</code>
         *
         * @param value The mediaType to set.
         * @return This builder for chaining.
         */
        public Builder setMediaType(java.lang.String value) {
            copyOnWrite();
            instance.setMediaType(value);
            return this;
        }

        /**
         * <code>optional string media_type = 5;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearMediaType() {
            copyOnWrite();
            instance.clearMediaType();
            return this;
        }

        /**
         * <code>optional string media_type = 5;</code>
         *
         * @param value The bytes for mediaType to set.
         * @return This builder for chaining.
         */
        public Builder setMediaTypeBytes(com.google.protobuf.ByteString value) {
            copyOnWrite();
            instance.setMediaTypeBytes(value);
            return this;
        }

        @java.lang.Override

        public int getExtraCount() {
            return instance.getExtraMap()
                    .size();
        }

        /**
         * <code>map&lt;string, string&gt; extra = 6;</code>
         */
        @java.lang.Override

        public boolean containsExtra(java.lang.String key) {
            java.lang.Class<?> keyClass = key.getClass();
            return instance.getExtraMap()
                    .containsKey(key);
        }

        public Builder clearExtra() {
            copyOnWrite();
            instance.getMutableExtraMap()
                    .clear();
            return this;
        }

        /**
         * <code>map&lt;string, string&gt; extra = 6;</code>
         */

        public Builder removeExtra(java.lang.String key) {
            java.lang.Class<?> keyClass = key.getClass();
            copyOnWrite();
            instance.getMutableExtraMap()
                    .remove(key);
            return this;
        }

        /**
         * Use {@link #getExtraMap()} instead.
         */
        @java.lang.Override
        @java.lang.Deprecated
        public java.util.Map<java.lang.String, java.lang.String> getExtra() {
            return getExtraMap();
        }

        /**
         * <code>map&lt;string, string&gt; extra = 6;</code>
         */
        @java.lang.Override
        public java.util.Map<java.lang.String, java.lang.String> getExtraMap() {
            return java.util.Collections.unmodifiableMap(instance.getExtraMap());
        }

        /**
         * <code>map&lt;string, string&gt; extra = 6;</code>
         */
        @java.lang.Override

        public /* nullable */
        java.lang.String getExtraOrDefault(
                java.lang.String key,
                /* nullable */
                java.lang.String defaultValue) {
            java.lang.Class<?> keyClass = key.getClass();
            java.util.Map<java.lang.String, java.lang.String> map = instance.getExtraMap();
            return map.containsKey(key)
                    ? map.get(key)
                    : defaultValue;
        }

        /**
         * <code>map&lt;string, string&gt; extra = 6;</code>
         */
        @java.lang.Override

        public java.lang.String getExtraOrThrow(java.lang.String key) {
            java.lang.Class<?> keyClass = key.getClass();
            java.util.Map<java.lang.String, java.lang.String> map = instance.getExtraMap();
            if (!map.containsKey(key)) {
                throw new java.lang.IllegalArgumentException();
            }
            return map.get(key);
        }

        /**
         * <code>map&lt;string, string&gt; extra = 6;</code>
         */
        public Builder putExtra(java.lang.String key, java.lang.String value) {
            java.lang.Class<?> keyClass = key.getClass();
            java.lang.Class<?> valueClass = value.getClass();
            copyOnWrite();
            instance.getMutableExtraMap()
                    .put(key, value);
            return this;
        }

        /**
         * <code>map&lt;string, string&gt; extra = 6;</code>
         */
        public Builder putAllExtra(java.util.Map<java.lang.String, java.lang.String> values) {
            copyOnWrite();
            instance.getMutableExtraMap()
                    .putAll(values);
            return this;
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.QueryResourceUploadInfoRequest)
    }

    @java.lang.Override
    @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
    protected final java.lang.Object dynamicMethod(
            com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
            java.lang.Object arg0,
            java.lang.Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE: {
                return new im.turms.client.model.proto.request.storage.QueryResourceUploadInfoRequest();
            }
            case NEW_BUILDER: {
                return new Builder();
            }
            case BUILD_MESSAGE_INFO: {
                java.lang.Object[] objects = new java.lang.Object[]{"bitField0_",
                        "type_",
                        "idNum_",
                        "idStr_",
                        "name_",
                        "mediaType_",
                        "extra_",
                        ExtraDefaultEntryHolder.defaultEntry,};
                java.lang.String info =
                        "\u0000\u0006\u0000\u0001\u0001\u0006\u0006\u0001\u0000\u0000\u0001\f\u0002\u1002"
                                + "\u0000\u0003\u1208\u0001\u0004\u1208\u0002\u0005\u1208\u0003\u00062";
                return newMessageInfo(DEFAULT_INSTANCE, info, objects);
            }
            // fall through
            case GET_DEFAULT_INSTANCE: {
                return DEFAULT_INSTANCE;
            }
            case GET_PARSER: {
                com.google.protobuf.Parser<im.turms.client.model.proto.request.storage.QueryResourceUploadInfoRequest> parser =
                        PARSER;
                if (parser == null) {
                    synchronized (im.turms.client.model.proto.request.storage.QueryResourceUploadInfoRequest.class) {
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

    // @@protoc_insertion_point(class_scope:im.turms.proto.QueryResourceUploadInfoRequest)
    private static final im.turms.client.model.proto.request.storage.QueryResourceUploadInfoRequest DEFAULT_INSTANCE;

    static {
        QueryResourceUploadInfoRequest defaultInstance = new QueryResourceUploadInfoRequest();
        // New instances are implicitly immutable so no need to make
        // immutable.
        DEFAULT_INSTANCE = defaultInstance;
        com.google.protobuf.GeneratedMessageLite
                .registerDefaultInstance(QueryResourceUploadInfoRequest.class, defaultInstance);
    }

    public static im.turms.client.model.proto.request.storage.QueryResourceUploadInfoRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static volatile com.google.protobuf.Parser<QueryResourceUploadInfoRequest> PARSER;

    public static com.google.protobuf.Parser<QueryResourceUploadInfoRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}