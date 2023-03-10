/*
 * Copyright (C) 2019 The Turms Project
 * https://github.com/turms-im/turms
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package im.turms.client.model

/**
 * @author James Chen
 */
object ResponseStatusCode {
    // **********************************************************
    // * Defined on the client side
    // **********************************************************

    // **********************************************************
    // * For application error
    // **********************************************************

    // Client - Common
    const val CONNECT_TIMEOUT = 1
    const val DATA_NOT_FOUND = 10
    const val HTTP_ERROR = 90
    const val HTTP_NOT_SUCCESSFUL_RESPONSE = 91

    // Client - Request
    const val INVALID_REQUEST = 100
    const val CLIENT_REQUESTS_TOO_FREQUENT = 101
    const val REQUEST_TIMEOUT = 102
    const val ILLEGAL_ARGUMENT = 103

    // Server - Notification
    const val INVALID_NOTIFICATION = 200
    const val INVALID_RESPONSE = 201

    // **********************************************************
    // * For business error
    // **********************************************************

    // User
    const val CLIENT_SESSION_ALREADY_ESTABLISHED = 300
    const val CLIENT_SESSION_HAS_BEEN_CLOSED = 301

    // Group

    // Conversation

    // Message

    // Storage

    // **********************************************************
    // * Defined on the server side
    // **********************************************************

    // Successful responses
    const val OK = 1000
    const val NO_CONTENT = 1001
    const val ALREADY_UP_TO_DATE = 1002

    // **********************************************************
    // * For application error
    // **********************************************************

    // Client
    const val INVALID_REQUEST_FROM_SERVER = 1100
    const val CLIENT_REQUESTS_TOO_FREQUENT_FROM_SERVER = 1101
    const val ILLEGAL_ARGUMENT_FROM_SERVER = 1102
    const val RECORD_CONTAINS_DUPLICATE_KEY = 1103
    const val REQUESTED_RECORDS_TOO_MANY = 1104
    const val SEND_REQUEST_FROM_NON_EXISTING_SESSION = 1105
    const val UNAUTHORIZED_REQUEST = 1106

    // Server
    const val SERVER_INTERNAL_ERROR = 1200
    const val SERVER_UNAVAILABLE = 1201

    // **********************************************************
    // * For business error
    // **********************************************************

    // User

    // User - Login
    const val UNSUPPORTED_CLIENT_VERSION = 2000

    const val LOGIN_TIMEOUT = 2010
    const val LOGIN_AUTHENTICATION_FAILED = 2011
    const val LOGGING_IN_USER_NOT_ACTIVE = 2012
    const val LOGIN_FROM_FORBIDDEN_DEVICE_TYPE = 2013

    // User - Session
    const val SESSION_SIMULTANEOUS_CONFLICTS_DECLINE = 2100
    const val SESSION_SIMULTANEOUS_CONFLICTS_NOTIFY = 2101
    const val SESSION_SIMULTANEOUS_CONFLICTS_OFFLINE = 2102
    const val CREATE_EXISTING_SESSION = 2103
    const val UPDATE_NON_EXISTING_SESSION_HEARTBEAT = 2104

    // User - Location
    const val USER_LOCATION_RELATED_FEATURES_ARE_DISABLED = 2200
    const val QUERYING_NEAREST_USERS_BY_SESSION_ID_IS_DISABLED = 2201

    // User - Info
    const val UPDATE_INFO_OF_NON_EXISTING_USER = 2300
    const val USER_PROFILE_NOT_FOUND = 2301
    const val PROFILE_REQUESTER_NOT_IN_CONTACTS_OR_BLOCKED = 2302
    const val PROFILE_REQUESTER_HAS_BEEN_BLOCKED = 2303

    // User - Permission
    const val QUERY_PERMISSION_OF_NON_EXISTING_USER = 2400

    // User - Relationship
    const val ADD_NOT_RELATED_USER_TO_GROUP = 2500
    const val CREATE_EXISTING_RELATIONSHIP = 2501

    // User - Friend Request
    const val REQUESTER_NOT_FRIEND_REQUEST_RECIPIENT = 2600
    const val CREATE_EXISTING_FRIEND_REQUEST = 2601
    const val FRIEND_REQUEST_SENDER_HAS_BEEN_BLOCKED = 2602

    // Group

    // Group - Info
    const val UPDATE_INFO_OF_NON_EXISTING_GROUP = 3000
    const val NOT_OWNER_TO_UPDATE_GROUP_INFO = 3001
    const val NOT_OWNER_OR_MANAGER_TO_UPDATE_GROUP_INFO = 3002
    const val NOT_MEMBER_TO_UPDATE_GROUP_INFO = 3003

