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

package im.turms.server.common.access.servicerequest.rpc;

import io.micrometer.core.instrument.Tag;
import lombok.Data;
import org.springframework.context.ApplicationContext;
import reactor.core.publisher.Mono;

import im.turms.server.common.access.client.dto.request.TurmsRequest;
import im.turms.server.common.access.servicerequest.dispatcher.IServiceRequestDispatcher;
import im.turms.server.common.access.servicerequest.dto.ServiceRequest;
import im.turms.server.common.access.servicerequest.dto.ServiceResponse;
import im.turms.server.common.infra.cluster.service.rpc.NodeTypeToHandleRpc;
import im.turms.server.common.infra.cluster.service.rpc.dto.RpcRequest;

/**
 * @author James Chen
 */
@Data
public class HandleServiceRequest extends RpcRequest<ServiceResponse> {

    private static final String NAME = "handleServiceRequest";
    private static final String METRICS_TAG_CLIENT_REQUEST_TYPE = "type";
    private static IServiceRequestDispatcher dispatcher;

    private final ServiceRequest serviceRequest;

    public HandleServiceRequest(ServiceRequest serviceRequest) {
        this.serviceRequest = serviceRequest;
        setBoundBuffer(serviceRequest.getTurmsRequestBuffer());
    }

    @Override
    public String toString() {
        return "HandleServiceRequest{"
                + "name='"
                + name()
                + "'"
                + ", tag="
                + tag()
                + ", requestTime="
                + getRequestTime()
                + ", tracingContext="
                + getTracingContext()
                + ", serviceRequest="
                + serviceRequest
                + '}';
    }

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public NodeTypeToHandleRpc nodeTypeToRequest() {
        return NodeTypeToHandleRpc.GATEWAY;
    }

    @Override
    public NodeTypeToHandleRpc nodeTypeToRespond() {
        return NodeTypeToHandleRpc.SERVICE;
    }

    @Override
    public Tag tag() {
        TurmsRequest.KindCase type = serviceRequest.getType();
        if (type == null) {
            return null;
        }
        return Tag.of(METRICS_TAG_CLIENT_REQUEST_TYPE, type.name());
    }

    @Override
    public boolean isAsync() {
        return true;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        super.setApplicationContext(applicationContext);
        if (dispatcher == null) {
            dispatcher = getBean(IServiceRequestDispatcher.class);
        }
    }

    @Override
    public Mono<ServiceResponse> callAsync() {
        return dispatcher.dispatch(getTracingContext(), serviceRequest);
    }

}