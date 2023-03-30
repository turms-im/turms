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

package im.turms.gateway.domain.session.service;

import java.util.EnumSet;
import java.util.Map;
import java.util.Set;
import jakarta.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import im.turms.server.common.access.client.dto.constant.DeviceType;
import im.turms.server.common.domain.common.util.DeviceTypeUtil;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.exception.IncompatibleInternalChangeException;
import im.turms.server.common.infra.property.TurmsProperties;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.property.constant.LoginConflictStrategy;
import im.turms.server.common.infra.property.constant.SimultaneousLoginStrategy;
import im.turms.server.common.infra.property.env.gateway.SimultaneousLoginProperties;
import im.turms.server.common.infra.validation.ValidDeviceType;

/**
 * @author James Chen
 */
@Service
public class UserSimultaneousLoginService {

    private Map<DeviceType, Set<DeviceType>> deviceTypeToExclusiveDeviceTypes;

    /**
     * Forbidden device types can never connect to turms servers
     */
    private Set<DeviceType> forbiddenDeviceTypes;

    private boolean allowDeviceTypeUnknownLogin;
    private boolean allowDeviceTypeOthersLogin;
    private LoginConflictStrategy loginConflictStrategy;

    public UserSimultaneousLoginService(TurmsPropertiesManager propertiesManager) {
        propertiesManager.notifyAndAddGlobalPropertiesChangeListener(this::updateProperties);
    }

    private void updateProperties(TurmsProperties properties) {
        SimultaneousLoginProperties loginProperties = properties.getGateway()
                .getSimultaneousLogin();
        allowDeviceTypeUnknownLogin = loginProperties.isAllowDeviceTypeUnknownLogin();
        allowDeviceTypeOthersLogin = loginProperties.isAllowDeviceTypeOthersLogin();
        loginConflictStrategy = loginProperties.getLoginConflictStrategy();

        SimultaneousLoginStrategy simultaneousLoginStrategy = loginProperties.getStrategy();
        deviceTypeToExclusiveDeviceTypes =
                newExclusiveDeviceFromStrategy(simultaneousLoginStrategy);
        forbiddenDeviceTypes = newForbiddenDeviceTypesFromStrategy(simultaneousLoginStrategy);
    }

    public Set<DeviceType> getConflictedDeviceTypes(
            @NotNull @ValidDeviceType DeviceType deviceType) {
        return deviceTypeToExclusiveDeviceTypes.get(deviceType);
    }

    public boolean isForbiddenDeviceType(DeviceType deviceType) {
        return forbiddenDeviceTypes.contains(deviceType);
    }

    public boolean shouldDisconnectLoggingInDeviceIfConflicts() {
        return loginConflictStrategy == LoginConflictStrategy.DISCONNECT_LOGGING_IN_DEVICE;
    }

    private Map<DeviceType, Set<DeviceType>> newExclusiveDeviceFromStrategy(
            SimultaneousLoginStrategy strategy) {
        Map<DeviceType, Set<DeviceType>> newDeviceTypeToExclusiveDeviceTypes = CollectionUtil
                .newMapWithExpectedSize(DeviceTypeUtil.ALL_AVAILABLE_DEVICE_TYPES.length);
        for (DeviceType deviceType : DeviceTypeUtil.ALL_AVAILABLE_DEVICE_TYPES) {
            // Every device type conflicts with itself
            newDeviceTypeToExclusiveDeviceTypes
                    .computeIfAbsent(deviceType,
                            key -> CollectionUtil.newSetWithExpectedSize(
                                    DeviceTypeUtil.ALL_AVAILABLE_DEVICE_TYPES.length))
                    .add(deviceType);
        }
        switch (strategy) {
            case ALLOW_ONE_DEVICE_OF_EACH_DEVICE_TYPE_ONLINE -> {
            }
            case ALLOW_ONE_DEVICE_FOR_ALL_DEVICE_TYPES_ONLINE -> {
                for (DeviceType type : DeviceTypeUtil.ALL_AVAILABLE_DEVICE_TYPES) {
                    addDeviceTypeConflictedWithAllTypes(newDeviceTypeToExclusiveDeviceTypes, type);
                }
            }
            case ALLOW_ONE_DEVICE_OF_DESKTOP_AND_ONE_DEVICE_OF_MOBILE_ONLINE,
                    ALLOW_ONE_DEVICE_OF_DESKTOP_AND_ONE_DEVICE_OF_BROWSER_AND_ONE_DEVICE_OF_MOBILE_ONLINE -> {
                addConflictedDeviceTypes(newDeviceTypeToExclusiveDeviceTypes,
                        DeviceType.ANDROID,
                        DeviceType.IOS);
            }
            case ALLOW_ONE_DEVICE_OF_DESKTOP_OR_BROWSER_AND_ONE_DEVICE_OF_MOBILE_ONLINE -> {
                addConflictedDeviceTypes(newDeviceTypeToExclusiveDeviceTypes,
                        DeviceType.DESKTOP,
                        DeviceType.BROWSER);
                addConflictedDeviceTypes(newDeviceTypeToExclusiveDeviceTypes,
                        DeviceType.ANDROID,
                        DeviceType.IOS);
            }
            case ALLOW_ONE_DEVICE_OF_DESKTOP_OR_MOBILE_ONLINE -> {
                addConflictedDeviceTypes(newDeviceTypeToExclusiveDeviceTypes,
                        DeviceType.DESKTOP,
                        DeviceType.ANDROID);
                addConflictedDeviceTypes(newDeviceTypeToExclusiveDeviceTypes,
                        DeviceType.DESKTOP,
                        DeviceType.IOS);
                addConflictedDeviceTypes(newDeviceTypeToExclusiveDeviceTypes,
                        DeviceType.ANDROID,
                        DeviceType.IOS);
            }
            case ALLOW_ONE_DEVICE_OF_DESKTOP_OR_BROWSER_OR_MOBILE_ONLINE -> {
                addConflictedDeviceTypes(newDeviceTypeToExclusiveDeviceTypes,
                        DeviceType.DESKTOP,
                        DeviceType.BROWSER);
                addConflictedDeviceTypes(newDeviceTypeToExclusiveDeviceTypes,
                        DeviceType.DESKTOP,
                        DeviceType.ANDROID);
                addConflictedDeviceTypes(newDeviceTypeToExclusiveDeviceTypes,
                        DeviceType.DESKTOP,
                        DeviceType.IOS);
                addConflictedDeviceTypes(newDeviceTypeToExclusiveDeviceTypes,
                        DeviceType.BROWSER,
                        DeviceType.ANDROID);
                addConflictedDeviceTypes(newDeviceTypeToExclusiveDeviceTypes,
                        DeviceType.BROWSER,
                        DeviceType.IOS);
                addConflictedDeviceTypes(newDeviceTypeToExclusiveDeviceTypes,
                        DeviceType.ANDROID,
                        DeviceType.IOS);
            }
            default -> throw new IncompatibleInternalChangeException(
                    "Unexpected simultaneous login strategy: "
                            + strategy);
        }
        return Map.copyOf(
                CollectionUtil.transformValues(newDeviceTypeToExclusiveDeviceTypes, Set::copyOf));
    }

