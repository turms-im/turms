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

package im.turms.plugin.antispam.controller;

import java.util.List;

import im.turms.plugin.antispam.AntiSpamHandler;
import im.turms.plugin.antispam.core.exception.CorruptedTrieDataException;
import im.turms.plugin.antispam.dto.TextDetectResultDTO;
import im.turms.server.common.access.admin.dto.response.HttpHandlerResult;
import im.turms.server.common.access.admin.dto.response.ResponseDTO;
import im.turms.server.common.access.admin.dto.response.UpdateResultDTO;
import im.turms.server.common.access.admin.web.MultipartFile;
import im.turms.server.common.access.admin.web.annotation.FormData;
import im.turms.server.common.access.admin.web.annotation.GetMapping;
import im.turms.server.common.access.admin.web.annotation.PostMapping;
import im.turms.server.common.access.admin.web.annotation.QueryParam;
import im.turms.server.common.access.admin.web.annotation.RestController;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.infra.exception.ResponseException;
import im.turms.server.common.infra.exception.ThrowableUtil;
import im.turms.server.common.infra.lang.Pair;

/**
 * @author James Chen
 */
@RestController("content-moderation")
public class ContentModerationController {

    private final AntiSpamHandler antiSpamHandler;

    public ContentModerationController(AntiSpamHandler antiSpamHandler) {
        this.antiSpamHandler = antiSpamHandler;
    }

    @GetMapping("text")
    HttpHandlerResult<ResponseDTO<TextDetectResultDTO>> detectUnwantedWords(
            @QueryParam String text,
            @QueryParam(required = false) Integer maxUnwantedWordCount,
            @QueryParam(required = false) Byte mask) {
        Pair<String, List<String>> result =
                antiSpamHandler.detectUnwantedWords(text, maxUnwantedWordCount, mask);
        return HttpHandlerResult
                .okIfTruthy(new TextDetectResultDTO(result.first(), result.second()));
    }

    @PostMapping("text/tries")
    HttpHandlerResult<ResponseDTO<UpdateResultDTO>> updateTextTrie(
            @FormData List<MultipartFile> files) {
        int count = files.size();
        return switch (count) {
            case 0 -> HttpHandlerResult.updateResult(0);
            case 1 -> {
                MultipartFile file = files.getFirst();
                String binFilePath = file.file()
                        .toPath()
                        .toAbsolutePath()
                        .normalize()
                        .toString();
                try {
                    antiSpamHandler.updateTrie(binFilePath);
                } catch (Exception e) {
                    if (ThrowableUtil.contains(e, CorruptedTrieDataException.class)) {
                        throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                                e.getMessage());
                    }
                    throw e;
                }
                yield HttpHandlerResult.updateResult(1);
            }
            default -> throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    "The number of files must be 0 or 1");
        };
    }
}