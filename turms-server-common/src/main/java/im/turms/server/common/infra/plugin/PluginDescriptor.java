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

package im.turms.server.common.infra.plugin;

import java.nio.file.Path;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

import im.turms.server.common.infra.cluster.node.NodeType;

/**
 * @author James Chen
 */
@AllArgsConstructor
@Data
public class PluginDescriptor {
    private final String id;
    private final Map<NodeType, ServerInfo> compatibleServerTypeToInfo;
    private final String version;
    private final String provider;
    private final String license;
    private final String description;

    private Path path;

    @Data
    public static class ServerInfo {
        public static final ServerInfo DEFAULT = new ServerInfo();

        // TODO: for future use
//        private final String version;

        private ServerInfo() {
        }
    }
}