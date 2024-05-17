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

public interface UpdateStreamRequestOrBuilder extends
        // @@protoc_insertion_point(interface_extends:livekit.UpdateStreamRequest)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <code>string egress_id = 1;</code>
     *
     * @return The egressId.
     */
    java.lang.String getEgressId();

    /**
     * <code>string egress_id = 1;</code>
     *
     * @return The bytes for egressId.
     */
    com.google.protobuf.ByteString getEgressIdBytes();

    /**
     * <code>repeated string add_output_urls = 2;</code>
     *
     * @return A list containing the addOutputUrls.
     */
    java.util.List<java.lang.String> getAddOutputUrlsList();

    /**
     * <code>repeated string add_output_urls = 2;</code>
     *
     * @return The count of addOutputUrls.
     */
    int getAddOutputUrlsCount();

    /**
     * <code>repeated string add_output_urls = 2;</code>
     *
     * @param index The index of the element to return.
     * @return The addOutputUrls at the given index.
     */
    java.lang.String getAddOutputUrls(int index);

    /**
     * <code>repeated string add_output_urls = 2;</code>
     *
     * @param index The index of the value to return.
     * @return The bytes of the addOutputUrls at the given index.
     */
    com.google.protobuf.ByteString getAddOutputUrlsBytes(int index);

    /**
     * <code>repeated string remove_output_urls = 3;</code>
     *
     * @return A list containing the removeOutputUrls.
     */
    java.util.List<java.lang.String> getRemoveOutputUrlsList();

    /**
     * <code>repeated string remove_output_urls = 3;</code>
     *
     * @return The count of removeOutputUrls.
     */
    int getRemoveOutputUrlsCount();

    /**
     * <code>repeated string remove_output_urls = 3;</code>
     *
     * @param index The index of the element to return.
     * @return The removeOutputUrls at the given index.
     */
    java.lang.String getRemoveOutputUrls(int index);

    /**
     * <code>repeated string remove_output_urls = 3;</code>
     *
     * @param index The index of the value to return.
     * @return The bytes of the removeOutputUrls at the given index.
     */
    com.google.protobuf.ByteString getRemoveOutputUrlsBytes(int index);
}
