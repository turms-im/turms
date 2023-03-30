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

package im.turms.server.common.access.client.dto.request.user.relationship;

/**
 * Protobuf type {@code im.turms.proto.DeleteRelationshipGroupMemberRequest}
 */
public final class DeleteRelationshipGroupMemberRequest
        extends com.google.protobuf.GeneratedMessageV3 implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.DeleteRelationshipGroupMemberRequest)
        DeleteRelationshipGroupMemberRequestOrBuilder {
    private static final long serialVersionUID = 0L;

    // Use DeleteRelationshipGroupMemberRequest.newBuilder() to construct.
    private DeleteRelationshipGroupMemberRequest(
            com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private DeleteRelationshipGroupMemberRequest() {
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(UnusedPrivateParameter unused) {
        return new DeleteRelationshipGroupMemberRequest();
    }

    public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupMemberRequestOuterClass.internal_static_im_turms_proto_DeleteRelationshipGroupMemberRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupMemberRequestOuterClass.internal_static_im_turms_proto_DeleteRelationshipGroupMemberRequest_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupMemberRequest.class,
                        im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupMemberRequest.Builder.class);
    }

    private int bitField0_;
    public static final int USER_ID_FIELD_NUMBER = 1;
    private long userId_ = 0L;

    /**
     * <code>int64 user_id = 1;</code>
     *
     * @return The userId.
     */
    @java.lang.Override
    public long getUserId() {
        return userId_;
    }

    public static final int GROUP_INDEX_FIELD_NUMBER = 2;
    private int groupIndex_ = 0;

    /**
     * <code>int32 group_index = 2;</code>
     *
     * @return The groupIndex.
     */
    @java.lang.Override
    public int getGroupIndex() {
        return groupIndex_;
    }

    public static final int TARGET_GROUP_INDEX_FIELD_NUMBER = 3;
    private int targetGroupIndex_ = 0;

    /**
     * <code>optional int32 target_group_index = 3;</code>
     *
     * @return Whether the targetGroupIndex field is set.
     */
    @java.lang.Override
    public boolean hasTargetGroupIndex() {
        return ((bitField0_ & 0x00000001) != 0);
    }

    /**
     * <code>optional int32 target_group_index = 3;</code>
     *
     * @return The targetGroupIndex.
     */
    @java.lang.Override
    public int getTargetGroupIndex() {
        return targetGroupIndex_;
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
        if (userId_ != 0L) {
            output.writeInt64(1, userId_);
        }
        if (groupIndex_ != 0) {
            output.writeInt32(2, groupIndex_);
        }
        if (((bitField0_ & 0x00000001) != 0)) {
            output.writeInt32(3, targetGroupIndex_);
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
        if (userId_ != 0L) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(1, userId_);
        }
        if (groupIndex_ != 0) {
            size += com.google.protobuf.CodedOutputStream.computeInt32Size(2, groupIndex_);
        }
        if (((bitField0_ & 0x00000001) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeInt32Size(3, targetGroupIndex_);
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
        if (!(obj instanceof DeleteRelationshipGroupMemberRequest other)) {
            return super.equals(obj);
        }

        if (getUserId() != other.getUserId()) {
            return false;
        }
        if (getGroupIndex() != other.getGroupIndex()) {
            return false;
        }
        if (hasTargetGroupIndex() != other.hasTargetGroupIndex()) {
            return false;
        }
        if (hasTargetGroupIndex()) {
            if (getTargetGroupIndex() != other.getTargetGroupIndex()) {
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
        hash = (37 * hash) + USER_ID_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getUserId());
        hash = (37 * hash) + GROUP_INDEX_FIELD_NUMBER;
        hash = (53 * hash) + getGroupIndex();
        if (hasTargetGroupIndex()) {
            hash = (37 * hash) + TARGET_GROUP_INDEX_FIELD_NUMBER;
            hash = (53 * hash) + getTargetGroupIndex();
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupMemberRequest parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupMemberRequest parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupMemberRequest parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupMemberRequest parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupMemberRequest parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupMemberRequest parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupMemberRequest parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupMemberRequest parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupMemberRequest parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupMemberRequest parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupMemberRequest parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupMemberRequest parseFrom(
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
            im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupMemberRequest prototype) {
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
     * Protobuf type {@code im.turms.proto.DeleteRelationshipGroupMemberRequest}
     */
    public static final class Builder
            extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.DeleteRelationshipGroupMemberRequest)
            im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupMemberRequestOrBuilder {
        public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupMemberRequestOuterClass.internal_static_im_turms_proto_DeleteRelationshipGroupMemberRequest_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupMemberRequestOuterClass.internal_static_im_turms_proto_DeleteRelationshipGroupMemberRequest_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupMemberRequest.class,
                            im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupMemberRequest.Builder.class);
        }

        // Construct using
        // im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupMemberRequest.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            userId_ = 0L;
            groupIndex_ = 0;
            targetGroupIndex_ = 0;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupMemberRequestOuterClass.internal_static_im_turms_proto_DeleteRelationshipGroupMemberRequest_descriptor;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupMemberRequest getDefaultInstanceForType() {
            return im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupMemberRequest
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupMemberRequest build() {
            im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupMemberRequest result =
                    buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupMemberRequest buildPartial() {
            im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupMemberRequest result =
                    new im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupMemberRequest(
                            this);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartial0(
                im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupMemberRequest result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.userId_ = userId_;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.groupIndex_ = groupIndex_;
            }
            int to_bitField0_ = 0;
            if (((from_bitField0_ & 0x00000004) != 0)) {
                result.targetGroupIndex_ = targetGroupIndex_;
                to_bitField0_ |= 0x00000001;
            }
            result.bitField0_ |= to_bitField0_;
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupMemberRequest) {
                return mergeFrom(
                        (im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupMemberRequest) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupMemberRequest other) {
            if (other == im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupMemberRequest
                    .getDefaultInstance()) {
                return this;
            }
            if (other.getUserId() != 0L) {
                setUserId(other.getUserId());
            }
            if (other.getGroupIndex() != 0) {
                setGroupIndex(other.getGroupIndex());
            }
            if (other.hasTargetGroupIndex()) {
                setTargetGroupIndex(other.getTargetGroupIndex());
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
                            userId_ = input.readInt64();
                            bitField0_ |= 0x00000001;
                        } // case 8
                        case 16 -> {
                            groupIndex_ = input.readInt32();
                            bitField0_ |= 0x00000002;
                        } // case 16
                        case 24 -> {
                            targetGroupIndex_ = input.readInt32();
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

        private long userId_;

        /**
         * <code>int64 user_id = 1;</code>
         *
         * @return The userId.
         */
        @java.lang.Override
        public long getUserId() {
            return userId_;
        }

        /**
         * <code>int64 user_id = 1;</code>
         *
         * @param value The userId to set.
         * @return This builder for chaining.
         */
        public Builder setUserId(long value) {

            userId_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>int64 user_id = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearUserId() {
            bitField0_ &= ~0x00000001;
            userId_ = 0L;
            onChanged();
            return this;
        }

        private int groupIndex_;

        /**
         * <code>int32 group_index = 2;</code>
         *
         * @return The groupIndex.
         */
        @java.lang.Override
        public int getGroupIndex() {
            return groupIndex_;
        }

        /**
         * <code>int32 group_index = 2;</code>
         *
         * @param value The groupIndex to set.
         * @return This builder for chaining.
         */
        public Builder setGroupIndex(int value) {

            groupIndex_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>int32 group_index = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearGroupIndex() {
            bitField0_ &= ~0x00000002;
            groupIndex_ = 0;
            onChanged();
            return this;
        }

        private int targetGroupIndex_;

        /**
         * <code>optional int32 target_group_index = 3;</code>
         *
         * @return Whether the targetGroupIndex field is set.
         */
        @java.lang.Override
        public boolean hasTargetGroupIndex() {
            return ((bitField0_ & 0x00000004) != 0);
        }

        /**
         * <code>optional int32 target_group_index = 3;</code>
         *
         * @return The targetGroupIndex.
         */
        @java.lang.Override
        public int getTargetGroupIndex() {
            return targetGroupIndex_;
        }

        /**
         * <code>optional int32 target_group_index = 3;</code>
         *
         * @param value The targetGroupIndex to set.
         * @return This builder for chaining.
         */
        public Builder setTargetGroupIndex(int value) {

            targetGroupIndex_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>optional int32 target_group_index = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearTargetGroupIndex() {
            bitField0_ &= ~0x00000004;
            targetGroupIndex_ = 0;
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

        // @@protoc_insertion_point(builder_scope:im.turms.proto.DeleteRelationshipGroupMemberRequest)
    }

    // @@protoc_insertion_point(class_scope:im.turms.proto.DeleteRelationshipGroupMemberRequest)
    private static final im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupMemberRequest DEFAULT_INSTANCE;

    static {
        DEFAULT_INSTANCE =
                new im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupMemberRequest();
    }

    public static im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupMemberRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<DeleteRelationshipGroupMemberRequest> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public DeleteRelationshipGroupMemberRequest parsePartialFrom(
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

    public static com.google.protobuf.Parser<DeleteRelationshipGroupMemberRequest> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<DeleteRelationshipGroupMemberRequest> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.user.relationship.DeleteRelationshipGroupMemberRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}