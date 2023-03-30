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

package im.turms.gateway.domain.observation.service;

import org.springframework.stereotype.Service;

import im.turms.gateway.domain.session.service.SessionService;
import im.turms.server.common.domain.observation.service.IStatisticsService;

/**
 * @author James Chen
 */
@Service
public class StatisticsService implements IStatisticsService {

    private final SessionService sessionService;

    public StatisticsService(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Override
    public int countLocalOnlineUsers() {
        return sessionService.countLocalOnlineUsers();
    }

}
