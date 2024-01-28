import NotificationUtil from '../util/notification-util';
import { ParsedModel } from '../model/parsed-model';
import Response from '../model/response';
import ResponseError from '../error/response-error';
import TurmsClient from '../turms-client';
import Validator from '../util/validator';

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
     *   and pass these user IDs as {@link targetIds} to get all private conversations.
     * * The returned {@link PrivateConversation} does NOT contain messages.
     *   To find messages in conversations, you can use methods like
     *   {@link MessageService#queryMessages} and {@link MessageService#queryMessagesWithTotal}.
     *
     * @param targetIds - the target user IDs.
     * If empty, an empty list of conversations is returned.
     * @returns a list of private conversations.
     * Note that the list only contains conversations in which the logged-in user is a participant.
     * If the logged-in user is not a participant of a specified conversation,
     * these conversations will be filtered out on the server, and no error will be thrown.
     * @throws {@link ResponseError} if an error occurs.
     */
    queryPrivateConversations({
        targetIds
    }: {
        targetIds: string[]
    }): Promise<Response<ParsedModel.PrivateConversation[]>> {
        if (Validator.isFalsy(targetIds)) {
            return Promise.resolve(Response.emptyList());
        }
        return this._turmsClient.driver.send({
            queryConversationsRequest: {
                targetIds,
                groupIds: []
            }
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
                targetIds: []
            }
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
     * @param targetId - the target user ID.
     * @param readDate - the read date.
     * If null, the current time is used.
     * @throws {@link ResponseError} if an error occurs.
     */
    updatePrivateConversationReadDate({
        targetId,
        readDate
    }: {
        targetId: string,
        readDate?: Date
    }): Promise<Response<void>> {
        if (Validator.isFalsy(targetId)) {
            return ResponseError.notFalsyPromise('targetId');
        }
        readDate = readDate ?? new Date();
        return this._turmsClient.driver.send({
            updateConversationRequest: {
                targetId,
                readDate: '' + readDate.getTime()
            }
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
                readDate: '' + readDate.getTime()
            }
        }).then(n => Response.fromNotification(n));
    }

    /**
     * Update the typing status of the target private conversation.
     *
     * @remarks
     * Authorization:
     * * If the server property `turms.service.conversation.typing-status.enabled`
     *   is true (true by default), throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#UPDATING_TYPING_STATUS_IS_DISABLED}.
     * * If the logged-in user is not a friend of {@link targetId}, throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#NOT_FRIEND_TO_SEND_TYPING_STATUS}.
     *
     * Notifications:
     * * If the server property `turms.service.conversation.typing-status.enabled`
     *   is true (true by default),
     *   the server will send a typing status update notification to the participant actively.
     *
     * @param targetId - the target user ID.
     * @throws {@link ResponseError} if an error occurs.
     */
    updatePrivateConversationTypingStatus({
        targetId
    }: {
        targetId: string
    }): Promise<Response<void>> {
        if (Validator.isFalsy(targetId)) {
            return ResponseError.notFalsyPromise('targetId');
        }
        return this._turmsClient.driver.send({
            updateTypingStatusRequest: {
                toId: targetId,
                isGroupMessage: false
            }
        }).then(n => Response.fromNotification(n));
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
                isGroupMessage: true
            }
        }).then(n => Response.fromNotification(n));
    }

}