# Status Code

There are two status codes that developers need to understand, one is `ResponseStatusCode`, and the other is `SessionCloseStatus`. The content in the following table does not need to be memorized deliberately. You only need to know how to query when encountering an unfamiliar status code.

## ResponseStatusCode

ResponseStatusCode indicates the processing status in the request response, similar to the HTTP status code.

Each request response will contain a ResponseStatusCode. For the specific status code declaration, please refer to the `im.turms.client.model.ResponseStatusCode` class under the `turms-client-kotlin` project.

### Client unique status code

The client-specific status code will not appear in the Turms server, indicating that the client request is rejected locally on the client.

| Category             | Name                                     | Status Code | Meaning |
| -------------------- | ---------------------------------------- | ----------- | ------- |
| Connection related   | CONNECT_TIMEOUT                          | 1           |         |
| Request related      | INVALID_REQUEST                          | 100         |         |
|                      | CLIENT_REQUESTS_TOO_FREQUENT             | 101         |         |
|                      | REQUEST_TIMEOUT                          | 102         |         |
|                      | ILLEGAL_ARGUMENT                         | 103         |         |
| Notification Related | INVALID_NOTIFICATION                     | 200         |         |
|                      | INVALID_RESPONSE                         | 201         |         |
| Session related      | CLIENT_SESSION_ALREADY_ESTABLISHED       | 300         |         |
|                      | CLIENT_SESSION_HAS_BEEN_CLOSED           | 301         |         |
| Message related      | MESSAGE_IS_REJECTED                      | 400         |         |
| Storage related      | QUERY_PROFILE_URL_TO_UPDATE_BEFORE_LOGIN | 500         |         |
### Common Status Codes

