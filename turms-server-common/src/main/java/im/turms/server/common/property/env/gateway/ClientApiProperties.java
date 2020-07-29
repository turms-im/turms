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

package im.turms.server.common.property.env.gateway;


import com.fasterxml.jackson.annotation.JsonView;
import im.turms.server.common.property.metadata.annotation.Description;
import im.turms.server.common.property.metadata.view.MutablePropertiesView;
import lombok.Data;

import javax.validation.constraints.Min;

/**
 * @author James Chen
 */
@Data
public class ClientApiProperties {

    /**
     * If 0, there is no debounce.
     * Better set the same value as client's for a better UX.
     */
    @JsonView(MutablePropertiesView.class)
    @Description("The minimum allowed interval between client requests")
    @Min(0)
    private int minClientRequestsIntervalMillis = 0;

}