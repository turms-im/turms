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

package im.turms.client.model.proto.model.conversation;

public interface GroupConversationOrBuilder extends
        // @@protoc_insertion_point(interface_extends:im.turms.proto.GroupConversation)
        com.google.protobuf.MessageLiteOrBuilder {

    /**
     * <code>int64 group_id = 1;</code>
     *
     * @return The groupId.
     */
    long getGroupId();

    /**
     * <code>map&lt;int64, int64&gt; member_id_to_read_date = 2;</code>
     */
    int getMemberIdToReadDateCount();

    /**
     * <code>map&lt;int64, int64&gt; member_id_to_read_date = 2;</code>
     */
    boolean containsMemberIdToReadDate(long key);

    /**
     * Use {@link #getMemberIdToReadDateMap()} instead.
     */
    @java.lang.Deprecated
    java.util.Map<java.lang.Long, java.lang.Long> getMemberIdToReadDate();

    /**
     * <code>map&lt;int64, int64&gt; member_id_to_read_date = 2;</code>
     */
    java.util.Map<java.lang.Long, java.lang.Long> getMemberIdToReadDateMap();

    /**
     * <code>map&lt;int64, int64&gt; member_id_to_read_date = 2;</code>
     */

    long getMemberIdToReadDateOrDefault(long key, long defaultValue);

    /**
     * <code>map&lt;int64, int64&gt; member_id_to_read_date = 2;</code>
     */

    long getMemberIdToReadDateOrThrow(long key);
}