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

import java.security.InvalidKeyException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author James Chen
 */
public final class MacUtil {

    private MacUtil() {
    }

    public static byte[] signMd5(byte[] data, SecretKeySpec key) {
        Mac mac = MacPool.getMd5();
        try {
            mac.init(key);
        } catch (InvalidKeyException e) {
            throw new IllegalArgumentException(e);
        }
        return mac.doFinal(data);
    }

}
