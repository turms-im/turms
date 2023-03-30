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

package im.turms.server.common.access.client.dto.constant;

/**
 * Protobuf enum {@code im.turms.proto.DeviceType}
 */
public enum DeviceType implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <code>DESKTOP = 0;</code>
     */
    DESKTOP(0),
    /**
     * <code>BROWSER = 1;</code>
     */
    BROWSER(1),
    /**
     * <code>IOS = 2;</code>
     */
    IOS(2),
    /**
     * <code>ANDROID = 3;</code>
     */
    ANDROID(3),
    /**
     * <code>OTHERS = 4;</code>
     */
    OTHERS(4),
    /**
     * <code>UNKNOWN = 5;</code>
     */
    UNKNOWN(5),
    UNRECOGNIZED(-1),;

    /**
     * <code>DESKTOP = 0;</code>
     */
    public static final int DESKTOP_VALUE = 0;
    /**
     * <code>BROWSER = 1;</code>
     */
    public static final int BROWSER_VALUE = 1;
    /**
     * <code>IOS = 2;</code>
     */
    public static final int IOS_VALUE = 2;
    /**
     * <code>ANDROID = 3;</code>
     */
    public static final int ANDROID_VALUE = 3;
    /**
     * <code>OTHERS = 4;</code>
     */
    public static final int OTHERS_VALUE = 4;
    /**
     * <code>UNKNOWN = 5;</code>
     */
    public static final int UNKNOWN_VALUE = 5;

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
    public static DeviceType valueOf(int value) {
        return forNumber(value);
    }

    /**
     * @param value The numeric wire value of the corresponding enum entry.
     * @return The enum associated with the given numeric wire value.
     */
    public static DeviceType forNumber(int value) {
        return switch (value) {
            case 0 -> DESKTOP;
            case 1 -> BROWSER;
            case 2 -> IOS;
            case 3 -> ANDROID;
            case 4 -> OTHERS;
            case 5 -> UNKNOWN;
            default -> null;
        };
    }

    public static com.google.protobuf.Internal.EnumLiteMap<DeviceType> internalGetValueMap() {
        return internalValueMap;
    }

    private static final com.google.protobuf.Internal.EnumLiteMap<DeviceType> internalValueMap =
            number -> DeviceType.forNumber(number);

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
        return im.turms.server.common.access.client.dto.constant.DeviceTypeOuterClass
                .getDescriptor()
                .getEnumTypes()
                .get(0);
    }

    private static final DeviceType[] VALUES = values();

    public static DeviceType valueOf(com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
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

    DeviceType(int value) {
        this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:im.turms.proto.DeviceType)
}