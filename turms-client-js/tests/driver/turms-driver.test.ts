import TurmsDriver from '../../src/driver/turms-driver';
import TurmsClient from '../../src/turms-client';
import ResponseStatusCode from '../../src/model/turms-status-code';

let client: TurmsClient;
let driver: TurmsDriver;

beforeAll(() => {
    client = new TurmsClient();
    driver = client.driver;
});

afterAll(async () => {
    await driver.disconnect();
});

describe('TurmsDriver Class', () => {
    it('constructor_shouldReturnNotNullDriverInstance', () => {
        expect(driver).toBeTruthy();
    });
    it('connect_shouldSucceed', async () => {
        await driver.connect();
        expect(driver.isConnected).toBeTruthy();
    });
    it('login_shouldSucceed', async () => {
        await client.userService.login('1', '123');
        expect(client.userService.isLoggedIn).toBeTruthy();
    });
    it('sendHeartbeat_shouldSucceed', async () => {
        const result = await driver.sendHeartbeat();
        expect(result).toBeUndefined();
    });
    it('sendTurmsRequest_shouldSucceed', async () => {
        const result = await driver.send({
            queryUserProfileRequest: {
                userId: '1'
            }
        });
        expect(ResponseStatusCode.isSuccessCode(result.code)).toBeTruthy();
    });
    it('disconnect_shouldSucceed', async () => {
        await driver.disconnect();
        expect(driver.isConnected).toBeFalsy();
    });
});