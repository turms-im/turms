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

package im.turms.server.common.access.admin.codec;

import im.turms.server.common.infra.io.FileResource;
import org.reactivestreams.Publisher;
import org.springframework.core.ResolvableType;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.http.codec.ResourceHttpMessageWriter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * spring-framework:v5.3.18 doesn't support triggering a callback
 * after a file is transferred in zero-copy mode.
 * <p>
 * So we use this class to support triggering a callback
 * after a file is transferred in zero-copy mode
 *
 * @author James Chen
 * @see org.springframework.boot.actuate.management.HeapDumpWebEndpoint.TemporaryFileSystemResource#isFile()
 * @see ResourceHttpMessageWriter#zeroCopy
 */
public class TurmsResourceHttpMessageWriter extends ResourceHttpMessageWriter {
    @Override
    public Mono<Void> write(Publisher<? extends Resource> inputStream, ResolvableType elementType, MediaType mediaType, ReactiveHttpOutputMessage message, Map<String, Object> hints) {
        return super.write(inputStream, elementType, mediaType, message, hints)
                .doOnSuccess(unused -> Mono.from(inputStream)
                        .subscribe(resource -> {
                            if (resource instanceof FileResource file) {
                                file.cleanup();
                            }
                        }));
    }

    @Override
    public Mono<Void> write(Publisher<? extends Resource> inputStream, ResolvableType actualType, ResolvableType elementType, MediaType mediaType, ServerHttpRequest request,
                            ServerHttpResponse response, Map<String, Object> hints) {
        return super.write(inputStream, actualType, elementType, mediaType, request, response, hints)
                .doOnSuccess(unused -> Mono.from(inputStream)
                        .subscribe(resource -> {
                            if (resource instanceof FileResource file) {
                                file.cleanup();
                            }
                        }));
    }
}
