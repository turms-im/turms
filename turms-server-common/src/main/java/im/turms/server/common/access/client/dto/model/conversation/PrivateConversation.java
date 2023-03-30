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

package im.turms.server.common.access.client.dto.model.conversation;

/**
 * Protobuf type {@code im.turms.proto.PrivateConversation}
 */
public final class PrivateConversation extends com.google.protobuf.GeneratedMessageV3 implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.PrivateConversation)
        PrivateConversationOrBuilder {
    private static final long serialVersionUID = 0L;

    // Use PrivateConversation.newBuilder() to construct.
    private PrivateConversation(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private PrivateConversation() {
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(UnusedPrivateParameter unused) {
        return new PrivateConversation();
    }

    public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.server.common.access.client.dto.model.conversation.PrivateConversationOuterClass.internal_static_im_turms_proto_PrivateConversation_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.server.common.access.client.dto.model.conversation.PrivateConversationOuterClass.internal_static_im_turms_proto_PrivateConversation_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.server.common.access.client.dto.model.conversation.PrivateConversation.class,
                        im.turms.server.common.access.client.dto.model.conversation.PrivateConversation.Builder.class);
    }

    public static final int OWNER_ID_FIELD_NUMBER = 1;
    private long ownerId_ = 0L;

    /**
     * <code>int64 owner_id = 1;</code>
     *
     * @return The ownerId.
     */
    @java.lang.Override
    public long getOwnerId() {
        return ownerId_;
    }

    public static final int TARGET_ID_FIELD_NUMBER = 2;
    private long targetId_ = 0L;

    /**
     * <code>int64 target_id = 2;</code>
     *
     * @return The targetId.
     */
    @java.lang.Override
    public long getTargetId() {
        return targetId_;
    }

    public static final int READ_DATE_FIELD_NUMBER = 3;
    private long readDate_ = 0L;

    /**
     * <code>int64 read_date = 3;</code>
     *
     * @return The readDate.
     */
    @java.lang.Override
    public long getReadDate() {
        return readDate_;
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
        if (ownerId_ != 0L) {
            output.writeInt64(1, ownerId_);
        }
        if (targetId_ != 0L) {
            output.writeInt64(2, targetId_);
        }
        if (readDate_ != 0L) {
            output.writeInt64(3, readDate_);
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
        if (ownerId_ != 0L) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(1, ownerId_);
        }
        if (targetId_ != 0L) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(2, targetId_);
        }
        if (readDate_ != 0L) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(3, readDate_);
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
        if (!(obj instanceof PrivateConversation other)) {
            return super.equals(obj);
        }

        if (getOwnerId() != other.getOwnerId()) {
            return false;
        }
        if (getTargetId() != other.getTargetId()) {
            return false;
        }
        if (getReadDate() != other.getReadDate()) {
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
        hash = (37 * hash) + OWNER_ID_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getOwnerId());
        hash = (37 * hash) + TARGET_ID_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getTargetId());
        hash = (37 * hash) + READ_DATE_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getReadDate());
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.server.common.access.client.dto.model.conversation.PrivateConversation parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.conversation.PrivateConversation parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.conversation.PrivateConversation parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.conversation.PrivateConversation parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.conversation.PrivateConversation parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.conversation.PrivateConversation parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.conversation.PrivateConversation parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.conversation.PrivateConversation parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.conversation.PrivateConversation parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.conversation.PrivateConversation parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.conversation.PrivateConversation parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.conversation.PrivateConversation parseFrom(
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
            im.turms.server.common.access.client.dto.model.conversation.PrivateConversation prototype) {
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
     * Protobuf type {@code im.turms.proto.PrivateConversation}
     */
    public static final class Builder
            extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.PrivateConversation)
            im.turms.server.common.access.client.dto.model.conversation.PrivateConversationOrBuilder {
        public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.server.common.access.client.dto.model.conversation.PrivateConversationOuterClass.internal_static_im_turms_proto_PrivateConversation_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.server.common.access.client.dto.model.conversation.PrivateConversationOuterClass.internal_static_im_turms_proto_PrivateConversation_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.server.common.access.client.dto.model.conversation.PrivateConversation.class,
                            im.turms.server.common.access.client.dto.model.conversation.PrivateConversation.Builder.class);
        }

        // Construct using
        // im.turms.server.common.access.client.dto.model.conversation.PrivateConversation.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            ownerId_ = 0L;
            targetId_ = 0L;
            readDate_ = 0L;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.server.common.access.client.dto.model.conversation.PrivateConversationOuterClass.internal_static_im_turms_proto_PrivateConversation_descriptor;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.conversation.PrivateConversation getDefaultInstanceForType() {
            return im.turms.server.common.access.client.dto.model.conversation.PrivateConversation
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.conversation.PrivateConversation build() {
            im.turms.server.common.access.client.dto.model.conversation.PrivateConversation result =
                    buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.conversation.PrivateConversation buildPartial() {
            im.turms.server.common.access.client.dto.model.conversation.PrivateConversation result =
                    new im.turms.server.common.access.client.dto.model.conversation.PrivateConversation(
                            this);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartial0(
                im.turms.server.common.access.client.dto.model.conversation.PrivateConversation result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.ownerId_ = ownerId_;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.targetId_ = targetId_;
            }
            if (((from_bitField0_ & 0x00000004) != 0)) {
                result.readDate_ = readDate_;
            }
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.server.common.access.client.dto.model.conversation.PrivateConversation) {
                return mergeFrom(
                        (im.turms.server.common.access.client.dto.model.conversation.PrivateConversation) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.server.common.access.client.dto.model.conversation.PrivateConversation other) {
            if (other == im.turms.server.common.access.client.dto.model.conversation.PrivateConversation
                    .getDefaultInstance()) {
                return this;
            }
            if (other.getOwnerId() != 0L) {
                setOwnerId(other.getOwnerId());
            }
            if (other.getTargetId() != 0L) {
                setTargetId(other.getTargetId());
            }
            if (other.getReadDate() != 0L) {
                setReadDate(other.getReadDate());
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
                        case 8 -> {
                            ownerId_ = input.readInt64();
                            bitField0_ |= 0x00000001;
                        } // case 8
                        case 16 -> {
                            targetId_ = input.readInt64();
                            bitField0_ |= 0x00000002;
                        } // case 16
                        case 24 -> {
                            readDate_ = input.readInt64();
                            bitField0_ |= 0x00000004;
                        } // case 24
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

        private long ownerId_;

        /**
         * <code>int64 owner_id = 1;</code>
         *
         * @return The ownerId.
         */
        @java.lang.Override
        public long getOwnerId() {
            return ownerId_;
        }

        /**
         * <code>int64 owner_id = 1;</code>
         *
         * @param value The ownerId to set.
         * @return This builder for chaining.
         */
        public Builder setOwnerId(long value) {

            ownerId_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>int64 owner_id = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearOwnerId() {
            bitField0_ &= ~0x00000001;
            ownerId_ = 0L;
            onChanged();
            return this;
        }

        private long targetId_;

        /**
         * <code>int64 target_id = 2;</code>
         *
         * @return The targetId.
         */
        @java.lang.Override
        public long getTargetId() {
            return targetId_;
        }

        /**
         * <code>int64 target_id = 2;</code>
         *
         * @param value The targetId to set.
         * @return This builder for chaining.
         */
        public Builder setTargetId(long value) {

            targetId_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>int64 target_id = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearTargetId() {
            bitField0_ &= ~0x00000002;
            targetId_ = 0L;
            onChanged();
            return this;
        }

        private long readDate_;

        /**
         * <code>int64 read_date = 3;</code>
         *
         * @return The readDate.
         */
        @java.lang.Override
        public long getReadDate() {
            return readDate_;
        }

        /**
         * <code>int64 read_date = 3;</code>
         *
         * @param value The readDate to set.
         * @return This builder for chaining.
         */
        public Builder setReadDate(long value) {

            readDate_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>int64 read_date = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearReadDate() {
            bitField0_ &= ~0x00000004;
            readDate_ = 0L;
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

        // @@protoc_insertion_point(builder_scope:im.turms.proto.PrivateConversation)
    }

    // @@protoc_insertion_point(class_scope:im.turms.proto.PrivateConversation)
    private static final im.turms.server.common.access.client.dto.model.conversation.PrivateConversation DEFAULT_INSTANCE;

    static {
        DEFAULT_INSTANCE =
                new im.turms.server.common.access.client.dto.model.conversation.PrivateConversation();
    }

    public static im.turms.server.common.access.client.dto.model.conversation.PrivateConversation getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<PrivateConversation> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public PrivateConversation parsePartialFrom(
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

    public static com.google.protobuf.Parser<PrivateConversation> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<PrivateConversation> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.conversation.PrivateConversation getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}