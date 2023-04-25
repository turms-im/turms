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

package im.turms.server.common.infra.cluster.service.discovery;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

import com.mongodb.client.model.changestream.FullDocument;
import com.mongodb.client.model.changestream.UpdateDescription;
import lombok.Getter;
import org.bson.BsonValue;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.infra.address.BaseServiceAddressManager;
import im.turms.server.common.infra.cluster.node.NodeType;
import im.turms.server.common.infra.cluster.node.NodeVersion;
import im.turms.server.common.infra.cluster.service.ClusterService;
import im.turms.server.common.infra.cluster.service.codec.CodecService;
import im.turms.server.common.infra.cluster.service.config.ChangeStreamUtil;
import im.turms.server.common.infra.cluster.service.config.SharedConfigService;
import im.turms.server.common.infra.cluster.service.config.entity.discovery.Leader;
import im.turms.server.common.infra.cluster.service.config.entity.discovery.Member;
import im.turms.server.common.infra.cluster.service.connection.ConnectionService;
import im.turms.server.common.infra.cluster.service.idgen.IdService;
import im.turms.server.common.infra.cluster.service.rpc.RpcService;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.collection.CollectorUtil;
import im.turms.server.common.infra.exception.ResponseException;
import im.turms.server.common.infra.exception.ResponseExceptionPublisherPool;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.property.env.common.cluster.DiscoveryProperties;
import im.turms.server.common.infra.thread.NamedThreadFactory;
import im.turms.server.common.infra.thread.ThreadNameConst;
import im.turms.server.common.infra.time.DurationConst;
import im.turms.server.common.storage.mongo.operation.option.Filter;
import im.turms.server.common.storage.mongo.operation.option.Update;

/**
 * Responsibilities: 1. Ensure the local node is registered even if it is unregistered unexpectedly
 * 2. Listen to the changes (added/removed/updated) of members and notify ConnectionService to
 * connect (TCP) or disconnect 3. Select a leader
 *
 * @author James Chen
 */
