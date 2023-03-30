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

package im.turms.service.domain.common.validation;

import im.turms.server.common.access.client.dto.constant.DeviceType;
import im.turms.server.common.access.client.dto.constant.GroupMemberRole;
import im.turms.server.common.access.client.dto.constant.ProfileAccessStrategy;
import im.turms.server.common.access.client.dto.constant.RequestStatus;
import im.turms.server.common.access.client.dto.constant.ResponseAction;
import im.turms.server.common.infra.exception.ResponseException;
import im.turms.service.domain.group.bo.GroupQuestionIdAndAnswer;
import im.turms.service.domain.group.bo.NewGroupQuestion;
import im.turms.service.domain.group.po.GroupBlockedUser;
import im.turms.service.domain.group.po.GroupMember;
import im.turms.service.domain.user.po.UserRelationship;
import im.turms.service.domain.user.po.UserRelationshipGroup;

import static im.turms.server.common.access.common.ResponseStatusCode.ILLEGAL_ARGUMENT;

/**
 * @author James Chen
 */
public final class DataValidator {

    // common
    private static final ResponseException UNRECOGNIZED_REQUEST_STATUS =
            ResponseException.get(ILLEGAL_ARGUMENT, "The request status must not be UNRECOGNIZED");
    private static final ResponseException UNRECOGNIZED_RESPONSE_ACTION =
            ResponseException.get(ILLEGAL_ARGUMENT, "The response action must not be UNRECOGNIZED");
    private static final ResponseException UNRECOGNIZED_DEVICE_TYPE =
            ResponseException.get(ILLEGAL_ARGUMENT, "The device type must not be UNRECOGNIZED");
    // user
    private static final ResponseException UNRECOGNIZED_PROFILE_ACCESS_STRATEGY = ResponseException
            .get(ILLEGAL_ARGUMENT, "The profile access strategy must not be UNRECOGNIZED");
    // user - relationship
    private static final ResponseException NULL_USER_RELATIONSHIP_KEY =
            ResponseException.get(ILLEGAL_ARGUMENT, "The user relationship key must not be null");
    private static final ResponseException NULL_USER_RELATIONSHIP_GROUP_KEY = ResponseException
            .get(ILLEGAL_ARGUMENT, "The user relationship group key must not be null");
    // group
    private static final ResponseException NULL_GROUP_MEMBER_KEY =
            ResponseException.get(ILLEGAL_ARGUMENT, "The group member key must not be null");
    private static final ResponseException UNRECOGNIZED_GROUP_MEMBER_ROLE = ResponseException
            .get(ILLEGAL_ARGUMENT, "The group member role must not be UNRECOGNIZED");
    private static final ResponseException NULL_GROUP_BLOCKED_USER_KEY =
            ResponseException.get(ILLEGAL_ARGUMENT, "The group blocked user key must not be null");
    private static final ResponseException EMPTY_GROUP_QUESTION_ANSWERS = ResponseException
            .get(ILLEGAL_ARGUMENT, "The answers of a new group question should not be empty");
    private static final ResponseException ILLEGAL_GROUP_QUESTION_SCORE = ResponseException.get(
            ILLEGAL_ARGUMENT,
            "The score of a new group question should not be null and must be greater than or equal to 0");
    private static final ResponseException ILLEGAL_GROUP_QUESTION_ID_AND_ANSWER =
            ResponseException.get(ILLEGAL_ARGUMENT, "The question ID and answer must not be null");

    private DataValidator() {
    }

    // region common
    public static void validRequestStatus(RequestStatus status) {
        if (status == RequestStatus.UNRECOGNIZED) {
            throw UNRECOGNIZED_REQUEST_STATUS;
        }
    }

    public static void validResponseAction(ResponseAction action) {
        if (action == ResponseAction.UNRECOGNIZED) {
            throw UNRECOGNIZED_RESPONSE_ACTION;
        }
    }

    public static void validDeviceType(DeviceType deviceType) {
        if (deviceType == DeviceType.UNRECOGNIZED) {
            throw UNRECOGNIZED_DEVICE_TYPE;
        }
    }

    // endregion

    // region user
    public static void validProfileAccess(ProfileAccessStrategy value) {
        if (value == ProfileAccessStrategy.UNRECOGNIZED) {
            throw UNRECOGNIZED_PROFILE_ACCESS_STRATEGY;
        }
    }
    // endregion

    // region user - relationship
    public static void validRelationshipKey(UserRelationship.Key key) {
        if (key == null || key.getOwnerId() == null || key.getRelatedUserId() == null) {
            throw NULL_USER_RELATIONSHIP_KEY;
        }
    }

    public static void validRelationshipGroupKey(UserRelationshipGroup.Key key) {
        if (key != null && key.getOwnerId() != null && key.getGroupIndex() != null) {
            throw NULL_USER_RELATIONSHIP_GROUP_KEY;
        }
    }
    // endregion

    // region group
    public static void validGroupMemberKey(GroupMember.Key key) {
        if (key == null || key.getGroupId() == null || key.getUserId() == null) {
            throw NULL_GROUP_MEMBER_KEY;
        }
    }

    public static void validGroupMemberRole(GroupMemberRole role) {
        if (role == GroupMemberRole.UNRECOGNIZED) {
            throw UNRECOGNIZED_GROUP_MEMBER_ROLE;
        }
    }

    public static void validGroupBlockedUserKey(GroupBlockedUser.Key key) {
        if (key == null || key.getGroupId() == null || key.getUserId() == null) {
            throw NULL_GROUP_BLOCKED_USER_KEY;
        }
    }

    public static void validNewGroupQuestion(NewGroupQuestion question) {
        if (question.answers()
                .isEmpty()) {
            throw EMPTY_GROUP_QUESTION_ANSWERS;
        }
        Integer score = question.score();
        if (score == null || score < 0) {
            throw ILLEGAL_GROUP_QUESTION_SCORE;
        }
    }

    public static void validGroupQuestionIdAndAnswer(GroupQuestionIdAndAnswer value) {
        if (value == null || value.id() == null || value.answer() == null) {
            throw ILLEGAL_GROUP_QUESTION_ID_AND_ANSWER;
        }
    }
    // endregion

}