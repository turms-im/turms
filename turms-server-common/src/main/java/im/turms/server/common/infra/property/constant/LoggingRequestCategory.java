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

package im.turms.server.common.infra.property.constant;

import java.util.Collections;
import java.util.Set;

import lombok.Getter;

import im.turms.server.common.access.client.dto.request.TurmsRequest;
import im.turms.server.common.access.client.dto.request.TurmsRequestTypePool;

/**
 * @author James Chen
 */
public enum LoggingRequestCategory {
    ALL(TurmsRequestTypePool.ALL),
    NONE(Collections.emptySet()),

    CREATE(TurmsRequestTypePool.CREATE),
    DELETE(TurmsRequestTypePool.DELETE),
    UPDATE(TurmsRequestTypePool.UPDATE),
    QUERY(TurmsRequestTypePool.QUERY),

    STORAGE(TurmsRequestTypePool.STORAGE),
    CONVERSATION(TurmsRequestTypePool.CONVERSATION),
    MESSAGE(TurmsRequestTypePool.MESSAGE),
    USER(TurmsRequestTypePool.USER),
    USER_RELATIONSHIP(TurmsRequestTypePool.USER_RELATIONSHIP),
    GROUP(TurmsRequestTypePool.GROUP),
    GROUP_BLOCKLIST(TurmsRequestTypePool.GROUP_BLOCKLIST),
    GROUP_ENROLLMENT(TurmsRequestTypePool.GROUP_ENROLLMENT),
    GROUP_MEMBER(TurmsRequestTypePool.GROUP_MEMBER);

    @Getter
    private final Set<TurmsRequest.KindCase> requestTypes;

    LoggingRequestCategory(Set<TurmsRequest.KindCase> requestTypes) {
        this.requestTypes = requestTypes;
    }

}