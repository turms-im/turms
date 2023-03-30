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
 * Protobuf enum {@code im.turms.proto.ProfileAccessStrategy}
 */
public enum ProfileAccessStrategy implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <code>ALL = 0;</code>
     */
    ALL(0),
    /**
     * <code>ALL_EXCEPT_BLOCKED_USERS = 1;</code>
     */
    ALL_EXCEPT_BLOCKED_USERS(1),
    /**
     * <code>FRIENDS = 2;</code>
     */
    FRIENDS(2),
    UNRECOGNIZED(-1),;

    /**
     * <code>ALL = 0;</code>
     */
    public static final int ALL_VALUE = 0;
    /**
     * <code>ALL_EXCEPT_BLOCKED_USERS = 1;</code>
     */
    public static final int ALL_EXCEPT_BLOCKED_USERS_VALUE = 1;
    /**
     * <code>FRIENDS = 2;</code>
     */
    public static final int FRIENDS_VALUE = 2;

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
    public static ProfileAccessStrategy valueOf(int value) {
        return forNumber(value);
    }

    /**
     * @param value The numeric wire value of the corresponding enum entry.
     * @return The enum associated with the given numeric wire value.
     */
    public static ProfileAccessStrategy forNumber(int value) {
        return switch (value) {
            case 0 -> ALL;
            case 1 -> ALL_EXCEPT_BLOCKED_USERS;
            case 2 -> FRIENDS;
            default -> null;
        };
    }

    public static com.google.protobuf.Internal.EnumLiteMap<ProfileAccessStrategy> internalGetValueMap() {
        return internalValueMap;
    }

    private static final com.google.protobuf.Internal.EnumLiteMap<ProfileAccessStrategy> internalValueMap =
            number -> ProfileAccessStrategy.forNumber(number);

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
        return im.turms.server.common.access.client.dto.constant.ProfileAccessStrategyOuterClass
                .getDescriptor()
                .getEnumTypes()
                .get(0);
    }

    private static final ProfileAccessStrategy[] VALUES = values();

    public static ProfileAccessStrategy valueOf(
            com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
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

    ProfileAccessStrategy(int value) {
        this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:im.turms.proto.ProfileAccessStrategy)
}