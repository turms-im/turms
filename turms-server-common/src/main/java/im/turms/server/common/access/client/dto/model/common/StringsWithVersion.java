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

package im.turms.server.common.access.client.dto.model.common;

/**
 * Protobuf type {@code im.turms.proto.StringsWithVersion}
 */
public final class StringsWithVersion extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.StringsWithVersion)
        StringsWithVersionOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 27,
                /* patch= */ 2,
                /* suffix= */ "",
                StringsWithVersion.class.getName());
    }

    // Use StringsWithVersion.newBuilder() to construct.
    private StringsWithVersion(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private StringsWithVersion() {
        strings_ = com.google.protobuf.LazyStringArrayList.emptyList();
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.server.common.access.client.dto.model.common.StringsWithVersionOuterClass.internal_static_im_turms_proto_StringsWithVersion_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.server.common.access.client.dto.model.common.StringsWithVersionOuterClass.internal_static_im_turms_proto_StringsWithVersion_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.server.common.access.client.dto.model.common.StringsWithVersion.class,
                        im.turms.server.common.access.client.dto.model.common.StringsWithVersion.Builder.class);
    }

    private int bitField0_;
    public static final int STRINGS_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
    private com.google.protobuf.LazyStringArrayList strings_ =
            com.google.protobuf.LazyStringArrayList.emptyList();

    /**
     * <code>repeated string strings = 1;</code>
     *
     * @return A list containing the strings.
     */
    public com.google.protobuf.ProtocolStringList getStringsList() {
        return strings_;
    }

    /**
     * <code>repeated string strings = 1;</code>
     *
     * @return The count of strings.
     */
    public int getStringsCount() {
        return strings_.size();
    }

    /**
     * <code>repeated string strings = 1;</code>
     *
     * @param index The index of the element to return.
     * @return The strings at the given index.
     */
    public java.lang.String getStrings(int index) {
        return strings_.get(index);
    }

    /**
     * <code>repeated string strings = 1;</code>
     *
     * @param index The index of the value to return.
     * @return The bytes of the strings at the given index.
     */
    public com.google.protobuf.ByteString getStringsBytes(int index) {
        return strings_.getByteString(index);
    }

    public static final int LAST_UPDATED_DATE_FIELD_NUMBER = 2;
    private long lastUpdatedDate_;

    /**
     * <code>optional int64 last_updated_date = 2;</code>
     *
     * @return Whether the lastUpdatedDate field is set.
     */
    @java.lang.Override
    public boolean hasLastUpdatedDate() {
        return ((bitField0_ & 0x00000001) != 0);
    }

    /**
     * <code>optional int64 last_updated_date = 2;</code>
     *
     * @return The lastUpdatedDate.
     */
    @java.lang.Override
    public long getLastUpdatedDate() {
        return lastUpdatedDate_;
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
        for (int i = 0; i < strings_.size(); i++) {
            com.google.protobuf.GeneratedMessage.writeString(output, 1, strings_.getRaw(i));
        }
        if (((bitField0_ & 0x00000001) != 0)) {
            output.writeInt64(2, lastUpdatedDate_);
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
        {
            int dataSize = 0;
            for (int i = 0; i < strings_.size(); i++) {
                dataSize += computeStringSizeNoTag(strings_.getRaw(i));
            }
            size += dataSize;
            size += getStringsList().size();
        }
        if (((bitField0_ & 0x00000001) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(2, lastUpdatedDate_);
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
        if (!(obj instanceof StringsWithVersion other)) {
            return super.equals(obj);
        }

        if (!getStringsList().equals(other.getStringsList())) {
            return false;
        }
        if (hasLastUpdatedDate() != other.hasLastUpdatedDate()) {
            return false;
        }
        if (hasLastUpdatedDate()) {
            if (getLastUpdatedDate() != other.getLastUpdatedDate()) {
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
        if (getStringsCount() > 0) {
            hash = (37 * hash) + STRINGS_FIELD_NUMBER;
            hash = (53 * hash) + getStringsList().hashCode();
        }
        if (hasLastUpdatedDate()) {
            hash = (37 * hash) + LAST_UPDATED_DATE_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getLastUpdatedDate());
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.server.common.access.client.dto.model.common.StringsWithVersion parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.common.StringsWithVersion parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.common.StringsWithVersion parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.common.StringsWithVersion parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.common.StringsWithVersion parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.common.StringsWithVersion parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.common.StringsWithVersion parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.common.StringsWithVersion parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.common.StringsWithVersion parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.common.StringsWithVersion parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.common.StringsWithVersion parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.common.StringsWithVersion parseFrom(
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
            im.turms.server.common.access.client.dto.model.common.StringsWithVersion prototype) {
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
     * Protobuf type {@code im.turms.proto.StringsWithVersion}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.StringsWithVersion)
            im.turms.server.common.access.client.dto.model.common.StringsWithVersionOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.server.common.access.client.dto.model.common.StringsWithVersionOuterClass.internal_static_im_turms_proto_StringsWithVersion_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.server.common.access.client.dto.model.common.StringsWithVersionOuterClass.internal_static_im_turms_proto_StringsWithVersion_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.server.common.access.client.dto.model.common.StringsWithVersion.class,
                            im.turms.server.common.access.client.dto.model.common.StringsWithVersion.Builder.class);
        }

        // Construct using
        // im.turms.server.common.access.client.dto.model.common.StringsWithVersion.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            strings_ = com.google.protobuf.LazyStringArrayList.emptyList();
            lastUpdatedDate_ = 0L;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.server.common.access.client.dto.model.common.StringsWithVersionOuterClass.internal_static_im_turms_proto_StringsWithVersion_descriptor;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.common.StringsWithVersion getDefaultInstanceForType() {
            return im.turms.server.common.access.client.dto.model.common.StringsWithVersion
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.common.StringsWithVersion build() {
            im.turms.server.common.access.client.dto.model.common.StringsWithVersion result =
                    buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.common.StringsWithVersion buildPartial() {
            im.turms.server.common.access.client.dto.model.common.StringsWithVersion result =
                    new im.turms.server.common.access.client.dto.model.common.StringsWithVersion(
                            this);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartial0(
                im.turms.server.common.access.client.dto.model.common.StringsWithVersion result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                strings_.makeImmutable();
                result.strings_ = strings_;
            }
            int to_bitField0_ = 0;
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.lastUpdatedDate_ = lastUpdatedDate_;
                to_bitField0_ |= 0x00000001;
            }
            result.bitField0_ |= to_bitField0_;
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.server.common.access.client.dto.model.common.StringsWithVersion) {
                return mergeFrom(
                        (im.turms.server.common.access.client.dto.model.common.StringsWithVersion) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.server.common.access.client.dto.model.common.StringsWithVersion other) {
            if (other == im.turms.server.common.access.client.dto.model.common.StringsWithVersion
                    .getDefaultInstance()) {
                return this;
            }
            if (!other.strings_.isEmpty()) {
                if (strings_.isEmpty()) {
                    strings_ = other.strings_;
                    bitField0_ |= 0x00000001;
                } else {
                    ensureStringsIsMutable();
                    strings_.addAll(other.strings_);
                }
                onChanged();
            }
            if (other.hasLastUpdatedDate()) {
                setLastUpdatedDate(other.getLastUpdatedDate());
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
                            String s = input.readStringRequireUtf8();
                            ensureStringsIsMutable();
                            strings_.add(s);
                        } // case 10
                        case 16 -> {
                            lastUpdatedDate_ = input.readInt64();
                            bitField0_ |= 0x00000002;
                        } // case 16
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

        private com.google.protobuf.LazyStringArrayList strings_ =
                com.google.protobuf.LazyStringArrayList.emptyList();

        private void ensureStringsIsMutable() {
            if (!strings_.isModifiable()) {
                strings_ = new com.google.protobuf.LazyStringArrayList(strings_);
            }
            bitField0_ |= 0x00000001;
        }

        /**
         * <code>repeated string strings = 1;</code>
         *
         * @return A list containing the strings.
         */
        public com.google.protobuf.ProtocolStringList getStringsList() {
            strings_.makeImmutable();
            return strings_;
        }

        /**
         * <code>repeated string strings = 1;</code>
         *
         * @return The count of strings.
         */
        public int getStringsCount() {
            return strings_.size();
        }

        /**
         * <code>repeated string strings = 1;</code>
         *
         * @param index The index of the element to return.
         * @return The strings at the given index.
         */
        public java.lang.String getStrings(int index) {
            return strings_.get(index);
        }

        /**
         * <code>repeated string strings = 1;</code>
         *
         * @param index The index of the value to return.
         * @return The bytes of the strings at the given index.
         */
        public com.google.protobuf.ByteString getStringsBytes(int index) {
            return strings_.getByteString(index);
        }

        /**
         * <code>repeated string strings = 1;</code>
         *
         * @param index The index to set the value at.
         * @param value The strings to set.
         * @return This builder for chaining.
         */
        public Builder setStrings(int index, java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ensureStringsIsMutable();
            strings_.set(index, value);
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>repeated string strings = 1;</code>
         *
         * @param value The strings to add.
         * @return This builder for chaining.
         */
        public Builder addStrings(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            ensureStringsIsMutable();
            strings_.add(value);
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>repeated string strings = 1;</code>
         *
         * @param values The strings to add.
         * @return This builder for chaining.
         */
        public Builder addAllStrings(java.lang.Iterable<java.lang.String> values) {
            ensureStringsIsMutable();
            com.google.protobuf.AbstractMessageLite.Builder.addAll(values, strings_);
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>repeated string strings = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearStrings() {
            strings_ = com.google.protobuf.LazyStringArrayList.emptyList();
            bitField0_ &= ~0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>repeated string strings = 1;</code>
         *
         * @param value The bytes of the strings to add.
         * @return This builder for chaining.
         */
        public Builder addStringsBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            ensureStringsIsMutable();
            strings_.add(value);
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        private long lastUpdatedDate_;

        /**
         * <code>optional int64 last_updated_date = 2;</code>
         *
         * @return Whether the lastUpdatedDate field is set.
         */
        @java.lang.Override
        public boolean hasLastUpdatedDate() {
            return ((bitField0_ & 0x00000002) != 0);
        }

        /**
         * <code>optional int64 last_updated_date = 2;</code>
         *
         * @return The lastUpdatedDate.
         */
        @java.lang.Override
        public long getLastUpdatedDate() {
            return lastUpdatedDate_;
        }

        /**
         * <code>optional int64 last_updated_date = 2;</code>
         *
         * @param value The lastUpdatedDate to set.
         * @return This builder for chaining.
         */
        public Builder setLastUpdatedDate(long value) {

            lastUpdatedDate_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>optional int64 last_updated_date = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearLastUpdatedDate() {
            bitField0_ &= ~0x00000002;
            lastUpdatedDate_ = 0L;
            onChanged();
            return this;
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.StringsWithVersion)
    }

    // @@protoc_insertion_point(class_scope:im.turms.proto.StringsWithVersion)
    private static final im.turms.server.common.access.client.dto.model.common.StringsWithVersion DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE =
                new im.turms.server.common.access.client.dto.model.common.StringsWithVersion();
    }

    public static im.turms.server.common.access.client.dto.model.common.StringsWithVersion getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<StringsWithVersion> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public StringsWithVersion parsePartialFrom(
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

    public static com.google.protobuf.Parser<StringsWithVersion> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<StringsWithVersion> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.common.StringsWithVersion getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}