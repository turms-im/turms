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

package system;

import java.time.Duration;

import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author James Chen
 */
public abstract class BaseSystemTest {

    private static final Duration HTTP_TIMEOUT = Duration.ofSeconds(10);

    protected void assertTurmsGatewayAvailable(String host, int port) {
        String uri = "http://"
                + host
                + ":"
                + port
                + "/health";
        Mono<String> response = HttpClient.create()
                .get()
                .uri(uri)
                .responseContent()
                .aggregate()
                .asString()
                .timeout(HTTP_TIMEOUT);
        StepVerifier.create(response)
                .expectNextMatches(resp -> {
                    assertThat(resp).contains("\"UP\"");
                    return true;
                })
                .verifyComplete();
    }

    static {
        System.setProperty("log4j2.contextSelector",
                "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");
    }

}