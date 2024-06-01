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

package im.turms.gateway.domain.session.service;

import reactor.core.publisher.Mono;

import im.turms.gateway.domain.session.bo.UserLoginInfo;
import im.turms.gateway.domain.session.bo.UserPermissionInfo;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.property.TurmsProperties;

import static im.turms.gateway.domain.session.bo.UserPermissionInfo.GRANTED_WITH_ALL_PERMISSIONS;
import static im.turms.gateway.domain.session.bo.UserPermissionInfo.LOGGING_IN_USER_NOT_ACTIVE_MONO;
import static im.turms.gateway.domain.session.bo.UserPermissionInfo.LOGIN_AUTHENTICATION_FAILED;

/**
 * @author James Chen
 */
public class PasswordSessionIdentityAccessManager
        implements SessionIdentityAccessManagementSupport {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(PasswordSessionIdentityAccessManager.class);

    private final UserService userService;

    public PasswordSessionIdentityAccessManager(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Mono<UserPermissionInfo> verifyAndGrant(UserLoginInfo userLoginInfo) {
        Long userId = userLoginInfo.userId();
        String password = userLoginInfo.password();
        return userService.isActiveAndNotDeleted(userId)
                .flatMap(isActiveAndNotDeleted -> isActiveAndNotDeleted
                        ? userService.authenticate(userId, password)
                                .map(authenticated -> authenticated
                                        ? GRANTED_WITH_ALL_PERMISSIONS
                                        : LOGIN_AUTHENTICATION_FAILED)
                        : LOGGING_IN_USER_NOT_ACTIVE_MONO);
    }

    @Override
    public boolean updateGlobalProperties(TurmsProperties properties) {
        boolean enableIdentityAccessManagement = properties.getGateway()
                .getSession()
                .getIdentityAccessManagement()
                .isEnabled();
        if (enableIdentityAccessManagement && !userService.isEnabled()) {
            // We refuse to update the wrong setting, otherwise users cannot log in
            // until developers correct it, and it will be a big problem.
            LOGGER.error(
                    "Refused an illegal operation that tried to enable the previously disabled password-based identity and access management, "
                            + "because "
                            + "\"turms.gateway.session.identity-access-management.enabled\" is false, or "
                            + "\"turms.gateway.session.identity-access-management.type\" is not \"password\" on startup. "
                            + "To enable it, you need to update the \"turms.gateway.session.identity-access-management.enabled\" setting to true and restart the server");
            return false;
        } else {
            return enableIdentityAccessManagement;
        }
    }
}