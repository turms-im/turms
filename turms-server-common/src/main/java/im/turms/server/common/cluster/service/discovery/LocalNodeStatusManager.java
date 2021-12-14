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

import im.turms.server.common.cluster.service.config.SharedConfigService;
import im.turms.server.common.cluster.service.config.domain.discovery.Leader;
import im.turms.server.common.cluster.service.config.domain.discovery.Member;
import im.turms.server.common.logging.core.logger.LoggerFactory;
import im.turms.server.common.logging.core.logger.Logger;
import im.turms.server.common.mongo.exception.DuplicateKeyException;
import im.turms.server.common.mongo.operation.option.Filter;
import im.turms.server.common.mongo.operation.option.Update;
import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author James Chen
 */
public class LocalNodeStatusManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocalNodeStatusManager.class);

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
    private final long heartbeatTimeoutMillis;
    private final Duration heartbeatInterval;
    private final long heartbeatIntervalMillis;
    private ScheduledFuture<?> heartbeatFuture;
    private final ScheduledExecutorService scheduler = new ScheduledThreadPoolExecutor(1, new DefaultThreadFactory("discovery-heartbeat"));

    // Health
    private final AtomicBoolean isHealthStatusUpdating = new AtomicBoolean();

    public LocalNodeStatusManager(
            DiscoveryService discoveryService,
            SharedConfigService sharedConfigService,
            Member localMember,
            int heartbeatTimeoutSeconds,
            int heartbeatIntervalSeconds) {
        this.discoveryService = discoveryService;
        this.sharedConfigService = sharedConfigService;
        this.localMember = localMember;
        this.heartbeatTimeoutMillis = heartbeatTimeoutSeconds * 1000L;
        this.heartbeatInterval = Duration.ofSeconds(heartbeatIntervalSeconds);
        this.heartbeatIntervalMillis = heartbeatInterval.toMillis();
    }

    public Mono<Void> upsertLocalNodeInfo(Update update) {
        String nodeId = localMember.getNodeId();
        Filter memberFilter = Filter.newBuilder(2)
                .eq(Member.ID_CLUSTER_ID, localMember.getClusterId())
                .eq(Member.ID_NODE_ID, nodeId);
        return sharedConfigService.upsert(memberFilter, update, localMember)
                .doOnSuccess(unused -> isLocalNodeRegistered = true);
    }

    public Mono<Void> registerLocalMember(boolean suppressDuplicateMemberError) {
        LOGGER.info("Registering the local member");
        return discoveryService.registerMember(localMember)
                .doOnSuccess(ignored -> {
                    isLocalNodeRegistered = true;
                    LOGGER.info("Registered the local member successfully");
                })
                .onErrorResume(t -> {
                    if (suppressDuplicateMemberError && t instanceof DuplicateKeyException) {
                        LOGGER.info("Cancelled the local member registration because it has been registered");
                        return Mono.empty();
                    } else {
                        LOGGER.error("Failed to register the local member", t);
                        return Mono.error(t);
                    }
                });
    }

    public Mono<Void> unregisterLocalMember() {
        LOGGER.info("Unregistering the local member");
        return discoveryService.unregisterMembers(Set.of(localMember.getNodeId()))
                .then(unregisterLocalMemberLeadership())
                .doOnError(e -> LOGGER.error("Failed to unregister the local member", e))
                .doOnSuccess(ignored -> {
                    isLocalNodeRegistered = false;
                    LOGGER.info("Unregistered the local member successfully");
                });
    }

    public Mono<Void> tryBecomeFirstLeader() {
        List<Member> qualifiedMembersToBeLeader = discoveryService.findQualifiedMembersToBeLeader();
        if (qualifiedMembersToBeLeader.contains(localMember)) {
            String clusterId = localMember.getClusterId();
            Leader localLeader = new Leader(clusterId, localMember.getNodeId(), new Date(), 1);
            return sharedConfigService.insert(localLeader)
                    .then()
                    .onErrorResume(DuplicateKeyException.class, t -> Mono.empty());
        }
        return Mono.empty();
    }

    private Mono<Void> unregisterLocalMemberLeadership() {
        Filter query = Filter.newBuilder(2)
                .eq("_id", localMember.getClusterId())
                .eq(Leader.Fields.nodeId, localMember.getNodeId());
        return sharedConfigService.remove(Leader.class, query).then();
    }

    public boolean isLocalNodeId(String nodeId) {
        return localMember.getNodeId().equals(nodeId);
    }

    public boolean isLocalNodeLeader() {
        Leader leader = discoveryService.getLeader();
        return leader != null
                && leader.getNodeId().equals(localMember.getNodeId())
                && leader.getClusterId().equals(localMember.getClusterId());
    }

    public void startHeartbeat() {
        if (heartbeatFuture != null && !heartbeatFuture.isDone()) {
            return;
        }
        heartbeatFuture = scheduler.scheduleWithFixedDelay(() -> {
            if (isClosing) {
                return;
            }
            try {
                Date now = new Date();
                List<Mono<?>> monos = new ArrayList<>(2);
                monos.add(upsertLocalNodeInfo(Update.newBuilder(1)
                        .set(Member.STATUS_LAST_HEARTBEAT_DATE, now)));
                if (isLocalNodeLeader()) {
                    monos.add(renewLocalLeader(now)
                            .flatMap(isLeader -> isLeader ? updateMembersStatus(now) : Mono.empty()));
                }
                Mono.when(monos)
                        .timeout(heartbeatInterval)
                        .doOnSuccess(ignored -> localMember.getStatus().setLastHeartbeatDate(now))
                        .onErrorResume(e -> {
                            LOGGER.error("Failed to send heartbeat request", e);
                            return Mono.empty();
                        })
                        .subscribe();
            } catch (Exception e) {
                LOGGER.error("Failed to send heartbeat request", e);
            }
        }, heartbeatIntervalMillis, heartbeatIntervalMillis, TimeUnit.MILLISECONDS);
    }

    public void updateInfo(Member member) {
        boolean isLeaderEligible = member.isLeaderEligible();
        boolean wasLeaderEligible = localMember.isLeaderEligible();
        boolean isLeaderEligibleChanged = isLeaderEligible != wasLeaderEligible;
        this.localMember.updateIfNotNull(
                member.getZone(),
                member.isSeed(),
                member.isLeaderEligible(),
                member.getPriority(),
                member.getMemberHost(),
                member.getMetricsApiAddress(),
                member.getAdminApiAddress(),
                member.getWsAddress(),
                member.getTcpAddress(),
                member.getUdpAddress(),
                member.getStatus().isHasJoinedCluster(),
                member.getStatus().isActive(),
                member.getStatus().isHealthy(),
                member.getStatus().getLastHeartbeatDate());
        if (isLeaderEligibleChanged) {
            if (isLeaderEligible) {
                tryBecomeFirstLeader().subscribe();
            } else {
                unregisterLocalMemberLeadership().subscribe();
            }
        }
    }

    public void updateHealthStatus(boolean isHealthy) {
        if (localMember.getStatus().isHealthy() == isHealthy
                && !isHealthStatusUpdating.compareAndSet(false, true)) {
            return;
        }
        Filter filter = Filter.newBuilder(2)
                .eq(Member.ID_NODE_ID, localMember.getNodeId())
                .eq(Member.ID_CLUSTER_ID, localMember.getClusterId());
        Update update = Update.newBuilder(1)
                .set(Member.STATUS_IS_HEALTHY, isHealthy);
        sharedConfigService.updateOne(Member.class, filter, update)
                .doFinally(signal -> isHealthStatusUpdating.set(false))
                .subscribe();
    }

    private Mono<Boolean> renewLocalLeader(Date renewDate) {
        Leader leader = discoveryService.getLeader();
        if (leader == null) {
            return Mono.just(false);
        }
        Filter filter = Filter.newBuilder(3)
                .eq("_id", localMember.getClusterId())
                .eq(Leader.Fields.nodeId, localMember.getNodeId())
                .eq(Leader.Fields.generation, leader.getGeneration());
        Update update = Update.newBuilder(1)
                .set(Leader.Fields.renewDate, renewDate);
        return sharedConfigService.updateOne(Leader.class, filter, update)
                .flatMap(updateResult -> {
                    // For cases: no leader or leader has changed
                    if (updateResult.getMatchedCount() == 0) {
                        return sharedConfigService.insert(leader)
                                // True for the case: no leader
                                .thenReturn(true)
                                // False for the case: leader has changed
                                .onErrorResume(DuplicateKeyException.class, e -> Mono.just(false));
                    } else {
                        return Mono.just(true);
                    }
                });
    }

    private Mono<Void> updateMembersStatus(Date lastHeartbeatDate) {
        Collection<Member> knownMembers = discoveryService.getAllKnownMembers().values();
        int knownMembersSize = knownMembers.size();
        List<String> availableMemberNodeIds = new LinkedList<>();
        int availableMembersSize = 0;
        long lastHeartbeatTime = lastHeartbeatDate.getTime();
        for (Member knownMember : knownMembers) {
            Date memberHeartbeat = knownMember.getStatus().getLastHeartbeatDate();
            boolean isAvailable = memberHeartbeat != null && (lastHeartbeatTime - memberHeartbeat.getTime()) < heartbeatTimeoutMillis;
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
            Set<String> unavailableMemberIds = UnifiedSet.newSet(discoveryService.getAllKnownMembers().keySet());
            for (String availableMemberNodeId : availableMemberNodeIds) {
                unavailableMemberIds.remove(availableMemberNodeId);
            }
            return Mono.when(
                    updateFollowersToAvailable(availableMemberNodeIds),
                    updateFollowersToUnavailable(unavailableMemberIds));
        }
    }

    private Mono<Void> updateFollowersToUnavailable(Collection<String> unavailableMemberNodeIds) {
        Filter filter = Filter.newBuilder(2)
                .in(Member.ID_NODE_ID, unavailableMemberNodeIds)
                .eq(Member.ID_CLUSTER_ID, localMember.getClusterId());
        Update update = Update.newBuilder(1)
                .set(Member.STATUS_HAS_JOINED_CLUSTER, false);
        return sharedConfigService.updateMany(Member.class, filter, update).then();
    }

    private Mono<Void> updateFollowersToAvailable(Collection<String> availableMemberNodeIds) {
        Filter filter = Filter.newBuilder(2)
                .in(Member.ID_NODE_ID, availableMemberNodeIds)
                .eq(Member.ID_CLUSTER_ID, localMember.getClusterId());
        Update update = Update.newBuilder(1)
                .set(Member.STATUS_HAS_JOINED_CLUSTER, true);
        return sharedConfigService.updateMany(Member.class, filter, update).then();
    }

}
