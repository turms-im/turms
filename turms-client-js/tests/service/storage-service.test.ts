import Constants from "../helper/constants";
import TurmsClient from "../../src/turms-client";
import {readFileSync} from 'fs';

let turmsClient: TurmsClient;
const USER_ID = '1';
const GROUP_ID = '1';

const PROFILE_PICTURE: Buffer = readFileSync("tests/resources/profile.webp");
const ATTACHMENT: Buffer = PROFILE_PICTURE;
let messageId;

beforeAll(async () => {
    turmsClient = new TurmsClient({
        wsUrl: Constants.WS_URL,
        storageServerUrl: Constants.STORAGE_SERVER_URL
    });
    await turmsClient.userService.login(USER_ID, "123");
});

afterAll(async () => {
    if (turmsClient.driver.isConnected()) {
        await turmsClient.driver.disconnect();
    }
});

describe('Create', async () => {
    it('uploadProfilePicture_shouldReturnUrl', async () => {
        const url = await turmsClient.storageService.uploadProfilePicture(PROFILE_PICTURE);
        expect(url).toBeTruthy();
    });
    it('uploadGroupProfilePicture_shouldReturnUrl', async () => {
        const url = await turmsClient.storageService.uploadGroupProfilePicture(PROFILE_PICTURE, GROUP_ID);
        expect(url).toBeTruthy();
    });
    it('uploadAttachment_shouldReturnUrl', async () => {
        messageId = await turmsClient.messageService.sendMessage(false, '2', null, "I've attached a picture")
        const url = await turmsClient.storageService.uploadAttachment(messageId, ATTACHMENT);
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
        await turmsClient.storageService.deleteProfile();
        expect(true).toBeTruthy();
    });
    it('deleteGroupProfile_shouldSucceed', async () => {
        await turmsClient.storageService.deleteGroupProfile(GROUP_ID);
        expect(true).toBeTruthy();
    });
});