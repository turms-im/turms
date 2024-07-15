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

package im.turms.server.common.access.client.dto.model.common;

public interface ValueOrBuilder extends
        // @@protoc_insertion_point(interface_extends:im.turms.proto.Value)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <code>int32 int32_value = 1;</code>
     *
     * @return Whether the int32Value field is set.
     */
    boolean hasInt32Value();

    /**
     * <code>int32 int32_value = 1;</code>
     *
     * @return The int32Value.
     */
    int getInt32Value();

    /**
     * <code>int64 int64_value = 2;</code>
     *
     * @return Whether the int64Value field is set.
     */
    boolean hasInt64Value();

    /**
     * <code>int64 int64_value = 2;</code>
     *
     * @return The int64Value.
     */
    long getInt64Value();

    /**
     * <code>float float_value = 3;</code>
     *
     * @return Whether the floatValue field is set.
     */
    boolean hasFloatValue();

    /**
     * <code>float float_value = 3;</code>
     *
     * @return The floatValue.
     */
    float getFloatValue();

    /**
     * <code>double double_value = 4;</code>
     *
     * @return Whether the doubleValue field is set.
     */
    boolean hasDoubleValue();

    /**
     * <code>double double_value = 4;</code>
     *
     * @return The doubleValue.
     */
    double getDoubleValue();

    /**
     * <code>bool bool_value = 5;</code>
     *
     * @return Whether the boolValue field is set.
     */
    boolean hasBoolValue();

    /**
     * <code>bool bool_value = 5;</code>
     *
     * @return The boolValue.
     */
    boolean getBoolValue();

    /**
     * <code>bytes bytes_value = 6;</code>
     *
     * @return Whether the bytesValue field is set.
     */
    boolean hasBytesValue();

    /**
     * <code>bytes bytes_value = 6;</code>
     *
     * @return The bytesValue.
     */
    com.google.protobuf.ByteString getBytesValue();

    /**
     * <code>string string_value = 7;</code>
     *
     * @return Whether the stringValue field is set.
     */
    boolean hasStringValue();

    /**
     * <code>string string_value = 7;</code>
     *
     * @return The stringValue.
     */
    java.lang.String getStringValue();

    /**
     * <code>string string_value = 7;</code>
     *
     * @return The bytes for stringValue.
     */
    com.google.protobuf.ByteString getStringValueBytes();

    /**
     * <code>repeated .im.turms.proto.Value list_value = 8;</code>
     */
    java.util.List<im.turms.server.common.access.client.dto.model.common.Value> getListValueList();

    /**
     * <code>repeated .im.turms.proto.Value list_value = 8;</code>
     */
    im.turms.server.common.access.client.dto.model.common.Value getListValue(int index);

    /**
     * <code>repeated .im.turms.proto.Value list_value = 8;</code>
     */
    int getListValueCount();

    /**
     * <code>repeated .im.turms.proto.Value list_value = 8;</code>
     */
    java.util.List<? extends im.turms.server.common.access.client.dto.model.common.ValueOrBuilder> getListValueOrBuilderList();

    /**
     * <code>repeated .im.turms.proto.Value list_value = 8;</code>
     */
    im.turms.server.common.access.client.dto.model.common.ValueOrBuilder getListValueOrBuilder(
            int index);

    im.turms.server.common.access.client.dto.model.common.Value.KindCase getKindCase();
}
