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

package im.turms.server.common.access.client.dto.request.conversation;

/**
 * Protobuf type {@code im.turms.proto.UpdateTypingStatusRequest}
 */
public final class UpdateTypingStatusRequest extends com.google.protobuf.GeneratedMessageV3
        implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.UpdateTypingStatusRequest)
        UpdateTypingStatusRequestOrBuilder {
    private static final long serialVersionUID = 0L;

    // Use UpdateTypingStatusRequest.newBuilder() to construct.
    private UpdateTypingStatusRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private UpdateTypingStatusRequest() {
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(UnusedPrivateParameter unused) {
        return new UpdateTypingStatusRequest();
    }

    public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequestOuterClass.internal_static_im_turms_proto_UpdateTypingStatusRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequestOuterClass.internal_static_im_turms_proto_UpdateTypingStatusRequest_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequest.class,
                        im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequest.Builder.class);
    }

    public static final int IS_GROUP_MESSAGE_FIELD_NUMBER = 1;
    private boolean isGroupMessage_ = false;

    /**
     * <pre>
     * Query filter
     * </pre>
     * 
     * <code>bool is_group_message = 1;</code>
     *
     * @return The isGroupMessage.
     */
    @java.lang.Override
    public boolean getIsGroupMessage() {
        return isGroupMessage_;
    }

    public static final int TO_ID_FIELD_NUMBER = 2;
    private long toId_ = 0L;

    /**
     * <code>int64 to_id = 2;</code>
     *
     * @return The toId.
     */
    @java.lang.Override
    public long getToId() {
        return toId_;
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
        if (isGroupMessage_) {
            output.writeBool(1, isGroupMessage_);
        }
        if (toId_ != 0L) {
            output.writeInt64(2, toId_);
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
        if (isGroupMessage_) {
            size += com.google.protobuf.CodedOutputStream.computeBoolSize(1, isGroupMessage_);
        }
        if (toId_ != 0L) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(2, toId_);
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
        if (!(obj instanceof UpdateTypingStatusRequest other)) {
            return super.equals(obj);
        }

        if (getIsGroupMessage() != other.getIsGroupMessage()) {
            return false;
        }
        if (getToId() != other.getToId()) {
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
        hash = (37 * hash) + IS_GROUP_MESSAGE_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(getIsGroupMessage());
        hash = (37 * hash) + TO_ID_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getToId());
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequest parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequest parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequest parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequest parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequest parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequest parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequest parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequest parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequest parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequest parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequest parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequest parseFrom(
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
            im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequest prototype) {
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
     * Protobuf type {@code im.turms.proto.UpdateTypingStatusRequest}
     */
    public static final class Builder
            extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.UpdateTypingStatusRequest)
            im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequestOrBuilder {
        public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequestOuterClass.internal_static_im_turms_proto_UpdateTypingStatusRequest_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequestOuterClass.internal_static_im_turms_proto_UpdateTypingStatusRequest_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequest.class,
                            im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequest.Builder.class);
        }

        // Construct using
        // im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequest.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            isGroupMessage_ = false;
            toId_ = 0L;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequestOuterClass.internal_static_im_turms_proto_UpdateTypingStatusRequest_descriptor;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequest getDefaultInstanceForType() {
            return im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequest
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequest build() {
            im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequest result =
                    buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequest buildPartial() {
            im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequest result =
                    new im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequest(
                            this);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartial0(
                im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequest result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.isGroupMessage_ = isGroupMessage_;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.toId_ = toId_;
            }
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequest) {
                return mergeFrom(
                        (im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequest) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequest other) {
            if (other == im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequest
                    .getDefaultInstance()) {
                return this;
            }
            if (other.getIsGroupMessage()) {
                setIsGroupMessage(other.getIsGroupMessage());
            }
            if (other.getToId() != 0L) {
                setToId(other.getToId());
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
                            isGroupMessage_ = input.readBool();
                            bitField0_ |= 0x00000001;
                        } // case 8
                        case 16 -> {
                            toId_ = input.readInt64();
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

        private boolean isGroupMessage_;

        /**
         * <pre>
         * Query filter
         * </pre>
         * 
         * <code>bool is_group_message = 1;</code>
         *
         * @return The isGroupMessage.
         */
        @java.lang.Override
        public boolean getIsGroupMessage() {
            return isGroupMessage_;
        }

        /**
         * <pre>
         * Query filter
         * </pre>
         * 
         * <code>bool is_group_message = 1;</code>
         *
         * @param value The isGroupMessage to set.
         * @return This builder for chaining.
         */
        public Builder setIsGroupMessage(boolean value) {

            isGroupMessage_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * Query filter
         * </pre>
         * 
         * <code>bool is_group_message = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearIsGroupMessage() {
            bitField0_ &= ~0x00000001;
            isGroupMessage_ = false;
            onChanged();
            return this;
        }

        private long toId_;

        /**
         * <code>int64 to_id = 2;</code>
         *
         * @return The toId.
         */
        @java.lang.Override
        public long getToId() {
            return toId_;
        }

        /**
         * <code>int64 to_id = 2;</code>
         *
         * @param value The toId to set.
         * @return This builder for chaining.
         */
        public Builder setToId(long value) {

            toId_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>int64 to_id = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearToId() {
            bitField0_ &= ~0x00000002;
            toId_ = 0L;
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

        // @@protoc_insertion_point(builder_scope:im.turms.proto.UpdateTypingStatusRequest)
    }

    // @@protoc_insertion_point(class_scope:im.turms.proto.UpdateTypingStatusRequest)
    private static final im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequest DEFAULT_INSTANCE;

    static {
        DEFAULT_INSTANCE =
                new im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequest();
    }

    public static im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<UpdateTypingStatusRequest> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public UpdateTypingStatusRequest parsePartialFrom(
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

    public static com.google.protobuf.Parser<UpdateTypingStatusRequest> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<UpdateTypingStatusRequest> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.conversation.UpdateTypingStatusRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}