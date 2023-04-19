# User-related Features

## Related paths and models

* Admin API path: `/users`. For specific API details, please refer to the OpenAPI documentation
* Client Interface: Please refer to the `UserServiceController` class
* The underlying request model: please refer to the interface description file in the `https://github.com/turms-im/proto/tree/master/request/user` directory
* Configuration class: `im.turms.server.common.infra.property.env.service.business.user.UserProperties`

## User information function

| **Function**                        | **Function Description**                                     | **Related Configuration**                                    |
| :---------------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| Add User                            |                                                              | turms.service.user.activate-user-when-added                  |
| Delete User                         |                                                              | turms.service.user.delete-user-logically                     |
| Modify user profile                 | Users modify their own nickname, introduction, avatar URL    |                                                              |
| Get user profile                    | User view own or other user's profile                        |                                                              |
| Set user profile access permissions | Users can set access permissions for each personal profile. Access rights are: visible to everyone, visible to friends, visible only to yourself |                                                              |
| User permission group               | Administrators can give different permissions to different users | Configuration model: im.turms.service.domain.user.po.UserPermissionGroup |

## User Relationship Hosting

concept:

- Relationship: Relationship is divided into one-way relationship and two-way relationship. One-way relationship refers to: the owner of the relationship (relationship owner) has a specific relationship with the Related User (relationship person), such as "one-way friend" (allowing the other party to send messages and friend requests) or "blocking User" (prohibit the other party from sending messages, friend requests, etc.). The establishment of a one-way relationship does not require permission authentication. A two-way relationship means that user A has a one-way relationship with user B, and user B has a one-way relationship with user A. For example, user A blocks user B, and user B can specify not to block user A.
- Related Users: Refers to users who have a one-way or two-way relationship (designate the other party as a friend or block the user). Two users are Strangers if they don't have a relationship of either kind.
- Relational person group: A relational person group consists of a group name and a group of related persons, and each relationship must exist in at least one related person group. If the client does not perform a group operation on the relationship when creating the relationship, the relationship will be put into the user's default relationship group. Therefore, special attention should be paid to the fact that there can be both "friends" and "blocked" users in "a related person group". Of course, you can restrict a group to only have a certain type of related person through business restrictions.

Additional supplement: In fact, there is no such concept as "friend/block user" in the Turms domain model, and its essence is a bool called "isBlocked".

| **<div style="min-width:100px">Function</div>**        | **Function Description**                                     | **Related Configuration**                                    |
| :----------------------------------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| Get relationship                                       | Get the relationship owned by the current user according to optional filtering (such as specifying user ID, "whether it is a contact", "whether it is a friend/blocked user", etc.) and grouping conditions |                                                              |
| Add a relationship (+initiate a friend request)        | ①If adding a relationship as a "friend", according to your customized Turms server configuration, the user can either directly add a "friend" relationship, or initiate a friend request first , the operation of adding a "friend" relationship will not be performed automatically until the requestee's approval is obtained. <br />② If you add a related person whose relationship is "block user", no approval is required and it will take effect directly. Users will no longer receive any messages or requests from blocked users. | turms.service.user.friend-request.content-limit<br />turms.service.user.friend-request.delete-expired-requests-when-cron-triggered<br />turms.service.user.friend -request.allow-send-request-after-declined-or-ignored-or-expired<br />turms.service.user.friend-request.friend-request-expire-after-seconds<br />turms.service .user.friend-request.expired-user-friend-requests-cleanup-cron<br />turms.service.user.friend-request.delete-expired-requests-when-cron-triggered |
| Approve/Reject Friend Request                          | User can approve or reject friend request. If you agree to the friend request, the two will establish a two-way "friend" relationship |                                                              |
| Delete Relation                                        | According to the optional deletion conditions (such as "is/is not a relation", "is a friend/block user"), delete a certain type of relation or a designated relation. | deleteTwoSidedRelationships                                  |
| Modify the relationship with related parties           | Modify user relationship (friend/block user) information. When modifying the relationship to "friend", you need to send a friend request by default (you can cancel this step) |                                                              |
| Create relational person group                         | When creating a group, you can specify the group name and the relational person to be added at the same time. The same person can be added to multiple groups |                                                              |
| Delete related group                                   | Delete the related group, and you can choose whether to transfer the related people in the deleted related group to other groups (if not specified, it will be assigned to "default group" by default) |                                                              |
| Rename Relationship Group                              | Rename Relationship Group                                    |                                                              |
| Obtain the user's own related person group information | Get the user's own related person group information          |                                                              |
| Add a relation to a group                              | Add/move a relation to a relation group. If the group does not exist, the operation fails |                                                              |
| Delete a related person from a group                   | Delete a related person from a related person group          |                                                              |

## GPS
Configuration class: `im.turms.server.common.infra.property.env.common.location.LocationProperties`

| **Function**         | **Function Description**                                     | **Related Configuration**                                    |
| -------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| User location record | Periodically record user location                            | turms.location.enabled<br />turms.location.treat-user-id-and-device-type-as-unique-user |
| People Nearby        | Search for other nearby users based on current real-time coordinates | turms.location.users-nearby-request.default-max-available-nearby-users-number<br />turms.location.users-nearby-request. default-max-distance-meters<br />turms.location.users-nearby-request.max-available-users-nearby-number-limit<br />turms.location.users-nearby-request.max-distance- meters |

## Statistics function

Configuration class: `im.turms.server.common.infra.property.env.service.env.StatisticsProperties`

Although Turms provides some basic statistical functions, it is recommended that users collect various statistical data through cloud services, such as Amazon CloudWatch.

| **Function**           | **Function Description**                                     | **Related Configuration**                                    |
| ---------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| Online user statistics | The Master node in the Turms cluster will regularly record the number of online users in the cluster in the form of logs | turms.service.statistics.log-online-users-number<br />turms.service.statistics. online-users-number-logging-cron |