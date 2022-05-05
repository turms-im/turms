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

import im.turms.server.common.access.admin.dto.response.HttpHandlerResult;
import im.turms.server.common.access.admin.dto.response.ResponseDTO;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.json.JsonCodecPool;
import im.turms.server.common.infra.netty.ByteBufUtil;
import io.netty.buffer.ByteBufInputStream;
import io.netty.handler.codec.http.HttpResponseStatus;
import reactor.core.publisher.Mono;
import reactor.netty.http.server.HttpServerRequest;

import javax.annotation.Nullable;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author James Chen
 */
public class HttpRequestParamParser {

    private static final int MAX_BODY_SIZE = 10 * 1024 * 1024;

    private static final Mono<Object> BODY_TOO_LARGE = Mono
            .error(new HttpResponseException(HttpHandlerResult.create(HttpResponseStatus.REQUEST_ENTITY_TOO_LARGE,
                    new ResponseDTO<>(ResponseStatusCode.ILLEGAL_ARGUMENT,
                            "Request body size should not exceed " + MAX_BODY_SIZE + " bytes"))));

    private static final Mono<Object[]> BODY_IS_REQUIRED = Mono
            .error(new HttpResponseException(HttpHandlerResult.create(HttpResponseStatus.BAD_REQUEST,
                    new ResponseDTO<>(ResponseStatusCode.ILLEGAL_ARGUMENT,
                            "Request body is required"))));

    private HttpRequestParamParser() {
    }

    public static Object parsePlainValue(Object value, Class<?> type) {
        if (type.isEnum()) {
            if (value instanceof String s) {
                return Enum.valueOf((Class) type, s);
            } else {
                throw new IllegalArgumentException("The value of " + type.getName() + " should be string");
            }
        }
        try {
            return JsonCodecPool.MAPPER.convertValue(value, type);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid value: " + value, e);
        }
    }

    public static Mono<Object[]> parse(HttpServerRequest request,
                                       RequestContext requestContext,
                                       Map<String, List<Object>> params,
                                       MethodParameterInfo[] parameters) {
        int length = parameters.length;
        Object[] args = new Object[length];
        MethodParameterInfo requestBodyParam = null;
        int bodyIndex = -1;
        for (int i = 0; i < length; i++) {
            MethodParameterInfo parameter = parameters[i];
            if (parameter.isBody()) {
                requestBodyParam = parameter;
                bodyIndex = i;
            } else if (RequestContext.class.isAssignableFrom(parameter.type())) {
                args[i] = requestContext;
            } else {
                args[i] = parseArgs(request, params, parameter);
            }
        }
        if (requestBodyParam == null) {
            return Mono.just(args);
        }
        int finalBodyIndex = bodyIndex;
        Mono<Object> parseBody = parseBody(request, requestBodyParam.type())
                .doOnNext(body -> args[finalBodyIndex] = body);
        if (requestBodyParam.isRequired()) {
            parseBody = parseBody.switchIfEmpty(BODY_IS_REQUIRED);
        }
        return parseBody
                // Used to ensure returning arguments because parseBody() may return MonoEmpty
                .thenReturn(args);
    }

    private static Object parseArgs(HttpServerRequest request,
                                    Map<String, List<Object>> params,
                                    MethodParameterInfo parameter) {
        List<Object> paramValues = null;
        if (parameter.isHeader()) {
            String value = request.requestHeaders().get(parameter.name());
            parseSingleValue(parameter, value);
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
                throw new IllegalArgumentException("Parameter " + parameter.name() + " is not a collection. Actual: " + paramValues);
            } else {
                return parseSingleValue(parameter, paramValues.get(0));
            }
        }
    }

    private static Object parseCollection(MethodParameterInfo parameter,
                                          @Nullable List<Object> values) {
        if (values == null || values.isEmpty()) {
            if (parameter.isRequired()) {
                throw new IllegalArgumentException("Missing required query parameter: " + parameter.name());
            } else {
                return null;
            }
        } else if (Set.class.isAssignableFrom(parameter.type())) {
            Set<Object> items = CollectionUtil.newSetWithExpectedSize(values.size());
            for (Object value : values) {
                items.add(parsePlainValue(value, parameter.elementType()));
            }
            return items;
        } else if (List.class.isAssignableFrom(parameter.type())) {
            List<Object> items = new ArrayList<>(values.size());
            for (Object value : values) {
                items.add(parsePlainValue(value, parameter.elementType()));
            }
            return items;
        } else {
            throw new IllegalArgumentException("Invalid parameter type: " + parameter.type());
        }
    }

    private static Object parseSingleValue(MethodParameterInfo parameter,
                                           @Nullable Object value) {
        if (value == null) {
            Object defaultValue = parameter.defaultValue();
            if (defaultValue != null) {
                return defaultValue;
            }
            if (parameter.isRequired()) {
                throw new HttpResponseException(HttpHandlerResult
                        .badRequest("Missing required "
                                + (parameter.isHeader() ? "header" : "query")
                                + " parameter: " + parameter.name()));
            } else {
                return null;
            }
        }
        return parsePlainValue(value, parameter.type());
    }

    private static Mono<Object> parseBody(HttpServerRequest request, Class<?> parameterType) {
        return request.receive()
                .aggregate()
                .flatMap(body -> {
                    // TODO: reject earlier
                    if (body.readableBytes() > MAX_BODY_SIZE) {
                        body.release();
                        return BODY_TOO_LARGE;
                    }
                    try {
                        Object value = JsonCodecPool.MAPPER
                                .readValue((InputStream) new ByteBufInputStream(body), parameterType);
                        body.release();
                        return Mono.just(value);
                    } catch (IOException e) {
                        ByteBufUtil.ensureReleased(body);
                        return Mono.error(new HttpResponseException(HttpHandlerResult.badRequest("Illegal request body"), e));
                    }
                });
    }

}
