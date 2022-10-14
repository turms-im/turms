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

import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.lang.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * @author James Chen
 */
public enum AdminPermission {
    FLIGHT_RECORDING_CREATE,
    FLIGHT_RECORDING_DELETE,
    FLIGHT_RECORDING_UPDATE,
    FLIGHT_RECORDING_QUERY,

    //region business
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

    GROUP_BLOCKLIST_CREATE,
    GROUP_BLOCKLIST_DELETE,
    GROUP_BLOCKLIST_UPDATE,
    GROUP_BLOCKLIST_QUERY,

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

    CONVERSATION_QUERY,
    CONVERSATION_DELETE,
    CONVERSATION_UPDATE,

    MESSAGE_CREATE,
    MESSAGE_DELETE,
    MESSAGE_UPDATE,
    MESSAGE_QUERY,
    //endregion

    //region business - statistics
    STATISTICS_USER_QUERY,
    STATISTICS_GROUP_QUERY,
    STATISTICS_MESSAGE_QUERY,
    //endregion

    //region admin
    ADMIN_CREATE,
    ADMIN_DELETE,
    ADMIN_UPDATE,
    ADMIN_QUERY,

    ADMIN_ROLE_CREATE,
    ADMIN_ROLE_DELETE,
    ADMIN_ROLE_UPDATE,
    ADMIN_ROLE_QUERY,
    //endregion

    //region client - blocklist
    CLIENT_BLOCKLIST_CREATE,
    CLIENT_BLOCKLIST_DELETE,
    CLIENT_BLOCKLIST_QUERY,
    //endregion

    //region client - request
    CLIENT_REQUEST_CREATE,
    //endregion

    //region cluster
    CLUSTER_MEMBER_CREATE,
    CLUSTER_MEMBER_DELETE,
    CLUSTER_MEMBER_UPDATE,
    CLUSTER_MEMBER_QUERY,

    CLUSTER_LEADER_UPDATE,
    CLUSTER_LEADER_QUERY,

    CLUSTER_SETTING_UPDATE,
    CLUSTER_SETTING_QUERY,
    //endregion

    //region node - plugin
    PLUGIN_CREATE,
    PLUGIN_DELETE,
    PLUGIN_UPDATE,
    PLUGIN_QUERY,
    //endregion

    //region node - others
    SHUTDOWN;
    //endregion

    public static final String SUFFIX_CREATE = "_CREATE";
    public static final String SUFFIX_DELETE = "_DELETE";
    public static final String SUFFIX_UPDATE = "_UPDATE";
    public static final String SUFFIX_QUERY = "_QUERY";

    public static final Set<AdminPermission> ALL = Set.of(AdminPermission.values());

    private static final List<String> NAMES;

    static {
        AdminPermission[] permissions = AdminPermission.values();
        NAMES = new ArrayList<>(permissions.length);
        for (AdminPermission value : permissions) {
            NAMES.add(value.name());
        }
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

}