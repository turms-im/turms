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
 * Protobuf enum {@code im.turms.proto.ProfileAccessStrategy}
 */
public enum ProfileAccessStrategy implements com.google.protobuf.Internal.EnumLite {
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
    public static ProfileAccessStrategy valueOf(int value) {
        return forNumber(value);
    }

    public static ProfileAccessStrategy forNumber(int value) {
        switch (value) {
            case 0:
                return ALL;
            case 1:
                return ALL_EXCEPT_BLOCKED_USERS;
            case 2:
                return FRIENDS;
            default:
                return null;
        }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<ProfileAccessStrategy> internalGetValueMap() {
        return internalValueMap;
    }

    private static final com.google.protobuf.Internal.EnumLiteMap<ProfileAccessStrategy> internalValueMap =
            number -> ProfileAccessStrategy.forNumber(number);

    public static com.google.protobuf.Internal.EnumVerifier internalGetVerifier() {
        return ProfileAccessStrategyVerifier.INSTANCE;
    }

    private static final class ProfileAccessStrategyVerifier
            implements com.google.protobuf.Internal.EnumVerifier {
        static final com.google.protobuf.Internal.EnumVerifier INSTANCE =
                new ProfileAccessStrategyVerifier();

        @java.lang.Override
        public boolean isInRange(int number) {
            return ProfileAccessStrategy.forNumber(number) != null;
        }
    }

    private final int value;

    ProfileAccessStrategy(int value) {
        this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:im.turms.proto.ProfileAccessStrategy)
}