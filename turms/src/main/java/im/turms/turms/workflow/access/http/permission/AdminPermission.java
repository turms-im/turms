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

package im.turms.turms.workflow.access.http.permission;

import java.util.Set;

/**
 * @author James Chen
 */
public enum AdminPermission {
    NONE,

    STATISTICS_USER_QUERY,
    STATISTICS_GROUP_QUERY,
    STATISTICS_MESSAGE_QUERY,

    USER_CREATE,
    USER_DELETE,
    USER_UPDATE,
    USER_QUERY,

    USER_RELATIONSHIP_CREATE,
    USER_RELATIONSHIP_DELETE,
    USER_RELATIONSHIP_UPDATE,
    USER_RELATIONSHIP_QUERY,

    USER_RELATIONSHIP_GROUP_CREATE,
    USER_RELATIONSHIP_GROUP_DELETE,
    USER_RELATIONSHIP_GROUP_UPDATE,
    USER_RELATIONSHIP_GROUP_QUERY,

    USER_FRIEND_REQUEST_CREATE,
    USER_FRIEND_REQUEST_DELETE,
    USER_FRIEND_REQUEST_UPDATE,
    USER_FRIEND_REQUEST_QUERY,

    USER_PERMISSION_GROUP_CREATE,
    USER_PERMISSION_GROUP_DELETE,
    USER_PERMISSION_GROUP_UPDATE,
    USER_PERMISSION_GROUP_QUERY,

    USER_ONLINE_INFO_UPDATE,
    USER_ONLINE_INFO_QUERY,

    GROUP_CREATE,
    GROUP_DELETE,
    GROUP_UPDATE,
    GROUP_QUERY,

    GROUP_BLACKLIST_CREATE,
    GROUP_BLACKLIST_DELETE,
    GROUP_BLACKLIST_UPDATE,
    GROUP_BLACKLIST_QUERY,

    GROUP_INVITATION_CREATE,
    GROUP_INVITATION_DELETE,
    GROUP_INVITATION_UPDATE,
    GROUP_INVITATION_QUERY,

    GROUP_QUESTION_CREATE,
    GROUP_QUESTION_DELETE,
    GROUP_QUESTION_UPDATE,
    GROUP_QUESTION_QUERY,

    GROUP_JOIN_REQUEST_CREATE,
    GROUP_JOIN_REQUEST_DELETE,
    GROUP_JOIN_REQUEST_UPDATE,
    GROUP_JOIN_REQUEST_QUERY,

    GROUP_MEMBER_UPDATE,
    GROUP_MEMBER_CREATE,
    GROUP_MEMBER_DELETE,
    GROUP_MEMBER_QUERY,

    GROUP_TYPE_CREATE,
    GROUP_TYPE_DELETE,
    GROUP_TYPE_UPDATE,
    GROUP_TYPE_QUERY,

    MESSAGE_CREATE,
    MESSAGE_DELETE,
    MESSAGE_UPDATE,
    MESSAGE_QUERY,

    CONVERSATION_QUERY,
    CONVERSATION_DELETE,
    CONVERSATION_UPDATE,

    ADMIN_CREATE,
    ADMIN_DELETE,
    ADMIN_UPDATE,
    ADMIN_QUERY,

    ADMIN_ROLE_CREATE,
    ADMIN_ROLE_DELETE,
    ADMIN_ROLE_UPDATE,
    ADMIN_ROLE_QUERY,

    CLUSTER_MEMBERS_CREATE,
    CLUSTER_MEMBERS_DELETE,
    CLUSTER_MEMBERS_UPDATE,
    CLUSTER_MEMBERS_QUERY,

    CLUSTER_CONFIG_UPDATE,
    CLUSTER_INFO_QUERY;

    public static final Set<AdminPermission> ALL = Set.of(AdminPermission.values());

    public static final Set<AdminPermission> ALL_STATISTICS = Set.of(
            STATISTICS_USER_QUERY,
            STATISTICS_GROUP_QUERY,
            STATISTICS_MESSAGE_QUERY);

    public static final Set<AdminPermission> ALL_CONTENT_USER = Set.of(
            USER_CREATE,
            USER_DELETE,
            USER_UPDATE,
            USER_QUERY,
            USER_RELATIONSHIP_CREATE,
            USER_RELATIONSHIP_DELETE,
            USER_RELATIONSHIP_UPDATE,
            USER_RELATIONSHIP_QUERY,
            USER_RELATIONSHIP_GROUP_CREATE,
            USER_RELATIONSHIP_GROUP_DELETE,
            USER_RELATIONSHIP_GROUP_UPDATE,
            USER_RELATIONSHIP_GROUP_QUERY,
            USER_FRIEND_REQUEST_CREATE,
            USER_FRIEND_REQUEST_DELETE,
            USER_FRIEND_REQUEST_UPDATE,
            USER_FRIEND_REQUEST_QUERY,
            USER_PERMISSION_GROUP_CREATE,
            USER_PERMISSION_GROUP_DELETE,
            USER_PERMISSION_GROUP_UPDATE,
            USER_PERMISSION_GROUP_QUERY,
            USER_ONLINE_INFO_UPDATE,
            USER_ONLINE_INFO_QUERY);

