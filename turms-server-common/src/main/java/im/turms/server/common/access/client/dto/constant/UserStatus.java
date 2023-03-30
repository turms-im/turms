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
 * Protobuf enum {@code im.turms.proto.UserStatus}
 */
public enum UserStatus implements com.google.protobuf.ProtocolMessageEnum {
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
    public static UserStatus valueOf(int value) {
        return forNumber(value);
    }

    /**
     * @param value The numeric wire value of the corresponding enum entry.
     * @return The enum associated with the given numeric wire value.
     */
    public static UserStatus forNumber(int value) {
        return switch (value) {
            case 0 -> AVAILABLE;
            case 1 -> OFFLINE;
            case 2 -> INVISIBLE;
            case 3 -> BUSY;
            case 4 -> DO_NOT_DISTURB;
            case 5 -> AWAY;
            case 6 -> BE_RIGHT_BACK;
            default -> null;
        };
    }

    public static com.google.protobuf.Internal.EnumLiteMap<UserStatus> internalGetValueMap() {
        return internalValueMap;
    }

    private static final com.google.protobuf.Internal.EnumLiteMap<UserStatus> internalValueMap =
            number -> UserStatus.forNumber(number);

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
        return im.turms.server.common.access.client.dto.constant.UserStatusOuterClass
                .getDescriptor()
                .getEnumTypes()
                .get(0);
    }

    private static final UserStatus[] VALUES = values();

    public static UserStatus valueOf(com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
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

    UserStatus(int value) {
        this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:im.turms.proto.UserStatus)
}