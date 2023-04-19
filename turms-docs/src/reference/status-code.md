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

| Category                                | Name                                                     | Status Code | Meaning |
| --------------------------------------- | -------------------------------------------------------- | ----------- | ------- |
| Successful Response                     | OK                                                       | 1000        |         |
|                                         | NO_CONTENT                                               | 1001        |         |
|                                         | ALREADY_UP_TO_DATE                                       | 1002        |         |
| Client request error                    | INVALID_REQUEST_FROM_SERVER                              | 1100        |         |
|                                         | CLIENT_REQUESTS_TOO_FREQUENT_FROM_SERVER                 | 1101        |         |
|                                         | ILLEGAL_ARGUMENT_FROM_SERVER                             | 1102        |         |
|                                         | RECORD_CONTAINS_DUPLICATE_KEY                            | 1103        |         |
|                                         | REQUESTED_RECORDS_TOO_MANY                               | 1104        |         |
|                                         | SEND_REQUEST_FROM_NON_EXISTING_SESSION                   | 1105        |         |
|                                         | UNAUTHORIZED_REQUEST                                     | 1106        |         |
| Server Error                            | SERVER_INTERNAL_ERROR                                    | 1200        |         |
|                                         | SERVER_UNAVAILABLE                                       | 1201        |         |
| User login related errors               | UNSUPPORTED_CLIENT_VERSION                               | 2000        |         |
|                                         | LOGIN_TIMEOUT                                            | 2010        |         |
|                                         | LOGIN_AUTHENTICATION_FAILED                              | 2011        |         |
|                                         | LOGGING_IN_USER_NOT_ACTIVE                               | 2012        |         |
|                                         | LOGIN_FROM_FORBIDDEN_DEVICE_TYPE                         | 2013        |         |
| User session related errors             | SESSION_SIMULTANEOUS_CONFLICTS_DECLINE                   | 2100        |         |
|                                         | SESSION_SIMULTANEOUS_CONFLICTS_NOTIFY                    | 2101        |         |
|                                         | SESSION_SIMULTANEOUS_CONFLICTS_OFFLINE                   | 2102        |         |
|                                         | CREATE_EXISTING_SESSION                                  | 2103        |         |
|                                         | UPDATE_NON_EXISTING_SESSION_HEARTBEAT                    | 2104        |         |
| User location related errors            | USER_LOCATION_RELATED_FEATURES_ARE_DISABLED              | 2200        |         |
|                                         | QUERYING_NEAREST_USERS_BY_SESSION_ID_IS_DISABLED         | 2201        |         |
| User information related errors         | UPDATE_INFO_OF_NON_EXISTING_USER                         | 2300        |         |
|                                         | USER_PROFILE_NOT_FOUND                                   | 2301        |         |
|                                         | PROFILE_REQUESTER_NOT_IN_CONTACTS_OR_BLOCKED             | 2302        |         |
|                                         | PROFILE_REQUESTER_HAS_BEEN_BLOCKED                       | 2303        |         |
| User permission group related errors    | QUERY_PERMISSION_OF_NON_EXISTING_USER                    | 2400        |         |
| User relation related errors            | ADD_NOT_RELATED_USER_TO_GROUP                            | 2500        |         |
|                                         | CREATE_EXISTING_RELATIONSHIP                             | 2501        |         |
| User friend request related error       | REQUESTER_NOT_FRIEND_REQUEST_RECIPIENT                   | 2600        |         |
|                                         | CREATE_EXISTING_FRIEND_REQUEST                           | 2601        |         |
|                                         | FRIEND_REQUEST_SENDER_HAS_BEEN_BLOCKED                   | 2602        |         |
| Group information related errors        | UPDATE_INFO_OF_NON_EXISTING_GROUP                        | 3000        |         |
|                                         | NOT_OWNER_TO_UPDATE_GROUP_INFO                           | 3001        |         |
|                                         | NOT_OWNER_OR_MANAGER_TO_UPDATE_GROUP_INFO                | 3002        |         |
|                                         | NOT_MEMBER_TO_UPDATE_GROUP_INFO                          | 3003        |         |
| Group type related errors               | NO_PERMISSION_TO_CREATE_GROUP_WITH_GROUP_TYPE            | 3100        |         |
|                                         | CREATE_GROUP_WITH_NON_EXISTING_GROUP_TYPE                | 3101        |         |
| Group ownership related errors          | NOT_ACTIVE_USER_TO_CREATE_GROUP                          | 3200        |         |
|                                         | NOT_OWNER_TO_TRANSFER_GROUP                              | 3201        |         |
|                                         | NOT_OWNER_TO_DELETE_GROUP                                | 3202        |         |
|                                         | SUCCESSOR_NOT_GROUP_MEMBER                               | 3203        |         |
|                                         | OWNER_QUITS_WITHOUT_SPECIFYING_SUCCESSOR                 | 3204        |         |
|                                         | MAX_OWNED_GROUPS_REACHED                                 | 3205        |         |
|                                         | TRANSFER_NON_EXISTING_GROUP                              | 3206        |         |
| Errors related to group entry questions | NOT_OWNER_OR_MANAGER_TO_CREATE_GROUP_QUESTION            | 3300        |         |
|                                         | NOT_OWNER_OR_MANAGER_TO_DELETE_GROUP_QUESTION            | 3301        |         |
|                                         | NOT_OWNER_OR_MANAGER_TO_UPDATE_GROUP_QUESTION            | 3302        |         |
|                                         | NOT_OWNER_OR_MANAGER_TO_ACCESS_GROUP_QUESTION_ANSWER     | 3303        |         |
|                                         | CREATE_GROUP_QUESTION_FOR_INACTIVE_GROUP                 | 3304        |         |
|                                         | CREATE_GROUP_QUESTION_FOR_GROUP_USING_JOIN_REQUEST       | 3305        |         |
|                                         | CREATE_GROUP_QUESTION_FOR_GROUP_USING_INVITATION         | 3306        |         |
|                                         | CREATE_GROUP_QUESTION_FOR_GROUP_USING_MEMBERSHIP_REQUEST | 3307        |         |
|                                         | GROUP_QUESTION_ANSWERER_HAS_BEEN_BLOCKED                 | 3308        |         |
|                                         | MEMBER_CANNOT_ANSWER_GROUP_QUESTION                      | 3309        |         |
|                                         | ANSWER_INACTIVE_QUESTION                                 | 3310        |         |
|                                         | ANSWER_QUESTION_OF_INACTIVE_GROUP                        | 3311        |         |
| Group membership related errors         | ADD_USER_TO_GROUP_REQUIRING_INVITATION                   | 3400        |         |
|                                         | ADD_USER_TO_INACTIVE_GROUP                               | 3401        |         |
|                                         | ADD_USER_WITH_ROLE_HIGHER_THAN_REQUESTER                 | 3402        |         |
|                                         | ADD_BLOCKED_USER_TO_GROUP                                | 3403        |         |
|                                         | ADD_BLOCKED_USER_TO_INACTIVE_GROUP                       | 3404        |         |
|                                         | NOT_OWNER_OR_MANAGER_TO_REMOVE_GROUP_MEMBER              | 3405        |         |
|                                         | NOT_OWNER_TO_REMOVE_GROUP_OWNER_OR_MANAGER               | 3406        |         |
|                                         | NOT_OWNER_TO_UPDATE_GROUP_MEMBER_INFO                    | 3407        |         |
|                                         | NOT_OWNER_OR_MANAGER_TO_UPDATE_GROUP_MEMBER_INFO         | 3408        |         |
|                                         | NOT_MEMBER_TO_QUERY_MEMBER_INFO                          | 3409        |         |
| Group blacklist related errors          | NOT_OWNER_OR_MANAGER_TO_ADD_BLOCKED_USER                 | 3500        |         |
|                                         | NOT_OWNER_OR_MANAGER_TO_REMOVE_BLOCKED_USER              | 3501        |         |
| Group join request related errors       | GROUP_JOIN_REQUEST_SENDER_HAS_BEEN_BLOCKED               | 3600        |         |
|                                         | NOT_JOIN_REQUEST_SENDER_TO_RECALL_REQUEST                | 3601        |         |
|                                         | NOT_OWNER_OR_MANAGER_TO_ACCESS_GROUP_REQUEST             | 3602        |         |
|                                         | RECALL_NOT_PENDING_GROUP_JOIN_REQUEST                    | 3603        |         |
|                                         | SEND_JOIN_REQUEST_TO_INACTIVE_GROUP                      | 3604        |         |
|                                         | SEND_JOIN_REQUEST_TO_GROUP_USING_MEMBERSHIP_REQUEST      | 3605        |         |
|                                         | SEND_JOIN_REQUEST_TO_GROUP_USING_INVITATION              | 3606        |         |
|                                         | SEND_JOIN_REQUEST_TO_GROUP_USING_QUESTION                | 3607        |         |
|                                         | RECALLING_GROUP_JOIN_REQUEST_IS_DISABLED                 | 3608        |         |
| Group invite related errors             | GROUP_INVITER_NOT_MEMBER                                 | 3700        |         |
|                                         | GROUP_INVITEE_ALREADY_GROUP_MEMBER                       | 3701        |         |
|                                         | NOT_OWNER_OR_MANAGER_TO_RECALL_INVITATION                | 3702        |         |
|                                         | NOT_OWNER_OR_MANAGER_TO_ACCESS_INVITATION                | 3703        |         |
|                                         | NOT_OWNER_TO_SEND_INVITATION                             | 3704        |         |
|                                         | NOT_OWNER_OR_MANAGER_TO_SEND_INVITATION                  | 3705        |         |
|                                         | NOT_MEMBER_TO_SEND_INVITATION                            | 3706        |         |
|                                         | INVITEE_HAS_BEEN_BLOCKED                                 | 3707        |         |
|                                         | RECALLING_GROUP_INVITATION_IS_DISABLED                   | 3708        |         |
|                                         | SEND_GROUP_INVITATION_TO_GROUP_NOT_REQUIRE_INVITATION    | 3709        |         |
|                                         | RECALL_NOT_PENDING_GROUP_INVITATION                      | 3710        |         |
| Chat session related errors             | UPDATING_TYPING_STATUS_IS_DISABLED                       | 4000        |         |
|                                         | UPDATING_READ_DATE_IS_DISABLED                           | 4001        |         |
|                                         | MOVING_READ_DATE_FORWARD_IS_DISABLED                     | 4002        |         |
| Message sending related error           | MESSAGE_RECIPIENT_NOT_ACTIVE                             | 5000        |         |
|                                         | MESSAGE_SENDER_NOT_IN_CONTACTS_OR_BLOCKED                | 5001        |         |
|                                         | PRIVATE_MESSAGE_SENDER_HAS_BEEN_BLOCKED                  | 5002        |         |
|                                         | GROUP_MESSAGE_SENDER_HAS_BEEN_BLOCKED                    | 5003        |         |
|                                         | SEND_MESSAGE_TO_INACTIVE_GROUP                           | 5004        |         |
|                                         | SEND_MESSAGE_TO_MUTED_GROUP                              | 5005        |         |
|                                         | SENDING_MESSAGES_TO_ONESELF_IS_DISABLED                  | 5006        |         |
|                                         | MUTED_MEMBER_SEND_MESSAGE                                | 5007        |         |
|                                         | GUESTS_HAVE_BEEN_MUTED                                   | 5008        |         |
|                                         | MESSAGE_IS_ILLEGAL                                       | 5009        |         |
| Message update related error            | UPDATING_MESSAGE_BY_SENDER_IS_DISABLED                   | 5100        |         |
|                                         | NOT_SENDER_TO_UPDATE_MESSAGE                             | 5101        |         |
|                                         | NOT_MESSAGE_RECIPIENT_TO_UPDATE_MESSAGE_READ_DATE        | 5102        |         |
| Message recall related error            | RECALL_NON_EXISTING_MESSAGE                              | 5200        |         |
|                                         | RECALLING_MESSAGE_IS_DISABLED                            | 5201        |         |
|                                         | MESSAGE_RECALL_TIMEOUT                                   | 5202        |         |
| Message query related errors            | NOT_MEMBER_TO_QUERY_GROUP_MESSAGES                       | 5300        |         |
| Storage related errors                  | STORAGE_NOT_IMPLEMENTED = 6000                           | 6000        |         |

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