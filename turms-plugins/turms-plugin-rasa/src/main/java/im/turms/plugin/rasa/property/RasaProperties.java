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

package im.turms.plugin.rasa.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author James Chen
 */
@ConfigurationProperties("turms-plugin.rasa")
@Data
public class RasaProperties {
    private boolean enabled = true;

    private String responseDelimiter = "\n";

    private InstanceFindStrategy instanceFindStrategy = InstanceFindStrategy.PROPERTY;

    private List<InstanceProperties> instances = List.of(
            new InstanceProperties()
    );

    @Data
    public static class InstanceProperties {
        private Long chatbotUserId = 0L;

        private String url = "http://localhost:5005/webhooks/rest/webhook";
    }
}