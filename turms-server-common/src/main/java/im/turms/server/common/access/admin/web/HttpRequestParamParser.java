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

package im.turms.server.common.access.admin.web;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import jakarta.annotation.Nullable;

import com.fasterxml.jackson.databind.JavaType;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.HttpResponseStatus;
import reactor.core.publisher.Mono;
import reactor.netty.http.server.HttpServerRequest;

import im.turms.server.common.access.admin.dto.response.HttpHandlerResult;
import im.turms.server.common.access.admin.dto.response.ResponseDTO;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.json.JsonCodecPool;
import im.turms.server.common.infra.lang.StringUtil;
import im.turms.server.common.infra.netty.ReferenceCountUtil;

/**
 * @author James Chen
 */
public class HttpRequestParamParser {

    private static final Mono<Object[]> BODY_IS_REQUIRED = Mono.error(new HttpResponseException(
            HttpHandlerResult.create(HttpResponseStatus.BAD_REQUEST,
                    new ResponseDTO<>(
                            ResponseStatusCode.ILLEGAL_ARGUMENT,
                            "The request body is required"))));
    private static final HttpResponseException FORM_DATA_IS_REQUIRED = new HttpResponseException(
            HttpHandlerResult.create(HttpResponseStatus.BAD_REQUEST,
                    new ResponseDTO<>(
                            ResponseStatusCode.ILLEGAL_ARGUMENT,
                            "The form data is required")));
    private final HttpResponseException bodyTooLargeException;

    private final int maxBodySize;

    public HttpRequestParamParser(int maxBodySize) {
        this.maxBodySize = maxBodySize;
        bodyTooLargeException = new HttpResponseException(
                HttpHandlerResult.create(HttpResponseStatus.REQUEST_ENTITY_TOO_LARGE,
                        new ResponseDTO<>(
                                ResponseStatusCode.ILLEGAL_ARGUMENT,
                                "The request body size must not exceed "
                                        + maxBodySize
                                        + " bytes")));
    }

    public <T> T parsePlainValue(Object value, Class<T> type, @Nullable JavaType typeForJackson) {
        if (type.isEnum()) {
            if (value instanceof String s) {
                return (T) Enum.valueOf((Class<? extends Enum>) type, s);
            } else {
                throw new IllegalArgumentException(
                        "The value of the type ("
                                + type.getName()
                                + ") must be string");
            }
        }
        try {
            return JsonCodecPool.MAPPER.convertValue(value, typeForJackson);
        } catch (Exception e) {
            throw new IllegalArgumentException(
                    "Invalid value: "
                            + value,
                    e);
        }
    }

    public Mono<ParsedParamCollection> parse(
            HttpServerRequest request,
            RequestContext requestContext,
            Map<String, List<Object>> params,
            MethodParameterInfo[] parameters) {
        int length = parameters.length;
        Object[] args = new Object[length];
        MethodParameterInfo requestBodyParam = null;
        MethodParameterInfo requestFormDataParam = null;
        int bodyIndex = -1;
        int formDataIndex = -1;
        for (int i = 0; i < length; i++) {
            MethodParameterInfo parameter = parameters[i];
            if (parameter.isBody()) {
                requestBodyParam = parameter;
                bodyIndex = i;
            } else if (parameter.isFormData()) {
                requestFormDataParam = parameter;
                formDataIndex = i;
            } else if (RequestContext.class.isAssignableFrom(parameter.type())) {
                args[i] = requestContext;
            } else {
                args[i] = parseArgs(request, params, parameter);
            }
        }
        if (requestBodyParam == null && requestFormDataParam == null) {
            return Mono.just(new ParsedParamCollection(args, Collections.emptyList()));
        }
        if (requestBodyParam != null) {
            int finalBodyIndex = bodyIndex;
            Mono<Object> parseBody = parseBody(request, requestBodyParam.typeForJackson())
                    .doOnNext(body -> args[finalBodyIndex] = body);
            if (requestBodyParam.isRequired()) {
                parseBody = parseBody.switchIfEmpty(BODY_IS_REQUIRED);
            }
            return parseBody
                    // Used to ensure returning arguments because parseBody() may return MonoEmpty
                    .thenReturn(new ParsedParamCollection(args, Collections.emptyList()));
        }
        int finalFormDataIndex = formDataIndex;
        Mono<List<MultipartFile>> parseFormData = parseFormData(request);
        Mono<ParsedParamCollection> collectionMono;
        if (requestFormDataParam.isRequired()) {
            collectionMono = parseFormData.map(files -> {
                if (files.isEmpty()) {
                    throw FORM_DATA_IS_REQUIRED;
                }
                args[finalFormDataIndex] = files;
                return new ParsedParamCollection(args, files);
            });
        } else {
            collectionMono = parseFormData.map(files -> {
                args[finalFormDataIndex] = files;
                return new ParsedParamCollection(args, files);
            });
        }
        return collectionMono;
    }

