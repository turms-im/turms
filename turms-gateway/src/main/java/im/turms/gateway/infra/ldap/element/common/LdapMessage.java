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

package im.turms.gateway.infra.ldap.element.common;

import java.util.Collections;
import java.util.List;

import lombok.Getter;

import im.turms.gateway.infra.ldap.asn1.BerBuffer;
import im.turms.gateway.infra.ldap.element.LdapElement;
import im.turms.gateway.infra.ldap.element.LdapTagConst;
import im.turms.gateway.infra.ldap.element.common.control.Control;
import im.turms.gateway.infra.ldap.element.operation.ProtocolOperation;

/**
 * @author James Chen
 */
public class LdapMessage<T extends ProtocolOperation> implements LdapElement {

    @Getter
    protected final int messageId;

    @Getter
    protected final T protocolOperation;

    @Getter
    protected final List<Control> controls;

    public LdapMessage(int messageId, T protocolOperation) {
        this.messageId = messageId;
        this.protocolOperation = protocolOperation;
        this.controls = Collections.emptyList();
    }

    public LdapMessage(int messageId, T protocolOperation, List<Control> controls) {
        this.messageId = messageId;
        this.protocolOperation = protocolOperation;
        this.controls = controls;
    }

    public int estimateSize() {
        return 0;
    }

    public final void writeTo(BerBuffer buffer) {
        buffer.beginSequence()
                .writeInteger(messageId);

        protocolOperation.writeTo(buffer);

        if (!controls.isEmpty()) {
            buffer.beginSequence(LdapTagConst.CONTROLS);
            for (Control control : controls) {
                buffer.beginSequence();
                buffer.writeOctetString(control.getOid());
                if (control.isCriticality()) {
                    buffer.writeBoolean(true);
                }
                buffer.endSequence();
            }
            buffer.endSequence();
        }

        buffer.endSequence();
    }

}