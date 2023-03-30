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

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
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

import im.turms.server.common.infra.reflect.UnsafeBasedVarAccessor;
import im.turms.server.common.infra.reflect.VarAccessorFactory;

/**
 * Reference: JMH version: 1.35 VM version: JDK 17.0.4.1, OpenJDK 64-Bit Server VM, 17.0.4.1+1
 * GetFieldValue.plain avgt 10 0.377 ± 0.002 ns/op
 * <p>
 * GetFieldValue.varAccessor_get avgt 10 1.024 ± 0.017 ns/op GetFieldValue.field_get avgt 10 2.761 ±
 * 0.012 ns/op GetFieldValue.methodHandle_invokeExact avgt 10 4.141 ± 0.066 ns/op
 * GetFieldValue.varHandle_get avgt 10 6.234 ± 0.211 ns/op
 * <p>
 * GetFieldValue.final_varAccessor_get avgt 10 0.886 ± 0.010 ns/op GetFieldValue.final_field_get
 * avgt 10 2.271 ± 0.021 ns/op GetFieldValue.final_methodHandle_invokeExact avgt 10 0.378 ± 0.003
 * ns/op GetFieldValue.final_varHandle_get avgt 10 0.386 ± 0.006 ns/op
 *
 * @author James Chen
 */
@Fork(value = 2, jvmArgsAppend = "--add-opens=java.base/java.lang=ALL-UNNAMED")
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5, time = 2)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class GetFieldValue {

    private static final byte[] BYTES = new byte[]{0, 1, 2, 3};
    private static final String STRING = new String(BYTES);

    private static Field field;
    private static MethodHandle methodHandle;
    private static VarHandle varHandle;
    private static UnsafeBasedVarAccessor<String, byte[]> varAccessor;

    private static final Field FIELD;
    private static final MethodHandle METHOD_HANDLE;
    private static final VarHandle VAR_HANDLE;
    private static final UnsafeBasedVarAccessor<String, byte[]> VAR_ACCESSOR;

    static {
        try {
            MethodHandles.Lookup lookup = MethodHandles.lookup();
            field = String.class.getDeclaredField("value");
            field.setAccessible(true);
            methodHandle = lookup.unreflectGetter(field);
            varHandle = MethodHandles.privateLookupIn(String.class, lookup)
                    .unreflectVarHandle(field);
            varAccessor = (UnsafeBasedVarAccessor) VarAccessorFactory.get(field);

            FIELD = field;
            METHOD_HANDLE = methodHandle;
            VAR_HANDLE = varHandle;
            VAR_ACCESSOR = varAccessor;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Benchmark
    public byte[] plain() {
        return BYTES;
    }

    @Benchmark
    public byte[] field_get() throws IllegalAccessException {
        return (byte[]) field.get(STRING);
    }

    @Benchmark
    public byte[] methodHandle_invokeExact() throws Throwable {
        return (byte[]) methodHandle.invokeExact(STRING);
    }

    @Benchmark
    public byte[] varHandle_get() {
        return (byte[]) varHandle.get(STRING);
    }

    @Benchmark
    public byte[] varAccessor_get() {
        return varAccessor.get(STRING);
    }

    @Benchmark
    public byte[] final_field_get() throws IllegalAccessException {
        return (byte[]) FIELD.get(STRING);
    }

    @Benchmark
    public byte[] final_methodHandle_invokeExact() throws Throwable {
        return (byte[]) METHOD_HANDLE.invokeExact(STRING);
    }

    @Benchmark
    public byte[] final_varHandle_get() {
        return (byte[]) VAR_HANDLE.get(STRING);
    }

    @Benchmark
    public byte[] final_varAccessor_get() {
        return VAR_ACCESSOR.get(STRING);
    }

}