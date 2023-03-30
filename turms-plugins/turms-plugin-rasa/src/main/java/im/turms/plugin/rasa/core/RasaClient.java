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

package im.turms.plugin.rasa.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.time.Duration;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.util.concurrent.FastThreadLocal;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import im.turms.server.common.infra.io.InputOutputException;
import im.turms.server.common.infra.json.JsonCodecPool;
import im.turms.server.common.infra.lang.StringUtil;
import im.turms.server.common.infra.netty.ReferenceCountUtil;

/**
 * @author James Chen
 */
public class RasaClient {

    private static final ConnectionProvider CONNECTION_PROVIDER =
            ConnectionProvider.builder("rasa-client")
                    .maxConnections(100)
                    .build();
    private static final FastThreadLocal<HttpClient> HTTP_CLIENT_POOL = new FastThreadLocal<>() {
        @Override
        protected HttpClient initialValue() {
            return HttpClient.create(CONNECTION_PROVIDER);
        }
    };
    private static final ObjectWriter REQUEST_WRITER =
            JsonCodecPool.MAPPER.writerFor(RasaRequest.class);
    private static final ObjectReader RESPONSES_READER =
            JsonCodecPool.MAPPER.readerForListOf(RasaResponse.class);

    private final URI url;
    private final Duration requestTimeout;

    public RasaClient(URI url, int requestTimeoutMillis) {
        this.url = url;
        this.requestTimeout = Duration.ofMillis(requestTimeoutMillis);
    }

    public Mono<List<RasaResponse>> sendRequest(RasaRequest request) {
        return HTTP_CLIENT_POOL.get()
                .post()
                .uri(url)
                .send((httpRequest, out) -> {
                    httpRequest.responseTimeout(requestTimeout);
                    int size = 48 + StringUtil.getLength(request.message());
                    ByteBuf requestBuffer = PooledByteBufAllocator.DEFAULT.directBuffer(size);
                    try {
                        ByteBufOutputStream outputStream = new ByteBufOutputStream(requestBuffer);
                        REQUEST_WRITER.writeValue((OutputStream) outputStream, request);
                    } catch (Exception e) {
                        ReferenceCountUtil.ensureReleased(requestBuffer);
                        throw new InputOutputException("Failed to write the request", e);
                    }
                    return out.sendObject(requestBuffer);
                })
                .responseContent()
                .aggregate()
                .map(response -> {
                    try {
                        return RESPONSES_READER
                                .readValue((InputStream) new ByteBufInputStream(response));
                    } catch (IOException e) {
                        throw new InputOutputException("Failed to read Rasa responses", e);
                    }
                });
    }

}
