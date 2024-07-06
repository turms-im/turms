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

package im.turms.server.common.testing;

import java.io.IOException;
import java.time.Duration;
import java.util.Base64;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import io.netty.buffer.ByteBufInputStream;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpResponseStatus;
import lombok.Getter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.util.MimeTypeUtils;
import reactor.netty.http.client.HttpClient;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.NONE,
        properties = "spring.profiles.active=test")
public abstract class BaseSpringAwareIntegrationTest extends BaseIntegrationTest {

    private static final String BASIC_AUTH = "Basic "
            + Base64.getEncoder()
                    .encodeToString("turms:turms".getBytes());
    private static final ObjectMapper MAPPER = JsonMapper.builder()
            .addModule(new ParameterNamesModule())
            .build();

    @Autowired
    @Getter
    protected ApplicationContext context;

    protected HttpClient adminApiHttpClient;

    protected int getAdminApiHttpServerPort() {
        return -1;
    }

    private synchronized void initAdminHttpClient() {
        if (adminApiHttpClient != null) {
            return;
        }
        int port = getAdminApiHttpServerPort();
        if (port <= 0) {
            throw new IllegalStateException("The admin API HTTP server port is not configured");
        }
        adminApiHttpClient = HttpClient.create()
                .host("127.0.0.1")
                .port(port);
    }

    public <T> T sendHttpRequest(String uri, TypeReference<T> typeReference) {
        initAdminHttpClient();
        return adminApiHttpClient
                .headers(httpHeaders -> httpHeaders.add(HttpHeaderNames.AUTHORIZATION, BASIC_AUTH)
                        .add(HttpHeaderNames.ACCEPT, MimeTypeUtils.APPLICATION_JSON_VALUE))
                .get()
                .uri(uri)
                .responseSingle((response, bufferMono) -> {
                    Assertions.assertThat(response.status())
                            .isEqualTo(HttpResponseStatus.OK);
                    return bufferMono.map(buffer -> {
                        try {
                            return MAPPER.readValue(new ByteBufInputStream(buffer), typeReference);
                        } catch (IOException e) {
                            throw new RuntimeException("Failed to deserialize the response", e);
                        }
                    });
                })
                .block(Duration.ofMinutes(1));
    }

}