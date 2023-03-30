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

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jakarta.annotation.Nullable;

import com.fasterxml.jackson.databind.JavaType;
import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.HttpMethod;
import org.springframework.context.ConfigurableApplicationContext;

import im.turms.server.common.access.admin.permission.RequiredPermission;
import im.turms.server.common.access.admin.web.annotation.DeleteMapping;
import im.turms.server.common.access.admin.web.annotation.FormData;
import im.turms.server.common.access.admin.web.annotation.GetMapping;
import im.turms.server.common.access.admin.web.annotation.HeadMapping;
import im.turms.server.common.access.admin.web.annotation.PostMapping;
import im.turms.server.common.access.admin.web.annotation.PutMapping;
import im.turms.server.common.access.admin.web.annotation.QueryParam;
import im.turms.server.common.access.admin.web.annotation.RequestBody;
import im.turms.server.common.access.admin.web.annotation.RequestHeader;
import im.turms.server.common.access.admin.web.annotation.RestController;
import im.turms.server.common.infra.io.BaseFileResource;
import im.turms.server.common.infra.json.JsonUtil;
import im.turms.server.common.infra.lang.ClassUtil;
import im.turms.server.common.infra.lang.Pair;
import im.turms.server.common.infra.lang.PrimitiveUtil;
import im.turms.server.common.infra.openapi.OpenApiBuilder;
import im.turms.server.common.infra.reflect.ReflectionUtil;

import static im.turms.server.common.access.admin.web.MediaTypeConst.APPLICATION_JSON;
import static im.turms.server.common.access.admin.web.MediaTypeConst.APPLICATION_OCTET_STREAM;
import static im.turms.server.common.access.admin.web.MediaTypeConst.TEXT_PLAIN_UTF_8;

/**
 * @author James Chen
 */
public class HttpEndpointCollector {

    private final HttpRequestParamParser requestParamParser;

    public HttpEndpointCollector(HttpRequestParamParser requestParamParser) {
        this.requestParamParser = requestParamParser;
    }

    public Map<ApiEndpointKey, ApiEndpoint> collectEndpoints(
            ConfigurableApplicationContext context) {
        Map<ApiEndpointKey, ApiEndpoint> keyToEndpoint = new HashMap<>(64);
        Map<String, Object> beans = context.getBeansWithAnnotation(RestController.class);
        for (Object controller : beans.values()) {
            collectEndpoints(keyToEndpoint, controller);
        }
        return Map.copyOf(keyToEndpoint);
    }

    public void collectEndpoints(
            Map<ApiEndpointKey, ApiEndpoint> keyToEndpoint,
            List<Object> controllers) {
        for (Object controller : controllers) {
            collectEndpoints(keyToEndpoint, controller);
        }
    }

    private void collectEndpoints(
            Map<ApiEndpointKey, ApiEndpoint> keyToEndpoint,
            Object controller) {
        Class<?> controllerClass = controller.getClass();
        RestController annotation = controllerClass.getDeclaredAnnotation(RestController.class);
        if (annotation == null) {
            throw new IllegalArgumentException(
                    "@RestController must be present in the controller class: "
                            + controllerClass.getName());
        }
        String basePath = annotation.value();
        Method[] methods = controllerClass.getDeclaredMethods();
        for (Method method : methods) {
            ReflectionUtil.setAccessible(method);
            Pair<HttpMethod, String> methodAndPath = parseMethod(method);
            if (methodAndPath == null) {
                continue;
            }
            String path = "/"
                    + basePath;
            if (!methodAndPath.second()
                    .isBlank()) {
                path += "/"
                        + methodAndPath.second();
            }
            HttpMethod httpMethod = methodAndPath.first();
            String encoding = null;
            if (httpMethod == HttpMethod.GET) {
                GetMapping mapping = method.getDeclaredAnnotation(GetMapping.class);
                encoding = mapping.encoding()
                        .isBlank()
                                ? null
                                : mapping.encoding();
            }
            ApiEndpoint endpoint = new ApiEndpoint(
                    controller,
                    method,
                    httpMethod,
                    parseParameters(method),
                    findMediaType(method),
                    encoding,
                    method.getDeclaredAnnotation(RequiredPermission.class));
            ApiEndpointKey key = new ApiEndpointKey(path, httpMethod);
            if (keyToEndpoint.putIfAbsent(key, endpoint) != null) {
                throw new IllegalArgumentException(
                        "Found a duplicate endpoint ("
                                + key
                                + ")"
                                + " in the controller: "
                                + controllerClass.getName());
            }
        }
    }

