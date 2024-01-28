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

package im.turms.server.common.access.common;

import io.netty.util.collection.IntObjectHashMap;
import lombok.Getter;

/**
 * Naming convention: *. All nouns must be singular.
 *
 * @author James Chen
 */
@Getter
public enum ResponseStatusCode {

    // Successful responses
    OK(1000, "ok", 200),
    NO_CONTENT(1001, "No content", 204),
    ALREADY_UP_TO_DATE(1002, "Already up-to-date", 204),

    // **********************************************************
    // * For application error
    // **********************************************************

    // Client
    INVALID_REQUEST(1100, "The client request is invalid", 400),
    CLIENT_REQUESTS_TOO_FREQUENT(1101, "Client requests are too frequent", 429),
    ILLEGAL_ARGUMENT(1102, "Illegal argument", 400),
    RECORD_CONTAINS_DUPLICATE_KEY(1103, "The record to add contains a duplicate key", 409),
    REQUESTED_RECORDS_TOO_MANY(1104, "Too many records are requested", 429),
    SEND_REQUEST_FROM_NONEXISTENT_SESSION(1105,
            "The session must be established before sending requests", 403),
    UNAUTHORIZED_REQUEST(1106, "The request is unauthorized", 401),

    // Server
    SERVER_INTERNAL_ERROR(1200, "Internal server error", 500),
    SERVER_UNAVAILABLE(1201, "The server is unavailable", 503),

    // **********************************************************
    // * For error about admin activity
    // **********************************************************

    // Admin
    // Admin - Common
    UNAUTHORIZED(1300, "Unauthorized", 401),
    NO_FILTER_FOR_DELETE_OPERATION(1301, "The delete operation must have at least one filter", 400),
    RESOURCE_NOT_FOUND(1302, "The resource is not found", 404),
    DUPLICATE_RESOURCE(1303, "Duplicate resource", 409),
    ADMIN_REQUESTS_TOO_FREQUENT(1304, "Admin requests are too frequent", 429),

    // Admin - JFR
    DUMP_JFR_IN_ILLEGAL_STATUS(1310, "Dump JFR in a legal status", 406),

    // Admin - plugin
    JAVASCRIPT_PLUGIN_IS_DISABLED(1320, "JavaScript plugin is disabled", 403),
    SAVING_JAVA_PLUGIN_IS_DISABLED(1321, "Saving a Java plugin is disabled", 403),
    SAVING_JAVASCRIPT_PLUGIN_IS_DISABLED(1322, "Saving a JavaScript plugin is disabled", 403),

    // Admin - Blocklist
    IP_BLOCKLIST_IS_DISABLED(1400, "Blocking an IP is disabled", 403),
    USER_ID_BLOCKLIST_IS_DISABLED(1401, "Blocking a user ID is disabled", 403),

    // Admin - Cluster - Leader
    NONEXISTENT_MEMBER_TO_BE_LEADER(1800, "Could not find the node", 404),
    NO_QUALIFIED_MEMBER_TO_BE_LEADER(1801, "No qualified node to be a leader", 503),
    NOT_QUALIFIED_MEMBER_TO_BE_LEADER(1802,
            "Only the qualified node {isLeaderEligible=true, nodeType=SERVICE} can be a leader",
            403),

    // **********************************************************
    // * For business error
    // **********************************************************

    // User

    // User - Login
    UNSUPPORTED_CLIENT_VERSION(2000, "The client version is not supported", 403),

    LOGIN_TIMEOUT(2010, "Login timeout", 408),
    LOGIN_AUTHENTICATION_FAILED(2011, "The user's login information does not match", 401),
    LOGGING_IN_USER_NOT_ACTIVE(2012, "The logging in user is inactive", 401),
    LOGIN_FROM_FORBIDDEN_DEVICE_TYPE(2013, "The device type is forbidden to log in", 401),

