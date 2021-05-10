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

package im.turms.server.common.access.http.dto.response;

import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author James Chen
 */
@Data
public class MetricDTO {

    private final String name;

    private final String description;

    private final String baseUnit;

    private final List<MeasurementDTO> measurements;

    private final Map<String, Set<String>> availableTags;

    @Data
    public static class MeasurementDTO {
        private final List<String> tags;
        private final Map<String, Double> measurements;
    }

}