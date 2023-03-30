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

package im.turms.gateway.infra.address;

import java.util.ArrayList;
import java.util.List;
import jakarta.annotation.Nullable;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import im.turms.server.common.infra.address.BaseServiceAddressManager;
import im.turms.server.common.infra.address.IpDetector;
import im.turms.server.common.infra.property.TurmsProperties;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.property.constant.AdvertiseStrategy;
import im.turms.server.common.infra.property.env.common.AddressProperties;
import im.turms.server.common.infra.property.env.common.adminapi.AdminHttpProperties;
import im.turms.server.common.infra.property.env.gateway.DiscoveryProperties;
import im.turms.server.common.infra.property.env.gateway.TcpProperties;
import im.turms.server.common.infra.property.env.gateway.UdpProperties;
import im.turms.server.common.infra.property.env.gateway.WebSocketProperties;
import im.turms.server.common.infra.reactor.PublisherPool;

/**
 * @author James Chen
 */
@Component
public class ServiceAddressManager extends BaseServiceAddressManager {

    private DiscoveryProperties gatewayApiDiscoveryProperties;

    @Nullable
    private String wsAddress;
    @Nullable
    private String tcpAddress;
    @Nullable
    private String udpAddress;

    public ServiceAddressManager(IpDetector ipDetector, TurmsPropertiesManager propertiesManager) {
        super(propertiesManager.getLocalProperties()
                .getGateway()
                .getAdminApi()
                .getHttp(), ipDetector, propertiesManager);
    }

    @Nullable
    @Override
    public String getWsAddress() {
        return wsAddress;
    }

    @Nullable
    @Override
    public String getTcpAddress() {
        return tcpAddress;
    }

    @Nullable
    @Override
    public String getUdpAddress() {
        return udpAddress;
    }

    @Override
    protected Mono<Boolean> updateCustomAddresses(
            AdminHttpProperties adminHttpProperties,
            TurmsProperties properties) {
        if (!areAddressPropertiesChange(properties)) {
            return PublisherPool.FALSE;
        }
        WebSocketProperties webSocketProperties = properties.getGateway()
                .getWebsocket();
        TcpProperties tcpProperties = properties.getGateway()
                .getTcp();
        UdpProperties udpProperties = properties.getGateway()
                .getUdp();
        gatewayApiDiscoveryProperties = properties.getGateway()
                .getServiceDiscovery();
        AdvertiseStrategy advertiseStrategy = gatewayApiDiscoveryProperties.getAdvertiseStrategy();
        String advertiseHost = gatewayApiDiscoveryProperties.getAdvertiseHost();
        boolean attachPortToHost = gatewayApiDiscoveryProperties.isAttachPortToHost();
        List<Mono<?>> updateMonos = new ArrayList<>(3);
        if (webSocketProperties.isEnabled()) {
            Mono<?> updateWsAddress =
                    queryHost(advertiseStrategy, webSocketProperties.getHost(), advertiseHost)
                            .doOnNext(host -> wsAddress = (adminHttpProperties.getSsl()
                                    .isEnabled()
                                            ? "wss://"
                                            : "ws://")
                                    + host + (attachPortToHost
                                            ? ":"
                                                    + webSocketProperties.getPort()
                                            : ""));
            updateMonos.add(updateWsAddress);
        }
        if (tcpProperties.isEnabled()) {
            Mono<?> updateTcpAddress =
                    queryHost(advertiseStrategy, tcpProperties.getHost(), advertiseHost)
                            .doOnNext(host -> tcpAddress = host + (attachPortToHost
                                    ? ":"
                                            + tcpProperties.getPort()
                                    : ""));
            updateMonos.add(updateTcpAddress);
        }
        if (udpProperties.isEnabled()) {
            Mono<?> updateUdpAddress =
                    queryHost(advertiseStrategy, udpProperties.getHost(), advertiseHost)
                            .doOnNext(host -> udpAddress = host + (attachPortToHost
                                    ? ":"
                                            + udpProperties.getPort()
                                    : ""));
            updateMonos.add(updateUdpAddress);
        }
        return Mono.whenDelayError(updateMonos)
                .thenReturn(true);
    }

    @Override
    protected AddressProperties getAdminAddressProperties(TurmsProperties properties) {
        return properties.getGateway()
                .getAdminApi()
                .getAddress();
    }

    private boolean areAddressPropertiesChange(TurmsProperties newTurmsProperties) {
        DiscoveryProperties newDiscoveryProperties = newTurmsProperties.getGateway()
                .getServiceDiscovery();
        return gatewayApiDiscoveryProperties == null
                || gatewayApiDiscoveryProperties.getAdvertiseStrategy() != newDiscoveryProperties
                        .getAdvertiseStrategy()
                || !StringUtils.equals(gatewayApiDiscoveryProperties.getAdvertiseHost(),
                        newDiscoveryProperties.getAdvertiseHost())
                || gatewayApiDiscoveryProperties.isAttachPortToHost() != newDiscoveryProperties
                        .isAttachPortToHost();
    }

}