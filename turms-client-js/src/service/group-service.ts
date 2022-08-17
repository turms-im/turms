import DataParser from '../util/data-parser';
import { GroupJoinQuestionsAnswerResult } from '../model/proto/model/group/group_join_questions_answer_result';
import { GroupMemberRole } from '../model/proto/constant/group_member_role';
import NotificationUtil from '../util/notification-util';
import { ParsedModel } from '../model/parsed-model';
import Response from '../model/response';
import ResponseError from '../error/response-error';
import ResponseStatusCode from '../model/response-status-code';
import TurmsClient from '../turms-client';
import Validator from '../util/validator';

export default class GroupService {
    private _turmsClient: TurmsClient;

    constructor(turmsClient: TurmsClient) {
        this._turmsClient = turmsClient;
    }

    createGroup({
        name,
        intro,
        announcement,
        minimumScore,
        muteEndDate,
        groupTypeId
    }: {
        name: string,
        intro?: string,
        announcement?: string,
        minimumScore?: number,
        muteEndDate?: Date,
        groupTypeId?: string
    }): Promise<Response<string>> {
        if (Validator.isFalsy(name)) {
            return ResponseError.notFalsyPromise('name');
        }
        return this._turmsClient.driver.send({
            createGroupRequest: {
                name,
                intro,
                announcement,
                minimumScore,
                muteEndDate: DataParser.getDateTimeStr(muteEndDate),
                groupTypeId
            }
        }).then(n => Response.fromNotification(n, data => NotificationUtil.getFirstIdOrThrow(data)));
    }

    deleteGroup({
        groupId
    }: {
        groupId: string
    }): Promise<Response<void>> {
        if (Validator.isFalsy(groupId)) {
            return ResponseError.notFalsyPromise('groupId');
        }
        return this._turmsClient.driver.send({
            deleteGroupRequest: {
                groupId
            }
        }).then(n => Response.fromNotification(n));
    }

    updateGroup({
        groupId,
        groupName,
        intro,
        announcement,
        minimumScore,
        groupTypeId,
        muteEndDate,
        successorId,
        quitAfterTransfer
    }: {
        groupId: string,
        groupName?: string,
        intro?: string,
        announcement?: string,
        minimumScore?: number,
        groupTypeId?: string,
        muteEndDate?: Date,
        successorId?: string,
        quitAfterTransfer?: boolean
    }): Promise<Response<void>> {
        if (Validator.isFalsy(groupId)) {
            return ResponseError.notFalsyPromise('groupId');
        }
        if (Validator.areAllFalsy(groupName, intro, announcement, minimumScore, groupTypeId,
            muteEndDate, successorId)) {
            return Promise.resolve(Response.nullValue());
        }
        return this._turmsClient.driver.send({
            updateGroupRequest: {
                groupId,
                groupName,
                intro,
                announcement,
                muteEndDate: DataParser.getDateTimeStr(muteEndDate),
                minimumScore,
                groupTypeId,
                successorId,
                quitAfterTransfer
            }
        }).then(n => Response.fromNotification(n));
    }

    transferOwnership({
        groupId,
        successorId,
        quitAfterTransfer = false
    }: {
        groupId: string,
        successorId: string,
        quitAfterTransfer: boolean
    }): Promise<Response<void>> {
        if (Validator.isFalsy(groupId)) {
            return ResponseError.notFalsyPromise('groupId');
        }
        if (Validator.isFalsy(successorId)) {
            return ResponseError.notFalsyPromise('successorId');
        }
        return this.updateGroup({
            groupId,
            successorId,
            quitAfterTransfer
        });
    }

    muteGroup({
        groupId,
        muteEndDate
    }: {
        groupId: string,
        muteEndDate: Date
    }): Promise<Response<void>> {
        if (Validator.isFalsy(groupId)) {
            return ResponseError.notFalsyPromise('groupId');
        }
        if (Validator.isFalsy(muteEndDate)) {
            return ResponseError.notFalsyPromise('muteEndDate');
        }
        return this.updateGroup({
            groupId,
            muteEndDate
        });
    }

    unmuteGroup({
        groupId
    }: {
        groupId: string
    }): Promise<Response<void>> {
        return this.muteGroup({
            groupId,
            muteEndDate: new Date(0)
        });
    }

