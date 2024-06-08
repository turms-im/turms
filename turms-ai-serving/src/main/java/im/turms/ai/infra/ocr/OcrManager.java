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

package im.turms.ai.infra.ocr;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import ai.djl.Device;
import ai.djl.inference.Predictor;
import ai.djl.modality.Classifications;
import ai.djl.modality.cv.Image;
import ai.djl.modality.cv.output.BoundingBox;
import ai.djl.modality.cv.output.DetectedObjects;
import ai.djl.opencv.ExtendedOpenCVImage;
import ai.djl.paddlepaddle.engine.PpEngine;
import ai.djl.repository.zoo.Criteria;
import ai.djl.repository.zoo.ZooModel;
import ai.djl.translate.TranslateException;
import ai.djl.util.cuda.CudaUtils;
import io.netty.util.concurrent.FastThreadLocal;
import nu.pattern.OpenCV;
import org.opencv.core.Core;

import im.turms.server.common.infra.lang.StringUtil;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;

import static im.turms.ai.infra.image.ImageUtil.extendBox;
import static im.turms.ai.infra.image.ImageUtil.getSubImage;
import static im.turms.ai.infra.image.ImageUtil.rotate90;

/**
 * @author James Chen
 */
public class OcrManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(OcrManager.class);

    private final float orientatePossibilityThreshold;

    private final ZooModel<Image, DetectedObjects> detectionModel;
    private final ZooModel<Image, Classifications> orientationClassificationModel;
    private final ZooModel<Image, String> recognitionModel;

    private final FastThreadLocal<Predictor<Image, DetectedObjects>> detector =
            new FastThreadLocal<>() {
                @Override
                protected Predictor<Image, DetectedObjects> initialValue() {
                    return detectionModel.newPredictor();
                }
            };
    private final FastThreadLocal<Predictor<Image, Classifications>> predictor =
            new FastThreadLocal<>() {
                @Override
                protected Predictor<Image, Classifications> initialValue() {
                    return orientationClassificationModel.newPredictor();
                }
            };
    private final FastThreadLocal<Predictor<Image, String>> recognizer = new FastThreadLocal<>() {
        @Override
        protected Predictor<Image, String> initialValue() {
            return recognitionModel.newPredictor();
        }
    };

    static {
        OpenCV.loadLocally();
        LOGGER.info("Loaded OpenCV: "
                + Core.getVersionString());
    }

    public OcrManager(
            float orientatePossibilityThreshold,
            Path dictPath,
            Path detectionModelPath,
            Path orientiationClassificationModelPath,
            Path recognitionModelPath) {
        if (!Files.exists(dictPath)) {
            throw new RuntimeException(
                    "The dictionary file does not exist: "
                            + dictPath);
        }
        if (!Files.exists(detectionModelPath)) {
            throw new RuntimeException(
                    "The detection model file does not exist: "
                            + detectionModelPath);
        }
        if (!Files.exists(orientiationClassificationModelPath)) {
            throw new RuntimeException(
                    "The orientation classification model file does not exist: "
                            + orientiationClassificationModelPath);
        }
        if (!Files.exists(recognitionModelPath)) {
            throw new RuntimeException(
                    "The recognition model file does not exist: "
                            + recognitionModelPath);
        }

        this.orientatePossibilityThreshold = orientatePossibilityThreshold;
        Device device = CudaUtils.hasCuda()
                ? Device.gpu()
                : Device.cpu();

        try {
            detectionModel = Criteria.builder()
                    .setTypes(Image.class, DetectedObjects.class)
                    .optDevice(device)
                    .optEngine(PpEngine.ENGINE_NAME)
                    .optModelPath(detectionModelPath)
                    .optTranslator(new PaddleWordDetectionTranslator())
                    .build()
                    .loadModel();
        } catch (Exception e) {
            throw new RuntimeException("Failed to load the detection model", e);
        }
        try {
            orientationClassificationModel = Criteria.builder()
                    .setTypes(Image.class, Classifications.class)
                    .optDevice(device)
                    .optEngine(PpEngine.ENGINE_NAME)
                    .optModelPath(orientiationClassificationModelPath)
                    .optTranslator(new PaddleWordOrientationTranslator())
                    .build()
                    .loadModel();
        } catch (Exception e) {
            throw new RuntimeException("Failed to load the orientation classification model", e);
        }
        try {
            recognitionModel = Criteria.builder()
                    .setTypes(Image.class, String.class)
                    .optDevice(device)
                    .optEngine(PpEngine.ENGINE_NAME)
                    .optModelPath(recognitionModelPath)
                    .optTranslator(new PaddleWordRecognitionTranslator(dictPath))
                    .build()
                    .loadModel();
        } catch (Exception e) {
            throw new RuntimeException("Failed to load the recognition model", e);
        }
    }

    public void close() {
        detectionModel.close();
        orientationClassificationModel.close();
        recognitionModel.close();
    }

    public DetectedObjects ocr(String imagePath) {
        try (ExtendedOpenCVImage image = new ExtendedOpenCVImage(imagePath)) {
            return ocr(image);
        }
    }

    public DetectedObjects ocr(Image image) {
        DetectedObjects detectResult = detect(image);
        List<DetectedObjects.DetectedObject> detectedObjects = detectResult.items();
        int size = detectedObjects.size();
        List<String> names = new ArrayList<>(size);
        List<Double> probabilities = new ArrayList<>(size);
        List<BoundingBox> boundingBoxes = new ArrayList<>(size);
        for (DetectedObjects.DetectedObject detectedObject : detectedObjects) {
            BoundingBox box = detectedObject.getBoundingBox();
            // Expands the text box because the detected text box
            // is smaller than the actual text box
            box = extendBox(box);
            Image subImg = getSubImage(image, box);
            if (subImg.getHeight() * 1.0 / subImg.getWidth() > 1.5) {
                subImg = rotate90(subImg);
            }
            Classifications.Classification classification = classifyOrientation(subImg);
            if (PaddleWordOrientationTranslator.ROTATED.equals(classification.getClassName())
                    && classification.getProbability() > orientatePossibilityThreshold) {
                subImg = rotate90(subImg);
            }
            String name = recognize(subImg);
            if (StringUtil.isBlank(name)) {
                continue;
            }
            names.add(name);
            probabilities.add(1.0);
            boundingBoxes.add(box);
        }
        return new DetectedObjects(names, probabilities, boundingBoxes);
    }

    public DetectedObjects detect(Image image) {
        try {
            return detector.get()
                    .predict(image);
        } catch (TranslateException e) {
            throw new RuntimeException("Failed to detect", e);
        }
    }

    private Classifications.Classification classifyOrientation(Image image) {
        try {
            Classifications predict = predictor.get()
                    .predict(image);
            return predict.best();
        } catch (TranslateException e) {
            throw new RuntimeException("Failed to classify orientation", e);
        }
    }

    private String recognize(Image image) {
        try {
            return recognizer.get()
                    .predict(image);
        } catch (TranslateException e) {
            throw new RuntimeException("Failed to recognize", e);
        }
    }

}