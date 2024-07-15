import NotificationUtil from '../util/notification-util';
import { ParsedModel } from '../model/parsed-model';
import Response from '../model/response';
import ResponseError from '../error/response-error';
import TurmsClient from '../turms-client';
import Validator from '../util/validator';
import DataParser from '../util/data-parser';
import { Value } from '../model/proto/model/common/value';
import CollectionUtil from '../util/collection-util';

export default class ConversationService {

    private _turmsClient: TurmsClient;

    constructor(turmsClient: TurmsClient) {
        this._turmsClient = turmsClient;
    }

    /**
     * Find private conversations between the logged-in user and another user.
     *
     * @remarks
     * Common Scenarios:
     * * If you want to find all private conversations between
     *   the current logged-in user and other users,
     *   you can call methods like {@link UserService#queryRelatedUserIds},
     *   {@link UserService#queryFriends} to get all user IDs first,
     *   and pass these user IDs as {@link userIds} to get all private conversations.
     * * The returned {@link PrivateConversation} does NOT contain messages.
     *   To find messages in conversations, you can use methods like
     *   {@link MessageService#queryMessages} and {@link MessageService#queryMessagesWithTotal}.
     *
     * @param userIds - the target user IDs.
     * If empty, an empty list of conversations is returned.
     * @returns a list of private conversations.
     * Note that the list only contains conversations in which the logged-in user is a participant.
     * If the logged-in user is not a participant of a specified conversation,
     * these conversations will be filtered out on the server, and no error will be thrown.
     * @throws {@link ResponseError} if an error occurs.
     */
    queryPrivateConversations({
        userIds
    }: {
        userIds: string[]
    }): Promise<Response<ParsedModel.PrivateConversation[]>> {
        if (Validator.isFalsy(userIds)) {
            return Promise.resolve(Response.emptyList());
        }
        return this._turmsClient.driver.send({
            queryConversationsRequest: {
                userIds: userIds,
                groupIds: [],
                customAttributes: []
            },
            customAttributes: []
        }).then(n => Response.fromNotification(n, data => NotificationUtil.transformOrEmpty(data.conversations?.privateConversations)));
    }

    /**
     * Find group conversations in which the logged-in user is a member.
     *
     * @remarks
     * Common Scenarios:
     * * If you want to find all group conversations between
     *   the current logged-in user and groups in which the logged-in user is a member,
     *   you can call methods like {@link GroupService#queryJoinedGroupIds},
     *   {@link GroupService#queryJoinedGroupInfos} to get all group IDs first,
     *   and pass these group IDs as {@link groupIds} to get all group conversations.
     * * {@link GroupConversation} does NOT contain messages.
     *   To find messages in conversations, you can use methods like
     *   {@link MessageService#queryMessages} and {@link MessageService#queryMessagesWithTotal}.
     *
     * @param groupIds - the target group IDs.
     * If empty, an empty list of conversations is returned.
     * @returns a list of group conversations.
     * Note that the list only contains conversations in which the logged-in user is a participant.
     * If the logged-in user is not a participant of a specified conversation,
     * these conversations will be filtered out on the server, and no error will be thrown.
     * @throws {@link ResponseError} if an error occurs.
     */
    queryGroupConversations({
        groupIds
    }: {
        groupIds: string[]
    }): Promise<Response<ParsedModel.GroupConversation[]>> {
        if (Validator.isFalsy(groupIds)) {
            return Promise.resolve(Response.emptyList());
        }
        return this._turmsClient.driver.send({
            queryConversationsRequest: {
                groupIds,
                userIds: [],
                customAttributes: []
            },
            customAttributes: []
        }).then(n => Response.fromNotification(n, data => NotificationUtil.transform(data.conversations?.groupConversations)?.map(c => ({
            groupId: c.groupId,
            memberIdToReadDate: NotificationUtil.transformMapValToDate(c.memberIdToReadDate)
        })) || []));
    }

