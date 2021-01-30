import TurmsClient from '../turms-client';
import {im} from '../model/proto-bundle';
import RequestUtil from '../util/request-util';
import {ParsedModel} from '../model/parsed-model';
import NotificationUtil from '../util/notification-util';
import TurmsBusinessError from '../model/turms-business-error';
import TurmsStatusCode from '../model/turms-status-code';
import GroupMemberRole = im.turms.proto.GroupMemberRole;
import GroupJoinQuestionsAnswerResult = im.turms.proto.GroupJoinQuestionsAnswerResult;

export default class GroupService {
    private _turmsClient: TurmsClient;

    constructor(turmsClient: TurmsClient) {
        this._turmsClient = turmsClient;
    }

    createGroup(
        name: string,
        intro?: string,
        announcement?: string,
        minimumScore?: number,
        muteEndDate?: Date,
        groupTypeId?: string): Promise<string> {
        if (RequestUtil.isFalsy(name)) {
            return TurmsBusinessError.notFalsyPromise('name');
        }
        return this._turmsClient.driver.send({
            createGroupRequest: {
                name,
                intro: RequestUtil.wrapValueIfNotNull(intro),
                announcement: RequestUtil.wrapValueIfNotNull(announcement),
                minimumScore: RequestUtil.wrapValueIfNotNull(minimumScore),
                muteEndDate: RequestUtil.wrapTimeIfNotNull(muteEndDate),
                groupTypeId: RequestUtil.wrapValueIfNotNull(groupTypeId)
            }
        }).then(n => NotificationUtil.getFirstVal(n, 'ids', true));
    }

    deleteGroup(groupId: string): Promise<void> {
        if (RequestUtil.isFalsy(groupId)) {
            return TurmsBusinessError.notFalsyPromise('groupId');
        }
        return this._turmsClient.driver.send({
            deleteGroupRequest: {
                groupId
            }
        }).then(() => null);
    }

    updateGroup(
        groupId: string,
        groupName?: string,
        intro?: string,
        announcement?: string,
        minimumScore?: number,
        groupTypeId?: string,
        muteEndDate?: Date,
        successorId?: string,
        quitAfterTransfer?: boolean): Promise<void> {
        if (RequestUtil.isFalsy(groupId)) {
            return TurmsBusinessError.notFalsyPromise('groupId');
        }
        if (RequestUtil.areAllFalsy(groupName, intro, announcement, minimumScore, groupTypeId,
            muteEndDate, successorId)) {
            return Promise.resolve();
        }
        return this._turmsClient.driver.send({
            updateGroupRequest: {
                groupId,
                groupName: RequestUtil.wrapValueIfNotNull(groupName),
                intro: RequestUtil.wrapValueIfNotNull(intro),
                announcement: RequestUtil.wrapValueIfNotNull(announcement),
                muteEndDate: RequestUtil.wrapTimeIfNotNull(muteEndDate),
                minimumScore: RequestUtil.wrapValueIfNotNull(minimumScore),
                groupTypeId: RequestUtil.wrapValueIfNotNull(groupTypeId),
                successorId: RequestUtil.wrapValueIfNotNull(successorId),
                quitAfterTransfer: RequestUtil.wrapValueIfNotNull(quitAfterTransfer)
            }
        }).then(() => null);
    }

    transferOwnership(groupId: string, successorId: string, quitAfterTransfer = false): Promise<void> {
        if (RequestUtil.isFalsy(groupId)) {
            return TurmsBusinessError.notFalsyPromise('groupId');
        }
        if (RequestUtil.isFalsy(successorId)) {
            return TurmsBusinessError.notFalsyPromise('successorId');
        }
        return this.updateGroup(groupId, null, null, null, null, null, null, successorId, quitAfterTransfer);
    }

    muteGroup(groupId: string, muteEndDate: Date): Promise<void> {
        if (RequestUtil.isFalsy(groupId)) {
            return TurmsBusinessError.notFalsyPromise('groupId');
        }
        if (RequestUtil.isFalsy(muteEndDate)) {
            return TurmsBusinessError.notFalsyPromise('muteEndDate');
        }
        return this.updateGroup(groupId, null, null, null, null, null, muteEndDate, null);
    }

