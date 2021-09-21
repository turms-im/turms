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

package im.turms.server.common.security;

import im.turms.server.common.util.ArrayUtil;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author James Chen
 */
public class SaltedSha256PasswordEncoder implements PasswordEncoder {

    private static final int SALT_SIZE_BYTES = 8;

    private final ThreadLocal<MessageDigest> sha256Digest = ThreadLocal.withInitial(() -> {
        try {
            return MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    });

    public SaltedSha256PasswordEncoder() {
        // Check if SHA-256 exists
        sha256Digest.get();
    }

    @Override
    public byte[] encode(byte[] rawPassword) {
        byte[] salt = new byte[SALT_SIZE_BYTES];
        ThreadLocalRandom.current().nextBytes(salt);

        byte[] rawPasswordWithSalt = ArrayUtil.concat(salt, rawPassword);
        byte[] saltedPassword = sha256Digest.get().digest(rawPasswordWithSalt);

        return ArrayUtil.concat(salt, saltedPassword);
    }

    @Override
    public boolean matches(byte[] rawPassword, byte[] saltedPasswordWithSalt) {
        if (saltedPasswordWithSalt.length < SALT_SIZE_BYTES) {
            return false;
        }
        byte[] rawPasswordWithSalt = new byte[SALT_SIZE_BYTES + rawPassword.length];
        System.arraycopy(saltedPasswordWithSalt, 0, rawPasswordWithSalt, 0, SALT_SIZE_BYTES);
        System.arraycopy(rawPassword, 0, rawPasswordWithSalt, SALT_SIZE_BYTES, rawPassword.length);
        byte[] saltedPassword = sha256Digest.get().digest(rawPasswordWithSalt);
        return Arrays.equals(saltedPassword, 0, saltedPassword.length,
                saltedPasswordWithSalt, SALT_SIZE_BYTES, saltedPasswordWithSalt.length);
    }

}
