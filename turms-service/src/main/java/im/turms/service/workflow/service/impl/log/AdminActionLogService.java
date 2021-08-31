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

package im.turms.service.workflow.service.impl.log;

import com.mongodb.DBObject;
import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.constraint.NoWhitespace;
import im.turms.server.common.constraint.ValidIpAddress;
import im.turms.server.common.logging.AdminApiLogging;
import im.turms.server.common.mongo.IMongoCollectionInitializer;
import im.turms.service.bo.AdminAction;
import im.turms.service.plugin.extension.handler.AdminActionHandler;
import im.turms.service.plugin.manager.TurmsPluginManager;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.util.Date;
import java.util.Set;

/**
 * @author James Chen
 */
@Service
@Log4j2
@DependsOn(IMongoCollectionInitializer.BEAN_NAME)
public class AdminActionLogService {

    private static final Set<String> SENSITIVE_FIELDS = Set.of("password");
    private static final String SENSITIVE_INFO_PLACEHOLDER = "******";

    private final Node node;
    private final TurmsPluginManager turmsPluginManager;

    public AdminActionLogService(
            Node node,
            TurmsPluginManager turmsPluginManager) {
        this.node = node;
        this.turmsPluginManager = turmsPluginManager;
    }

    public void tryLogAndTriggerHandlers(
            @NotNull @NoWhitespace String account,
            @NotNull @PastOrPresent Date timestamp,
            @NotNull @ValidIpAddress String ip,
            @NotNull @NoWhitespace String action,
            @Nullable DBObject params,
            @Nullable DBObject body) {
        boolean logAdminAction = node.getSharedProperties().getService().getLog().isLogAdminAction();
        boolean triggerHandlers = turmsPluginManager.isEnabled() && !turmsPluginManager.getAdminActionHandlerList().isEmpty();
        if (logAdminAction || triggerHandlers) {
            AdminAction adminAction = new AdminAction(
                    account,
                    timestamp,
                    ip,
                    action,
                    params,
                    body);
            if (triggerHandlers) {
                Mono<Void> handleAdminAction = Mono.empty();
                for (AdminActionHandler handler : turmsPluginManager.getAdminActionHandlerList()) {
                    handleAdminAction = handleAdminAction
                            .then(Mono.defer(() -> handler.handleAdminAction(adminAction)));
                }
                handleAdminAction.subscribe();
            }
            if (body != null) {
                for (String sensitiveField : SENSITIVE_FIELDS) {
                    if (body.containsField(sensitiveField)) {
                        body.put(sensitiveField, SENSITIVE_INFO_PLACEHOLDER);
                    }
                }
            }
            if (logAdminAction) {
                AdminApiLogging.log(adminAction);
            }
        }
    }

}