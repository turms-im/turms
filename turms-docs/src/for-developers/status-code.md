# 状态码

共有两种状态码需要开发者了解，一种是`ResponseStatusCode`，另一种是`SessionCloseStatus`。下表内容不需要刻意记忆，只需要在遇见不认识的状态码时，懂得查询即可。

## ResponseStatusCode

ResponseStatusCode表明请求响应中的处理状态，类似HTTP的状态码。

每一个请求响应都会包含一个ResponseStatusCode。具体的状态码声明可查阅`turms-client-kotlin`项目下的`im.turms.client.model.ResponseStatusCode`类。

### 客户端独有状态码

客户端独有状态码不会出现在Turms服务端中，表明客户端请求在客户端本地就被拒绝执行。

| 类别     | 名称                                     | 状态码 | 含义 |
| -------- | ---------------------------------------- | ------ | ---- |
| 连接相关 | CONNECT_TIMEOUT                          | 1      |      |
| 请求相关 | INVALID_REQUEST                          | 100    |      |
|          | CLIENT_REQUESTS_TOO_FREQUENT             | 101    |      |
|          | REQUEST_TIMEOUT                          | 102    |      |
|          | ILLEGAL_ARGUMENT                         | 103    |      |
| 通知相关 | INVALID_NOTIFICATION                     | 200    |      |
|          | INVALID_RESPONSE                         | 201    |      |
| 会话相关 | CLIENT_SESSION_ALREADY_ESTABLISHED       | 300    |      |
|          | CLIENT_SESSION_HAS_BEEN_CLOSED           | 301    |      |
| 消息相关 | MESSAGE_IS_REJECTED                      | 400    |      |
| 存储相关 | QUERY_PROFILE_URL_TO_UPDATE_BEFORE_LOGIN | 500    |      |

### 通用状态码

