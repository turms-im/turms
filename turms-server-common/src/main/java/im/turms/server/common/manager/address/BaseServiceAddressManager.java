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

package im.turms.server.common.manager.address;

import im.turms.server.common.manager.PublicIpManager;
import im.turms.server.common.property.constant.AdvertiseStrategy;
import im.turms.server.common.property.env.common.AdminApiDiscoveryProperties;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.web.server.Ssl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author James Chen
 */
@Log4j2
public abstract class BaseServiceAddressManager {

    protected final PublicIpManager publicIpManager;

    private final List<Consumer<AddressCollection>> onAddressesChangedListeners = new LinkedList<>();

    protected BaseServiceAddressManager(PublicIpManager publicIpManager) {
        this.publicIpManager = publicIpManager;
    }

    public void addOnAddressesChangedListener(Consumer<AddressCollection> listener) {
        onAddressesChangedListeners.add(listener);
    }

    protected AddressCollector getAdminApiAddressCollector(ServerProperties adminApiServerProperties, AdminApiDiscoveryProperties adminApiDiscoveryProperties) throws UnknownHostException {
        Integer port = adminApiServerProperties.getPort();
        if (port == null || port <= 0) {
            throw new UnknownHostException("Invalid service port: " + port);
        }
        AdvertiseStrategy advertiseStrategy = adminApiDiscoveryProperties.getAdvertiseStrategy();
        String advertiseHost = adminApiDiscoveryProperties.getAdvertiseHost();
        boolean attachPortToHost = adminApiDiscoveryProperties.isAttachPortToHost();
        InetAddress address = adminApiServerProperties.getAddress();
        if (address == null) {
            throw new IllegalStateException("The bind host isn't specified");
        }
        String bindHost = address.getHostAddress();
        Ssl ssl = adminApiServerProperties.getSsl();
        boolean isSslEnabled = ssl != null && ssl.isEnabled();
        return new AddressCollector(bindHost, advertiseHost, adminApiServerProperties.getPort(), isSslEnabled, attachPortToHost, advertiseStrategy, publicIpManager);
    }

    public String getMetricsApiAddress() {
        return null;
    }

    public String getAdminApiAddress() {
        return null;
    }

    public String getWsAddress() {
        return null;
    }

    public String getTcpAddress() {
        return null;
    }

    public String getUdpAddress() {
        return null;
    }

    protected void triggerOnAddressesChangedListeners(AddressCollection addresses) {
        for (Consumer<AddressCollection> listener : onAddressesChangedListeners) {
            try {
                listener.accept(addresses);
            } catch (Exception e) {
                log.error("Failed to run onAddressesChangedListener", e);
            }
        }
    }

}