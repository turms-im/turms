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
 * <pre>
 * details about the client
 * </pre>
 *
 * Protobuf type {@code livekit.ClientInfo}
 */
public final class ClientInfo extends com.google.protobuf.GeneratedMessage implements
        // @@protoc_insertion_point(message_implements:livekit.ClientInfo)
        ClientInfoOrBuilder {
    private static final long serialVersionUID = 0L;
    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                ClientInfo.class.getName());
    }

    // Use ClientInfo.newBuilder() to construct.
    private ClientInfo(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
    }

    private ClientInfo() {
        sdk_ = 0;
        version_ = "";
        os_ = "";
        osVersion_ = "";
        deviceModel_ = "";
        browser_ = "";
        browserVersion_ = "";
        address_ = "";
        network_ = "";
    }

    public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_ClientInfo_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_ClientInfo_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.plugin.livekit.core.proto.models.ClientInfo.class,
                        im.turms.plugin.livekit.core.proto.models.ClientInfo.Builder.class);
    }

    /**
     * Protobuf enum {@code livekit.ClientInfo.SDK}
     */
    public enum SDK implements com.google.protobuf.ProtocolMessageEnum {
        /**
         * <code>UNKNOWN = 0;</code>
         */
        UNKNOWN(0),
        /**
         * <code>JS = 1;</code>
         */
        JS(1),
        /**
         * <code>SWIFT = 2;</code>
         */
        SWIFT(2),
        /**
         * <code>ANDROID = 3;</code>
         */
        ANDROID(3),
        /**
         * <code>FLUTTER = 4;</code>
         */
        FLUTTER(4),
        /**
         * <code>GO = 5;</code>
         */
        GO(5),
        /**
         * <code>UNITY = 6;</code>
         */
        UNITY(6),
        /**
         * <code>REACT_NATIVE = 7;</code>
         */
        REACT_NATIVE(7),
        /**
         * <code>RUST = 8;</code>
         */
        RUST(8),
        /**
         * <code>PYTHON = 9;</code>
         */
        PYTHON(9),
        /**
         * <code>CPP = 10;</code>
         */
        CPP(10),
        UNRECOGNIZED(-1),;

        static {
            com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                    com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                    /* major= */ 4,
                    /* minor= */ 26,
                    /* patch= */ 1,
                    /* suffix= */ "",
                    SDK.class.getName());
        }
        /**
         * <code>UNKNOWN = 0;</code>
         */
        public static final int UNKNOWN_VALUE = 0;
        /**
         * <code>JS = 1;</code>
         */
        public static final int JS_VALUE = 1;
        /**
         * <code>SWIFT = 2;</code>
         */
        public static final int SWIFT_VALUE = 2;
        /**
         * <code>ANDROID = 3;</code>
         */
        public static final int ANDROID_VALUE = 3;
        /**
         * <code>FLUTTER = 4;</code>
         */
        public static final int FLUTTER_VALUE = 4;
        /**
         * <code>GO = 5;</code>
         */
        public static final int GO_VALUE = 5;
        /**
         * <code>UNITY = 6;</code>
         */
        public static final int UNITY_VALUE = 6;
        /**
         * <code>REACT_NATIVE = 7;</code>
         */
        public static final int REACT_NATIVE_VALUE = 7;
        /**
         * <code>RUST = 8;</code>
         */
        public static final int RUST_VALUE = 8;
        /**
         * <code>PYTHON = 9;</code>
         */
        public static final int PYTHON_VALUE = 9;
        /**
         * <code>CPP = 10;</code>
         */
        public static final int CPP_VALUE = 10;

        public final int getNumber() {
            if (this == UNRECOGNIZED) {
                throw new java.lang.IllegalArgumentException(
                        "Can't get the number of an unknown enum value.");
            }
            return value;
        }

        /**
         * @param value The numeric wire value of the corresponding enum entry.
         * @return The enum associated with the given numeric wire value.
         * @deprecated Use {@link #forNumber(int)} instead.
         */
        @java.lang.Deprecated
        public static SDK valueOf(int value) {
            return forNumber(value);
        }

        /**
         * @param value The numeric wire value of the corresponding enum entry.
         * @return The enum associated with the given numeric wire value.
         */
        public static SDK forNumber(int value) {
            return switch (value) {
                case 0 -> UNKNOWN;
                case 1 -> JS;
                case 2 -> SWIFT;
                case 3 -> ANDROID;
                case 4 -> FLUTTER;
                case 5 -> GO;
                case 6 -> UNITY;
                case 7 -> REACT_NATIVE;
                case 8 -> RUST;
                case 9 -> PYTHON;
                case 10 -> CPP;
                default -> null;
            };
        }

        public static com.google.protobuf.Internal.EnumLiteMap<SDK> internalGetValueMap() {
            return internalValueMap;
        }

        private static final com.google.protobuf.Internal.EnumLiteMap<SDK> internalValueMap =
                SDK::forNumber;

        public final com.google.protobuf.Descriptors.EnumValueDescriptor getValueDescriptor() {
            if (this == UNRECOGNIZED) {
                throw new java.lang.IllegalStateException(
                        "Can't get the descriptor of an unrecognized enum value.");
            }
            return getDescriptor().getValues()
                    .get(ordinal());
        }

        public final com.google.protobuf.Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final com.google.protobuf.Descriptors.EnumDescriptor getDescriptor() {
            return im.turms.plugin.livekit.core.proto.models.ClientInfo.getDescriptor()
                    .getEnumTypes()
                    .get(0);
        }

        private static final SDK[] VALUES = values();

        public static SDK valueOf(com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
                throw new java.lang.IllegalArgumentException(
                        "EnumValueDescriptor is not for this type.");
            }
            if (desc.getIndex() == -1) {
                return UNRECOGNIZED;
            }
            return VALUES[desc.getIndex()];
        }

        private final int value;

        SDK(int value) {
            this.value = value;
        }

        // @@protoc_insertion_point(enum_scope:livekit.ClientInfo.SDK)
    }

    public static final int SDK_FIELD_NUMBER = 1;
    private int sdk_ = 0;

    /**
     * <code>.livekit.ClientInfo.SDK sdk = 1;</code>
     *
     * @return The enum numeric value on the wire for sdk.
     */
    @java.lang.Override
    public int getSdkValue() {
        return sdk_;
    }

    /**
     * <code>.livekit.ClientInfo.SDK sdk = 1;</code>
     *
     * @return The sdk.
     */
    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.ClientInfo.SDK getSdk() {
        im.turms.plugin.livekit.core.proto.models.ClientInfo.SDK result =
                im.turms.plugin.livekit.core.proto.models.ClientInfo.SDK.forNumber(sdk_);
        return result == null
                ? im.turms.plugin.livekit.core.proto.models.ClientInfo.SDK.UNRECOGNIZED
                : result;
    }

    public static final int VERSION_FIELD_NUMBER = 2;
    @SuppressWarnings("serial")
    private volatile java.lang.Object version_ = "";

    /**
     * <code>string version = 2;</code>
     *
     * @return The version.
     */
    @java.lang.Override
    public java.lang.String getVersion() {
        java.lang.Object ref = version_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            version_ = s;
            return s;
        }
    }

    /**
     * <code>string version = 2;</code>
     *
     * @return The bytes for version.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getVersionBytes() {
        java.lang.Object ref = version_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            version_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int PROTOCOL_FIELD_NUMBER = 3;
    private int protocol_ = 0;

    /**
     * <code>int32 protocol = 3;</code>
     *
     * @return The protocol.
     */
    @java.lang.Override
    public int getProtocol() {
        return protocol_;
    }

    public static final int OS_FIELD_NUMBER = 4;
    @SuppressWarnings("serial")
    private volatile java.lang.Object os_ = "";

    /**
     * <code>string os = 4;</code>
     *
     * @return The os.
     */
    @java.lang.Override
    public java.lang.String getOs() {
        java.lang.Object ref = os_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            os_ = s;
            return s;
        }
    }

    /**
     * <code>string os = 4;</code>
     *
     * @return The bytes for os.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getOsBytes() {
        java.lang.Object ref = os_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            os_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int OS_VERSION_FIELD_NUMBER = 5;
    @SuppressWarnings("serial")
    private volatile java.lang.Object osVersion_ = "";

    /**
     * <code>string os_version = 5;</code>
     *
     * @return The osVersion.
     */
    @java.lang.Override
    public java.lang.String getOsVersion() {
        java.lang.Object ref = osVersion_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            osVersion_ = s;
            return s;
        }
    }

    /**
     * <code>string os_version = 5;</code>
     *
     * @return The bytes for osVersion.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getOsVersionBytes() {
        java.lang.Object ref = osVersion_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            osVersion_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int DEVICE_MODEL_FIELD_NUMBER = 6;
    @SuppressWarnings("serial")
    private volatile java.lang.Object deviceModel_ = "";

    /**
     * <code>string device_model = 6;</code>
     *
     * @return The deviceModel.
     */
    @java.lang.Override
    public java.lang.String getDeviceModel() {
        java.lang.Object ref = deviceModel_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            deviceModel_ = s;
            return s;
        }
    }

    /**
     * <code>string device_model = 6;</code>
     *
     * @return The bytes for deviceModel.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getDeviceModelBytes() {
        java.lang.Object ref = deviceModel_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            deviceModel_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int BROWSER_FIELD_NUMBER = 7;
    @SuppressWarnings("serial")
    private volatile java.lang.Object browser_ = "";

    /**
     * <code>string browser = 7;</code>
     *
     * @return The browser.
     */
    @java.lang.Override
    public java.lang.String getBrowser() {
        java.lang.Object ref = browser_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            browser_ = s;
            return s;
        }
    }

    /**
     * <code>string browser = 7;</code>
     *
     * @return The bytes for browser.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getBrowserBytes() {
        java.lang.Object ref = browser_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            browser_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int BROWSER_VERSION_FIELD_NUMBER = 8;
    @SuppressWarnings("serial")
    private volatile java.lang.Object browserVersion_ = "";

    /**
     * <code>string browser_version = 8;</code>
     *
     * @return The browserVersion.
     */
    @java.lang.Override
    public java.lang.String getBrowserVersion() {
        java.lang.Object ref = browserVersion_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            browserVersion_ = s;
            return s;
        }
    }

    /**
     * <code>string browser_version = 8;</code>
     *
     * @return The bytes for browserVersion.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getBrowserVersionBytes() {
        java.lang.Object ref = browserVersion_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            browserVersion_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int ADDRESS_FIELD_NUMBER = 9;
    @SuppressWarnings("serial")
    private volatile java.lang.Object address_ = "";

    /**
     * <code>string address = 9;</code>
     *
     * @return The address.
     */
    @java.lang.Override
    public java.lang.String getAddress() {
        java.lang.Object ref = address_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            address_ = s;
            return s;
        }
    }

    /**
     * <code>string address = 9;</code>
     *
     * @return The bytes for address.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getAddressBytes() {
        java.lang.Object ref = address_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            address_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int NETWORK_FIELD_NUMBER = 10;
    @SuppressWarnings("serial")
    private volatile java.lang.Object network_ = "";

    /**
     * <pre>
     * wifi, wired, cellular, vpn, empty if not known
     * </pre>
     *
     * <code>string network = 10;</code>
     *
     * @return The network.
     */
    @java.lang.Override
    public java.lang.String getNetwork() {
        java.lang.Object ref = network_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            network_ = s;
            return s;
        }
    }

    /**
     * <pre>
     * wifi, wired, cellular, vpn, empty if not known
     * </pre>
     *
     * <code>string network = 10;</code>
     *
     * @return The bytes for network.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getNetworkBytes() {
        java.lang.Object ref = network_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            network_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
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
        if (sdk_ != im.turms.plugin.livekit.core.proto.models.ClientInfo.SDK.UNKNOWN.getNumber()) {
            output.writeEnum(1, sdk_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(version_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 2, version_);
        }
        if (protocol_ != 0) {
            output.writeInt32(3, protocol_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(os_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 4, os_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(osVersion_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 5, osVersion_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(deviceModel_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 6, deviceModel_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(browser_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 7, browser_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(browserVersion_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 8, browserVersion_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(address_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 9, address_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(network_)) {
            com.google.protobuf.GeneratedMessage.writeString(output, 10, network_);
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
        if (sdk_ != im.turms.plugin.livekit.core.proto.models.ClientInfo.SDK.UNKNOWN.getNumber()) {
            size += com.google.protobuf.CodedOutputStream.computeEnumSize(1, sdk_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(version_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(2, version_);
        }
        if (protocol_ != 0) {
            size += com.google.protobuf.CodedOutputStream.computeInt32Size(3, protocol_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(os_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(4, os_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(osVersion_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(5, osVersion_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(deviceModel_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(6, deviceModel_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(browser_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(7, browser_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(browserVersion_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(8, browserVersion_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(address_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(9, address_);
        }
        if (!com.google.protobuf.GeneratedMessage.isStringEmpty(network_)) {
            size += com.google.protobuf.GeneratedMessage.computeStringSize(10, network_);
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
        if (!(obj instanceof ClientInfo other)) {
            return super.equals(obj);
        }

        if (sdk_ != other.sdk_) {
            return false;
        }
        if (!getVersion().equals(other.getVersion())) {
            return false;
        }
        if (getProtocol() != other.getProtocol()) {
            return false;
        }
        if (!getOs().equals(other.getOs())) {
            return false;
        }
        if (!getOsVersion().equals(other.getOsVersion())) {
            return false;
        }
        if (!getDeviceModel().equals(other.getDeviceModel())) {
            return false;
        }
        if (!getBrowser().equals(other.getBrowser())) {
            return false;
        }
        if (!getBrowserVersion().equals(other.getBrowserVersion())) {
            return false;
        }
        if (!getAddress().equals(other.getAddress())) {
            return false;
        }
        if (!getNetwork().equals(other.getNetwork())) {
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
        hash = (37 * hash) + SDK_FIELD_NUMBER;
        hash = (53 * hash) + sdk_;
        hash = (37 * hash) + VERSION_FIELD_NUMBER;
        hash = (53 * hash) + getVersion().hashCode();
        hash = (37 * hash) + PROTOCOL_FIELD_NUMBER;
        hash = (53 * hash) + getProtocol();
        hash = (37 * hash) + OS_FIELD_NUMBER;
        hash = (53 * hash) + getOs().hashCode();
        hash = (37 * hash) + OS_VERSION_FIELD_NUMBER;
        hash = (53 * hash) + getOsVersion().hashCode();
        hash = (37 * hash) + DEVICE_MODEL_FIELD_NUMBER;
        hash = (53 * hash) + getDeviceModel().hashCode();
        hash = (37 * hash) + BROWSER_FIELD_NUMBER;
        hash = (53 * hash) + getBrowser().hashCode();
        hash = (37 * hash) + BROWSER_VERSION_FIELD_NUMBER;
        hash = (53 * hash) + getBrowserVersion().hashCode();
        hash = (37 * hash) + ADDRESS_FIELD_NUMBER;
        hash = (53 * hash) + getAddress().hashCode();
        hash = (37 * hash) + NETWORK_FIELD_NUMBER;
        hash = (53 * hash) + getNetwork().hashCode();
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.plugin.livekit.core.proto.models.ClientInfo parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.models.ClientInfo parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.ClientInfo parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.models.ClientInfo parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.ClientInfo parseFrom(byte[] data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.plugin.livekit.core.proto.models.ClientInfo parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.ClientInfo parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.models.ClientInfo parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.ClientInfo parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.models.ClientInfo parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.plugin.livekit.core.proto.models.ClientInfo parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessage.parseWithIOException(PARSER, input);
    }

    public static im.turms.plugin.livekit.core.proto.models.ClientInfo parseFrom(
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
            im.turms.plugin.livekit.core.proto.models.ClientInfo prototype) {
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
     * <pre>
     * details about the client
     * </pre>
     *
     * Protobuf type {@code livekit.ClientInfo}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements
            // @@protoc_insertion_point(builder_implements:livekit.ClientInfo)
            im.turms.plugin.livekit.core.proto.models.ClientInfoOrBuilder {
        public static com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_ClientInfo_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_ClientInfo_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.plugin.livekit.core.proto.models.ClientInfo.class,
                            im.turms.plugin.livekit.core.proto.models.ClientInfo.Builder.class);
        }

        // Construct using im.turms.plugin.livekit.core.proto.models.ClientInfo.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            sdk_ = 0;
            version_ = "";
            protocol_ = 0;
            os_ = "";
            osVersion_ = "";
            deviceModel_ = "";
            browser_ = "";
            browserVersion_ = "";
            address_ = "";
            network_ = "";
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.plugin.livekit.core.proto.models.LivekitModels.internal_static_livekit_ClientInfo_descriptor;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.ClientInfo getDefaultInstanceForType() {
            return im.turms.plugin.livekit.core.proto.models.ClientInfo.getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.ClientInfo build() {
            im.turms.plugin.livekit.core.proto.models.ClientInfo result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.ClientInfo buildPartial() {
            im.turms.plugin.livekit.core.proto.models.ClientInfo result =
                    new im.turms.plugin.livekit.core.proto.models.ClientInfo(this);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartial0(im.turms.plugin.livekit.core.proto.models.ClientInfo result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.sdk_ = sdk_;
            }
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.version_ = version_;
            }
            if (((from_bitField0_ & 0x00000004) != 0)) {
                result.protocol_ = protocol_;
            }
            if (((from_bitField0_ & 0x00000008) != 0)) {
                result.os_ = os_;
            }
            if (((from_bitField0_ & 0x00000010) != 0)) {
                result.osVersion_ = osVersion_;
            }
            if (((from_bitField0_ & 0x00000020) != 0)) {
                result.deviceModel_ = deviceModel_;
            }
            if (((from_bitField0_ & 0x00000040) != 0)) {
                result.browser_ = browser_;
            }
            if (((from_bitField0_ & 0x00000080) != 0)) {
                result.browserVersion_ = browserVersion_;
            }
            if (((from_bitField0_ & 0x00000100) != 0)) {
                result.address_ = address_;
            }
            if (((from_bitField0_ & 0x00000200) != 0)) {
                result.network_ = network_;
            }
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.plugin.livekit.core.proto.models.ClientInfo) {
                return mergeFrom((im.turms.plugin.livekit.core.proto.models.ClientInfo) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(im.turms.plugin.livekit.core.proto.models.ClientInfo other) {
            if (other == im.turms.plugin.livekit.core.proto.models.ClientInfo
                    .getDefaultInstance()) {
                return this;
            }
            if (other.sdk_ != 0) {
                setSdkValue(other.getSdkValue());
            }
            if (!other.getVersion()
                    .isEmpty()) {
                version_ = other.version_;
                bitField0_ |= 0x00000002;
                onChanged();
            }
            if (other.getProtocol() != 0) {
                setProtocol(other.getProtocol());
            }
            if (!other.getOs()
                    .isEmpty()) {
                os_ = other.os_;
                bitField0_ |= 0x00000008;
                onChanged();
            }
            if (!other.getOsVersion()
                    .isEmpty()) {
                osVersion_ = other.osVersion_;
                bitField0_ |= 0x00000010;
                onChanged();
            }
            if (!other.getDeviceModel()
                    .isEmpty()) {
                deviceModel_ = other.deviceModel_;
                bitField0_ |= 0x00000020;
                onChanged();
            }
            if (!other.getBrowser()
                    .isEmpty()) {
                browser_ = other.browser_;
                bitField0_ |= 0x00000040;
                onChanged();
            }
            if (!other.getBrowserVersion()
                    .isEmpty()) {
                browserVersion_ = other.browserVersion_;
                bitField0_ |= 0x00000080;
                onChanged();
            }
            if (!other.getAddress()
                    .isEmpty()) {
                address_ = other.address_;
                bitField0_ |= 0x00000100;
                onChanged();
            }
            if (!other.getNetwork()
                    .isEmpty()) {
                network_ = other.network_;
                bitField0_ |= 0x00000200;
                onChanged();
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
                        case 8 -> {
                            sdk_ = input.readEnum();
                            bitField0_ |= 0x00000001;
                        } // case 8
                        case 18 -> {
                            version_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000002;
                        } // case 18
                        case 24 -> {
                            protocol_ = input.readInt32();
                            bitField0_ |= 0x00000004;
                        } // case 24
                        case 34 -> {
                            os_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000008;
                        } // case 34
                        case 42 -> {
                            osVersion_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000010;
                        } // case 42
                        case 50 -> {
                            deviceModel_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000020;
                        } // case 50
                        case 58 -> {
                            browser_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000040;
                        } // case 58
                        case 66 -> {
                            browserVersion_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000080;
                        } // case 66
                        case 74 -> {
                            address_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000100;
                        } // case 74
                        case 82 -> {
                            network_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000200;
                        } // case 82
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

        private int sdk_ = 0;

        /**
         * <code>.livekit.ClientInfo.SDK sdk = 1;</code>
         *
         * @return The enum numeric value on the wire for sdk.
         */
        @java.lang.Override
        public int getSdkValue() {
            return sdk_;
        }

        /**
         * <code>.livekit.ClientInfo.SDK sdk = 1;</code>
         *
         * @param value The enum numeric value on the wire for sdk to set.
         * @return This builder for chaining.
         */
        public Builder setSdkValue(int value) {
            sdk_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.ClientInfo.SDK sdk = 1;</code>
         *
         * @return The sdk.
         */
        @java.lang.Override
        public im.turms.plugin.livekit.core.proto.models.ClientInfo.SDK getSdk() {
            im.turms.plugin.livekit.core.proto.models.ClientInfo.SDK result =
                    im.turms.plugin.livekit.core.proto.models.ClientInfo.SDK.forNumber(sdk_);
            return result == null
                    ? im.turms.plugin.livekit.core.proto.models.ClientInfo.SDK.UNRECOGNIZED
                    : result;
        }

        /**
         * <code>.livekit.ClientInfo.SDK sdk = 1;</code>
         *
         * @param value The sdk to set.
         * @return This builder for chaining.
         */
        public Builder setSdk(im.turms.plugin.livekit.core.proto.models.ClientInfo.SDK value) {
            if (value == null) {
                throw new NullPointerException();
            }
            bitField0_ |= 0x00000001;
            sdk_ = value.getNumber();
            onChanged();
            return this;
        }

        /**
         * <code>.livekit.ClientInfo.SDK sdk = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearSdk() {
            bitField0_ &= ~0x00000001;
            sdk_ = 0;
            onChanged();
            return this;
        }

        private java.lang.Object version_ = "";

        /**
         * <code>string version = 2;</code>
         *
         * @return The version.
         */
        public java.lang.String getVersion() {
            java.lang.Object ref = version_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                version_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>string version = 2;</code>
         *
         * @return The bytes for version.
         */
        public com.google.protobuf.ByteString getVersionBytes() {
            java.lang.Object ref = version_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                version_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>string version = 2;</code>
         *
         * @param value The version to set.
         * @return This builder for chaining.
         */
        public Builder setVersion(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            version_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>string version = 2;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearVersion() {
            version_ = getDefaultInstance().getVersion();
            bitField0_ &= ~0x00000002;
            onChanged();
            return this;
        }

        /**
         * <code>string version = 2;</code>
         *
         * @param value The bytes for version to set.
         * @return This builder for chaining.
         */
        public Builder setVersionBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            version_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
            return this;
        }

        private int protocol_;

        /**
         * <code>int32 protocol = 3;</code>
         *
         * @return The protocol.
         */
        @java.lang.Override
        public int getProtocol() {
            return protocol_;
        }

        /**
         * <code>int32 protocol = 3;</code>
         *
         * @param value The protocol to set.
         * @return This builder for chaining.
         */
        public Builder setProtocol(int value) {

            protocol_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>int32 protocol = 3;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearProtocol() {
            bitField0_ &= ~0x00000004;
            protocol_ = 0;
            onChanged();
            return this;
        }

        private java.lang.Object os_ = "";

        /**
         * <code>string os = 4;</code>
         *
         * @return The os.
         */
        public java.lang.String getOs() {
            java.lang.Object ref = os_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                os_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>string os = 4;</code>
         *
         * @return The bytes for os.
         */
        public com.google.protobuf.ByteString getOsBytes() {
            java.lang.Object ref = os_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                os_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>string os = 4;</code>
         *
         * @param value The os to set.
         * @return This builder for chaining.
         */
        public Builder setOs(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            os_ = value;
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        /**
         * <code>string os = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearOs() {
            os_ = getDefaultInstance().getOs();
            bitField0_ &= ~0x00000008;
            onChanged();
            return this;
        }

        /**
         * <code>string os = 4;</code>
         *
         * @param value The bytes for os to set.
         * @return This builder for chaining.
         */
        public Builder setOsBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            os_ = value;
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        private java.lang.Object osVersion_ = "";

        /**
         * <code>string os_version = 5;</code>
         *
         * @return The osVersion.
         */
        public java.lang.String getOsVersion() {
            java.lang.Object ref = osVersion_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                osVersion_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>string os_version = 5;</code>
         *
         * @return The bytes for osVersion.
         */
        public com.google.protobuf.ByteString getOsVersionBytes() {
            java.lang.Object ref = osVersion_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                osVersion_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>string os_version = 5;</code>
         *
         * @param value The osVersion to set.
         * @return This builder for chaining.
         */
        public Builder setOsVersion(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            osVersion_ = value;
            bitField0_ |= 0x00000010;
            onChanged();
            return this;
        }

        /**
         * <code>string os_version = 5;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearOsVersion() {
            osVersion_ = getDefaultInstance().getOsVersion();
            bitField0_ &= ~0x00000010;
            onChanged();
            return this;
        }

        /**
         * <code>string os_version = 5;</code>
         *
         * @param value The bytes for osVersion to set.
         * @return This builder for chaining.
         */
        public Builder setOsVersionBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            osVersion_ = value;
            bitField0_ |= 0x00000010;
            onChanged();
            return this;
        }

        private java.lang.Object deviceModel_ = "";

        /**
         * <code>string device_model = 6;</code>
         *
         * @return The deviceModel.
         */
        public java.lang.String getDeviceModel() {
            java.lang.Object ref = deviceModel_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                deviceModel_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>string device_model = 6;</code>
         *
         * @return The bytes for deviceModel.
         */
        public com.google.protobuf.ByteString getDeviceModelBytes() {
            java.lang.Object ref = deviceModel_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                deviceModel_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>string device_model = 6;</code>
         *
         * @param value The deviceModel to set.
         * @return This builder for chaining.
         */
        public Builder setDeviceModel(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            deviceModel_ = value;
            bitField0_ |= 0x00000020;
            onChanged();
            return this;
        }

        /**
         * <code>string device_model = 6;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearDeviceModel() {
            deviceModel_ = getDefaultInstance().getDeviceModel();
            bitField0_ &= ~0x00000020;
            onChanged();
            return this;
        }

        /**
         * <code>string device_model = 6;</code>
         *
         * @param value The bytes for deviceModel to set.
         * @return This builder for chaining.
         */
        public Builder setDeviceModelBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            deviceModel_ = value;
            bitField0_ |= 0x00000020;
            onChanged();
            return this;
        }

        private java.lang.Object browser_ = "";

        /**
         * <code>string browser = 7;</code>
         *
         * @return The browser.
         */
        public java.lang.String getBrowser() {
            java.lang.Object ref = browser_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                browser_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>string browser = 7;</code>
         *
         * @return The bytes for browser.
         */
        public com.google.protobuf.ByteString getBrowserBytes() {
            java.lang.Object ref = browser_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                browser_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>string browser = 7;</code>
         *
         * @param value The browser to set.
         * @return This builder for chaining.
         */
        public Builder setBrowser(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            browser_ = value;
            bitField0_ |= 0x00000040;
            onChanged();
            return this;
        }

        /**
         * <code>string browser = 7;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearBrowser() {
            browser_ = getDefaultInstance().getBrowser();
            bitField0_ &= ~0x00000040;
            onChanged();
            return this;
        }

        /**
         * <code>string browser = 7;</code>
         *
         * @param value The bytes for browser to set.
         * @return This builder for chaining.
         */
        public Builder setBrowserBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            browser_ = value;
            bitField0_ |= 0x00000040;
            onChanged();
            return this;
        }

        private java.lang.Object browserVersion_ = "";

        /**
         * <code>string browser_version = 8;</code>
         *
         * @return The browserVersion.
         */
        public java.lang.String getBrowserVersion() {
            java.lang.Object ref = browserVersion_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                browserVersion_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>string browser_version = 8;</code>
         *
         * @return The bytes for browserVersion.
         */
        public com.google.protobuf.ByteString getBrowserVersionBytes() {
            java.lang.Object ref = browserVersion_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                browserVersion_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>string browser_version = 8;</code>
         *
         * @param value The browserVersion to set.
         * @return This builder for chaining.
         */
        public Builder setBrowserVersion(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            browserVersion_ = value;
            bitField0_ |= 0x00000080;
            onChanged();
            return this;
        }

        /**
         * <code>string browser_version = 8;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearBrowserVersion() {
            browserVersion_ = getDefaultInstance().getBrowserVersion();
            bitField0_ &= ~0x00000080;
            onChanged();
            return this;
        }

        /**
         * <code>string browser_version = 8;</code>
         *
         * @param value The bytes for browserVersion to set.
         * @return This builder for chaining.
         */
        public Builder setBrowserVersionBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            browserVersion_ = value;
            bitField0_ |= 0x00000080;
            onChanged();
            return this;
        }

        private java.lang.Object address_ = "";

        /**
         * <code>string address = 9;</code>
         *
         * @return The address.
         */
        public java.lang.String getAddress() {
            java.lang.Object ref = address_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                address_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>string address = 9;</code>
         *
         * @return The bytes for address.
         */
        public com.google.protobuf.ByteString getAddressBytes() {
            java.lang.Object ref = address_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                address_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>string address = 9;</code>
         *
         * @param value The address to set.
         * @return This builder for chaining.
         */
        public Builder setAddress(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            address_ = value;
            bitField0_ |= 0x00000100;
            onChanged();
            return this;
        }

        /**
         * <code>string address = 9;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearAddress() {
            address_ = getDefaultInstance().getAddress();
            bitField0_ &= ~0x00000100;
            onChanged();
            return this;
        }

        /**
         * <code>string address = 9;</code>
         *
         * @param value The bytes for address to set.
         * @return This builder for chaining.
         */
        public Builder setAddressBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            address_ = value;
            bitField0_ |= 0x00000100;
            onChanged();
            return this;
        }

        private java.lang.Object network_ = "";

        /**
         * <pre>
         * wifi, wired, cellular, vpn, empty if not known
         * </pre>
         *
         * <code>string network = 10;</code>
         *
         * @return The network.
         */
        public java.lang.String getNetwork() {
            java.lang.Object ref = network_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                network_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <pre>
         * wifi, wired, cellular, vpn, empty if not known
         * </pre>
         *
         * <code>string network = 10;</code>
         *
         * @return The bytes for network.
         */
        public com.google.protobuf.ByteString getNetworkBytes() {
            java.lang.Object ref = network_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                network_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <pre>
         * wifi, wired, cellular, vpn, empty if not known
         * </pre>
         *
         * <code>string network = 10;</code>
         *
         * @param value The network to set.
         * @return This builder for chaining.
         */
        public Builder setNetwork(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            network_ = value;
            bitField0_ |= 0x00000200;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * wifi, wired, cellular, vpn, empty if not known
         * </pre>
         *
         * <code>string network = 10;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearNetwork() {
            network_ = getDefaultInstance().getNetwork();
            bitField0_ &= ~0x00000200;
            onChanged();
            return this;
        }

        /**
         * <pre>
         * wifi, wired, cellular, vpn, empty if not known
         * </pre>
         *
         * <code>string network = 10;</code>
         *
         * @param value The bytes for network to set.
         * @return This builder for chaining.
         */
        public Builder setNetworkBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            network_ = value;
            bitField0_ |= 0x00000200;
            onChanged();
            return this;
        }

        // @@protoc_insertion_point(builder_scope:livekit.ClientInfo)
    }

    // @@protoc_insertion_point(class_scope:livekit.ClientInfo)
    private static final im.turms.plugin.livekit.core.proto.models.ClientInfo DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE = new im.turms.plugin.livekit.core.proto.models.ClientInfo();
    }

    public static im.turms.plugin.livekit.core.proto.models.ClientInfo getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<ClientInfo> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public ClientInfo parsePartialFrom(
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

    public static com.google.protobuf.Parser<ClientInfo> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<ClientInfo> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.plugin.livekit.core.proto.models.ClientInfo getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}