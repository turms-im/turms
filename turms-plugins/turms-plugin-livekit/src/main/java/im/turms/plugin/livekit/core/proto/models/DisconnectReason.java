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

package im.turms.plugin.livekit.core.proto.models;

/**
 * Protobuf enum {@code livekit.DisconnectReason}
 */
public enum DisconnectReason implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <code>UNKNOWN_REASON = 0;</code>
     */
    UNKNOWN_REASON(0),
    /**
     * <code>CLIENT_INITIATED = 1;</code>
     */
    CLIENT_INITIATED(1),
    /**
     * <code>DUPLICATE_IDENTITY = 2;</code>
     */
    DUPLICATE_IDENTITY(2),
    /**
     * <code>SERVER_SHUTDOWN = 3;</code>
     */
    SERVER_SHUTDOWN(3),
    /**
     * <code>PARTICIPANT_REMOVED = 4;</code>
     */
    PARTICIPANT_REMOVED(4),
    /**
     * <code>ROOM_DELETED = 5;</code>
     */
    ROOM_DELETED(5),
    /**
     * <code>STATE_MISMATCH = 6;</code>
     */
    STATE_MISMATCH(6),
    /**
     * <code>JOIN_FAILURE = 7;</code>
     */
    JOIN_FAILURE(7),
    /**
     * <code>MIGRATION = 8;</code>
     */
    MIGRATION(8),
    /**
     * <code>SIGNAL_CLOSE = 9;</code>
     */
    SIGNAL_CLOSE(9),
    UNRECOGNIZED(-1),;

    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                DisconnectReason.class.getName());
    }
    /**
     * <code>UNKNOWN_REASON = 0;</code>
     */
    public static final int UNKNOWN_REASON_VALUE = 0;
    /**
     * <code>CLIENT_INITIATED = 1;</code>
     */
    public static final int CLIENT_INITIATED_VALUE = 1;
    /**
     * <code>DUPLICATE_IDENTITY = 2;</code>
     */
    public static final int DUPLICATE_IDENTITY_VALUE = 2;
    /**
     * <code>SERVER_SHUTDOWN = 3;</code>
     */
    public static final int SERVER_SHUTDOWN_VALUE = 3;
    /**
     * <code>PARTICIPANT_REMOVED = 4;</code>
     */
    public static final int PARTICIPANT_REMOVED_VALUE = 4;
    /**
     * <code>ROOM_DELETED = 5;</code>
     */
    public static final int ROOM_DELETED_VALUE = 5;
    /**
     * <code>STATE_MISMATCH = 6;</code>
     */
    public static final int STATE_MISMATCH_VALUE = 6;
    /**
     * <code>JOIN_FAILURE = 7;</code>
     */
    public static final int JOIN_FAILURE_VALUE = 7;
    /**
     * <code>MIGRATION = 8;</code>
     */
    public static final int MIGRATION_VALUE = 8;
    /**
     * <code>SIGNAL_CLOSE = 9;</code>
     */
    public static final int SIGNAL_CLOSE_VALUE = 9;

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
    public static DisconnectReason valueOf(int value) {
        return forNumber(value);
    }

    /**
     * @param value The numeric wire value of the corresponding enum entry.
     * @return The enum associated with the given numeric wire value.
     */
    public static DisconnectReason forNumber(int value) {
        return switch (value) {
            case 0 -> UNKNOWN_REASON;
            case 1 -> CLIENT_INITIATED;
            case 2 -> DUPLICATE_IDENTITY;
            case 3 -> SERVER_SHUTDOWN;
            case 4 -> PARTICIPANT_REMOVED;
            case 5 -> ROOM_DELETED;
            case 6 -> STATE_MISMATCH;
            case 7 -> JOIN_FAILURE;
            case 8 -> MIGRATION;
            case 9 -> SIGNAL_CLOSE;
            default -> null;
        };
    }

    public static com.google.protobuf.Internal.EnumLiteMap<DisconnectReason> internalGetValueMap() {
        return internalValueMap;
    }

    private static final com.google.protobuf.Internal.EnumLiteMap<DisconnectReason> internalValueMap =
            DisconnectReason::forNumber;

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
        return im.turms.plugin.livekit.core.proto.models.LivekitModels.getDescriptor()
                .getEnumTypes()
                .get(8);
    }

    private static final DisconnectReason[] VALUES = values();

    public static DisconnectReason valueOf(
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

    DisconnectReason(int value) {
        this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:livekit.DisconnectReason)
}