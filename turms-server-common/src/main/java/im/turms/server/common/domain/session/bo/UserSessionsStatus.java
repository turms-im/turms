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

package im.turms.server.common.domain.session.bo;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import jakarta.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import im.turms.server.common.access.client.dto.constant.DeviceType;
import im.turms.server.common.access.client.dto.constant.UserStatus;
import im.turms.server.common.infra.collection.CollectionUtil;

/**
 * @author James Chen
 */
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public final class UserSessionsStatus {
    @Getter
    @EqualsAndHashCode.Include
    private final Long userId;

    @Getter
    @EqualsAndHashCode.Include
    private final UserStatus userStatus;

    @Getter
    @EqualsAndHashCode.Include
    private final Map<DeviceType, UserDeviceSessionInfo> deviceTypeToSessionInfo;

    private Map<String, Set<DeviceType>> activeNodeIdToDeviceTypes;
    private Set<String> activeNodeIds;
    private Set<DeviceType> activeDeviceTypes;

    public UserSessionsStatus(
            Long userId,
            UserStatus userStatus,
            @Nullable Map<DeviceType, UserDeviceSessionInfo> deviceTypeToSessionInfo) {
        this.userId = userId;
        this.userStatus = userStatus;
        this.deviceTypeToSessionInfo = deviceTypeToSessionInfo == null
                ? Collections.emptyMap()
                : deviceTypeToSessionInfo;
    }

    public boolean isOffline() {
        return UserStatus.OFFLINE == userStatus;
    }

    public UserStatus getUserStatus(boolean convertInvisibleToOffline) {
        return convertInvisibleToOffline && userStatus == UserStatus.INVISIBLE
                ? UserStatus.OFFLINE
                : userStatus;
    }

    @Nullable
    public String getNodeIdIfActive(DeviceType deviceType) {
        UserDeviceSessionInfo sessionInfo = deviceTypeToSessionInfo.get(deviceType);
        return (sessionInfo != null && sessionInfo.isActive())
                ? sessionInfo.getNodeId()
                : null;
    }

    @JsonIgnore
    public Set<String> getActiveNodeIds() {
        if (activeNodeIds != null) {
            return activeNodeIds;
        }
        Set<Map.Entry<DeviceType, UserDeviceSessionInfo>> entries =
                deviceTypeToSessionInfo.entrySet();
        int size = entries.size();
        activeNodeIds = switch (size) {
            case 0 -> Collections.emptySet();
            case 1 -> {
                Map.Entry<DeviceType, UserDeviceSessionInfo> next = entries.iterator()
                        .next();
                UserDeviceSessionInfo sessionInfo = next.getValue();
                yield sessionInfo.isActive()
                        ? Set.of(sessionInfo.getNodeId())
                        : Collections.emptySet();
            }
            default -> {
                Set<String> nodeIds = CollectionUtil.newSetWithExpectedSize(size);
                for (Map.Entry<DeviceType, UserDeviceSessionInfo> entry : entries) {
                    UserDeviceSessionInfo sessionInfo = entry.getValue();
                    if (sessionInfo.isActive()) {
                        nodeIds.add(sessionInfo.getNodeId());
                    }
                }
                yield nodeIds;
            }
        };
        return activeNodeIds;
    }

    @JsonIgnore
    public Set<DeviceType> getLoggedInDeviceTypes() {
        if (activeDeviceTypes != null) {
            return activeDeviceTypes;
        }
        Set<DeviceType> deviceTypes =
                CollectionUtil.newSetWithExpectedSize(deviceTypeToSessionInfo.size());
        for (Map.Entry<DeviceType, UserDeviceSessionInfo> entry : deviceTypeToSessionInfo
                .entrySet()) {
            UserDeviceSessionInfo sessionInfo = entry.getValue();
            if (sessionInfo.isActive()) {
                deviceTypes.add(entry.getKey());
            }
        }
        activeDeviceTypes = deviceTypes;
        return deviceTypes;
    }

    public boolean isDeviceLoggedIn(DeviceType deviceType) {
        UserDeviceSessionInfo sessionInfo = deviceTypeToSessionInfo.get(deviceType);
        return sessionInfo != null && sessionInfo.isActive();
    }

    @JsonIgnore
    public Map<String, Set<DeviceType>> getActiveNodeIdToDeviceTypes() {
        if (activeNodeIdToDeviceTypes != null) {
            return activeNodeIdToDeviceTypes;
        }
        Map<String, Set<DeviceType>> nodeIdToDeviceTypes =
                CollectionUtil.newMapWithExpectedSize(deviceTypeToSessionInfo.size());
        for (Map.Entry<DeviceType, UserDeviceSessionInfo> entry : deviceTypeToSessionInfo
                .entrySet()) {
            UserDeviceSessionInfo sessionInfo = entry.getValue();
            if (sessionInfo.isActive()) {
                nodeIdToDeviceTypes
                        .computeIfAbsent(sessionInfo.getNodeId(),
                                key -> CollectionUtil.newSetWithExpectedSize(2))
                        .add(entry.getKey());
            }
        }
        activeNodeIdToDeviceTypes = nodeIdToDeviceTypes;
        return nodeIdToDeviceTypes;
    }

}