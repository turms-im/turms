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

package im.turms.server.common.access.client.dto.model.file;

public interface AudioFileOrBuilder extends
        // @@protoc_insertion_point(interface_extends:im.turms.proto.AudioFile)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <code>optional .im.turms.proto.AudioFile.Description description = 1;</code>
     *
     * @return Whether the description field is set.
     */
    boolean hasDescription();

    /**
     * <code>optional .im.turms.proto.AudioFile.Description description = 1;</code>
     *
     * @return The description.
     */
    im.turms.server.common.access.client.dto.model.file.AudioFile.Description getDescription();

    /**
     * <code>optional .im.turms.proto.AudioFile.Description description = 1;</code>
     */
    im.turms.server.common.access.client.dto.model.file.AudioFile.DescriptionOrBuilder getDescriptionOrBuilder();

    /**
     * <code>optional bytes data = 2;</code>
     *
     * @return Whether the data field is set.
     */
    boolean hasData();

    /**
     * <code>optional bytes data = 2;</code>
     *
     * @return The data.
     */
    com.google.protobuf.ByteString getData();

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    java.util.List<im.turms.server.common.access.client.dto.model.common.Value> getCustomAttributesList();

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    im.turms.server.common.access.client.dto.model.common.Value getCustomAttributes(int index);

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    int getCustomAttributesCount();

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    java.util.List<? extends im.turms.server.common.access.client.dto.model.common.ValueOrBuilder> getCustomAttributesOrBuilderList();

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    im.turms.server.common.access.client.dto.model.common.ValueOrBuilder getCustomAttributesOrBuilder(
            int index);
}
