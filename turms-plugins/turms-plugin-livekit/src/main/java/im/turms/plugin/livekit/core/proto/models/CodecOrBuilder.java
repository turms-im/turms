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

package im.turms.plugin.livekit.core.proto.models;

public interface CodecOrBuilder extends
        // @@protoc_insertion_point(interface_extends:livekit.Codec)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <code>string mime = 1;</code>
     *
     * @return The mime.
     */
    java.lang.String getMime();

    /**
     * <code>string mime = 1;</code>
     *
     * @return The bytes for mime.
     */
    com.google.protobuf.ByteString getMimeBytes();

    /**
     * <code>string fmtp_line = 2;</code>
     *
     * @return The fmtpLine.
     */
    java.lang.String getFmtpLine();

    /**
     * <code>string fmtp_line = 2;</code>
     *
     * @return The bytes for fmtpLine.
     */
    com.google.protobuf.ByteString getFmtpLineBytes();
}
