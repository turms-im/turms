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

package im.turms.server.common.cluster.service.config.domain.discovery;

import com.fasterxml.jackson.annotation.JsonIgnore;
import im.turms.server.common.cluster.node.NodeType;
import im.turms.server.common.cluster.node.NodeVersion;
import im.turms.server.common.mongo.entity.annotation.Document;
import im.turms.server.common.mongo.entity.annotation.Id;
import im.turms.server.common.mongo.entity.annotation.Indexed;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.PersistenceConstructor;

import java.util.Date;

/**
 * @author James Chen
 */
@Document
@Data
@FieldNameConstants
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public final class Member {

    public static final String ID_CLUSTER_ID = "_id.clusterId";
    public static final String ID_NODE_ID = "_id.nodeId";

    @Id
    @EqualsAndHashCode.Include
    private final Key key;

    private final NodeVersion nodeVersion;

    private final NodeType nodeType;

    /**
     * If it's a seed member, we won't remove the member record even if the heartbeat has timed out.
     * Otherwise, the record will be removed automatically.
     */
    private boolean isSeed;

    private boolean isLeaderEligible;

    private final Date registrationDate;

    private final int priority;

    /**
     * Note that the TTL index isn't the heartbeat timeout but is only used to make sure
     * the record can be removed automatically even if the turms server crashes
     */
    @Indexed(partialFilter = "{" + Fields.isSeed + ":{$eq:false}}", expireAfterSeconds = 60)
    private volatile Date lastHeartbeatDate;

    private String memberHost;
    private final int memberPort;

    private String metricsApiAddress;
    private String adminApiAddress;
    private String wsAddress;
    private String tcpAddress;
    private String udpAddress;

    private boolean hasJoinedCluster;

    private boolean isActive;

    @PersistenceConstructor
    public Member(
            Key key,
            NodeType nodeType,
            NodeVersion nodeVersion,
            boolean isSeed,
            boolean isLeaderEligible,
            Date registrationDate,
            int priority,
            Date lastHeartbeatDate,
            String memberHost,
            int memberPort,
            String metricsApiAddress,
            String adminApiAddress,
            String wsAddress,
            String tcpAddress,
            String udpAddress,
            boolean hasJoinedCluster,
            boolean isActive) {
        this.key = key;
        this.nodeType = nodeType;
        this.nodeVersion = nodeVersion;
        this.isSeed = isSeed;
        this.isLeaderEligible = isLeaderEligible;
        this.registrationDate = registrationDate;
        this.priority = priority;
        this.lastHeartbeatDate = lastHeartbeatDate;
        this.memberHost = memberHost;
        this.memberPort = memberPort;
        this.metricsApiAddress = metricsApiAddress;
        this.adminApiAddress = adminApiAddress;
        this.wsAddress = wsAddress;
        this.tcpAddress = tcpAddress;
        this.udpAddress = udpAddress;
        this.hasJoinedCluster = hasJoinedCluster;
        this.isActive = isActive;
    }

    public Member(
            String clusterId,
            String nodeId,
            NodeType nodeType,
            NodeVersion nodeVersion,
            boolean isSeed,
            boolean isLeaderEligible,
            Date registrationDate,
            int priority,
            String memberHost,
            int memberPort,
            String metricsApiAddress,
            String adminApiAddress,
            String wsAddress,
            String tcpAddress,
            String udpAddress,
            boolean hasJoinedCluster,
            boolean isActive) {
        this(new Key(clusterId, nodeId),
                nodeType,
                nodeVersion,
                isSeed,
                isLeaderEligible,
                registrationDate,
                priority,
                registrationDate,
                memberHost,
                memberPort,
                metricsApiAddress,
                adminApiAddress,
                wsAddress,
                tcpAddress,
                udpAddress,
                hasJoinedCluster,
                isActive);
    }

    public void updateIfNotNull(
            Boolean isSeed,
            Boolean isLeaderEligible,
            Boolean hasJoinedCluster,
            Boolean isActive,
            Date lastHeartbeatDate,
            String memberHost,
            String metricsApiAddress,
            String adminApiAddress,
            String wsAddress,
            String tcpAddress,
            String udpAddress) {
        if (isSeed != null) {
            this.isSeed = isSeed;
        }
        if (isLeaderEligible != null) {
            this.isLeaderEligible = isLeaderEligible;
        }
        if (hasJoinedCluster != null) {
            this.hasJoinedCluster = hasJoinedCluster;
        }
        if (isActive != null) {
            this.isActive = isActive;
        }
        if (lastHeartbeatDate != null) {
            this.lastHeartbeatDate = lastHeartbeatDate;
        }
        if (memberHost != null) {
            this.memberHost = memberHost;
        }
        if (metricsApiAddress != null) {
            this.metricsApiAddress = metricsApiAddress;
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

}