import TurmsClient from "../../src/turms-client";
import Constants from "../helper/constants";
import {im} from "../../src/model/proto-bundle";
import MessageService from "../../src/service/message-service";
import DeviceType = im.turms.proto.DeviceType;
import UserStatus = im.turms.proto.UserStatus;
import ChatType = im.turms.proto.ChatType;

let senderClient: TurmsClient;
let recipientClient: TurmsClient;
const SENDER_ID = '1';
const RECIPIENT_ID = '2';
const TARGET_GROUP_ID = '1';
let privateMessageId;
let groupMessageId;

beforeAll(async () => {
    senderClient = new TurmsClient(Constants.WS_URL);
    recipientClient = new TurmsClient(Constants.WS_URL);
    await senderClient.driver.connect(SENDER_ID, "123", null, UserStatus.BUSY, DeviceType.BROWSER);
    await recipientClient.driver.connect(RECIPIENT_ID, "123", null, UserStatus.BUSY, DeviceType.BROWSER);
});

afterAll(async () => {
    if (senderClient.driver.connected()) {
        await senderClient.driver.disconnect();
    }
    if (recipientClient.driver.connected()) {
        await recipientClient.driver.disconnect();
    }
});

describe('Constructor', () => {
    it('constructor_shouldReturnNotNullMessageServiceInstance', () => {
        expect(senderClient.messageService).toBeTruthy();
        expect(recipientClient.messageService).toBeTruthy();
    });
});

describe('Create', () => {
    it('sendPrivateMessage_shouldReturnMessageId', async () => {
        privateMessageId = await senderClient.messageService.sendMessage(ChatType.PRIVATE, RECIPIENT_ID, new Date(), "hello");
        expect(privateMessageId).toBeTruthy();
    });
    it('sendGroupMessage_shouldReturnMessageId', async () => {
        groupMessageId = await senderClient.messageService.sendMessage(ChatType.GROUP, TARGET_GROUP_ID, new Date(), "hello");
        expect(privateMessageId).toBeTruthy();
    });
    it('forwardPrivateMessage_shouldReturnForwardedMessageId', async () => {
        const messageId = await senderClient.messageService.forwardMessage(privateMessageId, ChatType.PRIVATE, RECIPIENT_ID);
        expect(messageId).toBeTruthy();
    });
    it('forwardGroupMessage_shouldReturnForwardedMessageId', async () => {
        const messageId = await senderClient.messageService.forwardMessage(groupMessageId, ChatType.GROUP, TARGET_GROUP_ID);
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
        const result = await senderClient.messageService.updateTypingStatusRequest(ChatType.PRIVATE, privateMessageId);
        expect(result).toBeUndefined();
    });
});

describe('Query', () => {
    it('queryMessages_shouldReturnNotEmptyMessages', async () => {
        const messages = await recipientClient.messageService.queryMessages(null, ChatType.PRIVATE, null, SENDER_ID, null, null, null, 10);
        expect(messages.length).toBeGreaterThan(0);
    });
    it('queryPendingMessagesWithTotal_shouldReturnNotEmptyPendingMessagesWithTotal', async () => {
        const messagesWithTotals = await senderClient.messageService.queryPendingMessagesWithTotal(10);
        expect(messagesWithTotals.length).toBeGreaterThan(0);
    });
    it('queryMessageStatus_shouldReturnNotEmptyMessageStatus', async () => {
        const messageStatuses = await senderClient.messageService.queryMessageStatus(groupMessageId);
        expect(messageStatuses.length).toBeGreaterThan(0);
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