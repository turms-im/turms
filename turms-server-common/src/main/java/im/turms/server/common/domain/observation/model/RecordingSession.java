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

package im.turms.server.common.domain.observation.model;

import jdk.jfr.Recording;
import jdk.jfr.RecordingState;

import javax.annotation.Nullable;
import java.util.Date;

/**
 * @author James Chen
 */
public record RecordingSession(
        Long id,
        Recording recording,
        @Nullable
        String description
) {

    @Nullable
    public Date getStopTime() {
        RecordingState state = recording.getState();
        return state == RecordingState.CLOSED || state == RecordingState.STOPPED
                ? Date.from(recording.getStopTime()) : null;
    }

    public void close(boolean keepFile) {
        RecordingState state = recording.getState();
        try {
            if (state == RecordingState.RUNNING) {
                recording.stop();
                recording.close();
            } else if (state == RecordingState.STOPPED) {
                recording.close();
            }
        } catch (IllegalStateException e) {
            // ignore
        }
        if (!keepFile) {
            recording.getDestination().toFile().delete();
        }
    }
}
