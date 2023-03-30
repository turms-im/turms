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

package im.turms.client.model.proto.request.user.relationship;

/**
 * Protobuf type {@code im.turms.proto.UpdateRelationshipRequest}
 */
public final class UpdateRelationshipRequest extends
        com.google.protobuf.GeneratedMessageLite<UpdateRelationshipRequest, UpdateRelationshipRequest.Builder>
        implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.UpdateRelationshipRequest)
        UpdateRelationshipRequestOrBuilder {
    private UpdateRelationshipRequest() {
    }

    private int bitField0_;
    public static final int USER_ID_FIELD_NUMBER = 1;
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
     */
    private void setUserId(long value) {

        userId_ = value;
    }

    /**
     * <pre>
     * Query filter
     * </pre>
     * 
     * <code>int64 user_id = 1;</code>
     */
    private void clearUserId() {

        userId_ = 0L;
    }

    public static final int BLOCKED_FIELD_NUMBER = 2;
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

    /**
     * <pre>
     * Update
     * </pre>
     * 
     * <code>optional bool blocked = 2;</code>
     *
     * @param value The blocked to set.
     */
    private void setBlocked(boolean value) {
        bitField0_ |= 0x00000001;
        blocked_ = value;
    }

    /**
     * <pre>
     * Update
     * </pre>
     * 
     * <code>optional bool blocked = 2;</code>
     */
    private void clearBlocked() {
        bitField0_ &= ~0x00000001;
        blocked_ = false;
    }

    public static final int NEW_GROUP_INDEX_FIELD_NUMBER = 3;
    private int newGroupIndex_;

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

    /**
     * <code>optional int32 new_group_index = 3;</code>
     *
     * @param value The newGroupIndex to set.
     */
    private void setNewGroupIndex(int value) {
        bitField0_ |= 0x00000002;
        newGroupIndex_ = value;
    }

    /**
     * <code>optional int32 new_group_index = 3;</code>
     */
    private void clearNewGroupIndex() {
        bitField0_ &= ~0x00000002;
        newGroupIndex_ = 0;
    }

    public static final int DELETE_GROUP_INDEX_FIELD_NUMBER = 4;
    private int deleteGroupIndex_;

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

    /**
     * <code>optional int32 delete_group_index = 4;</code>
     *
     * @param value The deleteGroupIndex to set.
     */
    private void setDeleteGroupIndex(int value) {
        bitField0_ |= 0x00000004;
        deleteGroupIndex_ = value;
    }

    /**
     * <code>optional int32 delete_group_index = 4;</code>
     */
    private void clearDeleteGroupIndex() {
        bitField0_ &= ~0x00000004;
        deleteGroupIndex_ = 0;
    }

    public static im.turms.client.model.proto.request.user.relationship.UpdateRelationshipRequest parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.user.relationship.UpdateRelationshipRequest parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.user.relationship.UpdateRelationshipRequest parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.user.relationship.UpdateRelationshipRequest parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.user.relationship.UpdateRelationshipRequest parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.user.relationship.UpdateRelationshipRequest parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.user.relationship.UpdateRelationshipRequest parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.user.relationship.UpdateRelationshipRequest parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.user.relationship.UpdateRelationshipRequest parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.user.relationship.UpdateRelationshipRequest parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.user.relationship.UpdateRelationshipRequest parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.user.relationship.UpdateRelationshipRequest parseFrom(
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
            im.turms.client.model.proto.request.user.relationship.UpdateRelationshipRequest prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /**
     * Protobuf type {@code im.turms.proto.UpdateRelationshipRequest}
     */
    public static final class Builder extends
            com.google.protobuf.GeneratedMessageLite.Builder<im.turms.client.model.proto.request.user.relationship.UpdateRelationshipRequest, Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.UpdateRelationshipRequest)
            im.turms.client.model.proto.request.user.relationship.UpdateRelationshipRequestOrBuilder {
        // Construct using
        // im.turms.client.model.proto.request.user.relationship.UpdateRelationshipRequest.newBuilder()
        private Builder() {
            super(DEFAULT_INSTANCE);
        }

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
            return instance.getUserId();
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
            copyOnWrite();
            instance.setUserId(value);
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
            copyOnWrite();
            instance.clearUserId();
            return this;
        }

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
            return instance.hasBlocked();
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
            return instance.getBlocked();
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
            copyOnWrite();
            instance.setBlocked(value);
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
            copyOnWrite();
            instance.clearBlocked();
            return this;
        }

        /**
         * <code>optional int32 new_group_index = 3;</code>
         *
         * @return Whether the newGroupIndex field is set.
         */
        @java.lang.Override
        public boolean hasNewGroupIndex() {
            return instance.hasNewGroupIndex();
        }

        /**
         * <code>optional int32 new_group_index = 3;</code>
         *
         * @return The newGroupIndex.
         */
        @java.lang.Override
        public int getNewGroupIndex() {
            return instance.getNewGroupIndex();
        }

        /**
         * <code>optional int32 new_group_index = 3;</code>
         *
         * @param value The newGroupIndex to set.
         * @return This builder for chaining.
         */
        public Builder setNewGroupIndex(int value) {
            copyOnWrite();
            instance.setNewGroupIndex(value);
            return this;
        }

        /**
         * <code>optional int32 new_group_index = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearNewGroupIndex() {
            copyOnWrite();
            instance.clearNewGroupIndex();
            return this;
        }

        /**
         * <code>optional int32 delete_group_index = 4;</code>
         *
         * @return Whether the deleteGroupIndex field is set.
         */
        @java.lang.Override
        public boolean hasDeleteGroupIndex() {
            return instance.hasDeleteGroupIndex();
        }

        /**
         * <code>optional int32 delete_group_index = 4;</code>
         *
         * @return The deleteGroupIndex.
         */
        @java.lang.Override
        public int getDeleteGroupIndex() {
            return instance.getDeleteGroupIndex();
        }

        /**
         * <code>optional int32 delete_group_index = 4;</code>
         *
         * @param value The deleteGroupIndex to set.
         * @return This builder for chaining.
         */
        public Builder setDeleteGroupIndex(int value) {
            copyOnWrite();
            instance.setDeleteGroupIndex(value);
            return this;
        }

        /**
         * <code>optional int32 delete_group_index = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearDeleteGroupIndex() {
            copyOnWrite();
            instance.clearDeleteGroupIndex();
            return this;
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.UpdateRelationshipRequest)
    }

    @java.lang.Override
    @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
    protected final java.lang.Object dynamicMethod(
            com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
            java.lang.Object arg0,
            java.lang.Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE: {
                return new im.turms.client.model.proto.request.user.relationship.UpdateRelationshipRequest();
            }
            case NEW_BUILDER: {
                return new Builder();
            }
            case BUILD_MESSAGE_INFO: {
                java.lang.Object[] objects = new java.lang.Object[]{"bitField0_",
                        "userId_",
                        "blocked_",
                        "newGroupIndex_",
                        "deleteGroupIndex_",};
                java.lang.String info =
                        "\u0000\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001\u0002\u0002\u1007"
                                + "\u0000\u0003\u1004\u0001\u0004\u1004\u0002";
                return newMessageInfo(DEFAULT_INSTANCE, info, objects);
            }
            // fall through
            case GET_DEFAULT_INSTANCE: {
                return DEFAULT_INSTANCE;
            }
            case GET_PARSER: {
                com.google.protobuf.Parser<im.turms.client.model.proto.request.user.relationship.UpdateRelationshipRequest> parser =
                        PARSER;
                if (parser == null) {
                    synchronized (im.turms.client.model.proto.request.user.relationship.UpdateRelationshipRequest.class) {
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

    // @@protoc_insertion_point(class_scope:im.turms.proto.UpdateRelationshipRequest)
    private static final im.turms.client.model.proto.request.user.relationship.UpdateRelationshipRequest DEFAULT_INSTANCE;

    static {
        UpdateRelationshipRequest defaultInstance = new UpdateRelationshipRequest();
        // New instances are implicitly immutable so no need to make
        // immutable.
        DEFAULT_INSTANCE = defaultInstance;
        com.google.protobuf.GeneratedMessageLite
                .registerDefaultInstance(UpdateRelationshipRequest.class, defaultInstance);
    }

    public static im.turms.client.model.proto.request.user.relationship.UpdateRelationshipRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static volatile com.google.protobuf.Parser<UpdateRelationshipRequest> PARSER;

    public static com.google.protobuf.Parser<UpdateRelationshipRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}