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

package im.turms.server.common.access.client.dto.model.common;

public interface LongsWithVersionOrBuilder extends
        // @@protoc_insertion_point(interface_extends:im.turms.proto.LongsWithVersion)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <code>repeated int64 longs = 1;</code>
     *
     * @return A list containing the longs.
     */
    java.util.List<java.lang.Long> getLongsList();

    /**
     * <code>repeated int64 longs = 1;</code>
     *
     * @return The count of longs.
     */
    int getLongsCount();

    /**
     * <code>repeated int64 longs = 1;</code>
     *
     * @param index The index of the element to return.
     * @return The longs at the given index.
     */
    long getLongs(int index);

    /**
     * <code>optional int64 last_updated_date = 2;</code>
     *
     * @return Whether the lastUpdatedDate field is set.
     */
    boolean hasLastUpdatedDate();

    /**
     * <code>optional int64 last_updated_date = 2;</code>
     *
     * @return The lastUpdatedDate.
     */
    long getLastUpdatedDate();
}