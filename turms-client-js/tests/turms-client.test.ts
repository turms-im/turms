import TurmsClient from '../src/turms-client';
import Constants from './helper/constants';

describe('TurmsClient class', () => {
    it('constructor_shouldReturnNotNullClientInstance', () => {
        const turmsClient = new TurmsClient(Constants.WS_URL);
        expect(turmsClient).toBeTruthy();
    });
});