| Category                                        | Name                                                         | Status Code | Meaning |
| ----------------------------------------------- | ------------------------------------------------------------ | ----------- | ------- |
| Successful Response                             | OK                                                           | 1000        |         |
|                                                 | NO_CONTENT                                                   | 1001        |         |
|                                                 | ALREADY_UP_TO_DATE                                           | 1002        |         |
| Client Request Error                            | INVALID_REQUEST                                              | 1100        |         |
|                                                 | CLIENT_REQUESTS_TOO_FREQUENT                                 | 1101        |         |
|                                                 | ILLEGAL_ARGUMENT                                             | 1102        |         |
|                                                 | RECORD_CONTAINS_DUPLICATE_KEY                                | 1103        |         |
|                                                 | REQUESTED_RECORDS_TOO_MANY                                   | 1104        |         |
|                                                 | SEND_REQUEST_FROM_NONEXISTENT_SESSION                        | 1105        |         |
|                                                 | UNAUTHORIZED_REQUEST                                         | 1106        |         |
| Server Error                                    | SERVER_INTERNAL_ERROR                                        | 1200        |         |
|                                                 | SERVER_UNAVAILABLE                                           | 1201        |         |
| Admin - Common Error                            | UNAUTHORIZED                                                 | 1300        |         |
|                                                 | NO_FILTER_FOR_DELETE_OPERATION                               | 1301        |         |
|                                                 | RESOURCE_NOT_FOUND                                           | 1302        |         |
|                                                 | DUPLICATE_RESOURCE                                           | 1303        |         |
|                                                 | ADMIN_REQUESTS_TOO_FREQUENT                                  | 1304        |         |
| Admin - JFR Related Error                       | DUMP_JFR_IN_ILLEGAL_STATUS                                   | 1310        |         |
| Admin - Plugin Related Error                    | JAVASCRIPT_PLUGIN_IS_DISABLED                                | 1320        |         |
|                                                 | SAVING_JAVA_PLUGIN_IS_DISABLED                               | 1321        |         |
|                                                 | SAVING_JAVASCRIPT_PLUGIN_IS_DISABLED                         | 1322        |         |
| Admin - Blocklist Related Error                 | IP_BLOCKLIST_IS_DISABLED                                     | 1400        |         |
|                                                 | USER_ID_BLOCKLIST_IS_DISABLED                                | 1401        |         |
| Admin - Cluster - Leader Related Error          | NONEXISTENT_MEMBER_TO_BE_LEADER                              | 1800        |         |
|                                                 | NO_QUALIFIED_MEMBER_TO_BE_LEADER                             | 1801        |         |
|                                                 | NOT_QUALIFIED_MEMBER_TO_BE_LEADER                            | 1802        |         |
| User - Login Related Error                      | UNSUPPORTED_CLIENT_VERSION                                   | 2000        |         |
|                                                 | LOGIN_TIMEOUT                                                | 2010        |         |
|                                                 | LOGIN_AUTHENTICATION_FAILED                                  | 2011        |         |
|                                                 | LOGGING_IN_USER_NOT_ACTIVE                                   | 2012        |         |
|                                                 | LOGIN_FROM_FORBIDDEN_DEVICE_TYPE                             | 2013        |         |
| User - Session Related Error                    | SESSION_SIMULTANEOUS_CONFLICTS_DECLINE                       | 2100        |         |
|                                                 | SESSION_SIMULTANEOUS_CONFLICTS_NOTIFY                        | 2101        |         |
|                                                 | SESSION_SIMULTANEOUS_CONFLICTS_OFFLINE                       | 2102        |         |
|                                                 | CREATE_EXISTING_SESSION                                      | 2103        |         |
|                                                 | UPDATE_HEARTBEAT_OF_NONEXISTENT_SESSION                      | 2104        |         |
| User - Location Related Error                   | USER_LOCATION_RELATED_FEATURES_ARE_DISABLED                  | 2200        |         |
|                                                 | QUERYING_NEAREST_USERS_BY_SESSION_ID_IS_DISABLED             | 2201        |         |
| User - Info Related Error                       | UPDATE_INFO_OF_NONEXISTENT_USER                              | 2300        |         |
|                                                 | NOT_FRIEND_TO_QUERY_USER_PROFILE                             | 2301        |         |
|                                                 | BLOCKED_USER_TO_QUERY_USER_PROFILE                           | 2302        |         |
| User - Permission Related Error                 | QUERY_PERMISSION_OF_NONEXISTENT_USER                         | 2400        |         |
| User - Relationship Related Error               | ADD_NON_RELATED_USER_TO_GROUP                                | 2500        |         |
|                                                 | CREATE_EXISTING_RELATIONSHIP                                 | 2501        |         |
|                                                 | CANNOT_BLOCK_ONESELF                                         | 2502        |         |
| User - Friend Request Related Error             | CREATE_EXISTING_FRIEND_REQUEST                               | 2600        |         |
|                                                 | BLOCKED_USER_TO_SEND_FRIEND_REQUEST                          | 2601        |         |
|                                                 | RECALL_NON_PENDING_FRIEND_REQUEST                            | 2602        |         |
|                                                 | RECALLING_FRIEND_REQUEST_IS_DISABLED                         | 2603        |         |
|                                                 | NOT_SENDER_TO_RECALL_FRIEND_REQUEST                          | 2604        |         |
|                                                 | UPDATE_NON_PENDING_FRIEND_REQUEST                            | 2605        |         |
|                                                 | NOT_RECIPIENT_TO_UPDATE_FRIEND_REQUEST                       | 2606        |         |
| Group - Info Related Error                      | UPDATE_INFO_OF_NONEXISTENT_GROUP                             | 3000        |         |
|                                                 | NOT_GROUP_OWNER_TO_UPDATE_GROUP_INFO                         | 3001        |         |
|                                                 | NOT_GROUP_OWNER_OR_MANAGER_TO_UPDATE_GROUP_INFO              | 3002        |         |
|                                                 | NOT_GROUP_MEMBER_TO_UPDATE_GROUP_INFO                        | 3003        |         |
| Group - Type Related Error                      | NO_PERMISSION_TO_CREATE_GROUP_WITH_GROUP_TYPE                | 3100        |         |
|                                                 | CREATE_GROUP_WITH_NONEXISTENT_GROUP_TYPE                     | 3101        |         |
|                                                 | UPDATING_GROUP_TYPE_IS_DISABLED                              | 3102        |         |
|                                                 | NOT_GROUP_OWNER_TO_UPDATE_GROUP_TYPE                         | 3103        |         |
|                                                 | NO_PERMISSION_TO_UPDATE_GROUP_TO_GROUP_TYPE                  | 3104        |         |
|                                                 | UPDATE_GROUP_TO_NONEXISTENT_GROUP_TYPE                       | 3105        |         |
| Group - Ownership Related Error                 | NOT_ACTIVE_USER_TO_CREATE_GROUP                              | 3200        |         |
|                                                 | NOT_GROUP_OWNER_TO_TRANSFER_GROUP                            | 3201        |         |
|                                                 | NOT_GROUP_OWNER_TO_DELETE_GROUP                              | 3202        |         |
|                                                 | GROUP_SUCCESSOR_NOT_GROUP_MEMBER                             | 3203        |         |
|                                                 | GROUP_OWNER_QUIT_WITHOUT_SPECIFYING_SUCCESSOR                | 3204        |         |
|                                                 | MAX_OWNED_GROUPS_REACHED                                     | 3205        |         |
|                                                 | TRANSFER_NONEXISTENT_GROUP                                   | 3206        |         |
| Group - Question Related Error                  | NOT_GROUP_OWNER_OR_MANAGER_TO_CREATE_GROUP_QUESTION          | 3300        |         |
|                                                 | NOT_GROUP_OWNER_OR_MANAGER_TO_DELETE_GROUP_QUESTION          | 3301        |         |
|                                                 | NOT_GROUP_OWNER_OR_MANAGER_TO_UPDATE_GROUP_QUESTION          | 3302        |         |
|                                                 | NOT_GROUP_OWNER_OR_MANAGER_TO_QUERY_GROUP_QUESTION_ANSWER    | 3303        |         |
|                                                 | CREATE_GROUP_QUESTION_FOR_INACTIVE_GROUP                     | 3304        |         |
|                                                 | CREATE_GROUP_QUESTION_FOR_GROUP_USING_JOIN_REQUEST           | 3305        |         |
|                                                 | CREATE_GROUP_QUESTION_FOR_GROUP_USING_INVITATION             | 3306        |         |
|                                                 | CREATE_GROUP_QUESTION_FOR_GROUP_USING_MEMBERSHIP_REQUEST     | 3307        |         |
|                                                 | GROUP_QUESTION_ANSWERER_HAS_BEEN_BLOCKED                     | 3308        |         |
|                                                 | GROUP_MEMBER_ANSWER_GROUP_QUESTION                           | 3309        |         |
|                                                 | ANSWER_INACTIVE_GROUP_QUESTION                               | 3310        |         |
|                                                 | ANSWER_GROUP_QUESTION_OF_INACTIVE_GROUP                      | 3311        |         |
| Group - Member Related Error                    | ADD_USER_TO_GROUP_REQUIRING_USERS_APPROVAL                   | 3400        |         |
|                                                 | ADD_USER_TO_INACTIVE_GROUP                                   | 3401        |         |
|                                                 | NOT_GROUP_OWNER_TO_ADD_GROUP_MANAGER                         | 3402        |         |
|                                                 | ADD_USER_TO_GROUP_WITH_SIZE_LIMIT_REACHED                    | 3403        |         |
|                                                 | ADD_BLOCKED_USER_TO_GROUP                                    | 3404        |         |
|                                                 | NOT_GROUP_OWNER_TO_ADD_GROUP_MEMBER                          | 3405        |         |
|                                                 | NOT_GROUP_OWNER_OR_MANAGER_TO_ADD_GROUP_MEMBER               | 3406        |         |
|                                                 | NOT_GROUP_MEMBER_TO_ADD_GROUP_MEMBER                         | 3407        |         |
|                                                 | NOT_GROUP_OWNER_OR_MANAGER_TO_REMOVE_GROUP_MEMBER            | 3408        |         |
|                                                 | NOT_GROUP_OWNER_TO_REMOVE_GROUP_OWNER_OR_MANAGER             | 3409        |         |
|                                                 | NOT_GROUP_OWNER_TO_UPDATE_GROUP_MEMBER_ROLE                  | 3410        |         |
|                                                 | UPDATE_GROUP_MEMBER_ROLE_OF_NONEXISTENT_GROUP                | 3411        |         |
|                                                 | NOT_GROUP_OWNER_TO_UPDATE_GROUP_MEMBER_INFO                  | 3412        |         |
|                                                 | NOT_GROUP_OWNER_OR_MANAGER_TO_UPDATE_GROUP_MEMBER_INFO       | 3413        |         |
|                                                 | NOT_GROUP_MEMBER_TO_UPDATE_GROUP_MEMBER_INFO                 | 3414        |         |
|                                                 | UPDATE_GROUP_MEMBER_INFO_OF_NONEXISTENT_GROUP                | 3415        |         |
|                                                 | UPDATE_INFO_OF_NONEXISTENT_GROUP_MEMBER                      | 3416        |         |
|                                                 | NOT_GROUP_OWNER_OR_MANAGER_TO_MUTE_GROUP_MEMBER              | 3417        |         |
|                                                 | MUTE_GROUP_MEMBER_WITH_ROLE_EQUAL_TO_OR_HIGHER_THAN_REQUESTER | 3418        |         |
|                                                 | MUTE_GROUP_MEMBER_OF_NONEXISTENT_GROUP                       | 3419        |         |
|                                                 | MUTE_NONEXISTENT_GROUP_MEMBER                                | 3420        |         |
|                                                 | NOT_GROUP_MEMBER_TO_QUERY_GROUP_MEMBER_INFO                  | 3421        |         |
|                                                 | USER_JOIN_GROUP_WITHOUT_ACCEPTING_GROUP_INVITATION           | 3422        |         |
|                                                 | USER_JOIN_GROUP_WITHOUT_ANSWERING_GROUP_QUESTION             | 3423        |         |
|                                                 | USER_JOIN_GROUP_WITHOUT_SENDING_GROUP_JOIN_REQUEST           | 3424        |         |
| Group - Blocklist Related Error                 | NOT_GROUP_OWNER_OR_MANAGER_TO_ADD_BLOCKED_USER               | 3500        |         |
|                                                 | NOT_GROUP_OWNER_OR_MANAGER_TO_REMOVE_BLOCKED_USER            | 3501        |         |
| Group - Join Request Related Error              | BLOCKED_USER_SEND_GROUP_JOIN_REQUEST                         | 3600        |         |
|                                                 | GROUP_MEMBER_SEND_GROUP_JOIN_REQUEST                         | 3601        |         |
|                                                 | NOT_SENDER_TO_RECALL_GROUP_JOIN_REQUEST                      | 3602        |         |
|                                                 | NOT_GROUP_OWNER_OR_MANAGER_TO_QUERY_GROUP_JOIN_REQUEST       | 3603        |         |
|                                                 | RECALL_NON_PENDING_GROUP_JOIN_REQUEST                        | 3604        |         |
|                                                 | SEND_GROUP_JOIN_REQUEST_TO_INACTIVE_GROUP                    | 3605        |         |
|                                                 | SEND_GROUP_JOIN_REQUEST_TO_GROUP_USING_MEMBERSHIP_REQUEST    | 3606        |         |
|                                                 | SEND_GROUP_JOIN_REQUEST_TO_GROUP_USING_INVITATION            | 3607        |         |
|                                                 | SEND_GROUP_JOIN_REQUEST_TO_GROUP_USING_QUESTION              | 3608        |         |
|                                                 | RECALLING_GROUP_JOIN_REQUEST_IS_DISABLED                     | 3609        |         |
|                                                 | UPDATE_NON_PENDING_GROUP_JOIN_REQUEST                        | 3610        |         |
|                                                 | NOT_GROUP_OWNER_OR_MANAGER_TO_UPDATE_GROUP_JOIN_REQUEST      | 3611        |         |
| Group - Invitation Related Error                | SEND_GROUP_INVITATION_TO_GROUP_MEMBER                        | 3700        |         |
|                                                 | SEND_GROUP_INVITATION_TO_BLOCKED_USER                        | 3701        |         |
|                                                 | SEND_GROUP_INVITATION_TO_GROUP_NOT_REQUIRING_USERS_APPROVAL  | 3702        |         |
|                                                 | NOT_GROUP_OWNER_TO_SEND_GROUP_INVITATION                     | 3703        |         |
|                                                 | NOT_GROUP_OWNER_OR_MANAGER_TO_SEND_GROUP_INVITATION          | 3704        |         |
|                                                 | NOT_GROUP_MEMBER_TO_SEND_GROUP_INVITATION                    | 3705        |         |
|                                                 | RECALLING_GROUP_INVITATION_IS_DISABLED                       | 3706        |         |
|                                                 | NOT_GROUP_OWNER_OR_MANAGER_TO_RECALL_GROUP_INVITATION        | 3707        |         |
|                                                 | NOT_GROUP_OWNER_OR_MANAGER_OR_SENDER_TO_RECALL_GROUP_INVITATION | 3708        |         |
|                                                 | RECALL_NON_PENDING_GROUP_INVITATION                          | 3709        |         |
|                                                 | UPDATE_NON_PENDING_GROUP_INVITATION                          | 3710        |         |
|                                                 | NOT_INVITEE_TO_UPDATE_GROUP_INVITATION                       | 3711        |         |
|                                                 | NOT_GROUP_OWNER_OR_MANAGER_TO_QUERY_GROUP_INVITATION         | 3712        |         |
| Conversation - Read Date Related Error          | UPDATING_READ_DATE_IS_DISABLED                               | 4000        |         |
|                                                 | UPDATING_READ_DATE_IS_DISABLED_BY_GROUP                      | 4001        |         |
|                                                 | UPDATING_READ_DATE_OF_NONEXISTENT_GROUP_CONVERSATION         | 4002        |         |
|                                                 | NOT_GROUP_MEMBER_TO_UPDATE_READ_DATE_OF_GROUP_CONVERSATION   | 4003        |         |
|                                                 | MOVING_READ_DATE_FORWARD_IS_DISABLED                         | 4004        |         |
| Conversation - Typing Status Related Error      | UPDATING_TYPING_STATUS_IS_DISABLED                           | 4100        |         |
|                                                 | NOT_GROUP_MEMBER_TO_SEND_TYPING_STATUS                       | 4101        |         |
|                                                 | NOT_FRIEND_TO_SEND_TYPING_STATUS                             | 4102        |         |
| Message - Send Related Error                    | MESSAGE_RECIPIENT_NOT_ACTIVE                                 | 5000        |         |
|                                                 | NOT_FRIEND_TO_SEND_PRIVATE_MESSAGE                           | 5001        |         |
|                                                 | BLOCKED_USER_SEND_PRIVATE_MESSAGE                            | 5002        |         |
|                                                 | BLOCKED_USER_SEND_GROUP_MESSAGE                              | 5003        |         |
|                                                 | SEND_MESSAGE_TO_INACTIVE_GROUP                               | 5004        |         |
|                                                 | SEND_MESSAGE_TO_MUTED_GROUP                                  | 5005        |         |
|                                                 | SEND_MESSAGE_TO_NONEXISTENT_GROUP                            | 5006        |         |
|                                                 | SENDING_MESSAGES_TO_ONESELF_IS_DISABLED                      | 5007        |         |
|                                                 | MUTED_GROUP_MEMBER_SEND_MESSAGE                              | 5008        |         |
|                                                 | NOT_SPEAKABLE_GROUP_GUEST_TO_SEND_MESSAGE                    | 5009        |         |
|                                                 | MESSAGE_IS_ILLEGAL                                           | 5010        |         |
|                                                 | NOT_MESSAGE_RECIPIENT_OR_SENDER_TO_FORWARD_MESSAGE           | 5011        |         |
| Message - Update Related Error                  | UPDATING_MESSAGE_BY_SENDER_IS_DISABLED                       | 5100        |         |
|                                                 | NOT_SENDER_TO_UPDATE_MESSAGE                                 | 5101        |         |
|                                                 | UPDATE_MESSAGE_OF_NONEXISTENT_GROUP                          | 5102        |         |
|                                                 | UPDATING_GROUP_MESSAGE_BY_SENDER_IS_DISABLED                 | 5103        |         |
| Message - Recall Related Error                  | RECALL_NONEXISTENT_MESSAGE                                   | 5200        |         |
|                                                 | RECALLING_MESSAGE_IS_DISABLED                                | 5201        |         |
|                                                 | NOT_SENDER_TO_RECALL_MESSAGE                                 | 5202        |         |
|                                                 | RECALL_MESSAGE_OF_NONEXISTENT_GROUP                          | 5203        |         |
|                                                 | MESSAGE_RECALL_TIMEOUT                                       | 5204        |         |
| Message - Query Related Error                   | NOT_GROUP_MEMBER_TO_QUERY_GROUP_MESSAGES                     | 5300        |         |
| Storage Related Error                           | STORAGE_NOT_IMPLEMENTED                                      | 6000        |         |
| Storage - Message Attachment Related Error      | NOT_FRIEND_TO_UPLOAD_MESSAGE_ATTACHMENT_IN_PRIVATE_CONVERSATION | 6100        |         |
|                                                 | NOT_GROUP_MEMBER_TO_UPLOAD_MESSAGE_ATTACHMENT_IN_GROUP_CONVERSATION | 6101        |         |
|                                                 | NOT_UPLOADER_TO_SHARE_MESSAGE_ATTACHMENT                     | 6102        |         |
|                                                 | NOT_UPLOADER_OR_GROUP_MANAGER_TO_UNSHARE_MESSAGE_ATTACHMENT_IN_GROUP_CONVERSATION | 6103        |         |
|                                                 | NOT_UPLOADER_TO_UNSHARE_MESSAGE_ATTACHMENT_IN_PRIVATE_CONVERSATION | 6104        |         |
|                                                 | NOT_UPLOADER_OR_GROUP_MANAGER_TO_DELETE_MESSAGE_ATTACHMENT_IN_GROUP_CONVERSATION | 6105        |         |
|                                                 | NOT_UPLOADER_TO_DELETE_MESSAGE_ATTACHMENT_IN_PRIVATE_CONVERSATION | 6106        |         |
|                                                 | NOT_UPLOADER_OR_SHARED_WITH_USER_TO_DOWNLOAD_MESSAGE_ATTACHMENT | 6107        |         |
| Storage - Message Attachment Info Related Error | NOT_FRIEND_TO_QUERY_MESSAGE_ATTACHMENT_INFO_IN_PRIVATE_CONVERSATION | 6130        |         |
|                                                 | NOT_GROUP_MEMBER_TO_QUERY_MESSAGE_ATTACHMENT_INFO_IN_GROUP_CONVERSATION | 6131        |         |

