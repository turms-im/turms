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

import java.util.Set;

import org.junit.jupiter.api.Test;

import im.turms.server.common.infra.lang.ClassUtil;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author James Chen
 */
class ClassUtilTests {

    interface Interface1 {
    }

    interface Interface2 extends Interface1 {
    }

    interface Interface3 {
    }

    static class Class1 implements Interface2 {
    }

    static class Class2 extends Class1 implements Interface3 {
    }

    @Test
    void getInterfaces() {
        Set<Class<?>> interfaces = ClassUtil.getInterfaces(Class2.class, clazz -> true);
        assertThat(interfaces).containsExactlyInAnyOrder(Interface2.class, Interface3.class);

        interfaces = ClassUtil.getInterfaces(Class2.class, clazz -> clazz == Interface2.class);
        assertThat(interfaces).containsExactlyInAnyOrder(Interface2.class);
    }

    @Test
    void getFirstInterface() {
        Class<?> firstInterface = ClassUtil.getFirstInterface(Class2.class, clazz -> true);
        assertThat(firstInterface).isEqualTo(Interface3.class);

        firstInterface =
                ClassUtil.getFirstInterface(Class2.class, clazz -> clazz == Interface2.class);
        assertThat(firstInterface).isEqualTo(Interface2.class);
    }

}