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

package im.turms.server.common.access.client.dto.model.user;

public interface UserLocationOrBuilder extends
        // @@protoc_insertion_point(interface_extends:im.turms.proto.UserLocation)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <code>float latitude = 1;</code>
     *
     * @return The latitude.
     */
    float getLatitude();

    /**
     * <code>float longitude = 2;</code>
     *
     * @return The longitude.
     */
    float getLongitude();

    /**
     * <code>optional int64 timestamp = 3;</code>
     *
     * @return Whether the timestamp field is set.
     */
    boolean hasTimestamp();

    /**
     * <code>optional int64 timestamp = 3;</code>
     *
     * @return The timestamp.
     */
    long getTimestamp();

    /**
     * <pre>
     * e.g. street address, city, state, country, etc.
     * </pre>
     * 
     * <code>map&lt;string, string&gt; details = 4;</code>
     */
    int getDetailsCount();

    /**
     * <pre>
     * e.g. street address, city, state, country, etc.
     * </pre>
     * 
     * <code>map&lt;string, string&gt; details = 4;</code>
     */
    boolean containsDetails(java.lang.String key);

    /**
     * Use {@link #getDetailsMap()} instead.
     */
    @java.lang.Deprecated
    java.util.Map<java.lang.String, java.lang.String> getDetails();

    /**
     * <pre>
     * e.g. street address, city, state, country, etc.
     * </pre>
     * 
     * <code>map&lt;string, string&gt; details = 4;</code>
     */
    java.util.Map<java.lang.String, java.lang.String> getDetailsMap();

    /**
     * <pre>
     * e.g. street address, city, state, country, etc.
     * </pre>
     * 
     * <code>map&lt;string, string&gt; details = 4;</code>
     */
    /* nullable */
    java.lang.String getDetailsOrDefault(
            java.lang.String key,
            /* nullable */
            java.lang.String defaultValue);

    /**
     * <pre>
     * e.g. street address, city, state, country, etc.
     * </pre>
     * 
     * <code>map&lt;string, string&gt; details = 4;</code>
     */
    java.lang.String getDetailsOrThrow(java.lang.String key);
}