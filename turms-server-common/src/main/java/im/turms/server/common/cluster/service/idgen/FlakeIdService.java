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

package im.turms.server.common.cluster.service.idgen;

import im.turms.server.common.cluster.service.ClusterService;
import im.turms.server.common.cluster.service.discovery.DiscoveryService;

/**
 * @author James Chen
 */
public class FlakeIdService implements ClusterService {

    private static final int FLAKE_ID_GENERATORS_LENGTH = ServiceType.values().length;

    /**
     * Use an array to mitigate the unnecessary thread contention.
     */
    private final FlakeIdGenerator[] flakeIdGenerators = new FlakeIdGenerator[FLAKE_ID_GENERATORS_LENGTH];
    private int previousLocalMemberIndex;

    public FlakeIdService(DiscoveryService discoveryService) {
        for (int i = 0; i < FLAKE_ID_GENERATORS_LENGTH; i++) {
            // Reserve the dataCenterId value for future use.
            flakeIdGenerators[i] = new FlakeIdGenerator(0, 0);
        }
        // Listen to the member changes to get the local member index
        // as the memberId of the snowflake algorithm
        discoveryService.addListenerOnMembersChange(() -> {
            Integer localMemberIndex = discoveryService.getLocalServiceMemberIndex();
            if (localMemberIndex != null && localMemberIndex != previousLocalMemberIndex) {
                for (FlakeIdGenerator idGenerator : flakeIdGenerators) {
                    idGenerator.updateNodeInfo(0, localMemberIndex);
                }
                previousLocalMemberIndex = localMemberIndex;
            }
        });
    }

    /**
     * Note: It's unnecessary to check if the ID is 0L because it should never happen due to its mechanism
     */
    public long nextId(ServiceType serviceType) {
        return flakeIdGenerators[serviceType.ordinal()].getFlakeId();
    }

}