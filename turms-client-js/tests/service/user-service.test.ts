import Constants from '../helper/constants';
import TurmsClient from '../../src/turms-client';
import ResponseStatusCode from '../../src/model/response-status-code';
import { UserStatus } from '../../src/model/proto/constant/user_status';
import { ResponseAction } from '../../src/model/proto/constant/response_action';
import ResponseError from '../../src/error/response-error';

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
        const response = await turmsClient.userService.login({
            userId: '1',
            password: '123'
        });
        expect(response.data).toBeFalsy();
    });
});

describe('Create', () => {
    it('createRelationship_shouldSucceed', async () => {
        try {
            const response = await turmsClient.userService.createRelationship({
                userId: '10',
                isBlocked: true
            });
            expect(response.data).toBeFalsy();
        } catch (e) {
            expect((e as ResponseError).code).toEqual(ResponseStatusCode.CREATE_EXISTING_RELATIONSHIP);
        }
    });
    it('createFriendRelationship_shouldSucceed', async () => {
        try {
            const response = await turmsClient.userService.createFriendRelationship({
                userId: '10'
            });
            expect(response.data).toBeFalsy();
        } catch (e) {
            expect((e as ResponseError).code).toEqual(ResponseStatusCode.CREATE_EXISTING_RELATIONSHIP);
        }
    });
    it('createBlockedUserRelationship_shouldSucceed', async () => {
        try {
            const response = await turmsClient.userService.createBlockedUserRelationship({
                userId: '10'
            });
            expect(response.data).toBeFalsy();
        } catch (e) {
            expect((e as ResponseError).code).toEqual(ResponseStatusCode.CREATE_EXISTING_RELATIONSHIP);
        }
    });
    it('sendFriendRequest_shouldReturnFriendRequestId', async () => {
        try {
            const response = await turmsClient.userService.sendFriendRequest({
                recipientId: '11',
                content: 'content'
            });
            const friendRequestId = response.data;
            expect(friendRequestId).toBeTruthy();
        } catch (e) {
            expect((e as ResponseError).code).toEqual(ResponseStatusCode.CREATE_EXISTING_FRIEND_REQUEST);
        }
    });
    it('createRelationshipGroup_shouldReturnRelationshipGroupIndex', async () => {
        try {
            const response = await turmsClient.userService.createRelationshipGroup({
                name: 'newGroup'
            });
            relationshipGroupIndex = response.data;
            expect(relationshipGroupIndex).toBeTruthy();
        } catch (e) {
            expect((e as ResponseError).code).toEqual(ResponseStatusCode.CREATE_EXISTING_FRIEND_REQUEST);
        }
    });
});

describe('Update', () => {
    it('updateOnlineStatus_shouldSucceed', async () => {
        const response = await turmsClient.userService.updateOnlineStatus({
            onlineStatus: userStatus
        });
        expect(response.data).toBeFalsy();
    });
    it('updatePassword_shouldSucceed', async () => {
        const response = await turmsClient.userService.updatePassword({
            password: '123'
        });
        expect(response.data).toBeFalsy();
    });
    it('updateProfile_shouldSucceed', async () => {
        const response = await turmsClient.userService.updateProfile({
            name: '123',
            intro: '123'
        });
        expect(response.data).toBeFalsy();
    });
    it('updateRelationship_shouldSucceed', async () => {
        const response = await turmsClient.userService.updateRelationship({
            relatedUserId: '10',
            groupIndex: 1
        });
        expect(response.data).toBeFalsy();
    });
    it('replyFriendRequest_shouldSucceed', async () => {
        const response = await turmsClient.userService.replyFriendRequest({
            requestId: '10',
            responseAction: ResponseAction.ACCEPT,
            reason: 'reason'
        });
        expect(response.data).toBeFalsy();
    });
    it('updateRelationshipGroup_shouldSucceed', async () => {
        const response = await turmsClient.userService.updateRelationshipGroup({
            groupIndex: relationshipGroupIndex,
            newName: 'newGroupName'
        });
        expect(response.data).toBeFalsy();
    });
    it('moveRelatedUserToGroup_shouldSucceed', async () => {
        let response = await turmsClient.userService.moveRelatedUserToGroup({
            relatedUserId: '2',
            groupIndex: 1
        });
        expect(response.data).toBeFalsy();
        response = await turmsClient.userService.moveRelatedUserToGroup({
            relatedUserId: '2',
            groupIndex: 0
        });
        expect(response.data).toBeFalsy();
    });
    it('updateLocation_shouldSucceed', async () => {
        const response = await turmsClient.userService.updateLocation({
            latitude: 2,
            longitude: 2
        });
        expect(response.data).toBeFalsy();
    });
});

describe('Query', () => {
    it('queryUserProfiles_shouldReturnUserInfos', async () => {
        const response = await turmsClient.userService.queryUserProfiles({
            userIds: ['1']
        });
        const userInfos = response.data;
        expect(userInfos).toBeTruthy();
    });
    it('queryNearbyUsers_shouldReturnUsersInfos', async () => {
        const response = await turmsClient.userService.queryNearbyUsers({
            latitude: 1,
            longitude: 1
        });
        const users = response.data;
        expect(users).toBeTruthy();
    });
    it('queryOnlineStatusesRequest_shouldUsersOnlineStatus', async () => {
        const response = await turmsClient.userService.queryOnlineStatusesRequest({
            userIds: ['1']
        });
        const userStatuses = response.data;
        expect(userStatuses[0].userStatus).toEqual(userStatus);
    });
    it('queryRelationships_shouldReturnUserRelationshipsWithVersion', async () => {
        const response = await turmsClient.userService.queryRelationships({
            relatedUserIds: ['2']
        });
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
        const response = await turmsClient.userService.deleteRelationship({
            relatedUserId: '10'
        });
        expect(response.data).toBeFalsy();
    });
    it('deleteRelationship_shouldSucceed', async () => {
        const response = await turmsClient.userService.deleteRelationshipGroups({
            groupIndex: relationshipGroupIndex
        });
        expect(response.data).toBeFalsy();
    });
});

describe('Logout', () => {
    it('logout_shouldSucceed', async () => {
        const response = await turmsClient.userService.logout();
        expect(response.data).toBeFalsy();
    });
});