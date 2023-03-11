import Constants from '../helper/constants';
import TurmsClient from '../../src/turms-client';
import ResponseStatusCode from '../../src/model/response-status-code';
import { GroupMemberRole } from '../../src/model/proto/constant/group_member_role';
import ResponseError from '../../src/error/response-error';

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
    await turmsClient.userService.login({
        userId: '1',
        password: '123'
    });
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
        const response = await turmsClient.groupService.createGroup({
            name: 'name',
            intro: 'intro',
            announcement: 'announcement',
            minScore: 10
        });
        groupId = response.data;
        expect(groupId).toBeTruthy();
    });
    it('addGroupJoinQuestions_shouldReturnQuestionIds', async () => {
        const response = await turmsClient.groupService.addGroupJoinQuestions({
            groupId,
            questions: [{
                question: 'question',
                answers: ['answer1', 'answer2'],
                score: 10
            }]
        });
        groupJoinQuestionId = response.data[0];
        expect(groupJoinQuestionId).toBeTruthy();
    });
    it('createJoinRequest_shouldReturnJoinRequestId', async () => {
        const response = await turmsClient.groupService.createJoinRequest({
            groupId,
            content: 'content'
        });
        groupJoinRequestId = response.data;
        expect(groupJoinRequestId).toBeTruthy();
    });
    it('addGroupMembers_shouldSucceed', async () => {
        const response = await turmsClient.groupService.addGroupMembers({
            groupId,
            userIds: [GROUP_MEMBER_ID],
            name: 'name',
            role: GroupMemberRole.MEMBER
        });
        expect(response.data).toBeFalsy();
    });
    it('blockUser_shouldSucceed', async () => {
        const response = await turmsClient.groupService.blockUser({
            groupId,
            userId: GROUP_BLOCKED_USER_ID
        });
        expect(response.data).toBeFalsy();
    });
    it('createInvitation_shouldReturnInvitationId', async () => {
        const response = await turmsClient.groupService.createInvitation({
            groupId,
            inviteeId: GROUP_INVITATION_INVITEE,
            content: 'content'
        });
        groupInvitationId = response.data;
        expect(groupInvitationId).toBeTruthy();
    });
});

describe('Update', () => {
    it('updateGroup_shouldSucceed', async () => {
        const response = await turmsClient.groupService.updateGroup({
            groupId,
            name: 'name',
            intro: 'intro',
            announcement: 'announcement',
            minScore: 10
        });
        expect(response.data).toBeFalsy();
    });
    it('muteGroup_shouldSucceed', async () => {
        const response = await turmsClient.groupService.muteGroup({
            groupId,
            muteEndDate: new Date()
        });
        expect(response.data).toBeFalsy();
    });
    it('unmuteGroup_shouldSucceed', async () => {
        const response = await turmsClient.groupService.unmuteGroup({
            groupId
        });
        expect(response.data).toBeFalsy();
    });
    it('updateGroupJoinQuestion_shouldSucceed', async () => {
        const response = await turmsClient.groupService.updateGroupJoinQuestion({
            questionId: groupJoinQuestionId,
            question: 'new-question',
            answers: ['answer']
        });
        expect(response.data).toBeFalsy();
    });
    it('updateGroupMemberInfo_shouldSucceed', async () => {
        const response = await turmsClient.groupService.updateGroupMemberInfo({
            groupId,
            memberId: GROUP_MEMBER_ID,
            name: 'myname'
        });
        expect(response.data).toBeFalsy();
    });
    it('muteGroupMember_shouldSucceed', async () => {
        const response = await turmsClient.groupService.muteGroupMember({
            groupId,
            memberId: GROUP_MEMBER_ID,
            muteEndDate: new Date(Date.now() + 100000)
        });
        expect(response.data).toBeFalsy();
    });
    it('unmuteGroupMember_shouldSucceed', async () => {
        const response = await turmsClient.groupService.unmuteGroupMember({
            groupId,
            memberId: GROUP_MEMBER_ID
        });
        expect(response.data).toBeFalsy();
    });
});

