/*
 * Copyright (C) 2019 The Turms Project
 * https://github.com/turms-im/turms
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package im.turms.plugin.antispam.property;

import com.google.protobuf.Descriptors;
import im.turms.server.common.access.client.dto.request.TurmsRequest;
import lombok.Getter;

/**
 * @author James Chen
 */
public enum TextType {

    // Group

    CREATE_GROUP_REQUEST_NAME("create_group_request", "name"),
    CREATE_GROUP_REQUEST_INTRO("create_group_request", "intro"),
    CREATE_GROUP_REQUEST_ANNOUNCEMENT("create_group_request", "announcement"),

    CREATE_GROUP_INVITATION_REQUEST_CONTENT("create_group_invitation_request", "content"),

    CREATE_GROUP_JOIN_QUESTION_REQUEST_QUESTION("create_group_join_question_request", "question"),

    CREATE_GROUP_JOIN_REQUEST_REQUEST_CONTENT("create_group_join_request_request", "content"),
    UPDATE_GROUP_JOIN_QUESTION_REQUEST_QUESTION("update_group_join_question_request", "question"),

    CREATE_GROUP_MEMBER_REQUEST_NAME("create_group_member_request", "name"),
    UPDATE_GROUP_MEMBER_REQUEST_NAME("update_group_member_request", "name"),

    UPDATE_GROUP_REQUEST_GROUP_NAME("update_group_request", "group_name"),
    UPDATE_GROUP_REQUEST_INTRO("update_group_request", "intro"),
    UPDATE_GROUP_REQUEST_ANNOUNCEMENT("update_group_request", "announcement"),

    // Message

    CREATE_MESSAGE_REQUEST_TEXT("create_message_request", "text"),
    UPDATE_MESSAGE_REQUEST_TEXT("update_message_request", "text"),

    // User

    CREATE_FRIEND_REQUEST_REQUEST_CONTEXT("create_friend_request_request", "content"),
    CREATE_RELATIONSHIP_GROUP_REQUEST_NAME("create_relationship_group_request", "name"),
    UPDATE_FRIEND_REQUEST_REQUEST_REASON("update_friend_request_request", "reason"),
    UPDATE_RELATIONSHIP_GROUP_REQUEST_NEW_NAME("update_relationship_group_request", "new_name"),
    UPDATE_USER_REQUEST_NAME("update_user_request", "name"),
    UPDATE_USER_REQUEST_INTRO("update_user_request", "intro");

    @Getter
    private final TurmsRequest.KindCase type;
    @Getter
    private final Descriptors.FieldDescriptor requestFieldDescriptor;
    @Getter
    private final Descriptors.FieldDescriptor fieldDescriptor;

    TextType(String requestFieldName, String fieldName) {
        requestFieldDescriptor = TurmsRequest.getDescriptor().findFieldByName(requestFieldName);
        if (requestFieldDescriptor == null) {
            throw new RuntimeException("Cannot find the request %s in TurmsRequest"
                    .formatted(requestFieldName));
        }
        fieldDescriptor = requestFieldDescriptor.getMessageType().findFieldByName(fieldName);
        if (fieldDescriptor == null) {
            throw new RuntimeException("Cannot find the field %s in the request %s"
                    .formatted(fieldName, requestFieldDescriptor.getFullName()));
        }
        if (fieldDescriptor.getJavaType() != Descriptors.FieldDescriptor.JavaType.STRING) {
            throw new RuntimeException("The field %s isn't String in the request %s"
                    .formatted(fieldName, requestFieldDescriptor.getFullName()));
        }
        type = TurmsRequest.KindCase.forNumber(requestFieldDescriptor.getNumber());
    }

}
