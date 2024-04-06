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

package im.turms.server.common.infra.property.env.service.env.elasticsearch;

import jakarta.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import im.turms.server.common.infra.property.metadata.Description;

/**
 * @author James Chen
 */
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
public class ElasticsearchIndexProperties {

    @Description("The index will be created for the specified language. "
            + "If the language is NONE, this index will be used as the default index for all languages that don't have a specified index for them")
    private LanguageCode code = LanguageCode.NONE;

    @Description("The number of shards. -1 means use the default value")
    @Min(-1)
    private int numberOfShards = -1;

    @Description("The number of replicas. -1 means use the default value")
    @Min(-1)
    private int numberOfReplicas = -1;

    @NestedConfigurationProperty
    private ElasticsearchIndexPropertiesProperties properties =
            new ElasticsearchIndexPropertiesProperties();

}