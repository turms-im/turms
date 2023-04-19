# Group-related Features

Types of group members include: group owner, administrator, ordinary member, visitor, anonymous visitor

## Related paths and models

* Admin API path: `/groups`. For specific API details, please refer to the OpenAPI documentation
* Client Interface: Please refer to the `GroupServiceController` class.
* The underlying request model: please refer to the interface description file in the `https://github.com/turms-im/proto/tree/master/request/group` directory
* Configuration class: `im.turms.server.common.infra.property.env.service.business.group.GroupProperties`

## function list

| **<div style="min-width:100px">Function</div>** | **Description**                                              | **Related configuration attribute name**                     |
| :---------------------------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| New Group                                       | New Group                                                    | turms.service.group.activate-group-when-created              |
| The group owner dismisses the group             | The group owner can dismiss the group                        | turms.service.group.delete-group-logically-by-default        |
| Actively withdraw from the group                | Except for the group owner, other users can actively withdraw from the group. The group owner needs to transfer the group to other group members before they can withdraw from the group |                                                              |
| Group owner transfer group                      | The group owner can transfer the owner authority of the group to other members in the group. After the transfer, the transferred person becomes the new group owner, and the original group owner becomes an ordinary member. The group owner can also choose to quit the group directly while transferring |                                                              |
| Modify group information                        | Support group name, group avatar, group introduction, group notification, group type and other fields |                                                              |
| Group ban                                       | Ordinary members of the group cannot send messages during the mute period, only the group owner and administrator can send messages |                                                              |
| Get group information                           | Find groups based on filter conditions (such as group ID)    |                                                              |
| Add group members                               | Add group members                                            |                                                              |
| Send invitation to join the group               | Group members with the role of invitation permission can send invitation to the specified user | turms.service.group.invitation.content-limit<br />turms.service.group.invitation.expire-after- seconds<br />turms.service.group.invitation.expired-invitations-cleanup-cron<br />turms.service.group.invitation.delete-expired-invitations-when-cron-triggered<br /> |
| Cancel the invitation to join the group         | The group owner, administrator and initiator of the invitation to join the group can cancel the invitation to join the group | turms.service.group.invitation.allow-recall-pending-invitation-by-owner-and-manager |
| Send group request                              |                                                              | turms.service.group.join-request.content-limit<br />turms.service.group.join-request.expire-after-seconds<br />turms.service.group.join -request.expired-join-requests-cleanup-cron<br />turms.service.group.join-request.delete-expired-join-requests-when-cron-triggered |
| Cancel group join request                       |                                                              | turms.service.group.join-request.allow-recall-join-request-sent-by-oneself |
| Set group entry questions                       | For groups whose group entry policy is "join after the group entry requester answers the questions correctly", group owners and administrators can set group entry questions. There can be multiple questions for entering the group, and one question can have multiple answers | turms.service.group.question.answer-content-limit<br />turms.service.group.question.max-answer-count<br />turms .service.group.question.question-content-limit |
| Delete group entry question                     | Delete group entry question                                  |                                                              |
| Remove group members                            | Group owners and administrators can remove group members, and administrators cannot remove group owners and other administrators |                                                              |
| Update group member information                 | According to the corresponding "group type", group members with specified roles can modify the member information of other group members (for example: the group owner assigns administrator roles to group members) |                                                              |
| Muting group members                            | Muted users can be in the group, but cannot send messages    |                                                              |
| Group member coordinates sharing in real time   | Group members can share their coordinates with other group members in real time |                                                              |
| Group Blacklist                                 | After a user is blacklisted, he will no longer be able to enter the group. If the blocked user is a current group member before being blocked, the user will be automatically removed from the group member list after being blocked |                                                              |

## Group type configuration

In terms of group configuration, Turms uses the concept of "group types". By default, Turms provides a general group type, and you can also add, delete, modify and query the "group type" to meet your customized group type needs.

Corresponding admin API: `/groups/types`. For specific API details, please refer to the OpenAPI documentation
Corresponding configuration model: `im.turms.service.domain.group.po.GroupType`

### Configuration list

