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

package im.turms.plugin.livekit.core.proto.models;

/**
 * Protobuf type {@code livekit.Codec}
 */
public final class Codec extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:livekit.Codec)
        CodecOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                Codec.class.getName());
    }

    // Use Codec.newBuilder() to construct.
    private Codec(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private Codec() {
        mime_ = "";
        fmtpLine_ = "";
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_Codec_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_Codec_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.plugin.livekit.core.proto.models.Codec.class,
                        im.turms.plugin.livekit.core.proto.models.Codec.Builder.class);
    }

    public static final int MIME_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
    private volatile java.lang.Object mime_ = "";

    /**
     * <code>string mime = 1;</code>
     *
     * @return The mime.
     */
    @java.lang.Override
    public java.lang.String getMime() {
        java.lang.Object ref = mime_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            mime_ = s;
            return s;
        }
    }

    /**
     * <code>string mime = 1;</code>
     *
     * @return The bytes for mime.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getMimeBytes() {
        java.lang.Object ref = mime_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            mime_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int FMTP_LINE_FIELD_NUMBER = 2;
    @SuppressWarnings("serial")
    private volatile java.lang.Object fmtpLine_ = "";

    /**
     * <code>string fmtp_line = 2;</code>
     *
     * @return The fmtpLine.
     */
    @java.lang.Override
    public java.lang.String getFmtpLine() {
        java.lang.Object ref = fmtpLine_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            fmtpLine_ = s;
            return s;
        }
    }

    /**
     * <code>string fmtp_line = 2;</code>
     *
     * @return The bytes for fmtpLine.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getFmtpLineBytes() {
        java.lang.Object ref = fmtpLine_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            fmtpLine_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
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
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(mime_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 1, mime_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(fmtpLine_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 2, fmtpLine_);
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
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(mime_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(1, mime_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(fmtpLine_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(2, fmtpLine_);
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
        if (!(obj instanceof Codec other)) {
            return super.equals(obj);
        }

        if (!getMime().equals(other.getMime())) {
            return false;
        }
        if (!getFmtpLine().equals(other.getFmtpLine())) {
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
        hash = (37 * hash) + MIME_FIELD_NUMBER;
        hash = (53 * hash) + getMime().hashCode();
        hash = (37 * hash) + FMTP_LINE_FIELD_NUMBER;
        hash = (53 * hash) + getFmtpLine().hashCode();
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.plugin.livekit.core.proto.models.Codec parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.models.Codec parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.Codec parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.models.Codec parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.Codec parseFrom(byte[] data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.models.Codec parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.Codec parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.models.Codec parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.Codec parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.models.Codec parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.Codec parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.models.Codec parseFrom(
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

    public static Builder newBuilder(im.turms.plugin.livekit.core.proto.models.Codec prototype) {
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
     * Protobuf type {@code livekit.Codec}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:livekit.Codec)
            im.turms.plugin.livekit.core.proto.models.CodecOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_Codec_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_Codec_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.plugin.livekit.core.proto.models.Codec.class,
                            im.turms.plugin.livekit.core.proto.models.Codec.Builder.class);
        }

        // Construct using im.turms.plugin.livekit.core.proto.models.Codec.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            mime_ = "";
            fmtpLine_ = "";
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_Codec_descriptor;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.Codec getDefaultInstanceForType() {
            return im.turms.plugin.livekit.core.proto.models.Codec.getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.Codec build() {
            im.turms.plugin.livekit.core.proto.models.Codec result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.Codec buildPartial() {
            im.turms.plugin.livekit.core.proto.models.Codec result =
                    new im.turms.plugin.livekit.core.proto.models.Codec(this);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartial0(im.turms.plugin.livekit.core.proto.models.Codec result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.mime_ = mime_;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.fmtpLine_ = fmtpLine_;
            }
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.plugin.livekit.core.proto.models.Codec) {
                return mergeFrom((im.turms.plugin.livekit.core.proto.models.Codec) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(im.turms.plugin.livekit.core.proto.models.Codec other) {
            if (other == im.turms.plugin.livekit.core.proto.models.Codec.getDefaultInstance()) {
                return this;
            }
            if (!other.getMime()
                    .isEmpty()) {
                mime_ = other.mime_;
                bitField0_ |= 0x00000001;
                onChanged();
            }
            if (!other.getFmtpLine()
                    .isEmpty()) {
                fmtpLine_ = other.fmtpLine_;
                bitField0_ |= 0x00000002;
                onChanged();
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
                            mime_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000001;
                        } // case 10
                        case 18 -> {
                            fmtpLine_ = input.readStringRequireUtf8();
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

        private java.lang.Object mime_ = "";

        /**
         * <code>string mime = 1;</code>
         *
         * @return The mime.
         */
        public java.lang.String getMime() {
            java.lang.Object ref = mime_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                mime_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>string mime = 1;</code>
         *
         * @return The bytes for mime.
         */
        public com.google.protobuf.ByteString getMimeBytes() {
            java.lang.Object ref = mime_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                mime_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>string mime = 1;</code>
         *
         * @param value The mime to set.
         * @return This builder for chaining.
         */
        public Builder setMime(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            mime_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>string mime = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearMime() {
            mime_ = getDefaultInstance().getMime();
            bitField0_ &= ~0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>string mime = 1;</code>
         *
         * @param value The bytes for mime to set.
         * @return This builder for chaining.
         */
        public Builder setMimeBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            mime_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        private java.lang.Object fmtpLine_ = "";

        /**
         * <code>string fmtp_line = 2;</code>
         *
         * @return The fmtpLine.
         */
        public java.lang.String getFmtpLine() {
            java.lang.Object ref = fmtpLine_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                fmtpLine_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>string fmtp_line = 2;</code>
         *
         * @return The bytes for fmtpLine.
         */
        public com.google.protobuf.ByteString getFmtpLineBytes() {
            java.lang.Object ref = fmtpLine_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                fmtpLine_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>string fmtp_line = 2;</code>
         *
         * @param value The fmtpLine to set.
         * @return This builder for chaining.
         */
        public Builder setFmtpLine(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            fmtpLine_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>string fmtp_line = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearFmtpLine() {
            fmtpLine_ = getDefaultInstance().getFmtpLine();
            bitField0_ &= ~0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>string fmtp_line = 2;</code>
         *
         * @param value The bytes for fmtpLine to set.
         * @return This builder for chaining.
         */
        public Builder setFmtpLineBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            fmtpLine_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        // @@protoc_insertion_point(builder_scope:livekit.Codec)
    }

    // @@protoc_insertion_point(class_scope:livekit.Codec)
    private static final im.turms.plugin.livekit.core.proto.models.Codec DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE = new im.turms.plugin.livekit.core.proto.models.Codec();
    }

    public static im.turms.plugin.livekit.core.proto.models.Codec getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<Codec> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public Codec parsePartialFrom(
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

    public static com.google.protobuf.Parser<Codec> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<Codec> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.Codec getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}