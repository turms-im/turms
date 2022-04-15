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

package im.turms.server.common.access.admin.config;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import im.turms.server.common.access.admin.codec.TurmsCharSequenceEncoder;
import im.turms.server.common.access.admin.codec.TurmsResourceHttpMessageWriter;
import im.turms.server.common.infra.json.JsonSizeCalculator;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.PooledByteBufAllocator;
import org.springframework.boot.web.codec.CodecCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ResolvableType;
import org.springframework.core.codec.CharSequenceEncoder;
import org.springframework.core.codec.CodecException;
import org.springframework.core.codec.Encoder;
import org.springframework.core.codec.EncodingException;
import org.springframework.core.codec.Hints;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.NettyDataBuffer;
import org.springframework.core.io.buffer.NettyDataBufferFactory;
import org.springframework.http.codec.EncoderHttpMessageWriter;
import org.springframework.http.codec.HttpMessageWriter;
import org.springframework.http.codec.json.AbstractJackson2Encoder;
import org.springframework.http.codec.json.Jackson2CodecSupport;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.lang.Nullable;
import org.springframework.util.MimeType;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

/**
 * @author James Chen
 * @implNote Spring uses a list of pooled byte[], and serializes data to the byte[] instances,
 * and copies these pooled byte[] instances to one byte[] instance,
 * and then writes the byte[] to a pooled DirectByteBuf for flushing,
 * which is a waste of system resources,
 * <p>
 * So we use a custom codec to write data to a pooled DirectByteBuf without copying.
 * @see AbstractJackson2Encoder#encodeValue
 */
@Configuration
public class CodecConfig {

    private static final MimeType[] EMPTY_MIME_TYPES = {};

    @Bean
    CodecCustomizer myCodecCustomizer(ObjectMapper objectMapper) {
        return configurer -> {
            for (HttpMessageWriter<?> writer : configurer.getWriters()) {
                if (writer instanceof EncoderHttpMessageWriter w) {
                    Encoder<?> encoder = w.getEncoder();
                    if (encoder instanceof CharSequenceEncoder) {
                        MimeType[] mimeTypes = encoder.getEncodableMimeTypes().toArray(new MimeType[0]);
                        TurmsCharSequenceEncoder sequenceEncoder = new TurmsCharSequenceEncoder(mimeTypes);
                        configurer.customCodecs().register(sequenceEncoder);
                        break;
                    }
                }
            }
            configurer.customCodecs().register(new TurmsResourceHttpMessageWriter());
            configurer.defaultCodecs().jackson2JsonEncoder(new Jackson2JsonEncoder(objectMapper, EMPTY_MIME_TYPES) {
                @Override
                public DataBuffer encodeValue(Object value, DataBufferFactory bufferFactory, ResolvableType valueType, MimeType mimeType,
                                              Map<String, Object> hints) {
                    ObjectMapper mapper = selectObjectMapper(valueType, mimeType);
                    if (mapper == null) {
                        throw new IllegalStateException("No ObjectMapper for " + valueType);
                    }
                    Class<?> jsonView = null;
                    FilterProvider filters = null;
                    if (value instanceof MappingJacksonValue container) {
                        value = container.getValue();
                        jsonView = container.getSerializationView();
                        filters = container.getFilters();
                    }
                    ObjectWriter writer = createObjectWriter(mapper, valueType, mimeType, jsonView, hints);
                    if (filters != null) {
                        writer = writer.with(filters);
                    }
                    NettyDataBufferFactory factory = (NettyDataBufferFactory) bufferFactory;
                    int estimatedSize = JsonSizeCalculator.estimateJson(value);
                    ByteBuf buf = PooledByteBufAllocator.DEFAULT.directBuffer(estimatedSize);
                    OutputStream byteBuilder = new ByteBufOutputStream(buf);
                    try {
                        JsonEncoding encoding = getJsonEncoding(mimeType);
                        try (JsonGenerator generator = mapper.getFactory().createGenerator(byteBuilder, encoding)) {
                            writer.writeValue(generator, value);
                            generator.flush();
                        } catch (InvalidDefinitionException ex) {
                            throw new CodecException("Type definition error: " + ex.getType(), ex);
                        } catch (JsonProcessingException ex) {
                            throw new EncodingException("JSON encoding error: " + ex.getOriginalMessage(), ex);
                        } catch (IOException ex) {
                            throw new IllegalStateException("Unexpected I/O error while writing to byte array builder", ex);
                        }
                        NettyDataBuffer buffer = factory.wrap(buf);
                        Hints.touchDataBuffer(buffer, hints, logger);
                        return buffer;
                    } catch (Exception e) {
                        buf.release();
                        throw e;
                    }
                }

                private ObjectWriter createObjectWriter(
                        ObjectMapper mapper, ResolvableType valueType, @Nullable MimeType mimeType,
                        @Nullable Class<?> jsonView, @Nullable Map<String, Object> hints) {
                    JavaType javaType = getJavaType(valueType.getType(), null);
                    if (jsonView == null && hints != null) {
                        jsonView = (Class<?>) hints.get(Jackson2CodecSupport.JSON_VIEW_HINT);
                    }
                    ObjectWriter writer = jsonView == null ? mapper.writer() : mapper.writerWithView(jsonView);
                    if (javaType.isContainerType()) {
                        writer = writer.forType(javaType);
                    }
                    return customizeWriter(writer, mimeType, valueType, hints);
                }
            });
        };
    }

}
