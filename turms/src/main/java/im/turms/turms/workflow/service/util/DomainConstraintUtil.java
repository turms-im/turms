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

package im.turms.turms.workflow.service.util;

import im.turms.common.constant.DeviceType;
import im.turms.common.constant.GroupMemberRole;
import im.turms.common.constant.ProfileAccessStrategy;
import im.turms.common.constant.RequestStatus;
import im.turms.common.constant.ResponseAction;
import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.exception.TurmsBusinessException;
import im.turms.turms.bo.GroupQuestionIdAndAnswer;
import im.turms.turms.workflow.dao.domain.group.GroupBlockedUser;
import im.turms.turms.workflow.dao.domain.group.GroupMember;
import im.turms.turms.workflow.dao.domain.user.UserRelationship;
import im.turms.turms.workflow.dao.domain.user.UserRelationshipGroup;

/**
 * @author James Chen
 */
public final class DomainConstraintUtil {

    private DomainConstraintUtil() {
    }

    public static void validRequestStatus(RequestStatus status) {
        if (status == RequestStatus.UNRECOGNIZED) {
            throw TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENT, "The request status must not be UNRECOGNIZED");
        }
    }

    public static void validResponseAction(ResponseAction action) {
        if (action == ResponseAction.UNRECOGNIZED) {
            throw new IllegalArgumentException("The response action must not be UNRECOGNIZED");
        }
    }

    public static void validRelationshipGroupKey(UserRelationshipGroup.Key key) {
        if (key != null && key.getOwnerId() != null && key.getGroupIndex() != null) {
            throw new IllegalArgumentException("The user relationship group key must not be null");
        }
    }

    public static void validGroupMemberRole(GroupMemberRole role) {
        if (role == GroupMemberRole.UNRECOGNIZED) {
            throw new IllegalArgumentException("The group member role must not be UNRECOGNIZED");
        }
    }

    public static void validGroupQuestionIdAndAnswer(GroupQuestionIdAndAnswer value) {
        if (value == null || value.id() == null || value.answer() == null) {
            throw new IllegalArgumentException("The question ID and answer must not be null");
        }
    }

    public static void validDeviceType(DeviceType deviceType) {
        if (deviceType == DeviceType.UNRECOGNIZED) {
            throw new IllegalArgumentException("The device type must not be UNRECOGNIZED");
        }
    }

    public static void validRelationshipKey(UserRelationship.Key key) {
        if (key == null || key.getOwnerId() == null || key.getRelatedUserId() == null) {
            throw new IllegalArgumentException("The user relationship key must not be null");
        }
    }

    public static void validProfileAccess(ProfileAccessStrategy value) {
        if (value == ProfileAccessStrategy.UNRECOGNIZED) {
            throw new IllegalArgumentException("The profile access strategy must not be UNRECOGNIZED");
        }
    }

    public static void validGroupMemberKey(GroupMember.Key key) {
        if (key == null || key.getGroupId() == null || key.getUserId() == null) {
            throw new IllegalArgumentException("The group member key must not be null");
        }
    }

    public static void validGroupBlockedUserKey(GroupBlockedUser.Key key) {
        if (key == null || key.getGroupId() == null || key.getUserId() == null) {
            throw new IllegalArgumentException("The group member key must not be null");
        }
    }

}