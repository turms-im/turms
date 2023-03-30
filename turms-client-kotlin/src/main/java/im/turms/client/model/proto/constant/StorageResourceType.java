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
 * Protobuf enum {@code im.turms.proto.StorageResourceType}
 */
public enum StorageResourceType implements com.google.protobuf.Internal.EnumLite {
    /**
     * <code>USER_PROFILE_PICTURE = 0;</code>
     */
    USER_PROFILE_PICTURE(0),
    /**
     * <code>GROUP_PROFILE_PICTURE = 1;</code>
     */
    GROUP_PROFILE_PICTURE(1),
    /**
     * <code>MESSAGE_ATTACHMENT = 2;</code>
     */
    MESSAGE_ATTACHMENT(2),
    UNRECOGNIZED(-1),;

    /**
     * <code>USER_PROFILE_PICTURE = 0;</code>
     */
    public static final int USER_PROFILE_PICTURE_VALUE = 0;
    /**
     * <code>GROUP_PROFILE_PICTURE = 1;</code>
     */
    public static final int GROUP_PROFILE_PICTURE_VALUE = 1;
    /**
     * <code>MESSAGE_ATTACHMENT = 2;</code>
     */
    public static final int MESSAGE_ATTACHMENT_VALUE = 2;

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
    public static StorageResourceType valueOf(int value) {
        return forNumber(value);
    }

    public static StorageResourceType forNumber(int value) {
        switch (value) {
            case 0:
                return USER_PROFILE_PICTURE;
            case 1:
                return GROUP_PROFILE_PICTURE;
            case 2:
                return MESSAGE_ATTACHMENT;
            default:
                return null;
        }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<StorageResourceType> internalGetValueMap() {
        return internalValueMap;
    }

    private static final com.google.protobuf.Internal.EnumLiteMap<StorageResourceType> internalValueMap =
            number -> StorageResourceType.forNumber(number);

    public static com.google.protobuf.Internal.EnumVerifier internalGetVerifier() {
        return StorageResourceTypeVerifier.INSTANCE;
    }

    private static final class StorageResourceTypeVerifier
            implements com.google.protobuf.Internal.EnumVerifier {
        static final com.google.protobuf.Internal.EnumVerifier INSTANCE =
                new StorageResourceTypeVerifier();

        @java.lang.Override
        public boolean isInRange(int number) {
            return StorageResourceType.forNumber(number) != null;
        }
    }

    private final int value;

    StorageResourceType(int value) {
        this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:im.turms.proto.StorageResourceType)
}