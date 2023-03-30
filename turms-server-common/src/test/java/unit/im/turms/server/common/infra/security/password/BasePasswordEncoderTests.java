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

package unit.im.turms.server.common.infra.security.password;

import java.nio.charset.StandardCharsets;

import im.turms.server.common.infra.security.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author James Chen
 */
public abstract class BasePasswordEncoderTests {

    void test(PasswordEncoder encoder, int expectedEncodedPasswordLength) {
        byte[] rawPassword = "asdfjh9^(*:L}{<sdfy516测试テスト".getBytes(StandardCharsets.UTF_8);
        byte[] encodedPassword = encoder.encode(rawPassword);
        boolean matches = encoder.matches(rawPassword, encodedPassword);

        assertThat(matches).isTrue();
        assertThat(encodedPassword).hasSize(expectedEncodedPasswordLength);

        rawPassword = "asdfjh9^(*:L}{<sdfy516".getBytes(StandardCharsets.UTF_8);
        matches = encoder.matches(rawPassword, encodedPassword);

        assertThat(matches).isFalse();
    }

}