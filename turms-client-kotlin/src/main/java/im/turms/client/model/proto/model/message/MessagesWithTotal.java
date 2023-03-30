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

package im.turms.client.model.proto.model.message;

/**
 * Protobuf type {@code im.turms.proto.MessagesWithTotal}
 */
public final class MessagesWithTotal extends
        com.google.protobuf.GeneratedMessageLite<MessagesWithTotal, MessagesWithTotal.Builder>
        implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.MessagesWithTotal)
        MessagesWithTotalOrBuilder {
    private MessagesWithTotal() {
        messages_ = emptyProtobufList();
    }

    public static final int TOTAL_FIELD_NUMBER = 1;
    private int total_;

    /**
     * <code>int32 total = 1;</code>
     *
     * @return The total.
     */
    @java.lang.Override
    public int getTotal() {
        return total_;
    }

    /**
     * <code>int32 total = 1;</code>
     *
     * @param value The total to set.
     */
    private void setTotal(int value) {

        total_ = value;
    }

    /**
     * <code>int32 total = 1;</code>
     */
    private void clearTotal() {

        total_ = 0;
    }

    public static final int IS_GROUP_MESSAGE_FIELD_NUMBER = 2;
    private boolean isGroupMessage_;

    /**
     * <code>bool is_group_message = 2;</code>
     *
     * @return The isGroupMessage.
     */
    @java.lang.Override
    public boolean getIsGroupMessage() {
        return isGroupMessage_;
    }

    /**
     * <code>bool is_group_message = 2;</code>
     *
     * @param value The isGroupMessage to set.
     */
    private void setIsGroupMessage(boolean value) {

        isGroupMessage_ = value;
    }

    /**
     * <code>bool is_group_message = 2;</code>
     */
    private void clearIsGroupMessage() {

        isGroupMessage_ = false;
    }

    public static final int FROM_ID_FIELD_NUMBER = 3;
    private long fromId_;

    /**
     * <code>int64 from_id = 3;</code>
     *
     * @return The fromId.
     */
    @java.lang.Override
    public long getFromId() {
        return fromId_;
    }

    /**
     * <code>int64 from_id = 3;</code>
     *
     * @param value The fromId to set.
     */
    private void setFromId(long value) {

        fromId_ = value;
    }

    /**
     * <code>int64 from_id = 3;</code>
     */
    private void clearFromId() {

        fromId_ = 0L;
    }

    public static final int MESSAGES_FIELD_NUMBER = 4;
    private com.google.protobuf.Internal.ProtobufList<im.turms.client.model.proto.model.message.Message> messages_;

    /**
     * <code>repeated .im.turms.proto.Message messages = 4;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.client.model.proto.model.message.Message> getMessagesList() {
        return messages_;
    }

    /**
     * <code>repeated .im.turms.proto.Message messages = 4;</code>
     */
    public java.util.List<? extends im.turms.client.model.proto.model.message.MessageOrBuilder> getMessagesOrBuilderList() {
        return messages_;
    }

    /**
     * <code>repeated .im.turms.proto.Message messages = 4;</code>
     */
    @java.lang.Override
    public int getMessagesCount() {
        return messages_.size();
    }

    /**
     * <code>repeated .im.turms.proto.Message messages = 4;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.model.message.Message getMessages(int index) {
        return messages_.get(index);
    }

    /**
     * <code>repeated .im.turms.proto.Message messages = 4;</code>
     */
    public im.turms.client.model.proto.model.message.MessageOrBuilder getMessagesOrBuilder(
            int index) {
        return messages_.get(index);
    }

    private void ensureMessagesIsMutable() {
        com.google.protobuf.Internal.ProtobufList<im.turms.client.model.proto.model.message.Message> tmp =
                messages_;
        if (!tmp.isModifiable()) {
            messages_ = com.google.protobuf.GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /**
     * <code>repeated .im.turms.proto.Message messages = 4;</code>
     */
    private void setMessages(int index, im.turms.client.model.proto.model.message.Message value) {
        value.getClass();
        ensureMessagesIsMutable();
        messages_.set(index, value);
    }

    /**
     * <code>repeated .im.turms.proto.Message messages = 4;</code>
     */
    private void addMessages(im.turms.client.model.proto.model.message.Message value) {
        value.getClass();
        ensureMessagesIsMutable();
        messages_.add(value);
    }

    /**
     * <code>repeated .im.turms.proto.Message messages = 4;</code>
     */
    private void addMessages(int index, im.turms.client.model.proto.model.message.Message value) {
        value.getClass();
        ensureMessagesIsMutable();
        messages_.add(index, value);
    }

    /**
     * <code>repeated .im.turms.proto.Message messages = 4;</code>
     */
    private void addAllMessages(
            java.lang.Iterable<? extends im.turms.client.model.proto.model.message.Message> values) {
        ensureMessagesIsMutable();
        com.google.protobuf.AbstractMessageLite.addAll(values, messages_);
    }

    /**
     * <code>repeated .im.turms.proto.Message messages = 4;</code>
     */
    private void clearMessages() {
        messages_ = emptyProtobufList();
    }

    /**
     * <code>repeated .im.turms.proto.Message messages = 4;</code>
     */
    private void removeMessages(int index) {
        ensureMessagesIsMutable();
        messages_.remove(index);
    }

    public static im.turms.client.model.proto.model.message.MessagesWithTotal parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.model.message.MessagesWithTotal parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.message.MessagesWithTotal parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.model.message.MessagesWithTotal parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.message.MessagesWithTotal parseFrom(byte[] data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.model.message.MessagesWithTotal parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.message.MessagesWithTotal parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.model.message.MessagesWithTotal parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.message.MessagesWithTotal parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.model.message.MessagesWithTotal parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.message.MessagesWithTotal parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.model.message.MessagesWithTotal parseFrom(
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
            im.turms.client.model.proto.model.message.MessagesWithTotal prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /**
     * Protobuf type {@code im.turms.proto.MessagesWithTotal}
     */
    public static final class Builder extends
            com.google.protobuf.GeneratedMessageLite.Builder<im.turms.client.model.proto.model.message.MessagesWithTotal, Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.MessagesWithTotal)
            im.turms.client.model.proto.model.message.MessagesWithTotalOrBuilder {
        // Construct using im.turms.client.model.proto.model.message.MessagesWithTotal.newBuilder()
        private Builder() {
            super(DEFAULT_INSTANCE);
        }

        /**
         * <code>int32 total = 1;</code>
         *
         * @return The total.
         */
        @java.lang.Override
        public int getTotal() {
            return instance.getTotal();
        }

        /**
         * <code>int32 total = 1;</code>
         *
         * @param value The total to set.
         * @return This builder for chaining.
         */
        public Builder setTotal(int value) {
            copyOnWrite();
            instance.setTotal(value);
            return this;
        }

        /**
         * <code>int32 total = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearTotal() {
            copyOnWrite();
            instance.clearTotal();
            return this;
        }

        /**
         * <code>bool is_group_message = 2;</code>
         *
         * @return The isGroupMessage.
         */
        @java.lang.Override
        public boolean getIsGroupMessage() {
            return instance.getIsGroupMessage();
        }

        /**
         * <code>bool is_group_message = 2;</code>
         *
         * @param value The isGroupMessage to set.
         * @return This builder for chaining.
         */
        public Builder setIsGroupMessage(boolean value) {
            copyOnWrite();
            instance.setIsGroupMessage(value);
            return this;
        }

        /**
         * <code>bool is_group_message = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearIsGroupMessage() {
            copyOnWrite();
            instance.clearIsGroupMessage();
            return this;
        }

        /**
         * <code>int64 from_id = 3;</code>
         *
         * @return The fromId.
         */
        @java.lang.Override
        public long getFromId() {
            return instance.getFromId();
        }

        /**
         * <code>int64 from_id = 3;</code>
         *
         * @param value The fromId to set.
         * @return This builder for chaining.
         */
        public Builder setFromId(long value) {
            copyOnWrite();
            instance.setFromId(value);
            return this;
        }

        /**
         * <code>int64 from_id = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearFromId() {
            copyOnWrite();
            instance.clearFromId();
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Message messages = 4;</code>
         */
        @java.lang.Override
        public java.util.List<im.turms.client.model.proto.model.message.Message> getMessagesList() {
            return java.util.Collections.unmodifiableList(instance.getMessagesList());
        }

        /**
         * <code>repeated .im.turms.proto.Message messages = 4;</code>
         */
        @java.lang.Override
        public int getMessagesCount() {
            return instance.getMessagesCount();
        }

        /**
         * <code>repeated .im.turms.proto.Message messages = 4;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.model.message.Message getMessages(int index) {
            return instance.getMessages(index);
        }

        /**
         * <code>repeated .im.turms.proto.Message messages = 4;</code>
         */
        public Builder setMessages(
                int index,
                im.turms.client.model.proto.model.message.Message value) {
            copyOnWrite();
            instance.setMessages(index, value);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Message messages = 4;</code>
         */
        public Builder setMessages(
                int index,
                im.turms.client.model.proto.model.message.Message.Builder builderForValue) {
            copyOnWrite();
            instance.setMessages(index, builderForValue.build());
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Message messages = 4;</code>
         */
        public Builder addMessages(im.turms.client.model.proto.model.message.Message value) {
            copyOnWrite();
            instance.addMessages(value);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Message messages = 4;</code>
         */
        public Builder addMessages(
                int index,
                im.turms.client.model.proto.model.message.Message value) {
            copyOnWrite();
            instance.addMessages(index, value);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Message messages = 4;</code>
         */
        public Builder addMessages(
                im.turms.client.model.proto.model.message.Message.Builder builderForValue) {
            copyOnWrite();
            instance.addMessages(builderForValue.build());
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Message messages = 4;</code>
         */
        public Builder addMessages(
                int index,
                im.turms.client.model.proto.model.message.Message.Builder builderForValue) {
            copyOnWrite();
            instance.addMessages(index, builderForValue.build());
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Message messages = 4;</code>
         */
        public Builder addAllMessages(
                java.lang.Iterable<? extends im.turms.client.model.proto.model.message.Message> values) {
            copyOnWrite();
            instance.addAllMessages(values);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Message messages = 4;</code>
         */
        public Builder clearMessages() {
            copyOnWrite();
            instance.clearMessages();
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Message messages = 4;</code>
         */
        public Builder removeMessages(int index) {
            copyOnWrite();
            instance.removeMessages(index);
            return this;
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.MessagesWithTotal)
    }

    @java.lang.Override
    @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
    protected final java.lang.Object dynamicMethod(
            com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
            java.lang.Object arg0,
            java.lang.Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE: {
                return new im.turms.client.model.proto.model.message.MessagesWithTotal();
            }
            case NEW_BUILDER: {
                return new Builder();
            }
            case BUILD_MESSAGE_INFO: {
                java.lang.Object[] objects = new java.lang.Object[]{"total_",
                        "isGroupMessage_",
                        "fromId_",
                        "messages_",
                        im.turms.client.model.proto.model.message.Message.class,};
                java.lang.String info =
                        "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0001\u0000\u0001\u0004\u0002\u0007"
                                + "\u0003\u0002\u0004\u001b";
                return newMessageInfo(DEFAULT_INSTANCE, info, objects);
            }
            // fall through
            case GET_DEFAULT_INSTANCE: {
                return DEFAULT_INSTANCE;
            }
            case GET_PARSER: {
                com.google.protobuf.Parser<im.turms.client.model.proto.model.message.MessagesWithTotal> parser =
                        PARSER;
                if (parser == null) {
                    synchronized (im.turms.client.model.proto.model.message.MessagesWithTotal.class) {
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

    // @@protoc_insertion_point(class_scope:im.turms.proto.MessagesWithTotal)
    private static final im.turms.client.model.proto.model.message.MessagesWithTotal DEFAULT_INSTANCE;

    static {
        MessagesWithTotal defaultInstance = new MessagesWithTotal();
        // New instances are implicitly immutable so no need to make
        // immutable.
        DEFAULT_INSTANCE = defaultInstance;
        com.google.protobuf.GeneratedMessageLite.registerDefaultInstance(MessagesWithTotal.class,
                defaultInstance);
    }

    public static im.turms.client.model.proto.model.message.MessagesWithTotal getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static volatile com.google.protobuf.Parser<MessagesWithTotal> PARSER;

    public static com.google.protobuf.Parser<MessagesWithTotal> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}