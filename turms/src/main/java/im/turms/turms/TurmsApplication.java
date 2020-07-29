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

package im.turms.turms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author James Chen
 */
@SpringBootApplication(
        scanBasePackages = {"im.turms.turms", "im.turms.server.common"},
        proxyBeanMethods = false)
public class TurmsApplication {

    public static void main(String[] args) {
        System.setProperty("log4j2.contextSelector", "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");
        try {
            SpringApplication.run(TurmsApplication.class, args);
        } catch (Exception e) {
            // Make sure that turms can exit if SpringApplication failed to bootstrap (e.g. PortInUseException)
            // because there are still some non-daemon threads running after the context has been closed

            // Note that org.springframework.boot.SpringApplication.handleRunFailure may not trigger
            // im.turms.turms.context.ApplicationContextConfig.handleContextClosedEvent
            // because the context hadn't been initialized.

            // Don't print the exception because Spring should have done it
            System.exit(1);
        }
    }

}
