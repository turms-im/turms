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
import jakarta.annotation.Nullable;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import im.turms.server.common.infra.lang.StringUtil;
import im.turms.server.common.infra.property.TurmsProperties;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.property.constant.PasswordEncodingAlgorithm;

/**
 * @author James Chen
 */
@Component
public class PasswordManager {

    private final PasswordEncodingAlgorithm adminPasswordEncodingAlgorithm;
    private final PasswordEncodingAlgorithm userPasswordEncodingAlgorithm;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final SaltedSha256PasswordEncoder sha256PasswordEncoder;

    public PasswordManager(TurmsPropertiesManager propertiesManager) {
        TurmsProperties turmsProperties = propertiesManager.getLocalProperties();
        adminPasswordEncodingAlgorithm = turmsProperties.getSecurity()
                .getPassword()
                .getAdminPasswordEncodingAlgorithm();
        userPasswordEncodingAlgorithm = turmsProperties.getSecurity()
                .getPassword()
                .getUserPasswordEncodingAlgorithm();
        bCryptPasswordEncoder = adminPasswordEncodingAlgorithm == PasswordEncodingAlgorithm.BCRYPT
                || userPasswordEncodingAlgorithm == PasswordEncodingAlgorithm.BCRYPT
                        ? new BCryptPasswordEncoder()
                        : null;
        sha256PasswordEncoder =
                adminPasswordEncodingAlgorithm == PasswordEncodingAlgorithm.SALTED_SHA256
                        || userPasswordEncodingAlgorithm == PasswordEncodingAlgorithm.SALTED_SHA256
                                ? new SaltedSha256PasswordEncoder()
                                : null;
    }

    public byte[] encodePassword(PasswordEncodingAlgorithm strategy, byte[] rawPassword) {
        return switch (strategy) {
            case BCRYPT -> bCryptPasswordEncoder.encode(rawPassword);
            case SALTED_SHA256 -> sha256PasswordEncoder.encode(rawPassword);
            case NOOP -> rawPassword;
        };
    }

    public byte[] encodeAdminPassword(String rawPassword) {
        Assert.notNull(rawPassword, "rawPassword must not be null");
        return encodePassword(adminPasswordEncodingAlgorithm, StringUtil.getBytes(rawPassword));
    }

    public byte[] encodeUserPassword(String rawPassword) {
        Assert.notNull(rawPassword, "rawPassword must not be null");
        return encodePassword(userPasswordEncodingAlgorithm, StringUtil.getBytes(rawPassword));
    }

    public boolean matchesAdminPassword(
            @Nullable String rawPassword,
            @Nullable byte[] encodedPassword) {
        return matchesPassword(adminPasswordEncodingAlgorithm, rawPassword, encodedPassword);
    }

    public boolean matchesUserPassword(
            @Nullable String rawPassword,
            @Nullable byte[] encodedPassword) {
        return matchesPassword(userPasswordEncodingAlgorithm, rawPassword, encodedPassword);
    }

    /**
     * @return true if the passwords match. Note that the method returns true if both passwords are
     *         null
     */
    public boolean matchesPassword(
            PasswordEncodingAlgorithm strategy,
            @Nullable String rawPassword,
            @Nullable byte[] encodedPassword) {
        if (encodedPassword == null) {
            return rawPassword == null;
        } else if (rawPassword == null) {
            return false;
        }
        byte[] raw = StringUtil.getBytes(rawPassword);
        return switch (strategy) {
            case BCRYPT -> bCryptPasswordEncoder.matches(raw, encodedPassword);
            case SALTED_SHA256 -> sha256PasswordEncoder.matches(raw, encodedPassword);
            case NOOP -> Arrays.equals(raw, encodedPassword);
        };
    }

}
