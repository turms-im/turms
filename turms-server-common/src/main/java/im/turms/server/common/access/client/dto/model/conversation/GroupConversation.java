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

package im.turms.server.common.access.client.dto.model.conversation;

/**
 * Protobuf type {@code im.turms.proto.GroupConversation}
 */
public final class GroupConversation extends com.google.protobuf.GeneratedMessageV3 implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.GroupConversation)
        GroupConversationOrBuilder {
    private static final long serialVersionUID = 0L;

    // Use GroupConversation.newBuilder() to construct.
    private GroupConversation(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private GroupConversation() {
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(UnusedPrivateParameter unused) {
        return new GroupConversation();
    }

    public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.server.common.access.client.dto.model.conversation.GroupConversationOuterClass.internal_static_im_turms_proto_GroupConversation_descriptor;
    }

    @SuppressWarnings({"rawtypes"})
    @java.lang.Override
    protected com.google.protobuf.MapField internalGetMapField(int number) {
        return switch (number) {
            case 2 -> internalGetMemberIdToReadDate();
            default -> throw new RuntimeException(
                    "Invalid map field number: "
                            + number);
        };
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.server.common.access.client.dto.model.conversation.GroupConversationOuterClass.internal_static_im_turms_proto_GroupConversation_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.server.common.access.client.dto.model.conversation.GroupConversation.class,
                        im.turms.server.common.access.client.dto.model.conversation.GroupConversation.Builder.class);
    }

    public static final int GROUP_ID_FIELD_NUMBER = 1;
    private long groupId_ = 0L;

    /**
     * <code>int64 group_id = 1;</code>
     *
     * @return The groupId.
     */
    @java.lang.Override
    public long getGroupId() {
        return groupId_;
    }

    public static final int MEMBER_ID_TO_READ_DATE_FIELD_NUMBER = 2;

    private static final class MemberIdToReadDateDefaultEntryHolder {
        static final com.google.protobuf.MapEntry<java.lang.Long, java.lang.Long> defaultEntry =
                com.google.protobuf.MapEntry.<java.lang.Long, java.lang.Long>newDefaultInstance(
                        im.turms.server.common.access.client.dto.model.conversation.GroupConversationOuterClass.internal_static_im_turms_proto_GroupConversation_MemberIdToReadDateEntry_descriptor,
                        com.google.protobuf.WireFormat.FieldType.INT64,
                        0L,
                        com.google.protobuf.WireFormat.FieldType.INT64,
                        0L);
    }

    @SuppressWarnings("serial")
    private com.google.protobuf.MapField<java.lang.Long, java.lang.Long> memberIdToReadDate_;

    private com.google.protobuf.MapField<java.lang.Long, java.lang.Long> internalGetMemberIdToReadDate() {
        if (memberIdToReadDate_ == null) {
            return com.google.protobuf.MapField
                    .emptyMapField(MemberIdToReadDateDefaultEntryHolder.defaultEntry);
        }
        return memberIdToReadDate_;
    }

    public int getMemberIdToReadDateCount() {
        return internalGetMemberIdToReadDate().getMap()
                .size();
    }

    /**
     * <code>map&lt;int64, int64&gt; member_id_to_read_date = 2;</code>
     */
    @java.lang.Override
    public boolean containsMemberIdToReadDate(long key) {

        return internalGetMemberIdToReadDate().getMap()
                .containsKey(key);
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
        return internalGetMemberIdToReadDate().getMap();
    }

    /**
     * <code>map&lt;int64, int64&gt; member_id_to_read_date = 2;</code>
     */
    @java.lang.Override
    public long getMemberIdToReadDateOrDefault(long key, long defaultValue) {

        java.util.Map<java.lang.Long, java.lang.Long> map =
                internalGetMemberIdToReadDate().getMap();
        return map.getOrDefault(key, defaultValue);
    }

    /**
     * <code>map&lt;int64, int64&gt; member_id_to_read_date = 2;</code>
     */
    @java.lang.Override
    public long getMemberIdToReadDateOrThrow(long key) {

        java.util.Map<java.lang.Long, java.lang.Long> map =
                internalGetMemberIdToReadDate().getMap();
        if (!map.containsKey(key)) {
            throw new java.lang.IllegalArgumentException();
        }
        return map.get(key);
    }

    private byte memoizedIsInitialized = -1;

    @java.lang.Override
    public final boolean isInitialized() {
        byte isInitialized = memoizedIsInitialized;
        if (isInitialized == 1) {
            return true;
        }
        if (isInitialized == 0) {
            return false;
        }

        memoizedIsInitialized = 1;
        return true;
    }

    @java.lang.Override
    public void writeTo(com.google.protobuf.CodedOutputStream output) throws java.io.IOException {
        if (groupId_ != 0L) {
            output.writeInt64(1, groupId_);
        }
        com.google.protobuf.GeneratedMessageV3.serializeLongMapTo(output,
                internalGetMemberIdToReadDate(),
                MemberIdToReadDateDefaultEntryHolder.defaultEntry,
                2);
        getUnknownFields().writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
        int size = memoizedSize;
        if (size != -1) {
            return size;
        }

        size = 0;
        if (groupId_ != 0L) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(1, groupId_);
        }
        for (java.util.Map.Entry<java.lang.Long, java.lang.Long> entry : internalGetMemberIdToReadDate()
                .getMap()
                .entrySet()) {
            com.google.protobuf.MapEntry<java.lang.Long, java.lang.Long> memberIdToReadDate__ =
                    MemberIdToReadDateDefaultEntryHolder.defaultEntry.newBuilderForType()
                            .setKey(entry.getKey())
                            .setValue(entry.getValue())
                            .build();
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(2,
                    memberIdToReadDate__);
        }
        size += getUnknownFields().getSerializedSize();
        memoizedSize = size;
        return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GroupConversation other)) {
            return super.equals(obj);
        }

        if (getGroupId() != other.getGroupId()) {
            return false;
        }
        if (!internalGetMemberIdToReadDate().equals(other.internalGetMemberIdToReadDate())) {
            return false;
        }
        return getUnknownFields().equals(other.getUnknownFields());
    }

    @java.lang.Override
    public int hashCode() {
        if (memoizedHashCode != 0) {
            return memoizedHashCode;
        }
        int hash = 41;
        hash = (19 * hash) + getDescriptor().hashCode();
        hash = (37 * hash) + GROUP_ID_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getGroupId());
        if (!internalGetMemberIdToReadDate().getMap()
                .isEmpty()) {
            hash = (37 * hash) + MEMBER_ID_TO_READ_DATE_FIELD_NUMBER;
            hash = (53 * hash) + internalGetMemberIdToReadDate().hashCode();
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.server.common.access.client.dto.model.conversation.GroupConversation parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.conversation.GroupConversation parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.conversation.GroupConversation parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.conversation.GroupConversation parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.conversation.GroupConversation parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.conversation.GroupConversation parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.conversation.GroupConversation parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.conversation.GroupConversation parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.conversation.GroupConversation parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.conversation.GroupConversation parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.conversation.GroupConversation parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.conversation.GroupConversation parseFrom(
            com.google.protobuf.CodedInputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    @java.lang.Override
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(
            im.turms.server.common.access.client.dto.model.conversation.GroupConversation prototype) {
        return DEFAULT_INSTANCE.toBuilder()
                .mergeFrom(prototype);
    }

    @java.lang.Override
    public Builder toBuilder() {
        return this == DEFAULT_INSTANCE
                ? new Builder()
                : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
            com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        return new Builder(parent);
    }

    /**
     * Protobuf type {@code im.turms.proto.GroupConversation}
     */
    public static final class Builder
            extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.GroupConversation)
            im.turms.server.common.access.client.dto.model.conversation.GroupConversationOrBuilder {
        public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.server.common.access.client.dto.model.conversation.GroupConversationOuterClass.internal_static_im_turms_proto_GroupConversation_descriptor;
        }

        @SuppressWarnings({"rawtypes"})
        protected com.google.protobuf.MapField internalGetMapField(int number) {
            return switch (number) {
                case 2 -> internalGetMemberIdToReadDate();
                default -> throw new RuntimeException(
                        "Invalid map field number: "
                                + number);
            };
        }

        @SuppressWarnings({"rawtypes"})
        protected com.google.protobuf.MapField internalGetMutableMapField(int number) {
            return switch (number) {
                case 2 -> internalGetMutableMemberIdToReadDate();
                default -> throw new RuntimeException(
                        "Invalid map field number: "
                                + number);
            };
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.server.common.access.client.dto.model.conversation.GroupConversationOuterClass.internal_static_im_turms_proto_GroupConversation_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.server.common.access.client.dto.model.conversation.GroupConversation.class,
                            im.turms.server.common.access.client.dto.model.conversation.GroupConversation.Builder.class);
        }

        // Construct using
        // im.turms.server.common.access.client.dto.model.conversation.GroupConversation.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            groupId_ = 0L;
            internalGetMutableMemberIdToReadDate().clear();
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.server.common.access.client.dto.model.conversation.GroupConversationOuterClass.internal_static_im_turms_proto_GroupConversation_descriptor;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.conversation.GroupConversation getDefaultInstanceForType() {
            return im.turms.server.common.access.client.dto.model.conversation.GroupConversation
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.conversation.GroupConversation build() {
            im.turms.server.common.access.client.dto.model.conversation.GroupConversation result =
                    buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.conversation.GroupConversation buildPartial() {
            im.turms.server.common.access.client.dto.model.conversation.GroupConversation result =
                    new im.turms.server.common.access.client.dto.model.conversation.GroupConversation(
                            this);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartial0(
                im.turms.server.common.access.client.dto.model.conversation.GroupConversation result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.groupId_ = groupId_;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.memberIdToReadDate_ = internalGetMemberIdToReadDate();
                result.memberIdToReadDate_.makeImmutable();
            }
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.server.common.access.client.dto.model.conversation.GroupConversation) {
                return mergeFrom(
                        (im.turms.server.common.access.client.dto.model.conversation.GroupConversation) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.server.common.access.client.dto.model.conversation.GroupConversation other) {
            if (other == im.turms.server.common.access.client.dto.model.conversation.GroupConversation
                    .getDefaultInstance()) {
                return this;
            }
            if (other.getGroupId() != 0L) {
                setGroupId(other.getGroupId());
            }
            internalGetMutableMemberIdToReadDate().mergeFrom(other.internalGetMemberIdToReadDate());
            bitField0_ |= 0x00000002;
            this.mergeUnknownFields(other.getUnknownFields());
            onChanged();
            return this;
        }

        @java.lang.Override
        public final boolean isInitialized() {
            return true;
        }

        @java.lang.Override
        public Builder mergeFrom(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            if (extensionRegistry == null) {
                throw new java.lang.NullPointerException();
            }
            try {
                boolean done = false;
                while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0 -> done = true;
                        case 8 -> {
                            groupId_ = input.readInt64();
                            bitField0_ |= 0x00000001;
                        } // case 8
                        case 18 -> {
                            com.google.protobuf.MapEntry<Long, Long> memberIdToReadDate__ =
                                    input.readMessage(
                                            MemberIdToReadDateDefaultEntryHolder.defaultEntry
                                                    .getParserForType(),
                                            extensionRegistry);
                            internalGetMutableMemberIdToReadDate().getMutableMap()
                                    .put(memberIdToReadDate__.getKey(),
                                            memberIdToReadDate__.getValue());
                            bitField0_ |= 0x00000002;
                        } // case 18
                        default -> {
                            if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                                done = true; // was an endgroup tag
                            }
                        } // default:
                    } // switch (tag)
                } // while (!done)
            } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                throw e.unwrapIOException();
            } finally {
                onChanged();
            } // finally
            return this;
        }

        private int bitField0_;

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
         * @return This builder for chaining.
         */
        public Builder setGroupId(long value) {

            groupId_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>int64 group_id = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearGroupId() {
            bitField0_ &= ~0x00000001;
            groupId_ = 0L;
            onChanged();
            return this;
        }

        private com.google.protobuf.MapField<java.lang.Long, java.lang.Long> memberIdToReadDate_;

        private com.google.protobuf.MapField<java.lang.Long, java.lang.Long> internalGetMemberIdToReadDate() {
            if (memberIdToReadDate_ == null) {
                return com.google.protobuf.MapField
                        .emptyMapField(MemberIdToReadDateDefaultEntryHolder.defaultEntry);
            }
            return memberIdToReadDate_;
        }

        private com.google.protobuf.MapField<java.lang.Long, java.lang.Long> internalGetMutableMemberIdToReadDate() {
            if (memberIdToReadDate_ == null) {
                memberIdToReadDate_ = com.google.protobuf.MapField
                        .newMapField(MemberIdToReadDateDefaultEntryHolder.defaultEntry);
            }
            if (!memberIdToReadDate_.isMutable()) {
                memberIdToReadDate_ = memberIdToReadDate_.copy();
            }
            bitField0_ |= 0x00000002;
            onChanged();
            return memberIdToReadDate_;
        }

        public int getMemberIdToReadDateCount() {
            return internalGetMemberIdToReadDate().getMap()
                    .size();
        }

        /**
         * <code>map&lt;int64, int64&gt; member_id_to_read_date = 2;</code>
         */
        @java.lang.Override
        public boolean containsMemberIdToReadDate(long key) {

            return internalGetMemberIdToReadDate().getMap()
                    .containsKey(key);
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
            return internalGetMemberIdToReadDate().getMap();
        }

        /**
         * <code>map&lt;int64, int64&gt; member_id_to_read_date = 2;</code>
         */
        @java.lang.Override
        public long getMemberIdToReadDateOrDefault(long key, long defaultValue) {

            java.util.Map<java.lang.Long, java.lang.Long> map =
                    internalGetMemberIdToReadDate().getMap();
            return map.getOrDefault(key, defaultValue);
        }

        /**
         * <code>map&lt;int64, int64&gt; member_id_to_read_date = 2;</code>
         */
        @java.lang.Override
        public long getMemberIdToReadDateOrThrow(long key) {

            java.util.Map<java.lang.Long, java.lang.Long> map =
                    internalGetMemberIdToReadDate().getMap();
            if (!map.containsKey(key)) {
                throw new java.lang.IllegalArgumentException();
            }
            return map.get(key);
        }

        public Builder clearMemberIdToReadDate() {
            bitField0_ &= ~0x00000002;
            internalGetMutableMemberIdToReadDate().getMutableMap()
                    .clear();
            return this;
        }

        /**
         * <code>map&lt;int64, int64&gt; member_id_to_read_date = 2;</code>
         */
        public Builder removeMemberIdToReadDate(long key) {

            internalGetMutableMemberIdToReadDate().getMutableMap()
                    .remove(key);
            return this;
        }

        /**
         * Use alternate mutation accessors instead.
         */
        @java.lang.Deprecated
        public java.util.Map<java.lang.Long, java.lang.Long> getMutableMemberIdToReadDate() {
            bitField0_ |= 0x00000002;
            return internalGetMutableMemberIdToReadDate().getMutableMap();
        }

        /**
         * <code>map&lt;int64, int64&gt; member_id_to_read_date = 2;</code>
         */
        public Builder putMemberIdToReadDate(long key, long value) {

            internalGetMutableMemberIdToReadDate().getMutableMap()
                    .put(key, value);
            bitField0_ |= 0x00000002;
            return this;
        }

        /**
         * <code>map&lt;int64, int64&gt; member_id_to_read_date = 2;</code>
         */
        public Builder putAllMemberIdToReadDate(
                java.util.Map<java.lang.Long, java.lang.Long> values) {
            internalGetMutableMemberIdToReadDate().getMutableMap()
                    .putAll(values);
            bitField0_ |= 0x00000002;
            return this;
        }

        @java.lang.Override
        public final Builder setUnknownFields(
                final com.google.protobuf.UnknownFieldSet unknownFields) {
            return super.setUnknownFields(unknownFields);
        }

        @java.lang.Override
        public final Builder mergeUnknownFields(
                final com.google.protobuf.UnknownFieldSet unknownFields) {
            return super.mergeUnknownFields(unknownFields);
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.GroupConversation)
    }

    // @@protoc_insertion_point(class_scope:im.turms.proto.GroupConversation)
    private static final im.turms.server.common.access.client.dto.model.conversation.GroupConversation DEFAULT_INSTANCE;

    static {
        DEFAULT_INSTANCE =
                new im.turms.server.common.access.client.dto.model.conversation.GroupConversation();
    }

    public static im.turms.server.common.access.client.dto.model.conversation.GroupConversation getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<GroupConversation> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public GroupConversation parsePartialFrom(
                        com.google.protobuf.CodedInputStream input,
                        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                        throws com.google.protobuf.InvalidProtocolBufferException {
                    Builder builder = newBuilder();
                    try {
                        builder.mergeFrom(input, extensionRegistry);
                    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(builder.buildPartial());
                    } catch (com.google.protobuf.UninitializedMessageException e) {
                        throw e.asInvalidProtocolBufferException()
                                .setUnfinishedMessage(builder.buildPartial());
                    } catch (java.io.IOException e) {
                        throw new com.google.protobuf.InvalidProtocolBufferException(e)
                                .setUnfinishedMessage(builder.buildPartial());
                    }
                    return builder.buildPartial();
                }
            };

    public static com.google.protobuf.Parser<GroupConversation> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<GroupConversation> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.conversation.GroupConversation getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}