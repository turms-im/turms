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
 * Protobuf enum {@code im.turms.proto.ResponseAction}
 */
public enum ResponseAction implements com.google.protobuf.Internal.EnumLite {
    /**
     * <code>ACCEPT = 0;</code>
     */
    ACCEPT(0),
    /**
     * <code>DECLINE = 1;</code>
     */
    DECLINE(1),
    /**
     * <code>IGNORE = 2;</code>
     */
    IGNORE(2),
    UNRECOGNIZED(-1),;

    /**
     * <code>ACCEPT = 0;</code>
     */
    public static final int ACCEPT_VALUE = 0;
    /**
     * <code>DECLINE = 1;</code>
     */
    public static final int DECLINE_VALUE = 1;
    /**
     * <code>IGNORE = 2;</code>
     */
    public static final int IGNORE_VALUE = 2;

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
    public static ResponseAction valueOf(int value) {
        return forNumber(value);
    }

    public static ResponseAction forNumber(int value) {
        switch (value) {
            case 0:
                return ACCEPT;
            case 1:
                return DECLINE;
            case 2:
                return IGNORE;
            default:
                return null;
        }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<ResponseAction> internalGetValueMap() {
        return internalValueMap;
    }

    private static final com.google.protobuf.Internal.EnumLiteMap<ResponseAction> internalValueMap =
            number -> ResponseAction.forNumber(number);

    public static com.google.protobuf.Internal.EnumVerifier internalGetVerifier() {
        return ResponseActionVerifier.INSTANCE;
    }

    private static final class ResponseActionVerifier
            implements com.google.protobuf.Internal.EnumVerifier {
        static final com.google.protobuf.Internal.EnumVerifier INSTANCE =
                new ResponseActionVerifier();

        @java.lang.Override
        public boolean isInRange(int number) {
            return ResponseAction.forNumber(number) != null;
        }
    }

    private final int value;

    ResponseAction(int value) {
        this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:im.turms.proto.ResponseAction)
}