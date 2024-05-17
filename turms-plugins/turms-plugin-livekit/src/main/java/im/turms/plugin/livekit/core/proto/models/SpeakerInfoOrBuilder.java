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

public interface SpeakerInfoOrBuilder extends
        // @@protoc_insertion_point(interface_extends:livekit.SpeakerInfo)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <code>string sid = 1;</code>
     *
     * @return The sid.
     */
    java.lang.String getSid();

    /**
     * <code>string sid = 1;</code>
     *
     * @return The bytes for sid.
     */
    com.google.protobuf.ByteString getSidBytes();

    /**
     * <pre>
     * audio level, 0-1.0, 1 is loudest
     * </pre>
     *
     * <code>float level = 2;</code>
     *
     * @return The level.
     */
    float getLevel();

    /**
     * <pre>
     * true if speaker is currently active
     * </pre>
     *
     * <code>bool active = 3;</code>
     *
     * @return The active.
     */
    boolean getActive();
}
