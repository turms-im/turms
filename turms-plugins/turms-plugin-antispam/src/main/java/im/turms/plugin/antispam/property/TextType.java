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

package im.turms.plugin.antispam.property;

import com.google.protobuf.Descriptors;
import lombok.Getter;

import im.turms.server.common.access.client.dto.request.TurmsRequest;

/**
 * @author James Chen
 */
public enum TextType {

    // Group

    CREATE_GROUP_REQUEST_NAME("create_group_request", "name"),
    CREATE_GROUP_REQUEST_INTRO("create_group_request", "intro"),
    CREATE_GROUP_REQUEST_ANNOUNCEMENT("create_group_request", "announcement"),

    CREATE_GROUP_INVITATION_REQUEST_CONTENT("create_group_invitation_request", "content"),

    CREATE_GROUP_JOIN_QUESTION_REQUEST_QUESTION("create_group_join_questions_request", "questions",
            "question"),

    CREATE_GROUP_JOIN_REQUEST_REQUEST_CONTENT("create_group_join_request_request", "content"),
    UPDATE_GROUP_JOIN_QUESTION_REQUEST_QUESTION("update_group_join_question_request", "question"),

    CREATE_GROUP_MEMBER_REQUEST_NAME("create_group_members_request", "name"),
    UPDATE_GROUP_MEMBER_REQUEST_NAME("update_group_member_request", "name"),

    UPDATE_GROUP_REQUEST_NAME("update_group_request", "name"),
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
    @Getter
    private final Descriptors.FieldDescriptor subfieldDescriptor;

    TextType(String requestFieldName, String fieldName, String subfieldName) {
        requestFieldDescriptor = TurmsRequest.getDescriptor()
                .findFieldByName(requestFieldName);
        if (requestFieldDescriptor == null) {
            throw new IllegalArgumentException(
                    "Could not find the request \""
                            + requestFieldName
                            + "\" in the request: "
                            + TurmsRequest.getDescriptor()
                                    .getFullName());
        }
        fieldDescriptor = requestFieldDescriptor.getMessageType()
                .findFieldByName(fieldName);
        if (fieldDescriptor == null) {
            throw new IllegalArgumentException(
                    "Could not find the field \""
                            + fieldName
                            + "\" in the request: "
                            + requestFieldDescriptor.getFullName());
        }
        Descriptors.FieldDescriptor.JavaType javaType = fieldDescriptor.getJavaType();
        Descriptors.FieldDescriptor subfieldDescriptor = null;
        if (subfieldName == null) {
            if (javaType != Descriptors.FieldDescriptor.JavaType.STRING) {
                throw new IllegalArgumentException(
                        "Expecting the field \""
                                + fieldName
                                + "\" to be a string in the request: "
                                + requestFieldDescriptor.getFullName()
                                + ", but got: "
                                + javaType);
            }
        } else {
            if (fieldDescriptor.isRepeated()) {
                subfieldDescriptor = fieldDescriptor.getMessageType()
                        .findFieldByName(subfieldName);
                if (subfieldDescriptor == null) {
                    throw new IllegalArgumentException(
                            "Could not find the subfield \""
                                    + subfieldName
                                    + "\" in the field ("
                                    + fieldDescriptor.getMessageType()
                                            .getFullName()
                                    + ") in the request: "
                                    + requestFieldDescriptor.getFullName());
                } else if (subfieldDescriptor
                        .getJavaType() != Descriptors.FieldDescriptor.JavaType.STRING) {
                    throw new IllegalArgumentException(
                            "Expecting the field \""
                                    + fieldName
                                    + "\" in the field ("
                                    + fieldDescriptor.getMessageType()
                                            .getFullName()
                                    + ") in the request ("
                                    + requestFieldDescriptor.getFullName()
                                    + ") to be a List<String>, but got: "
                                    + javaType);
                }
            } else {
                throw new IllegalArgumentException(
                        "Expecting the field \""
                                + fieldName
                                + "\" in the field ("
                                + fieldDescriptor.getMessageType()
                                        .getFullName()
                                + ") in the request ("
                                + requestFieldDescriptor.getFullName()
                                + ") to be a List<String>, but got: "
                                + javaType);
            }
        }
        this.subfieldDescriptor = subfieldDescriptor;
        type = TurmsRequest.KindCase.forNumber(requestFieldDescriptor.getNumber());
    }

    TextType(String requestFieldName, String fieldName) {
        this(requestFieldName, fieldName, null);
    }

}
