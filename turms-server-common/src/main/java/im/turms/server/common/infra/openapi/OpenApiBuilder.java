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

package im.turms.server.common.infra.openapi;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import jakarta.annotation.Nullable;

import com.fasterxml.jackson.databind.ObjectWriter;
import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.HttpMethod;
import io.swagger.v3.core.converter.AnnotatedType;
import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.core.converter.ResolvedSchema;
import io.swagger.v3.core.util.AnnotationsUtils;
import io.swagger.v3.core.util.ObjectMapperFactory;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.ArraySchema;
import io.swagger.v3.oas.models.media.BinarySchema;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.Encoding;
import io.swagger.v3.oas.models.media.FileSchema;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.ObjectSchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.HeaderParameter;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.parameters.QueryParameter;
import io.swagger.v3.oas.models.parameters.RequestBody;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import reactor.core.publisher.Mono;

import im.turms.server.common.access.admin.dto.response.HttpHandlerResult;
import im.turms.server.common.access.admin.web.ApiEndpoint;
import im.turms.server.common.access.admin.web.ApiEndpointKey;
import im.turms.server.common.access.admin.web.MediaTypeConst;
import im.turms.server.common.access.admin.web.MethodParameterInfo;
import im.turms.server.common.infra.lang.StringUtil;
import im.turms.server.common.infra.serialization.SerializationException;

import static im.turms.server.common.access.admin.web.MediaTypeConst.APPLICATION_JAVASCRIPT;
import static im.turms.server.common.access.admin.web.MediaTypeConst.APPLICATION_OCTET_STREAM;
import static im.turms.server.common.access.admin.web.MediaTypeConst.IMAGE_PNG;
import static im.turms.server.common.access.admin.web.MediaTypeConst.TEXT_CSS;
import static im.turms.server.common.access.admin.web.MediaTypeConst.TEXT_CSV_UTF_8;
import static im.turms.server.common.access.admin.web.MediaTypeConst.TEXT_HTML;

/**
 * @author James Chen
 */
public class OpenApiBuilder {

    private static final String TITLE = "Turms";
    private static final String EXTERNAL_DOCUMENTATION_DESCRIPTION = "Turms Documentation";
    private static final String EXTERNAL_DOCUMENTATION_URL = "https://turms-im.github.io/docs";

    private static final String FORM_DATA_PROPERTY_FILE = "filename";

    private OpenApiBuilder() {
    }

    public static byte[] build(
            String version,
            String nodeType,
            String serverUrl,
            Map<ApiEndpointKey, ApiEndpoint> keyToEndpoint) {
        OpenAPI api = new OpenAPI().info(new Info().title(TITLE
                + " - "
                + nodeType)
                .version(version))
                .addServersItem(new Server().url(serverUrl))
                .externalDocs(
                        new ExternalDocumentation().description(EXTERNAL_DOCUMENTATION_DESCRIPTION)
                                .url(EXTERNAL_DOCUMENTATION_URL))
                .components(new Components().addSecuritySchemes("BasicAuth",
                        new SecurityScheme().type(SecurityScheme.Type.HTTP)
                                .scheme("basic")));
        TreeMap<String, Schema> schemas = new TreeMap<>();
        TreeMap<String, PathItem> paths = new TreeMap<>();
        for (Map.Entry<ApiEndpointKey, ApiEndpoint> entry : keyToEndpoint.entrySet()) {
            ApiEndpoint endpoint = entry.getValue();
            ResolvedSchema responseSchema = getResponseSchema(endpoint.method());
            if (responseSchema != null) {
                schemas.putAll(responseSchema.referencedSchemas);
            }
            OperationItem operationItem = getOperation(endpoint,
                    responseSchema == null
                            ? null
                            : responseSchema.schema);
            for (ResolvedSchema paramSchema : operationItem.paramSchemas()) {
                schemas.putAll(paramSchema.referencedSchemas);
            }
            paths.computeIfAbsent(entry.getKey()
                    .path(), path -> new PathItem())
                    .operation(operationItem.method(), operationItem.operation());
        }
        for (Map.Entry<String, Schema> entry : schemas.entrySet()) {
            api.schema(entry.getKey(), entry.getValue());
        }
        for (Map.Entry<String, PathItem> entry : paths.entrySet()) {
            api.path(entry.getKey(), entry.getValue());
        }
        ObjectWriter writer = ObjectMapperFactory.buildStrictGenericObjectMapper()
                .writerWithDefaultPrettyPrinter();
        try {
            return writer.writeValueAsBytes(api);
        } catch (Exception e) {
            throw new SerializationException("Failed to write the OpenAPI as a JSON string", e);
        }
    }

