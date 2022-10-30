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

package im.turms.plugin.minio;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author James Chen
 */
@ConfigurationProperties("turms-plugin.minio")
@Data
public class MinioStorageProperties {

    private boolean enabled = true;
    private String endpoint = "http://localhost:9000";
    private String region = "";
    private String accessKey = "minioadmin";
    private String secretKey = "minioadmin";

    private Retry retry = new Retry();

    @Data
    public static class Retry {

        private boolean enabled = true;
        private int initialIntervalMillis = 30 * 1000;
        private int intervalMillis = 30 * 1000;
        private int maxAttempts = 3;

    }
}