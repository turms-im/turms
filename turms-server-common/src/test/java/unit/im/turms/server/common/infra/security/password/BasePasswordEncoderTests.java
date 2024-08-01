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
        String password = "asdfjh9^(*:L}{<sdfy516测试テスト";
        assertMatchResult(encoder, password, password, true, expectedEncodedPasswordLength);
        assertMatchResult(encoder,
                password,
                "asdfjh9^(*:L}\0abc",
                false,
                expectedEncodedPasswordLength);
        assertMatchResult(encoder, "abc", "abcdef", false, expectedEncodedPasswordLength);
        // Reference: https://github.com/bcgit/bc-java/issues/1496
        assertMatchResult(encoder, "abc", "abcabc", false, expectedEncodedPasswordLength);
        assertMatchResult(encoder, "abc", "abc\0abc", false, expectedEncodedPasswordLength);
        assertMatchResult(encoder,
                "abc\0{<sdfy516",
                "abc\0abc",
                false,
                expectedEncodedPasswordLength);
        assertMatchResult(encoder,
                "0123456789012345678901234567890123456789",
                "01234567890123456789012345678901234567890123456789",
                false,
                expectedEncodedPasswordLength);
        assertMatchResult(encoder,
                "0123456789012345678901234567890123456789",
                "0123456789012345678901234567890123456789'\0'0123456789",
                false,
                expectedEncodedPasswordLength);
    }

    public void assertMatchResult(
            PasswordEncoder encoder,
            String password1,
            String password2,
            boolean expectedMatched,
            int expectedEncodedPasswordLength) {
        byte[] encodedPassword;
        byte[] rawPassword;
        boolean actualMatched;
        rawPassword = password1.getBytes(StandardCharsets.UTF_8);
        encodedPassword = encoder.encode(rawPassword);
        rawPassword = password2.getBytes(StandardCharsets.UTF_8);
        actualMatched = encoder.matches(rawPassword, encodedPassword);

        assertThat(actualMatched).isEqualTo(expectedMatched);
        assertThat(encodedPassword).hasSize(expectedEncodedPasswordLength);
    }

}