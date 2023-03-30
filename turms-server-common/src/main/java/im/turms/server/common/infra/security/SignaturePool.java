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
import java.security.Signature;
import java.util.Map;

import io.netty.util.concurrent.FastThreadLocal;

/**
 * @author James Chen
 */
public final class SignaturePool {

    private SignaturePool() {
    }

    private static final FastThreadLocal<Signature> SHA_256_WITH_RSA = new FastThreadLocal<>() {
        @Override
        protected Signature initialValue() throws Exception {
            return Signature.getInstance("SHA256withRSA");
        }
    };

    private static final FastThreadLocal<Signature> SHA_384_WITH_RSA = new FastThreadLocal<>() {
        @Override
        protected Signature initialValue() throws Exception {
            return Signature.getInstance("SHA384withRSA");
        }
    };

    private static final FastThreadLocal<Signature> SHA_512_WITH_RSA = new FastThreadLocal<>() {
        @Override
        protected Signature initialValue() throws Exception {
            return Signature.getInstance("SHA512withRSA");
        }
    };

    private static final FastThreadLocal<Signature> SHA_256_WITH_ECDSA = new FastThreadLocal<>() {
        @Override
        protected Signature initialValue() throws Exception {
            return Signature.getInstance("SHA256withECDSA");
        }
    };

    private static final FastThreadLocal<Signature> SHA_384_WITH_ECDSA = new FastThreadLocal<>() {
        @Override
        protected Signature initialValue() throws Exception {
            return Signature.getInstance("SHA384withECDSA");
        }
    };

    private static final FastThreadLocal<Signature> SHA_512_WITH_ECDSA = new FastThreadLocal<>() {
        @Override
        protected Signature initialValue() throws Exception {
            return Signature.getInstance("SHA512withECDSA");
        }
    };

    private static final Map<String, FastThreadLocal<Signature>> NAME_TO_ALGORITHM =
            Map.of("SHA256withRSA",
                    SHA_256_WITH_RSA,
                    "SHA384withRSA",
                    SHA_384_WITH_RSA,
                    "SHA512withRSA",
                    SHA_512_WITH_RSA,
                    "SHA256withECDSA",
                    SHA_256_WITH_ECDSA,
                    "SHA384withECDSA",
                    SHA_384_WITH_ECDSA,
                    "SHA512withECDSA",
                    SHA_512_WITH_ECDSA);

    public static void ensureAvailability(String algorithm) {
        try {
            Signature.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(
                    "Unavailable algorithm: \""
                            + algorithm
                            + "\"",
                    e);
        }
    }

    public static Signature get(String algorithm) {
        FastThreadLocal<Signature> threadLocal = NAME_TO_ALGORITHM.get(algorithm);
        if (threadLocal == null) {
            throw new IllegalArgumentException(
                    "Unknown algorithm: \""
                            + algorithm
                            + "\"");
        }
        return threadLocal.get();
    }

}