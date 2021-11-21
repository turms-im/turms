import Constants from '../helper/constants';
import TurmsClient from '../../src/turms-client';
import TurmsStatusCode from '../../src/model/turms-status-code'
import {GroupMemberRole} from '../../src/model/proto/constant/group_member_role';

const GROUP_MEMBER_ID = '3';
const GROUP_INVITATION_INVITEE = '4';
const GROUP_SUCCESSOR = '1';
const GROUP_BLOCKED_USER_ID = '5';
let turmsClient: TurmsClient;
let groupId;
let groupJoinQuestionId;
let groupJoinRequestId;
let groupInvitationId;

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
    })
});

describe('Create', () => {
    it('createGroup_shouldReturnGroupId', async () => {
        groupId = await turmsClient.groupService.createGroup('name', 'intro', 'announcement', 10)
        expect(groupId).toBeTruthy();
    });
    it('addGroupJoinQuestion_shouldReturnQuestionId', async () => {
        groupJoinQuestionId = await turmsClient.groupService.addGroupJoinQuestion(groupId, 'question', ['answer1', 'answer2'], 10);
        expect(groupJoinQuestionId).toBeTruthy();
    });
    it('createJoinRequest_shouldReturnJoinRequestId', async () => {
        groupJoinRequestId = await turmsClient.groupService.createJoinRequest(groupId, 'content');
        expect(groupJoinRequestId).toBeTruthy();
    });
    it('addGroupMember_shouldSucceed', async () => {
        const result = await turmsClient.groupService.addGroupMember(groupId, GROUP_MEMBER_ID, 'name', GroupMemberRole.MEMBER);
        expect(result).toBeFalsy();
    });
    it('blockUser_shouldSucceed', async () => {
        const result = await turmsClient.groupService.blockUser(groupId, GROUP_BLOCKED_USER_ID);
        expect(result).toBeFalsy();
    });
    it('createInvitation_shouldReturnInvitationId', async () => {
        groupInvitationId = await turmsClient.groupService.createInvitation(groupId, GROUP_INVITATION_INVITEE, 'content');
        expect(groupInvitationId).toBeTruthy();
    });
});

describe('Update', () => {
    it('updateGroup_shouldSucceed', async () => {
        const result = await turmsClient.groupService.updateGroup(groupId, 'name', 'intro', 'announcement', 10);
        expect(result).toBeFalsy();
    });
    it('muteGroup_shouldSucceed', async () => {
        const result = await turmsClient.groupService.muteGroup(groupId, new Date());
        expect(result).toBeFalsy();
    });
    it('unmuteGroup_shouldSucceed', async () => {
        const result = await turmsClient.groupService.unmuteGroup(groupId);
        expect(result).toBeFalsy();
    });
    it('updateGroupJoinQuestion_shouldSucceed', async () => {
        const result = await turmsClient.groupService.updateGroupJoinQuestion(groupId, 'new-question', ['answer']);
        expect(result).toBeFalsy();
    });
    it('updateGroupMemberInfo_shouldSucceed', async () => {
        const result = await turmsClient.groupService.updateGroupMemberInfo(groupId, GROUP_MEMBER_ID, 'myname');
        expect(result).toBeFalsy();
    });
    it('muteGroupMember_shouldSucceed', async () => {
        const result = await turmsClient.groupService.muteGroupMember(groupId, GROUP_MEMBER_ID, new Date(new Date().getTime() + 100000));
        expect(result).toBeFalsy();
    });
    it('unmuteGroupMember_shouldSucceed', async () => {
        const result = await turmsClient.groupService.unmuteGroupMember(groupId, GROUP_MEMBER_ID);
        expect(result).toBeFalsy();
    });
});

