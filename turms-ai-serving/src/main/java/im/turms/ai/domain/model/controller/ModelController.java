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

package im.turms.ai.domain.model.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ai.djl.Application;
import ai.djl.repository.Artifact;
import ai.djl.repository.zoo.Criteria;
import ai.djl.repository.zoo.ModelZoo;

import im.turms.ai.domain.model.dto.QueryModelsDTO;
import im.turms.server.common.access.admin.web.annotation.GetMapping;
import im.turms.server.common.access.admin.web.annotation.QueryParam;
import im.turms.server.common.access.admin.web.annotation.RestController;

/**
 * @author James Chen
 */
@RestController("models")
public class ModelController {

    private static final Criteria<?, ?> ALL = Criteria.builder()
            .build();

    @GetMapping
    public List<Artifact> getModels(@QueryParam(required = false) QueryModelsDTO request) {
        Criteria<?, ?> criteria;
        if (request == null) {
            criteria = ALL;
        } else {
            String artifactId = request.artifactId();
            criteria = artifactId == null
                    ? ALL
                    : Criteria.builder()
                            .optArtifactId(artifactId)
                            .build();
        }
        Set<Map.Entry<Application, List<Artifact>>> entries;
        try {
            entries = ModelZoo.listModels(criteria)
                    .entrySet();
        } catch (Exception e) {
            throw new RuntimeException("Failed to find models", e);
        }
        List<Artifact> artifacts = new ArrayList<>(entries.size());
        for (Map.Entry<Application, List<Artifact>> entry : entries) {
            artifacts.addAll(entry.getValue());
        }
        return artifacts;
    }

}