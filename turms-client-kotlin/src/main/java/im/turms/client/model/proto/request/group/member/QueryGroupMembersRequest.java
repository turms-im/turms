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

package im.turms.client.model.proto.request.group.member;

/**
 * Protobuf type {@code im.turms.proto.QueryGroupMembersRequest}
 */
public final class QueryGroupMembersRequest extends
        com.google.protobuf.GeneratedMessageLite<QueryGroupMembersRequest, QueryGroupMembersRequest.Builder>
        implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.QueryGroupMembersRequest)
        QueryGroupMembersRequestOrBuilder {
    private QueryGroupMembersRequest() {
        memberIds_ = emptyLongList();
    }

    private int bitField0_;
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

    public static final int MEMBER_IDS_FIELD_NUMBER = 3;
    private com.google.protobuf.Internal.LongList memberIds_;

    /**
     * <code>repeated int64 member_ids = 3;</code>
     *
     * @return A list containing the memberIds.
     */
    @java.lang.Override
    public java.util.List<java.lang.Long> getMemberIdsList() {
        return memberIds_;
    }

    /**
     * <code>repeated int64 member_ids = 3;</code>
     *
     * @return The count of memberIds.
     */
    @java.lang.Override
    public int getMemberIdsCount() {
        return memberIds_.size();
    }

    /**
     * <code>repeated int64 member_ids = 3;</code>
     *
     * @param index The index of the element to return.
     * @return The memberIds at the given index.
     */
    @java.lang.Override
    public long getMemberIds(int index) {
        return memberIds_.getLong(index);
    }

    private int memberIdsMemoizedSerializedSize = -1;

    private void ensureMemberIdsIsMutable() {
        com.google.protobuf.Internal.LongList tmp = memberIds_;
        if (!tmp.isModifiable()) {
            memberIds_ = com.google.protobuf.GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /**
     * <code>repeated int64 member_ids = 3;</code>
     *
     * @param index The index to set the value at.
     * @param value The memberIds to set.
     */
    private void setMemberIds(int index, long value) {
        ensureMemberIdsIsMutable();
        memberIds_.setLong(index, value);
    }

    /**
     * <code>repeated int64 member_ids = 3;</code>
     *
     * @param value The memberIds to add.
     */
    private void addMemberIds(long value) {
        ensureMemberIdsIsMutable();
        memberIds_.addLong(value);
    }

    /**
     * <code>repeated int64 member_ids = 3;</code>
     *
     * @param values The memberIds to add.
     */
    private void addAllMemberIds(java.lang.Iterable<? extends java.lang.Long> values) {
        ensureMemberIdsIsMutable();
        com.google.protobuf.AbstractMessageLite.addAll(values, memberIds_);
    }

    /**
     * <code>repeated int64 member_ids = 3;</code>
     */
    private void clearMemberIds() {
        memberIds_ = emptyLongList();
    }

    public static final int WITH_STATUS_FIELD_NUMBER = 4;
    private boolean withStatus_;

    /**
     * <code>optional bool with_status = 4;</code>
     *
     * @return Whether the withStatus field is set.
     */
    @java.lang.Override
    public boolean hasWithStatus() {
        return ((bitField0_ & 0x00000002) != 0);
    }

    /**
     * <code>optional bool with_status = 4;</code>
     *
     * @return The withStatus.
     */
    @java.lang.Override
    public boolean getWithStatus() {
        return withStatus_;
    }

    /**
     * <code>optional bool with_status = 4;</code>
     *
     * @param value The withStatus to set.
     */
    private void setWithStatus(boolean value) {
        bitField0_ |= 0x00000002;
        withStatus_ = value;
    }

    /**
     * <code>optional bool with_status = 4;</code>
     */
    private void clearWithStatus() {
        bitField0_ &= ~0x00000002;
        withStatus_ = false;
    }

    public static im.turms.client.model.proto.request.group.member.QueryGroupMembersRequest parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.group.member.QueryGroupMembersRequest parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.group.member.QueryGroupMembersRequest parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.group.member.QueryGroupMembersRequest parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.group.member.QueryGroupMembersRequest parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.group.member.QueryGroupMembersRequest parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.group.member.QueryGroupMembersRequest parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.group.member.QueryGroupMembersRequest parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.group.member.QueryGroupMembersRequest parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.group.member.QueryGroupMembersRequest parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.group.member.QueryGroupMembersRequest parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.group.member.QueryGroupMembersRequest parseFrom(
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
            im.turms.client.model.proto.request.group.member.QueryGroupMembersRequest prototype) {
        return DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /**
     * Protobuf type {@code im.turms.proto.QueryGroupMembersRequest}
     */
    public static final class Builder extends
            com.google.protobuf.GeneratedMessageLite.Builder<im.turms.client.model.proto.request.group.member.QueryGroupMembersRequest, Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.QueryGroupMembersRequest)
            im.turms.client.model.proto.request.group.member.QueryGroupMembersRequestOrBuilder {
        // Construct using
        // im.turms.client.model.proto.request.group.member.QueryGroupMembersRequest.newBuilder()
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

        /**
         * <code>repeated int64 member_ids = 3;</code>
         *
         * @return A list containing the memberIds.
         */
        @java.lang.Override
        public java.util.List<java.lang.Long> getMemberIdsList() {
            return java.util.Collections.unmodifiableList(instance.getMemberIdsList());
        }

        /**
         * <code>repeated int64 member_ids = 3;</code>
         *
         * @return The count of memberIds.
         */
        @java.lang.Override
        public int getMemberIdsCount() {
            return instance.getMemberIdsCount();
        }

        /**
         * <code>repeated int64 member_ids = 3;</code>
         *
         * @param index The index of the element to return.
         * @return The memberIds at the given index.
         */
        @java.lang.Override
        public long getMemberIds(int index) {
            return instance.getMemberIds(index);
        }

        /**
         * <code>repeated int64 member_ids = 3;</code>
         *
         * @param value The memberIds to set.
         * @return This builder for chaining.
         */
        public Builder setMemberIds(int index, long value) {
            copyOnWrite();
            instance.setMemberIds(index, value);
            return this;
        }

        /**
         * <code>repeated int64 member_ids = 3;</code>
         *
         * @param value The memberIds to add.
         * @return This builder for chaining.
         */
        public Builder addMemberIds(long value) {
            copyOnWrite();
            instance.addMemberIds(value);
            return this;
        }

        /**
         * <code>repeated int64 member_ids = 3;</code>
         *
         * @param values The memberIds to add.
         * @return This builder for chaining.
         */
        public Builder addAllMemberIds(java.lang.Iterable<? extends java.lang.Long> values) {
            copyOnWrite();
            instance.addAllMemberIds(values);
            return this;
        }

        /**
         * <code>repeated int64 member_ids = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearMemberIds() {
            copyOnWrite();
            instance.clearMemberIds();
            return this;
        }

        /**
         * <code>optional bool with_status = 4;</code>
         *
         * @return Whether the withStatus field is set.
         */
        @java.lang.Override
        public boolean hasWithStatus() {
            return instance.hasWithStatus();
        }

        /**
         * <code>optional bool with_status = 4;</code>
         *
         * @return The withStatus.
         */
        @java.lang.Override
        public boolean getWithStatus() {
            return instance.getWithStatus();
        }

        /**
         * <code>optional bool with_status = 4;</code>
         *
         * @param value The withStatus to set.
         * @return This builder for chaining.
         */
        public Builder setWithStatus(boolean value) {
            copyOnWrite();
            instance.setWithStatus(value);
            return this;
        }

        /**
         * <code>optional bool with_status = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearWithStatus() {
            copyOnWrite();
            instance.clearWithStatus();
            return this;
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.QueryGroupMembersRequest)
    }

    @java.lang.Override
    @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
    protected final java.lang.Object dynamicMethod(
            com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
            java.lang.Object arg0,
            java.lang.Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE: {
                return new im.turms.client.model.proto.request.group.member.QueryGroupMembersRequest();
            }
            case NEW_BUILDER: {
                return new Builder();
            }
            case BUILD_MESSAGE_INFO: {
                java.lang.Object[] objects = new java.lang.Object[]{"bitField0_",
                        "groupId_",
                        "lastUpdatedDate_",
                        "memberIds_",
                        "withStatus_",};
                java.lang.String info =
                        "\u0000\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0001\u0000\u0001\u0002\u0002\u1002"
                                + "\u0000\u0003%\u0004\u1007\u0001";
                return newMessageInfo(DEFAULT_INSTANCE, info, objects);
            }
            // fall through
            case GET_DEFAULT_INSTANCE: {
                return DEFAULT_INSTANCE;
            }
            case GET_PARSER: {
                com.google.protobuf.Parser<im.turms.client.model.proto.request.group.member.QueryGroupMembersRequest> parser =
                        PARSER;
                if (parser == null) {
                    synchronized (im.turms.client.model.proto.request.group.member.QueryGroupMembersRequest.class) {
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

    // @@protoc_insertion_point(class_scope:im.turms.proto.QueryGroupMembersRequest)
    private static final im.turms.client.model.proto.request.group.member.QueryGroupMembersRequest DEFAULT_INSTANCE;

    static {
        QueryGroupMembersRequest defaultInstance = new QueryGroupMembersRequest();
        // New instances are implicitly immutable so no need to make
        // immutable.
        DEFAULT_INSTANCE = defaultInstance;
        com.google.protobuf.GeneratedMessageLite
                .registerDefaultInstance(QueryGroupMembersRequest.class, defaultInstance);
    }

    public static im.turms.client.model.proto.request.group.member.QueryGroupMembersRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static volatile com.google.protobuf.Parser<QueryGroupMembersRequest> PARSER;

    public static com.google.protobuf.Parser<QueryGroupMembersRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}