import Long from 'long';

import { AudioFile } from '../model/proto/model/file/audio_file';
import BuiltinSystemMessageType from '../model/builtin-system-message-type';
import DataParser from '../util/data-parser';
import { File } from '../model/proto/model/file/file';
import { ImageFile } from '../model/proto/model/file/image_file';
import MessageAddition from '../model/message-addition';
import NotificationUtil from '../util/notification-util';
import { ParsedModel } from '../model/parsed-model';
import Response from '../model/response';
import ResponseError from '../error/response-error';
import TurmsClient from '../turms-client';
import { UserLocation } from '../model/proto/model/user/user_location';
import Validator from '../util/validator';
import { VideoFile } from '../model/proto/model/file/video_file';
import CollectionUtil from '../util/collection-util';

export default class MessageService {
    /**
     * Format: "@{userId}"
     * Example: "@{123}", "I need to talk with @{123} and @{321}"
     */
    private static readonly DEFAULT_MENTIONED_USER_IDS_PARSER = function (message: ParsedModel.Message): string[] {
        const regex = /@{(\d+?)}/g;
        if (!message.text) {
            return [];
        }
        const userIds = [];
        let matches;
        while ((matches = regex.exec(message.text))) {
            const match = matches[1];
            if (!isNaN(match)) {
                userIds.push(match);
            }
        }
        return CollectionUtil.uniqueArray(userIds);
    };

    private _turmsClient: TurmsClient;
    private _mentionedUserIdsParser?: (message: ParsedModel.Message) => string[];
    private _messageListeners: ((message: ParsedModel.Message, messageAddition: MessageAddition) => void)[] = [];

    constructor(turmsClient: TurmsClient) {
        this._turmsClient = turmsClient;
        this._turmsClient.driver
            .addNotificationListener(notification => {
                if (this._messageListeners.length && notification.relayedRequest) {
                    const request = notification.relayedRequest.createMessageRequest;
                    if (request) {
                        const message = MessageService._createMessageRequest2Message(notification.requesterId, request);
                        const addition = this._parseMessageAddition(message);
                        this._messageListeners.forEach(listener => listener(message, addition));
                    }
                }
            });
    }

    /**
     * Add a message listener that will be triggered when a new message arrives.
     * Note: To listen to notifications excluding messages,
     * use {@link NotificationService#addNotificationListener} instead.
     */
    addMessageListener(listener: (message: ParsedModel.Message, messageAddition: MessageAddition) => void): void {
        this._messageListeners.push(listener);
    }

    /**
     * Remove a message listener.
     */
    removeMessageListener(listener: (message: ParsedModel.Message, messageAddition: MessageAddition) => void): void {
        this._messageListeners = this._messageListeners.filter(cur => cur !== listener);
    }