    private Set<DeviceType> newForbiddenDeviceTypesFromStrategy(
            SimultaneousLoginStrategy strategy) {
        Set<DeviceType> newForbiddenDeviceTypes = EnumSet.noneOf(DeviceType.class);
        switch (strategy) {
            case ALLOW_ONE_DEVICE_OF_DESKTOP_AND_ONE_DEVICE_OF_MOBILE_ONLINE,
                    ALLOW_ONE_DEVICE_OF_DESKTOP_OR_MOBILE_ONLINE ->
                newForbiddenDeviceTypes.add(DeviceType.BROWSER);
            case ALLOW_ONE_DEVICE_OF_EACH_DEVICE_TYPE_ONLINE,
                    ALLOW_ONE_DEVICE_FOR_ALL_DEVICE_TYPES_ONLINE,
                    ALLOW_ONE_DEVICE_OF_DESKTOP_OR_BROWSER_AND_ONE_DEVICE_OF_MOBILE_ONLINE,
                    ALLOW_ONE_DEVICE_OF_DESKTOP_AND_ONE_DEVICE_OF_BROWSER_AND_ONE_DEVICE_OF_MOBILE_ONLINE,
                    ALLOW_ONE_DEVICE_OF_DESKTOP_OR_BROWSER_OR_MOBILE_ONLINE ->
                {
                }
            default -> throw new IncompatibleInternalChangeException(
                    "Unexpected simultaneous login strategy: "
                            + strategy);
        }
        if (!allowDeviceTypeUnknownLogin) {
            newForbiddenDeviceTypes.add(DeviceType.UNKNOWN);
        }
        if (!allowDeviceTypeOthersLogin) {
            newForbiddenDeviceTypes.add(DeviceType.OTHERS);
        }
        return newForbiddenDeviceTypes;
    }

    private void addDeviceTypeConflictedWithAllTypes(
            Map<DeviceType, Set<DeviceType>> deviceTypeToExclusiveDeviceTypes,
            @NotNull @ValidDeviceType DeviceType deviceType) {
        for (DeviceType type : DeviceTypeUtil.ALL_AVAILABLE_DEVICE_TYPES) {
            addConflictedDeviceTypes(deviceTypeToExclusiveDeviceTypes, deviceType, type);
        }
    }

    private void addConflictedDeviceTypes(
            Map<DeviceType, Set<DeviceType>> deviceTypeToExclusiveDeviceTypes,
            @NotNull @ValidDeviceType DeviceType deviceTypeOne,
            @NotNull @ValidDeviceType DeviceType deviceTypeTwo) {
        deviceTypeToExclusiveDeviceTypes
                .computeIfAbsent(deviceTypeOne,
                        key -> CollectionUtil.newSetWithExpectedSize(
                                DeviceTypeUtil.ALL_AVAILABLE_DEVICE_TYPES.length))
                .add(deviceTypeTwo);
        deviceTypeToExclusiveDeviceTypes
                .computeIfAbsent(deviceTypeTwo,
                        key -> CollectionUtil.newSetWithExpectedSize(
                                DeviceTypeUtil.ALL_AVAILABLE_DEVICE_TYPES.length))
                .add(deviceTypeOne);
    }

}
