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
import io.rsocket.RSocket;
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
import java.util.stream.Collectors;

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

    private volatile boolean isClosing;

    /**
     * Use independent collections to speed up query operations
     */
    @Getter
    private final Map<String, Member> allKnownMembers = new HashMap<>();

    @Getter
    private List<Member> activeServiceMemberList = new ArrayList<>();

    /**
     * Used for RpcService so that it doesn't need to find a socket from a map by a member ID every time
     */
    @Getter
    private List<MemberInfoWithConnection> otherActiveConnectedServiceMemberList = Collections.emptyList();

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
            public void onMemberConnectionAdded(Member member, RSocket connection) {
                if (member.isActive() && member.getNodeType() == NodeType.SERVICE) {
                    updateOtherActiveConnectedServiceMemberList(true, member, connection);
                }
            }

            @Override
            public void onMemberConnectionRemoved(Member member) {
                updateOtherActiveConnectedServiceMemberList(false, member, null);
            }
        });
    }

    @Override
    public void start() {
        // Leadership
        listenLeadershipChangeEvent();
        localNodeStatusManager.tryBecomeLeader().block();

        // Members
        sharedConfigService.ensureTtlIndex(new Criteria().andOperator(
                Criteria.where(Member.Fields.isSeed).is(false)),
                Member.Fields.lastHeartbeatDate,
                Member.class)
                .block();
        listenMembersChangeEvent();
        List<Member> memberList = queryMembers()
                .collectList()
                .block(CRUD_TIMEOUT_DURATION);
        Member localMember = getLocalMember();
        for (Member member : memberList) {
            if (localMember.isSameNode(member)) {
                String message = "Failed to bootstrap the local node because the local node has been registered: " + member;
                throw new IllegalStateException(message);
            }
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
                                updateOtherActiveConnectedServiceMemberList(false, deletedMember, null);
                                if (nodeId.equals(getLocalMember().getNodeId()) && !isClosing) {
                                    registerMember(getLocalMember()).subscribe();
                                }
                                break;
                        }
                        updateActiveMembers(allKnownMembers.values());
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
            RSocket connection = connectionManager.getMemberConnection(member.getNodeId());
            if (member.isActive()
                    && member.getNodeType() == NodeType.SERVICE
                    && connection != null) {
                updateOtherActiveConnectedServiceMemberList(true, member, connection);
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

    private synchronized void updateActiveMembers(Collection<Member> allKnownMembers) {
        activeServiceMemberList = allKnownMembers
                .stream()
                .filter(Member::isActive)
                .collect(Collectors.toList());
    }

    private synchronized void updateOtherActiveConnectedServiceMemberList(boolean isAdd, Member member, RSocket connection) {
        boolean isLocalNode = member.isSameNode(localNodeStatusManager.getLocalMember());
        if (!isLocalNode) {
            int size = isAdd
                    ? otherActiveConnectedServiceMemberList.size() + 1
                    : otherActiveConnectedServiceMemberList.size();
            List<MemberInfoWithConnection> tempOtherActiveConnectedServiceMemberList = new ArrayList<>(size);
            tempOtherActiveConnectedServiceMemberList.addAll(otherActiveConnectedServiceMemberList);
            if (isAdd) {
                tempOtherActiveConnectedServiceMemberList.add(new MemberInfoWithConnection(member, connection));
            } else {
                tempOtherActiveConnectedServiceMemberList.remove(new MemberInfoWithConnection(member, null));
            }
            tempOtherActiveConnectedServiceMemberList.sort((i1, i2) -> {
                Member m1 = i1.getMember();
                Member m2 = i2.getMember();
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
            otherActiveConnectedServiceMemberList = tempOtherActiveConnectedServiceMemberList;
        }
    }

    /**
     * @return null if the local node isn't active
     */
    @Nullable
    public Integer getLocalServiceMemberIndex() {
        int index = activeServiceMemberList.indexOf(getLocalMember());
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

    public Mono<Boolean> updateMemberInfo(@NotNull String id, @Nullable Boolean isSeed, @Nullable Boolean isActive) {
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