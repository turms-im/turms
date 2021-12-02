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

import im.turms.server.common.property.TurmsProperties;
import im.turms.server.common.property.constant.AdvertiseStrategy;
import im.turms.server.common.property.env.common.AddressProperties;
import lombok.extern.log4j.Log4j2;
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
@Log4j2
public abstract class BaseServiceAddressManager {

    protected final PublicIpManager publicIpManager;

    private final List<Consumer<AddressCollection>> onAddressesChangedListeners = new LinkedList<>();
    private final String memberBindHost;
    private AddressProperties memberAddressProperties;
    private String memberHost;

    protected BaseServiceAddressManager(PublicIpManager publicIpManager, TurmsProperties propertiesForMemberBindHost) {
        this.publicIpManager = publicIpManager;
        memberBindHost = propertiesForMemberBindHost.getCluster().getConnection().getServer().getHost();
        updateMemberHostIfChanged(propertiesForMemberBindHost);
    }

    public void addOnAddressesChangedListener(Consumer<AddressCollection> listener) {
        onAddressesChangedListeners.add(listener);
    }

    public String getMemberHost() {
        return memberHost;
    }

    @Nullable
    public String getMetricsApiAddress() {
        return null;
    }

    @Nullable
    public String getAdminApiAddress() {
        return null;
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

    protected boolean updateMemberHostIfChanged(TurmsProperties newProperties) {
        AddressProperties newAddressProperties = newProperties.getCluster().getDiscovery().getAddress();
        boolean isAddressPropertiesChanged = !newAddressProperties.equals(memberAddressProperties);
        if (isAddressPropertiesChanged) {
            try {
                memberHost = getAddressCollector(newAddressProperties, memberBindHost, null, null).getHost();
                memberAddressProperties = newAddressProperties;
                return true;
            } catch (UnknownHostException e) {
                log.error(e);
            }
        }
        return false;
    }

    protected AddressCollector getAddressCollector(AddressProperties addressProperties, ServerProperties serverProperties)
            throws UnknownHostException {
        InetAddress address = serverProperties.getAddress();
        Integer port = serverProperties.getPort();
        if (address == null) {
            throw new IllegalStateException("The bind host isn't specified");
        }
        if (port == null || port <= 0) {
            throw new UnknownHostException("Invalid service port: " + port);
        }
        Ssl ssl = serverProperties.getSsl();
        boolean isSslEnabled = ssl != null && ssl.isEnabled();
        return getAddressCollector(addressProperties, address.getHostAddress(), port, isSslEnabled);
    }

    protected AddressCollector getAddressCollector(AddressProperties addressProperties,
                                                   String host,
                                                   Integer port,
                                                   Boolean isSslEnabled) throws UnknownHostException {
        AdvertiseStrategy advertiseStrategy = addressProperties.getAdvertiseStrategy();
        String advertiseHost = addressProperties.getAdvertiseHost();
        boolean attachPortToHost = addressProperties.isAttachPortToHost();
        return new AddressCollector(host, advertiseHost, port, isSslEnabled, attachPortToHost, advertiseStrategy, publicIpManager);
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