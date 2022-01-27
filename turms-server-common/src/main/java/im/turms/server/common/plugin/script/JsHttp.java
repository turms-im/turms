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

package im.turms.server.common.plugin.script;

import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.util.Pair;
import reactor.core.publisher.Mono;
import reactor.netty.ByteBufFlux;
import reactor.netty.http.client.HttpClient;
import reactor.netty.http.client.HttpClientResponse;

import java.util.Map;

/**
 * @author James Chen
 */
public class JsHttp {

    private static final HttpClient HTTP_CLIENT = HttpClient.create();

    public Thenable fetch(String uri, FetchOptions options) {
        return (resolve, reject) -> {
            String method = options.method;
            HttpMethod httpMethod = method == null
                    ? HttpMethod.GET
                    : HttpMethod.valueOf(method);
            if (options.headers != null) {
                HTTP_CLIENT.headers(entries -> {
                    for (Map.Entry<String, String> entry : options.headers.entrySet()) {
                        entries.add(entry.getKey(), entry.getValue());
                    }
                });
            }
            HttpClient.RequestSender sender = HTTP_CLIENT
                    .request(httpMethod)
                    .uri(uri);
            Mono<Pair<HttpClientResponse, String>> responseMono;
            if (options.body == null) {
                responseMono = sender.responseSingle((response, buffer) -> buffer.asString()
                        .map(data -> Pair.of(response, data)));
            } else {
                ByteBufFlux body = ByteBufFlux.fromString(Mono.just(options.body));
                responseMono = sender.send(body).responseSingle((response, buffer) -> buffer.asString()
                        .map(data -> Pair.of(response, data)));
            }
            responseMono.subscribe(pair -> {
                        HttpClientResponse response = pair.getFirst();
                        FetchResponse fetchResponse = new FetchResponse(response.status().code(),
                                response.responseHeaders(),
                                pair.getSecond());
                        resolve.execute(fetchResponse);
                    },
                    reject::execute);
        };
    }

    @AllArgsConstructor
    @NoArgsConstructor
    public class FetchOptions {
        public String method;
        public Map<String, String> headers;
        public String body;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    public class FetchResponse {
        public int status;
        public HttpHeaders headers;
        public String data;
    }

}