    queryGroup({
        groupId,
        lastUpdatedDate
    }: {
        groupId: string,
        lastUpdatedDate?: Date
    }): Promise<Response<ParsedModel.GroupWithVersion | undefined>> {
        if (Validator.isFalsy(groupId)) {
            return ResponseError.notFalsyPromise('groupId');
        }
        return this._turmsClient.driver.send({
            queryGroupRequest: {
                groupId,
                lastUpdatedDate: DataParser.getDateTimeStr(lastUpdatedDate)
            }
        }).then(n => Response.fromNotification(n, (data) => {
            const groupsWithVersion = data.groupsWithVersion;
            const group = NotificationUtil.transform(groupsWithVersion?.groups?.[0]);
            if (group) {
                return {
                    group,
                    lastUpdatedDate: NotificationUtil.transformDate(groupsWithVersion.lastUpdatedDate)
                };
            }
        }));
    }

    queryJoinedGroupIds({
        lastUpdatedDate
    }: {
        lastUpdatedDate?: Date
    } = {}): Promise<Response<ParsedModel.IdsWithVersion | undefined>> {
        return this._turmsClient.driver.send({
            queryJoinedGroupIdsRequest: {
                lastUpdatedDate: DataParser.getDateTimeStr(lastUpdatedDate)
            }
        }).then(n => Response.fromNotification(n, data => NotificationUtil.getIdsWithVer(data)));
    }

    queryJoinedGroupInfos({
        lastUpdatedDate
    }: {
        lastUpdatedDate?: Date
    } = {}): Promise<Response<ParsedModel.GroupsWithVersion | undefined>> {
        return this._turmsClient.driver.send({
            queryJoinedGroupInfosRequest: {
                lastUpdatedDate: DataParser.getDateTimeStr(lastUpdatedDate)
            }
        }).then(n => Response.fromNotification(n, data => NotificationUtil.transform(data.groupsWithVersion)));
    }

    addGroupJoinQuestion({
        groupId,
        question,
        answers,
        score
    }: {
        groupId: string,
        question: string,
        answers: string[],
        score: number
    }): Promise<Response<string>> {
        if (Validator.isFalsy(groupId)) {
            return ResponseError.notFalsyPromise('groupId');
        }
        if (Validator.isFalsy(question)) {
            return ResponseError.notFalsyPromise('question');
        }
        if (Validator.isFalsy(answers)) {
            return ResponseError.notFalsyPromise('answers', true);
        }
        if (Validator.isFalsy(score)) {
            return ResponseError.notFalsyPromise('score');
        }
        return this._turmsClient.driver.send({
            createGroupJoinQuestionRequest: {
                groupId,
                question,
                answers: answers || [],
                score
            }
        }).then(n => Response.fromNotification(n, data => NotificationUtil.getFirstIdOrThrow(data)));
    }

    deleteGroupJoinQuestion({
        questionId
    }: {
        questionId: string
    }): Promise<Response<void>> {
        if (Validator.isFalsy(questionId)) {
            return ResponseError.notFalsyPromise('questionId');
        }
        return this._turmsClient.driver.send({
            deleteGroupJoinQuestionRequest: {
                questionId
            }
        }).then(n => Response.fromNotification(n));
    }

    updateGroupJoinQuestion({
        questionId,
        question,
        answers,
        score
    }: {
        questionId: string,
        question?: string,
        answers?: string[],
        score?: number
    }): Promise<Response<void>> {
        if (Validator.isFalsy(questionId)) {
            return ResponseError.notFalsyPromise('questionId');
        }
        if (Validator.areAllFalsy(question, answers, score)) {
            return Promise.resolve(Response.nullValue());
        }
        return this._turmsClient.driver.send({
            updateGroupJoinQuestionRequest: {
                questionId,
                question,
                answers: answers || [],
                score
            }
        }).then(n => Response.fromNotification(n));
    }

    // Group Blocklist
    blockUser({
        groupId,
        userId
    }: {
        groupId: string,
        userId: string
    }): Promise<Response<void>> {
        if (Validator.isFalsy(groupId)) {
            return ResponseError.notFalsyPromise('groupId');
        }
        if (Validator.isFalsy(userId)) {
            return ResponseError.notFalsyPromise('userId');
        }
        return this._turmsClient.driver.send({
            createGroupBlockedUserRequest: {
                userId,
                groupId
            }
        }).then(n => Response.fromNotification(n));
    }

    unblockUser({
        groupId,
        userId
    }: {
        groupId: string,
        userId: string
    }): Promise<Response<void>> {
        if (Validator.isFalsy(groupId)) {
            return ResponseError.notFalsyPromise('groupId');
        }
        if (Validator.isFalsy(userId)) {
            return ResponseError.notFalsyPromise('userId');
        }
        return this._turmsClient.driver.send({
            deleteGroupBlockedUserRequest: {
                groupId,
                userId
            }
        }).then(n => Response.fromNotification(n));
    }

