import TurmsClient from "../turms-client";
import ConstantTransformer from "../util/constant-transformer";
import {im} from "../model/proto-bundle";
import RequestUtil from "../util/request-util";
import {ParsedModel} from "../model/parsed-model";
import NotificationUtil from "../util/notification-util";
import GroupMemberRole = im.turms.proto.GroupMemberRole;

export default class GroupService {
    private _turmsClient: TurmsClient;

    constructor(turmsClient: TurmsClient) {
        this._turmsClient = turmsClient;
    }

    createGroup(
        name: string,
        intro?: string,
        announcement?: string,
        profilePictureUrl?: string,
        minimumScore?: number,
        muteEndDate?: Date,
        groupTypeId?: string): Promise<string> {
        RequestUtil.throwIfAnyFalsy(name);
        return this._turmsClient.driver.send({
            createGroupRequest: {
                name,
                intro: RequestUtil.wrapValueIfNotNull(intro),
                announcement: RequestUtil.wrapValueIfNotNull(announcement),
                profilePictureUrl: RequestUtil.wrapValueIfNotNull(profilePictureUrl),
                minimumScore: RequestUtil.wrapValueIfNotNull(minimumScore),
                muteEndDate: RequestUtil.wrapTimeIfNotNull(muteEndDate),
                groupTypeId: RequestUtil.wrapValueIfNotNull(groupTypeId)
            }
        }).then(n => NotificationUtil.getFirstVal(n, 'ids'));
    }

    deleteGroup(groupId: string): Promise<void> {
        RequestUtil.throwIfAnyFalsy(groupId);
        return this._turmsClient.driver.send({
            deleteGroupRequest: {
                groupId
            }
        }).then(_ => {
        });
    }

    updateGroup(
        groupId: string,
        groupName?: string,
        intro?: string,
        announcement?: string,
        profilePictureUrl?: string,
        minimumScore?: number,
        groupTypeId?: string,
        muteEndDate?: Date,
        successorId?: string,
        quitAfterTransfer?: boolean): Promise<void> {
        RequestUtil.throwIfAnyFalsy(groupId);
        if (RequestUtil.areAllFalsy(groupName, intro, announcement, profilePictureUrl, minimumScore, groupTypeId,
            muteEndDate, successorId)) {
            return Promise.resolve();
        }
        return this._turmsClient.driver.send({
            updateGroupRequest: {
                groupId,
                groupName: RequestUtil.wrapValueIfNotNull(groupName),
                intro: RequestUtil.wrapValueIfNotNull(intro),
                announcement: RequestUtil.wrapValueIfNotNull(announcement),
                profilePictureUrl: RequestUtil.wrapValueIfNotNull(profilePictureUrl),
                muteEndDate: RequestUtil.wrapTimeIfNotNull(muteEndDate),
                minimumScore: RequestUtil.wrapValueIfNotNull(minimumScore),
                groupTypeId: RequestUtil.wrapValueIfNotNull(groupTypeId),
                successorId: RequestUtil.wrapValueIfNotNull(successorId),
                quitAfterTransfer: RequestUtil.wrapValueIfNotNull(quitAfterTransfer)
            }
        }).then(_ => {
        });
    }

    transferOwnership(groupId: string, successorId: string, quitAfterTransfer = false): Promise<void> {
        RequestUtil.throwIfAnyFalsy(groupId, successorId);
        return this.updateGroup(groupId, null, null, null, null, null, null, null, successorId, quitAfterTransfer);
    }

    muteGroup(groupId: string, muteEndDate: Date): Promise<void> {
        RequestUtil.throwIfAnyFalsy(groupId, muteEndDate);
        return this.updateGroup(groupId, null, null, null, null, null, null, muteEndDate, null);
    }

    unmuteGroup(groupId: string): Promise<void> {
        return this.muteGroup(groupId, new Date(0));
    }

    queryGroup(groupId: string, lastUpdatedDate?: Date): Promise<ParsedModel.GroupWithVersion> {
        RequestUtil.throwIfAnyFalsy(groupId);
        // @ts-ignore
        return this._turmsClient.driver.send({
            queryGroupRequest: {
                groupId,
                lastUpdatedDate: RequestUtil.wrapTimeIfNotNull(lastUpdatedDate)
            }
        }).then(n => {
            return {
                group: NotificationUtil.getAndTransform(n, 'groupsWithVersion.groups.0'),
                lastUpdatedDate: NotificationUtil.getVerDate(n, 'groupsWithVersion')
            };
        });
    }

