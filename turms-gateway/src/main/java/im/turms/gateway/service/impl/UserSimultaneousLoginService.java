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

package im.turms.gateway.service.impl;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.SetMultimap;
import im.turms.common.constant.DeviceType;
import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.constraint.ValidDeviceType;
import im.turms.server.common.property.constant.LoginConflictStrategy;
import im.turms.server.common.property.constant.SimultaneousLoginStrategy;
import im.turms.server.common.util.DeviceTypeUtil;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.EnumSet;
import java.util.Set;

/**
 * @author James Chen
 */
@Service
public class UserSimultaneousLoginService {

    private final Node node;

    /**
     * device type -> exclusive device types
     */
    private SetMultimap<DeviceType, DeviceType> exclusiveDeviceTypes;

    /**
     * Forbidden device types can never connect to turms servers
     */
    private Set<DeviceType> forbiddenDeviceTypes;

    public UserSimultaneousLoginService(Node node) {
        this.node = node;
        applyStrategy(node.getSharedProperties()
                .getGateway()
                .getSimultaneousLogin()
                .getStrategy());
        node.addPropertiesChangeListener(turmsProperties ->
                applyStrategy(turmsProperties.getGateway().getSimultaneousLogin().getStrategy()));
    }

    public void applyStrategy(@NotNull SimultaneousLoginStrategy strategy) {
        exclusiveDeviceTypes = newExclusiveDeviceFromStrategy(strategy);
        forbiddenDeviceTypes = newForbiddenDeviceTypesFromStrategy(strategy);
    }

    public Set<DeviceType> getConflictedDeviceTypes(@NotNull @ValidDeviceType DeviceType deviceType) {
        return exclusiveDeviceTypes.get(deviceType);
    }

    public boolean isForbiddenDeviceType(DeviceType deviceType) {
        return forbiddenDeviceTypes.contains(deviceType);
    }

    public boolean shouldDisconnectLoggingInDeviceIfConflicts() {
        LoginConflictStrategy conflictStrategy = node
                .getSharedProperties()
                .getGateway()
                .getSimultaneousLogin()
                .getLoginConflictStrategy();
        return conflictStrategy == LoginConflictStrategy.DISCONNECT_LOGGING_IN_DEVICE;
    }

    private SetMultimap<DeviceType, DeviceType> newExclusiveDeviceFromStrategy(SimultaneousLoginStrategy strategy) {
        SetMultimap<DeviceType, DeviceType> newExclusiveDeviceTypes =
                HashMultimap.create(DeviceTypeUtil.ALL_AVAILABLE_DEVICE_TYPES.length, DeviceTypeUtil.ALL_AVAILABLE_DEVICE_TYPES.length);
        for (DeviceType deviceType : DeviceTypeUtil.ALL_AVAILABLE_DEVICE_TYPES) {
            // Every device type conflicts with itself
            newExclusiveDeviceTypes.put(deviceType, deviceType);
        }
        switch (strategy) {
            case ALLOW_ONE_DEVICE_OF_EACH_DEVICE_TYPE_ONLINE -> {
            }
            case ALLOW_ONE_DEVICE_FOR_ALL_DEVICE_TYPES_ONLINE -> {
                for (DeviceType type : DeviceTypeUtil.ALL_AVAILABLE_DEVICE_TYPES) {
                    addDeviceTypeConflictedWithAllTypes(newExclusiveDeviceTypes, type);
                }
            }
            case ALLOW_ONE_DEVICE_OF_DESKTOP_AND_ONE_DEVICE_OF_MOBILE_ONLINE,
                    ALLOW_ONE_DEVICE_OF_DESKTOP_AND_ONE_DEVICE_OF_BROWSER_AND_ONE_DEVICE_OF_MOBILE_ONLINE -> {
                addConflictedDeviceTypes(newExclusiveDeviceTypes, DeviceType.ANDROID, DeviceType.IOS);
            }
            case ALLOW_ONE_DEVICE_OF_DESKTOP_OR_BROWSER_AND_ONE_DEVICE_OF_MOBILE_ONLINE -> {
                addConflictedDeviceTypes(newExclusiveDeviceTypes, DeviceType.DESKTOP, DeviceType.BROWSER);
                addConflictedDeviceTypes(newExclusiveDeviceTypes, DeviceType.ANDROID, DeviceType.IOS);
            }
            case ALLOW_ONE_DEVICE_OF_DESKTOP_OR_MOBILE_ONLINE -> {
                addConflictedDeviceTypes(newExclusiveDeviceTypes, DeviceType.DESKTOP, DeviceType.ANDROID);
                addConflictedDeviceTypes(newExclusiveDeviceTypes, DeviceType.DESKTOP, DeviceType.IOS);
                addConflictedDeviceTypes(newExclusiveDeviceTypes, DeviceType.ANDROID, DeviceType.IOS);
            }
            case ALLOW_ONE_DEVICE_OF_DESKTOP_OR_BROWSER_OR_MOBILE_ONLINE -> {
                addConflictedDeviceTypes(newExclusiveDeviceTypes, DeviceType.DESKTOP, DeviceType.BROWSER);
                addConflictedDeviceTypes(newExclusiveDeviceTypes, DeviceType.DESKTOP, DeviceType.ANDROID);
                addConflictedDeviceTypes(newExclusiveDeviceTypes, DeviceType.DESKTOP, DeviceType.IOS);
                addConflictedDeviceTypes(newExclusiveDeviceTypes, DeviceType.BROWSER, DeviceType.ANDROID);
                addConflictedDeviceTypes(newExclusiveDeviceTypes, DeviceType.BROWSER, DeviceType.IOS);
                addConflictedDeviceTypes(newExclusiveDeviceTypes, DeviceType.ANDROID, DeviceType.IOS);
            }
        }
        return newExclusiveDeviceTypes;
    }

