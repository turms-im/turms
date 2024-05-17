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

package im.turms.plugin.livekit.core.proto.egress;

/**
 * Protobuf type {@code livekit.StreamOutput}
 */
public final class StreamOutput extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:livekit.StreamOutput)
        StreamOutputOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                StreamOutput.class.getName());
    }

    // Use StreamOutput.newBuilder() to construct.
    private StreamOutput(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private StreamOutput() {
        protocol_ = 0;
        urls_ = com.google.protobuf.LazyStringArrayList.emptyList();
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_StreamOutput_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_StreamOutput_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.plugin.livekit.core.proto.egress.StreamOutput.class,
                        im.turms.plugin.livekit.core.proto.egress.StreamOutput.Builder.class);
    }

    public static final int PROTOCOL_FIELD_NUMBER = 1;
    private int protocol_ = 0;

    /**
     * <pre>
     * required
     * </pre>
     *
     * <code>.livekit.StreamProtocol protocol = 1;</code>
     *
     * @return The enum numeric value on the wire for protocol.
     */
    @java.lang.Override
    public int getProtocolValue() {
        return protocol_;
    }

    /**
     * <pre>
     * required
     * </pre>
     *
     * <code>.livekit.StreamProtocol protocol = 1;</code>
     *
     * @return The protocol.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.StreamProtocol getProtocol() {
        im.turms.plugin.livekit.core.proto.egress.StreamProtocol result =
                im.turms.plugin.livekit.core.proto.egress.StreamProtocol.forNumber(protocol_);
        return result == null
                ? im.turms.plugin.livekit.core.proto.egress.StreamProtocol.UNRECOGNIZED
                : result;
    }

    public static final int URLS_FIELD_NUMBER = 2;
    @SuppressWarnings("serial")
    private com.google.protobuf.LazyStringArrayList urls_ =
            com.google.protobuf.LazyStringArrayList.emptyList();

    /**
     * <pre>
     * required
     * </pre>
     *
     * <code>repeated string urls = 2;</code>
     *
     * @return A list containing the urls.
     */
    public com.google.protobuf.ProtocolStringList getUrlsList() {
        return urls_;
    }

    /**
     * <pre>
     * required
     * </pre>
     *
     * <code>repeated string urls = 2;</code>
     *
     * @return The count of urls.
     */
    public int getUrlsCount() {
        return urls_.size();
    }

    /**
     * <pre>
     * required
     * </pre>
     *
     * <code>repeated string urls = 2;</code>
     *
     * @param index The index of the element to return.
     * @return The urls at the given index.
     */
    public java.lang.String getUrls(int index) {
        return urls_.get(index);
    }

    /**
     * <pre>
     * required
     * </pre>
     *
     * <code>repeated string urls = 2;</code>
     *
     * @param index The index of the value to return.
     * @return The bytes of the urls at the given index.
     */
    public com.google.protobuf.ByteString getUrlsBytes(int index) {
        return urls_.getByteString(index);
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
        if (protocol_ != im.turms.plugin.livekit.core.proto.egress.StreamProtocol.DEFAULT_PROTOCOL
                .getNumber()) {
            output.writeEnum(1, protocol_);
        }
        for (int i = 0; i < urls_.size(); i++) {
            com.google.protobuf.GeneratedMessage.writeString(output, 2, urls_.getRaw(i));
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
        if (protocol_ != im.turms.plugin.livekit.core.proto.egress.StreamProtocol.DEFAULT_PROTOCOL
                .getNumber()) {
            size += com.google.protobuf.CodedOutputStream.computeEnumSize(1, protocol_);
        }
        {
            int dataSize = 0;
            for (int i = 0; i < urls_.size(); i++) {
                dataSize += computeStringSizeNoTag(urls_.getRaw(i));
            }
            size += dataSize;
            size += getUrlsList().size();
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
        if (!(obj instanceof StreamOutput other)) {
            return super.equals(obj);
        }

        if (protocol_ != other.protocol_) {
            return false;
        }
        if (!getUrlsList().equals(other.getUrlsList())) {
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
        hash = (37 * hash) + PROTOCOL_FIELD_NUMBER;
        hash = (53 * hash) + protocol_;
        if (getUrlsCount() > 0) {
            hash = (37 * hash) + URLS_FIELD_NUMBER;
            hash = (53 * hash) + getUrlsList().hashCode();
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.plugin.livekit.core.proto.egress.StreamOutput parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.egress.StreamOutput parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.StreamOutput parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.egress.StreamOutput parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.StreamOutput parseFrom(byte[] data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.egress.StreamOutput parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.StreamOutput parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.egress.StreamOutput parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.StreamOutput parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.egress.StreamOutput parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.StreamOutput parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.egress.StreamOutput parseFrom(
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

    public static Builder newBuilder(
            im.turms.plugin.livekit.core.proto.egress.StreamOutput prototype) {
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
     * Protobuf type {@code livekit.StreamOutput}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:livekit.StreamOutput)
            im.turms.plugin.livekit.core.proto.egress.StreamOutputOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_StreamOutput_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_StreamOutput_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.plugin.livekit.core.proto.egress.StreamOutput.class,
                            im.turms.plugin.livekit.core.proto.egress.StreamOutput.Builder.class);
        }

        // Construct using im.turms.plugin.livekit.core.proto.egress.StreamOutput.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            protocol_ = 0;
            urls_ = com.google.protobuf.LazyStringArrayList.emptyList();
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_StreamOutput_descriptor;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.StreamOutput getDefaultInstanceForType() {
            return im.turms.plugin.livekit.core.proto.egress.StreamOutput.getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.StreamOutput build() {
            im.turms.plugin.livekit.core.proto.egress.StreamOutput result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.StreamOutput buildPartial() {
            im.turms.plugin.livekit.core.proto.egress.StreamOutput result =
                    new im.turms.plugin.livekit.core.proto.egress.StreamOutput(this);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartial0(im.turms.plugin.livekit.core.proto.egress.StreamOutput result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.protocol_ = protocol_;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                urls_.makeImmutable();
                result.urls_ = urls_;
            }
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.plugin.livekit.core.proto.egress.StreamOutput) {
                return mergeFrom((im.turms.plugin.livekit.core.proto.egress.StreamOutput) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(im.turms.plugin.livekit.core.proto.egress.StreamOutput other) {
            if (other == im.turms.plugin.livekit.core.proto.egress.StreamOutput
                    .getDefaultInstance()) {
                return this;
            }
            if (other.protocol_ != 0) {
                setProtocolValue(other.getProtocolValue());
            }
            if (!other.urls_.isEmpty()) {
                if (urls_.isEmpty()) {
                    urls_ = other.urls_;
                    bitField0_ |= 0x00000002;
                } else {
                    ensureUrlsIsMutable();
                    urls_.addAll(other.urls_);
                }
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
                        case 8 -> {
                            protocol_ = input.readEnum();
                            bitField0_ |= 0x00000001;
                        } // case 8
                        case 18 -> {
                            String s = input.readStringRequireUtf8();
                            ensureUrlsIsMutable();
                            urls_.add(s);
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

        private int protocol_ = 0;

        /**
         * <pre>
         * required
         * </pre>
         *
         * <code>.livekit.StreamProtocol protocol = 1;</code>
         *
         * @return The enum numeric value on the wire for protocol.
         */
        @java.lang.Override
        public int getProtocolValue() {
            return protocol_;
        }

        /**
         * <pre>
         * required
         * </pre>
         *
         * <code>.livekit.StreamProtocol protocol = 1;</code>
         *
         * @param value The enum numeric value on the wire for protocol to set.
         * @return This builder for chaining.
         */
        public Builder setProtocolValue(int value) {
            protocol_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * required
         * </pre>
         *
         * <code>.livekit.StreamProtocol protocol = 1;</code>
         *
         * @return The protocol.
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.StreamProtocol getProtocol() {
            im.turms.plugin.livekit.core.proto.egress.StreamProtocol result =
                    im.turms.plugin.livekit.core.proto.egress.StreamProtocol.forNumber(protocol_);
            return result == null
                    ? im.turms.plugin.livekit.core.proto.egress.StreamProtocol.UNRECOGNIZED
                    : result;
        }

        /**
         * <pre>
         * required
         * </pre>
         *
         * <code>.livekit.StreamProtocol protocol = 1;</code>
         *
         * @param value The protocol to set.
         * @return This builder for chaining.
         */
        public Builder setProtocol(im.turms.plugin.livekit.core.proto.egress.StreamProtocol value) {
            if (value == null) {
                throw new NullPointerException();
            }
            bitField0_ |= 0x00000001;
            protocol_ = value.getNumber();
            onChanged();
            return this;
        }

        /**
         * <pre>
         * required
         * </pre>
         *
         * <code>.livekit.StreamProtocol protocol = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearProtocol() {
            bitField0_ &= ~0x00000001;
            protocol_ = 0;
            onChanged();
            return this;
        }

        private com.google.protobuf.LazyStringArrayList urls_ =
                com.google.protobuf.LazyStringArrayList.emptyList();

        private void ensureUrlsIsMutable() {
            if (!urls_.isModifiable()) {
                urls_ = new com.google.protobuf.LazyStringArrayList(urls_);
            }
            bitField0_ |= 0x00000002;
        }

        /**
         * <pre>
         * required
         * </pre>
         *
         * <code>repeated string urls = 2;</code>
         *
         * @return A list containing the urls.
         */
        public com.google.protobuf.ProtocolStringList getUrlsList() {
            urls_.makeImmutable();
            return urls_;
        }

        /**
         * <pre>
         * required
         * </pre>
         *
         * <code>repeated string urls = 2;</code>
         *
         * @return The count of urls.
         */
        public int getUrlsCount() {
            return urls_.size();
        }

        /**
         * <pre>
         * required
         * </pre>
         *
         * <code>repeated string urls = 2;</code>
         *
         * @param index The index of the element to return.
         * @return The urls at the given index.
         */
        public java.lang.String getUrls(int index) {
            return urls_.get(index);
        }

        /**
         * <pre>
         * required
         * </pre>
         *
         * <code>repeated string urls = 2;</code>
         *
         * @param index The index of the value to return.
         * @return The bytes of the urls at the given index.
         */
        public com.google.protobuf.ByteString getUrlsBytes(int index) {
            return urls_.getByteString(index);
        }

        /**
         * <pre>
         * required
         * </pre>
         *
         * <code>repeated string urls = 2;</code>
         *
         * @param index The index to set the value at.
         * @param value The urls to set.
         * @return This builder for chaining.
         */
        public Builder setUrls(int index, java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ensureUrlsIsMutable();
            urls_.set(index, value);
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * required
         * </pre>
         *
         * <code>repeated string urls = 2;</code>
         *
         * @param value The urls to add.
         * @return This builder for chaining.
         */
        public Builder addUrls(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ensureUrlsIsMutable();
            urls_.add(value);
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * required
         * </pre>
         *
         * <code>repeated string urls = 2;</code>
         *
         * @param values The urls to add.
         * @return This builder for chaining.
         */
        public Builder addAllUrls(java.lang.Iterable<java.lang.String> values) {
            ensureUrlsIsMutable();
            com.google.protobuf.AbstractMessageLite.Builder.addAll(values, urls_);
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * required
         * </pre>
         *
         * <code>repeated string urls = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearUrls() {
            urls_ = com.google.protobuf.LazyStringArrayList.emptyList();
            bitField0_ &= ~0x00000002;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * required
         * </pre>
         *
         * <code>repeated string urls = 2;</code>
         *
         * @param value The bytes of the urls to add.
         * @return This builder for chaining.
         */
        public Builder addUrlsBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            ensureUrlsIsMutable();
            urls_.add(value);
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        // @@protoc_insertion_point(builder_scope:livekit.StreamOutput)
    }

    // @@protoc_insertion_point(class_scope:livekit.StreamOutput)
    private static final im.turms.plugin.livekit.core.proto.egress.StreamOutput DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE = new im.turms.plugin.livekit.core.proto.egress.StreamOutput();
    }

    public static im.turms.plugin.livekit.core.proto.egress.StreamOutput getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<StreamOutput> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public StreamOutput parsePartialFrom(
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

    public static com.google.protobuf.Parser<StreamOutput> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<StreamOutput> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.StreamOutput getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}