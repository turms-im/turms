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

package im.turms.gateway.access.client.common.authorization.policy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import lombok.Getter;

import im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase;
import im.turms.server.common.infra.collection.CollectionUtil;

import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.CHECK_GROUP_JOIN_QUESTIONS_ANSWERS_REQUEST;
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
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.QUERY_MESSAGE_ATTACHMENT_INFOS_REQUEST;
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
import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.UPDATE_MESSAGE_ATTACHMENT_INFO_REQUEST;
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
@Getter
public enum PolicyStatementResource {
    // region user
    USER(Collections.emptySet(), Collections.emptySet(), Set.of(UPDATE_USER_REQUEST),
            Collections.emptySet()),
    USER_LOCATION(Collections.emptySet(), Collections.emptySet(),
            Set.of(UPDATE_USER_LOCATION_REQUEST), Collections.emptySet()),
    USER_ONLINE_STATUS(Collections.emptySet(), Collections.emptySet(),
            Set.of(UPDATE_USER_ONLINE_STATUS_REQUEST), Set.of(QUERY_USER_ONLINE_STATUSES_REQUEST)),
    USER_PROFILE(Collections.emptySet(), Collections.emptySet(), Collections.emptySet(),
            Set.of(QUERY_USER_PROFILES_REQUEST)),
    NEARBY_USER(Collections.emptySet(), Collections.emptySet(), Collections.emptySet(),
            Set.of(QUERY_NEARBY_USERS_REQUEST)),
    // endregion
    // region relationship
    RELATIONSHIP(Set.of(CREATE_RELATIONSHIP_REQUEST), Set.of(DELETE_RELATIONSHIP_REQUEST),
            Set.of(UPDATE_RELATIONSHIP_REQUEST),
            Set.of(QUERY_RELATIONSHIPS_REQUEST, QUERY_RELATED_USER_IDS_REQUEST)),
    RELATIONSHIP_GROUP(Set.of(CREATE_RELATIONSHIP_GROUP_REQUEST),
            Set.of(DELETE_RELATIONSHIP_GROUP_REQUEST), Set.of(UPDATE_RELATIONSHIP_GROUP_REQUEST),
            Set.of(QUERY_RELATIONSHIP_GROUPS_REQUEST)),
    FRIEND_REQUEST(Set.of(KindCase.CREATE_FRIEND_REQUEST_REQUEST), Collections.emptySet(),
            Set.of(UPDATE_FRIEND_REQUEST_REQUEST), Set.of(QUERY_FRIEND_REQUESTS_REQUEST)),
    // endregion
    // region group
    GROUP(Set.of(CREATE_GROUP_REQUEST), Set.of(DELETE_GROUP_REQUEST), Set.of(UPDATE_GROUP_REQUEST),
            Set.of(QUERY_GROUPS_REQUEST)),
    GROUP_BLOCKED_USER(Set.of(CREATE_GROUP_BLOCKED_USER_REQUEST),
            Set.of(DELETE_GROUP_BLOCKED_USER_REQUEST), Collections.emptySet(),
            Set.of(QUERY_GROUP_BLOCKED_USER_IDS_REQUEST, QUERY_GROUP_BLOCKED_USER_INFOS_REQUEST)),
    GROUP_INVITATION(Set.of(CREATE_GROUP_INVITATION_REQUEST),
            Set.of(DELETE_GROUP_INVITATION_REQUEST), Collections.emptySet(),
            Set.of(QUERY_GROUP_INVITATIONS_REQUEST)),
    GROUP_JOIN_QUESTION(Set.of(CREATE_GROUP_JOIN_QUESTIONS_REQUEST),
            Set.of(DELETE_GROUP_JOIN_QUESTIONS_REQUEST), Set.of(UPDATE_GROUP_JOIN_QUESTION_REQUEST),
            Set.of(QUERY_GROUP_JOIN_QUESTIONS_REQUEST)),
    GROUP_JOIN_QUESTION_ANSWER(Collections.emptySet(), Collections.emptySet(),
            Collections.emptySet(), Set.of(CHECK_GROUP_JOIN_QUESTIONS_ANSWERS_REQUEST)),
    GROUP_JOIN_REQUEST(Set.of(CREATE_GROUP_JOIN_REQUEST_REQUEST),
            Set.of(DELETE_GROUP_JOIN_REQUEST_REQUEST), Collections.emptySet(),
            Set.of(QUERY_GROUP_JOIN_REQUESTS_REQUEST)),
    GROUP_MEMBER(Set.of(CREATE_GROUP_MEMBERS_REQUEST), Set.of(DELETE_GROUP_MEMBERS_REQUEST),
            Set.of(UPDATE_GROUP_MEMBER_REQUEST), Set.of(QUERY_GROUP_MEMBERS_REQUEST)),
    JOINED_GROUP(Collections.emptySet(), Collections.emptySet(), Collections.emptySet(),
            Set.of(QUERY_JOINED_GROUP_IDS_REQUEST, QUERY_JOINED_GROUP_INFOS_REQUEST)),
    // endregion
    // region message
    MESSAGE(Set.of(CREATE_MESSAGE_REQUEST), Collections.emptySet(), Set.of(UPDATE_MESSAGE_REQUEST),
            Set.of(QUERY_MESSAGES_REQUEST)),
    // endregion
    // region conversation
    CONVERSATION(Collections.emptySet(), Collections.emptySet(),
            Set.of(UPDATE_CONVERSATION_REQUEST), Set.of(QUERY_CONVERSATIONS_REQUEST)),
    // endregion
    // region typing status
    TYPING_STATUS(Collections.emptySet(), Collections.emptySet(),
            Set.of(UPDATE_TYPING_STATUS_REQUEST), Collections.emptySet()),
    // endregion
    // region storage
    RESOURCE(Collections.emptySet(), Set.of(DELETE_RESOURCE_REQUEST),
            Set.of(UPDATE_MESSAGE_ATTACHMENT_INFO_REQUEST),
            Set.of(QUERY_RESOURCE_DOWNLOAD_INFO_REQUEST,
                    QUERY_RESOURCE_UPLOAD_INFO_REQUEST,
                    QUERY_MESSAGE_ATTACHMENT_INFOS_REQUEST));
    // endregion