    /**
     * Update the read date of the target private conversation.
     *
     * @remarks
     * Common Scenarios:
     * * To find the read date of a conversation actively (if no notification is received from the server),
     *   you can call {@link queryPrivateConversations}.
     *
     * Authorization:
     * * If the server property `turms.service.conversation.read-receipt.enabled`
     *   is false (true by default), throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#UPDATING_READ_DATE_IS_DISABLED}.
     *
     * Notifications:
     * * If the server property `turms.service.notification.private-conversation-read-date-updated.notify-contact`
     *   is true (false by default),
     *   the server will send a read date update notification to the participant actively.
     * * If the server property `turms.service.notification.private-conversation-read-date-updated.notify-requester-other-online-sessions`
     *   is true (true by default),
     *   the server will send a read date update notification to all other online sessions of the logged-in user actively.
     *
     * @param userId - the target user ID.
     * @param readDate - the read date.
     * If null, the current time is used.
     * @throws {@link ResponseError} if an error occurs.
     */
    updatePrivateConversationReadDate({
        userId,
        readDate
    }: {
        userId: string,
        readDate?: Date
    }): Promise<Response<void>> {
        if (Validator.isFalsy(userId)) {
            return ResponseError.notFalsyPromise('userId');
        }
        readDate = readDate ?? new Date();
        return this._turmsClient.driver.send({
            updateConversationRequest: {
                userId,
                readDate: DataParser.getDateTimeStr(readDate),
                customAttributes: []
            },
            customAttributes: []
        }).then(n => Response.fromNotification(n));
    }

    /**
     * Update the read date of the target group conversation.
     *
     * @remarks
     * Common Scenarios:
     * * To find the read date of a conversation actively (if no notification is received from the server),
     *   you can call {@link queryGroupConversations}.
     *
     * Authorization:
     * * If the server property `turms.service.conversation.read-receipt.enabled`
     *   is false (true by default), throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#UPDATING_READ_DATE_IS_DISABLED}.
     * * If the target group doesn't exist, throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#UPDATING_READ_DATE_OF_NONEXISTENT_GROUP_CONVERSATION}.
     * * If the target group has disabled read receipts, throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#UPDATING_READ_DATE_IS_DISABLED_BY_GROUP}.
     * * If the logged-in user is not a member of the target group, throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#NOT_GROUP_MEMBER_TO_UPDATE_READ_DATE_OF_GROUP_CONVERSATION}.
     *
     * Notifications:
     * * If the server property `turms.service.notification.group-conversation-read-date-updated.notify-other-group-members`
     *   is true (false by default),
     *   the server will send a read date update notification to all participants actively.
     * * If the server property `turms.service.notification.group-conversation-read-date-updated.notify-requester-other-online-sessions`
     *   is true (true by default),
     *   the server will send a read date update notification to all other online sessions of the logged-in user actively.
     *
     * @param groupId - the target group ID.
     * @param readDate - the read date.
     * If null, the current time is used.
     * @throws {@link ResponseError} if an error occurs.
     */
    updateGroupConversationReadDate({
        groupId,
        readDate
    }: {
        groupId: string,
        readDate?: Date
    }): Promise<Response<void>> {
        if (Validator.isFalsy(groupId)) {
            return ResponseError.notFalsyPromise('groupId');
        }
        readDate = readDate ?? new Date();
        return this._turmsClient.driver.send({
            updateConversationRequest: {
                groupId,
                readDate: DataParser.getDateTimeStr(readDate),
                customAttributes: []
            },
            customAttributes: []
        }).then(n => Response.fromNotification(n));
    }

