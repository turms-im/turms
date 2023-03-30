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

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import jakarta.annotation.Nullable;

import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Message;
import reactor.core.publisher.Mono;

import im.turms.plugin.antispam.ac.AhoCorasickCodec;
import im.turms.plugin.antispam.ac.AhoCorasickDoubleArrayTrie;
import im.turms.plugin.antispam.dictionary.DictionaryParser;
import im.turms.plugin.antispam.dictionary.Word;
import im.turms.plugin.antispam.property.AntiSpamProperties;
import im.turms.plugin.antispam.property.DictionaryParsingProperties;
import im.turms.plugin.antispam.property.TextType;
import im.turms.plugin.antispam.property.UnwantedWordHandleStrategy;
import im.turms.server.common.access.client.dto.request.TurmsRequest;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.infra.exception.ResponseException;
import im.turms.server.common.infra.plugin.TurmsExtension;
import im.turms.server.common.infra.test.VisibleForTesting;
import im.turms.service.access.servicerequest.dto.ClientRequest;
import im.turms.service.infra.plugin.extension.ClientRequestTransformer;

/**
 * @author James Chen
 */
public class AntiSpamHandler extends TurmsExtension implements ClientRequestTransformer {

    private boolean enabled;
    private UnwantedWordHandleStrategy unwantedWordHandleStrategy;
    private byte mask;
    private int maxNumberOfUnwantedWordsToReturn;

    @Nullable
    private SpamDetector spamDetector;
    private TextPreprocessor textPreprocessor;

    private Map<TurmsRequest.KindCase, TextTypeProperties> textTypeToProperties;

    public AntiSpamHandler() {
    }

    @VisibleForTesting
    public AntiSpamHandler(AntiSpamProperties properties) {
        enabled = properties.isEnabled();
        textPreprocessor = new TextPreprocessor(properties.getTextParsingStrategy());
        unwantedWordHandleStrategy = properties.getUnwantedWordHandleStrategy();
        mask = properties.getMask();
        maxNumberOfUnwantedWordsToReturn = properties.getMaxNumberOfUnwantedWordsToReturn();
        spamDetector = enabled
                ? new SpamDetector(
                        textPreprocessor,
                        buildTrie(properties.getDictParsing(), textPreprocessor))
                : null;
        textTypeToProperties = createTextTypeToPropertiesMap(properties.getTextTypes(),
                properties.getSilentIllegalTextTypes());
    }

    @Override
    protected void onStarted() {
        AntiSpamProperties properties = loadProperties(AntiSpamProperties.class);
        enabled = properties.isEnabled();
        textPreprocessor = new TextPreprocessor(properties.getTextParsingStrategy());
        unwantedWordHandleStrategy = properties.getUnwantedWordHandleStrategy();
        mask = properties.getMask();
        maxNumberOfUnwantedWordsToReturn = properties.getMaxNumberOfUnwantedWordsToReturn();
        spamDetector = enabled
                ? new SpamDetector(
                        textPreprocessor,
                        buildTrie(properties.getDictParsing(), textPreprocessor))
                : null;
        textTypeToProperties = createTextTypeToPropertiesMap(properties.getTextTypes(),
                properties.getSilentIllegalTextTypes());
    }

    private Map<TurmsRequest.KindCase, TextTypeProperties> createTextTypeToPropertiesMap(
            Set<TextType> textTypes,
            Set<TextType> silentIllegalTextTypes) {
        Map<TurmsRequest.KindCase, TextTypeProperties> map =
                new IdentityHashMap<>(textTypes.size());
        for (TextType textType : textTypes) {
            TextTypeProperties properties = map.get(textType.getType());
            boolean rejectSilently = silentIllegalTextTypes.contains(textType);
            List<RequestField> fields;
            if (properties == null) {
                fields = new ArrayList<>(4);
                map.put(textType.getType(),
                        new TextTypeProperties(textType.getRequestFieldDescriptor(), fields));
            } else {
                fields = properties.fields;
            }
            fields.add(new RequestField(
                    textType.getFieldDescriptor(),
                    textType.getSubfieldDescriptor(),
                    rejectSilently));
        }
        return map;
    }

