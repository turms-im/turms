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

package im.turms.server.common.access.client.dto.request;

import java.util.Set;

import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.lang.ClassUtil;
import im.turms.server.common.infra.lang.StringUtil;

import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.CHECK_GROUP_JOIN_QUESTIONS_ANSWERS_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.CREATE_FRIEND_REQUEST_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.CREATE_GROUP_BLOCKED_USER_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.CREATE_GROUP_INVITATION_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.CREATE_GROUP_JOIN_QUESTIONS_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.CREATE_GROUP_JOIN_REQUEST_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.CREATE_GROUP_MEMBERS_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.CREATE_GROUP_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.CREATE_MESSAGE_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.CREATE_RELATIONSHIP_GROUP_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.CREATE_RELATIONSHIP_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.DELETE_GROUP_BLOCKED_USER_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.DELETE_GROUP_INVITATION_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.DELETE_GROUP_JOIN_QUESTIONS_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.DELETE_GROUP_JOIN_REQUEST_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.DELETE_GROUP_MEMBERS_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.DELETE_GROUP_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.DELETE_RELATIONSHIP_GROUP_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.DELETE_RELATIONSHIP_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.DELETE_RESOURCE_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.QUERY_CONVERSATIONS_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.QUERY_FRIEND_REQUESTS_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.QUERY_GROUPS_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.QUERY_GROUP_BLOCKED_USER_IDS_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.QUERY_GROUP_BLOCKED_USER_INFOS_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.QUERY_GROUP_INVITATIONS_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.QUERY_GROUP_JOIN_QUESTIONS_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.QUERY_GROUP_JOIN_REQUESTS_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.QUERY_GROUP_MEMBERS_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.QUERY_JOINED_GROUP_IDS_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.QUERY_JOINED_GROUP_INFOS_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.QUERY_MESSAGES_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.QUERY_NEARBY_USERS_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.QUERY_RELATED_USER_IDS_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.QUERY_RELATIONSHIPS_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.QUERY_RELATIONSHIP_GROUPS_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.QUERY_RESOURCE_DOWNLOAD_INFO_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.QUERY_RESOURCE_UPLOAD_INFO_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.QUERY_USER_ONLINE_STATUSES_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.QUERY_USER_PROFILES_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.UPDATE_CONVERSATION_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.UPDATE_FRIEND_REQUEST_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.UPDATE_GROUP_JOIN_QUESTION_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.UPDATE_GROUP_MEMBER_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.UPDATE_GROUP_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.UPDATE_MESSAGE_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.UPDATE_RELATIONSHIP_GROUP_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.UPDATE_RELATIONSHIP_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.UPDATE_TYPING_STATUS_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.UPDATE_USER_LOCATION_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.UPDATE_USER_ONLINE_STATUS_REQUEST;
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.UPDATE_USER_REQUEST;

/**
 * @author James Chen
 */
public final class TurmsRequestTypePool {

    private TurmsRequestTypePool() {
    }

    public static final Set<TurmsRequest.KindCase> ALL;

    public static final Set<TurmsRequest.KindCase> CREATE;
    public static final Set<TurmsRequest.KindCase> DELETE;
    public static final Set<TurmsRequest.KindCase> UPDATE;
    public static final Set<TurmsRequest.KindCase> QUERY;

    public static final Set<TurmsRequest.KindCase> STORAGE = Set.of(DELETE_RESOURCE_REQUEST,
            QUERY_RESOURCE_DOWNLOAD_INFO_REQUEST,
            QUERY_RESOURCE_UPLOAD_INFO_REQUEST);

    public static final Set<TurmsRequest.KindCase> CONVERSATION =
            Set.of(QUERY_CONVERSATIONS_REQUEST,
                    UPDATE_CONVERSATION_REQUEST,
                    UPDATE_TYPING_STATUS_REQUEST);

    public static final Set<TurmsRequest.KindCase> MESSAGE =
            Set.of(CREATE_MESSAGE_REQUEST, QUERY_MESSAGES_REQUEST, UPDATE_MESSAGE_REQUEST);

