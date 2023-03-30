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

package im.turms.client.model.proto.request.group;

public interface UpdateGroupRequestOrBuilder extends
        // @@protoc_insertion_point(interface_extends:im.turms.proto.UpdateGroupRequest)
        com.google.protobuf.MessageLiteOrBuilder {

    /**
     * <pre>
     * Query filter
     * </pre>
     * 
     * <code>int64 group_id = 1;</code>
     *
     * @return The groupId.
     */
    long getGroupId();

    /**
     * <pre>
     * Update options
     * </pre>
     * 
     * <code>optional bool quit_after_transfer = 2;</code>
     *
     * @return Whether the quitAfterTransfer field is set.
     */
    boolean hasQuitAfterTransfer();

    /**
     * <pre>
     * Update options
     * </pre>
     * 
     * <code>optional bool quit_after_transfer = 2;</code>
     *
     * @return The quitAfterTransfer.
     */
    boolean getQuitAfterTransfer();

    /**
     * <pre>
     * Update
     * </pre>
     * 
     * <code>optional string name = 3;</code>
     *
     * @return Whether the name field is set.
     */
    boolean hasName();

    /**
     * <pre>
     * Update
     * </pre>
     * 
     * <code>optional string name = 3;</code>
     *
     * @return The name.
     */
    java.lang.String getName();

    /**
     * <pre>
     * Update
     * </pre>
     * 
     * <code>optional string name = 3;</code>
     *
     * @return The bytes for name.
     */
    com.google.protobuf.ByteString getNameBytes();

    /**
     * <code>optional string intro = 4;</code>
     *
     * @return Whether the intro field is set.
     */
    boolean hasIntro();

    /**
     * <code>optional string intro = 4;</code>
     *
     * @return The intro.
     */
    java.lang.String getIntro();

    /**
     * <code>optional string intro = 4;</code>
     *
     * @return The bytes for intro.
     */
    com.google.protobuf.ByteString getIntroBytes();

    /**
     * <code>optional string announcement = 5;</code>
     *
     * @return Whether the announcement field is set.
     */
    boolean hasAnnouncement();

    /**
     * <code>optional string announcement = 5;</code>
     *
     * @return The announcement.
     */
    java.lang.String getAnnouncement();

    /**
     * <code>optional string announcement = 5;</code>
     *
     * @return The bytes for announcement.
     */
    com.google.protobuf.ByteString getAnnouncementBytes();

    /**
     * <code>optional int32 min_score = 6;</code>
     *
     * @return Whether the minScore field is set.
     */
    boolean hasMinScore();

    /**
     * <code>optional int32 min_score = 6;</code>
     *
     * @return The minScore.
     */
    int getMinScore();

    /**
     * <code>optional int64 type_id = 7;</code>
     *
     * @return Whether the typeId field is set.
     */
    boolean hasTypeId();

    /**
     * <code>optional int64 type_id = 7;</code>
     *
     * @return The typeId.
     */
    long getTypeId();

    /**
     * <code>optional int64 mute_end_date = 8;</code>
     *
     * @return Whether the muteEndDate field is set.
     */
    boolean hasMuteEndDate();

    /**
     * <code>optional int64 mute_end_date = 8;</code>
     *
     * @return The muteEndDate.
     */
    long getMuteEndDate();

    /**
     * <code>optional int64 successor_id = 9;</code>
     *
     * @return Whether the successorId field is set.
     */
    boolean hasSuccessorId();

    /**
     * <code>optional int64 successor_id = 9;</code>
     *
     * @return The successorId.
     */
    long getSuccessorId();
}