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

package im.turms.server.common.testing;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

/**
 * @author James Chen
 */
public abstract class BaseIntegrationTest {

    protected long waitMillis = 1000L;

    public static final TestingEnvContainer ENV = new TestingEnvContainer(
            TestingEnvContainerOptions.builder()
                    .build());

    protected static final Duration DEFAULT_IO_TIMEOUT = Duration.ofSeconds(15);

    static {
        ENV.start();
    }

    protected static String getMongoUri() {
        return ENV.getMongoUri("turms-test");
    }

    protected String readText(String path) {
        InputStream resource = BaseIntegrationTest.class.getClassLoader()
                .getResourceAsStream(path);
        if (resource == null) {
            throw new RuntimeException(
                    "Could not find the resource: "
                            + path);
        }
        try {
            return new String(resource.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void waitToSync() {
        try {
            Thread.sleep(waitMillis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}