| 类别                 | 名称                                                     | 状态码 | 含义 |
| -------------------- | -------------------------------------------------------- | ------ | ---- |
| 成功响应             | OK                                                       | 1000   |      |
|                      | NO_CONTENT                                               | 1001   |      |
|                      | ALREADY_UP_TO_DATE                                       | 1002   |      |
| 客户端请求错误       | INVALID_REQUEST_FROM_SERVER                              | 1100   |      |
|                      | CLIENT_REQUESTS_TOO_FREQUENT_FROM_SERVER                 | 1101   |      |
|                      | ILLEGAL_ARGUMENT_FROM_SERVER                             | 1102   |      |
|                      | RECORD_CONTAINS_DUPLICATE_KEY                            | 1103   |      |
|                      | REQUESTED_RECORDS_TOO_MANY                               | 1104   |      |
|                      | SEND_REQUEST_FROM_NON_EXISTING_SESSION                   | 1105   |      |
|                      | UNAUTHORIZED_REQUEST                                     | 1106   |      |
| 服务端错误           | SERVER_INTERNAL_ERROR                                    | 1200   |      |
|                      | SERVER_UNAVAILABLE                                       | 1201   |      |
| 用户登录相关错误     | UNSUPPORTED_CLIENT_VERSION                               | 2000   |      |
|                      | LOGIN_TIMEOUT                                            | 2010   |      |
|                      | LOGIN_AUTHENTICATION_FAILED                              | 2011   |      |
|                      | LOGGING_IN_USER_NOT_ACTIVE                               | 2012   |      |
|                      | LOGIN_FROM_FORBIDDEN_DEVICE_TYPE                         | 2013   |      |
| 用户会话相关错误     | SESSION_SIMULTANEOUS_CONFLICTS_DECLINE                   | 2100   |      |
|                      | SESSION_SIMULTANEOUS_CONFLICTS_NOTIFY                    | 2101   |      |
|                      | SESSION_SIMULTANEOUS_CONFLICTS_OFFLINE                   | 2102   |      |
|                      | CREATE_EXISTING_SESSION                                  | 2103   |      |
|                      | UPDATE_NON_EXISTING_SESSION_HEARTBEAT                    | 2104   |      |
| 用户位置相关错误     | USER_LOCATION_RELATED_FEATURES_ARE_DISABLED              | 2200   |      |
|                      | QUERYING_NEAREST_USERS_BY_SESSION_ID_IS_DISABLED         | 2201   |      |
| 用户信息相关错误     | UPDATE_INFO_OF_NON_EXISTING_USER                         | 2300   |      |
|                      | USER_PROFILE_NOT_FOUND                                   | 2301   |      |
|                      | PROFILE_REQUESTER_NOT_IN_CONTACTS_OR_BLOCKED             | 2302   |      |
|                      | PROFILE_REQUESTER_HAS_BEEN_BLOCKED                       | 2303   |      |
| 用户权限组相关错误   | QUERY_PERMISSION_OF_NON_EXISTING_USER                    | 2400   |      |
| 用户关系相关错误     | ADD_NOT_RELATED_USER_TO_GROUP                            | 2500   |      |
|                      | CREATE_EXISTING_RELATIONSHIP                             | 2501   |      |
| 用户好友请求相关错误 | REQUESTER_NOT_FRIEND_REQUEST_RECIPIENT                   | 2600   |      |
|                      | CREATE_EXISTING_FRIEND_REQUEST                           | 2601   |      |
|                      | FRIEND_REQUEST_SENDER_HAS_BEEN_BLOCKED                   | 2602   |      |
| 群组信息相关错误     | UPDATE_INFO_OF_NON_EXISTING_GROUP                        | 3000   |      |
|                      | NOT_OWNER_TO_UPDATE_GROUP_INFO                           | 3001   |      |
|                      | NOT_OWNER_OR_MANAGER_TO_UPDATE_GROUP_INFO                | 3002   |      |
|                      | NOT_MEMBER_TO_UPDATE_GROUP_INFO                          | 3003   |      |
| 群组类型相关错误     | NO_PERMISSION_TO_CREATE_GROUP_WITH_GROUP_TYPE            | 3100   |      |
|                      | CREATE_GROUP_WITH_NON_EXISTING_GROUP_TYPE                | 3101   |      |
| 群组所有权相关错误   | NOT_ACTIVE_USER_TO_CREATE_GROUP                          | 3200   |      |
|                      | NOT_OWNER_TO_TRANSFER_GROUP                              | 3201   |      |
|                      | NOT_OWNER_TO_DELETE_GROUP                                | 3202   |      |
|                      | SUCCESSOR_NOT_GROUP_MEMBER                               | 3203   |      |
|                      | OWNER_QUITS_WITHOUT_SPECIFYING_SUCCESSOR                 | 3204   |      |
|                      | MAX_OWNED_GROUPS_REACHED                                 | 3205   |      |
|                      | TRANSFER_NON_EXISTING_GROUP                              | 3206   |      |
| 群组入群问题相关错误 | NOT_OWNER_OR_MANAGER_TO_CREATE_GROUP_QUESTION            | 3300   |      |
|                      | NOT_OWNER_OR_MANAGER_TO_DELETE_GROUP_QUESTION            | 3301   |      |
|                      | NOT_OWNER_OR_MANAGER_TO_UPDATE_GROUP_QUESTION            | 3302   |      |
|                      | NOT_OWNER_OR_MANAGER_TO_ACCESS_GROUP_QUESTION_ANSWER     | 3303   |      |
|                      | CREATE_GROUP_QUESTION_FOR_INACTIVE_GROUP                 | 3304   |      |
|                      | CREATE_GROUP_QUESTION_FOR_GROUP_USING_JOIN_REQUEST       | 3305   |      |
|                      | CREATE_GROUP_QUESTION_FOR_GROUP_USING_INVITATION         | 3306   |      |
|                      | CREATE_GROUP_QUESTION_FOR_GROUP_USING_MEMBERSHIP_REQUEST | 3307   |      |
|                      | GROUP_QUESTION_ANSWERER_HAS_BEEN_BLOCKED                 | 3308   |      |
|                      | MEMBER_CANNOT_ANSWER_GROUP_QUESTION                      | 3309   |      |
|                      | ANSWER_INACTIVE_QUESTION                                 | 3310   |      |
|                      | ANSWER_QUESTION_OF_INACTIVE_GROUP                        | 3311   |      |
| 群组成员相关错误     | ADD_USER_TO_GROUP_REQUIRING_INVITATION                   | 3400   |      |
|                      | ADD_USER_TO_INACTIVE_GROUP                               | 3401   |      |
|                      | ADD_USER_WITH_ROLE_HIGHER_THAN_REQUESTER                 | 3402   |      |
|                      | ADD_BLOCKED_USER_TO_GROUP                                | 3403   |      |
|                      | ADD_BLOCKED_USER_TO_INACTIVE_GROUP                       | 3404   |      |
|                      | NOT_OWNER_OR_MANAGER_TO_REMOVE_GROUP_MEMBER              | 3405   |      |
|                      | NOT_OWNER_TO_REMOVE_GROUP_OWNER_OR_MANAGER               | 3406   |      |
|                      | NOT_OWNER_TO_UPDATE_GROUP_MEMBER_INFO                    | 3407   |      |
|                      | NOT_OWNER_OR_MANAGER_TO_UPDATE_GROUP_MEMBER_INFO         | 3408   |      |
|                      | NOT_MEMBER_TO_QUERY_MEMBER_INFO                          | 3409   |      |
| 群组黑名单相关错误   | NOT_OWNER_OR_MANAGER_TO_ADD_BLOCKED_USER                 | 3500   |      |
|                      | NOT_OWNER_OR_MANAGER_TO_REMOVE_BLOCKED_USER              | 3501   |      |
| 群组入群请求相关错误 | GROUP_JOIN_REQUEST_SENDER_HAS_BEEN_BLOCKED               | 3600   |      |
|                      | NOT_JOIN_REQUEST_SENDER_TO_RECALL_REQUEST                | 3601   |      |
|                      | NOT_OWNER_OR_MANAGER_TO_ACCESS_GROUP_REQUEST             | 3602   |      |
|                      | RECALL_NOT_PENDING_GROUP_JOIN_REQUEST                    | 3603   |      |
|                      | SEND_JOIN_REQUEST_TO_INACTIVE_GROUP                      | 3604   |      |
|                      | SEND_JOIN_REQUEST_TO_GROUP_USING_MEMBERSHIP_REQUEST      | 3605   |      |
|                      | SEND_JOIN_REQUEST_TO_GROUP_USING_INVITATION              | 3606   |      |
|                      | SEND_JOIN_REQUEST_TO_GROUP_USING_QUESTION                | 3607   |      |
|                      | RECALLING_GROUP_JOIN_REQUEST_IS_DISABLED                 | 3608   |      |
| 群组邀请相关错误     | GROUP_INVITER_NOT_MEMBER                                 | 3700   |      |
|                      | GROUP_INVITEE_ALREADY_GROUP_MEMBER                       | 3701   |      |
|                      | NOT_OWNER_OR_MANAGER_TO_RECALL_INVITATION                | 3702   |      |
|                      | NOT_OWNER_OR_MANAGER_TO_ACCESS_INVITATION                | 3703   |      |
|                      | NOT_OWNER_TO_SEND_INVITATION                             | 3704   |      |
|                      | NOT_OWNER_OR_MANAGER_TO_SEND_INVITATION                  | 3705   |      |
|                      | NOT_MEMBER_TO_SEND_INVITATION                            | 3706   |      |
|                      | INVITEE_HAS_BEEN_BLOCKED                                 | 3707   |      |
|                      | RECALLING_GROUP_INVITATION_IS_DISABLED                   | 3708   |      |
|                      | SEND_GROUP_INVITATION_TO_GROUP_NOT_REQUIRE_INVITATION    | 3709   |      |
|                      | RECALL_NOT_PENDING_GROUP_INVITATION                      | 3710   |      |
| 聊天会话相关错误     | UPDATING_TYPING_STATUS_IS_DISABLED                       | 4000   |      |
|                      | UPDATING_READ_DATE_IS_DISABLED                           | 4001   |      |
|                      | MOVING_READ_DATE_FORWARD_IS_DISABLED                     | 4002   |      |
| 消息发送相关错误     | MESSAGE_RECIPIENT_NOT_ACTIVE                             | 5000   |      |
|                      | MESSAGE_SENDER_NOT_IN_CONTACTS_OR_BLOCKED                | 5001   |      |
|                      | PRIVATE_MESSAGE_SENDER_HAS_BEEN_BLOCKED                  | 5002   |      |
|                      | GROUP_MESSAGE_SENDER_HAS_BEEN_BLOCKED                    | 5003   |      |
|                      | SEND_MESSAGE_TO_INACTIVE_GROUP                           | 5004   |      |
|                      | SEND_MESSAGE_TO_MUTED_GROUP                              | 5005   |      |
|                      | SENDING_MESSAGES_TO_ONESELF_IS_DISABLED                  | 5006   |      |
|                      | MUTED_MEMBER_SEND_MESSAGE                                | 5007   |      |
|                      | GUESTS_HAVE_BEEN_MUTED                                   | 5008   |      |
|                      | MESSAGE_IS_ILLEGAL                                       | 5009   |      |
| 消息更新相关错误     | UPDATING_MESSAGE_BY_SENDER_IS_DISABLED                   | 5100   |      |
|                      | NOT_SENDER_TO_UPDATE_MESSAGE                             | 5101   |      |
|                      | NOT_MESSAGE_RECIPIENT_TO_UPDATE_MESSAGE_READ_DATE        | 5102   |      |
| 消息撤回相关错误     | RECALL_NON_EXISTING_MESSAGE                              | 5200   |      |
|                      | RECALLING_MESSAGE_IS_DISABLED                            | 5201   |      |
|                      | MESSAGE_RECALL_TIMEOUT                                   | 5202   |      |
| 消息查询相关错误     | NOT_MEMBER_TO_QUERY_GROUP_MESSAGES                       | 5300   |      |
| 存储相关错误         | STORAGE_NOT_IMPLEMENTED = 6000                           | 6000   |      |

