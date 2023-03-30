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

package im.turms.client.model.proto.request.group.enrollment;

public interface UpdateGroupJoinQuestionRequestOrBuilder extends
        // @@protoc_insertion_point(interface_extends:im.turms.proto.UpdateGroupJoinQuestionRequest)
        com.google.protobuf.MessageLiteOrBuilder {

    /**
     * <pre>
     * Query filter
     * </pre>
     * 
     * <code>int64 question_id = 1;</code>
     *
     * @return The questionId.
     */
    long getQuestionId();

    /**
     * <pre>
     * Update
     * </pre>
     * 
     * <code>optional string question = 2;</code>
     *
     * @return Whether the question field is set.
     */
    boolean hasQuestion();

    /**
     * <pre>
     * Update
     * </pre>
     * 
     * <code>optional string question = 2;</code>
     *
     * @return The question.
     */
    java.lang.String getQuestion();

    /**
     * <pre>
     * Update
     * </pre>
     * 
     * <code>optional string question = 2;</code>
     *
     * @return The bytes for question.
     */
    com.google.protobuf.ByteString getQuestionBytes();

    /**
     * <code>repeated string answers = 3;</code>
     *
     * @return A list containing the answers.
     */
    java.util.List<java.lang.String> getAnswersList();

    /**
     * <code>repeated string answers = 3;</code>
     *
     * @return The count of answers.
     */
    int getAnswersCount();

    /**
     * <code>repeated string answers = 3;</code>
     *
     * @param index The index of the element to return.
     * @return The answers at the given index.
     */
    java.lang.String getAnswers(int index);

    /**
     * <code>repeated string answers = 3;</code>
     *
     * @param index The index of the element to return.
     * @return The answers at the given index.
     */
    com.google.protobuf.ByteString getAnswersBytes(int index);

    /**
     * <code>optional int32 score = 4;</code>
     *
     * @return Whether the score field is set.
     */
    boolean hasScore();

    /**
     * <code>optional int32 score = 4;</code>
     *
     * @return The score.
     */
    int getScore();
}