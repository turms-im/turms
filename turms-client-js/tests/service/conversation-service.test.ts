import TurmsClient from "../../src/turms-client";
import Constants from "../helper/constants";

let turmsClient: TurmsClient;
const USER_ID = '1';
const RELATED_USER_ID = '2';
const GROUP_ID = '1';

beforeAll(async () => {
    turmsClient = new TurmsClient(Constants.WS_URL);
    await turmsClient.driver.connect(USER_ID, "123");
});

afterAll(async () => {
    if (turmsClient.driver.isConnected()) {
        await turmsClient.driver.disconnect();
    }
});

describe('Constructor', () => {
    it('constructor_shouldReturnNotNullConversationServiceInstance', () => {
        expect(turmsClient.conversationService).toBeTruthy();
    });
});

describe('Update', () => {
    it('updatePrivateConversationReadDate_shouldSucceed', async () => {
        const result = await turmsClient.conversationService.updatePrivateConversationReadDate(RELATED_USER_ID);
        expect(result).toBeFalsy();
    });
    it('updateGroupConversationReadDate_shouldSucceed', async () => {
        const result = await turmsClient.conversationService.updateGroupConversationReadDate(GROUP_ID);
        expect(result).toBeFalsy();
    });
    it('updatePrivateConversationTypingStatus_shouldSucceed', async () => {
        const result = await turmsClient.conversationService.updatePrivateConversationTypingStatus(RELATED_USER_ID);
        expect(result).toBeFalsy();
    });
    it('updateGroupConversationTypingStatus_shouldSucceed', async () => {
        const result = await turmsClient.conversationService.updateGroupConversationTypingStatus(GROUP_ID);
        expect(result).toBeFalsy();
    });
});

describe('Query', () => {
    it('queryPrivateConversations_shouldReturnNotEmptyConversations', async () => {
        const conversations = await turmsClient.conversationService.queryPrivateConversations([RELATED_USER_ID]);
        expect(conversations.length).toBeGreaterThan(0);
        expect(conversations[0].readDate).toBeTruthy();
    });
    it('queryGroupConversations_shouldReturnNotEmptyConversations', async () => {
        const conversations = await turmsClient.conversationService.queryGroupConversations([GROUP_ID]);
        expect(conversations.length).toBeGreaterThan(0);
        expect(conversations[0].memberIdAndReadDate)
    });
});