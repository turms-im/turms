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

package im.turms.server.common.access.admin.dto.response;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import jakarta.annotation.Nullable;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import io.netty.handler.codec.http.HttpResponseStatus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.infra.collection.CollectorUtil;
import im.turms.server.common.infra.exception.ResponseException;
import im.turms.server.common.infra.exception.ResponseExceptionPublisherPool;
import im.turms.server.common.infra.validation.Validator;

/**
 * @author James Chen
 */
public record HttpHandlerResult<T>(
        HttpResponseStatus status,
        @Nullable Map<String, String> headers,
        @Nullable T body
) {

    public static final HttpHandlerResult<Void> OK =
            new HttpHandlerResult<>(HttpResponseStatus.OK, null, null);

    public static final HttpHandlerResult<ResponseDTO<Void>> RESPONSE_OK = HttpHandlerResult
            .create(HttpResponseStatus.OK, new ResponseDTO<>(ResponseStatusCode.OK, null));

    public static final HttpHandlerResult<ResponseDTO<Void>> RESPONSE_NO_CONTENT =
            HttpHandlerResult.create(ResponseStatusCode.NO_CONTENT.getHttpStatusCode(),
                    new ResponseDTO<>(ResponseStatusCode.NO_CONTENT, null));

    public static <T> HttpHandlerResult<T> create(
            HttpResponseStatus status,
            @Nullable Map<String, String> headers,
            @Nullable T body) {
        return new HttpHandlerResult<>(status, headers, body);
    }

    public static <T> HttpHandlerResult<T> create(
            int status,
            @Nullable Map<String, String> headers,
            @Nullable T body) {
        return new HttpHandlerResult<>(HttpResponseStatus.valueOf(status), headers, body);
    }

    public static <T> HttpHandlerResult<T> create(HttpResponseStatus status) {
        return new HttpHandlerResult<>(status, null, null);
    }

    public static <T> HttpHandlerResult<T> create(
            HttpResponseStatus status,
            @Nullable Map<String, String> headers) {
        return new HttpHandlerResult<>(status, headers, null);
    }

    public static <T> HttpHandlerResult<T> create(HttpResponseStatus status, @Nullable T body) {
        return new HttpHandlerResult<>(status, null, body);
    }

    public static <T> HttpHandlerResult<T> create(int status, @Nullable T body) {
        return new HttpHandlerResult<>(HttpResponseStatus.valueOf(status), null, body);
    }

    public static HttpHandlerResult<ResponseDTO<?>> unauthorized(String reason) {
        return HttpHandlerResult.create(HttpResponseStatus.UNAUTHORIZED,
                new ResponseDTO<>(ResponseStatusCode.UNAUTHORIZED, reason));
    }

    public static HttpHandlerResult<ResponseDTO<?>> badRequest(String reason) {
        return HttpHandlerResult.create(HttpResponseStatus.BAD_REQUEST,
                new ResponseDTO<>(ResponseStatusCode.ILLEGAL_ARGUMENT, reason));
    }

    public static <T> HttpHandlerResult<ResponseDTO<PaginationDTO<T>>> page(
            long total,
            Collection<T> data) {
        if (total <= 0) {
            throw ResponseException.get(ResponseStatusCode.NO_CONTENT);
        }
        PaginationDTO<T> pagination = new PaginationDTO<>(total, data);
        return okIfTruthy(pagination);
    }

    public static <T> Mono<HttpHandlerResult<ResponseDTO<PaginationDTO<T>>>> page(
            Mono<Long> totalMono,
            Flux<T> data) {
        Mono<PaginationDTO<T>> mono =
                Mono.zip(totalMono, data.collect(CollectorUtil.toChunkedList()))
                        .map(tuple -> {
                            Long total = tuple.getT1();
                            if (total <= 0L) {
                                throw ResponseException.get(ResponseStatusCode.NO_CONTENT);
                            }
                            return new PaginationDTO<>(total, tuple.getT2());
                        });
        return okIfTruthy(mono);
    }

    public static <T> Mono<HttpHandlerResult<ResponseDTO<PaginationDTO<T>>>> page(
            Mono<Long> totalMono,
            Mono<List<T>> data) {
        Mono<PaginationDTO<T>> mono = Mono.zip(totalMono, data)
                .map(tuple -> {
                    Long total = tuple.getT1();
                    if (total <= 0L) {
                        throw ResponseException.get(ResponseStatusCode.NO_CONTENT);
                    }
                    return new PaginationDTO<>(total, tuple.getT2());
                });
        return okIfTruthy(mono);
    }

    public static Mono<HttpHandlerResult<ResponseDTO<UpdateResultDTO>>> updateResult(
            Mono<UpdateResult> data) {
        return okIfTruthy(data.map(UpdateResultDTO::get));
    }

    public static HttpHandlerResult<ResponseDTO<UpdateResultDTO>> updateResult(long modifiedCount) {
        return okIfTruthy(new UpdateResultDTO(modifiedCount, modifiedCount));
    }

    public static Mono<HttpHandlerResult<ResponseDTO<DeleteResultDTO>>> deleteResult(
            Mono<DeleteResult> data) {
        return okIfTruthy(data.map(DeleteResultDTO::get));
    }

    public static HttpHandlerResult<ResponseDTO<DeleteResultDTO>> deleteResult(long deletedCount) {
        return okIfTruthy(new DeleteResultDTO(deletedCount));
    }

    public static <T> Mono<HttpHandlerResult<ResponseDTO<Collection<T>>>> okIfTruthy(Flux<T> data) {
        return data.collect(CollectorUtil.toChunkedList())
                .map(HttpHandlerResult::okIfTruthy);
    }

    public static <T> Mono<HttpHandlerResult<ResponseDTO<Collection<T>>>> okIfTruthy(
            Flux<T> data,
            int estimatedSize) {
        return data.collect(CollectorUtil.toList(estimatedSize))
                .map(HttpHandlerResult::okIfTruthy);
    }

    public static <T> Mono<HttpHandlerResult<ResponseDTO<T>>> okIfTruthy(Mono<T> dataMono) {
        return dataMono
                .map(data -> HttpHandlerResult.create(HttpResponseStatus.OK,
                        new ResponseDTO<>(ResponseStatusCode.OK, data)))
                .switchIfEmpty(ResponseExceptionPublisherPool.noContent());
    }

    public static <T> HttpHandlerResult<ResponseDTO<T>> okIfTruthy(T data) {
        if (Validator.isFalsy(data)) {
            return (HttpHandlerResult) RESPONSE_NO_CONTENT;
        }
        return HttpHandlerResult.create(HttpResponseStatus.OK,
                new ResponseDTO<>(ResponseStatusCode.OK, data));
    }

    public static <T> HttpHandlerResult<ResponseDTO<Collection<T>>> okIfTruthy(Collection<T> data) {
        if (data == null || data.isEmpty()) {
            return (HttpHandlerResult) RESPONSE_NO_CONTENT;
        }
        return HttpHandlerResult.create(HttpResponseStatus.OK,
                new ResponseDTO<>(ResponseStatusCode.OK, data));
    }

}