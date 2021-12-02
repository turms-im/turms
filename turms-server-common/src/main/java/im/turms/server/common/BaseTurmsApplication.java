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

package im.turms.server.common;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.async.AsyncLogger;
import org.springframework.boot.SpringApplication;

import java.util.concurrent.TimeUnit;

import static org.apache.logging.log4j.LogManager.getLogger;

/**
 * @author James Chen
 */
public abstract class BaseTurmsApplication {

    static {
        // Hard code these properties to ensure they work as expected
        // Disable the max direct memory limit and buffer counters of Netty
        // so that we can get the used direct memory via BufferPoolMXBean without depending on ByteBufAllocator of Netty
        System.setProperty("io.netty.maxDirectMemory", "0");
        System.setProperty("log4j2.contextSelector", "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");
    }

    protected static void bootstrap(Class<?> applicationClass, String[] args) {
        try {
            SpringApplication.run(applicationClass, args);
        } catch (Exception e) {
            // Note that org.springframework.boot.SpringApplication.handleRunFailure may not trigger
            // im.turms.service.context.ApplicationContextConfig.handleContextClosedEvent
            // if the context hadn't been initialized.

            Logger log = getLogger(BaseTurmsApplication.class);
            log.error(e);
            // Flush
            AsyncLogger logger = (AsyncLogger) log;
            logger.getContext().stop(1, TimeUnit.MINUTES);

            // Make sure turms can exit if SpringApplication failed to bootstrap
            // (e.g. PortInUseException) because there are may still some non-daemon
            // threads preventing from exiting after the context has been closed
            System.exit(1);
        }
    }

}
