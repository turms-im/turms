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

package im.turms.service.workflow.access.http.controller.cluster;

import im.turms.server.common.access.http.dto.response.ResponseDTO;
import im.turms.server.common.access.http.dto.response.ResponseFactory;
import im.turms.server.common.access.http.permission.RequiredPermission;
import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.cluster.node.NodeType;
import im.turms.server.common.cluster.node.NodeVersion;
import im.turms.server.common.cluster.service.config.domain.discovery.Leader;
import im.turms.server.common.cluster.service.config.domain.discovery.Member;
import im.turms.server.common.cluster.service.discovery.DiscoveryService;
import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.exception.TurmsBusinessException;
import im.turms.server.common.util.CollectionUtil;
import im.turms.service.workflow.access.http.dto.request.cluster.AddMemberDTO;
import im.turms.service.workflow.access.http.dto.request.cluster.UpdateMemberDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.List;

import static im.turms.server.common.access.http.permission.AdminPermission.CLUSTER_LEADER_QUERY;
import static im.turms.server.common.access.http.permission.AdminPermission.CLUSTER_LEADER_UPDATE;
import static im.turms.server.common.access.http.permission.AdminPermission.CLUSTER_MEMBER_CREATE;
import static im.turms.server.common.access.http.permission.AdminPermission.CLUSTER_MEMBER_DELETE;
import static im.turms.server.common.access.http.permission.AdminPermission.CLUSTER_MEMBER_QUERY;
import static im.turms.server.common.access.http.permission.AdminPermission.CLUSTER_MEMBER_UPDATE;


/**
 * @author James Chen
 */
@RestController
@RequestMapping("/cluster/members")
public class MemberController {

    private final Node node;

    public MemberController(Node node) {
        this.node = node;
    }

    @GetMapping
    @RequiredPermission(CLUSTER_MEMBER_QUERY)
    public ResponseEntity<ResponseDTO<Collection<Member>>> queryMembers() {
        return ResponseFactory.okIfTruthy(node.getDiscoveryService().getAllKnownMembers().values());
    }

    @DeleteMapping
    @RequiredPermission(CLUSTER_MEMBER_DELETE)
    public Mono<ResponseEntity<ResponseDTO<Void>>> removeMembers(@RequestParam List<String> ids) {
        Mono<Void> unregisterMembers = node.getDiscoveryService().unregisterMembers(CollectionUtil.newSet(ids));
        return unregisterMembers.thenReturn(ResponseFactory.OK);
    }

    @PostMapping
    @RequiredPermission(CLUSTER_MEMBER_CREATE)
    public Mono<ResponseEntity<ResponseDTO<Void>>> addMember(@RequestBody AddMemberDTO addMemberDTO) {
        String clusterId = node.getDiscoveryService().getLocalMember().getClusterId();
        NodeType nodeType = addMemberDTO.nodeType();
        if (nodeType != NodeType.SERVICE && addMemberDTO.isLeaderEligible()) {
            return Mono.error(TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENT, "Only turms-service servers can be the leader"));
        }
        Member member = new Member(
                clusterId,
                addMemberDTO.nodeId(),
                addMemberDTO.zone(),
                nodeType,
                NodeVersion.parse(addMemberDTO.version()),
                addMemberDTO.isSeed(),
                addMemberDTO.isLeaderEligible(),
                addMemberDTO.registrationDate(),
                addMemberDTO.priority(),
                addMemberDTO.memberHost(),
                addMemberDTO.memberPort(),
                addMemberDTO.metricsApiAddress(),
                addMemberDTO.adminApiAddress(),
                addMemberDTO.wsAddress(),
                addMemberDTO.tcpAddress(),
                addMemberDTO.udpAddress(),
                false,
                addMemberDTO.isActive(),
                addMemberDTO.isHealthy());
        return node.getDiscoveryService()
                .registerMember(member)
                .thenReturn(ResponseFactory.OK);
    }

    @PutMapping
    @RequiredPermission(CLUSTER_MEMBER_UPDATE)
    public Mono<ResponseEntity<ResponseDTO<Void>>> updateMember(
            @RequestParam String id,
            @RequestBody UpdateMemberDTO updateMemberDTO) {
        Mono<Void> addMemberMono = node.getDiscoveryService().updateMemberInfo(
                id,
                updateMemberDTO.zone(),
                updateMemberDTO.isSeed(),
                updateMemberDTO.isLeaderEligible(),
                updateMemberDTO.isActive(),
                updateMemberDTO.priority());
        return addMemberMono.thenReturn(ResponseFactory.OK);
    }

    // Leader

    @GetMapping("/leader")
    @RequiredPermission(CLUSTER_LEADER_QUERY)
    public ResponseEntity<ResponseDTO<Member>> queryLeader() {
        DiscoveryService discoveryService = node.getDiscoveryService();
        Leader leader = discoveryService.getLeader();
        if (leader == null) {
            throw TurmsBusinessException.get(TurmsStatusCode.NO_CONTENT);
        }
        String nodeId = leader.getNodeId();
        Member member = discoveryService.getAllKnownMembers().get(nodeId);
        return ResponseFactory.okIfTruthy(member);
    }

    @PostMapping("/leader")
    @RequiredPermission(CLUSTER_LEADER_UPDATE)
    public Mono<ResponseEntity<ResponseDTO<Member>>> electNewLeader(@RequestParam(required = false) String id) {
        Mono<Member> leader = id == null
                ? node.getDiscoveryService().electNewLeaderByPriority()
                : node.getDiscoveryService().electNewLeaderByNodeId(id);
        return ResponseFactory.okIfTruthy(leader);
    }

}