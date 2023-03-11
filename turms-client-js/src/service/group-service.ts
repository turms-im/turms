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
import CollectionUtil from '../util/collection-util';
import NewGroupJoinQuestion from '../model/new-group-join-question';

export default class GroupService {
    private _turmsClient: TurmsClient;

    constructor(turmsClient: TurmsClient) {
        this._turmsClient = turmsClient;
    }

    createGroup({
        name,
        intro,
        announcement,
        minScore,
        muteEndDate,
        typeId
    }: {
        name: string,
        intro?: string,
        announcement?: string,
        minScore?: number,
        muteEndDate?: Date,
        typeId?: string
    }): Promise<Response<string>> {
        if (Validator.isFalsy(name)) {
            return ResponseError.notFalsyPromise('name');
        }
        return this._turmsClient.driver.send({
            createGroupRequest: {
                name,
                intro,
                announcement,
                minScore,
                muteEndDate: DataParser.getDateTimeStr(muteEndDate),
                typeId
            }
        }).then(n => Response.fromNotification(n, data => NotificationUtil.getLongOrThrow(data)));
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
        name,
        intro,
        announcement,
        minScore,
        typeId,
        muteEndDate,
        successorId,
        quitAfterTransfer
    }: {
        groupId: string,
        name?: string,
        intro?: string,
        announcement?: string,
        minScore?: number,
        typeId?: string,
        muteEndDate?: Date,
        successorId?: string,
        quitAfterTransfer?: boolean
    }): Promise<Response<void>> {
        if (Validator.isFalsy(groupId)) {
            return ResponseError.notFalsyPromise('groupId');
        }
        if (Validator.areAllFalsy(name, intro, announcement, minScore, typeId,
            muteEndDate, successorId)) {
            return Promise.resolve(Response.nullValue());
        }
        return this._turmsClient.driver.send({
            updateGroupRequest: {
                groupId,
                name,
                intro,
                announcement,
                muteEndDate: DataParser.getDateTimeStr(muteEndDate),
                minScore,
                typeId,
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

    queryGroups({
        groupIds,
        lastUpdatedDate
    }: {
        groupIds: string[],
        lastUpdatedDate?: Date
    }): Promise<Response<ParsedModel.Group[]>> {
        if (Validator.isFalsy(groupIds)) {
            return Promise.resolve(Response.emptyList());
        }
        return this._turmsClient.driver.send({
            queryGroupsRequest: {
                groupIds: CollectionUtil.uniqueArray(groupIds),
                lastUpdatedDate: DataParser.getDateTimeStr(lastUpdatedDate)
            }
        }).then(n => Response.fromNotification(n, (data) =>
            NotificationUtil.transform(data.groupsWithVersion?.groups)));
    }

    queryJoinedGroupIds({
        lastUpdatedDate
    }: {
        lastUpdatedDate?: Date
    } = {}): Promise<Response<ParsedModel.LongsWithVersion | undefined>> {
        return this._turmsClient.driver.send({
            queryJoinedGroupIdsRequest: {
                lastUpdatedDate: DataParser.getDateTimeStr(lastUpdatedDate)
            }
        }).then(n => Response.fromNotification(n, data => NotificationUtil.getLongsWithVersion(data)));
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

    addGroupJoinQuestions({
        groupId,
        questions
    }: {
        groupId: string,
        questions: NewGroupJoinQuestion[]
    }): Promise<Response<string[]>> {
        if (Validator.isFalsy(groupId)) {
            return ResponseError.notFalsyPromise('groupId');
        }
        if (Validator.isFalsy(questions)) {
            return Promise.resolve(Response.emptyList());
        }
        let newQuestions;
        try {
            newQuestions = questions.map(q => {
                const question = q.question;
                const answers = q.answers;
                const score = q.score;
                if (Validator.isFalsy(question)) {
                    ResponseError.notFalsy('question');
                }
                if (Validator.isFalsy(answers)) {
                    ResponseError.notFalsy('answers', true);
                }
                if (Validator.isFalsy(score)) {
                    ResponseError.notFalsy('score');
                }
                return {
                    question,
                    answers: new Set(answers),
                    score
                };
            });
        } catch (e) {
            return Promise.reject(e);
        }
        return this._turmsClient.driver.send({
            createGroupJoinQuestionsRequest: {
                groupId,
                questions: newQuestions
            }
        }).then(n => Response.fromNotification(n, data => data.longsWithVersion?.longs || []));
    }

    deleteGroupJoinQuestions({
        groupId,
        questionIds
    }: {
        groupId: string,
        questionIds: string[]
    }): Promise<Response<void>> {
        if (Validator.isFalsy(questionIds)) {
            return Promise.resolve(Response.nullValue());
        }
        return this._turmsClient.driver.send({
            deleteGroupJoinQuestionsRequest: {
                groupId,
                questionIds
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
    }): Promise<Response<ParsedModel.LongsWithVersion | undefined>> {
        if (Validator.isFalsy(groupId)) {
            return ResponseError.notFalsyPromise('groupId');
        }
        return this._turmsClient.driver.send({
            queryGroupBlockedUserIdsRequest: {
                groupId,
                lastUpdatedDate: DataParser.getDateTimeStr(lastUpdatedDate)
            }
        }).then(n => Response.fromNotification(n, data => NotificationUtil.getLongsWithVersion(data)));
    }

    queryBlockedUserInfos({
        groupId,
        lastUpdatedDate
    }: {
        groupId: string,
        lastUpdatedDate?: Date
    }): Promise<Response<ParsedModel.UserInfosWithVersion | undefined>> {
        if (Validator.isFalsy(groupId)) {
            return ResponseError.notFalsyPromise('groupId');
        }
        return this._turmsClient.driver.send({
            queryGroupBlockedUserInfosRequest: {
                groupId,
                lastUpdatedDate: DataParser.getDateTimeStr(lastUpdatedDate)
            }
        }).then(n => Response.fromNotification(n, data => NotificationUtil.transform(data.userInfosWithVersion)));
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
        }).then(n => Response.fromNotification(n, data => NotificationUtil.getLongOrThrow(data)));
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
        }).then(n => Response.fromNotification(n, data => NotificationUtil.getLongOrThrow(data)));
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
    queryGroupJoinQuestions({
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
                throw ResponseError.from({
                    code: ResponseStatusCode.INVALID_RESPONSE
                });
            }
        }));
    }

    // Group Member
    addGroupMembers({
        groupId,
        userIds,
        name,
        role,
        muteEndDate
    }: {
        groupId: string,
        userIds: string[],
        name?: string,
        role?: string | GroupMemberRole,
        muteEndDate?: Date
    }): Promise<Response<void>> {
        if (Validator.isFalsy(groupId)) {
            return ResponseError.notFalsyPromise('groupId');
        }
        if (typeof role === 'string') {
            role = GroupMemberRole[role] as GroupMemberRole;
            if (Validator.isFalsy(role)) {
                return ResponseError.notFalsyPromise('role');
            }
        }
        if (Validator.isFalsy(userIds)) {
            return Promise.resolve(Response.nullValue());
        }
        return this._turmsClient.driver.send({
            createGroupMembersRequest: {
                groupId,
                userIds,
                name,
                role,
                muteEndDate: DataParser.getDateTimeStr(muteEndDate)
            }
        }).then(n => Response.fromNotification(n));
    }

    joinGroup({
        groupId
    }: {
        groupId: string
    }): Promise<Response<void>> {
        if (Validator.isFalsy(groupId)) {
            return ResponseError.notFalsyPromise('groupId');
        }
        const userId = this._turmsClient.userService.userInfo.userId;
        if (userId == null) {
            return Promise.reject(ResponseError.from({
                code: ResponseStatusCode.CLIENT_SESSION_HAS_BEEN_CLOSED
            }));
        }
        return this.addGroupMembers({
            groupId,
            userIds: [userId]
        });
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
        const userId = this._turmsClient.userService.userInfo.userId;
        if (userId == null) {
            return Promise.reject(ResponseError.from({
                code: ResponseStatusCode.CLIENT_SESSION_HAS_BEEN_CLOSED
            }));
        }
        return this._turmsClient.driver.send({
            deleteGroupMembersRequest: {
                groupId,
                memberIds: [userId],
                successorId,
                quitAfterTransfer
            }
        }).then(n => Response.fromNotification(n));
    }

    removeGroupMembers({
        groupId,
        memberIds
    }: {
        groupId: string,
        memberIds: string[]
    }): Promise<Response<void>> {
        if (Validator.isFalsy(groupId)) {
            return ResponseError.notFalsyPromise('groupId');
        }
        if (Validator.isFalsy(memberIds)) {
            return Promise.resolve(Response.nullValue());
        }
        return this._turmsClient.driver.send({
            deleteGroupMembersRequest: {
                groupId,
                memberIds
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