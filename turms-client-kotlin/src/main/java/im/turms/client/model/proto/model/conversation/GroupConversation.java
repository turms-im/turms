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
 * Protobuf type {@code im.turms.proto.GroupConversation}
 */
public final class GroupConversation extends
        com.google.protobuf.GeneratedMessageLite<GroupConversation, GroupConversation.Builder>
        implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.GroupConversation)
        GroupConversationOrBuilder {
    private GroupConversation() {
    }

    public static final int GROUP_ID_FIELD_NUMBER = 1;
    private long groupId_;

    /**
     * <code>int64 group_id = 1;</code>
     *
     * @return The groupId.
     */
    @java.lang.Override
    public long getGroupId() {
        return groupId_;
    }

    /**
     * <code>int64 group_id = 1;</code>
     *
     * @param value The groupId to set.
     */
    private void setGroupId(long value) {

        groupId_ = value;
    }

    /**
     * <code>int64 group_id = 1;</code>
     */
    private void clearGroupId() {

        groupId_ = 0L;
    }

    public static final int MEMBER_ID_TO_READ_DATE_FIELD_NUMBER = 2;

    private static final class MemberIdToReadDateDefaultEntryHolder {
        static final com.google.protobuf.MapEntryLite<java.lang.Long, java.lang.Long> defaultEntry =
                com.google.protobuf.MapEntryLite.<java.lang.Long, java.lang.Long>newDefaultInstance(
                        com.google.protobuf.WireFormat.FieldType.INT64,
                        0L,
                        com.google.protobuf.WireFormat.FieldType.INT64,
                        0L);
    }

    private com.google.protobuf.MapFieldLite<java.lang.Long, java.lang.Long> memberIdToReadDate_ =
            com.google.protobuf.MapFieldLite.emptyMapField();

    private com.google.protobuf.MapFieldLite<java.lang.Long, java.lang.Long> internalGetMemberIdToReadDate() {
        return memberIdToReadDate_;
    }

    private com.google.protobuf.MapFieldLite<java.lang.Long, java.lang.Long> internalGetMutableMemberIdToReadDate() {
        if (!memberIdToReadDate_.isMutable()) {
            memberIdToReadDate_ = memberIdToReadDate_.mutableCopy();
        }
        return memberIdToReadDate_;
    }

    @java.lang.Override

    public int getMemberIdToReadDateCount() {
        return internalGetMemberIdToReadDate().size();
    }

    /**
     * <code>map&lt;int64, int64&gt; member_id_to_read_date = 2;</code>
     */
    @java.lang.Override

    public boolean containsMemberIdToReadDate(long key) {

        return internalGetMemberIdToReadDate().containsKey(key);
    }

    /**
     * Use {@link #getMemberIdToReadDateMap()} instead.
     */
    @java.lang.Override
    @java.lang.Deprecated
    public java.util.Map<java.lang.Long, java.lang.Long> getMemberIdToReadDate() {
        return getMemberIdToReadDateMap();
    }

    /**
     * <code>map&lt;int64, int64&gt; member_id_to_read_date = 2;</code>
     */
    @java.lang.Override

    public java.util.Map<java.lang.Long, java.lang.Long> getMemberIdToReadDateMap() {
        return java.util.Collections.unmodifiableMap(internalGetMemberIdToReadDate());
    }

    /**
     * <code>map&lt;int64, int64&gt; member_id_to_read_date = 2;</code>
     */
    @java.lang.Override

    public long getMemberIdToReadDateOrDefault(long key, long defaultValue) {

        java.util.Map<java.lang.Long, java.lang.Long> map = internalGetMemberIdToReadDate();
        return map.containsKey(key)
                ? map.get(key)
                : defaultValue;
    }

    /**
     * <code>map&lt;int64, int64&gt; member_id_to_read_date = 2;</code>
     */
    @java.lang.Override

    public long getMemberIdToReadDateOrThrow(long key) {

        java.util.Map<java.lang.Long, java.lang.Long> map = internalGetMemberIdToReadDate();
        if (!map.containsKey(key)) {
            throw new java.lang.IllegalArgumentException();
        }
        return map.get(key);
    }

    /**
     * <code>map&lt;int64, int64&gt; member_id_to_read_date = 2;</code>
     */
    private java.util.Map<java.lang.Long, java.lang.Long> getMutableMemberIdToReadDateMap() {
        return internalGetMutableMemberIdToReadDate();
    }

    public static im.turms.client.model.proto.model.conversation.GroupConversation parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.model.conversation.GroupConversation parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.conversation.GroupConversation parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.model.conversation.GroupConversation parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.conversation.GroupConversation parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.model.conversation.GroupConversation parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.conversation.GroupConversation parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.model.conversation.GroupConversation parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.conversation.GroupConversation parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.model.conversation.GroupConversation parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.conversation.GroupConversation parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.model.conversation.GroupConversation parseFrom(
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
            im.turms.client.model.proto.model.conversation.GroupConversation prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /**
     * Protobuf type {@code im.turms.proto.GroupConversation}
     */
    public static final class Builder extends
            com.google.protobuf.GeneratedMessageLite.Builder<im.turms.client.model.proto.model.conversation.GroupConversation, Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.GroupConversation)
            im.turms.client.model.proto.model.conversation.GroupConversationOrBuilder {
        // Construct using
        // im.turms.client.model.proto.model.conversation.GroupConversation.newBuilder()
        private Builder() {
            super(DEFAULT_INSTANCE);
        }

        /**
         * <code>int64 group_id = 1;</code>
         *
         * @return The groupId.
         */
        @java.lang.Override
        public long getGroupId() {
            return instance.getGroupId();
        }

        /**
         * <code>int64 group_id = 1;</code>
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
         * <code>int64 group_id = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearGroupId() {
            copyOnWrite();
            instance.clearGroupId();
            return this;
        }

        @java.lang.Override

        public int getMemberIdToReadDateCount() {
            return instance.getMemberIdToReadDateMap()
                    .size();
        }

        /**
         * <code>map&lt;int64, int64&gt; member_id_to_read_date = 2;</code>
         */
        @java.lang.Override

        public boolean containsMemberIdToReadDate(long key) {

            return instance.getMemberIdToReadDateMap()
                    .containsKey(key);
        }

        public Builder clearMemberIdToReadDate() {
            copyOnWrite();
            instance.getMutableMemberIdToReadDateMap()
                    .clear();
            return this;
        }

        /**
         * <code>map&lt;int64, int64&gt; member_id_to_read_date = 2;</code>
         */

        public Builder removeMemberIdToReadDate(long key) {

            copyOnWrite();
            instance.getMutableMemberIdToReadDateMap()
                    .remove(key);
            return this;
        }

        /**
         * Use {@link #getMemberIdToReadDateMap()} instead.
         */
        @java.lang.Override
        @java.lang.Deprecated
        public java.util.Map<java.lang.Long, java.lang.Long> getMemberIdToReadDate() {
            return getMemberIdToReadDateMap();
        }

        /**
         * <code>map&lt;int64, int64&gt; member_id_to_read_date = 2;</code>
         */
        @java.lang.Override
        public java.util.Map<java.lang.Long, java.lang.Long> getMemberIdToReadDateMap() {
            return java.util.Collections.unmodifiableMap(instance.getMemberIdToReadDateMap());
        }

        /**
         * <code>map&lt;int64, int64&gt; member_id_to_read_date = 2;</code>
         */
        @java.lang.Override

        public long getMemberIdToReadDateOrDefault(long key, long defaultValue) {

            java.util.Map<java.lang.Long, java.lang.Long> map = instance.getMemberIdToReadDateMap();
            return map.containsKey(key)
                    ? map.get(key)
                    : defaultValue;
        }

        /**
         * <code>map&lt;int64, int64&gt; member_id_to_read_date = 2;</code>
         */
        @java.lang.Override

        public long getMemberIdToReadDateOrThrow(long key) {

            java.util.Map<java.lang.Long, java.lang.Long> map = instance.getMemberIdToReadDateMap();
            if (!map.containsKey(key)) {
                throw new java.lang.IllegalArgumentException();
            }
            return map.get(key);
        }

        /**
         * <code>map&lt;int64, int64&gt; member_id_to_read_date = 2;</code>
         */
        public Builder putMemberIdToReadDate(long key, long value) {

            copyOnWrite();
            instance.getMutableMemberIdToReadDateMap()
                    .put(key, value);
            return this;
        }

        /**
         * <code>map&lt;int64, int64&gt; member_id_to_read_date = 2;</code>
         */
        public Builder putAllMemberIdToReadDate(
                java.util.Map<java.lang.Long, java.lang.Long> values) {
            copyOnWrite();
            instance.getMutableMemberIdToReadDateMap()
                    .putAll(values);
            return this;
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.GroupConversation)
    }

    @java.lang.Override
    @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
    protected final java.lang.Object dynamicMethod(
            com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
            java.lang.Object arg0,
            java.lang.Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE: {
                return new im.turms.client.model.proto.model.conversation.GroupConversation();
            }
            case NEW_BUILDER: {
                return new Builder();
            }
            case BUILD_MESSAGE_INFO: {
                java.lang.Object[] objects = new java.lang.Object[]{"groupId_",
                        "memberIdToReadDate_",
                        MemberIdToReadDateDefaultEntryHolder.defaultEntry,};
                java.lang.String info =
                        "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0001\u0000\u0000\u0001\u0002\u00022";
                return newMessageInfo(DEFAULT_INSTANCE, info, objects);
            }
            // fall through
            case GET_DEFAULT_INSTANCE: {
                return DEFAULT_INSTANCE;
            }
            case GET_PARSER: {
                com.google.protobuf.Parser<im.turms.client.model.proto.model.conversation.GroupConversation> parser =
                        PARSER;
                if (parser == null) {
                    synchronized (im.turms.client.model.proto.model.conversation.GroupConversation.class) {
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

    // @@protoc_insertion_point(class_scope:im.turms.proto.GroupConversation)
    private static final im.turms.client.model.proto.model.conversation.GroupConversation DEFAULT_INSTANCE;

    static {
        GroupConversation defaultInstance = new GroupConversation();
        // New instances are implicitly immutable so no need to make
        // immutable.
        DEFAULT_INSTANCE = defaultInstance;
        com.google.protobuf.GeneratedMessageLite.registerDefaultInstance(GroupConversation.class,
                defaultInstance);
    }

    public static im.turms.client.model.proto.model.conversation.GroupConversation getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static volatile com.google.protobuf.Parser<GroupConversation> PARSER;

    public static com.google.protobuf.Parser<GroupConversation> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}