    // User - Session
    SESSION_SIMULTANEOUS_CONFLICTS_DECLINE(2100, "A different device has logged into your account",
            409),
    SESSION_SIMULTANEOUS_CONFLICTS_NOTIFY(2101,
            "A different device attempted to log into your account", 409),
    SESSION_SIMULTANEOUS_CONFLICTS_OFFLINE(2102, "A different device has logged into your account",
            409),
    CREATE_EXISTING_SESSION(2103, "The session has existed", 503),
    UPDATE_HEARTBEAT_OF_NONEXISTENT_SESSION(2104,
            "Cannot update the heartbeat of a nonexistent session", 403),

    // User - Location
    USER_LOCATION_RELATED_FEATURES_ARE_DISABLED(2200,
            "The user-location-related features are disabled", 510),
    QUERYING_NEAREST_USERS_BY_SESSION_ID_IS_DISABLED(2201,
            "Querying the nearest users by session IDs is disabled", 510),

    // User - Info
    UPDATE_INFO_OF_NONEXISTENT_USER(2300, "Cannot update a nonexistent user's information", 403),
    USER_PROFILE_NOT_FOUND(2301, "User profile is not found", 404),
    // TODO: rename "CONTACTS"
    PROFILE_REQUESTER_NOT_IN_CONTACTS_OR_BLOCKED(2302,
            "The profile requester is not in contacts or is blocked", 403),
    PROFILE_REQUESTER_HAS_BEEN_BLOCKED(2303, "The profile requester has been blocked", 403),

    // User - Permission
    QUERY_PERMISSION_OF_NONEXISTENT_USER(2400, "Cannot query a nonexistent user's permission", 404),

    // User - Relationship
    ADD_NON_RELATED_USER_TO_GROUP(2500, "Cannot add a non-related user to a relationship group",
            403),
    CREATE_EXISTING_RELATIONSHIP(2501, "Cannot create an existing relationship", 409),

    // User - Friend Request
    REQUESTER_NOT_FRIEND_REQUEST_RECIPIENT(2600,
            "Only the recipient of the friend request can handle the friend request", 403),
    CREATE_EXISTING_FRIEND_REQUEST(2601, "A friend request has already existed", 409),
    FRIEND_REQUEST_SENDER_HAS_BEEN_BLOCKED(2602,
            "The friend request sender has been blocked by the recipient", 403),

    // Group

    // Group - Info
    UPDATE_INFO_OF_NONEXISTENT_GROUP(3000, "Cannot update the information of a nonexistent group",
            403),
    NOT_GROUP_OWNER_TO_UPDATE_GROUP_INFO(3001,
            "Only the group owner can update the group information", 403),
    NOT_GROUP_OWNER_OR_MANAGER_TO_UPDATE_GROUP_INFO(3002,
            "Only the group owner and managers can update the group information", 403),
    NOT_GROUP_MEMBER_TO_UPDATE_GROUP_INFO(3003,
            "Only the group members can update the group information", 403),

    // Group - Type
    NO_PERMISSION_TO_CREATE_GROUP_WITH_GROUP_TYPE(3100,
            "No permission to create a group with the group type", 403),
    CREATE_GROUP_WITH_NONEXISTENT_GROUP_TYPE(3101,
            "Cannot create a group with a nonexistent group type", 403),

    // Group - Ownership
    NOT_ACTIVE_USER_TO_CREATE_GROUP(3200, "The user trying to create a group is inactive", 403),
    NOT_GROUP_OWNER_TO_TRANSFER_GROUP(3201, "Only the group owner can transfer the group", 403),
    NOT_GROUP_OWNER_TO_DELETE_GROUP(3202, "Only the group owner can delete the group", 403),
    SUCCESSOR_NOT_GROUP_MEMBER(3203, "The successor must be a member of the group", 403),
    OWNER_QUITS_WITHOUT_SPECIFYING_SUCCESSOR(3204,
            "The successor ID must be specified when the owner quits the group", 400),
    MAX_OWNED_GROUPS_REACHED(3205, "The user has reached the maximum allowed owned groups", 403),
    TRANSFER_NONEXISTENT_GROUP(3206, "Cannot transfer a nonexistent group", 403),

