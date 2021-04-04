import TurmsClient from '../turms-client';
import RequestUtil from '../util/request-util';
import {ParsedModel} from '../model/parsed-model';
import NotificationUtil from '../util/notification-util';
import TurmsBusinessError from '../model/turms-business-error';

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
        }).then(n => NotificationUtil.transform(n.data?.conversations?.privateConversations) as ParsedModel.PrivateConversation[] || []);
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
        }).then(n => {
            const conversations = NotificationUtil.transform(n.data?.conversations?.groupConversations) as any[];
            if (conversations) {
                conversations.forEach(c => c.memberIdAndReadDate = NotificationUtil.transformMapValToDate(c.memberIdAndReadDate))
            }
            return conversations as ParsedModel.GroupConversation[] || []
        });
    }

    updatePrivateConversationReadDate(targetId: string, readDate?: Date): Promise<void> {
        if (RequestUtil.isFalsy(targetId)) {
            return TurmsBusinessError.notFalsyPromise('targetId');
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
            return TurmsBusinessError.notFalsyPromise('groupId');
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
            return TurmsBusinessError.notFalsyPromise('targetId');
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
            return TurmsBusinessError.notFalsyPromise('groupId');
        }
        return this._turmsClient.driver.send({
            updateTypingStatusRequest: {
                toId: groupId,
                isGroupMessage: true
            }
        }).then(() => null);
    }

}