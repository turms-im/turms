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

package im.turms.server.common.access.admin.filter;

import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.domain.admin.bo.AdminAction;
import im.turms.server.common.infra.collection.MapUtil;
import im.turms.server.common.infra.exception.ResponseException;
import im.turms.server.common.infra.logging.AdminApiLogging;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.plugin.PluginManager;
import im.turms.server.common.infra.plugin.extension.AdminActionHandler;
import im.turms.server.common.infra.tracing.TracingCloseableContext;
import im.turms.server.common.infra.tracing.TracingContext;
import im.turms.server.common.infra.validation.Validator;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.reactive.result.method.MethodInvokeInterceptor;
import org.springframework.web.reactive.result.method.TurmsHandlerMethod;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Nullable;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author James Chen
 */
public class EndpointInvokeInterceptor extends MethodInvokeInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(EndpointInvokeInterceptor.class);

    private final PluginManager pluginManager;
    private final boolean allowDeleteWithoutFilter;

    private final TurmsHandlerMethod handlerMethod;
    private final String requestId;
    private final long requestTime;
    private final TracingContext tracingContext;
    private final String account;
    private final String ip;
    private final String action;

    public EndpointInvokeInterceptor(PluginManager pluginManager,
                                     boolean allowDeleteWithoutFilter,
                                     TurmsHandlerMethod handlerMethod,
                                     String requestId,
                                     long requestTime,
                                     TracingContext tracingContext,
                                     String account,
                                     String ip,
                                     String action) {
        this.pluginManager = pluginManager;
        this.allowDeleteWithoutFilter = allowDeleteWithoutFilter;

        this.handlerMethod = handlerMethod;
        this.requestId = requestId;
        this.requestTime = requestTime;
        this.tracingContext = tracingContext;
        this.account = account;
        this.ip = ip;
        this.action = action;
    }

    @Nullable
    @Override
    public Object beforeInvoke(ServerWebExchange exchange, Method method, Object[] args) {
        if (method.isAnnotationPresent(DeleteMapping.class) && !isValidDeleteRequest(args)) {
            return Mono.error(ResponseException.get(ResponseStatusCode.NO_FILTER_FOR_DELETE_OPERATION));
        }
        return null;
    }

    @Override
    public Object afterInvoke(ServerWebExchange exchange, Method method, Object[] args,
                              @Nullable Object response, @Nullable Throwable t) {
        int processingTime = (int) (System.currentTimeMillis() - requestTime);
        return logAndTriggerHandlers(handlerMethod,
                tracingContext,
                account,
                ip,
                requestId,
                requestTime,
                processingTime,
                action,
                args,
                response,
                t);
    }

    private boolean isValidDeleteRequest(Object[] args) {
        if (allowDeleteWithoutFilter) {
            return true;
        }
        return !Validator.areAllFalsy(args);
    }

    private Mono<?> logAndTriggerHandlers(
            TurmsHandlerMethod handlerMethod,
            TracingContext context,
            String account,
            String ip,
            String requestId,
            long requestTime,
            int processingTime,
            String action,
            Object[] args,
            Object response,
            Throwable throwable) {
        if (response instanceof Mono<?> responseMono) {
            return responseMono
                    .doOnEach(signal -> {
                        if (!signal.isOnComplete() && !signal.isOnError()) {
                            return;
                        }
                        logAndTriggerHandlers0(handlerMethod,
                                context,
                                account,
                                ip,
                                requestId,
                                requestTime,
                                action,
                                args,
                                processingTime,
                                signal.getThrowable());
                    });
        } else if (response instanceof Flux) {
            return Mono.error(new IllegalStateException("Unexpected response type: Flux. Use Mono instead"));
        } else {
            logAndTriggerHandlers0(handlerMethod,
                    context,
                    account,
                    ip,
                    requestId,
                    requestTime,
                    action,
                    args,
                    processingTime,
                    throwable);
        }
        return null;
    }

    private void logAndTriggerHandlers0(
            TurmsHandlerMethod handlerMethod,
            TracingContext context,
            String account,
            String ip,
            String requestId,
            long requestTime,
            String action,
            Object[] args,
            int processingTime,
            Throwable throwable) {
        Map<String, Object> params = new HashMap<>(MapUtil.getCapability(args.length));
        for (int i = 0; i < args.length; i++) {
            Object arg = args[i];
            if (arg == null) {
                continue;
            }
            MethodParameter parameter = handlerMethod.getMethodParameters()[i];
            params.put(parameter.getParameterName(), arg);
        }
        AdminAction adminAction = new AdminAction(
                account,
                ip,
                new Date(requestTime),
                action,
                params,
                processingTime);
        pluginManager.invokeExtensionPoints(AdminActionHandler.class,
                        "handleAdminAction",
                        handler -> handler.handleAdminAction(adminAction))
                .subscribe(null, LOGGER::error);
        try (TracingCloseableContext ignored = context.asCloseable()) {
            AdminApiLogging.log(
                    account,
                    ip,
                    requestId,
                    requestTime,
                    action,
                    params,
                    processingTime,
                    throwable);
        }
    }

}