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

package im.turms.server.common.domain.observation.service;

import im.turms.server.common.domain.common.dto.response.UpdateResultDTO;
import im.turms.server.common.domain.observation.model.RecordingSession;
import im.turms.server.common.infra.context.TurmsApplicationContext;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.property.env.common.FlightRecorderProperties;
import im.turms.server.common.infra.random.RandomUtil;
import im.turms.server.common.infra.task.CronConst;
import im.turms.server.common.infra.task.TaskManager;
import im.turms.server.common.infra.validation.Validator;
import jdk.jfr.Configuration;
import jdk.jfr.FlightRecorder;
import jdk.jfr.Recording;
import jdk.jfr.RecordingState;
import lombok.SneakyThrows;
import org.jctools.queues.MpscUnboundedArrayQueue;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import javax.validation.constraints.Min;
import java.io.File;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author James Chen
 */
@Service
public class FlightRecordingService {

    private final File jfrBasePath;
    private final Map<String, String> defaultConfigs;
    private final int closedRecordingRetentionPeriod;

    private final Map<Long, RecordingSession> sessionMap = new ConcurrentHashMap<>(16);
    private final MpscUnboundedArrayQueue<Long> pendingClosedRecordingIds;

    public FlightRecordingService(TurmsApplicationContext context,
                                  TurmsPropertiesManager propertiesManager,
                                  TaskManager taskManager) {
        if (!FlightRecorder.isAvailable()) {
            throw new IllegalStateException("Flight recorder is unavailable");
        }
        Map<String, String> localDefaultConfigs = null;
        for (Configuration config : Configuration.getConfigurations()) {
            if (config.getName().equals("default")) {
                localDefaultConfigs = config.getSettings();
                break;
            }
        }
        if (localDefaultConfigs == null) {
            throw new IllegalStateException("No default flight recorder configuration found");
        } else {
            defaultConfigs = localDefaultConfigs;
        }
        jfrBasePath = context.getHome().resolve("./jfr").toFile();
        for (Recording recording : FlightRecorder.getFlightRecorder().getRecordings()) {
            long id = recording.getId();
            sessionMap.put(id, new RecordingSession(id, recording, null));
        }
        FlightRecorderProperties recorderProperties = propertiesManager.getLocalProperties().getFlightRecorder();
        closedRecordingRetentionPeriod = recorderProperties.getClosedRecordingRetentionPeriod();
        if (closedRecordingRetentionPeriod > 0) {
            pendingClosedRecordingIds = new MpscUnboundedArrayQueue<>(16);
            taskManager.reschedule("closedRecordingCleanup", CronConst.CLOSED_RECORDINGS_CLEANUP_CRON, () ->
                    pendingClosedRecordingIds.drain(id -> {
                        RecordingSession session = sessionMap.remove(id);
                        if (session != null) {
                            session.close(false);
                        }
                    }));
        } else {
            pendingClosedRecordingIds = null;
        }
    }

    @SneakyThrows
    public RecordingSession startRecording(
            @Min(0) Integer durationSeconds,
            @Nullable @Min(0) Integer maxAgeSeconds,
            @Nullable @Min(0) Integer maxSizeBytes,
            @Nullable @Min(0) Integer delaySeconds,
            @Nullable Map<String, String> customSettings,
            @Nullable String description) {
        Validator.notNull(durationSeconds, "durationSeconds");
        Validator.min(durationSeconds, "durationSeconds", 0);
        Validator.min(maxAgeSeconds, "maxAgeSeconds", 0);
        Validator.min(maxSizeBytes, "maxSizeBytes", 0);
        Validator.min(delaySeconds, "delaySeconds", 0);

        if (customSettings == null) {
            customSettings = defaultConfigs;
        } else {
            customSettings.putAll(defaultConfigs);
        }
        Recording recording = new Recording(customSettings);
        long id = recording.getId();
        String name = "custom-" + id;
        recording.setName(name);

        File tempFile = File.createTempFile(name, ".jfr", jfrBasePath);
        tempFile.deleteOnExit();
        try {
            recording.setDuration(Duration.of(durationSeconds, ChronoUnit.SECONDS));
            recording.setDestination(tempFile.toPath());
            recording.setToDisk(true);
            if (maxAgeSeconds != null) {
                recording.setMaxAge(Duration.of(maxAgeSeconds, ChronoUnit.SECONDS));
            }
            if (maxSizeBytes != null) {
                recording.setMaxSize(maxSizeBytes);
            }
            if (delaySeconds == null) {
                recording.start();
            } else {
                recording.scheduleStart(Duration.of(delaySeconds, ChronoUnit.SECONDS));
            }
            RecordingSession session = new RecordingSession(id, recording, description);
            sessionMap.put(id, session);
            return session;
        } catch (Exception e) {
            tempFile.delete();
            try {
                recording.close();
            } catch (Exception e2) {
                // ignore
            }
            throw e;
        }
    }

