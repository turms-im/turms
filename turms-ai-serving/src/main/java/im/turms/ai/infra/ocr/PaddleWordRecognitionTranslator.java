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

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import ai.djl.modality.cv.Image;
import ai.djl.modality.cv.util.NDImageUtils;
import ai.djl.ndarray.NDArray;
import ai.djl.ndarray.NDList;
import ai.djl.paddlepaddle.zoo.cv.wordrecognition.PpWordRecognitionTranslator;
import ai.djl.translate.NoBatchifyTranslator;
import ai.djl.translate.TranslatorContext;

/**
 * @author James Chen
 * @see PpWordRecognitionTranslator
 */
public class PaddleWordRecognitionTranslator implements NoBatchifyTranslator<Image, String> {

    private final String[] table;

    public PaddleWordRecognitionTranslator(Path dictFile) {
        List<String> result = new ArrayList<>(1024);
        result.add("blank");
        try (BufferedReader reader = Files.newBufferedReader(dictFile, StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.add(line.trim());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (result.size() == 1) {
            throw new IllegalArgumentException(
                    "The dictionary file is blank: "
                            + dictFile.toAbsolutePath()
                                    .normalize());
        }
        result.add("");
        table = result.toArray(new String[0]);
    }

    @Override
    public String processOutput(TranslatorContext ctx, NDList list) {
        StringBuilder sb = new StringBuilder();
        NDArray tokens = list.singletonOrThrow();
        long[] indices = tokens.get(0)
                .argMax(1)
                .toLongArray();
        int lastIdx = 0;
        int length = indices.length;
        for (int i = 0; i < length; i++) {
            if (indices[i] > 0 && !(i > 0 && indices[i] == lastIdx)) {
                sb.append(table[(int) indices[i]]);
            }
        }
        return sb.toString();
    }

    @Override
    public NDList processInput(TranslatorContext ctx, Image input) {
        NDArray img = input.toNDArray(ctx.getNDManager());
        int[] hw = resize32(input.getWidth());
        img = NDImageUtils.resize(img, hw[1], hw[0]);
        img = NDImageUtils.toTensor(img)
                .sub(0.5f)
                .div(0.5f);
        img = img.expandDims(0);
        return new NDList(img);
    }

    private int[] resize32(double w) {
        // Paddle doesn't rely on aspect ratio
        int width = ((int) Math.max(32, w)) / 32 * 32;
        return new int[]{32, width};
    }
}