    /**
     * Send a message.
     *
     * @remarks
     * Common Scenarios:
     * * A client can call {@link addMessageListener} to listen to incoming new messages.
     *
     * Authorization:
     *
     * For private messages,
     * * If the server property `turms.service.message.allow-send-messages-to-oneself`
     *   is true (false by default), the logged-in user can send messages to itself.
     *   Otherwise, throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#SENDING_MESSAGES_TO_ONESELF_IS_DISABLED}.
     * * If the server property `turms.service.message.allow-send-messages-to-stranger`
     *   is true (true by default), the logged-in user can send messages to strangers
     *   (has no relationship with the logged-in user).
     * * If the logged-in user has blocked the target user,
     *   throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#BLOCKED_USER_SEND_PRIVATE_MESSAGE}.
     *
     * For group messages,
     * * If the logged-in user has blocked the target group,
     *   throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#BLOCKED_USER_SEND_GROUP_MESSAGE}.
     * * If the logged-in user is not a group member, and the group does NOT allow non-members to send messages,
     *   throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#NOT_SPEAKABLE_GROUP_GUEST_TO_SEND_MESSAGE}.
     * * If the logged-in user has been muted,
     *   throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#MUTED_GROUP_MEMBER_SEND_MESSAGE}.
     * * If the target group has been deleted,
     *   throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#SEND_MESSAGE_TO_INACTIVE_GROUP}.
     * * If the target group has been muted,
     *   throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#SEND_MESSAGE_TO_MUTED_GROUP}.
     *
     * Notifications:
     * * If the server property `turms.service.notification.message-created.notify-message-recipients`
     *   is true (true by default), a new message notification will be sent to the message recipients actively.
     * * If the server property `turms.service.notification.message-created.notify-requester-other-online-sessions`
     *   is true (true by default), a new message notification will be sent to all other online sessions of the logged-in user actively.
     *
     * @param isGroupMessage - whether the message is a group message.
     * @param targetId - The target ID.
     * If {@link isGroupMessage} is true, the target ID is the group ID.
     * If {@link isGroupMessage} is false, the target ID is the user ID.
     * @param deliveryDate - The delivery date.
     * Note that {@link deliveryDate} will only work if the server property
     * `turms.service.message.time-type` is `client_time` (`local_server_time` by default).
     * @param text - the message text.
     * {@link text} can be anything you want. e.g. Markdown, base64 encoded bytes.
     * Note that if {@link text} is null, {@link records} must not be null.
     * @param records - the message records.
     * {@link records} can be anything you want. e.g. base64 encoded images (it is highly not recommended).
     * Note that if {@link records} is null, {@link text} must not be null.
     * @param burnAfter - The burn after the specified time.
     * Note that Turms server and client do NOT implement the `burn after` feature,
     * and they just store and pass {@link burnAfter} between server and clients.
     * @param preMessageId - The pre-message ID.
     * {@link preMessageId} is mainly used to arrange the order of messages on UI.
     * If what you want is to ensure every message will not be lost, even if the server crashes,
     * you can set the server property `turms.service.message.use-conversation-id` to true
     * (false by default).
     * @returns the message ID.
     * @throws {@link ResponseError} if an error occurs.
     */
    sendMessage({
        isGroupMessage,
        targetId,
        deliveryDate,
        text,
        records,
        burnAfter,
        preMessageId
    }: {
        isGroupMessage: boolean,
        targetId: string,
        deliveryDate?: Date,
        text?: string,
        records?: Uint8Array[],
        burnAfter?: number,
        preMessageId?: string
    }): Promise<Response<string>> {
        if (Validator.isFalsy(targetId)) {
            return ResponseError.notFalsyPromise('targetId');
        }
        if (Validator.isFalsy(text) && Validator.isFalsy(records)) {
            return ResponseError.illegalParamPromise('text and records must not all be null');
        }
        return this._turmsClient.driver.send({
            createMessageRequest: {
                groupId: isGroupMessage ? targetId : undefined,
                recipientId: !isGroupMessage ? targetId : undefined,
                deliveryDate: DataParser.getDateTimeStr(deliveryDate),
                text,
                records: records || [],
                burnAfter,
                preMessageId
            }
        }).then(n => Response.fromNotification(n, data => NotificationUtil.getLongOrThrow(data)));
    }

    /**
     * Forward a message.
     * In other words, create and send a new message based on a existing message
     * to the target user or group.
     *
     * @remarks
     * Authorization:
     * * If the logged-in user is not allowed to view the message with {@link messageId},
     *   throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#NOT_MESSAGE_RECIPIENT_OR_SENDER_TO_FORWARD_MESSAGE}.
     *
     * Notifications:
     * * If the server property `turms.service.notification.message-created.notify-message-recipients`
     *   is true (true by default), a new message notification will be sent to the message recipients actively.
     * * If the server property `turms.service.notification.message-created.notify-requester-other-online-sessions`
     *   is true (true by default), a new message notification will be sent to all other online sessions of the logged-in user actively.
     *
     * @param messageId - the message ID for copying.
     * @param isGroupMessage - whether the message is a group message.
     * @param targetId - the target ID.
     * If {@link isGroupMessage} is true, the target ID is the group ID.
     * If {@link isGroupMessage} is false, the target ID is the user ID.
     * @returns the message ID.
     * @throws {@link ResponseError} if an error occurs.
     */
    forwardMessage({
        messageId,
        isGroupMessage,
        targetId
    }: {
        messageId: string,
        isGroupMessage: boolean,
        targetId: string
    }): Promise<Response<string>> {
        if (Validator.isFalsy(messageId)) {
            return ResponseError.notFalsyPromise('messageId');
        }
        if (Validator.isFalsy(targetId)) {
            return ResponseError.notFalsyPromise('targetId');
        }
        return this._turmsClient.driver.send({
            createMessageRequest: {
                messageId,
                groupId: isGroupMessage ? targetId : undefined,
                recipientId: !isGroupMessage ? targetId : undefined,
                records: []
            }
        }).then(n => Response.fromNotification(n, data => NotificationUtil.getLongOrThrow(data)));
    }

