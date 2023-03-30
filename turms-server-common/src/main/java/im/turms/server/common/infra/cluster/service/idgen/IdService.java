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

package im.turms.server.common.infra.cluster.service.idgen;

import java.util.TreeSet;

import im.turms.server.common.infra.cluster.service.ClusterService;
import im.turms.server.common.infra.cluster.service.config.entity.discovery.Member;
import im.turms.server.common.infra.cluster.service.discovery.DiscoveryService;
import im.turms.server.common.infra.lang.ClassUtil;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;

/**
 * @author James Chen
 */
public class IdService implements ClusterService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IdService.class);

    private static final int FLAKE_ID_GENERATORS_LENGTH =
            ClassUtil.getSharedEnumConstants(ServiceType.class).length;

    private final DiscoveryService discoveryService;

    /**
     * Use an array to mitigate unnecessary thread contention.
     */
    private final SnowflakeIdGenerator[] idGenerators =
            new SnowflakeIdGenerator[FLAKE_ID_GENERATORS_LENGTH];
    private int previousLocalDataCenterId;
    private int previousLocalWorkerId;

    public IdService(DiscoveryService discoveryService) {
        this.discoveryService = discoveryService;
        for (int i = 0; i < FLAKE_ID_GENERATORS_LENGTH; i++) {
            idGenerators[i] = new SnowflakeIdGenerator(0, 0);
        }
        discoveryService.addOnMembersChangeListener(() -> {
            int dataCenterId = findNewDataCenterId();
            int localWorkerId = findNewWorkerId();
            if (previousLocalDataCenterId != dataCenterId
                    || localWorkerId != previousLocalWorkerId) {
                for (SnowflakeIdGenerator idGenerator : idGenerators) {
                    idGenerator.updateNodeInfo(dataCenterId, localWorkerId);
                }
                previousLocalDataCenterId = dataCenterId;
                previousLocalWorkerId = localWorkerId;
            }
        });
    }

    /**
     * Note: It is unnecessary to check if the ID is 0L because it should never happen due to its
     * implementation
     */
    public long nextIncreasingId(ServiceType serviceType) {
        return idGenerators[serviceType.ordinal()].nextIncreasingId();
    }

    public long nextLargeGapId(ServiceType serviceType) {
        return idGenerators[serviceType.ordinal()].nextLargeGapId();
    }

    private int findNewDataCenterId() {
        TreeSet<String> zones = new TreeSet<>();
        for (Member member : discoveryService.getAllKnownMembers()
                .values()) {
            zones.add(member.getZone());
        }
        int dataCenterId = zones.headSet(discoveryService.getLocalMember()
                .getZone())
                .size();
        if (dataCenterId >= SnowflakeIdGenerator.MAX_DATA_CENTER_ID) {
            int fallbackDataCenterId = dataCenterId % SnowflakeIdGenerator.MAX_DATA_CENTER_ID;
            LOGGER.warn("The data center ID ({}) is larger than {}, so the ID falls back to ({})."
                    + " It runs the risk of generating same IDs in the cluster",
                    dataCenterId,
                    SnowflakeIdGenerator.MAX_DATA_CENTER_ID - 1,
                    fallbackDataCenterId);
            dataCenterId = fallbackDataCenterId;
        }
        return dataCenterId;
    }

    private int findNewWorkerId() {
        Integer localWorkerId = discoveryService.getLocalServiceMemberIndex();
        if (localWorkerId == null) {
            // Use the previous worker ID instead of 0
            // to decrease chance of collision
            return previousLocalWorkerId;
        }
        if (localWorkerId >= SnowflakeIdGenerator.MAX_WORKER_ID) {
            int fallbackWorkerId = localWorkerId % SnowflakeIdGenerator.MAX_WORKER_ID;
            LOGGER.warn("The node ID ({}) is larger than {}, so the ID falls back to ({})."
                    + " It runs the risk of generating same IDs in the cluster",
                    localWorkerId,
                    SnowflakeIdGenerator.MAX_WORKER_ID - 1,
                    fallbackWorkerId);
            return fallbackWorkerId;
        }
        return localWorkerId;
    }

}