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
 * Protobuf type {@code im.turms.proto.QuerySignedPutUrlRequest}
 */
public final class QuerySignedPutUrlRequest extends com.google.protobuf.GeneratedMessageV3
        implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.QuerySignedPutUrlRequest)
        QuerySignedPutUrlRequestOrBuilder {
    private static final long serialVersionUID = 0L;

    // Use QuerySignedPutUrlRequest.newBuilder() to construct.
    private QuerySignedPutUrlRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private QuerySignedPutUrlRequest() {
        contentType_ = 0;
        keyStr_ = "";
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(UnusedPrivateParameter unused) {
        return new QuerySignedPutUrlRequest();
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.server.common.access.client.dto.request.storage.QuerySignedPutUrlRequestOuterClass.internal_static_im_turms_proto_QuerySignedPutUrlRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.server.common.access.client.dto.request.storage.QuerySignedPutUrlRequestOuterClass.internal_static_im_turms_proto_QuerySignedPutUrlRequest_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.server.common.access.client.dto.request.storage.QuerySignedPutUrlRequest.class,
                        im.turms.server.common.access.client.dto.request.storage.QuerySignedPutUrlRequest.Builder.class);
    }

    private int bitField0_;
    public static final int CONTENT_TYPE_FIELD_NUMBER = 1;
    private int contentType_;

    /**
     * <code>.im.turms.proto.ContentType content_type = 1;</code>
     *
     * @return The enum numeric value on the wire for contentType.
     */
    @java.lang.Override
    public int getContentTypeValue() {
        return contentType_;
    }

    /**
     * <code>.im.turms.proto.ContentType content_type = 1;</code>
     *
     * @return The contentType.
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.constant.ContentType getContentType() {
        @SuppressWarnings("deprecation")
        im.turms.server.common.access.client.dto.constant.ContentType result =
                im.turms.server.common.access.client.dto.constant.ContentType.valueOf(contentType_);
        return result == null
                ? im.turms.server.common.access.client.dto.constant.ContentType.UNRECOGNIZED
                : result;
    }

    public static final int KEY_STR_FIELD_NUMBER = 2;
    private volatile java.lang.Object keyStr_;

    /**
     * <code>optional string key_str = 2;</code>
     *
     * @return Whether the keyStr field is set.
     */
    @java.lang.Override
    public boolean hasKeyStr() {
        return ((bitField0_ & 0x00000001) != 0);
    }

    /**
     * <code>optional string key_str = 2;</code>
     *
     * @return The keyStr.
     */
    @java.lang.Override
    public java.lang.String getKeyStr() {
        java.lang.Object ref = keyStr_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            keyStr_ = s;
            return s;
        }
    }

    /**
     * <code>optional string key_str = 2;</code>
     *
     * @return The bytes for keyStr.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getKeyStrBytes() {
        java.lang.Object ref = keyStr_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            keyStr_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int KEY_NUM_FIELD_NUMBER = 3;
    private long keyNum_;

    /**
     * <code>optional int64 key_num = 3;</code>
     *
     * @return Whether the keyNum field is set.
     */
    @java.lang.Override
    public boolean hasKeyNum() {
        return ((bitField0_ & 0x00000002) != 0);
    }

    /**
     * <code>optional int64 key_num = 3;</code>
     *
     * @return The keyNum.
     */
    @java.lang.Override
    public long getKeyNum() {
        return keyNum_;
    }

    public static final int CONTENT_LENGTH_FIELD_NUMBER = 4;
    private long contentLength_;

    /**
     * <code>int64 content_length = 4;</code>
     *
     * @return The contentLength.
     */
    @java.lang.Override
    public long getContentLength() {
        return contentLength_;
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
        if (contentType_ != im.turms.server.common.access.client.dto.constant.ContentType.PROFILE
                .getNumber()) {
            output.writeEnum(1, contentType_);
        }
        if (((bitField0_ & 0x00000001) != 0)) {
            com.google.protobuf.GeneratedMessageV3.writeString(output, 2, keyStr_);
        }
        if (((bitField0_ & 0x00000002) != 0)) {
            output.writeInt64(3, keyNum_);
        }
        if (contentLength_ != 0L) {
            output.writeInt64(4, contentLength_);
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
        if (contentType_ != im.turms.server.common.access.client.dto.constant.ContentType.PROFILE
                .getNumber()) {
            size += com.google.protobuf.CodedOutputStream.computeEnumSize(1, contentType_);
        }
        if (((bitField0_ & 0x00000001) != 0)) {
            size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, keyStr_);
        }
        if (((bitField0_ & 0x00000002) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(3, keyNum_);
        }
        if (contentLength_ != 0L) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(4, contentLength_);
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
        if (!(obj instanceof QuerySignedPutUrlRequest other)) {
            return super.equals(obj);
        }

        if (contentType_ != other.contentType_) {
            return false;
        }
        if (hasKeyStr() != other.hasKeyStr()) {
            return false;
        }
        if (hasKeyStr()) {
            if (!getKeyStr().equals(other.getKeyStr())) {
                return false;
            }
        }
        if (hasKeyNum() != other.hasKeyNum()) {
            return false;
        }
        if (hasKeyNum()) {
            if (getKeyNum() != other.getKeyNum()) {
                return false;
            }
        }
        return getContentLength() == other.getContentLength()
                && getUnknownFields().equals(other.getUnknownFields());
    }

    @java.lang.Override
    public int hashCode() {
        if (memoizedHashCode != 0) {
            return memoizedHashCode;
        }
        int hash = 41;
        hash = (19 * hash) + getDescriptor().hashCode();
        hash = (37 * hash) + CONTENT_TYPE_FIELD_NUMBER;
        hash = (53 * hash) + contentType_;
        if (hasKeyStr()) {
            hash = (37 * hash) + KEY_STR_FIELD_NUMBER;
            hash = (53 * hash) + getKeyStr().hashCode();
        }
        if (hasKeyNum()) {
            hash = (37 * hash) + KEY_NUM_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getKeyNum());
        }
        hash = (37 * hash) + CONTENT_LENGTH_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getContentLength());
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.server.common.access.client.dto.request.storage.QuerySignedPutUrlRequest parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.storage.QuerySignedPutUrlRequest parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.storage.QuerySignedPutUrlRequest parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.storage.QuerySignedPutUrlRequest parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.storage.QuerySignedPutUrlRequest parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.storage.QuerySignedPutUrlRequest parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.storage.QuerySignedPutUrlRequest parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.storage.QuerySignedPutUrlRequest parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.storage.QuerySignedPutUrlRequest parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.storage.QuerySignedPutUrlRequest parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.storage.QuerySignedPutUrlRequest parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.storage.QuerySignedPutUrlRequest parseFrom(
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
            im.turms.server.common.access.client.dto.request.storage.QuerySignedPutUrlRequest prototype) {
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
     * Protobuf type {@code im.turms.proto.QuerySignedPutUrlRequest}
     */
    public static final class Builder
            extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.QuerySignedPutUrlRequest)
            im.turms.server.common.access.client.dto.request.storage.QuerySignedPutUrlRequestOrBuilder {
        public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.server.common.access.client.dto.request.storage.QuerySignedPutUrlRequestOuterClass.internal_static_im_turms_proto_QuerySignedPutUrlRequest_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.server.common.access.client.dto.request.storage.QuerySignedPutUrlRequestOuterClass.internal_static_im_turms_proto_QuerySignedPutUrlRequest_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.server.common.access.client.dto.request.storage.QuerySignedPutUrlRequest.class,
                            im.turms.server.common.access.client.dto.request.storage.QuerySignedPutUrlRequest.Builder.class);
        }

        // Construct using
        // im.turms.server.common.access.client.dto.request.storage.QuerySignedPutUrlRequest.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            contentType_ = 0;

            keyStr_ = "";
            bitField0_ &= ~0x00000001;
            keyNum_ = 0L;
            bitField0_ &= ~0x00000002;
            contentLength_ = 0L;

            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.server.common.access.client.dto.request.storage.QuerySignedPutUrlRequestOuterClass.internal_static_im_turms_proto_QuerySignedPutUrlRequest_descriptor;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.storage.QuerySignedPutUrlRequest getDefaultInstanceForType() {
            return im.turms.server.common.access.client.dto.request.storage.QuerySignedPutUrlRequest
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.storage.QuerySignedPutUrlRequest build() {
            im.turms.server.common.access.client.dto.request.storage.QuerySignedPutUrlRequest result =
                    buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.storage.QuerySignedPutUrlRequest buildPartial() {
            im.turms.server.common.access.client.dto.request.storage.QuerySignedPutUrlRequest result =
                    new im.turms.server.common.access.client.dto.request.storage.QuerySignedPutUrlRequest(
                            this);
            int from_bitField0_ = bitField0_;
            int to_bitField0_ = 0;
            result.contentType_ = contentType_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                to_bitField0_ |= 0x00000001;
            }
            result.keyStr_ = keyStr_;
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.keyNum_ = keyNum_;
                to_bitField0_ |= 0x00000002;
            }
            result.contentLength_ = contentLength_;
            result.bitField0_ = to_bitField0_;
            onBuilt();
            return result;
        }

        @java.lang.Override
        public Builder clone() {
            return super.clone();
        }

        @java.lang.Override
        public Builder setField(
                com.google.protobuf.Descriptors.FieldDescriptor field,
                java.lang.Object value) {
            return super.setField(field, value);
        }

        @java.lang.Override
        public Builder clearField(com.google.protobuf.Descriptors.FieldDescriptor field) {
            return super.clearField(field);
        }

        @java.lang.Override
        public Builder clearOneof(com.google.protobuf.Descriptors.OneofDescriptor oneof) {
            return super.clearOneof(oneof);
        }

        @java.lang.Override
        public Builder setRepeatedField(
                com.google.protobuf.Descriptors.FieldDescriptor field,
                int index,
                java.lang.Object value) {
            return super.setRepeatedField(field, index, value);
        }

        @java.lang.Override
        public Builder addRepeatedField(
                com.google.protobuf.Descriptors.FieldDescriptor field,
                java.lang.Object value) {
            return super.addRepeatedField(field, value);
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.server.common.access.client.dto.request.storage.QuerySignedPutUrlRequest) {
                return mergeFrom(
                        (im.turms.server.common.access.client.dto.request.storage.QuerySignedPutUrlRequest) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.server.common.access.client.dto.request.storage.QuerySignedPutUrlRequest other) {
            if (other == im.turms.server.common.access.client.dto.request.storage.QuerySignedPutUrlRequest
                    .getDefaultInstance()) {
                return this;
            }
            if (other.contentType_ != 0) {
                setContentTypeValue(other.getContentTypeValue());
            }
            if (other.hasKeyStr()) {
                bitField0_ |= 0x00000001;
                keyStr_ = other.keyStr_;
                onChanged();
            }
            if (other.hasKeyNum()) {
                setKeyNum(other.getKeyNum());
            }
            if (other.getContentLength() != 0L) {
                setContentLength(other.getContentLength());
            }
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
                        case 8 -> contentType_ = input.readEnum(); // case 8
                        case 18 -> {
                            keyStr_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000001;
                        } // case 18
                        case 24 -> {
                            keyNum_ = input.readInt64();
                            bitField0_ |= 0x00000002;
                        } // case 24
                        case 32 -> contentLength_ = input.readInt64(); // case 32
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

        private int contentType_ = 0;

        /**
         * <code>.im.turms.proto.ContentType content_type = 1;</code>
         *
         * @return The enum numeric value on the wire for contentType.
         */
        @java.lang.Override
        public int getContentTypeValue() {
            return contentType_;
        }

        /**
         * <code>.im.turms.proto.ContentType content_type = 1;</code>
         *
         * @param value The enum numeric value on the wire for contentType to set.
         * @return This builder for chaining.
         */
        public Builder setContentTypeValue(int value) {

            contentType_ = value;
            onChanged();
            return this;
        }

        /**
         * <code>.im.turms.proto.ContentType content_type = 1;</code>
         *
         * @return The contentType.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.constant.ContentType getContentType() {
            @SuppressWarnings("deprecation")
            im.turms.server.common.access.client.dto.constant.ContentType result =
                    im.turms.server.common.access.client.dto.constant.ContentType
                            .valueOf(contentType_);
            return result == null
                    ? im.turms.server.common.access.client.dto.constant.ContentType.UNRECOGNIZED
                    : result;
        }

        /**
         * <code>.im.turms.proto.ContentType content_type = 1;</code>
         *
         * @param value The contentType to set.
         * @return This builder for chaining.
         */
        public Builder setContentType(
                im.turms.server.common.access.client.dto.constant.ContentType value) {
            if (value == null) {
                throw new NullPointerException();
            }

            contentType_ = value.getNumber();
            onChanged();
            return this;
        }

        /**
         * <code>.im.turms.proto.ContentType content_type = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearContentType() {

            contentType_ = 0;
            onChanged();
            return this;
        }

        private java.lang.Object keyStr_ = "";

        /**
         * <code>optional string key_str = 2;</code>
         *
         * @return Whether the keyStr field is set.
         */
        public boolean hasKeyStr() {
            return ((bitField0_ & 0x00000001) != 0);
        }

        /**
         * <code>optional string key_str = 2;</code>
         *
         * @return The keyStr.
         */
        public java.lang.String getKeyStr() {
            java.lang.Object ref = keyStr_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                keyStr_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>optional string key_str = 2;</code>
         *
         * @return The bytes for keyStr.
         */
        public com.google.protobuf.ByteString getKeyStrBytes() {
            java.lang.Object ref = keyStr_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                keyStr_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>optional string key_str = 2;</code>
         *
         * @param value The keyStr to set.
         * @return This builder for chaining.
         */
        public Builder setKeyStr(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            bitField0_ |= 0x00000001;
            keyStr_ = value;
            onChanged();
            return this;
        }

        /**
         * <code>optional string key_str = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearKeyStr() {
            bitField0_ &= ~0x00000001;
            keyStr_ = getDefaultInstance().getKeyStr();
            onChanged();
            return this;
        }

        /**
         * <code>optional string key_str = 2;</code>
         *
         * @param value The bytes for keyStr to set.
         * @return This builder for chaining.
         */
        public Builder setKeyStrBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            bitField0_ |= 0x00000001;
            keyStr_ = value;
            onChanged();
            return this;
        }

        private long keyNum_;

        /**
         * <code>optional int64 key_num = 3;</code>
         *
         * @return Whether the keyNum field is set.
         */
        @java.lang.Override
        public boolean hasKeyNum() {
            return ((bitField0_ & 0x00000002) != 0);
        }

        /**
         * <code>optional int64 key_num = 3;</code>
         *
         * @return The keyNum.
         */
        @java.lang.Override
        public long getKeyNum() {
            return keyNum_;
        }

        /**
         * <code>optional int64 key_num = 3;</code>
         *
         * @param value The keyNum to set.
         * @return This builder for chaining.
         */
        public Builder setKeyNum(long value) {
            bitField0_ |= 0x00000002;
            keyNum_ = value;
            onChanged();
            return this;
        }

        /**
         * <code>optional int64 key_num = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearKeyNum() {
            bitField0_ &= ~0x00000002;
            keyNum_ = 0L;
            onChanged();
            return this;
        }

        private long contentLength_;

        /**
         * <code>int64 content_length = 4;</code>
         *
         * @return The contentLength.
         */
        @java.lang.Override
        public long getContentLength() {
            return contentLength_;
        }

        /**
         * <code>int64 content_length = 4;</code>
         *
         * @param value The contentLength to set.
         * @return This builder for chaining.
         */
        public Builder setContentLength(long value) {

            contentLength_ = value;
            onChanged();
            return this;
        }

        /**
         * <code>int64 content_length = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearContentLength() {

            contentLength_ = 0L;
            onChanged();
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

        // @@protoc_insertion_point(builder_scope:im.turms.proto.QuerySignedPutUrlRequest)
    }

    // @@protoc_insertion_point(class_scope:im.turms.proto.QuerySignedPutUrlRequest)
    private static final im.turms.server.common.access.client.dto.request.storage.QuerySignedPutUrlRequest DEFAULT_INSTANCE;

    static {
        DEFAULT_INSTANCE =
                new im.turms.server.common.access.client.dto.request.storage.QuerySignedPutUrlRequest();
    }

    public static im.turms.server.common.access.client.dto.request.storage.QuerySignedPutUrlRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<QuerySignedPutUrlRequest> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public QuerySignedPutUrlRequest parsePartialFrom(
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

    public static com.google.protobuf.Parser<QuerySignedPutUrlRequest> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<QuerySignedPutUrlRequest> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.storage.QuerySignedPutUrlRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}