    /**
     * Update a sent message.
     *
     * @remarks
     * Authorization:
     * * If the server property `turms.service.message.allow-send-messages-to-oneself`
     *   is true (true by default), the logged-in user can update sent messages.
     *   Otherwise, throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#UPDATING_MESSAGE_BY_SENDER_IS_DISABLED}.
     * * If the message is not sent by the logged-in user,
     *   throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#NOT_SENDER_TO_UPDATE_MESSAGE}.
     * * If the message is group message, and is sent by the logged-in user but the group
     *   has been deleted,
     *   throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#UPDATE_MESSAGE_OF_NONEXISTENT_GROUP}.
     * * If the message is group message, and the group type has disabled updating messages,
     *   throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#UPDATING_GROUP_MESSAGE_BY_SENDER_IS_DISABLED}.
     *
     * Notifications:
     * * If the server property `turms.service.notification.message-updated.notify-message-recipients`
     *   is true (true by default), a message update notification will be sent to the message recipients actively.
     * * If the server property `turms.service.notification.message-updated.notify-requester-other-online-sessions`
     *   is true (true by default), a message update notification will be sent to all other online sessions of the logged-in user actively.
     *
     * @param messageId - The sent message ID.
     * @param text - The new message text.
     * If null, the message text will not be changed.
     * @param records - The new message records.
     * If null, the message records will not be changed.
     * @throws {@link ResponseError} if an error occurs.
     */
    updateSentMessage({
        messageId,
        text,
        records
    }: {
        messageId: string,
        text?: string,
        records?: Uint8Array[]
    }): Promise<Response<void>> {
        if (Validator.isFalsy(messageId)) {
            return ResponseError.notFalsyPromise('messageId');
        }
        if (Validator.areAllFalsy(text, records)) {
            return Promise.resolve(Response.nullValue());
        }
        return this._turmsClient.driver.send({
            updateMessageRequest: {
                messageId,
                text,
                records: records || []
            }
        }).then(n => Response.fromNotification(n));
    }

    /**
     * Find messages.
     *
     * @param ids - the message IDs for querying.
     * @param areGroupMessages - whether the messages are group messages.
     * If the logged-in user is not a group member,
     * throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#NOT_GROUP_MEMBER_TO_QUERY_GROUP_MESSAGES}.
     * TODO: guest users of some group types should be able to query messages.
     * @param areSystemMessages - whether the messages are system messages.
     * @param fromIds - the from IDs.
     * If {@link areGroupMessages} is true, the from ID is the group ID.
     * If {@link areGroupMessages} is false, the from ID is the user ID.
     * @param deliveryDateStart - the start delivery date for querying.
     * @param deliveryDateEnd - the end delivery date for querying.
     * @param maxCount - the maximum count for querying.
     * @param descending - whether the messages are sorted in descending order.
     * @returns list of messages.
     * Note that the list only contains messages in which the logged-in user
     * has permission to view.
     * If the logged-in user has no permission to view specified messages,
     * these messages will be filtered out on the server, and no error will be thrown,
     * except for {@link ResponseStatusCode#NOT_GROUP_MEMBER_TO_QUERY_GROUP_MESSAGES} mentioned above.
     * @throws {@link ResponseError} if an error occurs.
     */
    queryMessages({
        ids,
        areGroupMessages,
        areSystemMessages,
        fromIds,
        deliveryDateStart,
        deliveryDateEnd,
        maxCount = 50,
        descending,
    }: {
        ids?: string[],
        areGroupMessages?: boolean,
        areSystemMessages?: boolean,
        fromIds?: string[],
        deliveryDateStart?: Date,
        deliveryDateEnd?: Date,
        maxCount?: number
        descending?: boolean
    }): Promise<Response<ParsedModel.Message[]>> {
        return this._turmsClient.driver.send({
            queryMessagesRequest: {
                ids: ids || [],
                areGroupMessages,
                areSystemMessages,
                fromIds: fromIds || [],
                deliveryDateStart: DataParser.getDateTimeStr(deliveryDateStart),
                deliveryDateEnd: DataParser.getDateTimeStr(deliveryDateEnd),
                maxCount,
                descending: descending != null && descending ? true : null,
                withTotal: false
            }
        }).then(n => Response.fromNotification(n, data => NotificationUtil.transformOrEmpty(data.messages?.messages)));
    }