public class DiscoveryService implements ClusterService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DiscoveryService.class);

    private static final Duration CRUD_TIMEOUT_DURATION = DurationConst.ONE_MINUTE;
    private static final Comparator<Member> MEMBER_PRIORITY_COMPARATOR =
            DiscoveryService::compareMemberPriority;

    private final ScheduledExecutorService scheduler = new ScheduledThreadPoolExecutor(
            1,
            new NamedThreadFactory(ThreadNameConst.NODE_DISCOVERY_CHANGE_NOTIFIER, false));
    private ScheduledFuture<?> notifyMembersChangeFuture;

    private final DiscoveryProperties discoveryProperties;

    private final SharedConfigService sharedConfigService;
    @Getter
    private final LocalNodeStatusManager localNodeStatusManager;
    private ConnectionService connectionService;

    /**
     * Don't use volatile for better performance
     */
    @Getter
    @Nullable
    private Leader leader;

    /**
     * Use independent collections to speed up querying
     */
    @Getter
    private final Map<String, Member> allKnownMembers = new ConcurrentHashMap<>(32);

    @Getter
    private List<Member> activeSortedServiceMembers = Collections.emptyList();

    @Getter
    private List<Member> activeSortedGatewayMembers = Collections.emptyList();

    @Getter
    private List<Member> otherActiveConnectedServiceMembers = Collections.emptyList();
    @Getter
    private List<Member> otherActiveConnectedGatewayMembers = Collections.emptyList();
    @Getter
    private List<Member> otherActiveConnectedMembers = Collections.emptyList();

    private final List<MembersChangeListener> membersChangeListeners = new LinkedList<>();

    public DiscoveryService(
            String clusterId,
            String nodeId,
            String zone,
            NodeType nodeType,
            NodeVersion nodeVersion,
            boolean isLeaderEligible,
            int priority,
            boolean isActive,
            boolean isHealthy,
            int memberBindPort,
            DiscoveryProperties discoveryProperties,
            BaseServiceAddressManager serviceAddressManager,
            SharedConfigService sharedConfigService) {
        Date now = new Date();
        Member localMember = new Member(
                clusterId,
                nodeId,
                zone,
                nodeType,
                nodeVersion,
                false,
                isLeaderEligible,
                now,
                priority,
                serviceAddressManager.getMemberHost(),
                memberBindPort,
                serviceAddressManager.getAdminApiAddress(),
                serviceAddressManager.getWsAddress(),
                serviceAddressManager.getTcpAddress(),
                serviceAddressManager.getUdpAddress(),
                false,
                isActive,
                isHealthy);
        this.discoveryProperties = discoveryProperties;
        this.sharedConfigService = sharedConfigService;
        this.localNodeStatusManager = new LocalNodeStatusManager(
                this,
                sharedConfigService,
                localMember,
                discoveryProperties.getHeartbeatTimeoutSeconds(),
                discoveryProperties.getHeartbeatIntervalSeconds());
        serviceAddressManager.addOnNodeAddressInfoChangedListener(info -> {
            String nodeHost = info.memberHost();
            String adminApiAddress = info.adminApiAddress();
            String wsAddress = info.wsAddress();
            String tcpAddress = info.tcpAddress();
            String udpAddress = info.udpAddress();
            Update update = Update.newBuilder(6)
                    .setIfNotNull(Member.Fields.memberHost, nodeHost)
                    .setIfNotNull(Member.Fields.adminApiAddress, adminApiAddress)
                    .setIfNotNull(Member.Fields.wsAddress, wsAddress)
                    .setIfNotNull(Member.Fields.tcpAddress, tcpAddress)
                    .setIfNotNull(Member.Fields.udpAddress, udpAddress);
            localNodeStatusManager.upsertLocalNodeInfo(update)
                    .subscribe(null,
                            t -> LOGGER.error("Caught an error while upserting the local node info",
                                    t));
        });
    }

    /**
     * @return a positive or negative number, and never 0 to ensure that the order is consistent in
     *         every node.
     */
    private static int compareMemberPriority(Member m1, Member m2) {
        int m1Priority = m1.getPriority();
        int m2Priority = m2.getPriority();
        if (m1Priority == m2Priority) {
            // Don't use 0 to make sure that the order is consistent in every node
            // and it should never happen
            return m1.getNodeId()
                    .hashCode() < m2.getNodeId()
                            .hashCode()
                                    ? -1
                                    : 1;
        }
        return m1Priority < m2Priority
                ? -1
                : 1;
    }

    @Override
    public void start() {
        listenLeadershipChangeEvent();

        // Members
        listenMembersChangeEvent();
        List<Member> memberList;
        try {
            memberList = queryMembers().collect(CollectorUtil.toList())
                    .block(CRUD_TIMEOUT_DURATION);
        } catch (Exception e) {
            throw new RuntimeException("Failed to find members", e);
        }
        Member localMember = localNodeStatusManager.getLocalMember();
        for (Member member : memberList) {
            if (localMember.isSameNode(member)) {
                String message =
                        "Failed to bootstrap the local node because the local node has been registered. "
                                + "Local Node: "
                                + localMember
                                + ". "
                                + "Registered Node: "
                                + member;
                throw new RuntimeException(message);
            }
            onMemberAddedOrReplaced(member);
        }
        onMemberAddedOrReplaced(localMember);
        updateActiveMembers(allKnownMembers.values());

        try {
            localNodeStatusManager.registerLocalNodeAsMember(false)
                    .block(CRUD_TIMEOUT_DURATION);
        } catch (Exception e) {
            throw new RuntimeException(
                    "Caught an error while registering the local node as a member",
                    e);
        }
        try {
            localNodeStatusManager.tryBecomeFirstLeader()
                    .block();
        } catch (Exception e) {
            throw new RuntimeException(
                    "Caught an error while trying to become the first leader",
                    e);
        }
        localNodeStatusManager.startHeartbeat();
    }

    @Override
    public void lazyInit(
            CodecService codecService,
            ConnectionService connectionService,
            DiscoveryService discoveryService,
            IdService idService,
            RpcService rpcService,
            SharedConfigService sharedConfigService) {
        this.connectionService = connectionService;
        this.connectionService
                .addMemberConnectionListenerSupplier(() -> new MemberConnectionListener() {
                    private Member member;

                    @Override
                    public void onOpeningHandshakeCompleted(Member member) {
                        this.member = member;
                        updateOtherActiveConnectedMemberList(true, member);
                    }

                    @Override
                    public void onConnectionClosed() {
                        if (member != null) {
                            updateOtherActiveConnectedMemberList(false, member);
                        }
                    }
                });
    }

    public Member getMember(String nodeId) {
        return allKnownMembers.get(nodeId);
    }

    private Flux<Member> queryMembers() {
        Filter filter = Filter.newBuilder(1)
                .eq(Member.ID_CLUSTER_ID,
                        localNodeStatusManager.getLocalMember()
                                .getClusterId());
        return sharedConfigService.find(Member.class, filter);
    }

    private void listenLeadershipChangeEvent() {
        sharedConfigService.subscribe(Leader.class, FullDocument.UPDATE_LOOKUP)
                .doOnNext(event -> {
                    Leader changedLeader = event.getFullDocument();
                    String clusterId = changedLeader != null
                            ? changedLeader.getClusterId()
                            : ChangeStreamUtil.getIdAsString(event.getDocumentKey());
                    if (clusterId.equals(localNodeStatusManager.getLocalMember()
                            .getClusterId())) {
                        switch (event.getOperationType()) {
                            case INSERT, REPLACE, UPDATE -> leader = changedLeader;
                            case INVALIDATE -> {
                                leader = null;
                                int delay = (int) (5 * ThreadLocalRandom.current()
                                        .nextFloat());
                                Mono.delay(Duration.ofSeconds(delay))
                                        .subscribe(ignored -> {
                                            if (leader == null) {
                                                localNodeStatusManager.tryBecomeFirstLeader()
                                                        .subscribe(null,
                                                                t -> LOGGER.error(
                                                                        "Caught an error while trying to become the first leader",
                                                                        t));
                                            }
                                        });
                            }
                        }
                    }
                })
                .onErrorContinue((throwable, o) -> LOGGER.error(
                        "Caught an error while processing the change stream event of Leader: {}",
                        o,
                        throwable))
                .subscribe();
    }

    private void listenMembersChangeEvent() {
        // Because the information of members changes frequently due to lastHeartbeatDate,
        // use DEFAULT instead of UPDATE_LOOKUP to reduce the overhead of data
        sharedConfigService.subscribe(Member.class, FullDocument.DEFAULT)
                .doOnNext(event -> {
                    Member changedMember = event.getFullDocument();
                    String clusterId = changedMember == null
                            ? ChangeStreamUtil.getStringFromId(event.getDocumentKey(),
                                    Member.Key.Fields.clusterId)
                            : changedMember.getClusterId();
                    String nodeId = ChangeStreamUtil.getStringFromId(event.getDocumentKey(),
                            Member.Key.Fields.nodeId);
                    if (!clusterId.equals(localNodeStatusManager.getLocalMember()
                            .getClusterId())) {
                        return;
                    }
                    switch (event.getOperationType()) {
                        case INSERT, REPLACE -> onMemberAddedOrReplaced(changedMember);
                        case UPDATE -> onMemberUpdated(nodeId, event.getUpdateDescription());
                        case DELETE -> {
                            Member deletedMember = allKnownMembers.remove(nodeId);
                            if (deletedMember == null) {
                                return;
                            }
                            updateOtherActiveConnectedMemberList(false, deletedMember);
                            // Note that we assume that there is no the case:
                            // a node is running but has just been unregistered in the registry
                            // because the node may lose the connection with the registry and TTL
                            // has
                            // passed.
                            // During the time, another node with the SAME node ID registers itself.
                            // If the lost node recovers again, there is a potential bug.
                            if (nodeId.equals(localNodeStatusManager.getLocalMember()
                                    .getNodeId())) {
                                localNodeStatusManager.setLocalNodeRegistered(false);
                                if (!localNodeStatusManager.isClosing()) {
                                    // Ignore the error because the node may have been registered by
                                    // its
                                    // heartbeat timer
                                    localNodeStatusManager.registerLocalNodeAsMember(true)
                                            .subscribe();
                                }
                            }
                        }
                    }
                    updateActiveMembers(allKnownMembers.values());
                    connectionService.updateHasConnectedToAllMembers(allKnownMembers.keySet());
                })
                .onErrorContinue((throwable, o) -> LOGGER.error(
                        "Caught an error while processing the change stream event of Member: {}",
                        o,
                        throwable))
                .subscribe();
    }

    private void onMemberUpdated(String nodeId, UpdateDescription updateDescription) {
        Member memberToUpdate = allKnownMembers.get(nodeId);
        if (memberToUpdate == null) {
            LOGGER.error("Could not update the information of the unknown member: "
                    + nodeId);
            return;
        }
        // Info
        Set<Map.Entry<String, BsonValue>> entries = updateDescription.getUpdatedFields()
                .entrySet();
        for (Map.Entry<String, BsonValue> entry : entries) {
            // We don't use reflection to update fields for clarity and
            // better maintainability considering possible field changes
            String fieldName = entry.getKey();
            BsonValue value = entry.getValue();
            // TODO: pattern matching
            // Check status change
            if (fieldName.endsWith(Member.MemberStatus.Fields.lastHeartbeatDate)) {
                memberToUpdate.getStatus()
                        .setLastHeartbeatDate(new Date(
                                value.asDateTime()
                                        .getValue()));
            } else if (fieldName.endsWith(Member.MemberStatus.Fields.hasJoinedCluster)) {
                memberToUpdate.getStatus()
                        .setHasJoinedCluster(value.asBoolean()
                                .getValue());
            } else if (fieldName.endsWith(Member.MemberStatus.Fields.isActive)) {
                memberToUpdate.getStatus()
                        .setActive(value.asBoolean()
                                .getValue());
            } else if (fieldName.endsWith(Member.MemberStatus.Fields.isHealthy)) {
                memberToUpdate.getStatus()
                        .setHealthy(value.asBoolean()
                                .getValue());
                // Check info
            } else if (fieldName.equals(Member.Fields.zone)) {
                memberToUpdate.setZone(value.asString()
                        .getValue());
            } else if (fieldName.equals(Member.Fields.isSeed)) {
                // TODO: RENAME
                memberToUpdate.setSeed(value.asBoolean()
                        .getValue());
            } else if (fieldName.equals(Member.Fields.isLeaderEligible)) {
                memberToUpdate.setLeaderEligible(value.asBoolean()
                        .getValue());
            } else if (fieldName.equals(Member.Fields.priority)) {
                memberToUpdate.setPriority(value.asInt32()
                        .getValue());
            } else if (fieldName.equals(Member.Fields.memberHost)) {
                memberToUpdate.setMemberHost(value.asString()
                        .getValue());
            } else if (fieldName.equals(Member.Fields.adminApiAddress)) {
                memberToUpdate.setAdminApiAddress(value.asString()
                        .getValue());
            } else if (fieldName.equals(Member.Fields.wsAddress)) {
                memberToUpdate.setWsAddress(value.asString()
                        .getValue());
            } else if (fieldName.equals(Member.Fields.tcpAddress)) {
                memberToUpdate.setTcpAddress(value.asString()
                        .getValue());
            } else if (fieldName.equals(Member.Fields.udpAddress)) {
                memberToUpdate.setUdpAddress(value.asString()
                        .getValue());
            } else {
                LOGGER.warn(
                        "Could not update the unknown field \"{}\" with the value ({}) for the member: {}",
                        fieldName,
                        value,
                        memberToUpdate);
            }
        }
    }

    /**
     * @param newMember can be the local node
     */
    private void onMemberAddedOrReplaced(Member newMember) {
        String nodeId = newMember.getNodeId();
        Member localMember = localNodeStatusManager.getLocalMember();
        boolean isLocalNode = nodeId.equals(localMember.getNodeId());
        allKnownMembers.put(nodeId, newMember);
        synchronized (this) {
            if (isLocalNode) {
                localNodeStatusManager.updateInfo(newMember);
            }
            if (newMember.getStatus()
                    .isActive()
                    && connectionService.isMemberConnected(nodeId)) {
                updateOtherActiveConnectedMemberList(true, newMember);
                if (notifyMembersChangeFuture != null) {
                    notifyMembersChangeFuture.cancel(false);
                }
                notifyMembersChangeFuture = scheduler.schedule(this::notifyMembersChangeListeners,
                        discoveryProperties.getDelayToNotifyMembersChangeSeconds(),
                        TimeUnit.SECONDS);
            }
        }
        // shouldLocalNodeBeClient is used to ensure there is
        // only one TCP connection between two peers
        boolean shouldLocalNodeBeClient = compareMemberPriority(localMember, newMember) < 0;
        if (!isLocalNode && shouldLocalNodeBeClient) {
            connectionService.connectMemberUntilSucceedOrRemoved(newMember);
        }
    }

    private synchronized void updateActiveMembers(Collection<Member> allKnownMembers) {
        List<Member> knownMembers = new ArrayList<>(allKnownMembers);
        knownMembers.sort(MEMBER_PRIORITY_COMPARATOR);
        int size = knownMembers.size();
        List<Member> tempActiveSortedServiceMembers = new ArrayList<>(size);
        List<Member> tempActiveSortedGatewayMembers = new ArrayList<>(size);
        for (Member member : knownMembers) {
            if (member.getStatus()
                    .isActive()) {
                if (member.getNodeType() == NodeType.SERVICE) {
                    tempActiveSortedServiceMembers.add(member);
                } else {
                    tempActiveSortedGatewayMembers.add(member);
                }
            }
        }
        activeSortedServiceMembers = tempActiveSortedServiceMembers;
        activeSortedGatewayMembers = tempActiveSortedGatewayMembers;
    }

    private synchronized void updateOtherActiveConnectedMemberList(boolean isAdd, Member member) {
        boolean isLocalNode = member.isSameNode(localNodeStatusManager.getLocalMember());
        if (isLocalNode) {
            return;
        }
        boolean isServiceMember = member.getNodeType() == NodeType.SERVICE;
        List<Member> memberList = isServiceMember
                ? otherActiveConnectedServiceMembers
                : otherActiveConnectedGatewayMembers;
        int size = isAdd
                ? memberList.size() + 1
                : memberList.size();
        List<Member> tempOtherActiveConnectedMembers = new ArrayList<>(size);
        tempOtherActiveConnectedMembers.addAll(memberList);
        if (isAdd) {
            tempOtherActiveConnectedMembers.add(member);
        } else {
            tempOtherActiveConnectedMembers.remove(member);
        }
        if (isServiceMember) {
            otherActiveConnectedServiceMembers = tempOtherActiveConnectedMembers;
        } else {
            otherActiveConnectedGatewayMembers = tempOtherActiveConnectedMembers;
        }
        otherActiveConnectedMembers = CollectionUtil.union(otherActiveConnectedServiceMembers,
                otherActiveConnectedGatewayMembers);
    }

    /**
     * @return null if the local node isn't active
     */
    @Nullable
    public Integer getLocalServiceMemberIndex() {
        int index = activeSortedServiceMembers.indexOf(getLocalMember());
        return index != -1
                ? index
                : null;
    }

    @Override
    public Mono<Void> stop(long timeoutMillis) {
        localNodeStatusManager.setClosing(true);
        scheduler.shutdown();
        try {
            scheduler.awaitTermination(timeoutMillis, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            // ignored
        }
        if (localNodeStatusManager.isLocalNodeRegistered()) {
            return localNodeStatusManager.unregisterLocalNodeMembership()
                    .onErrorMap(t -> new RuntimeException(
                            "Caught an error while unregistering the membership of the local node",
                            t));
        }
        return Mono.empty();
    }

    // Registration

    public Mono<Void> registerMember(Member member) {
        boolean noClusterId = member.getClusterId() == null;
        boolean noNodeId = member.getNodeId() == null;
        if (noClusterId || noNodeId) {
            String message;
            if (noClusterId) {
                if (noNodeId) {
                    message = "Failed to register the member ("
                            + member
                            + ") because both the cluster ID and the node ID are missing";
                } else {
                    message = "Failed to register the member ("
                            + member
                            + ") because the cluster ID is missing";
                }
            } else {
                message = "Failed to register the member ("
                        + member
                        + ") because the node ID is missing";
            }
            throw new IllegalArgumentException(message);
        }
        return sharedConfigService.insert(member)
                .then();
    }

    public Mono<Void> unregisterMembers(Set<String> nodeIds) {
        Filter filter = Filter.newBuilder(2)
                .eq(Member.ID_CLUSTER_ID, getLocalMember().getClusterId())
                .in(Member.ID_NODE_ID, nodeIds);
        return sharedConfigService.remove(Member.class, filter)
                .then();
    }

    public Mono<Void> updateMemberInfo(
            @NotNull String id,
            @Nullable String zone,
            @Nullable Boolean isSeed,
            @Nullable Boolean isLeaderEligible,
            @Nullable Boolean isActive,
            @Nullable Integer priority) {
        Member member = allKnownMembers.get(id);
        if (member == null) {
            return ResponseExceptionPublisherPool.noContent();
        }
        Filter filter = Filter.newBuilder(2)
                .eq(Member.ID_CLUSTER_ID, getLocalMember().getClusterId())
                .eq(Member.ID_NODE_ID, id);
        Update update = Update.newBuilder(5)
                .setIfNotNull(Member.Fields.zone, zone)
                .setIfNotNull(Member.Fields.isSeed, isSeed)
                .setIfNotNull(Member.Fields.isLeaderEligible, isLeaderEligible)
                .setIfNotNull(Member.STATUS_IS_ACTIVE, isActive)
                .setIfNotNull(Member.Fields.priority, priority);
        // Note that we just need to update the member info in the config server
        // and the listener to the change stream will do remaining jobs.
        return sharedConfigService.upsert(filter, update, member);
    }

    // Event

    public void addOnMembersChangeListener(MembersChangeListener listener) {
        membersChangeListeners.add(listener);
    }

    private void notifyMembersChangeListeners() {
        for (MembersChangeListener listener : membersChangeListeners) {
            listener.onMembersChange();
        }
    }

    //

    public Member getLocalMember() {
        return localNodeStatusManager.getLocalMember();
    }

    public boolean isKnownMember(String nodeId) {
        return allKnownMembers.containsKey(nodeId);
    }

    // Leader

    /**
     * @return members with the same highest priority
     */
    public List<Member> findQualifiedMembersToBeLeader() {
        List<Member> members = new ArrayList<>(activeSortedServiceMembers.size());
        int highestPriority = Integer.MIN_VALUE;
        for (Member member : activeSortedServiceMembers) {
            if (member.getPriority() < highestPriority) {
                return members;
            }
            if (isQualifiedToBeLeader(member)) {
                highestPriority = member.getPriority();
                members.add(member);
            }
        }
        return members;
    }

    /**
     * @implNote Even a member with a lower priority is qualified to be a leader
     */
    public boolean isQualifiedToBeLeader(Member member) {
        return member.getNodeType() == NodeType.SERVICE
                && member.isLeaderEligible()
                && member.getStatus()
                        .isActive();
    }

    public Mono<Member> electNewLeaderByMember(Member member) {
        String clusterId = member.getClusterId();
        String nodeId = member.getNodeId();
        if (!isQualifiedToBeLeader(member)) {
            return Mono.error(
                    ResponseException.get(ResponseStatusCode.NOT_QUALIFIED_MEMBER_TO_BE_LEADER));
        }
        int generation = leader == null
                ? 1
                : leader.getGeneration() + 1;
        Filter filter = Filter.newBuilder(2)
                .eq(Leader.Fields.clusterId, clusterId)
                .ltOrNull(Leader.Fields.generation, generation);
        Date now = new Date();
        Update update = Update.newBuilder(2)
                .set(Leader.Fields.nodeId, nodeId)
                .set(Leader.Fields.renewDate, now);
        Leader localLeader = new Leader(clusterId, nodeId, now, generation);
        return sharedConfigService.upsert(filter, update, localLeader)
                .thenReturn(member);
    }

    public Mono<Member> electNewLeaderByNodeId(String nodeId) {
        Member member = allKnownMembers.get(nodeId);
        if (member == null) {
            return Mono.error(
                    ResponseException.get(ResponseStatusCode.NON_EXISTING_MEMBER_TO_BE_LEADER));
        }
        return electNewLeaderByMember(member);
    }

    public Mono<Member> electNewLeaderByPriority() {
        List<Member> qualifiedMembers = findQualifiedMembersToBeLeader();
        if (qualifiedMembers.isEmpty()) {
            return Mono.error(
                    ResponseException.get(ResponseStatusCode.NO_QUALIFIED_MEMBER_TO_BE_LEADER));
        }
        if (leader != null) {
            for (Member qualifiedMember : qualifiedMembers) {
                if (qualifiedMember.getNodeId()
                        .equals(leader.getNodeId())) {
                    return Mono.just(qualifiedMember);
                }
            }
        }
        return electNewLeaderByNodeId(qualifiedMembers.get(0)
                .getNodeId());
    }

}