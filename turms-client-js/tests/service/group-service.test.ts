import Constants from '../helper/constants';
import TurmsClient from '../../src/turms-client';
import ResponseStatusCode from '../../src/model/response-status-code';
import { GroupMemberRole } from '../../src/model/proto/constant/group_member_role';

const GROUP_MEMBER_ID = '3';
const GROUP_INVITATION_INVITEE = '4';
const GROUP_SUCCESSOR = '1';
const GROUP_BLOCKED_USER_ID = '5';
let turmsClient: TurmsClient;
let groupId: string;
let groupJoinQuestionId: string;
let groupJoinRequestId: string;
let groupInvitationId: string;

beforeAll(async () => {
    turmsClient = new TurmsClient(Constants.WS_URL);
    await turmsClient.userService.login('1', '123');
});

afterAll(async () => {
    await turmsClient.userService.logout();
});

describe('Constructor', () => {
    it('Constructor', () => {
        expect(turmsClient.groupService).toBeTruthy();
    });
});

describe('Create', () => {
    it('createGroup_shouldReturnGroupId', async () => {
        const response = await turmsClient.groupService.createGroup('name', 'intro', 'announcement', 10);
        groupId = response.data;
        expect(groupId).toBeTruthy();
    });
    it('addGroupJoinQuestion_shouldReturnQuestionId', async () => {
        const response = await turmsClient.groupService.addGroupJoinQuestion(groupId, 'question', ['answer1', 'answer2'], 10);
        groupJoinQuestionId = response.data;
        expect(groupJoinQuestionId).toBeTruthy();
    });
    it('createJoinRequest_shouldReturnJoinRequestId', async () => {
        const response = await turmsClient.groupService.createJoinRequest(groupId, 'content');
        groupJoinRequestId = response.data;
        expect(groupJoinRequestId).toBeTruthy();
    });
    it('addGroupMember_shouldSucceed', async () => {
        const response = await turmsClient.groupService.addGroupMember(groupId, GROUP_MEMBER_ID, 'name', GroupMemberRole.MEMBER);
        expect(response.data).toBeFalsy();
    });
    it('blockUser_shouldSucceed', async () => {
        const response = await turmsClient.groupService.blockUser(groupId, GROUP_BLOCKED_USER_ID);
        expect(response.data).toBeFalsy();
    });
    it('createInvitation_shouldReturnInvitationId', async () => {
        const response = await turmsClient.groupService.createInvitation(groupId, GROUP_INVITATION_INVITEE, 'content');
        groupInvitationId = response.data;
        expect(groupInvitationId).toBeTruthy();
    });
});

describe('Update', () => {
    it('updateGroup_shouldSucceed', async () => {
        const response = await turmsClient.groupService.updateGroup(groupId, 'name', 'intro', 'announcement', 10);
        expect(response.data).toBeFalsy();
    });
    it('muteGroup_shouldSucceed', async () => {
        const response = await turmsClient.groupService.muteGroup(groupId, new Date());
        expect(response.data).toBeFalsy();
    });
    it('unmuteGroup_shouldSucceed', async () => {
        const response = await turmsClient.groupService.unmuteGroup(groupId);
        expect(response.data).toBeFalsy();
    });
    it('updateGroupJoinQuestion_shouldSucceed', async () => {
        const response = await turmsClient.groupService.updateGroupJoinQuestion(groupId, 'new-question', ['answer']);
        expect(response.data).toBeFalsy();
    });
    it('updateGroupMemberInfo_shouldSucceed', async () => {
        const response = await turmsClient.groupService.updateGroupMemberInfo(groupId, GROUP_MEMBER_ID, 'myname');
        expect(response.data).toBeFalsy();
    });
    it('muteGroupMember_shouldSucceed', async () => {
        const response = await turmsClient.groupService.muteGroupMember(groupId, GROUP_MEMBER_ID, new Date(new Date().getTime() + 100000));
        expect(response.data).toBeFalsy();
    });
    it('unmuteGroupMember_shouldSucceed', async () => {
        const response = await turmsClient.groupService.unmuteGroupMember(groupId, GROUP_MEMBER_ID);
        expect(response.data).toBeFalsy();
    });
});

