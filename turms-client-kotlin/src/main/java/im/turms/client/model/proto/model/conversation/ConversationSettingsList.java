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
 * Protobuf type {@code im.turms.proto.ConversationSettingsList}
 */
public final class ConversationSettingsList extends
        com.google.protobuf.GeneratedMessageLite<ConversationSettingsList, ConversationSettingsList.Builder>
        implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.ConversationSettingsList)
        ConversationSettingsListOrBuilder {
    private ConversationSettingsList() {
        conversationSettingsList_ = emptyProtobufList();
    }

    public static final int CONVERSATION_SETTINGS_LIST_FIELD_NUMBER = 1;
    private com.google.protobuf.Internal.ProtobufList<im.turms.client.model.proto.model.conversation.ConversationSettings> conversationSettingsList_;

    /**
     * <code>repeated .im.turms.proto.ConversationSettings conversation_settings_list = 1;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.client.model.proto.model.conversation.ConversationSettings> getConversationSettingsListList() {
        return conversationSettingsList_;
    }

    /**
     * <code>repeated .im.turms.proto.ConversationSettings conversation_settings_list = 1;</code>
     */
    public java.util.List<? extends im.turms.client.model.proto.model.conversation.ConversationSettingsOrBuilder> getConversationSettingsListOrBuilderList() {
        return conversationSettingsList_;
    }

    /**
     * <code>repeated .im.turms.proto.ConversationSettings conversation_settings_list = 1;</code>
     */
    @java.lang.Override
    public int getConversationSettingsListCount() {
        return conversationSettingsList_.size();
    }

    /**
     * <code>repeated .im.turms.proto.ConversationSettings conversation_settings_list = 1;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.model.conversation.ConversationSettings getConversationSettingsList(
            int index) {
        return conversationSettingsList_.get(index);
    }

    /**
     * <code>repeated .im.turms.proto.ConversationSettings conversation_settings_list = 1;</code>
     */
    public im.turms.client.model.proto.model.conversation.ConversationSettingsOrBuilder getConversationSettingsListOrBuilder(
            int index) {
        return conversationSettingsList_.get(index);
    }

    private void ensureConversationSettingsListIsMutable() {
        com.google.protobuf.Internal.ProtobufList<im.turms.client.model.proto.model.conversation.ConversationSettings> tmp =
                conversationSettingsList_;
        if (!tmp.isModifiable()) {
            conversationSettingsList_ = com.google.protobuf.GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /**
     * <code>repeated .im.turms.proto.ConversationSettings conversation_settings_list = 1;</code>
     */
    private void setConversationSettingsList(
            int index,
            im.turms.client.model.proto.model.conversation.ConversationSettings value) {
        value.getClass();
        ensureConversationSettingsListIsMutable();
        conversationSettingsList_.set(index, value);
    }

    /**
     * <code>repeated .im.turms.proto.ConversationSettings conversation_settings_list = 1;</code>
     */
    private void addConversationSettingsList(
            im.turms.client.model.proto.model.conversation.ConversationSettings value) {
        value.getClass();
        ensureConversationSettingsListIsMutable();
        conversationSettingsList_.add(value);
    }

    /**
     * <code>repeated .im.turms.proto.ConversationSettings conversation_settings_list = 1;</code>
     */
    private void addConversationSettingsList(
            int index,
            im.turms.client.model.proto.model.conversation.ConversationSettings value) {
        value.getClass();
        ensureConversationSettingsListIsMutable();
        conversationSettingsList_.add(index, value);
    }

    /**
     * <code>repeated .im.turms.proto.ConversationSettings conversation_settings_list = 1;</code>
     */
    private void addAllConversationSettingsList(
            java.lang.Iterable<? extends im.turms.client.model.proto.model.conversation.ConversationSettings> values) {
        ensureConversationSettingsListIsMutable();
        com.google.protobuf.AbstractMessageLite.addAll(values, conversationSettingsList_);
    }

    /**
     * <code>repeated .im.turms.proto.ConversationSettings conversation_settings_list = 1;</code>
     */
    private void clearConversationSettingsList() {
        conversationSettingsList_ = emptyProtobufList();
    }

    /**
     * <code>repeated .im.turms.proto.ConversationSettings conversation_settings_list = 1;</code>
     */
    private void removeConversationSettingsList(int index) {
        ensureConversationSettingsListIsMutable();
        conversationSettingsList_.remove(index);
    }

    public static im.turms.client.model.proto.model.conversation.ConversationSettingsList parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.model.conversation.ConversationSettingsList parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.conversation.ConversationSettingsList parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.model.conversation.ConversationSettingsList parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.conversation.ConversationSettingsList parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.model.conversation.ConversationSettingsList parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.conversation.ConversationSettingsList parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.model.conversation.ConversationSettingsList parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.conversation.ConversationSettingsList parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.model.conversation.ConversationSettingsList parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.conversation.ConversationSettingsList parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.model.conversation.ConversationSettingsList parseFrom(
            com.google.protobuf.CodedInputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(
            im.turms.client.model.proto.model.conversation.ConversationSettingsList prototype) {
        return DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /**
     * Protobuf type {@code im.turms.proto.ConversationSettingsList}
     */
    public static final class Builder extends
            com.google.protobuf.GeneratedMessageLite.Builder<im.turms.client.model.proto.model.conversation.ConversationSettingsList, Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.ConversationSettingsList)
            im.turms.client.model.proto.model.conversation.ConversationSettingsListOrBuilder {
        // Construct using
        // im.turms.client.model.proto.model.conversation.ConversationSettingsList.newBuilder()
        private Builder() {
            super(DEFAULT_INSTANCE);
        }

        /**
         * <code>repeated .im.turms.proto.ConversationSettings conversation_settings_list = 1;</code>
         */
        @java.lang.Override
        public java.util.List<im.turms.client.model.proto.model.conversation.ConversationSettings> getConversationSettingsListList() {
            return java.util.Collections
                    .unmodifiableList(instance.getConversationSettingsListList());
        }

        /**
         * <code>repeated .im.turms.proto.ConversationSettings conversation_settings_list = 1;</code>
         */
        @java.lang.Override
        public int getConversationSettingsListCount() {
            return instance.getConversationSettingsListCount();
        }

        /**
         * <code>repeated .im.turms.proto.ConversationSettings conversation_settings_list = 1;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.model.conversation.ConversationSettings getConversationSettingsList(
                int index) {
            return instance.getConversationSettingsList(index);
        }

        /**
         * <code>repeated .im.turms.proto.ConversationSettings conversation_settings_list = 1;</code>
         */
        public Builder setConversationSettingsList(
                int index,
                im.turms.client.model.proto.model.conversation.ConversationSettings value) {
            copyOnWrite();
            instance.setConversationSettingsList(index, value);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.ConversationSettings conversation_settings_list = 1;</code>
         */
        public Builder setConversationSettingsList(
                int index,
                im.turms.client.model.proto.model.conversation.ConversationSettings.Builder builderForValue) {
            copyOnWrite();
            instance.setConversationSettingsList(index, builderForValue.build());
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.ConversationSettings conversation_settings_list = 1;</code>
         */
        public Builder addConversationSettingsList(
                im.turms.client.model.proto.model.conversation.ConversationSettings value) {
            copyOnWrite();
            instance.addConversationSettingsList(value);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.ConversationSettings conversation_settings_list = 1;</code>
         */
        public Builder addConversationSettingsList(
                int index,
                im.turms.client.model.proto.model.conversation.ConversationSettings value) {
            copyOnWrite();
            instance.addConversationSettingsList(index, value);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.ConversationSettings conversation_settings_list = 1;</code>
         */
        public Builder addConversationSettingsList(
                im.turms.client.model.proto.model.conversation.ConversationSettings.Builder builderForValue) {
            copyOnWrite();
            instance.addConversationSettingsList(builderForValue.build());
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.ConversationSettings conversation_settings_list = 1;</code>
         */
        public Builder addConversationSettingsList(
                int index,
                im.turms.client.model.proto.model.conversation.ConversationSettings.Builder builderForValue) {
            copyOnWrite();
            instance.addConversationSettingsList(index, builderForValue.build());
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.ConversationSettings conversation_settings_list = 1;</code>
         */
        public Builder addAllConversationSettingsList(
                java.lang.Iterable<? extends im.turms.client.model.proto.model.conversation.ConversationSettings> values) {
            copyOnWrite();
            instance.addAllConversationSettingsList(values);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.ConversationSettings conversation_settings_list = 1;</code>
         */
        public Builder clearConversationSettingsList() {
            copyOnWrite();
            instance.clearConversationSettingsList();
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.ConversationSettings conversation_settings_list = 1;</code>
         */
        public Builder removeConversationSettingsList(int index) {
            copyOnWrite();
            instance.removeConversationSettingsList(index);
            return this;
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.ConversationSettingsList)
    }

    @java.lang.Override
    @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
    protected java.lang.Object dynamicMethod(
            com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
            java.lang.Object arg0,
            java.lang.Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE: {
                return new im.turms.client.model.proto.model.conversation.ConversationSettingsList();
            }
            case NEW_BUILDER: {
                return new Builder();
            }
            case BUILD_MESSAGE_INFO: {
                java.lang.Object[] objects = new java.lang.Object[]{"conversationSettingsList_",
                        im.turms.client.model.proto.model.conversation.ConversationSettings.class,};
                java.lang.String info =
                        "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b";
                return newMessageInfo(DEFAULT_INSTANCE, info, objects);
            }
            // fall through
            case GET_DEFAULT_INSTANCE: {
                return DEFAULT_INSTANCE;
            }
            case GET_PARSER: {
                com.google.protobuf.Parser<im.turms.client.model.proto.model.conversation.ConversationSettingsList> parser =
                        PARSER;
                if (parser == null) {
                    synchronized (im.turms.client.model.proto.model.conversation.ConversationSettingsList.class) {
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

    // @@protoc_insertion_point(class_scope:im.turms.proto.ConversationSettingsList)
    private static final im.turms.client.model.proto.model.conversation.ConversationSettingsList DEFAULT_INSTANCE;
    static {
        ConversationSettingsList defaultInstance = new ConversationSettingsList();
        // New instances are implicitly immutable so no need to make
        // immutable.
        DEFAULT_INSTANCE = defaultInstance;
        com.google.protobuf.GeneratedMessageLite
                .registerDefaultInstance(ConversationSettingsList.class, defaultInstance);
    }

    public static im.turms.client.model.proto.model.conversation.ConversationSettingsList getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static volatile com.google.protobuf.Parser<ConversationSettingsList> PARSER;

    public static com.google.protobuf.Parser<ConversationSettingsList> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}