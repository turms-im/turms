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

package im.turms.server.common.infra.security;

import javax.crypto.Cipher;

import io.netty.util.concurrent.FastThreadLocal;

/**
 * @author James Chen
 */
public final class CypherPool {

    private CypherPool() {
    }

    private static final FastThreadLocal<Cipher> AES_GCM = new FastThreadLocal<>() {
        @Override
        protected Cipher initialValue() throws Exception {
            return Cipher.getInstance("AES/GCM/NoPadding");
        }
    };

    public static Cipher getAesGcm() {
        return AES_GCM.get();
    }

}