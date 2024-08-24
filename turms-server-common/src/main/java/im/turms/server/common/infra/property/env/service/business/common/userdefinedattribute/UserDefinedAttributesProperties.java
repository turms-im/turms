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

package im.turms.server.common.infra.property.env.service.business.common.userdefinedattribute;

import java.util.Collections;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import im.turms.server.common.infra.property.metadata.Description;
import im.turms.server.common.infra.property.metadata.GlobalProperty;
import im.turms.server.common.infra.property.metadata.MutableProperty;

/**
 * @author James Chen
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public abstract class UserDefinedAttributesProperties {

    @Description("The list of allowed attributes")
    @GlobalProperty
    @MutableProperty
    protected List<UserDefinedAttributeProperties> allowedAttributes = Collections.emptyList();

    @Description("Whether to ignore unknown attributes on upsert. If false, the server will throw if the request "
            + "specifies an unknown attribute. If true, the server will ignore the unknown attributes, "
            + "and continue to process the request")
    @GlobalProperty
    @MutableProperty
    protected boolean ignoreUnknownAttributesOnUpsert;

//    @Description("Whether to ignore unknown attributes on delete. If false, the server will throw if the request "
//            + "specifies an unknown attribute. If true, the server will ignore the unknown attributes, "
//            + "and continue to process the request")
//    @GlobalProperty
//    @MutableProperty
//    private boolean ignoreUnknownAttributesOnDelete;
}