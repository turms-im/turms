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

import lombok.extern.slf4j.Slf4j;
import org.testcontainers.containers.output.BaseConsumer;
import org.testcontainers.containers.output.OutputFrame;

/**
 * @author James Chen
 */
@Slf4j
public class ServiceLogConsumer extends BaseConsumer<ServiceLogConsumer> {

    private final String serviceName;

    public ServiceLogConsumer(String serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public void accept(OutputFrame frame) {
        switch (frame.getType()) {
            case STDOUT -> log.info("["
                    + serviceName
                    + "]: "
                    + frame.getUtf8String());
            case STDERR -> log.error("["
                    + serviceName
                    + "]: "
                    + frame.getUtf8String());
            case END -> {
            }
        }
    }

}
