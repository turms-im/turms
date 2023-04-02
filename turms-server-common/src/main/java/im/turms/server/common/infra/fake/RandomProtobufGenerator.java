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

package im.turms.server.common.infra.fake;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import com.google.protobuf.AbstractMessage;
import com.google.protobuf.ByteString;
import com.google.protobuf.ByteStringUtil;
import com.google.protobuf.Message;
import org.apache.commons.lang3.RandomStringUtils;
import org.jctools.maps.NonBlockingHashMapLong;

import im.turms.server.common.infra.lang.Range;
import im.turms.server.common.infra.thread.ThreadSafe;

import static com.google.protobuf.Descriptors.EnumDescriptor;
import static com.google.protobuf.Descriptors.EnumValueDescriptor;
import static com.google.protobuf.Descriptors.FieldDescriptor;

/**
 * @author James Chen
 */
@ThreadSafe
public class RandomProtobufGenerator<T extends AbstractMessage> {

    private static final int MAX_LIST_LENGTH = 10;
    private static final Range<Long> POSITIVE_NUMBER_RANGE = Range.between(0L, Long.MAX_VALUE);

    private final Random random;
    private final Message instance;
    private final List<FieldDescriptor> fieldDescriptors;
    private NonBlockingHashMapLong<RandomProtobufGenerator<?>> fieldMessageGenerators;

    public RandomProtobufGenerator(Random random, Message instance) {
        this.random = random;
        this.instance = instance;
        fieldDescriptors = instance.getDescriptorForType()
                .getFields();
    }

    public T generate() {
        return generate(1, 1, POSITIVE_NUMBER_RANGE);
    }

    public T generate(
            float possibilityToFillOptionalFields,
            float possibilityToHaveNotEmptyRepeatedFields,
            Range<Long> numberRange) {
        GeneratorOptions options = new GeneratorOptions(
                possibilityToFillOptionalFields,
                possibilityToHaveNotEmptyRepeatedFields,
                numberRange);
        return generate(options);
    }

    public T generate(GeneratorOptions options) {
        Message.Builder builder = instance.newBuilderForType();
        float possibility = options.possibilityToFillOptionalFields;
        for (FieldDescriptor field : fieldDescriptors) {
            if (field.isOptional() && possibility < 1 && random.nextFloat() > possibility) {
                continue;
            }
            Object value = getRandomValue(builder, field, options);
            try {
                builder.setField(field, value);
            } catch (Exception e) {
                throw new RuntimeException(
                        "Failed to set the field ("
                                + field.getFullName()
                                + ") to the value: "
                                + value);
            }
        }
        return (T) builder.build();
    }

    private Object getRandomValue(
            Message.Builder builder,
            FieldDescriptor field,
            GeneratorOptions options) {
        if (field.isRepeated()) {
            float possibility = options.possibilityToHaveNotEmptyRepeatedFields;
            if (possibility < 1 && random.nextFloat() > possibility) {
                return Collections.emptyList();
            }
            int length = random.nextInt(MAX_LIST_LENGTH);
            List<Object> values = new ArrayList<>(length);
            for (int i = 0; i < length; i++) {
                values.add(getRandomSingleValue(builder, field, options));
            }
            return values;
        }
        return getRandomSingleValue(builder, field, options);
    }

    private Object getRandomSingleValue(
            Message.Builder builder,
            FieldDescriptor field,
            GeneratorOptions options) {
        Range<Long> numberRange = options.numberRange;
        return switch (field.getJavaType()) {
            case BOOLEAN -> random.nextBoolean();
            case BYTE_STRING -> getRandomByteString();
            case DOUBLE -> random.nextDouble();
            case ENUM -> getRandomEnum(field.getEnumType());
            case FLOAT -> random.nextFloat();
            case INT -> getRandomNumberFromRange(numberRange).intValue();
            case LONG -> getRandomNumberFromRange(numberRange);
            case MESSAGE -> getRandomMessage(builder, field, options);
            case STRING -> RandomStringUtils.randomAlphabetic(8, 64);
            default -> new IllegalArgumentException(
                    "Failed to generate random single value for the unknown type: "
                            + field.getJavaType());
        };
    }

    private ByteString getRandomByteString() {
        int length = random.nextInt(32 + 1);
        byte[] bytes = new byte[length];
        random.nextBytes(bytes);
        return ByteStringUtil.wrap(bytes);
    }

    private EnumValueDescriptor getRandomEnum(EnumDescriptor enumDesc) {
        List<EnumValueDescriptor> values = enumDesc.getValues();
        return values.get(random.nextInt(values.size()));
    }

    private AbstractMessage getRandomMessage(
            Message.Builder builder,
            FieldDescriptor field,
            GeneratorOptions options) {
        if (fieldMessageGenerators == null) {
            synchronized (this) {
                if (fieldMessageGenerators == null) {
                    fieldMessageGenerators = new NonBlockingHashMapLong<>(8);
                }
            }
        }
        return fieldMessageGenerators.computeIfAbsent((long) field.getIndex(), index -> {
            Message.Builder fieldBuilder = builder.newBuilderForField(field);
            return new RandomProtobufGenerator<>(random, fieldBuilder.getDefaultInstanceForType());
        })
                .generate(options);
    }

    private Long getRandomNumberFromRange(Range<Long> numberRange) {
        return ThreadLocalRandom.current()
                .nextLong(numberRange.minInclusive(), numberRange.maxInclusive());
    }

    public record GeneratorOptions(
            float possibilityToFillOptionalFields,
            float possibilityToHaveNotEmptyRepeatedFields,
            Range<Long> numberRange
    ) {
    }

}