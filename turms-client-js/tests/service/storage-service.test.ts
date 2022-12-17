import Constants from '../helper/constants';
import TurmsClient from '../../src/turms-client';

let turmsClient: TurmsClient;
const USER_ID = '1';
const GROUP_ID = '1';

const MEDIA_TYPE = 'image/png';
const PROFILE_PICTURE: Buffer = new Buffer([0, 1, 2, 3]);
const MESSAGE_ATTACHMENT: Buffer = PROFILE_PICTURE;
let attachmentId;

beforeAll(async () => {
    turmsClient = new TurmsClient({
        wsUrl: Constants.WS_URL,
        storageServerUrl: Constants.STORAGE_SERVER_URL
    });
    await turmsClient.userService.login({
        userId: USER_ID,
        password: '123'
    });
});

afterAll(async () => {
    await turmsClient.userService.logout();
});

describe('Create', () => {
    it('uploadUserProfilePicture_shouldReturnUploadResult', async () => {
        const response = await turmsClient.storageService.uploadUserProfilePicture({
            mediaType: MEDIA_TYPE,
            data: PROFILE_PICTURE
        });
        const result = response.data;
        expect(result).toBeTruthy();
    });
    it('uploadGroupProfilePicture_shouldReturnUploadResult', async () => {
        const response = await turmsClient.storageService.uploadGroupProfilePicture({
            groupId: GROUP_ID,
            mediaType: MEDIA_TYPE,
            data: PROFILE_PICTURE
        });
        const result = response.data;
        expect(result).toBeTruthy();
    });
    it('uploadMessageAttachment_shouldReturnUploadResult', async () => {
        const uploadAttachmentResponse = await turmsClient.storageService.uploadMessageAttachment({
            data: MESSAGE_ATTACHMENT,
            mediaType: MEDIA_TYPE
        });
        attachmentId = uploadAttachmentResponse.data.resourceIdNum;
        expect(attachmentId).toBeTruthy();
    });
});

describe('Query', () => {
    it('queryUserProfilePicture_shouldEqualUploadedPicture', async () => {
        const resource = await turmsClient.storageService.queryUserProfilePicture({
            userId: USER_ID
        });
        expect(resource.data.data).toEqual(PROFILE_PICTURE.valueOf());
    });
    it('queryGroupProfilePicture_shouldEqualUploadedPicture', async () => {
        const resource = await turmsClient.storageService.queryGroupProfilePicture({
            groupId: GROUP_ID
        });
        expect(resource.data.data).toEqual(PROFILE_PICTURE.valueOf());
    });
    it('queryMessageAttachment_shouldEqualUploadedAttachment', async () => {
        const resource = await turmsClient.storageService.queryMessageAttachment({
            attachmentIdNum: attachmentId
        });
        expect(resource.data.data).toEqual(MESSAGE_ATTACHMENT);
    });
});

describe('Delete', () => {
    it('deleteUserProfilePicture_shouldSucceed', async () => {
        const response = await turmsClient.storageService.deleteUserProfilePicture();
        expect(response.data).toBeFalsy();
    });
    it('deleteGroupProfilePicture_shouldSucceed', async () => {
        const response = await turmsClient.storageService.deleteGroupProfilePicture({
            groupId: GROUP_ID
        });
        expect(response.data).toBeFalsy();
    });
});