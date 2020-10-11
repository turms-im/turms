import TurmsClient from "../turms-client";
import {im} from "../model/proto-bundle";
import RequestUtil from "../util/request-util";
import {ParsedModel} from "../model/parsed-model";
import NotificationUtil from "../util/notification-util";
import MessageAddition from "../model/message-addition";
import TurmsBusinessError from "../model/turms-business-error";
import File = im.turms.proto.File;
import AudioFile = im.turms.proto.AudioFile;
import VideoFile = im.turms.proto.VideoFile;
import ImageFile = im.turms.proto.ImageFile;
import Location = im.turms.proto.UserLocation;
import MessageDeliveryStatus = im.turms.proto.MessageDeliveryStatus;

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
    private _ackMessageTimerId?: number;
    private _unacknowledgedMessageIds: string[] = [];
    private _mentionedUserIdsParser?: (message: ParsedModel.Message) => string[];
    private _onMessage?: (message: ParsedModel.Message, messageAddition: MessageAddition) => void;

    get onMessage(): (message: ParsedModel.Message, messageAddition: MessageAddition) => void {
        return this._onMessage;
    }

    set onMessage(value: (message: ParsedModel.Message, messageAddition: MessageAddition) => void) {
        this._onMessage = value;
    }

    constructor(turmsClient: TurmsClient, ackMessageInterval?: number) {
        this._turmsClient = turmsClient;
        if (typeof ackMessageInterval === 'number' && ackMessageInterval > 0) {
            this._ackMessageTimerId = this._startAckMessagesTimer(ackMessageInterval);
        }
        this._turmsClient.driver
            .addOnNotificationListener(notification => {
                if (this._onMessage != null && notification.relayedRequest) {
                    const request = notification.relayedRequest.createMessageRequest;
                    if (request) {
                        const message = MessageService._createMessageRequest2Message(notification.requesterId, request);
                        const addition = this._parseMessageAddition(message);
                        this._onMessage(message, addition);
                    }
                }
                return null;
            });
    }

    sendMessage(
        isGroupMessage: boolean,
        targetId: string,
        deliveryDate?: Date,
        text?: string,
        records?: Uint8Array[],
        burnAfter?: number): Promise<string> {
        if (RequestUtil.isFalsy(targetId)) {
            return TurmsBusinessError.notFalsy('targetId');
        }
        if (RequestUtil.isFalsy(text) && RequestUtil.isFalsy(records)) {
            return TurmsBusinessError.illegalParam('text and records must not all be null');
        }
        if (!deliveryDate) {
            deliveryDate = new Date();
        }
        return this._turmsClient.driver.send({
            createMessageRequest: {
                groupId: RequestUtil.wrapValueIfNotNull(isGroupMessage ? targetId : undefined),
                recipientId: RequestUtil.wrapValueIfNotNull(!isGroupMessage ? targetId : undefined),
                deliveryDate: '' + deliveryDate.getTime(),
                text: RequestUtil.wrapValueIfNotNull(text),
                records: records,
                burnAfter: RequestUtil.wrapValueIfNotNull(burnAfter)
            }
        }).then(n => NotificationUtil.getFirstVal(n, 'ids', true));
    }

    ackMessages(messageIds: string[]): Promise<void> {
        if (RequestUtil.isFalsy(messageIds)) {
            return TurmsBusinessError.notFalsy('messageIds', true);
        }
        return this._turmsClient.driver.send({
            ackRequest: {
                messageIds
            }
        }).then(() => null)
    }

    forwardMessage(
        messageId: string,
        isGroupMessage: boolean,
        targetId: string): Promise<string> {
        if (RequestUtil.isFalsy(messageId)) {
            return TurmsBusinessError.notFalsy('messageId');
        }
        if (RequestUtil.isFalsy(targetId)) {
            return TurmsBusinessError.notFalsy('targetId');
        }
        return this._turmsClient.driver.send({
            createMessageRequest: {
                messageId: {value: messageId},
                groupId: RequestUtil.wrapValueIfNotNull(isGroupMessage ? targetId : undefined),
                recipientId: RequestUtil.wrapValueIfNotNull(!isGroupMessage ? targetId : undefined),
            }
        }).then(n => NotificationUtil.getFirstVal(n, 'ids', true));
    }

    updateSentMessage(
        messageId: string,
        text?: string,
        records?: Uint8Array[]): Promise<void> {
        if (RequestUtil.isFalsy(messageId)) {
            return TurmsBusinessError.notFalsy('messageId');
        }
        if (RequestUtil.areAllFalsy(text, records)) {
            return Promise.resolve();
        }
        return this._turmsClient.driver.send({
            updateMessageRequest: {
                messageId,
                text: RequestUtil.wrapValueIfNotNull(text),
                records
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
        deliveryStatus?: string | MessageDeliveryStatus,
        size = 50): Promise<ParsedModel.Message[]> {
        if (typeof deliveryStatus === 'string') {
            deliveryStatus = MessageDeliveryStatus[deliveryStatus] as MessageDeliveryStatus;
            if (RequestUtil.isFalsy(deliveryStatus)) {
                return TurmsBusinessError.notFalsy("deliveryStatus");
            }
        }
        return this._turmsClient.driver.send({
            queryMessagesRequest: {
                ids: RequestUtil.wrapValueIfNotNull(ids),
                areGroupMessages: RequestUtil.wrapValueIfNotNull(areGroupMessages),
                areSystemMessages: RequestUtil.wrapValueIfNotNull(areSystemMessages),
                fromId: RequestUtil.wrapValueIfNotNull(fromId),
                deliveryDateAfter: RequestUtil.wrapTimeIfNotNull(deliveryDateAfter),
                deliveryDateBefore: RequestUtil.wrapTimeIfNotNull(deliveryDateBefore),
                size: RequestUtil.wrapValueIfNotNull(size),
                deliveryStatus: deliveryStatus
            }
        }).then(n => NotificationUtil.getArrAndTransform(n, 'messages.messages'));
    }

    queryPendingMessagesWithTotal(size = 1): Promise<ParsedModel.MessagesWithTotal[]> {
        return this._turmsClient.driver.send({
            queryPendingMessagesWithTotalRequest: {
                size: RequestUtil.wrapValueIfNotNull(size)
            }
        }).then(n => NotificationUtil.getArrAndTransform(n, 'messagesWithTotalList.messagesWithTotalList'));
    }

    queryMessageStatus(messageId: string): Promise<ParsedModel.MessageStatus[]> {
        if (RequestUtil.isFalsy(messageId)) {
            return TurmsBusinessError.notFalsy('messageId');
        }
        return this._turmsClient.driver.send({
            queryMessageStatusesRequest: {
                messageId
            }
        }).then(n => NotificationUtil.getArrAndTransform(n, 'messageStatuses.messageStatuses'));
    }

    recallMessage(messageId: string, recallDate = new Date()): Promise<void> {
        if (RequestUtil.isFalsy(messageId)) {
            return TurmsBusinessError.notFalsy('messageId');
        }
        return this._turmsClient.driver.send({
            updateMessageRequest: {
                messageId,
                recallDate: RequestUtil.wrapTimeIfNotNull(recallDate)
            }
        }).then(() => null);
    }

    readMessage(messageId: string, readDate = new Date()): Promise<void> {
        if (RequestUtil.isFalsy(messageId)) {
            return TurmsBusinessError.notFalsy('messageId');
        }
        return this._turmsClient.driver.send({
            updateMessageRequest: {
                messageId,
                readDate: RequestUtil.wrapTimeIfNotNull(readDate)
            }
        }).then(() => null);
    }

    markMessageUnread(messageId: string): Promise<void> {
        return this.readMessage(messageId, new Date(0));
    }

    updateTypingStatusRequest(isGroupMessage: boolean, targetId: string): Promise<void> {
        if (RequestUtil.isFalsy(targetId)) {
            return TurmsBusinessError.notFalsy('targetId');
        }
        return this._turmsClient.driver.send({
            updateTypingStatusRequest: {
                isGroupMessage,
                toId: targetId
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
        return Location.encode({
            latitude,
            longitude,
            address: RequestUtil.wrapValueIfNotNull(address),
            name: RequestUtil.wrapValueIfNotNull(name)
        }).finish();
    }

    static generateAudioRecordByDescription(url: string, duration?: number, format?: string, size?: number): Uint8Array {
        RequestUtil.throwIfAnyFalsy(url);
        return AudioFile.encode({
            description: {
                url,
                duration: RequestUtil.wrapValueIfNotNull(duration),
                format: RequestUtil.wrapValueIfNotNull(format),
                size: RequestUtil.wrapValueIfNotNull(size),
            }
        }).finish();
    }

    static generateAudioRecordByData(data: ArrayBuffer): Uint8Array {
        RequestUtil.throwIfAnyFalsy(data);
        return AudioFile.encode({
            data: {
                value: new Uint8Array(data)
            }
        }).finish();
    }

    static generateVideoRecordByDescription(url: string, duration?: number, format?: string, size?: number): Uint8Array {
        RequestUtil.throwIfAnyFalsy(url);
        return VideoFile.encode({
            description: {
                url,
                duration: RequestUtil.wrapValueIfNotNull(duration),
                format: RequestUtil.wrapValueIfNotNull(format),
                size: RequestUtil.wrapValueIfNotNull(size),
            }
        }).finish();
    }

    static generateVideoRecordByData(data: ArrayBuffer): Uint8Array {
        RequestUtil.throwIfAnyFalsy(data);
        return VideoFile.encode({
            data: {
                value: new Uint8Array(data)
            }
        }).finish();
    }

    public static generateImageRecordByData(data: ArrayBuffer): Uint8Array {
        RequestUtil.throwIfAnyFalsy(data);
        return ImageFile.encode({
            data: {
                value: new Uint8Array(data)
            }
        }).finish();
    }

    static generateImageRecordByDescription(url: string, fileSize?: number, imageSize?: number, original?: boolean): Uint8Array {
        RequestUtil.throwIfAnyFalsy(url);
        return ImageFile.encode({
            description: {
                url,
                fileSize: RequestUtil.wrapValueIfNotNull(fileSize),
                imageSize: RequestUtil.wrapValueIfNotNull(imageSize),
                original: RequestUtil.wrapValueIfNotNull(original)
            }
        }).finish();
    }

    static generateFileRecordByDate(data: ArrayBuffer): Uint8Array {
        RequestUtil.throwIfAnyFalsy(data);
        return File.encode({
            data: {
                value: new Uint8Array(data)
            }
        }).finish();
    }

    static generateFileRecordByDescription(url: string, format?: string, size?: number): Uint8Array {
        RequestUtil.throwIfAnyFalsy(url);
        return File.encode({
            description: {
                url,
                format: RequestUtil.wrapValueIfNotNull(format),
                size: RequestUtil.wrapValueIfNotNull(size)
            }
        }).finish();
    }

    private _startAckMessagesTimer(ackMessageInterval: number): number {
        return window.setInterval(() => {
            if (!this._unacknowledgedMessageIds.length) {
                const unacknowledgedMessageIds = JSON.parse(JSON.stringify(this._unacknowledgedMessageIds));
                this.ackMessages(unacknowledgedMessageIds)
                    .then(() => {
                        this._unacknowledgedMessageIds = this._unacknowledgedMessageIds.filter(id => !unacknowledgedMessageIds.includes(id))
                    });
            }
        }, ackMessageInterval);
    }

    private _parseMessageAddition(message: ParsedModel.Message): MessageAddition {
        let mentionedUserIds;
        if (this._mentionedUserIdsParser) {
            mentionedUserIds = this._mentionedUserIdsParser(message);
        } else {
            mentionedUserIds = [];
        }
        const isMentioned = mentionedUserIds.includes(this._turmsClient.userService.userId);
        return new MessageAddition(isMentioned, mentionedUserIds);
    }

    /**
     * @param request should be a parsed CreateMessageRequest
     */
    private static _createMessageRequest2Message(requesterId: string, request: any): ParsedModel.Message {
        return {
            id: request.messageId,
            deliveryDate: request.deliveryDate,
            text: request.text,
            records: request.records,
            senderId: requesterId,
            groupId: request.groupId,
            recipientId: request.recipientId
        }
    }
}
