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

package im.turms.client.model.proto.model.storage;

/**
 * Protobuf type {@code im.turms.proto.StorageResourceInfos}
 */
public final class StorageResourceInfos extends
        com.google.protobuf.GeneratedMessageLite<StorageResourceInfos, StorageResourceInfos.Builder>
        implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.StorageResourceInfos)
        StorageResourceInfosOrBuilder {
    private StorageResourceInfos() {
        infos_ = emptyProtobufList();
    }

    public static final int INFOS_FIELD_NUMBER = 1;
    private com.google.protobuf.Internal.ProtobufList<im.turms.client.model.proto.model.storage.StorageResourceInfo> infos_;

    /**
     * <code>repeated .im.turms.proto.StorageResourceInfo infos = 1;</code>
     */
    @java.lang.Override
    public java.util.List<im.turms.client.model.proto.model.storage.StorageResourceInfo> getInfosList() {
        return infos_;
    }

    /**
     * <code>repeated .im.turms.proto.StorageResourceInfo infos = 1;</code>
     */
    public java.util.List<? extends im.turms.client.model.proto.model.storage.StorageResourceInfoOrBuilder> getInfosOrBuilderList() {
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
    public im.turms.client.model.proto.model.storage.StorageResourceInfo getInfos(int index) {
        return infos_.get(index);
    }

    /**
     * <code>repeated .im.turms.proto.StorageResourceInfo infos = 1;</code>
     */
    public im.turms.client.model.proto.model.storage.StorageResourceInfoOrBuilder getInfosOrBuilder(
            int index) {
        return infos_.get(index);
    }

    private void ensureInfosIsMutable() {
        com.google.protobuf.Internal.ProtobufList<im.turms.client.model.proto.model.storage.StorageResourceInfo> tmp =
                infos_;
        if (!tmp.isModifiable()) {
            infos_ = com.google.protobuf.GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /**
     * <code>repeated .im.turms.proto.StorageResourceInfo infos = 1;</code>
     */
    private void setInfos(
            int index,
            im.turms.client.model.proto.model.storage.StorageResourceInfo value) {
        value.getClass();
        ensureInfosIsMutable();
        infos_.set(index, value);
    }

    /**
     * <code>repeated .im.turms.proto.StorageResourceInfo infos = 1;</code>
     */
    private void addInfos(im.turms.client.model.proto.model.storage.StorageResourceInfo value) {
        value.getClass();
        ensureInfosIsMutable();
        infos_.add(value);
    }

    /**
     * <code>repeated .im.turms.proto.StorageResourceInfo infos = 1;</code>
     */
    private void addInfos(
            int index,
            im.turms.client.model.proto.model.storage.StorageResourceInfo value) {
        value.getClass();
        ensureInfosIsMutable();
        infos_.add(index, value);
    }

    /**
     * <code>repeated .im.turms.proto.StorageResourceInfo infos = 1;</code>
     */
    private void addAllInfos(
            java.lang.Iterable<? extends im.turms.client.model.proto.model.storage.StorageResourceInfo> values) {
        ensureInfosIsMutable();
        com.google.protobuf.AbstractMessageLite.addAll(values, infos_);
    }

    /**
     * <code>repeated .im.turms.proto.StorageResourceInfo infos = 1;</code>
     */
    private void clearInfos() {
        infos_ = emptyProtobufList();
    }

    /**
     * <code>repeated .im.turms.proto.StorageResourceInfo infos = 1;</code>
     */
    private void removeInfos(int index) {
        ensureInfosIsMutable();
        infos_.remove(index);
    }

    public static im.turms.client.model.proto.model.storage.StorageResourceInfos parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.model.storage.StorageResourceInfos parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.storage.StorageResourceInfos parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.model.storage.StorageResourceInfos parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.storage.StorageResourceInfos parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.model.storage.StorageResourceInfos parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.storage.StorageResourceInfos parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.model.storage.StorageResourceInfos parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.storage.StorageResourceInfos parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.model.storage.StorageResourceInfos parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.storage.StorageResourceInfos parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.model.storage.StorageResourceInfos parseFrom(
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
            im.turms.client.model.proto.model.storage.StorageResourceInfos prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /**
     * Protobuf type {@code im.turms.proto.StorageResourceInfos}
     */
    public static final class Builder extends
            com.google.protobuf.GeneratedMessageLite.Builder<im.turms.client.model.proto.model.storage.StorageResourceInfos, Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.StorageResourceInfos)
            im.turms.client.model.proto.model.storage.StorageResourceInfosOrBuilder {
        // Construct using
        // im.turms.client.model.proto.model.storage.StorageResourceInfos.newBuilder()
        private Builder() {
            super(DEFAULT_INSTANCE);
        }

        /**
         * <code>repeated .im.turms.proto.StorageResourceInfo infos = 1;</code>
         */
        @java.lang.Override
        public java.util.List<im.turms.client.model.proto.model.storage.StorageResourceInfo> getInfosList() {
            return java.util.Collections.unmodifiableList(instance.getInfosList());
        }

        /**
         * <code>repeated .im.turms.proto.StorageResourceInfo infos = 1;</code>
         */
        @java.lang.Override
        public int getInfosCount() {
            return instance.getInfosCount();
        }

        /**
         * <code>repeated .im.turms.proto.StorageResourceInfo infos = 1;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.model.storage.StorageResourceInfo getInfos(int index) {
            return instance.getInfos(index);
        }

        /**
         * <code>repeated .im.turms.proto.StorageResourceInfo infos = 1;</code>
         */
        public Builder setInfos(
                int index,
                im.turms.client.model.proto.model.storage.StorageResourceInfo value) {
            copyOnWrite();
            instance.setInfos(index, value);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.StorageResourceInfo infos = 1;</code>
         */
        public Builder setInfos(
                int index,
                im.turms.client.model.proto.model.storage.StorageResourceInfo.Builder builderForValue) {
            copyOnWrite();
            instance.setInfos(index, builderForValue.build());
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.StorageResourceInfo infos = 1;</code>
         */
        public Builder addInfos(
                im.turms.client.model.proto.model.storage.StorageResourceInfo value) {
            copyOnWrite();
            instance.addInfos(value);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.StorageResourceInfo infos = 1;</code>
         */
        public Builder addInfos(
                int index,
                im.turms.client.model.proto.model.storage.StorageResourceInfo value) {
            copyOnWrite();
            instance.addInfos(index, value);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.StorageResourceInfo infos = 1;</code>
         */
        public Builder addInfos(
                im.turms.client.model.proto.model.storage.StorageResourceInfo.Builder builderForValue) {
            copyOnWrite();
            instance.addInfos(builderForValue.build());
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.StorageResourceInfo infos = 1;</code>
         */
        public Builder addInfos(
                int index,
                im.turms.client.model.proto.model.storage.StorageResourceInfo.Builder builderForValue) {
            copyOnWrite();
            instance.addInfos(index, builderForValue.build());
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.StorageResourceInfo infos = 1;</code>
         */
        public Builder addAllInfos(
                java.lang.Iterable<? extends im.turms.client.model.proto.model.storage.StorageResourceInfo> values) {
            copyOnWrite();
            instance.addAllInfos(values);
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.StorageResourceInfo infos = 1;</code>
         */
        public Builder clearInfos() {
            copyOnWrite();
            instance.clearInfos();
            return this;
        }

        /**
         * <code>repeated .im.turms.proto.StorageResourceInfo infos = 1;</code>
         */
        public Builder removeInfos(int index) {
            copyOnWrite();
            instance.removeInfos(index);
            return this;
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.StorageResourceInfos)
    }

    @java.lang.Override
    @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
    protected final java.lang.Object dynamicMethod(
            com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
            java.lang.Object arg0,
            java.lang.Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE: {
                return new im.turms.client.model.proto.model.storage.StorageResourceInfos();
            }
            case NEW_BUILDER: {
                return new Builder();
            }
            case BUILD_MESSAGE_INFO: {
                java.lang.Object[] objects = new java.lang.Object[]{"infos_",
                        im.turms.client.model.proto.model.storage.StorageResourceInfo.class,};
                java.lang.String info =
                        "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b";
                return newMessageInfo(DEFAULT_INSTANCE, info, objects);
            }
            // fall through
            case GET_DEFAULT_INSTANCE: {
                return DEFAULT_INSTANCE;
            }
            case GET_PARSER: {
                com.google.protobuf.Parser<im.turms.client.model.proto.model.storage.StorageResourceInfos> parser =
                        PARSER;
                if (parser == null) {
                    synchronized (im.turms.client.model.proto.model.storage.StorageResourceInfos.class) {
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

    // @@protoc_insertion_point(class_scope:im.turms.proto.StorageResourceInfos)
    private static final im.turms.client.model.proto.model.storage.StorageResourceInfos DEFAULT_INSTANCE;

    static {
        StorageResourceInfos defaultInstance = new StorageResourceInfos();
        // New instances are implicitly immutable so no need to make
        // immutable.
        DEFAULT_INSTANCE = defaultInstance;
        com.google.protobuf.GeneratedMessageLite.registerDefaultInstance(StorageResourceInfos.class,
                defaultInstance);
    }

    public static im.turms.client.model.proto.model.storage.StorageResourceInfos getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static volatile com.google.protobuf.Parser<StorageResourceInfos> PARSER;

    public static com.google.protobuf.Parser<StorageResourceInfos> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}