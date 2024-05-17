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
 * Protobuf enum {@code livekit.VideoQuality}
 */
public enum VideoQuality implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <code>LOW = 0;</code>
     */
    LOW(0),
    /**
     * <code>MEDIUM = 1;</code>
     */
    MEDIUM(1),
    /**
     * <code>HIGH = 2;</code>
     */
    HIGH(2),
    /**
     * <code>OFF = 3;</code>
     */
    OFF(3),
    UNRECOGNIZED(-1),;

    static {
        com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
                com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
                /* major= */ 4,
                /* minor= */ 26,
                /* patch= */ 1,
                /* suffix= */ "",
                VideoQuality.class.getName());
    }
    /**
     * <code>LOW = 0;</code>
     */
    public static final int LOW_VALUE = 0;
    /**
     * <code>MEDIUM = 1;</code>
     */
    public static final int MEDIUM_VALUE = 1;
    /**
     * <code>HIGH = 2;</code>
     */
    public static final int HIGH_VALUE = 2;
    /**
     * <code>OFF = 3;</code>
     */
    public static final int OFF_VALUE = 3;

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
    public static VideoQuality valueOf(int value) {
        return forNumber(value);
    }

    /**
     * @param value The numeric wire value of the corresponding enum entry.
     * @return The enum associated with the given numeric wire value.
     */
    public static VideoQuality forNumber(int value) {
        return switch (value) {
            case 0 -> LOW;
            case 1 -> MEDIUM;
            case 2 -> HIGH;
            case 3 -> OFF;
            default -> null;
        };
    }

    public static com.google.protobuf.Internal.EnumLiteMap<VideoQuality> internalGetValueMap() {
        return internalValueMap;
    }

    private static final com.google.protobuf.Internal.EnumLiteMap<VideoQuality> internalValueMap =
            VideoQuality::forNumber;

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
                .get(5);
    }

    private static final VideoQuality[] VALUES = values();

    public static VideoQuality valueOf(com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
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

    VideoQuality(int value) {
        this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:livekit.VideoQuality)
}