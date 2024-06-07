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

import java.lang.reflect.Method;
import java.util.Map;
import jakarta.annotation.Nullable;

import reactor.core.publisher.Mono;

import im.turms.gateway.access.client.common.authorization.policy.PolicyManager;
import im.turms.gateway.domain.session.bo.UserLoginInfo;
import im.turms.gateway.domain.session.bo.UserPermissionInfo;
import im.turms.gateway.infra.plugin.extension.UserAuthenticator;
import im.turms.server.common.access.client.dto.constant.DeviceType;
import im.turms.server.common.access.client.dto.constant.UserStatus;
import im.turms.server.common.domain.admin.constant.AdminConst;
import im.turms.server.common.domain.location.bo.Location;
import im.turms.server.common.infra.exception.IncompatibleInternalChangeException;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.plugin.PluginManager;
import im.turms.server.common.infra.plugin.invoker.SequentialExtensionPointInvoker;
import im.turms.server.common.infra.property.TurmsProperties;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.property.env.gateway.identityaccessmanagement.IdentityAccessManagementProperties;

import static im.turms.gateway.domain.session.bo.UserPermissionInfo.GRANTED_WITH_ALL_PERMISSIONS;
import static im.turms.gateway.domain.session.bo.UserPermissionInfo.GRANTED_WITH_ALL_PERMISSIONS_MONO;
import static im.turms.gateway.domain.session.bo.UserPermissionInfo.LOGIN_AUTHENTICATION_FAILED;
import static im.turms.gateway.domain.session.bo.UserPermissionInfo.LOGIN_AUTHENTICATION_FAILED_MONO;

/**
 * @author James Chen
 */
public class SessionIdentityAccessManager {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(SessionIdentityAccessManager.class);

    private static final Method AUTHENTICATE_METHOD;

    static {
        try {
            AUTHENTICATE_METHOD =
                    UserAuthenticator.class.getDeclaredMethod("authenticate", UserLoginInfo.class);
        } catch (NoSuchMethodException e) {
            throw new IncompatibleInternalChangeException(e);
        }
    }

    private final PluginManager pluginManager;

    private boolean enableIdentityAccessManagement;

    private final SessionIdentityAccessManagementSupport sessionIdentityAccessManagementSupport;

    public SessionIdentityAccessManager(
            TurmsPropertiesManager propertiesManager,
            PluginManager pluginManager,
            UserService userService) {
        this.pluginManager = pluginManager;
        IdentityAccessManagementProperties identityAccessManagementProperties =
                propertiesManager.getLocalProperties()
                        .getGateway()
                        .getSession()
                        .getIdentityAccessManagement();

        sessionIdentityAccessManagementSupport =
                switch (identityAccessManagementProperties.getType()) {
                    case NOOP -> new NoopSessionIdentityAccessManager();
                    case HTTP -> new HttpSessionIdentityAccessManager(
                            identityAccessManagementProperties.getHttp(),
                            new PolicyManager());
                    case JWT -> new JwtSessionIdentityAccessManager(
                            identityAccessManagementProperties.getJwt(),
                            new PolicyManager());
                    case PASSWORD -> new PasswordSessionIdentityAccessManager(userService);
                    case LDAP -> new LdapSessionIdentityAccessManager(
                            identityAccessManagementProperties.getLdap());
                };

        propertiesManager.notifyAndAddGlobalPropertiesChangeListener(this::updateGlobalProperties);
    }

    private void updateGlobalProperties(TurmsProperties properties) {
        enableIdentityAccessManagement =
                sessionIdentityAccessManagementSupport.updateGlobalProperties(properties);
    }

    // @Override
    public Mono<UserPermissionInfo> verifyAndGrant(
            int version,
            Long userId,
            @Nullable String password,
            DeviceType deviceType,
            @Nullable Map<String, String> deviceDetails,
            @Nullable UserStatus userStatus,
            @Nullable Location location,
            @Nullable String ip) {
        if (userId.equals(AdminConst.ADMIN_REQUESTER_ID)) {
            return LOGIN_AUTHENTICATION_FAILED_MONO;
        }
        if (!enableIdentityAccessManagement) {
            return GRANTED_WITH_ALL_PERMISSIONS_MONO;
        }
        UserLoginInfo userLoginInfo = new UserLoginInfo(
                version,
                userId,
                password,
                deviceType,
                deviceDetails,
                userStatus,
                location,
                ip);
        Mono<UserPermissionInfo> defaultVerifyAndGrantHandler =
                sessionIdentityAccessManagementSupport.verifyAndGrant(userLoginInfo);
        // TODO: Support authorization for plugins
        if (pluginManager.hasRunningExtensions(UserAuthenticator.class)) {
            Mono<UserPermissionInfo> authenticate =
                    pluginManager
                            .invokeExtensionPointsSequentially(UserAuthenticator.class,
                                    AUTHENTICATE_METHOD,
                                    (SequentialExtensionPointInvoker<UserAuthenticator, Boolean>) (
                                            authenticator,
                                            pre) -> pre.switchIfEmpty(Mono.defer(() -> authenticator
                                                    .authenticate(userLoginInfo))))
                            .map(authenticated -> authenticated
                                    ? GRANTED_WITH_ALL_PERMISSIONS
                                    : LOGIN_AUTHENTICATION_FAILED);
            return authenticate.switchIfEmpty(defaultVerifyAndGrantHandler);
        }
        return defaultVerifyAndGrantHandler;
    }

}