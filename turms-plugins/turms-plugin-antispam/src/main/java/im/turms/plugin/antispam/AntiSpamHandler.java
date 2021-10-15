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

import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Message;
import im.turms.common.model.dto.request.TurmsRequest;
import im.turms.plugin.antispam.ac.AhoCorasickCodec;
import im.turms.plugin.antispam.ac.AhoCorasickDoubleArrayTrie;
import im.turms.plugin.antispam.parser.DictionaryParser;
import im.turms.plugin.antispam.property.AntiSpamProperties;
import im.turms.plugin.antispam.property.DictionaryParsingProperties;
import im.turms.plugin.antispam.property.TextType;
import im.turms.plugin.antispam.property.UnwantedWordHandleStrategy;
import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.exception.TurmsBusinessException;
import im.turms.server.common.plugin.TurmsExtension;
import im.turms.service.plugin.extension.ClientRequestTransformer;
import im.turms.service.workflow.access.servicerequest.dto.ClientRequest;
import reactor.core.publisher.Mono;

import java.nio.file.Path;
import java.util.IdentityHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author James Chen
 */
public class AntiSpamHandler extends TurmsExtension implements ClientRequestTransformer {

    private final boolean enabled;
    private final UnwantedWordHandleStrategy unwantedWordHandleStrategy;
    private final char mask;

    private final SpamDetector spamDetector;
    private final TextPreprocessor textPreprocessor;

    private final Map<TurmsRequest.KindCase, TextTypeProperties> textTypeMap = new IdentityHashMap<>();

    public AntiSpamHandler() {
        AntiSpamProperties properties = loadProperties(AntiSpamProperties.class);
        enabled = properties.isEnabled();
        textPreprocessor = new TextPreprocessor(properties.getTextParsingStrategy());
        unwantedWordHandleStrategy = properties.getUnwantedWordHandleStrategy();
        mask = properties.getMask();
        spamDetector = enabled
                ? new SpamDetector(textPreprocessor, buildTrie(properties.getDictParsing(), textPreprocessor))
                : null;
        initTextTypeMap(textTypeMap, properties.getTextTypes());
    }

    public AntiSpamHandler(AntiSpamProperties properties) {
        enabled = properties.isEnabled();
        textPreprocessor = new TextPreprocessor(properties.getTextParsingStrategy());
        unwantedWordHandleStrategy = properties.getUnwantedWordHandleStrategy();
        mask = properties.getMask();
        spamDetector = enabled
                ? new SpamDetector(textPreprocessor, buildTrie(properties.getDictParsing(), textPreprocessor))
                : null;
        initTextTypeMap(textTypeMap, properties.getTextTypes());
    }

    private void initTextTypeMap(Map<TurmsRequest.KindCase, TextTypeProperties> map, Set<TextType> textTypes) {
        for (TextType textType : textTypes) {
            TextTypeProperties properties = map.get(textType.getType());
            if (properties == null) {
                List<FieldDescriptor> list = new LinkedList<>();
                list.add(textType.getFieldDescriptor());
                map.put(textType.getType(), new TextTypeProperties(textType.getRequestFieldDescriptor(), list));
            } else {
                properties.fieldDescriptors.add(textType.getFieldDescriptor());
            }
        }
    }

    @Override
    public Mono<ClientRequest> transform(ClientRequest clientRequest) {
        if (!enabled) {
            return Mono.just(clientRequest);
        }
        TurmsRequest.Builder builder = clientRequest.turmsRequestBuilder();
        TextTypeProperties properties = textTypeMap.get(builder.getKindCase());
        FieldDescriptor requestFieldDescriptor = properties.requestFieldDescriptor;
        GeneratedMessageV3 req = (GeneratedMessageV3) builder.getField(requestFieldDescriptor);
        for (FieldDescriptor fieldDescriptor : properties.fieldDescriptors) {
            String text = (String) req.getField(fieldDescriptor);
            if (text == null || text.isEmpty()) {
                continue;
            }
            char[] chars = text.toCharArray();
            if (unwantedWordHandleStrategy == UnwantedWordHandleStrategy.MASK_TEXT) {
                if (spamDetector.mask(chars, mask)) {
                    Message newReq = req.toBuilder()
                            .setField(fieldDescriptor, new String(chars)).build();
                    builder = builder
                            .setField(requestFieldDescriptor, newReq);
                }
            } else if (spamDetector.containsUnwantedWords(chars)) {
                return Mono.error(TurmsBusinessException.get(TurmsStatusCode.MESSAGE_IS_ILLEGAL));
            }
        }
        return Mono.just(clientRequest);
    }

    private AhoCorasickDoubleArrayTrie buildTrie(DictionaryParsingProperties dictParsing,
                                                 TextPreprocessor textPreprocessor) {
        String path = dictParsing.getBinFilePath();
        if (path != null && !path.isBlank()) {
            return AhoCorasickCodec.deserialize(path);
        }
        String textFilePath = dictParsing.getTextFilePath();
        if (textFilePath == null || textFilePath.isBlank()) {
            throw new RuntimeException("The binary file path and the text file path cannot be both blank");
        }
        DictionaryParser parser = new DictionaryParser(textPreprocessor);
        List<char[]> words = parser.parse(Path.of(textFilePath),
                dictParsing.getTextFileCharset(),
                dictParsing.isSkipInvalidCharacter());
        return new AhoCorasickDoubleArrayTrie(words);
    }

    private record TextTypeProperties(
            FieldDescriptor requestFieldDescriptor,
            List<FieldDescriptor> fieldDescriptors
    ) {
    }

}