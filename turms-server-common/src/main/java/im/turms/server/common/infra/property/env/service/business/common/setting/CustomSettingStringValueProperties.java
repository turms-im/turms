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

package im.turms.server.common.infra.property.env.service.business.common.setting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import jakarta.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import im.turms.server.common.infra.property.metadata.Description;
import im.turms.server.common.infra.property.metadata.GlobalProperty;
import im.turms.server.common.infra.property.metadata.MutableProperty;
import im.turms.server.common.infra.validation.ValidRegex;

/**
 * @author James Chen
 */
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
public class CustomSettingStringValueProperties extends CustomSettingEnumValueProperties<String> {

    @Description("The minimum allowed length")
    @GlobalProperty
    @MutableProperty
    @Min(0)
    private int minLength = 0;

    @Description("The maximum allowed length")
    @GlobalProperty
    @MutableProperty
    private int maxLength = 100;

    @Description("The regular expressions that the value must match")
    @GlobalProperty
    @MutableProperty
    @ValidRegex
    private List<String> regexes = Collections.emptyList();

    private transient List<Pattern> parsedRegexes;

    @JsonIgnore
    public List<Pattern> getParsedRegexes() {
        if (parsedRegexes != null) {
            return parsedRegexes;
        }
        if (regexes == null) {
            parsedRegexes = Collections.emptyList();
            return parsedRegexes;
        }
        List<Pattern> list = new ArrayList<>(regexes.size());
        for (String regex : regexes) {
            Pattern compile = Pattern.compile(regex);
            list.add(compile);
        }
        parsedRegexes = list;
        return parsedRegexes;
    }

}