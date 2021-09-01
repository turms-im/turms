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

package im.turms.server.common.cluster.service.rpc;

import im.turms.server.common.cluster.service.connection.TurmsConnection;
import im.turms.server.common.cluster.service.rpc.dto.RpcRequest;
import im.turms.server.common.cluster.service.rpc.exception.RpcException;
import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.exception.TurmsBusinessException;
import im.turms.server.common.lang.Null;
import im.turms.server.common.logging.RequestLoggingContext;
import im.turms.server.common.tracing.TracingContext;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationContext;
import reactor.core.publisher.Mono;

import javax.annotation.Nullable;

/**
 * @author James Chen
 */
@Log4j2
public class RpcRequestExecutor {

    private final ApplicationContext context;

    public RpcRequestExecutor(ApplicationContext context) {
        this.context = context;
    }

    /**
     * @implNote 1. We record request time/response here because the RPC request may run on the local machine
     * 2.The method itself will call RpcRequest#releaseBoundBuffer()
     */
    public <T> Mono<T> runRpcRequest(RequestLoggingContext loggingContext,
                                     RpcRequest<T> rpcRequest,
                                     @Nullable TurmsConnection connection,
                                     String fromNodeId) {
        rpcRequest.touchBuffer(rpcRequest);
        TracingContext tracingContext = loggingContext.getTracingContext();
        try {
            tracingContext.updateMdc();
            rpcRequest.init(context, connection, fromNodeId);
            Mono<T> result;
            // It's the responsibility of the implementations of call() or callAsync()
            // to release by 1 if the request has a bound buffer
            if (rpcRequest.isAsync()) {
                result = rpcRequest.callAsync();
            } else {
                T data = rpcRequest.call();
                // Convert null to Null.INSTANCE
                // so that we don't need to adapt the whole data flow for null
                if (data == null) {
                    data = (T) Null.INSTANCE;
                }
                result = Mono.just(data);
            }
            // TODO: slow log
            return result
                    .onErrorMap(e -> e instanceof RpcException
                            ? e
                            : RpcException.get(RpcErrorCode.FAILED_TO_RUN_RPC, TurmsStatusCode.SERVER_INTERNAL_ERROR, e.toString(), e))
                    .doFinally(signalType -> tracingContext.clearMdc());
        } catch (RpcException e) {
            rpcRequest.releaseBoundBuffer();
            return Mono.error(e);
        } catch (TurmsBusinessException e) {
            rpcRequest.releaseBoundBuffer();
            return Mono.error(RpcException.get(RpcErrorCode.FAILED_TO_RUN_RPC, e.getCode(), e.getReason()));
        } catch (Exception e) {
            rpcRequest.releaseBoundBuffer();
            return Mono.error(RpcException.get(RpcErrorCode.FAILED_TO_RUN_RPC, TurmsStatusCode.SERVER_INTERNAL_ERROR, e.toString(), e));
        } finally {
            if (tracingContext != null) {
                tracingContext.clearMdc();
            }
        }
    }

}