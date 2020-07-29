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

import im.turms.common.constant.statuscode.TurmsStatusCode;
import im.turms.common.exception.TurmsBusinessException;
import im.turms.server.common.cluster.node.NodeType;
import im.turms.server.common.cluster.node.NodeVersion;
import im.turms.server.common.cluster.service.ClusterService;
import im.turms.server.common.cluster.service.config.ChangeStreamUtil;
import im.turms.server.common.cluster.service.config.SharedConfigService;
import im.turms.server.common.cluster.service.config.domain.discovery.Leader;
import im.turms.server.common.cluster.service.config.domain.discovery.Member;
import im.turms.server.common.manager.address.IServiceAddressManager;
import im.turms.server.common.property.env.common.cluster.DiscoveryProperties;
import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Consumer;

/**
 * @author James Chen
 */
@Log4j2
public class DiscoveryService implements ClusterService {

    private static final Duration CRUD_TIMEOUT_DURATION = Duration.ofSeconds(10);

    @Getter
    private final ScheduledExecutorService scheduler = new ScheduledThreadPoolExecutor(1, new DefaultThreadFactory("turms-cluster-discovery"));
    private ScheduledFuture<?> notifyMembersChangeFuture;

    private final DiscoveryProperties discoveryProperties;

    private final SharedConfigService sharedConfigService;
    @Getter
    private final LocalNodeStatusManager localNodeStatusManager;
    @Getter
    private final ConnectionManager connectionManager;

    /**
     * Don't use volatile for better performance
     */
    @Getter
    private Leader leader;

    /**
     * Don't use volatile for better performance
     */
    private boolean isClosing;

    /**
     * Use independent collections to speed up query operations
     */
    @Getter
    private final Map<String, Member> allKnownMembers = new HashMap<>();
    @Getter
    private final List<Member> activeConnectedServiceMemberList = new ArrayList<>();
    @Getter
    private List<Member> otherActiveConnectedServiceMemberList = Collections.emptyList();
    @Getter
    private final Set<Member> otherActiveConnectedServiceMembers = new HashSet<>();

    private final List<Consumer<Leader>> leadershipChangeListeners = new LinkedList<>();
    private final List<MembersChangeListener> membersChangeListeners = new LinkedList<>();

    public DiscoveryService(
            String clusterId,
            String nodeId,
            NodeType nodeType,
            NodeVersion nodeVersion,
            boolean isActive,
            String memberAddress,
            int memberPort,
            int outputThreadCount,
            DiscoveryProperties discoveryProperties,
            IServiceAddressManager serviceAddressManager,
            SharedConfigService sharedConfigService) {
        Date now = new Date();
        Member localMember = new Member(clusterId,
                nodeId,
                nodeType,
                nodeVersion,
                false,
                now,
                (int) now.getTime(),
                memberAddress,
                memberPort,
                serviceAddressManager.getServiceAddress(),
                false,
                isActive);
        this.discoveryProperties = discoveryProperties;
        this.sharedConfigService = sharedConfigService;
        this.localNodeStatusManager = new LocalNodeStatusManager(
                this,
                sharedConfigService,
                localMember,
                discoveryProperties.getHeartbeatIntervalInSeconds());
        serviceAddressManager.addListener(serviceAddress ->
                localNodeStatusManager.updateLocalNodeInfo(new Update().set(Member.Fields.serviceAddress, serviceAddress)));
        this.connectionManager = new ConnectionManager(this, discoveryProperties, outputThreadCount);
        connectionManager.addMemberConnectionChangeListener(new MemberConnectionChangeListener() {
            @Override
            public void onMemberConnectionAdded(Member member) {
                if (member.isActive() && member.getNodeType() == NodeType.SERVICE) {
                    updateActiveConnectedServiceMembers(true, member);
                }
            }

            @Override
            public void onMemberConnectionRemoved(Member member) {
                updateActiveConnectedServiceMembers(false, member);
            }
        });
    }