describe('Query', () => {
    it('queryGroups_shouldReturnGroups', async () => {
        const response = await turmsClient.groupService.queryGroups({
            groupIds: [groupId]
        });
        const groups = response.data;
        expect(groups[0].id).toEqual(groupId);
    });
    it('queryJoinedGroupIds_shouldEqualNewGroupId', async () => {
        const response = await turmsClient.groupService.queryJoinedGroupIds();
        const joinedGroupIdsWithVersion = response.data;
        expect(joinedGroupIdsWithVersion.longs).toContain(groupId);
    });
    it('queryJoinedGroupInfos_shouldEqualNewGroupId', async () => {
        const response = await turmsClient.groupService.queryJoinedGroupInfos();
        const groupWithVersion = response.data;
        const groupIds = groupWithVersion.groups.map(group => group.id);
        expect(groupIds).toContain(groupId);
    });
    it('queryBlockedUserIds_shouldEqualBlockedUserId', async () => {
        const response = await turmsClient.groupService.queryBlockedUserIds({
            groupId
        });
        const blockedUserIdsWithVersion = response.data;
        expect(blockedUserIdsWithVersion.longs[0]).toEqual(GROUP_BLOCKED_USER_ID);
    });
    it('queryBlockedUserInfos_shouldEqualBlockedUserId', async () => {
        const response = await turmsClient.groupService.queryBlockedUserInfos({
            groupId
        });
        const usersInfosWithVersion = response.data;
        expect(usersInfosWithVersion.userInfos[0].id).toEqual(GROUP_BLOCKED_USER_ID);
    });
    it('queryInvitationsByGroupId_shouldEqualNewInvitationId', async () => {
        const response = await turmsClient.groupService.queryInvitationsByGroupId({
            groupId
        });
        const groupInvitationsWithVersion = response.data;
        expect(groupInvitationsWithVersion.groupInvitations[0].id).toEqual(groupInvitationId);
    });
    it('queryJoinRequests_shouldEqualNewJoinRequestId', async () => {
        const response = await turmsClient.groupService.queryJoinRequests({
            groupId
        });
        const groupJoinRequestsWithVersion = response.data;
        expect(groupJoinRequestsWithVersion.groupJoinRequests[0].id).toEqual(groupJoinRequestId);
    });
    it('queryGroupJoinQuestions_shouldEqualNewGroupQuestionId', async () => {
        const response = await turmsClient.groupService.queryGroupJoinQuestions({
            groupId,
            withAnswers: true
        });
        const groupJoinQuestionsWithVersion = response.data;
        expect(groupJoinQuestionsWithVersion.groupJoinQuestions[0].id).toEqual(groupJoinQuestionId);
    });
    it('queryGroupMembers_shouldEqualNewMemberId', async () => {
        const response = await turmsClient.groupService.queryGroupMembers({
            groupId,
            withStatus: true
        });
        const groupMembersWithVersion = response.data;
        expect(groupMembersWithVersion.groupMembers[1].userId).toEqual(GROUP_MEMBER_ID);
    });
    it('queryGroupMembersByMemberIds_shouldEqualNewMemberId', async () => {
        const response = await turmsClient.groupService.queryGroupMembersByMemberIds({
            groupId,
            memberIds: [GROUP_MEMBER_ID],
            withStatus: true
        });
        const groupMembersWithVersion = response.data;
        expect(groupMembersWithVersion.groupMembers[0].userId).toEqual(GROUP_MEMBER_ID);
    });
    it('answerGroupQuestions_shouldReturnAnswerResult', async () => {
        const idAndAnswer = {};
        idAndAnswer[groupJoinQuestionId] = 'answer';
        try {
            const response = await turmsClient.groupService.answerGroupQuestions({
                questionIdToAnswer: idAndAnswer
            });
            const answerResult = response.data;
            const isCorrect = answerResult.questionIds.indexOf(groupJoinQuestionId) >= 0;
            expect(isCorrect).toEqual(true);
        } catch (e) {
            expect((e as ResponseError).code).toEqual(ResponseStatusCode.MEMBER_CANNOT_ANSWER_GROUP_QUESTION);
        }
    });
});

describe('Delete', () => {
    it('removeGroupMembers_shouldSucceed', async () => {
        const response = await turmsClient.groupService.removeGroupMembers({
            groupId,
            memberIds: [GROUP_MEMBER_ID]
        });
        expect(response.data).toBeFalsy();
    });
    it('unblockUser_shouldSucceed', async () => {
        const response = await turmsClient.groupService.unblockUser({
            groupId,
            userId: GROUP_BLOCKED_USER_ID
        });
        expect(response.data).toBeFalsy();
    });
    it('deleteInvitation_shouldSucceedOrThrowDisabledFunction', async () => {
        try {
            const response = await turmsClient.groupService.deleteInvitation({
                invitationId: groupInvitationId
            });
            expect(response.data).toBeFalsy();
        } catch (e) {
            expect((e as ResponseError).code).toEqual(ResponseStatusCode.RECALL_NOT_PENDING_GROUP_INVITATION);
        }
    });
    it('deleteInvitation_shouldSucceedOrThrowDisabledFunction', async () => {
        try {
            const response = await turmsClient.groupService.deleteJoinRequest({
                requestId: groupJoinRequestId
            });
            expect(response.data).toBeFalsy();
        } catch (e) {
            expect((e as ResponseError).code).toEqual(ResponseStatusCode.RECALL_NOT_PENDING_GROUP_INVITATION);
        }
    });
    it('quitGroup_shouldSucceed', async () => {
        const response = await turmsClient.groupService.quitGroup({
            groupId,
            quitAfterTransfer: false
        });
        expect(response.data).toBeFalsy();
    });
    it('deleteGroup_shouldSucceed', async () => {
        const createGroupResponse = await turmsClient.groupService.createGroup({
            name: 'readyToDelete'
        });
        const readyToDeleteGroupId = createGroupResponse.data;
        const deleteGroupResponse = await turmsClient.groupService.deleteGroup({
            groupId: readyToDeleteGroupId
        });
        expect(deleteGroupResponse.data).toBeFalsy();
    });
});

describe('Update - Last', () => {
    it('transferOwnership_shouldSucceed', async () => {
        const response = await turmsClient.groupService.transferOwnership({
            groupId,
            successorId: GROUP_SUCCESSOR,
            quitAfterTransfer: true
        });
        expect(response.data).toBeFalsy();
    });
});