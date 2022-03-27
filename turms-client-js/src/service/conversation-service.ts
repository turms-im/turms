import TurmsClient from '../turms-client';
import RequestUtil from '../util/request-util';
import { ParsedModel } from '../model/parsed-model';
import NotificationUtil from '../util/notification-util';
import ResponseError from '../error/response-error';

export default class ConversationService {

    private _turmsClient: TurmsClient;

    constructor(turmsClient: TurmsClient) {
        this._turmsClient = turmsClient;
    }

    queryPrivateConversations(targetIds: string[]): Promise<ParsedModel.PrivateConversation[]> {
        if (RequestUtil.isFalsy(targetIds)) {
            return Promise.resolve([]);
        }
        return this._turmsClient.driver.send({
            queryConversationsRequest: {
                targetIds,
                groupIds: []
            }
        }).then(n => NotificationUtil.transformOrEmpty(n.data?.conversations?.privateConversations));
    }

    queryGroupConversations(groupIds: string[]): Promise<ParsedModel.GroupConversation[]> {
        if (RequestUtil.isFalsy(groupIds)) {
            return Promise.resolve([]);
        }
        return this._turmsClient.driver.send({
            queryConversationsRequest: {
                groupIds,
                targetIds: []
            }
        }).then(n => NotificationUtil.transform(n.data?.conversations?.groupConversations)?.map(c => ({
            groupId: c.groupId,
            memberIdAndReadDate: NotificationUtil.transformMapValToDate(c.memberIdAndReadDate)
        })) || []);
    }

    updatePrivateConversationReadDate(targetId: string, readDate?: Date): Promise<void> {
        if (RequestUtil.isFalsy(targetId)) {
            return ResponseError.notFalsyPromise('targetId');
        }
        readDate = readDate ?? new Date();
        return this._turmsClient.driver.send({
            updateConversationRequest: {
                targetId,
                readDate: '' + readDate.getTime()
            }
        }).then(() => null);
    }

    updateGroupConversationReadDate(groupId: string, readDate?: Date): Promise<void> {
        if (RequestUtil.isFalsy(groupId)) {
            return ResponseError.notFalsyPromise('groupId');
        }
        readDate = readDate ?? new Date();
        return this._turmsClient.driver.send({
            updateConversationRequest: {
                groupId,
                readDate: '' + readDate.getTime()
            }
        }).then(() => null);
    }

    updatePrivateConversationTypingStatus(targetId: string): Promise<void> {
        if (RequestUtil.isFalsy(targetId)) {
            return ResponseError.notFalsyPromise('targetId');
        }
        return this._turmsClient.driver.send({
            updateTypingStatusRequest: {
                toId: targetId,
                isGroupMessage: false
            }
        }).then(() => null);
    }

    updateGroupConversationTypingStatus(groupId: string): Promise<void> {
        if (RequestUtil.isFalsy(groupId)) {
            return ResponseError.notFalsyPromise('groupId');
        }
        return this._turmsClient.driver.send({
            updateTypingStatusRequest: {
                toId: groupId,
                isGroupMessage: true
            }
        }).then(() => null);
    }

}