    public static final Set<AdminPermission> ALL_CONTENT_GROUP = Set.of(GROUP_CREATE,
            GROUP_DELETE,
            GROUP_UPDATE,
            GROUP_QUERY,
            GROUP_BLACKLIST_CREATE,
            GROUP_BLACKLIST_DELETE,
            GROUP_BLACKLIST_UPDATE,
            GROUP_BLACKLIST_QUERY,
            GROUP_INVITATION_CREATE,
            GROUP_INVITATION_DELETE,
            GROUP_INVITATION_UPDATE,
            GROUP_INVITATION_QUERY,
            GROUP_QUESTION_CREATE,
            GROUP_QUESTION_DELETE,
            GROUP_QUESTION_UPDATE,
            GROUP_QUESTION_QUERY,
            GROUP_JOIN_REQUEST_CREATE,
            GROUP_JOIN_REQUEST_DELETE,
            GROUP_JOIN_REQUEST_UPDATE,
            GROUP_JOIN_REQUEST_QUERY,
            GROUP_MEMBER_CREATE,
            GROUP_MEMBER_DELETE,
            GROUP_MEMBER_UPDATE,
            GROUP_MEMBER_QUERY,
            GROUP_TYPE_CREATE,
            GROUP_TYPE_DELETE,
            GROUP_TYPE_UPDATE,
            GROUP_TYPE_QUERY);

    public static final Set<AdminPermission> ALL_CONTENT_CONVERSATION = Set.of(
            CONVERSATION_QUERY,
            CONVERSATION_DELETE,
            CONVERSATION_UPDATE);

    public static final Set<AdminPermission> ALL_CONTENT_MESSAGE = Set.of(
            MESSAGE_CREATE,
            MESSAGE_DELETE,
            MESSAGE_UPDATE,
            MESSAGE_QUERY);

    public static final Set<AdminPermission> ALL_CONTENT_ADMIN = Set.of(
            ADMIN_CREATE,
            ADMIN_DELETE,
            ADMIN_UPDATE,
            ADMIN_QUERY,
            ADMIN_ROLE_CREATE,
            ADMIN_ROLE_DELETE,
            ADMIN_ROLE_UPDATE,
            ADMIN_ROLE_QUERY);

    public static final Set<AdminPermission> ALL_CLUSTER = Set.of(
            CLUSTER_MEMBERS_CREATE,
            CLUSTER_MEMBERS_DELETE,
            CLUSTER_MEMBERS_UPDATE,
            CLUSTER_MEMBERS_QUERY,
            CLUSTER_CONFIG_UPDATE,
            CLUSTER_INFO_QUERY);

    public static final Set<AdminPermission> ALL_CREATE = Set.of(
            USER_CREATE,
            USER_RELATIONSHIP_CREATE,
            USER_RELATIONSHIP_GROUP_CREATE,
            USER_FRIEND_REQUEST_CREATE,
            USER_PERMISSION_GROUP_CREATE,
            GROUP_CREATE,
            GROUP_BLACKLIST_CREATE,
            GROUP_INVITATION_CREATE,
            GROUP_QUESTION_CREATE,
            GROUP_JOIN_REQUEST_CREATE,
            GROUP_MEMBER_CREATE,
            GROUP_TYPE_CREATE,
            MESSAGE_CREATE,
            ADMIN_CREATE,
            ADMIN_ROLE_CREATE,
            CLUSTER_MEMBERS_CREATE);

    public static final Set<AdminPermission> ALL_QUERY = Set.of(
            STATISTICS_USER_QUERY,
            STATISTICS_GROUP_QUERY,
            STATISTICS_MESSAGE_QUERY,
            USER_QUERY,
            USER_RELATIONSHIP_QUERY,
            USER_RELATIONSHIP_GROUP_QUERY,
            USER_FRIEND_REQUEST_QUERY,
            USER_PERMISSION_GROUP_QUERY,
            USER_ONLINE_INFO_QUERY,
            GROUP_QUERY,
            GROUP_BLACKLIST_QUERY,
            GROUP_INVITATION_QUERY,
            GROUP_QUESTION_QUERY,
            GROUP_JOIN_REQUEST_QUERY,
            GROUP_MEMBER_QUERY,
            GROUP_TYPE_QUERY,
            CONVERSATION_QUERY,
            MESSAGE_QUERY,
            ADMIN_QUERY,
            ADMIN_ROLE_QUERY,
            CLUSTER_MEMBERS_QUERY,
            CLUSTER_INFO_QUERY);

}