describe('Query', () => {
    it('queryGroup_shouldReturnGroupWithVersion', async () => {
        const groupWithVersion = await turmsClient.groupService.queryGroup(groupId);
        expect(groupWithVersion.group.id).toEqual(groupId);
    });
    it('queryJoinedGroupIds_shouldEqualNewGroupId', async () => {
        const joinedGroupIdsWithVersion = await turmsClient.groupService.queryJoinedGroupIds();
        expect(joinedGroupIdsWithVersion.ids).toContain(groupId);
    });
    it('queryJoinedGroupInfos_shouldEqualNewGroupId', async () => {
        const groupWithVersion = await turmsClient.groupService.queryJoinedGroupInfos();
        let groupIds = groupWithVersion.groups.map(group => group.id);
        expect(groupIds).toContain(groupId);
    });
    it('queryBlockedUserIds_shouldEqualBlockedUserId', async () => {
        const blockedUserIdsWithVersion = await turmsClient.groupService.queryBlockedUserIds(groupId);
        expect(blockedUserIdsWithVersion.ids[0]).toEqual(GROUP_BLOCKED_USER_ID);
    });
    it('queryBlockedUserInfos_shouldEqualBlockedUserId', async () => {
        const usersInfosWithVersion = await turmsClient.groupService.queryBlockedUserInfos(groupId);
        expect(usersInfosWithVersion.userInfos[0].id).toEqual(GROUP_BLOCKED_USER_ID);
    });
    it('queryInvitationsByGroupId_shouldEqualNewInvitationId', async () => {
        const groupInvitationsWithVersion = await turmsClient.groupService.queryInvitationsByGroupId(groupId);
        expect(groupInvitationsWithVersion.groupInvitations[0].id).toEqual(groupInvitationId);
    });
    it('queryJoinRequests_shouldEqualNewJoinRequestId', async () => {
        const groupJoinRequestsWithVersion = await turmsClient.groupService.queryJoinRequests(groupId);
        expect(groupJoinRequestsWithVersion.groupJoinRequests[0].id).toEqual(groupJoinRequestId);
    });
    it('queryGroupJoinQuestionsRequest_shouldEqualNewGroupQuestionId', async () => {
        const groupJoinQuestionsWithVersion = await turmsClient.groupService.queryGroupJoinQuestionsRequest(groupId, true);
        expect(groupJoinQuestionsWithVersion.groupJoinQuestions[0].id).toEqual(groupJoinQuestionId);
    });
    it('queryGroupMembers_shouldEqualNewMemberId', async () => {
        const groupMembersWithVersion = await turmsClient.groupService.queryGroupMembers(groupId, true);
        expect(groupMembersWithVersion.groupMembers[1].userId).toEqual(GROUP_MEMBER_ID);
    });
    it('queryGroupMembersByMemberIds_shouldEqualNewMemberId', async () => {
        const groupMembersWithVersion = await turmsClient.groupService.queryGroupMembersByMemberIds(groupId, [GROUP_MEMBER_ID], true);
        expect(groupMembersWithVersion.groupMembers[0].userId).toEqual(GROUP_MEMBER_ID);
    });
    it('answerGroupQuestions_shouldReturnAnswerResult', async () => {
        const idsAndAnswer = {};
        idsAndAnswer[groupJoinQuestionId] = 'answer';
        try {
            const answerResult = await turmsClient.groupService.answerGroupQuestions(idsAndAnswer);
            const isCorrect = answerResult.questionIds.indexOf(groupJoinQuestionId) >= 0;
            expect(isCorrect).toEqual(true);
        } catch (e) {
            expect(e.code).toEqual(TurmsStatusCode.MEMBER_CANNOT_ANSWER_GROUP_QUESTION);
        }
    });
});

describe('Delete', () => {
    it('removeGroupMember_shouldSucceed', async () => {
        const result = await turmsClient.groupService.removeGroupMember(groupId, GROUP_MEMBER_ID);
        expect(result).toBeFalsy();
    });
    it('unblockUser_shouldSucceed', async () => {
        const result = await turmsClient.groupService.unblockUser(groupId, GROUP_BLOCKED_USER_ID);
        expect(result).toBeFalsy();
    });
    it('deleteInvitation_shouldSucceedOrThrowDisabledFunction', async () => {
        try {
            expect(await turmsClient.groupService.deleteInvitation(groupInvitationId)).toBeTruthy();
        } catch (e) {
            expect(e.code).toEqual(TurmsStatusCode.RECALL_NOT_PENDING_GROUP_INVITATION);
        }
    });
    it('deleteInvitation_shouldSucceedOrThrowDisabledFunction', async () => {
        try {
            expect(await turmsClient.groupService.deleteJoinRequest(groupJoinRequestId)).toBeTruthy();
        } catch (e) {
            expect(e.code).toEqual(TurmsStatusCode.RECALL_NOT_PENDING_GROUP_INVITATION);
        }
    });
    it('quitGroup_shouldSucceed', async () => {
        const result = await turmsClient.groupService.quitGroup(groupId, null, false);
        expect(result).toBeFalsy();
    });
    it('deleteGroup_shouldSucceed', async () => {
        const readyToDeleteGroupId = await turmsClient.groupService.createGroup('readyToDelete');
        const result = await turmsClient.groupService.deleteGroup(readyToDeleteGroupId)
        expect(result).toBeFalsy();
    });
});

describe('Update - Last', () => {
    it('transferOwnership_shouldSucceed', async () => {
        const result = await turmsClient.groupService.transferOwnership(groupId, GROUP_SUCCESSOR, true);
        expect(result).toBeFalsy();
    });
});