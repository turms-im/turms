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

import java.util.ArrayList;
import java.util.List;

import ai.djl.modality.cv.Image;
import ai.djl.modality.cv.ImageFactory;
import ai.djl.modality.cv.output.BoundingBox;
import ai.djl.modality.cv.output.DetectedObjects;
import ai.djl.modality.cv.output.Rectangle;
import ai.djl.modality.cv.util.NDImageUtils;
import ai.djl.ndarray.NDArray;
import ai.djl.ndarray.NDList;
import ai.djl.paddlepaddle.zoo.cv.objectdetection.PpWordDetectionTranslator;
import ai.djl.translate.NoBatchifyTranslator;
import ai.djl.translate.TranslatorContext;

/**
 * @author James Chen
 * @see PpWordDetectionTranslator
 */
public class PaddleWordDetectionTranslator implements NoBatchifyTranslator<Image, DetectedObjects> {

    private static final int MAX_LENGTH = 960;

    @Override
    public DetectedObjects processOutput(TranslatorContext ctx, NDList list) {
        NDArray result = list.singletonOrThrow();
        ImageFactory factory = ImageFactory.getInstance();
        result = result.squeeze(0);
        Image image = factory.fromNDArray(result);
        List<BoundingBox> boxes = image.findBoundingBoxes();
        int boxSize = boxes.size();
        List<String> names = new ArrayList<>(boxSize);
        List<Double> probs = new ArrayList<>(boxSize);
        for (BoundingBox box : boxes) {
            Rectangle rect = (Rectangle) box;
            if (rect.getWidth() * image.getWidth() > 5
                    || rect.getHeight() * image.getHeight() > 5) {
                names.add("word");
                probs.add(1.0);
            }
        }
        return new DetectedObjects(names, probs, boxes);
    }

    @Override
    public NDList processInput(TranslatorContext ctx, Image input) {
        NDArray img = input.toNDArray(ctx.getNDManager());
        int h = input.getHeight();
        int w = input.getWidth();
        int[] hw = scale(h, w);

        img = NDImageUtils.resize(img, hw[1], hw[0]);
        img = NDImageUtils.toTensor(img);
        img = NDImageUtils.normalize(img,
                new float[]{0.485f, 0.456f, 0.406f},
                new float[]{0.229f, 0.224f, 0.225f});
        img = img.expandDims(0);
        return new NDList(img);
    }

    private int[] scale(int h, int w) {
        int localMax = Math.max(h, w);
        float scale = 1.0f;
        if (PaddleWordDetectionTranslator.MAX_LENGTH < localMax) {
            scale = PaddleWordDetectionTranslator.MAX_LENGTH * 1.0f / localMax;
        }
        // paddle model only take 32-based size
        return resize32(h * scale, w * scale);
    }

    private int[] resize32(double h, double w) {
        double min = Math.min(h, w);
        if (min < 32) {
            h = 32.0 / min * h;
            w = 32.0 / min * w;
        }
        int h32 = (int) h / 32;
        int w32 = (int) w / 32;
        return new int[]{h32 * 32, w32 * 32};
    }
}