    // Group - Question
    NOT_GROUP_OWNER_OR_MANAGER_TO_CREATE_GROUP_QUESTION(3300,
            "Only the group owner and managers can create group questions", 403),
    NOT_GROUP_OWNER_OR_MANAGER_TO_DELETE_GROUP_QUESTION(3301,
            "Only the group owner and managers can delete group questions", 403),
    NOT_GROUP_OWNER_OR_MANAGER_TO_UPDATE_GROUP_QUESTION(3302,
            "Only the group owner and managers can update group questions", 403),
    NOT_GROUP_OWNER_OR_MANAGER_TO_ACCESS_GROUP_QUESTION_ANSWER(3303,
            "Only the group owner and managers can access group question answers", 403),
    CREATE_GROUP_QUESTION_FOR_INACTIVE_GROUP(3304,
            "Cannot create a group question for the inactive group", 403),
    CREATE_GROUP_QUESTION_FOR_GROUP_USING_JOIN_REQUEST(3305,
            "Cannot create a group question for the group that uses the join request strategy",
            403),
    CREATE_GROUP_QUESTION_FOR_GROUP_USING_INVITATION(3306,
            "Cannot create a group question for the group that uses the invitation strategy", 403),
    CREATE_GROUP_QUESTION_FOR_GROUP_USING_MEMBERSHIP_REQUEST(3307,
            "Cannot create a group question for the group that uses the membership request strategy",
            403),
    GROUP_QUESTION_ANSWERER_HAS_BEEN_BLOCKED(3308, "The group question answerer has been blocked",
            403),
    GROUP_MEMBER_ANSWER_GROUP_QUESTION(3309, "A group member cannot answer group questions", 409),
    ANSWER_INACTIVE_GROUP_QUESTION(3310,
            "Cannot answer the inactive group questions because the type of the group has changed",
            403),
    ANSWER_GROUP_QUESTION_OF_INACTIVE_GROUP(3311,
            "Cannot answer the questions of an inactive group", 403),

    // Group - Member
    ADD_USER_TO_GROUP_REQUIRING_INVITATION(3400,
            "Cannot add a user to the group that requires invitations", 403),
    ADD_USER_TO_INACTIVE_GROUP(3401, "Cannot add a user to the inactive group", 403),
    ADD_USER_TO_GROUP_WITH_ROLE_HIGHER_THAN_REQUESTER(3402,
            "Cannot add a user to a group with the role higher than the requester's", 403),
    ADD_USER_TO_GROUP_WITH_SIZE_LIMIT_REACHED(3403, "The group has reached the size limit", 403),
    ADD_BLOCKED_USER_TO_GROUP(3404, "Cannot add a blocked user to the group", 403),
    ADD_BLOCKED_USER_TO_INACTIVE_GROUP(3405, "Cannot add a blocked user to the inactive group",
            403),
    NOT_GROUP_OWNER_OR_MANAGER_TO_REMOVE_GROUP_MEMBER(3406,
            "Only the group owner and managers can remove the group member", 403),
    NOT_GROUP_OWNER_TO_REMOVE_GROUP_OWNER_OR_MANAGER(3407,
            "Only the group owner can remove the owner or managers", 403),
    NOT_GROUP_OWNER_TO_UPDATE_GROUP_MEMBER_ROLE(3408,
            "Only the group owner can update the group member's role", 403),
    UPDATE_GROUP_MEMBER_ROLE_OF_NONEXISTENT_GROUP(3409,
            "Cannot update the group member's role of a nonexistent group", 403),
    NOT_GROUP_OWNER_TO_UPDATE_GROUP_MEMBER_INFO(3410,
            "Only the group owner can update the group member's information", 403),
    NOT_GROUP_OWNER_OR_MANAGER_TO_UPDATE_GROUP_MEMBER_INFO(3411,
            "Only the group owner and managers can update the group member's information", 403),
    NOT_GROUP_MEMBER_TO_UPDATE_GROUP_MEMBER_INFO(3412,
            "Only the group members can update the group member's information", 403),
    UPDATE_GROUP_MEMBER_INFO_OF_NONEXISTENT_GROUP(3413,
            "Cannot update the group member's information of a nonexistent group", 403),
    UPDATE_INFO_OF_NONEXISTENT_GROUP_MEMBER(3414,
            "Cannot update the nonexistent group member's information", 403),
    NOT_GROUP_OWNER_OR_MANAGER_TO_MUTE_GROUP_MEMBER(3415,
            "Only the group owner and managers can mute the group member", 403),
    MUTE_GROUP_MEMBER_WITH_ROLE_EQUAL_TO_OR_HIGHER_THAN_REQUESTER(3416,
            "Cannot mute a group user with the role equal to or higher than the requester's", 403),
    MUTE_GROUP_MEMBER_OF_NONEXISTENT_GROUP(3417,
            "Cannot mute a group member of a nonexistent group", 403),
    MUTE_NONEXISTENT_GROUP_MEMBER(3418, "Cannot mute a nonexistent group member", 403),
    NOT_GROUP_MEMBER_TO_QUERY_GROUP_MEMBER_INFO(3419,
            "Only the group members can query its group members' information", 403),

