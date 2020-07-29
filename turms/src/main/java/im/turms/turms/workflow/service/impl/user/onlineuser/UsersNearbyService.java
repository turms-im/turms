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

package im.turms.turms.workflow.service.impl.user.onlineuser;

import im.turms.common.constant.DeviceType;
import im.turms.server.common.dao.domain.User;
import im.turms.server.common.service.session.SessionLocationService;
import im.turms.turms.workflow.service.impl.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.stream.Collectors;

/**
 * @author James Chen
 */
@Service
@Validated
public class UsersNearbyService {

    private final UserService userService;
    private final SessionLocationService sessionLocationService;

    public UsersNearbyService(
            UserService userService,
            SessionLocationService sessionLocationService) {
        this.sessionLocationService = sessionLocationService;
        this.userService = userService;
    }

    public Flux<User> queryUsersProfilesNearby(
            @NotNull Long userId,
            @NotNull DeviceType deviceType,
            @Nullable Short maxPeopleNumber,
            @Nullable Double maxDistance) {
        return sessionLocationService.queryNearestUserIds(userId, deviceType, maxPeopleNumber, maxDistance)
                .collect(Collectors.toSet())
                .flatMapMany(ids -> ids.isEmpty()
                        ? Flux.empty()
                        : userService.queryUsersProfiles(ids, false));
    }

}