    private String findMediaType(Method method) {
        Type type = OpenApiBuilder.unwrapType(method.getGenericReturnType());
        if (!(type instanceof Class<?> returnValueClass)) {
            return APPLICATION_JSON;
        }
        GetMapping mapping = method.getDeclaredAnnotation(GetMapping.class);
        if (mapping != null
                && !mapping.produces()
                        .isBlank()) {
            return mapping.produces();
        }
        if (ByteBuf.class.isAssignableFrom(returnValueClass)
                || BaseFileResource.class.isAssignableFrom(returnValueClass)) {
            return APPLICATION_OCTET_STREAM;
        } else if (CharSequence.class.isAssignableFrom(returnValueClass)) {
            return TEXT_PLAIN_UTF_8;
        }
        return APPLICATION_JSON;
    }

    private MethodParameterInfo[] parseParameters(Method method) {
        Parameter[] parameters = method.getParameters();
        int paramCount = parameters.length;
        MethodParameterInfo[] parameterInfos = new MethodParameterInfo[paramCount];
        boolean hasRequestBody = false;
        boolean hasRequestFormData = false;
        for (int i = 0; i < paramCount; i++) {
            Parameter parameter = parameters[i];
            MethodParameterInfo info = parseAsRequestBody(parameter);
            if (info != null) {
                parameterInfos[i] = info;
                if (hasRequestFormData) {
                    throw new IllegalArgumentException(
                            "The endpoint must not accept both request form data and request body");
                }
                hasRequestBody = true;
                continue;
            }
            info = parseAsRequestFormData(parameter);
            if (info != null) {
                parameterInfos[i] = info;
                if (hasRequestBody) {
                    throw new IllegalArgumentException(
                            "The endpoint must not accept both request form data and request body");
                }
                hasRequestFormData = true;
                continue;
            }
            info = parseAsRequestHeader(parameter);
            if (info != null) {
                parameterInfos[i] = info;
                continue;
            }
            if (RequestContext.class.isAssignableFrom(parameter.getType())) {
                parameterInfos[i] = new MethodParameterInfo(
                        parameter.getName(),
                        RequestContext.class,
                        null,
                        null,
                        null,
                        false,
                        false,
                        false,
                        false,
                        null,
                        null,
                        false);
                continue;
            }
            parameterInfos[i] = parseAsRequestParam(parameter);
        }
        return parameterInfos;
    }

    private MethodParameterInfo parseAsRequestParam(Parameter parameter) {
        QueryParam queryParam = parameter.getDeclaredAnnotation(QueryParam.class);
        Class<?> type = parameter.getType();
        JavaType typeForJackson = JsonUtil.constructJavaType(type);
        Class<?> elementClass = ClassUtil.getElementClass(parameter.getParameterizedType());
        JavaType elementTypeForJackson = elementClass == null
                ? null
                : JsonUtil.constructJavaType(elementClass);
        if (queryParam == null) {
            Object defaultValue = PrimitiveUtil.getDefaultValue(type);
            return new MethodParameterInfo(
                    parameter.getName(),
                    type,
                    typeForJackson,
                    elementClass,
                    elementTypeForJackson,
                    defaultValue == null,
                    false,
                    false,
                    false,
                    defaultValue,
                    null,
                    true);
        }
        String name = queryParam.value()
                .isBlank()
                        ? parameter.getName()
                        : queryParam.value();
        Object parsedDefaultValue = queryParam.defaultValue()
                .isBlank()
                        ? PrimitiveUtil.getDefaultValue(type)
                        : requestParamParser
                                .parsePlainValue(queryParam.defaultValue(), type, typeForJackson);
        return new MethodParameterInfo(
                name,
                type,
                typeForJackson,
                elementClass,
                elementTypeForJackson,
                queryParam.required(),
                false,
                false,
                false,
                parsedDefaultValue,
                null,
                true);
    }

