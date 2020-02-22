import Constants from "./helper/constants";
import TurmsClient from "../src/turms-client";

describe('TurmsClient class', () => {
    it('constructor_shouldReturnNotNullClientInstance', () => {
        const turmsClient = new TurmsClient(Constants.WS_URL);
        expect(turmsClient).toBeTruthy();
    });
});