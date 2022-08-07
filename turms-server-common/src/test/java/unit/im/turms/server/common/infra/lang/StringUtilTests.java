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

package unit.im.turms.server.common.infra.lang;

import im.turms.server.common.infra.lang.StringUtil;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * @author James Chen
 */
class StringUtilTests {

    @Test
    void test() {
        String expected = "myテスト字符串";
        byte[] bytes = StringUtil.getBytes(expected);
        byte coder = StringUtil.getCoder(expected);
        String actual = StringUtil.newString(bytes, coder);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void substitute() {
        assertThat(StringUtil.substitute("my-{}-string", "test"))
                .isEqualTo("my-test-string");
        assertThatThrownBy(() -> StringUtil.substitute("my-{}-string", "test", "string"))
                .isInstanceOf(IllegalArgumentException.class);
        assertThat(StringUtil.substitute("my-{}-{}", "test", "string"))
                .isEqualTo("my-test-string");
        assertThat(StringUtil.substitute("my-{{}-{{}}", "test", "string"))
                .isEqualTo("my-{test-{string}");
        assertThatThrownBy(() -> StringUtil.substitute("my-{}-{}", "test"))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
