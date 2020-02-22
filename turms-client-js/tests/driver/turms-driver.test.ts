import TurmsDriver from "../../src/driver/turms-driver";
import {im} from "../../src/model/proto-bundle";
import UserLocation = im.turms.proto.UserLocation;
import DeviceType = im.turms.proto.DeviceType;
import UserStatus = im.turms.proto.UserStatus;
import TurmsRequest = im.turms.proto.TurmsRequest;

let turmsDriver: TurmsDriver;

beforeAll(() => {
    turmsDriver = new TurmsDriver();
});

afterAll(async () => {
    if (turmsDriver.connected()) {
        await turmsDriver.disconnect();
    }
});

describe('TurmsDriver Class', () => {
    it('constructor_shouldReturnNotNullDriverInstance', () => {
        expect(turmsDriver).toBeTruthy();
    });
    it('connect_shouldSucceed', async () => {
        const location = UserLocation.encode({
            longitude: 1,
            latitude: 1
        }).finish();
        const result = await turmsDriver.connect('1', "123", location, UserStatus.BUSY, DeviceType.ANDROID);
        expect(result).toBeUndefined();
    });
    it('sendHeartbeat_shouldSucceed', async () => {
        const location = UserLocation.encode({
            longitude: 1,
            latitude: 1
        }).finish();
        const result = await turmsDriver.sendHeartbeat();
        expect(result).toBeUndefined();
    });
    it('sendTurmsRequest_shouldSucceed', async () => {
        const result = await turmsDriver.send({
            queryUserProfileRequest: {
                userId: '1'
            }
        });
        expect(result).toBeTruthy();
    });
    it('disconnect_shouldSucceed', async () => {
        const result = await turmsDriver.disconnect();
        expect(result).toBeUndefined();
    });
});