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

package im.turms.plugin.livekit.core.proto.ingress;

/**
 * Protobuf enum {@code livekit.IngressInput}
 */
public enum IngressInput implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <code>RTMP_INPUT = 0;</code>
     */
    RTMP_INPUT(0),
    /**
     * <code>WHIP_INPUT = 1;</code>
     */
    WHIP_INPUT(1),
    /**
     * <pre>
     * Pull from the provided URL. Only HTTP url are supported, serving either a single media file or a HLS stream
     * </pre>
     *
     * <code>URL_INPUT = 2;</code>
     */
    URL_INPUT(2),
    UNRECOGNIZED(-1),;

    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                IngressInput.class.getName());
    }
    /**
     * <code>RTMP_INPUT = 0;</code>
     */
    public static final int RTMP_INPUT_VALUE = 0;
    /**
     * <code>WHIP_INPUT = 1;</code>
     */
    public static final int WHIP_INPUT_VALUE = 1;
    /**
     * <pre>
     * Pull from the provided URL. Only HTTP url are supported, serving either a single media file or a HLS stream
     * </pre>
     *
     * <code>URL_INPUT = 2;</code>
     */
    public static final int URL_INPUT_VALUE = 2;

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
    public static IngressInput valueOf(int value) {
        return forNumber(value);
    }

    /**
     * @param value The numeric wire value of the corresponding enum entry.
     * @return The enum associated with the given numeric wire value.
     */
    public static IngressInput forNumber(int value) {
        return switch (value) {
            case 0 -> RTMP_INPUT;
            case 1 -> WHIP_INPUT;
            case 2 -> URL_INPUT;
            default -> null;
        };
    }

    public static com.google.protobuf.Internal.EnumLiteMap<IngressInput> internalGetValueMap() {
        return internalValueMap;
    }

    private static final com.google.protobuf.Internal.EnumLiteMap<IngressInput> internalValueMap =
            IngressInput::forNumber;

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
        return im.turms.plugin.livekit.core.proto.ingress.LivekitIngress.getDescriptor()
                .getEnumTypes()
                .get(0);
    }

    private static final IngressInput[] VALUES = values();

    public static IngressInput valueOf(com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
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

    IngressInput(int value) {
        this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:livekit.IngressInput)
}