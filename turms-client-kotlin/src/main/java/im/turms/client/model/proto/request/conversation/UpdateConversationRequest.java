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

package im.turms.client.model.proto.request.conversation;

/**
 * Protobuf type {@code im.turms.proto.UpdateConversationRequest}
 */
public final class UpdateConversationRequest extends
        com.google.protobuf.GeneratedMessageLite<UpdateConversationRequest, UpdateConversationRequest.Builder>
        implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.UpdateConversationRequest)
        UpdateConversationRequestOrBuilder {
    private UpdateConversationRequest() {
    }

    private int bitField0_;
    public static final int TARGET_ID_FIELD_NUMBER = 1;
    private long targetId_;

    /**
     * <pre>
     * Query filter
     * </pre>
     * 
     * <code>optional int64 target_id = 1;</code>
     *
     * @return Whether the targetId field is set.
     */
    @java.lang.Override
    public boolean hasTargetId() {
        return ((bitField0_ & 0x00000001) != 0);
    }

    /**
     * <pre>
     * Query filter
     * </pre>
     * 
     * <code>optional int64 target_id = 1;</code>
     *
     * @return The targetId.
     */
    @java.lang.Override
    public long getTargetId() {
        return targetId_;
    }

    /**
     * <pre>
     * Query filter
     * </pre>
     * 
     * <code>optional int64 target_id = 1;</code>
     *
     * @param value The targetId to set.
     */
    private void setTargetId(long value) {
        bitField0_ |= 0x00000001;
        targetId_ = value;
    }

    /**
     * <pre>
     * Query filter
     * </pre>
     * 
     * <code>optional int64 target_id = 1;</code>
     */
    private void clearTargetId() {
        bitField0_ &= ~0x00000001;
        targetId_ = 0L;
    }

    public static final int GROUP_ID_FIELD_NUMBER = 2;
    private long groupId_;

    /**
     * <code>optional int64 group_id = 2;</code>
     *
     * @return Whether the groupId field is set.
     */
    @java.lang.Override
    public boolean hasGroupId() {
        return ((bitField0_ & 0x00000002) != 0);
    }

    /**
     * <code>optional int64 group_id = 2;</code>
     *
     * @return The groupId.
     */
    @java.lang.Override
    public long getGroupId() {
        return groupId_;
    }

    /**
     * <code>optional int64 group_id = 2;</code>
     *
     * @param value The groupId to set.
     */
    private void setGroupId(long value) {
        bitField0_ |= 0x00000002;
        groupId_ = value;
    }

    /**
     * <code>optional int64 group_id = 2;</code>
     */
    private void clearGroupId() {
        bitField0_ &= ~0x00000002;
        groupId_ = 0L;
    }

    public static final int READ_DATE_FIELD_NUMBER = 3;
    private long readDate_;

    /**
     * <pre>
     * Update
     * </pre>
     * 
     * <code>int64 read_date = 3;</code>
     *
     * @return The readDate.
     */
    @java.lang.Override
    public long getReadDate() {
        return readDate_;
    }

    /**
     * <pre>
     * Update
     * </pre>
     * 
     * <code>int64 read_date = 3;</code>
     *
     * @param value The readDate to set.
     */
    private void setReadDate(long value) {

        readDate_ = value;
    }

    /**
     * <pre>
     * Update
     * </pre>
     * 
     * <code>int64 read_date = 3;</code>
     */
    private void clearReadDate() {

        readDate_ = 0L;
    }

    public static im.turms.client.model.proto.request.conversation.UpdateConversationRequest parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.conversation.UpdateConversationRequest parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.conversation.UpdateConversationRequest parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.conversation.UpdateConversationRequest parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.conversation.UpdateConversationRequest parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.conversation.UpdateConversationRequest parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.conversation.UpdateConversationRequest parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.conversation.UpdateConversationRequest parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.conversation.UpdateConversationRequest parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.conversation.UpdateConversationRequest parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.conversation.UpdateConversationRequest parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.conversation.UpdateConversationRequest parseFrom(
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
            im.turms.client.model.proto.request.conversation.UpdateConversationRequest prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /**
     * Protobuf type {@code im.turms.proto.UpdateConversationRequest}
     */
    public static final class Builder extends
            com.google.protobuf.GeneratedMessageLite.Builder<im.turms.client.model.proto.request.conversation.UpdateConversationRequest, Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.UpdateConversationRequest)
            im.turms.client.model.proto.request.conversation.UpdateConversationRequestOrBuilder {
        // Construct using
        // im.turms.client.model.proto.request.conversation.UpdateConversationRequest.newBuilder()
        private Builder() {
            super(DEFAULT_INSTANCE);
        }

        /**
         * <pre>
         * Query filter
         * </pre>
         * 
         * <code>optional int64 target_id = 1;</code>
         *
         * @return Whether the targetId field is set.
         */
        @java.lang.Override
        public boolean hasTargetId() {
            return instance.hasTargetId();
        }

        /**
         * <pre>
         * Query filter
         * </pre>
         * 
         * <code>optional int64 target_id = 1;</code>
         *
         * @return The targetId.
         */
        @java.lang.Override
        public long getTargetId() {
            return instance.getTargetId();
        }

        /**
         * <pre>
         * Query filter
         * </pre>
         * 
         * <code>optional int64 target_id = 1;</code>
         *
         * @param value The targetId to set.
         * @return This builder for chaining.
         */
        public Builder setTargetId(long value) {
            copyOnWrite();
            instance.setTargetId(value);
            return this;
        }

        /**
         * <pre>
         * Query filter
         * </pre>
         * 
         * <code>optional int64 target_id = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearTargetId() {
            copyOnWrite();
            instance.clearTargetId();
            return this;
        }

        /**
         * <code>optional int64 group_id = 2;</code>
         *
         * @return Whether the groupId field is set.
         */
        @java.lang.Override
        public boolean hasGroupId() {
            return instance.hasGroupId();
        }

        /**
         * <code>optional int64 group_id = 2;</code>
         *
         * @return The groupId.
         */
        @java.lang.Override
        public long getGroupId() {
            return instance.getGroupId();
        }

        /**
         * <code>optional int64 group_id = 2;</code>
         *
         * @param value The groupId to set.
         * @return This builder for chaining.
         */
        public Builder setGroupId(long value) {
            copyOnWrite();
            instance.setGroupId(value);
            return this;
        }

        /**
         * <code>optional int64 group_id = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearGroupId() {
            copyOnWrite();
            instance.clearGroupId();
            return this;
        }

        /**
         * <pre>
         * Update
         * </pre>
         * 
         * <code>int64 read_date = 3;</code>
         *
         * @return The readDate.
         */
        @java.lang.Override
        public long getReadDate() {
            return instance.getReadDate();
        }

        /**
         * <pre>
         * Update
         * </pre>
         * 
         * <code>int64 read_date = 3;</code>
         *
         * @param value The readDate to set.
         * @return This builder for chaining.
         */
        public Builder setReadDate(long value) {
            copyOnWrite();
            instance.setReadDate(value);
            return this;
        }

        /**
         * <pre>
         * Update
         * </pre>
         * 
         * <code>int64 read_date = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearReadDate() {
            copyOnWrite();
            instance.clearReadDate();
            return this;
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.UpdateConversationRequest)
    }

    @java.lang.Override
    @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
    protected final java.lang.Object dynamicMethod(
            com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
            java.lang.Object arg0,
            java.lang.Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE: {
                return new im.turms.client.model.proto.request.conversation.UpdateConversationRequest();
            }
            case NEW_BUILDER: {
                return new Builder();
            }
            case BUILD_MESSAGE_INFO: {
                java.lang.Object[] objects =
                        new java.lang.Object[]{"bitField0_", "targetId_", "groupId_", "readDate_",};
                java.lang.String info =
                        "\u0000\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u1002\u0000\u0002"
                                + "\u1002\u0001\u0003\u0002";
                return newMessageInfo(DEFAULT_INSTANCE, info, objects);
            }
            // fall through
            case GET_DEFAULT_INSTANCE: {
                return DEFAULT_INSTANCE;
            }
            case GET_PARSER: {
                com.google.protobuf.Parser<im.turms.client.model.proto.request.conversation.UpdateConversationRequest> parser =
                        PARSER;
                if (parser == null) {
                    synchronized (im.turms.client.model.proto.request.conversation.UpdateConversationRequest.class) {
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

    // @@protoc_insertion_point(class_scope:im.turms.proto.UpdateConversationRequest)
    private static final im.turms.client.model.proto.request.conversation.UpdateConversationRequest DEFAULT_INSTANCE;

    static {
        UpdateConversationRequest defaultInstance = new UpdateConversationRequest();
        // New instances are implicitly immutable so no need to make
        // immutable.
        DEFAULT_INSTANCE = defaultInstance;
        com.google.protobuf.GeneratedMessageLite
                .registerDefaultInstance(UpdateConversationRequest.class, defaultInstance);
    }

    public static im.turms.client.model.proto.request.conversation.UpdateConversationRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static volatile com.google.protobuf.Parser<UpdateConversationRequest> PARSER;

    public static com.google.protobuf.Parser<UpdateConversationRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}