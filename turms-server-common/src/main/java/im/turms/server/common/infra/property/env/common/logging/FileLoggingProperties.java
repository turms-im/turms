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

package im.turms.server.common.infra.property.env.common.logging;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import im.turms.server.common.infra.logging.core.model.LogLevel;

/**
 * @author James Chen
 */
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
public class FileLoggingProperties {

    public static final boolean DEFAULT_VALUE_ENABLED = true;
    public static final LogLevel DEFAULT_VALUE_LEVEL = LogLevel.INFO;
    public static final String DEFAULT_VALUE_FILE_PATH = "@HOME/@SERVICE_TYPE_NAME.log";
    public static final int DEFAULT_VALUE_MAX_FILES = 320;
    public static final int DEFAULT_VALUE_FILE_SIZE_MB = 32;

    private boolean enabled = DEFAULT_VALUE_ENABLED;

    private LogLevel level = DEFAULT_VALUE_LEVEL;

    private String filePath = DEFAULT_VALUE_FILE_PATH;
    private int maxFiles = DEFAULT_VALUE_MAX_FILES;
    private int maxFileSizeMb = DEFAULT_VALUE_FILE_SIZE_MB;

    @NestedConfigurationProperty
    private FileLoggingCompressionProperties compression = new FileLoggingCompressionProperties();

}