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

package im.turms.server.common.manager;

import im.turms.server.common.property.TurmsProperties;
import im.turms.server.common.property.TurmsPropertiesManager;
import im.turms.server.common.property.constant.PasswordEncodingAlgorithm;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;

/**
 * @author James Chen
 */
@Component
public class PasswordManager {

    private static final BCryptPasswordEncoder BCRYPT_PASSWORD_ENCODER = new BCryptPasswordEncoder(10);
    /**
     * Ignore @Deprecated because it's definitely secure enough to encode the password of users in most IM scenarios
     */
    @SuppressWarnings("deprecation")
    private static final MessageDigestPasswordEncoder MESSAGE_DIGEST_PASSWORD_ENCODER = new MessageDigestPasswordEncoder("SHA-256");

    private final PasswordEncodingAlgorithm adminPasswordEncodingAlgorithm;
    private final PasswordEncodingAlgorithm userPasswordEncodingAlgorithm;

    public PasswordManager(TurmsPropertiesManager turmsPropertiesManager) {
        TurmsProperties turmsProperties = turmsPropertiesManager.getLocalProperties();
        adminPasswordEncodingAlgorithm = turmsProperties.getSecurity().getAdminPasswordEncodingAlgorithm();
        userPasswordEncodingAlgorithm = turmsProperties.getSecurity().getUserPasswordEncodingAlgorithm();
    }

    public String encodePassword(PasswordEncodingAlgorithm strategy, String rawPassword) {
        switch (strategy) {
            case BCRYPT:
                return BCRYPT_PASSWORD_ENCODER.encode(rawPassword);
            case SALTED_SHA256:
                return MESSAGE_DIGEST_PASSWORD_ENCODER.encode(rawPassword);
            case NOOP:
            default:
                return rawPassword;
        }
    }

    public String encodeAdminPassword(@NotNull String rawPassword) {
        Assert.notNull(rawPassword, "rawPassword must not be null");
        return encodePassword(adminPasswordEncodingAlgorithm, rawPassword);
    }

    public String encodeUserPassword(@NotNull String rawPassword) {
        Assert.notNull(rawPassword, "rawPassword must not be null");
        return encodePassword(userPasswordEncodingAlgorithm, rawPassword);
    }

    public boolean matchesAdminPassword(@NotNull String rawPassword, @NotNull String encodedPassword) {
        Assert.notNull(rawPassword, "rawPassword must not be null");
        Assert.notNull(rawPassword, "encodedPassword must not be null");
        return matchesPassword(adminPasswordEncodingAlgorithm, rawPassword, encodedPassword);
    }

    public boolean matchesUserPassword(@NotNull String rawPassword, @NotNull String encodedPassword) {
        Assert.notNull(rawPassword, "rawPassword must not be null");
        Assert.notNull(rawPassword, "encodedPassword must not be null");
        return matchesPassword(userPasswordEncodingAlgorithm, rawPassword, encodedPassword);
    }

    public boolean matchesPassword(
            PasswordEncodingAlgorithm strategy,
            String rawPassword,
            String encodedPassword) {
        switch (strategy) {
            case BCRYPT:
                return BCRYPT_PASSWORD_ENCODER.matches(rawPassword, encodedPassword);
            case SALTED_SHA256:
                return MESSAGE_DIGEST_PASSWORD_ENCODER.matches(rawPassword, encodedPassword);
            case NOOP:
                return rawPassword.equals(encodedPassword);
            default:
                return false;
        }
    }

}
