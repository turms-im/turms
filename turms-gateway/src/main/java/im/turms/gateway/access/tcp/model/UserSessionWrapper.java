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

package im.turms.gateway.access.tcp.model;

import im.turms.gateway.pojo.bo.session.UserSession;
import lombok.Data;
import reactor.netty.Connection;

/**
 * @author James Chen
 */
@Data
public class UserSessionWrapper {

    private final Connection connection;
    private UserSession userSession;

    public UserSessionWrapper(Connection connection) {
        this.connection = connection;
    }

    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
        connection.outbound()
                .send(userSession.getNotificationFlux(), byteBuf -> true)
                .then()
                .subscribe();
    }

    public boolean hasUserSession() {
        return userSession != null;
    }

}