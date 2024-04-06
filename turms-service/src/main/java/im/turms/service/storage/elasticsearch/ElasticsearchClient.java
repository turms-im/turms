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

package im.turms.service.storage.elasticsearch;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.function.Function;
import java.util.function.Supplier;
import jakarta.annotation.Nullable;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectReader;
import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.HttpMethod;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.http.client.HttpClientResponse;

import im.turms.server.common.access.admin.web.HttpUtil;
import im.turms.server.common.infra.io.InputOutputException;
import im.turms.server.common.infra.json.JsonCodecPool;
import im.turms.server.common.infra.json.JsonUtil;
import im.turms.service.storage.elasticsearch.model.BulkRequest;
import im.turms.service.storage.elasticsearch.model.BulkResponse;
import im.turms.service.storage.elasticsearch.model.ClosePointInTimeRequest;
import im.turms.service.storage.elasticsearch.model.CreateIndexRequest;
import im.turms.service.storage.elasticsearch.model.DeleteByQueryRequest;
import im.turms.service.storage.elasticsearch.model.DeleteByQueryResponse;
import im.turms.service.storage.elasticsearch.model.DeleteResponse;
import im.turms.service.storage.elasticsearch.model.ErrorResponse;
import im.turms.service.storage.elasticsearch.model.HealthResponse;
import im.turms.service.storage.elasticsearch.model.SearchRequest;
import im.turms.service.storage.elasticsearch.model.SearchResponse;
import im.turms.service.storage.elasticsearch.model.UpdateByQueryRequest;
import im.turms.service.storage.elasticsearch.model.UpdateByQueryResponse;

/**
 * @author James Chen
 * @implNote Don't use the Elasticsearch official implementation because:
 *           <p>
 *           1. Its jar (elasticsearch-java:8.13.0 is 11.2 MB) is unnecessarily large for our use
 *           cases.
 *           <p>
 *           2. Our implementation is more concise and efficient.
 */
public class ElasticsearchClient {

