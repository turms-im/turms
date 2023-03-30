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

package im.turms.client.model.proto.model.group;

public interface GroupJoinRequestOrBuilder extends
        // @@protoc_insertion_point(interface_extends:im.turms.proto.GroupJoinRequest)
        com.google.protobuf.MessageLiteOrBuilder {

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
     * <code>optional int64 creation_date = 2;</code>
     *
     * @return Whether the creationDate field is set.
     */
    boolean hasCreationDate();

    /**
     * <code>optional int64 creation_date = 2;</code>
     *
     * @return The creationDate.
     */
    long getCreationDate();

    /**
     * <code>optional string content = 3;</code>
     *
     * @return Whether the content field is set.
     */
    boolean hasContent();

    /**
     * <code>optional string content = 3;</code>
     *
     * @return The content.
     */
    java.lang.String getContent();

    /**
     * <code>optional string content = 3;</code>
     *
     * @return The bytes for content.
     */
    com.google.protobuf.ByteString getContentBytes();

    /**
     * <code>optional .im.turms.proto.RequestStatus status = 4;</code>
     *
     * @return Whether the status field is set.
     */
    boolean hasStatus();

    /**
     * <code>optional .im.turms.proto.RequestStatus status = 4;</code>
     *
     * @return The enum numeric value on the wire for status.
     */
    int getStatusValue();

    /**
     * <code>optional .im.turms.proto.RequestStatus status = 4;</code>
     *
     * @return The status.
     */
    im.turms.client.model.proto.constant.RequestStatus getStatus();

    /**
     * <code>optional int64 expiration_date = 5;</code>
     *
     * @return Whether the expirationDate field is set.
     */
    boolean hasExpirationDate();

    /**
     * <code>optional int64 expiration_date = 5;</code>
     *
     * @return The expirationDate.
     */
    long getExpirationDate();

    /**
     * <code>optional int64 group_id = 6;</code>
     *
     * @return Whether the groupId field is set.
     */
    boolean hasGroupId();

    /**
     * <code>optional int64 group_id = 6;</code>
     *
     * @return The groupId.
     */
    long getGroupId();

    /**
     * <code>optional int64 requester_id = 7;</code>
     *
     * @return Whether the requesterId field is set.
     */
    boolean hasRequesterId();

    /**
     * <code>optional int64 requester_id = 7;</code>
     *
     * @return The requesterId.
     */
    long getRequesterId();

    /**
     * <code>optional int64 responder_id = 8;</code>
     *
     * @return Whether the responderId field is set.
     */
    boolean hasResponderId();

    /**
     * <code>optional int64 responder_id = 8;</code>
     *
     * @return The responderId.
     */
    long getResponderId();
}