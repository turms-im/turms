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

package im.turms.client.model.proto.model.user;

/**
 * Protobuf type {@code im.turms.proto.UserRelationship}
 */
public final class UserRelationship
        extends com.google.protobuf.GeneratedMessageLite<UserRelationship, UserRelationship.Builder>
        implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.UserRelationship)
        UserRelationshipOrBuilder {
    private UserRelationship() {
    }

    private int bitField0_;
    public static final int OWNER_ID_FIELD_NUMBER = 1;
    private long ownerId_;

    /**
     * <code>optional int64 owner_id = 1;</code>
     *
     * @return Whether the ownerId field is set.
     */
    @java.lang.Override
    public boolean hasOwnerId() {
        return ((bitField0_ & 0x00000001) != 0);
    }

    /**
     * <code>optional int64 owner_id = 1;</code>
     *
     * @return The ownerId.
     */
    @java.lang.Override
    public long getOwnerId() {
        return ownerId_;
    }

    /**
     * <code>optional int64 owner_id = 1;</code>
     *
     * @param value The ownerId to set.
     */
    private void setOwnerId(long value) {
        bitField0_ |= 0x00000001;
        ownerId_ = value;
    }

    /**
     * <code>optional int64 owner_id = 1;</code>
     */
    private void clearOwnerId() {
        bitField0_ &= ~0x00000001;
        ownerId_ = 0L;
    }

    public static final int RELATED_USER_ID_FIELD_NUMBER = 2;
    private long relatedUserId_;

    /**
     * <code>optional int64 related_user_id = 2;</code>
     *
     * @return Whether the relatedUserId field is set.
     */
    @java.lang.Override
    public boolean hasRelatedUserId() {
        return ((bitField0_ & 0x00000002) != 0);
    }

    /**
     * <code>optional int64 related_user_id = 2;</code>
     *
     * @return The relatedUserId.
     */
    @java.lang.Override
    public long getRelatedUserId() {
        return relatedUserId_;
    }

    /**
     * <code>optional int64 related_user_id = 2;</code>
     *
     * @param value The relatedUserId to set.
     */
    private void setRelatedUserId(long value) {
        bitField0_ |= 0x00000002;
        relatedUserId_ = value;
    }

    /**
     * <code>optional int64 related_user_id = 2;</code>
     */
    private void clearRelatedUserId() {
        bitField0_ &= ~0x00000002;
        relatedUserId_ = 0L;
    }

    public static final int BLOCK_DATE_FIELD_NUMBER = 3;
    private long blockDate_;

    /**
     * <code>optional int64 block_date = 3;</code>
     *
     * @return Whether the blockDate field is set.
     */
    @java.lang.Override
    public boolean hasBlockDate() {
        return ((bitField0_ & 0x00000004) != 0);
    }

    /**
     * <code>optional int64 block_date = 3;</code>
     *
     * @return The blockDate.
     */
    @java.lang.Override
    public long getBlockDate() {
        return blockDate_;
    }

    /**
     * <code>optional int64 block_date = 3;</code>
     *
     * @param value The blockDate to set.
     */
    private void setBlockDate(long value) {
        bitField0_ |= 0x00000004;
        blockDate_ = value;
    }

    /**
     * <code>optional int64 block_date = 3;</code>
     */
    private void clearBlockDate() {
        bitField0_ &= ~0x00000004;
        blockDate_ = 0L;
    }

    public static final int GROUP_INDEX_FIELD_NUMBER = 4;
    private long groupIndex_;

    /**
     * <code>optional int64 group_index = 4;</code>
     *
     * @return Whether the groupIndex field is set.
     */
    @java.lang.Override
    public boolean hasGroupIndex() {
        return ((bitField0_ & 0x00000008) != 0);
    }

    /**
     * <code>optional int64 group_index = 4;</code>
     *
     * @return The groupIndex.
     */
    @java.lang.Override
    public long getGroupIndex() {
        return groupIndex_;
    }

    /**
     * <code>optional int64 group_index = 4;</code>
     *
     * @param value The groupIndex to set.
     */
    private void setGroupIndex(long value) {
        bitField0_ |= 0x00000008;
        groupIndex_ = value;
    }

    /**
     * <code>optional int64 group_index = 4;</code>
     */
    private void clearGroupIndex() {
        bitField0_ &= ~0x00000008;
        groupIndex_ = 0L;
    }

    public static final int ESTABLISHMENT_DATE_FIELD_NUMBER = 5;
    private long establishmentDate_;

    /**
     * <code>optional int64 establishment_date = 5;</code>
     *
     * @return Whether the establishmentDate field is set.
     */
    @java.lang.Override
    public boolean hasEstablishmentDate() {
        return ((bitField0_ & 0x00000010) != 0);
    }

    /**
     * <code>optional int64 establishment_date = 5;</code>
     *
     * @return The establishmentDate.
     */
    @java.lang.Override
    public long getEstablishmentDate() {
        return establishmentDate_;
    }

    /**
     * <code>optional int64 establishment_date = 5;</code>
     *
     * @param value The establishmentDate to set.
     */
    private void setEstablishmentDate(long value) {
        bitField0_ |= 0x00000010;
        establishmentDate_ = value;
    }

    /**
     * <code>optional int64 establishment_date = 5;</code>
     */
    private void clearEstablishmentDate() {
        bitField0_ &= ~0x00000010;
        establishmentDate_ = 0L;
    }

    public static im.turms.client.model.proto.model.user.UserRelationship parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.model.user.UserRelationship parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.user.UserRelationship parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.model.user.UserRelationship parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.user.UserRelationship parseFrom(byte[] data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.model.user.UserRelationship parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.user.UserRelationship parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.model.user.UserRelationship parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.user.UserRelationship parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.model.user.UserRelationship parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.user.UserRelationship parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.model.user.UserRelationship parseFrom(
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
            im.turms.client.model.proto.model.user.UserRelationship prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /**
     * Protobuf type {@code im.turms.proto.UserRelationship}
     */
    public static final class Builder extends
            com.google.protobuf.GeneratedMessageLite.Builder<im.turms.client.model.proto.model.user.UserRelationship, Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.UserRelationship)
            im.turms.client.model.proto.model.user.UserRelationshipOrBuilder {
        // Construct using im.turms.client.model.proto.model.user.UserRelationship.newBuilder()
        private Builder() {
            super(DEFAULT_INSTANCE);
        }

        /**
         * <code>optional int64 owner_id = 1;</code>
         *
         * @return Whether the ownerId field is set.
         */
        @java.lang.Override
        public boolean hasOwnerId() {
            return instance.hasOwnerId();
        }

        /**
         * <code>optional int64 owner_id = 1;</code>
         *
         * @return The ownerId.
         */
        @java.lang.Override
        public long getOwnerId() {
            return instance.getOwnerId();
        }

        /**
         * <code>optional int64 owner_id = 1;</code>
         *
         * @param value The ownerId to set.
         * @return This builder for chaining.
         */
        public Builder setOwnerId(long value) {
            copyOnWrite();
            instance.setOwnerId(value);
            return this;
        }

        /**
         * <code>optional int64 owner_id = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearOwnerId() {
            copyOnWrite();
            instance.clearOwnerId();
            return this;
        }

        /**
         * <code>optional int64 related_user_id = 2;</code>
         *
         * @return Whether the relatedUserId field is set.
         */
        @java.lang.Override
        public boolean hasRelatedUserId() {
            return instance.hasRelatedUserId();
        }

        /**
         * <code>optional int64 related_user_id = 2;</code>
         *
         * @return The relatedUserId.
         */
        @java.lang.Override
        public long getRelatedUserId() {
            return instance.getRelatedUserId();
        }

        /**
         * <code>optional int64 related_user_id = 2;</code>
         *
         * @param value The relatedUserId to set.
         * @return This builder for chaining.
         */
        public Builder setRelatedUserId(long value) {
            copyOnWrite();
            instance.setRelatedUserId(value);
            return this;
        }

        /**
         * <code>optional int64 related_user_id = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearRelatedUserId() {
            copyOnWrite();
            instance.clearRelatedUserId();
            return this;
        }

        /**
         * <code>optional int64 block_date = 3;</code>
         *
         * @return Whether the blockDate field is set.
         */
        @java.lang.Override
        public boolean hasBlockDate() {
            return instance.hasBlockDate();
        }

        /**
         * <code>optional int64 block_date = 3;</code>
         *
         * @return The blockDate.
         */
        @java.lang.Override
        public long getBlockDate() {
            return instance.getBlockDate();
        }

        /**
         * <code>optional int64 block_date = 3;</code>
         *
         * @param value The blockDate to set.
         * @return This builder for chaining.
         */
        public Builder setBlockDate(long value) {
            copyOnWrite();
            instance.setBlockDate(value);
            return this;
        }

        /**
         * <code>optional int64 block_date = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearBlockDate() {
            copyOnWrite();
            instance.clearBlockDate();
            return this;
        }

        /**
         * <code>optional int64 group_index = 4;</code>
         *
         * @return Whether the groupIndex field is set.
         */
        @java.lang.Override
        public boolean hasGroupIndex() {
            return instance.hasGroupIndex();
        }

        /**
         * <code>optional int64 group_index = 4;</code>
         *
         * @return The groupIndex.
         */
        @java.lang.Override
        public long getGroupIndex() {
            return instance.getGroupIndex();
        }

        /**
         * <code>optional int64 group_index = 4;</code>
         *
         * @param value The groupIndex to set.
         * @return This builder for chaining.
         */
        public Builder setGroupIndex(long value) {
            copyOnWrite();
            instance.setGroupIndex(value);
            return this;
        }

        /**
         * <code>optional int64 group_index = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearGroupIndex() {
            copyOnWrite();
            instance.clearGroupIndex();
            return this;
        }

        /**
         * <code>optional int64 establishment_date = 5;</code>
         *
         * @return Whether the establishmentDate field is set.
         */
        @java.lang.Override
        public boolean hasEstablishmentDate() {
            return instance.hasEstablishmentDate();
        }

        /**
         * <code>optional int64 establishment_date = 5;</code>
         *
         * @return The establishmentDate.
         */
        @java.lang.Override
        public long getEstablishmentDate() {
            return instance.getEstablishmentDate();
        }

        /**
         * <code>optional int64 establishment_date = 5;</code>
         *
         * @param value The establishmentDate to set.
         * @return This builder for chaining.
         */
        public Builder setEstablishmentDate(long value) {
            copyOnWrite();
            instance.setEstablishmentDate(value);
            return this;
        }

        /**
         * <code>optional int64 establishment_date = 5;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearEstablishmentDate() {
            copyOnWrite();
            instance.clearEstablishmentDate();
            return this;
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.UserRelationship)
    }

    @java.lang.Override
    @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
    protected final java.lang.Object dynamicMethod(
            com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
            java.lang.Object arg0,
            java.lang.Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE: {
                return new im.turms.client.model.proto.model.user.UserRelationship();
            }
            case NEW_BUILDER: {
                return new Builder();
            }
            case BUILD_MESSAGE_INFO: {
                java.lang.Object[] objects = new java.lang.Object[]{"bitField0_",
                        "ownerId_",
                        "relatedUserId_",
                        "blockDate_",
                        "groupIndex_",
                        "establishmentDate_",};
                java.lang.String info =
                        "\u0000\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001\u1002\u0000\u0002"
                                + "\u1002\u0001\u0003\u1002\u0002\u0004\u1002\u0003\u0005\u1002\u0004";
                return newMessageInfo(DEFAULT_INSTANCE, info, objects);
            }
            // fall through
            case GET_DEFAULT_INSTANCE: {
                return DEFAULT_INSTANCE;
            }
            case GET_PARSER: {
                com.google.protobuf.Parser<im.turms.client.model.proto.model.user.UserRelationship> parser =
                        PARSER;
                if (parser == null) {
                    synchronized (im.turms.client.model.proto.model.user.UserRelationship.class) {
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

    // @@protoc_insertion_point(class_scope:im.turms.proto.UserRelationship)
    private static final im.turms.client.model.proto.model.user.UserRelationship DEFAULT_INSTANCE;

    static {
        UserRelationship defaultInstance = new UserRelationship();
        // New instances are implicitly immutable so no need to make
        // immutable.
        DEFAULT_INSTANCE = defaultInstance;
        com.google.protobuf.GeneratedMessageLite.registerDefaultInstance(UserRelationship.class,
                defaultInstance);
    }

    public static im.turms.client.model.proto.model.user.UserRelationship getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static volatile com.google.protobuf.Parser<UserRelationship> PARSER;

    public static com.google.protobuf.Parser<UserRelationship> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}