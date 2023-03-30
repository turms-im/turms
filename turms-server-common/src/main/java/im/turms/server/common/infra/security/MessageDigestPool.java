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

import java.security.MessageDigest;

import io.netty.util.concurrent.FastThreadLocal;

/**
 * @author James Chen
 */
public class MessageDigestPool {

    private MessageDigestPool() {
    }

    /**
     * We don't need to call "remove()" because we assume all caller threads won't die until the
     * application is shutdown.
     */
    private static final FastThreadLocal<MessageDigest> SHA1 = new FastThreadLocal<>() {
        @Override
        protected MessageDigest initialValue() throws Exception {
            return MessageDigest.getInstance("SHA1");
        }
    };

    private static final FastThreadLocal<MessageDigest> SHA256 = new FastThreadLocal<>() {
        @Override
        protected MessageDigest initialValue() throws Exception {
            return MessageDigest.getInstance("SHA-256");
        }
    };

    public static MessageDigest getSha1() {
        MessageDigest digest = SHA1.get();
        digest.reset();
        return digest;
    }

    public static MessageDigest getSha256() {
        MessageDigest digest = SHA256.get();
        digest.reset();
        return digest;
    }

}
