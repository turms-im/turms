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
 * Protobuf type {@code im.turms.proto.VideoFile}
 */
public final class VideoFile
        extends com.google.protobuf.GeneratedMessageLite<VideoFile, VideoFile.Builder> implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.VideoFile)
        VideoFileOrBuilder {
    private VideoFile() {
        data_ = com.google.protobuf.ByteString.EMPTY;
    }

    public interface DescriptionOrBuilder extends
            // @@protoc_insertion_point(interface_extends:im.turms.proto.VideoFile.Description)
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
         * <code>optional int32 duration = 2;</code>
         *
         * @return Whether the duration field is set.
         */
        boolean hasDuration();

        /**
         * <code>optional int32 duration = 2;</code>
         *
         * @return The duration.
         */
        int getDuration();

        /**
         * <code>optional int32 size = 3;</code>
         *
         * @return Whether the size field is set.
         */
        boolean hasSize();

        /**
         * <code>optional int32 size = 3;</code>
         *
         * @return The size.
         */
        int getSize();

        /**
         * <code>optional string format = 4;</code>
         *
         * @return Whether the format field is set.
         */
        boolean hasFormat();

        /**
         * <code>optional string format = 4;</code>
         *
         * @return The format.
         */
        java.lang.String getFormat();

        /**
         * <code>optional string format = 4;</code>
         *
         * @return The bytes for format.
         */
        com.google.protobuf.ByteString getFormatBytes();
    }

    /**
     * Protobuf type {@code im.turms.proto.VideoFile.Description}
     */
    public static final class Description extends
            com.google.protobuf.GeneratedMessageLite<Description, Description.Builder> implements
            // @@protoc_insertion_point(message_implements:im.turms.proto.VideoFile.Description)
            DescriptionOrBuilder {
        private Description() {
            url_ = "";
            format_ = "";
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

        public static final int DURATION_FIELD_NUMBER = 2;
        private int duration_;

        /**
         * <code>optional int32 duration = 2;</code>
         *
         * @return Whether the duration field is set.
         */
        @java.lang.Override
        public boolean hasDuration() {
            return ((bitField0_ & 0x00000001) != 0);
        }

        /**
         * <code>optional int32 duration = 2;</code>
         *
         * @return The duration.
         */
        @java.lang.Override
        public int getDuration() {
            return duration_;
        }

        /**
         * <code>optional int32 duration = 2;</code>
         *
         * @param value The duration to set.
         */
        private void setDuration(int value) {
            bitField0_ |= 0x00000001;
            duration_ = value;
        }

        /**
         * <code>optional int32 duration = 2;</code>
         */
        private void clearDuration() {
            bitField0_ &= ~0x00000001;
            duration_ = 0;
        }

        public static final int SIZE_FIELD_NUMBER = 3;
        private int size_;

        /**
         * <code>optional int32 size = 3;</code>
         *
         * @return Whether the size field is set.
         */
        @java.lang.Override
        public boolean hasSize() {
            return ((bitField0_ & 0x00000002) != 0);
        }

        /**
         * <code>optional int32 size = 3;</code>
         *
         * @return The size.
         */
        @java.lang.Override
        public int getSize() {
            return size_;
        }

        /**
         * <code>optional int32 size = 3;</code>
         *
         * @param value The size to set.
         */
        private void setSize(int value) {
            bitField0_ |= 0x00000002;
            size_ = value;
        }

        /**
         * <code>optional int32 size = 3;</code>
         */
        private void clearSize() {
            bitField0_ &= ~0x00000002;
            size_ = 0;
        }

        public static final int FORMAT_FIELD_NUMBER = 4;
        private java.lang.String format_;

        /**
         * <code>optional string format = 4;</code>
         *
         * @return Whether the format field is set.
         */
        @java.lang.Override
        public boolean hasFormat() {
            return ((bitField0_ & 0x00000004) != 0);
        }

        /**
         * <code>optional string format = 4;</code>
         *
         * @return The format.
         */
        @java.lang.Override
        public java.lang.String getFormat() {
            return format_;
        }

        /**
         * <code>optional string format = 4;</code>
         *
         * @return The bytes for format.
         */
        @java.lang.Override
        public com.google.protobuf.ByteString getFormatBytes() {
            return com.google.protobuf.ByteString.copyFromUtf8(format_);
        }

        /**
         * <code>optional string format = 4;</code>
         *
         * @param value The format to set.
         */
        private void setFormat(java.lang.String value) {
            java.lang.Class<?> valueClass = value.getClass();
            bitField0_ |= 0x00000004;
            format_ = value;
        }

        /**
         * <code>optional string format = 4;</code>
         */
        private void clearFormat() {
            bitField0_ &= ~0x00000004;
            format_ = getDefaultInstance().getFormat();
        }

        /**
         * <code>optional string format = 4;</code>
         *
         * @param value The bytes for format to set.
         */
        private void setFormatBytes(com.google.protobuf.ByteString value) {
            checkByteStringIsUtf8(value);
            format_ = value.toStringUtf8();
            bitField0_ |= 0x00000004;
        }

        public static im.turms.client.model.proto.model.file.VideoFile.Description parseFrom(
                java.nio.ByteBuffer data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static im.turms.client.model.proto.model.file.VideoFile.Description parseFrom(
                java.nio.ByteBuffer data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite
                    .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static im.turms.client.model.proto.model.file.VideoFile.Description parseFrom(
                com.google.protobuf.ByteString data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static im.turms.client.model.proto.model.file.VideoFile.Description parseFrom(
                com.google.protobuf.ByteString data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite
                    .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static im.turms.client.model.proto.model.file.VideoFile.Description parseFrom(
                byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static im.turms.client.model.proto.model.file.VideoFile.Description parseFrom(
                byte[] data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite
                    .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static im.turms.client.model.proto.model.file.VideoFile.Description parseFrom(
                java.io.InputStream input) throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static im.turms.client.model.proto.model.file.VideoFile.Description parseFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageLite
                    .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static im.turms.client.model.proto.model.file.VideoFile.Description parseDelimitedFrom(
                java.io.InputStream input) throws java.io.IOException {
            return parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static im.turms.client.model.proto.model.file.VideoFile.Description parseDelimitedFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static im.turms.client.model.proto.model.file.VideoFile.Description parseFrom(
                com.google.protobuf.CodedInputStream input) throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static im.turms.client.model.proto.model.file.VideoFile.Description parseFrom(
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
                im.turms.client.model.proto.model.file.VideoFile.Description prototype) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
        }

        /**
         * Protobuf type {@code im.turms.proto.VideoFile.Description}
         */
        public static final class Builder extends
                com.google.protobuf.GeneratedMessageLite.Builder<im.turms.client.model.proto.model.file.VideoFile.Description, Builder>
                implements
                // @@protoc_insertion_point(builder_implements:im.turms.proto.VideoFile.Description)
                im.turms.client.model.proto.model.file.VideoFile.DescriptionOrBuilder {
            // Construct using
            // im.turms.client.model.proto.model.file.VideoFile.Description.newBuilder()
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
             * <code>optional int32 duration = 2;</code>
             *
             * @return Whether the duration field is set.
             */
            @java.lang.Override
            public boolean hasDuration() {
                return instance.hasDuration();
            }

            /**
             * <code>optional int32 duration = 2;</code>
             *
             * @return The duration.
             */
            @java.lang.Override
            public int getDuration() {
                return instance.getDuration();
            }

            /**
             * <code>optional int32 duration = 2;</code>
             *
             * @param value The duration to set.
             * @return This builder for chaining.
             */
            public Builder setDuration(int value) {
                copyOnWrite();
                instance.setDuration(value);
                return this;
            }

            /**
             * <code>optional int32 duration = 2;</code>
             *
             * @return This builder for chaining.
             */
            public Builder clearDuration() {
                copyOnWrite();
                instance.clearDuration();
                return this;
            }

            /**
             * <code>optional int32 size = 3;</code>
             *
             * @return Whether the size field is set.
             */
            @java.lang.Override
            public boolean hasSize() {
                return instance.hasSize();
            }

            /**
             * <code>optional int32 size = 3;</code>
             *
             * @return The size.
             */
            @java.lang.Override
            public int getSize() {
                return instance.getSize();
            }

            /**
             * <code>optional int32 size = 3;</code>
             *
             * @param value The size to set.
             * @return This builder for chaining.
             */
            public Builder setSize(int value) {
                copyOnWrite();
                instance.setSize(value);
                return this;
            }

            /**
             * <code>optional int32 size = 3;</code>
             *
             * @return This builder for chaining.
             */
            public Builder clearSize() {
                copyOnWrite();
                instance.clearSize();
                return this;
            }

            /**
             * <code>optional string format = 4;</code>
             *
             * @return Whether the format field is set.
             */
            @java.lang.Override
            public boolean hasFormat() {
                return instance.hasFormat();
            }

            /**
             * <code>optional string format = 4;</code>
             *
             * @return The format.
             */
            @java.lang.Override
            public java.lang.String getFormat() {
                return instance.getFormat();
            }

            /**
             * <code>optional string format = 4;</code>
             *
             * @return The bytes for format.
             */
            @java.lang.Override
            public com.google.protobuf.ByteString getFormatBytes() {
                return instance.getFormatBytes();
            }

            /**
             * <code>optional string format = 4;</code>
             *
             * @param value The format to set.
             * @return This builder for chaining.
             */
            public Builder setFormat(java.lang.String value) {
                copyOnWrite();
                instance.setFormat(value);
                return this;
            }

            /**
             * <code>optional string format = 4;</code>
             *
             * @return This builder for chaining.
             */
            public Builder clearFormat() {
                copyOnWrite();
                instance.clearFormat();
                return this;
            }

            /**
             * <code>optional string format = 4;</code>
             *
             * @param value The bytes for format to set.
             * @return This builder for chaining.
             */
            public Builder setFormatBytes(com.google.protobuf.ByteString value) {
                copyOnWrite();
                instance.setFormatBytes(value);
                return this;
            }

            // @@protoc_insertion_point(builder_scope:im.turms.proto.VideoFile.Description)
        }

        @java.lang.Override
        @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
        protected final java.lang.Object dynamicMethod(
                com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
                java.lang.Object arg0,
                java.lang.Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE: {
                    return new im.turms.client.model.proto.model.file.VideoFile.Description();
                }
                case NEW_BUILDER: {
                    return new Builder();
                }
                case BUILD_MESSAGE_INFO: {
                    java.lang.Object[] objects = new java.lang.Object[]{"bitField0_",
                            "url_",
                            "duration_",
                            "size_",
                            "format_",};
                    java.lang.String info =
                            "\u0000\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001\u0208\u0002\u1004"
                                    + "\u0000\u0003\u1004\u0001\u0004\u1208\u0002";
                    return newMessageInfo(DEFAULT_INSTANCE, info, objects);
                }
                // fall through
                case GET_DEFAULT_INSTANCE: {
                    return DEFAULT_INSTANCE;
                }
                case GET_PARSER: {
                    com.google.protobuf.Parser<im.turms.client.model.proto.model.file.VideoFile.Description> parser =
                            PARSER;
                    if (parser == null) {
                        synchronized (im.turms.client.model.proto.model.file.VideoFile.Description.class) {
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

        // @@protoc_insertion_point(class_scope:im.turms.proto.VideoFile.Description)
        private static final im.turms.client.model.proto.model.file.VideoFile.Description DEFAULT_INSTANCE;

        static {
            Description defaultInstance = new Description();
            // New instances are implicitly immutable so no need to make
            // immutable.
            DEFAULT_INSTANCE = defaultInstance;
            com.google.protobuf.GeneratedMessageLite.registerDefaultInstance(Description.class,
                    defaultInstance);
        }

        public static im.turms.client.model.proto.model.file.VideoFile.Description getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        private static volatile com.google.protobuf.Parser<Description> PARSER;

        public static com.google.protobuf.Parser<Description> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    private int bitField0_;
    public static final int DESCRIPTION_FIELD_NUMBER = 1;
    private im.turms.client.model.proto.model.file.VideoFile.Description description_;

    /**
     * <code>optional .im.turms.proto.VideoFile.Description description = 1;</code>
     */
    @java.lang.Override
    public boolean hasDescription() {
        return ((bitField0_ & 0x00000001) != 0);
    }

    /**
     * <code>optional .im.turms.proto.VideoFile.Description description = 1;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.model.file.VideoFile.Description getDescription() {
        return description_ == null
                ? im.turms.client.model.proto.model.file.VideoFile.Description.getDefaultInstance()
                : description_;
    }

    /**
     * <code>optional .im.turms.proto.VideoFile.Description description = 1;</code>
     */
    private void setDescription(
            im.turms.client.model.proto.model.file.VideoFile.Description value) {
        value.getClass();
        description_ = value;
        bitField0_ |= 0x00000001;
    }

    /**
     * <code>optional .im.turms.proto.VideoFile.Description description = 1;</code>
     */
    @java.lang.SuppressWarnings({"ReferenceEquality"})
    private void mergeDescription(
            im.turms.client.model.proto.model.file.VideoFile.Description value) {
        value.getClass();
        if (description_ != null
                && description_ != im.turms.client.model.proto.model.file.VideoFile.Description
                        .getDefaultInstance()) {
            description_ = im.turms.client.model.proto.model.file.VideoFile.Description
                    .newBuilder(description_)
                    .mergeFrom(value)
                    .buildPartial();
        } else {
            description_ = value;
        }
        bitField0_ |= 0x00000001;
    }

    /**
     * <code>optional .im.turms.proto.VideoFile.Description description = 1;</code>
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

    public static im.turms.client.model.proto.model.file.VideoFile parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.model.file.VideoFile parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.file.VideoFile parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.model.file.VideoFile parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.file.VideoFile parseFrom(byte[] data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.model.file.VideoFile parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.file.VideoFile parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.model.file.VideoFile parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.file.VideoFile parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.model.file.VideoFile parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.file.VideoFile parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.model.file.VideoFile parseFrom(
            com.google.protobuf.CodedInputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(im.turms.client.model.proto.model.file.VideoFile prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /**
     * Protobuf type {@code im.turms.proto.VideoFile}
     */
    public static final class Builder extends
            com.google.protobuf.GeneratedMessageLite.Builder<im.turms.client.model.proto.model.file.VideoFile, Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.VideoFile)
            im.turms.client.model.proto.model.file.VideoFileOrBuilder {
        // Construct using im.turms.client.model.proto.model.file.VideoFile.newBuilder()
        private Builder() {
            super(DEFAULT_INSTANCE);
        }

        /**
         * <code>optional .im.turms.proto.VideoFile.Description description = 1;</code>
         */
        @java.lang.Override
        public boolean hasDescription() {
            return instance.hasDescription();
        }

        /**
         * <code>optional .im.turms.proto.VideoFile.Description description = 1;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.model.file.VideoFile.Description getDescription() {
            return instance.getDescription();
        }

        /**
         * <code>optional .im.turms.proto.VideoFile.Description description = 1;</code>
         */
        public Builder setDescription(
                im.turms.client.model.proto.model.file.VideoFile.Description value) {
            copyOnWrite();
            instance.setDescription(value);
            return this;
        }

        /**
         * <code>optional .im.turms.proto.VideoFile.Description description = 1;</code>
         */
        public Builder setDescription(
                im.turms.client.model.proto.model.file.VideoFile.Description.Builder builderForValue) {
            copyOnWrite();
            instance.setDescription(builderForValue.build());
            return this;
        }

        /**
         * <code>optional .im.turms.proto.VideoFile.Description description = 1;</code>
         */
        public Builder mergeDescription(
                im.turms.client.model.proto.model.file.VideoFile.Description value) {
            copyOnWrite();
            instance.mergeDescription(value);
            return this;
        }

        /**
         * <code>optional .im.turms.proto.VideoFile.Description description = 1;</code>
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

        // @@protoc_insertion_point(builder_scope:im.turms.proto.VideoFile)
    }

    @java.lang.Override
    @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
    protected final java.lang.Object dynamicMethod(
            com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
            java.lang.Object arg0,
            java.lang.Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE: {
                return new im.turms.client.model.proto.model.file.VideoFile();
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
                com.google.protobuf.Parser<im.turms.client.model.proto.model.file.VideoFile> parser =
                        PARSER;
                if (parser == null) {
                    synchronized (im.turms.client.model.proto.model.file.VideoFile.class) {
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

    // @@protoc_insertion_point(class_scope:im.turms.proto.VideoFile)
    private static final im.turms.client.model.proto.model.file.VideoFile DEFAULT_INSTANCE;

    static {
        VideoFile defaultInstance = new VideoFile();
        // New instances are implicitly immutable so no need to make
        // immutable.
        DEFAULT_INSTANCE = defaultInstance;
        com.google.protobuf.GeneratedMessageLite.registerDefaultInstance(VideoFile.class,
                defaultInstance);
    }

    public static im.turms.client.model.proto.model.file.VideoFile getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static volatile com.google.protobuf.Parser<VideoFile> PARSER;

    public static com.google.protobuf.Parser<VideoFile> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}