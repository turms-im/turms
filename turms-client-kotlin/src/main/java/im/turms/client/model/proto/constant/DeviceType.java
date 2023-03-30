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

package im.turms.client.model.proto.constant;

/**
 * Protobuf enum {@code im.turms.proto.DeviceType}
 */
public enum DeviceType implements com.google.protobuf.Internal.EnumLite {
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

    @java.lang.Override
    public final int getNumber() {
        if (this == UNRECOGNIZED) {
            throw new java.lang.IllegalArgumentException(
                    "Can't get the number of an unknown enum value.");
        }
        return value;
    }

    /**
     * @param value The number of the enum to look for.
     * @return The enum associated with the given number.
     * @deprecated Use {@link #forNumber(int)} instead.
     */
    @java.lang.Deprecated
    public static DeviceType valueOf(int value) {
        return forNumber(value);
    }

    public static DeviceType forNumber(int value) {
        switch (value) {
            case 0:
                return DESKTOP;
            case 1:
                return BROWSER;
            case 2:
                return IOS;
            case 3:
                return ANDROID;
            case 4:
                return OTHERS;
            case 5:
                return UNKNOWN;
            default:
                return null;
        }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<DeviceType> internalGetValueMap() {
        return internalValueMap;
    }

    private static final com.google.protobuf.Internal.EnumLiteMap<DeviceType> internalValueMap =
            number -> DeviceType.forNumber(number);

    public static com.google.protobuf.Internal.EnumVerifier internalGetVerifier() {
        return DeviceTypeVerifier.INSTANCE;
    }

    private static final class DeviceTypeVerifier
            implements com.google.protobuf.Internal.EnumVerifier {
        static final com.google.protobuf.Internal.EnumVerifier INSTANCE = new DeviceTypeVerifier();

        @java.lang.Override
        public boolean isInRange(int number) {
            return DeviceType.forNumber(number) != null;
        }
    }

    private final int value;

    DeviceType(int value) {
        this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:im.turms.proto.DeviceType)
}