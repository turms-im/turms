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

import java.util.List;

import ai.djl.modality.Classifications;
import ai.djl.modality.cv.Image;
import ai.djl.modality.cv.util.NDImageUtils;
import ai.djl.ndarray.NDArray;
import ai.djl.ndarray.NDList;
import ai.djl.paddlepaddle.zoo.cv.imageclassification.PpWordRotateTranslator;
import ai.djl.translate.NoBatchifyTranslator;
import ai.djl.translate.TranslatorContext;

/**
 * @author James Chen
 * @see PpWordRotateTranslator
 */
public class PaddleWordOrientationTranslator
        implements NoBatchifyTranslator<Image, Classifications> {

    public static final String NORMAL = "n";
    public static final String ROTATED = "r";
    private static final List<String> CLASSES = List.of(NORMAL, ROTATED);

    @Override
    public Classifications processOutput(TranslatorContext ctx, NDList list) {
        NDArray prob = list.singletonOrThrow();
        return new Classifications(CLASSES, prob);
    }

    @Override
    public NDList processInput(TranslatorContext ctx, Image input) {
        NDArray img = input.toNDArray(ctx.getNDManager());
        int[] hw = resize32(input.getHeight(), input.getWidth());
        img = NDImageUtils.resize(img, hw[1], hw[0]);
        img = NDImageUtils.toTensor(img)
                .sub(0.5f)
                .div(0.5f);
        img = img.expandDims(0);
        return new NDList(img);
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