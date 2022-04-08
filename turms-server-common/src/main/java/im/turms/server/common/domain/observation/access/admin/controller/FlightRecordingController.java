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

package im.turms.server.common.domain.observation.access.admin.controller;

import im.turms.server.common.access.admin.permission.AdminPermission;
import im.turms.server.common.access.admin.permission.RequiredPermission;
import im.turms.server.common.domain.common.dto.response.DeleteResultDTO;
import im.turms.server.common.domain.common.dto.response.ResponseDTO;
import im.turms.server.common.domain.common.dto.response.ResponseFactory;
import im.turms.server.common.domain.common.dto.response.UpdateResultDTO;
import im.turms.server.common.domain.observation.dto.request.CreateRecordingDTO;
import im.turms.server.common.domain.observation.dto.response.RecordingSessionDTO;
import im.turms.server.common.domain.observation.model.RecordingSession;
import im.turms.server.common.domain.observation.service.FlightRecordingService;
import jdk.jfr.Recording;
import jdk.jfr.RecordingState;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author James Chen
 */
@RestController
@RequestMapping("/flight-recordings")
public class FlightRecordingController {

    private final FlightRecordingService flightRecordingService;

    public FlightRecordingController(FlightRecordingService flightRecordingService) {
        this.flightRecordingService = flightRecordingService;
    }

    @RequiredPermission(AdminPermission.FLIGHT_RECORDING_QUERY)
    @GetMapping
    public ResponseEntity<ResponseDTO<Collection<RecordingSessionDTO>>> getRecordings(
            @RequestParam(required = false) Set<Long> ids) {
        Collection<RecordingSession> sessions = ids == null
                ? flightRecordingService.getSessions()
                : flightRecordingService.getSessions(ids);
        List<RecordingSessionDTO> result = new ArrayList<>(sessions.size());
        for (RecordingSession session : sessions) {
            Recording recording = session.recording();
            RecordingState state = recording.getState();
            result.add(new RecordingSessionDTO(
                    recording.getId(),
                    state.name(),
                    Date.from(recording.getStartTime()),
                    session.getStopTime(),
                    session.description()
            ));
        }
        return ResponseFactory.okIfTruthy(result);
    }

    @RequiredPermission(AdminPermission.FLIGHT_RECORDING_CREATE)
    @PostMapping
    public ResponseEntity<ResponseDTO<Long>> startRecording(@RequestBody CreateRecordingDTO createRecording) {
        RecordingSession session = flightRecordingService.startRecording(createRecording.durationSeconds(),
                createRecording.maxAgeSeconds(),
                createRecording.maxSizeBytes(),
                createRecording.delaySeconds(),
                createRecording.customSettings(),
                createRecording.description());
        return ResponseFactory.okIfTruthy(session.id());
    }

    @RequiredPermission(AdminPermission.FLIGHT_RECORDING_DELETE)
    @DeleteMapping
    public ResponseEntity<ResponseDTO<DeleteResultDTO>> deleteRecordings(
            @RequestParam(required = false) Set<Long> ids) {
        int deletedCount = ids == null
                ? flightRecordingService.deleteRecordings()
                : flightRecordingService.deleteRecordings(ids);
        return ResponseFactory.deleteResult(deletedCount);
    }

    @RequiredPermission(AdminPermission.FLIGHT_RECORDING_UPDATE)
    @PutMapping
    public ResponseEntity<ResponseDTO<UpdateResultDTO>> closeRecordings(
            @RequestParam(required = false) Set<Long> ids) {
        UpdateResultDTO result = ids == null
                ? flightRecordingService.closeRecordings()
                : flightRecordingService.closeRecordings(ids);
        return ResponseFactory.okIfTruthy(result);
    }

    @RequiredPermission(AdminPermission.FLIGHT_RECORDING_QUERY)
    @GetMapping("/jfr")
    public ResponseEntity<FileSystemResource> downloadRecording(
            @RequestParam Long id,
            @RequestParam(required = false) boolean close) {
        File file = flightRecordingService.getRecordingFile(id, close);
        if (file == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok()
                .headers(headers -> headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=flight-recording-" + id + ".jfr"))
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new FileSystemResource(file));
    }

}
