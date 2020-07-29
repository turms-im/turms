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

package im.turms.server.common.property.env.service.env;

import im.turms.server.common.property.metadata.annotation.Description;
import lombok.Data;

import javax.validation.constraints.Min;

/**
 * @author James Chen
 */
@Data
public class MockProperties {

    @Description("Whether to mock data. Note that mocking only works in non-production environments")
    private boolean enabled = false;

    @Description("The user number to mock")
    @Min(0)
    private int userNumber = 1000;

    @Description("Whether to clear all collections before mocking at startup")
    private boolean clearAllCollectionsBeforeMocking = false;

}