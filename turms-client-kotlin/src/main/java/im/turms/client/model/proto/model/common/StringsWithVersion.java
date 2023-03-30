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

package im.turms.client.model.proto.model.common;

/**
 * Protobuf type {@code im.turms.proto.StringsWithVersion}
 */
public final class StringsWithVersion extends
        com.google.protobuf.GeneratedMessageLite<StringsWithVersion, StringsWithVersion.Builder>
        implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.StringsWithVersion)
        StringsWithVersionOrBuilder {
    private StringsWithVersion() {
        strings_ = com.google.protobuf.GeneratedMessageLite.emptyProtobufList();
    }

    private int bitField0_;
    public static final int STRINGS_FIELD_NUMBER = 1;
    private com.google.protobuf.Internal.ProtobufList<java.lang.String> strings_;

    /**
     * <code>repeated string strings = 1;</code>
     *
     * @return A list containing the strings.
     */
    @java.lang.Override
    public java.util.List<java.lang.String> getStringsList() {
        return strings_;
    }

    /**
     * <code>repeated string strings = 1;</code>
     *
     * @return The count of strings.
     */
    @java.lang.Override
    public int getStringsCount() {
        return strings_.size();
    }

    /**
     * <code>repeated string strings = 1;</code>
     *
     * @param index The index of the element to return.
     * @return The strings at the given index.
     */
    @java.lang.Override
    public java.lang.String getStrings(int index) {
        return strings_.get(index);
    }

    /**
     * <code>repeated string strings = 1;</code>
     *
     * @param index The index of the value to return.
     * @return The bytes of the strings at the given index.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getStringsBytes(int index) {
        return com.google.protobuf.ByteString.copyFromUtf8(strings_.get(index));
    }

    private void ensureStringsIsMutable() {
        com.google.protobuf.Internal.ProtobufList<java.lang.String> tmp = strings_;
        if (!tmp.isModifiable()) {
            strings_ = com.google.protobuf.GeneratedMessageLite.mutableCopy(tmp);
        }
    }

    /**
     * <code>repeated string strings = 1;</code>
     *
     * @param index The index to set the value at.
     * @param value The strings to set.
     */
    private void setStrings(int index, java.lang.String value) {
        java.lang.Class<?> valueClass = value.getClass();
        ensureStringsIsMutable();
        strings_.set(index, value);
    }

    /**
     * <code>repeated string strings = 1;</code>
     *
     * @param value The strings to add.
     */
    private void addStrings(java.lang.String value) {
        java.lang.Class<?> valueClass = value.getClass();
        ensureStringsIsMutable();
        strings_.add(value);
    }

    /**
     * <code>repeated string strings = 1;</code>
     *
     * @param values The strings to add.
     */
    private void addAllStrings(java.lang.Iterable<java.lang.String> values) {
        ensureStringsIsMutable();
        com.google.protobuf.AbstractMessageLite.addAll(values, strings_);
    }

    /**
     * <code>repeated string strings = 1;</code>
     */
    private void clearStrings() {
        strings_ = com.google.protobuf.GeneratedMessageLite.emptyProtobufList();
    }

    /**
     * <code>repeated string strings = 1;</code>
     *
     * @param value The bytes of the strings to add.
     */
    private void addStringsBytes(com.google.protobuf.ByteString value) {
        checkByteStringIsUtf8(value);
        ensureStringsIsMutable();
        strings_.add(value.toStringUtf8());
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

    public static im.turms.client.model.proto.model.common.StringsWithVersion parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.model.common.StringsWithVersion parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.common.StringsWithVersion parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.model.common.StringsWithVersion parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.common.StringsWithVersion parseFrom(byte[] data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.model.common.StringsWithVersion parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.common.StringsWithVersion parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.model.common.StringsWithVersion parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.common.StringsWithVersion parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.model.common.StringsWithVersion parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.model.common.StringsWithVersion parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.model.common.StringsWithVersion parseFrom(
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
            im.turms.client.model.proto.model.common.StringsWithVersion prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /**
     * Protobuf type {@code im.turms.proto.StringsWithVersion}
     */
    public static final class Builder extends
            com.google.protobuf.GeneratedMessageLite.Builder<im.turms.client.model.proto.model.common.StringsWithVersion, Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.StringsWithVersion)
            im.turms.client.model.proto.model.common.StringsWithVersionOrBuilder {
        // Construct using im.turms.client.model.proto.model.common.StringsWithVersion.newBuilder()
        private Builder() {
            super(DEFAULT_INSTANCE);
        }

        /**
         * <code>repeated string strings = 1;</code>
         *
         * @return A list containing the strings.
         */
        @java.lang.Override
        public java.util.List<java.lang.String> getStringsList() {
            return java.util.Collections.unmodifiableList(instance.getStringsList());
        }

        /**
         * <code>repeated string strings = 1;</code>
         *
         * @return The count of strings.
         */
        @java.lang.Override
        public int getStringsCount() {
            return instance.getStringsCount();
        }

        /**
         * <code>repeated string strings = 1;</code>
         *
         * @param index The index of the element to return.
         * @return The strings at the given index.
         */
        @java.lang.Override
        public java.lang.String getStrings(int index) {
            return instance.getStrings(index);
        }

        /**
         * <code>repeated string strings = 1;</code>
         *
         * @param index The index of the value to return.
         * @return The bytes of the strings at the given index.
         */
        @java.lang.Override
        public com.google.protobuf.ByteString getStringsBytes(int index) {
            return instance.getStringsBytes(index);
        }

        /**
         * <code>repeated string strings = 1;</code>
         *
         * @param index The index to set the value at.
         * @param value The strings to set.
         * @return This builder for chaining.
         */
        public Builder setStrings(int index, java.lang.String value) {
            copyOnWrite();
            instance.setStrings(index, value);
            return this;
        }

        /**
         * <code>repeated string strings = 1;</code>
         *
         * @param value The strings to add.
         * @return This builder for chaining.
         */
        public Builder addStrings(java.lang.String value) {
            copyOnWrite();
            instance.addStrings(value);
            return this;
        }

        /**
         * <code>repeated string strings = 1;</code>
         *
         * @param values The strings to add.
         * @return This builder for chaining.
         */
        public Builder addAllStrings(java.lang.Iterable<java.lang.String> values) {
            copyOnWrite();
            instance.addAllStrings(values);
            return this;
        }

        /**
         * <code>repeated string strings = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearStrings() {
            copyOnWrite();
            instance.clearStrings();
            return this;
        }

        /**
         * <code>repeated string strings = 1;</code>
         *
         * @param value The bytes of the strings to add.
         * @return This builder for chaining.
         */
        public Builder addStringsBytes(com.google.protobuf.ByteString value) {
            copyOnWrite();
            instance.addStringsBytes(value);
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

        // @@protoc_insertion_point(builder_scope:im.turms.proto.StringsWithVersion)
    }

    @java.lang.Override
    @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
    protected final java.lang.Object dynamicMethod(
            com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
            java.lang.Object arg0,
            java.lang.Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE: {
                return new im.turms.client.model.proto.model.common.StringsWithVersion();
            }
            case NEW_BUILDER: {
                return new Builder();
            }
            case BUILD_MESSAGE_INFO: {
                java.lang.Object[] objects =
                        new java.lang.Object[]{"bitField0_", "strings_", "lastUpdatedDate_",};
                java.lang.String info =
                        "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u021a\u0002\u1002"
                                + "\u0000";
                return newMessageInfo(DEFAULT_INSTANCE, info, objects);
            }
            // fall through
            case GET_DEFAULT_INSTANCE: {
                return DEFAULT_INSTANCE;
            }
            case GET_PARSER: {
                com.google.protobuf.Parser<im.turms.client.model.proto.model.common.StringsWithVersion> parser =
                        PARSER;
                if (parser == null) {
                    synchronized (im.turms.client.model.proto.model.common.StringsWithVersion.class) {
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

    // @@protoc_insertion_point(class_scope:im.turms.proto.StringsWithVersion)
    private static final im.turms.client.model.proto.model.common.StringsWithVersion DEFAULT_INSTANCE;

    static {
        StringsWithVersion defaultInstance = new StringsWithVersion();
        // New instances are implicitly immutable so no need to make
        // immutable.
        DEFAULT_INSTANCE = defaultInstance;
        com.google.protobuf.GeneratedMessageLite.registerDefaultInstance(StringsWithVersion.class,
                defaultInstance);
    }

    public static im.turms.client.model.proto.model.common.StringsWithVersion getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static volatile com.google.protobuf.Parser<StringsWithVersion> PARSER;

    public static com.google.protobuf.Parser<StringsWithVersion> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}