    queryJoinedGroupsIds(lastUpdatedDate?: Date): Promise<ParsedModel.IdsWithVersion> {
        return this._turmsClient.driver.send({
            queryJoinedGroupsIdsRequest: {
                lastUpdatedDate: RequestUtil.wrapTimeIfNotNull(lastUpdatedDate)
            }
        }).then(n => NotificationUtil.getIdsWithVer(n));
    }

    queryJoinedGroupsInfos(lastUpdatedDate?: Date): Promise<ParsedModel.GroupsWithVersion> {
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
        }).then(n => NotificationUtil.getFirstVal(n, 'ids'));
    }

    deleteGroupJoinQuestion(questionId: string): Promise<void> {
        RequestUtil.throwIfAnyFalsy(questionId);
        return this._turmsClient.driver.send({
            deleteGroupJoinQuestionRequest: {
                questionId
            }
        }).then(_ => {
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
        }).then(_ => {
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
        }).then(_ => {
        });
    }

    unblacklistUser(groupId: string, userId: string): Promise<void> {
        RequestUtil.throwIfAnyFalsy(groupId, userId);
        return this._turmsClient.driver.send({
            deleteGroupBlacklistedUserRequest: {
                groupId,
                unblacklistedUserId: userId
            }
        }).then(_ => {
        });
    }

    queryBlacklistedUsersIds(
        groupId: string,
        lastUpdatedDate?: Date): Promise<ParsedModel.IdsWithVersion> {
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
        lastUpdatedDate?: Date): Promise<ParsedModel.UsersInfosWithVersion> {
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
        }).then(n => NotificationUtil.getFirstVal(n, 'ids'));
    }

    deleteInvitation(invitationId: string): Promise<void> {
        RequestUtil.throwIfAnyFalsy(invitationId);
        return this._turmsClient.driver.send({
            deleteGroupInvitationRequest: {
                invitationId
            }
        }).then(_ => {
        });
    }

    queryInvitations(groupId: string, lastUpdatedDate?: Date): Promise<ParsedModel.GroupInvitationsWithVersion> {
        RequestUtil.throwIfAnyFalsy(groupId);
        // @ts-ignore
        return this._turmsClient.driver.send({
            queryGroupInvitationsRequest: {
                groupId,
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
        }).then(n => NotificationUtil.getFirstVal(n, 'ids'));
    }

    deleteJoinRequest(requestId: string): Promise<void> {
        RequestUtil.throwIfAnyFalsy(requestId);
        return this._turmsClient.driver.send({
            deleteGroupJoinRequestRequest: {
                requestId
            }
        }).then(_ => {
        });
    }

    queryJoinRequests(groupId: string, lastUpdatedDate?: Date): Promise<ParsedModel.GroupJoinRequestsWithVersion> {
        RequestUtil.throwIfAnyFalsy(groupId);
        // @ts-ignore
        return this._turmsClient.driver.send({
            queryGroupJoinRequestsRequest: {
                groupId,
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
        lastUpdatedDate?: Date): Promise<ParsedModel.GroupJoinQuestionsWithVersion> {
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

    answerGroupQuestions(questionIdsAndAnswers: { [k: string]: string }): Promise<boolean> {
        RequestUtil.throwIfEmpty(questionIdsAndAnswers);
        return this._turmsClient.driver.send({
            checkGroupJoinQuestionsAnswersRequest: {
                questionIdAndAnswer: questionIdsAndAnswers
            }
        }).then(n => NotificationUtil.getVal(n, 'success'));
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
        }).then(_ => {
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
        }).then(_ => {
        });
    }

    removeGroupMember(groupId: string, memberId: string): Promise<void> {
        RequestUtil.throwIfAnyFalsy(groupId, memberId);
        return this._turmsClient.driver.send({
            deleteGroupMemberRequest: {
                groupId,
                groupMemberId: memberId
            }
        }).then(_ => {
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
        }).then(_ => {
        });
    }

    muteGroupMember(groupId: string, memberId: string, muteEndDate: Date): Promise<void> {
        RequestUtil.throwIfAnyFalsy(groupId, memberId, muteEndDate);
        return this.updateGroupMemberInfo(groupId, memberId, undefined, undefined, muteEndDate);
    }

    unmuteGroupMember(groupId: string, memberId: string): Promise<void> {
        return this.muteGroupMember(groupId, memberId, new Date(0));
    }

    queryGroupMembers(groupId: string, withStatus = false, lastUpdatedDate?: Date): Promise<ParsedModel.GroupMembersWithVersion> {
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

    queryGroupMembersByMembersIds(groupId: string, membersIds: string[], withStatus = false): Promise<ParsedModel.GroupMembersWithVersion> {
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
