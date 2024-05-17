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
 * Protobuf type {@code livekit.SpeakerInfo}
 */
public final class SpeakerInfo extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:livekit.SpeakerInfo)
        SpeakerInfoOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                SpeakerInfo.class.getName());
    }

    // Use SpeakerInfo.newBuilder() to construct.
    private SpeakerInfo(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private SpeakerInfo() {
        sid_ = "";
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_SpeakerInfo_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_SpeakerInfo_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.plugin.livekit.core.proto.models.SpeakerInfo.class,
                        im.turms.plugin.livekit.core.proto.models.SpeakerInfo.Builder.class);
    }

    public static final int SID_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
    private volatile java.lang.Object sid_ = "";

    /**
     * <code>string sid = 1;</code>
     *
     * @return The sid.
     */
    @java.lang.Override
    public java.lang.String getSid() {
        java.lang.Object ref = sid_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            sid_ = s;
            return s;
        }
    }

    /**
     * <code>string sid = 1;</code>
     *
     * @return The bytes for sid.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getSidBytes() {
        java.lang.Object ref = sid_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            sid_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int LEVEL_FIELD_NUMBER = 2;
    private float level_ = 0F;

    /**
     * <pre>
     * audio level, 0-1.0, 1 is loudest
     * </pre>
     *
     * <code>float level = 2;</code>
     *
     * @return The level.
     */
    @java.lang.Override
    public float getLevel() {
        return level_;
    }

    public static final int ACTIVE_FIELD_NUMBER = 3;
    private boolean active_ = false;

    /**
     * <pre>
     * true if speaker is currently active
     * </pre>
     *
     * <code>bool active = 3;</code>
     *
     * @return The active.
     */
    @java.lang.Override
    public boolean getActive() {
        return active_;
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
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(sid_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 1, sid_);
        }
        if (java.lang.Float.floatToRawIntBits(level_) != 0) {
            output.writeFloat(2, level_);
        }
        if (active_) {
            output.writeBool(3, active_);
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
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(sid_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(1, sid_);
        }
        if (java.lang.Float.floatToRawIntBits(level_) != 0) {
            size += com.google.protobuf.CodedOutputStream.computeFloatSize(2, level_);
        }
        if (active_) {
            size += com.google.protobuf.CodedOutputStream.computeBoolSize(3, active_);
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
        if (!(obj instanceof SpeakerInfo other)) {
            return super.equals(obj);
        }

        if (!getSid().equals(other.getSid())) {
            return false;
        }
        if (java.lang.Float.floatToIntBits(getLevel()) != java.lang.Float
                .floatToIntBits(other.getLevel())) {
            return false;
        }
        if (getActive() != other.getActive()) {
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
        hash = (37 * hash) + SID_FIELD_NUMBER;
        hash = (53 * hash) + getSid().hashCode();
        hash = (37 * hash) + LEVEL_FIELD_NUMBER;
        hash = (53 * hash) + java.lang.Float.floatToIntBits(getLevel());
        hash = (37 * hash) + ACTIVE_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(getActive());
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.plugin.livekit.core.proto.models.SpeakerInfo parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.models.SpeakerInfo parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.SpeakerInfo parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.models.SpeakerInfo parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.SpeakerInfo parseFrom(byte[] data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.models.SpeakerInfo parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.SpeakerInfo parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.models.SpeakerInfo parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.SpeakerInfo parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.models.SpeakerInfo parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.SpeakerInfo parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.models.SpeakerInfo parseFrom(
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
            im.turms.plugin.livekit.core.proto.models.SpeakerInfo prototype) {
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
     * Protobuf type {@code livekit.SpeakerInfo}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:livekit.SpeakerInfo)
            im.turms.plugin.livekit.core.proto.models.SpeakerInfoOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_SpeakerInfo_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_SpeakerInfo_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.plugin.livekit.core.proto.models.SpeakerInfo.class,
                            im.turms.plugin.livekit.core.proto.models.SpeakerInfo.Builder.class);
        }

        // Construct using im.turms.plugin.livekit.core.proto.models.SpeakerInfo.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            sid_ = "";
            level_ = 0F;
            active_ = false;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_SpeakerInfo_descriptor;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.SpeakerInfo getDefaultInstanceForType() {
            return im.turms.plugin.livekit.core.proto.models.SpeakerInfo.getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.SpeakerInfo build() {
            im.turms.plugin.livekit.core.proto.models.SpeakerInfo result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.SpeakerInfo buildPartial() {
            im.turms.plugin.livekit.core.proto.models.SpeakerInfo result =
                    new im.turms.plugin.livekit.core.proto.models.SpeakerInfo(this);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartial0(im.turms.plugin.livekit.core.proto.models.SpeakerInfo result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.sid_ = sid_;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.level_ = level_;
            }
            if (((from_bitField0_ & 0x00000004) != 0)) {
                result.active_ = active_;
            }
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.plugin.livekit.core.proto.models.SpeakerInfo) {
                return mergeFrom((im.turms.plugin.livekit.core.proto.models.SpeakerInfo) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(im.turms.plugin.livekit.core.proto.models.SpeakerInfo other) {
            if (other == im.turms.plugin.livekit.core.proto.models.SpeakerInfo
                    .getDefaultInstance()) {
                return this;
            }
            if (!other.getSid()
                    .isEmpty()) {
                sid_ = other.sid_;
                bitField0_ |= 0x00000001;
                onChanged();
            }
            if (other.getLevel() != 0F) {
                setLevel(other.getLevel());
            }
            if (other.getActive()) {
                setActive(other.getActive());
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
                            sid_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000001;
                        } // case 10
                        case 21 -> {
                            level_ = input.readFloat();
                            bitField0_ |= 0x00000002;
                        } // case 21
                        case 24 -> {
                            active_ = input.readBool();
                            bitField0_ |= 0x00000004;
                        } // case 24
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

        private java.lang.Object sid_ = "";

        /**
         * <code>string sid = 1;</code>
         *
         * @return The sid.
         */
        public java.lang.String getSid() {
            java.lang.Object ref = sid_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                sid_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>string sid = 1;</code>
         *
         * @return The bytes for sid.
         */
        public com.google.protobuf.ByteString getSidBytes() {
            java.lang.Object ref = sid_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                sid_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>string sid = 1;</code>
         *
         * @param value The sid to set.
         * @return This builder for chaining.
         */
        public Builder setSid(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            sid_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>string sid = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearSid() {
            sid_ = getDefaultInstance().getSid();
            bitField0_ &= ~0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>string sid = 1;</code>
         *
         * @param value The bytes for sid to set.
         * @return This builder for chaining.
         */
        public Builder setSidBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            sid_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        private float level_;

        /**
         * <pre>
         * audio level, 0-1.0, 1 is loudest
         * </pre>
         *
         * <code>float level = 2;</code>
         *
         * @return The level.
         */
        @java.lang.Override
        public float getLevel() {
            return level_;
        }

        /**
         * <pre>
         * audio level, 0-1.0, 1 is loudest
         * </pre>
         *
         * <code>float level = 2;</code>
         *
         * @param value The level to set.
         * @return This builder for chaining.
         */
        public Builder setLevel(float value) {

            level_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * audio level, 0-1.0, 1 is loudest
         * </pre>
         *
         * <code>float level = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearLevel() {
            bitField0_ &= ~0x00000002;
            level_ = 0F;
            onChanged();
            return this;
        }

        private boolean active_;

        /**
         * <pre>
         * true if speaker is currently active
         * </pre>
         *
         * <code>bool active = 3;</code>
         *
         * @return The active.
         */
        @java.lang.Override
        public boolean getActive() {
            return active_;
        }

        /**
         * <pre>
         * true if speaker is currently active
         * </pre>
         *
         * <code>bool active = 3;</code>
         *
         * @param value The active to set.
         * @return This builder for chaining.
         */
        public Builder setActive(boolean value) {

            active_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * true if speaker is currently active
         * </pre>
         *
         * <code>bool active = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearActive() {
            bitField0_ &= ~0x00000004;
            active_ = false;
            onChanged();
            return this;
        }

        // @@protoc_insertion_point(builder_scope:livekit.SpeakerInfo)
    }

    // @@protoc_insertion_point(class_scope:livekit.SpeakerInfo)
    private static final im.turms.plugin.livekit.core.proto.models.SpeakerInfo DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE = new im.turms.plugin.livekit.core.proto.models.SpeakerInfo();
    }

    public static im.turms.plugin.livekit.core.proto.models.SpeakerInfo getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<SpeakerInfo> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public SpeakerInfo parsePartialFrom(
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

    public static com.google.protobuf.Parser<SpeakerInfo> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<SpeakerInfo> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.SpeakerInfo getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}