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

package im.turms.gateway.manager;

import im.turms.server.common.manager.PublicIpManager;
import im.turms.server.common.manager.address.AddressCollection;
import im.turms.server.common.manager.address.AddressCollector;
import im.turms.server.common.manager.address.BaseServiceAddressManager;
import im.turms.server.common.property.TurmsProperties;
import im.turms.server.common.property.TurmsPropertiesManager;
import im.turms.server.common.property.constant.AdvertiseStrategy;
import im.turms.server.common.property.env.common.AddressProperties;
import im.turms.server.common.property.env.gateway.BaseServerProperties;
import im.turms.server.common.property.env.gateway.DiscoveryProperties;
import im.turms.server.common.property.env.gateway.TcpProperties;
import im.turms.server.common.property.env.gateway.UdpProperties;
import im.turms.server.common.property.env.gateway.WebSocketProperties;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.web.server.Ssl;
import org.springframework.stereotype.Component;

import java.net.UnknownHostException;

/**
 * @author James Chen
 */
@Component
@Log4j2
public class ServiceAddressManager extends BaseServiceAddressManager {

    private DiscoveryProperties gatewayApiDiscoveryProperties;

    private String metricsApiAddress;
    private String wsAddress;
    private String tcpAddress;
    private String udpAddress;

    public ServiceAddressManager(
            ServerProperties metricsApiProperties,
            TurmsPropertiesManager turmsPropertiesManager,
            PublicIpManager publicIpManager) throws UnknownHostException {
        super(publicIpManager, turmsPropertiesManager.getLocalProperties());
        updateCollectorAndAddresses(metricsApiProperties, turmsPropertiesManager.getLocalProperties());
        turmsPropertiesManager.addListeners(properties -> {
            boolean areAddressPropertiesChange = areAddressPropertiesChange(properties);
            boolean isMemberHostChanged = updateMemberHostIfChanged(properties);
            if (areAddressPropertiesChange) {
                try {
                    updateCollectorAndAddresses(metricsApiProperties, properties);
                } catch (Exception e) {
                    log.error("Failed to update address collector", e);
                }
            }
            if (areAddressPropertiesChange || isMemberHostChanged) {
                AddressCollection addresses = new AddressCollection(getMemberHost(),
                        metricsApiAddress,
                        null,
                        wsAddress,
                        tcpAddress,
                        udpAddress);
                triggerOnAddressesChangedListeners(addresses);
            }
        });
    }

    @Override
    public String getMetricsApiAddress() {
        return metricsApiAddress;
    }

    @Override
    public String getWsAddress() {
        return wsAddress;
    }

    @Override
    public String getTcpAddress() {
        return tcpAddress;
    }

    @Override
    public String getUdpAddress() {
        return udpAddress;
    }

    private void updateCollectorAndAddresses(ServerProperties metricsApiProperties, TurmsProperties properties)
            throws UnknownHostException {
        AddressProperties newMetricsApiAddressProperties = properties.getGateway().getMetricsApiAddress();
        AddressCollector adminApiAddressesCollector = getAddressCollector(newMetricsApiAddressProperties, metricsApiProperties);
        WebSocketProperties webSocketProperties = properties.getGateway().getWebsocket();
        TcpProperties tcpProperties = properties.getGateway().getTcp();
        UdpProperties udpProperties = properties.getGateway().getUdp();
        gatewayApiDiscoveryProperties = properties.getGateway().getServiceDiscovery();
        metricsApiAddress = adminApiAddressesCollector.getHttpAddress() + "/actuator";
        wsAddress = webSocketProperties.isEnabled()
                ? getGatewayApiAddressCollector(webSocketProperties, gatewayApiDiscoveryProperties).getWsAddress()
                : null;
        tcpAddress = tcpProperties.isEnabled()
                ? getGatewayApiAddressCollector(tcpProperties, gatewayApiDiscoveryProperties).getAddress()
                : null;
        udpAddress = udpProperties.isEnabled()
                ? getGatewayApiAddressCollector(udpProperties, gatewayApiDiscoveryProperties).getAddress()
                : null;
    }

    private AddressCollector getGatewayApiAddressCollector(BaseServerProperties serverProperties,
                                                           DiscoveryProperties gatewayApiDiscoveryProperties) throws UnknownHostException {
        AdvertiseStrategy advertiseStrategy = gatewayApiDiscoveryProperties.getAdvertiseStrategy();
        String advertiseHost = gatewayApiDiscoveryProperties.getAdvertiseHost();
        boolean attachPortToHost = gatewayApiDiscoveryProperties.isAttachPortToHost();
        String bindHost = serverProperties.getHost();
        int port = serverProperties.getPort();
        Ssl ssl = serverProperties.getSsl();
        boolean isSslEnabled = ssl != null && ssl.isEnabled();
        return new AddressCollector(bindHost, advertiseHost, port, isSslEnabled, attachPortToHost, advertiseStrategy, publicIpManager);
    }

    private boolean areAddressPropertiesChange(TurmsProperties newTurmsProperties) {
        DiscoveryProperties newDiscoveryProperties = newTurmsProperties.getGateway().getServiceDiscovery();
        return gatewayApiDiscoveryProperties.getAdvertiseStrategy() != newDiscoveryProperties.getAdvertiseStrategy()
                || !StringUtils.equals(gatewayApiDiscoveryProperties.getAdvertiseHost(), newDiscoveryProperties.getAdvertiseHost())
                || gatewayApiDiscoveryProperties.isAttachPortToHost() != newDiscoveryProperties.isAttachPortToHost();
    }

}