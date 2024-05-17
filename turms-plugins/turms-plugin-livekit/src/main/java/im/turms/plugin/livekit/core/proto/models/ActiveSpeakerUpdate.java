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

package im.turms.plugin.livekit.core.proto.models;

/**
 * Protobuf type {@code livekit.ActiveSpeakerUpdate}
 */
public final class ActiveSpeakerUpdate extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:livekit.ActiveSpeakerUpdate)
        ActiveSpeakerUpdateOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                ActiveSpeakerUpdate.class.getName());
    }

    // Use ActiveSpeakerUpdate.newBuilder() to construct.
    private ActiveSpeakerUpdate(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private ActiveSpeakerUpdate() {
        speakers_ = java.util.Collections.emptyList();
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_ActiveSpeakerUpdate_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_ActiveSpeakerUpdate_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.plugin.livekit.core.proto.models.ActiveSpeakerUpdate.class,
                        im.turms.plugin.livekit.core.proto.models.ActiveSpeakerUpdate.Builder.class);
    }

    public static final int SPEAKERS_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
    private java.util.List<im.turms.plugin.livekit.core.proto.models.SpeakerInfo> speakers_;

    /**
     * <code>repeated .livekit.SpeakerInfo speakers = 1;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.plugin.livekit.core.proto.models.SpeakerInfo> getSpeakersList() {
        return speakers_;
    }

    /**
     * <code>repeated .livekit.SpeakerInfo speakers = 1;</code>
     */
    @java.lang.Override
    public java.util.List<? extends im.turms.plugin.livekit.core.proto.models.SpeakerInfoOrBuilder> getSpeakersOrBuilderList() {
        return speakers_;
    }

    /**
     * <code>repeated .livekit.SpeakerInfo speakers = 1;</code>
     */
    @java.lang.Override
    public int getSpeakersCount() {
        return speakers_.size();
    }

    /**
     * <code>repeated .livekit.SpeakerInfo speakers = 1;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.SpeakerInfo getSpeakers(int index) {
        return speakers_.get(index);
    }

    /**
     * <code>repeated .livekit.SpeakerInfo speakers = 1;</code>
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.SpeakerInfoOrBuilder getSpeakersOrBuilder(
            int index) {
        return speakers_.get(index);
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
        for (SpeakerInfo speakerInfo : speakers_) {
            output.writeMessage(1, speakerInfo);
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
        for (SpeakerInfo speakerInfo : speakers_) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(1, speakerInfo);
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
        if (!(obj instanceof ActiveSpeakerUpdate other)) {
            return super.equals(obj);
        }

        if (!getSpeakersList().equals(other.getSpeakersList())) {
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
        if (getSpeakersCount() > 0) {
            hash = (37 * hash) + SPEAKERS_FIELD_NUMBER;
            hash = (53 * hash) + getSpeakersList().hashCode();
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.plugin.livekit.core.proto.models.ActiveSpeakerUpdate parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.models.ActiveSpeakerUpdate parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.ActiveSpeakerUpdate parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.models.ActiveSpeakerUpdate parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.ActiveSpeakerUpdate parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.models.ActiveSpeakerUpdate parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.ActiveSpeakerUpdate parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.models.ActiveSpeakerUpdate parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.ActiveSpeakerUpdate parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.models.ActiveSpeakerUpdate parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.ActiveSpeakerUpdate parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.models.ActiveSpeakerUpdate parseFrom(
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
            im.turms.plugin.livekit.core.proto.models.ActiveSpeakerUpdate prototype) {
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
     * Protobuf type {@code livekit.ActiveSpeakerUpdate}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:livekit.ActiveSpeakerUpdate)
            im.turms.plugin.livekit.core.proto.models.ActiveSpeakerUpdateOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_ActiveSpeakerUpdate_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_ActiveSpeakerUpdate_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.plugin.livekit.core.proto.models.ActiveSpeakerUpdate.class,
                            im.turms.plugin.livekit.core.proto.models.ActiveSpeakerUpdate.Builder.class);
        }

        // Construct using
        // im.turms.plugin.livekit.core.proto.models.ActiveSpeakerUpdate.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            if (speakersBuilder_ == null) {
                speakers_ = java.util.Collections.emptyList();
            } else {
                speakers_ = null;
                speakersBuilder_.clear();
            }
            bitField0_ &= ~0x00000001;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_ActiveSpeakerUpdate_descriptor;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.ActiveSpeakerUpdate getDefaultInstanceForType() {
            return im.turms.plugin.livekit.core.proto.models.ActiveSpeakerUpdate
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.ActiveSpeakerUpdate build() {
            im.turms.plugin.livekit.core.proto.models.ActiveSpeakerUpdate result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.ActiveSpeakerUpdate buildPartial() {
            im.turms.plugin.livekit.core.proto.models.ActiveSpeakerUpdate result =
                    new im.turms.plugin.livekit.core.proto.models.ActiveSpeakerUpdate(this);
            buildPartialRepeatedFields(result);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartialRepeatedFields(
                im.turms.plugin.livekit.core.proto.models.ActiveSpeakerUpdate result) {
            if (speakersBuilder_ == null) {
                if (((bitField0_ & 0x00000001) != 0)) {
                    speakers_ = java.util.Collections.unmodifiableList(speakers_);
                    bitField0_ &= ~0x00000001;
                }
                result.speakers_ = speakers_;
            } else {
                result.speakers_ = speakersBuilder_.build();
            }
        }

        private void buildPartial0(
                im.turms.plugin.livekit.core.proto.models.ActiveSpeakerUpdate result) {
            int from_bitField0_ = bitField0_;
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.plugin.livekit.core.proto.models.ActiveSpeakerUpdate) {
                return mergeFrom(
                        (im.turms.plugin.livekit.core.proto.models.ActiveSpeakerUpdate) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.plugin.livekit.core.proto.models.ActiveSpeakerUpdate other) {
            if (other == im.turms.plugin.livekit.core.proto.models.ActiveSpeakerUpdate
                    .getDefaultInstance()) {
                return this;
            }
            if (speakersBuilder_ == null) {
                if (!other.speakers_.isEmpty()) {
                    if (speakers_.isEmpty()) {
                        speakers_ = other.speakers_;
                        bitField0_ &= ~0x00000001;
                    } else {
                        ensureSpeakersIsMutable();
                        speakers_.addAll(other.speakers_);
                    }
                    onChanged();
                }
            } else {
                if (!other.speakers_.isEmpty()) {
                    if (speakersBuilder_.isEmpty()) {
                        speakersBuilder_.dispose();
                        speakersBuilder_ = null;
                        speakers_ = other.speakers_;
                        bitField0_ &= ~0x00000001;
                        speakersBuilder_ =
                                com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders
                                        ? getSpeakersFieldBuilder()
                                        : null;
                    } else {
                        speakersBuilder_.addAllMessages(other.speakers_);
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
                            SpeakerInfo m =
                                    input.readMessage(SpeakerInfo.parser(), extensionRegistry);
                            if (speakersBuilder_ == null) {
                                ensureSpeakersIsMutable();
                                speakers_.add(m);
                            } else {
                                speakersBuilder_.addMessage(m);
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

        private java.util.List<im.turms.plugin.livekit.core.proto.models.SpeakerInfo> speakers_ =
                java.util.Collections.emptyList();

        private void ensureSpeakersIsMutable() {
            if ((bitField0_ & 0x00000001) == 0) {
                speakers_ = new java.util.ArrayList<>(speakers_);
                bitField0_ |= 0x00000001;
            }
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.plugin.livekit.core.proto.models.SpeakerInfo, im.turms.plugin.livekit.core.proto.models.SpeakerInfo.Builder, im.turms.plugin.livekit.core.proto.models.SpeakerInfoOrBuilder> speakersBuilder_;

        /**
         * <code>repeated .livekit.SpeakerInfo speakers = 1;</code>
         */
        public java.util.List<im.turms.plugin.livekit.core.proto.models.SpeakerInfo> getSpeakersList() {
            if (speakersBuilder_ == null) {
                return java.util.Collections.unmodifiableList(speakers_);
            } else {
                return speakersBuilder_.getMessageList();
            }
        }

        /**
         * <code>repeated .livekit.SpeakerInfo speakers = 1;</code>
         */
        public int getSpeakersCount() {
            if (speakersBuilder_ == null) {
                return speakers_.size();
            } else {
                return speakersBuilder_.getCount();
            }
        }

        /**
         * <code>repeated .livekit.SpeakerInfo speakers = 1;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.SpeakerInfo getSpeakers(int index) {
            if (speakersBuilder_ == null) {
                return speakers_.get(index);
            } else {
                return speakersBuilder_.getMessage(index);
            }
        }

        /**
         * <code>repeated .livekit.SpeakerInfo speakers = 1;</code>
         */
        public Builder setSpeakers(
                int index,
                im.turms.plugin.livekit.core.proto.models.SpeakerInfo value) {
            if (speakersBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureSpeakersIsMutable();
                speakers_.set(index, value);
                onChanged();
            } else {
                speakersBuilder_.setMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.SpeakerInfo speakers = 1;</code>
         */
        public Builder setSpeakers(
                int index,
                im.turms.plugin.livekit.core.proto.models.SpeakerInfo.Builder builderForValue) {
            if (speakersBuilder_ == null) {
                ensureSpeakersIsMutable();
                speakers_.set(index, builderForValue.build());
                onChanged();
            } else {
                speakersBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .livekit.SpeakerInfo speakers = 1;</code>
         */
        public Builder addSpeakers(im.turms.plugin.livekit.core.proto.models.SpeakerInfo value) {
            if (speakersBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureSpeakersIsMutable();
                speakers_.add(value);
                onChanged();
            } else {
                speakersBuilder_.addMessage(value);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.SpeakerInfo speakers = 1;</code>
         */
        public Builder addSpeakers(
                int index,
                im.turms.plugin.livekit.core.proto.models.SpeakerInfo value) {
            if (speakersBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureSpeakersIsMutable();
                speakers_.add(index, value);
                onChanged();
            } else {
                speakersBuilder_.addMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.SpeakerInfo speakers = 1;</code>
         */
        public Builder addSpeakers(
                im.turms.plugin.livekit.core.proto.models.SpeakerInfo.Builder builderForValue) {
            if (speakersBuilder_ == null) {
                ensureSpeakersIsMutable();
                speakers_.add(builderForValue.build());
                onChanged();
            } else {
                speakersBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .livekit.SpeakerInfo speakers = 1;</code>
         */
        public Builder addSpeakers(
                int index,
                im.turms.plugin.livekit.core.proto.models.SpeakerInfo.Builder builderForValue) {
            if (speakersBuilder_ == null) {
                ensureSpeakersIsMutable();
                speakers_.add(index, builderForValue.build());
                onChanged();
            } else {
                speakersBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .livekit.SpeakerInfo speakers = 1;</code>
         */
        public Builder addAllSpeakers(
                java.lang.Iterable<? extends im.turms.plugin.livekit.core.proto.models.SpeakerInfo> values) {
            if (speakersBuilder_ == null) {
                ensureSpeakersIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(values, speakers_);
                onChanged();
            } else {
                speakersBuilder_.addAllMessages(values);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.SpeakerInfo speakers = 1;</code>
         */
        public Builder clearSpeakers() {
            if (speakersBuilder_ == null) {
                speakers_ = java.util.Collections.emptyList();
                bitField0_ &= ~0x00000001;
                onChanged();
            } else {
                speakersBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>repeated .livekit.SpeakerInfo speakers = 1;</code>
         */
        public Builder removeSpeakers(int index) {
            if (speakersBuilder_ == null) {
                ensureSpeakersIsMutable();
                speakers_.remove(index);
                onChanged();
            } else {
                speakersBuilder_.remove(index);
            }
            return this;
        }

        /**
         * <code>repeated .livekit.SpeakerInfo speakers = 1;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.SpeakerInfo.Builder getSpeakersBuilder(
                int index) {
            return getSpeakersFieldBuilder().getBuilder(index);
        }

        /**
         * <code>repeated .livekit.SpeakerInfo speakers = 1;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.SpeakerInfoOrBuilder getSpeakersOrBuilder(
                int index) {
            if (speakersBuilder_ == null) {
                return speakers_.get(index);
            } else {
                return speakersBuilder_.getMessageOrBuilder(index);
            }
        }

        /**
         * <code>repeated .livekit.SpeakerInfo speakers = 1;</code>
         */
        public java.util.List<? extends im.turms.plugin.livekit.core.proto.models.SpeakerInfoOrBuilder> getSpeakersOrBuilderList() {
            if (speakersBuilder_ != null) {
                return speakersBuilder_.getMessageOrBuilderList();
            } else {
                return java.util.Collections.unmodifiableList(speakers_);
            }
        }

        /**
         * <code>repeated .livekit.SpeakerInfo speakers = 1;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.SpeakerInfo.Builder addSpeakersBuilder() {
            return getSpeakersFieldBuilder().addBuilder(
                    im.turms.plugin.livekit.core.proto.models.SpeakerInfo.getDefaultInstance());
        }

        /**
         * <code>repeated .livekit.SpeakerInfo speakers = 1;</code>
         */
        public im.turms.plugin.livekit.core.proto.models.SpeakerInfo.Builder addSpeakersBuilder(
                int index) {
            return getSpeakersFieldBuilder().addBuilder(index,
                    im.turms.plugin.livekit.core.proto.models.SpeakerInfo.getDefaultInstance());
        }

        /**
         * <code>repeated .livekit.SpeakerInfo speakers = 1;</code>
         */
        public java.util.List<im.turms.plugin.livekit.core.proto.models.SpeakerInfo.Builder> getSpeakersBuilderList() {
            return getSpeakersFieldBuilder().getBuilderList();
        }

        private com.google.protobuf.RepeatedFieldBuilder<im.turms.plugin.livekit.core.proto.models.SpeakerInfo, im.turms.plugin.livekit.core.proto.models.SpeakerInfo.Builder, im.turms.plugin.livekit.core.proto.models.SpeakerInfoOrBuilder> getSpeakersFieldBuilder() {
            if (speakersBuilder_ == null) {
                speakersBuilder_ = new com.google.protobuf.RepeatedFieldBuilder<>(
                        speakers_,
                        ((bitField0_ & 0x00000001) != 0),
                        getParentForChildren(),
                        isClean());
                speakers_ = null;
            }
            return speakersBuilder_;
        }

        // @@protoc_insertion_point(builder_scope:livekit.ActiveSpeakerUpdate)
    }

    // @@protoc_insertion_point(class_scope:livekit.ActiveSpeakerUpdate)
    private static final im.turms.plugin.livekit.core.proto.models.ActiveSpeakerUpdate DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE = new im.turms.plugin.livekit.core.proto.models.ActiveSpeakerUpdate();
    }

    public static im.turms.plugin.livekit.core.proto.models.ActiveSpeakerUpdate getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<ActiveSpeakerUpdate> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public ActiveSpeakerUpdate parsePartialFrom(
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

    public static com.google.protobuf.Parser<ActiveSpeakerUpdate> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<ActiveSpeakerUpdate> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.ActiveSpeakerUpdate getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}