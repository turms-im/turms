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

package im.turms.plugin.livekit.core.webhook;

/**
 * @author James Chen
 */
public final class WebhookEventNameConst {

    public static final String ROOM_STARTED = "room_started";
    public static final String ROOM_FINISHED = "room_finished";
    public static final String PARTICIPANT_JOINED = "participant_joined";
    public static final String PARTICIPANT_LEFT = "participant_left";
    public static final String TRACK_PUBLISHED = "track_published";
    public static final String TRACK_UNPUBLISHED = "track_unpublished";
    public static final String EGRESS_STARTED = "egress_started";
    public static final String EGRESS_UPDATED = "egress_updated";
    public static final String EGRESS_ENDED = "egress_ended";
    public static final String INGRESS_STARTED = "ingress_started";
    public static final String INGRESS_ENDED = "ingress_ended";

    private WebhookEventNameConst() {
    }

}