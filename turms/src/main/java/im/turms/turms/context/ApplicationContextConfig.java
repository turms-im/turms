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

package im.turms.turms.context;

import im.turms.server.common.cluster.node.Node;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.TimeZone;

/**
 * @author James Chen
 */
@Log4j2
@Component
public class ApplicationContextConfig {

    private final Node node;

    public ApplicationContextConfig(Node node) {
        this.node = node;
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    /**
     * We place all listeners on close event here to manage them easily and
     * know what happens explicitly when the context is closed
     */
    @EventListener(classes = ContextClosedEvent.class)
    public void handleContextClosedEvent() {
        node.stop();
    }

}
