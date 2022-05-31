import Constants from '../helper/constants';
import TurmsClient from '../../src/turms-client';

let turmsClient: TurmsClient;
const USER_ID = '1';
const GROUP_ID = '1';

const PROFILE_PICTURE: Buffer = new Buffer([0, 1, 2, 3]);
const ATTACHMENT: Buffer = PROFILE_PICTURE;
let messageId;

beforeAll(async () => {
    turmsClient = new TurmsClient({
        wsUrl: Constants.WS_URL,
        storageServerUrl: Constants.STORAGE_SERVER_URL
    });
    await turmsClient.userService.login(USER_ID, '123');
});

afterAll(async () => {
    await turmsClient.userService.logout();
});

describe('Create', async () => {
    it('uploadProfilePicture_shouldReturnUrl', async () => {
        const response = await turmsClient.storageService.uploadProfilePicture(PROFILE_PICTURE);
        const url = response.data;
        expect(url).toBeTruthy();
    });
    it('uploadGroupProfilePicture_shouldReturnUrl', async () => {
        const response = await turmsClient.storageService.uploadGroupProfilePicture(PROFILE_PICTURE, GROUP_ID);
        const url = response.data;
        expect(url).toBeTruthy();
    });
    it('uploadAttachment_shouldReturnUrl', async () => {
        const sendMessageResponse = await turmsClient.messageService.sendMessage(false, '2', null, 'I\'ve attached a picture');
        messageId = sendMessageResponse.data;
        const uploadAttachmentResponse = await turmsClient.storageService.uploadAttachment(messageId, ATTACHMENT);
        const url = uploadAttachmentResponse.data;
        expect(url).toBeTruthy();
    });
});

describe('Query', () => {
    // TODO
    // it('queryProfilePicture_shouldEqualUploadedPicture', async () => {
    //     const bytes = await turmsClient.storageService.queryProfilePicture(USER_ID);
    //     expect(Buffer.from(bytes)).toEqual(PROFILE_PICTURE);
    // });
    // it('queryGroupProfilePicture_shouldEqualUploadedPicture', async () => {
    //     const bytes = await turmsClient.storageService.queryGroupProfilePicture(GROUP_ID);
    //     expect(Buffer.from(bytes)).toEqual(PROFILE_PICTURE);
    // });
    // it('queryAttachment_shouldEqualUploadedAttachment', async () => {
    //     const bytes = await turmsClient.storageService.queryAttachment(messageId);
    //     expect(Buffer.from(bytes)).toEqual(ATTACHMENT);
    // });
});

describe('Delete', () => {
    it('deleteProfile_shouldSucceed', async () => {
        const response = await turmsClient.storageService.deleteProfile();
        expect(response.data).toBeFalsy();
    });
    it('deleteGroupProfile_shouldSucceed', async () => {
        const response = await turmsClient.storageService.deleteGroupProfile(GROUP_ID);
        expect(response.data).toBeFalsy();
    });
});