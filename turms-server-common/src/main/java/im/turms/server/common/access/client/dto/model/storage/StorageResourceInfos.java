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

package im.turms.server.common.access.client.dto.model.storage;

/**
 * Protobuf type {@code im.turms.proto.StorageResourceInfos}
 */
public final class StorageResourceInfos extends com.google.protobuf.GeneratedMessageV3 implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.StorageResourceInfos)
        StorageResourceInfosOrBuilder {
    private static final long serialVersionUID = 0L;

    // Use StorageResourceInfos.newBuilder() to construct.
    private StorageResourceInfos(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private StorageResourceInfos() {
        infos_ = java.util.Collections.emptyList();
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(UnusedPrivateParameter unused) {
        return new StorageResourceInfos();
    }

    public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.server.common.access.client.dto.model.storage.StorageResourceInfosOuterClass.internal_static_im_turms_proto_StorageResourceInfos_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.server.common.access.client.dto.model.storage.StorageResourceInfosOuterClass.internal_static_im_turms_proto_StorageResourceInfos_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.server.common.access.client.dto.model.storage.StorageResourceInfos.class,
                        im.turms.server.common.access.client.dto.model.storage.StorageResourceInfos.Builder.class);
    }

    public static final int INFOS_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
    private java.util.List<im.turms.server.common.access.client.dto.model.storage.StorageResourceInfo> infos_;

    /**
     * <code>repeated .im.turms.proto.StorageResourceInfo infos = 1;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.server.common.access.client.dto.model.storage.StorageResourceInfo> getInfosList() {
        return infos_;
    }

    /**
     * <code>repeated .im.turms.proto.StorageResourceInfo infos = 1;</code>
     */
    @java.lang.Override
    public java.util.List<? extends im.turms.server.common.access.client.dto.model.storage.StorageResourceInfoOrBuilder> getInfosOrBuilderList() {
        return infos_;
    }

    /**
     * <code>repeated .im.turms.proto.StorageResourceInfo infos = 1;</code>
     */
    @java.lang.Override
    public int getInfosCount() {
        return infos_.size();
    }

    /**
     * <code>repeated .im.turms.proto.StorageResourceInfo infos = 1;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.storage.StorageResourceInfo getInfos(
            int index) {
        return infos_.get(index);
    }

    /**
     * <code>repeated .im.turms.proto.StorageResourceInfo infos = 1;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.storage.StorageResourceInfoOrBuilder getInfosOrBuilder(
            int index) {
        return infos_.get(index);
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
        for (StorageResourceInfo storageResourceInfo : infos_) {
            output.writeMessage(1, storageResourceInfo);
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
        for (StorageResourceInfo storageResourceInfo : infos_) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(1,
                    storageResourceInfo);
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
        if (!(obj instanceof StorageResourceInfos other)) {
            return super.equals(obj);
        }

        if (!getInfosList().equals(other.getInfosList())) {
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
        if (getInfosCount() > 0) {
            hash = (37 * hash) + INFOS_FIELD_NUMBER;
            hash = (53 * hash) + getInfosList().hashCode();
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.server.common.access.client.dto.model.storage.StorageResourceInfos parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.storage.StorageResourceInfos parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.storage.StorageResourceInfos parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.storage.StorageResourceInfos parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.storage.StorageResourceInfos parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.model.storage.StorageResourceInfos parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.storage.StorageResourceInfos parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.storage.StorageResourceInfos parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.storage.StorageResourceInfos parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.storage.StorageResourceInfos parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.model.storage.StorageResourceInfos parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.model.storage.StorageResourceInfos parseFrom(
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
            im.turms.server.common.access.client.dto.model.storage.StorageResourceInfos prototype) {
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
     * Protobuf type {@code im.turms.proto.StorageResourceInfos}
     */
    public static final class Builder
            extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.StorageResourceInfos)
            im.turms.server.common.access.client.dto.model.storage.StorageResourceInfosOrBuilder {
        public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.server.common.access.client.dto.model.storage.StorageResourceInfosOuterClass.internal_static_im_turms_proto_StorageResourceInfos_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.server.common.access.client.dto.model.storage.StorageResourceInfosOuterClass.internal_static_im_turms_proto_StorageResourceInfos_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.server.common.access.client.dto.model.storage.StorageResourceInfos.class,
                            im.turms.server.common.access.client.dto.model.storage.StorageResourceInfos.Builder.class);
        }

        // Construct using
        // im.turms.server.common.access.client.dto.model.storage.StorageResourceInfos.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            if (infosBuilder_ == null) {
                infos_ = java.util.Collections.emptyList();
            } else {
                infos_ = null;
                infosBuilder_.clear();
            }
            bitField0_ &= ~0x00000001;
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.server.common.access.client.dto.model.storage.StorageResourceInfosOuterClass.internal_static_im_turms_proto_StorageResourceInfos_descriptor;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.storage.StorageResourceInfos getDefaultInstanceForType() {
            return im.turms.server.common.access.client.dto.model.storage.StorageResourceInfos
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.storage.StorageResourceInfos build() {
            im.turms.server.common.access.client.dto.model.storage.StorageResourceInfos result =
                    buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.storage.StorageResourceInfos buildPartial() {
            im.turms.server.common.access.client.dto.model.storage.StorageResourceInfos result =
                    new im.turms.server.common.access.client.dto.model.storage.StorageResourceInfos(
                            this);
            buildPartialRepeatedFields(result);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartialRepeatedFields(
                im.turms.server.common.access.client.dto.model.storage.StorageResourceInfos result) {
            if (infosBuilder_ == null) {
                if (((bitField0_ & 0x00000001) != 0)) {
                    infos_ = java.util.Collections.unmodifiableList(infos_);
                    bitField0_ &= ~0x00000001;
                }
                result.infos_ = infos_;
            } else {
                result.infos_ = infosBuilder_.build();
            }
        }

        private void buildPartial0(
                im.turms.server.common.access.client.dto.model.storage.StorageResourceInfos result) {
            int from_bitField0_ = bitField0_;
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.server.common.access.client.dto.model.storage.StorageResourceInfos) {
                return mergeFrom(
                        (im.turms.server.common.access.client.dto.model.storage.StorageResourceInfos) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.server.common.access.client.dto.model.storage.StorageResourceInfos other) {
            if (other == im.turms.server.common.access.client.dto.model.storage.StorageResourceInfos
                    .getDefaultInstance()) {
                return this;
            }
            if (infosBuilder_ == null) {
                if (!other.infos_.isEmpty()) {
                    if (infos_.isEmpty()) {
                        infos_ = other.infos_;
                        bitField0_ &= ~0x00000001;
                    } else {
                        ensureInfosIsMutable();
                        infos_.addAll(other.infos_);
                    }
                    onChanged();
                }
            } else {
                if (!other.infos_.isEmpty()) {
                    if (infosBuilder_.isEmpty()) {
                        infosBuilder_.dispose();
                        infosBuilder_ = null;
                        infos_ = other.infos_;
                        bitField0_ &= ~0x00000001;
                        infosBuilder_ =
                                com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders
                                        ? getInfosFieldBuilder()
                                        : null;
                    } else {
                        infosBuilder_.addAllMessages(other.infos_);
                    }
                }
            }
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
                        case 10 -> {
                            StorageResourceInfo m = input.readMessage(StorageResourceInfo.parser(),
                                    extensionRegistry);
                            if (infosBuilder_ == null) {
                                ensureInfosIsMutable();
                                infos_.add(m);
                            } else {
                                infosBuilder_.addMessage(m);
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

        private java.util.List<im.turms.server.common.access.client.dto.model.storage.StorageResourceInfo> infos_ =
                java.util.Collections.emptyList();

        private void ensureInfosIsMutable() {
            if ((bitField0_ & 0x00000001) == 0) {
                infos_ = new java.util.ArrayList<>(infos_);
                bitField0_ |= 0x00000001;
            }
        }

        private com.google.protobuf.RepeatedFieldBuilderV3<im.turms.server.common.access.client.dto.model.storage.StorageResourceInfo, im.turms.server.common.access.client.dto.model.storage.StorageResourceInfo.Builder, im.turms.server.common.access.client.dto.model.storage.StorageResourceInfoOrBuilder> infosBuilder_;

        /**
         * <code>repeated .im.turms.proto.StorageResourceInfo infos = 1;</code>
         */
        public java.util.List<im.turms.server.common.access.client.dto.model.storage.StorageResourceInfo> getInfosList() {
            if (infosBuilder_ == null) {
                return java.util.Collections.unmodifiableList(infos_);
            } else {
                return infosBuilder_.getMessageList();
            }
        }

        /**
         * <code>repeated .im.turms.proto.StorageResourceInfo infos = 1;</code>
         */
        public int getInfosCount() {
            if (infosBuilder_ == null) {
                return infos_.size();
            } else {
                return infosBuilder_.getCount();
            }
        }

        /**
         * <code>repeated .im.turms.proto.StorageResourceInfo infos = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.storage.StorageResourceInfo getInfos(
                int index) {
            if (infosBuilder_ == null) {
                return infos_.get(index);
            } else {
                return infosBuilder_.getMessage(index);
            }
        }

        /**
         * <code>repeated .im.turms.proto.StorageResourceInfo infos = 1;</code>
         */
        public Builder setInfos(
                int index,
                im.turms.server.common.access.client.dto.model.storage.StorageResourceInfo value) {
            if (infosBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureInfosIsMutable();
                infos_.set(index, value);
                onChanged();
            } else {
                infosBuilder_.setMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.StorageResourceInfo infos = 1;</code>
         */
        public Builder setInfos(
                int index,
                im.turms.server.common.access.client.dto.model.storage.StorageResourceInfo.Builder builderForValue) {
            if (infosBuilder_ == null) {
                ensureInfosIsMutable();
                infos_.set(index, builderForValue.build());
                onChanged();
            } else {
                infosBuilder_.setMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.StorageResourceInfo infos = 1;</code>
         */
        public Builder addInfos(
                im.turms.server.common.access.client.dto.model.storage.StorageResourceInfo value) {
            if (infosBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureInfosIsMutable();
                infos_.add(value);
                onChanged();
            } else {
                infosBuilder_.addMessage(value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.StorageResourceInfo infos = 1;</code>
         */
        public Builder addInfos(
                int index,
                im.turms.server.common.access.client.dto.model.storage.StorageResourceInfo value) {
            if (infosBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureInfosIsMutable();
                infos_.add(index, value);
                onChanged();
            } else {
                infosBuilder_.addMessage(index, value);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.StorageResourceInfo infos = 1;</code>
         */
        public Builder addInfos(
                im.turms.server.common.access.client.dto.model.storage.StorageResourceInfo.Builder builderForValue) {
            if (infosBuilder_ == null) {
                ensureInfosIsMutable();
                infos_.add(builderForValue.build());
                onChanged();
            } else {
                infosBuilder_.addMessage(builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.StorageResourceInfo infos = 1;</code>
         */
        public Builder addInfos(
                int index,
                im.turms.server.common.access.client.dto.model.storage.StorageResourceInfo.Builder builderForValue) {
            if (infosBuilder_ == null) {
                ensureInfosIsMutable();
                infos_.add(index, builderForValue.build());
                onChanged();
            } else {
                infosBuilder_.addMessage(index, builderForValue.build());
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.StorageResourceInfo infos = 1;</code>
         */
        public Builder addAllInfos(
                java.lang.Iterable<? extends im.turms.server.common.access.client.dto.model.storage.StorageResourceInfo> values) {
            if (infosBuilder_ == null) {
                ensureInfosIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(values, infos_);
                onChanged();
            } else {
                infosBuilder_.addAllMessages(values);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.StorageResourceInfo infos = 1;</code>
         */
        public Builder clearInfos() {
            if (infosBuilder_ == null) {
                infos_ = java.util.Collections.emptyList();
                bitField0_ &= ~0x00000001;
                onChanged();
            } else {
                infosBuilder_.clear();
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.StorageResourceInfo infos = 1;</code>
         */
        public Builder removeInfos(int index) {
            if (infosBuilder_ == null) {
                ensureInfosIsMutable();
                infos_.remove(index);
                onChanged();
            } else {
                infosBuilder_.remove(index);
            }
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.StorageResourceInfo infos = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.storage.StorageResourceInfo.Builder getInfosBuilder(
                int index) {
            return getInfosFieldBuilder().getBuilder(index);
        }

        /**
         * <code>repeated .im.turms.proto.StorageResourceInfo infos = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.storage.StorageResourceInfoOrBuilder getInfosOrBuilder(
                int index) {
            if (infosBuilder_ == null) {
                return infos_.get(index);
            } else {
                return infosBuilder_.getMessageOrBuilder(index);
            }
        }

        /**
         * <code>repeated .im.turms.proto.StorageResourceInfo infos = 1;</code>
         */
        public java.util.List<? extends im.turms.server.common.access.client.dto.model.storage.StorageResourceInfoOrBuilder> getInfosOrBuilderList() {
            if (infosBuilder_ != null) {
                return infosBuilder_.getMessageOrBuilderList();
            } else {
                return java.util.Collections.unmodifiableList(infos_);
            }
        }

        /**
         * <code>repeated .im.turms.proto.StorageResourceInfo infos = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.storage.StorageResourceInfo.Builder addInfosBuilder() {
            return getInfosFieldBuilder().addBuilder(
                    im.turms.server.common.access.client.dto.model.storage.StorageResourceInfo
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .im.turms.proto.StorageResourceInfo infos = 1;</code>
         */
        public im.turms.server.common.access.client.dto.model.storage.StorageResourceInfo.Builder addInfosBuilder(
                int index) {
            return getInfosFieldBuilder().addBuilder(index,
                    im.turms.server.common.access.client.dto.model.storage.StorageResourceInfo
                            .getDefaultInstance());
        }

        /**
         * <code>repeated .im.turms.proto.StorageResourceInfo infos = 1;</code>
         */
        public java.util.List<im.turms.server.common.access.client.dto.model.storage.StorageResourceInfo.Builder> getInfosBuilderList() {
            return getInfosFieldBuilder().getBuilderList();
        }

        private com.google.protobuf.RepeatedFieldBuilderV3<im.turms.server.common.access.client.dto.model.storage.StorageResourceInfo, im.turms.server.common.access.client.dto.model.storage.StorageResourceInfo.Builder, im.turms.server.common.access.client.dto.model.storage.StorageResourceInfoOrBuilder> getInfosFieldBuilder() {
            if (infosBuilder_ == null) {
                infosBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<>(
                        infos_,
                        ((bitField0_ & 0x00000001) != 0),
                        getParentForChildren(),
                        isClean());
                infos_ = null;
            }
            return infosBuilder_;
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

        // @@protoc_insertion_point(builder_scope:im.turms.proto.StorageResourceInfos)
    }

    // @@protoc_insertion_point(class_scope:im.turms.proto.StorageResourceInfos)
    private static final im.turms.server.common.access.client.dto.model.storage.StorageResourceInfos DEFAULT_INSTANCE;

    static {
        DEFAULT_INSTANCE =
                new im.turms.server.common.access.client.dto.model.storage.StorageResourceInfos();
    }

    public static im.turms.server.common.access.client.dto.model.storage.StorageResourceInfos getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<StorageResourceInfos> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public StorageResourceInfos parsePartialFrom(
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

    public static com.google.protobuf.Parser<StorageResourceInfos> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<StorageResourceInfos> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.server.common.access.client.dto.model.storage.StorageResourceInfos getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}