    // Group - Type
    const val NO_PERMISSION_TO_CREATE_GROUP_WITH_GROUP_TYPE = 3100
    const val CREATE_GROUP_WITH_NON_EXISTING_GROUP_TYPE = 3101

    // Group - Ownership
    const val NOT_ACTIVE_USER_TO_CREATE_GROUP = 3200
    const val NOT_OWNER_TO_TRANSFER_GROUP = 3201
    const val NOT_OWNER_TO_DELETE_GROUP = 3202
    const val SUCCESSOR_NOT_GROUP_MEMBER = 3203
    const val OWNER_QUITS_WITHOUT_SPECIFYING_SUCCESSOR = 3204
    const val MAX_OWNED_GROUPS_REACHED = 3205
    const val TRANSFER_NON_EXISTING_GROUP = 3206

    // Group - Question
    const val NOT_OWNER_OR_MANAGER_TO_CREATE_GROUP_QUESTION = 3300
    const val NOT_OWNER_OR_MANAGER_TO_DELETE_GROUP_QUESTION = 3301
    const val NOT_OWNER_OR_MANAGER_TO_UPDATE_GROUP_QUESTION = 3302
    const val NOT_OWNER_OR_MANAGER_TO_ACCESS_GROUP_QUESTION_ANSWER = 3303
    const val CREATE_GROUP_QUESTION_FOR_INACTIVE_GROUP = 3304
    const val CREATE_GROUP_QUESTION_FOR_GROUP_USING_JOIN_REQUEST = 3305
    const val CREATE_GROUP_QUESTION_FOR_GROUP_USING_INVITATION = 3306
    const val CREATE_GROUP_QUESTION_FOR_GROUP_USING_MEMBERSHIP_REQUEST = 3307
    const val GROUP_QUESTION_ANSWERER_HAS_BEEN_BLOCKED = 3308
    const val MEMBER_CANNOT_ANSWER_GROUP_QUESTION = 3309
    const val ANSWER_INACTIVE_QUESTION = 3310
    const val ANSWER_QUESTION_OF_INACTIVE_GROUP = 3311

    // Group - Member
    const val ADD_USER_TO_GROUP_REQUIRING_INVITATION = 3400
    const val ADD_USER_TO_INACTIVE_GROUP = 3401
    const val ADD_USER_WITH_ROLE_HIGHER_THAN_REQUESTER = 3402
    const val ADD_BLOCKED_USER_TO_GROUP = 3403
    const val ADD_BLOCKED_USER_TO_INACTIVE_GROUP = 3404
    const val NOT_OWNER_OR_MANAGER_TO_REMOVE_GROUP_MEMBER = 3405
    const val NOT_OWNER_TO_REMOVE_GROUP_OWNER_OR_MANAGER = 3406
    const val NOT_OWNER_TO_UPDATE_GROUP_MEMBER_INFO = 3407
    const val NOT_OWNER_OR_MANAGER_TO_UPDATE_GROUP_MEMBER_INFO = 3408
    const val NOT_MEMBER_TO_QUERY_MEMBER_INFO = 3409

    // Group - Blocklist
    const val NOT_OWNER_OR_MANAGER_TO_ADD_BLOCKED_USER = 3500
    const val NOT_OWNER_OR_MANAGER_TO_REMOVE_BLOCKED_USER = 3501

    // Group - Join Request
    const val GROUP_JOIN_REQUEST_SENDER_HAS_BEEN_BLOCKED = 3600
    const val NOT_JOIN_REQUEST_SENDER_TO_RECALL_REQUEST = 3601
    const val NOT_OWNER_OR_MANAGER_TO_ACCESS_GROUP_REQUEST = 3602
    const val RECALL_NOT_PENDING_GROUP_JOIN_REQUEST = 3603
    const val SEND_JOIN_REQUEST_TO_INACTIVE_GROUP = 3604
    const val SEND_JOIN_REQUEST_TO_GROUP_USING_MEMBERSHIP_REQUEST = 3605
    const val SEND_JOIN_REQUEST_TO_GROUP_USING_INVITATION = 3606
    const val SEND_JOIN_REQUEST_TO_GROUP_USING_QUESTION = 3607
    const val RECALLING_GROUP_JOIN_REQUEST_IS_DISABLED = 3608

