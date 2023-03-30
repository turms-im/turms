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

package im.turms.server.common.infra.proto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import jakarta.annotation.Nullable;

import com.google.protobuf.Descriptors;
import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.TextFormat;

/**
 * @author James Chen
 */
public class ProtoFormatter {

    private ProtoFormatter() {
    }

    private static final char[] SENSITIVE_VALUE = "'*'".toCharArray();
    private static final Map<Descriptors.Descriptor, List<Map.Entry<Descriptors.FieldDescriptor, Object>>> FIELD_CACHE =
            new ConcurrentHashMap<>();

    public static String toJSON5(MessageOrBuilder message, int initialCapacity) {
        StringBuilder builder = new StringBuilder(initialCapacity);
        builder.append('{');
        print(message, builder);
        builder.append('}');
        return builder.toString();
    }

    @Nullable
    public static String toLogString(@Nullable MessageOrBuilder message) {
        if (message == null) {
            return null;
        }
        return ProtoFormatter.toJSON5(message, 128);
    }

    private static void print(MessageOrBuilder message, StringBuilder builder) {
        if (message.getDescriptorForType()
                .getFullName()
                .equals("google.protobuf.Any")) {
            return;
        }
        printMessage(message, builder);
    }

    private static void printField(
            Descriptors.FieldDescriptor field,
            Object value,
            StringBuilder builder) {
        if (field.isMapField() || field.isRepeated()) {
            builder.append(field.getName());
            builder.append(':');
            builder.append(SENSITIVE_VALUE);
        } else {
            printSingleField(field, value, builder);
        }
    }

    private static void printFieldValue(
            Descriptors.FieldDescriptor field,
            Object value,
            StringBuilder builder) {
        switch (field.getType()) {
            case INT32, SINT32, SFIXED32 -> builder.append((int) value);
            case INT64, SINT64, SFIXED64 -> builder.append((long) value);
            case BOOL -> builder.append((boolean) value);
            case FLOAT, DOUBLE -> builder.append(value.toString());
            case UINT32, FIXED32 -> builder.append(TextFormat.unsignedToString((int) value));
            case UINT64, FIXED64 -> builder.append(TextFormat.unsignedToString((long) value));
            case STRING, BYTES -> builder.append(SENSITIVE_VALUE);
            case ENUM -> builder.append(((Descriptors.EnumValueDescriptor) value).getName());
            case MESSAGE, GROUP -> print((Message) value, builder);
            default -> throw new IllegalArgumentException(
                    "Unexpected field type: "
                            + field.getType());
        }
    }

    private static void printMessage(MessageOrBuilder message, StringBuilder builder) {
        Descriptors.Descriptor descriptor = message.getDescriptorForType();
        List<Map.Entry<Descriptors.FieldDescriptor, Object>> fields = FIELD_CACHE.get(descriptor);
        if (fields == null) {
            fields = new ArrayList<>(
                    message.getAllFields()
                            .entrySet());
            FIELD_CACHE.put(descriptor, fields);
        }
        int count = fields.size();
        for (int i = 0; i < count; i++) {
            if (i != 0) {
                builder.append(',');
            }
            Map.Entry<Descriptors.FieldDescriptor, Object> field = fields.get(i);
            printField(field.getKey(), field.getValue(), builder);
        }
    }

    private static void printSingleField(
            Descriptors.FieldDescriptor field,
            Object value,
            StringBuilder builder) {
        if (field.isExtension()) {
            builder.append("\"")
                    .append(field.getFullName())
                    .append("\"");
        } else {
            if (field.getType() == Descriptors.FieldDescriptor.Type.GROUP) {
                // Groups must be serialized with their original capitalization.
                builder.append(field.getMessageType()
                        .getName());
            } else {
                builder.append(field.getName());
            }
        }

        if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
            builder.append(":{");
        } else {
            builder.append(':');
        }

        printFieldValue(field, value, builder);

        if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
            builder.append('}');
        }
    }

}