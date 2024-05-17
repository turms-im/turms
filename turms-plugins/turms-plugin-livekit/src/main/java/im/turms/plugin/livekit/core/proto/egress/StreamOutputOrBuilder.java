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

public interface StreamOutputOrBuilder extends
        // @@protoc_insertion_point(interface_extends:livekit.StreamOutput)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     * required
     * </pre>
     *
     * <code>.livekit.StreamProtocol protocol = 1;</code>
     *
     * @return The enum numeric value on the wire for protocol.
     */
    int getProtocolValue();

    /**
     * <pre>
     * required
     * </pre>
     *
     * <code>.livekit.StreamProtocol protocol = 1;</code>
     *
     * @return The protocol.
     */
    im.turms.plugin.livekit.core.proto.egress.StreamProtocol getProtocol();

    /**
     * <pre>
     * required
     * </pre>
     *
     * <code>repeated string urls = 2;</code>
     *
     * @return A list containing the urls.
     */
    java.util.List<java.lang.String> getUrlsList();

    /**
     * <pre>
     * required
     * </pre>
     *
     * <code>repeated string urls = 2;</code>
     *
     * @return The count of urls.
     */
    int getUrlsCount();

    /**
     * <pre>
     * required
     * </pre>
     *
     * <code>repeated string urls = 2;</code>
     *
     * @param index The index of the element to return.
     * @return The urls at the given index.
     */
    java.lang.String getUrls(int index);

    /**
     * <pre>
     * required
     * </pre>
     *
     * <code>repeated string urls = 2;</code>
     *
     * @param index The index of the value to return.
     * @return The bytes of the urls at the given index.
     */
    com.google.protobuf.ByteString getUrlsBytes(int index);
}
