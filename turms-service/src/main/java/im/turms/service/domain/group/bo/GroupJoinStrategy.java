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
public enum GroupJoinStrategy {
    /**
     * Add the requester as a group member when the server received a membership request
     */
    MEMBERSHIP_REQUEST,
    /**
     * A user can only join these groups via invitations
     */
    INVITATION,
    /**
     * A user is required to answer questions to join
     */
    QUESTION,
    /**
     * A user sends a join request to the server, and can only join the group automatically when the
     * request is approved
     */
    JOIN_REQUEST
}