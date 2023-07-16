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

package im.turms.ai.domain.ocr.service;

import java.nio.file.Path;
import jakarta.annotation.PreDestroy;

import ai.djl.modality.cv.Image;
import ai.djl.modality.cv.output.DetectedObjects;
import org.springframework.stereotype.Service;

import im.turms.ai.infra.ocr.OcrManager;
import im.turms.server.common.infra.context.TurmsApplicationContext;
import im.turms.server.common.infra.property.TurmsPropertiesManager;

/**
 * @author James Chen
 */
@Service
public class OcrService {

    private final OcrManager ocrManager;

    public OcrService(TurmsApplicationContext context, TurmsPropertiesManager propertiesManager) {
        Path modelDir = context.getHome()
                .resolve("model");
        ocrManager = new OcrManager(
                propertiesManager.getLocalProperties()
                        .getAiServing()
                        .getOcr()
                        .getOrientatePossibilityThreshold(),
                modelDir.resolve("dict.txt"),
                modelDir.resolve("detection.tar"),
                modelDir.resolve("orientation-classification.tar"),
                modelDir.resolve("recognition.tar"));
    }

    @PreDestroy
    public void closeAll() {
        ocrManager.close();
    }

    public DetectedObjects ocr(String imagePath) {
        return ocrManager.ocr(imagePath);
    }

    public DetectedObjects ocr(Image image) {
        return ocrManager.ocr(image);
    }

}