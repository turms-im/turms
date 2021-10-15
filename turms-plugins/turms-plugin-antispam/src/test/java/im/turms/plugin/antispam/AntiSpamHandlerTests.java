/*
 * Copyright (C) 2019 The Turms Project
 * https://github.com/turms-im/turms
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package im.turms.plugin.antispam;

import im.turms.common.constant.DeviceType;
import im.turms.common.model.dto.request.TurmsRequest;
import im.turms.common.model.dto.request.group.CreateGroupRequest;
import im.turms.plugin.antispam.ac.Store;
import im.turms.plugin.antispam.property.AntiSpamProperties;
import im.turms.plugin.antispam.property.UnwantedWordHandleStrategy;
import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.exception.TurmsBusinessException;
import im.turms.service.workflow.access.servicerequest.dto.ClientRequest;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author James Chen
 */
class AntiSpamHandlerTests {

    Path path = Path.of("./anti-spam-handler-tests.tmp");

    @Test
    void shouldRejectRequest() {
        AntiSpamHandler handler = createHandler(UnwantedWordHandleStrategy.REJECT_REQUEST);
        TurmsRequest.Builder builder = TurmsRequest
                .newBuilder()
                .setCreateGroupRequest(CreateGroupRequest.newBuilder()
                        .setName(new String(Store.UNWANTED_TERMS.get(0)))
                        .build());
        ClientRequest clientRequest = new ClientRequest(1L, DeviceType.DESKTOP, 1L, builder, null);
        Mono<ClientRequest> result = handler.transform(clientRequest);
        StepVerifier.create(result)
                .expectErrorSatisfies(t -> {
                    assertThat(t).isInstanceOf(TurmsBusinessException.class);
                    assertThat(((TurmsBusinessException) t).getCode()).isEqualTo(TurmsStatusCode.MESSAGE_IS_ILLEGAL);
                })
                .verify();
    }

    @Test
    void shouldMask() {
        String text = "Hello敏感词句.,asd#(&𤳵/()12%&123敏gan词321";
        AntiSpamHandler handler = createHandler(UnwantedWordHandleStrategy.MASK_TEXT);
        TurmsRequest.Builder builder = TurmsRequest
                .newBuilder()
                .setCreateGroupRequest(CreateGroupRequest.newBuilder()
                        .setName(text));
        ClientRequest clientRequest = new ClientRequest(1L, DeviceType.DESKTOP, 1L, builder, null);
        Mono<ClientRequest> result = handler.transform(clientRequest);
        StepVerifier.create(result)
                .expectNextMatches(request -> {
                    CreateGroupRequest createGroupRequest = request.turmsRequest().getCreateGroupRequest();
                    assertThat(createGroupRequest.getName()).isEqualTo("Hello****.,asd#(&𤳵/()12%&********321");
                    return true;
                })
                .verifyComplete();
    }

    @SneakyThrows
    AntiSpamHandler createHandler(UnwantedWordHandleStrategy handleStrategy) {
        try {
            List<String> terms = Store.UNWANTED_TERMS.stream().map(String::new).toList();
            String text = String.join("\n", terms);
            Files.writeString(path, text, StandardCharsets.UTF_8);
            AntiSpamProperties properties = new AntiSpamProperties()
                    .toBuilder()
                    .unwantedWordHandleStrategy(handleStrategy)
                    .build();
            properties.getDictParsing().setTextFilePath(path.toString());
            return new AntiSpamHandler(properties);
        } finally {
            Files.delete(path);
        }
    }

}
