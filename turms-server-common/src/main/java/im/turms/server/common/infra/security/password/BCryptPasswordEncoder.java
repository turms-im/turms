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

package im.turms.server.common.infra.security.password;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import io.netty.util.concurrent.FastThreadLocal;
import org.bouncycastle.crypto.generators.BCrypt;

import im.turms.server.common.infra.collection.ArrayUtil;

/**
 * @author James Chen
 */
public class BCryptPasswordEncoder implements PasswordEncoder {

    public static final int SALT_SIZE_BYTES = 16;
    private static final int COST = 10;

    private static final FastThreadLocal<BCrypt> BCRYPT = new FastThreadLocal<>() {
        @Override
        protected BCrypt initialValue() {
            return new BCrypt();
        }
    };

    @Override
    public byte[] encode(byte[] rawPassword) {
        byte[] salt = new byte[SALT_SIZE_BYTES];
        ThreadLocalRandom.current()
                .nextBytes(salt);
        byte[] password = BCRYPT.get()
                .generate(rawPassword, salt, COST);
        return ArrayUtil.concat(salt, password);
    }

    @Override
    public boolean matches(byte[] rawPassword, byte[] saltedPasswordWithSalt) {
        byte[] saltedPassword = BCRYPT.get()
                .generate(rawPassword, saltedPasswordWithSalt, COST);
        return Arrays.equals(saltedPassword,
                0,
                saltedPassword.length,
                saltedPasswordWithSalt,
                SALT_SIZE_BYTES,
                saltedPasswordWithSalt.length);
    }

}
