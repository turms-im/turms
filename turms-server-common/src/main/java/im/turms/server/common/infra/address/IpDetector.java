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

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import jakarta.annotation.Nullable;

import io.netty.handler.codec.http.HttpStatusClass;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import im.turms.server.common.infra.net.InetAddressUtil;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.property.env.common.IpProperties;
import im.turms.server.common.infra.time.DateTimeUtil;

/**
 * @author James Chen
 * @implNote Don't cache the IP forever because they may change over time.
 */
@Component
public class IpDetector {

    private static final Mono<String> EXCEPTION_NO_AVAILABLE_ADDRESS_FOUND =
            Mono.error(new NoAvailableAddressFoundException(
                    "Failed to detect the public IP of the local node because there is no available IP"));

    private final TurmsPropertiesManager propertiesManager;
    @Nullable
    private String cachedPrivateIp;
    private long privateIpLastUpdatedTimeNanos;
    @Nullable
    private String cachedPublicIp;
    private long publicIpLastUpdatedTimeNanos;

    public IpDetector(TurmsPropertiesManager propertiesManager) {
        this.propertiesManager = propertiesManager;
    }

    public String queryPrivateIp() {
        IpProperties ipProperties = propertiesManager.getLocalProperties()
                .getIp();
        int cachedPrivateIpExpireAfterMillis = ipProperties.getCachedPrivateIpExpireAfterMillis();
        String localCachedPrivateIp = cachedPrivateIp;
        if (cachedPrivateIpExpireAfterMillis > 0
                && localCachedPrivateIp != null
                && System.nanoTime() - privateIpLastUpdatedTimeNanos < DateTimeUtil
                        .millisToNanos(cachedPrivateIpExpireAfterMillis)) {
            return localCachedPrivateIp;
        }
        DatagramChannel channel = null;
        try {
            channel = DatagramChannel.open();
            InetSocketAddress address = new InetSocketAddress("8.8.8.8", 10002);
            channel.connect(address);
            InetSocketAddress localAddress = (InetSocketAddress) channel.getLocalAddress();
            InetAddress inetAddress = localAddress.getAddress();
            String ip = inetAddress.getHostAddress();
            if (!inetAddress.isSiteLocalAddress()) {
                throw new NoAvailableAddressFoundException(
                        "The IP address ("
                                + ip
                                + ") is not a site local IP address");
            }
            privateIpLastUpdatedTimeNanos = System.nanoTime();
            cachedPrivateIp = ip;
            return ip;
        } catch (Exception e) {
            throw new NoAvailableAddressFoundException("Failed to detect the local IP", e);
        } finally {
            try {
                if (channel != null) {
                    channel.close();
                }
            } catch (IOException ignored) {
            }
        }
    }

    public Mono<String> queryPublicIp() {
        IpProperties ipProperties = propertiesManager.getLocalProperties()
                .getIp();
        int cachedPublicIpExpireAfterMillis = ipProperties.getCachedPublicIpExpireAfterMillis();
        String localCachedPublicIp = cachedPublicIp;
        if (cachedPublicIpExpireAfterMillis > 0
                && localCachedPublicIp != null
                && System.nanoTime() - publicIpLastUpdatedTimeNanos < DateTimeUtil
                        .millisToNanos(cachedPublicIpExpireAfterMillis)) {
            return Mono.just(localCachedPublicIp);
        }
        List<String> ipDetectorAddresses = ipProperties.getPublicIpDetectorAddresses();
        if (ipDetectorAddresses.isEmpty()) {
            return Mono.error(new IllegalArgumentException(
                    "Failed to detect the public IP of the local node because no IP detector address is specified"));
        }
        List<Mono<String>> monos = new ArrayList<>(ipDetectorAddresses.size());
        HttpClient httpClient = HttpClient.newConnection();
        for (String ipDetectorAddress : ipDetectorAddresses) {
            Mono<String> ipMono = httpClient.get()
                    .uri(ipDetectorAddress)
                    .responseSingle((response, body) -> response.status()
                            .codeClass()
                            .equals(HttpStatusClass.SUCCESS)
                                    ? body.asString()
                                            .flatMap(ip -> {
                                                ip = ip.trim();
                                                return InetAddressUtil.isIp(ip)
                                                        ? Mono.just(ip)
                                                        : Mono.empty();
                                            })
                                    : Mono.empty());
            monos.add(ipMono);
        }
        return Mono.firstWithValue(monos)
                .doOnNext(ip -> {
                    publicIpLastUpdatedTimeNanos = System.nanoTime();
                    cachedPublicIp = ip;
                })
                .switchIfEmpty(EXCEPTION_NO_AVAILABLE_ADDRESS_FOUND)
                .onErrorResume(t -> {
                    if (t instanceof NoSuchElementException) {
                        return EXCEPTION_NO_AVAILABLE_ADDRESS_FOUND;
                    }
                    return Mono.error(new RuntimeException(
                            "Failed to detect the public IP of the local node",
                            t));
                });
    }

}