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

package im.turms.plugin.livekit.core.proto.egress;

public interface FileInfoOrBuilder extends
        // @@protoc_insertion_point(interface_extends:livekit.FileInfo)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <code>string filename = 1;</code>
     *
     * @return The filename.
     */
    java.lang.String getFilename();

    /**
     * <code>string filename = 1;</code>
     *
     * @return The bytes for filename.
     */
    com.google.protobuf.ByteString getFilenameBytes();

    /**
     * <code>int64 started_at = 2;</code>
     *
     * @return The startedAt.
     */
    long getStartedAt();

    /**
     * <code>int64 ended_at = 3;</code>
     *
     * @return The endedAt.
     */
    long getEndedAt();

    /**
     * <code>int64 duration = 6;</code>
     *
     * @return The duration.
     */
    long getDuration();

    /**
     * <code>int64 size = 4;</code>
     *
     * @return The size.
     */
    long getSize();

    /**
     * <code>string location = 5;</code>
     *
     * @return The location.
     */
    java.lang.String getLocation();

    /**
     * <code>string location = 5;</code>
     *
     * @return The bytes for location.
     */
    com.google.protobuf.ByteString getLocationBytes();
}
