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
import im.turms.server.common.util.FutureUtil;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.UnknownHostException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author James Chen
 */
@Component
public class PublicIpManager {

    private static final int IP_DETECTION_TIMEOUT_IN_SECONDS = 15;

    private final TurmsPropertiesManager turmsPropertiesManager;

    private HttpClient client;
    private String publicIp;

    public PublicIpManager(TurmsPropertiesManager turmsPropertiesManager) {
        this.turmsPropertiesManager = turmsPropertiesManager;
    }

    public String getPublicIp() throws InterruptedException, ExecutionException, TimeoutException, UnknownHostException {
        if (publicIp == null) {
            publicIp = queryPublicIp();
        }
        return publicIp;
    }

    public synchronized String queryPublicIp() throws InterruptedException, ExecutionException, TimeoutException, UnknownHostException {
        List<String> ipDetectorAddresses = turmsPropertiesManager.getLocalProperties().getIp().getPublicIpDetectorAddresses();
        if (!ipDetectorAddresses.isEmpty()) {
            List<CompletableFuture<HttpResponse<String>>> futures = new ArrayList<>(ipDetectorAddresses.size());
            if (client == null) {
                client = HttpClient.newHttpClient();
            }
            for (String checkerAddress : ipDetectorAddresses) {
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(checkerAddress))
                        .build();
                CompletableFuture<HttpResponse<String>> future = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
                futures.add(future);
            }
            String ip = FutureUtil.race(futures).get(IP_DETECTION_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS).body();
            if (InetAddresses.isInetAddress(ip)) {
                return ip;
            } else {
                throw new UnknownHostException("The IP is an illegal addresses is empty");
            }
        } else {
            throw new IllegalStateException("The IP detector addresses is empty");
        }
    }

}
