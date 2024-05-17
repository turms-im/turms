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

package im.turms.plugin.livekit.core.auth;

import java.util.List;

import lombok.Data;

/**
 * @author James Chen
 * @see <a href="https://github.com/livekit/protocol/blob/main/auth/grants.go">grants.go</a>
 */
@Data
public sealed class VideoGrant {
    private final String key;
    private final Object value;

    VideoGrant(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    public static final class RoomCreate extends VideoGrant {
        public static final String KEY = "roomCreate";
        public static final RoomCreate ENABLED = new RoomCreate(true);
        public static final RoomCreate DISABLED = new RoomCreate(false);

        private RoomCreate(boolean value) {
            super(KEY, value);
        }
    }

    public static final class RoomList extends VideoGrant {
        public static final String KEY = "roomList";
        public static final RoomList ENABLED = new RoomList(true);
        public static final RoomList DISABLED = new RoomList(false);

        private RoomList(boolean value) {
            super(KEY, value);
        }
    }

    public static final class RoomRecord extends VideoGrant {
        public static final String KEY = "roomRecord";
        public static final RoomRecord ENABLED = new RoomRecord(true);
        public static final RoomRecord DISABLED = new RoomRecord(false);

        private RoomRecord(boolean value) {
            super(KEY, value);
        }
    }

    public static final class RoomAdmin extends VideoGrant {
        public static final String KEY = "roomAdmin";
        public static final RoomAdmin ENABLED = new RoomAdmin(true);
        public static final RoomAdmin DISABLED = new RoomAdmin(false);

        private RoomAdmin(boolean value) {
            super(KEY, value);
        }
    }

    public static final class RoomJoin extends VideoGrant {
        public static final String KEY = "roomJoin";
        public static final RoomJoin ENABLED = new RoomJoin(true);
        public static final RoomJoin DISABLED = new RoomJoin(false);

        private RoomJoin(boolean value) {
            super(KEY, value);
        }
    }

    public static final class RoomName extends VideoGrant {
        public static final String KEY = "room";

        public RoomName(String value) {
            super(KEY, value);
        }
    }

    public static final class CanPublish extends VideoGrant {
        public static final String KEY = "canPublish";
        public static final CanPublish ENABLED = new CanPublish(true);
        public static final CanPublish DISABLED = new CanPublish(false);

        private CanPublish(boolean value) {
            super(KEY, value);
        }
    }

    public static final class CanSubscribe extends VideoGrant {
        public static final String KEY = "canSubscribe";
        public static final CanSubscribe ENABLED = new CanSubscribe(true);
        public static final CanSubscribe DISABLED = new CanSubscribe(false);

        private CanSubscribe(boolean value) {
            super(KEY, value);
        }
    }

    public static final class CanPublishData extends VideoGrant {
        public static final String KEY = "canPublishData";
        public static final CanPublishData ENABLED = new CanPublishData(true);
        public static final CanPublishData DISABLED = new CanPublishData(false);

        private CanPublishData(boolean value) {
            super(KEY, value);
        }
    }

    public static final class CanPublishSources extends VideoGrant {
        public static final String KEY = "canPublishSources";

        public CanPublishSources(List<String> value) {
            super(KEY, value);
        }
    }

    public static final class CanUpdateOwnMetadata extends VideoGrant {
        public static final String KEY = "canUpdateOwnMetadata";
        public static final CanUpdateOwnMetadata ENABLED = new CanUpdateOwnMetadata(true);
        public static final CanUpdateOwnMetadata DISABLED = new CanUpdateOwnMetadata(false);

        private CanUpdateOwnMetadata(boolean value) {
            super(KEY, value);
        }
    }

    public static final class IngressAdmin extends VideoGrant {
        public static final String KEY = "ingressAdmin";
        public static final IngressAdmin ENABLED = new IngressAdmin(true);
        public static final IngressAdmin DISABLED = new IngressAdmin(false);

        private IngressAdmin(boolean value) {
            super(KEY, value);
        }
    }

    public static final class Hidden extends VideoGrant {
        public static final String KEY = "hidden";
        public static final Hidden ENABLED = new Hidden(true);
        public static final Hidden DISABLED = new Hidden(false);

        private Hidden(boolean value) {
            super(KEY, value);
        }
    }

    public static final class Recorder extends VideoGrant {
        public static final String KEY = "recorder";
        public static final Recorder ENABLED = new Recorder(true);
        public static final Recorder DISABLED = new Recorder(false);

        private Recorder(boolean value) {
            super(KEY, value);
        }
    }
}