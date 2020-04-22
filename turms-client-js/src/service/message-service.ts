import TurmsClient from "../turms-client";
import {im} from "../model/proto-bundle";
import ConstantTransformer from "../util/constant-transformer";
import RequestUtil from "../util/request-util";
import {ParsedModel} from "../model/parsed-model";
import NotificationUtil from "../util/notification-util";
import MessageAddition from "../model/message-addition";
import ChatType = im.turms.proto.ChatType;
import File = im.turms.proto.File;
import AudioFile = im.turms.proto.AudioFile;
import VideoFile = im.turms.proto.VideoFile;
import ImageFile = im.turms.proto.ImageFile;
import Location = im.turms.proto.UserLocation;
import MessageDeliveryStatus = im.turms.proto.MessageDeliveryStatus;
import TurmsBusinessException from "../model/turms-business-exception";

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
    private _onMessage?: (message: ParsedModel.Message, messageAddition: MessageAddition) => void;

    get onMessage(): (message: ParsedModel.Message, messageAddition: MessageAddition) => void {
        return this._onMessage;
    }

    set onMessage(value: (message: ParsedModel.Message, messageAddition: MessageAddition) => void) {
        this._onMessage = value;
    }

    constructor(turmsClient: TurmsClient) {
        this._turmsClient = turmsClient;
        this._turmsClient.driver
            .onNotificationListeners
            .push(notification => {
                if (this._onMessage != null && notification.relayedRequest) {
                    const request = notification.relayedRequest.createMessageRequest;
                    if (request) {
                        const message = MessageService._createMessageRequest2Message(notification.requesterId, request);
                        const addition = this._parseMessageAddition(message);
                        this._onMessage(message as ParsedModel.Message, addition);
                    }
                }
                return null;
            });
    }

    sendMessage(
        chatType: string | ChatType,
        targetId: string,
        deliveryDate?: Date,
        text?: string,
        records?: Uint8Array[],
        burnAfter?: number): Promise<string> {
        if (RequestUtil.isFalsy(chatType)) {
            return TurmsBusinessException.notFalsy('chatType');
        }
        if (RequestUtil.isFalsy(targetId)) {
            return TurmsBusinessException.notFalsy('targetId');
        }
        if (RequestUtil.isFalsy(text) && RequestUtil.isFalsy(records)) {
            return TurmsBusinessException.illegalParam('text and records must not all be null');
        }
        if (typeof chatType === "string") {
            try {
                chatType = ConstantTransformer.string2ChatType(chatType);
            } catch (e) {
                return TurmsBusinessException.illegalParam(e);
            }
        }
        if (!deliveryDate) {
            deliveryDate = new Date();
        }
        return this._turmsClient.driver.send({
            createMessageRequest: {
                groupId: RequestUtil.wrapValueIfNotNull(chatType === ChatType.GROUP ? targetId : undefined),
                recipientId: RequestUtil.wrapValueIfNotNull(chatType === ChatType.PRIVATE ? targetId : undefined),
                deliveryDate: '' + deliveryDate.getTime(),
                text: RequestUtil.wrapValueIfNotNull(text),
                records: records,
                burnAfter: RequestUtil.wrapValueIfNotNull(burnAfter)
            }
        }).then(n => NotificationUtil.getFirstVal(n, 'ids', true));
    }

    forwardMessage(
        messageId: string,
        chatType: string | ChatType,
        targetId: string): Promise<string> {
        if (RequestUtil.isFalsy(messageId)) {
            return TurmsBusinessException.notFalsy('messageId');
        }
        if (RequestUtil.isFalsy(chatType)) {
            return TurmsBusinessException.notFalsy('chatType');
        }
        if (RequestUtil.isFalsy(targetId)) {
            return TurmsBusinessException.notFalsy('targetId');
        }
        if (typeof chatType === "string") {
            try {
                chatType = ConstantTransformer.string2ChatType(chatType);
            } catch (e) {
                return TurmsBusinessException.illegalParam(e);
            }
        }
        return this._turmsClient.driver.send({
            createMessageRequest: {
                messageId: {value: messageId},
                groupId: RequestUtil.wrapValueIfNotNull(chatType === ChatType.GROUP ? targetId : undefined),
                recipientId: RequestUtil.wrapValueIfNotNull(chatType === ChatType.PRIVATE ? targetId : undefined),
            }
        }).then(n => NotificationUtil.getFirstVal(n, 'ids', true));
    }

    updateSentMessage(
        messageId: string,
        text?: string,
        records?: Uint8Array[]): Promise<void> {
        if (RequestUtil.isFalsy(messageId)) {
            return TurmsBusinessException.notFalsy('messageId');
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
            // eslint-disable-next-line @typescript-eslint/no-empty-function
        }).then(() => {
        });
    }

    queryMessages(
        ids?: string[],
        chatType?: string | ChatType,
        areSystemMessages?: boolean,
        fromId?: string,
        deliveryDateAfter?: Date,
        deliveryDateBefore?: Date,
        deliveryStatus?: string | MessageDeliveryStatus,
        size = 50): Promise<ParsedModel.Message[]> {
        if (typeof chatType === 'string') {
            try {
                chatType = ConstantTransformer.string2ChatType(chatType);
            } catch (e) {
                return TurmsBusinessException.illegalParam(e);
            }
        }
        if (typeof deliveryStatus === 'string') {
            try {
                deliveryStatus = ConstantTransformer.string2DeliveryStatus(deliveryStatus);
            } catch (e) {
                return TurmsBusinessException.illegalParam(e);
            }
        }
        // @ts-ignore
        return this._turmsClient.driver.send({
            queryMessagesRequest: {
                ids: RequestUtil.wrapValueIfNotNull(ids),
                chatType,
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
        // @ts-ignore
        return this._turmsClient.driver.send({
            queryPendingMessagesWithTotalRequest: {
                size: RequestUtil.wrapValueIfNotNull(size)
            }
        }).then(n => NotificationUtil.getArrAndTransform(n, 'messagesWithTotalList.messagesWithTotalList'));
    }

    queryMessageStatus(messageId: string): Promise<ParsedModel.MessageStatus[]> {
        if (RequestUtil.isFalsy(messageId)) {
            return TurmsBusinessException.notFalsy('messageId');
        }
        // @ts-ignore
        return this._turmsClient.driver.send({
            queryMessageStatusesRequest: {
                messageId
            }
        }).then(n => NotificationUtil.getArrAndTransform(n, 'messageStatuses.messageStatuses'));
    }

    recallMessage(messageId: string, recallDate = new Date()): Promise<void> {
        if (RequestUtil.isFalsy(messageId)) {
            return TurmsBusinessException.notFalsy('messageId');
        }
        return this._turmsClient.driver.send({
            updateMessageRequest: {
                messageId,
                recallDate: RequestUtil.wrapTimeIfNotNull(recallDate)
            }
            // eslint-disable-next-line @typescript-eslint/no-empty-function
        }).then(() => {
        });
    }

    readMessage(messageId: string, readDate = new Date()): Promise<void> {
        if (RequestUtil.isFalsy(messageId)) {
            return TurmsBusinessException.notFalsy('messageId');
        }
        return this._turmsClient.driver.send({
            updateMessageRequest: {
                messageId,
                readDate: RequestUtil.wrapTimeIfNotNull(readDate)
            }
            // eslint-disable-next-line @typescript-eslint/no-empty-function
        }).then(() => {
        });
    }

    markMessageUnread(messageId: string): Promise<void> {
        return this.readMessage(messageId, new Date(0));
    }

    updateTypingStatusRequest(chatType: string | ChatType, targetId: string): Promise<void> {
        if (RequestUtil.isFalsy(chatType)) {
            return TurmsBusinessException.notFalsy('chatType');
        }
        if (RequestUtil.isFalsy(targetId)) {
            return TurmsBusinessException.notFalsy('targetId');
        }
        if (typeof chatType === 'string') {
            try {
                chatType = ConstantTransformer.string2ChatType(chatType);
            } catch (e) {
                return TurmsBusinessException.illegalParam(e);
            }
        }
        return this._turmsClient.driver.send({
            updateTypingStatusRequest: {
                chatType,
                toId: targetId
            }
            // eslint-disable-next-line @typescript-eslint/no-empty-function
        }).then(() => {
        });
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