    queryBlockedUserIds({
        groupId,
        lastUpdatedDate
    }: {
        groupId: string,
        lastUpdatedDate?: Date
    }): Promise<Response<ParsedModel.IdsWithVersion | undefined>> {
        if (Validator.isFalsy(groupId)) {
            return ResponseError.notFalsyPromise('groupId');
        }
        return this._turmsClient.driver.send({
            queryGroupBlockedUserIdsRequest: {
                groupId,
                lastUpdatedDate: DataParser.getDateTimeStr(lastUpdatedDate)
            }
        }).then(n => Response.fromNotification(n, data => NotificationUtil.getIdsWithVer(data)));
    }

    queryBlockedUserInfos({
        groupId,
        lastUpdatedDate
    }: {
        groupId: string,
        lastUpdatedDate?: Date
    }): Promise<Response<ParsedModel.UsersInfosWithVersion | undefined>> {
        if (Validator.isFalsy(groupId)) {
            return ResponseError.notFalsyPromise('groupId');
        }
        return this._turmsClient.driver.send({
            queryGroupBlockedUserInfosRequest: {
                groupId,
                lastUpdatedDate: DataParser.getDateTimeStr(lastUpdatedDate)
            }
        }).then(n => Response.fromNotification(n, data => NotificationUtil.transform(data.usersInfosWithVersion)));
    }

    // Group Enrollment
    createInvitation({
        groupId,
        inviteeId,
        content
    }: {
        groupId: string,
        inviteeId: string,
        content: string
    }): Promise<Response<string>> {
        if (Validator.isFalsy(groupId)) {
            return ResponseError.notFalsyPromise('groupId');
        }
        if (Validator.isFalsy(inviteeId)) {
            return ResponseError.notFalsyPromise('inviteeId');
        }
        if (Validator.isFalsy(content)) {
            return ResponseError.notFalsyPromise('content');
        }
        return this._turmsClient.driver.send({
            createGroupInvitationRequest: {
                groupId,
                inviteeId,
                content
            }
        }).then(n => Response.fromNotification(n, data => NotificationUtil.getFirstIdOrThrow(data)));
    }

    deleteInvitation({
        invitationId
    }: {
        invitationId: string
    }): Promise<Response<void>> {
        if (Validator.isFalsy(invitationId)) {
            return ResponseError.notFalsyPromise('invitationId');
        }
        return this._turmsClient.driver.send({
            deleteGroupInvitationRequest: {
                invitationId
            }
        }).then(n => Response.fromNotification(n));
    }

    queryInvitationsByGroupId({
        groupId,
        lastUpdatedDate
    }: {
        groupId: string,
        lastUpdatedDate?: Date
    }): Promise<Response<ParsedModel.GroupInvitationsWithVersion | undefined>> {
        if (Validator.isFalsy(groupId)) {
            return ResponseError.notFalsyPromise('groupId');
        }
        return this._turmsClient.driver.send({
            queryGroupInvitationsRequest: {
                groupId,
                lastUpdatedDate: DataParser.getDateTimeStr(lastUpdatedDate)
            }
        }).then(n => Response.fromNotification(n, data => NotificationUtil.transform(data.groupInvitationsWithVersion)));
    }

    queryInvitations({
        areSentByMe,
        lastUpdatedDate
    }: {
        areSentByMe: boolean,
        lastUpdatedDate?: Date
    }): Promise<Response<ParsedModel.GroupInvitationsWithVersion | undefined>> {
        if (Validator.isFalsy(areSentByMe)) {
            return ResponseError.notFalsyPromise('areSentByMe');
        }
        return this._turmsClient.driver.send({
            queryGroupInvitationsRequest: {
                areSentByMe,
                lastUpdatedDate: DataParser.getDateTimeStr(lastUpdatedDate)
            }
        }).then(n => Response.fromNotification(n, data => NotificationUtil.transform(data.groupInvitationsWithVersion)));
    }

    createJoinRequest({
        groupId,
        content
    }: {
        groupId: string,
        content: string
    }): Promise<Response<string>> {
        if (Validator.isFalsy(groupId)) {
            return ResponseError.notFalsyPromise('groupId');
        }
        if (Validator.isFalsy(content)) {
            return ResponseError.notFalsyPromise('content');
        }
        return this._turmsClient.driver.send({
            createGroupJoinRequestRequest: {
                groupId,
                content
            }
        }).then(n => Response.fromNotification(n, data => NotificationUtil.getFirstIdOrThrow(data)));
    }

