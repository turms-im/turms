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
    /**
     * This isn't the standard implementation of BCrypt, and is used for Turms only.
     */
    public static final byte VERSION_BYTES = 1;
    private static final int COST = 10;
    private static final byte NULL_TERMINATOR = (byte) 0;

    private static final FastThreadLocal<BCrypt> BCRYPT = new FastThreadLocal<>() {
        @Override
        protected BCrypt initialValue() {
            return new BCrypt();
        }
    };

    /**
     * @implNote The implementation doesn't validate the null terminator in the raw password to
     *           avoid double checks, and it is the responsibility of the caller to ensure that no
     *           null terminator is in the raw password.
     */
    @Override
    public byte[] encode(byte[] rawPassword) {
        int length = rawPassword.length;
        if (length > BCrypt.MAX_PASSWORD_BYTES) {
            throw new IllegalArgumentException(
                    "The password length must be less than "
                            + BCrypt.MAX_PASSWORD_BYTES);
        }
        if (length != BCrypt.MAX_PASSWORD_BYTES) {
            rawPassword = ArrayUtil.concat(rawPassword, NULL_TERMINATOR);
        }
        byte[] salt = new byte[SALT_SIZE_BYTES];
        ThreadLocalRandom.current()
                .nextBytes(salt);
        byte[] encodedPassword = BCRYPT.get()
                .deriveRawKey(COST, salt, rawPassword);

        int encodedPasswordLength = encodedPassword.length;
        int resultLength = VERSION_BYTES + SALT_SIZE_BYTES + encodedPasswordLength;
        byte[] result = new byte[resultLength];
        System.arraycopy(salt, 0, result, 0, SALT_SIZE_BYTES);
        System.arraycopy(encodedPassword, 0, result, SALT_SIZE_BYTES, encodedPasswordLength);
        // The last byte indicates the version and cost.
        result[resultLength - 1] = (byte) COST;
        return result;
    }

    @Override
    public boolean matches(byte[] rawPassword, byte[] saltedPasswordWithSalt) {
        int length = rawPassword.length;
        if (length > BCrypt.MAX_PASSWORD_BYTES) {
            return false;
        }
        for (byte b : rawPassword) {
            if (b == NULL_TERMINATOR) {
                return false;
            }
        }
        // If the length is 40, it means the password of the first version that has no null
        // terminator.
        int saltedPasswordWithSaltLength = saltedPasswordWithSalt.length;
        if (saltedPasswordWithSaltLength == 40) {
            byte[] saltedPassword = BCRYPT.get()
                    .deriveRawKey(10, saltedPasswordWithSalt, rawPassword);
            return Arrays.equals(saltedPassword,
                    0,
                    saltedPassword.length,
                    saltedPasswordWithSalt,
                    SALT_SIZE_BYTES,
                    saltedPasswordWithSaltLength);
        }
        int cost = saltedPasswordWithSalt[saltedPasswordWithSaltLength - 1] & 0xFF;
        if (length != BCrypt.MAX_PASSWORD_BYTES) {
            rawPassword = ArrayUtil.concat(rawPassword, NULL_TERMINATOR);
        }
        byte[] saltedPassword = BCRYPT.get()
                .deriveRawKey(cost, saltedPasswordWithSalt, rawPassword);
        return Arrays.equals(saltedPassword,
                0,
                saltedPassword.length,
                saltedPasswordWithSalt,
                SALT_SIZE_BYTES,
                saltedPasswordWithSaltLength - 1);
    }

}