## SessionCloseStatus

SessionCloseStatus indicates why the session was closed.

For the specific status code declaration, please refer to `im.turms.server.common.access.common.SessionCloseStatus` class.

| Cause Category                    | Name                           | Status Code | Meaning                                                      |
| --------------------------------- | ------------------------------ | ----------- | ------------------------------------------------------------ |
| Illegal client behavior           | ILLEGAL_REQUEST                | 100         | Illegal request                                              |
|                                   | HEARTBEAT_TIMEOUT              | 110         | Heartbeat timeout                                            |
|                                   | LOGIN_TIMEOUT                  | 111         | Login timeout                                                |
|                                   | SWITCH                         | 112         | Session timeout, TCP or WebSocket switches to UDP and enters dormant keep-alive state |
| Server behavior                   | SERVER_ERROR                   | 200         | Server exception error                                       |
|                                   | SERVER_CLOSED                  | 201         | The server enters shutdown state                             |
|                                   | SERVER_UNAVAILABLE             | 202         | Service Unavailable                                          |
| Network layer error               | CONNECTION_CLOSED              | 300         | No close frame received, the network layer connection is forcibly closed |
| Unknown error                     | UNKNOWN_ERROR                  | 400         | Unknown server or client behavior error                      |
| Closed by the user                | DISCONNECTED_BY_CLIENT         | 500         | The current user actively requests to close the session      |
|                                   | DISCONNECTED_BY_OTHER_DEVICE   | 501         | The current session is closed because the current user's other device is online |
| The administrator actively closes | DISCONNECTED_BY_ADMIN          | 600         | The administrator actively closes the session through the API |
| User status change                | USER_IS_DELETED_OR_INACTIVATED | 700         | User account is deleted or enters inactive state             |
|                                   | USER_IS_BLOCKED                | 701         | User IP or User ID is blocked                                |