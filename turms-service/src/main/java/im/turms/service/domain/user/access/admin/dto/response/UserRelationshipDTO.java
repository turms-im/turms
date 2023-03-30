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

package im.turms.service.domain.user.access.admin.dto.response;

import java.util.Date;
import java.util.Set;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

import im.turms.server.common.domain.common.access.dto.ControllerDTO;
import im.turms.service.domain.user.po.UserRelationship;

/**
 * @author James Chen
 */
public record UserRelationshipDTO(
        Key key,
        Date blockDate,
        Date establishmentDate,
        Set<Integer> groupIndexes
) implements ControllerDTO {

    public UserRelationshipDTO(
            Long ownerId,
            Long relatedUserId,
            Date blockDate,
            Date establishmentDate,
            Set<Integer> groupIndexes) {
        this(new Key(ownerId, relatedUserId), blockDate, establishmentDate, groupIndexes);
    }

    public static UserRelationshipDTO fromDomain(UserRelationship relationship) {
        return fromDomain(relationship, null);
    }

    public static UserRelationshipDTO fromDomain(
            @NotNull UserRelationship relationship,
            @Nullable Set<Integer> groupIndexes) {
        return new UserRelationshipDTO(
                relationship.getKey()
                        .getOwnerId(),
                relationship.getKey()
                        .getRelatedUserId(),
                relationship.getBlockDate(),
                relationship.getEstablishmentDate(),
                groupIndexes);
    }

    public static record Key(
            Long ownerId,
            Long relatedUserId
    ) {
    }
}