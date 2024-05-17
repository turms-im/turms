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

public interface StreamInfoOrBuilder extends
        // @@protoc_insertion_point(interface_extends:livekit.StreamInfo)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <code>string url = 1;</code>
     *
     * @return The url.
     */
    java.lang.String getUrl();

    /**
     * <code>string url = 1;</code>
     *
     * @return The bytes for url.
     */
    com.google.protobuf.ByteString getUrlBytes();

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
     * <code>int64 duration = 4;</code>
     *
     * @return The duration.
     */
    long getDuration();

    /**
     * <code>.livekit.StreamInfo.Status status = 5;</code>
     *
     * @return The enum numeric value on the wire for status.
     */
    int getStatusValue();

    /**
     * <code>.livekit.StreamInfo.Status status = 5;</code>
     *
     * @return The status.
     */
    im.turms.plugin.livekit.core.proto.egress.StreamInfo.Status getStatus();

    /**
     * <code>string error = 6;</code>
     *
     * @return The error.
     */
    java.lang.String getError();

    /**
     * <code>string error = 6;</code>
     *
     * @return The bytes for error.
     */
    com.google.protobuf.ByteString getErrorBytes();
}