    // Group - Blocklist
    NOT_GROUP_OWNER_OR_MANAGER_TO_ADD_BLOCKED_USER(3500,
            "Only the group owner and managers can add blocked users", 403),
    NOT_GROUP_OWNER_OR_MANAGER_TO_REMOVE_BLOCKED_USER(3501,
            "Only the group owner and managers can remove blocked users", 403),

    // Group - Join Request
    GROUP_JOIN_REQUEST_SENDER_HAS_BEEN_BLOCKED(3600,
            "The group join request sender has been blocked", 403),
    NOT_GROUP_JOIN_REQUEST_SENDER_TO_RECALL_REQUEST(3601,
            "Only the group join request sender can recall the request", 403),
    NOT_GROUP_OWNER_OR_MANAGER_TO_ACCESS_GROUP_JOIN_REQUEST(3602,
            "Only the group owner and managers can access group join requests", 403),
    RECALL_NON_PENDING_GROUP_JOIN_REQUEST(3603, "Cannot recall non-pending group join requests",
            403),
    SEND_GROUP_JOIN_REQUEST_TO_INACTIVE_GROUP(3604,
            "Cannot send a group join request to an inactive group", 403),
    SEND_GROUP_JOIN_REQUEST_TO_GROUP_USING_MEMBERSHIP_REQUEST(3605,
            "Cannot send a group join request to the group that uses the membership request strategy",
            403),
    SEND_GROUP_JOIN_REQUEST_TO_GROUP_USING_INVITATION(3606,
            "Cannot send a group join request to the group that uses the invitation strategy", 403),
    SEND_GROUP_JOIN_REQUEST_TO_GROUP_USING_QUESTION(3607,
            "Cannot send a group join request to the group that uses the question strategy", 403),
    RECALLING_GROUP_JOIN_REQUEST_IS_DISABLED(3608, "Recalling group join requests is disabled",
            510),

    // Group - Invitation
    GROUP_INVITER_NOT_GROUP_MEMBER(3700, "Only the group members can invite other users", 403),
    GROUP_INVITEE_ALREADY_GROUP_MEMBER(3701, "The group invitee is already a member of the group",
            409),
    GROUP_INVITEE_HAS_BEEN_BLOCKED_BY_GROUP(3702, "The group invitee has been blocked by the group",
            403),
    NOT_GROUP_OWNER_OR_MANAGER_TO_RECALL_GROUP_INVITATION(3703,
            "Only the group owner and managers can recall group invitations", 403),
    NOT_GROUP_OWNER_OR_MANAGER_OR_SENDER_TO_RECALL_GROUP_INVITATION(3704,
            "Only the group owner and managers and the sender can recall group invitations", 403),
    NOT_GROUP_OWNER_OR_MANAGER_TO_ACCESS_GROUP_INVITATION(3705,
            "Only the group owner and managers can access group invitations", 403),
    NOT_GROUP_OWNER_TO_SEND_GROUP_INVITATION(3706,
            "Only the group owner can send group invitations", 403),
    NOT_GROUP_OWNER_OR_MANAGER_TO_SEND_GROUP_INVITATION(3707,
            "Only the group owner and managers can send group invitations", 403),
    NOT_GROUP_MEMBER_TO_SEND_GROUP_INVITATION(3708,
            "Only the group members can send group invitations", 403),
    RECALLING_GROUP_INVITATION_IS_DISABLED(3709, "Recalling group invitations is disabled", 510),
    SEND_GROUP_INVITATION_TO_GROUP_NOT_REQUIRE_INVITATION(3710,
            "Cannot send a group invitation to the group that does not require invitations", 406),
    RECALL_NON_PENDING_GROUP_INVITATION(3711, "Cannot recall non-pending group invitations", 403),