    /**
     * Find the pair of messages and the total count for each conversation.
     *
     * @param ids - the message IDs for querying.
     * @param areGroupMessages - whether the messages are group messages.
     * @param areSystemMessages - whether the messages are system messages.
     * If the logged-in user is not a group member,
     * throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#NOT_GROUP_MEMBER_TO_QUERY_GROUP_MESSAGES}.
     * TODO: guest users of some group types should be able to query messages.
     * @param fromIds - The from IDs.
     * If {@link areGroupMessages} is true, the from ID is the group ID.
     * If {@link areGroupMessages} is false, the from ID is the user ID.
     * @param deliveryDateStart - The start delivery date for querying.
     * @param deliveryDateEnd - The end delivery date for querying.
     * @param maxCount - The maximum count for querying.
     * @param descending - Whether the messages are sorted in descending order.
     * @returns list of the pair of messages and the total count for each conversation.
     * Note that the list only contains messages in which the logged-in user
     * has permission to view.
     * If the logged-in user has no permission to view specified messages,
     * these messages will be filtered out on the server, and no error will be thrown,
     * except for {@link ResponseStatusCode#NOT_GROUP_MEMBER_TO_QUERY_GROUP_MESSAGES} mentioned above.
     * @throws {@link ResponseError} if an error occurs.
     */
    queryMessagesWithTotal({
        ids,
        areGroupMessages,
        areSystemMessages,
        fromIds,
        deliveryDateStart,
        deliveryDateEnd,
        maxCount = 1,
        descending
    }: {
        ids?: string[],
        areGroupMessages?: boolean,
        areSystemMessages?: boolean,
        fromIds?: string[],
        deliveryDateStart?: Date,
        deliveryDateEnd?: Date,
        maxCount?: number,
        descending?: boolean
    }): Promise<Response<ParsedModel.MessagesWithTotal[]>> {
        return this._turmsClient.driver.send({
            queryMessagesRequest: {
                ids: ids || [],
                areGroupMessages,
                areSystemMessages,
                fromIds: fromIds || [],
                deliveryDateStart: DataParser.getDateTimeStr(deliveryDateStart),
                deliveryDateEnd: DataParser.getDateTimeStr(deliveryDateEnd),
                maxCount,
                descending: descending != null && descending ? true : null,
                withTotal: true
            }
        }).then(n => Response.fromNotification(n, data => NotificationUtil.transformOrEmpty(data.messagesWithTotalList?.messagesWithTotalList)));
    }

    /**
     * Recall a message.
     *
     * @remarks
     * Authorization:
     * * If the server property `turms.service.message.allow-recall-message`
     *   is true (true by default), the logged-in user can recall sent messages.
     *   Otherwise, throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#RECALLING_MESSAGE_IS_DISABLED}.
     * * If the message does not exist,
     *   throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#RECALL_NONEXISTENT_MESSAGE}.
     * * If the message is group message, but the group has been deleted,
     *   throws {@link {@link ResponseError}} with the code {@link ResponseStatusCode#RECALL_MESSAGE_OF_NONEXISTENT_GROUP}.
     *
     * Common Scenarios:
     * * A client can call {@link addMessageListener} to listen to recalled messages.
     *   The listener will receive a non-empty {@link MessageAddition#recalledMessageIds} when a message is recalled.
     *
     * @param messageId - the message ID.
     * @param recallDate - the recall date.
     * If null, the current date will be used.
     * @throws {@link ResponseError} if an error occurs.
     */
    recallMessage({
        messageId,
        recallDate = new Date()
    }: {
        messageId: string,
        recallDate?: Date
    }): Promise<Response<void>> {
        if (Validator.isFalsy(messageId)) {
            return ResponseError.notFalsyPromise('messageId');
        }
        return this._turmsClient.driver.send({
            updateMessageRequest: {
                messageId,
                recallDate: DataParser.getDateTimeStr(recallDate),
                records: []
            }
        }).then(n => Response.fromNotification(n));
    }

    isMentionEnabled(): boolean {
        return this._mentionedUserIdsParser != null;
    }

