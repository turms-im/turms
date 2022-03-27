import Constants from '../helper/constants';
import TurmsClient from '../../src/turms-client';
import ResponseStatusCode from '../../src/model/turms-status-code'
import {UserStatus} from '../../src/model/proto/constant/user_status';
import {ResponseAction} from '../../src/model/proto/constant/response_action';

let turmsClient: TurmsClient;
let userStatus = UserStatus.AWAY;
let relationshipGroupIndex;

beforeAll(() => {
    turmsClient = new TurmsClient(Constants.WS_URL);
});

afterAll(async () => {
    await turmsClient.userService.logout();
});

describe('Constructor', () => {
    it('constructor_shouldReturnNotNullGroupServiceInstance', () => {
        expect(turmsClient.userService).toBeTruthy();
    })
});

describe('Login', () => {
    it('login_shouldSucceed', async () => {
        const result = await turmsClient.userService.login('1', '123');
        expect(result).toBeFalsy();
    });
});

describe('Create', () => {
    it('createRelationship_shouldSucceed', async () => {
        try {
            const result = await turmsClient.userService.createRelationship('10', true);
            expect(result).toBeFalsy();
        } catch (e) {
            expect(e.code).toEqual(ResponseStatusCode.CREATE_EXISTING_RELATIONSHIP);
        }
    });
    it('createFriendRelationship_shouldSucceed', async () => {
        try {
            const result = await turmsClient.userService.createFriendRelationship('10');
            expect(result).toBeFalsy();
        } catch (e) {
            expect(e.code).toEqual(ResponseStatusCode.CREATE_EXISTING_RELATIONSHIP);
        }
    });
    it('createBlockedUserRelationship_shouldSucceed', async () => {
        try {
            const result = await turmsClient.userService.createBlockedUserRelationship('10');
            expect(result).toBeFalsy();
        } catch (e) {
            expect(e.code).toEqual(ResponseStatusCode.CREATE_EXISTING_RELATIONSHIP);
        }
    });
    it('sendFriendRequest_shouldReturnFriendRequestId', async () => {
        try {
            const friendRequestId = await turmsClient.userService.sendFriendRequest('11', 'content');
            expect(friendRequestId).toBeTruthy();
        } catch (e) {
            expect(e.code).toEqual(ResponseStatusCode.CREATE_EXISTING_FRIEND_REQUEST);
        }
    });
    it('createRelationshipGroup_shouldReturnRelationshipGroupIndex', async () => {
        try {
            relationshipGroupIndex = await turmsClient.userService.createRelationshipGroup('newGroup');
            expect(relationshipGroupIndex).toBeTruthy();
        } catch (e) {
            expect(e.code).toEqual(ResponseStatusCode.CREATE_EXISTING_FRIEND_REQUEST);
        }
    });
});

describe('Update', () => {
    it('updateOnlineStatus_shouldSucceed', async () => {
        const result = await turmsClient.userService.updateOnlineStatus(userStatus);
        expect(result).toBeFalsy();
    });
    it('updatePassword_shouldSucceed', async () => {
        const result = await turmsClient.userService.updatePassword('123');
        expect(result).toBeFalsy();
    });
    it('updateProfile_shouldSucceed', async () => {
        const result = await turmsClient.userService.updateProfile('123', '123');
        expect(result).toBeFalsy();
    });
    it('updateRelationship_shouldSucceed', async () => {
        const result = await turmsClient.userService.updateRelationship('10', null, 1);
        expect(result).toBeFalsy();
    });
    it('replyFriendRequest_shouldSucceed', async () => {
        const result = await turmsClient.userService.replyFriendRequest('10', ResponseAction.ACCEPT, 'reason');
        expect(result).toBeFalsy();
    });
    it('updateRelationshipGroup_shouldSucceed', async () => {
        const result = await turmsClient.userService.updateRelationshipGroup(relationshipGroupIndex, 'newGroupName');
        expect(result).toBeFalsy();
    });
    it('moveRelatedUserToGroup_shouldSucceed', async () => {
        let result = await turmsClient.userService.moveRelatedUserToGroup('2', 1);
        expect(result).toBeFalsy();
        result = await turmsClient.userService.moveRelatedUserToGroup('2', 0);
        expect(result).toBeFalsy();
    });
    it('updateLocation_shouldSucceed', async () => {
        const result = await turmsClient.userService.updateLocation(2, 2);
        expect(result).toBeFalsy();
    });
});

describe('Query', () => {
    it('queryUserProfile_shouldReturnUserInfoWithVersion', async () => {
        const result = await turmsClient.userService.queryUserProfile('1');
        expect(result).toBeTruthy();
    });
    it('queryNearbyUsers_shouldReturnUsersInfos', async () => {
        const result = await turmsClient.userService.queryNearbyUsers(1, 1);
        expect(result).toBeTruthy();
    });
    it('queryOnlineStatusesRequest_shouldUsersOnlineStatus', async () => {
        const result = await turmsClient.userService.queryOnlineStatusesRequest(['1']);
        expect(result[0].userStatus).toEqual(userStatus);
    });
    it('queryRelationships_shouldReturnUserRelationshipsWithVersion', async () => {
        const result = await turmsClient.userService.queryRelationships(['2']);
        expect(result).toBeTruthy();
    });
    it('queryRelatedUserIds_shouldReturnRelatedUserIds', async () => {
        const result = await turmsClient.userService.queryRelatedUserIds();
        expect(result).toBeTruthy();
    });
    it('queryFriends_shouldReturnFriendRelationships', async () => {
        const result = await turmsClient.userService.queryFriends();
        expect(result).toBeTruthy();
    });
    it('queryBlockedUsers_shouldReturnRelationshipsWithBlockedUsers', async () => {
        const result = await turmsClient.userService.queryBlockedUsers();
        expect(result).toBeTruthy();
    });
    // TODO: Extend the query friend request
    // it('queryFriendRequests_shouldReturnFriendRequests', async () => {
    //     const result = await turmsClient.userService.queryFriendRequests();
    //     expect(result).toBeTruthy();
    // });
    it('queryRelationshipGroups_shouldReturnRelationshipGroups', async () => {
        const result = await turmsClient.userService.queryRelationshipGroups();
        expect(result).toBeTruthy();
    });
});

describe('Delete', () => {
    it('deleteRelationship_shouldSucceed', async () => {
        const result = await turmsClient.userService.deleteRelationship('10');
        expect(result).toBeFalsy();
    });
    it('deleteRelationship_shouldSucceed', async () => {
        const result = await turmsClient.userService.deleteRelationshipGroups(relationshipGroupIndex);
        expect(result).toBeFalsy();
    })
});

describe('Logout', () => {
    it('logout_shouldSucceed', async () => {
        const result = await turmsClient.userService.logout();
        expect(result).toBeFalsy();
    });
});