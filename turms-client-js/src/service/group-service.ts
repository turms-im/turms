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
import { ResponseAction } from '../model/proto/constant/response_action';

export default class GroupService {
    private _turmsClient: TurmsClient;

    constructor(turmsClient: TurmsClient) {
        this._turmsClient = turmsClient;
    }

    /**
     * Create a new group.
     * The logged-in user will become the group creator and owner.
     *
     * @remarks
     * Common Scenarios:
     * * To add new group members, you can use methods like {@link addGroupMembers}.
     *
     * Authorization:
     * * If the groups owned by the logged-in user has exceeded the limit,
     *   throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#MAX_OWNED_GROUPS_REACHED}.
     *
     * Notifications:
     * * If the server property `turms.service.notification.group-created.notify-requester-other-online-sessions`
     *   is true (true by default), the server will send a create group notification to all other online sessions of the logged-in user actively.
     *
     * @param name - the group name.
     * @param intro - the group introduction.
     * @param announcement - the group announcement.
     * @param minScore - the group minimum score that a non-member user needs to acquire
     * to join the group when answering group questions.
     * @param typeId - the group type ID.
     * If null, the default group type configured in turms-service will be used.
     *
     * Authorization:
     * * If the group type ID does not exist,
     *   throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#CREATE_GROUP_WITH_NONEXISTENT_GROUP_TYPE}.
     * * If the logged-in user does not have the permission to create the group with {@link typeId},
     *   throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#NO_PERMISSION_TO_CREATE_GROUP_WITH_GROUP_TYPE}.
     * @returns the group ID.
     * @throws {@link ResponseError} if an error occurs.
     */
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
                typeId,
                userDefinedAttributes: {},
                customAttributes: []
            },
            customAttributes: []
        }).then(n => Response.fromNotification(n, data => NotificationUtil.getLongOrThrow(data)));
    }

    /**
     * Delete the target group.
     *
     * @remarks
     * Authorization:
     * * If the logged-in user is not the group owner, or the target group does not exist,
     *   throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#NOT_GROUP_OWNER_TO_DELETE_GROUP}.
     *
     * Notifications:
     * * If the server property `turms.service.notification.group-deleted.notify-requester-other-online-sessions`
     *   is true (true by default),
     *   the server will send a delete group notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.group-deleted.notify-group-members`
     *   is true (true by default),
     *   the server will send a delete group notification to all group members of the target group.
     * @throws {@link ResponseError} if an error occurs.
     */
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
                groupId,
                customAttributes: []
            },
            customAttributes: []
        }).then(n => Response.fromNotification(n));
    }

    /**
     * Update the target group.
     *
     * @remarks
     * Notifications:
     * * If the server property `turms.service.notification.group-updated.notify-requester-other-online-sessions`
     *   the server will send an update group notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.group-updated.notify-group-members`
     *   is true (true by default),
     *   the server will send an update group notification to all group members of the target group actively.
     *
     * @param groupId - the target group ID to find the group for updating.
     * @param name - the new group name.
     * If null, the group name will not be changed.
     *
     * Authorization:
     * * Whether the logged-in user can change the group name depends on the group type.
     *   If not null and the logged-in user does NOT have the permission to change the group name,
     *   throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#NOT_GROUP_MEMBER_TO_UPDATE_GROUP_INFO}
     *   or {@link ResponseStatusCode#NOT_GROUP_OWNER_OR_MANAGER_TO_UPDATE_GROUP_INFO}
     *   or {@link ResponseStatusCode#NOT_GROUP_OWNER_TO_UPDATE_GROUP_INFO}.
     * @param intro - the new group introduction.
     * If null, the group introduction will not be changed.
     *
     * Authorization:
     * * Whether the logged-in user can change the group introduction depends on the group type.
     *   If not null and the logged-in user does NOT have the permission to change the group introduction,
     *   throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#NOT_GROUP_MEMBER_TO_UPDATE_GROUP_INFO}
     *   or {@link ResponseStatusCode#NOT_GROUP_OWNER_OR_MANAGER_TO_UPDATE_GROUP_INFO}
     *   or {@link ResponseStatusCode#NOT_GROUP_OWNER_TO_UPDATE_GROUP_INFO}.
     * @param announcement - the new group announcement.
     * If null, the group announcement will not be changed.
     *
     * Authorization:
     * * Whether the logged-in user can change the group announcement depends on the group type.
     *   If not null and the logged-in user does NOT have the permission to change the group announcement,
     *   throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#NOT_GROUP_MEMBER_TO_UPDATE_GROUP_INFO}
     *   or {@link ResponseStatusCode#NOT_GROUP_OWNER_OR_MANAGER_TO_UPDATE_GROUP_INFO}
     *   or {@link ResponseStatusCode#NOT_GROUP_OWNER_TO_UPDATE_GROUP_INFO}.
     * @param minScore - the new group minimum score that a non-member user needs to acquire
     * to join the group when answering group questions.
     * If null, the group minimum score will not be changed.
     *
     * Authorization:
     * * Whether the logged-in user can change the group minimum score depends on the group type.
     *   If not null and the logged-in user does NOT have the permission to change the group minimum score,
     *   throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#NOT_GROUP_MEMBER_TO_UPDATE_GROUP_INFO}
     *   or {@link ResponseStatusCode#NOT_GROUP_OWNER_OR_MANAGER_TO_UPDATE_GROUP_INFO}
     *   or {@link ResponseStatusCode#NOT_GROUP_OWNER_TO_UPDATE_GROUP_INFO}.
     * @param typeId - the new group type ID.
     * If null, the group type ID will not be changed.
     *
     * Authorization:
     * * If the server property `turms.service.group.allow-group-owner-change-group-type`
     *   is true (false by default), the logged-in user can change the group type.
     *   Otherwise, throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#UPDATING_GROUP_TYPE_IS_DISABLED}.
     * * If the logged-in user is not the group owner,
     *   throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#NOT_GROUP_OWNER_TO_UPDATE_GROUP_TYPE}.
     * * If the logged-in user is not allowed to use the group type,
     *   throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#NO_PERMISSION_TO_UPDATE_GROUP_TO_GROUP_TYPE}.
     * * If {@link typeId} doesn't exist, throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#UPDATE_GROUP_TO_NONEXISTENT_GROUP_TYPE}.
     * @param muteEndDate - the new group mute end date.
     * Before the group mute end date, the group members will not be able
     * to send messages.
     *
     * Authorization:
     * * Only the group owner or group managers can mute or unmute the group.
     *   If the logged-in user is not the owner or manager of the group,
     *   {@link {@link ResponseError}} with the code {@link ResponseStatusCode#NOT_GROUP_OWNER_OR_MANAGER_TO_MUTE_GROUP_MEMBER}
     *   will be thrown.
     * @param successorId - the new successor ID.
     * If the logged-in user is the owner of the group, they must transfer the group ownership to the {@link successorId},
     * throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#GROUP_OWNER_QUIT_WITHOUT_SPECIFYING_SUCCESSOR} otherwise.
     * And the successor will become the group owner.
     * The successor must already be a member of the group, throws {@link {@link ResponseError}} with the code
     * {@link ResponseStatusCode#GROUP_SUCCESSOR_NOT_GROUP_MEMBER} otherwise.
     * @param quitAfterTransfer - whether to quit the group after transfer the group ownership.
     * If false, the logged-in user will become a normal group member (not the group admin).
     * If null, the value will not be changed.
     *
     * Authorization:
     * * If the logged-in user is not the owner of the group,
     *   throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#NOT_GROUP_OWNER_TO_TRANSFER_GROUP}.
     * @throws {@link ResponseError} if an error occurs.
     */
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
                quitAfterTransfer,
                userDefinedAttributes: {},
                customAttributes: []
            },
            customAttributes: []
        }).then(n => Response.fromNotification(n));
    }

    /**
     * Transfer the group ownership.
     *
     * @remarks
     * Notifications:
     * * If the server property `turms.service.notification.group-updated.notify-requester-other-online-sessions`
     *   the server will send a update group notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.group-updated.notify-group-members`
     *   is true (true by default),
     *   the server will send a update group notification to all group members of the target group actively.
     *
     * @param groupId - the target group ID to find the group for updating.
     * @param successorId - the new successor ID.
     * If the logged-in user is the owner of the group, they must transfer the group ownership to the {@link successorId},
     * throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#GROUP_OWNER_QUIT_WITHOUT_SPECIFYING_SUCCESSOR} otherwise.
     * And the successor will become the group owner.
     * The successor must already be a member of the group, throws {@link {@link ResponseError}} with the code
     * {@link ResponseStatusCode#GROUP_SUCCESSOR_NOT_GROUP_MEMBER} otherwise.
     * @param quitAfterTransfer - whether to quit the group after transfer the group ownership.
     * If false, the logged-in user will become a normal group member (not the group admin).
     * If null, the value will not be changed.
     * Authorization: If the logged-in user is not the owner of the group,
     * throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#NOT_GROUP_OWNER_TO_TRANSFER_GROUP}.
     * @throws {@link ResponseError} if an error occurs.
     */
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

    /**
     * Mute the target group.
     *
     * @remarks
     * Notifications:
     * * If the server property `turms.service.notification.group-updated.notify-requester-other-online-sessions`
     *   the server will send a update group notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.group-updated.notify-group-members`
     *   is true (true by default),
     *   the server will send a update group notification to all group members of the target group actively.
     *
     * @param groupId - the target group ID to find the group for updating.
     * @param muteEndDate - the new group mute end date.
     * Before the group mute end date, the group members will not be able
     * to send messages.
     *
     * Authorization:
     * * Only the group owner or group managers can mute or unmute the group.
     *   If the logged-in user is not the owner or manager of the group,
     *   throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#NOT_GROUP_OWNER_OR_MANAGER_TO_MUTE_GROUP_MEMBER}.
     * @throws {@link ResponseError} if an error occurs.
     */
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

    /**
     * Unmute the target group.
     *
     * @remarks
     * Authorization:
     * * Only the group owner or group managers can mute or unmute the group.
     *   If the logged-in user is not the owner or manager of the group,
     *   throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#NOT_GROUP_OWNER_OR_MANAGER_TO_MUTE_GROUP_MEMBER}.
     *
     * Notifications:
     * * If the server property `turms.service.notification.group-updated.notify-requester-other-online-sessions`
     *   the server will send a update group notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.group-updated.notify-group-members`
     *   is true (true by default),
     *   the server will send a update group notification to all group members of the target group actively.
     *
     * @param groupId - the target group ID to find the group for updating.
     * @throws {@link ResponseError} if an error occurs.
     */
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

    /**
     * Find groups.
     *
     * @param groupIds - the target group IDs for finding groups.
     * @param lastUpdatedDate - the last updated date of groups on local.
     * The server will only return groups that are updated after {@link lastUpdatedDate}.
     * If null, all groups will be returned.
     * @returns a list of groups.
     * @throws {@link ResponseError} if an error occurs.
     */
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
                lastUpdatedDate: DataParser.getDateTimeStr(lastUpdatedDate),
                fieldsToHighlight: [],
                customAttributes: []
            },
            customAttributes: []
        }).then(n => Response.fromNotification(n, (data) =>
            NotificationUtil.transform(data.groupsWithVersion?.groups)));
    }

    /**
     * Search for groups.
     *
     * @param name - search for groups whose name matches {@link name}.
     * @param highlight - whether to highlight the name.
     * If true, the highlighted parts of the name will be paired with '\u0002' and '\u0003'.
     * @param skip - the number of groups to skip.
     * @param limit - the max number of groups to return.
     * @returns a list of groups sorted in descending relevance.
     * @throws {@link ResponseError} if an error occurs.
     */
    searchGroups({
        name, highlight, skip, limit
    }: {
        name: string,
        highlight?: boolean, skip?: number, limit?: number
    }): Promise<Response<ParsedModel.Group[]>> {
        if (!name) {
            return Promise.resolve(Response.emptyList());
        }
        return this._turmsClient.driver.send({
            queryGroupsRequest: {
                groupIds: [],
                name: name,
                fieldsToHighlight: highlight ? [1] : [],
                skip: skip,
                limit: limit,
                customAttributes: []
            },
            customAttributes: []
        }).then(n => Response.fromNotification(n, (data) =>
            NotificationUtil.transform(data.groupsWithVersion?.groups)));
    }

    /**
     * Find group IDs that the logged-in user has joined.
     *
     * @param lastUpdatedDate - the last updated date of group IDs that the logged-in user has joined stored locally.
     * The server will only return group IDs that are updated after {@link lastUpdatedDate}.
     * If null, all group IDs will be returned.
     * @returns a list of group IDs and the version.
     * Note: The version can be used to update the last updated date on local.
     * @throws {@link ResponseError} if an error occurs.
     */
    queryJoinedGroupIds({
        lastUpdatedDate
    }: {
        lastUpdatedDate?: Date
    } = {}): Promise<Response<ParsedModel.LongsWithVersion | undefined>> {
        return this._turmsClient.driver.send({
            queryJoinedGroupIdsRequest: {
                lastUpdatedDate: DataParser.getDateTimeStr(lastUpdatedDate),
                customAttributes: []
            },
            customAttributes: []
        }).then(n => Response.fromNotification(n, data => NotificationUtil.getLongsWithVersion(data)));
    }

    /**
     * Find groups that the logged-in user has joined.
     *
     * @param lastUpdatedDate - the last updated date of groups that the logged-in user has joined stored locally.
     * The server will only return groups that are updated after {@link lastUpdatedDate}.
     * If null, all groups will be returned.
     * @throws {@link ResponseError} if an error occurs.
     */
    queryJoinedGroupInfos({
        lastUpdatedDate
    }: {
        lastUpdatedDate?: Date
    } = {}): Promise<Response<ParsedModel.GroupsWithVersion | undefined>> {
        return this._turmsClient.driver.send({
            queryJoinedGroupInfosRequest: {
                lastUpdatedDate: DataParser.getDateTimeStr(lastUpdatedDate),
                customAttributes: []
            },
            customAttributes: []
        }).then(n => Response.fromNotification(n, data => NotificationUtil.transform(data.groupsWithVersion)));
    }

    /**
     * Add group join/membership questions.
     *
     * @remarks
     * Authorization:
     * * Only the group owner or group managers can add group membership questions.
     *   Otherwise, throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#NOT_GROUP_OWNER_OR_MANAGER_TO_CREATE_GROUP_QUESTION}.
     * * Only the group that use `question` as the join strategy can add group membership questions.
     *   Otherwise, throws {@link {@link ResponseError}} with the code
     *   {@link ResponseStatusCode#CREATE_GROUP_QUESTION_FOR_GROUP_USING_JOIN_REQUEST}
     *   or {@link ResponseStatusCode#CREATE_GROUP_QUESTION_FOR_GROUP_USING_INVITATION}
     *   or {@link ResponseStatusCode#CREATE_GROUP_QUESTION_FOR_GROUP_USING_MEMBERSHIP_REQUEST}.
     * * If the group has been deleted,
     *   throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#CREATE_GROUP_QUESTION_FOR_INACTIVE_GROUP}.
     *
     * @param groupId - the target group ID.
     * @param questions - the group membership questions.
     * @returns new group questions IDs.
     * @throws {@link ResponseError} if an error occurs.
     */
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
                questions: newQuestions,
                customAttributes: []
            },
            customAttributes: []
        }).then(n => Response.fromNotification(n, data => data.longsWithVersion?.longs || []));
    }

    /**
     * Delete group join/membership questions.
     *
     * @remarks
     * Authorization:
     * * Only the group owner or group managers can delete group membership questions.
     *   Otherwise, throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#NOT_GROUP_OWNER_OR_MANAGER_TO_DELETE_GROUP_QUESTION}.
     *
     * @param groupId - the target group ID.
     * @param questionIds - the group membership question IDs.
     * @throws {@link ResponseError} if an error occurs.
     */
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
                questionIds,
                customAttributes: []
            },
            customAttributes: []
        }).then(n => Response.fromNotification(n));
    }

    /**
     * Update group join/membership questions.
     *
     * @remarks
     * Authorization:
     * * Only the group owner or group managers can update group membership questions.
     *   Otherwise, throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#NOT_GROUP_OWNER_OR_MANAGER_TO_UPDATE_GROUP_QUESTION}.
     *
     * @param questionId - the target question ID.
     * @param question - the question.
     * If null, the question will not be updated.
     * @param answers - the answers.
     * If null, the answers will not be updated.
     * @param score - the score.
     * If null, the score will not be updated.
     * @throws {@link ResponseError} if an error occurs.
     */
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
                score,
                customAttributes: []
            },
            customAttributes: []
        }).then(n => Response.fromNotification(n));
    }

    /**
     * Block a user in the group.
     * If the logged-in user is a group member, the server will delete the group member automatically.
     *
     * @remarks
     * Authorization:
     * * Only the group owner or group managers can block users.
     *   Otherwise, throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#NOT_GROUP_OWNER_OR_MANAGER_TO_ADD_BLOCKED_USER}.
     * * If the logged-in user trys to block themselves,
     *   throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#CANNOT_BLOCK_ONESELF}.
     *
     * Notifications:
     * * If the server property `turms.service.notification.group-blocked-user-added.notify-requester-other-online-sessions`
     *   is true (true by default), the server will send a block user notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.group-blocked-user-added.notify-blocked-user`,
     *   is true (false by default), the server will send a block user notification to the target user actively.
     * * If the server property `turms.service.notification.group-blocked-user-added.notify-group-members`
     *   is true (false by default), the server will send a block user notification to all group members of the target group actively.
     *
     * @param groupId - the target group ID.
     * @param userId - the target user ID.
     * @throws {@link ResponseError} if an error occurs.
     */
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
                groupId,
                customAttributes: []
            },
            customAttributes: []
        }).then(n => Response.fromNotification(n));
    }

    /**
     * Unblock a user in the group.
     *
     * @remarks
     * Authorization:
     * * Only the group owner or group managers can unblock users.
     *   Otherwise, throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#NOT_GROUP_OWNER_OR_MANAGER_TO_REMOVE_BLOCKED_USER}.
     *
     * Notifications:
     * * If the server property `turms.service.notification.group-blocked-user-removed.notify-requester-other-online-sessions`
     *   is true (true by default), the server will send a unblock user notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.group-blocked-user-removed.notify-blocked-user`,
     *   is true (false by default), the server will send a unblock user notification to the target user actively.
     * * If the server property `turms.service.notification.group-blocked-user-removed.notify-group-members`
     *   is true (false by default), the server will send a unblock user notification to all group members of the target group actively.
     *
     * @param groupId - the target group ID.
     * @param userId - the target user ID.
     * @throws {@link ResponseError} if an error occurs.
     */
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
                userId,
                customAttributes: []
            },
            customAttributes: []
        }).then(n => Response.fromNotification(n));
    }

    /**
     * Find blocked user IDs.
     *
     * @param groupId - the target group ID.
     * @param lastUpdatedDate - the last updated date of blocked user IDs stored locally.
     * The server will only return blocked user IDs that are updated after {@link lastUpdatedDate}.
     * If null, all blocked user IDs will be returned.
     * @throws {@link ResponseError} if an error occurs.
     */
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
                lastUpdatedDate: DataParser.getDateTimeStr(lastUpdatedDate),
                customAttributes: []
            },
            customAttributes: []
        }).then(n => Response.fromNotification(n, data => NotificationUtil.getLongsWithVersion(data)));
    }

    /**
     * Find blocked user infos.
     *
     * @param groupId - the target group ID.
     * @param lastUpdatedDate - the last updated date of blocked user infos stored locally.
     * The server will only return blocked user infos that are updated after {@link lastUpdatedDate}.
     * If null, all blocked user infos will be returned.
     * @throws {@link ResponseError} if an error occurs.
     */
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
                lastUpdatedDate: DataParser.getDateTimeStr(lastUpdatedDate),
                customAttributes: []
            },
            customAttributes: []
        }).then(n => Response.fromNotification(n, data => NotificationUtil.transform(data.userInfosWithVersion)));
    }

    // Group Enrollment

    /**
     * Create an invitation.
     *
     * @remarks
     * Authorization:
     * * If {@link inviteeId} is already a group member,
     *   throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#SEND_GROUP_INVITATION_TO_GROUP_MEMBER}.
     * * Depending on the group join strategy, if the group do not use the invitation strategy
     *   throws {@link {@link ResponseError}} with the code
     *   {@link ResponseStatusCode#NOT_GROUP_OWNER_TO_SEND_GROUP_INVITATION},
     *   {@link ResponseStatusCode#NOT_GROUP_OWNER_OR_MANAGER_TO_SEND_GROUP_INVITATION},
     *   or {@link ResponseStatusCode#NOT_GROUP_MEMBER_TO_SEND_GROUP_INVITATION}.
     * * If the group allows adding users as new group members without users' approval,
     *   throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#SEND_GROUP_INVITATION_TO_GROUP_NOT_REQUIRING_USERS_APPROVAL}.
     * * If the group does not exist,
     *   throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#ADD_USER_TO_INACTIVE_GROUP}.
     *
     * Notifications:
     * * If the server property `turms.service.notification.group-invitation-added.notify-requester-other-online-sessions`
     *   is true (true by default), the server will send a new invitation notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.group-invitation-added.notify-group-owner-and-managers`
     *   is true (true by default), the server will send a new invitation notification to the group owner and managers actively.
     * * If the server property `turms.service.notification.group-invitation-added.notify-group-members`,
     *   is true (false by default), the server will send a new invitation notification to all group members of the target group actively.
     * * If the server property `turms.service.notification.group-invitation-added.notify-invitee`,
     *   is true (true by default), the server will send a new invitation notification to the target user actively.
     *
     * @param groupId - the target group ID.
     * @param inviteeId - the target user ID.
     * @param content - the invitation content.
     * @returns the invitation ID.
     * @throws {@link ResponseError} if an error occurs.
     */
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
                content,
                customAttributes: []
            },
            customAttributes: []
        }).then(n => Response.fromNotification(n, data => NotificationUtil.getLongOrThrow(data)));
    }

    /**
     * Delete/Recall an invitation.
     *
     * @remarks
     * Authorization:
     * * If the server property `turms.service.group.invitation.allow-recall-pending-invitation-by-sender`
     *   is true (false by default), the logged-in user can recall pending invitations sent by themselves.
     *   Otherwise, throws {@link {@link ResponseError}}.
     * * If the server property `turms.service.group.invitation.allow-recall-pending-invitation-by-owner-and-manager`
     *   is true (false by default), the logged-in user can recall pending invitations only if they are the group owner or manager of the invitation.
     *   Otherwise, throws {@link {@link ResponseError}}.
     * * For the above two cases, the following codes will be thrown according to different properties:
     *   {@link ResponseStatusCode#RECALLING_GROUP_INVITATION_IS_DISABLED} if the above two properties are false.
     *   {@link ResponseStatusCode#NOT_GROUP_OWNER_OR_MANAGER_TO_RECALL_GROUP_INVITATION},
     *   {@link ResponseStatusCode#NOT_GROUP_OWNER_OR_MANAGER_OR_SENDER_TO_RECALL_GROUP_INVITATION}
     * * If the group invitation is not pending (e.g. expired, accepted, deleted, etc),
     *   throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#RECALL_NON_PENDING_GROUP_INVITATION}.
     *
     * Notifications:
     * * If the server property `turms.service.notification.group-invitation-recalled.notify-requester-other-online-sessions`
     *   is true (true by default), the server will send a delete invitation notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.group-invitation-recalled.notify-group-owner-and-managers`
     *   is true (true by default), the server will send a delete invitation notification to the group owner and managers actively.
     * * If the server property `turms.service.notification.group-invitation-recalled.notify-group-members`,
     *   is true (false by default), the server will send a delete invitation notification to all group members of the target group actively.
     * * If the server property `turms.service.notification.group-invitation-recalled.notify-invitee`,
     *   is true (true by default), the server will send a delete invitation notification to the target user actively.
     * @throws {@link ResponseError} if an error occurs.
     */
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
                invitationId,
                customAttributes: []
            },
            customAttributes: []
        }).then(n => Response.fromNotification(n));
    }

    /**
     * Reply to a group invitation.
     *
     * @remarks
     * If the logged-in user accepts an invitation sent by a group,
     * the user will become a group member automatically.
     *
     * Authorization:
     * * If the logged-in user is not the invitee of the group invitation,
     *   throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#NOT_INVITEE_TO_UPDATE_GROUP_INVITATION}.
     * * If the group invitation is not pending (e.g. expired, accepted, deleted, etc),
     *   throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#UPDATE_NON_PENDING_GROUP_INVITATION}.
     *
     * Notifications:
     * * If the server property `turms.service.notification.group-invitation-replied.notify-requester-other-online-sessions`,
     *   is true (true by default), the server will send a reply group invitation notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.group-invitation-replied.notify-group-invitation-inviter`,
     *   is true (true by default), the server will send a reply group invitation notification to the group join request sender actively.
     * * If the server property `turms.service.notification.group-invitation-replied.notify-group-members`,
     *   is true (false by default), the server will send a reply group invitation notification to all group members of the target group actively.
     * * If the server property `turms.service.notification.group-invitation-replied.notify-group-owner-and-managers`,
     *   is true (true by default), the server will send a reply group invitation notification to the group owner and managers actively.
     *
     * @param invitationId - the invitation ID.
     * @param responseAction - the response action.
     * @param reason - the reason of the response.
     * @throws {@link ResponseError} if an error occurs.
     */
    replyInvitation({
        invitationId,
        responseAction,
        reason
                    }: {
        invitationId: string,
        responseAction: ResponseAction,
        reason?: string
                    }): Promise<Response<void>> {
        if (Validator.isFalsy(invitationId)) {
            return ResponseError.notFalsyPromise('invitationId');
        }
        if (Validator.isFalsy(responseAction)) {
            return ResponseError.notFalsyPromise('responseAction');
        }
        return this._turmsClient.driver.send({
            updateGroupInvitationRequest: {
                invitationId,
                responseAction,
                reason,
                customAttributes: []
            },
            customAttributes: []
        }).then(n => Response.fromNotification(n));
    }

    /**
     * Find invitations.
     *
     * @param groupId - the target group ID.
     * @param lastUpdatedDate - the last updated date of invitations stored locally.
     * The server will only return groups that are updated after {@link lastUpdatedDate}.
     * If null, all group IDs will be returned.
     * @returns a list of invitation IDs and the version.
     * Note: The version can be used to update the last updated date stored locally.
     * @throws {@link ResponseError} if an error occurs.
     */
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
                lastUpdatedDate: DataParser.getDateTimeStr(lastUpdatedDate),
                customAttributes: []
            },
            customAttributes: []
        }).then(n => Response.fromNotification(n, data => NotificationUtil.transform(data.groupInvitationsWithVersion)));
    }

    /**
     * Find invitations.
     *
     * @param areSentByMe - whether the invitations are sent by me.
     * @param lastUpdatedDate - the last updated date of invitations stored locally.
     * The server will only return invitations that are updated after {@link lastUpdatedDate}.
     * If null, all invitations will be returned.
     * @returns a list of invitation IDs and the version.
     * Note: The version can be used to update the last updated date stored locally.
     * @throws {@link ResponseError} if an error occurs.
     */
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
                lastUpdatedDate: DataParser.getDateTimeStr(lastUpdatedDate),
                customAttributes: []
            },
            customAttributes: []
        }).then(n => Response.fromNotification(n, data => NotificationUtil.transform(data.groupInvitationsWithVersion)));
    }

    /**
     * Create a group join/membership request.
     *
     * @remarks
     * Authorization:
     * * If the logged-in user has been blocked by the group,
     *   throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#BLOCKED_USER_SEND_GROUP_JOIN_REQUEST}.
     * * If the logged-in user trys to send a join request to the group
     *   in which they are already a member,
     *   throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#GROUP_MEMBER_SEND_GROUP_JOIN_REQUEST}.
     * * If the group does not allow group join requests,
     *   throws {@link {@link ResponseError}} with the code:
     *   {@link ResponseStatusCode#SEND_GROUP_JOIN_REQUEST_TO_GROUP_USING_INVITATION},
     *   {@link ResponseStatusCode#SEND_GROUP_JOIN_REQUEST_TO_GROUP_USING_MEMBERSHIP_REQUEST},
     *   or {@link ResponseStatusCode#SEND_GROUP_JOIN_REQUEST_TO_GROUP_USING_QUESTION}.
     *
     * Notifications:
     * * If the server property `turms.service.notification.group-join-request-created.notify-requester-other-online-sessions`
     *   is true (true by default), the server will send a group membership request notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.group-join-request-created.notify-group-owner-and-managers`,
     *   is true (true by default), the server will send a group membership request notification to the group owner and managers actively.
     * * If the server property `turms.service.notification.group-join-request-created.notify-group-members`
     *   is true (false by default), the server will send a group membership request notification to all group members of the target group actively.
     *
     * @param groupId - the target group ID.
     * @param content - the request content.
     * @returns the request ID.
     * @throws {@link ResponseError} if an error occurs.
     */
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
                content,
                customAttributes: []
            },
            customAttributes: []
        }).then(n => Response.fromNotification(n, data => NotificationUtil.getLongOrThrow(data)));
    }

    /**
     * Delete/Recall a group join/membership request.
     *
     * @remarks
     * Authorization:
     * * If the server property `turms.service.group.join-request.allow-recall-pending-join-request-by-sender`
     *   is true (false by default), the logged-in user can recall pending join requests sent by themselves.
     *   Otherwise, throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#RECALLING_GROUP_JOIN_REQUEST_IS_DISABLED}.
     * * If the logged-in user is not the sender of the group join request,
     *   throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#NOT_SENDER_TO_RECALL_GROUP_JOIN_REQUEST}.
     * * If the group join request is not pending (e.g. expired, accepted, deleted, etc),
     *   throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#RECALL_NON_PENDING_GROUP_JOIN_REQUEST}.
     *
     * Notifications:
     * * If the server property `turms.service.notification.group-join-request-recalled.notify-requester-other-online-sessions`
     *   is true (true by default), the server will send a delete join request notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.group-join-request-recalled.notify-group-owner-and-managers`
     *   is true (true by default), the server will send a delete join request notification to the group owner and managers actively.
     * * If the server property `turms.service.notification.group-join-request-recalled.notify-group-members`,
     *   is true (false by default), the server will send a delete join request notification to all group members of the target group actively.
     * @throws {@link ResponseError} if an error occurs.
     */
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
                requestId,
                customAttributes: []
            },
            customAttributes: []
        }).then(n => Response.fromNotification(n));
    }

    /**
     * Reply a group join/membership request.
     *
     * @remarks
     * If the logged-in user accepts/approves a join request sent by a user,
     * the user will become a group member automatically.
     *
     * Authorization:
     * 1. If the logged-in user is not the group owner or manager of the group,
     * throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#NOT_GROUP_OWNER_OR_MANAGER_TO_UPDATE_GROUP_JOIN_REQUEST}.
     * 2. If the group join request is not pending (e.g. expired, accepted, deleted, etc),
     * throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#UPDATE_NON_PENDING_GROUP_JOIN_REQUEST}.
     *
     * Notifications:
     * * If the server property `turms.service.notification.group-join-request-replied.notify-requester-other-online-sessions`,
     *   is true (true by default), the server will send a reply group join request notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.group-join-request-replied.notify-group-join-request-sender`,
     *   is true (true by default), the server will send a reply group join request notification to the group join request sender actively.
     * * If the server property `turms.service.notification.group-join-request-replied.notify-group-members`,
     *   is true (false by default), the server will send a reply group join request notification to all group members of the target group actively.
     * * If the server property `turms.service.notification.group-join-request-replied.notify-group-owner-and-managers`,
     *   is true (true by default), the server will send a reply group join request notification to the group owner and managers actively.
     *
     * @param requestId - the target group join request ID.
     * @param responseAction - the response action.
     * @param reason - the reason of the response.
     * @throws {@link ResponseError} if an error occurs.
     */
    replyJoinRequest({
                        requestId,
                        responseAction,
                        reason
                    }: {
        requestId: string,
        responseAction: ResponseAction,
        reason?: string
    }): Promise<Response<void>> {
        if (Validator.isFalsy(requestId)) {
            return ResponseError.notFalsyPromise('requestId');
        }
        if (Validator.isFalsy(responseAction)) {
            return ResponseError.notFalsyPromise('responseAction');
        }
        return this._turmsClient.driver.send({
            updateGroupJoinRequestRequest: {
                requestId,
                responseAction,
                reason,
                customAttributes: []
            },
            customAttributes: []
        }).then(n => Response.fromNotification(n));
    }

    /**
     * Find group join/membership requests.
     *
     * @param groupId - the target group ID.
     * @param lastUpdatedDate - the last updated date of requests stored locally.
     * The server will only return requests that are updated after {@link lastUpdatedDate}.
     * If null, all requests will be returned.
     * @returns a list of request IDs and the version.
     * Note: The version can be used to update the last updated date stored locally.
     * @throws {@link ResponseError} if an error occurs.
     */
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
                lastUpdatedDate: DataParser.getDateTimeStr(lastUpdatedDate),
                customAttributes: []
            },
            customAttributes: []
        }).then(n => Response.fromNotification(n, data => NotificationUtil.transform(data.groupJoinRequestsWithVersion)));
    }

    /**
     * Find group join/membership requests sent by the logged-in user.
     *
     * @param lastUpdatedDate - the last updated date of requests stored locally.
     * The server will only return requests that are updated after {@link lastUpdatedDate}.
     * If null, all requests will be returned.
     * @returns a list of request IDs and the version.
     * Note: The version can be used to update the last updated date stored locally.
     * @throws {@link ResponseError} if an error occurs.
     */
    querySentJoinRequests({
        lastUpdatedDate
    }: {
        lastUpdatedDate?: Date
    } = {}): Promise<Response<ParsedModel.GroupJoinRequestsWithVersion | undefined>> {
        return this._turmsClient.driver.send({
            queryGroupJoinRequestsRequest: {
                lastUpdatedDate: DataParser.getDateTimeStr(lastUpdatedDate),
                customAttributes: []
            },
            customAttributes: []
        }).then(n => Response.fromNotification(n, data => NotificationUtil.transform(data.groupJoinRequestsWithVersion)));
    }

    /**
     * Find group join/membership questions.
     *
     * @remarks
     * Authorization:
     * * Only the owner and managers have the right to fetch questions with answers
     *
     * @param groupId - the target group ID.
     * @param withAnswers - Whether to return the answers.
     * @param lastUpdatedDate - the last updated date of questions stored locally.
     * The server will only return questions that are updated after {@link lastUpdatedDate}.
     * If null, all questions will be returned.
     * @returns a list of question IDs and the version.
     * Note: The version can be used to update the last updated date stored locally.
     * @throws {@link ResponseError} if an error occurs.
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
                lastUpdatedDate: DataParser.getDateTimeStr(lastUpdatedDate),
                customAttributes: []
            },
            customAttributes: []
        }).then(n => Response.fromNotification(n, data => NotificationUtil.transform(data.groupJoinQuestionsWithVersion)));
    }

    /**
     * Answer group join/membership questions, and join the group automatically
     * if the logged-in user has answered some questions correctly
     * and acquire the minimum score to join.
     *
     * @param questionIdToAnswer - the map of question ID to answer.
     * @returns the group membership questions answer result.
     * @throws {@link ResponseError} if an error occurs.
     */
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
                questionIdToAnswer: questionIdToAnswer,
                customAttributes: []
            },
            customAttributes: []
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

    /**
     * Add group members.
     *
     * @remarks
     * Authorization:
     * * If the group is inactive,
     *   throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#ADD_USER_TO_INACTIVE_GROUP}.
     * * If the group has reached the maximum number of group members,
     *   throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#ADD_USER_TO_GROUP_WITH_SIZE_LIMIT_REACHED}.
     * * If the group doesn't allow add users as group members directly,
     *   throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#ADD_USER_TO_GROUP_REQUIRING_USERS_APPROVAL}.
     * * When the logged-in user tries to add themselves as a group member,
     *   they will become a group member if the group uses member requests as the join strategy.
     *   Otherwise, throws the following codes according to different join strategies:
     *   {@link ResponseStatusCode#USER_JOIN_GROUP_WITHOUT_ACCEPTING_GROUP_INVITATION},
     *   {@link ResponseStatusCode#USER_JOIN_GROUP_WITHOUT_ANSWERING_GROUP_QUESTION},
     *   {@link ResponseStatusCode#USER_JOIN_GROUP_WITHOUT_SENDING_GROUP_JOIN_REQUEST}.
     * * If the logged-in user has no permission to add new group members,
     *   throws {@link {@link ResponseError}} with one of the following codes:
     *   {@link ResponseStatusCode#NOT_GROUP_OWNER_TO_ADD_GROUP_MEMBER},
     *   {@link ResponseStatusCode#NOT_GROUP_OWNER_OR_MANAGER_TO_ADD_GROUP_MEMBER},
     *   {@link ResponseStatusCode#NOT_GROUP_MEMBER_TO_ADD_GROUP_MEMBER},
     *   {@link ResponseStatusCode#NOT_GROUP_OWNER_TO_ADD_GROUP_MANAGER}.
     * * If {@link userIds} contains a blocked user ID,
     *   throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#ADD_BLOCKED_USER_TO_GROUP}.
     *
     * Notifications:
     * * If the server property `turms.service.notification.group-member-added.notify-requester-other-online-sessions`
     *   is true (true by default), the server will send a add group member notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.group-member-added.notify-added-group-member`,
     *   is true (true by default), the server will send a add group member notification to all other online sessions of the added group member.
     * * If the server property `turms.service.notification.group-member-added.notify-other-group-members`,
     *   is true (true by default), the server will send a add group member notification to all other online sessions of the other group members.
     *
     * @param groupId - the target group ID.
     * @param userIds - the target user IDs.
     * @param name - the name of the group member.
     * @param role - the role of the group member.
     * @param muteEndDate - the mute end date of the group member.
     * @throws {@link ResponseError} if an error occurs.
     */
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
                muteEndDate: DataParser.getDateTimeStr(muteEndDate),
                customAttributes: []
            },
            customAttributes: []
        }).then(n => Response.fromNotification(n));
    }

    /**
     * Join a group.
     *
     * @remarks
     * Authorization:
     * * When the logged-in user tries to add themselves as a group member,
     *   they will become a group member if the group uses member requests as the join strategy.
     *   Otherwise, throws the following codes according to different join strategies:
     *   {@link ResponseStatusCode#USER_JOIN_GROUP_WITHOUT_ACCEPTING_GROUP_INVITATION},
     *   {@link ResponseStatusCode#USER_JOIN_GROUP_WITHOUT_ANSWERING_GROUP_QUESTION},
     *   {@link ResponseStatusCode#USER_JOIN_GROUP_WITHOUT_SENDING_GROUP_JOIN_REQUEST}.
     *
     * Notifications:
     * * If the server property `turms.service.notification.group-member-added.notify-requester-other-online-sessions`
     *   is true (true by default), the server will send a add group member notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.group-member-added.notify-added-group-member`,
     *   is true (true by default), the server will send a add group member notification to all other online sessions of the added group member.
     * * If the server property `turms.service.notification.group-member-added.notify-other-group-members`,
     *   is true (true by default), the server will send a add group member notification to all other online sessions of the other group members.
     *
     * @param groupId - the target group ID.
     * @param name - the name as the group member.
     * @throws {@link ResponseError} if an error occurs.
     */
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

    /**
     * Quit a group.
     *
     * @remarks
     * Notifications:
     * * If the server property `turms.service.notification.group-member-removed.notify-requester-other-online-sessions`,
     *   is true (true by default), the server will send a delete group member notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.group-member-removed.notify-other-group-members`,
     *   is true (true by default), the server will send a delete group member notification to all other group members of the group actively.
     *
     * @param groupId - the target group ID.
     * @param successorId - the new successor ID.
     * If the logged-in user is the owner of the group, they must transfer the group ownership to the {@link successorId},
     * throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#GROUP_OWNER_QUIT_WITHOUT_SPECIFYING_SUCCESSOR} otherwise.
     * And the successor will become the group owner.
     * The successor must already be a member of the group, throws {@link {@link ResponseError}} with the code
     * {@link ResponseStatusCode#GROUP_SUCCESSOR_NOT_GROUP_MEMBER} otherwise.
     * @param quitAfterTransfer - whether to quit the group after transfer the group ownership.
     * If false, the logged-in user will become a normal group member (not the group admin).
     * If null, the value will not be changed.
     *
     * Authorization:
     * * If the logged-in user is not the owner of the group,
     *   {@link {@link ResponseError}} with the code {@link ResponseStatusCode#NOT_GROUP_OWNER_TO_TRANSFER_GROUP} will be thrown.
     * @throws {@link ResponseError} if an error occurs.
     */
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
                quitAfterTransfer,
                customAttributes: []
            },
            customAttributes: []
        }).then(n => Response.fromNotification(n));
    }

    /**
     * Remove group members.
     *
     * @remarks
     * Authorization:
     * * If the logged-in user is not the group owner or manager of the group,
     *   throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#NOT_GROUP_OWNER_OR_MANAGER_TO_REMOVE_GROUP_MEMBER}.
     *
     * Notifications:
     * * If the server property `turms.service.notification.group-member-removed.notify-requester-other-online-sessions`,
     *   is true (true by default), the server will send a delete group member notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.group-member-removed.notify-removed-group-member`,
     *   is true (true by default), the server will send a delete group member notification to the removed group member actively.
     * * If the server property `turms.service.notification.group-member-removed.notify-other-group-members`,
     *   is true (true by default), the server will send a delete group member notification to all other group members of the group actively.
     *
     * @param groupId - the target group ID.
     * @param memberIds - the target member IDs.
     * @throws {@link ResponseError} if an error occurs.
     */
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
                memberIds,
                customAttributes: []
            },
            customAttributes: []
        }).then(n => Response.fromNotification(n));
    }

    /**
     * Update group member info.
     *
     * @remarks
     * Authorization:
     * * If the logged-in user is not the group owner or manager of the group,
     *   throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#NOT_GROUP_OWNER_OR_MANAGER_TO_UPDATE_GROUP_INFO}.
     *
     * Notifications:
     * * If the server property `turms.service.notification.group-member-info-updated.notify-requester-other-online-sessions`,
     *   is true (true by default), the server will send a update group member info notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.group-member-info-updated.notify-updated-group-member`,
     *   is true (false by default), the server will send a update group member info notification to the updated group member actively.
     * * If the server property `turms.service.notification.group-member-info-updated.notify-other-group-members`,
     *   is true (false by default), the server will send a update group member info notification to all other group members of the group actively.
     *
     * @param groupId - the target group ID.
     * @param memberId - the target member ID.
     * @param name - the new name of the group member.
     * If null, the name will not be updated.
     * @param role - the new role of the group member.
     * If null, the role will not be updated.
     * @param muteEndDate - the new mute end date of the group member.
     * If null, the mute end date will not be updated.
     *
     * Authorization:
     * * If the logged-in user is not the group owner or manager of the group,
     *   throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#NOT_GROUP_OWNER_OR_MANAGER_TO_MUTE_GROUP_MEMBER}
     * * If the logged-in user is not the group owner,
     *   throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#MUTE_GROUP_MEMBER_WITH_ROLE_EQUAL_TO_OR_HIGHER_THAN_REQUESTER}.
     * @throws {@link ResponseError} if an error occurs.
     */
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
                muteEndDate: DataParser.getDateTimeStr(muteEndDate),
                customAttributes: []
            },
            customAttributes: []
        }).then(n => Response.fromNotification(n));
    }

    /**
     * Mute group member.
     *
     * @remarks
     * Authorization:
     * * If the logged-in user is not the group owner or manager of the group,
     *   throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#NOT_GROUP_OWNER_OR_MANAGER_TO_MUTE_GROUP_MEMBER}
     * * If the logged-in user is not the group owner,
     *   throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#MUTE_GROUP_MEMBER_WITH_ROLE_EQUAL_TO_OR_HIGHER_THAN_REQUESTER}.
     *
     * Notifications:
     * * If the server property `turms.service.notification.group-member-info-updated.notify-requester-other-online-sessions`,
     *   is true (true by default), the server will send a update group member info notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.group-member-info-updated.notify-updated-group-member`,
     *   is true (false by default), the server will send a update group member info notification to the updated group member actively.
     * * If the server property `turms.service.notification.group-member-info-updated.notify-other-group-members`,
     *   is true (false by default), the server will send a update group member info notification to all other group members of the group actively.
     *
     * @param groupId - the target group ID.
     * @param memberId - the target member ID.
     * @param muteEndDate - the new mute end date of the group member.
     * @throws {@link ResponseError} if an error occurs.
     */
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

    /**
     * Unmute group member.
     *
     * @remarks
     * Authorization:
     * * If the logged-in user is not the group owner or manager of the group,
     *   throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#NOT_GROUP_OWNER_OR_MANAGER_TO_MUTE_GROUP_MEMBER}
     * * If the logged-in user is not the group owner,
     *   throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#MUTE_GROUP_MEMBER_WITH_ROLE_EQUAL_TO_OR_HIGHER_THAN_REQUESTER}.
     *
     * Notifications:
     * * If the server property `turms.service.notification.group-member-info-updated.notify-requester-other-online-sessions`,
     *   is true (true by default), the server will send a update group member info notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.group-member-info-updated.notify-updated-group-member`,
     *   is true (false by default), the server will send a update group member info notification to the updated group member actively.
     * * If the server property `turms.service.notification.group-member-info-updated.notify-other-group-members`,
     *   is true (false by default), the server will send a update group member info notification to all other group members of the group actively.
     *
     * @param groupId - the target group ID.
     * @param memberId - the target member ID.
     * @throws {@link ResponseError} if an error occurs.
     */
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

    /**
     * Find group members.
     *
     * @param groupId - the target group ID.
     * @param withStatus - whether to return the session status of the group members.
     * @param lastUpdatedDate - the last updated date of the group members stored locally.
     * The server will only return group members that are updated after {@link lastUpdatedDate}.
     * If null, all group members will be returned.
     * @returns group members and the version.
     * Note: The version can be used to update the last updated date stored locally.
     * @throws {@link ResponseError} if an error occurs.
     */
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
                memberIds: [],
                customAttributes: []
            },
            customAttributes: []
        }).then(n => Response.fromNotification(n, data => NotificationUtil.transform(data.groupMembersWithVersion)));
    }

    /**
     * Find group members.
     *
     * @param groupId - the target group ID.
     * @param memberIds - the target member IDs.
     * @param withStatus - whether to return the session status of the group members.
     * @returns group members and the version.
     * Note: The version can be used to update the last updated date stored locally.
     * @throws {@link ResponseError} if an error occurs.
     */
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
                withStatus,
                customAttributes: []
            },
            customAttributes: []
        }).then(n => Response.fromNotification(n, data => NotificationUtil.transform(data.groupMembersWithVersion)));
    }
}