## SessionCloseStatus

SessionCloseStatus表明会话关闭的原因。

具体的状态码声明可查阅`im.turms.server.common.access.common.SessionCloseStatus`类。

| 原因类别       | 名称                           | 状态码 | 含义                                              |
| -------------- | ------------------------------ | ------ | ------------------------------------------------- |
| 客户端非法行为 | ILLEGAL_REQUEST                | 100    | 非法请求                                          |
|                | HEARTBEAT_TIMEOUT              | 110    | 心跳超时                                          |
|                | LOGIN_TIMEOUT                  | 111    | 登录超时                                          |
|                | SWITCH                         | 112    | 会话超时，TCP或WebSocket切换为UDP进入休眠保活状态 |
| 服务端行为     | SERVER_ERROR                   | 200    | 服务端异常错误                                    |
|                | SERVER_CLOSED                  | 201    | 服务端进入停机状态                                |
|                | SERVER_UNAVAILABLE             | 202    | 服务不可用                                        |
| 网络层错误     | CONNECTION_CLOSED              | 300    | 未收到关闭帧，网络层连接被强制关闭                |
| 未知错误       | UNKNOWN_ERROR                  | 400    | 未知的服务端或客户端行为错误                      |
| 用户主动关闭   | DISCONNECTED_BY_CLIENT         | 500    | 当前用户主动请求关闭会话                          |
|                | DISCONNECTED_BY_OTHER_DEVICE   | 501    | 由于当前用户的其他设备上线，导致当前会话关闭      |
| 管理员主动关闭 | DISCONNECTED_BY_ADMIN          | 600    | 管理员通过API主动关闭会话                         |
| 用户状态变更   | USER_IS_DELETED_OR_INACTIVATED | 700    | 用户账号被删除或进入未激活状态                    |
|                | USER_IS_BLOCKED                | 701    | 用户IP或用户ID被封禁                              |
