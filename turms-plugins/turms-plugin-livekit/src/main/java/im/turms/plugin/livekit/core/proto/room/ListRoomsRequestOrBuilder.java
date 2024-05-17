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

package im.turms.plugin.livekit.core.proto.room;

public interface ListRoomsRequestOrBuilder extends
        // @@protoc_insertion_point(interface_extends:livekit.ListRoomsRequest)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     * when set, will only return rooms with name match
     * </pre>
     *
     * <code>repeated string names = 1;</code>
     *
     * @return A list containing the names.
     */
    java.util.List<java.lang.String> getNamesList();

    /**
     * <pre>
     * when set, will only return rooms with name match
     * </pre>
     *
     * <code>repeated string names = 1;</code>
     *
     * @return The count of names.
     */
    int getNamesCount();

    /**
     * <pre>
     * when set, will only return rooms with name match
     * </pre>
     *
     * <code>repeated string names = 1;</code>
     *
     * @param index The index of the element to return.
     * @return The names at the given index.
     */
    java.lang.String getNames(int index);

    /**
     * <pre>
     * when set, will only return rooms with name match
     * </pre>
     *
     * <code>repeated string names = 1;</code>
     *
     * @param index The index of the value to return.
     * @return The bytes of the names at the given index.
     */
    com.google.protobuf.ByteString getNamesBytes(int index);
}