    @Override
    public Mono<ClientRequest> transform(ClientRequest clientRequest) {
        if (!enabled) {
            return Mono.just(clientRequest);
        }
        TurmsRequest.Builder builder = clientRequest.turmsRequestBuilder();
        TurmsRequest.KindCase requestType = builder.getKindCase();
        TextTypeProperties properties = textTypeToProperties.get(requestType);
        FieldDescriptor requestFieldDescriptor = properties.requestFieldDescriptor;
        Message.Builder request = builder.getFieldBuilder(requestFieldDescriptor);
        for (RequestField field : properties.fields) {
            FieldDescriptor fieldDescriptor = field.fieldDescriptor;
            FieldDescriptor subfieldDescriptor = field.subfieldDescriptor;
            if (subfieldDescriptor == null) {
                String text = (String) request.getField(fieldDescriptor);
                if (text == null || text.isEmpty()) {
                    continue;
                }
                switch (unwantedWordHandleStrategy) {
                    case REJECT_REQUEST -> {
                        RuntimeException exception =
                                rejectRequestIfFindUnwantedWord(field.shouldRejectSilently, text);
                        if (exception != null) {
                            return Mono.error(exception);
                        }
                    }
                    case MASK_TEXT -> {
                        String maskedStr = spamDetector.mask(text, mask);
                        if (maskedStr != null) {
                            request.setField(fieldDescriptor, maskedStr);
                        }
                    }
                }
            } else {
                List<Message> fieldValues = (List<Message>) request.getField(fieldDescriptor);
                List<Message> newFieldValues = null;
                boolean hasNewFieldValues = false;
                int index = -1;
                for (Message fieldValue : fieldValues) {
                    index++;
                    String text = (String) fieldValue.getField(subfieldDescriptor);
                    if (text == null || text.isEmpty()) {
                        continue;
                    }
                    Message.Builder fieldValueBuilder = null;
                    switch (unwantedWordHandleStrategy) {
                        case REJECT_REQUEST -> {
                            RuntimeException exception =
                                    rejectRequestIfFindUnwantedWord(field.shouldRejectSilently,
                                            text);
                            if (exception != null) {
                                return Mono.error(exception);
                            }
                        }
                        case MASK_TEXT -> {
                            String maskedStr = spamDetector.mask(text, mask);
                            if (maskedStr != null) {
                                fieldValueBuilder = fieldValue.toBuilder()
                                        .setField(subfieldDescriptor, maskedStr);
                            }
                        }
                    }
                    if (hasNewFieldValues) {
                        if (fieldValueBuilder == null) {
                            newFieldValues.add(fieldValue);
                        } else {
                            newFieldValues.add(fieldValueBuilder.build());
                        }
                    } else {
                        if (fieldValueBuilder != null) {
                            hasNewFieldValues = true;
                            newFieldValues = new ArrayList<>(fieldValues.size());
                            for (int i = 0; i < index; i++) {
                                Message value = fieldValues.get(i);
                                newFieldValues.add(value);
                            }
                            newFieldValues.add(fieldValueBuilder.build());
                        }
                    }
                }
                if (hasNewFieldValues) {
                    request.setField(fieldDescriptor, newFieldValues);
                }
            }
        }
        return Mono.just(clientRequest);
    }

    private RuntimeException rejectRequestIfFindUnwantedWord(
            boolean shouldRejectSilently,
            String text) {
        if (shouldRejectSilently) {
            if (spamDetector.containsUnwantedWords(text)) {
                return ResponseException.get(ResponseStatusCode.OK);
            }
        } else if (maxNumberOfUnwantedWordsToReturn > 0) {
            String words = spamDetector.findUnwantedWords(text, maxNumberOfUnwantedWordsToReturn);
            if (words != null) {
                return ResponseException.get(ResponseStatusCode.MESSAGE_IS_ILLEGAL, words);
            }
        } else if (spamDetector.containsUnwantedWords(text)) {
            return ResponseException.get(ResponseStatusCode.MESSAGE_IS_ILLEGAL);
        }
        return null;
    }

    private AhoCorasickDoubleArrayTrie buildTrie(
            DictionaryParsingProperties dictParsing,
            TextPreprocessor textPreprocessor) {
        String path = dictParsing.getBinFilePath();
        if (path != null && !path.isBlank()) {
            return AhoCorasickCodec.deserialize(path);
        }
        String textFilePath = dictParsing.getTextFilePath();
        if (textFilePath == null || textFilePath.isBlank()) {
            throw new IllegalArgumentException(
                    "The binary file path and the text file path must not be both blank");
        }
        DictionaryParser parser = new DictionaryParser(textPreprocessor);
        List<Word> words = parser.parse(Path.of(textFilePath),
                dictParsing.getTextFileCharset(),
                dictParsing.isSkipInvalidCharacter(),
                dictParsing.getExtendedWord()
                        .isEnabled());
        return new AhoCorasickDoubleArrayTrie(words);
    }

    private record TextTypeProperties(
            FieldDescriptor requestFieldDescriptor,
            List<RequestField> fields
    ) {
    }

    private record RequestField(
            FieldDescriptor fieldDescriptor,
            FieldDescriptor subfieldDescriptor,
            boolean shouldRejectSilently
    ) {
    }

}