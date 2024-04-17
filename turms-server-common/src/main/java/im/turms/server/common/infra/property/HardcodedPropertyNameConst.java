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

package im.turms.server.common.infra.property;

/**
 * Define all hardcoded property names here to manage them in one place.
 * <p>
 * We need these hardcoded property names because we need to use them before Spring Boot prepares
 * properties.
 *
 * @author James Chen
 */
public final class HardcodedPropertyNameConst {

    public static final String TURMS_CLUSTER_NODE_ID = "turms.cluster.node.id";

    public static final String TURMS_LOGGING_CONSOLE_ENABLED = "turms.logging.console.enabled";
    public static final String TURMS_LOGGING_CONSOLE_LEVEL = "turms.logging.console.level";
    public static final String TURMS_LOGGING_FILE_ENABLED = "turms.logging.file.enabled";
    public static final String TURMS_LOGGING_FILE_LEVEL = "turms.logging.file.level";
    public static final String TURMS_LOGGING_FILE_FILE_PATH = "turms.logging.file.file-path";
    public static final String TURMS_LOGGING_FILE_MAX_FILES = "turms.logging.file.max-files";
    public static final String TURMS_LOGGING_FILE_MAX_FILE_SIZE_MB =
            "turms.logging.file.max-file-size-mb";
    public static final String TURMS_LOGGING_FILE_COMPRESSION_ENABLED =
            "turms.logging.file.compression.enabled";

    private HardcodedPropertyNameConst() {
    }

}