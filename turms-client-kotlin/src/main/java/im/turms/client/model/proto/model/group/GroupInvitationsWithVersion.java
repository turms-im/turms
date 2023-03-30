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

package im.turms.client.model.proto.model.group;

/**
 * Protobuf type {@code im.turms.proto.GroupInvitationsWithVersion}
 */
public final class GroupInvitationsWithVersion extends
        com.google.protobuf.GeneratedMessageLite<GroupInvitationsWithVersion, GroupInvitationsWithVersion.Builder>
        implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.GroupInvitationsWithVersion)
        GroupInvitationsWithVersionOrBuilder {
    private GroupInvitationsWithVersion() {
        groupInvitations_ = emptyProtobufList();
    }

    private int bitField0_;
    public static final int GROUP_INVITATIONS_FIELD_NUMBER = 1;
    private com.google.protobuf.Internal.ProtobufList<im.turms.client.model.proto.model.group.GroupInvitation> groupInvitations_;

    /**
     * <code>repeated .im.turms.proto.GroupInvitation group_invitations = 1;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.client.model.proto.model.group.GroupInvitation> getGroupInvitationsList() {
        return groupInvitations_;
    }

    /**
     * <code>repeated .im.turms.proto.GroupInvitation group_invitations = 1;</code>
     */
    public java.util.List<? extends im.turms.client.model.proto.model.group.GroupInvitationOrBuilder> getGroupInvitationsOrBuilderList() {
        return groupInvitations_;
    }

    /**
     * <code>repeated .im.turms.proto.GroupInvitation group_invitations = 1;</code>
     */
    @java.lang.Override
    public int getGroupInvitationsCount() {
        return groupInvitations_.size();
    }

    /**
     * <code>repeated .im.turms.proto.GroupInvitation group_invitations = 1;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.model.group.GroupInvitation getGroupInvitations(int index) {
        return groupInvitations_.get(index);
    }

    /**
     * <code>repeated .im.turms.proto.GroupInvitation group_invitations = 1;</code>
     */
    public im.turms.client.model.proto.model.group.GroupInvitationOrBuilder getGroupInvitationsOrBuilder(
            int index) {
        return groupInvitations_.get(index);
    }

    private void ensureGroupInvitationsIsMutable() {
        com.google.protobuf.Internal.ProtobufList<im.turms.client.model.proto.model.group.GroupInvitation> tmp =
                groupInvitations_;
        if (!tmp.isModifiable()) {
            groupInvitations_ = com.google.protobuf.GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /**
     * <code>repeated .im.turms.proto.GroupInvitation group_invitations = 1;</code>
     */
    private void setGroupInvitations(
            int index,
            im.turms.client.model.proto.model.group.GroupInvitation value) {
        value.getClass();
        ensureGroupInvitationsIsMutable();
        groupInvitations_.set(index, value);
    }

    /**
     * <code>repeated .im.turms.proto.GroupInvitation group_invitations = 1;</code>
     */
    private void addGroupInvitations(
            im.turms.client.model.proto.model.group.GroupInvitation value) {
        value.getClass();
        ensureGroupInvitationsIsMutable();
        groupInvitations_.add(value);
    }

    /**
     * <code>repeated .im.turms.proto.GroupInvitation group_invitations = 1;</code>
     */
    private void addGroupInvitations(
            int index,
            im.turms.client.model.proto.model.group.GroupInvitation value) {
        value.getClass();
        ensureGroupInvitationsIsMutable();
        groupInvitations_.add(index, value);
    }

    /**
     * <code>repeated .im.turms.proto.GroupInvitation group_invitations = 1;</code>
     */
    private void addAllGroupInvitations(
            java.lang.Iterable<? extends im.turms.client.model.proto.model.group.GroupInvitation> values) {
        ensureGroupInvitationsIsMutable();
        com.google.protobuf.AbstractMessageLite.addAll(values, groupInvitations_);
    }

    /**
     * <code>repeated .im.turms.proto.GroupInvitation group_invitations = 1;</code>
     */
    private void clearGroupInvitations() {
        groupInvitations_ = emptyProtobufList();
    }

    /**
     * <code>repeated .im.turms.proto.GroupInvitation group_invitations = 1;</code>
     */
    private void removeGroupInvitations(int index) {
        ensureGroupInvitationsIsMutable();
        groupInvitations_.remove(index);
    }

    public static final int LAST_UPDATED_DATE_FIELD_NUMBER = 2;
    private long lastUpdatedDate_;

    /**
     * <code>optional int64 last_updated_date = 2;</code>
     *
     * @return Whether the lastUpdatedDate field is set.
     */
    @java.lang.Override
    public boolean hasLastUpdatedDate() {
        return ((bitField0_ & 0x00000001) != 0);
    }

    /**
     * <code>optional int64 last_updated_date = 2;</code>
     *
     * @return The lastUpdatedDate.
     */
    @java.lang.Override
    public long getLastUpdatedDate() {
        return lastUpdatedDate_;
    }

    /**
     * <code>optional int64 last_updated_date = 2;</code>
     *
     * @param value The lastUpdatedDate to set.
     */
    private void setLastUpdatedDate(long value) {
        bitField0_ |= 0x00000001;
        lastUpdatedDate_ = value;
    }

    /**
     * <code>optional int64 last_updated_date = 2;</code>
     */
    private void clearLastUpdatedDate() {
        bitField0_ &= ~0x00000001;
        lastUpdatedDate_ = 0L;
    }

    public static im.turms.client.model.proto.model.group.GroupInvitationsWithVersion parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.model.group.GroupInvitationsWithVersion parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.group.GroupInvitationsWithVersion parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.model.group.GroupInvitationsWithVersion parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.group.GroupInvitationsWithVersion parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.model.group.GroupInvitationsWithVersion parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.group.GroupInvitationsWithVersion parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.model.group.GroupInvitationsWithVersion parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.group.GroupInvitationsWithVersion parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.model.group.GroupInvitationsWithVersion parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.group.GroupInvitationsWithVersion parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.model.group.GroupInvitationsWithVersion parseFrom(
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
            im.turms.client.model.proto.model.group.GroupInvitationsWithVersion prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /**
     * Protobuf type {@code im.turms.proto.GroupInvitationsWithVersion}
     */
    public static final class Builder extends
            com.google.protobuf.GeneratedMessageLite.Builder<im.turms.client.model.proto.model.group.GroupInvitationsWithVersion, Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.GroupInvitationsWithVersion)
            im.turms.client.model.proto.model.group.GroupInvitationsWithVersionOrBuilder {
        // Construct using
        // im.turms.client.model.proto.model.group.GroupInvitationsWithVersion.newBuilder()
        private Builder() {
            super(DEFAULT_INSTANCE);
        }

        /**
         * <code>repeated .im.turms.proto.GroupInvitation group_invitations = 1;</code>
         */
        @java.lang.Override
        public java.util.List<im.turms.client.model.proto.model.group.GroupInvitation> getGroupInvitationsList() {
            return java.util.Collections.unmodifiableList(instance.getGroupInvitationsList());
        }

        /**
         * <code>repeated .im.turms.proto.GroupInvitation group_invitations = 1;</code>
         */
        @java.lang.Override
        public int getGroupInvitationsCount() {
            return instance.getGroupInvitationsCount();
        }

        /**
         * <code>repeated .im.turms.proto.GroupInvitation group_invitations = 1;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.model.group.GroupInvitation getGroupInvitations(
                int index) {
            return instance.getGroupInvitations(index);
        }

        /**
         * <code>repeated .im.turms.proto.GroupInvitation group_invitations = 1;</code>
         */
        public Builder setGroupInvitations(
                int index,
                im.turms.client.model.proto.model.group.GroupInvitation value) {
            copyOnWrite();
            instance.setGroupInvitations(index, value);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupInvitation group_invitations = 1;</code>
         */
        public Builder setGroupInvitations(
                int index,
                im.turms.client.model.proto.model.group.GroupInvitation.Builder builderForValue) {
            copyOnWrite();
            instance.setGroupInvitations(index, builderForValue.build());
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupInvitation group_invitations = 1;</code>
         */
        public Builder addGroupInvitations(
                im.turms.client.model.proto.model.group.GroupInvitation value) {
            copyOnWrite();
            instance.addGroupInvitations(value);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupInvitation group_invitations = 1;</code>
         */
        public Builder addGroupInvitations(
                int index,
                im.turms.client.model.proto.model.group.GroupInvitation value) {
            copyOnWrite();
            instance.addGroupInvitations(index, value);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupInvitation group_invitations = 1;</code>
         */
        public Builder addGroupInvitations(
                im.turms.client.model.proto.model.group.GroupInvitation.Builder builderForValue) {
            copyOnWrite();
            instance.addGroupInvitations(builderForValue.build());
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupInvitation group_invitations = 1;</code>
         */
        public Builder addGroupInvitations(
                int index,
                im.turms.client.model.proto.model.group.GroupInvitation.Builder builderForValue) {
            copyOnWrite();
            instance.addGroupInvitations(index, builderForValue.build());
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupInvitation group_invitations = 1;</code>
         */
        public Builder addAllGroupInvitations(
                java.lang.Iterable<? extends im.turms.client.model.proto.model.group.GroupInvitation> values) {
            copyOnWrite();
            instance.addAllGroupInvitations(values);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupInvitation group_invitations = 1;</code>
         */
        public Builder clearGroupInvitations() {
            copyOnWrite();
            instance.clearGroupInvitations();
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.GroupInvitation group_invitations = 1;</code>
         */
        public Builder removeGroupInvitations(int index) {
            copyOnWrite();
            instance.removeGroupInvitations(index);
            return this;
        }

        /**
         * <code>optional int64 last_updated_date = 2;</code>
         *
         * @return Whether the lastUpdatedDate field is set.
         */
        @java.lang.Override
        public boolean hasLastUpdatedDate() {
            return instance.hasLastUpdatedDate();
        }

        /**
         * <code>optional int64 last_updated_date = 2;</code>
         *
         * @return The lastUpdatedDate.
         */
        @java.lang.Override
        public long getLastUpdatedDate() {
            return instance.getLastUpdatedDate();
        }

        /**
         * <code>optional int64 last_updated_date = 2;</code>
         *
         * @param value The lastUpdatedDate to set.
         * @return This builder for chaining.
         */
        public Builder setLastUpdatedDate(long value) {
            copyOnWrite();
            instance.setLastUpdatedDate(value);
            return this;
        }

        /**
         * <code>optional int64 last_updated_date = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearLastUpdatedDate() {
            copyOnWrite();
            instance.clearLastUpdatedDate();
            return this;
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.GroupInvitationsWithVersion)
    }

    @java.lang.Override
    @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
    protected final java.lang.Object dynamicMethod(
            com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
            java.lang.Object arg0,
            java.lang.Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE: {
                return new im.turms.client.model.proto.model.group.GroupInvitationsWithVersion();
            }
            case NEW_BUILDER: {
                return new Builder();
            }
            case BUILD_MESSAGE_INFO: {
                java.lang.Object[] objects = new java.lang.Object[]{"bitField0_",
                        "groupInvitations_",
                        im.turms.client.model.proto.model.group.GroupInvitation.class,
                        "lastUpdatedDate_",};
                java.lang.String info =
                        "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u001b\u0002\u1002"
                                + "\u0000";
                return newMessageInfo(DEFAULT_INSTANCE, info, objects);
            }
            // fall through
            case GET_DEFAULT_INSTANCE: {
                return DEFAULT_INSTANCE;
            }
            case GET_PARSER: {
                com.google.protobuf.Parser<im.turms.client.model.proto.model.group.GroupInvitationsWithVersion> parser =
                        PARSER;
                if (parser == null) {
                    synchronized (im.turms.client.model.proto.model.group.GroupInvitationsWithVersion.class) {
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

    // @@protoc_insertion_point(class_scope:im.turms.proto.GroupInvitationsWithVersion)
    private static final im.turms.client.model.proto.model.group.GroupInvitationsWithVersion DEFAULT_INSTANCE;

    static {
        GroupInvitationsWithVersion defaultInstance = new GroupInvitationsWithVersion();
        // New instances are implicitly immutable so no need to make
        // immutable.
        DEFAULT_INSTANCE = defaultInstance;
        com.google.protobuf.GeneratedMessageLite
                .registerDefaultInstance(GroupInvitationsWithVersion.class, defaultInstance);
    }

    public static im.turms.client.model.proto.model.group.GroupInvitationsWithVersion getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static volatile com.google.protobuf.Parser<GroupInvitationsWithVersion> PARSER;

    public static com.google.protobuf.Parser<GroupInvitationsWithVersion> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}