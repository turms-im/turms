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
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableScheduling
public class TurmsApplication {
    public static void main(String[] args) {
        try {
            SpringApplication.run(TurmsApplication.class, args);
        } catch (Exception e) {
            // Make sure that turms can exit if SpringApplication failed to bootstrap (e.g. PortInUseException)
            // because there are still some non-daemon threads that are running after the context has been closed
            // in org.springframework.boot.SpringApplication.handleRunFailure,
            // which won't trigger im.turms.turms.config.spring.ApplicationContextConfig.handleContextClosedEvent.
            System.exit(1);
        }
    }
}
