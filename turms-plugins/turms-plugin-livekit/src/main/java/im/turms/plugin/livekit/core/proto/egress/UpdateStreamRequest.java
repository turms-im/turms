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
 * Protobuf type {@code livekit.UpdateStreamRequest}
 */
public final class UpdateStreamRequest extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:livekit.UpdateStreamRequest)
        UpdateStreamRequestOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                UpdateStreamRequest.class.getName());
    }

    // Use UpdateStreamRequest.newBuilder() to construct.
    private UpdateStreamRequest(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private UpdateStreamRequest() {
        egressId_ = "";
        addOutputUrls_ = com.google.protobuf.LazyStringArrayList.emptyList();
        removeOutputUrls_ = com.google.protobuf.LazyStringArrayList.emptyList();
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_UpdateStreamRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_UpdateStreamRequest_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.plugin.livekit.core.proto.egress.UpdateStreamRequest.class,
                        im.turms.plugin.livekit.core.proto.egress.UpdateStreamRequest.Builder.class);
    }

    public static final int EGRESS_ID_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
    private volatile java.lang.Object egressId_ = "";

    /**
     * <code>string egress_id = 1;</code>
     *
     * @return The egressId.
     */
    @java.lang.Override
    public java.lang.String getEgressId() {
        java.lang.Object ref = egressId_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            egressId_ = s;
            return s;
        }
    }

    /**
     * <code>string egress_id = 1;</code>
     *
     * @return The bytes for egressId.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getEgressIdBytes() {
        java.lang.Object ref = egressId_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            egressId_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int ADD_OUTPUT_URLS_FIELD_NUMBER = 2;
    @SuppressWarnings("serial")
    private com.google.protobuf.LazyStringArrayList addOutputUrls_ =
            com.google.protobuf.LazyStringArrayList.emptyList();

    /**
     * <code>repeated string add_output_urls = 2;</code>
     *
     * @return A list containing the addOutputUrls.
     */
    public com.google.protobuf.ProtocolStringList getAddOutputUrlsList() {
        return addOutputUrls_;
    }

    /**
     * <code>repeated string add_output_urls = 2;</code>
     *
     * @return The count of addOutputUrls.
     */
    public int getAddOutputUrlsCount() {
        return addOutputUrls_.size();
    }

    /**
     * <code>repeated string add_output_urls = 2;</code>
     *
     * @param index The index of the element to return.
     * @return The addOutputUrls at the given index.
     */
    public java.lang.String getAddOutputUrls(int index) {
        return addOutputUrls_.get(index);
    }

    /**
     * <code>repeated string add_output_urls = 2;</code>
     *
     * @param index The index of the value to return.
     * @return The bytes of the addOutputUrls at the given index.
     */
    public com.google.protobuf.ByteString getAddOutputUrlsBytes(int index) {
        return addOutputUrls_.getByteString(index);
    }

    public static final int REMOVE_OUTPUT_URLS_FIELD_NUMBER = 3;
    @SuppressWarnings("serial")
    private com.google.protobuf.LazyStringArrayList removeOutputUrls_ =
            com.google.protobuf.LazyStringArrayList.emptyList();

    /**
     * <code>repeated string remove_output_urls = 3;</code>
     *
     * @return A list containing the removeOutputUrls.
     */
    public com.google.protobuf.ProtocolStringList getRemoveOutputUrlsList() {
        return removeOutputUrls_;
    }

    /**
     * <code>repeated string remove_output_urls = 3;</code>
     *
     * @return The count of removeOutputUrls.
     */
    public int getRemoveOutputUrlsCount() {
        return removeOutputUrls_.size();
    }

    /**
     * <code>repeated string remove_output_urls = 3;</code>
     *
     * @param index The index of the element to return.
     * @return The removeOutputUrls at the given index.
     */
    public java.lang.String getRemoveOutputUrls(int index) {
        return removeOutputUrls_.get(index);
    }

    /**
     * <code>repeated string remove_output_urls = 3;</code>
     *
     * @param index The index of the value to return.
     * @return The bytes of the removeOutputUrls at the given index.
     */
    public com.google.protobuf.ByteString getRemoveOutputUrlsBytes(int index) {
        return removeOutputUrls_.getByteString(index);
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
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(egressId_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 1, egressId_);
        }
        for (int i = 0; i < addOutputUrls_.size(); i++) {
            com.google.protobuf.GeneratedMessage.writeString(output, 2, addOutputUrls_.getRaw(i));
        }
        for (int i = 0; i < removeOutputUrls_.size(); i++) {
            com.google.protobuf.GeneratedMessage
                    .writeString(output, 3, removeOutputUrls_.getRaw(i));
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
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(egressId_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(1, egressId_);
        }
        {
            int dataSize = 0;
            for (int i = 0; i < addOutputUrls_.size(); i++) {
                dataSize += computeStringSizeNoTag(addOutputUrls_.getRaw(i));
            }
            size += dataSize;
            size += getAddOutputUrlsList().size();
        }
        {
            int dataSize = 0;
            for (int i = 0; i < removeOutputUrls_.size(); i++) {
                dataSize += computeStringSizeNoTag(removeOutputUrls_.getRaw(i));
            }
            size += dataSize;
            size += getRemoveOutputUrlsList().size();
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
        if (!(obj instanceof UpdateStreamRequest other)) {
            return super.equals(obj);
        }

        if (!getEgressId().equals(other.getEgressId())) {
            return false;
        }
        if (!getAddOutputUrlsList().equals(other.getAddOutputUrlsList())) {
            return false;
        }
        if (!getRemoveOutputUrlsList().equals(other.getRemoveOutputUrlsList())) {
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
        hash = (37 * hash) + EGRESS_ID_FIELD_NUMBER;
        hash = (53 * hash) + getEgressId().hashCode();
        if (getAddOutputUrlsCount() > 0) {
            hash = (37 * hash) + ADD_OUTPUT_URLS_FIELD_NUMBER;
            hash = (53 * hash) + getAddOutputUrlsList().hashCode();
        }
        if (getRemoveOutputUrlsCount() > 0) {
            hash = (37 * hash) + REMOVE_OUTPUT_URLS_FIELD_NUMBER;
            hash = (53 * hash) + getRemoveOutputUrlsList().hashCode();
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.plugin.livekit.core.proto.egress.UpdateStreamRequest parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.egress.UpdateStreamRequest parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.UpdateStreamRequest parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.egress.UpdateStreamRequest parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.UpdateStreamRequest parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.egress.UpdateStreamRequest parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.UpdateStreamRequest parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.egress.UpdateStreamRequest parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.UpdateStreamRequest parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.egress.UpdateStreamRequest parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.egress.UpdateStreamRequest parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.egress.UpdateStreamRequest parseFrom(
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
            im.turms.plugin.livekit.core.proto.egress.UpdateStreamRequest prototype) {
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
     * Protobuf type {@code livekit.UpdateStreamRequest}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:livekit.UpdateStreamRequest)
            im.turms.plugin.livekit.core.proto.egress.UpdateStreamRequestOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_UpdateStreamRequest_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_UpdateStreamRequest_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.plugin.livekit.core.proto.egress.UpdateStreamRequest.class,
                            im.turms.plugin.livekit.core.proto.egress.UpdateStreamRequest.Builder.class);
        }

        // Construct using
        // im.turms.plugin.livekit.core.proto.egress.UpdateStreamRequest.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            egressId_ = "";
            addOutputUrls_ = com.google.protobuf.LazyStringArrayList.emptyList();
            removeOutputUrls_ = com.google.protobuf.LazyStringArrayList.emptyList();
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.internal_static_livekit_UpdateStreamRequest_descriptor;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.UpdateStreamRequest getDefaultInstanceForType() {
            return im.turms.plugin.livekit.core.proto.egress.UpdateStreamRequest
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.UpdateStreamRequest build() {
            im.turms.plugin.livekit.core.proto.egress.UpdateStreamRequest result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.egress.UpdateStreamRequest buildPartial() {
            im.turms.plugin.livekit.core.proto.egress.UpdateStreamRequest result =
                    new im.turms.plugin.livekit.core.proto.egress.UpdateStreamRequest(this);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartial0(
                im.turms.plugin.livekit.core.proto.egress.UpdateStreamRequest result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.egressId_ = egressId_;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                addOutputUrls_.makeImmutable();
                result.addOutputUrls_ = addOutputUrls_;
            }
            if (((from_bitField0_ & 0x00000004) != 0)) {
                removeOutputUrls_.makeImmutable();
                result.removeOutputUrls_ = removeOutputUrls_;
            }
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.plugin.livekit.core.proto.egress.UpdateStreamRequest) {
                return mergeFrom(
                        (im.turms.plugin.livekit.core.proto.egress.UpdateStreamRequest) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.plugin.livekit.core.proto.egress.UpdateStreamRequest other) {
            if (other == im.turms.plugin.livekit.core.proto.egress.UpdateStreamRequest
                    .getDefaultInstance()) {
                return this;
            }
            if (!other.getEgressId()
                    .isEmpty()) {
                egressId_ = other.egressId_;
                bitField0_ |= 0x00000001;
                onChanged();
            }
            if (!other.addOutputUrls_.isEmpty()) {
                if (addOutputUrls_.isEmpty()) {
                    addOutputUrls_ = other.addOutputUrls_;
                    bitField0_ |= 0x00000002;
                } else {
                    ensureAddOutputUrlsIsMutable();
                    addOutputUrls_.addAll(other.addOutputUrls_);
                }
                onChanged();
            }
            if (!other.removeOutputUrls_.isEmpty()) {
                if (removeOutputUrls_.isEmpty()) {
                    removeOutputUrls_ = other.removeOutputUrls_;
                    bitField0_ |= 0x00000004;
                } else {
                    ensureRemoveOutputUrlsIsMutable();
                    removeOutputUrls_.addAll(other.removeOutputUrls_);
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
                        case 10 -> {
                            egressId_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000001;
                        } // case 10
                        case 18 -> {
                            String s = input.readStringRequireUtf8();
                            ensureAddOutputUrlsIsMutable();
                            addOutputUrls_.add(s);
                        } // case 18
                        case 26 -> {
                            String s = input.readStringRequireUtf8();
                            ensureRemoveOutputUrlsIsMutable();
                            removeOutputUrls_.add(s);
                        } // case 26
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

        private java.lang.Object egressId_ = "";

        /**
         * <code>string egress_id = 1;</code>
         *
         * @return The egressId.
         */
        public java.lang.String getEgressId() {
            java.lang.Object ref = egressId_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                egressId_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>string egress_id = 1;</code>
         *
         * @return The bytes for egressId.
         */
        public com.google.protobuf.ByteString getEgressIdBytes() {
            java.lang.Object ref = egressId_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                egressId_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>string egress_id = 1;</code>
         *
         * @param value The egressId to set.
         * @return This builder for chaining.
         */
        public Builder setEgressId(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            egressId_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>string egress_id = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearEgressId() {
            egressId_ = getDefaultInstance().getEgressId();
            bitField0_ &= ~0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>string egress_id = 1;</code>
         *
         * @param value The bytes for egressId to set.
         * @return This builder for chaining.
         */
        public Builder setEgressIdBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            egressId_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        private com.google.protobuf.LazyStringArrayList addOutputUrls_ =
                com.google.protobuf.LazyStringArrayList.emptyList();

        private void ensureAddOutputUrlsIsMutable() {
            if (!addOutputUrls_.isModifiable()) {
                addOutputUrls_ = new com.google.protobuf.LazyStringArrayList(addOutputUrls_);
            }
            bitField0_ |= 0x00000002;
        }

        /**
         * <code>repeated string add_output_urls = 2;</code>
         *
         * @return A list containing the addOutputUrls.
         */
        public com.google.protobuf.ProtocolStringList getAddOutputUrlsList() {
            addOutputUrls_.makeImmutable();
            return addOutputUrls_;
        }

        /**
         * <code>repeated string add_output_urls = 2;</code>
         *
         * @return The count of addOutputUrls.
         */
        public int getAddOutputUrlsCount() {
            return addOutputUrls_.size();
        }

        /**
         * <code>repeated string add_output_urls = 2;</code>
         *
         * @param index The index of the element to return.
         * @return The addOutputUrls at the given index.
         */
        public java.lang.String getAddOutputUrls(int index) {
            return addOutputUrls_.get(index);
        }

        /**
         * <code>repeated string add_output_urls = 2;</code>
         *
         * @param index The index of the value to return.
         * @return The bytes of the addOutputUrls at the given index.
         */
        public com.google.protobuf.ByteString getAddOutputUrlsBytes(int index) {
            return addOutputUrls_.getByteString(index);
        }

        /**
         * <code>repeated string add_output_urls = 2;</code>
         *
         * @param index The index to set the value at.
         * @param value The addOutputUrls to set.
         * @return This builder for chaining.
         */
        public Builder setAddOutputUrls(int index, java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ensureAddOutputUrlsIsMutable();
            addOutputUrls_.set(index, value);
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>repeated string add_output_urls = 2;</code>
         *
         * @param value The addOutputUrls to add.
         * @return This builder for chaining.
         */
        public Builder addAddOutputUrls(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ensureAddOutputUrlsIsMutable();
            addOutputUrls_.add(value);
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>repeated string add_output_urls = 2;</code>
         *
         * @param values The addOutputUrls to add.
         * @return This builder for chaining.
         */
        public Builder addAllAddOutputUrls(java.lang.Iterable<java.lang.String> values) {
            ensureAddOutputUrlsIsMutable();
            com.google.protobuf.AbstractMessageLite.Builder.addAll(values, addOutputUrls_);
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>repeated string add_output_urls = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearAddOutputUrls() {
            addOutputUrls_ = com.google.protobuf.LazyStringArrayList.emptyList();
            bitField0_ &= ~0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>repeated string add_output_urls = 2;</code>
         *
         * @param value The bytes of the addOutputUrls to add.
         * @return This builder for chaining.
         */
        public Builder addAddOutputUrlsBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            ensureAddOutputUrlsIsMutable();
            addOutputUrls_.add(value);
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        private com.google.protobuf.LazyStringArrayList removeOutputUrls_ =
                com.google.protobuf.LazyStringArrayList.emptyList();

        private void ensureRemoveOutputUrlsIsMutable() {
            if (!removeOutputUrls_.isModifiable()) {
                removeOutputUrls_ = new com.google.protobuf.LazyStringArrayList(removeOutputUrls_);
            }
            bitField0_ |= 0x00000004;
        }

        /**
         * <code>repeated string remove_output_urls = 3;</code>
         *
         * @return A list containing the removeOutputUrls.
         */
        public com.google.protobuf.ProtocolStringList getRemoveOutputUrlsList() {
            removeOutputUrls_.makeImmutable();
            return removeOutputUrls_;
        }

        /**
         * <code>repeated string remove_output_urls = 3;</code>
         *
         * @return The count of removeOutputUrls.
         */
        public int getRemoveOutputUrlsCount() {
            return removeOutputUrls_.size();
        }

        /**
         * <code>repeated string remove_output_urls = 3;</code>
         *
         * @param index The index of the element to return.
         * @return The removeOutputUrls at the given index.
         */
        public java.lang.String getRemoveOutputUrls(int index) {
            return removeOutputUrls_.get(index);
        }

        /**
         * <code>repeated string remove_output_urls = 3;</code>
         *
         * @param index The index of the value to return.
         * @return The bytes of the removeOutputUrls at the given index.
         */
        public com.google.protobuf.ByteString getRemoveOutputUrlsBytes(int index) {
            return removeOutputUrls_.getByteString(index);
        }

        /**
         * <code>repeated string remove_output_urls = 3;</code>
         *
         * @param index The index to set the value at.
         * @param value The removeOutputUrls to set.
         * @return This builder for chaining.
         */
        public Builder setRemoveOutputUrls(int index, java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ensureRemoveOutputUrlsIsMutable();
            removeOutputUrls_.set(index, value);
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>repeated string remove_output_urls = 3;</code>
         *
         * @param value The removeOutputUrls to add.
         * @return This builder for chaining.
         */
        public Builder addRemoveOutputUrls(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ensureRemoveOutputUrlsIsMutable();
            removeOutputUrls_.add(value);
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>repeated string remove_output_urls = 3;</code>
         *
         * @param values The removeOutputUrls to add.
         * @return This builder for chaining.
         */
        public Builder addAllRemoveOutputUrls(java.lang.Iterable<java.lang.String> values) {
            ensureRemoveOutputUrlsIsMutable();
            com.google.protobuf.AbstractMessageLite.Builder.addAll(values, removeOutputUrls_);
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>repeated string remove_output_urls = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearRemoveOutputUrls() {
            removeOutputUrls_ = com.google.protobuf.LazyStringArrayList.emptyList();
            bitField0_ &= ~0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>repeated string remove_output_urls = 3;</code>
         *
         * @param value The bytes of the removeOutputUrls to add.
         * @return This builder for chaining.
         */
        public Builder addRemoveOutputUrlsBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            ensureRemoveOutputUrlsIsMutable();
            removeOutputUrls_.add(value);
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        // @@protoc_insertion_point(builder_scope:livekit.UpdateStreamRequest)
    }

    // @@protoc_insertion_point(class_scope:livekit.UpdateStreamRequest)
    private static final im.turms.plugin.livekit.core.proto.egress.UpdateStreamRequest DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE = new im.turms.plugin.livekit.core.proto.egress.UpdateStreamRequest();
    }

    public static im.turms.plugin.livekit.core.proto.egress.UpdateStreamRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<UpdateStreamRequest> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public UpdateStreamRequest parsePartialFrom(
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

    public static com.google.protobuf.Parser<UpdateStreamRequest> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<UpdateStreamRequest> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.egress.UpdateStreamRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}