    @Nullable
    private static ResolvedSchema getResponseSchema(Method method) {
        Type schemaType = AnnotationsUtils.getSchemaType(
                method.getAnnotation(io.swagger.v3.oas.annotations.media.Schema.class),
                true);
        if (schemaType == null) {
            schemaType = unwrapType(method.getGenericReturnType());
            if (schemaType instanceof Class<?> clazz && ByteBuf.class.isAssignableFrom(clazz)) {
                return null;
            }
        } else {
            schemaType = unwrapType(schemaType);
        }
        return ModelConverters.getInstance()
                .resolveAsResolvedSchema(new AnnotatedType(schemaType));
    }

    public static Type unwrapType(Type type) {
        if (type instanceof ParameterizedType parameterizedType
                && parameterizedType.getRawType() instanceof Class<?> rawType
                && (Mono.class.isAssignableFrom(rawType)
                        || HttpHandlerResult.class.isAssignableFrom(rawType))) {
            return unwrapType(parameterizedType.getActualTypeArguments()[0]);
        }
        return type;
    }

    private static OperationItem getOperation(
            ApiEndpoint endpoint,
            @Nullable Schema<?> responseSchema) {
        HttpMethod httpMethod = endpoint.httpMethod();
        PathItem.HttpMethod httpMethodEnum = PathItem.HttpMethod.valueOf(httpMethod.name());
        Method method = endpoint.method();
        responseSchema = switch (endpoint.mediaType()) {
            case APPLICATION_OCTET_STREAM, TEXT_CSV_UTF_8, APPLICATION_JAVASCRIPT, TEXT_CSS,
                    TEXT_HTML, IMAGE_PNG ->
                new FileSchema();
            default -> responseSchema == null
                    ? new ObjectSchema()
                    : new Schema<>().$ref(responseSchema.getName());
        };

        Operation operation = new Operation().operationId(method.getName())
                .addTagsItem(StringUtil.upperCamelToLowerHyphenLatin1(endpoint.controller()
                        .getClass()
                        .getSimpleName()))
                .responses(new ApiResponses()
                        .addApiResponse("200",
                                new ApiResponse().description("OK")
                                        .content(new Content().addMediaType(endpoint.mediaType(),
                                                new MediaType().schema(responseSchema))))
                        .addApiResponse("400", new ApiResponse().description("Bad Request"))
                        .addApiResponse("401", new ApiResponse().description("Unauthorized"))
                        .addApiResponse("403", new ApiResponse().description("Forbidden"))
                        .addApiResponse("404", new ApiResponse().description("Not Found"))
                        .addApiResponse("500",
                                new ApiResponse().description("Internal Server Error"))
                        .addApiResponse("503",
                                new ApiResponse().description("Service Unavailable")));
        MethodParameterInfo[] parameters = endpoint.parameters();
        List<ResolvedSchema> paramSchemas = new ArrayList<>(parameters.length);
        ModelConverters converters = ModelConverters.getInstance();
        for (MethodParameterInfo parameter : parameters) {
            if (!parameter.isVisibleForOpenApi()) {
                continue;
            }
            Class<?> elementType = parameter.elementType();
            ResolvedSchema paramSchema = null;
            if (!parameter.isFormData()) {
                paramSchema = converters.resolveAsResolvedSchema(new AnnotatedType(
                        unwrapType(elementType == null
                                ? parameter.type()
                                : elementType)));
                paramSchemas.add(paramSchema);
            }
            if (parameter.isBody() || parameter.isFormData()) {
                Content content = new Content();
                if (parameter.isBody()) {
                    content.addMediaType(MediaTypeConst.APPLICATION_JSON,
                            new MediaType().schema(elementType == null
                                    ? paramSchema.schema
                                    : new ArraySchema().items(paramSchema.schema)));
                } else {
                    MediaType mediaType = new MediaType();
                    content.addMediaType(MediaTypeConst.MULTIPART_FORM_DATA,
                            mediaType.schema(new ObjectSchema().addProperty(FORM_DATA_PROPERTY_FILE,
                                    new ArraySchema().items(new BinarySchema()))));
                    String contentType = parameter.contentType();
                    if (contentType != null) {
                        mediaType.addEncoding(FORM_DATA_PROPERTY_FILE,
                                new Encoding().contentType(contentType));
                    }
                }
                operation.requestBody(new RequestBody().required(parameter.isRequired())
                        .content(content));
            } else {
                Parameter parameterItem = parameter.isHeader()
                        ? new HeaderParameter()
                        : new QueryParameter();
                operation.addParametersItem(parameterItem.name(parameter.name())
                        .required(parameter.isRequired())
                        .schema(elementType == null
                                ? paramSchema.schema
                                : new ArraySchema().items(paramSchema.schema)));
            }
        }
        if (endpoint.permission() != null) {
            operation.addSecurityItem(new SecurityRequirement().addList("BasicAuth"));
        }
        return new OperationItem(httpMethodEnum, operation, paramSchemas);
    }

}