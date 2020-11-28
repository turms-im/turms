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
import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.*;
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
    private final Duration heartbeatInterval;
    private final long heartbeatIntervalInMillis;
    private ScheduledFuture<?> heartbeatFuture;
    private final ScheduledExecutorService scheduler = new ScheduledThreadPoolExecutor(1, new DefaultThreadFactory("discovery-heartbeat"));

    public LocalNodeStatusManager(
            DiscoveryService discoveryService,
            SharedConfigService sharedConfigService,
            Member localMember,
            int heartbeatIntervalInSeconds) {
        this.discoveryService = discoveryService;
        this.sharedConfigService = sharedConfigService;
        this.localMember = localMember;
        this.heartbeatInterval = Duration.ofSeconds(heartbeatIntervalInSeconds);
        this.heartbeatIntervalInMillis = heartbeatInterval.toMillis();
    }

    public Mono<Boolean> updateLocalNodeInfo(Update update) {
        String nodeId = localMember.getNodeId();
        Query memberQuery = new Query()
                .addCriteria(Criteria.where(Member.ID_CLUSTER_ID).is(localMember.getClusterId()))
                .addCriteria(Criteria.where(Member.ID_NODE_ID).is(nodeId));
        return sharedConfigService.upsert(memberQuery, update, localMember, Member.class);
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

    public Mono<Boolean> unregisterLocalMember() {
        log.info("Unregistering the local member");
        return discoveryService.unregisterMembers(Set.of(localMember.getNodeId()))
                .then(unregisterLocalMemberLeadership())
                .doOnError(e -> log.error("Failed to unregister the local member", e))
                .doOnSuccess(ignored -> {
                    isLocalNodeRegistered = false;
                    log.info("Unregistered the local member successfully");
                });
    }

    public Mono<Leader> tryBecomeLeader() {
        if (localMember.getNodeType() == NodeType.SERVICE) {
            String clusterId = localMember.getClusterId();
            Leader localLeader = new Leader(clusterId, localMember.getNodeId(), new Date());
            return sharedConfigService.insertOrGet(localLeader);
        } else {
            return sharedConfigService.findOne(Leader.class);
        }
    }

    private Mono<Boolean> unregisterLocalMemberLeadership() {
        Member.Key key = new Member.Key(localMember.getClusterId(), localMember.getNodeId());
        Query query = new Query()
                .addCriteria(Criteria.where("_id").is(key));
        return sharedConfigService.remove(query, Leader.class)
                .doOnSuccess(removed -> {
                    if (removed != null && removed) {
                        discoveryService.notifyLeadershipChangeListeners(null);
                    }
                });
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
                try {
                    Date now = new Date();
                    Update update = new Update().set(Member.Fields.lastHeartbeatDate, now);
                    List<Mono<?>> monos = new LinkedList<>();
                    monos.add(updateLocalNodeInfo(update));
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
        this.localMember.updateIfNotNull(
                member.isSeed(),
                member.isActive(),
                member.getLastHeartbeatDate(),
                member.getMemberHost(),
                member.getServiceAddress());
    }

    private Mono<Boolean> updateLocalLeaderHeartbeat(Date lastHeartbeatDate) {
        Update update = new Update().set(Leader.Fields.lastHeartbeatDate, lastHeartbeatDate);
        Query leaderQuery = new Query()
                .addCriteria(Criteria.where("_id").is(localMember.getClusterId()))
                .addCriteria(Criteria.where(Leader.Fields.nodeId).is(localMember.getNodeId()));
        Leader leader = discoveryService.getLeader();
        return sharedConfigService.upsert(leaderQuery, update, leader, Leader.class);
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
            boolean isAvailable = (lastHeartbeatTime - knownMember.getLastHeartbeatDate().getTime()) < heartbeatIntervalInMillis;
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
        Query query = new Query()
                .addCriteria(Criteria.where(Member.ID_NODE_ID).in(unavailableMemberNodeIds))
                .addCriteria(Criteria.where(Member.ID_CLUSTER_ID).is(localMember.getClusterId()));
        Update update = new Update().set(Member.Fields.hasJoinedCluster, false);
        return sharedConfigService.updateMulti(query, update, Member.class).then();
    }

    private Mono<Void> updateFollowersToAvailable(Collection<String> availableMemberNodeIds) {
        Query query = new Query()
                .addCriteria(Criteria.where(Member.ID_NODE_ID).in(availableMemberNodeIds))
                .addCriteria(Criteria.where(Member.ID_CLUSTER_ID).is(localMember.getClusterId()));
        Update update = new Update().set(Member.Fields.hasJoinedCluster, true);
        return sharedConfigService.updateMulti(query, update, Member.class).then();
    }

}
