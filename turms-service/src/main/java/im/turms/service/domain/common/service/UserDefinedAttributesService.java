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

package im.turms.service.domain.common.service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import reactor.core.publisher.Mono;

import im.turms.server.common.access.client.dto.model.common.Value;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.exception.ResponseException;
import im.turms.server.common.infra.lang.StringUtil;
import im.turms.server.common.infra.property.env.service.business.common.userdefinedattribute.UserDefinedAttributeProperties;
import im.turms.server.common.infra.property.env.service.business.common.userdefinedattribute.UserDefinedAttributesProperties;
import im.turms.server.common.infra.reactor.PublisherPool;
import im.turms.server.common.infra.validation.Validator;

/**
 * @author James Chen
 */
public abstract class UserDefinedAttributesService extends CustomValueService {

    private static final String EXCEPTION_MESSAGE_PREFIX_FOUND_DUPLICATE_ATTRIBUTE =
            "Found a duplicate attribute: ";
    private static final String EXCEPTION_MESSAGE_PREFIX_UNKNOWN_ATTRIBUTE = "Unknown attribute: ";
    private static final String EXCEPTION_MESSAGE_PREFIX_UNKNOWN_ATTRIBUTES =
            "Unknown attributes: ";

    public Set<String> knownAttributes = Collections.emptySet();
    protected Map<String, UserDefinedAttributeProperties> sourceNameToAttributeProperties =
            Collections.emptyMap();
    protected Set<String> immutableAttributes = Collections.emptySet();
    public boolean ignoreUnknownAttributesOnUpsert;

    public UserDefinedAttributesService() {
        super("The value of the attribute \"",
                "The string value of the attribute \"",
                "The string value length of the attribute \"",
                "The array value of the attribute \"");
    }

    protected abstract Mono<List<String>> findUserDefinedAttributes(
            Collection<String> immutableAttributesForUpsert);

    public void updateGlobalProperties(UserDefinedAttributesProperties properties) {
        List<UserDefinedAttributeProperties> attributePropertiesList =
                properties.getAllowedAttributes();
        int size = attributePropertiesList.size();
        if (0 == size) {
            return;
        }
        Map<String, UserDefinedAttributeProperties> newSourceNameToAttributeProperties =
                CollectionUtil.newMapWithExpectedSize(size);
        Set<String> newImmutableAttributes = null;
        for (int i = 0; i < size; i++) {
            UserDefinedAttributeProperties attributeProperties = attributePropertiesList.get(i);
            String sourceName = attributeProperties.getSourceName();
            String storedName = attributeProperties.getStoredName();
            if (StringUtil.isEmpty(storedName)) {
                if (newSourceNameToAttributeProperties.put(sourceName,
                        attributeProperties.toBuilder()
                                .storedName(sourceName)
                                .build()) != null) {
                    throw new IllegalArgumentException(
                            EXCEPTION_MESSAGE_PREFIX_FOUND_DUPLICATE_ATTRIBUTE + sourceName);
                }
            } else if (newSourceNameToAttributeProperties.put(storedName,
                    attributeProperties) != null) {
                throw new IllegalArgumentException(
                        EXCEPTION_MESSAGE_PREFIX_FOUND_DUPLICATE_ATTRIBUTE + storedName);
            }
            if (attributeProperties.isImmutable()) {
                if (newImmutableAttributes == null) {
                    newImmutableAttributes = new UnifiedSet<>(4);
                }
                newImmutableAttributes.add(sourceName);
            }
        }

        this.sourceNameToAttributeProperties = newSourceNameToAttributeProperties;
        this.knownAttributes = Set.copyOf(sourceNameToAttributeProperties.keySet());
        this.immutableAttributes = newImmutableAttributes == null
                ? Collections.emptySet()
                : newImmutableAttributes;
        this.ignoreUnknownAttributesOnUpsert = properties.isIgnoreUnknownAttributesOnUpsert();
    }

