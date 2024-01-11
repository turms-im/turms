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

package im.turms.gateway.infra.ldap.asn1;

/**
 * @author James Chen
 */
public final class Asn1IdConst {

    private Asn1IdConst() {
    }

    public static final int TAG_BOOLEAN = 1;
    public static final int TAG_INTEGER = 2;
    public static final int TAG_BIT_STRING = 3;
    public static final int TAG_OCTET_STRING = 4;
    public static final int TAG_NULL = 5;
    public static final int TAG_OBJECT_IDENTIFIER = 6;
    public static final int TAG_OBJECT_DESCRIPTOR = 7;
    public static final int TAG_EXTERNAL = 8;
    public static final int TAG_REAL = 9;
    public static final int TAG_ENUMERATED = 10;
    public static final int TAG_EMBEDDED_PDV = 11;
    public static final int TAG_UTF8_STRING = 12;
    public static final int TAG_RELATIVE_OID = 13;
    public static final int TAG_SEQUENCE = 16;
    public static final int TAG_SET = 17;
    public static final int TAG_NUMERIC_STRING = 18;
    public static final int TAG_PRINTABLE_STRING = 19;

    public static final int TAG_CLASS_UNIVERSAL = 0x00;
    public static final int TAG_CLASS_APPLICATION = 0x40;
    public static final int TAG_CLASS_CONTEXT = 0x80;
    public static final int TAG_CLASS_PRIVATE = 0xC0;

    public static final int FORM_PRIMITIVE = 0x00;
    public static final int FORM_CONSTRUCTED = 0x20;
}