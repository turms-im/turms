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

package im.turms.server.common.cluster.service.discovery;

import im.turms.server.common.cluster.node.NodeType;
import im.turms.server.common.cluster.service.config.SharedConfigService;
import im.turms.server.common.cluster.service.config.domain.discovery.Leader;
import im.turms.server.common.cluster.service.config.domain.discovery.Member;
import im.turms.server.common.mongo.operation.option.Filter;
import im.turms.server.common.mongo.operation.option.Update;
import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DuplicateKeyException;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author James Chen
 */
@Log4j2
public class LocalNodeStatusManager {

    private final DiscoveryService discoveryService;
    private final SharedConfigService sharedConfigService;
    @Getter
    private final Member localMember;
    @Getter
    @Setter
    private volatile boolean isLocalNodeRegistered;
    @Getter
    @Setter
    private volatile boolean isClosing;
    private final long heartbeatTimeoutInMillis;
    private final Duration heartbeatInterval;
    private final long heartbeatIntervalInMillis;
    private ScheduledFuture<?> heartbeatFuture;
    private final ScheduledExecutorService scheduler = new ScheduledThreadPoolExecutor(1, new DefaultThreadFactory("discovery-heartbeat"));

    public LocalNodeStatusManager(
            DiscoveryService discoveryService,
            SharedConfigService sharedConfigService,
            Member localMember,
            int heartbeatTimeoutInSeconds,
            int heartbeatIntervalInSeconds) {
        this.discoveryService = discoveryService;
        this.sharedConfigService = sharedConfigService;
        this.localMember = localMember;
        this.heartbeatTimeoutInMillis = heartbeatTimeoutInSeconds * 1000L;
        this.heartbeatInterval = Duration.ofSeconds(heartbeatIntervalInSeconds);
        this.heartbeatIntervalInMillis = heartbeatInterval.toMillis();
    }

    public Mono<Void> upsertLocalNodeInfo(Update update) {
        String nodeId = localMember.getNodeId();
        Filter memberFilter = Filter.newBuilder()
                .eq(Member.ID_CLUSTER_ID, localMember.getClusterId())
                .eq(Member.ID_NODE_ID, nodeId);
        return sharedConfigService.upsert(memberFilter, update, localMember)
                .doOnSuccess(unused -> isLocalNodeRegistered = true);
    }

    public Mono<Void> registerLocalMember() {
        log.info("Registering the local member");
        return discoveryService.registerMember(localMember)
                .doOnError(e -> log.error("Failed to register the local member", e))
                .doOnSuccess(ignored -> {
                    isLocalNodeRegistered = true;
                    log.info("Registered the local member successfully");
                });
    }

    public Mono<Void> unregisterLocalMember() {
        log.info("Unregistering the local member");
        return discoveryService.unregisterMembers(Set.of(localMember.getNodeId()))
                .then(unregisterLocalMemberLeadership())
                .doOnError(e -> log.error("Failed to unregister the local member", e))
                .doOnSuccess(ignored -> {
                    isLocalNodeRegistered = false;
                    log.info("Unregistered the local member successfully");
                });
    }

    public Mono<Void> tryBecomeLeader() {
        if (localMember.getNodeType() == NodeType.SERVICE && localMember.isLeaderEligible()) {
            String clusterId = localMember.getClusterId();
            Leader localLeader = new Leader(clusterId, localMember.getNodeId(), new Date());
            return sharedConfigService.insert(localLeader)
                    .then()
                    .onErrorResume(DuplicateKeyException.class, t -> Mono.empty());
        } else {
            return Mono.empty();
        }
    }

    private Mono<Void> unregisterLocalMemberLeadership() {
        Member.Key key = new Member.Key(localMember.getClusterId(), localMember.getNodeId());
        Filter query = Filter.newBuilder()
                .eq("_id", key);
        return sharedConfigService.remove(Leader.class, query)
                .doOnSuccess(result -> {
                    if (result.getDeletedCount() > 0) {
                        discoveryService.notifyLeadershipChangeListeners(null);
                    }
                })
                .then();
    }

    public boolean isLocalNodeMaster() {
        Leader leader = discoveryService.getLeader();
        return leader != null
                && leader.getNodeId().equals(localMember.getNodeId())
                && leader.getClusterId().equals(localMember.getClusterId());
    }

