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

package im.turms.server.common.access.client.dto.model.conference;

/**
 * Protobuf type {@code im.turms.proto.Meetings}
 */
public final class Meetings extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.Meetings)
        MeetingsOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 27,
                /* patch= */ 2,
                /* suffix= */ "",
                Meetings.class.getName());
    }

    // Use Meetings.newBuilder() to construct.
    private Meetings(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private Meetings() {
        meetings_ = java.util.Collections.emptyList();
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.server.common.access.client.dto.model.conference.MeetingsOuterClass.internal_static_im_turms_proto_Meetings_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.server.common.access.client.dto.model.conference.MeetingsOuterClass.internal_static_im_turms_proto_Meetings_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.server.common.access.client.dto.model.conference.Meetings.class,
                        im.turms.server.common.access.client.dto.model.conference.Meetings.Builder.class);
    }

    public static final int MEETINGS_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
    private java.util.List<im.turms.server.common.access.client.dto.model.conference.Meeting> meetings_;

    /**
     * <code>repeated .im.turms.proto.Meeting meetings = 1;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.server.common.access.client.dto.model.conference.Meeting> getMeetingsList() {
        return meetings_;
    }

    /**
     * <code>repeated .im.turms.proto.Meeting meetings = 1;</code>
     */
    @java.lang.Override
    public java.util.List<? extends im.turms.server.common.access.client.dto.model.conference.MeetingOrBuilder> getMeetingsOrBuilderList() {
        return meetings_;
    }

    /**
     * <code>repeated .im.turms.proto.Meeting meetings = 1;</code>
     */
    @java.lang.Override
    public int getMeetingsCount() {
        return meetings_.size();
    }

    /**
     * <code>repeated .im.turms.proto.Meeting meetings = 1;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.conference.Meeting getMeetings(
            int index) {
        return meetings_.get(index);
    }

    /**
     * <code>repeated .im.turms.proto.Meeting meetings = 1;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.conference.MeetingOrBuilder getMeetingsOrBuilder(
            int index) {
        return meetings_.get(index);
    }

    private byte memoizedIsInitialized = -1;

    @java.lang.Override
    public boolean isInitialized() {
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
        for (Meeting meeting : meetings_) {
            output.writeMessage(1, meeting);
        }
        getUnknownFields().writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
        int size = memoizedSize;
        if (size != -1) {
            return size;
        }

        size = 0;
        for (Meeting meeting : meetings_) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(1, meeting);
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
        if (!(obj instanceof Meetings other)) {
            return super.equals(obj);
        }

        return getMeetingsList().equals(other.getMeetingsList())
                && getUnknownFields().equals(other.getUnknownFields());
    }

    @java.lang.Override
    public int hashCode() {
        if (memoizedHashCode != 0) {
            return memoizedHashCode;
        }
        int hash = 41;
        hash = (19 * hash) + getDescriptor().hashCode();
        if (getMeetingsCount() > 0) {
            hash = (37 * hash) + MEETINGS_FIELD_NUMBER;
            hash = (53 * hash) + getMeetingsList().hashCode();
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.server.common.access.client.dto.model.conference.Meetings parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.conference.Meetings parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.conference.Meetings parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.conference.Meetings parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.conference.Meetings parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.conference.Meetings parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.conference.Meetings parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.conference.Meetings parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.conference.Meetings parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.conference.Meetings parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.conference.Meetings parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.conference.Meetings parseFrom(
            com.google.protobuf.CodedInputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
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
            im.turms.server.common.access.client.dto.model.conference.Meetings prototype) {
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
    protected Builder newBuilderForType(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
        return new Builder(parent);
    }

    /**
     * Protobuf type {@code im.turms.proto.Meetings}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.Meetings)
            im.turms.server.common.access.client.dto.model.conference.MeetingsOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.server.common.access.client.dto.model.conference.MeetingsOuterClass.internal_static_im_turms_proto_Meetings_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.server.common.access.client.dto.model.conference.MeetingsOuterClass.internal_static_im_turms_proto_Meetings_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.server.common.access.client.dto.model.conference.Meetings.class,
                            im.turms.server.common.access.client.dto.model.conference.Meetings.Builder.class);
        }

        // Construct using
        // im.turms.server.common.access.client.dto.model.conference.Meetings.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            if (meetingsBuilder_ == null) {
                meetings_ = java.util.Collections.emptyList();
            } else {
                meetings_ = null;
                meetingsBuilder_.clear();
            }
            bitField0_ &= ~0x00000001;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.server.common.access.client.dto.model.conference.MeetingsOuterClass.internal_static_im_turms_proto_Meetings_descriptor;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.conference.Meetings getDefaultInstanceForType() {
            return im.turms.server.common.access.client.dto.model.conference.Meetings
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.conference.Meetings build() {
            im.turms.server.common.access.client.dto.model.conference.Meetings result =
                    buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.conference.Meetings buildPartial() {
            im.turms.server.common.access.client.dto.model.conference.Meetings result =
                    new im.turms.server.common.access.client.dto.model.conference.Meetings(this);
            buildPartialRepeatedFields(result);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartialRepeatedFields(
                im.turms.server.common.access.client.dto.model.conference.Meetings result) {
            if (meetingsBuilder_ == null) {
                if (((bitField0_ & 0x00000001) != 0)) {
                    meetings_ = java.util.Collections.unmodifiableList(meetings_);
                    bitField0_ &= ~0x00000001;
                }
                result.meetings_ = meetings_;
            } else {
                result.meetings_ = meetingsBuilder_.build();
            }
        }

        private void buildPartial0(
                im.turms.server.common.access.client.dto.model.conference.Meetings result) {
            int from_bitField0_ = bitField0_;
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.server.common.access.client.dto.model.conference.Meetings) {
                return mergeFrom(
                        (im.turms.server.common.access.client.dto.model.conference.Meetings) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.server.common.access.client.dto.model.conference.Meetings other) {
            if (other == im.turms.server.common.access.client.dto.model.conference.Meetings
                    .getDefaultInstance()) {
                return this;
            }
            if (meetingsBuilder_ == null) {
                if (!other.meetings_.isEmpty()) {
                    if (meetings_.isEmpty()) {
                        meetings_ = other.meetings_;
                        bitField0_ &= ~0x00000001;
                    } else {
                        ensureMeetingsIsMutable();
                        meetings_.addAll(other.meetings_);
                    }
                    onChanged();
                }
            } else {
                if (!other.meetings_.isEmpty()) {
                    if (meetingsBuilder_.isEmpty()) {
                        meetingsBuilder_.dispose();
                        meetingsBuilder_ = null;
                        meetings_ = other.meetings_;
                        bitField0_ &= ~0x00000001;
                        meetingsBuilder_ =
                                com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders
                                        ? getMeetingsFieldBuilder()
                                        : null;
                    } else {
                        meetingsBuilder_.addAllMessages(other.meetings_);
                    }
                }
            }
            this.mergeUnknownFields(other.getUnknownFields());
            onChanged();
            return this;
        }

        @java.lang.Override
        public boolean isInitialized() {
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
                        case 10 -> {
                            Meeting m = input.readMessage(Meeting.parser(), extensionRegistry);
                            if (meetingsBuilder_ == null) {
                                ensureMeetingsIsMutable();
                                meetings_.add(m);
                            } else {
                                meetingsBuilder_.addMessage(m);
                            }
                        } // case 10
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

        private java.util.List<im.turms.server.common.access.client.dto.model.conference.Meeting> meetings_ =
                java.util.Collections.emptyList();

        private void ensureMeetingsIsMutable() {
            if ((bitField0_ & 0x00000001) == 0) {
                meetings_ = new java.util.ArrayList<>(meetings_);
                bitField0_ |= 0x00000001;
            }
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.server.common.access.client.dto.model.conference.Meeting, im.turms.server.common.access.client.dto.model.conference.Meeting.Builder, im.turms.server.common.access.client.dto.model.conference.MeetingOrBuilder> meetingsBuilder_;

        /**
         * <code>repeated .im.turms.proto.Meeting meetings = 1;</code>
         */
        public java.util.List<im.turms.server.common.access.client.dto.model.conference.Meeting> getMeetingsList() {
            if (meetingsBuilder_ == null) {
                return java.util.Collections.unmodifiableList(meetings_);
            } else {
                return meetingsBuilder_.getMessageList();
            }
        }

        /**
         * <code>repeated .im.turms.proto.Meeting meetings = 1;</code>
         */
        public int getMeetingsCount() {
            if (meetingsBuilder_ == null) {
                return meetings_.size();
            } else {
                return meetingsBuilder_.getCount();
            }
        }

        /**
         * <code>repeated .im.turms.proto.Meeting meetings = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.conference.Meeting getMeetings(
                int index) {
            if (meetingsBuilder_ == null) {
                return meetings_.get(index);
            } else {
                return meetingsBuilder_.getMessage(index);
            }
        }

        /**
         * <code>repeated .im.turms.proto.Meeting meetings = 1;</code>
         */
        public Builder setMeetings(
                int index,
                im.turms.server.common.access.client.dto.model.conference.Meeting value) {
            if (meetingsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureMeetingsIsMutable();
                meetings_.set(index, value);
                onChanged();
            } else {
                meetingsBuilder_.setMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Meeting meetings = 1;</code>
         */
        public Builder setMeetings(
                int index,
                im.turms.server.common.access.client.dto.model.conference.Meeting.Builder builderForValue) {
            if (meetingsBuilder_ == null) {
                ensureMeetingsIsMutable();
                meetings_.set(index, builderForValue.build());
                onChanged();
            } else {
                meetingsBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Meeting meetings = 1;</code>
         */
        public Builder addMeetings(
                im.turms.server.common.access.client.dto.model.conference.Meeting value) {
            if (meetingsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureMeetingsIsMutable();
                meetings_.add(value);
                onChanged();
            } else {
                meetingsBuilder_.addMessage(value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Meeting meetings = 1;</code>
         */
        public Builder addMeetings(
                int index,
                im.turms.server.common.access.client.dto.model.conference.Meeting value) {
            if (meetingsBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureMeetingsIsMutable();
                meetings_.add(index, value);
                onChanged();
            } else {
                meetingsBuilder_.addMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Meeting meetings = 1;</code>
         */
        public Builder addMeetings(
                im.turms.server.common.access.client.dto.model.conference.Meeting.Builder builderForValue) {
            if (meetingsBuilder_ == null) {
                ensureMeetingsIsMutable();
                meetings_.add(builderForValue.build());
                onChanged();
            } else {
                meetingsBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Meeting meetings = 1;</code>
         */
        public Builder addMeetings(
                int index,
                im.turms.server.common.access.client.dto.model.conference.Meeting.Builder builderForValue) {
            if (meetingsBuilder_ == null) {
                ensureMeetingsIsMutable();
                meetings_.add(index, builderForValue.build());
                onChanged();
            } else {
                meetingsBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Meeting meetings = 1;</code>
         */
        public Builder addAllMeetings(
                java.lang.Iterable<? extends im.turms.server.common.access.client.dto.model.conference.Meeting> values) {
            if (meetingsBuilder_ == null) {
                ensureMeetingsIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(values, meetings_);
                onChanged();
            } else {
                meetingsBuilder_.addAllMessages(values);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Meeting meetings = 1;</code>
         */
        public Builder clearMeetings() {
            if (meetingsBuilder_ == null) {
                meetings_ = java.util.Collections.emptyList();
                bitField0_ &= ~0x00000001;
                onChanged();
            } else {
                meetingsBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Meeting meetings = 1;</code>
         */
        public Builder removeMeetings(int index) {
            if (meetingsBuilder_ == null) {
                ensureMeetingsIsMutable();
                meetings_.remove(index);
                onChanged();
            } else {
                meetingsBuilder_.remove(index);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.Meeting meetings = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.conference.Meeting.Builder getMeetingsBuilder(
                int index) {
            return getMeetingsFieldBuilder().getBuilder(index);
        }

        /**
         * <code>repeated .im.turms.proto.Meeting meetings = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.conference.MeetingOrBuilder getMeetingsOrBuilder(
                int index) {
            if (meetingsBuilder_ == null) {
                return meetings_.get(index);
            } else {
                return meetingsBuilder_.getMessageOrBuilder(index);
            }
        }

        /**
         * <code>repeated .im.turms.proto.Meeting meetings = 1;</code>
         */
        public java.util.List<? extends im.turms.server.common.access.client.dto.model.conference.MeetingOrBuilder> getMeetingsOrBuilderList() {
            if (meetingsBuilder_ != null) {
                return meetingsBuilder_.getMessageOrBuilderList();
            } else {
                return java.util.Collections.unmodifiableList(meetings_);
            }
        }

        /**
         * <code>repeated .im.turms.proto.Meeting meetings = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.conference.Meeting.Builder addMeetingsBuilder() {
            return getMeetingsFieldBuilder()
                    .addBuilder(im.turms.server.common.access.client.dto.model.conference.Meeting
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .im.turms.proto.Meeting meetings = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.conference.Meeting.Builder addMeetingsBuilder(
                int index) {
            return getMeetingsFieldBuilder().addBuilder(index,
                    im.turms.server.common.access.client.dto.model.conference.Meeting
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .im.turms.proto.Meeting meetings = 1;</code>
         */
        public java.util.List<im.turms.server.common.access.client.dto.model.conference.Meeting.Builder> getMeetingsBuilderList() {
            return getMeetingsFieldBuilder().getBuilderList();
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.server.common.access.client.dto.model.conference.Meeting, im.turms.server.common.access.client.dto.model.conference.Meeting.Builder, im.turms.server.common.access.client.dto.model.conference.MeetingOrBuilder> getMeetingsFieldBuilder() {
            if (meetingsBuilder_ == null) {
                meetingsBuilder_ = new com.google.protobuf.RepeatedFieldBuilder<>(
                        meetings_,
                        ((bitField0_ & 0x00000001) != 0),
                        getParentForChildren(),
                        isClean());
                meetings_ = null;
            }
            return meetingsBuilder_;
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.Meetings)
    }

    // @@protoc_insertion_point(class_scope:im.turms.proto.Meetings)
    private static final im.turms.server.common.access.client.dto.model.conference.Meetings DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE = new im.turms.server.common.access.client.dto.model.conference.Meetings();
    }

    public static im.turms.server.common.access.client.dto.model.conference.Meetings getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<Meetings> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public Meetings parsePartialFrom(
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

    public static com.google.protobuf.Parser<Meetings> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<Meetings> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.conference.Meetings getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}