describe('Query', () => {
    it('queryGroup_shouldReturnGroupWithVersion', async () => {
        const response = await turmsClient.groupService.queryGroup(groupId);
        const groupWithVersion = response.data;
        expect(groupWithVersion.group.id).toEqual(groupId);
    });
    it('queryJoinedGroupIds_shouldEqualNewGroupId', async () => {
        const response = await turmsClient.groupService.queryJoinedGroupIds();
        const joinedGroupIdsWithVersion = response.data;
        expect(joinedGroupIdsWithVersion.ids).toContain(groupId);
    });
    it('queryJoinedGroupInfos_shouldEqualNewGroupId', async () => {
        const response = await turmsClient.groupService.queryJoinedGroupInfos();
        const groupWithVersion = response.data;
        const groupIds = groupWithVersion.groups.map(group => group.id);
        expect(groupIds).toContain(groupId);
    });
    it('queryBlockedUserIds_shouldEqualBlockedUserId', async () => {
        const response = await turmsClient.groupService.queryBlockedUserIds(groupId);
        const blockedUserIdsWithVersion = response.data;
        expect(blockedUserIdsWithVersion.ids[0]).toEqual(GROUP_BLOCKED_USER_ID);
    });
    it('queryBlockedUserInfos_shouldEqualBlockedUserId', async () => {
        const response = await turmsClient.groupService.queryBlockedUserInfos(groupId);
        const usersInfosWithVersion = response.data;
        expect(usersInfosWithVersion.userInfos[0].id).toEqual(GROUP_BLOCKED_USER_ID);
    });
    it('queryInvitationsByGroupId_shouldEqualNewInvitationId', async () => {
        const response = await turmsClient.groupService.queryInvitationsByGroupId(groupId);
        const groupInvitationsWithVersion = response.data;
        expect(groupInvitationsWithVersion.groupInvitations[0].id).toEqual(groupInvitationId);
    });
    it('queryJoinRequests_shouldEqualNewJoinRequestId', async () => {
        const response = await turmsClient.groupService.queryJoinRequests(groupId);
        const groupJoinRequestsWithVersion = response.data;
        expect(groupJoinRequestsWithVersion.groupJoinRequests[0].id).toEqual(groupJoinRequestId);
    });
    it('queryGroupJoinQuestionsRequest_shouldEqualNewGroupQuestionId', async () => {
        const response = await turmsClient.groupService.queryGroupJoinQuestionsRequest(groupId, true);
        const groupJoinQuestionsWithVersion = response.data;
        expect(groupJoinQuestionsWithVersion.groupJoinQuestions[0].id).toEqual(groupJoinQuestionId);
    });
    it('queryGroupMembers_shouldEqualNewMemberId', async () => {
        const response = await turmsClient.groupService.queryGroupMembers(groupId, true);
        const groupMembersWithVersion = response.data;
        expect(groupMembersWithVersion.groupMembers[1].userId).toEqual(GROUP_MEMBER_ID);
    });
    it('queryGroupMembersByMemberIds_shouldEqualNewMemberId', async () => {
        const response = await turmsClient.groupService.queryGroupMembersByMemberIds(groupId, [GROUP_MEMBER_ID], true);
        const groupMembersWithVersion = response.data;
        expect(groupMembersWithVersion.groupMembers[0].userId).toEqual(GROUP_MEMBER_ID);
    });
    it('answerGroupQuestions_shouldReturnAnswerResult', async () => {
        const idsAndAnswer = {};
        idsAndAnswer[groupJoinQuestionId] = 'answer';
        try {
            const response = await turmsClient.groupService.answerGroupQuestions(idsAndAnswer);
            const answerResult = response.data;
            const isCorrect = answerResult.questionIds.indexOf(groupJoinQuestionId) >= 0;
            expect(isCorrect).toEqual(true);
        } catch (e) {
            expect(e.code).toEqual(ResponseStatusCode.MEMBER_CANNOT_ANSWER_GROUP_QUESTION);
        }
    });
});

describe('Delete', () => {
    it('removeGroupMember_shouldSucceed', async () => {
        const response = await turmsClient.groupService.removeGroupMember(groupId, GROUP_MEMBER_ID);
        expect(response.data).toBeFalsy();
    });
    it('unblockUser_shouldSucceed', async () => {
        const response = await turmsClient.groupService.unblockUser(groupId, GROUP_BLOCKED_USER_ID);
        expect(response.data).toBeFalsy();
    });
    it('deleteInvitation_shouldSucceedOrThrowDisabledFunction', async () => {
        try {
            const response = await turmsClient.groupService.deleteInvitation(groupInvitationId);
            expect(response.data).toBeFalsy();
        } catch (e) {
            expect(e.code).toEqual(ResponseStatusCode.RECALL_NOT_PENDING_GROUP_INVITATION);
        }
    });
    it('deleteInvitation_shouldSucceedOrThrowDisabledFunction', async () => {
        try {
            const response = await turmsClient.groupService.deleteJoinRequest(groupJoinRequestId);
            expect(response.data).toBeFalsy();
        } catch (e) {
            expect(e.code).toEqual(ResponseStatusCode.RECALL_NOT_PENDING_GROUP_INVITATION);
        }
    });
    it('quitGroup_shouldSucceed', async () => {
        const response = await turmsClient.groupService.quitGroup(groupId, null, false);
        expect(response.data).toBeFalsy();
    });
    it('deleteGroup_shouldSucceed', async () => {
        const createGroupResponse = await turmsClient.groupService.createGroup('readyToDelete');
        const readyToDeleteGroupId = createGroupResponse.data;
        const deleteGroupResponse = await turmsClient.groupService.deleteGroup(readyToDeleteGroupId);
        expect(deleteGroupResponse.data).toBeFalsy();
    });
});

describe('Update - Last', () => {
    it('transferOwnership_shouldSucceed', async () => {
        const response = await turmsClient.groupService.transferOwnership(groupId, GROUP_SUCCESSOR, true);
        expect(response.data).toBeFalsy();
    });
});