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
            memberIdAndReadDate: NotificationUtil.transformMapValToDate(c.memberIdAndReadDate)
        })) || []));
    }

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