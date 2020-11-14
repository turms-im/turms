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

package im.turms.gateway.pojo.bo.session.connection;

import im.turms.common.constant.statuscode.SessionCloseStatus;
import im.turms.common.model.dto.udpsignal.UdpNotificationType;
import im.turms.gateway.access.udp.UdpDispatcher;
import im.turms.server.common.dto.CloseReason;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.net.InetSocketAddress;

/**
 * @author James Chen
 */
@Data
public abstract class NetConnection {

    private InetSocketAddress address;
    private volatile boolean isConnected;
    /**
     * true if it is switching UDP to TCP/WebSocket
     */
    private volatile boolean isConnectionRecovering;

    public NetConnection(boolean isConnected) {
        this.isConnected = isConnected;
    }

    public void close(@NotNull CloseReason closeReason) {
        isConnected = false;
        isConnectionRecovering = false;
    }

    public void switchToUdp() {
        isConnected = false;
        isConnectionRecovering = false;
        close(CloseReason.get(SessionCloseStatus.SWITCH));
    }

    public void tryNotifyClientToRecover() {
        if (!isConnected && !isConnectionRecovering) {
            UdpDispatcher.instance.sendSignal(address, UdpNotificationType.OPEN_CONNECTION);
            isConnectionRecovering = true;
        }
    }

}
