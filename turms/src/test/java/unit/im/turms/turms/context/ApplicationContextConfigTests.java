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

package unit.im.turms.turms.context;

import im.turms.server.common.cluster.node.Node;
import im.turms.turms.context.ApplicationContextConfig;
import org.junit.jupiter.api.Test;

import java.util.TimeZone;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

/**
 * @author James Chen
 */
class ApplicationContextConfigTests {

    @Test
    void constructor_shouldSetTimeZoneToUTC() {
        new ApplicationContextConfig(null);
        assertThat(TimeZone.getDefault().getID()).isEqualTo("UTC");
    }

    @Test
    void handleContextClosedEvent_shouldStopNode() {
        Node node = mock(Node.class);
        ApplicationContextConfig config = new ApplicationContextConfig(node);
        config.handleContextClosedEvent();

        verify(node, times(1)).stop();
    }

}
