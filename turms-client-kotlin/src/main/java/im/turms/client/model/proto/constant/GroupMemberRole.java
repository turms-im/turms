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
 * Protobuf enum {@code im.turms.proto.GroupMemberRole}
 */
public enum GroupMemberRole implements com.google.protobuf.Internal.EnumLite {
    /**
     * <code>OWNER = 0;</code>
     */
    OWNER(0),
    /**
     * <code>MANAGER = 1;</code>
     */
    MANAGER(1),
    /**
     * <code>MEMBER = 2;</code>
     */
    MEMBER(2),
    /**
     * <code>GUEST = 3;</code>
     */
    GUEST(3),
    /**
     * <code>ANONYMOUS_GUEST = 4;</code>
     */
    ANONYMOUS_GUEST(4),
    UNRECOGNIZED(-1),;

    /**
     * <code>OWNER = 0;</code>
     */
    public static final int OWNER_VALUE = 0;
    /**
     * <code>MANAGER = 1;</code>
     */
    public static final int MANAGER_VALUE = 1;
    /**
     * <code>MEMBER = 2;</code>
     */
    public static final int MEMBER_VALUE = 2;
    /**
     * <code>GUEST = 3;</code>
     */
    public static final int GUEST_VALUE = 3;
    /**
     * <code>ANONYMOUS_GUEST = 4;</code>
     */
    public static final int ANONYMOUS_GUEST_VALUE = 4;

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
    public static GroupMemberRole valueOf(int value) {
        return forNumber(value);
    }

    public static GroupMemberRole forNumber(int value) {
        switch (value) {
            case 0:
                return OWNER;
            case 1:
                return MANAGER;
            case 2:
                return MEMBER;
            case 3:
                return GUEST;
            case 4:
                return ANONYMOUS_GUEST;
            default:
                return null;
        }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<GroupMemberRole> internalGetValueMap() {
        return internalValueMap;
    }

    private static final com.google.protobuf.Internal.EnumLiteMap<GroupMemberRole> internalValueMap =
            number -> GroupMemberRole.forNumber(number);

    public static com.google.protobuf.Internal.EnumVerifier internalGetVerifier() {
        return GroupMemberRoleVerifier.INSTANCE;
    }

    private static final class GroupMemberRoleVerifier
            implements com.google.protobuf.Internal.EnumVerifier {
        static final com.google.protobuf.Internal.EnumVerifier INSTANCE =
                new GroupMemberRoleVerifier();

        @java.lang.Override
        public boolean isInRange(int number) {
            return GroupMemberRole.forNumber(number) != null;
        }
    }

    private final int value;

    GroupMemberRole(int value) {
        this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:im.turms.proto.GroupMemberRole)
}