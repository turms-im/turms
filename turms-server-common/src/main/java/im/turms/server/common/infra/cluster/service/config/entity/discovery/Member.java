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

package im.turms.server.common.infra.cluster.service.config.entity.discovery;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldNameConstants;

import im.turms.server.common.infra.cluster.node.NodeType;
import im.turms.server.common.infra.cluster.node.NodeVersion;
import im.turms.server.common.storage.mongo.entity.annotation.Document;
import im.turms.server.common.storage.mongo.entity.annotation.Id;
import im.turms.server.common.storage.mongo.entity.annotation.Indexed;
import im.turms.server.common.storage.mongo.entity.annotation.PersistenceConstructor;

/**
 * @author James Chen
 */
@Data
@Document
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@FieldNameConstants
public final class Member {

    public static final String ID_CLUSTER_ID = "_id."
            + Key.Fields.clusterId;
    public static final String ID_NODE_ID = "_id."
            + Key.Fields.nodeId;
    public static final String STATUS_HAS_JOINED_CLUSTER = Fields.status
            + "."
            + MemberStatus.Fields.hasJoinedCluster;
    public static final String STATUS_IS_ACTIVE = Fields.status
            + "."
            + MemberStatus.Fields.isActive;
    public static final String STATUS_IS_HEALTHY = Fields.status
            + "."
            + MemberStatus.Fields.isHealthy;
    public static final String STATUS_LAST_HEARTBEAT_DATE = Fields.status
            + "."
            + MemberStatus.Fields.lastHeartbeatDate;

    @Id
    @EqualsAndHashCode.Include
    private final Key key;

    private String zone;

    private final NodeVersion nodeVersion;

    private final NodeType nodeType;

    /**
     * For a seed member, it won't be removed from the config server when the TTL (60s) expires (TTL
     * should be always longer than the heartbeat timeout). Otherwise, the record will be removed
     * automatically when the TTL expires.
     */
    private boolean isSeed;

    private final Date registrationDate;

    /**
     * Only active leader-eligible turms-service server can be a leader
     */
    private boolean isLeaderEligible;

    /**
     * The priority to be a leader. 1. When there is no leader, a qualified (active,
     * leader-eligible, and nodeType=SERVICE) member with the highest priority will be selected as a
     * leader. 2. When there is a leader, even if a new qualified member with a higher priority
     * joins, the new member will NOT be elected as a new leader immediately until the existing
     * leader dies, or a developer triggers a new leader election manually via admin API.
     */
    private int priority;

    private String memberHost;
    private final int memberPort;

    private String adminApiAddress;
    private String wsAddress;
    private String tcpAddress;
    private String udpAddress;

    private final MemberStatus status;

    @PersistenceConstructor
    public Member(
            Key key,
            String zone,
            NodeType nodeType,
            NodeVersion nodeVersion,
            boolean isSeed,
            boolean isLeaderEligible,
            Date registrationDate,
            int priority,
            String memberHost,
            int memberPort,
            String adminApiAddress,
            String wsAddress,
            String tcpAddress,
            String udpAddress,
            MemberStatus status) {
        this.key = key;
        this.zone = zone;
        this.nodeType = nodeType;
        this.nodeVersion = nodeVersion;
        this.isSeed = isSeed;
        this.isLeaderEligible = isLeaderEligible;
        this.registrationDate = registrationDate;
        this.priority = priority;
        this.memberHost = memberHost;
        this.memberPort = memberPort;
        this.adminApiAddress = adminApiAddress;
        this.wsAddress = wsAddress;
        this.tcpAddress = tcpAddress;
        this.udpAddress = udpAddress;
        this.status = status;
    }

    public Member(
            String clusterId,
            String nodeId,
            String zone,
            NodeType nodeType,
            NodeVersion nodeVersion,
            boolean isSeed,
            boolean isLeaderEligible,
            Date registrationDate,
            int priority,
            String memberHost,
            int memberPort,
            String adminApiAddress,
            String wsAddress,
            String tcpAddress,
            String udpAddress,
            boolean hasJoinedCluster,
            boolean isActive,
            boolean isHealthy) {
        this(new Key(clusterId, nodeId),
                zone,
                nodeType,
                nodeVersion,
                isSeed,
                isLeaderEligible,
                registrationDate,
                priority,
                memberHost,
                memberPort,
                adminApiAddress,
                wsAddress,
                tcpAddress,
                udpAddress,
                new MemberStatus(hasJoinedCluster, isHealthy, isActive, new Date()));
    }

    public void updateIfNotNull(
            String zone,
            Boolean isSeed,
            Boolean isLeaderEligible,
            Integer priority,
            String memberHost,
            String adminApiAddress,
            String wsAddress,
            String tcpAddress,
            String udpAddress,
            // Status
            Boolean hasJoinedCluster,
            Boolean isActive,
            Boolean isHealthy,
            Date lastHeartbeatDate) {
        if (zone != null) {
            this.zone = zone;
        }
        if (isSeed != null) {
            this.isSeed = isSeed;
        }
        if (isLeaderEligible != null) {
            this.isLeaderEligible = isLeaderEligible;
        }
        if (priority != null) {
            this.priority = priority;
        }
        if (memberHost != null) {
            this.memberHost = memberHost;
        }
        if (adminApiAddress != null) {
            this.adminApiAddress = adminApiAddress;
        }
        if (wsAddress != null) {
            this.wsAddress = wsAddress;
        }
        if (tcpAddress != null) {
            this.tcpAddress = tcpAddress;
        }
        if (udpAddress != null) {
            this.udpAddress = udpAddress;
        }
        // Status
        if (hasJoinedCluster != null) {
            status.setHasJoinedCluster(hasJoinedCluster);
        }
        if (isActive != null) {
            status.setActive(isActive);
        }
        if (isHealthy != null) {
            status.setHealthy(isHealthy);
        }
        if (lastHeartbeatDate != null) {
            status.setLastHeartbeatDate(lastHeartbeatDate);
        }
    }

    @JsonIgnore
    public String getClusterId() {
        return getKey().getClusterId();
    }

    @JsonIgnore
    public String getNodeId() {
        return getKey().getNodeId();
    }

    /**
     * Note that it is also considered as the same note if their addresses are same.
     */
    public boolean isSameNode(Member member) {
        return isSameId(member) || isSameAddress(member);
    }

    public boolean isSameId(Member member) {
        return key.equals(member.getKey());
    }

    public boolean isSameAddress(Member member) {
        return memberHost.equals(member.getMemberHost()) && memberPort == member.getMemberPort();
    }

    @Data
    @FieldNameConstants
    public static class Key {
        private final String clusterId;

        @Indexed
        private final String nodeId;
    }

    @AllArgsConstructor
    @Data
    @FieldNameConstants
    public static class MemberStatus {

        /**
         * True if the last heartbeat has not timed out. hasJoinedCluster only works as an indicator
         * for node status, and it can still handle client requests even if it is false.
         */
        private boolean hasJoinedCluster;

        /**
         * The node will stop serving if false
         */
        private boolean isHealthy;

        /**
         * If a node isn't active, it cannot handle client requests. Developer can update the value
         * via admin API. Useful for blue-green deployment.
         */
        private boolean isActive;

        /**
         * Note that the TTL index isn't the heartbeat timeout but is only used to make sure the
         * record can be removed automatically even if the turms server crashes
         */
        @Indexed(
                partialFilter = "{"
                        + Member.Fields.isSeed
                        + ":{$eq:false}}",
                expireAfterSeconds = 60)
        private volatile Date lastHeartbeatDate;
    }

}