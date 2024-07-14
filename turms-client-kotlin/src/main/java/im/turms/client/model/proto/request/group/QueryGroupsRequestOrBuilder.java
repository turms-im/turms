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

package im.turms.client.model.proto.request.group;

public interface QueryGroupsRequestOrBuilder extends
        // @@protoc_insertion_point(interface_extends:im.turms.proto.QueryGroupsRequest)
        com.google.protobuf.MessageLiteOrBuilder {

    /**
     * <code>repeated int64 group_ids = 1;</code>
     *
     * @return A list containing the groupIds.
     */
    java.util.List<java.lang.Long> getGroupIdsList();

    /**
     * <code>repeated int64 group_ids = 1;</code>
     *
     * @return The count of groupIds.
     */
    int getGroupIdsCount();

    /**
     * <code>repeated int64 group_ids = 1;</code>
     *
     * @param index The index of the element to return.
     * @return The groupIds at the given index.
     */
    long getGroupIds(int index);

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

    /**
     * <code>optional string name = 3;</code>
     *
     * @return Whether the name field is set.
     */
    boolean hasName();

    /**
     * <code>optional string name = 3;</code>
     *
     * @return The name.
     */
    java.lang.String getName();

    /**
     * <code>optional string name = 3;</code>
     *
     * @return The bytes for name.
     */
    com.google.protobuf.ByteString getNameBytes();

    /**
     * <code>optional int32 skip = 10;</code>
     *
     * @return Whether the skip field is set.
     */
    boolean hasSkip();

    /**
     * <code>optional int32 skip = 10;</code>
     *
     * @return The skip.
     */
    int getSkip();

    /**
     * <code>optional int32 limit = 11;</code>
     *
     * @return Whether the limit field is set.
     */
    boolean hasLimit();

    /**
     * <code>optional int32 limit = 11;</code>
     *
     * @return The limit.
     */
    int getLimit();

    /**
     * <code>repeated int32 fields_to_highlight = 12;</code>
     *
     * @return A list containing the fieldsToHighlight.
     */
    java.util.List<java.lang.Integer> getFieldsToHighlightList();

    /**
     * <code>repeated int32 fields_to_highlight = 12;</code>
     *
     * @return The count of fieldsToHighlight.
     */
    int getFieldsToHighlightCount();

    /**
     * <code>repeated int32 fields_to_highlight = 12;</code>
     *
     * @param index The index of the element to return.
     * @return The fieldsToHighlight at the given index.
     */
    int getFieldsToHighlight(int index);

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    java.util.List<im.turms.client.model.proto.model.common.Value> getCustomAttributesList();

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    im.turms.client.model.proto.model.common.Value getCustomAttributes(int index);

    /**
     * <code>repeated .im.turms.proto.Value custom_attributes = 15;</code>
     */
    int getCustomAttributesCount();
}