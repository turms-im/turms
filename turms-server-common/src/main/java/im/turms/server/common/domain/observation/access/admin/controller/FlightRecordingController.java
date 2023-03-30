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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import jdk.jfr.Recording;
import jdk.jfr.RecordingState;

import im.turms.server.common.access.admin.dto.response.DeleteResultDTO;
import im.turms.server.common.access.admin.dto.response.HttpHandlerResult;
import im.turms.server.common.access.admin.dto.response.ResponseDTO;
import im.turms.server.common.access.admin.dto.response.UpdateResultDTO;
import im.turms.server.common.access.admin.permission.AdminPermission;
import im.turms.server.common.access.admin.permission.RequiredPermission;
import im.turms.server.common.access.admin.web.HttpResponseException;
import im.turms.server.common.access.admin.web.annotation.DeleteMapping;
import im.turms.server.common.access.admin.web.annotation.GetMapping;
import im.turms.server.common.access.admin.web.annotation.PostMapping;
import im.turms.server.common.access.admin.web.annotation.PutMapping;
import im.turms.server.common.access.admin.web.annotation.QueryParam;
import im.turms.server.common.access.admin.web.annotation.RequestBody;
import im.turms.server.common.access.admin.web.annotation.RestController;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.domain.observation.access.admin.dto.request.CreateRecordingDTO;
import im.turms.server.common.domain.observation.access.admin.dto.response.RecordingSessionDTO;
import im.turms.server.common.domain.observation.exception.DumpIllegalStateException;
import im.turms.server.common.domain.observation.model.RecordingSession;
import im.turms.server.common.domain.observation.service.FlightRecordingService;
import im.turms.server.common.infra.io.FileResource;

/**
 * @author James Chen
 */
@RestController("flight-recordings")
public class FlightRecordingController {

    private final FlightRecordingService flightRecordingService;

    public FlightRecordingController(FlightRecordingService flightRecordingService) {
        this.flightRecordingService = flightRecordingService;
    }

    @RequiredPermission(AdminPermission.FLIGHT_RECORDING_QUERY)
    @GetMapping
    public HttpHandlerResult<ResponseDTO<Collection<RecordingSessionDTO>>> getRecordings(
            @QueryParam(required = false) Set<Long> ids) {
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
                    session.getCloseDate(),
                    session.description()));
        }
        return HttpHandlerResult.okIfTruthy(result);
    }

    @RequiredPermission(AdminPermission.FLIGHT_RECORDING_CREATE)
    @PostMapping
    public HttpHandlerResult<ResponseDTO<Long>> startRecording(
            @RequestBody CreateRecordingDTO createRecording) {
        RecordingSession session =
                flightRecordingService.startRecording(createRecording.durationSeconds(),
                        createRecording.maxAgeSeconds(),
                        createRecording.maxSizeBytes(),
                        createRecording.delaySeconds(),
                        createRecording.customSettings(),
                        createRecording.description());
        return HttpHandlerResult.okIfTruthy(session.id());
    }

    @RequiredPermission(AdminPermission.FLIGHT_RECORDING_DELETE)
    @DeleteMapping
    public HttpHandlerResult<ResponseDTO<DeleteResultDTO>> deleteRecordings(
            @QueryParam(required = false) Set<Long> ids) {
        int deletedCount = ids == null
                ? flightRecordingService.deleteRecordings()
                : flightRecordingService.deleteRecordings(ids);
        return HttpHandlerResult.deleteResult(deletedCount);
    }

    @RequiredPermission(AdminPermission.FLIGHT_RECORDING_UPDATE)
    @PutMapping
    public HttpHandlerResult<ResponseDTO<UpdateResultDTO>> closeRecordings(
            @QueryParam(required = false) Set<Long> ids) {
        UpdateResultDTO result = ids == null
                ? flightRecordingService.closeRecordings()
                : flightRecordingService.closeRecordings(ids);
        return HttpHandlerResult.okIfTruthy(result);
    }

    @RequiredPermission(AdminPermission.FLIGHT_RECORDING_QUERY)
    @GetMapping("jfr")
    public FileResource downloadJfr(Long id, boolean close) {
        FileResource file;
        try {
            file = flightRecordingService.getRecordingFile(id, close);
        } catch (DumpIllegalStateException e) {
            throw new HttpResponseException(ResponseStatusCode.DUMP_JFR_IN_ILLEGAL_STATUS, e);
        }
        if (file == null) {
            throw new HttpResponseException(
                    ResponseStatusCode.RESOURCE_NOT_FOUND,
                    "Recording not found");
        }
        return file;
    }

}