    // Group - Invitation
    const val GROUP_INVITER_NOT_MEMBER = 3700
    const val GROUP_INVITEE_ALREADY_GROUP_MEMBER = 3701
    const val NOT_OWNER_OR_MANAGER_TO_RECALL_INVITATION = 3702
    const val NOT_OWNER_OR_MANAGER_TO_ACCESS_INVITATION = 3703
    const val NOT_OWNER_TO_SEND_INVITATION = 3704
    const val NOT_OWNER_OR_MANAGER_TO_SEND_INVITATION = 3705
    const val NOT_MEMBER_TO_SEND_INVITATION = 3706
    const val INVITEE_HAS_BEEN_BLOCKED = 3707
    const val RECALLING_GROUP_INVITATION_IS_DISABLED = 3708
    const val SEND_GROUP_INVITATION_TO_GROUP_NOT_REQUIRE_INVITATION = 3709
    const val RECALL_NOT_PENDING_GROUP_INVITATION = 3710

    // Conversation
    const val UPDATING_TYPING_STATUS_IS_DISABLED = 4000
    const val UPDATING_READ_DATE_IS_DISABLED = 4001
    const val MOVING_READ_DATE_FORWARD_IS_DISABLED = 4002

    // Message

    // Message - Send
    const val MESSAGE_RECIPIENT_NOT_ACTIVE = 5000
    const val MESSAGE_SENDER_NOT_IN_CONTACTS_OR_BLOCKED = 5001
    const val PRIVATE_MESSAGE_SENDER_HAS_BEEN_BLOCKED = 5002
    const val GROUP_MESSAGE_SENDER_HAS_BEEN_BLOCKED = 5003
    const val SEND_MESSAGE_TO_INACTIVE_GROUP = 5004
    const val SEND_MESSAGE_TO_MUTED_GROUP = 5005
    const val SENDING_MESSAGES_TO_ONESELF_IS_DISABLED = 5006
    const val MUTED_MEMBER_SEND_MESSAGE = 5007
    const val GUESTS_HAVE_BEEN_MUTED = 5008
    const val MESSAGE_IS_ILLEGAL = 5009

    // Message - Update
    const val UPDATING_MESSAGE_BY_SENDER_IS_DISABLED = 5100
    const val NOT_SENDER_TO_UPDATE_MESSAGE = 5101
    const val NOT_MESSAGE_RECIPIENT_TO_UPDATE_MESSAGE_READ_DATE = 5102

    // Message - Recall
    const val RECALL_NON_EXISTING_MESSAGE = 5200
    const val RECALLING_MESSAGE_IS_DISABLED = 5201
    const val MESSAGE_RECALL_TIMEOUT = 5202

    // Message - Query
    const val NOT_MEMBER_TO_QUERY_GROUP_MESSAGES = 5300

    // Storage
    const val STORAGE_NOT_IMPLEMENTED = 6000
    const val NOT_FRIEND_TO_UPLOAD_MESSAGE_ATTACHMENT_IN_PRIVATE_CONVERSATION = 6100
    const val NOT_GROUP_MEMBER_TO_UPLOAD_MESSAGE_ATTACHMENT_IN_GROUP_CONVERSATION = 6101
    const val NOT_UPLOADER_TO_SHARE_MESSAGE_ATTACHMENT = 6102
    const val NOT_UPLOADER_OR_GROUP_MANAGER_TO_UNSHARE_MESSAGE_ATTACHMENT_IN_GROUP_CONVERSATION = 6103
    const val NOT_UPLOADER_TO_UNSHARE_MESSAGE_ATTACHMENT_IN_PRIVATE_CONVERSATION = 6104
    const val NOT_UPLOADER_OR_GROUP_MANAGER_TO_DELETE_MESSAGE_ATTACHMENT_IN_GROUP_CONVERSATION = 6105
    const val NOT_UPLOADER_TO_DELETE_MESSAGE_ATTACHMENT_IN_PRIVATE_CONVERSATION = 6106
    const val NOT_UPLOADER_OR_SHARED_WITH_USER_TO_DOWNLOAD_MESSAGE_ATTACHMENT = 6107
    const val NOT_FRIEND_TO_QUERY_MESSAGE_ATTACHMENT_INFO_IN_PRIVATE_CONVERSATION = 6130
    const val NOT_GROUP_MEMBER_TO_QUERY_MESSAGE_ATTACHMENT_INFO_IN_GROUP_CONVERSATION = 6131

    @JvmStatic
    fun isSuccessCode(businessCode: Int): Boolean {
        return businessCode in 1000..1099
    }

    @JvmStatic
    fun isErrorCode(businessCode: Int): Boolean {
        return !isSuccessCode(businessCode)
    }
}