    public static final Set<PolicyStatementResource> ALL =
            EnumSet.allOf(PolicyStatementResource.class);
    public static final Set<KindCase> ALL_REQUEST_TYPES;

    private final Set<KindCase> allRequestTypes;
    private final Set<KindCase> requestTypesForCreating;
    private final Set<KindCase> requestTypesForDeleting;
    private final Set<KindCase> requestTypesForUpdating;
    private final Set<KindCase> requestTypesForQuerying;

    static {
        List<Collection<KindCase>> requestTypes = new ArrayList<>(ALL.size());
        for (PolicyStatementResource resource : ALL) {
            requestTypes.add(resource.allRequestTypes);
        }
        ALL_REQUEST_TYPES = CollectionUtil.toImmutableSet(CollectionUtil.newSet(requestTypes));
    }

    PolicyStatementResource(
            Set<KindCase> requestTypesForCreating,
            Set<KindCase> requestTypesForDeleting,
            Set<KindCase> requestTypesForUpdating,
            Set<KindCase> requestTypesForQuerying) {
        this.requestTypesForCreating = requestTypesForCreating;
        this.requestTypesForDeleting = requestTypesForDeleting;
        this.requestTypesForUpdating = requestTypesForUpdating;
        this.requestTypesForQuerying = requestTypesForQuerying;
        allRequestTypes =
                CollectionUtil.toImmutableSet(CollectionUtil.newSet(requestTypesForCreating,
                        requestTypesForDeleting,
                        requestTypesForUpdating,
                        requestTypesForQuerying));
    }
}