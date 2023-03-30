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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Min;

import jdk.jfr.Configuration;
import jdk.jfr.FlightRecorder;
import jdk.jfr.Recording;
import org.jctools.maps.NonBlockingHashMapLong;
import org.jctools.queues.MpscUnboundedArrayQueue;
import org.springframework.stereotype.Service;

import im.turms.server.common.access.admin.dto.response.UpdateResultDTO;
import im.turms.server.common.domain.observation.model.RecordingSession;
import im.turms.server.common.infra.context.TurmsApplicationContext;
import im.turms.server.common.infra.exception.IncompatibleJvmException;
import im.turms.server.common.infra.io.FileResource;
import im.turms.server.common.infra.io.FileUtil;
import im.turms.server.common.infra.io.InputOutputException;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.property.env.common.FlightRecorderProperties;
import im.turms.server.common.infra.random.RandomUtil;
import im.turms.server.common.infra.task.CronConst;
import im.turms.server.common.infra.task.TaskManager;
import im.turms.server.common.infra.validation.Validator;

/**
 * @author James Chen
 */
@Service
public class FlightRecordingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlightRecordingService.class);

    private final File jfrBaseDir;
    private final Path jfrBaseDirPath;
    private final Map<String, String> defaultConfigs;
    private final int closedRecordingRetentionPeriod;

    /**
     * Includes not deleted recording sessions
     */
    private final NonBlockingHashMapLong<RecordingSession> idToSession =
            new NonBlockingHashMapLong<>(16);
    private final MpscUnboundedArrayQueue<Long> closedNotDeletedSessionIds;

    public FlightRecordingService(
            TurmsApplicationContext context,
            TurmsPropertiesManager propertiesManager,
            TaskManager taskManager) {
        if (!FlightRecorder.isAvailable()) {
            throw new IncompatibleJvmException("The flight recorder is unavailable");
        }
        Map<String, String> localDefaultConfigs = null;
        for (Configuration config : Configuration.getConfigurations()) {
            if (config.getName()
                    .equals("default")) {
                localDefaultConfigs = config.getSettings();
                break;
            }
        }
        if (localDefaultConfigs == null) {
            throw new IncompatibleJvmException("No default flight recorder configuration found");
        } else {
            defaultConfigs = localDefaultConfigs;
        }
        jfrBaseDirPath = context.getHome()
                .resolve("./jfr");
        jfrBaseDir = jfrBaseDirPath.toFile();
        createJfrDir();
        for (Recording recording : FlightRecorder.getFlightRecorder()
                .getRecordings()) {
            long id = recording.getId();
            idToSession.put(id, new RecordingSession(id, recording, null));
        }
        FlightRecorderProperties recorderProperties = propertiesManager.getLocalProperties()
                .getFlightRecorder();
        closedRecordingRetentionPeriod = recorderProperties.getClosedRecordingRetentionPeriod();
        if (closedRecordingRetentionPeriod > 0) {
            closedNotDeletedSessionIds = new MpscUnboundedArrayQueue<>(16);
            taskManager.reschedule("closedRecordingCleanup",
                    CronConst.CLOSED_RECORDINGS_CLEANUP_CRON,
                    () -> {
                        List<Long> pendingSessionIds = null;
                        Long sessionId;
                        while ((sessionId = closedNotDeletedSessionIds.relaxedPoll()) != null) {
                            RecordingSession session = idToSession.remove(sessionId);
                            if (session == null) {
                                continue;
                            }
                            try {
                                session.deleteFile();
                            } catch (Exception e) {
                                // The file may be in use
                                LOGGER.error(
                                        "Failed to delete the recording file of the session: {}",
                                        sessionId,
                                        e);
                                if (session.checkIfFileExists()) {
                                    if (pendingSessionIds == null) {
                                        pendingSessionIds = new ArrayList<>();
                                    }
                                    pendingSessionIds.add(sessionId);
                                    idToSession.put(sessionId, session);
                                }
                            }
                        }
                        if (pendingSessionIds != null) {
                            closedNotDeletedSessionIds.addAll(pendingSessionIds);
                        }
                    });
        } else {
            closedNotDeletedSessionIds = null;
        }
    }

    private void createJfrDir() {
        try {
            Files.createDirectories(jfrBaseDirPath);
        } catch (IOException e) {
            throw new InputOutputException(
                    "Failed to create the JFR directory: "
                            + jfrBaseDirPath.normalize(),
                    e);
        }
    }

    public RecordingSession startRecording(
            @Nullable @Min(0) Integer durationSeconds,
            @Nullable @Min(0) Integer maxAgeSeconds,
            @Nullable @Min(0) Integer maxSizeBytes,
            @Nullable @Min(0) Integer delaySeconds,
            @Nullable Map<String, String> customSettings,
            @Nullable String description) {
        Validator.min(durationSeconds, "durationSeconds", 0);
        Validator.min(maxAgeSeconds, "maxAgeSeconds", 0);
        Validator.min(maxSizeBytes, "maxSizeBytes", 0);
        Validator.min(delaySeconds, "delaySeconds", 0);
        // TODO: max limit
        if (customSettings == null || customSettings.isEmpty()) {
            customSettings = defaultConfigs;
        } else {
            customSettings.putAll(defaultConfigs);
        }
        Recording recording = new Recording(customSettings);
        long id = recording.getId();
        String name = "custom-"
                + id;
        recording.setName(name);

        // Ensure the directory exists because it may be deleted by users unexpectedly
        createJfrDir();
        File tempFile = FileUtil.createTempFile(name, ".jfr", jfrBaseDir);
        try {
            recording.setDestination(tempFile.toPath());
            recording.setToDisk(true);
            if (durationSeconds != null) {
                recording.setDuration(Duration.of(durationSeconds, ChronoUnit.SECONDS));
            }
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
            idToSession.put(id, session);
            return session;
        } catch (Exception e) {
            try {
                recording.close();
            } catch (Exception ignored) {
                // ignore
            }
            tempFile.delete();
            throw new RuntimeException("Failed to start a recording", e);
        }
    }

    public UpdateResultDTO closeRecordings() {
        long matchedCount = 0;
        long modifiedCount = 0;
        for (RecordingSession session : idToSession.values()) {
            matchedCount++;
            if (session.isRunning()) {
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
            RecordingSession session = idToSession.get(id);
            if (session == null) {
                continue;
            }
            matchedCount++;
            if (session.isRunning()) {
                closeSession(session);
                modifiedCount++;
            }
        }
        return new UpdateResultDTO(matchedCount, modifiedCount);
    }

    public void closeRecording(Long id) {
        RecordingSession session = idToSession.get(id);
        if (session == null) {
            return;
        }
        if (session.isRunning()) {
            closeSession(session);
        }
    }

    @Nullable
    public FileResource getRecordingFile(Long id, boolean close) {
        RecordingSession session = idToSession.get(id);
        if (session == null) {
            return null;
        }
        String fileName = id
                + ".jfr";
        if (close) {
            session.close(true);
            return new FileResource(fileName, session.getFilePath(), throwable -> {
                if (throwable == null) {
                    closeRecording(id);
                } else {
                    LOGGER.error("Failed to close the recording session: {}", id, throwable);
                }
            });
        }
        String prefix = "temp-"
                + id
                + "-"
                + RandomUtil.nextPositiveInt();
        File tempFile = FileUtil.createTempFile(prefix, ".jfr", jfrBaseDir);
        return new FileResource(fileName, session.getFilePath(tempFile));
    }

    public int deleteRecordings() {
        int deletedCount = 0;
        Iterator<RecordingSession> iterator = idToSession.values()
                .iterator();
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
            RecordingSession session = idToSession.remove(id);
            if (session == null) {
                continue;
            }
            session.close(false);
            deletedCount++;
        }
        return deletedCount;
    }

    public Collection<RecordingSession> getSessions() {
        return idToSession.values();
    }

    public Collection<RecordingSession> getSessions(Set<Long> ids) {
        List<RecordingSession> sessions = new ArrayList<>(ids.size());
        for (Long id : ids) {
            RecordingSession session = idToSession.get(id);
            if (session != null) {
                sessions.add(session);
            }
        }
        return sessions;
    }

    private void closeSession(RecordingSession session) {
        if (closedRecordingRetentionPeriod > 0) {
            session.close(true);
            closedNotDeletedSessionIds.offer(session.id());
        } else if (closedRecordingRetentionPeriod == 0) {
            session.close(false);
            idToSession.remove(session.id(), session);
        } else {
            session.close(true);
        }
    }

}
