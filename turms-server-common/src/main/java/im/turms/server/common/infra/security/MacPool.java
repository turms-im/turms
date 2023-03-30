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

import java.security.NoSuchAlgorithmException;
import java.util.Map;
import javax.crypto.Mac;

import io.netty.util.concurrent.FastThreadLocal;

/**
 * @author James Chen
 */
public final class MacPool {

    private MacPool() {
    }

    private static final FastThreadLocal<Mac> HMAC_MD5 = new FastThreadLocal<>() {
        @Override
        protected Mac initialValue() throws Exception {
            return Mac.getInstance("HmacMD5");
        }
    };

    private static final FastThreadLocal<Mac> HMAC_SHA_256 = new FastThreadLocal<>() {
        @Override
        protected Mac initialValue() throws Exception {
            return Mac.getInstance("HmacSHA256");
        }
    };

    private static final FastThreadLocal<Mac> HMAC_SHA_384 = new FastThreadLocal<>() {
        @Override
        protected Mac initialValue() throws Exception {
            return Mac.getInstance("HmacSHA384");
        }
    };

    private static final FastThreadLocal<Mac> HMAC_SHA_512 = new FastThreadLocal<>() {
        @Override
        protected Mac initialValue() throws Exception {
            return Mac.getInstance("HmacSHA512");
        }
    };

    private static final Map<String, FastThreadLocal<Mac>> NAME_TO_ALGORITHM = Map
            .of("HmacSHA256", HMAC_SHA_256, "HmacSHA384", HMAC_SHA_384, "HmacSHA512", HMAC_SHA_512);

    public static void ensureAvailability(String algorithm) {
        try {
            Mac.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(
                    "Unavailable algorithm: \""
                            + algorithm
                            + "\"",
                    e);
        }
    }

    public static Mac get(String algorithm) {
        FastThreadLocal<Mac> threadLocal = NAME_TO_ALGORITHM.get(algorithm);
        if (threadLocal == null) {
            throw new IllegalArgumentException(
                    "Unknown algorithm: \""
                            + algorithm
                            + "\"");
        }
        Mac mac = threadLocal.get();
        mac.reset();
        return mac;
    }

    public static Mac getMd5() {
        Mac mac = HMAC_MD5.get();
        mac.reset();
        return mac;
    }

}