import Constants from '../helper/constants';
import TurmsClient from '../../src/turms-client';
import ResponseStatusCode from '../../src/model/response-status-code';
import { UserStatus } from '../../src/model/proto/constant/user_status';
import { ResponseAction } from '../../src/model/proto/constant/response_action';

let turmsClient: TurmsClient;
const userStatus = UserStatus.AWAY;
let relationshipGroupIndex: number;

beforeAll(() => {
    turmsClient = new TurmsClient(Constants.WS_URL);
});

afterAll(async () => {
    await turmsClient.userService.logout();
});

describe('Constructor', () => {
    it('constructor_shouldReturnNotNullGroupServiceInstance', () => {
        expect(turmsClient.userService).toBeTruthy();
    });
});

describe('Login', () => {
    it('login_shouldSucceed', async () => {
        const response = await turmsClient.userService.login('1', '123');
        expect(response.data).toBeFalsy();
    });
});

describe('Create', () => {
    it('createRelationship_shouldSucceed', async () => {
        try {
            const response = await turmsClient.userService.createRelationship('10', true);
            expect(response.data).toBeFalsy();
        } catch (e) {
            expect(e.code).toEqual(ResponseStatusCode.CREATE_EXISTING_RELATIONSHIP);
        }
    });
    it('createFriendRelationship_shouldSucceed', async () => {
        try {
            const response = await turmsClient.userService.createFriendRelationship('10');
            expect(response.data).toBeFalsy();
        } catch (e) {
            expect(e.code).toEqual(ResponseStatusCode.CREATE_EXISTING_RELATIONSHIP);
        }
    });
    it('createBlockedUserRelationship_shouldSucceed', async () => {
        try {
            const response = await turmsClient.userService.createBlockedUserRelationship('10');
            expect(response.data).toBeFalsy();
        } catch (e) {
            expect(e.code).toEqual(ResponseStatusCode.CREATE_EXISTING_RELATIONSHIP);
        }
    });
    it('sendFriendRequest_shouldReturnFriendRequestId', async () => {
        try {
            const response = await turmsClient.userService.sendFriendRequest('11', 'content');
            const friendRequestId = response.data;
            expect(friendRequestId).toBeTruthy();
        } catch (e) {
            expect(e.code).toEqual(ResponseStatusCode.CREATE_EXISTING_FRIEND_REQUEST);
        }
    });
    it('createRelationshipGroup_shouldReturnRelationshipGroupIndex', async () => {
        try {
            const response = await turmsClient.userService.createRelationshipGroup('newGroup');
            relationshipGroupIndex = response.data;
            expect(relationshipGroupIndex).toBeTruthy();
        } catch (e) {
            expect(e.code).toEqual(ResponseStatusCode.CREATE_EXISTING_FRIEND_REQUEST);
        }
    });
});

describe('Update', () => {
    it('updateOnlineStatus_shouldSucceed', async () => {
        const response = await turmsClient.userService.updateOnlineStatus(userStatus);
        expect(response.data).toBeFalsy();
    });
    it('updatePassword_shouldSucceed', async () => {
        const response = await turmsClient.userService.updatePassword('123');
        expect(response.data).toBeFalsy();
    });
    it('updateProfile_shouldSucceed', async () => {
        const response = await turmsClient.userService.updateProfile('123', '123');
        expect(response.data).toBeFalsy();
    });
    it('updateRelationship_shouldSucceed', async () => {
        const response = await turmsClient.userService.updateRelationship('10', null, 1);
        expect(response.data).toBeFalsy();
    });
    it('replyFriendRequest_shouldSucceed', async () => {
        const response = await turmsClient.userService.replyFriendRequest('10', ResponseAction.ACCEPT, 'reason');
        expect(response.data).toBeFalsy();
    });
    it('updateRelationshipGroup_shouldSucceed', async () => {
        const response = await turmsClient.userService.updateRelationshipGroup(relationshipGroupIndex, 'newGroupName');
        expect(response.data).toBeFalsy();
    });
    it('moveRelatedUserToGroup_shouldSucceed', async () => {
        let response = await turmsClient.userService.moveRelatedUserToGroup('2', 1);
        expect(response.data).toBeFalsy();
        response = await turmsClient.userService.moveRelatedUserToGroup('2', 0);
        expect(response.data).toBeFalsy();
    });
    it('updateLocation_shouldSucceed', async () => {
        const response = await turmsClient.userService.updateLocation(2, 2);
        expect(response.data).toBeFalsy();
    });
});

describe('Query', () => {
    it('queryUserProfile_shouldReturnUserInfoWithVersion', async () => {
        const response = await turmsClient.userService.queryUserProfile('1');
        const userInfos = response.data;
        expect(userInfos).toBeTruthy();
    });
    it('queryNearbyUsers_shouldReturnUsersInfos', async () => {
        const response = await turmsClient.userService.queryNearbyUsers(1, 1);
        const users = response.data;
        expect(users).toBeTruthy();
    });
    it('queryOnlineStatusesRequest_shouldUsersOnlineStatus', async () => {
        const response = await turmsClient.userService.queryOnlineStatusesRequest(['1']);
        const userStatuses = response.data;
        expect(userStatuses[0].userStatus).toEqual(userStatus);
    });
    it('queryRelationships_shouldReturnUserRelationshipsWithVersion', async () => {
        const response = await turmsClient.userService.queryRelationships(['2']);
        const relationships = response.data;
        expect(relationships).toBeTruthy();
    });
    it('queryRelatedUserIds_shouldReturnRelatedUserIds', async () => {
        const response = await turmsClient.userService.queryRelatedUserIds();
        const userIds = response.data;
        expect(userIds).toBeTruthy();
    });
    it('queryFriends_shouldReturnFriendRelationships', async () => {
        const response = await turmsClient.userService.queryFriends();
        const relationships = response.data;
        expect(relationships).toBeTruthy();
    });
    it('queryBlockedUsers_shouldReturnRelationshipsWithBlockedUsers', async () => {
        const response = await turmsClient.userService.queryBlockedUsers();
        const relationships = response.data;
        expect(relationships).toBeTruthy();
    });
    // TODO: Extend the query friend request
    // it('queryFriendRequests_shouldReturnFriendRequests', async () => {
    //     const result = await turmsClient.userService.queryFriendRequests();
    //     expect(result).toBeTruthy();
    // });
    it('queryRelationshipGroups_shouldReturnRelationshipGroups', async () => {
        const response = await turmsClient.userService.queryRelationshipGroups();
        const relationshipGroups = response.data;
        expect(relationshipGroups).toBeTruthy();
    });
});

describe('Delete', () => {
    it('deleteRelationship_shouldSucceed', async () => {
        const response = await turmsClient.userService.deleteRelationship('10');
        expect(response.data).toBeFalsy();
    });
    it('deleteRelationship_shouldSucceed', async () => {
        const response = await turmsClient.userService.deleteRelationshipGroups(relationshipGroupIndex);
        expect(response.data).toBeFalsy();
    });
});

describe('Logout', () => {
    it('logout_shouldSucceed', async () => {
        const response = await turmsClient.userService.logout();
        expect(response.data).toBeFalsy();
    });
});