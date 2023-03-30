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

package benchmark.im.turms.server.common.infra.reflect;

import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

import im.turms.server.common.infra.reflect.ReflectionUtil;

/**
 * Reference: JMH version: 1.35 VM version: JDK 17.0.4.1, OpenJDK 64-Bit Server VM, 17.0.4.1+1
 * SetAccessible.field_setAccessible avgt 10 50.175 ± 4.245 ns/op
 * SetAccessible.reflectionUtil_setAccessible avgt 10 0.518 ± 0.009 ns/op
 *
 * @author James Chen
 */

@Fork(value = 2, jvmArgsAppend = "--add-opens=java.base/java.lang=ALL-UNNAMED")
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5, time = 2)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class SetAccessible {

    private static final Field PRIVATE_FIELD;

    static {
        try {
            PRIVATE_FIELD = String.class.getDeclaredField("value");
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    @Benchmark
    public void field_setAccessible() {
        PRIVATE_FIELD.setAccessible(true);
    }

    @Benchmark
    public void reflectionUtil_setAccessible() {
        ReflectionUtil.setAccessible(PRIVATE_FIELD);
    }

}