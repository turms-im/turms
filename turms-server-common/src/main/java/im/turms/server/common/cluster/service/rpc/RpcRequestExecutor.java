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
import im.turms.server.common.tracing.TracingContext;
import org.springframework.context.ApplicationContext;
import reactor.core.publisher.Mono;

import javax.annotation.Nullable;

/**
 * @author James Chen
 */
public class RpcRequestExecutor {

    private final ApplicationContext context;

    public RpcRequestExecutor(ApplicationContext context) {
        this.context = context;
    }

    /**
     * @implNote 1. We record request time/response here because the RPC request may run on the local machine
     * 2.The method itself will call {@link RpcRequest#release()}
     */
    public <T> Mono<T> runRpcRequest(TracingContext tracingContext,
                                     RpcRequest<T> rpcRequest,
                                     @Nullable TurmsConnection connection,
                                     String fromNodeId) {
        rpcRequest.touch(rpcRequest);
        try {
            tracingContext.updateThreadContext();
            rpcRequest.init(context, connection, fromNodeId);
            Mono<T> result;
            // It's the responsibility of the implementation of call() or callAsync()
            // to release the request once if the request has a bound buffer
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
                    .doFinally(signalType -> tracingContext.clearThreadContext());
        } catch (RpcException e) {
            rpcRequest.release();
            return Mono.error(e);
        } catch (TurmsBusinessException e) {
            rpcRequest.release();
            return Mono.error(RpcException.get(RpcErrorCode.FAILED_TO_RUN_RPC, e.getCode(), e.getReason()));
        } catch (Exception e) {
            rpcRequest.release();
            return Mono.error(RpcException.get(RpcErrorCode.FAILED_TO_RUN_RPC, TurmsStatusCode.SERVER_INTERNAL_ERROR, e.toString(), e));
        } finally {
            tracingContext.clearThreadContext();
        }
    }

}