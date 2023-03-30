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

package im.turms.plugin.push.core;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import jakarta.annotation.Nullable;

import freemarker.template.Template;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Mono;

import im.turms.plugin.push.core.template.TemplateEngine;
import im.turms.plugin.push.property.TemplateMappingProperties;
import im.turms.plugin.push.property.TemplateProperties;
import im.turms.server.common.infra.collection.FastEnumMap;
import im.turms.server.common.infra.lang.StringBuilderPool;
import im.turms.server.common.infra.lang.StringBuilderWriter;
import im.turms.server.common.infra.lang.StringUtil;

/**
 * @author James Chen
 */
public abstract class PushNotificationSender {

    private static final TemplateEngine TEMPLATE_ENGINE = new TemplateEngine();

    private final Map<String, Map<PushNotificationType, TemplateGroup>> localeToTypeToTemplate;

    protected PushNotificationSender(
            Map<String, TemplateProperties> nameToTemplate,
            Map<String, Map<PushNotificationType, TemplateMappingProperties>> localeToTypeToProperties) {
        Map<String, Map<PushNotificationType, TemplateGroup>> map =
                new HashMap<>(localeToTypeToProperties.size() << 2);
        for (Map.Entry<String, Map<PushNotificationType, TemplateMappingProperties>> localeToTypeToPropertiesEntry : localeToTypeToProperties
                .entrySet()) {
            for (Map.Entry<PushNotificationType, TemplateMappingProperties> typeToPropertiesEntry : localeToTypeToPropertiesEntry
                    .getValue()
                    .entrySet()) {
                TemplateMappingProperties properties = typeToPropertiesEntry.getValue();
                addTemplate(map,
                        properties.getTitleTemplateName(),
                        nameToTemplate,
                        localeToTypeToPropertiesEntry,
                        typeToPropertiesEntry,
                        (templateGroup, template) -> templateGroup.titleTemplate = template);
                addTemplate(map,
                        properties.getBodyTemplateName(),
                        nameToTemplate,
                        localeToTypeToPropertiesEntry,
                        typeToPropertiesEntry,
                        (templateGroup, template) -> templateGroup.bodyTemplate = template);
            }
        }
        localeToTypeToTemplate = Map.copyOf(map);
    }

    /**
     * @return {@link SendPushNotificationException} if the sender failed to send notifications no
     *         matter what reason
     */
    public abstract Mono<Void> sendNotification(
            PushNotification notification,
            String locale,
            Supplier<Object> dataModelSupplier);

    public abstract Mono<Void> close();

    private void addTemplate(
            Map<String, Map<PushNotificationType, TemplateGroup>> output,
            String templateName,
            Map<String, TemplateProperties> nameToTemplate,
            Map.Entry<String, Map<PushNotificationType, TemplateMappingProperties>> localeToTypeToPropertiesEntry,
            Map.Entry<PushNotificationType, TemplateMappingProperties> typeToPropertiesEntry,
            BiConsumer<TemplateGroup, Template> consumer) {
        if (StringUtil.isBlank(templateName)) {
            return;
        }
        TemplateProperties templateProperties = nameToTemplate.get(templateName);
        if (templateProperties == null) {
            throw new IllegalArgumentException(
                    "Could not find a template with the name: \""
                            + templateName
                            + "\"");
        }
        String template = templateProperties.getTemplate();
        if (StringUtil.isBlank(template)) {
            return;
        }
        Template t = TEMPLATE_ENGINE.buildTemplate(template);
        output.compute(localeToTypeToPropertiesEntry.getKey(), (key, typeToTemplateGroup) -> {
            if (typeToTemplateGroup == null) {
                typeToTemplateGroup = new FastEnumMap<>(PushNotificationType.class);
            }
            typeToTemplateGroup.compute(typeToPropertiesEntry.getKey(), (type, templateGroup) -> {
                if (templateGroup == null) {
                    templateGroup = new TemplateGroup();
                }
                consumer.accept(templateGroup, t);
                return templateGroup;
            });
            return typeToTemplateGroup;
        });
    }

    public Message buildMessage(
            String locale,
            PushNotificationType type,
            @Nullable String title,
            @Nullable String body,
            Supplier<Object> dataModelSupplier) {
        Map<PushNotificationType, TemplateGroup> typeToTemplate =
                localeToTypeToTemplate.get(locale);
        if (typeToTemplate == null) {
            return new Message(title, body);
        }
        TemplateGroup group = typeToTemplate.get(type);
        if (group == null) {
            return new Message(title, body);
        }
        Object dataModel = null;
        StringBuilderWriter writer = null;
        Template template = group.titleTemplate;
        String titleOutput;
        if (template == null) {
            titleOutput = title;
        } else {
            dataModel = dataModelSupplier.get();
            writer = StringBuilderPool.getWriter();
            try {
                template.process(dataModel, writer);
            } catch (Exception e) {
                throw new RuntimeException("Failed to generate the title output", e);
            } finally {
                writer.close();
            }
            titleOutput = writer.toString();
        }
        template = group.bodyTemplate;
        String bodyOutput;
        if (template == null) {
            bodyOutput = body;
        } else {
            if (writer == null) {
                dataModel = dataModelSupplier.get();
                writer = StringBuilderPool.getWriter();
            }
            try {
                template.process(dataModel, writer);
            } catch (Exception e) {
                throw new RuntimeException("Failed to generate the body output", e);
            } finally {
                writer.close();
            }
            bodyOutput = writer.toString();
        }
        return new Message(titleOutput, bodyOutput);
    }

    @NoArgsConstructor
    private static class TemplateGroup {
        Template titleTemplate;
        Template bodyTemplate;
    }

}