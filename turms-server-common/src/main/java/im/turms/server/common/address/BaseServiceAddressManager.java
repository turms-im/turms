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

package im.turms.server.common.address;

import im.turms.server.common.logging.core.logger.Logger;
import im.turms.server.common.logging.core.logger.LoggerFactory;
import im.turms.server.common.property.TurmsProperties;
import im.turms.server.common.property.TurmsPropertiesManager;
import im.turms.server.common.property.constant.AdvertiseStrategy;
import im.turms.server.common.property.env.common.AddressProperties;
import lombok.SneakyThrows;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.web.server.Ssl;

import javax.annotation.Nullable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author James Chen
 */
public abstract class BaseServiceAddressManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseServiceAddressManager.class);

    protected final PublicIpManager publicIpManager;

    private final List<Consumer<AddressCollection>> onAddressesChangedListeners = new LinkedList<>();
    // member address
    private AddressProperties memberAddressProperties;
    private final String memberBindHost;
    private String memberHost;

    // admin address
    private AddressProperties adminApiAddressProperties;
    private String metricsApiAddress;
    private String adminApiAddress;

    protected BaseServiceAddressManager(ServerProperties adminApiServerProperties,
                                        PublicIpManager publicIpManager,
                                        TurmsPropertiesManager propertiesManager) {
        this.publicIpManager = publicIpManager;
        TurmsProperties turmsProperties = propertiesManager.getLocalProperties();
        memberBindHost = turmsProperties.getCluster().getConnection().getServer().getHost();
        updateMemberHostIfChanged(turmsProperties);
        updateAdminApiAddresses(adminApiServerProperties, getAdminAddressProperties(turmsProperties));
        updateCustomAddresses(adminApiServerProperties, turmsProperties);
        propertiesManager.addListeners(properties -> {
            AddressProperties newAdminApiDiscoveryProperties = getAdminAddressProperties(properties);
            boolean areAdminApiAddressPropertiesChange = !adminApiAddressProperties
                    .equals(newAdminApiDiscoveryProperties);
            boolean isMemberHostChanged = updateMemberHostIfChanged(properties);
            if (areAdminApiAddressPropertiesChange) {
                try {
                    updateAdminApiAddresses(adminApiServerProperties, newAdminApiDiscoveryProperties);
                } catch (Exception e) {
                    LOGGER.error("Failed to update admin API addresses", e);
                }
            }
            boolean areCustomAddressesChanged = false;
            try {
                areCustomAddressesChanged = updateCustomAddresses(adminApiServerProperties, turmsProperties);
            } catch (Exception e) {
                LOGGER.error("Failed to update custom addresses", e);
            }
            if (areAdminApiAddressPropertiesChange || isMemberHostChanged || areCustomAddressesChanged) {
                AddressCollection addresses = new AddressCollection(getMemberHost(),
                        metricsApiAddress,
                        adminApiAddress,
                        getWsAddress(),
                        getTcpAddress(),
                        getUdpAddress());
                triggerOnAddressesChangedListeners(addresses);
            }
        });
    }

    public String getMemberHost() {
        return memberHost;
    }

    @Nullable
    public String getMetricsApiAddress() {
        return metricsApiAddress;
    }

    @Nullable
    public String getAdminApiAddress() {
        return adminApiAddress;
    }

    @Nullable
    public String getWsAddress() {
        return null;
    }

    @Nullable
    public String getTcpAddress() {
        return null;
    }

    @Nullable
    public String getUdpAddress() {
        return null;
    }

    // Listeners

    public void addOnAddressesChangedListener(Consumer<AddressCollection> listener) {
        onAddressesChangedListeners.add(listener);
    }

    private void triggerOnAddressesChangedListeners(AddressCollection addresses) {
        for (Consumer<AddressCollection> listener : onAddressesChangedListeners) {
            try {
                listener.accept(addresses);
            } catch (Exception e) {
                LOGGER.error("Failed to run onAddressesChangedListener", e);
            }
        }
    }

    // Get and Update Addresses

    protected abstract AddressProperties getAdminAddressProperties(TurmsProperties properties);

    protected boolean updateCustomAddresses(ServerProperties adminApiServerProperties,
                                            TurmsProperties properties) {
        return false;
    }

    @SneakyThrows
    private void updateAdminApiAddresses(ServerProperties adminApiServerProperties,
                                         AddressProperties newAdminApiAddressProperties) {

        InetAddress address = adminApiServerProperties.getAddress();
        Integer port = adminApiServerProperties.getPort();
        if (address == null) {
            throw new IllegalStateException("The bind host isn't specified");
        }
        if (port == null || port <= 0) {
            throw new UnknownHostException("Invalid service port: " + port);
        }
        Ssl ssl = adminApiServerProperties.getSsl();
        boolean isSslEnabled = ssl != null && ssl.isEnabled();
        AddressCollector collector = getAddressCollector(newAdminApiAddressProperties, address.getHostAddress(), port, isSslEnabled);
        metricsApiAddress = collector.getHttpAddress() + "/actuator";
        adminApiAddress = collector.getHttpAddress();
        adminApiAddressProperties = newAdminApiAddressProperties;
    }

    private boolean updateMemberHostIfChanged(TurmsProperties newProperties) {
        AddressProperties newAddressProperties = newProperties.getCluster().getDiscovery().getAddress();
        boolean isAddressPropertiesChanged = !newAddressProperties.equals(memberAddressProperties);
        if (isAddressPropertiesChanged) {
            try {
                memberHost = getAddressCollector(newAddressProperties, memberBindHost, null, null)
                        .getHost();
                memberAddressProperties = newAddressProperties;
                return true;
            } catch (Exception e) {
                LOGGER.error("Failed to update the member host", e);
            }
        }
        return false;
    }

    @SneakyThrows
    private AddressCollector getAddressCollector(AddressProperties addressProperties,
                                                 String host,
                                                 Integer port,
                                                 Boolean isSslEnabled) {
        AdvertiseStrategy advertiseStrategy = addressProperties.getAdvertiseStrategy();
        String advertiseHost = addressProperties.getAdvertiseHost();
        boolean attachPortToHost = addressProperties.isAttachPortToHost();
        return new AddressCollector(host, advertiseHost, port, isSslEnabled, attachPortToHost, advertiseStrategy, publicIpManager);
    }

}