    private static final Mono EMPTY_RESPONSE = Mono.error(new RuntimeException("Empty response"));
    public static final ObjectReader READER = JsonCodecPool.MAPPER.reader()
            .withoutFeatures(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    private static final ObjectReader READER_HEALTH_RESPONSE = READER.forType(HealthResponse.class);
    private static final ObjectReader READER_DELETE_RESPONSE = READER.forType(DeleteResponse.class);
    private static final ObjectReader READER_DELETE_BY_QUERY_RESPONSE =
            READER.forType(DeleteByQueryResponse.class);
    private static final ObjectReader READER_UPDATE_BY_QUERY_RESPONSE =
            READER.forType(UpdateByQueryResponse.class);
    private static final ObjectReader READER_ERROR_RESPONSE = READER.forType(ErrorResponse.class);

    private final HttpClient httpClient;

    public ElasticsearchClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public Mono<HealthResponse> healthcheck() {
        HttpClient.ResponseReceiver<?> send = httpClient.request(HttpMethod.GET)
                .uri("/_cluster/health");
        return parseResponse(send, inputStream -> {
            try {
                return READER_HEALTH_RESPONSE.readValue(inputStream);
            } catch (IOException e) {
                throw new InputOutputException("Failed to parse the health response", e);
            }
        });
    }

    public Mono<Void> putIndex(String index, CreateIndexRequest request) {
        HttpClient.ResponseReceiver<?> send = httpClient.put()
                .uri("/"
                        + index)
                .send(Mono.fromCallable(() -> JsonUtil.write(512, request)));
        return ignoreResponseBody(send);
    }

    public Mono<Void> putDoc(String index, String id, Supplier<ByteBuf> payloadSupplier) {
        HttpClient.ResponseReceiver<?> send = httpClient.put()
                .uri("/"
                        + index
                        + "/_doc/"
                        + id)
                .send(Mono.fromCallable(payloadSupplier::get));
        return ignoreResponseBody(send);
    }

    public Mono<DeleteResponse> deleteDoc(String index, String id) {
        HttpClient.ResponseReceiver<?> send = httpClient.delete()
                .uri("/"
                        + index
                        + "/_doc/"
                        + id);
        return parseResponse(send, inputStream -> {
            try {
                return READER_DELETE_RESPONSE.readValue(inputStream);
            } catch (IOException e) {
                throw new InputOutputException("Failed to parse the result", e);
            }
        });
    }

    public Mono<DeleteByQueryResponse> deleteByQuery(String index, DeleteByQueryRequest request) {
        HttpClient.ResponseReceiver<?> send = httpClient.post()
                .uri("/"
                        + index
                        + "/_delete_by_query")
                .send(Mono.fromCallable(() -> JsonUtil.write(request)));
        return parseResponse(send, inputStream -> {
            try {
                return READER_DELETE_BY_QUERY_RESPONSE.readValue(inputStream);
            } catch (IOException e) {
                throw new InputOutputException("Failed to parse the result", e);
            }
        });
    }

    public Mono<UpdateByQueryResponse> updateByQuery(String index, UpdateByQueryRequest request) {
        HttpClient.ResponseReceiver<?> send = httpClient.post()
                .uri("/"
                        + index
                        + "/_update_by_query")
                .send(Mono.fromCallable(() -> JsonUtil.write(request)));
        return parseResponse(send, inputStream -> {
            try {
                return READER_UPDATE_BY_QUERY_RESPONSE.readValue(inputStream);
            } catch (IOException e) {
                throw new InputOutputException("Failed to parse the result", e);
            }
        });
    }

    public <T> Mono<SearchResponse<T>> search(
            String index,
            SearchRequest request,
            ObjectReader reader) {
        HttpClient.ResponseReceiver<?> send = httpClient.request(HttpMethod.GET)
                .uri("/"
                        + index
                        + "/_search")
                .send(Mono.fromCallable(() -> JsonUtil.write(request)));
        return parseResponse(send, inputStream -> {
            try {
                return reader.readValue(inputStream);
            } catch (IOException e) {
                throw new InputOutputException("Failed to parse the result", e);
            }
        });
    }

    public Mono<BulkResponse> bulk(BulkRequest request) {
        HttpClient.ResponseReceiver<?> send = httpClient.post()
                .uri("/_bulk")
                .send(Mono.fromCallable(() -> JsonUtil.write(request)));
        return parseResponse(send, inputStream -> {
            try {
                return READER.readValue(inputStream, BulkResponse.class);
            } catch (IOException e) {
                throw new InputOutputException("Failed to parse the result", e);
            }
        });
    }

    public Mono<Void> deletePit(String scrollId) {
        HttpClient.ResponseReceiver<?> send = httpClient.delete()
                .uri("/_pit")
                .send(Mono.fromCallable(() -> {
                    ClosePointInTimeRequest request = new ClosePointInTimeRequest(scrollId);
                    return JsonUtil.write(request);
                }));
        return ignoreResponseBody(send);
    }

    private <T> Mono<T> ignoreResponseBody(HttpClient.ResponseReceiver<?> responseReceiver) {
        return responseReceiver.responseSingle((response, byteBufMono) -> {
            if (HttpUtil.isSuccess(response.status())) {
                return Mono.empty();
            }
            return byteBufMono.asInputStream()
                    .switchIfEmpty(emptyResponse())
                    .flatMap(inputStream -> handleResponse(null, response, inputStream));
        });
    }

    private <T> Mono<T> parseResponse(
            HttpClient.ResponseReceiver<?> responseReceiver,
            Function<InputStream, T> responseTransformer) {
        return responseReceiver.responseSingle((response, byteBufMono) -> byteBufMono
                .asInputStream()
                .switchIfEmpty(emptyResponse())
                .flatMap(
                        inputStream -> handleResponse(responseTransformer, response, inputStream)));
    }

    private <T> Mono<T> handleResponse(
            @Nullable Function<InputStream, T> responseTransformer,
            HttpClientResponse response,
            InputStream inputStream) {
        try {
            if (inputStream.available() == 0) {
                return Mono.error(new RuntimeException(
                        "HTTP response error status: "
                                + response.status()));
            }
        } catch (IOException e) {
            return Mono.error(new RuntimeException("Failed to read HTTP response payload", e));
        }
        if (!HttpUtil.isSuccess(response.status())) {
            inputStream.mark(0);
            try {
                ErrorResponse errorResponse = READER_ERROR_RESPONSE.readValue(inputStream);
                return Mono.error(new ErrorResponseException(errorResponse));
            } catch (Exception e) {
                try {
                    inputStream.reset();
                    return Mono.error(new RuntimeException(
                            "HTTP response error status: "
                                    + response.status()
                                    + ". Response payload: "
                                    + convertStreamToString(inputStream)));
                } catch (IOException ex) {
                    return Mono.error(new RuntimeException(
                            "HTTP response error status: "
                                    + response.status()));
                }
            }
        }
        if (responseTransformer == null) {
            return Mono.empty();
        }
        T responsePayload = responseTransformer.apply(inputStream);
        return Mono.just(responsePayload);
    }

    private <T> Mono<T> emptyResponse() {
        return EMPTY_RESPONSE;
    }

    @Nullable
    private static String convertStreamToString(InputStream inputStream) {
        try {
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            return null;
        }
    }

}