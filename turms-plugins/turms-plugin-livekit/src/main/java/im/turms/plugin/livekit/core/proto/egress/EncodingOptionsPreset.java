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
 * Protobuf enum {@code livekit.EncodingOptionsPreset}
 */
public enum EncodingOptionsPreset implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <pre>
     * 1280x720, 30fps, 3000kpbs, H.264_MAIN / OPUS
     * </pre>
     *
     * <code>H264_720P_30 = 0;</code>
     */
    H264_720P_30(0),
    /**
     * <pre>
     * 1280x720, 60fps, 4500kbps, H.264_MAIN / OPUS
     * </pre>
     *
     * <code>H264_720P_60 = 1;</code>
     */
    H264_720P_60(1),
    /**
     * <pre>
     * 1920x1080, 30fps, 4500kbps, H.264_MAIN / OPUS
     * </pre>
     *
     * <code>H264_1080P_30 = 2;</code>
     */
    H264_1080P_30(2),
    /**
     * <pre>
     * 1920x1080, 60fps, 6000kbps, H.264_MAIN / OPUS
     * </pre>
     *
     * <code>H264_1080P_60 = 3;</code>
     */
    H264_1080P_60(3),
    /**
     * <pre>
     * 720x1280, 30fps, 3000kpbs, H.264_MAIN / OPUS
     * </pre>
     *
     * <code>PORTRAIT_H264_720P_30 = 4;</code>
     */
    PORTRAIT_H264_720P_30(4),
    /**
     * <pre>
     * 720x1280, 60fps, 4500kbps, H.264_MAIN / OPUS
     * </pre>
     *
     * <code>PORTRAIT_H264_720P_60 = 5;</code>
     */
    PORTRAIT_H264_720P_60(5),
    /**
     * <pre>
     * 1080x1920, 30fps, 4500kbps, H.264_MAIN / OPUS
     * </pre>
     *
     * <code>PORTRAIT_H264_1080P_30 = 6;</code>
     */
    PORTRAIT_H264_1080P_30(6),
    /**
     * <pre>
     * 1080x1920, 60fps, 6000kbps, H.264_MAIN / OPUS
     * </pre>
     *
     * <code>PORTRAIT_H264_1080P_60 = 7;</code>
     */
    PORTRAIT_H264_1080P_60(7),
    UNRECOGNIZED(-1),;

    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                EncodingOptionsPreset.class.getName());
    }
    /**
     * <pre>
     * 1280x720, 30fps, 3000kpbs, H.264_MAIN / OPUS
     * </pre>
     *
     * <code>H264_720P_30 = 0;</code>
     */
    public static final int H264_720P_30_VALUE = 0;
    /**
     * <pre>
     * 1280x720, 60fps, 4500kbps, H.264_MAIN / OPUS
     * </pre>
     *
     * <code>H264_720P_60 = 1;</code>
     */
    public static final int H264_720P_60_VALUE = 1;
    /**
     * <pre>
     * 1920x1080, 30fps, 4500kbps, H.264_MAIN / OPUS
     * </pre>
     *
     * <code>H264_1080P_30 = 2;</code>
     */
    public static final int H264_1080P_30_VALUE = 2;
    /**
     * <pre>
     * 1920x1080, 60fps, 6000kbps, H.264_MAIN / OPUS
     * </pre>
     *
     * <code>H264_1080P_60 = 3;</code>
     */
    public static final int H264_1080P_60_VALUE = 3;
    /**
     * <pre>
     * 720x1280, 30fps, 3000kpbs, H.264_MAIN / OPUS
     * </pre>
     *
     * <code>PORTRAIT_H264_720P_30 = 4;</code>
     */
    public static final int PORTRAIT_H264_720P_30_VALUE = 4;
    /**
     * <pre>
     * 720x1280, 60fps, 4500kbps, H.264_MAIN / OPUS
     * </pre>
     *
     * <code>PORTRAIT_H264_720P_60 = 5;</code>
     */
    public static final int PORTRAIT_H264_720P_60_VALUE = 5;
    /**
     * <pre>
     * 1080x1920, 30fps, 4500kbps, H.264_MAIN / OPUS
     * </pre>
     *
     * <code>PORTRAIT_H264_1080P_30 = 6;</code>
     */
    public static final int PORTRAIT_H264_1080P_30_VALUE = 6;
    /**
     * <pre>
     * 1080x1920, 60fps, 6000kbps, H.264_MAIN / OPUS
     * </pre>
     *
     * <code>PORTRAIT_H264_1080P_60 = 7;</code>
     */
    public static final int PORTRAIT_H264_1080P_60_VALUE = 7;

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
    public static EncodingOptionsPreset valueOf(int value) {
        return forNumber(value);
    }

    /**
     * @param value The numeric wire value of the corresponding enum entry.
     * @return The enum associated with the given numeric wire value.
     */
    public static EncodingOptionsPreset forNumber(int value) {
        return switch (value) {
            case 0 -> H264_720P_30;
            case 1 -> H264_720P_60;
            case 2 -> H264_1080P_30;
            case 3 -> H264_1080P_60;
            case 4 -> PORTRAIT_H264_720P_30;
            case 5 -> PORTRAIT_H264_720P_60;
            case 6 -> PORTRAIT_H264_1080P_30;
            case 7 -> PORTRAIT_H264_1080P_60;
            default -> null;
        };
    }

    public static com.google.protobuf.Internal.EnumLiteMap<EncodingOptionsPreset> internalGetValueMap() {
        return internalValueMap;
    }

    private static final com.google.protobuf.Internal.EnumLiteMap<EncodingOptionsPreset> internalValueMap =
            EncodingOptionsPreset::forNumber;

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
                .get(5);
    }

    private static final EncodingOptionsPreset[] VALUES = values();

    public static EncodingOptionsPreset valueOf(
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

    EncodingOptionsPreset(int value) {
        this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:livekit.EncodingOptionsPreset)
}