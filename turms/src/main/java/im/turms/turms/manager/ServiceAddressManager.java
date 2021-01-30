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

package im.turms.turms.manager;

import im.turms.server.common.manager.PublicIpManager;
import im.turms.server.common.manager.address.AddressCollector;
import im.turms.server.common.manager.address.IServiceAddressManager;
import im.turms.server.common.property.TurmsProperties;
import im.turms.server.common.property.TurmsPropertiesManager;
import im.turms.server.common.property.constant.AdvertiseStrategy;
import im.turms.server.common.property.env.service.env.DiscoveryProperties;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.web.server.Ssl;
import org.springframework.stereotype.Component;

import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author James Chen
 */
@Component
@Log4j2
public class ServiceAddressManager implements IServiceAddressManager {

    public final List<Consumer<String>> onAddressChangeListeners = new LinkedList<>();
    private final Integer port;
    private final boolean isSslEnabled;
    private final String bindHost;
    private final PublicIpManager publicIpManager;
    private DiscoveryProperties discoveryProperties;

    @Getter
    private String serviceAddress;
    private AddressCollector addressCollector;

    public ServiceAddressManager(
            TurmsPropertiesManager turmsPropertiesManager,
            ServerProperties serverProperties,
            PublicIpManager publicIpManager) throws UnknownHostException {
        discoveryProperties = turmsPropertiesManager.getLocalProperties().getService().getDiscovery();
        port = serverProperties.getPort();
        if (port == null || port <= 0) {
            throw new UnknownHostException("Invalid service port: " + port);
        }
        bindHost = serverProperties.getAddress().getHostAddress();
        Ssl ssl = serverProperties.getSsl();
        isSslEnabled = ssl != null && ssl.isEnabled();
        this.publicIpManager = publicIpManager;
        this.addressCollector = getAddressCollector(turmsPropertiesManager.getLocalProperties());
        serviceAddress = addressCollector.getHttpAddress();
        turmsPropertiesManager.addListeners(properties -> {
            if (arePropertiesChange(properties)) {
                try {
                    this.addressCollector = getAddressCollector(properties);
                    this.discoveryProperties = properties.getService().getDiscovery();
                    serviceAddress = addressCollector.getWsAddress();
                    for (Consumer<String> listener : onAddressChangeListeners) {
                        listener.accept(serviceAddress);
                    }
                } catch (Exception e) {
                    log.error(e);
                }
            }
        });
    }

    @Override
    public void addListener(Consumer<String> listener) {
        onAddressChangeListeners.add(listener);
    }

    private AddressCollector getAddressCollector(TurmsProperties turmsProperties) throws UnknownHostException {
        DiscoveryProperties discoveryProperties = turmsProperties.getService().getDiscovery();
        AdvertiseStrategy advertiseStrategy = discoveryProperties.getAdvertiseStrategy();
        String advertiseHost = discoveryProperties.getAdvertiseHost();
        boolean attachPortToHost = discoveryProperties.isAttachPortToHost();
        return new AddressCollector(bindHost, advertiseHost, port, isSslEnabled, attachPortToHost, advertiseStrategy, publicIpManager);
    }

    private boolean arePropertiesChange(TurmsProperties newTurmsProperties) {
        DiscoveryProperties newDiscoveryProperties = newTurmsProperties.getService().getDiscovery();
        return discoveryProperties.getAdvertiseStrategy() != newDiscoveryProperties.getAdvertiseStrategy()
                || !StringUtils.equals(discoveryProperties.getAdvertiseHost(), newDiscoveryProperties.getAdvertiseHost())
                || discoveryProperties.isAttachPortToHost() != newDiscoveryProperties.isAttachPortToHost();
    }

}
