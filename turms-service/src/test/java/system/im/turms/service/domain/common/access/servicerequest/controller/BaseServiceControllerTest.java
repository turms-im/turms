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

package system.im.turms.service.domain.common.access.servicerequest.controller;

import java.lang.reflect.ParameterizedType;
import java.util.function.Consumer;
import jakarta.annotation.Nullable;

import helper.SpringAwareIntegrationTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.infra.exception.ResponseException;
import im.turms.service.access.servicerequest.dto.RequestHandlerResult;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author James Chen
 */
public class BaseServiceControllerTest<T> extends SpringAwareIntegrationTest {

    private final Class<T> controllerClass;

    public BaseServiceControllerTest() {
        controllerClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    protected void assertResult(
            Mono<RequestHandlerResult> resultMono,
            @Nullable Consumer<RequestHandlerResult> resultConsumer,
            @Nullable ResponseStatusCode... expectedCodes) {
        Mono<?> mono = resultMono.switchIfEmpty(Mono.error(new RuntimeException("No result")))
                .onErrorResume(ResponseException.class, e -> {
                    assertThat(e.getCode()).isIn((Object[]) expectedCodes);
                    return Mono.empty();
                })
                .flatMap(result -> {
                    if (expectedCodes != null && expectedCodes.length > 0) {
                        assertThat(result.code()).isIn((Object[]) expectedCodes);
                    }
                    if (resultConsumer != null) {
                        resultConsumer.accept(result);
                    }
                    return Mono.empty();
                });
        StepVerifier.create(mono)
                .verifyComplete();
    }

    protected void assertResultCodes(
            Mono<RequestHandlerResult> resultMono,
            ResponseStatusCode... expectedCodes) {
        assertResult(resultMono, null, expectedCodes);
    }

    protected void assertResultIsOk(
            Mono<RequestHandlerResult> resultMono,
            @Nullable Consumer<RequestHandlerResult> resultConsumer) {
        assertResult(resultMono, resultConsumer);
    }

    protected void assertResultIsOk(Mono<RequestHandlerResult> resultMono) {
        assertResult(resultMono, null, ResponseStatusCode.OK);
    }

    protected void assertResultIsOkAndRecipients(
            Mono<RequestHandlerResult> resultMono,
            @Nullable Consumer<RequestHandlerResult> resultConsumer,
            @Nullable Long... recipients) {
        StepVerifier.create(resultMono)
                .expectNextMatches(result -> {
                    assertThat(result.code()).as("The status code should be "
                            + ResponseStatusCode.OK)
                            .isEqualTo(ResponseStatusCode.OK);
                    if (resultConsumer != null) {
                        resultConsumer.accept(result);
                    }
                    if (recipients != null && recipients.length > 0) {
                        assertThat(result.recipients()).containsExactlyInAnyOrder(recipients);
                    }
                    return true;
                })
                .verifyComplete();
    }

    protected T getController() {
        return getContext().getBean(controllerClass);
    }

}