    @Nullable
    private Object parseArgs(
            HttpServerRequest request,
            Map<String, List<Object>> params,
            MethodParameterInfo parameter) {
        List<Object> paramValues = null;
        if (parameter.isHeader()) {
            String valueStr = request.requestHeaders()
                    .get(parameter.name());
            // TODO: support multiple values when we need in the future
            Object value = parseSingleValue(parameter, valueStr);
            if (value != null) {
                paramValues = List.of(value);
            }
        } else {
            paramValues = params.get(parameter.name());
        }
        boolean expectCollection = Collection.class.isAssignableFrom(parameter.type());
        if (expectCollection) {
            return parseCollection(parameter, paramValues);
        } else if (paramValues == null || paramValues.isEmpty()) {
            return parseSingleValue(parameter, null);
        } else {
            int count = paramValues.size();
            if (count > 1) {
                throw new IllegalArgumentException(
                        "Expected the parameter \""
                                + parameter.name()
                                + "\" not to be a collection, but got: "
                                + paramValues);
            } else {
                return parseSingleValue(parameter, paramValues.get(0));
            }
        }
    }

    @Nullable
    private Object parseCollection(MethodParameterInfo parameter, @Nullable List<Object> values) {
        if (values == null || values.isEmpty()) {
            if (parameter.isRequired()) {
                throw new IllegalArgumentException(
                        "Missing the required query parameter: \""
                                + parameter.name()
                                + "\"");
            }
            return null;
        }
        Class<?> type = parameter.type();
        Class<?> elementType = parameter.elementType();
        JavaType elementTypeForJackson = parameter.elementTypeForJackson();
        if (Set.class.isAssignableFrom(type)) {
            Set<Object> items = LinkedHashSet.class.isAssignableFrom(type)
                    ? new LinkedHashSet<>(CollectionUtil.getMapCapability(values.size()))
                    : CollectionUtil.newSetWithExpectedSize(values.size());
            for (Object value : values) {
                items.add(parsePlainValue(value, elementType, elementTypeForJackson));
            }
            return items;
        }
        if (List.class.isAssignableFrom(type)) {
            List<Object> items = new ArrayList<>(values.size());
            for (Object value : values) {
                items.add(parsePlainValue(value, elementType, elementTypeForJackson));
            }
            return items;
        }
        throw new IllegalArgumentException(
                "Invalid parameter type: "
                        + type.getName());
    }

    @Nullable
    private Object parseSingleValue(MethodParameterInfo parameter, @Nullable Object value) {
        if (value != null) {
            return parsePlainValue(value, parameter.type(), parameter.typeForJackson());
        }
        Object defaultValue = parameter.defaultValue();
        if (defaultValue != null) {
            return defaultValue;
        }
        if (parameter.isRequired()) {
            String type = parameter.isHeader()
                    ? "header"
                    : "query";
            throw new HttpResponseException(
                    HttpHandlerResult.badRequest("Missing required "
                            + type
                            + " parameter: \""
                            + parameter.name()
                            + "\""));
        }
        return null;
    }

    /**
     * TODO: Support more charsets
     */
    private Mono<Object> parseBody(HttpServerRequest request, @Nullable JavaType parameterType) {
        return Mono.defer(() -> {
            CompositeByteBuf body = Unpooled.compositeBuffer();
            return request.receive()
                    // We don't use "collectList()" because we need to reject
                    // to receive buffers if it has exceeded the max size.
                    .doOnNext(buffer -> {
                        body.addComponent(true, buffer);
                        if (body.readableBytes() > maxBodySize) {
                            body.release();
                            throw bodyTooLargeException;
                        }
                        buffer.retain();
                    })
                    .then(Mono.defer(() -> {
                        int length = body.readableBytes();
                        if (length == 0) {
                            return Mono.empty();
                        }
                        try (ByteBufInputStream stream =
                                new ByteBufInputStream(body, length, true)) {
                            Object value = JsonCodecPool.MAPPER.readValue((InputStream) stream,
                                    parameterType);
                            return Mono.just(value);
                        } catch (Exception e) {
                            HttpHandlerResult<ResponseDTO<?>> result =
                                    HttpHandlerResult.badRequest("Illegal request body: "
                                            + e.getMessage());
                            return Mono.error(new HttpResponseException(result, e));
                        }
                    }))
                    .doFinally(signalType -> ReferenceCountUtil.safeEnsureReleased(body));
        });
    }

    private Mono<List<MultipartFile>> parseFormData(HttpServerRequest request) {
        return request.receiveForm(builder -> builder
                // store on disk
                .maxInMemorySize(0)
                .maxSize(maxBodySize))
                .map(data -> {
                    File file;
                    try {
                        file = data.getFile();
                    } catch (IOException e) {
                        // Should never happen because the data should be a file
                        // (DiskAttribute or DiskFileUpload)
                        throw new IllegalArgumentException(
                                "Failed to get the file from the HTTP data: "
                                        + data,
                                e);
                    }
                    data.retain();
                    String name = data.getName();
                    String basename = StringUtil.substringToLastDelimiter(name, '.');
                    return new MultipartFile(data, name, basename, file);
                })
                .collectList();
    }

}