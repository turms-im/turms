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

package im.turms.client.model.proto.request.message;

public interface QueryMessagesRequestOrBuilder extends
        // @@protoc_insertion_point(interface_extends:im.turms.proto.QueryMessagesRequest)
        com.google.protobuf.MessageLiteOrBuilder {

    /**
     * <pre>
     * Filter
     * </pre>
     * 
     * <code>repeated int64 ids = 1;</code>
     *
     * @return A list containing the ids.
     */
    java.util.List<java.lang.Long> getIdsList();

    /**
     * <pre>
     * Filter
     * </pre>
     * 
     * <code>repeated int64 ids = 1;</code>
     *
     * @return The count of ids.
     */
    int getIdsCount();

    /**
     * <pre>
     * Filter
     * </pre>
     * 
     * <code>repeated int64 ids = 1;</code>
     *
     * @param index The index of the element to return.
     * @return The ids at the given index.
     */
    long getIds(int index);

    /**
     * <code>optional bool are_group_messages = 2;</code>
     *
     * @return Whether the areGroupMessages field is set.
     */
    boolean hasAreGroupMessages();

    /**
     * <code>optional bool are_group_messages = 2;</code>
     *
     * @return The areGroupMessages.
     */
    boolean getAreGroupMessages();

    /**
     * <code>optional bool are_system_messages = 3;</code>
     *
     * @return Whether the areSystemMessages field is set.
     */
    boolean hasAreSystemMessages();

    /**
     * <code>optional bool are_system_messages = 3;</code>
     *
     * @return The areSystemMessages.
     */
    boolean getAreSystemMessages();

    /**
     * <code>repeated int64 from_ids = 4;</code>
     *
     * @return A list containing the fromIds.
     */
    java.util.List<java.lang.Long> getFromIdsList();

    /**
     * <code>repeated int64 from_ids = 4;</code>
     *
     * @return The count of fromIds.
     */
    int getFromIdsCount();

    /**
     * <code>repeated int64 from_ids = 4;</code>
     *
     * @param index The index of the element to return.
     * @return The fromIds at the given index.
     */
    long getFromIds(int index);

    /**
     * <code>optional int64 delivery_date_start = 5;</code>
     *
     * @return Whether the deliveryDateStart field is set.
     */
    boolean hasDeliveryDateStart();

    /**
     * <code>optional int64 delivery_date_start = 5;</code>
     *
     * @return The deliveryDateStart.
     */
    long getDeliveryDateStart();

    /**
     * <code>optional int64 delivery_date_end = 6;</code>
     *
     * @return Whether the deliveryDateEnd field is set.
     */
    boolean hasDeliveryDateEnd();

    /**
     * <code>optional int64 delivery_date_end = 6;</code>
     *
     * @return The deliveryDateEnd.
     */
    long getDeliveryDateEnd();

    /**
     * <pre>
     * Option
     * </pre>
     * 
     * <code>optional int32 max_count = 7;</code>
     *
     * @return Whether the maxCount field is set.
     */
    boolean hasMaxCount();

    /**
     * <pre>
     * Option
     * </pre>
     * 
     * <code>optional int32 max_count = 7;</code>
     *
     * @return The maxCount.
     */
    int getMaxCount();

    /**
     * <pre>
     * Command
     * </pre>
     * 
     * <code>bool with_total = 8;</code>
     *
     * @return The withTotal.
     */
    boolean getWithTotal();

    /**
     * <pre>
     * Option
     * TODO: reorder
     * </pre>
     * 
     * <code>optional bool descending = 9;</code>
     *
     * @return Whether the descending field is set.
     */
    boolean hasDescending();

    /**
     * <pre>
     * Option
     * TODO: reorder
     * </pre>
     * 
     * <code>optional bool descending = 9;</code>
     *
     * @return The descending.
     */
    boolean getDescending();
}