    // Conversation

    // Conversation - Read Date
    UPDATING_READ_DATE_IS_DISABLED(4000, "Updating the read data is disabled", 510),
    UPDATING_READ_DATE_IS_DISABLED_BY_GROUP(4001, "Updating the read data is disabled by the group",
            510),
    UPDATING_READ_DATE_OF_NONEXISTENT_GROUP_CONVERSATION(4002,
            "Cannot update the read date of a nonexistent group conversation", 510),
    NOT_GROUP_MEMBER_TO_UPDATE_READ_DATE_OF_GROUP_CONVERSATION(4003,
            "Only group members can update the read date of a group conversation", 403),
    MOVING_READ_DATE_FORWARD_IS_DISABLED(4004, "Moving the read data forward is disabled", 510),

    // Conversation - Typing Status
    UPDATING_TYPING_STATUS_IS_DISABLED(4100, "Updating the typing status is disabled", 510),
    NOT_GROUP_MEMBER_TO_SEND_TYPING_STATUS(4101, "Only group members can send their typing status",
            403),
    NOT_FRIEND_TO_SEND_TYPING_STATUS(4102, "Only friends can send their typing status", 403),

    // Message

    // Message - Send
    MESSAGE_RECIPIENT_NOT_ACTIVE(5000, "The message recipient is inactive", 403),
    // TODO: rename "CONTACTS"
    MESSAGE_SENDER_NOT_IN_CONTACTS_OR_BLOCKED(5001,
            "The message sender is not in contacts or is blocked by the recipient", 403),
    PRIVATE_MESSAGE_SENDER_HAS_BEEN_BLOCKED(5002, "The private message sender has been blocked",
            403),
    GROUP_MESSAGE_SENDER_HAS_BEEN_BLOCKED(5003, "The group message sender has been blocked", 403),
    SEND_MESSAGE_TO_INACTIVE_GROUP(5004, "Cannot send a message to an inactive group", 403),
    SEND_MESSAGE_TO_MUTED_GROUP(5005, "Cannot send a message to a muted group", 403),
    SEND_MESSAGE_TO_NONEXISTENT_GROUP(5006, "Cannot send a message to a nonexistent group", 403),
    SENDING_MESSAGES_TO_ONESELF_IS_DISABLED(5007, "Sending a message to oneself is disabled", 510),
    MUTED_GROUP_MEMBER_SEND_MESSAGE(5008, "The muted group member cannot send a message", 403),
    GUESTS_HAVE_BEEN_MUTED(5009, "All guests of the group have been muted", 403),
    MESSAGE_IS_ILLEGAL(5010, "The message contains unwanted words", 403),
    NOT_MESSAGE_RECIPIENT_OR_SENDER_TO_FORWARD_MESSAGE(5011,
            "Only the message recipient and sender can forward the message", 403),

    // Message - Update
    UPDATING_MESSAGE_BY_SENDER_IS_DISABLED(5100, "Updating messages sent by the sender is disabled",
            510),
    NOT_SENDER_TO_UPDATE_MESSAGE(5101, "Only the message sender can update the message", 403),
    UPDATE_MESSAGE_OF_NONEXISTENT_GROUP(5102, "Cannot update the message of a nonexistent group",
            403),
    UPDATING_GROUP_MESSAGE_BY_SENDER_IS_DISABLED(5103,
            "Updating group messages sent by the sender is disabled by the group", 403),

    // Message - Recall
    RECALL_NONEXISTENT_MESSAGE(5200, "Cannot recall a nonexistent message", 403),
    RECALLING_MESSAGE_IS_DISABLED(5201, "Recalling messages is disabled", 510),
    NOT_SENDER_TO_RECALL_MESSAGE(5202, "Only the message sender can recall the message", 403),
    RECALL_MESSAGE_OF_NONEXISTENT_GROUP(5203, "Cannot recall the message of a nonexistent group",
            403),
    MESSAGE_RECALL_TIMEOUT(5204, "The maximum allowed time to recall the message has passed", 403),

