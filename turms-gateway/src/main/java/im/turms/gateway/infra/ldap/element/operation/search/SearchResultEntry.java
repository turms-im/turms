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

package im.turms.gateway.infra.ldap.element.operation.search;

import java.util.List;

import lombok.Data;

import im.turms.gateway.infra.ldap.element.common.Attribute;
import im.turms.gateway.infra.ldap.element.common.control.Control;

/**
 * @author James Chen
 * @implSpec <a href="https://datatracker.ietf.org/doc/html/rfc4511#section-4.5.2">4.5.2. Search
 *           Result</a>
 */
@Data
public class SearchResultEntry {

    private final String objectName;
    private final List<Attribute> attributes;
    private final List<Control> controls;

    public SearchResultEntry(
            String objectName,
            List<Attribute> attributes,
            List<Control> controls) {
        this.objectName = objectName;
        this.attributes = attributes;
        this.controls = controls;
    }
}