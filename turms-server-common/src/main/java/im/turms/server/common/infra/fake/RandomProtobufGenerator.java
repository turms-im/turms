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

import com.google.common.collect.Range;
import com.google.protobuf.AbstractMessage;
import com.google.protobuf.ByteString;
import com.google.protobuf.Message;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static com.google.protobuf.Descriptors.Descriptor;
import static com.google.protobuf.Descriptors.EnumDescriptor;
import static com.google.protobuf.Descriptors.EnumValueDescriptor;
import static com.google.protobuf.Descriptors.FieldDescriptor;

/**
 * @author James Chen
 */
public class RandomProtobufGenerator<T extends AbstractMessage> {

    private static final int MAX_LIST_LENGTH = 10;
    private static final Range<Long> POSITIVE_NUMBER_RANGE = Range.closedOpen(0L, Long.MAX_VALUE);

    private final Random random;
    private final Message instance;

    public RandomProtobufGenerator(Random random, Message instance) {
        this.random = random;
        this.instance = instance;
    }

    public T generate() {
        return generate(1, 1, POSITIVE_NUMBER_RANGE);
    }

    public T generate(float possibilityToFillOptionalFields,
                      float possibilityToHaveNotEmptyRepeatedFields,
                      Range<Long> numberRange) {
        GeneratorOptions options = new GeneratorOptions(possibilityToFillOptionalFields,
                possibilityToHaveNotEmptyRepeatedFields,
                numberRange);
        return generate(options);
    }

    public T generate(GeneratorOptions options) {
        Message.Builder builder = instance.newBuilderForType();
        Descriptor descriptor = instance.getDescriptorForType();
        for (FieldDescriptor field : descriptor.getFields()) {
            if (field.isOptional() && random.nextFloat() > options.possibilityToFillOptionalFields) {
                continue;
            }
            Object value = getRandomValue(field, options);
            try {
                builder.setField(field, value);
            } catch (Exception e) {
                throw new RuntimeException("Failed to set the value %s to the field %s".formatted(value.toString(), field.getFullName()));
            }
        }
        return (T) builder.build();
    }

    private Object getRandomValue(FieldDescriptor field, GeneratorOptions options) {
        if (field.isRepeated()) {
            int length = random.nextInt(MAX_LIST_LENGTH);
            List<Object> values = new ArrayList<>(length);
            for (int i = 0; i < length; i++) {
                values.add(getRandomSingleValue(field, options));
            }
            return values;
        }
        return getRandomSingleValue(field, options);
    }

    private Object getRandomSingleValue(FieldDescriptor field, GeneratorOptions options) {
        Range<Long> numberRange = options.numberRange;
        return switch (field.getJavaType()) {
            case BOOLEAN -> random.nextBoolean();
            case BYTE_STRING -> getRandomByteString();
            case DOUBLE -> random.nextDouble();
            case ENUM -> getRandomEnum(field.getEnumType());
            case FLOAT -> random.nextFloat();
            case INT -> getRandomNumberFromRange(numberRange).intValue();
            case LONG -> getRandomNumberFromRange(numberRange);
            case MESSAGE -> getRandomMessage(field, options);
            case STRING -> RandomStringUtils.randomAlphabetic(8, 64);
        };
    }

    private ByteString getRandomByteString() {
        byte[] bytes = new byte[32];
        random.nextBytes(bytes);
        return ByteString.copyFrom(bytes);
    }

    private EnumValueDescriptor getRandomEnum(EnumDescriptor enumDesc) {
        List<EnumValueDescriptor> values = enumDesc.getValues();
        return values.get(random.nextInt(values.size()));
    }

    private AbstractMessage getRandomMessage(FieldDescriptor field, GeneratorOptions options) {
        Message.Builder fieldBuilder = instance.toBuilder().newBuilderForField(field);
        return new RandomProtobufGenerator<>(random, fieldBuilder.getDefaultInstanceForType()).generate(options);
    }

    private Long getRandomNumberFromRange(Range<Long> numberRange) {
        long upperBound = numberRange.hasUpperBound() ? numberRange.upperEndpoint() : Long.MAX_VALUE;
        long lowerBound = numberRange.hasLowerBound() ? numberRange.lowerEndpoint() : Long.MIN_VALUE;
        return ThreadLocalRandom.current().nextLong(lowerBound, upperBound);
    }

    public static record GeneratorOptions(
            float possibilityToFillOptionalFields,
            float possibilityToHaveNotEmptyRepeatedFields,
            Range<Long> numberRange) {
    }

}