    private Set<DeviceType> newForbiddenDeviceTypesFromStrategy(SimultaneousLoginStrategy strategy) {
        Set<DeviceType> newForbiddenDeviceTypes = EnumSet.noneOf(DeviceType.class);
        switch (strategy) {
            case ALLOW_ONE_DEVICE_OF_DESKTOP_AND_ONE_DEVICE_OF_MOBILE_ONLINE,
                    ALLOW_ONE_DEVICE_OF_DESKTOP_OR_MOBILE_ONLINE -> newForbiddenDeviceTypes.add(DeviceType.BROWSER);
            case ALLOW_ONE_DEVICE_OF_EACH_DEVICE_TYPE_ONLINE,
                    ALLOW_ONE_DEVICE_FOR_ALL_DEVICE_TYPES_ONLINE,
                    ALLOW_ONE_DEVICE_OF_DESKTOP_OR_BROWSER_AND_ONE_DEVICE_OF_MOBILE_ONLINE,
                    ALLOW_ONE_DEVICE_OF_DESKTOP_AND_ONE_DEVICE_OF_BROWSER_AND_ONE_DEVICE_OF_MOBILE_ONLINE,
                    ALLOW_ONE_DEVICE_OF_DESKTOP_OR_BROWSER_OR_MOBILE_ONLINE -> {
            }
        }
        if (!node.getSharedProperties().getGateway().getSimultaneousLogin().isAllowDeviceTypeUnknownLogin()) {
            newForbiddenDeviceTypes.add(DeviceType.UNKNOWN);
        }
        if (!node.getSharedProperties().getGateway().getSimultaneousLogin().isAllowDeviceTypeOthersLogin()) {
            newForbiddenDeviceTypes.add(DeviceType.OTHERS);
        }
        return newForbiddenDeviceTypes;
    }

    private void addDeviceTypeConflictedWithAllTypes(
            Multimap<DeviceType, DeviceType> exclusiveDeviceTypes,
            @NotNull @ValidDeviceType DeviceType deviceType) {
        for (DeviceType type : DeviceTypeUtil.ALL_AVAILABLE_DEVICE_TYPES) {
            addConflictedDeviceTypes(exclusiveDeviceTypes, deviceType, type);
        }
    }

    private void addConflictedDeviceTypes(
            Multimap<DeviceType, DeviceType> exclusiveDeviceTypes,
            @NotNull @ValidDeviceType DeviceType deviceTypeOne,
            @NotNull @ValidDeviceType DeviceType deviceTypeTwo) {
        exclusiveDeviceTypes.put(deviceTypeOne, deviceTypeTwo);
        exclusiveDeviceTypes.put(deviceTypeTwo, deviceTypeOne);
    }

}
