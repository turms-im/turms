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

package im.turms.plugin.livekit.core.proto.egress;

/**
 * Protobuf enum {@code livekit.EgressStatus}
 */
public enum EgressStatus implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <code>EGRESS_STARTING = 0;</code>
     */
    EGRESS_STARTING(0),
    /**
     * <code>EGRESS_ACTIVE = 1;</code>
     */
    EGRESS_ACTIVE(1),
    /**
     * <code>EGRESS_ENDING = 2;</code>
     */
    EGRESS_ENDING(2),
    /**
     * <code>EGRESS_COMPLETE = 3;</code>
     */
    EGRESS_COMPLETE(3),
    /**
     * <code>EGRESS_FAILED = 4;</code>
     */
    EGRESS_FAILED(4),
    /**
     * <code>EGRESS_ABORTED = 5;</code>
     */
    EGRESS_ABORTED(5),
    /**
     * <code>EGRESS_LIMIT_REACHED = 6;</code>
     */
    EGRESS_LIMIT_REACHED(6),
    UNRECOGNIZED(-1),;

    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                EgressStatus.class.getName());
    }
    /**
     * <code>EGRESS_STARTING = 0;</code>
     */
    public static final int EGRESS_STARTING_VALUE = 0;
    /**
     * <code>EGRESS_ACTIVE = 1;</code>
     */
    public static final int EGRESS_ACTIVE_VALUE = 1;
    /**
     * <code>EGRESS_ENDING = 2;</code>
     */
    public static final int EGRESS_ENDING_VALUE = 2;
    /**
     * <code>EGRESS_COMPLETE = 3;</code>
     */
    public static final int EGRESS_COMPLETE_VALUE = 3;
    /**
     * <code>EGRESS_FAILED = 4;</code>
     */
    public static final int EGRESS_FAILED_VALUE = 4;
    /**
     * <code>EGRESS_ABORTED = 5;</code>
     */
    public static final int EGRESS_ABORTED_VALUE = 5;
    /**
     * <code>EGRESS_LIMIT_REACHED = 6;</code>
     */
    public static final int EGRESS_LIMIT_REACHED_VALUE = 6;

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
    public static EgressStatus valueOf(int value) {
        return forNumber(value);
    }

    /**
     * @param value The numeric wire value of the corresponding enum entry.
     * @return The enum associated with the given numeric wire value.
     */
    public static EgressStatus forNumber(int value) {
        return switch (value) {
            case 0 -> EGRESS_STARTING;
            case 1 -> EGRESS_ACTIVE;
            case 2 -> EGRESS_ENDING;
            case 3 -> EGRESS_COMPLETE;
            case 4 -> EGRESS_FAILED;
            case 5 -> EGRESS_ABORTED;
            case 6 -> EGRESS_LIMIT_REACHED;
            default -> null;
        };
    }

    public static com.google.protobuf.Internal.EnumLiteMap<EgressStatus> internalGetValueMap() {
        return internalValueMap;
    }

    private static final com.google.protobuf.Internal.EnumLiteMap<EgressStatus> internalValueMap =
            EgressStatus::forNumber;

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
        return im.turms.plugin.livekit.core.proto.egress.LivekitEgress.getDescriptor()
                .getEnumTypes()
                .get(6);
    }

    private static final EgressStatus[] VALUES = values();

    public static EgressStatus valueOf(com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
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

    EgressStatus(int value) {
        this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:livekit.EgressStatus)
}