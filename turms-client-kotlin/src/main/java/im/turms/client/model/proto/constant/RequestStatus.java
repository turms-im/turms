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
 * Protobuf enum {@code im.turms.proto.RequestStatus}
 */
public enum RequestStatus implements com.google.protobuf.Internal.EnumLite {
    /**
     * <code>PENDING = 0;</code>
     */
    PENDING(0),
    /**
     * <code>ACCEPTED = 1;</code>
     */
    ACCEPTED(1),
    /**
     * <code>ACCEPTED_WITHOUT_CONFIRM = 2;</code>
     */
    ACCEPTED_WITHOUT_CONFIRM(2),
    /**
     * <code>DECLINED = 3;</code>
     */
    DECLINED(3),
    /**
     * <code>IGNORED = 4;</code>
     */
    IGNORED(4),
    /**
     * <code>EXPIRED = 5;</code>
     */
    EXPIRED(5),
    /**
     * <code>CANCELED = 6;</code>
     */
    CANCELED(6),
    UNRECOGNIZED(-1),;

    /**
     * <code>PENDING = 0;</code>
     */
    public static final int PENDING_VALUE = 0;
    /**
     * <code>ACCEPTED = 1;</code>
     */
    public static final int ACCEPTED_VALUE = 1;
    /**
     * <code>ACCEPTED_WITHOUT_CONFIRM = 2;</code>
     */
    public static final int ACCEPTED_WITHOUT_CONFIRM_VALUE = 2;
    /**
     * <code>DECLINED = 3;</code>
     */
    public static final int DECLINED_VALUE = 3;
    /**
     * <code>IGNORED = 4;</code>
     */
    public static final int IGNORED_VALUE = 4;
    /**
     * <code>EXPIRED = 5;</code>
     */
    public static final int EXPIRED_VALUE = 5;
    /**
     * <code>CANCELED = 6;</code>
     */
    public static final int CANCELED_VALUE = 6;

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
    public static RequestStatus valueOf(int value) {
        return forNumber(value);
    }

    public static RequestStatus forNumber(int value) {
        switch (value) {
            case 0:
                return PENDING;
            case 1:
                return ACCEPTED;
            case 2:
                return ACCEPTED_WITHOUT_CONFIRM;
            case 3:
                return DECLINED;
            case 4:
                return IGNORED;
            case 5:
                return EXPIRED;
            case 6:
                return CANCELED;
            default:
                return null;
        }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<RequestStatus> internalGetValueMap() {
        return internalValueMap;
    }

    private static final com.google.protobuf.Internal.EnumLiteMap<RequestStatus> internalValueMap =
            number -> RequestStatus.forNumber(number);

    public static com.google.protobuf.Internal.EnumVerifier internalGetVerifier() {
        return RequestStatusVerifier.INSTANCE;
    }

    private static final class RequestStatusVerifier
            implements com.google.protobuf.Internal.EnumVerifier {
        static final com.google.protobuf.Internal.EnumVerifier INSTANCE =
                new RequestStatusVerifier();

        @java.lang.Override
        public boolean isInRange(int number) {
            return RequestStatus.forNumber(number) != null;
        }
    }

    private final int value;

    RequestStatus(int value) {
        this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:im.turms.proto.RequestStatus)
}