    private MethodParameterInfo parseAsRequestHeader(Parameter parameter) {
        RequestHeader requestHeader = parameter.getDeclaredAnnotation(RequestHeader.class);
        if (requestHeader == null) {
            return null;
        }
        String name = requestHeader.value()
                .isBlank()
                        ? parameter.getName()
                        : requestHeader.value();
        Class<?> type = parameter.getType();
        Class<?> elementClass = ClassUtil.getElementClass(parameter.getParameterizedType());
        return new MethodParameterInfo(
                name,
                type,
                JsonUtil.constructJavaType(type),
                elementClass,
                elementClass == null
                        ? null
                        : JsonUtil.constructJavaType(elementClass),
                requestHeader.required(),
                true,
                false,
                false,
                PrimitiveUtil.getDefaultValue(type),
                null,
                true);
    }

    private MethodParameterInfo parseAsRequestBody(Parameter parameter) {
        RequestBody requestBody = parameter.getDeclaredAnnotation(RequestBody.class);
        if (requestBody == null) {
            return null;
        }
        Class<?> elementClass = ClassUtil.getElementClass(parameter.getParameterizedType());
        Class<?> type = parameter.getType();
        return new MethodParameterInfo(
                parameter.getName(),
                type,
                JsonUtil.constructJavaType(type),
                elementClass,
                elementClass == null
                        ? null
                        : JsonUtil.constructJavaType(elementClass),
                requestBody.required(),
                false,
                true,
                false,
                null,
                null,
                true);
    }

    private MethodParameterInfo parseAsRequestFormData(Parameter parameter) {
        FormData requestFormData = parameter.getDeclaredAnnotation(FormData.class);
        if (requestFormData == null) {
            return null;
        }
        if (!ClassUtil.isListOf(parameter.getParameterizedType(), MultipartFile.class)) {
            throw new IllegalArgumentException(
                    "The type of the form data parameter must be List<MultipartFile>: "
                            + parameter);
        }
        String contentType = requestFormData.contentType();
        return new MethodParameterInfo(
                parameter.getName(),
                List.class,
                null,
                MultipartFile.class,
                null,
                requestFormData.required(),
                false,
                false,
                true,
                null,
                contentType.isBlank()
                        ? null
                        : contentType,
                true);
    }

    @Nullable
    private Pair<HttpMethod, String> parseMethod(Method method) {
        if (method.isAnnotationPresent(GetMapping.class)) {
            return Pair.of(HttpMethod.GET,
                    method.getDeclaredAnnotation(GetMapping.class)
                            .value());
        } else if (method.isAnnotationPresent(PostMapping.class)) {
            return Pair.of(HttpMethod.POST,
                    method.getDeclaredAnnotation(PostMapping.class)
                            .value());
        } else if (method.isAnnotationPresent(PutMapping.class)) {
            return Pair.of(HttpMethod.PUT,
                    method.getDeclaredAnnotation(PutMapping.class)
                            .value());
        } else if (method.isAnnotationPresent(DeleteMapping.class)) {
            return Pair.of(HttpMethod.DELETE,
                    method.getDeclaredAnnotation(DeleteMapping.class)
                            .value());
        } else if (method.isAnnotationPresent(HeadMapping.class)) {
            return Pair.of(HttpMethod.HEAD,
                    method.getDeclaredAnnotation(HeadMapping.class)
                            .value());
        }
        return null;
    }

}