    protected Map<String, Object> parseAttributes(
            boolean ignoreUnknownAttributes,
            Map<String, Value> inputAttributes) {
        if (inputAttributes.isEmpty()) {
            return Collections.emptyMap();
        }
        Map<String, UserDefinedAttributeProperties> localSourceNameToAttributeProperties =
                sourceNameToAttributeProperties;
        if (localSourceNameToAttributeProperties.isEmpty()) {
            if (ignoreUnknownAttributes) {
                return Collections.emptyMap();
            }
            throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    EXCEPTION_MESSAGE_PREFIX_UNKNOWN_ATTRIBUTES
                            + new TreeSet<>(inputAttributes.keySet()));
        }
        int inputAttributeCount = inputAttributes.size();
        Map<String, Object> outputAttributes =
                CollectionUtil.newMapWithExpectedSize(inputAttributeCount);
        if (inputAttributeCount <= localSourceNameToAttributeProperties.size()) {
            if (ignoreUnknownAttributes) {
                for (Map.Entry<String, Value> entry : inputAttributes.entrySet()) {
                    String sourceName = entry.getKey();
                    UserDefinedAttributeProperties attributeProperties =
                            localSourceNameToAttributeProperties.get(sourceName);
                    if (attributeProperties == null) {
                        continue;
                    }
                    outputAttributes.put(attributeProperties.getStoredName(),
                            parseValue(attributeProperties.getValue(),
                                    sourceName,
                                    entry.getValue()));
                }
            } else {
                for (Map.Entry<String, Value> entry : inputAttributes.entrySet()) {
                    String sourceName = entry.getKey();
                    UserDefinedAttributeProperties attributeProperties =
                            localSourceNameToAttributeProperties.get(sourceName);
                    if (attributeProperties == null) {
                        throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                                EXCEPTION_MESSAGE_PREFIX_UNKNOWN_ATTRIBUTE + sourceName);
                    }
                    outputAttributes.put(attributeProperties.getStoredName(),
                            parseValue(attributeProperties.getValue(),
                                    sourceName,
                                    entry.getValue()));
                }
            }
        } else {
            if (!ignoreUnknownAttributes) {
                Set<String> unknownAttributeNames = new TreeSet<>();
                for (String sourceName : inputAttributes.keySet()) {
                    if (!localSourceNameToAttributeProperties.containsKey(sourceName)) {
                        unknownAttributeNames.add(sourceName);
                    }
                }
                throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                        EXCEPTION_MESSAGE_PREFIX_UNKNOWN_ATTRIBUTES + unknownAttributeNames);
            }
            for (Map.Entry<String, UserDefinedAttributeProperties> sourceNameAndAttributeProperties : localSourceNameToAttributeProperties
                    .entrySet()) {
                String sourceName = sourceNameAndAttributeProperties.getKey();
                Value value = inputAttributes.get(sourceName);
                if (value == null) {
                    continue;
                }
                UserDefinedAttributeProperties attributeProperties =
                        sourceNameAndAttributeProperties.getValue();
                outputAttributes.put(attributeProperties.getStoredName(),
                        parseValue(attributeProperties.getValue(), sourceName, value));
            }
        }
        return outputAttributes;
    }

    public Mono<Map<String, Object>> parseAttributesForUpsert(
            Map<String, Value> userDefinedAttributes) {
        try {
            Validator.notNull(userDefinedAttributes, "userDefinedAttributes");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (userDefinedAttributes.isEmpty()) {
            return PublisherPool.emptyMap();
        }
        Set<String> immutableAttributesForUpsert = null;
        if (!immutableAttributes.isEmpty()) {
            for (String attributeName : userDefinedAttributes.keySet()) {
                if (immutableAttributes.contains(attributeName)) {
                    if (immutableAttributesForUpsert == null) {
                        immutableAttributesForUpsert = new UnifiedSet<>(4);
                    }
                    immutableAttributesForUpsert.add(attributeName);
                }
            }
        }
        if (immutableAttributesForUpsert == null) {
            Map<String, Object> parsedAttributes;
            try {
                parsedAttributes =
                        parseAttributes(ignoreUnknownAttributesOnUpsert, userDefinedAttributes);
            } catch (Exception e) {
                return Mono.error(e);
            }
            return Mono.just(parsedAttributes);
        }
        Set<String> finalImmutableAttributesForUpsert = immutableAttributesForUpsert;
        return findUserDefinedAttributes(immutableAttributesForUpsert).map(existingAttributes -> {
            if (existingAttributes.isEmpty()) {
                return parseAttributes(ignoreUnknownAttributesOnUpsert, userDefinedAttributes);
            }
            finalImmutableAttributesForUpsert
                    .removeIf(settingName -> !existingAttributes.contains(settingName));
            if (finalImmutableAttributesForUpsert.isEmpty()) {
                return parseAttributes(ignoreUnknownAttributesOnUpsert, userDefinedAttributes);
            }
            List<String> sortedConflictedAttributes =
                    CollectionUtil.toMutableList(finalImmutableAttributesForUpsert);
            sortedConflictedAttributes.sort(null);
            throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    "Cannot update existing immutable attributes: "
                            + sortedConflictedAttributes);
        });
    }
}