    deleteJoinRequest({
        requestId
    }: {
        requestId: string
    }): Promise<Response<void>> {
        if (Validator.isFalsy(requestId)) {
            return ResponseError.notFalsyPromise('requestId');
        }
        return this._turmsClient.driver.send({
            deleteGroupJoinRequestRequest: {
                requestId
            }
        }).then(n => Response.fromNotification(n));
    }

    queryJoinRequests({
        groupId,
        lastUpdatedDate
    }: {
        groupId: string,
        lastUpdatedDate?: Date
    }): Promise<Response<ParsedModel.GroupJoinRequestsWithVersion | undefined>> {
        if (Validator.isFalsy(groupId)) {
            return ResponseError.notFalsyPromise('groupId');
        }
        return this._turmsClient.driver.send({
            queryGroupJoinRequestsRequest: {
                groupId,
                lastUpdatedDate: DataParser.getDateTimeStr(lastUpdatedDate)
            }
        }).then(n => Response.fromNotification(n, data => NotificationUtil.transform(data.groupJoinRequestsWithVersion)));
    }

    querySentJoinRequests({
        lastUpdatedDate
    }: {
        lastUpdatedDate?: Date
    } = {}): Promise<Response<ParsedModel.GroupJoinRequestsWithVersion | undefined>> {
        return this._turmsClient.driver.send({
            queryGroupJoinRequestsRequest: {
                lastUpdatedDate: DataParser.getDateTimeStr(lastUpdatedDate)
            }
        }).then(n => Response.fromNotification(n, data => NotificationUtil.transform(data.groupJoinRequestsWithVersion)));
    }

    /**
     * Note: Only the owner and managers have the right to fetch questions with answers
     */
    queryGroupJoinQuestionsRequest({
        groupId,
        withAnswers = false,
        lastUpdatedDate
    }: {
        groupId: string,
        withAnswers: boolean,
        lastUpdatedDate?: Date
    }): Promise<Response<ParsedModel.GroupJoinQuestionsWithVersion | undefined>> {
        if (Validator.isFalsy(groupId)) {
            return ResponseError.notFalsyPromise('groupId');
        }
        return this._turmsClient.driver.send({
            queryGroupJoinQuestionsRequest: {
                groupId,
                withAnswers,
                lastUpdatedDate: DataParser.getDateTimeStr(lastUpdatedDate)
            }
        }).then(n => Response.fromNotification(n, data => NotificationUtil.transform(data.groupJoinQuestionsWithVersion)));
    }

    answerGroupQuestions({
        questionIdToAnswer
    }: {
        questionIdToAnswer: Record<string, string>
    }): Promise<Response<GroupJoinQuestionsAnswerResult | undefined>> {
        if (Validator.isFalsy(questionIdToAnswer)) {
            return ResponseError.notFalsyPromise('questionIdToAnswer', true);
        }
        return this._turmsClient.driver.send({
            checkGroupJoinQuestionsAnswersRequest: {
                questionIdToAnswer: questionIdToAnswer
            }
        }).then(n => Response.fromNotification(n, data => {
            const result = NotificationUtil.transform(data.groupJoinQuestionAnswerResult);
            if (result) {
                return result;
            } else {
                throw ResponseError.fromCode(ResponseStatusCode.INVALID_RESPONSE);
            }
        }));
    }

    // Group Member
    addGroupMember({
        groupId,
        userId,
        name,
        role,
        muteEndDate
    }: {
        groupId: string,
        userId: string,
        name?: string,
        role?: string | GroupMemberRole,
        muteEndDate?: Date
    }): Promise<Response<void>> {
        if (Validator.isFalsy(groupId)) {
            return ResponseError.notFalsyPromise('groupId');
        }
        if (Validator.isFalsy(userId)) {
            return ResponseError.notFalsyPromise('userId');
        }
        if (typeof role === 'string') {
            role = GroupMemberRole[role] as GroupMemberRole;
            if (Validator.isFalsy(role)) {
                return ResponseError.notFalsyPromise('role');
            }
        }
        return this._turmsClient.driver.send({
            createGroupMemberRequest: {
                groupId,
                userId,
                name,
                role,
                muteEndDate: DataParser.getDateTimeStr(muteEndDate)
            }
        }).then(n => Response.fromNotification(n));
    }

