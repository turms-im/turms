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
 * Protobuf type {@code im.turms.proto.MessageReactionGroup}
 */
public final class MessageReactionGroup extends
        com.google.protobuf.GeneratedMessageLite<MessageReactionGroup, MessageReactionGroup.Builder>
        implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.MessageReactionGroup)
        MessageReactionGroupOrBuilder {
    private MessageReactionGroup() {
        userInfos_ = emptyProtobufList();
        customAttributes_ = emptyProtobufList();
    }

    public static final int REACTION_TYPE_FIELD_NUMBER = 1;
    private int reactionType_;

    /**
     * <code>int32 reaction_type = 1;</code>
     *
     * @return The reactionType.
     */
    @java.lang.Override
    public int getReactionType() {
        return reactionType_;
    }

    /**
     * <code>int32 reaction_type = 1;</code>
     *
     * @param value The reactionType to set.
     */
    private void setReactionType(int value) {

        reactionType_ = value;
    }

    /**
     * <code>int32 reaction_type = 1;</code>
     */
    private void clearReactionType() {

        reactionType_ = 0;
    }

    public static final int REACTION_COUNT_FIELD_NUMBER = 2;
    private int reactionCount_;

    /**
     * <code>int32 reaction_count = 2;</code>
     *
     * @return The reactionCount.
     */
    @java.lang.Override
    public int getReactionCount() {
        return reactionCount_;
    }

    /**
     * <code>int32 reaction_count = 2;</code>
     *
     * @param value The reactionCount to set.
     */
    private void setReactionCount(int value) {

        reactionCount_ = value;
    }

    /**
     * <code>int32 reaction_count = 2;</code>
     */
    private void clearReactionCount() {

        reactionCount_ = 0;
    }

    public static final int USER_INFOS_FIELD_NUMBER = 3;
    private com.google.protobuf.Internal.ProtobufList<im.turms.client.model.proto.model.user.UserInfo> userInfos_;

    /**
     * <code>repeated .im.turms.proto.UserInfo user_infos = 3;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.client.model.proto.model.user.UserInfo> getUserInfosList() {
        return userInfos_;
    }

    /**
     * <code>repeated .im.turms.proto.UserInfo user_infos = 3;</code>
     */
    public java.util.List<? extends im.turms.client.model.proto.model.user.UserInfoOrBuilder> getUserInfosOrBuilderList() {
        return userInfos_;
    }

    /**
     * <code>repeated .im.turms.proto.UserInfo user_infos = 3;</code>
     */
    @java.lang.Override
    public int getUserInfosCount() {
        return userInfos_.size();
    }

    /**
     * <code>repeated .im.turms.proto.UserInfo user_infos = 3;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.model.user.UserInfo getUserInfos(int index) {
        return userInfos_.get(index);
    }

    /**
     * <code>repeated .im.turms.proto.UserInfo user_infos = 3;</code>
     */
    public im.turms.client.model.proto.model.user.UserInfoOrBuilder getUserInfosOrBuilder(
            int index) {
        return userInfos_.get(index);
    }

    private void ensureUserInfosIsMutable() {
        com.google.protobuf.Internal.ProtobufList<im.turms.client.model.proto.model.user.UserInfo> tmp =
                userInfos_;
        if (!tmp.isModifiable()) {
            userInfos_ = com.google.protobuf.GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /**
     * <code>repeated .im.turms.proto.UserInfo user_infos = 3;</code>
     */
    private void setUserInfos(int index, im.turms.client.model.proto.model.user.UserInfo value) {
        value.getClass();
        ensureUserInfosIsMutable();
        userInfos_.set(index, value);
    }

    /**
     * <code>repeated .im.turms.proto.UserInfo user_infos = 3;</code>
     */
    private void addUserInfos(im.turms.client.model.proto.model.user.UserInfo value) {
        value.getClass();
        ensureUserInfosIsMutable();
        userInfos_.add(value);
    }

    /**
     * <code>repeated .im.turms.proto.UserInfo user_infos = 3;</code>
     */
    private void addUserInfos(int index, im.turms.client.model.proto.model.user.UserInfo value) {
        value.getClass();
        ensureUserInfosIsMutable();
        userInfos_.add(index, value);
    }

    /**
     * <code>repeated .im.turms.proto.UserInfo user_infos = 3;</code>
     */
    private void addAllUserInfos(
            java.lang.Iterable<? extends im.turms.client.model.proto.model.user.UserInfo> values) {
        ensureUserInfosIsMutable();
        com.google.protobuf.AbstractMessageLite.addAll(values, userInfos_);
    }

    /**
     * <code>repeated .im.turms.proto.UserInfo user_infos = 3;</code>
     */
    private void clearUserInfos() {
        userInfos_ = emptyProtobufList();
    }

    /**
     * <code>repeated .im.turms.proto.UserInfo user_infos = 3;</code>
     */
    private void removeUserInfos(int index) {
        ensureUserInfosIsMutable();
        userInfos_.remove(index);
    }

    public static final int CUSTOM_ATTRIBUTES_FIELD_NUMBER = 15;
    private com.google.protobuf.Internal.ProtobufList<im.turms.client.model.proto.model.common.Value> customAttributes_;

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.client.model.proto.model.common.Value> getCustomAttributesList() {
        return customAttributes_;
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    public java.util.List<? extends im.turms.client.model.proto.model.common.ValueOrBuilder> getCustomAttributesOrBuilderList() {
        return customAttributes_;
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    @java.lang.Override
    public int getCustomAttributesCount() {
        return customAttributes_.size();
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.model.common.Value getCustomAttributes(int index) {
        return customAttributes_.get(index);
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    public im.turms.client.model.proto.model.common.ValueOrBuilder getCustomAttributesOrBuilder(
            int index) {
        return customAttributes_.get(index);
    }

    private void ensureCustomAttributesIsMutable() {
        com.google.protobuf.Internal.ProtobufList<im.turms.client.model.proto.model.common.Value> tmp =
                customAttributes_;
        if (!tmp.isModifiable()) {
            customAttributes_ = com.google.protobuf.GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    private void setCustomAttributes(
            int index,
            im.turms.client.model.proto.model.common.Value value) {
        value.getClass();
        ensureCustomAttributesIsMutable();
        customAttributes_.set(index, value);
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    private void addCustomAttributes(im.turms.client.model.proto.model.common.Value value) {
        value.getClass();
        ensureCustomAttributesIsMutable();
        customAttributes_.add(value);
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    private void addCustomAttributes(
            int index,
            im.turms.client.model.proto.model.common.Value value) {
        value.getClass();
        ensureCustomAttributesIsMutable();
        customAttributes_.add(index, value);
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    private void addAllCustomAttributes(
            java.lang.Iterable<? extends im.turms.client.model.proto.model.common.Value> values) {
        ensureCustomAttributesIsMutable();
        com.google.protobuf.AbstractMessageLite.addAll(values, customAttributes_);
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    private void clearCustomAttributes() {
        customAttributes_ = emptyProtobufList();
    }

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    private void removeCustomAttributes(int index) {
        ensureCustomAttributesIsMutable();
        customAttributes_.remove(index);
    }

    public static im.turms.client.model.proto.model.message.MessageReactionGroup parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.model.message.MessageReactionGroup parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.message.MessageReactionGroup parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.model.message.MessageReactionGroup parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.message.MessageReactionGroup parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.model.message.MessageReactionGroup parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.message.MessageReactionGroup parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.model.message.MessageReactionGroup parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.message.MessageReactionGroup parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.model.message.MessageReactionGroup parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.message.MessageReactionGroup parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.model.message.MessageReactionGroup parseFrom(
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
            im.turms.client.model.proto.model.message.MessageReactionGroup prototype) {
        return DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /**
     * Protobuf type {@code im.turms.proto.MessageReactionGroup}
     */
    public static final class Builder extends
            com.google.protobuf.GeneratedMessageLite.Builder<im.turms.client.model.proto.model.message.MessageReactionGroup, Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.MessageReactionGroup)
            im.turms.client.model.proto.model.message.MessageReactionGroupOrBuilder {
        // Construct using
        // im.turms.client.model.proto.model.message.MessageReactionGroup.newBuilder()
        private Builder() {
            super(DEFAULT_INSTANCE);
        }

        /**
         * <code>int32 reaction_type = 1;</code>
         *
         * @return The reactionType.
         */
        @java.lang.Override
        public int getReactionType() {
            return instance.getReactionType();
        }

        /**
         * <code>int32 reaction_type = 1;</code>
         *
         * @param value The reactionType to set.
         * @return This builder for chaining.
         */
        public Builder setReactionType(int value) {
            copyOnWrite();
            instance.setReactionType(value);
            return this;
        }

        /**
         * <code>int32 reaction_type = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearReactionType() {
            copyOnWrite();
            instance.clearReactionType();
            return this;
        }

        /**
         * <code>int32 reaction_count = 2;</code>
         *
         * @return The reactionCount.
         */
        @java.lang.Override
        public int getReactionCount() {
            return instance.getReactionCount();
        }

        /**
         * <code>int32 reaction_count = 2;</code>
         *
         * @param value The reactionCount to set.
         * @return This builder for chaining.
         */
        public Builder setReactionCount(int value) {
            copyOnWrite();
            instance.setReactionCount(value);
            return this;
        }

        /**
         * <code>int32 reaction_count = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearReactionCount() {
            copyOnWrite();
            instance.clearReactionCount();
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.UserInfo user_infos = 3;</code>
         */
        @java.lang.Override
        public java.util.List<im.turms.client.model.proto.model.user.UserInfo> getUserInfosList() {
            return java.util.Collections.unmodifiableList(instance.getUserInfosList());
        }

        /**
         * <code>repeated .im.turms.proto.UserInfo user_infos = 3;</code>
         */
        @java.lang.Override
        public int getUserInfosCount() {
            return instance.getUserInfosCount();
        }

        /**
         * <code>repeated .im.turms.proto.UserInfo user_infos = 3;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.model.user.UserInfo getUserInfos(int index) {
            return instance.getUserInfos(index);
        }

        /**
         * <code>repeated .im.turms.proto.UserInfo user_infos = 3;</code>
         */
        public Builder setUserInfos(
                int index,
                im.turms.client.model.proto.model.user.UserInfo value) {
            copyOnWrite();
            instance.setUserInfos(index, value);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.UserInfo user_infos = 3;</code>
         */
        public Builder setUserInfos(
                int index,
                im.turms.client.model.proto.model.user.UserInfo.Builder builderForValue) {
            copyOnWrite();
            instance.setUserInfos(index, builderForValue.build());
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.UserInfo user_infos = 3;</code>
         */
        public Builder addUserInfos(im.turms.client.model.proto.model.user.UserInfo value) {
            copyOnWrite();
            instance.addUserInfos(value);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.UserInfo user_infos = 3;</code>
         */
        public Builder addUserInfos(
                int index,
                im.turms.client.model.proto.model.user.UserInfo value) {
            copyOnWrite();
            instance.addUserInfos(index, value);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.UserInfo user_infos = 3;</code>
         */
        public Builder addUserInfos(
                im.turms.client.model.proto.model.user.UserInfo.Builder builderForValue) {
            copyOnWrite();
            instance.addUserInfos(builderForValue.build());
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.UserInfo user_infos = 3;</code>
         */
        public Builder addUserInfos(
                int index,
                im.turms.client.model.proto.model.user.UserInfo.Builder builderForValue) {
            copyOnWrite();
            instance.addUserInfos(index, builderForValue.build());
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.UserInfo user_infos = 3;</code>
         */
        public Builder addAllUserInfos(
                java.lang.Iterable<? extends im.turms.client.model.proto.model.user.UserInfo> values) {
            copyOnWrite();
            instance.addAllUserInfos(values);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.UserInfo user_infos = 3;</code>
         */
        public Builder clearUserInfos() {
            copyOnWrite();
            instance.clearUserInfos();
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.UserInfo user_infos = 3;</code>
         */
        public Builder removeUserInfos(int index) {
            copyOnWrite();
            instance.removeUserInfos(index);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        @java.lang.Override
        public java.util.List<im.turms.client.model.proto.model.common.Value> getCustomAttributesList() {
            return java.util.Collections.unmodifiableList(instance.getCustomAttributesList());
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        @java.lang.Override
        public int getCustomAttributesCount() {
            return instance.getCustomAttributesCount();
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.model.common.Value getCustomAttributes(int index) {
            return instance.getCustomAttributes(index);
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder setCustomAttributes(
                int index,
                im.turms.client.model.proto.model.common.Value value) {
            copyOnWrite();
            instance.setCustomAttributes(index, value);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder setCustomAttributes(
                int index,
                im.turms.client.model.proto.model.common.Value.Builder builderForValue) {
            copyOnWrite();
            instance.setCustomAttributes(index, builderForValue.build());
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder addCustomAttributes(im.turms.client.model.proto.model.common.Value value) {
            copyOnWrite();
            instance.addCustomAttributes(value);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder addCustomAttributes(
                int index,
                im.turms.client.model.proto.model.common.Value value) {
            copyOnWrite();
            instance.addCustomAttributes(index, value);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder addCustomAttributes(
                im.turms.client.model.proto.model.common.Value.Builder builderForValue) {
            copyOnWrite();
            instance.addCustomAttributes(builderForValue.build());
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder addCustomAttributes(
                int index,
                im.turms.client.model.proto.model.common.Value.Builder builderForValue) {
            copyOnWrite();
            instance.addCustomAttributes(index, builderForValue.build());
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder addAllCustomAttributes(
                java.lang.Iterable<? extends im.turms.client.model.proto.model.common.Value> values) {
            copyOnWrite();
            instance.addAllCustomAttributes(values);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder clearCustomAttributes() {
            copyOnWrite();
            instance.clearCustomAttributes();
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
         */
        public Builder removeCustomAttributes(int index) {
            copyOnWrite();
            instance.removeCustomAttributes(index);
            return this;
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.MessageReactionGroup)
    }

    @java.lang.Override
    @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
    protected java.lang.Object dynamicMethod(
            com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
            java.lang.Object arg0,
            java.lang.Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE: {
                return new im.turms.client.model.proto.model.message.MessageReactionGroup();
            }
            case NEW_BUILDER: {
                return new Builder();
            }
            case BUILD_MESSAGE_INFO: {
                java.lang.Object[] objects = new java.lang.Object[]{"reactionType_",
                        "reactionCount_",
                        "userInfos_",
                        im.turms.client.model.proto.model.user.UserInfo.class,
                        "customAttributes_",
                        im.turms.client.model.proto.model.common.Value.class,};
                java.lang.String info =
                        "\u0000\u0004\u0000\u0000\u0001\u000f\u0004\u0000\u0002\u0000\u0001\u0004\u0002\u0004"
                                + "\u0003\u001b\u000f\u001b";
                return newMessageInfo(DEFAULT_INSTANCE, info, objects);
            }
            // fall through
            case GET_DEFAULT_INSTANCE: {
                return DEFAULT_INSTANCE;
            }
            case GET_PARSER: {
                com.google.protobuf.Parser<im.turms.client.model.proto.model.message.MessageReactionGroup> parser =
                        PARSER;
                if (parser == null) {
                    synchronized (im.turms.client.model.proto.model.message.MessageReactionGroup.class) {
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

    // @@protoc_insertion_point(class_scope:im.turms.proto.MessageReactionGroup)
    private static final im.turms.client.model.proto.model.message.MessageReactionGroup DEFAULT_INSTANCE;
    static {
        MessageReactionGroup defaultInstance = new MessageReactionGroup();
        // New instances are implicitly immutable so no need to make
        // immutable.
        DEFAULT_INSTANCE = defaultInstance;
        com.google.protobuf.GeneratedMessageLite.registerDefaultInstance(MessageReactionGroup.class,
                defaultInstance);
    }

    public static im.turms.client.model.proto.model.message.MessageReactionGroup getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static volatile com.google.protobuf.Parser<MessageReactionGroup> PARSER;

    public static com.google.protobuf.Parser<MessageReactionGroup> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}