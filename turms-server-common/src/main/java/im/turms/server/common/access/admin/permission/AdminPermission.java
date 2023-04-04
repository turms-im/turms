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

package im.turms.server.common.access.admin.permission;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import lombok.Getter;

import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.lang.ClassUtil;
import im.turms.server.common.infra.lang.StringUtil;

/**
 * @author James Chen
 */
public enum AdminPermission {
    FLIGHT_RECORDING_CREATE(Groups.FLIGHT_RECORDING),
    FLIGHT_RECORDING_DELETE(Groups.FLIGHT_RECORDING),
    FLIGHT_RECORDING_UPDATE(Groups.FLIGHT_RECORDING),
    FLIGHT_RECORDING_QUERY(Groups.FLIGHT_RECORDING),

    // region business
    USER_CREATE(Groups.USER),
    USER_DELETE(Groups.USER),
    USER_UPDATE(Groups.USER),
    USER_QUERY(Groups.USER),

    USER_RELATIONSHIP_CREATE(Groups.USER_RELATIONSHIP),
    USER_RELATIONSHIP_DELETE(Groups.USER_RELATIONSHIP),
    USER_RELATIONSHIP_UPDATE(Groups.USER_RELATIONSHIP),
    USER_RELATIONSHIP_QUERY(Groups.USER_RELATIONSHIP),

    USER_RELATIONSHIP_GROUP_CREATE(Groups.USER_RELATIONSHIP_GROUP),
    USER_RELATIONSHIP_GROUP_DELETE(Groups.USER_RELATIONSHIP_GROUP),
    USER_RELATIONSHIP_GROUP_UPDATE(Groups.USER_RELATIONSHIP_GROUP),
    USER_RELATIONSHIP_GROUP_QUERY(Groups.USER_RELATIONSHIP_GROUP),

    USER_FRIEND_REQUEST_CREATE(Groups.USER_FRIEND_REQUEST),
    USER_FRIEND_REQUEST_DELETE(Groups.USER_FRIEND_REQUEST),
    USER_FRIEND_REQUEST_UPDATE(Groups.USER_FRIEND_REQUEST),
    USER_FRIEND_REQUEST_QUERY(Groups.USER_FRIEND_REQUEST),

    USER_PERMISSION_GROUP_CREATE(Groups.USER_PERMISSION_GROUP),
    USER_PERMISSION_GROUP_DELETE(Groups.USER_PERMISSION_GROUP),
    USER_PERMISSION_GROUP_UPDATE(Groups.USER_PERMISSION_GROUP),
    USER_PERMISSION_GROUP_QUERY(Groups.USER_PERMISSION_GROUP),

    USER_ONLINE_INFO_UPDATE(Groups.USER_ONLINE_INFO),
    USER_ONLINE_INFO_QUERY(Groups.USER_ONLINE_INFO),

    GROUP_CREATE(Groups.GROUP),
    GROUP_DELETE(Groups.GROUP),
    GROUP_UPDATE(Groups.GROUP),
    GROUP_QUERY(Groups.GROUP),

    GROUP_BLOCKLIST_CREATE(Groups.GROUP_BLOCKLIST),
    GROUP_BLOCKLIST_DELETE(Groups.GROUP_BLOCKLIST),
    GROUP_BLOCKLIST_UPDATE(Groups.GROUP_BLOCKLIST),
    GROUP_BLOCKLIST_QUERY(Groups.GROUP_BLOCKLIST),

    GROUP_INVITATION_CREATE(Groups.GROUP_INVITATION),
    GROUP_INVITATION_DELETE(Groups.GROUP_INVITATION),
    GROUP_INVITATION_UPDATE(Groups.GROUP_INVITATION),
    GROUP_INVITATION_QUERY(Groups.GROUP_INVITATION),

    GROUP_QUESTION_CREATE(Groups.GROUP_QUESTION),
    GROUP_QUESTION_DELETE(Groups.GROUP_QUESTION),
    GROUP_QUESTION_UPDATE(Groups.GROUP_QUESTION),
    GROUP_QUESTION_QUERY(Groups.GROUP_QUESTION),

