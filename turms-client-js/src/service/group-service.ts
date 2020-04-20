import TurmsClient from "../turms-client";
import ConstantTransformer from "../util/constant-transformer";
import {im} from "../model/proto-bundle";
import RequestUtil from "../util/request-util";
import {ParsedModel} from "../model/parsed-model";
import NotificationUtil from "../util/notification-util";
import TurmsBusinessException from "../model/turms-business-exception";
import TurmsStatusCode from "../model/turms-status-code";
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
        RequestUtil.throwIfAnyFalsy(name);
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
        RequestUtil.throwIfAnyFalsy(groupId);
        return this._turmsClient.driver.send({
            deleteGroupRequest: {
                groupId
            }
            // eslint-disable-next-line @typescript-eslint/no-empty-function
        }).then(() => {
        });
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
        RequestUtil.throwIfAnyFalsy(groupId);
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
            // eslint-disable-next-line @typescript-eslint/no-empty-function
        }).then(() => {
        });
    }

    transferOwnership(groupId: string, successorId: string, quitAfterTransfer = false): Promise<void> {
        RequestUtil.throwIfAnyFalsy(groupId, successorId);
        return this.updateGroup(groupId, null, null, null, null, null, null, successorId, quitAfterTransfer);
    }

    muteGroup(groupId: string, muteEndDate: Date): Promise<void> {
        RequestUtil.throwIfAnyFalsy(groupId, muteEndDate);
        return this.updateGroup(groupId, null, null, null, null, null, muteEndDate, null);
    }

    unmuteGroup(groupId: string): Promise<void> {
        return this.muteGroup(groupId, new Date(0));
    }

    queryGroup(groupId: string, lastUpdatedDate?: Date): Promise<ParsedModel.GroupWithVersion | undefined> {
        RequestUtil.throwIfAnyFalsy(groupId);
        // @ts-ignore
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

    queryJoinedGroupsIds(lastUpdatedDate?: Date): Promise<ParsedModel.IdsWithVersion | undefined> {
        return this._turmsClient.driver.send({
            queryJoinedGroupsIdsRequest: {
                lastUpdatedDate: RequestUtil.wrapTimeIfNotNull(lastUpdatedDate)
            }
        }).then(n => NotificationUtil.getIdsWithVer(n));
    }

    queryJoinedGroupsInfos(lastUpdatedDate?: Date): Promise<ParsedModel.GroupsWithVersion | undefined> {
        // @ts-ignore
        return this._turmsClient.driver.send({
            queryJoinedGroupsInfosRequest: {
                lastUpdatedDate: RequestUtil.wrapTimeIfNotNull(lastUpdatedDate)
            }
        }).then(n => NotificationUtil.getAndTransform(n, 'groupsWithVersion'));
    }

    addGroupJoinQuestion(groupId: string, question: string, answers: string[], score: number): Promise<string> {
        RequestUtil.throwIfAnyFalsy(groupId, question, answers, score);
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
        RequestUtil.throwIfAnyFalsy(questionId);
        return this._turmsClient.driver.send({
            deleteGroupJoinQuestionRequest: {
                questionId
            }
            // eslint-disable-next-line @typescript-eslint/no-empty-function
        }).then(() => {
        });
    }

    updateGroupJoinQuestion(questionId: string, question?: string, answers?: string[], score?: number): Promise<void> {
        RequestUtil.throwIfAnyFalsy(questionId);
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
            // eslint-disable-next-line @typescript-eslint/no-empty-function
        }).then(() => {
        });
    }

    // Group Blacklist
    blacklistUser(groupId: string, userId: string): Promise<void> {
        RequestUtil.throwIfAnyFalsy(groupId, userId);
        return this._turmsClient.driver.send({
            createGroupBlacklistedUserRequest: {
                blacklistedUserId: userId,
                groupId
            }
            // eslint-disable-next-line @typescript-eslint/no-empty-function
        }).then(() => {
        });
    }

    unblacklistUser(groupId: string, userId: string): Promise<void> {
        RequestUtil.throwIfAnyFalsy(groupId, userId);
        return this._turmsClient.driver.send({
            deleteGroupBlacklistedUserRequest: {
                groupId,
                unblacklistedUserId: userId
            }
            // eslint-disable-next-line @typescript-eslint/no-empty-function
        }).then(() => {
        });
    }

    queryBlacklistedUsersIds(
        groupId: string,
        lastUpdatedDate?: Date): Promise<ParsedModel.IdsWithVersion | undefined> {
        RequestUtil.throwIfAnyFalsy(groupId);
        return this._turmsClient.driver.send({
            queryGroupBlacklistedUsersIdsRequest: {
                groupId,
                lastUpdatedDate: RequestUtil.wrapTimeIfNotNull(lastUpdatedDate)
            }
        }).then(n => NotificationUtil.getIdsWithVer(n));
    }

    queryBlacklistedUsersInfos(
        groupId: string,
        lastUpdatedDate?: Date): Promise<ParsedModel.UsersInfosWithVersion | undefined> {
        RequestUtil.throwIfAnyFalsy(groupId);
        // @ts-ignore
        return this._turmsClient.driver.send({
            queryGroupBlacklistedUsersInfosRequest: {
                groupId,
                lastUpdatedDate: RequestUtil.wrapTimeIfNotNull(lastUpdatedDate)
            }
        }).then(n => NotificationUtil.getAndTransform(n, 'usersInfosWithVersion'));
    }

    // Group Enrollment
    createInvitation(groupId: string, inviteeId: string, content: string): Promise<string> {
        RequestUtil.throwIfAnyFalsy(groupId, inviteeId, content);
        return this._turmsClient.driver.send({
            createGroupInvitationRequest: {
                groupId,
                inviteeId,
                content
            }
        }).then(n => NotificationUtil.getFirstVal(n, 'ids', true));
    }

    deleteInvitation(invitationId: string): Promise<void> {
        RequestUtil.throwIfAnyFalsy(invitationId);
        return this._turmsClient.driver.send({
            deleteGroupInvitationRequest: {
                invitationId
            }
            // eslint-disable-next-line @typescript-eslint/no-empty-function
        }).then(() => {
        });
    }

    queryInvitationsByGroupId(groupId: string, lastUpdatedDate?: Date): Promise<ParsedModel.GroupInvitationsWithVersion | undefined> {
        RequestUtil.throwIfAnyFalsy(groupId);
        // @ts-ignore
        return this._turmsClient.driver.send({
            queryGroupInvitationsRequest: {
                groupId: RequestUtil.wrapValueIfNotNull(groupId),
                lastUpdatedDate: RequestUtil.wrapTimeIfNotNull(lastUpdatedDate)
            }
        }).then(n => NotificationUtil.getAndTransform(n, 'groupInvitationsWithVersion'));
    }

    queryInvitations(areSentByMe: boolean, lastUpdatedDate?: Date): Promise<ParsedModel.GroupInvitationsWithVersion | undefined> {
        RequestUtil.throwIfAnyFalsy(areSentByMe);
        // @ts-ignore
        return this._turmsClient.driver.send({
            queryGroupInvitationsRequest: {
                areSentByMe: RequestUtil.wrapValueIfNotNull(areSentByMe),
                lastUpdatedDate: RequestUtil.wrapTimeIfNotNull(lastUpdatedDate)
            }
        }).then(n => NotificationUtil.getAndTransform(n, 'groupInvitationsWithVersion'));
    }

    createJoinRequest(groupId: string, content: string): Promise<string> {
        RequestUtil.throwIfAnyFalsy(groupId, content);
        return this._turmsClient.driver.send({
            createGroupJoinRequestRequest: {
                groupId,
                content
            }
        }).then(n => NotificationUtil.getFirstVal(n, 'ids', true));
    }

    deleteJoinRequest(requestId: string): Promise<void> {
        RequestUtil.throwIfAnyFalsy(requestId);
        return this._turmsClient.driver.send({
            deleteGroupJoinRequestRequest: {
                requestId
            }
            // eslint-disable-next-line @typescript-eslint/no-empty-function
        }).then(() => {
        });
    }

    queryJoinRequests(groupId: string, lastUpdatedDate?: Date): Promise<ParsedModel.GroupJoinRequestsWithVersion | undefined> {
        RequestUtil.throwIfAnyFalsy(groupId);
        // @ts-ignore
        return this._turmsClient.driver.send({
            queryGroupJoinRequestsRequest: {
                groupId: RequestUtil.wrapValueIfNotNull(groupId),
                lastUpdatedDate: RequestUtil.wrapTimeIfNotNull(lastUpdatedDate)
            }
        }).then(n => NotificationUtil.getAndTransform(n, 'groupJoinRequestsWithVersion'));
    }

    querySentJoinRequests(lastUpdatedDate?: Date): Promise<ParsedModel.GroupJoinRequestsWithVersion | undefined> {
        // @ts-ignore
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
        RequestUtil.throwIfAnyFalsy(groupId, withAnswers);
        // @ts-ignore
        return this._turmsClient.driver.send({
            queryGroupJoinQuestionsRequest: {
                groupId,
                withAnswers,
                lastUpdatedDate: RequestUtil.wrapTimeIfNotNull(lastUpdatedDate)
            }
        }).then(n => NotificationUtil.getAndTransform(n, 'groupJoinQuestionsWithVersion'));
    }

    answerGroupQuestions(questionIdsAndAnswers: { [k: string]: string }): Promise<GroupJoinQuestionsAnswerResult | undefined> {
        RequestUtil.throwIfEmpty(questionIdsAndAnswers);
        return this._turmsClient.driver.send({
            checkGroupJoinQuestionsAnswersRequest: {
                questionIdAndAnswer: questionIdsAndAnswers
            }
        }).then(n => {
            const result = NotificationUtil.get(n, 'groupJoinQuestionsAnswerResult');
            if (result) {
                return result;
            } else {
                throw TurmsBusinessException.fromCode(TurmsStatusCode.MISSING_DATA);
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
        RequestUtil.throwIfAnyFalsy(groupId, userId);
        if (typeof role === 'string') {
            role = ConstantTransformer.string2GroupMemberRole(role);
        }
        return this._turmsClient.driver.send({
            createGroupMemberRequest: {
                groupId,
                userId,
                name: RequestUtil.wrapValueIfNotNull(name),
                role: role,
                muteEndDate: RequestUtil.wrapTimeIfNotNull(muteEndDate)
            }
            // eslint-disable-next-line @typescript-eslint/no-empty-function
        }).then(() => {
        });
    }

    quitGroup(groupId: string, successorId?: string, quitAfterTransfer?: boolean): Promise<void> {
        RequestUtil.throwIfAnyFalsy(groupId);
        return this._turmsClient.driver.send({
            deleteGroupMemberRequest: {
                groupId,
                groupMemberId: this._turmsClient.userService.userId,
                successorId: RequestUtil.wrapValueIfNotNull(successorId),
                quitAfterTransfer: RequestUtil.wrapValueIfNotNull(quitAfterTransfer)
            }
            // eslint-disable-next-line @typescript-eslint/no-empty-function
        }).then(() => {
        });
    }

    removeGroupMember(groupId: string, memberId: string): Promise<void> {
        RequestUtil.throwIfAnyFalsy(groupId, memberId);
        return this._turmsClient.driver.send({
            deleteGroupMemberRequest: {
                groupId,
                groupMemberId: memberId
            }
            // eslint-disable-next-line @typescript-eslint/no-empty-function
        }).then(() => {
        });
    }

    updateGroupMemberInfo(
        groupId: string,
        memberId: string,
        name?: string,
        role?: string | GroupMemberRole,
        muteEndDate?: Date): Promise<void> {
        RequestUtil.throwIfAnyFalsy(groupId, memberId);
        if (RequestUtil.areAllFalsy(name, role, muteEndDate)) {
            return Promise.resolve();
        }
        if (typeof role === 'string') {
            role = ConstantTransformer.string2GroupMemberRole(role);
        }
        return this._turmsClient.driver.send({
            updateGroupMemberRequest: {
                groupId,
                memberId,
                name: RequestUtil.wrapValueIfNotNull(name),
                role: role,
                muteEndDate: RequestUtil.wrapTimeIfNotNull(muteEndDate)
            }
            // eslint-disable-next-line @typescript-eslint/no-empty-function
        }).then(() => {
        });
    }

    muteGroupMember(groupId: string, memberId: string, muteEndDate: Date): Promise<void> {
        RequestUtil.throwIfAnyFalsy(groupId, memberId, muteEndDate);
        return this.updateGroupMemberInfo(groupId, memberId, undefined, undefined, muteEndDate);
    }

    unmuteGroupMember(groupId: string, memberId: string): Promise<void> {
        return this.muteGroupMember(groupId, memberId, new Date(0));
    }

    queryGroupMembers(groupId: string, withStatus = false, lastUpdatedDate?: Date): Promise<ParsedModel.GroupMembersWithVersion | undefined> {
        RequestUtil.throwIfAnyFalsy(groupId);
        // @ts-ignore
        return this._turmsClient.driver.send({
            queryGroupMembersRequest: {
                groupId,
                lastUpdatedDate: RequestUtil.wrapTimeIfNotNull(lastUpdatedDate),
                withStatus: RequestUtil.wrapValueIfNotNull(withStatus)
            }
        }).then(n => NotificationUtil.getAndTransform(n, 'groupMembersWithVersion'));
    }

    queryGroupMembersByMembersIds(groupId: string, membersIds: string[], withStatus = false): Promise<ParsedModel.GroupMembersWithVersion | undefined> {
        RequestUtil.throwIfAnyFalsy(groupId, membersIds);
        // @ts-ignore
        return this._turmsClient.driver.send({
            queryGroupMembersRequest: {
                groupId,
                groupMembersIds: membersIds,
                withStatus: RequestUtil.wrapValueIfNotNull(withStatus)
            }
        }).then(n => NotificationUtil.getAndTransform(n, 'groupMembersWithVersion'));
    }
}