    unmuteGroup(groupId: string): Promise<void> {
        return this.muteGroup(groupId, new Date(0));
    }

    queryGroup(groupId: string, lastUpdatedDate?: Date): Promise<ParsedModel.GroupWithVersion | undefined> {
        if (RequestUtil.isFalsy(groupId)) {
            return TurmsBusinessError.notFalsyPromise('groupId');
        }
        return this._turmsClient.driver.send({
            queryGroupRequest: {
                groupId,
                lastUpdatedDate: RequestUtil.wrapTimeIfNotNull(lastUpdatedDate)
            }
        }).then(n => {
            const group = NotificationUtil.getAndTransform(n, 'groupsWithVersion.groups.0');
            if (group) {
                return {
                    group,
                    lastUpdatedDate: NotificationUtil.getVerDate(n, 'groupsWithVersion')
                };
            }
        });
    }

    queryJoinedGroupIds(lastUpdatedDate?: Date): Promise<ParsedModel.IdsWithVersion | undefined> {
        return this._turmsClient.driver.send({
            queryJoinedGroupIdsRequest: {
                lastUpdatedDate: RequestUtil.wrapTimeIfNotNull(lastUpdatedDate)
            }
        }).then(n => NotificationUtil.getIdsWithVer(n));
    }

    queryJoinedGroupInfos(lastUpdatedDate?: Date): Promise<ParsedModel.GroupsWithVersion | undefined> {
        return this._turmsClient.driver.send({
            queryJoinedGroupInfosRequest: {
                lastUpdatedDate: RequestUtil.wrapTimeIfNotNull(lastUpdatedDate)
            }
        }).then(n => NotificationUtil.getAndTransform(n, 'groupsWithVersion'));
    }

    addGroupJoinQuestion(groupId: string, question: string, answers: string[], score: number): Promise<string> {
        if (RequestUtil.isFalsy(groupId)) {
            return TurmsBusinessError.notFalsyPromise('groupId');
        }
        if (RequestUtil.isFalsy(question)) {
            return TurmsBusinessError.notFalsyPromise('question');
        }
        if (RequestUtil.isFalsy(answers)) {
            return TurmsBusinessError.notFalsyPromise('answers', true);
        }
        if (RequestUtil.isFalsy(score)) {
            return TurmsBusinessError.notFalsyPromise('score');
        }
        return this._turmsClient.driver.send({
            createGroupJoinQuestionRequest: {
                groupId,
                question,
                answers,
                score
            }
        }).then(n => NotificationUtil.getFirstVal(n, 'ids', true));
    }

    deleteGroupJoinQuestion(questionId: string): Promise<void> {
        if (RequestUtil.isFalsy(questionId)) {
            return TurmsBusinessError.notFalsyPromise('questionId');
        }
        return this._turmsClient.driver.send({
            deleteGroupJoinQuestionRequest: {
                questionId
            }
        }).then(() => null);
    }

    updateGroupJoinQuestion(questionId: string, question?: string, answers?: string[], score?: number): Promise<void> {
        if (RequestUtil.isFalsy(questionId)) {
            return TurmsBusinessError.notFalsyPromise('questionId');
        }
        if (RequestUtil.areAllFalsy(question, answers, score)) {
            return Promise.resolve();
        }
        return this._turmsClient.driver.send({
            updateGroupJoinQuestionRequest: {
                questionId: questionId,
                question: RequestUtil.wrapValueIfNotNull(question),
                answers: answers,
                score: RequestUtil.wrapValueIfNotNull(score)
            }
        }).then(() => null);
    }

    // Group Blocklist
    blockUser(groupId: string, userId: string): Promise<void> {
        if (RequestUtil.isFalsy(groupId)) {
            return TurmsBusinessError.notFalsyPromise('groupId');
        }
        if (RequestUtil.isFalsy(userId)) {
            return TurmsBusinessError.notFalsyPromise('userId');
        }
        return this._turmsClient.driver.send({
            createGroupBlockedUserRequest: {
                userId,
                groupId
            }
        }).then(() => null);
    }

    unblockUser(groupId: string, userId: string): Promise<void> {
        if (RequestUtil.isFalsy(groupId)) {
            return TurmsBusinessError.notFalsyPromise('groupId');
        }
        if (RequestUtil.isFalsy(userId)) {
            return TurmsBusinessError.notFalsyPromise('userId');
        }
        return this._turmsClient.driver.send({
            deleteGroupBlockedUserRequest: {
                groupId,
                userId
            }
        }).then(() => null);
    }

