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

package im.turms.client.model.proto.request.storage;

public interface DeleteResourceRequestOrBuilder extends
        // @@protoc_insertion_point(interface_extends:im.turms.proto.DeleteResourceRequest)
        com.google.protobuf.MessageLiteOrBuilder {

    /**
     * <code>.im.turms.proto.StorageResourceType type = 1;</code>
     *
     * @return The enum numeric value on the wire for type.
     */
    int getTypeValue();

    /**
     * <code>.im.turms.proto.StorageResourceType type = 1;</code>
     *
     * @return The type.
     */
    im.turms.client.model.proto.constant.StorageResourceType getType();

    /**
     * <code>optional int64 id_num = 2;</code>
     *
     * @return Whether the idNum field is set.
     */
    boolean hasIdNum();

    /**
     * <code>optional int64 id_num = 2;</code>
     *
     * @return The idNum.
     */
    long getIdNum();

    /**
     * <code>optional string id_str = 3;</code>
     *
     * @return Whether the idStr field is set.
     */
    boolean hasIdStr();

    /**
     * <code>optional string id_str = 3;</code>
     *
     * @return The idStr.
     */
    java.lang.String getIdStr();

    /**
     * <code>optional string id_str = 3;</code>
     *
     * @return The bytes for idStr.
     */
    com.google.protobuf.ByteString getIdStrBytes();

    /**
     * <code>map&lt;string, string&gt; extra = 4;</code>
     */
    int getExtraCount();

    /**
     * <code>map&lt;string, string&gt; extra = 4;</code>
     */
    boolean containsExtra(java.lang.String key);

    /**
     * Use {@link #getExtraMap()} instead.
     */
    @java.lang.Deprecated
    java.util.Map<java.lang.String, java.lang.String> getExtra();

    /**
     * <code>map&lt;string, string&gt; extra = 4;</code>
     */
    java.util.Map<java.lang.String, java.lang.String> getExtraMap();

    /**
     * <code>map&lt;string, string&gt; extra = 4;</code>
     */

    /* nullable */
    java.lang.String getExtraOrDefault(
            java.lang.String key,
            /* nullable */
            java.lang.String defaultValue);

    /**
     * <code>map&lt;string, string&gt; extra = 4;</code>
     */

    java.lang.String getExtraOrThrow(java.lang.String key);
}