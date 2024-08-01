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

package benchmark.java;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.concurrent.ThreadLocalRandom;
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

/**
 * Reference:
 * <p>
 * JMH version: 1.37
 * <p>
 * VM version: JDK 21, OpenJDK 64-Bit Server VM, 21+35-LTS
 * <p>
 * Random.secureRandom_nextBytes avgt 10 1292.702 ± 158.306 ns/op
 * <p>
 * Random.threadLocalRandom_nextBytes avgt 10 34.875 ± 3.729 ns/op
 *
 * @author James Chen
 */
@Fork(value = 2)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5, time = 2)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class Random {

    private static final SecureRandom SECURE_RANDOM;

    static {
        try {
            SECURE_RANDOM = SecureRandom.getInstance("DRBG");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Benchmark
    public void threadLocalRandom_nextBytes() {
        byte[] bytes = new byte[16];
        // Note we don't cache "ThreadLocalRandom.current()" on purpose
        // because most use cases of Turms will call "ThreadLocalRandom.current()"
        // without cache to generate random values.
        ThreadLocalRandom.current()
                .nextBytes(bytes);
    }

    @Benchmark
    public void secureRandom_nextBytes() {
        byte[] bytes = new byte[16];
        SECURE_RANDOM.nextBytes(bytes);
    }

}