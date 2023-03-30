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

package im.turms.server.common.access.client.dto.request.storage;

public interface QuerySignedPutUrlRequestOrBuilder extends
        // @@protoc_insertion_point(interface_extends:im.turms.proto.QuerySignedPutUrlRequest)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <code>.im.turms.proto.ContentType content_type = 1;</code>
     *
     * @return The enum numeric value on the wire for contentType.
     */
    int getContentTypeValue();

    /**
     * <code>.im.turms.proto.ContentType content_type = 1;</code>
     *
     * @return The contentType.
     */
    im.turms.server.common.access.client.dto.constant.ContentType getContentType();

    /**
     * <code>optional string key_str = 2;</code>
     *
     * @return Whether the keyStr field is set.
     */
    boolean hasKeyStr();

    /**
     * <code>optional string key_str = 2;</code>
     *
     * @return The keyStr.
     */
    java.lang.String getKeyStr();

    /**
     * <code>optional string key_str = 2;</code>
     *
     * @return The bytes for keyStr.
     */
    com.google.protobuf.ByteString getKeyStrBytes();

    /**
     * <code>optional int64 key_num = 3;</code>
     *
     * @return Whether the keyNum field is set.
     */
    boolean hasKeyNum();

    /**
     * <code>optional int64 key_num = 3;</code>
     *
     * @return The keyNum.
     */
    long getKeyNum();

    /**
     * <code>int64 content_length = 4;</code>
     *
     * @return The contentLength.
     */
    long getContentLength();
}