    /**
     * Upsert private conversation settings, such as "sticky", "new message alert", etc.
     * Note that only the settings specified in `turms.service.conversation.settings.allowed-settings` can be upserted.
     *
     * @remarks
     * Notifications:
     * * If the server property `turms.service.notification.private-conversation-setting-updated.notify-requester-other-online-sessions` is true (true by default),
     *   the server will send a conversation settings updated notification to all other online sessions of the logged-in user actively.
     *
     * @param userId - the target user ID.
     * @param settings - the private conversation settings to upsert.
     * @throws {@link ResponseError} if an error occurs.
     * * If trying to update any existing immutable setting, throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#ILLEGAL_ARGUMENT}
     * * If trying to upsert an unknown setting and the server property `turms.service.conversation.settings.ignore-unknown-settings-on-upsert` is
     *   false (false by default), throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#ILLEGAL_ARGUMENT}.
     */
    upsertPrivateConversationSettings({
        userId,
        settings
    }: {
        userId: string,
        settings: Record<string, Value>
    }): Promise<Response<void>> {
        if (Validator.isFalsy(userId)) {
            return ResponseError.notFalsyPromise('userId');
        }
        return this._turmsClient.driver.send({
            updateConversationSettingsRequest: {
                userId,
                settings,
                customAttributes: []
            },
            customAttributes: []
        }).then(n => Response.fromNotification(n));
    }

    /**
     * Upsert group conversation settings, such as "sticky", "new message alert", etc.
     * Note that only the settings specified in `turms.service.conversation.settings.allowed-settings` can be upserted.
     *
     * @remarks
     * Notifications:
     * * If the server property `turms.service.notification.group-conversation-setting-updated.notify-requester-other-online-sessions` is true (true by default),
     *   the server will send a conversation settings updated notification to all other online sessions of the logged-in user actively.
     *
     * @param groupId - the target group ID.
     * @param settings - the group conversation settings to upsert.
     * @throws {@link ResponseError} if an error occurs.
     * * If trying to update any existing immutable setting, throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#ILLEGAL_ARGUMENT}
     * * If trying to upsert an unknown setting and the server property `turms.service.conversation.settings.ignore-unknown-settings-on-upsert` is
     *   false (false by default), throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#ILLEGAL_ARGUMENT}.
     */
    upsertGroupConversationSettings({
        groupId,
        settings
    }: {
        groupId: string,
        settings: Record<string, Value>
    }): Promise<Response<void>> {
        if (Validator.isFalsy(groupId)) {
            return ResponseError.notFalsyPromise('groupId');
        }
        return this._turmsClient.driver.send({
            updateConversationSettingsRequest: {
                groupId,
                settings,
                customAttributes: []
            },
            customAttributes: []
        }).then(n => Response.fromNotification(n));
    }

    /**
     * Delete conversation settings.
     *
     * @remarks
     * Notifications:
     * * If the server property `turms.service.notification.private-conversation-setting-deleted.notify-requester-other-online-sessions` is true (true by default),
     *   and a private conversation setting is deleted, the server will send a user settings deleted notification to all other online sessions of the logged-in user actively.
     * * If the server property `turms.service.notification.group-conversation-setting-deleted.notify-requester-other-online-sessions` is true (true by default),
     *   and a group conversation setting is deleted, the server will send a group settings deleted notification to all other online sessions of the logged-in user actively.
     *
     * @param userIds - the target user IDs. If both {@link userIds} and {@link groupIds} are null, all deletable conversation settings will be deleted.
     * @param groupIds - the target group IDs. If both {@link userIds} and {@link groupIds} are null, all deletable conversation settings will be deleted.
     * @param names - the names of the conversation settings to delete. If null, all deletable conversation settings will be deleted.
     * @throws {@link ResponseError} if an error occurs.
     * * If trying to delete any non-deletable setting, throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#ILLEGAL_ARGUMENT}.
     */
    deleteConversationSettings({
        userIds,
        groupIds,
        names
    }: {
        userIds?: string[],
        groupIds?: string[],
        names?: string[]
    }): Promise<Response<void>> {
        return this._turmsClient.driver.send({
            deleteConversationSettingsRequest: {
                userIds: CollectionUtil.uniqueArray(userIds),
                groupIds: CollectionUtil.uniqueArray(groupIds),
                names: CollectionUtil.uniqueArray(names),
                customAttributes: []
            },
            customAttributes: []
        }).then(n => Response.fromNotification(n));
    }

