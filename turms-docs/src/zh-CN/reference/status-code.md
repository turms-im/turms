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

| 类别                               | 名称                                                         | 状态码 | 含义 |
| ---------------------------------- | ------------------------------------------------------------ | ------ | ---- |
| 成功响应                           | OK                                                           | 1000   |      |
|                                    | NO_CONTENT                                                   | 1001   |      |
|                                    | ALREADY_UP_TO_DATE                                           | 1002   |      |
| 客户端请求错误                     | INVALID_REQUEST                                              | 1100   |      |
|                                    | CLIENT_REQUESTS_TOO_FREQUENT                                 | 1101   |      |
|                                    | ILLEGAL_ARGUMENT                                             | 1102   |      |
|                                    | RECORD_CONTAINS_DUPLICATE_KEY                                | 1103   |      |
|                                    | REQUESTED_RECORDS_TOO_MANY                                   | 1104   |      |
|                                    | SEND_REQUEST_FROM_NONEXISTENT_SESSION                        | 1105   |      |
|                                    | UNAUTHORIZED_REQUEST                                         | 1106   |      |
| 服务端错误                         | SERVER_INTERNAL_ERROR                                        | 1200   |      |
|                                    | SERVER_UNAVAILABLE                                           | 1201   |      |
| 管理员接口 - 通用错误              | UNAUTHORIZED                                                 | 1300   |      |
|                                    | NO_FILTER_FOR_DELETE_OPERATION                               | 1301   |      |
|                                    | RESOURCE_NOT_FOUND                                           | 1302   |      |
|                                    | DUPLICATE_RESOURCE                                           | 1303   |      |
|                                    | ADMIN_REQUESTS_TOO_FREQUENT                                  | 1304   |      |
| 管理员接口 - JFR相关错误           | DUMP_JFR_IN_ILLEGAL_STATUS                                   | 1310   |      |
| 管理员接口 - 插件相关错误          | JAVASCRIPT_PLUGIN_IS_DISABLED                                | 1320   |      |
|                                    | SAVING_JAVA_PLUGIN_IS_DISABLED                               | 1321   |      |
|                                    | SAVING_JAVASCRIPT_PLUGIN_IS_DISABLED                         | 1322   |      |
| 管理员接口 - 封禁相关错误          | IP_BLOCKLIST_IS_DISABLED                                     | 1400   |      |
|                                    | USER_ID_BLOCKLIST_IS_DISABLED                                | 1401   |      |
| 管理员接口 - 集群 - 主节点相关错误 | NONEXISTENT_MEMBER_TO_BE_LEADER                              | 1800   |      |
|                                    | NO_QUALIFIED_MEMBER_TO_BE_LEADER                             | 1801   |      |
|                                    | NOT_QUALIFIED_MEMBER_TO_BE_LEADER                            | 1802   |      |
| 用户 - 登陆相关错误                | UNSUPPORTED_CLIENT_VERSION                                   | 2000   |      |
|                                    | LOGIN_TIMEOUT                                                | 2010   |      |
|                                    | LOGIN_AUTHENTICATION_FAILED                                  | 2011   |      |
|                                    | LOGGING_IN_USER_NOT_ACTIVE                                   | 2012   |      |
|                                    | LOGIN_FROM_FORBIDDEN_DEVICE_TYPE                             | 2013   |      |
| 用户 - 会话相关错误                | SESSION_SIMULTANEOUS_CONFLICTS_DECLINE                       | 2100   |      |
|                                    | SESSION_SIMULTANEOUS_CONFLICTS_NOTIFY                        | 2101   |      |
|                                    | SESSION_SIMULTANEOUS_CONFLICTS_OFFLINE                       | 2102   |      |
|                                    | CREATE_EXISTING_SESSION                                      | 2103   |      |
|                                    | UPDATE_HEARTBEAT_OF_NONEXISTENT_SESSION                      | 2104   |      |
| 用户 - 位置相关错误                | USER_LOCATION_RELATED_FEATURES_ARE_DISABLED                  | 2200   |      |
|                                    | QUERYING_NEAREST_USERS_BY_SESSION_ID_IS_DISABLED             | 2201   |      |
| 用户 - 信息相关错误                | UPDATE_INFO_OF_NONEXISTENT_USER                              | 2300   |      |
|                                    | NOT_FRIEND_TO_QUERY_USER_PROFILE                             | 2301   |      |
|                                    | BLOCKED_USER_TO_QUERY_USER_PROFILE                           | 2302   |      |
| 用户 - 权限组相关错误              | QUERY_PERMISSION_OF_NONEXISTENT_USER                         | 2400   |      |
| 用户 - 关系相关错误                | ADD_NON_RELATED_USER_TO_GROUP                                | 2500   |      |
|                                    | CREATE_EXISTING_RELATIONSHIP                                 | 2501   |      |
|                                    | CANNOT_BLOCK_ONESELF                                         | 2502   |      |
| 用户 - 好友请求相关错误            | CREATE_EXISTING_FRIEND_REQUEST                               | 2600   |      |
|                                    | BLOCKED_USER_TO_SEND_FRIEND_REQUEST                          | 2601   |      |
|                                    | RECALL_NON_PENDING_FRIEND_REQUEST                            | 2602   |      |
|                                    | RECALLING_FRIEND_REQUEST_IS_DISABLED                         | 2603   |      |
|                                    | NOT_SENDER_TO_RECALL_FRIEND_REQUEST                          | 2604   |      |
|                                    | UPDATE_NON_PENDING_FRIEND_REQUEST                            | 2605   |      |
|                                    | NOT_RECIPIENT_TO_UPDATE_FRIEND_REQUEST                       | 2606   |      |
| 群组 - 信息相关错误                | UPDATE_INFO_OF_NONEXISTENT_GROUP                             | 3000   |      |
|                                    | NOT_GROUP_OWNER_TO_UPDATE_GROUP_INFO                         | 3001   |      |
|                                    | NOT_GROUP_OWNER_OR_MANAGER_TO_UPDATE_GROUP_INFO              | 3002   |      |
|                                    | NOT_GROUP_MEMBER_TO_UPDATE_GROUP_INFO                        | 3003   |      |
| 群组 - 类型相关错误                | NO_PERMISSION_TO_CREATE_GROUP_WITH_GROUP_TYPE                | 3100   |      |
|                                    | CREATE_GROUP_WITH_NONEXISTENT_GROUP_TYPE                     | 3101   |      |
|                                    | UPDATING_GROUP_TYPE_IS_DISABLED                              | 3102   |      |
|                                    | NOT_GROUP_OWNER_TO_UPDATE_GROUP_TYPE                         | 3103   |      |
|                                    | NO_PERMISSION_TO_UPDATE_GROUP_TO_GROUP_TYPE                  | 3104   |      |
|                                    | UPDATE_GROUP_TO_NONEXISTENT_GROUP_TYPE                       | 3105   |      |
| 群组 - 所有权相关错误              | NOT_ACTIVE_USER_TO_CREATE_GROUP                              | 3200   |      |
|                                    | NOT_GROUP_OWNER_TO_TRANSFER_GROUP                            | 3201   |      |
|                                    | NOT_GROUP_OWNER_TO_DELETE_GROUP                              | 3202   |      |
|                                    | GROUP_SUCCESSOR_NOT_GROUP_MEMBER                             | 3203   |      |
|                                    | GROUP_OWNER_QUIT_WITHOUT_SPECIFYING_SUCCESSOR                | 3204   |      |
|                                    | MAX_OWNED_GROUPS_REACHED                                     | 3205   |      |
|                                    | TRANSFER_NONEXISTENT_GROUP                                   | 3206   |      |
| 群组 - 入群问题相关错误            | NOT_GROUP_OWNER_OR_MANAGER_TO_CREATE_GROUP_QUESTION          | 3300   |      |
|                                    | NOT_GROUP_OWNER_OR_MANAGER_TO_DELETE_GROUP_QUESTION          | 3301   |      |
|                                    | NOT_GROUP_OWNER_OR_MANAGER_TO_UPDATE_GROUP_QUESTION          | 3302   |      |
|                                    | NOT_GROUP_OWNER_OR_MANAGER_TO_QUERY_GROUP_QUESTION_ANSWER    | 3303   |      |
|                                    | CREATE_GROUP_QUESTION_FOR_INACTIVE_GROUP                     | 3304   |      |
|                                    | CREATE_GROUP_QUESTION_FOR_GROUP_USING_JOIN_REQUEST           | 3305   |      |
|                                    | CREATE_GROUP_QUESTION_FOR_GROUP_USING_INVITATION             | 3306   |      |
|                                    | CREATE_GROUP_QUESTION_FOR_GROUP_USING_MEMBERSHIP_REQUEST     | 3307   |      |
|                                    | GROUP_QUESTION_ANSWERER_HAS_BEEN_BLOCKED                     | 3308   |      |
|                                    | GROUP_MEMBER_ANSWER_GROUP_QUESTION                           | 3309   |      |
|                                    | ANSWER_INACTIVE_GROUP_QUESTION                               | 3310   |      |
|                                    | ANSWER_GROUP_QUESTION_OF_INACTIVE_GROUP                      | 3311   |      |
| 群组 - 成员相关错误                | ADD_USER_TO_GROUP_REQUIRING_USERS_APPROVAL                   | 3400   |      |
|                                    | ADD_USER_TO_INACTIVE_GROUP                                   | 3401   |      |
|                                    | NOT_GROUP_OWNER_TO_ADD_GROUP_MANAGER                         | 3402   |      |
|                                    | ADD_USER_TO_GROUP_WITH_SIZE_LIMIT_REACHED                    | 3403   |      |
|                                    | ADD_BLOCKED_USER_TO_GROUP                                    | 3404   |      |
|                                    | NOT_GROUP_OWNER_TO_ADD_GROUP_MEMBER                          | 3405   |      |
|                                    | NOT_GROUP_OWNER_OR_MANAGER_TO_ADD_GROUP_MEMBER               | 3406   |      |
|                                    | NOT_GROUP_MEMBER_TO_ADD_GROUP_MEMBER                         | 3407   |      |
|                                    | NOT_GROUP_OWNER_OR_MANAGER_TO_REMOVE_GROUP_MEMBER            | 3408   |      |
|                                    | NOT_GROUP_OWNER_TO_REMOVE_GROUP_OWNER_OR_MANAGER             | 3409   |      |
|                                    | NOT_GROUP_OWNER_TO_UPDATE_GROUP_MEMBER_ROLE                  | 3410   |      |
|                                    | UPDATE_GROUP_MEMBER_ROLE_OF_NONEXISTENT_GROUP                | 3411   |      |
|                                    | NOT_GROUP_OWNER_TO_UPDATE_GROUP_MEMBER_INFO                  | 3412   |      |
|                                    | NOT_GROUP_OWNER_OR_MANAGER_TO_UPDATE_GROUP_MEMBER_INFO       | 3413   |      |
|                                    | NOT_GROUP_MEMBER_TO_UPDATE_GROUP_MEMBER_INFO                 | 3414   |      |
|                                    | UPDATE_GROUP_MEMBER_INFO_OF_NONEXISTENT_GROUP                | 3415   |      |
|                                    | UPDATE_INFO_OF_NONEXISTENT_GROUP_MEMBER                      | 3416   |      |
|                                    | NOT_GROUP_OWNER_OR_MANAGER_TO_MUTE_GROUP_MEMBER              | 3417   |      |
|                                    | MUTE_GROUP_MEMBER_WITH_ROLE_EQUAL_TO_OR_HIGHER_THAN_REQUESTER | 3418   |      |
|                                    | MUTE_GROUP_MEMBER_OF_NONEXISTENT_GROUP                       | 3419   |      |
|                                    | MUTE_NONEXISTENT_GROUP_MEMBER                                | 3420   |      |
|                                    | NOT_GROUP_MEMBER_TO_QUERY_GROUP_MEMBER_INFO                  | 3421   |      |
|                                    | USER_JOIN_GROUP_WITHOUT_ACCEPTING_GROUP_INVITATION           | 3422   |      |
|                                    | USER_JOIN_GROUP_WITHOUT_ANSWERING_GROUP_QUESTION             | 3423   |      |
|                                    | USER_JOIN_GROUP_WITHOUT_SENDING_GROUP_JOIN_REQUEST           | 3424   |      |
| 群组 - 黑名单相关错误              | NOT_GROUP_OWNER_OR_MANAGER_TO_ADD_BLOCKED_USER               | 3500   |      |
|                                    | NOT_GROUP_OWNER_OR_MANAGER_TO_REMOVE_BLOCKED_USER            | 3501   |      |
| 群组 - 入群请求相关错误            | BLOCKED_USER_SEND_GROUP_JOIN_REQUEST                         | 3600   |      |
|                                    | GROUP_MEMBER_SEND_GROUP_JOIN_REQUEST                         | 3601   |      |
|                                    | NOT_SENDER_TO_RECALL_GROUP_JOIN_REQUEST                      | 3602   |      |
|                                    | NOT_GROUP_OWNER_OR_MANAGER_TO_QUERY_GROUP_JOIN_REQUEST       | 3603   |      |
|                                    | RECALL_NON_PENDING_GROUP_JOIN_REQUEST                        | 3604   |      |
|                                    | SEND_GROUP_JOIN_REQUEST_TO_INACTIVE_GROUP                    | 3605   |      |
|                                    | SEND_GROUP_JOIN_REQUEST_TO_GROUP_USING_MEMBERSHIP_REQUEST    | 3606   |      |
|                                    | SEND_GROUP_JOIN_REQUEST_TO_GROUP_USING_INVITATION            | 3607   |      |
|                                    | SEND_GROUP_JOIN_REQUEST_TO_GROUP_USING_QUESTION              | 3608   |      |
|                                    | RECALLING_GROUP_JOIN_REQUEST_IS_DISABLED                     | 3609   |      |
|                                    | UPDATE_NON_PENDING_GROUP_JOIN_REQUEST                        | 3610   |      |
|                                    | NOT_GROUP_OWNER_OR_MANAGER_TO_UPDATE_GROUP_JOIN_REQUEST      | 3611   |      |
| 群组 - 邀请相关错误                | SEND_GROUP_INVITATION_TO_GROUP_MEMBER                        | 3700   |      |
|                                    | SEND_GROUP_INVITATION_TO_BLOCKED_USER                        | 3701   |      |
|                                    | SEND_GROUP_INVITATION_TO_GROUP_NOT_REQUIRING_USERS_APPROVAL  | 3702   |      |
|                                    | NOT_GROUP_OWNER_TO_SEND_GROUP_INVITATION                     | 3703   |      |
|                                    | NOT_GROUP_OWNER_OR_MANAGER_TO_SEND_GROUP_INVITATION          | 3704   |      |
|                                    | NOT_GROUP_MEMBER_TO_SEND_GROUP_INVITATION                    | 3705   |      |
|                                    | RECALLING_GROUP_INVITATION_IS_DISABLED                       | 3706   |      |
|                                    | NOT_GROUP_OWNER_OR_MANAGER_TO_RECALL_GROUP_INVITATION        | 3707   |      |
|                                    | NOT_GROUP_OWNER_OR_MANAGER_OR_SENDER_TO_RECALL_GROUP_INVITATION | 3708   |      |
|                                    | RECALL_NON_PENDING_GROUP_INVITATION                          | 3709   |      |
|                                    | UPDATE_NON_PENDING_GROUP_INVITATION                          | 3710   |      |
|                                    | NOT_INVITEE_TO_UPDATE_GROUP_INVITATION                       | 3711   |      |
|                                    | NOT_GROUP_OWNER_OR_MANAGER_TO_QUERY_GROUP_INVITATION         | 3712   |      |
| 聊天会话 - 查阅时间相关错误        | UPDATING_READ_DATE_IS_DISABLED                               | 4000   |      |
|                                    | UPDATING_READ_DATE_IS_DISABLED_BY_GROUP                      | 4001   |      |
|                                    | UPDATING_READ_DATE_OF_NONEXISTENT_GROUP_CONVERSATION         | 4002   |      |
|                                    | NOT_GROUP_MEMBER_TO_UPDATE_READ_DATE_OF_GROUP_CONVERSATION   | 4003   |      |
|                                    | MOVING_READ_DATE_FORWARD_IS_DISABLED                         | 4004   |      |
| 聊天会话 - 输入状态相关错误        | UPDATING_TYPING_STATUS_IS_DISABLED                           | 4100   |      |
|                                    | NOT_GROUP_MEMBER_TO_SEND_TYPING_STATUS                       | 4101   |      |
|                                    | NOT_FRIEND_TO_SEND_TYPING_STATUS                             | 4102   |      |
| 消息 - 发送相关错误                | MESSAGE_RECIPIENT_NOT_ACTIVE                                 | 5000   |      |
|                                    | NOT_FRIEND_TO_SEND_PRIVATE_MESSAGE                           | 5001   |      |
|                                    | BLOCKED_USER_SEND_PRIVATE_MESSAGE                            | 5002   |      |
|                                    | BLOCKED_USER_SEND_GROUP_MESSAGE                              | 5003   |      |
|                                    | SEND_MESSAGE_TO_INACTIVE_GROUP                               | 5004   |      |
|                                    | SEND_MESSAGE_TO_MUTED_GROUP                                  | 5005   |      |
|                                    | SEND_MESSAGE_TO_NONEXISTENT_GROUP                            | 5006   |      |
|                                    | SENDING_MESSAGES_TO_ONESELF_IS_DISABLED                      | 5007   |      |
|                                    | MUTED_GROUP_MEMBER_SEND_MESSAGE                              | 5008   |      |
|                                    | NOT_SPEAKABLE_GROUP_GUEST_TO_SEND_MESSAGE                    | 5009   |      |
|                                    | MESSAGE_IS_ILLEGAL                                           | 5010   |      |
|                                    | NOT_MESSAGE_RECIPIENT_OR_SENDER_TO_FORWARD_MESSAGE           | 5011   |      |
| 消息 - 更新相关错误                | UPDATING_MESSAGE_BY_SENDER_IS_DISABLED                       | 5100   |      |
|                                    | NOT_SENDER_TO_UPDATE_MESSAGE                                 | 5101   |      |
|                                    | UPDATE_MESSAGE_OF_NONEXISTENT_GROUP                          | 5102   |      |
|                                    | UPDATING_GROUP_MESSAGE_BY_SENDER_IS_DISABLED                 | 5103   |      |
| 消息 - 撤回相关错误                | RECALL_NONEXISTENT_MESSAGE                                   | 5200   |      |
|                                    | RECALLING_MESSAGE_IS_DISABLED                                | 5201   |      |
|                                    | NOT_SENDER_TO_RECALL_MESSAGE                                 | 5202   |      |
|                                    | RECALL_MESSAGE_OF_NONEXISTENT_GROUP                          | 5203   |      |
|                                    | MESSAGE_RECALL_TIMEOUT                                       | 5204   |      |
| 消息 - 查询相关错误                | NOT_GROUP_MEMBER_TO_QUERY_GROUP_MESSAGES                     | 5300   |      |
| 存储 - 通用错误                    | STORAGE_NOT_IMPLEMENTED                                      | 6000   |      |
| 存储 - 消息附件相关错误            | NOT_FRIEND_TO_UPLOAD_MESSAGE_ATTACHMENT_IN_PRIVATE_CONVERSATION | 6100   |      |
|                                    | NOT_GROUP_MEMBER_TO_UPLOAD_MESSAGE_ATTACHMENT_IN_GROUP_CONVERSATION | 6101   |      |
|                                    | NOT_UPLOADER_TO_SHARE_MESSAGE_ATTACHMENT                     | 6102   |      |
|                                    | NOT_UPLOADER_OR_GROUP_MANAGER_TO_UNSHARE_MESSAGE_ATTACHMENT_IN_GROUP_CONVERSATION | 6103   |      |
|                                    | NOT_UPLOADER_TO_UNSHARE_MESSAGE_ATTACHMENT_IN_PRIVATE_CONVERSATION | 6104   |      |
|                                    | NOT_UPLOADER_OR_GROUP_MANAGER_TO_DELETE_MESSAGE_ATTACHMENT_IN_GROUP_CONVERSATION | 6105   |      |
|                                    | NOT_UPLOADER_TO_DELETE_MESSAGE_ATTACHMENT_IN_PRIVATE_CONVERSATION | 6106   |      |
|                                    | NOT_UPLOADER_OR_SHARED_WITH_USER_TO_DOWNLOAD_MESSAGE_ATTACHMENT | 6107   |      |
| 存储 - 消息附件信息相关错误        | NOT_FRIEND_TO_QUERY_MESSAGE_ATTACHMENT_INFO_IN_PRIVATE_CONVERSATION | 6130   |      |
|                                    | NOT_GROUP_MEMBER_TO_QUERY_MESSAGE_ATTACHMENT_INFO_IN_GROUP_CONVERSATION | 6131   |      |

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