    GROUP_JOIN_REQUEST_CREATE(Groups.GROUP_JOIN_REQUEST),
    GROUP_JOIN_REQUEST_DELETE(Groups.GROUP_JOIN_REQUEST),
    GROUP_JOIN_REQUEST_UPDATE(Groups.GROUP_JOIN_REQUEST),
    GROUP_JOIN_REQUEST_QUERY(Groups.GROUP_JOIN_REQUEST),

    GROUP_MEMBER_UPDATE(Groups.GROUP_MEMBER),
    GROUP_MEMBER_CREATE(Groups.GROUP_MEMBER),
    GROUP_MEMBER_DELETE(Groups.GROUP_MEMBER),
    GROUP_MEMBER_QUERY(Groups.GROUP_MEMBER),

    GROUP_TYPE_CREATE(Groups.GROUP_TYPE),
    GROUP_TYPE_DELETE(Groups.GROUP_TYPE),
    GROUP_TYPE_UPDATE(Groups.GROUP_TYPE),
    GROUP_TYPE_QUERY(Groups.GROUP_TYPE),

    CONVERSATION_QUERY(Groups.CONVERSATION),
    CONVERSATION_DELETE(Groups.CONVERSATION),
    CONVERSATION_UPDATE(Groups.CONVERSATION),

    MESSAGE_CREATE(Groups.MESSAGE),
    MESSAGE_DELETE(Groups.MESSAGE),
    MESSAGE_UPDATE(Groups.MESSAGE),
    MESSAGE_QUERY(Groups.MESSAGE),
    // endregion

    // region session
    SESSION_DELETE(Groups.SESSION),
    // endregion

    // region business - statistics
    STATISTICS_USER_QUERY(Groups.STATISTICS),
    STATISTICS_GROUP_QUERY(Groups.STATISTICS),
    STATISTICS_MESSAGE_QUERY(Groups.STATISTICS),
    // endregion

    // region admin
    ADMIN_CREATE(Groups.ADMIN),
    ADMIN_DELETE(Groups.ADMIN),
    ADMIN_UPDATE(Groups.ADMIN),
    ADMIN_QUERY(Groups.ADMIN),

    ADMIN_ROLE_CREATE(Groups.ADMIN_ROLE),
    ADMIN_ROLE_DELETE(Groups.ADMIN_ROLE),
    ADMIN_ROLE_UPDATE(Groups.ADMIN_ROLE),
    ADMIN_ROLE_QUERY(Groups.ADMIN_ROLE),

    ADMIN_PERMISSION_QUERY(Groups.ADMIN_PERMISSION),
    // endregion

    // region client - blocklist
    CLIENT_BLOCKLIST_CREATE(Groups.CLIENT_BLOCKLIST),
    CLIENT_BLOCKLIST_DELETE(Groups.CLIENT_BLOCKLIST),
    CLIENT_BLOCKLIST_QUERY(Groups.CLIENT_BLOCKLIST),
    // endregion

    // region client - request
    CLIENT_REQUEST_CREATE(Groups.CLIENT_REQUEST),
    // endregion

    // region cluster
    CLUSTER_MEMBER_CREATE(Groups.CLUSTER_MEMBER),
    CLUSTER_MEMBER_DELETE(Groups.CLUSTER_MEMBER),
    CLUSTER_MEMBER_UPDATE(Groups.CLUSTER_MEMBER),
    CLUSTER_MEMBER_QUERY(Groups.CLUSTER_MEMBER),

    CLUSTER_LEADER_UPDATE(Groups.CLUSTER_LEADER),
    CLUSTER_LEADER_QUERY(Groups.CLUSTER_LEADER),

    CLUSTER_SETTING_UPDATE(Groups.CLUSTER_SETTING),
    CLUSTER_SETTING_QUERY(Groups.CLUSTER_SETTING),
    // endregion

