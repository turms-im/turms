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

package im.turms.server.common.property.env.gateway.clientapi;

import com.fasterxml.jackson.annotation.JsonView;
import im.turms.server.common.property.metadata.annotation.Description;
import im.turms.server.common.property.metadata.annotation.GlobalProperty;
import im.turms.server.common.property.metadata.view.MutablePropertiesView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

/**
 * @author James Chen
 */
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
public class RateLimitingProperties {

    @Description("The minimum allowed interval between client requests. " +
            "If 0, there is no debounce. " +
            "It's better set the same value as client's for a better UX.")
    @GlobalProperty
    @JsonView(MutablePropertiesView.class)
    @Min(0)
    private int minClientRequestIntervalMillis;

    @Description("\"permits\" specifies the number of requests are allowed in every time period \"perSeconds\". " +
            "0 for no limit")
    @GlobalProperty
    @JsonView(MutablePropertiesView.class)
    @Min(0)
    private int permits = 60;

    @Description("The time period in which the number \"permits\" of requests are allowed. " +
            "0 for no limit")
    @GlobalProperty
    @JsonView(MutablePropertiesView.class)
    @Min(0)
    private int perSeconds = 30;
}