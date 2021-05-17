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

import im.turms.server.common.access.http.dto.response.ResponseDTO;
import im.turms.server.common.testing.BaseIntegrationTest;
import im.turms.turms.TurmsApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.Base64Utils;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = {TurmsApplication.class, ContainerConfig.class},
        properties = "spring.profiles.active=test"
)
public abstract class SpringAwareIntegrationTest extends BaseIntegrationTest {

    private static final String BASIC_AUTH = "Basic " + Base64Utils.encodeToString("turms:turms".getBytes());

    @Autowired
    public WebTestClient webClient;

    public <T> ResponseDTO<T> getResponse(WebTestClient.RequestHeadersSpec<?> spec) {
        return spec
                .header(AUTHORIZATION, BASIC_AUTH)
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(ResponseDTO.class)
                .returnResult()
                .getResponseBody();
    }

}