    queryBlockedUserIds(
        groupId: string,
        lastUpdatedDate?: Date): Promise<ParsedModel.IdsWithVersion | undefined> {
        if (RequestUtil.isFalsy(groupId)) {
            return TurmsBusinessError.notFalsyPromise('groupId');
        }
        return this._turmsClient.driver.send({
            queryGroupBlockedUserIdsRequest: {
                groupId,
                lastUpdatedDate: RequestUtil.wrapTimeIfNotNull(lastUpdatedDate)
            }
        }).then(n => NotificationUtil.getIdsWithVer(n));
    }

    queryBlockedUserInfos(
        groupId: string,
        lastUpdatedDate?: Date): Promise<ParsedModel.UsersInfosWithVersion | undefined> {
        if (RequestUtil.isFalsy(groupId)) {
            return TurmsBusinessError.notFalsyPromise('groupId');
        }
        return this._turmsClient.driver.send({
            queryGroupBlockedUserInfosRequest: {
                groupId,
                lastUpdatedDate: RequestUtil.wrapTimeIfNotNull(lastUpdatedDate)
            }
        }).then(n => NotificationUtil.getAndTransform(n, 'usersInfosWithVersion'));
    }

    // Group Enrollment
    createInvitation(groupId: string, inviteeId: string, content: string): Promise<string> {
        if (RequestUtil.isFalsy(groupId)) {
            return TurmsBusinessError.notFalsyPromise('groupId');
        }
        if (RequestUtil.isFalsy(inviteeId)) {
            return TurmsBusinessError.notFalsyPromise('inviteeId');
        }
        if (RequestUtil.isFalsy(content)) {
            return TurmsBusinessError.notFalsyPromise('content');
        }
        return this._turmsClient.driver.send({
            createGroupInvitationRequest: {
                groupId,
                inviteeId,
                content
            }
        }).then(n => NotificationUtil.getFirstVal(n, 'ids', true));
    }

    deleteInvitation(invitationId: string): Promise<void> {
        if (RequestUtil.isFalsy(invitationId)) {
            return TurmsBusinessError.notFalsyPromise('invitationId');
        }
        return this._turmsClient.driver.send({
            deleteGroupInvitationRequest: {
                invitationId
            }
        }).then(() => null);
    }

    queryInvitationsByGroupId(groupId: string, lastUpdatedDate?: Date): Promise<ParsedModel.GroupInvitationsWithVersion | undefined> {
        if (RequestUtil.isFalsy(groupId)) {
            return TurmsBusinessError.notFalsyPromise('groupId');
        }
        return this._turmsClient.driver.send({
            queryGroupInvitationsRequest: {
                groupId: RequestUtil.wrapValueIfNotNull(groupId),
                lastUpdatedDate: RequestUtil.wrapTimeIfNotNull(lastUpdatedDate)
            }
        }).then(n => NotificationUtil.getAndTransform(n, 'groupInvitationsWithVersion'));
    }

    queryInvitations(areSentByMe: boolean, lastUpdatedDate?: Date): Promise<ParsedModel.GroupInvitationsWithVersion | undefined> {
        if (RequestUtil.isFalsy(areSentByMe)) {
            return TurmsBusinessError.notFalsyPromise('areSentByMe');
        }
        return this._turmsClient.driver.send({
            queryGroupInvitationsRequest: {
                areSentByMe: RequestUtil.wrapValueIfNotNull(areSentByMe),
                lastUpdatedDate: RequestUtil.wrapTimeIfNotNull(lastUpdatedDate)
            }
        }).then(n => NotificationUtil.getAndTransform(n, 'groupInvitationsWithVersion'));
    }

    createJoinRequest(groupId: string, content: string): Promise<string> {
        if (RequestUtil.isFalsy(groupId)) {
            return TurmsBusinessError.notFalsyPromise('groupId');
        }
        if (RequestUtil.isFalsy(content)) {
            return TurmsBusinessError.notFalsyPromise('content');
        }
        return this._turmsClient.driver.send({
            createGroupJoinRequestRequest: {
                groupId,
                content
            }
        }).then(n => NotificationUtil.getFirstVal(n, 'ids', true));
    }

