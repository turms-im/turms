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

package im.turms.client.model.proto.request.conversation;

public interface UpdateTypingStatusRequestOrBuilder extends
        // @@protoc_insertion_point(interface_extends:im.turms.proto.UpdateTypingStatusRequest)
        com.google.protobuf.MessageLiteOrBuilder {

    /**
     * <pre>
     * Query filter
     * </pre>
     * 
     * <code>bool is_group_message = 1;</code>
     *
     * @return The isGroupMessage.
     */
    boolean getIsGroupMessage();

    /**
     * <code>int64 to_id = 2;</code>
     *
     * @return The toId.
     */
    long getToId();
}