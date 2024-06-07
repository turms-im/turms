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

package im.turms.ai.domain.ocr.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import ai.djl.modality.cv.output.DetectedObjects;
import ai.djl.modality.cv.output.Rectangle;

import im.turms.ai.domain.ocr.dto.DetectObjectDTO;
import im.turms.ai.domain.ocr.service.OcrService;
import im.turms.server.common.access.admin.dto.response.HttpHandlerResult;
import im.turms.server.common.access.admin.dto.response.ResponseDTO;
import im.turms.server.common.access.admin.web.MediaTypeConst;
import im.turms.server.common.access.admin.web.MultipartFile;
import im.turms.server.common.access.admin.web.annotation.FormData;
import im.turms.server.common.access.admin.web.annotation.PostMapping;
import im.turms.server.common.access.admin.web.annotation.RestController;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.infra.exception.ResponseException;
import im.turms.server.common.infra.io.FileResource;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;

import static im.turms.server.common.access.admin.web.MediaTypeConst.IMAGE_PNG;

/**
 * @author James Chen
 */
@RestController("ocr")
public class OcrController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OcrController.class);

    private final OcrService ocrService;

    public OcrController(OcrService ocrService) {
        this.ocrService = ocrService;
    }

    @PostMapping
    public HttpHandlerResult<ResponseDTO<Collection<DetectObjectDTO>>> ocr(
            @FormData(contentType = MediaTypeConst.IMAGE) List<MultipartFile> files) {
        int size = files.size();
        if (0 == size) {
            return HttpHandlerResult.okIfTruthy(Collections.emptyList());
        }
        if (1 < size) {
            throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    "Only support one image");
        }
        MultipartFile file = files.getFirst();
        String imagePath = file.file()
                .toPath()
                .toAbsolutePath()
                .normalize()
                .toString();
        DetectedObjects detectedObjects = ocrService.ocr(imagePath);
        List<DetectedObjects.DetectedObject> items = detectedObjects.items();
        List<DetectObjectDTO> dtos = new ArrayList<>(items.size());
        for (DetectedObjects.DetectedObject item : items) {
            Rectangle bounds = item.getBoundingBox()
                    .getBounds();
            dtos.add(new DetectObjectDTO(
                    item.getClassName(),
                    item.getProbability(),
                    bounds.getX(),
                    bounds.getY(),
                    bounds.getWidth(),
                    bounds.getHeight()));
        }
        return HttpHandlerResult.okIfTruthy(dtos);
    }

    @PostMapping(value = "image", produces = IMAGE_PNG)
    public FileResource ocrImage(
            @FormData(contentType = MediaTypeConst.IMAGE) List<MultipartFile> files) {
        int size = files.size();
        if (0 == size) {
            throw ResponseException.get(ResponseStatusCode.NO_CONTENT);
        }
        if (1 < size) {
            throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    "Only support one image");
        }
        MultipartFile file = files.getFirst();
        Path path = ocrService.ocrAndWriteToFile(file.file());
        return new FileResource("result.png", path, t -> {
            try {
                Files.deleteIfExists(path);
            } catch (IOException e) {
                LOGGER.warn("Failed to delete the output image file: "
                        + path, e);
            }
        });
    }

}