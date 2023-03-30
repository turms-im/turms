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

package im.turms.server.common.access.client.dto.request.storage;

/**
 * Protobuf type {@code im.turms.proto.QueryResourceUploadInfoRequest}
 */
public final class QueryResourceUploadInfoRequest extends com.google.protobuf.GeneratedMessageV3
        implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.QueryResourceUploadInfoRequest)
        QueryResourceUploadInfoRequestOrBuilder {
    private static final long serialVersionUID = 0L;

    // Use QueryResourceUploadInfoRequest.newBuilder() to construct.
    private QueryResourceUploadInfoRequest(
            com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private QueryResourceUploadInfoRequest() {
        type_ = 0;
        idStr_ = "";
        name_ = "";
        mediaType_ = "";
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(UnusedPrivateParameter unused) {
        return new QueryResourceUploadInfoRequest();
    }

    public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequestOuterClass.internal_static_im_turms_proto_QueryResourceUploadInfoRequest_descriptor;
    }

    @SuppressWarnings({"rawtypes"})
    @java.lang.Override
    protected com.google.protobuf.MapField internalGetMapField(int number) {
        return switch (number) {
            case 6 -> internalGetExtra();
            default -> throw new RuntimeException(
                    "Invalid map field number: "
                            + number);
        };
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequestOuterClass.internal_static_im_turms_proto_QueryResourceUploadInfoRequest_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequest.class,
                        im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequest.Builder.class);
    }

    private int bitField0_;
    public static final int TYPE_FIELD_NUMBER = 1;
    private int type_ = 0;

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
    public im.turms.server.common.access.client.dto.constant.StorageResourceType getType() {
        im.turms.server.common.access.client.dto.constant.StorageResourceType result =
                im.turms.server.common.access.client.dto.constant.StorageResourceType
                        .forNumber(type_);
        return result == null
                ? im.turms.server.common.access.client.dto.constant.StorageResourceType.UNRECOGNIZED
                : result;
    }

    public static final int ID_NUM_FIELD_NUMBER = 2;
    private long idNum_ = 0L;

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

    public static final int ID_STR_FIELD_NUMBER = 3;
    @SuppressWarnings("serial")
    private volatile java.lang.Object idStr_ = "";

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
        java.lang.Object ref = idStr_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            idStr_ = s;
            return s;
        }
    }

    /**
     * <code>optional string id_str = 3;</code>
     *
     * @return The bytes for idStr.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getIdStrBytes() {
        java.lang.Object ref = idStr_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            idStr_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int NAME_FIELD_NUMBER = 4;
    @SuppressWarnings("serial")
    private volatile java.lang.Object name_ = "";

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
        java.lang.Object ref = name_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            name_ = s;
            return s;
        }
    }

    /**
     * <code>optional string name = 4;</code>
     *
     * @return The bytes for name.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getNameBytes() {
        java.lang.Object ref = name_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            name_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int MEDIA_TYPE_FIELD_NUMBER = 5;
    @SuppressWarnings("serial")
    private volatile java.lang.Object mediaType_ = "";

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
        java.lang.Object ref = mediaType_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            mediaType_ = s;
            return s;
        }
    }

    /**
     * <code>optional string media_type = 5;</code>
     *
     * @return The bytes for mediaType.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getMediaTypeBytes() {
        java.lang.Object ref = mediaType_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            mediaType_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int EXTRA_FIELD_NUMBER = 6;

    private static final class ExtraDefaultEntryHolder {
        static final com.google.protobuf.MapEntry<java.lang.String, java.lang.String> defaultEntry =
                com.google.protobuf.MapEntry.<java.lang.String, java.lang.String>newDefaultInstance(
                        im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequestOuterClass.internal_static_im_turms_proto_QueryResourceUploadInfoRequest_ExtraEntry_descriptor,
                        com.google.protobuf.WireFormat.FieldType.STRING,
                        "",
                        com.google.protobuf.WireFormat.FieldType.STRING,
                        "");
    }

    @SuppressWarnings("serial")
    private com.google.protobuf.MapField<java.lang.String, java.lang.String> extra_;

    private com.google.protobuf.MapField<java.lang.String, java.lang.String> internalGetExtra() {
        if (extra_ == null) {
            return com.google.protobuf.MapField.emptyMapField(ExtraDefaultEntryHolder.defaultEntry);
        }
        return extra_;
    }

    public int getExtraCount() {
        return internalGetExtra().getMap()
                .size();
    }

    /**
     * <code>map&lt;string, string&gt; extra = 6;</code>
     */
    @java.lang.Override
    public boolean containsExtra(java.lang.String key) {
        if (key == null) {
            throw new NullPointerException("map key");
        }
        return internalGetExtra().getMap()
                .containsKey(key);
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
        return internalGetExtra().getMap();
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
        if (key == null) {
            throw new NullPointerException("map key");
        }
        java.util.Map<java.lang.String, java.lang.String> map = internalGetExtra().getMap();
        return map.getOrDefault(key, defaultValue);
    }

    /**
     * <code>map&lt;string, string&gt; extra = 6;</code>
     */
    @java.lang.Override
    public java.lang.String getExtraOrThrow(java.lang.String key) {
        if (key == null) {
            throw new NullPointerException("map key");
        }
        java.util.Map<java.lang.String, java.lang.String> map = internalGetExtra().getMap();
        if (!map.containsKey(key)) {
            throw new java.lang.IllegalArgumentException();
        }
        return map.get(key);
    }

    private byte memoizedIsInitialized = -1;

    @java.lang.Override
    public final boolean isInitialized() {
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
        if (type_ != im.turms.server.common.access.client.dto.constant.StorageResourceType.USER_PROFILE_PICTURE
                .getNumber()) {
            output.writeEnum(1, type_);
        }
        if (((bitField0_ & 0x00000001) != 0)) {
            output.writeInt64(2, idNum_);
        }
        if (((bitField0_ & 0x00000002) != 0)) {
            com.google.protobuf.GeneratedMessageV3.writeString(output, 3, idStr_);
        }
        if (((bitField0_ & 0x00000004) != 0)) {
            com.google.protobuf.GeneratedMessageV3.writeString(output, 4, name_);
        }
        if (((bitField0_ & 0x00000008) != 0)) {
            com.google.protobuf.GeneratedMessageV3.writeString(output, 5, mediaType_);
        }
        com.google.protobuf.GeneratedMessageV3.serializeStringMapTo(output,
                internalGetExtra(),
                ExtraDefaultEntryHolder.defaultEntry,
                6);
        getUnknownFields().writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
        int size = memoizedSize;
        if (size != -1) {
            return size;
        }

        size = 0;
        if (type_ != im.turms.server.common.access.client.dto.constant.StorageResourceType.USER_PROFILE_PICTURE
                .getNumber()) {
            size += com.google.protobuf.CodedOutputStream.computeEnumSize(1, type_);
        }
        if (((bitField0_ & 0x00000001) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(2, idNum_);
        }
        if (((bitField0_ & 0x00000002) != 0)) {
            size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, idStr_);
        }
        if (((bitField0_ & 0x00000004) != 0)) {
            size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, name_);
        }
        if (((bitField0_ & 0x00000008) != 0)) {
            size += com.google.protobuf.GeneratedMessageV3.computeStringSize(5, mediaType_);
        }
        for (java.util.Map.Entry<java.lang.String, java.lang.String> entry : internalGetExtra()
                .getMap()
                .entrySet()) {
            com.google.protobuf.MapEntry<java.lang.String, java.lang.String> extra__ =
                    ExtraDefaultEntryHolder.defaultEntry.newBuilderForType()
                            .setKey(entry.getKey())
                            .setValue(entry.getValue())
                            .build();
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(6, extra__);
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
        if (!(obj instanceof QueryResourceUploadInfoRequest other)) {
            return super.equals(obj);
        }

        if (type_ != other.type_) {
            return false;
        }
        if (hasIdNum() != other.hasIdNum()) {
            return false;
        }
        if (hasIdNum()) {
            if (getIdNum() != other.getIdNum()) {
                return false;
            }
        }
        if (hasIdStr() != other.hasIdStr()) {
            return false;
        }
        if (hasIdStr()) {
            if (!getIdStr().equals(other.getIdStr())) {
                return false;
            }
        }
        if (hasName() != other.hasName()) {
            return false;
        }
        if (hasName()) {
            if (!getName().equals(other.getName())) {
                return false;
            }
        }
        if (hasMediaType() != other.hasMediaType()) {
            return false;
        }
        if (hasMediaType()) {
            if (!getMediaType().equals(other.getMediaType())) {
                return false;
            }
        }
        if (!internalGetExtra().equals(other.internalGetExtra())) {
            return false;
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
        hash = (37 * hash) + TYPE_FIELD_NUMBER;
        hash = (53 * hash) + type_;
        if (hasIdNum()) {
            hash = (37 * hash) + ID_NUM_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getIdNum());
        }
        if (hasIdStr()) {
            hash = (37 * hash) + ID_STR_FIELD_NUMBER;
            hash = (53 * hash) + getIdStr().hashCode();
        }
        if (hasName()) {
            hash = (37 * hash) + NAME_FIELD_NUMBER;
            hash = (53 * hash) + getName().hashCode();
        }
        if (hasMediaType()) {
            hash = (37 * hash) + MEDIA_TYPE_FIELD_NUMBER;
            hash = (53 * hash) + getMediaType().hashCode();
        }
        if (!internalGetExtra().getMap()
                .isEmpty()) {
            hash = (37 * hash) + EXTRA_FIELD_NUMBER;
            hash = (53 * hash) + internalGetExtra().hashCode();
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequest parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequest parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequest parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequest parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequest parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequest parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequest parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequest parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequest parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequest parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequest parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequest parseFrom(
            com.google.protobuf.CodedInputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
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
            im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequest prototype) {
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
    protected Builder newBuilderForType(
            com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        return new Builder(parent);
    }

    /**
     * Protobuf type {@code im.turms.proto.QueryResourceUploadInfoRequest}
     */
    public static final class Builder
            extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.QueryResourceUploadInfoRequest)
            im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequestOrBuilder {
        public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequestOuterClass.internal_static_im_turms_proto_QueryResourceUploadInfoRequest_descriptor;
        }

        @SuppressWarnings({"rawtypes"})
        protected com.google.protobuf.MapField internalGetMapField(int number) {
            return switch (number) {
                case 6 -> internalGetExtra();
                default -> throw new RuntimeException(
                        "Invalid map field number: "
                                + number);
            };
        }

        @SuppressWarnings({"rawtypes"})
        protected com.google.protobuf.MapField internalGetMutableMapField(int number) {
            return switch (number) {
                case 6 -> internalGetMutableExtra();
                default -> throw new RuntimeException(
                        "Invalid map field number: "
                                + number);
            };
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequestOuterClass.internal_static_im_turms_proto_QueryResourceUploadInfoRequest_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequest.class,
                            im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequest.Builder.class);
        }

        // Construct using
        // im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequest.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            type_ = 0;
            idNum_ = 0L;
            idStr_ = "";
            name_ = "";
            mediaType_ = "";
            internalGetMutableExtra().clear();
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequestOuterClass.internal_static_im_turms_proto_QueryResourceUploadInfoRequest_descriptor;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequest getDefaultInstanceForType() {
            return im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequest
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequest build() {
            im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequest result =
                    buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequest buildPartial() {
            im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequest result =
                    new im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequest(
                            this);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartial0(
                im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequest result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.type_ = type_;
            }
            int to_bitField0_ = 0;
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.idNum_ = idNum_;
                to_bitField0_ |= 0x00000001;
            }
            if (((from_bitField0_ & 0x00000004) != 0)) {
                result.idStr_ = idStr_;
                to_bitField0_ |= 0x00000002;
            }
            if (((from_bitField0_ & 0x00000008) != 0)) {
                result.name_ = name_;
                to_bitField0_ |= 0x00000004;
            }
            if (((from_bitField0_ & 0x00000010) != 0)) {
                result.mediaType_ = mediaType_;
                to_bitField0_ |= 0x00000008;
            }
            if (((from_bitField0_ & 0x00000020) != 0)) {
                result.extra_ = internalGetExtra();
                result.extra_.makeImmutable();
            }
            result.bitField0_ |= to_bitField0_;
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequest) {
                return mergeFrom(
                        (im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequest) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequest other) {
            if (other == im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequest
                    .getDefaultInstance()) {
                return this;
            }
            if (other.type_ != 0) {
                setTypeValue(other.getTypeValue());
            }
            if (other.hasIdNum()) {
                setIdNum(other.getIdNum());
            }
            if (other.hasIdStr()) {
                idStr_ = other.idStr_;
                bitField0_ |= 0x00000004;
                onChanged();
            }
            if (other.hasName()) {
                name_ = other.name_;
                bitField0_ |= 0x00000008;
                onChanged();
            }
            if (other.hasMediaType()) {
                mediaType_ = other.mediaType_;
                bitField0_ |= 0x00000010;
                onChanged();
            }
            internalGetMutableExtra().mergeFrom(other.internalGetExtra());
            bitField0_ |= 0x00000020;
            this.mergeUnknownFields(other.getUnknownFields());
            onChanged();
            return this;
        }

        @java.lang.Override
        public final boolean isInitialized() {
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
                            type_ = input.readEnum();
                            bitField0_ |= 0x00000001;
                        } // case 8
                        case 16 -> {
                            idNum_ = input.readInt64();
                            bitField0_ |= 0x00000002;
                        } // case 16
                        case 26 -> {
                            idStr_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000004;
                        } // case 26
                        case 34 -> {
                            name_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000008;
                        } // case 34
                        case 42 -> {
                            mediaType_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000010;
                        } // case 42
                        case 50 -> {
                            com.google.protobuf.MapEntry<String, String> extra__ =
                                    input.readMessage(
                                            ExtraDefaultEntryHolder.defaultEntry.getParserForType(),
                                            extensionRegistry);
                            internalGetMutableExtra().getMutableMap()
                                    .put(extra__.getKey(), extra__.getValue());
                            bitField0_ |= 0x00000020;
                        } // case 50
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

        private int type_ = 0;

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
         * @param value The enum numeric value on the wire for type to set.
         * @return This builder for chaining.
         */
        public Builder setTypeValue(int value) {
            type_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>.im.turms.proto.StorageResourceType type = 1;</code>
         *
         * @return The type.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.constant.StorageResourceType getType() {
            im.turms.server.common.access.client.dto.constant.StorageResourceType result =
                    im.turms.server.common.access.client.dto.constant.StorageResourceType
                            .forNumber(type_);
            return result == null
                    ? im.turms.server.common.access.client.dto.constant.StorageResourceType.UNRECOGNIZED
                    : result;
        }

        /**
         * <code>.im.turms.proto.StorageResourceType type = 1;</code>
         *
         * @param value The type to set.
         * @return This builder for chaining.
         */
        public Builder setType(
                im.turms.server.common.access.client.dto.constant.StorageResourceType value) {
            if (value == null) {
                throw new NullPointerException();
            }
            bitField0_ |= 0x00000001;
            type_ = value.getNumber();
            onChanged();
            return this;
        }

        /**
         * <code>.im.turms.proto.StorageResourceType type = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearType() {
            bitField0_ &= ~0x00000001;
            type_ = 0;
            onChanged();
            return this;
        }

        private long idNum_;

        /**
         * <code>optional int64 id_num = 2;</code>
         *
         * @return Whether the idNum field is set.
         */
        @java.lang.Override
        public boolean hasIdNum() {
            return ((bitField0_ & 0x00000002) != 0);
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
         * @return This builder for chaining.
         */
        public Builder setIdNum(long value) {

            idNum_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>optional int64 id_num = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearIdNum() {
            bitField0_ &= ~0x00000002;
            idNum_ = 0L;
            onChanged();
            return this;
        }

        private java.lang.Object idStr_ = "";

        /**
         * <code>optional string id_str = 3;</code>
         *
         * @return Whether the idStr field is set.
         */
        public boolean hasIdStr() {
            return ((bitField0_ & 0x00000004) != 0);
        }

        /**
         * <code>optional string id_str = 3;</code>
         *
         * @return The idStr.
         */
        public java.lang.String getIdStr() {
            java.lang.Object ref = idStr_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                idStr_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>optional string id_str = 3;</code>
         *
         * @return The bytes for idStr.
         */
        public com.google.protobuf.ByteString getIdStrBytes() {
            java.lang.Object ref = idStr_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                idStr_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>optional string id_str = 3;</code>
         *
         * @param value The idStr to set.
         * @return This builder for chaining.
         */
        public Builder setIdStr(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            idStr_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>optional string id_str = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearIdStr() {
            idStr_ = getDefaultInstance().getIdStr();
            bitField0_ &= ~0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>optional string id_str = 3;</code>
         *
         * @param value The bytes for idStr to set.
         * @return This builder for chaining.
         */
        public Builder setIdStrBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            idStr_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        private java.lang.Object name_ = "";

        /**
         * <code>optional string name = 4;</code>
         *
         * @return Whether the name field is set.
         */
        public boolean hasName() {
            return ((bitField0_ & 0x00000008) != 0);
        }

        /**
         * <code>optional string name = 4;</code>
         *
         * @return The name.
         */
        public java.lang.String getName() {
            java.lang.Object ref = name_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                name_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>optional string name = 4;</code>
         *
         * @return The bytes for name.
         */
        public com.google.protobuf.ByteString getNameBytes() {
            java.lang.Object ref = name_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                name_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>optional string name = 4;</code>
         *
         * @param value The name to set.
         * @return This builder for chaining.
         */
        public Builder setName(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            name_ = value;
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        /**
         * <code>optional string name = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearName() {
            name_ = getDefaultInstance().getName();
            bitField0_ &= ~0x00000008;
            onChanged();
            return this;
        }

        /**
         * <code>optional string name = 4;</code>
         *
         * @param value The bytes for name to set.
         * @return This builder for chaining.
         */
        public Builder setNameBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            name_ = value;
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        private java.lang.Object mediaType_ = "";

        /**
         * <code>optional string media_type = 5;</code>
         *
         * @return Whether the mediaType field is set.
         */
        public boolean hasMediaType() {
            return ((bitField0_ & 0x00000010) != 0);
        }

        /**
         * <code>optional string media_type = 5;</code>
         *
         * @return The mediaType.
         */
        public java.lang.String getMediaType() {
            java.lang.Object ref = mediaType_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                mediaType_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>optional string media_type = 5;</code>
         *
         * @return The bytes for mediaType.
         */
        public com.google.protobuf.ByteString getMediaTypeBytes() {
            java.lang.Object ref = mediaType_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                mediaType_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>optional string media_type = 5;</code>
         *
         * @param value The mediaType to set.
         * @return This builder for chaining.
         */
        public Builder setMediaType(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            mediaType_ = value;
            bitField0_ |= 0x00000010;
            onChanged();
            return this;
        }

        /**
         * <code>optional string media_type = 5;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearMediaType() {
            mediaType_ = getDefaultInstance().getMediaType();
            bitField0_ &= ~0x00000010;
            onChanged();
            return this;
        }

        /**
         * <code>optional string media_type = 5;</code>
         *
         * @param value The bytes for mediaType to set.
         * @return This builder for chaining.
         */
        public Builder setMediaTypeBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            mediaType_ = value;
            bitField0_ |= 0x00000010;
            onChanged();
            return this;
        }

        private com.google.protobuf.MapField<java.lang.String, java.lang.String> extra_;

        private com.google.protobuf.MapField<java.lang.String, java.lang.String> internalGetExtra() {
            if (extra_ == null) {
                return com.google.protobuf.MapField
                        .emptyMapField(ExtraDefaultEntryHolder.defaultEntry);
            }
            return extra_;
        }

        private com.google.protobuf.MapField<java.lang.String, java.lang.String> internalGetMutableExtra() {
            if (extra_ == null) {
                extra_ = com.google.protobuf.MapField
                        .newMapField(ExtraDefaultEntryHolder.defaultEntry);
            }
            if (!extra_.isMutable()) {
                extra_ = extra_.copy();
            }
            bitField0_ |= 0x00000020;
            onChanged();
            return extra_;
        }

        public int getExtraCount() {
            return internalGetExtra().getMap()
                    .size();
        }

        /**
         * <code>map&lt;string, string&gt; extra = 6;</code>
         */
        @java.lang.Override
        public boolean containsExtra(java.lang.String key) {
            if (key == null) {
                throw new NullPointerException("map key");
            }
            return internalGetExtra().getMap()
                    .containsKey(key);
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
            return internalGetExtra().getMap();
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
            if (key == null) {
                throw new NullPointerException("map key");
            }
            java.util.Map<java.lang.String, java.lang.String> map = internalGetExtra().getMap();
            return map.getOrDefault(key, defaultValue);
        }

        /**
         * <code>map&lt;string, string&gt; extra = 6;</code>
         */
        @java.lang.Override
        public java.lang.String getExtraOrThrow(java.lang.String key) {
            if (key == null) {
                throw new NullPointerException("map key");
            }
            java.util.Map<java.lang.String, java.lang.String> map = internalGetExtra().getMap();
            if (!map.containsKey(key)) {
                throw new java.lang.IllegalArgumentException();
            }
            return map.get(key);
        }

        public Builder clearExtra() {
            bitField0_ &= ~0x00000020;
            internalGetMutableExtra().getMutableMap()
                    .clear();
            return this;
        }

        /**
         * <code>map&lt;string, string&gt; extra = 6;</code>
         */
        public Builder removeExtra(java.lang.String key) {
            if (key == null) {
                throw new NullPointerException("map key");
            }
            internalGetMutableExtra().getMutableMap()
                    .remove(key);
            return this;
        }

        /**
         * Use alternate mutation accessors instead.
         */
        @java.lang.Deprecated
        public java.util.Map<java.lang.String, java.lang.String> getMutableExtra() {
            bitField0_ |= 0x00000020;
            return internalGetMutableExtra().getMutableMap();
        }

        /**
         * <code>map&lt;string, string&gt; extra = 6;</code>
         */
        public Builder putExtra(java.lang.String key, java.lang.String value) {
            if (key == null) {
                throw new NullPointerException("map key");
            }
            if (value == null) {
                throw new NullPointerException("map value");
            }
            internalGetMutableExtra().getMutableMap()
                    .put(key, value);
            bitField0_ |= 0x00000020;
            return this;
        }

        /**
         * <code>map&lt;string, string&gt; extra = 6;</code>
         */
        public Builder putAllExtra(java.util.Map<java.lang.String, java.lang.String> values) {
            internalGetMutableExtra().getMutableMap()
                    .putAll(values);
            bitField0_ |= 0x00000020;
            return this;
        }

        @java.lang.Override
        public final Builder setUnknownFields(
                final com.google.protobuf.UnknownFieldSet unknownFields) {
            return super.setUnknownFields(unknownFields);
        }

        @java.lang.Override
        public final Builder mergeUnknownFields(
                final com.google.protobuf.UnknownFieldSet unknownFields) {
            return super.mergeUnknownFields(unknownFields);
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.QueryResourceUploadInfoRequest)
    }

    // @@protoc_insertion_point(class_scope:im.turms.proto.QueryResourceUploadInfoRequest)
    private static final im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequest DEFAULT_INSTANCE;

    static {
        DEFAULT_INSTANCE =
                new im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequest();
    }

    public static im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<QueryResourceUploadInfoRequest> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public QueryResourceUploadInfoRequest parsePartialFrom(
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

    public static com.google.protobuf.Parser<QueryResourceUploadInfoRequest> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<QueryResourceUploadInfoRequest> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.storage.QueryResourceUploadInfoRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}