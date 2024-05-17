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

public interface ParticipantPermissionOrBuilder extends
        // @@protoc_insertion_point(interface_extends:livekit.ParticipantPermission)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     * allow participant to subscribe to other tracks in the room
     * </pre>
     *
     * <code>bool can_subscribe = 1;</code>
     *
     * @return The canSubscribe.
     */
    boolean getCanSubscribe();

    /**
     * <pre>
     * allow participant to publish new tracks to room
     * </pre>
     *
     * <code>bool can_publish = 2;</code>
     *
     * @return The canPublish.
     */
    boolean getCanPublish();

    /**
     * <pre>
     * allow participant to publish data
     * </pre>
     *
     * <code>bool can_publish_data = 3;</code>
     *
     * @return The canPublishData.
     */
    boolean getCanPublishData();

    /**
     * <pre>
     * sources that are allowed to be published
     * </pre>
     *
     * <code>repeated .livekit.TrackSource can_publish_sources = 9;</code>
     *
     * @return A list containing the canPublishSources.
     */
    java.util.List<im.turms.plugin.livekit.core.proto.models.TrackSource> getCanPublishSourcesList();

    /**
     * <pre>
     * sources that are allowed to be published
     * </pre>
     *
     * <code>repeated .livekit.TrackSource can_publish_sources = 9;</code>
     *
     * @return The count of canPublishSources.
     */
    int getCanPublishSourcesCount();

    /**
     * <pre>
     * sources that are allowed to be published
     * </pre>
     *
     * <code>repeated .livekit.TrackSource can_publish_sources = 9;</code>
     *
     * @param index The index of the element to return.
     * @return The canPublishSources at the given index.
     */
    im.turms.plugin.livekit.core.proto.models.TrackSource getCanPublishSources(int index);

    /**
     * <pre>
     * sources that are allowed to be published
     * </pre>
     *
     * <code>repeated .livekit.TrackSource can_publish_sources = 9;</code>
     *
     * @return A list containing the enum numeric values on the wire for canPublishSources.
     */
    java.util.List<java.lang.Integer> getCanPublishSourcesValueList();

    /**
     * <pre>
     * sources that are allowed to be published
     * </pre>
     *
     * <code>repeated .livekit.TrackSource can_publish_sources = 9;</code>
     *
     * @param index The index of the value to return.
     * @return The enum numeric value on the wire of canPublishSources at the given index.
     */
    int getCanPublishSourcesValue(int index);

    /**
     * <pre>
     * indicates that it's hidden to others
     * </pre>
     *
     * <code>bool hidden = 7;</code>
     *
     * @return The hidden.
     */
    boolean getHidden();

    /**
     * <pre>
     * indicates it's a recorder instance
     * </pre>
     *
     * <code>bool recorder = 8;</code>
     *
     * @return The recorder.
     */
    boolean getRecorder();

    /**
     * <pre>
     * indicates that participant can update own metadata
     * </pre>
     *
     * <code>bool can_update_metadata = 10;</code>
     *
     * @return The canUpdateMetadata.
     */
    boolean getCanUpdateMetadata();

    /**
     * <pre>
     * indicates that participant is an agent
     * </pre>
     *
     * <code>bool agent = 11;</code>
     *
     * @return The agent.
     */
    boolean getAgent();
}
