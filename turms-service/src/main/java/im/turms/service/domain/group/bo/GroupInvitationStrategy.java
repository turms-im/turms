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

package im.turms.service.domain.group.bo;

/**
 * @author James Chen
 */
public enum GroupInvitationStrategy {
    ALL_REQUIRING_APPROVAL(true),
    OWNER_MANAGER_MEMBER_REQUIRING_APPROVAL(true),
    OWNER_MANAGER_REQUIRING_APPROVAL(true),
    OWNER_REQUIRING_APPROVAL(true),
    ALL(false),
    OWNER_MANAGER_MEMBER(false),
    OWNER_MANAGER(false),
    OWNER(false);

    private final boolean requiresApproval;

    GroupInvitationStrategy(boolean requiresApproval) {
        this.requiresApproval = requiresApproval;
    }

    /**
     * If requiring approval, the group admin needs to send an invitation to the invitee. Otherwise,
     * the group admin can add any user to the group without their approval.
     */
    public boolean requiresApproval() {
        return requiresApproval;
    }

}