    @Override
    public void start() {
        listenLeadershipChangeEvent();
        localNodeStatusManager.tryBecomeLeader().block();

        sharedConfigService.ensureTtlIndex(new Criteria().andOperator(
                Criteria.where(Member.Fields.isSeed).is(false)),
                Member.Fields.lastHeartbeatDate,
                Member.class)
                .block();
        listenMembersChangeEvent();
        List<Member> memberList = queryMembers()
                .collectList()
                .block(CRUD_TIMEOUT_DURATION);
        for (Member member : memberList) {
            this.onAddOrUpdateMember(member);
        }

        localNodeStatusManager.registerLocalMember().block(CRUD_TIMEOUT_DURATION);
        localNodeStatusManager.startHeartbeat();
    }

    private Flux<Member> queryMembers() {
        Criteria criteria = Criteria.where(Member.ID_CLUSTER_ID);
        Query query = new Query().addCriteria(criteria.is(getLocalMember().getClusterId()));
        return sharedConfigService.find(query, Member.class);
    }

    private void listenLeadershipChangeEvent() {
        sharedConfigService.subscribe(Leader.class, true)
                .doOnNext(event -> {
                    Leader changedLeader = event.getBody();
                    String clusterId = changedLeader != null
                            ? changedLeader.getClusterId()
                            : ChangeStreamUtil.getIdAsString(event);
                    if (clusterId.equals(getLocalMember().getClusterId())) {
                        switch (event.getOperationType()) {
                            case INSERT:
                            case REPLACE:
                            case UPDATE:
                                leader = changedLeader;
                                break;
                            case INVALIDATE:
                                leader = null;
                                int delay = (int) (5 * ThreadLocalRandom.current().nextFloat());
                                Mono.delay(Duration.ofSeconds(delay))
                                        .subscribe(ignored -> {
                                            if (leader == null) {
                                                localNodeStatusManager.tryBecomeLeader().subscribe();
                                            }
                                        });
                                break;
                        }
                    }
                })
                .onErrorContinue((throwable, o) -> log.error("Error while processing the change stream event of Leader: {}", o, throwable))
                .subscribe();
    }

    private void listenMembersChangeEvent() {
        sharedConfigService.subscribe(Member.class, true)
                .doOnNext(event -> {
                    Member changedMember = event.getBody();
                    String clusterId = changedMember != null
                            ? changedMember.getClusterId()
                            : ChangeStreamUtil.getStringFromId(event, Member.Key.Fields.clusterId);
                    if (clusterId.equals(getLocalMember().getClusterId())) {
                        switch (event.getOperationType()) {
                            case INSERT:
                            case REPLACE:
                                onAddOrUpdateMember(changedMember);
                                break;
                            case UPDATE:
                                Member memberToUpdate = allKnownMembers.get(changedMember.getNodeId());
                                memberToUpdate.updateIfNotNull(
                                        changedMember.isSeed(),
                                        changedMember.isActive(),
                                        changedMember.getLastHeartbeatDate(),
                                        changedMember.getMemberHost(),
                                        changedMember.getServiceAddress());
                                break;
                            case DELETE:
                                String nodeId = ChangeStreamUtil.getStringFromId(event, Member.Key.Fields.nodeId);
                                Member deletedMember = allKnownMembers.remove(nodeId);
                                updateActiveConnectedServiceMembers(false, deletedMember);
                                if (nodeId.equals(getLocalMember().getNodeId()) && !isClosing) {
                                    registerMember(getLocalMember()).subscribe();
                                }
                                break;
                        }
                        connectionManager.updateHasConnectedToAllMembers(allKnownMembers.keySet());
                    }
                })
                .onErrorContinue((throwable, o) -> log.error("Error while processing the change stream event of Member: {}", o, throwable))
                .subscribe();
    }

    private void onAddOrUpdateMember(Member member) {
        synchronized (this) {
            String nodeId = member.getNodeId();
            allKnownMembers.put(nodeId, member);
            if (nodeId.equals(localNodeStatusManager.getLocalMember().getNodeId())) {
                localNodeStatusManager.updateInfo(member);
            }
            if (member.isActive()
                    && member.getNodeType() == NodeType.SERVICE
                    && connectionManager.isMemberConnected(member.getNodeId())) {
                updateActiveConnectedServiceMembers(true, member);
                if (notifyMembersChangeFuture != null) {
                    notifyMembersChangeFuture.cancel(false);
                }
                notifyMembersChangeFuture = scheduler.schedule(
                        this::notifyMembersChangeListeners,
                        discoveryProperties.getJitterInSeconds(),
                        TimeUnit.SECONDS);
            }
        }
        connectionManager.connectMemberUntilSucceedOrRemoved(member);
    }

