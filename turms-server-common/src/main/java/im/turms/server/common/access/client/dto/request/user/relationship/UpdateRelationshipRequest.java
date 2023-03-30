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
 * Protobuf type {@code im.turms.proto.UpdateRelationshipRequest}
 */
public final class UpdateRelationshipRequest extends com.google.protobuf.GeneratedMessageV3
        implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.UpdateRelationshipRequest)
        UpdateRelationshipRequestOrBuilder {
    private static final long serialVersionUID = 0L;

    // Use UpdateRelationshipRequest.newBuilder() to construct.
    private UpdateRelationshipRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private UpdateRelationshipRequest() {
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(UnusedPrivateParameter unused) {
        return new UpdateRelationshipRequest();
    }

    public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequestOuterClass.internal_static_im_turms_proto_UpdateRelationshipRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequestOuterClass.internal_static_im_turms_proto_UpdateRelationshipRequest_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequest.class,
                        im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequest.Builder.class);
    }

    private int bitField0_;
    public static final int USER_ID_FIELD_NUMBER = 1;
    private long userId_ = 0L;

    /**
     * <pre>
     * Query filter
     * </pre>
     * 
     * <code>int64 user_id = 1;</code>
     *
     * @return The userId.
     */
    @java.lang.Override
    public long getUserId() {
        return userId_;
    }

    public static final int BLOCKED_FIELD_NUMBER = 2;
    private boolean blocked_ = false;

    /**
     * <pre>
     * Update
     * </pre>
     * 
     * <code>optional bool blocked = 2;</code>
     *
     * @return Whether the blocked field is set.
     */
    @java.lang.Override
    public boolean hasBlocked() {
        return ((bitField0_ & 0x00000001) != 0);
    }

    /**
     * <pre>
     * Update
     * </pre>
     * 
     * <code>optional bool blocked = 2;</code>
     *
     * @return The blocked.
     */
    @java.lang.Override
    public boolean getBlocked() {
        return blocked_;
    }

    public static final int NEW_GROUP_INDEX_FIELD_NUMBER = 3;
    private int newGroupIndex_ = 0;

    /**
     * <code>optional int32 new_group_index = 3;</code>
     *
     * @return Whether the newGroupIndex field is set.
     */
    @java.lang.Override
    public boolean hasNewGroupIndex() {
        return ((bitField0_ & 0x00000002) != 0);
    }

    /**
     * <code>optional int32 new_group_index = 3;</code>
     *
     * @return The newGroupIndex.
     */
    @java.lang.Override
    public int getNewGroupIndex() {
        return newGroupIndex_;
    }

    public static final int DELETE_GROUP_INDEX_FIELD_NUMBER = 4;
    private int deleteGroupIndex_ = 0;

    /**
     * <code>optional int32 delete_group_index = 4;</code>
     *
     * @return Whether the deleteGroupIndex field is set.
     */
    @java.lang.Override
    public boolean hasDeleteGroupIndex() {
        return ((bitField0_ & 0x00000004) != 0);
    }

    /**
     * <code>optional int32 delete_group_index = 4;</code>
     *
     * @return The deleteGroupIndex.
     */
    @java.lang.Override
    public int getDeleteGroupIndex() {
        return deleteGroupIndex_;
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
        if (((bitField0_ & 0x00000001) != 0)) {
            output.writeBool(2, blocked_);
        }
        if (((bitField0_ & 0x00000002) != 0)) {
            output.writeInt32(3, newGroupIndex_);
        }
        if (((bitField0_ & 0x00000004) != 0)) {
            output.writeInt32(4, deleteGroupIndex_);
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
        if (((bitField0_ & 0x00000001) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeBoolSize(2, blocked_);
        }
        if (((bitField0_ & 0x00000002) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeInt32Size(3, newGroupIndex_);
        }
        if (((bitField0_ & 0x00000004) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeInt32Size(4, deleteGroupIndex_);
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
        if (!(obj instanceof UpdateRelationshipRequest other)) {
            return super.equals(obj);
        }

        if (getUserId() != other.getUserId()) {
            return false;
        }
        if (hasBlocked() != other.hasBlocked()) {
            return false;
        }
        if (hasBlocked()) {
            if (getBlocked() != other.getBlocked()) {
                return false;
            }
        }
        if (hasNewGroupIndex() != other.hasNewGroupIndex()) {
            return false;
        }
        if (hasNewGroupIndex()) {
            if (getNewGroupIndex() != other.getNewGroupIndex()) {
                return false;
            }
        }
        if (hasDeleteGroupIndex() != other.hasDeleteGroupIndex()) {
            return false;
        }
        if (hasDeleteGroupIndex()) {
            if (getDeleteGroupIndex() != other.getDeleteGroupIndex()) {
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
        if (hasBlocked()) {
            hash = (37 * hash) + BLOCKED_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(getBlocked());
        }
        if (hasNewGroupIndex()) {
            hash = (37 * hash) + NEW_GROUP_INDEX_FIELD_NUMBER;
            hash = (53 * hash) + getNewGroupIndex();
        }
        if (hasDeleteGroupIndex()) {
            hash = (37 * hash) + DELETE_GROUP_INDEX_FIELD_NUMBER;
            hash = (53 * hash) + getDeleteGroupIndex();
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequest parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequest parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequest parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequest parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequest parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequest parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequest parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequest parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequest parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequest parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequest parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequest parseFrom(
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
            im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequest prototype) {
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
     * Protobuf type {@code im.turms.proto.UpdateRelationshipRequest}
     */
    public static final class Builder
            extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.UpdateRelationshipRequest)
            im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequestOrBuilder {
        public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequestOuterClass.internal_static_im_turms_proto_UpdateRelationshipRequest_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequestOuterClass.internal_static_im_turms_proto_UpdateRelationshipRequest_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequest.class,
                            im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequest.Builder.class);
        }

        // Construct using
        // im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequest.newBuilder()
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
            blocked_ = false;
            newGroupIndex_ = 0;
            deleteGroupIndex_ = 0;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequestOuterClass.internal_static_im_turms_proto_UpdateRelationshipRequest_descriptor;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequest getDefaultInstanceForType() {
            return im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequest
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequest build() {
            im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequest result =
                    buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequest buildPartial() {
            im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequest result =
                    new im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequest(
                            this);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartial0(
                im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequest result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.userId_ = userId_;
            }
            int to_bitField0_ = 0;
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.blocked_ = blocked_;
                to_bitField0_ |= 0x00000001;
            }
            if (((from_bitField0_ & 0x00000004) != 0)) {
                result.newGroupIndex_ = newGroupIndex_;
                to_bitField0_ |= 0x00000002;
            }
            if (((from_bitField0_ & 0x00000008) != 0)) {
                result.deleteGroupIndex_ = deleteGroupIndex_;
                to_bitField0_ |= 0x00000004;
            }
            result.bitField0_ |= to_bitField0_;
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequest) {
                return mergeFrom(
                        (im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequest) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequest other) {
            if (other == im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequest
                    .getDefaultInstance()) {
                return this;
            }
            if (other.getUserId() != 0L) {
                setUserId(other.getUserId());
            }
            if (other.hasBlocked()) {
                setBlocked(other.getBlocked());
            }
            if (other.hasNewGroupIndex()) {
                setNewGroupIndex(other.getNewGroupIndex());
            }
            if (other.hasDeleteGroupIndex()) {
                setDeleteGroupIndex(other.getDeleteGroupIndex());
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
                            blocked_ = input.readBool();
                            bitField0_ |= 0x00000002;
                        } // case 16
                        case 24 -> {
                            newGroupIndex_ = input.readInt32();
                            bitField0_ |= 0x00000004;
                        } // case 24
                        case 32 -> {
                            deleteGroupIndex_ = input.readInt32();
                            bitField0_ |= 0x00000008;
                        } // case 32
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
         * <pre>
         * Query filter
         * </pre>
         * 
         * <code>int64 user_id = 1;</code>
         *
         * @return The userId.
         */
        @java.lang.Override
        public long getUserId() {
            return userId_;
        }

        /**
         * <pre>
         * Query filter
         * </pre>
         * 
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
         * <pre>
         * Query filter
         * </pre>
         * 
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

        private boolean blocked_;

        /**
         * <pre>
         * Update
         * </pre>
         * 
         * <code>optional bool blocked = 2;</code>
         *
         * @return Whether the blocked field is set.
         */
        @java.lang.Override
        public boolean hasBlocked() {
            return ((bitField0_ & 0x00000002) != 0);
        }

        /**
         * <pre>
         * Update
         * </pre>
         * 
         * <code>optional bool blocked = 2;</code>
         *
         * @return The blocked.
         */
        @java.lang.Override
        public boolean getBlocked() {
            return blocked_;
        }

        /**
         * <pre>
         * Update
         * </pre>
         * 
         * <code>optional bool blocked = 2;</code>
         *
         * @param value The blocked to set.
         * @return This builder for chaining.
         */
        public Builder setBlocked(boolean value) {

            blocked_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * Update
         * </pre>
         * 
         * <code>optional bool blocked = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearBlocked() {
            bitField0_ &= ~0x00000002;
            blocked_ = false;
            onChanged();
            return this;
        }

        private int newGroupIndex_;

        /**
         * <code>optional int32 new_group_index = 3;</code>
         *
         * @return Whether the newGroupIndex field is set.
         */
        @java.lang.Override
        public boolean hasNewGroupIndex() {
            return ((bitField0_ & 0x00000004) != 0);
        }

        /**
         * <code>optional int32 new_group_index = 3;</code>
         *
         * @return The newGroupIndex.
         */
        @java.lang.Override
        public int getNewGroupIndex() {
            return newGroupIndex_;
        }

        /**
         * <code>optional int32 new_group_index = 3;</code>
         *
         * @param value The newGroupIndex to set.
         * @return This builder for chaining.
         */
        public Builder setNewGroupIndex(int value) {

            newGroupIndex_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>optional int32 new_group_index = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearNewGroupIndex() {
            bitField0_ &= ~0x00000004;
            newGroupIndex_ = 0;
            onChanged();
            return this;
        }

        private int deleteGroupIndex_;

        /**
         * <code>optional int32 delete_group_index = 4;</code>
         *
         * @return Whether the deleteGroupIndex field is set.
         */
        @java.lang.Override
        public boolean hasDeleteGroupIndex() {
            return ((bitField0_ & 0x00000008) != 0);
        }

        /**
         * <code>optional int32 delete_group_index = 4;</code>
         *
         * @return The deleteGroupIndex.
         */
        @java.lang.Override
        public int getDeleteGroupIndex() {
            return deleteGroupIndex_;
        }

        /**
         * <code>optional int32 delete_group_index = 4;</code>
         *
         * @param value The deleteGroupIndex to set.
         * @return This builder for chaining.
         */
        public Builder setDeleteGroupIndex(int value) {

            deleteGroupIndex_ = value;
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        /**
         * <code>optional int32 delete_group_index = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearDeleteGroupIndex() {
            bitField0_ &= ~0x00000008;
            deleteGroupIndex_ = 0;
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

        // @@protoc_insertion_point(builder_scope:im.turms.proto.UpdateRelationshipRequest)
    }

    // @@protoc_insertion_point(class_scope:im.turms.proto.UpdateRelationshipRequest)
    private static final im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequest DEFAULT_INSTANCE;

    static {
        DEFAULT_INSTANCE =
                new im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequest();
    }

    public static im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<UpdateRelationshipRequest> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public UpdateRelationshipRequest parsePartialFrom(
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

    public static com.google.protobuf.Parser<UpdateRelationshipRequest> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<UpdateRelationshipRequest> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.user.relationship.UpdateRelationshipRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}