    quitGroup({
        groupId,
        successorId,
        quitAfterTransfer
    }: {
        groupId: string,
        successorId?: string,
        quitAfterTransfer?: boolean
    }): Promise<Response<void>> {
        if (Validator.isFalsy(groupId)) {
            return ResponseError.notFalsyPromise('groupId');
        }
        return this._turmsClient.driver.send({
            deleteGroupMemberRequest: {
                groupId,
                memberId: this._turmsClient.userService.userInfo.userId,
                successorId,
                quitAfterTransfer
            }
        }).then(n => Response.fromNotification(n));
    }

    removeGroupMember({
        groupId,
        memberId
    }: {
        groupId: string,
        memberId: string
    }): Promise<Response<void>> {
        if (Validator.isFalsy(groupId)) {
            return ResponseError.notFalsyPromise('groupId');
        }
        if (Validator.isFalsy(memberId)) {
            return ResponseError.notFalsyPromise('memberId');
        }
        return this._turmsClient.driver.send({
            deleteGroupMemberRequest: {
                groupId,
                memberId
            }
        }).then(n => Response.fromNotification(n));
    }

    updateGroupMemberInfo({
        groupId,
        memberId,
        name,
        role,
        muteEndDate
    }: {
        groupId: string,
        memberId: string,
        name?: string,
        role?: string | GroupMemberRole,
        muteEndDate?: Date
    }): Promise<Response<void>> {
        if (Validator.isFalsy(groupId)) {
            return ResponseError.notFalsyPromise('groupId');
        }
        if (Validator.isFalsy(memberId)) {
            return ResponseError.notFalsyPromise('memberId');
        }
        if (Validator.areAllFalsy(name, role, muteEndDate)) {
            return Promise.resolve(Response.nullValue());
        }
        if (typeof role === 'string') {
            role = GroupMemberRole[role] as GroupMemberRole;
            if (Validator.isFalsy(role)) {
                return ResponseError.notFalsyPromise('role');
            }
        }
        return this._turmsClient.driver.send({
            updateGroupMemberRequest: {
                groupId,
                memberId,
                name,
                role,
                muteEndDate: DataParser.getDateTimeStr(muteEndDate)
            }
        }).then(n => Response.fromNotification(n));
    }

    muteGroupMember({
        groupId,
        memberId,
        muteEndDate
    }: {
        groupId: string,
        memberId: string,
        muteEndDate: Date
    }): Promise<Response<void>> {
        if (Validator.isFalsy(groupId)) {
            return ResponseError.notFalsyPromise('groupId');
        }
        if (Validator.isFalsy(memberId)) {
            return ResponseError.notFalsyPromise('memberId');
        }
        if (Validator.isFalsy(muteEndDate)) {
            return ResponseError.notFalsyPromise('muteEndDate');
        }
        return this.updateGroupMemberInfo({
            groupId,
            memberId,
            muteEndDate
        });
    }

    unmuteGroupMember({
        groupId,
        memberId
    }: {
        groupId: string,
        memberId: string
    }): Promise<Response<void>> {
        return this.muteGroupMember({
            groupId,
            memberId,
            muteEndDate: new Date(0)
        });
    }

    queryGroupMembers({
        groupId,
        withStatus = false,
        lastUpdatedDate
    }: {
        groupId: string,
        withStatus: boolean,
        lastUpdatedDate?: Date
    }): Promise<Response<ParsedModel.GroupMembersWithVersion | undefined>> {
        if (Validator.isFalsy(groupId)) {
            return ResponseError.notFalsyPromise('groupId');
        }
        return this._turmsClient.driver.send({
            queryGroupMembersRequest: {
                groupId,
                lastUpdatedDate: DataParser.getDateTimeStr(lastUpdatedDate),
                withStatus,
                memberIds: []
            }
        }).then(n => Response.fromNotification(n, data => NotificationUtil.transform(data.groupMembersWithVersion)));
    }

    queryGroupMembersByMemberIds({
        groupId,
        memberIds,
        withStatus = false
    }: {
        groupId: string,
        memberIds: string[],
        withStatus: boolean
    }): Promise<Response<ParsedModel.GroupMembersWithVersion | undefined>> {
        if (Validator.isFalsy(groupId)) {
            return ResponseError.notFalsyPromise('groupId');
        }
        if (Validator.isFalsy(memberIds)) {
            return ResponseError.notFalsyPromise('memberIds', true);
        }
        return this._turmsClient.driver.send({
            queryGroupMembersRequest: {
                groupId,
                memberIds,
                withStatus
            }
        }).then(n => Response.fromNotification(n, data => NotificationUtil.transform(data.groupMembersWithVersion)));
    }
}