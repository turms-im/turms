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
 * Protobuf type {@code im.turms.proto.DeleteRelationshipRequest}
 */
public final class DeleteRelationshipRequest extends
        com.google.protobuf.GeneratedMessageLite<DeleteRelationshipRequest, DeleteRelationshipRequest.Builder>
        implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.DeleteRelationshipRequest)
        DeleteRelationshipRequestOrBuilder {
    private DeleteRelationshipRequest() {
    }

    private int bitField0_;
    public static final int USER_ID_FIELD_NUMBER = 1;
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
     */
    private void setUserId(long value) {

        userId_ = value;
    }

    /**
     * <code>int64 user_id = 1;</code>
     */
    private void clearUserId() {

        userId_ = 0L;
    }

    public static final int GROUP_INDEX_FIELD_NUMBER = 2;
    private int groupIndex_;

    /**
     * <code>optional int32 group_index = 2;</code>
     *
     * @return Whether the groupIndex field is set.
     */
    @java.lang.Override
    public boolean hasGroupIndex() {
        return ((bitField0_ & 0x00000001) != 0);
    }

    /**
     * <code>optional int32 group_index = 2;</code>
     *
     * @return The groupIndex.
     */
    @java.lang.Override
    public int getGroupIndex() {
        return groupIndex_;
    }

    /**
     * <code>optional int32 group_index = 2;</code>
     *
     * @param value The groupIndex to set.
     */
    private void setGroupIndex(int value) {
        bitField0_ |= 0x00000001;
        groupIndex_ = value;
    }

    /**
     * <code>optional int32 group_index = 2;</code>
     */
    private void clearGroupIndex() {
        bitField0_ &= ~0x00000001;
        groupIndex_ = 0;
    }

    public static final int TARGET_GROUP_INDEX_FIELD_NUMBER = 3;
    private int targetGroupIndex_;

    /**
     * <code>optional int32 target_group_index = 3;</code>
     *
     * @return Whether the targetGroupIndex field is set.
     */
    @java.lang.Override
    public boolean hasTargetGroupIndex() {
        return ((bitField0_ & 0x00000002) != 0);
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
     */
    private void setTargetGroupIndex(int value) {
        bitField0_ |= 0x00000002;
        targetGroupIndex_ = value;
    }

    /**
     * <code>optional int32 target_group_index = 3;</code>
     */
    private void clearTargetGroupIndex() {
        bitField0_ &= ~0x00000002;
        targetGroupIndex_ = 0;
    }

    public static im.turms.client.model.proto.request.user.relationship.DeleteRelationshipRequest parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.user.relationship.DeleteRelationshipRequest parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.user.relationship.DeleteRelationshipRequest parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.user.relationship.DeleteRelationshipRequest parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.user.relationship.DeleteRelationshipRequest parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.user.relationship.DeleteRelationshipRequest parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.user.relationship.DeleteRelationshipRequest parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.user.relationship.DeleteRelationshipRequest parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.user.relationship.DeleteRelationshipRequest parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.user.relationship.DeleteRelationshipRequest parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.user.relationship.DeleteRelationshipRequest parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.user.relationship.DeleteRelationshipRequest parseFrom(
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
            im.turms.client.model.proto.request.user.relationship.DeleteRelationshipRequest prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /**
     * Protobuf type {@code im.turms.proto.DeleteRelationshipRequest}
     */
    public static final class Builder extends
            com.google.protobuf.GeneratedMessageLite.Builder<im.turms.client.model.proto.request.user.relationship.DeleteRelationshipRequest, Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.DeleteRelationshipRequest)
            im.turms.client.model.proto.request.user.relationship.DeleteRelationshipRequestOrBuilder {
        // Construct using
        // im.turms.client.model.proto.request.user.relationship.DeleteRelationshipRequest.newBuilder()
        private Builder() {
            super(DEFAULT_INSTANCE);
        }

        /**
         * <code>int64 user_id = 1;</code>
         *
         * @return The userId.
         */
        @java.lang.Override
        public long getUserId() {
            return instance.getUserId();
        }

        /**
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
         * <code>optional int32 group_index = 2;</code>
         *
         * @return Whether the groupIndex field is set.
         */
        @java.lang.Override
        public boolean hasGroupIndex() {
            return instance.hasGroupIndex();
        }

        /**
         * <code>optional int32 group_index = 2;</code>
         *
         * @return The groupIndex.
         */
        @java.lang.Override
        public int getGroupIndex() {
            return instance.getGroupIndex();
        }

        /**
         * <code>optional int32 group_index = 2;</code>
         *
         * @param value The groupIndex to set.
         * @return This builder for chaining.
         */
        public Builder setGroupIndex(int value) {
            copyOnWrite();
            instance.setGroupIndex(value);
            return this;
        }

        /**
         * <code>optional int32 group_index = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearGroupIndex() {
            copyOnWrite();
            instance.clearGroupIndex();
            return this;
        }

        /**
         * <code>optional int32 target_group_index = 3;</code>
         *
         * @return Whether the targetGroupIndex field is set.
         */
        @java.lang.Override
        public boolean hasTargetGroupIndex() {
            return instance.hasTargetGroupIndex();
        }

        /**
         * <code>optional int32 target_group_index = 3;</code>
         *
         * @return The targetGroupIndex.
         */
        @java.lang.Override
        public int getTargetGroupIndex() {
            return instance.getTargetGroupIndex();
        }

        /**
         * <code>optional int32 target_group_index = 3;</code>
         *
         * @param value The targetGroupIndex to set.
         * @return This builder for chaining.
         */
        public Builder setTargetGroupIndex(int value) {
            copyOnWrite();
            instance.setTargetGroupIndex(value);
            return this;
        }

        /**
         * <code>optional int32 target_group_index = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearTargetGroupIndex() {
            copyOnWrite();
            instance.clearTargetGroupIndex();
            return this;
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.DeleteRelationshipRequest)
    }

    @java.lang.Override
    @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
    protected final java.lang.Object dynamicMethod(
            com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
            java.lang.Object arg0,
            java.lang.Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE: {
                return new im.turms.client.model.proto.request.user.relationship.DeleteRelationshipRequest();
            }
            case NEW_BUILDER: {
                return new Builder();
            }
            case BUILD_MESSAGE_INFO: {
                java.lang.Object[] objects = new java.lang.Object[]{"bitField0_",
                        "userId_",
                        "groupIndex_",
                        "targetGroupIndex_",};
                java.lang.String info =
                        "\u0000\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u0002\u0002\u1004"
                                + "\u0000\u0003\u1004\u0001";
                return newMessageInfo(DEFAULT_INSTANCE, info, objects);
            }
            // fall through
            case GET_DEFAULT_INSTANCE: {
                return DEFAULT_INSTANCE;
            }
            case GET_PARSER: {
                com.google.protobuf.Parser<im.turms.client.model.proto.request.user.relationship.DeleteRelationshipRequest> parser =
                        PARSER;
                if (parser == null) {
                    synchronized (im.turms.client.model.proto.request.user.relationship.DeleteRelationshipRequest.class) {
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

    // @@protoc_insertion_point(class_scope:im.turms.proto.DeleteRelationshipRequest)
    private static final im.turms.client.model.proto.request.user.relationship.DeleteRelationshipRequest DEFAULT_INSTANCE;

    static {
        DeleteRelationshipRequest defaultInstance = new DeleteRelationshipRequest();
        // New instances are implicitly immutable so no need to make
        // immutable.
        DEFAULT_INSTANCE = defaultInstance;
        com.google.protobuf.GeneratedMessageLite
                .registerDefaultInstance(DeleteRelationshipRequest.class, defaultInstance);
    }

    public static im.turms.client.model.proto.request.user.relationship.DeleteRelationshipRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static volatile com.google.protobuf.Parser<DeleteRelationshipRequest> PARSER;

    public static com.google.protobuf.Parser<DeleteRelationshipRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}