    /**
     * Update the typing status of the target private conversation.
     *
     * @remarks
     * Authorization:
     * * If the server property `turms.service.conversation.typing-status.enabled`
     *   is true (true by default), throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#UPDATING_TYPING_STATUS_IS_DISABLED}.
     * * If the logged-in user is not a friend of {@link userId}, throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#NOT_FRIEND_TO_SEND_TYPING_STATUS}.
     *
     * Notifications:
     * * If the server property `turms.service.conversation.typing-status.enabled`
     *   is true (true by default),
     *   the server will send a typing status update notification to the participant actively.
     *
     * @param userId - the target user ID.
     * @throws {@link ResponseError} if an error occurs.
     */
    updatePrivateConversationTypingStatus({
        userId
    }: {
        userId: string
    }): Promise<Response<void>> {
        if (Validator.isFalsy(userId)) {
            return ResponseError.notFalsyPromise('userId');
        }
        return this._turmsClient.driver.send({
            updateTypingStatusRequest: {
                toId: userId,
                isGroupMessage: false,
                customAttributes: []
            },
            customAttributes: []
        }).then(n => Response.fromNotification(n));
    }

    /**
     * Find conversation settings.
     *
     * @param userIds - the target user IDs. If both {@link userIds} and {@link groupIds} are null, the settings of all private and group conversations will be returned.
     * @param groupIds - the target group IDs. If both {@link userIds} and {@link groupIds} are null, the settings of all private and group conversations will be returned.
     * @param names - the target setting names.
     * @param lastUpdatedDate - the last updated date of conversation settings stored locally.
     * The server will only return conversation settings if a setting has been updated after {@link lastUpdatedDate}.
     * @throws {@link ResponseError} if an error occurs.
     */
    queryConversationSettings({
        userIds,
        groupIds,
        names,
        lastUpdatedDate
    }: {
        userIds?: string[],
        groupIds?: string[],
        names?: string[],
        lastUpdatedDate?: Date
    }): Promise<Response<ParsedModel.ConversationSettings[]>> {
        return this._turmsClient.driver.send({
            queryConversationSettingsRequest: {
                userIds: CollectionUtil.uniqueArray(userIds),
                groupIds: CollectionUtil.uniqueArray(groupIds),
                names: CollectionUtil.uniqueArray(names),
                lastUpdatedDateStart: DataParser.getDateTimeStr(lastUpdatedDate),
                customAttributes: []
            },
            customAttributes: []
        }).then(n => Response.fromNotification(n, data => NotificationUtil.transform(data.conversationSettingsList.conversationSettingsList)));
    }

    /**
     * Update the typing status of the target group conversation.
     *
     * @remarks
     * Authorization:
     * * If the server property `turms.service.conversation.typing-status.enabled`
     *   is true (true by default), throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#UPDATING_TYPING_STATUS_IS_DISABLED}.
     * * If the logged-in user is not a member of the target group, throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#NOT_GROUP_MEMBER_TO_SEND_TYPING_STATUS}.
     *
     * Notifications:
     * * If the server property `turms.service.conversation.typing-status.enabled`
     *   is true (true by default),
     *   the server will send a typing status update notification to all participants actively.
     *
     * @param groupId - the target group ID.
     * @throws {@link ResponseError} if an error occurs.
     */
    updateGroupConversationTypingStatus({
        groupId
    }: {
        groupId: string
    }): Promise<Response<void>> {
        if (Validator.isFalsy(groupId)) {
            return ResponseError.notFalsyPromise('groupId');
        }
        return this._turmsClient.driver.send({
            updateTypingStatusRequest: {
                toId: groupId,
                isGroupMessage: true,
                customAttributes: []
            },
            customAttributes: []
        }).then(n => Response.fromNotification(n));
    }

}