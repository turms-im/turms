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
 * Protobuf type {@code livekit.SipDTMF}
 */
public final class SipDTMF extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:livekit.SipDTMF)
        SipDTMFOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                SipDTMF.class.getName());
    }

    // Use SipDTMF.newBuilder() to construct.
    private SipDTMF(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private SipDTMF() {
        digit_ = "";
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_SipDTMF_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_SipDTMF_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.plugin.livekit.core.proto.models.SipDTMF.class,
                        im.turms.plugin.livekit.core.proto.models.SipDTMF.Builder.class);
    }

    public static final int CODE_FIELD_NUMBER = 3;
    private int code_ = 0;

    /**
     * <code>uint32 code = 3;</code>
     *
     * @return The code.
     */
    @java.lang.Override
    public int getCode() {
        return code_;
    }

    public static final int DIGIT_FIELD_NUMBER = 4;
    @SuppressWarnings("serial")
    private volatile java.lang.Object digit_ = "";

    /**
     * <code>string digit = 4;</code>
     *
     * @return The digit.
     */
    @java.lang.Override
    public java.lang.String getDigit() {
        java.lang.Object ref = digit_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            digit_ = s;
            return s;
        }
    }

    /**
     * <code>string digit = 4;</code>
     *
     * @return The bytes for digit.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getDigitBytes() {
        java.lang.Object ref = digit_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            digit_ = b;
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
        if (code_ != 0) {
            output.writeUInt32(3, code_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(digit_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 4, digit_);
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
        if (code_ != 0) {
            size += com.google.protobuf.CodedOutputStream.computeUInt32Size(3, code_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(digit_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(4, digit_);
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
        if (!(obj instanceof SipDTMF other)) {
            return super.equals(obj);
        }

        if (getCode() != other.getCode()) {
            return false;
        }
        if (!getDigit().equals(other.getDigit())) {
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
        hash = (37 * hash) + CODE_FIELD_NUMBER;
        hash = (53 * hash) + getCode();
        hash = (37 * hash) + DIGIT_FIELD_NUMBER;
        hash = (53 * hash) + getDigit().hashCode();
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.plugin.livekit.core.proto.models.SipDTMF parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.models.SipDTMF parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.SipDTMF parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.models.SipDTMF parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.SipDTMF parseFrom(byte[] data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.models.SipDTMF parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.SipDTMF parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.models.SipDTMF parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.SipDTMF parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.models.SipDTMF parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.SipDTMF parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.models.SipDTMF parseFrom(
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

    public static Builder newBuilder(im.turms.plugin.livekit.core.proto.models.SipDTMF prototype) {
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
     * Protobuf type {@code livekit.SipDTMF}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:livekit.SipDTMF)
            im.turms.plugin.livekit.core.proto.models.SipDTMFOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_SipDTMF_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_SipDTMF_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.plugin.livekit.core.proto.models.SipDTMF.class,
                            im.turms.plugin.livekit.core.proto.models.SipDTMF.Builder.class);
        }

        // Construct using im.turms.plugin.livekit.core.proto.models.SipDTMF.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            code_ = 0;
            digit_ = "";
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_SipDTMF_descriptor;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.SipDTMF getDefaultInstanceForType() {
            return im.turms.plugin.livekit.core.proto.models.SipDTMF.getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.SipDTMF build() {
            im.turms.plugin.livekit.core.proto.models.SipDTMF result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.SipDTMF buildPartial() {
            im.turms.plugin.livekit.core.proto.models.SipDTMF result =
                    new im.turms.plugin.livekit.core.proto.models.SipDTMF(this);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartial0(im.turms.plugin.livekit.core.proto.models.SipDTMF result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.code_ = code_;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.digit_ = digit_;
            }
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.plugin.livekit.core.proto.models.SipDTMF) {
                return mergeFrom((im.turms.plugin.livekit.core.proto.models.SipDTMF) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(im.turms.plugin.livekit.core.proto.models.SipDTMF other) {
            if (other == im.turms.plugin.livekit.core.proto.models.SipDTMF.getDefaultInstance()) {
                return this;
            }
            if (other.getCode() != 0) {
                setCode(other.getCode());
            }
            if (!other.getDigit()
                    .isEmpty()) {
                digit_ = other.digit_;
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
                        case 24 -> {
                            code_ = input.readUInt32();
                            bitField0_ |= 0x00000001;
                        } // case 24
                        case 34 -> {
                            digit_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000002;
                        } // case 34
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

        private int code_;

        /**
         * <code>uint32 code = 3;</code>
         *
         * @return The code.
         */
        @java.lang.Override
        public int getCode() {
            return code_;
        }

        /**
         * <code>uint32 code = 3;</code>
         *
         * @param value The code to set.
         * @return This builder for chaining.
         */
        public Builder setCode(int value) {

            code_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>uint32 code = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearCode() {
            bitField0_ &= ~0x00000001;
            code_ = 0;
            onChanged();
            return this;
        }

        private java.lang.Object digit_ = "";

        /**
         * <code>string digit = 4;</code>
         *
         * @return The digit.
         */
        public java.lang.String getDigit() {
            java.lang.Object ref = digit_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                digit_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>string digit = 4;</code>
         *
         * @return The bytes for digit.
         */
        public com.google.protobuf.ByteString getDigitBytes() {
            java.lang.Object ref = digit_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                digit_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>string digit = 4;</code>
         *
         * @param value The digit to set.
         * @return This builder for chaining.
         */
        public Builder setDigit(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            digit_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>string digit = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearDigit() {
            digit_ = getDefaultInstance().getDigit();
            bitField0_ &= ~0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>string digit = 4;</code>
         *
         * @param value The bytes for digit to set.
         * @return This builder for chaining.
         */
        public Builder setDigitBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            digit_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        // @@protoc_insertion_point(builder_scope:livekit.SipDTMF)
    }

    // @@protoc_insertion_point(class_scope:livekit.SipDTMF)
    private static final im.turms.plugin.livekit.core.proto.models.SipDTMF DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE = new im.turms.plugin.livekit.core.proto.models.SipDTMF();
    }

    public static im.turms.plugin.livekit.core.proto.models.SipDTMF getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<SipDTMF> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public SipDTMF parsePartialFrom(
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

    public static com.google.protobuf.Parser<SipDTMF> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<SipDTMF> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.SipDTMF getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}