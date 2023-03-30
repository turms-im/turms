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

package im.turms.server.common.access.client.dto.model.group;

public interface GroupJoinQuestionOrBuilder extends
        // @@protoc_insertion_point(interface_extends:im.turms.proto.GroupJoinQuestion)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <code>optional int64 id = 1;</code>
     *
     * @return Whether the id field is set.
     */
    boolean hasId();

    /**
     * <code>optional int64 id = 1;</code>
     *
     * @return The id.
     */
    long getId();

    /**
     * <code>optional int64 group_id = 2;</code>
     *
     * @return Whether the groupId field is set.
     */
    boolean hasGroupId();

    /**
     * <code>optional int64 group_id = 2;</code>
     *
     * @return The groupId.
     */
    long getGroupId();

    /**
     * <code>optional string question = 3;</code>
     *
     * @return Whether the question field is set.
     */
    boolean hasQuestion();

    /**
     * <code>optional string question = 3;</code>
     *
     * @return The question.
     */
    java.lang.String getQuestion();

    /**
     * <code>optional string question = 3;</code>
     *
     * @return The bytes for question.
     */
    com.google.protobuf.ByteString getQuestionBytes();

    /**
     * <code>repeated string answers = 4;</code>
     *
     * @return A list containing the answers.
     */
    java.util.List<java.lang.String> getAnswersList();

    /**
     * <code>repeated string answers = 4;</code>
     *
     * @return The count of answers.
     */
    int getAnswersCount();

    /**
     * <code>repeated string answers = 4;</code>
     *
     * @param index The index of the element to return.
     * @return The answers at the given index.
     */
    java.lang.String getAnswers(int index);

    /**
     * <code>repeated string answers = 4;</code>
     *
     * @param index The index of the value to return.
     * @return The bytes of the answers at the given index.
     */
    com.google.protobuf.ByteString getAnswersBytes(int index);

    /**
     * <code>optional int32 score = 5;</code>
     *
     * @return Whether the score field is set.
     */
    boolean hasScore();

    /**
     * <code>optional int32 score = 5;</code>
     *
     * @return The score.
     */
    int getScore();
}