    enableMention(mentionedUserIdsParser?: (message: ParsedModel.Message) => string[]): void {
        if (mentionedUserIdsParser) {
            this._mentionedUserIdsParser = mentionedUserIdsParser;
        } else if (!this._mentionedUserIdsParser) {
            this._mentionedUserIdsParser = MessageService.DEFAULT_MENTIONED_USER_IDS_PARSER;
        }
    }

    static generateLocationRecord({
        latitude,
        longitude,
        details
    }: {
        latitude: number,
        longitude: number,
        details?: { [_: string]: string }
    }): Uint8Array {
        Validator.throwIfAnyFalsy(latitude, longitude);
        return UserLocation.encode({
            latitude,
            longitude,
            details: details || {}
        }).finish();
    }

    static generateAudioRecordByDescription({
        url,
        duration,
        format,
        size
    }: {
        url: string,
        duration?: number,
        format?: string,
        size?: number
    }): Uint8Array {
        Validator.throwIfAnyFalsy(url);
        return AudioFile.encode({
            description: {
                url,
                duration,
                format,
                size,
            }
        }).finish();
    }

    static generateAudioRecordByData({
        data
    }: {
        data: ArrayBuffer
    }): Uint8Array {
        Validator.throwIfAnyFalsy(data);
        return AudioFile.encode({
            data: new Uint8Array(data)
        }).finish();
    }

    static generateVideoRecordByDescription({
        url,
        duration,
        format,
        size
    }: {
        url: string,
        duration?: number,
        format?: string,
        size?: number
    }): Uint8Array {
        Validator.throwIfAnyFalsy(url);
        return VideoFile.encode({
            description: {
                url,
                duration,
                format,
                size,
            }
        }).finish();
    }

    static generateVideoRecordByData({
        data
    }: {
        data: ArrayBuffer
    }): Uint8Array {
        Validator.throwIfAnyFalsy(data);
        return VideoFile.encode({
            data: new Uint8Array(data)
        }).finish();
    }

    public static generateImageRecordByData({
        data
    }: {
        data: ArrayBuffer
    }): Uint8Array {
        Validator.throwIfAnyFalsy(data);
        return ImageFile.encode({
            data: new Uint8Array(data)
        }).finish();
    }

    static generateImageRecordByDescription({
        url,
        fileSize,
        imageSize,
        original
    }: {
        url: string,
        fileSize?: number,
        imageSize?: number,
        original?: boolean
    }): Uint8Array {
        Validator.throwIfAnyFalsy(url);
        return ImageFile.encode({
            description: {
                url,
                fileSize,
                imageSize,
                original
            }
        }).finish();
    }

    static generateFileRecordByDate({
        data
    }: {
        data: ArrayBuffer
    }): Uint8Array {
        Validator.throwIfAnyFalsy(data);
        return File.encode({
            data: new Uint8Array(data)
        }).finish();
    }

    static generateFileRecordByDescription({
        url,
        format,
        size
    }: {
        url: string,
        format?: string,
        size?: number
    }): Uint8Array {
        Validator.throwIfAnyFalsy(url);
        return File.encode({
            description: {
                url,
                format,
                size
            }
        }).finish();
    }

    private _parseMessageAddition(message: ParsedModel.Message): MessageAddition {
        const mentionedUserIds = this._mentionedUserIdsParser
            ? this._mentionedUserIdsParser(message)
            : [];
        const isMentioned = mentionedUserIds.indexOf(this._turmsClient.userService.userInfo.userId) >= 0;
        const systemMessageType = message.isSystemMessage && message.records[0]?.[0];
        const recalledMessageIds = [];
        if (systemMessageType === BuiltinSystemMessageType.RECALL_MESSAGE) {
            const size = message.records.length;
            for (let i = 1; i < size; i++) {
                const id = Long.fromBytes(message.records[i] as any).toString();
                recalledMessageIds.push(id);
            }
        }
        return new MessageAddition(isMentioned, mentionedUserIds, recalledMessageIds);
    }

    /**
     * @param request should be a parsed im.turms.proto.ICreateMessageRequest
     */
    private static _createMessageRequest2Message(requesterId: string, request: any): ParsedModel.Message {
        return {
            id: request.messageId,
            isSystemMessage: request.isSystemMessage,
            deliveryDate: request.deliveryDate,
            text: request.text,
            records: request.records,
            senderId: requesterId,
            groupId: request.groupId,
            recipientId: request.recipientId
        };
    }
}