    deleteJoinRequest(requestId: string): Promise<void> {
        if (RequestUtil.isFalsy(requestId)) {
            return TurmsBusinessError.notFalsyPromise('requestId');
        }
        return this._turmsClient.driver.send({
            deleteGroupJoinRequestRequest: {
                requestId
            }
        }).then(() => null);
    }

    queryJoinRequests(groupId: string, lastUpdatedDate?: Date): Promise<ParsedModel.GroupJoinRequestsWithVersion | undefined> {
        if (RequestUtil.isFalsy(groupId)) {
            return TurmsBusinessError.notFalsyPromise('groupId');
        }
        return this._turmsClient.driver.send({
            queryGroupJoinRequestsRequest: {
                groupId: RequestUtil.wrapValueIfNotNull(groupId),
                lastUpdatedDate: RequestUtil.wrapTimeIfNotNull(lastUpdatedDate)
            }
        }).then(n => NotificationUtil.getAndTransform(n, 'groupJoinRequestsWithVersion'));
    }

    querySentJoinRequests(lastUpdatedDate?: Date): Promise<ParsedModel.GroupJoinRequestsWithVersion | undefined> {
        return this._turmsClient.driver.send({
            queryGroupJoinRequestsRequest: {
                lastUpdatedDate: RequestUtil.wrapTimeIfNotNull(lastUpdatedDate)
            }
        }).then(n => NotificationUtil.getAndTransform(n, 'groupJoinRequestsWithVersion'));
    }

    /**
     * Note: Only the owner and managers have the right to fetch questions with answers
     */
    queryGroupJoinQuestionsRequest(
        groupId: string,
        withAnswers = false,
        lastUpdatedDate?: Date): Promise<ParsedModel.GroupJoinQuestionsWithVersion | undefined> {
        if (RequestUtil.isFalsy(groupId)) {
            return TurmsBusinessError.notFalsyPromise('groupId');
        }
        return this._turmsClient.driver.send({
            queryGroupJoinQuestionsRequest: {
                groupId,
                withAnswers,
                lastUpdatedDate: RequestUtil.wrapTimeIfNotNull(lastUpdatedDate)
            }
        }).then(n => NotificationUtil.getAndTransform(n, 'groupJoinQuestionsWithVersion'));
    }

    answerGroupQuestions(questionIdsAndAnswers: { [k: string]: string }): Promise<GroupJoinQuestionsAnswerResult | undefined> {
        if (RequestUtil.isFalsy(questionIdsAndAnswers)) {
            return TurmsBusinessError.notFalsyPromise('questionIdsAndAnswers', true);
        }
        return this._turmsClient.driver.send({
            checkGroupJoinQuestionsAnswersRequest: {
                questionIdAndAnswer: questionIdsAndAnswers
            }
        }).then(n => {
            const result = NotificationUtil.get(n, 'groupJoinQuestionsAnswerResult');
            if (result) {
                return result;
            } else {
                throw TurmsBusinessError.fromCode(TurmsStatusCode.INVALID_RESPONSE);
            }
        });
    }

    // Group Member
    addGroupMember(
        groupId: string,
        userId: string,
        name?: string,
        role?: string | GroupMemberRole,
        muteEndDate?: Date): Promise<void> {
        if (RequestUtil.isFalsy(groupId)) {
            return TurmsBusinessError.notFalsyPromise('groupId');
        }
        if (RequestUtil.isFalsy(userId)) {
            return TurmsBusinessError.notFalsyPromise('userId');
        }
        if (typeof role === 'string') {
            role = GroupMemberRole[role] as GroupMemberRole;
            if (RequestUtil.isFalsy(role)) {
                return TurmsBusinessError.notFalsyPromise('role');
            }
        }
        return this._turmsClient.driver.send({
            createGroupMemberRequest: {
                groupId,
                userId,
                name: RequestUtil.wrapValueIfNotNull(name),
                role,
                muteEndDate: RequestUtil.wrapTimeIfNotNull(muteEndDate)
            }
        }).then(() => null);
    }