    public UpdateResultDTO closeRecordings() {
        long matchedCount = 0;
        long modifiedCount = 0;
        for (RecordingSession session : sessionMap.values()) {
            matchedCount++;
            Recording recording = session.recording();
            if (recording.getState() == RecordingState.RUNNING) {
                closeSession(session);
                modifiedCount++;
            }
        }
        return new UpdateResultDTO(matchedCount, modifiedCount);
    }

    public UpdateResultDTO closeRecordings(Set<Long> ids) {
        long matchedCount = 0;
        long modifiedCount = 0;
        for (Long id : ids) {
            RecordingSession session = sessionMap.get(id);
            if (session == null) {
                continue;
            }
            matchedCount++;
            Recording recording = session.recording();
            if (recording.getState() == RecordingState.RUNNING) {
                closeSession(session);
                modifiedCount++;
            }
        }
        return new UpdateResultDTO(matchedCount, modifiedCount);
    }

    @SneakyThrows
    @Nullable
    public File getRecordingFile(Long id, boolean close) {
        RecordingSession session = sessionMap.get(id);
        if (session == null) {
            return null;
        }
        if (close) {
            session.close(true);
            if (closedRecordingRetentionPeriod > 0) {
                // TODO: don't delete the file if it's in use
                pendingClosedRecordingIds.offer(session.id());
            } else if (closedRecordingRetentionPeriod == 0) {
                sessionMap.remove(session.id());
            }
            return session.recording().getDestination().toFile();
        } else {
            File file = File.createTempFile("temp-" + id + "-" + RandomUtil.nextPositiveInt(), ".jfr", jfrBasePath);
            file.deleteOnExit();
            session.recording().dump(file.toPath());
            return file;
        }
    }

    public int deleteRecordings() {
        int deletedCount = 0;
        Iterator<RecordingSession> iterator = sessionMap.values().iterator();
        while (iterator.hasNext()) {
            RecordingSession session = iterator.next();
            session.close(false);
            deletedCount++;
            iterator.remove();
        }
        return deletedCount;
    }

    public int deleteRecordings(Set<Long> ids) {
        int deletedCount = 0;
        for (Long id : ids) {
            RecordingSession session = sessionMap.remove(id);
            if (session == null) {
                continue;
            }
            session.close(false);
            deletedCount++;
        }
        return deletedCount;
    }

    public Collection<RecordingSession> getSessions() {
        return sessionMap.values();
    }

    public Collection<RecordingSession> getSessions(Set<Long> ids) {
        List<RecordingSession> sessions = new ArrayList<>(ids.size());
        for (Long id : ids) {
            RecordingSession session = sessionMap.get(id);
            if (session != null) {
                sessions.add(session);
            }
        }
        return sessions;
    }

    private void closeSession(RecordingSession session) {
        if (closedRecordingRetentionPeriod > 0) {
            session.close(true);
            pendingClosedRecordingIds.offer(session.id());
        } else if (closedRecordingRetentionPeriod == 0) {
            session.close(false);
            sessionMap.remove(session.id());
        }
    }

}
