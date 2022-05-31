import TurmsClient from '../../src/turms-client';
import Constants from '../helper/constants';

let turmsClient: TurmsClient;
const USER_ID = '1';
const RELATED_USER_ID = '2';
const GROUP_ID = '1';

beforeAll(async () => {
    turmsClient = new TurmsClient(Constants.WS_URL);
    await turmsClient.userService.login(USER_ID, '123');
});

afterAll(async () => {
    await turmsClient.userService.logout();
});

describe('Constructor', () => {
    it('constructor_shouldReturnNotNullConversationServiceInstance', () => {
        expect(turmsClient.conversationService).toBeTruthy();
    });
});

describe('Update', () => {
    it('updatePrivateConversationReadDate_shouldSucceed', async () => {
        const response = await turmsClient.conversationService.updatePrivateConversationReadDate(RELATED_USER_ID);
        expect(response.data).toBeFalsy();
    });
    it('updateGroupConversationReadDate_shouldSucceed', async () => {
        const response = await turmsClient.conversationService.updateGroupConversationReadDate(GROUP_ID);
        expect(response.data).toBeFalsy();
    });
    it('updatePrivateConversationTypingStatus_shouldSucceed', async () => {
        const response = await turmsClient.conversationService.updatePrivateConversationTypingStatus(RELATED_USER_ID);
        expect(response.data).toBeFalsy();
    });
    it('updateGroupConversationTypingStatus_shouldSucceed', async () => {
        const response = await turmsClient.conversationService.updateGroupConversationTypingStatus(GROUP_ID);
        expect(response.data).toBeFalsy();
    });
});

describe('Query', () => {
    it('queryPrivateConversations_shouldReturnNotEmptyConversations', async () => {
        const response = await turmsClient.conversationService.queryPrivateConversations([RELATED_USER_ID]);
        const conversations = response.data;
        expect(conversations.length).toBeGreaterThan(0);
        expect(conversations[0].readDate).toBeTruthy();
    });
    it('queryGroupConversations_shouldReturnNotEmptyConversations', async () => {
        const response = await turmsClient.conversationService.queryGroupConversations([GROUP_ID]);
        const conversations = response.data;
        expect(conversations.length).toBeGreaterThan(0);
        expect(conversations[0].memberIdAndReadDate).toBeTruthy();
    });
});