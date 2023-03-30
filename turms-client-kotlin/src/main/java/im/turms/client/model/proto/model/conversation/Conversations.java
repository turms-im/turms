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

package im.turms.client.model.proto.model.conversation;

/**
 * Protobuf type {@code im.turms.proto.Conversations}
 */
public final class Conversations extends
        com.google.protobuf.GeneratedMessageLite<Conversations, Conversations.Builder> implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.Conversations)
        ConversationsOrBuilder {
    private Conversations() {
        privateConversations_ = emptyProtobufList();
        groupConversations_ = emptyProtobufList();
    }

    public static final int PRIVATE_CONVERSATIONS_FIELD_NUMBER = 1;
    private com.google.protobuf.Internal.ProtobufList<im.turms.client.model.proto.model.conversation.PrivateConversation> privateConversations_;

    /**
     * <code>repeated .im.turms.proto.PrivateConversation private_conversations = 1;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.client.model.proto.model.conversation.PrivateConversation> getPrivateConversationsList() {
        return privateConversations_;
    }

    /**
     * <code>repeated .im.turms.proto.PrivateConversation private_conversations = 1;</code>
     */
    public java.util.List<? extends im.turms.client.model.proto.model.conversation.PrivateConversationOrBuilder> getPrivateConversationsOrBuilderList() {
        return privateConversations_;
    }

    /**
     * <code>repeated .im.turms.proto.PrivateConversation private_conversations = 1;</code>
     */
    @java.lang.Override
    public int getPrivateConversationsCount() {
        return privateConversations_.size();
    }

    /**
     * <code>repeated .im.turms.proto.PrivateConversation private_conversations = 1;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.model.conversation.PrivateConversation getPrivateConversations(
            int index) {
        return privateConversations_.get(index);
    }

    /**
     * <code>repeated .im.turms.proto.PrivateConversation private_conversations = 1;</code>
     */
    public im.turms.client.model.proto.model.conversation.PrivateConversationOrBuilder getPrivateConversationsOrBuilder(
            int index) {
        return privateConversations_.get(index);
    }

    private void ensurePrivateConversationsIsMutable() {
        com.google.protobuf.Internal.ProtobufList<im.turms.client.model.proto.model.conversation.PrivateConversation> tmp =
                privateConversations_;
        if (!tmp.isModifiable()) {
            privateConversations_ = com.google.protobuf.GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /**
     * <code>repeated .im.turms.proto.PrivateConversation private_conversations = 1;</code>
     */
    private void setPrivateConversations(
            int index,
            im.turms.client.model.proto.model.conversation.PrivateConversation value) {
        value.getClass();
        ensurePrivateConversationsIsMutable();
        privateConversations_.set(index, value);
    }

    /**
     * <code>repeated .im.turms.proto.PrivateConversation private_conversations = 1;</code>
     */
    private void addPrivateConversations(
            im.turms.client.model.proto.model.conversation.PrivateConversation value) {
        value.getClass();
        ensurePrivateConversationsIsMutable();
        privateConversations_.add(value);
    }

    /**
     * <code>repeated .im.turms.proto.PrivateConversation private_conversations = 1;</code>
     */
    private void addPrivateConversations(
            int index,
            im.turms.client.model.proto.model.conversation.PrivateConversation value) {
        value.getClass();
        ensurePrivateConversationsIsMutable();
        privateConversations_.add(index, value);
    }

    /**
     * <code>repeated .im.turms.proto.PrivateConversation private_conversations = 1;</code>
     */
    private void addAllPrivateConversations(
            java.lang.Iterable<? extends im.turms.client.model.proto.model.conversation.PrivateConversation> values) {
        ensurePrivateConversationsIsMutable();
        com.google.protobuf.AbstractMessageLite.addAll(values, privateConversations_);
    }

    /**
     * <code>repeated .im.turms.proto.PrivateConversation private_conversations = 1;</code>
     */
    private void clearPrivateConversations() {
        privateConversations_ = emptyProtobufList();
    }

    /**
     * <code>repeated .im.turms.proto.PrivateConversation private_conversations = 1;</code>
     */
    private void removePrivateConversations(int index) {
        ensurePrivateConversationsIsMutable();
        privateConversations_.remove(index);
    }

    public static final int GROUP_CONVERSATIONS_FIELD_NUMBER = 2;
    private com.google.protobuf.Internal.ProtobufList<im.turms.client.model.proto.model.conversation.GroupConversation> groupConversations_;

    /**
     * <code>repeated .im.turms.proto.GroupConversation group_conversations = 2;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.client.model.proto.model.conversation.GroupConversation> getGroupConversationsList() {
        return groupConversations_;
    }

    /**
     * <code>repeated .im.turms.proto.GroupConversation group_conversations = 2;</code>
     */
    public java.util.List<? extends im.turms.client.model.proto.model.conversation.GroupConversationOrBuilder> getGroupConversationsOrBuilderList() {
        return groupConversations_;
    }

    /**
     * <code>repeated .im.turms.proto.GroupConversation group_conversations = 2;</code>
     */
    @java.lang.Override
    public int getGroupConversationsCount() {
        return groupConversations_.size();
    }

    /**
     * <code>repeated .im.turms.proto.GroupConversation group_conversations = 2;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.model.conversation.GroupConversation getGroupConversations(
            int index) {
        return groupConversations_.get(index);
    }

    /**
     * <code>repeated .im.turms.proto.GroupConversation group_conversations = 2;</code>
     */
    public im.turms.client.model.proto.model.conversation.GroupConversationOrBuilder getGroupConversationsOrBuilder(
            int index) {
        return groupConversations_.get(index);
    }

    private void ensureGroupConversationsIsMutable() {
        com.google.protobuf.Internal.ProtobufList<im.turms.client.model.proto.model.conversation.GroupConversation> tmp =
                groupConversations_;
        if (!tmp.isModifiable()) {
            groupConversations_ = com.google.protobuf.GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /**
     * <code>repeated .im.turms.proto.GroupConversation group_conversations = 2;</code>
     */
    private void setGroupConversations(
            int index,
            im.turms.client.model.proto.model.conversation.GroupConversation value) {
        value.getClass();
        ensureGroupConversationsIsMutable();
        groupConversations_.set(index, value);
    }

    /**
     * <code>repeated .im.turms.proto.GroupConversation group_conversations = 2;</code>
     */
    private void addGroupConversations(
            im.turms.client.model.proto.model.conversation.GroupConversation value) {
        value.getClass();
        ensureGroupConversationsIsMutable();
        groupConversations_.add(value);
    }

    /**
     * <code>repeated .im.turms.proto.GroupConversation group_conversations = 2;</code>
     */
    private void addGroupConversations(
            int index,
            im.turms.client.model.proto.model.conversation.GroupConversation value) {
        value.getClass();
        ensureGroupConversationsIsMutable();
        groupConversations_.add(index, value);
    }

    /**
     * <code>repeated .im.turms.proto.GroupConversation group_conversations = 2;</code>
     */
    private void addAllGroupConversations(
            java.lang.Iterable<? extends im.turms.client.model.proto.model.conversation.GroupConversation> values) {
        ensureGroupConversationsIsMutable();
        com.google.protobuf.AbstractMessageLite.addAll(values, groupConversations_);
    }

    /**
     * <code>repeated .im.turms.proto.GroupConversation group_conversations = 2;</code>
     */
    private void clearGroupConversations() {
        groupConversations_ = emptyProtobufList();
    }

    /**
     * <code>repeated .im.turms.proto.GroupConversation group_conversations = 2;</code>
     */
    private void removeGroupConversations(int index) {
        ensureGroupConversationsIsMutable();
        groupConversations_.remove(index);
    }

    public static im.turms.client.model.proto.model.conversation.Conversations parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.model.conversation.Conversations parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.conversation.Conversations parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.model.conversation.Conversations parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.conversation.Conversations parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.model.conversation.Conversations parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.conversation.Conversations parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.model.conversation.Conversations parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.conversation.Conversations parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.model.conversation.Conversations parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.conversation.Conversations parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.model.conversation.Conversations parseFrom(
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
            im.turms.client.model.proto.model.conversation.Conversations prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /**
     * Protobuf type {@code im.turms.proto.Conversations}
     */
    public static final class Builder extends
            com.google.protobuf.GeneratedMessageLite.Builder<im.turms.client.model.proto.model.conversation.Conversations, Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.Conversations)
            im.turms.client.model.proto.model.conversation.ConversationsOrBuilder {
        // Construct using im.turms.client.model.proto.model.conversation.Conversations.newBuilder()
        private Builder() {
            super(DEFAULT_INSTANCE);
        }

        /**
         * <code>repeated .im.turms.proto.PrivateConversation private_conversations = 1;</code>
         */
        @java.lang.Override
        public java.util.List<im.turms.client.model.proto.model.conversation.PrivateConversation> getPrivateConversationsList() {
            return java.util.Collections.unmodifiableList(instance.getPrivateConversationsList());
        }

        /**
         * <code>repeated .im.turms.proto.PrivateConversation private_conversations = 1;</code>
         */
        @java.lang.Override
        public int getPrivateConversationsCount() {
            return instance.getPrivateConversationsCount();
        }

        /**
         * <code>repeated .im.turms.proto.PrivateConversation private_conversations = 1;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.model.conversation.PrivateConversation getPrivateConversations(
                int index) {
            return instance.getPrivateConversations(index);
        }

        /**
         * <code>repeated .im.turms.proto.PrivateConversation private_conversations = 1;</code>
         */
        public Builder setPrivateConversations(
                int index,
                im.turms.client.model.proto.model.conversation.PrivateConversation value) {
            copyOnWrite();
            instance.setPrivateConversations(index, value);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.PrivateConversation private_conversations = 1;</code>
         */
        public Builder setPrivateConversations(
                int index,
                im.turms.client.model.proto.model.conversation.PrivateConversation.Builder builderForValue) {
            copyOnWrite();
            instance.setPrivateConversations(index, builderForValue.build());
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.PrivateConversation private_conversations = 1;</code>
         */
        public Builder addPrivateConversations(
                im.turms.client.model.proto.model.conversation.PrivateConversation value) {
            copyOnWrite();
            instance.addPrivateConversations(value);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.PrivateConversation private_conversations = 1;</code>
         */
        public Builder addPrivateConversations(
                int index,
                im.turms.client.model.proto.model.conversation.PrivateConversation value) {
            copyOnWrite();
            instance.addPrivateConversations(index, value);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.PrivateConversation private_conversations = 1;</code>
         */
        public Builder addPrivateConversations(
                im.turms.client.model.proto.model.conversation.PrivateConversation.Builder builderForValue) {
            copyOnWrite();
            instance.addPrivateConversations(builderForValue.build());
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.PrivateConversation private_conversations = 1;</code>
         */
        public Builder addPrivateConversations(
                int index,
                im.turms.client.model.proto.model.conversation.PrivateConversation.Builder builderForValue) {
            copyOnWrite();
            instance.addPrivateConversations(index, builderForValue.build());
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.PrivateConversation private_conversations = 1;</code>
         */
        public Builder addAllPrivateConversations(
                java.lang.Iterable<? extends im.turms.client.model.proto.model.conversation.PrivateConversation> values) {
            copyOnWrite();
            instance.addAllPrivateConversations(values);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.PrivateConversation private_conversations = 1;</code>
         */
        public Builder clearPrivateConversations() {
            copyOnWrite();
            instance.clearPrivateConversations();
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.PrivateConversation private_conversations = 1;</code>
         */
        public Builder removePrivateConversations(int index) {
            copyOnWrite();
            instance.removePrivateConversations(index);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupConversation group_conversations = 2;</code>
         */
        @java.lang.Override
        public java.util.List<im.turms.client.model.proto.model.conversation.GroupConversation> getGroupConversationsList() {
            return java.util.Collections.unmodifiableList(instance.getGroupConversationsList());
        }

        /**
         * <code>repeated .im.turms.proto.GroupConversation group_conversations = 2;</code>
         */
        @java.lang.Override
        public int getGroupConversationsCount() {
            return instance.getGroupConversationsCount();
        }

        /**
         * <code>repeated .im.turms.proto.GroupConversation group_conversations = 2;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.model.conversation.GroupConversation getGroupConversations(
                int index) {
            return instance.getGroupConversations(index);
        }

        /**
         * <code>repeated .im.turms.proto.GroupConversation group_conversations = 2;</code>
         */
        public Builder setGroupConversations(
                int index,
                im.turms.client.model.proto.model.conversation.GroupConversation value) {
            copyOnWrite();
            instance.setGroupConversations(index, value);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupConversation group_conversations = 2;</code>
         */
        public Builder setGroupConversations(
                int index,
                im.turms.client.model.proto.model.conversation.GroupConversation.Builder builderForValue) {
            copyOnWrite();
            instance.setGroupConversations(index, builderForValue.build());
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupConversation group_conversations = 2;</code>
         */
        public Builder addGroupConversations(
                im.turms.client.model.proto.model.conversation.GroupConversation value) {
            copyOnWrite();
            instance.addGroupConversations(value);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupConversation group_conversations = 2;</code>
         */
        public Builder addGroupConversations(
                int index,
                im.turms.client.model.proto.model.conversation.GroupConversation value) {
            copyOnWrite();
            instance.addGroupConversations(index, value);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupConversation group_conversations = 2;</code>
         */
        public Builder addGroupConversations(
                im.turms.client.model.proto.model.conversation.GroupConversation.Builder builderForValue) {
            copyOnWrite();
            instance.addGroupConversations(builderForValue.build());
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupConversation group_conversations = 2;</code>
         */
        public Builder addGroupConversations(
                int index,
                im.turms.client.model.proto.model.conversation.GroupConversation.Builder builderForValue) {
            copyOnWrite();
            instance.addGroupConversations(index, builderForValue.build());
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupConversation group_conversations = 2;</code>
         */
        public Builder addAllGroupConversations(
                java.lang.Iterable<? extends im.turms.client.model.proto.model.conversation.GroupConversation> values) {
            copyOnWrite();
            instance.addAllGroupConversations(values);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupConversation group_conversations = 2;</code>
         */
        public Builder clearGroupConversations() {
            copyOnWrite();
            instance.clearGroupConversations();
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupConversation group_conversations = 2;</code>
         */
        public Builder removeGroupConversations(int index) {
            copyOnWrite();
            instance.removeGroupConversations(index);
            return this;
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.Conversations)
    }

    @java.lang.Override
    @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
    protected final java.lang.Object dynamicMethod(
            com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
            java.lang.Object arg0,
            java.lang.Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE: {
                return new im.turms.client.model.proto.model.conversation.Conversations();
            }
            case NEW_BUILDER: {
                return new Builder();
            }
            case BUILD_MESSAGE_INFO: {
                java.lang.Object[] objects = new java.lang.Object[]{"privateConversations_",
                        im.turms.client.model.proto.model.conversation.PrivateConversation.class,
                        "groupConversations_",
                        im.turms.client.model.proto.model.conversation.GroupConversation.class,};
                java.lang.String info =
                        "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0002\u0000\u0001\u001b\u0002\u001b"
                                + "";
                return newMessageInfo(DEFAULT_INSTANCE, info, objects);
            }
            // fall through
            case GET_DEFAULT_INSTANCE: {
                return DEFAULT_INSTANCE;
            }
            case GET_PARSER: {
                com.google.protobuf.Parser<im.turms.client.model.proto.model.conversation.Conversations> parser =
                        PARSER;
                if (parser == null) {
                    synchronized (im.turms.client.model.proto.model.conversation.Conversations.class) {
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

    // @@protoc_insertion_point(class_scope:im.turms.proto.Conversations)
    private static final im.turms.client.model.proto.model.conversation.Conversations DEFAULT_INSTANCE;

    static {
        Conversations defaultInstance = new Conversations();
        // New instances are implicitly immutable so no need to make
        // immutable.
        DEFAULT_INSTANCE = defaultInstance;
        com.google.protobuf.GeneratedMessageLite.registerDefaultInstance(Conversations.class,
                defaultInstance);
    }

    public static im.turms.client.model.proto.model.conversation.Conversations getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static volatile com.google.protobuf.Parser<Conversations> PARSER;

    public static com.google.protobuf.Parser<Conversations> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}