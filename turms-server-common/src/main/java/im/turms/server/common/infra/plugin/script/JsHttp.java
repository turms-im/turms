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

package im.turms.server.common.infra.plugin.script;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Map;
import java.util.function.BiFunction;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.util.concurrent.FastThreadLocal;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.graalvm.polyglot.Value;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;
import reactor.netty.NettyOutbound;
import reactor.netty.http.client.HttpClient;
import reactor.netty.http.client.HttpClientRequest;
import reactor.netty.http.client.HttpClientResponse;
import reactor.netty.resources.ConnectionProvider;

import im.turms.server.common.infra.lang.Pair;
import im.turms.server.common.infra.time.DurationConst;

/**
 * @author James Chen
 */
public class JsHttp {

    private static final ConnectionProvider CONNECTION_PROVIDER =
            ConnectionProvider.builder("turms-js-http")
                    .maxConnections(100)
                    .build();
    private static final FetchOptions DEFAULT_OPTIONS = new FetchOptions();
    private static final FastThreadLocal<HttpClient> HTTP_CLIENT_POOL = new FastThreadLocal<>() {
        @Override
        protected HttpClient initialValue() {
            return HttpClient.create(CONNECTION_PROVIDER);
        }
    };

    public Thenable fetch(String uri) {
        return fetch(uri, DEFAULT_OPTIONS);
    }

    public Thenable fetch(String uri, FetchOptions options) {
        return (resolve, reject) -> {
            try {
                fetch0(resolve, reject, uri, options);
            } catch (Exception e) {
                reject.execute(e);
            }
        };
    }

    private void fetch0(Value resolve, Value reject, String uri, FetchOptions options) {
        if (uri == null) {
            reject.execute(new IllegalArgumentException("The URI must not be null"));
            return;
        }
        if (options == null) {
            options = DEFAULT_OPTIONS;
        }
        String method = options.method;
        HttpMethod httpMethod = method == null
                ? HttpMethod.GET
                : HttpMethod.valueOf(method);
        Integer timeoutMillis = options.timeout;
        Duration timeout = timeoutMillis == null
                ? DurationConst.FIVE_MINUTES
                : Duration.ofMillis(timeoutMillis);
        HttpClient httpClient = HTTP_CLIENT_POOL.get();
        Map<String, String> headers = options.headers;
        if (headers != null) {
            httpClient.headers(entries -> {
                entries.clear();
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    entries.add(entry.getKey(), entry.getValue());
                }
            });
        }
        HttpClient.RequestSender sender = httpClient.request(httpMethod)
                .uri(uri);
        BiFunction<HttpClientRequest, NettyOutbound, Publisher<Void>> sendConfigurer;
        String body = options.body;
        if (body == null) {
            sendConfigurer = (request, outbound) -> {
                request.responseTimeout(timeout);
                return Mono.empty();
            };
        } else {
            sendConfigurer = (request, outbound) -> {
                request.responseTimeout(timeout);
                return outbound.send(Mono.fromCallable(() -> {
                    ByteBuf bodyBuffer = PooledByteBufAllocator.DEFAULT.directBuffer(body.length());
                    bodyBuffer.writeCharSequence(body, StandardCharsets.UTF_8);
                    return bodyBuffer;
                }));
            };
        }
        sender.send(sendConfigurer)
                .responseSingle((response, buffer) -> buffer.asString()
                        .map(data -> Pair.of(response, data)))
                .subscribe(pair -> {
                    HttpClientResponse response = pair.first();
                    FetchResponse fetchResponse = new FetchResponse(
                            response.status()
                                    .code(),
                            response.responseHeaders(),
                            pair.second());
                    resolve.execute(fetchResponse);
                }, reject::execute);
    }

    @AllArgsConstructor
    @NoArgsConstructor
    public static class FetchOptions {
        public String method;
        public Map<String, String> headers;
        public String body;
        public Integer timeout;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    public static class FetchResponse {
        public int status;
        public HttpHeaders headers;
        public String data;
    }

}
