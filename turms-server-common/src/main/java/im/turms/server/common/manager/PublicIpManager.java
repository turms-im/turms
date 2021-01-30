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

package im.turms.server.common.manager;

import com.google.common.net.InetAddresses;
import im.turms.server.common.property.TurmsPropertiesManager;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.util.ArrayList;
import java.util.List;

/**
 * @author James Chen
 * @implNote Don't cache the public IP because it may change over time.
 */
@Component
public class PublicIpManager {

    private final TurmsPropertiesManager turmsPropertiesManager;
    private HttpClient client;

    public PublicIpManager(TurmsPropertiesManager turmsPropertiesManager) {
        this.turmsPropertiesManager = turmsPropertiesManager;
    }

    public Mono<String> getPublicIp() {
        List<String> ipDetectorAddresses = turmsPropertiesManager.getLocalProperties().getIp().getPublicIpDetectorAddresses();
        if (ipDetectorAddresses.isEmpty()) {
            throw new IllegalStateException("Failed to detect the public IP because cannot find an IP detector address");
        }
        List<Mono<String>> monos = new ArrayList<>(ipDetectorAddresses.size());
        for (String checkerAddress : ipDetectorAddresses) {
            Mono<String> ipMono = getClient().get()
                    .uri(checkerAddress)
                    .responseContent()
                    .aggregate()
                    .asString();
            monos.add(ipMono);
        }
        return Mono.firstWithValue(monos)
                .map(ip -> {
                    if (InetAddresses.isInetAddress(ip)) {
                        return ip;
                    } else {
                        throw new IllegalStateException("The IP is invalid: " + ip);
                    }
                });
    }

    private synchronized HttpClient getClient() {
        if (client == null) {
            client = HttpClient.create();
        }
        return client;
    }

}