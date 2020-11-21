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

package im.turms.gateway.service.impl.log;

import im.turms.common.constant.DeviceType;
import im.turms.common.constant.UserStatus;
import im.turms.gateway.plugin.extension.UserLoginActionLogHandler;
import im.turms.gateway.plugin.manager.TurmsPluginManager;
import im.turms.server.common.bo.log.UserLoginActionLog;
import im.turms.server.common.bo.log.UserLogoutActionLog;
import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.log4j.UserActivityLogging;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author James Chen
 */
@Service
public class UserLoginActionService {

    private final Node node;
    private final TurmsPluginManager turmsPluginManager;

    public UserLoginActionService(
            Node node,
            TurmsPluginManager turmsPluginManager) {
        this.node = node;
        this.turmsPluginManager = turmsPluginManager;
    }

    public void tryLogLoginActionAndTriggerHandlers(
            long logId,
            @NotNull Long userId,
            @NotNull UserStatus userStatus,
            @NotNull DeviceType loggingInDeviceType,
            @Nullable Point position,
            @NotNull String ip,
            @Nullable String deviceDetails,
            @NotNull Date loginDate) {
        boolean logUserLoginAction = node.getSharedProperties().getGateway().getLog().isLogUserLoginAction();
        boolean triggerHandlers = turmsPluginManager.isEnabled() && !turmsPluginManager.getUserLoginActionLogHandlerList().isEmpty();
        if (logUserLoginAction || triggerHandlers) {
            UserLoginActionLog loginAction = new UserLoginActionLog(logId, userId, loginDate, position, ip, userStatus, loggingInDeviceType, deviceDetails);
            if (logUserLoginAction) {
                UserActivityLogging.log(loginAction);
            }
            if (triggerHandlers) {
                triggerLoginHandlers(loginAction).subscribe();
            }
        }
    }

    public void tryLogLogoutActionAndTriggerHandlers(
            long logId,
            @NotNull Long userId,
            @NotNull Date logoutDate) {
        boolean logUserLogoutAction = node.getSharedProperties().getGateway().getLog().isLogUserLogoutAction();
        boolean triggerHandlers = turmsPluginManager.isEnabled() && !turmsPluginManager.getUserLoginActionLogHandlerList().isEmpty();
        if (logUserLogoutAction || triggerHandlers) {
            UserLogoutActionLog logoutActionLog = new UserLogoutActionLog(logId, userId, logoutDate);
            if (logUserLogoutAction) {
                UserActivityLogging.log(logoutActionLog);
            }
            if (triggerHandlers) {
                triggerLogoutHandlers(logoutActionLog).subscribe();
            }
        }
    }

    // Plugin

    private Mono<Void> triggerLoginHandlers(@NotNull UserLoginActionLog log) {
        List<UserLoginActionLogHandler> logHandlerList = turmsPluginManager.getUserLoginActionLogHandlerList();
        if (!logHandlerList.isEmpty()) {
            List<Mono<Void>> monos = new ArrayList<>(logHandlerList.size());
            for (UserLoginActionLogHandler handler : logHandlerList) {
                monos.add(handler.handleUserLoginActionLog(log));
            }
            return Mono.when(monos);
        } else {
            return Mono.empty();
        }
    }

    private Mono<Void> triggerLogoutHandlers(@NotNull UserLogoutActionLog log) {
        List<UserLoginActionLogHandler> logHandlerList = turmsPluginManager.getUserLoginActionLogHandlerList();
        if (!logHandlerList.isEmpty()) {
            List<Mono<Void>> monos = new ArrayList<>(logHandlerList.size());
            for (UserLoginActionLogHandler handler : logHandlerList) {
                monos.add(handler.handleUserLogoutActionLog(log));
            }
            return Mono.when(monos);
        } else {
            return Mono.empty();
        }
    }

}