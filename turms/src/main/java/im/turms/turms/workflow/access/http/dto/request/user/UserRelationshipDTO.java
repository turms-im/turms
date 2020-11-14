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

package im.turms.turms.workflow.access.http.dto.request.user;

import im.turms.turms.workflow.dao.domain.UserRelationship;
import lombok.Data;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

/**
 * @author James Chen
 */
@Data
public final class UserRelationshipDTO {

    private final Key key;
    private final Boolean isBlocked;
    private final Date establishmentDate;
    private final Set<Integer> groupIndexes;

    public UserRelationshipDTO(Long ownerId, Long relatedUserId, Boolean isBlocked, Date establishmentDate, Set<Integer> groupIndexes) {
        this.key = new Key(ownerId, relatedUserId);
        this.isBlocked = isBlocked;
        this.establishmentDate = establishmentDate;
        this.groupIndexes = groupIndexes;
    }

    public static UserRelationshipDTO fromDomain(UserRelationship relationship) {
        return fromDomain(relationship, null);
    }

    public static UserRelationshipDTO fromDomain(@NotNull UserRelationship relationship, @Nullable Set<Integer> groupIndexes) {
        return new UserRelationshipDTO(
                relationship.getKey().getOwnerId(),
                relationship.getKey().getRelatedUserId(),
                relationship.getIsBlocked(),
                relationship.getEstablishmentDate(),
                groupIndexes);
    }

    @Data
    public static final class Key {
        private final Long ownerId;
        private final Long relatedUserId;
    }
}