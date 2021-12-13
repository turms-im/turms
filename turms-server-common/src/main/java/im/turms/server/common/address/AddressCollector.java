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

import im.turms.server.common.property.constant.AdvertiseStrategy;
import lombok.Getter;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.time.Duration;

/**
 * @author James Chen
 */
public class AddressCollector {

    private static final Duration IP_DETECTION_TIMEOUT = Duration.ofSeconds(15);

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
            @Nullable Boolean isSslEnabled,
            boolean attachPortToIp,
            @NotNull AdvertiseStrategy advertiseStrategy,
            @NotNull PublicIpManager publicIpManager) throws UnknownHostException {
        this.bindHost = bindHost;
        this.advertiseHost = advertiseHost;
        this.port = port;
        this.isSslEnabled = isSslEnabled != null && isSslEnabled;
        this.attachPortToIp = attachPortToIp;
        this.advertiseStrategy = advertiseStrategy;
        this.publicIpManager = publicIpManager;
        addressGroup = queryAddressGroup();
    }

    private String attachPortToHost(String host) {
        return "%s:%d".formatted(host, port);
    }

    private String queryHost() throws UnknownHostException {
        return switch (advertiseStrategy) {
            case ADVERTISE_ADDRESS -> advertiseHost;
            case BIND_ADDRESS -> bindHost;
            case LOCAL_ADDRESS -> {
                try (DatagramSocket socket = new DatagramSocket()) {
                    socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
                    yield socket.getLocalAddress().getHostAddress();
                } catch (SocketException e) {
                    throw new IllegalStateException(e);
                }
            }
            case PUBLIC_ADDRESS -> publicIpManager.getPublicIp().block(IP_DETECTION_TIMEOUT);
            default -> throw new IllegalArgumentException("Unexpected value: " + advertiseStrategy.name());
        };
    }

    private AddressGroup queryAddressGroup() throws UnknownHostException {
        String host = queryHost();
        if (host != null) {
            String address = attachPortToIp ? attachPortToHost(host) : host;
            String httpProtocol = isSslEnabled ? "https" : "http";
            String wsProtocol = isSslEnabled ? "wss" : "ws";
            return new AddressGroup(
                    host,
                    address,
                    "%s://%s".formatted(httpProtocol, address),
                    "%s://%s".formatted(wsProtocol, address));
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

    public record AddressGroup(
            String host,
            String address,
            String httpAddress,
            String wsAddress
    ) {
    }

}