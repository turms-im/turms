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

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import jakarta.annotation.Nullable;
import jakarta.annotation.PreDestroy;

import ai.djl.modality.cv.output.DetectedObjects;
import ai.djl.opencv.ExtendedOpenCVImage;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.springframework.stereotype.Service;

import im.turms.ai.infra.image.ImageUtil;
import im.turms.ai.infra.ocr.OcrManager;
import im.turms.server.common.domain.common.service.BaseService;
import im.turms.server.common.infra.application.TurmsApplicationContext;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.io.InputOutputException;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.property.env.aiserving.FontProperties;
import im.turms.server.common.infra.property.env.aiserving.OcrProperties;

/**
 * @author James Chen
 */
@Service
public class OcrService extends BaseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OcrService.class);

    private final Font font;
    private final OcrManager ocrManager;

    public OcrService(TurmsApplicationContext context, TurmsPropertiesManager propertiesManager) {
        registerCustomFonts(context.getHome()
                .resolve("font"));
        OcrProperties ocrProperties = propertiesManager.getLocalProperties()
                .getAiServing()
                .getOcr();
        Font preferredFont = findPreferredFont(ocrProperties.getPreferredFonts());
        font = preferredFont == null
                ? new Font(null, Font.PLAIN, 1)
                : preferredFont;
        Path modelDir = context.getHome()
                .resolve("model");
        ocrManager = new OcrManager(
                ocrProperties.getOrientationPossibilityThreshold(),
                modelDir.resolve("dict.txt"),
                modelDir.resolve("detection.tar"),
                modelDir.resolve("orientation-classification.tar"),
                modelDir.resolve("recognition.tar"));

        // Warm up
        try (ExtendedOpenCVImage image = new ExtendedOpenCVImage(Mat.zeros(1, 1, CvType.CV_8U))) {
            ocrManager.ocr(image);
        }
    }

    private void registerCustomFonts(Path fontDir) {
        if (Files.notExists(fontDir)) {
            return;
        }
        GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try (Stream<Path> stream = Files.list(fontDir)) {
            stream.forEach(path -> {
                if (!Files.isRegularFile(path)) {
                    return;
                }
                String pathStr = path.toString();
                if (!pathStr.endsWith(".otf") && !pathStr.endsWith(".ttf")) {
                    return;
                }
                try (InputStream inputStream = Files.newInputStream(path)) {
                    Font font = Font.createFont(Font.PLAIN, inputStream);
                    if (environment.registerFont(font)) {
                        LOGGER.info("Registered a custom font: \""
                                + font.getFontName()
                                + "\"");
                    }
                } catch (IOException e) {
                    throw new InputOutputException(
                            "Failed to read from the path: "
                                    + pathStr,
                            e);
                } catch (FontFormatException e) {
                    throw new RuntimeException(
                            "Failed to create the font: "
                                    + pathStr,
                            e);
                }
            });
        } catch (IOException e) {
            throw new InputOutputException("Failed to find fonts", e);
        }
    }

    @Nullable
    private Font findPreferredFont(List<FontProperties> fontPropertiesList) {
        if (fontPropertiesList.isEmpty()) {
            return null;
        }
        GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Set<String> fontFamilyNames =
                CollectionUtil.newSet(environment.getAvailableFontFamilyNames());
        for (FontProperties fontProperties : fontPropertiesList) {
            String familyName = fontProperties.getFamilyName();
            if (fontFamilyNames.contains(familyName)) {
                return new Font(
                        familyName,
                        fontProperties.getStyle()
                                .getValue(),
                        1);
            }
        }
        return null;
    }

    @PreDestroy
    public void closeAll() {
        ocrManager.close();
    }

    public DetectedObjects ocr(String imagePath) {
        return ocrManager.ocr(imagePath);
    }

    public Path ocrAndWriteToFile(File file) {
        String imagePath = file.toPath()
                .toAbsolutePath()
                .normalize()
                .toString();
        try (ExtendedOpenCVImage image = new ExtendedOpenCVImage(imagePath)) {
            DetectedObjects detectedObjects = ocrManager.ocr(image);
            Mat mat = ImageUtil.drawBoundingBoxes(image.getWrappedImage(), detectedObjects, font);
            return ImageUtil.writeTempImageFile(mat);
        }
    }

}