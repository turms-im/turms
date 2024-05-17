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
 * Protobuf enum {@code livekit.StreamProtocol}
 */
public enum StreamProtocol implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <pre>
     * protocol chosen based on urls
     * </pre>
     *
     * <code>DEFAULT_PROTOCOL = 0;</code>
     */
    DEFAULT_PROTOCOL(0),
    /**
     * <code>RTMP = 1;</code>
     */
    RTMP(1),
    UNRECOGNIZED(-1),;

    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                StreamProtocol.class.getName());
    }
    /**
     * <pre>
     * protocol chosen based on urls
     * </pre>
     *
     * <code>DEFAULT_PROTOCOL = 0;</code>
     */
    public static final int DEFAULT_PROTOCOL_VALUE = 0;
    /**
     * <code>RTMP = 1;</code>
     */
    public static final int RTMP_VALUE = 1;

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
    public static StreamProtocol valueOf(int value) {
        return forNumber(value);
    }

    /**
     * @param value The numeric wire value of the corresponding enum entry.
     * @return The enum associated with the given numeric wire value.
     */
    public static StreamProtocol forNumber(int value) {
        return switch (value) {
            case 0 -> DEFAULT_PROTOCOL;
            case 1 -> RTMP;
            default -> null;
        };
    }

    public static com.google.protobuf.Internal.EnumLiteMap<StreamProtocol> internalGetValueMap() {
        return internalValueMap;
    }

    private static final com.google.protobuf.Internal.EnumLiteMap<StreamProtocol> internalValueMap =
            StreamProtocol::forNumber;

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
                .get(4);
    }

    private static final StreamProtocol[] VALUES = values();

    public static StreamProtocol valueOf(com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
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

    StreamProtocol(int value) {
        this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:livekit.StreamProtocol)
}