    private synchronized void updateActiveConnectedServiceMembers(boolean isAdd, Member member) {
        if (isAdd) {
            activeConnectedServiceMemberList.add(member);
        } else {
            activeConnectedServiceMemberList.remove(member);
        }
        if (!member.getNodeId().equals(localNodeStatusManager.getLocalMember().getNodeId())) {
            if (isAdd) {
                otherActiveConnectedServiceMembers.add(member);
            } else {
                otherActiveConnectedServiceMembers.remove(member);
            }
            List<Member> otherActiveConnectedServiceMemberList = Arrays.asList(otherActiveConnectedServiceMembers.toArray(new Member[0]));
            otherActiveConnectedServiceMemberList.sort((m1, m2) -> {
                int m1Priority = m1.getPriority();
                int m2Priority = m2.getPriority();
                if (m1Priority == m2Priority) {
                    // Don't use 0 to make sure that the order is consistent in every nodes
                    // and it should never happen
                    return m1.getNodeId().hashCode() < m2.getNodeId().hashCode() ? -1 : 1;
                } else {
                    return m1Priority < m2Priority ? -1 : 1;
                }
            });
            this.otherActiveConnectedServiceMemberList = otherActiveConnectedServiceMemberList;
        }
    }

    @Nullable
    public Integer getLocalServiceMemberIndex() {
        int index = activeConnectedServiceMemberList.indexOf(getLocalMember());
        return index != -1 ? index : null;
    }

    public boolean onlyOneMemberInCluster() {
        return allKnownMembers.size() == 1;
    }

    @Override
    public void stop() {
        isClosing = true;
        scheduler.shutdownNow();
        connectionManager.stop();
        localNodeStatusManager.unregisterLocalMember().block(CRUD_TIMEOUT_DURATION);
    }

    // Registration

    public Mono<Boolean> registerMember(Member member) {
        if (member.getClusterId() == null
                || member.getNodeId() == null) {
            throw new IllegalArgumentException("Failed to register member because required fields are missing");
        }
        return sharedConfigService.insert(member)
                .thenReturn(true);
    }

    public Mono<Boolean> unregisterMembers(Set<String> nodeIds) {
        Query query = new Query()
                .addCriteria(Criteria.where(Member.ID_CLUSTER_ID).is(getLocalMember().getClusterId()))
                .addCriteria(Criteria.where(Member.ID_NODE_ID).in(nodeIds));
        return sharedConfigService.remove(query, Member.class);
    }

    public Mono<Void> updateMemberInfo(@NotNull String id, @Nullable Boolean isSeed, @Nullable Boolean isActive) {
        Member member = allKnownMembers.get(id);
        if (member == null) {
            return Mono.error(TurmsBusinessException.get(TurmsStatusCode.NO_CONTENT));
        }
        Query query = new Query()
                .addCriteria(Criteria.where(Member.ID_CLUSTER_ID).is(getLocalMember().getClusterId()))
                .addCriteria(Criteria.where(Member.ID_NODE_ID).is(id));
        Update update = new Update();
        if (isSeed != null) {
            update.set(Member.Fields.isSeed, isSeed);
        }
        if (isActive != null) {
            update.set(Member.Fields.isActive, isActive);
        }
        return sharedConfigService.upsert(query, update, member, Member.class);
    }

    // Event

    public void addListenerOnMembersChange(MembersChangeListener listener) {
        membersChangeListeners.add(listener);
    }

    private void notifyMembersChangeListeners() {
        for (MembersChangeListener listener : membersChangeListeners) {
            listener.onMembersChange();
        }
    }

    public void addListenerOnLeadershipChange(Consumer<Leader> listener) {
        leadershipChangeListeners.add(listener);
    }

    public void notifyLeadershipChangeListeners(@Nullable Leader leader) {
        for (Consumer<Leader> listener : leadershipChangeListeners) {
            listener.accept(leader);
        }
    }

    //

    public Member getLocalMember() {
        return localNodeStatusManager.getLocalMember();
    }

    public boolean isKnownMember(String nodeId) {
        return allKnownMembers.containsKey(nodeId);
    }

}