| **Attribute**                              | **Description**                                              | **Configuration attribute name** |
| :----------------------------------------- | ------------------------------------------------------------ | -------------------------------- |
| Maximum number of group members            | Valid value is 1~∞                                           | groupSizeLimit                   |
| Group Invitation Policy                    | Support configuration:<br />①Only the group owner can invite: `OWNER`, `OWNER_REQUIRING_APPROVAL`;<br />②The group owner + administrator can invite: `OWNER_MANAGER`, `OWNER_MANAGER_REQUIRING_APPROVAL`;< br />③Group owner + administrator and group members can invite: `OWNER_MANAGER_MEMBER`, `OWNER_MANAGER_MEMBER_REQUIRING_APPROVAL`;<br />④Everyone can invite: `ALL`, `ALL_REQUIRING_APPROVAL` | invitationStrategy               |
| Invitee's Consent Mode                     | Support configuration:<br />①The invitee's consent is required: the inviter sends an invitation to the invitee. If the invitee agrees to the invitation, it will automatically join the group: the strategy with `_REQUIRING_APPROVAL`;<br />②The invitee's consent is not required: the inviter is prohibited from sending invitations to the invitee. The inviter can directly add the invitee to the group: strategy without `_REQUIRING_APPROVAL` | invitationStrategy               |
| Group Joining Policy                       | Supported configuration:<br />①After the group owner or administrator approves the group joining request, the group requester can join: `JOIN_REQUEST`;<br />②After the group joining requester answers the questions correctly , automatically join: `QUESTION`;<br />③Allow unblocked users to actively join:`MEMBERSHIP_REQUEST`;<br />④No user is allowed to actively join, the group owner or administrator needs to send an invitation or directly pull Entering the group: `INVITATION` | joinStrategy                     |
| Group information update strategy          | Supported configuration:<br />①Only the group owner can modify;<br />②Group owner + administrator can modify;<br />③Group owner+administrator+group members can modify;<br /> br />④ Everyone can modify | groupInfoUpdateStrategy          |
| Group member information update strategy   | The group owner can modify the member information of everyone in the group, and the administrator can only modify the member information of ordinary members in the group | memberInfoUpdateStrategy         |
| Guest Speak                                | Prohibited, Allowed                                          | guestSpeakable                   |
| Group members modify their own information | Can be prohibited, allowed                                   | selfInfoUpdatable                |
| Group message read receipt                 | Can be turned on and off                                     | enableReadReceipt                |
| Modify sent messages                       | Can be turned on and off                                     | messageEditable                  |

remind:

* There is no mutually exclusive relationship between the above "invitation policy", "invitee consent mode" and "group policy", and they are all compatible with each other, so developers can match them according to their own application scenarios .

* If the administrator modifies the invitation policy or joining policy of a group type, which leads to a change in the policy corresponding to the group, the data corresponding to the old policy will be archived and will not be deleted by the system. Authorized users can still delete, modify and query these data.

  For example, a group originally allowed new users to join the group based on the policy of "approving group entry requests", and the group has received some group entry requests. If the system administrator (note: users do not have permission to modify the group type) modify the group policy to "question-and-answer based" policy to allow new users to join the group, then the previously received request to join the group will not be deleted by the system. When the group administrator tries to approve these group entry requests, the server will also notify the group policy of the change and reject the approval. But group administrators can still delete, modify and query these group requests.

  In addition, some users may think that the group policy of Turms is more complicated, but this kind of "complexity" has nothing to do with users. Users only need to configure according to their own application scenarios. It is very simple to use, just the development of Turms It is more complicated to implement these dynamic combination strategies.

* We have no plan to support the feature of "users block groups to refuse to receive group invitations and be pulled into groups".

## Scene introduction

### User joins a group

1. The client queries the group information of the specified group through `turmsClient.groupService.queryGroups(...)`.

2. Obtain group type information based on the relationship between the local hard-coded group type ID and group type information.

   Replenish:

    * Here, the client does not support dynamic query of group type information because the group type of most applications is fixed, and there is no need to dynamically pull information.
    * If your application only uses one group type, you can directly hard-code the group type information on the client side, skip steps ① and ②, and go directly to the next step.

3. According to the group entry policy in the group type information, determine which client API needs to be called to join the group:

    * If it is `JOIN_REQUEST` policy, you need to call `turmsClient.groupService.createJoinRequest(...)` to send the request to join the group, and wait for the approval of the group administrator.
    * If it is `QUESTION` strategy, you need to call `turmsClient.groupService.queryGroupJoinQuestions(...)` to query group questions, and then use `turmsClient.groupService.answerGroupQuestions(...)` to answer group questions, when the score reaches After the group administrator sets the entry threshold, you can automatically join the group.
    * If it is `MEMBERSHIP_REQUEST` policy, call `turmsClient.groupService.joinGroup(...)` to directly join the group without any approval.
    * If it is `INVITATION` strategy, you need to wait for the group administrator to send the current user an invitation to join the group.