    // region node - plugin
    PLUGIN_CREATE(Groups.PLUGIN),
    PLUGIN_DELETE(Groups.PLUGIN),
    PLUGIN_UPDATE(Groups.PLUGIN),
    PLUGIN_QUERY(Groups.PLUGIN),
    // endregion

    // region node - others
    SHUTDOWN(Groups.LIFECYCLE);
    // endregion

    public static final String SUFFIX_CREATE = "_CREATE";
    public static final String SUFFIX_DELETE = "_DELETE";
    public static final String SUFFIX_UPDATE = "_UPDATE";
    public static final String SUFFIX_QUERY = "_QUERY";

    public static final Set<AdminPermission> ALL =
            Set.of(ClassUtil.getSharedEnumConstants(AdminPermission.class));

    private static final List<String> NAMES = CollectionUtil.transformAsList(ALL, Enum::name);

    @Getter
    private final String group;

    AdminPermission(String group) {
        this.group = group;
    }

    public static Set<AdminPermission> matchPermission(String pattern) {
        Set<AdminPermission> permissions = CollectionUtil.newSetWithExpectedSize(8);
        for (String name : NAMES) {
            if (StringUtil.matchLatin1(name, pattern)) {
                permissions.add(AdminPermission.valueOf(name));
            }
        }
        return permissions;
    }

    public static Set<AdminPermission> matchPermissions(Set<String> patterns) {
        int count = patterns.size();
        if (count == 0) {
            return Collections.emptySet();
        }
        Set<AdminPermission> permissions = CollectionUtil.newSetWithExpectedSize(count * 8);
        for (String pattern : patterns) {
            for (String name : NAMES) {
                if (StringUtil.matchLatin1(name, pattern)) {
                    permissions.add(AdminPermission.valueOf(name));
                }
            }
        }
        return permissions;
    }

    private static class Groups {
        private static final String FLIGHT_RECORDING = "FLIGHT_RECORDING";

        private static final String USER = "USER";
        private static final String USER_RELATIONSHIP = "USER_RELATIONSHIP";
        private static final String USER_RELATIONSHIP_GROUP = "USER_RELATIONSHIP_GROUP";
        private static final String USER_FRIEND_REQUEST = "USER_FRIEND_REQUEST";
        private static final String USER_PERMISSION_GROUP = "USER_PERMISSION_GROUP";
        private static final String USER_ONLINE_INFO = "USER_ONLINE_INFO";

        private static final String GROUP = "GROUP";
        private static final String GROUP_BLOCKLIST = "GROUP_BLOCKLIST";
        private static final String GROUP_INVITATION = "GROUP_INVITATION";
        private static final String GROUP_QUESTION = "GROUP_QUESTION";
        private static final String GROUP_JOIN_REQUEST = "GROUP_JOIN_REQUEST";
        private static final String GROUP_MEMBER = "GROUP_MEMBER";
        private static final String GROUP_TYPE = "GROUP_TYPE";

        private static final String CONVERSATION = "CONVERSATION";

        private static final String MESSAGE = "MESSAGE";

        private static final String SESSION = "SESSION";

        private static final String STATISTICS = "STATISTICS";

        private static final String ADMIN = "ADMIN";
        private static final String ADMIN_ROLE = "ADMIN_ROLE";
        private static final String ADMIN_PERMISSION = "ADMIN_PERMISSION";

        private static final String CLIENT_BLOCKLIST = "CLIENT_BLOCKLIST";
        private static final String CLIENT_REQUEST = "CLIENT_REQUEST";

        private static final String CLUSTER_MEMBER = "CLUSTER_MEMBER";
        private static final String CLUSTER_LEADER = "CLUSTER_LEADER";
        private static final String CLUSTER_SETTING = "CLUSTER_SETTING";

        private static final String PLUGIN = "PLUGIN";

        private static final String LIFECYCLE = "LIFECYCLE";
    }
}