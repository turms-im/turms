import TurmsClient from "../../src/turms-client";
import Constants from "../helper/constants";
import MessageService from "../../src/service/message-service";

let senderClient: TurmsClient;
let recipientClient: TurmsClient;
let groupMemberClient: TurmsClient;
const SENDER_ID = '1';
const RECIPIENT_ID = '2';
const GROUP_MEMBER_ID = '3';
const TARGET_GROUP_ID = '1';
let privateMessageId;
let groupMessageId;

beforeAll(async () => {
    senderClient = new TurmsClient(Constants.WS_URL);
    recipientClient = new TurmsClient(Constants.WS_URL);
    groupMemberClient = new TurmsClient(Constants.WS_URL);
    await senderClient.driver.connect(SENDER_ID, "123");
    await recipientClient.driver.connect(RECIPIENT_ID, "123");
    await groupMemberClient.driver.connect(GROUP_MEMBER_ID, "123");
});

afterAll(async () => {
    if (senderClient.driver.isConnected()) {
        await senderClient.driver.disconnect();
    }
    if (recipientClient.driver.isConnected()) {
        await recipientClient.driver.disconnect();
    }
    if (groupMemberClient.driver.isConnected()) {
        await groupMemberClient.driver.disconnect();
    }
});

describe('Constructor', () => {
    it('constructor_shouldReturnNotNullMessageServiceInstance', () => {
        expect(senderClient.messageService).toBeTruthy();
        expect(recipientClient.messageService).toBeTruthy();
        expect(groupMemberClient.messageService).toBeTruthy();
    });
});

describe('Create', () => {
    it('sendPrivateMessage_shouldReturnMessageId', async () => {
        privateMessageId = await senderClient.messageService.sendMessage(false, RECIPIENT_ID, new Date(), "hello");
        expect(privateMessageId).toBeTruthy();
    });
    it('sendGroupMessage_shouldReturnMessageId', async () => {
        groupMessageId = await senderClient.messageService.sendMessage(true, TARGET_GROUP_ID, new Date(), "hello");
        expect(privateMessageId).toBeTruthy();
    });
    it('forwardPrivateMessage_shouldReturnForwardedMessageId', async () => {
        const messageId = await senderClient.messageService.forwardMessage(privateMessageId, false, RECIPIENT_ID);
        expect(messageId).toBeTruthy();
    });
    it('forwardGroupMessage_shouldReturnForwardedMessageId', async () => {
        const messageId = await senderClient.messageService.forwardMessage(groupMessageId, true, TARGET_GROUP_ID);
        expect(messageId).toBeTruthy();
    });
});

describe('Update', () => {
    it('recallMessage_shouldSucceed', async () => {
        const result = await senderClient.messageService.recallMessage(groupMessageId);
        expect(result).toBeUndefined();
    });
    it('recallMessage_shouldSucceed', async () => {
        const result = await senderClient.messageService.updateSentMessage(privateMessageId, "I have modified the message");
        expect(result).toBeUndefined();
    });
    it('readMessage_shouldSucceed', async () => {
        const result = await recipientClient.messageService.readMessage(privateMessageId, new Date());
        expect(result).toBeUndefined();
    });
    it('markMessageUnread_shouldSucceed', async () => {
        const result = await recipientClient.messageService.markMessageUnread(privateMessageId);
        expect(result).toBeUndefined();
    });
    it('updateTypingStatus_shouldSucceed', async () => {
        const result = await senderClient.messageService.updateTypingStatusRequest(false, privateMessageId);
        expect(result).toBeUndefined();
    });
});

describe('Query', () => {
    it('queryMessages_shouldReturnNotEmptyMessages', async () => {
        const messages = await recipientClient.messageService.queryMessages(null, false, null, SENDER_ID, null, null, null, 10);
        expect(messages.length).toBeGreaterThan(0);
    });
    it('queryPendingMessagesWithTotal_shouldReturnNotEmptyPendingMessagesWithTotal', async () => {
        const messagesWithTotals = await senderClient.messageService.queryPendingMessagesWithTotal(10);
        expect(messagesWithTotals.length).toBeGreaterThan(0);
    });
    it('queryMessageStatus_shouldReturnNotEmptyMessageStatus', async () => {
        const messageStatusesOfMember1 = await senderClient.messageService.queryMessageStatus(groupMessageId);
        const messageStatusesOfMember2 = await groupMemberClient.messageService.queryMessageStatus(groupMessageId);
        expect(messageStatusesOfMember1[0].messageId === messageStatusesOfMember2[0].messageId);
    });
});

describe('Util', () => {
    it('generateLocationRecord', () => {
        const data = MessageService.generateLocationRecord(1, 1, "name", "address");
        expect(data).toBeTruthy();
    });
    it('generateAudioRecordByDescription', () => {
        const data = MessageService.generateAudioRecordByDescription("https://abc.com");
        expect(data).toBeTruthy();
    });
    it('generateAudioRecordByData', () => {
        const source = new Uint8Array([0, 1, 2]);
        const data = MessageService.generateAudioRecordByData(source);
        expect(data).toBeTruthy();
    });
    it('generateVideoRecordByDescription', () => {
        const data = MessageService.generateVideoRecordByDescription("https://abc.com");
        expect(data).toBeTruthy();
    });
    it('generateVideoRecordByData', () => {
        const source = new Uint8Array([0, 1, 2]);
        const data = MessageService.generateVideoRecordByData(source);
        expect(data).toBeTruthy();
    });
    it('generateImageRecordByData', () => {
        const source = new Uint8Array([0, 1, 2]);
        const data = MessageService.generateImageRecordByData(source);
        expect(data).toBeTruthy();
    });
    it('generateFileRecordByDate', () => {
        const source = new Uint8Array([0, 1, 2]);
        const data = MessageService.generateFileRecordByDate(source);
        expect(data).toBeTruthy();
    });
    it('generateImageRecordByDescription', () => {
        const data = MessageService.generateImageRecordByDescription("https://abc.com");
        expect(data).toBeTruthy();
    });
    it('generateFileRecordByDescription', () => {
        const data = MessageService.generateFileRecordByDescription("https://abc.com");
        expect(data).toBeTruthy();
    });
});