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
 * Protobuf enum {@code livekit.IngressAudioEncodingPreset}
 */
public enum IngressAudioEncodingPreset implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <pre>
     * OPUS, 2 channels, 96kbps
     * </pre>
     *
     * <code>OPUS_STEREO_96KBPS = 0;</code>
     */
    OPUS_STEREO_96KBPS(0),
    /**
     * <pre>
     * OPUS, 1 channel, 64kbps
     * </pre>
     *
     * <code>OPUS_MONO_64KBS = 1;</code>
     */
    OPUS_MONO_64KBS(1),
    UNRECOGNIZED(-1),;

    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                IngressAudioEncodingPreset.class.getName());
    }
    /**
     * <pre>
     * OPUS, 2 channels, 96kbps
     * </pre>
     *
     * <code>OPUS_STEREO_96KBPS = 0;</code>
     */
    public static final int OPUS_STEREO_96KBPS_VALUE = 0;
    /**
     * <pre>
     * OPUS, 1 channel, 64kbps
     * </pre>
     *
     * <code>OPUS_MONO_64KBS = 1;</code>
     */
    public static final int OPUS_MONO_64KBS_VALUE = 1;

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
    public static IngressAudioEncodingPreset valueOf(int value) {
        return forNumber(value);
    }

    /**
     * @param value The numeric wire value of the corresponding enum entry.
     * @return The enum associated with the given numeric wire value.
     */
    public static IngressAudioEncodingPreset forNumber(int value) {
        return switch (value) {
            case 0 -> OPUS_STEREO_96KBPS;
            case 1 -> OPUS_MONO_64KBS;
            default -> null;
        };
    }

    public static com.google.protobuf.Internal.EnumLiteMap<IngressAudioEncodingPreset> internalGetValueMap() {
        return internalValueMap;
    }

    private static final com.google.protobuf.Internal.EnumLiteMap<IngressAudioEncodingPreset> internalValueMap =
            IngressAudioEncodingPreset::forNumber;

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
                .get(1);
    }

    private static final IngressAudioEncodingPreset[] VALUES = values();

    public static IngressAudioEncodingPreset valueOf(
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

    IngressAudioEncodingPreset(int value) {
        this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:livekit.IngressAudioEncodingPreset)
}