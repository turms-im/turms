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

package im.turms.plugin.antispam;

import im.turms.common.model.dto.request.TurmsRequest;
import im.turms.common.model.dto.request.message.CreateMessageRequest;
import im.turms.plugin.antispam.ac.AhoCorasickCodec;
import im.turms.plugin.antispam.ac.AhoCorasickDoubleArrayTrie;
import im.turms.plugin.antispam.parser.DictionaryParser;
import im.turms.plugin.antispam.property.AntiSpamProperties;
import im.turms.plugin.antispam.property.DictionaryParsingProperties;
import im.turms.plugin.antispam.property.UnwantedWordHandleStrategy;
import im.turms.plugin.antispam.property.TextParsingStrategy;
import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.exception.TurmsBusinessException;
import im.turms.server.common.plugin.TurmsExtension;
import im.turms.service.plugin.extension.ClientRequestTransformer;
import im.turms.service.workflow.access.servicerequest.dto.ClientRequest;
import reactor.core.publisher.Mono;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

/**
 * @author James Chen
 */
public class AntiSpamHandler extends TurmsExtension implements ClientRequestTransformer {

    private final boolean enabled;
    private final UnwantedWordHandleStrategy unwantedWordHandleStrategy;
    private final char mask;

    private final AhoCorasickDoubleArrayTrie trie;

    public AntiSpamHandler() {
        AntiSpamProperties properties = loadProperties(AntiSpamProperties.class);
        enabled = properties.isEnabled();
        unwantedWordHandleStrategy = properties.getUnwantedWordHandleStrategy();
        mask = properties.getMask();
        if (enabled) {
            trie = buildTrie(properties.getDictParsing(), properties.getTextParsingStrategy());
        } else {
            trie = null;
        }
    }

    @Override
    public Mono<ClientRequest> transform(ClientRequest clientRequest) {
        if (!enabled) {
            return Mono.just(clientRequest);
        }
        TurmsRequest request = clientRequest.turmsRequest();
        return switch (request.getKindCase()) {
            case CREATE_MESSAGE_REQUEST -> {
                CreateMessageRequest req = request.getCreateMessageRequest();
                if (!req.hasText()) {
                    yield Mono.just(clientRequest);
                }
                char[] text = req.getText().toCharArray();
                if (unwantedWordHandleStrategy == UnwantedWordHandleStrategy.MASK_TEXT) {
                    boolean containsUnwantedWord = trie.findOccurrences(text, (begin, end) -> Arrays.fill(text, begin, end, mask));
                    if (containsUnwantedWord) {
                        TurmsRequest turmsRequest = request.toBuilder()
                                .setCreateMessageRequest(req.toBuilder().setText(new String(text))).build();
                        yield Mono.just(new ClientRequest(clientRequest.userId(), clientRequest.deviceType(), clientRequest.requestId(), turmsRequest));
                    }
                } else if (trie.matches(text)) {
                    yield Mono.error(TurmsBusinessException.get(TurmsStatusCode.MESSAGE_IS_ILLEGAL));
                }
                yield Mono.just(clientRequest);
            }
            default -> Mono.just(clientRequest);
        };
    }

    private AhoCorasickDoubleArrayTrie buildTrie(DictionaryParsingProperties dictParsing,
                                                 TextParsingStrategy parsingStrategy) {
        String path = dictParsing.getBinFilePath();
        if (path != null && !path.isBlank()) {
            return AhoCorasickCodec.deserialize(path);
        }
        List<char[]> words = DictionaryParser.parse(Path.of(dictParsing.getTextFilePath()),
                dictParsing.getTextFileCharset(),
                dictParsing.isSkipInvalidCharacter(),
                parsingStrategy);
        return new AhoCorasickDoubleArrayTrie(words);
    }

}