    public void startHeartbeat() {
        if (heartbeatFuture == null || heartbeatFuture.isDone()) {
            heartbeatFuture = scheduler.scheduleWithFixedDelay(() -> {
                if (isClosing) {
                    return;
                }
                try {
                    Date now = new Date();
                    Update update = Update.newBuilder()
                            .set(Member.Fields.lastHeartbeatDate, now);
                    List<Mono<?>> monos = new ArrayList<>(3);
                    monos.add(upsertLocalNodeInfo(update));
                    if (isLocalNodeMaster()) {
                        monos.add(updateLocalLeaderHeartbeat(now));
                        monos.add(updateFollowersStatus(now));
                    }
                    Mono.when(monos)
                            .timeout(heartbeatInterval)
                            .doOnSuccess(ignored -> localMember.setLastHeartbeatDate(now))
                            .doOnError(e -> log.error("Failed to send heartbeat request", e))
                            .subscribe();
                } catch (Exception e) {
                    log.error("Failed to send heartbeat request", e);
                }
            }, heartbeatIntervalInMillis, heartbeatIntervalInMillis, TimeUnit.MILLISECONDS);
        }
    }

    public void updateInfo(Member member) {
        boolean isLeaderEligible = member.isLeaderEligible();
        boolean wasLeaderEligible = localMember.isLeaderEligible();
        boolean isLeaderEligibleChanged = isLeaderEligible != wasLeaderEligible;
        this.localMember.updateIfNotNull(
                member.isSeed(),
                member.isLeaderEligible(),
                member.isHasJoinedCluster(),
                member.isActive(),
                member.getLastHeartbeatDate(),
                member.getMemberHost(),
                member.getMetricsApiAddress(),
                member.getAdminApiAddress(),
                member.getWsAddress(),
                member.getTcpAddress(),
                member.getUdpAddress());
        if (isLeaderEligibleChanged) {
            if (isLeaderEligible) {
                tryBecomeLeader().subscribe();
            } else {
                unregisterLocalMemberLeadership().subscribe();
            }
        }
    }

    private Mono<Void> updateLocalLeaderHeartbeat(Date lastHeartbeatDate) {
        Update update = Update.newBuilder()
                .set(Leader.Fields.lastHeartbeatDate, lastHeartbeatDate);
        Filter leaderFilter = Filter.newBuilder()
                .eq("_id", localMember.getClusterId())
                .eq(Leader.Fields.nodeId, localMember.getNodeId());
        Leader leader = discoveryService.getLeader();
        return sharedConfigService.upsert(leaderFilter, update, leader);
    }

    private Mono<Void> updateFollowersStatus(Date lastHeartbeatDate) {
        Collection<Member> knownMembers = discoveryService.getAllKnownMembers().values();
        if (knownMembers.isEmpty()) {
            return Mono.empty();
        }
        int knownMembersSize = knownMembers.size();
        boolean hasOnlyLocalNode = knownMembersSize == 1 && knownMembers.iterator().next().isSameNode(localMember);
        if (hasOnlyLocalNode) {
            return Mono.empty();
        }
        List<String> availableMemberNodeIds = new LinkedList<>();
        int availableMembersSize = 0;
        long lastHeartbeatTime = lastHeartbeatDate.getTime();
        for (Member knownMember : knownMembers) {
            boolean isAvailable = (lastHeartbeatTime - knownMember.getLastHeartbeatDate().getTime()) < heartbeatTimeoutInMillis;
            if (isAvailable) {
                availableMemberNodeIds.add(knownMember.getNodeId());
                availableMembersSize++;
            }
        }
        if (availableMembersSize == 0) {
            return updateFollowersToUnavailable(discoveryService.getAllKnownMembers().keySet());
        } else if (knownMembersSize == availableMembersSize) {
            return updateFollowersToAvailable(availableMemberNodeIds);
        } else {
            Set<String> unavailableMemberIds = new HashSet<>(discoveryService.getAllKnownMembers().keySet());
            for (String availableMemberNodeId : availableMemberNodeIds) {
                unavailableMemberIds.remove(availableMemberNodeId);
            }
            return Mono.when(
                    updateFollowersToAvailable(availableMemberNodeIds),
                    updateFollowersToUnavailable(unavailableMemberIds));
        }
    }

    private Mono<Void> updateFollowersToUnavailable(Collection<String> unavailableMemberNodeIds) {
        Filter filter = Filter.newBuilder()
                .in(Member.ID_NODE_ID, unavailableMemberNodeIds)
                .eq(Member.ID_CLUSTER_ID, localMember.getClusterId());
        Update update = Update.newBuilder()
                .set(Member.Fields.hasJoinedCluster, false);
        return sharedConfigService.updateMany(Member.class, filter, update).then();
    }

    private Mono<Void> updateFollowersToAvailable(Collection<String> availableMemberNodeIds) {
        Filter filter = Filter.newBuilder()
                .in(Member.ID_NODE_ID, availableMemberNodeIds)
                .eq(Member.ID_CLUSTER_ID, localMember.getClusterId());
        Update update = Update.newBuilder()
                .set(Member.Fields.hasJoinedCluster, true);
        return sharedConfigService.updateMany(Member.class, filter, update).then();
    }

}