    // Message - Query
    NOT_GROUP_MEMBER_TO_QUERY_GROUP_MESSAGES(5300,
            "Only the group members can query their group messages", 403),

    // Storage
    STORAGE_NOT_IMPLEMENTED(6000, "The storage feature is enabled but not implemented yet", 501),

    // Storage - Message attachment
    NOT_FRIEND_TO_UPLOAD_MESSAGE_ATTACHMENT_IN_PRIVATE_CONVERSATION(6100,
            "Only friends can upload message attachments in private conversations", 403),
    NOT_GROUP_MEMBER_TO_UPLOAD_MESSAGE_ATTACHMENT_IN_GROUP_CONVERSATION(6101,
            "Only group members can upload message attachments in group conversations", 403),
    NOT_UPLOADER_TO_SHARE_MESSAGE_ATTACHMENT(6102,
            "Only the uploader can share message attachments", 403),
    NOT_UPLOADER_OR_GROUP_MANAGER_TO_UNSHARE_MESSAGE_ATTACHMENT_IN_GROUP_CONVERSATION(6103,
            "Only the uploader or group managers can unshare message attachments in group conversations",
            403),
    NOT_UPLOADER_TO_UNSHARE_MESSAGE_ATTACHMENT_IN_PRIVATE_CONVERSATION(6104,
            "Only the uploader can unshare message attachments in private conversations", 403),
    NOT_UPLOADER_OR_GROUP_MANAGER_TO_DELETE_MESSAGE_ATTACHMENT_IN_GROUP_CONVERSATION(6105,
            "Only the uploader or group managers can delete message attachments in group conversations",
            403),
    NOT_UPLOADER_TO_DELETE_MESSAGE_ATTACHMENT_IN_PRIVATE_CONVERSATION(6106,
            "Only the uploader can delete message attachments in private conversations", 403),
    NOT_UPLOADER_OR_SHARED_WITH_USER_TO_DOWNLOAD_MESSAGE_ATTACHMENT(6107,
            "Only the uploader or the users who are shared with can download message attachments",
            403),

    // Storage - Message attachment info
    NOT_FRIEND_TO_QUERY_MESSAGE_ATTACHMENT_INFO_IN_PRIVATE_CONVERSATION(6130,
            "Only friends can query message attachments in private conversations", 403),
    NOT_GROUP_MEMBER_TO_QUERY_MESSAGE_ATTACHMENT_INFO_IN_GROUP_CONVERSATION(6131,
            "Only group members can query message attachments in group conversations", 403);

    public static final int STATUS_CODE_LENGTH = 4;
    public static final ResponseStatusCode[] VALUES = values();
    private static final IntObjectHashMap<ResponseStatusCode> CODE_POOL =
            new IntObjectHashMap<>((int) (VALUES.length / 0.5F));

    static {
        for (ResponseStatusCode value : VALUES) {
            CODE_POOL.put(value.businessCode, value);
        }
    }

    private final int businessCode;
    private final String reason;
    private final int httpStatusCode;

    ResponseStatusCode(int businessCode, String reason, int httpStatusCode) {
        this.businessCode = businessCode;
        this.reason = reason;
        this.httpStatusCode = httpStatusCode;
    }

    public static ResponseStatusCode from(int businessCode) {
        return CODE_POOL.get(businessCode);
    }

    public static boolean isCodeClientIllegalRequest(int businessCode) {
        return businessCode == INVALID_REQUEST.businessCode
                || businessCode == ILLEGAL_ARGUMENT.businessCode;
    }

    public static boolean isSuccessCode(int businessCode) {
        return 1000 <= businessCode && businessCode < 1100;
    }

    public static boolean isServerError(int businessCode) {
        return 1200 <= businessCode && businessCode < 1300;
    }

    public boolean isCodeClientIllegalRequest() {
        return isCodeClientIllegalRequest(businessCode);
    }

    public boolean isSuccessCode() {
        return isSuccessCode(businessCode);
    }

    public boolean isServerError() {
        return isServerError(businessCode);
    }

}