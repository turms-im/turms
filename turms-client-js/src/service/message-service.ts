import TurmsClient from '../turms-client';
import RequestUtil from '../util/request-util';
import {ParsedModel} from '../model/parsed-model';
import NotificationUtil from '../util/notification-util';
import MessageAddition from '../model/message/message-addition';
import TurmsBusinessError from '../model/turms-business-error';
import BuiltinSystemMessageType from '../model/message/builtin-system-message-type';
import * as Long from 'long'
import {UserLocation} from '../model/proto/model/user/user_location';
import {AudioFile} from '../model/proto/model/file/audio_file';
import {VideoFile} from '../model/proto/model/file/video_file';
import {ImageFile} from '../model/proto/model/file/image_file';
import {File} from '../model/proto/model/file/file';

export default class MessageService {
    /**
     * Format: "@{userId}"
     * Example: "@{123}", "I need to talk with @{123} and @{321}"
     */
    private static readonly DEFAULT_MENTIONED_USER_IDS_PARSER = function (message: ParsedModel.Message): string[] {
        const regex = /@{(\d+?)}/g;
        if (message.text) {
            const userIds = [];
            let matches;
            while ((matches = regex.exec(message.text))) {
                userIds.push(matches[1]);
            }
            return userIds;
        }
        return [];
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
                return null;
            });
    }

    addMessageListener(listener: (message: ParsedModel.Message, messageAddition: MessageAddition) => void): void {
        this._messageListeners.push(listener);
    }

    removeMessageListener(listener: (message: ParsedModel.Message, messageAddition: MessageAddition) => void): void {
        this._messageListeners = this._messageListeners.filter(cur => cur !== listener);
    }

    sendMessage(
        isGroupMessage: boolean,
        targetId: string,
        deliveryDate?: Date,
        text?: string,
        records?: Uint8Array[],
        burnAfter?: number,
        preMessageId?: string): Promise<string> {
        if (RequestUtil.isFalsy(targetId)) {
            return TurmsBusinessError.notFalsyPromise('targetId');
        }
        if (RequestUtil.isFalsy(text) && RequestUtil.isFalsy(records)) {
            return TurmsBusinessError.illegalParamPromise('text and records must not all be null');
        }
        if (!deliveryDate) {
            deliveryDate = new Date();
        }
        return this._turmsClient.driver.send({
            createMessageRequest: {
                groupId: isGroupMessage ? targetId : undefined,
                recipientId: !isGroupMessage ? targetId : undefined,
                deliveryDate: RequestUtil.getDateTimeStr(deliveryDate),
                text,
                records: records || [],
                burnAfter,
                preMessageId
            }
        }).then(n => NotificationUtil.getFirstIdOrThrow(n));
    }

    forwardMessage(
        messageId: string,
        isGroupMessage: boolean,
        targetId: string): Promise<string> {
        if (RequestUtil.isFalsy(messageId)) {
            return TurmsBusinessError.notFalsyPromise('messageId');
        }
        if (RequestUtil.isFalsy(targetId)) {
            return TurmsBusinessError.notFalsyPromise('targetId');
        }
        return this._turmsClient.driver.send({
            createMessageRequest: {
                messageId,
                groupId: isGroupMessage ? targetId : undefined,
                recipientId: !isGroupMessage ? targetId : undefined,
                records: []
            }
        }).then(n => NotificationUtil.getFirstIdOrThrow(n));
    }

    updateSentMessage(
        messageId: string,
        text?: string,
        records?: Uint8Array[]): Promise<void> {
        if (RequestUtil.isFalsy(messageId)) {
            return TurmsBusinessError.notFalsyPromise('messageId');
        }
        if (RequestUtil.areAllFalsy(text, records)) {
            return Promise.resolve();
        }
        return this._turmsClient.driver.send({
            updateMessageRequest: {
                messageId,
                text,
                records: records || []
            }
        }).then(() => null);
    }

    queryMessages(
        ids?: string[],
        areGroupMessages?: boolean,
        areSystemMessages?: boolean,
        fromId?: string,
        deliveryDateAfter?: Date,
        deliveryDateBefore?: Date,
        size = 50): Promise<ParsedModel.Message[]> {
        return this._turmsClient.driver.send({
            queryMessagesRequest: {
                ids: ids || [],
                areGroupMessages,
                areSystemMessages,
                fromId,
                deliveryDateAfter: RequestUtil.getDateTimeStr(deliveryDateAfter),
                deliveryDateBefore: RequestUtil.getDateTimeStr(deliveryDateBefore),
                size,
                withTotal: false
            }
        }).then(n => NotificationUtil.transformOrEmpty(n.data?.messages?.messages));
    }

    queryMessagesWithTotal(
        ids?: string[],
        areGroupMessages?: boolean,
        areSystemMessages?: boolean,
        fromId?: string,
        deliveryDateAfter?: Date,
        deliveryDateBefore?: Date,
        size = 1): Promise<ParsedModel.MessagesWithTotal[]> {
        return this._turmsClient.driver.send({
            queryMessagesRequest: {
                ids: ids || [],
                areGroupMessages,
                areSystemMessages,
                fromId,
                deliveryDateAfter: RequestUtil.getDateTimeStr(deliveryDateAfter),
                deliveryDateBefore: RequestUtil.getDateTimeStr(deliveryDateBefore),
                size,
                withTotal: true
            }
        }).then(n => NotificationUtil.transformOrEmpty(n.data?.messagesWithTotalList?.messagesWithTotalList));
    }

    recallMessage(messageId: string, recallDate = new Date()): Promise<void> {
        if (RequestUtil.isFalsy(messageId)) {
            return TurmsBusinessError.notFalsyPromise('messageId');
        }
        return this._turmsClient.driver.send({
            updateMessageRequest: {
                messageId,
                recallDate: RequestUtil.getDateTimeStr(recallDate),
                records: []
            }
        }).then(() => null);
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

    static generateLocationRecord(
        latitude: number,
        longitude: number,
        locationName?: string,
        address?: string
    ): Uint8Array {
        RequestUtil.throwIfAnyFalsy(latitude, longitude);
        return UserLocation.encode({
            latitude,
            longitude,
            address,
            name: locationName
        }).finish();
    }

    static generateAudioRecordByDescription(url: string, duration?: number, format?: string, size?: number): Uint8Array {
        RequestUtil.throwIfAnyFalsy(url);
        return AudioFile.encode({
            description: {
                url,
                duration,
                format,
                size,
            }
        }).finish();
    }

    static generateAudioRecordByData(data: ArrayBuffer): Uint8Array {
        RequestUtil.throwIfAnyFalsy(data);
        return AudioFile.encode({
            data: new Uint8Array(data)
        }).finish();
    }

    static generateVideoRecordByDescription(url: string, duration?: number, format?: string, size?: number): Uint8Array {
        RequestUtil.throwIfAnyFalsy(url);
        return VideoFile.encode({
            description: {
                url,
                duration,
                format,
                size,
            }
        }).finish();
    }

    static generateVideoRecordByData(data: ArrayBuffer): Uint8Array {
        RequestUtil.throwIfAnyFalsy(data);
        return VideoFile.encode({
            data: new Uint8Array(data)
        }).finish();
    }

    public static generateImageRecordByData(data: ArrayBuffer): Uint8Array {
        RequestUtil.throwIfAnyFalsy(data);
        return ImageFile.encode({
            data: new Uint8Array(data)
        }).finish();
    }

    static generateImageRecordByDescription(url: string, fileSize?: number, imageSize?: number, original?: boolean): Uint8Array {
        RequestUtil.throwIfAnyFalsy(url);
        return ImageFile.encode({
            description: {
                url,
                fileSize,
                imageSize,
                original
            }
        }).finish();
    }

    static generateFileRecordByDate(data: ArrayBuffer): Uint8Array {
        RequestUtil.throwIfAnyFalsy(data);
        return File.encode({
            data: new Uint8Array(data)
        }).finish();
    }

    static generateFileRecordByDescription(url: string, format?: string, size?: number): Uint8Array {
        RequestUtil.throwIfAnyFalsy(url);
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
                const id = Long.fromBytes(message.records[i] as any);
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
        }
    }
}