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

package im.turms.client.model.proto.model.file;

/**
 * Protobuf type {@code im.turms.proto.ImageFile}
 */
public final class ImageFile
        extends com.google.protobuf.GeneratedMessageLite<ImageFile, ImageFile.Builder> implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.ImageFile)
        ImageFileOrBuilder {
    private ImageFile() {
        data_ = com.google.protobuf.ByteString.EMPTY;
    }

    public interface DescriptionOrBuilder extends
            // @@protoc_insertion_point(interface_extends:im.turms.proto.ImageFile.Description)
            com.google.protobuf.MessageLiteOrBuilder {

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
    public static final class Description extends
            com.google.protobuf.GeneratedMessageLite<Description, Description.Builder> implements
            // @@protoc_insertion_point(message_implements:im.turms.proto.ImageFile.Description)
            DescriptionOrBuilder {
        private Description() {
            url_ = "";
        }

        private int bitField0_;
        public static final int URL_FIELD_NUMBER = 1;
        private java.lang.String url_;

        /**
         * <code>string url = 1;</code>
         *
         * @return The url.
         */
        @java.lang.Override
        public java.lang.String getUrl() {
            return url_;
        }

        /**
         * <code>string url = 1;</code>
         *
         * @return The bytes for url.
         */
        @java.lang.Override
        public com.google.protobuf.ByteString getUrlBytes() {
            return com.google.protobuf.ByteString.copyFromUtf8(url_);
        }

        /**
         * <code>string url = 1;</code>
         *
         * @param value The url to set.
         */
        private void setUrl(java.lang.String value) {
            java.lang.Class<?> valueClass = value.getClass();

            url_ = value;
        }

        /**
         * <code>string url = 1;</code>
         */
        private void clearUrl() {

            url_ = getDefaultInstance().getUrl();
        }

        /**
         * <code>string url = 1;</code>
         *
         * @param value The bytes for url to set.
         */
        private void setUrlBytes(com.google.protobuf.ByteString value) {
            checkByteStringIsUtf8(value);
            url_ = value.toStringUtf8();

        }

        public static final int ORIGINAL_FIELD_NUMBER = 2;
        private boolean original_;

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

        /**
         * <code>optional bool original = 2;</code>
         *
         * @param value The original to set.
         */
        private void setOriginal(boolean value) {
            bitField0_ |= 0x00000001;
            original_ = value;
        }

        /**
         * <code>optional bool original = 2;</code>
         */
        private void clearOriginal() {
            bitField0_ &= ~0x00000001;
            original_ = false;
        }

        public static final int IMAGE_SIZE_FIELD_NUMBER = 3;
        private int imageSize_;

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

        /**
         * <code>optional int32 image_size = 3;</code>
         *
         * @param value The imageSize to set.
         */
        private void setImageSize(int value) {
            bitField0_ |= 0x00000002;
            imageSize_ = value;
        }

        /**
         * <code>optional int32 image_size = 3;</code>
         */
        private void clearImageSize() {
            bitField0_ &= ~0x00000002;
            imageSize_ = 0;
        }

        public static final int FILE_SIZE_FIELD_NUMBER = 4;
        private int fileSize_;

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

        /**
         * <code>optional int32 file_size = 4;</code>
         *
         * @param value The fileSize to set.
         */
        private void setFileSize(int value) {
            bitField0_ |= 0x00000004;
            fileSize_ = value;
        }

        /**
         * <code>optional int32 file_size = 4;</code>
         */
        private void clearFileSize() {
            bitField0_ &= ~0x00000004;
            fileSize_ = 0;
        }

        public static im.turms.client.model.proto.model.file.ImageFile.Description parseFrom(
                java.nio.ByteBuffer data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static im.turms.client.model.proto.model.file.ImageFile.Description parseFrom(
                java.nio.ByteBuffer data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite
                    .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static im.turms.client.model.proto.model.file.ImageFile.Description parseFrom(
                com.google.protobuf.ByteString data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static im.turms.client.model.proto.model.file.ImageFile.Description parseFrom(
                com.google.protobuf.ByteString data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite
                    .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static im.turms.client.model.proto.model.file.ImageFile.Description parseFrom(
                byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static im.turms.client.model.proto.model.file.ImageFile.Description parseFrom(
                byte[] data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite
                    .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static im.turms.client.model.proto.model.file.ImageFile.Description parseFrom(
                java.io.InputStream input) throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static im.turms.client.model.proto.model.file.ImageFile.Description parseFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageLite
                    .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static im.turms.client.model.proto.model.file.ImageFile.Description parseDelimitedFrom(
                java.io.InputStream input) throws java.io.IOException {
            return parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static im.turms.client.model.proto.model.file.ImageFile.Description parseDelimitedFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static im.turms.client.model.proto.model.file.ImageFile.Description parseFrom(
                com.google.protobuf.CodedInputStream input) throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static im.turms.client.model.proto.model.file.ImageFile.Description parseFrom(
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
                im.turms.client.model.proto.model.file.ImageFile.Description prototype) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
        }

        /**
         * Protobuf type {@code im.turms.proto.ImageFile.Description}
         */
        public static final class Builder extends
                com.google.protobuf.GeneratedMessageLite.Builder<im.turms.client.model.proto.model.file.ImageFile.Description, Builder>
                implements
                // @@protoc_insertion_point(builder_implements:im.turms.proto.ImageFile.Description)
                im.turms.client.model.proto.model.file.ImageFile.DescriptionOrBuilder {
            // Construct using
            // im.turms.client.model.proto.model.file.ImageFile.Description.newBuilder()
            private Builder() {
                super(DEFAULT_INSTANCE);
            }

            /**
             * <code>string url = 1;</code>
             *
             * @return The url.
             */
            @java.lang.Override
            public java.lang.String getUrl() {
                return instance.getUrl();
            }

            /**
             * <code>string url = 1;</code>
             *
             * @return The bytes for url.
             */
            @java.lang.Override
            public com.google.protobuf.ByteString getUrlBytes() {
                return instance.getUrlBytes();
            }

            /**
             * <code>string url = 1;</code>
             *
             * @param value The url to set.
             * @return This builder for chaining.
             */
            public Builder setUrl(java.lang.String value) {
                copyOnWrite();
                instance.setUrl(value);
                return this;
            }

            /**
             * <code>string url = 1;</code>
             *
             * @return This builder for chaining.
             */
            public Builder clearUrl() {
                copyOnWrite();
                instance.clearUrl();
                return this;
            }

            /**
             * <code>string url = 1;</code>
             *
             * @param value The bytes for url to set.
             * @return This builder for chaining.
             */
            public Builder setUrlBytes(com.google.protobuf.ByteString value) {
                copyOnWrite();
                instance.setUrlBytes(value);
                return this;
            }

            /**
             * <code>optional bool original = 2;</code>
             *
             * @return Whether the original field is set.
             */
            @java.lang.Override
            public boolean hasOriginal() {
                return instance.hasOriginal();
            }

            /**
             * <code>optional bool original = 2;</code>
             *
             * @return The original.
             */
            @java.lang.Override
            public boolean getOriginal() {
                return instance.getOriginal();
            }

            /**
             * <code>optional bool original = 2;</code>
             *
             * @param value The original to set.
             * @return This builder for chaining.
             */
            public Builder setOriginal(boolean value) {
                copyOnWrite();
                instance.setOriginal(value);
                return this;
            }

            /**
             * <code>optional bool original = 2;</code>
             *
             * @return This builder for chaining.
             */
            public Builder clearOriginal() {
                copyOnWrite();
                instance.clearOriginal();
                return this;
            }

            /**
             * <code>optional int32 image_size = 3;</code>
             *
             * @return Whether the imageSize field is set.
             */
            @java.lang.Override
            public boolean hasImageSize() {
                return instance.hasImageSize();
            }

            /**
             * <code>optional int32 image_size = 3;</code>
             *
             * @return The imageSize.
             */
            @java.lang.Override
            public int getImageSize() {
                return instance.getImageSize();
            }

            /**
             * <code>optional int32 image_size = 3;</code>
             *
             * @param value The imageSize to set.
             * @return This builder for chaining.
             */
            public Builder setImageSize(int value) {
                copyOnWrite();
                instance.setImageSize(value);
                return this;
            }

            /**
             * <code>optional int32 image_size = 3;</code>
             *
             * @return This builder for chaining.
             */
            public Builder clearImageSize() {
                copyOnWrite();
                instance.clearImageSize();
                return this;
            }

            /**
             * <code>optional int32 file_size = 4;</code>
             *
             * @return Whether the fileSize field is set.
             */
            @java.lang.Override
            public boolean hasFileSize() {
                return instance.hasFileSize();
            }

            /**
             * <code>optional int32 file_size = 4;</code>
             *
             * @return The fileSize.
             */
            @java.lang.Override
            public int getFileSize() {
                return instance.getFileSize();
            }

            /**
             * <code>optional int32 file_size = 4;</code>
             *
             * @param value The fileSize to set.
             * @return This builder for chaining.
             */
            public Builder setFileSize(int value) {
                copyOnWrite();
                instance.setFileSize(value);
                return this;
            }

            /**
             * <code>optional int32 file_size = 4;</code>
             *
             * @return This builder for chaining.
             */
            public Builder clearFileSize() {
                copyOnWrite();
                instance.clearFileSize();
                return this;
            }

            // @@protoc_insertion_point(builder_scope:im.turms.proto.ImageFile.Description)
        }

        @java.lang.Override
        @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
        protected final java.lang.Object dynamicMethod(
                com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
                java.lang.Object arg0,
                java.lang.Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE: {
                    return new im.turms.client.model.proto.model.file.ImageFile.Description();
                }
                case NEW_BUILDER: {
                    return new Builder();
                }
                case BUILD_MESSAGE_INFO: {
                    java.lang.Object[] objects = new java.lang.Object[]{"bitField0_",
                            "url_",
                            "original_",
                            "imageSize_",
                            "fileSize_",};
                    java.lang.String info =
                            "\u0000\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001\u0208\u0002\u1007"
                                    + "\u0000\u0003\u1004\u0001\u0004\u1004\u0002";
                    return newMessageInfo(DEFAULT_INSTANCE, info, objects);
                }
                // fall through
                case GET_DEFAULT_INSTANCE: {
                    return DEFAULT_INSTANCE;
                }
                case GET_PARSER: {
                    com.google.protobuf.Parser<im.turms.client.model.proto.model.file.ImageFile.Description> parser =
                            PARSER;
                    if (parser == null) {
                        synchronized (im.turms.client.model.proto.model.file.ImageFile.Description.class) {
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

        // @@protoc_insertion_point(class_scope:im.turms.proto.ImageFile.Description)
        private static final im.turms.client.model.proto.model.file.ImageFile.Description DEFAULT_INSTANCE;

        static {
            Description defaultInstance = new Description();
            // New instances are implicitly immutable so no need to make
            // immutable.
            DEFAULT_INSTANCE = defaultInstance;
            com.google.protobuf.GeneratedMessageLite.registerDefaultInstance(Description.class,
                    defaultInstance);
        }

        public static im.turms.client.model.proto.model.file.ImageFile.Description getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        private static volatile com.google.protobuf.Parser<Description> PARSER;

        public static com.google.protobuf.Parser<Description> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    private int bitField0_;
    public static final int DESCRIPTION_FIELD_NUMBER = 1;
    private im.turms.client.model.proto.model.file.ImageFile.Description description_;

    /**
     * <code>optional .im.turms.proto.ImageFile.Description description = 1;</code>
     */
    @java.lang.Override
    public boolean hasDescription() {
        return ((bitField0_ & 0x00000001) != 0);
    }

    /**
     * <code>optional .im.turms.proto.ImageFile.Description description = 1;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.model.file.ImageFile.Description getDescription() {
        return description_ == null
                ? im.turms.client.model.proto.model.file.ImageFile.Description.getDefaultInstance()
                : description_;
    }

    /**
     * <code>optional .im.turms.proto.ImageFile.Description description = 1;</code>
     */
    private void setDescription(
            im.turms.client.model.proto.model.file.ImageFile.Description value) {
        value.getClass();
        description_ = value;
        bitField0_ |= 0x00000001;
    }

    /**
     * <code>optional .im.turms.proto.ImageFile.Description description = 1;</code>
     */
    @java.lang.SuppressWarnings({"ReferenceEquality"})
    private void mergeDescription(
            im.turms.client.model.proto.model.file.ImageFile.Description value) {
        value.getClass();
        if (description_ != null
                && description_ != im.turms.client.model.proto.model.file.ImageFile.Description
                        .getDefaultInstance()) {
            description_ = im.turms.client.model.proto.model.file.ImageFile.Description
                    .newBuilder(description_)
                    .mergeFrom(value)
                    .buildPartial();
        } else {
            description_ = value;
        }
        bitField0_ |= 0x00000001;
    }

    /**
     * <code>optional .im.turms.proto.ImageFile.Description description = 1;</code>
     */
    private void clearDescription() {
        description_ = null;
        bitField0_ &= ~0x00000001;
    }

    public static final int DATA_FIELD_NUMBER = 2;
    private com.google.protobuf.ByteString data_;

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
     */
    private void setData(com.google.protobuf.ByteString value) {
        java.lang.Class<?> valueClass = value.getClass();
        bitField0_ |= 0x00000002;
        data_ = value;
    }

    /**
     * <code>optional bytes data = 2;</code>
     */
    private void clearData() {
        bitField0_ &= ~0x00000002;
        data_ = getDefaultInstance().getData();
    }

    public static im.turms.client.model.proto.model.file.ImageFile parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.model.file.ImageFile parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.file.ImageFile parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.model.file.ImageFile parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.file.ImageFile parseFrom(byte[] data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.model.file.ImageFile parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.file.ImageFile parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.model.file.ImageFile parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.file.ImageFile parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.model.file.ImageFile parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.file.ImageFile parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.model.file.ImageFile parseFrom(
            com.google.protobuf.CodedInputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(im.turms.client.model.proto.model.file.ImageFile prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /**
     * Protobuf type {@code im.turms.proto.ImageFile}
     */
    public static final class Builder extends
            com.google.protobuf.GeneratedMessageLite.Builder<im.turms.client.model.proto.model.file.ImageFile, Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.ImageFile)
            im.turms.client.model.proto.model.file.ImageFileOrBuilder {
        // Construct using im.turms.client.model.proto.model.file.ImageFile.newBuilder()
        private Builder() {
            super(DEFAULT_INSTANCE);
        }

        /**
         * <code>optional .im.turms.proto.ImageFile.Description description = 1;</code>
         */
        @java.lang.Override
        public boolean hasDescription() {
            return instance.hasDescription();
        }

        /**
         * <code>optional .im.turms.proto.ImageFile.Description description = 1;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.model.file.ImageFile.Description getDescription() {
            return instance.getDescription();
        }

        /**
         * <code>optional .im.turms.proto.ImageFile.Description description = 1;</code>
         */
        public Builder setDescription(
                im.turms.client.model.proto.model.file.ImageFile.Description value) {
            copyOnWrite();
            instance.setDescription(value);
            return this;
        }

        /**
         * <code>optional .im.turms.proto.ImageFile.Description description = 1;</code>
         */
        public Builder setDescription(
                im.turms.client.model.proto.model.file.ImageFile.Description.Builder builderForValue) {
            copyOnWrite();
            instance.setDescription(builderForValue.build());
            return this;
        }

        /**
         * <code>optional .im.turms.proto.ImageFile.Description description = 1;</code>
         */
        public Builder mergeDescription(
                im.turms.client.model.proto.model.file.ImageFile.Description value) {
            copyOnWrite();
            instance.mergeDescription(value);
            return this;
        }

        /**
         * <code>optional .im.turms.proto.ImageFile.Description description = 1;</code>
         */
        public Builder clearDescription() {
            copyOnWrite();
            instance.clearDescription();
            return this;
        }

        /**
         * <code>optional bytes data = 2;</code>
         *
         * @return Whether the data field is set.
         */
        @java.lang.Override
        public boolean hasData() {
            return instance.hasData();
        }

        /**
         * <code>optional bytes data = 2;</code>
         *
         * @return The data.
         */
        @java.lang.Override
        public com.google.protobuf.ByteString getData() {
            return instance.getData();
        }

        /**
         * <code>optional bytes data = 2;</code>
         *
         * @param value The data to set.
         * @return This builder for chaining.
         */
        public Builder setData(com.google.protobuf.ByteString value) {
            copyOnWrite();
            instance.setData(value);
            return this;
        }

        /**
         * <code>optional bytes data = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearData() {
            copyOnWrite();
            instance.clearData();
            return this;
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.ImageFile)
    }

    @java.lang.Override
    @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
    protected final java.lang.Object dynamicMethod(
            com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
            java.lang.Object arg0,
            java.lang.Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE: {
                return new im.turms.client.model.proto.model.file.ImageFile();
            }
            case NEW_BUILDER: {
                return new Builder();
            }
            case BUILD_MESSAGE_INFO: {
                java.lang.Object[] objects =
                        new java.lang.Object[]{"bitField0_", "description_", "data_",};
                java.lang.String info =
                        "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u1009\u0000\u0002"
                                + "\u100a\u0001";
                return newMessageInfo(DEFAULT_INSTANCE, info, objects);
            }
            // fall through
            case GET_DEFAULT_INSTANCE: {
                return DEFAULT_INSTANCE;
            }
            case GET_PARSER: {
                com.google.protobuf.Parser<im.turms.client.model.proto.model.file.ImageFile> parser =
                        PARSER;
                if (parser == null) {
                    synchronized (im.turms.client.model.proto.model.file.ImageFile.class) {
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

    // @@protoc_insertion_point(class_scope:im.turms.proto.ImageFile)
    private static final im.turms.client.model.proto.model.file.ImageFile DEFAULT_INSTANCE;

    static {
        ImageFile defaultInstance = new ImageFile();
        // New instances are implicitly immutable so no need to make
        // immutable.
        DEFAULT_INSTANCE = defaultInstance;
        com.google.protobuf.GeneratedMessageLite.registerDefaultInstance(ImageFile.class,
                defaultInstance);
    }

    public static im.turms.client.model.proto.model.file.ImageFile getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static volatile com.google.protobuf.Parser<ImageFile> PARSER;

    public static com.google.protobuf.Parser<ImageFile> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}