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

package im.turms.server.common.infra.address;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import jakarta.annotation.Nullable;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.function.Tuple3;

import im.turms.server.common.infra.lang.StringUtil;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.property.TurmsProperties;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.property.constant.AdvertiseStrategy;
import im.turms.server.common.infra.property.env.common.AddressProperties;
import im.turms.server.common.infra.property.env.common.adminapi.AdminHttpProperties;
import im.turms.server.common.infra.reactor.PublisherPool;
import im.turms.server.common.infra.time.DurationConst;

/**
 * @author James Chen
 */
public abstract class BaseServiceAddressManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseServiceAddressManager.class);

    private final IpDetector ipDetector;

    private final List<Consumer<NodeAddressInfo>> onNodeAddressInfoChangedListeners =
            new LinkedList<>();
    // member address
    private AddressProperties memberAddressProperties;
    private final String memberBindHost;
    private String memberHost;

    // admin address
    private AddressProperties adminApiAddressProperties;
    private String adminApiAddress;

    protected BaseServiceAddressManager(
            AdminHttpProperties adminHttpProperties,
            IpDetector ipDetector,
            TurmsPropertiesManager propertiesManager) {
        this.ipDetector = ipDetector;
        TurmsProperties turmsProperties = propertiesManager.getLocalProperties();
        memberBindHost = turmsProperties.getCluster()
                .getConnection()
                .getServer()
                .getHost();
        AddressProperties adminAddressProperties = getAdminAddressProperties(turmsProperties);
        Mono.whenDelayError(updateMemberHostIfChanged(turmsProperties),
                updateAdminApiAddresses(adminHttpProperties, adminAddressProperties),
                updateCustomAddresses(adminHttpProperties, turmsProperties))
                .block(DurationConst.ONE_MINUTE);
        propertiesManager.addLocalPropertiesChangeListener(properties -> {
            AddressProperties newAdminApiDiscoveryProperties =
                    getAdminAddressProperties(properties);
            boolean areAdminApiAddressPropertiesChange =
                    !adminApiAddressProperties.equals(newAdminApiDiscoveryProperties);
            Mono<Void> updateAdminApiAddresses = areAdminApiAddressPropertiesChange
                    ? updateAdminApiAddresses(adminHttpProperties, newAdminApiDiscoveryProperties)
                    : Mono.empty();
            Mono<Boolean> updateMemberHost = updateMemberHostIfChanged(properties);
            Mono<Boolean> updateCustomAddresses =
                    updateCustomAddresses(adminHttpProperties, turmsProperties);
            Schedulers.single()
                    .schedule(() -> {
                        Tuple3<Void, Boolean, Boolean> changed;
                        try {
                            changed = Mono
                                    .zipDelayError(updateAdminApiAddresses,
                                            updateMemberHost,
                                            updateCustomAddresses)
                                    .block(DurationConst.ONE_MINUTE);
                        } catch (Exception e) {
                            LOGGER.error(
                                    "Caught an error while updating the node address information",
                                    e);
                            return;
                        }
                        boolean isMemberHostChanged = changed.getT2();
                        boolean areCustomAddressesChanged = changed.getT3();
                        if (areAdminApiAddressPropertiesChange
                                || isMemberHostChanged
                                || areCustomAddressesChanged) {
                            NodeAddressInfo addressInfo = new NodeAddressInfo(
                                    getMemberHost(),
                                    adminApiAddress,
                                    getWsAddress(),
                                    getTcpAddress(),
                                    getUdpAddress());
                            notifyOnNodeAddressInfoChangedListeners(addressInfo);
                        }
                    });
        });
    }

    public String getMemberHost() {
        return memberHost;
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

    public void addOnNodeAddressInfoChangedListener(Consumer<NodeAddressInfo> listener) {
        onNodeAddressInfoChangedListeners.add(listener);
    }

    private void notifyOnNodeAddressInfoChangedListeners(NodeAddressInfo addresses) {
        for (Consumer<NodeAddressInfo> listener : onNodeAddressInfoChangedListeners) {
            try {
                listener.accept(addresses);
            } catch (Exception e) {
                LOGGER.error(
                        "Caught an error while notifying the onNodeAddressInfoChanged listener: "
                                + listener.getClass()
                                        .getName(),
                        e);
            }
        }
    }

    // Get and Update Address Info

    protected abstract AddressProperties getAdminAddressProperties(TurmsProperties properties);

    protected Mono<Boolean> updateCustomAddresses(
            AdminHttpProperties adminHttpProperties,
            TurmsProperties properties) {
        return PublisherPool.FALSE;
    }

    private Mono<Void> updateAdminApiAddresses(
            AdminHttpProperties adminHttpProperties,
            AddressProperties newAdminApiAddressProperties) {
        String bindHost = adminHttpProperties.getHost();
        int port = adminHttpProperties.getPort();
        if (bindHost == null) {
            return Mono.error(new IllegalArgumentException("The bind host is not specified"));
        }
        if (port <= 0) {
            return Mono.error(new IllegalArgumentException(
                    "Invalid service port: "
                            + port));
        }
        return queryHost(newAdminApiAddressProperties.getAdvertiseStrategy(),
                bindHost,
                newAdminApiAddressProperties.getAdvertiseHost())
                .doOnError(t -> LOGGER.error("Failed to update the admin API address", t))
                .flatMap(host -> {
                    adminApiAddress = (adminHttpProperties.getSsl()
                            .isEnabled()
                                    ? "https://"
                                    : "http://")
                            + host + (newAdminApiAddressProperties.isAttachPortToHost()
                                    ? ":"
                                            + port
                                    : "");
                    adminApiAddressProperties = newAdminApiAddressProperties;
                    return Mono.empty();
                });
    }

    private Mono<Boolean> updateMemberHostIfChanged(TurmsProperties newProperties) {
        AddressProperties newAddressProperties = newProperties.getCluster()
                .getDiscovery()
                .getAddress();
        boolean isAddressPropertiesChanged = !newAddressProperties.equals(memberAddressProperties);
        if (!isAddressPropertiesChanged) {
            return PublisherPool.FALSE;
        }
        return queryHost(newAddressProperties.getAdvertiseStrategy(),
                memberBindHost,
                newAddressProperties.getAdvertiseHost())
                .doOnError(t -> LOGGER.error("Failed to update the member host", t))
                .map(host -> {
                    memberHost = host;
                    memberAddressProperties = newAddressProperties;
                    return true;
                });
    }

    protected Mono<String> queryHost(
            AdvertiseStrategy advertiseStrategy,
            String bindHost,
            String advertiseHost) {
        return switch (advertiseStrategy) {
            case ADVERTISE_ADDRESS -> StringUtil.isBlank(advertiseHost)
                    ? Mono.error(
                            new IllegalArgumentException("The advertise host is not specified"))
                    : Mono.just(advertiseHost);
            case BIND_ADDRESS -> StringUtil.isBlank(bindHost)
                    ? Mono.error(new IllegalArgumentException("The bind host is not specified"))
                    : Mono.just(bindHost);
            case PRIVATE_ADDRESS -> {
                try {
                    yield Mono.just(ipDetector.queryPrivateIp());
                } catch (Exception e) {
                    yield Mono.error(e);
                }
            }
            case PUBLIC_ADDRESS -> ipDetector.queryPublicIp();
        };
    }
}