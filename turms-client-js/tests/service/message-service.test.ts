import TurmsClient from '../../src/turms-client';
import Constants from '../helper/constants';
import MessageService from '../../src/service/message-service';

let senderClient: TurmsClient;
let recipientClient: TurmsClient;
let groupMemberClient: TurmsClient;
const SENDER_ID = '1';
const RECIPIENT_ID = '2';
const GROUP_MEMBER_ID = '3';
const TARGET_GROUP_ID = '1';
let privateMessageId: string;
let groupMessageId: string;

beforeAll(async () => {
    senderClient = new TurmsClient(Constants.WS_URL);
    recipientClient = new TurmsClient(Constants.WS_URL);
    groupMemberClient = new TurmsClient(Constants.WS_URL);
    await senderClient.userService.login({
        userId: SENDER_ID,
        password: '123'
    });
    await recipientClient.userService.login({
        userId: RECIPIENT_ID,
        password: '123'
    });
    await groupMemberClient.userService.login({
        userId: GROUP_MEMBER_ID,
        password: '123'
    });
});

afterAll(async () => {
    await senderClient.userService.logout();
    await recipientClient.userService.logout();
    await groupMemberClient.userService.logout();
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
        const response = await senderClient.messageService.sendMessage({
            isGroupMessage: false,
            targetId: RECIPIENT_ID,
            deliveryDate: new Date(),
            text: 'hello'
        });
        privateMessageId = response.data;
        expect(privateMessageId).toBeTruthy();
    });
    it('sendGroupMessage_shouldReturnMessageId', async () => {
        const response = await senderClient.messageService.sendMessage({
            isGroupMessage: true,
            targetId: TARGET_GROUP_ID,
            deliveryDate: new Date(),
            text: 'hello'
        });
        groupMessageId = response.data;
        expect(privateMessageId).toBeTruthy();
    });
    it('forwardPrivateMessage_shouldReturnForwardedMessageId', async () => {
        const response = await senderClient.messageService.forwardMessage({
            messageId: privateMessageId,
            isGroupMessage: false,
            targetId: RECIPIENT_ID
        });
        const messageId = response.data;
        expect(messageId).toBeTruthy();
    });
    it('forwardGroupMessage_shouldReturnForwardedMessageId', async () => {
        const response = await senderClient.messageService.forwardMessage({
            messageId: groupMessageId,
            isGroupMessage: true,
            targetId: TARGET_GROUP_ID
        });
        const messageId = response.data;
        expect(messageId).toBeTruthy();
    });
});

describe('Update', () => {
    it('recallMessage_shouldSucceed', async () => {
        const response = await senderClient.messageService.recallMessage({
            messageId: groupMessageId
        });
        expect(response.data).toBeFalsy();
    });
    it('updateSentMessage_shouldSucceed', async () => {
        const response = await senderClient.messageService.updateSentMessage({
            messageId: privateMessageId,
            text: 'I have modified the message'
        });
        expect(response.data).toBeFalsy();
    });
});

describe('Query', () => {
    it('queryMessages_shouldReturnNotEmptyMessages', async () => {
        const response = await recipientClient.messageService.queryMessages({
            areGroupMessages: false,
            fromIds: [SENDER_ID],
            maxCount: 10
        });
        const messages = response.data;
        expect(messages.length).toBeGreaterThan(0);
    });
    it('queryMessagesWithTotal_shouldReturnNotEmptyMessagesWithTotal', async () => {
        const response = await recipientClient.messageService.queryMessages({
            areGroupMessages: false,
            fromIds: [SENDER_ID],
            maxCount: 1
        });
        const messagesWithTotals = response.data;
        expect(messagesWithTotals.length).toBeGreaterThan(0);
    });
});

describe('Util', () => {
    it('generateLocationRecord', () => {
        const data = MessageService.generateLocationRecord({
            latitude: 1,
            longitude: 1,
            details: {
                'name': 'value'
            }
        });
        expect(data).toBeTruthy();
    });
    it('generateAudioRecordByDescription', () => {
        const data = MessageService.generateAudioRecordByDescription({
            url: 'https://abc.com'
        });
        expect(data).toBeTruthy();
    });
    it('generateAudioRecordByData', () => {
        const source = new Uint8Array([0, 1, 2]);
        const data = MessageService.generateAudioRecordByData({
            data: source
        });
        expect(data).toBeTruthy();
    });
    it('generateVideoRecordByDescription', () => {
        const data = MessageService.generateVideoRecordByDescription({
            url: 'https://abc.com'
        });
        expect(data).toBeTruthy();
    });
    it('generateVideoRecordByData', () => {
        const source = new Uint8Array([0, 1, 2]);
        const data = MessageService.generateVideoRecordByData({
            data: source
        });
        expect(data).toBeTruthy();
    });
    it('generateImageRecordByData', () => {
        const source = new Uint8Array([0, 1, 2]);
        const data = MessageService.generateImageRecordByData({
            data: source
        });
        expect(data).toBeTruthy();
    });
    it('generateFileRecordByDate', () => {
        const source = new Uint8Array([0, 1, 2]);
        const data = MessageService.generateFileRecordByDate({
            data: source
        });
        expect(data).toBeTruthy();
    });
    it('generateImageRecordByDescription', () => {
        const data = MessageService.generateImageRecordByDescription({
            url: 'https://abc.com'
        });
        expect(data).toBeTruthy();
    });
    it('generateFileRecordByDescription', () => {
        const data = MessageService.generateFileRecordByDescription({
            url: 'https://abc.com'
        });
        expect(data).toBeTruthy();
    });
});