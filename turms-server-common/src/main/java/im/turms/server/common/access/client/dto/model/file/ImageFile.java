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

package im.turms.server.common.access.client.dto.model.file;

/**
 * Protobuf type {@code im.turms.proto.ImageFile}
 */
public final class ImageFile extends com.google.protobuf.GeneratedMessageV3 implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.ImageFile)
        ImageFileOrBuilder {
    private static final long serialVersionUID = 0L;

    // Use ImageFile.newBuilder() to construct.
    private ImageFile(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private ImageFile() {
        data_ = com.google.protobuf.ByteString.EMPTY;
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(UnusedPrivateParameter unused) {
        return new ImageFile();
    }

    public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.server.common.access.client.dto.model.file.ImageFileOuterClass.internal_static_im_turms_proto_ImageFile_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.server.common.access.client.dto.model.file.ImageFileOuterClass.internal_static_im_turms_proto_ImageFile_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.server.common.access.client.dto.model.file.ImageFile.class,
                        im.turms.server.common.access.client.dto.model.file.ImageFile.Builder.class);
    }

    public interface DescriptionOrBuilder extends
            // @@protoc_insertion_point(interface_extends:im.turms.proto.ImageFile.Description)
            com.google.protobuf.MessageOrBuilder {

        /**
         * <code>string url = 1;</code>
         *
         * @return The url.
         */
        java.lang.String getUrl();

        /**
         * <code>string url = 1;</code>
         *
         * @return The bytes for url.
         */
        com.google.protobuf.ByteString getUrlBytes();

        /**
         * <code>optional bool original = 2;</code>
         *
         * @return Whether the original field is set.
         */
        boolean hasOriginal();

        /**
         * <code>optional bool original = 2;</code>
         *
         * @return The original.
         */
        boolean getOriginal();

        /**
         * <code>optional int32 image_size = 3;</code>
         *
         * @return Whether the imageSize field is set.
         */
        boolean hasImageSize();

        /**
         * <code>optional int32 image_size = 3;</code>
         *
         * @return The imageSize.
         */
        int getImageSize();

        /**
         * <code>optional int32 file_size = 4;</code>
         *
         * @return Whether the fileSize field is set.
         */
        boolean hasFileSize();

        /**
         * <code>optional int32 file_size = 4;</code>
         *
         * @return The fileSize.
         */
        int getFileSize();
    }

    /**
     * Protobuf type {@code im.turms.proto.ImageFile.Description}
     */
    public static final class Description extends com.google.protobuf.GeneratedMessageV3 implements
            // @@protoc_insertion_point(message_implements:im.turms.proto.ImageFile.Description)
            DescriptionOrBuilder {
        private static final long serialVersionUID = 0L;

        // Use Description.newBuilder() to construct.
        private Description(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
            super(builder);
        }

        private Description() {
            url_ = "";
        }

        @java.lang.Override
        @SuppressWarnings({"unused"})
        protected java.lang.Object newInstance(UnusedPrivateParameter unused) {
            return new Description();
        }

        public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.server.common.access.client.dto.model.file.ImageFileOuterClass.internal_static_im_turms_proto_ImageFile_Description_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.server.common.access.client.dto.model.file.ImageFileOuterClass.internal_static_im_turms_proto_ImageFile_Description_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.server.common.access.client.dto.model.file.ImageFile.Description.class,
                            im.turms.server.common.access.client.dto.model.file.ImageFile.Description.Builder.class);
        }

        private int bitField0_;
        public static final int URL_FIELD_NUMBER = 1;
        @SuppressWarnings("serial")
        private volatile java.lang.Object url_ = "";

        /**
         * <code>string url = 1;</code>
         *
         * @return The url.
         */
        @java.lang.Override
        public java.lang.String getUrl() {
            java.lang.Object ref = url_;
            if (ref instanceof java.lang.String) {
                return (java.lang.String) ref;
            } else {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                url_ = s;
                return s;
            }
        }

        /**
         * <code>string url = 1;</code>
         *
         * @return The bytes for url.
         */
        @java.lang.Override
        public com.google.protobuf.ByteString getUrlBytes() {
            java.lang.Object ref = url_;
            if (ref instanceof java.lang.String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                url_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        public static final int ORIGINAL_FIELD_NUMBER = 2;
        private boolean original_ = false;

        /**
         * <code>optional bool original = 2;</code>
         *
         * @return Whether the original field is set.
         */
        @java.lang.Override
        public boolean hasOriginal() {
            return ((bitField0_ & 0x00000001) != 0);
        }

        /**
         * <code>optional bool original = 2;</code>
         *
         * @return The original.
         */
        @java.lang.Override
        public boolean getOriginal() {
            return original_;
        }

        public static final int IMAGE_SIZE_FIELD_NUMBER = 3;
        private int imageSize_ = 0;

        /**
         * <code>optional int32 image_size = 3;</code>
         *
         * @return Whether the imageSize field is set.
         */
        @java.lang.Override
        public boolean hasImageSize() {
            return ((bitField0_ & 0x00000002) != 0);
        }

        /**
         * <code>optional int32 image_size = 3;</code>
         *
         * @return The imageSize.
         */
        @java.lang.Override
        public int getImageSize() {
            return imageSize_;
        }

        public static final int FILE_SIZE_FIELD_NUMBER = 4;
        private int fileSize_ = 0;

        /**
         * <code>optional int32 file_size = 4;</code>
         *
         * @return Whether the fileSize field is set.
         */
        @java.lang.Override
        public boolean hasFileSize() {
            return ((bitField0_ & 0x00000004) != 0);
        }

        /**
         * <code>optional int32 file_size = 4;</code>
         *
         * @return The fileSize.
         */
        @java.lang.Override
        public int getFileSize() {
            return fileSize_;
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
        public void writeTo(com.google.protobuf.CodedOutputStream output)
                throws java.io.IOException {
            if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(url_)) {
                com.google.protobuf.GeneratedMessageV3.writeString(output, 1, url_);
            }
            if (((bitField0_ & 0x00000001) != 0)) {
                output.writeBool(2, original_);
            }
            if (((bitField0_ & 0x00000002) != 0)) {
                output.writeInt32(3, imageSize_);
            }
            if (((bitField0_ & 0x00000004) != 0)) {
                output.writeInt32(4, fileSize_);
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
            if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(url_)) {
                size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, url_);
            }
            if (((bitField0_ & 0x00000001) != 0)) {
                size += com.google.protobuf.CodedOutputStream.computeBoolSize(2, original_);
            }
            if (((bitField0_ & 0x00000002) != 0)) {
                size += com.google.protobuf.CodedOutputStream.computeInt32Size(3, imageSize_);
            }
            if (((bitField0_ & 0x00000004) != 0)) {
                size += com.google.protobuf.CodedOutputStream.computeInt32Size(4, fileSize_);
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
            if (!(obj instanceof Description other)) {
                return super.equals(obj);
            }

            if (!getUrl().equals(other.getUrl())) {
                return false;
            }
            if (hasOriginal() != other.hasOriginal()) {
                return false;
            }
            if (hasOriginal()) {
                if (getOriginal() != other.getOriginal()) {
                    return false;
                }
            }
            if (hasImageSize() != other.hasImageSize()) {
                return false;
            }
            if (hasImageSize()) {
                if (getImageSize() != other.getImageSize()) {
                    return false;
                }
            }
            if (hasFileSize() != other.hasFileSize()) {
                return false;
            }
            if (hasFileSize()) {
                if (getFileSize() != other.getFileSize()) {
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
            hash = (37 * hash) + URL_FIELD_NUMBER;
            hash = (53 * hash) + getUrl().hashCode();
            if (hasOriginal()) {
                hash = (37 * hash) + ORIGINAL_FIELD_NUMBER;
                hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(getOriginal());
            }
            if (hasImageSize()) {
                hash = (37 * hash) + IMAGE_SIZE_FIELD_NUMBER;
                hash = (53 * hash) + getImageSize();
            }
            if (hasFileSize()) {
                hash = (37 * hash) + FILE_SIZE_FIELD_NUMBER;
                hash = (53 * hash) + getFileSize();
            }
            hash = (29 * hash) + getUnknownFields().hashCode();
            memoizedHashCode = hash;
            return hash;
        }

        public static im.turms.server.common.access.client.dto.model.file.ImageFile.Description parseFrom(
                java.nio.ByteBuffer data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static im.turms.server.common.access.client.dto.model.file.ImageFile.Description parseFrom(
                java.nio.ByteBuffer data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static im.turms.server.common.access.client.dto.model.file.ImageFile.Description parseFrom(
                com.google.protobuf.ByteString data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static im.turms.server.common.access.client.dto.model.file.ImageFile.Description parseFrom(
                com.google.protobuf.ByteString data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static im.turms.server.common.access.client.dto.model.file.ImageFile.Description parseFrom(
                byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static im.turms.server.common.access.client.dto.model.file.ImageFile.Description parseFrom(
                byte[] data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static im.turms.server.common.access.client.dto.model.file.ImageFile.Description parseFrom(
                java.io.InputStream input) throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
        }

        public static im.turms.server.common.access.client.dto.model.file.ImageFile.Description parseFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input, extensionRegistry);
        }

        public static im.turms.server.common.access.client.dto.model.file.ImageFile.Description parseDelimitedFrom(
                java.io.InputStream input) throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(PARSER,
                    input);
        }

        public static im.turms.server.common.access.client.dto.model.file.ImageFile.Description parseDelimitedFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
        }

        public static im.turms.server.common.access.client.dto.model.file.ImageFile.Description parseFrom(
                com.google.protobuf.CodedInputStream input) throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
        }

        public static im.turms.server.common.access.client.dto.model.file.ImageFile.Description parseFrom(
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
                im.turms.server.common.access.client.dto.model.file.ImageFile.Description prototype) {
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
         * Protobuf type {@code im.turms.proto.ImageFile.Description}
         */
        public static final class Builder
                extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
                // @@protoc_insertion_point(builder_implements:im.turms.proto.ImageFile.Description)
                im.turms.server.common.access.client.dto.model.file.ImageFile.DescriptionOrBuilder {
            public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
                return im.turms.server.common.access.client.dto.model.file.ImageFileOuterClass.internal_static_im_turms_proto_ImageFile_Description_descriptor;
            }

            @java.lang.Override
            protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return im.turms.server.common.access.client.dto.model.file.ImageFileOuterClass.internal_static_im_turms_proto_ImageFile_Description_fieldAccessorTable
                        .ensureFieldAccessorsInitialized(
                                im.turms.server.common.access.client.dto.model.file.ImageFile.Description.class,
                                im.turms.server.common.access.client.dto.model.file.ImageFile.Description.Builder.class);
            }

            // Construct using
            // im.turms.server.common.access.client.dto.model.file.ImageFile.Description.newBuilder()
            private Builder() {

            }

            private Builder(com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
                super(parent);

            }

            @java.lang.Override
            public Builder clear() {
                super.clear();
                bitField0_ = 0;
                url_ = "";
                original_ = false;
                imageSize_ = 0;
                fileSize_ = 0;
                return this;
            }

            @java.lang.Override
            public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
                return im.turms.server.common.access.client.dto.model.file.ImageFileOuterClass.internal_static_im_turms_proto_ImageFile_Description_descriptor;
            }

            @java.lang.Override
            public im.turms.server.common.access.client.dto.model.file.ImageFile.Description getDefaultInstanceForType() {
                return im.turms.server.common.access.client.dto.model.file.ImageFile.Description
                        .getDefaultInstance();
            }

            @java.lang.Override
            public im.turms.server.common.access.client.dto.model.file.ImageFile.Description build() {
                im.turms.server.common.access.client.dto.model.file.ImageFile.Description result =
                        buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return result;
            }

            @java.lang.Override
            public im.turms.server.common.access.client.dto.model.file.ImageFile.Description buildPartial() {
                im.turms.server.common.access.client.dto.model.file.ImageFile.Description result =
                        new im.turms.server.common.access.client.dto.model.file.ImageFile.Description(
                                this);
                if (bitField0_ != 0) {
                    buildPartial0(result);
                }
                onBuilt();
                return result;
            }

            private void buildPartial0(
                    im.turms.server.common.access.client.dto.model.file.ImageFile.Description result) {
                int from_bitField0_ = bitField0_;
                if (((from_bitField0_ & 0x00000001) != 0)) {
                    result.url_ = url_;
                }
                int to_bitField0_ = 0;
                if (((from_bitField0_ & 0x00000002) != 0)) {
                    result.original_ = original_;
                    to_bitField0_ |= 0x00000001;
                }
                if (((from_bitField0_ & 0x00000004) != 0)) {
                    result.imageSize_ = imageSize_;
                    to_bitField0_ |= 0x00000002;
                }
                if (((from_bitField0_ & 0x00000008) != 0)) {
                    result.fileSize_ = fileSize_;
                    to_bitField0_ |= 0x00000004;
                }
                result.bitField0_ |= to_bitField0_;
            }

            @java.lang.Override
            public Builder mergeFrom(com.google.protobuf.Message other) {
                if (other instanceof im.turms.server.common.access.client.dto.model.file.ImageFile.Description) {
                    return mergeFrom(
                            (im.turms.server.common.access.client.dto.model.file.ImageFile.Description) other);
                } else {
                    super.mergeFrom(other);
                    return this;
                }
            }

            public Builder mergeFrom(
                    im.turms.server.common.access.client.dto.model.file.ImageFile.Description other) {
                if (other == im.turms.server.common.access.client.dto.model.file.ImageFile.Description
                        .getDefaultInstance()) {
                    return this;
                }
                if (!other.getUrl()
                        .isEmpty()) {
                    url_ = other.url_;
                    bitField0_ |= 0x00000001;
                    onChanged();
                }
                if (other.hasOriginal()) {
                    setOriginal(other.getOriginal());
                }
                if (other.hasImageSize()) {
                    setImageSize(other.getImageSize());
                }
                if (other.hasFileSize()) {
                    setFileSize(other.getFileSize());
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
                            case 10 -> {
                                url_ = input.readStringRequireUtf8();
                                bitField0_ |= 0x00000001;
                            } // case 10
                            case 16 -> {
                                original_ = input.readBool();
                                bitField0_ |= 0x00000002;
                            } // case 16
                            case 24 -> {
                                imageSize_ = input.readInt32();
                                bitField0_ |= 0x00000004;
                            } // case 24
                            case 32 -> {
                                fileSize_ = input.readInt32();
                                bitField0_ |= 0x00000008;
                            } // case 32
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

            private java.lang.Object url_ = "";

            /**
             * <code>string url = 1;</code>
             *
             * @return The url.
             */
            public java.lang.String getUrl() {
                java.lang.Object ref = url_;
                if (!(ref instanceof java.lang.String)) {
                    com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                    java.lang.String s = bs.toStringUtf8();
                    url_ = s;
                    return s;
                } else {
                    return (java.lang.String) ref;
                }
            }

            /**
             * <code>string url = 1;</code>
             *
             * @return The bytes for url.
             */
            public com.google.protobuf.ByteString getUrlBytes() {
                java.lang.Object ref = url_;
                if (ref instanceof String) {
                    com.google.protobuf.ByteString b =
                            com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                    url_ = b;
                    return b;
                } else {
                    return (com.google.protobuf.ByteString) ref;
                }
            }

            /**
             * <code>string url = 1;</code>
             *
             * @param value The url to set.
             * @return This builder for chaining.
             */
            public Builder setUrl(java.lang.String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                url_ = value;
                bitField0_ |= 0x00000001;
                onChanged();
                return this;
            }

            /**
             * <code>string url = 1;</code>
             *
             * @return This builder for chaining.
             */
            public Builder clearUrl() {
                url_ = getDefaultInstance().getUrl();
                bitField0_ &= ~0x00000001;
                onChanged();
                return this;
            }

            /**
             * <code>string url = 1;</code>
             *
             * @param value The bytes for url to set.
             * @return This builder for chaining.
             */
            public Builder setUrlBytes(com.google.protobuf.ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                checkByteStringIsUtf8(value);
                url_ = value;
                bitField0_ |= 0x00000001;
                onChanged();
                return this;
            }

            private boolean original_;

            /**
             * <code>optional bool original = 2;</code>
             *
             * @return Whether the original field is set.
             */
            @java.lang.Override
            public boolean hasOriginal() {
                return ((bitField0_ & 0x00000002) != 0);
            }

            /**
             * <code>optional bool original = 2;</code>
             *
             * @return The original.
             */
            @java.lang.Override
            public boolean getOriginal() {
                return original_;
            }

            /**
             * <code>optional bool original = 2;</code>
             *
             * @param value The original to set.
             * @return This builder for chaining.
             */
            public Builder setOriginal(boolean value) {

                original_ = value;
                bitField0_ |= 0x00000002;
                onChanged();
                return this;
            }

            /**
             * <code>optional bool original = 2;</code>
             *
             * @return This builder for chaining.
             */
            public Builder clearOriginal() {
                bitField0_ &= ~0x00000002;
                original_ = false;
                onChanged();
                return this;
            }

            private int imageSize_;

            /**
             * <code>optional int32 image_size = 3;</code>
             *
             * @return Whether the imageSize field is set.
             */
            @java.lang.Override
            public boolean hasImageSize() {
                return ((bitField0_ & 0x00000004) != 0);
            }

            /**
             * <code>optional int32 image_size = 3;</code>
             *
             * @return The imageSize.
             */
            @java.lang.Override
            public int getImageSize() {
                return imageSize_;
            }

            /**
             * <code>optional int32 image_size = 3;</code>
             *
             * @param value The imageSize to set.
             * @return This builder for chaining.
             */
            public Builder setImageSize(int value) {

                imageSize_ = value;
                bitField0_ |= 0x00000004;
                onChanged();
                return this;
            }

            /**
             * <code>optional int32 image_size = 3;</code>
             *
             * @return This builder for chaining.
             */
            public Builder clearImageSize() {
                bitField0_ &= ~0x00000004;
                imageSize_ = 0;
                onChanged();
                return this;
            }

            private int fileSize_;

            /**
             * <code>optional int32 file_size = 4;</code>
             *
             * @return Whether the fileSize field is set.
             */
            @java.lang.Override
            public boolean hasFileSize() {
                return ((bitField0_ & 0x00000008) != 0);
            }

            /**
             * <code>optional int32 file_size = 4;</code>
             *
             * @return The fileSize.
             */
            @java.lang.Override
            public int getFileSize() {
                return fileSize_;
            }

            /**
             * <code>optional int32 file_size = 4;</code>
             *
             * @param value The fileSize to set.
             * @return This builder for chaining.
             */
            public Builder setFileSize(int value) {

                fileSize_ = value;
                bitField0_ |= 0x00000008;
                onChanged();
                return this;
            }

            /**
             * <code>optional int32 file_size = 4;</code>
             *
             * @return This builder for chaining.
             */
            public Builder clearFileSize() {
                bitField0_ &= ~0x00000008;
                fileSize_ = 0;
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

            // @@protoc_insertion_point(builder_scope:im.turms.proto.ImageFile.Description)
        }

        // @@protoc_insertion_point(class_scope:im.turms.proto.ImageFile.Description)
        private static final im.turms.server.common.access.client.dto.model.file.ImageFile.Description DEFAULT_INSTANCE;

        static {
            DEFAULT_INSTANCE =
                    new im.turms.server.common.access.client.dto.model.file.ImageFile.Description();
        }

        public static im.turms.server.common.access.client.dto.model.file.ImageFile.Description getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        private static final com.google.protobuf.Parser<Description> PARSER =
                new com.google.protobuf.AbstractParser<>() {
                    @java.lang.Override
                    public Description parsePartialFrom(
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

        public static com.google.protobuf.Parser<Description> parser() {
            return PARSER;
        }

        @java.lang.Override
        public com.google.protobuf.Parser<Description> getParserForType() {
            return PARSER;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.file.ImageFile.Description getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

    }

    private int bitField0_;
    public static final int DESCRIPTION_FIELD_NUMBER = 1;
    private im.turms.server.common.access.client.dto.model.file.ImageFile.Description description_;

    /**
     * <code>optional .im.turms.proto.ImageFile.Description description = 1;</code>
     *
     * @return Whether the description field is set.
     */
    @java.lang.Override
    public boolean hasDescription() {
        return ((bitField0_ & 0x00000001) != 0);
    }

    /**
     * <code>optional .im.turms.proto.ImageFile.Description description = 1;</code>
     *
     * @return The description.
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.file.ImageFile.Description getDescription() {
        return description_ == null
                ? im.turms.server.common.access.client.dto.model.file.ImageFile.Description
                        .getDefaultInstance()
                : description_;
    }

    /**
     * <code>optional .im.turms.proto.ImageFile.Description description = 1;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.file.ImageFile.DescriptionOrBuilder getDescriptionOrBuilder() {
        return description_ == null
                ? im.turms.server.common.access.client.dto.model.file.ImageFile.Description
                        .getDefaultInstance()
                : description_;
    }

    public static final int DATA_FIELD_NUMBER = 2;
    private com.google.protobuf.ByteString data_ = com.google.protobuf.ByteString.EMPTY;

    /**
     * <code>optional bytes data = 2;</code>
     *
     * @return Whether the data field is set.
     */
    @java.lang.Override
    public boolean hasData() {
        return ((bitField0_ & 0x00000002) != 0);
    }

    /**
     * <code>optional bytes data = 2;</code>
     *
     * @return The data.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getData() {
        return data_;
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
        if (((bitField0_ & 0x00000001) != 0)) {
            output.writeMessage(1, getDescription());
        }
        if (((bitField0_ & 0x00000002) != 0)) {
            output.writeBytes(2, data_);
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
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(1, getDescription());
        }
        if (((bitField0_ & 0x00000002) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeBytesSize(2, data_);
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
        if (!(obj instanceof ImageFile other)) {
            return super.equals(obj);
        }

        if (hasDescription() != other.hasDescription()) {
            return false;
        }
        if (hasDescription()) {
            if (!getDescription().equals(other.getDescription())) {
                return false;
            }
        }
        if (hasData() != other.hasData()) {
            return false;
        }
        if (hasData()) {
            if (!getData().equals(other.getData())) {
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
        if (hasDescription()) {
            hash = (37 * hash) + DESCRIPTION_FIELD_NUMBER;
            hash = (53 * hash) + getDescription().hashCode();
        }
        if (hasData()) {
            hash = (37 * hash) + DATA_FIELD_NUMBER;
            hash = (53 * hash) + getData().hashCode();
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.server.common.access.client.dto.model.file.ImageFile parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.file.ImageFile parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.file.ImageFile parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.file.ImageFile parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.file.ImageFile parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.file.ImageFile parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.file.ImageFile parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.file.ImageFile parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.file.ImageFile parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.file.ImageFile parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.file.ImageFile parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.file.ImageFile parseFrom(
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
            im.turms.server.common.access.client.dto.model.file.ImageFile prototype) {
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
     * Protobuf type {@code im.turms.proto.ImageFile}
     */
    public static final class Builder
            extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.ImageFile)
            im.turms.server.common.access.client.dto.model.file.ImageFileOrBuilder {
        public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.server.common.access.client.dto.model.file.ImageFileOuterClass.internal_static_im_turms_proto_ImageFile_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.server.common.access.client.dto.model.file.ImageFileOuterClass.internal_static_im_turms_proto_ImageFile_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.server.common.access.client.dto.model.file.ImageFile.class,
                            im.turms.server.common.access.client.dto.model.file.ImageFile.Builder.class);
        }

        // Construct using
        // im.turms.server.common.access.client.dto.model.file.ImageFile.newBuilder()
        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
            super(parent);
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders) {
                getDescriptionFieldBuilder();
            }
        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            description_ = null;
            if (descriptionBuilder_ != null) {
                descriptionBuilder_.dispose();
                descriptionBuilder_ = null;
            }
            data_ = com.google.protobuf.ByteString.EMPTY;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.server.common.access.client.dto.model.file.ImageFileOuterClass.internal_static_im_turms_proto_ImageFile_descriptor;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.file.ImageFile getDefaultInstanceForType() {
            return im.turms.server.common.access.client.dto.model.file.ImageFile
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.file.ImageFile build() {
            im.turms.server.common.access.client.dto.model.file.ImageFile result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.file.ImageFile buildPartial() {
            im.turms.server.common.access.client.dto.model.file.ImageFile result =
                    new im.turms.server.common.access.client.dto.model.file.ImageFile(this);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartial0(
                im.turms.server.common.access.client.dto.model.file.ImageFile result) {
            int from_bitField0_ = bitField0_;
            int to_bitField0_ = 0;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.description_ = descriptionBuilder_ == null
                        ? description_
                        : descriptionBuilder_.build();
                to_bitField0_ |= 0x00000001;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.data_ = data_;
                to_bitField0_ |= 0x00000002;
            }
            result.bitField0_ |= to_bitField0_;
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.server.common.access.client.dto.model.file.ImageFile) {
                return mergeFrom(
                        (im.turms.server.common.access.client.dto.model.file.ImageFile) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.server.common.access.client.dto.model.file.ImageFile other) {
            if (other == im.turms.server.common.access.client.dto.model.file.ImageFile
                    .getDefaultInstance()) {
                return this;
            }
            if (other.hasDescription()) {
                mergeDescription(other.getDescription());
            }
            if (other.hasData()) {
                setData(other.getData());
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
                        case 10 -> {
                            input.readMessage(getDescriptionFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            bitField0_ |= 0x00000001;
                        } // case 10
                        case 18 -> {
                            data_ = input.readBytes();
                            bitField0_ |= 0x00000002;
                        } // case 18
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

        private im.turms.server.common.access.client.dto.model.file.ImageFile.Description description_;
        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.model.file.ImageFile.Description, im.turms.server.common.access.client.dto.model.file.ImageFile.Description.Builder, im.turms.server.common.access.client.dto.model.file.ImageFile.DescriptionOrBuilder> descriptionBuilder_;

        /**
         * <code>optional .im.turms.proto.ImageFile.Description description = 1;</code>
         *
         * @return Whether the description field is set.
         */
        public boolean hasDescription() {
            return ((bitField0_ & 0x00000001) != 0);
        }

        /**
         * <code>optional .im.turms.proto.ImageFile.Description description = 1;</code>
         *
         * @return The description.
         */
        public im.turms.server.common.access.client.dto.model.file.ImageFile.Description getDescription() {
            if (descriptionBuilder_ == null) {
                return description_ == null
                        ? im.turms.server.common.access.client.dto.model.file.ImageFile.Description
                                .getDefaultInstance()
                        : description_;
            } else {
                return descriptionBuilder_.getMessage();
            }
        }

        /**
         * <code>optional .im.turms.proto.ImageFile.Description description = 1;</code>
         */
        public Builder setDescription(
                im.turms.server.common.access.client.dto.model.file.ImageFile.Description value) {
            if (descriptionBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                description_ = value;
            } else {
                descriptionBuilder_.setMessage(value);
            }
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>optional .im.turms.proto.ImageFile.Description description = 1;</code>
         */
        public Builder setDescription(
                im.turms.server.common.access.client.dto.model.file.ImageFile.Description.Builder builderForValue) {
            if (descriptionBuilder_ == null) {
                description_ = builderForValue.build();
            } else {
                descriptionBuilder_.setMessage(builderForValue.build());
            }
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>optional .im.turms.proto.ImageFile.Description description = 1;</code>
         */
        public Builder mergeDescription(
                im.turms.server.common.access.client.dto.model.file.ImageFile.Description value) {
            if (descriptionBuilder_ == null) {
                if (((bitField0_ & 0x00000001) != 0)
                        && description_ != null
                        && description_ != im.turms.server.common.access.client.dto.model.file.ImageFile.Description
                                .getDefaultInstance()) {
                    getDescriptionBuilder().mergeFrom(value);
                } else {
                    description_ = value;
                }
            } else {
                descriptionBuilder_.mergeFrom(value);
            }
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>optional .im.turms.proto.ImageFile.Description description = 1;</code>
         */
        public Builder clearDescription() {
            bitField0_ &= ~0x00000001;
            description_ = null;
            if (descriptionBuilder_ != null) {
                descriptionBuilder_.dispose();
                descriptionBuilder_ = null;
            }
            onChanged();
            return this;
        }

        /**
         * <code>optional .im.turms.proto.ImageFile.Description description = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.file.ImageFile.Description.Builder getDescriptionBuilder() {
            bitField0_ |= 0x00000001;
            onChanged();
            return getDescriptionFieldBuilder().getBuilder();
        }

        /**
         * <code>optional .im.turms.proto.ImageFile.Description description = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.file.ImageFile.DescriptionOrBuilder getDescriptionOrBuilder() {
            if (descriptionBuilder_ != null) {
                return descriptionBuilder_.getMessageOrBuilder();
            } else {
                return description_ == null
                        ? im.turms.server.common.access.client.dto.model.file.ImageFile.Description
                                .getDefaultInstance()
                        : description_;
            }
        }

        /**
         * <code>optional .im.turms.proto.ImageFile.Description description = 1;</code>
         */
        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.model.file.ImageFile.Description, im.turms.server.common.access.client.dto.model.file.ImageFile.Description.Builder, im.turms.server.common.access.client.dto.model.file.ImageFile.DescriptionOrBuilder> getDescriptionFieldBuilder() {
            if (descriptionBuilder_ == null) {
                descriptionBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<>(
                        getDescription(),
                        getParentForChildren(),
                        isClean());
                description_ = null;
            }
            return descriptionBuilder_;
        }

        private com.google.protobuf.ByteString data_ = com.google.protobuf.ByteString.EMPTY;

        /**
         * <code>optional bytes data = 2;</code>
         *
         * @return Whether the data field is set.
         */
        @java.lang.Override
        public boolean hasData() {
            return ((bitField0_ & 0x00000002) != 0);
        }

        /**
         * <code>optional bytes data = 2;</code>
         *
         * @return The data.
         */
        @java.lang.Override
        public com.google.protobuf.ByteString getData() {
            return data_;
        }

        /**
         * <code>optional bytes data = 2;</code>
         *
         * @param value The data to set.
         * @return This builder for chaining.
         */
        public Builder setData(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            data_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>optional bytes data = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearData() {
            bitField0_ &= ~0x00000002;
            data_ = getDefaultInstance().getData();
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

        // @@protoc_insertion_point(builder_scope:im.turms.proto.ImageFile)
    }

    // @@protoc_insertion_point(class_scope:im.turms.proto.ImageFile)
    private static final im.turms.server.common.access.client.dto.model.file.ImageFile DEFAULT_INSTANCE;

    static {
        DEFAULT_INSTANCE = new im.turms.server.common.access.client.dto.model.file.ImageFile();
    }

    public static im.turms.server.common.access.client.dto.model.file.ImageFile getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<ImageFile> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public ImageFile parsePartialFrom(
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

    public static com.google.protobuf.Parser<ImageFile> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<ImageFile> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.file.ImageFile getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}