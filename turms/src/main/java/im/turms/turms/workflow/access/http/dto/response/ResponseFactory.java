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

package im.turms.turms.workflow.access.http.dto.response;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.exception.TurmsBusinessException;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;

/**
 * @author James Chen
 */
@Data
public final class ResponseFactory {

    private ResponseFactory() {
    }

    public static final ResponseEntity<ResponseDTO<Void>> OK = ResponseEntity
            .ok()
            .body(new ResponseDTO<>(TurmsStatusCode.OK, null));

    public static <T> Mono<ResponseEntity<ResponseDTO<PaginationDTO<T>>>> page(Mono<Long> totalMono, Flux<T> data) {
        Mono<PaginationDTO<T>> mono = Mono
                .zip(totalMono, data.collectList())
                .map(tuple -> {
                    Long total = tuple.getT1();
                    if (total.equals(0L)) {
                        throw TurmsBusinessException.get(TurmsStatusCode.NO_CONTENT);
                    }
                    return new PaginationDTO<>(total, tuple.getT2());
                });
        return okIfTruthy(mono);
    }

    public static Mono<ResponseEntity<ResponseDTO<UpdateResultDTO>>> updateResult(Mono<UpdateResult> data) {
        return okIfTruthy(data.map(UpdateResultDTO::get));
    }

    public static Mono<ResponseEntity<ResponseDTO<DeleteResultDTO>>> deleteResult(Mono<DeleteResult> data) {
        return okIfTruthy(data.map(DeleteResultDTO::get));
    }

    // Base methods

    public static <T> Mono<ResponseEntity<ResponseDTO<Collection<T>>>> okIfTruthy(Flux<T> data) {
        return data
                .collectList()
                .map(ResponseFactory::okIfTruthy);
    }

    public static <T> Mono<ResponseEntity<ResponseDTO<T>>> okIfTruthy(Mono<T> dataMono) {
        return dataMono
                .map(data -> ResponseEntity.ok(new ResponseDTO<>(TurmsStatusCode.OK, data)))
                .switchIfEmpty(Mono.error(TurmsBusinessException.get(TurmsStatusCode.NO_CONTENT)));
    }

    public static <T> Mono<ResponseEntity<T>> raw(Mono<T> dataMono) {
        return dataMono
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.error(TurmsBusinessException.get(TurmsStatusCode.NO_CONTENT)));
    }

    public static <T> ResponseEntity<ResponseDTO<T>> okIfTruthy(T data) {
        return ResponseEntity.ok(new ResponseDTO<>(TurmsStatusCode.OK, data));
    }

    public static <T> ResponseEntity<T> raw(T data) {
        return ResponseEntity.ok(data);
    }

    public static <T> ResponseEntity<ResponseDTO<Collection<T>>> okIfTruthy(Collection<T> data) {
        if (data != null) {
            if (data.isEmpty()) {
                return ResponseEntity.status(TurmsStatusCode.NO_CONTENT.getHttpStatusCode())
                        .body(new ResponseDTO<>(TurmsStatusCode.NO_CONTENT, null));
            } else {
                return ResponseEntity.ok(new ResponseDTO<>(TurmsStatusCode.OK, data));
            }
        } else {
            return ResponseEntity.status(TurmsStatusCode.NO_CONTENT.getHttpStatusCode())
                    .body(new ResponseDTO<>(TurmsStatusCode.NO_CONTENT, null));
        }
    }

}