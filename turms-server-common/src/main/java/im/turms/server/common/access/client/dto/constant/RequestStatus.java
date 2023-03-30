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
 * Protobuf enum {@code im.turms.proto.RequestStatus}
 */
public enum RequestStatus implements com.google.protobuf.ProtocolMessageEnum {
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
    public static RequestStatus valueOf(int value) {
        return forNumber(value);
    }

    /**
     * @param value The numeric wire value of the corresponding enum entry.
     * @return The enum associated with the given numeric wire value.
     */
    public static RequestStatus forNumber(int value) {
        return switch (value) {
            case 0 -> PENDING;
            case 1 -> ACCEPTED;
            case 2 -> ACCEPTED_WITHOUT_CONFIRM;
            case 3 -> DECLINED;
            case 4 -> IGNORED;
            case 5 -> EXPIRED;
            case 6 -> CANCELED;
            default -> null;
        };
    }

    public static com.google.protobuf.Internal.EnumLiteMap<RequestStatus> internalGetValueMap() {
        return internalValueMap;
    }

    private static final com.google.protobuf.Internal.EnumLiteMap<RequestStatus> internalValueMap =
            number -> RequestStatus.forNumber(number);

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
        return im.turms.server.common.access.client.dto.constant.RequestStatusOuterClass
                .getDescriptor()
                .getEnumTypes()
                .get(0);
    }

    private static final RequestStatus[] VALUES = values();

    public static RequestStatus valueOf(com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
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

    RequestStatus(int value) {
        this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:im.turms.proto.RequestStatus)
}