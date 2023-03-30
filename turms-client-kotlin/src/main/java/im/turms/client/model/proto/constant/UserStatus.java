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
 * Protobuf enum {@code im.turms.proto.UserStatus}
 */
public enum UserStatus implements com.google.protobuf.Internal.EnumLite {
    /**
     * <code>AVAILABLE = 0;</code>
     */
    AVAILABLE(0),
    /**
     * <code>OFFLINE = 1;</code>
     */
    OFFLINE(1),
    /**
     * <code>INVISIBLE = 2;</code>
     */
    INVISIBLE(2),
    /**
     * <code>BUSY = 3;</code>
     */
    BUSY(3),
    /**
     * <code>DO_NOT_DISTURB = 4;</code>
     */
    DO_NOT_DISTURB(4),
    /**
     * <code>AWAY = 5;</code>
     */
    AWAY(5),
    /**
     * <code>BE_RIGHT_BACK = 6;</code>
     */
    BE_RIGHT_BACK(6),
    UNRECOGNIZED(-1),;

    /**
     * <code>AVAILABLE = 0;</code>
     */
    public static final int AVAILABLE_VALUE = 0;
    /**
     * <code>OFFLINE = 1;</code>
     */
    public static final int OFFLINE_VALUE = 1;
    /**
     * <code>INVISIBLE = 2;</code>
     */
    public static final int INVISIBLE_VALUE = 2;
    /**
     * <code>BUSY = 3;</code>
     */
    public static final int BUSY_VALUE = 3;
    /**
     * <code>DO_NOT_DISTURB = 4;</code>
     */
    public static final int DO_NOT_DISTURB_VALUE = 4;
    /**
     * <code>AWAY = 5;</code>
     */
    public static final int AWAY_VALUE = 5;
    /**
     * <code>BE_RIGHT_BACK = 6;</code>
     */
    public static final int BE_RIGHT_BACK_VALUE = 6;

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
    public static UserStatus valueOf(int value) {
        return forNumber(value);
    }

    public static UserStatus forNumber(int value) {
        switch (value) {
            case 0:
                return AVAILABLE;
            case 1:
                return OFFLINE;
            case 2:
                return INVISIBLE;
            case 3:
                return BUSY;
            case 4:
                return DO_NOT_DISTURB;
            case 5:
                return AWAY;
            case 6:
                return BE_RIGHT_BACK;
            default:
                return null;
        }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<UserStatus> internalGetValueMap() {
        return internalValueMap;
    }

    private static final com.google.protobuf.Internal.EnumLiteMap<UserStatus> internalValueMap =
            number -> UserStatus.forNumber(number);

    public static com.google.protobuf.Internal.EnumVerifier internalGetVerifier() {
        return UserStatusVerifier.INSTANCE;
    }

    private static final class UserStatusVerifier
            implements com.google.protobuf.Internal.EnumVerifier {
        static final com.google.protobuf.Internal.EnumVerifier INSTANCE = new UserStatusVerifier();

        @java.lang.Override
        public boolean isInRange(int number) {
            return UserStatus.forNumber(number) != null;
        }
    }

    private final int value;

    UserStatus(int value) {
        this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:im.turms.proto.UserStatus)
}