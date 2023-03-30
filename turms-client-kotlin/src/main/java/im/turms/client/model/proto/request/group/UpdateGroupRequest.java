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

package im.turms.client.model.proto.request.group;

/**
 * Protobuf type {@code im.turms.proto.UpdateGroupRequest}
 */
public final class UpdateGroupRequest extends
        com.google.protobuf.GeneratedMessageLite<UpdateGroupRequest, UpdateGroupRequest.Builder>
        implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.UpdateGroupRequest)
        UpdateGroupRequestOrBuilder {
    private UpdateGroupRequest() {
        name_ = "";
        intro_ = "";
        announcement_ = "";
    }

    private int bitField0_;
    public static final int GROUP_ID_FIELD_NUMBER = 1;
    private long groupId_;

    /**
     * <pre>
     * Query filter
     * </pre>
     * 
     * <code>int64 group_id = 1;</code>
     *
     * @return The groupId.
     */
    @java.lang.Override
    public long getGroupId() {
        return groupId_;
    }

    /**
     * <pre>
     * Query filter
     * </pre>
     * 
     * <code>int64 group_id = 1;</code>
     *
     * @param value The groupId to set.
     */
    private void setGroupId(long value) {

        groupId_ = value;
    }

    /**
     * <pre>
     * Query filter
     * </pre>
     * 
     * <code>int64 group_id = 1;</code>
     */
    private void clearGroupId() {

        groupId_ = 0L;
    }

    public static final int QUIT_AFTER_TRANSFER_FIELD_NUMBER = 2;
    private boolean quitAfterTransfer_;

    /**
     * <pre>
     * Update options
     * </pre>
     * 
     * <code>optional bool quit_after_transfer = 2;</code>
     *
     * @return Whether the quitAfterTransfer field is set.
     */
    @java.lang.Override
    public boolean hasQuitAfterTransfer() {
        return ((bitField0_ & 0x00000001) != 0);
    }

    /**
     * <pre>
     * Update options
     * </pre>
     * 
     * <code>optional bool quit_after_transfer = 2;</code>
     *
     * @return The quitAfterTransfer.
     */
    @java.lang.Override
    public boolean getQuitAfterTransfer() {
        return quitAfterTransfer_;
    }

    /**
     * <pre>
     * Update options
     * </pre>
     * 
     * <code>optional bool quit_after_transfer = 2;</code>
     *
     * @param value The quitAfterTransfer to set.
     */
    private void setQuitAfterTransfer(boolean value) {
        bitField0_ |= 0x00000001;
        quitAfterTransfer_ = value;
    }

    /**
     * <pre>
     * Update options
     * </pre>
     * 
     * <code>optional bool quit_after_transfer = 2;</code>
     */
    private void clearQuitAfterTransfer() {
        bitField0_ &= ~0x00000001;
        quitAfterTransfer_ = false;
    }

    public static final int NAME_FIELD_NUMBER = 3;
    private java.lang.String name_;

    /**
     * <pre>
     * Update
     * </pre>
     * 
     * <code>optional string name = 3;</code>
     *
     * @return Whether the name field is set.
     */
    @java.lang.Override
    public boolean hasName() {
        return ((bitField0_ & 0x00000002) != 0);
    }

    /**
     * <pre>
     * Update
     * </pre>
     * 
     * <code>optional string name = 3;</code>
     *
     * @return The name.
     */
    @java.lang.Override
    public java.lang.String getName() {
        return name_;
    }

    /**
     * <pre>
     * Update
     * </pre>
     * 
     * <code>optional string name = 3;</code>
     *
     * @return The bytes for name.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getNameBytes() {
        return com.google.protobuf.ByteString.copyFromUtf8(name_);
    }

    /**
     * <pre>
     * Update
     * </pre>
     * 
     * <code>optional string name = 3;</code>
     *
     * @param value The name to set.
     */
    private void setName(java.lang.String value) {
        java.lang.Class<?> valueClass = value.getClass();
        bitField0_ |= 0x00000002;
        name_ = value;
    }

    /**
     * <pre>
     * Update
     * </pre>
     * 
     * <code>optional string name = 3;</code>
     */
    private void clearName() {
        bitField0_ &= ~0x00000002;
        name_ = getDefaultInstance().getName();
    }

    /**
     * <pre>
     * Update
     * </pre>
     * 
     * <code>optional string name = 3;</code>
     *
     * @param value The bytes for name to set.
     */
    private void setNameBytes(com.google.protobuf.ByteString value) {
        checkByteStringIsUtf8(value);
        name_ = value.toStringUtf8();
        bitField0_ |= 0x00000002;
    }

    public static final int INTRO_FIELD_NUMBER = 4;
    private java.lang.String intro_;

    /**
     * <code>optional string intro = 4;</code>
     *
     * @return Whether the intro field is set.
     */
    @java.lang.Override
    public boolean hasIntro() {
        return ((bitField0_ & 0x00000004) != 0);
    }

    /**
     * <code>optional string intro = 4;</code>
     *
     * @return The intro.
     */
    @java.lang.Override
    public java.lang.String getIntro() {
        return intro_;
    }

    /**
     * <code>optional string intro = 4;</code>
     *
     * @return The bytes for intro.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getIntroBytes() {
        return com.google.protobuf.ByteString.copyFromUtf8(intro_);
    }

    /**
     * <code>optional string intro = 4;</code>
     *
     * @param value The intro to set.
     */
    private void setIntro(java.lang.String value) {
        java.lang.Class<?> valueClass = value.getClass();
        bitField0_ |= 0x00000004;
        intro_ = value;
    }

    /**
     * <code>optional string intro = 4;</code>
     */
    private void clearIntro() {
        bitField0_ &= ~0x00000004;
        intro_ = getDefaultInstance().getIntro();
    }

    /**
     * <code>optional string intro = 4;</code>
     *
     * @param value The bytes for intro to set.
     */
    private void setIntroBytes(com.google.protobuf.ByteString value) {
        checkByteStringIsUtf8(value);
        intro_ = value.toStringUtf8();
        bitField0_ |= 0x00000004;
    }

    public static final int ANNOUNCEMENT_FIELD_NUMBER = 5;
    private java.lang.String announcement_;

    /**
     * <code>optional string announcement = 5;</code>
     *
     * @return Whether the announcement field is set.
     */
    @java.lang.Override
    public boolean hasAnnouncement() {
        return ((bitField0_ & 0x00000008) != 0);
    }

    /**
     * <code>optional string announcement = 5;</code>
     *
     * @return The announcement.
     */
    @java.lang.Override
    public java.lang.String getAnnouncement() {
        return announcement_;
    }

    /**
     * <code>optional string announcement = 5;</code>
     *
     * @return The bytes for announcement.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getAnnouncementBytes() {
        return com.google.protobuf.ByteString.copyFromUtf8(announcement_);
    }

    /**
     * <code>optional string announcement = 5;</code>
     *
     * @param value The announcement to set.
     */
    private void setAnnouncement(java.lang.String value) {
        java.lang.Class<?> valueClass = value.getClass();
        bitField0_ |= 0x00000008;
        announcement_ = value;
    }

    /**
     * <code>optional string announcement = 5;</code>
     */
    private void clearAnnouncement() {
        bitField0_ &= ~0x00000008;
        announcement_ = getDefaultInstance().getAnnouncement();
    }

    /**
     * <code>optional string announcement = 5;</code>
     *
     * @param value The bytes for announcement to set.
     */
    private void setAnnouncementBytes(com.google.protobuf.ByteString value) {
        checkByteStringIsUtf8(value);
        announcement_ = value.toStringUtf8();
        bitField0_ |= 0x00000008;
    }

    public static final int MIN_SCORE_FIELD_NUMBER = 6;
    private int minScore_;

    /**
     * <code>optional int32 min_score = 6;</code>
     *
     * @return Whether the minScore field is set.
     */
    @java.lang.Override
    public boolean hasMinScore() {
        return ((bitField0_ & 0x00000010) != 0);
    }

    /**
     * <code>optional int32 min_score = 6;</code>
     *
     * @return The minScore.
     */
    @java.lang.Override
    public int getMinScore() {
        return minScore_;
    }

    /**
     * <code>optional int32 min_score = 6;</code>
     *
     * @param value The minScore to set.
     */
    private void setMinScore(int value) {
        bitField0_ |= 0x00000010;
        minScore_ = value;
    }

    /**
     * <code>optional int32 min_score = 6;</code>
     */
    private void clearMinScore() {
        bitField0_ &= ~0x00000010;
        minScore_ = 0;
    }

    public static final int TYPE_ID_FIELD_NUMBER = 7;
    private long typeId_;

    /**
     * <code>optional int64 type_id = 7;</code>
     *
     * @return Whether the typeId field is set.
     */
    @java.lang.Override
    public boolean hasTypeId() {
        return ((bitField0_ & 0x00000020) != 0);
    }

    /**
     * <code>optional int64 type_id = 7;</code>
     *
     * @return The typeId.
     */
    @java.lang.Override
    public long getTypeId() {
        return typeId_;
    }

    /**
     * <code>optional int64 type_id = 7;</code>
     *
     * @param value The typeId to set.
     */
    private void setTypeId(long value) {
        bitField0_ |= 0x00000020;
        typeId_ = value;
    }

    /**
     * <code>optional int64 type_id = 7;</code>
     */
    private void clearTypeId() {
        bitField0_ &= ~0x00000020;
        typeId_ = 0L;
    }

    public static final int MUTE_END_DATE_FIELD_NUMBER = 8;
    private long muteEndDate_;

    /**
     * <code>optional int64 mute_end_date = 8;</code>
     *
     * @return Whether the muteEndDate field is set.
     */
    @java.lang.Override
    public boolean hasMuteEndDate() {
        return ((bitField0_ & 0x00000040) != 0);
    }

    /**
     * <code>optional int64 mute_end_date = 8;</code>
     *
     * @return The muteEndDate.
     */
    @java.lang.Override
    public long getMuteEndDate() {
        return muteEndDate_;
    }

    /**
     * <code>optional int64 mute_end_date = 8;</code>
     *
     * @param value The muteEndDate to set.
     */
    private void setMuteEndDate(long value) {
        bitField0_ |= 0x00000040;
        muteEndDate_ = value;
    }

    /**
     * <code>optional int64 mute_end_date = 8;</code>
     */
    private void clearMuteEndDate() {
        bitField0_ &= ~0x00000040;
        muteEndDate_ = 0L;
    }

    public static final int SUCCESSOR_ID_FIELD_NUMBER = 9;
    private long successorId_;

    /**
     * <code>optional int64 successor_id = 9;</code>
     *
     * @return Whether the successorId field is set.
     */
    @java.lang.Override
    public boolean hasSuccessorId() {
        return ((bitField0_ & 0x00000080) != 0);
    }

    /**
     * <code>optional int64 successor_id = 9;</code>
     *
     * @return The successorId.
     */
    @java.lang.Override
    public long getSuccessorId() {
        return successorId_;
    }

    /**
     * <code>optional int64 successor_id = 9;</code>
     *
     * @param value The successorId to set.
     */
    private void setSuccessorId(long value) {
        bitField0_ |= 0x00000080;
        successorId_ = value;
    }

    /**
     * <code>optional int64 successor_id = 9;</code>
     */
    private void clearSuccessorId() {
        bitField0_ &= ~0x00000080;
        successorId_ = 0L;
    }

    public static im.turms.client.model.proto.request.group.UpdateGroupRequest parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.group.UpdateGroupRequest parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.group.UpdateGroupRequest parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.group.UpdateGroupRequest parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.group.UpdateGroupRequest parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.request.group.UpdateGroupRequest parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.group.UpdateGroupRequest parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.group.UpdateGroupRequest parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.group.UpdateGroupRequest parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.group.UpdateGroupRequest parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.request.group.UpdateGroupRequest parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.request.group.UpdateGroupRequest parseFrom(
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
            im.turms.client.model.proto.request.group.UpdateGroupRequest prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /**
     * Protobuf type {@code im.turms.proto.UpdateGroupRequest}
     */
    public static final class Builder extends
            com.google.protobuf.GeneratedMessageLite.Builder<im.turms.client.model.proto.request.group.UpdateGroupRequest, Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.UpdateGroupRequest)
            im.turms.client.model.proto.request.group.UpdateGroupRequestOrBuilder {
        // Construct using im.turms.client.model.proto.request.group.UpdateGroupRequest.newBuilder()
        private Builder() {
            super(DEFAULT_INSTANCE);
        }

        /**
         * <pre>
         * Query filter
         * </pre>
         * 
         * <code>int64 group_id = 1;</code>
         *
         * @return The groupId.
         */
        @java.lang.Override
        public long getGroupId() {
            return instance.getGroupId();
        }

        /**
         * <pre>
         * Query filter
         * </pre>
         * 
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
         * <pre>
         * Query filter
         * </pre>
         * 
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
         * <pre>
         * Update options
         * </pre>
         * 
         * <code>optional bool quit_after_transfer = 2;</code>
         *
         * @return Whether the quitAfterTransfer field is set.
         */
        @java.lang.Override
        public boolean hasQuitAfterTransfer() {
            return instance.hasQuitAfterTransfer();
        }

        /**
         * <pre>
         * Update options
         * </pre>
         * 
         * <code>optional bool quit_after_transfer = 2;</code>
         *
         * @return The quitAfterTransfer.
         */
        @java.lang.Override
        public boolean getQuitAfterTransfer() {
            return instance.getQuitAfterTransfer();
        }

        /**
         * <pre>
         * Update options
         * </pre>
         * 
         * <code>optional bool quit_after_transfer = 2;</code>
         *
         * @param value The quitAfterTransfer to set.
         * @return This builder for chaining.
         */
        public Builder setQuitAfterTransfer(boolean value) {
            copyOnWrite();
            instance.setQuitAfterTransfer(value);
            return this;
        }

        /**
         * <pre>
         * Update options
         * </pre>
         * 
         * <code>optional bool quit_after_transfer = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearQuitAfterTransfer() {
            copyOnWrite();
            instance.clearQuitAfterTransfer();
            return this;
        }

        /**
         * <pre>
         * Update
         * </pre>
         * 
         * <code>optional string name = 3;</code>
         *
         * @return Whether the name field is set.
         */
        @java.lang.Override
        public boolean hasName() {
            return instance.hasName();
        }

        /**
         * <pre>
         * Update
         * </pre>
         * 
         * <code>optional string name = 3;</code>
         *
         * @return The name.
         */
        @java.lang.Override
        public java.lang.String getName() {
            return instance.getName();
        }

        /**
         * <pre>
         * Update
         * </pre>
         * 
         * <code>optional string name = 3;</code>
         *
         * @return The bytes for name.
         */
        @java.lang.Override
        public com.google.protobuf.ByteString getNameBytes() {
            return instance.getNameBytes();
        }

        /**
         * <pre>
         * Update
         * </pre>
         * 
         * <code>optional string name = 3;</code>
         *
         * @param value The name to set.
         * @return This builder for chaining.
         */
        public Builder setName(java.lang.String value) {
            copyOnWrite();
            instance.setName(value);
            return this;
        }

        /**
         * <pre>
         * Update
         * </pre>
         * 
         * <code>optional string name = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearName() {
            copyOnWrite();
            instance.clearName();
            return this;
        }

        /**
         * <pre>
         * Update
         * </pre>
         * 
         * <code>optional string name = 3;</code>
         *
         * @param value The bytes for name to set.
         * @return This builder for chaining.
         */
        public Builder setNameBytes(com.google.protobuf.ByteString value) {
            copyOnWrite();
            instance.setNameBytes(value);
            return this;
        }

        /**
         * <code>optional string intro = 4;</code>
         *
         * @return Whether the intro field is set.
         */
        @java.lang.Override
        public boolean hasIntro() {
            return instance.hasIntro();
        }

        /**
         * <code>optional string intro = 4;</code>
         *
         * @return The intro.
         */
        @java.lang.Override
        public java.lang.String getIntro() {
            return instance.getIntro();
        }

        /**
         * <code>optional string intro = 4;</code>
         *
         * @return The bytes for intro.
         */
        @java.lang.Override
        public com.google.protobuf.ByteString getIntroBytes() {
            return instance.getIntroBytes();
        }

        /**
         * <code>optional string intro = 4;</code>
         *
         * @param value The intro to set.
         * @return This builder for chaining.
         */
        public Builder setIntro(java.lang.String value) {
            copyOnWrite();
            instance.setIntro(value);
            return this;
        }

        /**
         * <code>optional string intro = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearIntro() {
            copyOnWrite();
            instance.clearIntro();
            return this;
        }

        /**
         * <code>optional string intro = 4;</code>
         *
         * @param value The bytes for intro to set.
         * @return This builder for chaining.
         */
        public Builder setIntroBytes(com.google.protobuf.ByteString value) {
            copyOnWrite();
            instance.setIntroBytes(value);
            return this;
        }

        /**
         * <code>optional string announcement = 5;</code>
         *
         * @return Whether the announcement field is set.
         */
        @java.lang.Override
        public boolean hasAnnouncement() {
            return instance.hasAnnouncement();
        }

        /**
         * <code>optional string announcement = 5;</code>
         *
         * @return The announcement.
         */
        @java.lang.Override
        public java.lang.String getAnnouncement() {
            return instance.getAnnouncement();
        }

        /**
         * <code>optional string announcement = 5;</code>
         *
         * @return The bytes for announcement.
         */
        @java.lang.Override
        public com.google.protobuf.ByteString getAnnouncementBytes() {
            return instance.getAnnouncementBytes();
        }

        /**
         * <code>optional string announcement = 5;</code>
         *
         * @param value The announcement to set.
         * @return This builder for chaining.
         */
        public Builder setAnnouncement(java.lang.String value) {
            copyOnWrite();
            instance.setAnnouncement(value);
            return this;
        }

        /**
         * <code>optional string announcement = 5;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearAnnouncement() {
            copyOnWrite();
            instance.clearAnnouncement();
            return this;
        }

        /**
         * <code>optional string announcement = 5;</code>
         *
         * @param value The bytes for announcement to set.
         * @return This builder for chaining.
         */
        public Builder setAnnouncementBytes(com.google.protobuf.ByteString value) {
            copyOnWrite();
            instance.setAnnouncementBytes(value);
            return this;
        }

        /**
         * <code>optional int32 min_score = 6;</code>
         *
         * @return Whether the minScore field is set.
         */
        @java.lang.Override
        public boolean hasMinScore() {
            return instance.hasMinScore();
        }

        /**
         * <code>optional int32 min_score = 6;</code>
         *
         * @return The minScore.
         */
        @java.lang.Override
        public int getMinScore() {
            return instance.getMinScore();
        }

        /**
         * <code>optional int32 min_score = 6;</code>
         *
         * @param value The minScore to set.
         * @return This builder for chaining.
         */
        public Builder setMinScore(int value) {
            copyOnWrite();
            instance.setMinScore(value);
            return this;
        }

        /**
         * <code>optional int32 min_score = 6;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearMinScore() {
            copyOnWrite();
            instance.clearMinScore();
            return this;
        }

        /**
         * <code>optional int64 type_id = 7;</code>
         *
         * @return Whether the typeId field is set.
         */
        @java.lang.Override
        public boolean hasTypeId() {
            return instance.hasTypeId();
        }

        /**
         * <code>optional int64 type_id = 7;</code>
         *
         * @return The typeId.
         */
        @java.lang.Override
        public long getTypeId() {
            return instance.getTypeId();
        }

        /**
         * <code>optional int64 type_id = 7;</code>
         *
         * @param value The typeId to set.
         * @return This builder for chaining.
         */
        public Builder setTypeId(long value) {
            copyOnWrite();
            instance.setTypeId(value);
            return this;
        }

        /**
         * <code>optional int64 type_id = 7;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearTypeId() {
            copyOnWrite();
            instance.clearTypeId();
            return this;
        }

        /**
         * <code>optional int64 mute_end_date = 8;</code>
         *
         * @return Whether the muteEndDate field is set.
         */
        @java.lang.Override
        public boolean hasMuteEndDate() {
            return instance.hasMuteEndDate();
        }

        /**
         * <code>optional int64 mute_end_date = 8;</code>
         *
         * @return The muteEndDate.
         */
        @java.lang.Override
        public long getMuteEndDate() {
            return instance.getMuteEndDate();
        }

        /**
         * <code>optional int64 mute_end_date = 8;</code>
         *
         * @param value The muteEndDate to set.
         * @return This builder for chaining.
         */
        public Builder setMuteEndDate(long value) {
            copyOnWrite();
            instance.setMuteEndDate(value);
            return this;
        }

        /**
         * <code>optional int64 mute_end_date = 8;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearMuteEndDate() {
            copyOnWrite();
            instance.clearMuteEndDate();
            return this;
        }

        /**
         * <code>optional int64 successor_id = 9;</code>
         *
         * @return Whether the successorId field is set.
         */
        @java.lang.Override
        public boolean hasSuccessorId() {
            return instance.hasSuccessorId();
        }

        /**
         * <code>optional int64 successor_id = 9;</code>
         *
         * @return The successorId.
         */
        @java.lang.Override
        public long getSuccessorId() {
            return instance.getSuccessorId();
        }

        /**
         * <code>optional int64 successor_id = 9;</code>
         *
         * @param value The successorId to set.
         * @return This builder for chaining.
         */
        public Builder setSuccessorId(long value) {
            copyOnWrite();
            instance.setSuccessorId(value);
            return this;
        }

        /**
         * <code>optional int64 successor_id = 9;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearSuccessorId() {
            copyOnWrite();
            instance.clearSuccessorId();
            return this;
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.UpdateGroupRequest)
    }

    @java.lang.Override
    @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
    protected final java.lang.Object dynamicMethod(
            com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
            java.lang.Object arg0,
            java.lang.Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE: {
                return new im.turms.client.model.proto.request.group.UpdateGroupRequest();
            }
            case NEW_BUILDER: {
                return new Builder();
            }
            case BUILD_MESSAGE_INFO: {
                java.lang.Object[] objects = new java.lang.Object[]{"bitField0_",
                        "groupId_",
                        "quitAfterTransfer_",
                        "name_",
                        "intro_",
                        "announcement_",
                        "minScore_",
                        "typeId_",
                        "muteEndDate_",
                        "successorId_",};
                java.lang.String info =
                        "\u0000\t\u0000\u0001\u0001\t\t\u0000\u0000\u0000\u0001\u0002\u0002\u1007\u0000\u0003"
                                + "\u1208\u0001\u0004\u1208\u0002\u0005\u1208\u0003\u0006\u1004\u0004\u0007\u1002\u0005"
                                + "\b\u1002\u0006\t\u1002\u0007";
                return newMessageInfo(DEFAULT_INSTANCE, info, objects);
            }
            // fall through
            case GET_DEFAULT_INSTANCE: {
                return DEFAULT_INSTANCE;
            }
            case GET_PARSER: {
                com.google.protobuf.Parser<im.turms.client.model.proto.request.group.UpdateGroupRequest> parser =
                        PARSER;
                if (parser == null) {
                    synchronized (im.turms.client.model.proto.request.group.UpdateGroupRequest.class) {
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

    // @@protoc_insertion_point(class_scope:im.turms.proto.UpdateGroupRequest)
    private static final im.turms.client.model.proto.request.group.UpdateGroupRequest DEFAULT_INSTANCE;

    static {
        UpdateGroupRequest defaultInstance = new UpdateGroupRequest();
        // New instances are implicitly immutable so no need to make
        // immutable.
        DEFAULT_INSTANCE = defaultInstance;
        com.google.protobuf.GeneratedMessageLite.registerDefaultInstance(UpdateGroupRequest.class,
                defaultInstance);
    }

    public static im.turms.client.model.proto.request.group.UpdateGroupRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static volatile com.google.protobuf.Parser<UpdateGroupRequest> PARSER;

    public static com.google.protobuf.Parser<UpdateGroupRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}