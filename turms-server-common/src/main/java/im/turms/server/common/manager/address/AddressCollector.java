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

import com.google.common.net.InetAddresses;
import im.turms.server.common.manager.PublicIpManager;
import im.turms.server.common.property.constant.AdvertiseStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * @author James Chen
 */
@Log4j2
public class AddressCollector {

    private final AddressGroup addressGroup;
    private final String bindHost;
    private final String advertiseHost;
    @Getter
    private final Integer port;
    @Getter
    private final boolean isSslEnabled;
    private final boolean attachPortToIp;
    private final AdvertiseStrategy advertiseStrategy;
    private final PublicIpManager publicIpManager;

    public AddressCollector(
            @Nullable String bindHost,
            @Nullable String advertiseHost,
            @Nullable Integer port,
            boolean isSslEnabled,
            boolean attachPortToIp,
            @NotNull AdvertiseStrategy advertiseStrategy,
            @NotNull PublicIpManager publicIpManager) throws UnknownHostException, InterruptedException, ExecutionException, TimeoutException {
        this.bindHost = bindHost;
        this.advertiseHost = advertiseHost;
        this.port = port;
        this.isSslEnabled = isSslEnabled;
        this.attachPortToIp = attachPortToIp;
        this.advertiseStrategy = advertiseStrategy;
        this.publicIpManager = publicIpManager;
        addressGroup = queryAddressGroup();
    }

    private String attachPortToIp(String ip) {
        return String.format("%s:%d", ip, port);
    }

    private String queryHost() throws UnknownHostException, InterruptedException, ExecutionException, TimeoutException {
        String host;
        switch (advertiseStrategy) {
            case ADVERTISE_ADDRESS:
                host = advertiseHost;
                break;
            case BIND_ADDRESS:
                host = bindHost;
                break;
            case LOCAL_ADDRESS:
                host = InetAddress.getLocalHost().getHostAddress();
                break;
            case PUBLIC_ADDRESS:
                host = publicIpManager.getPublicIp();
                break;
            default:
                throw new IllegalArgumentException("Unexpected value: " + advertiseStrategy.name());
        }
        if (InetAddresses.isInetAddress(host)) {
            return host;
        } else {
            String message = String.format("The host %s is an illegal host according to strategy %s", host, advertiseStrategy.name());
            throw new UnknownHostException(message);
        }
    }

    private AddressGroup queryAddressGroup() throws UnknownHostException, InterruptedException, ExecutionException, TimeoutException {
        String host = queryHost();
        if (host != null) {
            String httpProtocol = isSslEnabled ? "https" : "http";
            String wsProtocol = isSslEnabled ? "wss" : "ws";
            return new AddressGroup(
                    host,
                    attachPortToIp ? attachPortToIp(host) : host,
                    String.format("%s://%s", httpProtocol, host),
                    String.format("%s://%s", wsProtocol, host));
        } else {
            throw new UnknownHostException("The address of the current server cannot be found");
        }
    }

    public String getHost() {
        return addressGroup.host;
    }

    public String getAddress() {
        return addressGroup.address;
    }

    public String getHttpAddress() {
        return addressGroup.httpAddress;
    }

    public String getWsAddress() {
        return addressGroup.wsAddress;
    }

    @AllArgsConstructor
    @EqualsAndHashCode
    @Data
    public static final class AddressGroup {
        private final String host;
        private final String address;
        private final String httpAddress;
        private final String wsAddress;
    }

}
