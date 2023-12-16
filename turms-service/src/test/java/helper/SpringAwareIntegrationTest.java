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

package helper;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Base64;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import io.netty.buffer.ByteBufInputStream;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpResponseStatus;
import lombok.Getter;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.util.MimeTypeUtils;
import reactor.netty.http.client.HttpClient;

import im.turms.server.common.access.admin.dto.response.ResponseDTO;
import im.turms.server.common.infra.cluster.node.Node;
import im.turms.server.common.infra.property.TurmsProperties;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.property.env.common.adminapi.AdminHttpProperties;
import im.turms.server.common.testing.BaseIntegrationTest;
import im.turms.service.TurmsServiceApplication;

import static org.assertj.core.api.Assertions.assertThat;

//
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.NONE,
        classes = {TurmsServiceApplication.class, TestEnvironmentConfig.class},
        properties = "spring.profiles.active=test")
public abstract class SpringAwareIntegrationTest extends BaseIntegrationTest {

    private static final String BASIC_AUTH = "Basic "
            + Base64.getEncoder()
                    .encodeToString("turms:turms".getBytes());
    private static final ObjectMapper MAPPER = JsonMapper.builder()
            .addModule(new ParameterNamesModule())
            .build();

    @Autowired
    @Getter
    private ApplicationContext context;

    public HttpClient adminHttpClient;

    private synchronized void initAdminHttpClient() {
        if (adminHttpClient != null) {
            return;
        }
        Node node = context.getBean(Node.class);
        TurmsPropertiesManager propertiesManager = context.getBean(TurmsPropertiesManager.class);
        TurmsProperties properties = propertiesManager.getLocalProperties();
        AdminHttpProperties httpProperties = switch (node.getNodeType()) {
            case AI_SERVING -> properties.getAiServing()
                    .getAdminApi()
                    .getHttp();
            case GATEWAY -> properties.getGateway()
                    .getAdminApi()
                    .getHttp();
            case SERVICE -> properties.getService()
                    .getAdminApi()
                    .getHttp();
        };
        adminHttpClient = HttpClient.create()
                .host("127.0.0.1")
                .port(httpProperties.getPort());
    }

    public <T> ResponseDTO<T> sendHttpRequest(String uri) {
        initAdminHttpClient();
        return adminHttpClient
                .headers(httpHeaders -> httpHeaders.add(HttpHeaderNames.AUTHORIZATION, BASIC_AUTH)
                        .add(HttpHeaderNames.ACCEPT, MimeTypeUtils.APPLICATION_JSON_VALUE))
                .get()
                .uri(uri)
                .responseSingle((response, bufferMono) -> {
                    assertThat(response.status()).isEqualTo(HttpResponseStatus.OK);
                    return bufferMono.map(buffer -> {
                        try {
                            return MAPPER.readValue((InputStream) new ByteBufInputStream(buffer),
                                    ResponseDTO.class);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                })
                .block(Duration.ofMinutes(1));
    }

}