    quitGroup(groupId: string, successorId?: string, quitAfterTransfer?: boolean): Promise<void> {
        if (RequestUtil.isFalsy(groupId)) {
            return TurmsBusinessError.notFalsyPromise('groupId');
        }
        return this._turmsClient.driver.send({
            deleteGroupMemberRequest: {
                groupId,
                memberId: this._turmsClient.userService.userInfo.userId,
                successorId: RequestUtil.wrapValueIfNotNull(successorId),
                quitAfterTransfer: RequestUtil.wrapValueIfNotNull(quitAfterTransfer)
            }
        }).then(() => null);
    }

    removeGroupMember(groupId: string, memberId: string): Promise<void> {
        if (RequestUtil.isFalsy(groupId)) {
            return TurmsBusinessError.notFalsyPromise('groupId');
        }
        if (RequestUtil.isFalsy(memberId)) {
            return TurmsBusinessError.notFalsyPromise('memberId');
        }
        return this._turmsClient.driver.send({
            deleteGroupMemberRequest: {
                groupId,
                memberId
            }
        }).then(() => null);
    }

    updateGroupMemberInfo(
        groupId: string,
        memberId: string,
        name?: string,
        role?: string | GroupMemberRole,
        muteEndDate?: Date): Promise<void> {
        if (RequestUtil.isFalsy(groupId)) {
            return TurmsBusinessError.notFalsyPromise('groupId');
        }
        if (RequestUtil.isFalsy(memberId)) {
            return TurmsBusinessError.notFalsyPromise('memberId');
        }
        if (RequestUtil.areAllFalsy(name, role, muteEndDate)) {
            return Promise.resolve();
        }
        if (typeof role === 'string') {
            role = GroupMemberRole[role] as GroupMemberRole;
            if (RequestUtil.isFalsy(role)) {
                return TurmsBusinessError.notFalsyPromise('role');
            }
        }
        return this._turmsClient.driver.send({
            updateGroupMemberRequest: {
                groupId,
                memberId,
                name: RequestUtil.wrapValueIfNotNull(name),
                role,
                muteEndDate: RequestUtil.wrapTimeIfNotNull(muteEndDate)
            }
        }).then(() => null);
    }

    muteGroupMember(groupId: string, memberId: string, muteEndDate: Date): Promise<void> {
        if (RequestUtil.isFalsy(groupId)) {
            return TurmsBusinessError.notFalsyPromise('groupId');
        }
        if (RequestUtil.isFalsy(memberId)) {
            return TurmsBusinessError.notFalsyPromise('memberId');
        }
        if (RequestUtil.isFalsy(muteEndDate)) {
            return TurmsBusinessError.notFalsyPromise('muteEndDate');
        }
        return this.updateGroupMemberInfo(groupId, memberId, undefined, undefined, muteEndDate);
    }

    unmuteGroupMember(groupId: string, memberId: string): Promise<void> {
        return this.muteGroupMember(groupId, memberId, new Date(0));
    }

    queryGroupMembers(groupId: string, withStatus = false, lastUpdatedDate?: Date): Promise<ParsedModel.GroupMembersWithVersion | undefined> {
        if (RequestUtil.isFalsy(groupId)) {
            return TurmsBusinessError.notFalsyPromise('groupId');
        }
        return this._turmsClient.driver.send({
            queryGroupMembersRequest: {
                groupId,
                lastUpdatedDate: RequestUtil.wrapTimeIfNotNull(lastUpdatedDate),
                withStatus: RequestUtil.wrapValueIfNotNull(withStatus)
            }
        }).then(n => NotificationUtil.getAndTransform(n, 'groupMembersWithVersion'));
    }

    queryGroupMembersByMemberIds(groupId: string, memberIds: string[], withStatus = false): Promise<ParsedModel.GroupMembersWithVersion | undefined> {
        if (RequestUtil.isFalsy(groupId)) {
            return TurmsBusinessError.notFalsyPromise('groupId');
        }
        if (RequestUtil.isFalsy(memberIds)) {
            return TurmsBusinessError.notFalsyPromise('memberIds', true);
        }
        return this._turmsClient.driver.send({
            queryGroupMembersRequest: {
                groupId,
                memberIds,
                withStatus: RequestUtil.wrapValueIfNotNull(withStatus)
            }
        }).then(n => NotificationUtil.getAndTransform(n, 'groupMembersWithVersion'));
    }
}