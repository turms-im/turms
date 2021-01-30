import TurmsClient from '../turms-client';
import { ParsedModel } from '../model/parsed-model';
export default class ConversationService {
    private _turmsClient;
    constructor(turmsClient: TurmsClient);
    queryPrivateConversations(targetIds: string[]): Promise<ParsedModel.PrivateConversation[]>;
    queryGroupConversations(groupIds: string[]): Promise<ParsedModel.GroupConversation[]>;
    updatePrivateConversationReadDate(targetId: string, readDate?: Date): Promise<void>;
    updateGroupConversationReadDate(groupId: string, readDate?: Date): Promise<void>;
    updatePrivateConversationTypingStatus(targetId: string): Promise<void>;
    updateGroupConversationTypingStatus(groupId: string): Promise<void>;
}