    public static final Set<TurmsRequest.KindCase> USER = Set.of(QUERY_USER_PROFILES_REQUEST,
            QUERY_NEARBY_USERS_REQUEST,
            QUERY_USER_ONLINE_STATUSES_REQUEST,
            UPDATE_USER_LOCATION_REQUEST,
            UPDATE_USER_ONLINE_STATUS_REQUEST,
            UPDATE_USER_REQUEST);

    public static final Set<TurmsRequest.KindCase> USER_RELATIONSHIP =
            Set.of(CREATE_FRIEND_REQUEST_REQUEST,
                    CREATE_RELATIONSHIP_GROUP_REQUEST,
                    CREATE_RELATIONSHIP_REQUEST,
                    DELETE_RELATIONSHIP_GROUP_REQUEST,
                    DELETE_RELATIONSHIP_REQUEST,
                    QUERY_FRIEND_REQUESTS_REQUEST,
                    QUERY_RELATED_USER_IDS_REQUEST,
                    QUERY_RELATIONSHIP_GROUPS_REQUEST,
                    QUERY_RELATIONSHIPS_REQUEST,
                    UPDATE_FRIEND_REQUEST_REQUEST,
                    UPDATE_RELATIONSHIP_GROUP_REQUEST,
                    UPDATE_RELATIONSHIP_REQUEST);

    public static final Set<TurmsRequest.KindCase> GROUP = Set.of(CREATE_GROUP_REQUEST,
            DELETE_GROUP_REQUEST,
            QUERY_GROUPS_REQUEST,
            QUERY_JOINED_GROUP_IDS_REQUEST,
            QUERY_JOINED_GROUP_INFOS_REQUEST,
            UPDATE_GROUP_REQUEST);

    public static final Set<TurmsRequest.KindCase> GROUP_BLOCKLIST =
            Set.of(CREATE_GROUP_BLOCKED_USER_REQUEST,
                    DELETE_GROUP_BLOCKED_USER_REQUEST,
                    QUERY_GROUP_BLOCKED_USER_IDS_REQUEST,
                    QUERY_GROUP_BLOCKED_USER_INFOS_REQUEST);

    public static final Set<TurmsRequest.KindCase> GROUP_ENROLLMENT =
            Set.of(CHECK_GROUP_JOIN_QUESTIONS_ANSWERS_REQUEST,
                    CREATE_GROUP_INVITATION_REQUEST,
                    CREATE_GROUP_JOIN_REQUEST_REQUEST,
                    CREATE_GROUP_JOIN_QUESTIONS_REQUEST,
                    DELETE_GROUP_INVITATION_REQUEST,
                    DELETE_GROUP_JOIN_REQUEST_REQUEST,
                    DELETE_GROUP_JOIN_QUESTIONS_REQUEST,
                    QUERY_GROUP_INVITATIONS_REQUEST,
                    QUERY_GROUP_JOIN_REQUESTS_REQUEST,
                    QUERY_GROUP_JOIN_QUESTIONS_REQUEST,
                    UPDATE_GROUP_JOIN_QUESTION_REQUEST);

    public static final Set<TurmsRequest.KindCase> GROUP_MEMBER =
            Set.of(CREATE_GROUP_MEMBERS_REQUEST,
                    DELETE_GROUP_MEMBERS_REQUEST,
                    QUERY_GROUP_MEMBERS_REQUEST,
                    UPDATE_GROUP_MEMBER_REQUEST);

    static {
        TurmsRequest.KindCase[] values =
                ClassUtil.getSharedEnumConstants(TurmsRequest.KindCase.class);
        CREATE = CollectionUtil.toImmutableSet(StringUtil.findMatchesLatin1(values, "CREATE_*"));
        DELETE = CollectionUtil.toImmutableSet(StringUtil.findMatchesLatin1(values, "DELETE_*"));
        UPDATE = CollectionUtil.toImmutableSet(StringUtil.findMatchesLatin1(values, "UPDATE_*"));
        QUERY = CollectionUtil.toImmutableSet(
                CollectionUtil.newSet(StringUtil.findMatchesLatin1(values, "QUERY_*"),
                        StringUtil.findMatchesLatin1(values, "CHECK_*")));
        ALL = CollectionUtil.newSet(CREATE, DELETE, UPDATE, QUERY);
    }

}