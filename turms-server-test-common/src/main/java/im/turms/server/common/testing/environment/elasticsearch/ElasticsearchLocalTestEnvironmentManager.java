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

package im.turms.server.common.testing.environment.elasticsearch;

import im.turms.server.common.testing.properties.ElasticsearchLocalTestEnvironmentProperties;

/**
 * @author James Chen
 */
public class ElasticsearchLocalTestEnvironmentManager implements ElasticsearchTestEnvironmentAware {

    private final String host;
    private final int port;

    public ElasticsearchLocalTestEnvironmentManager(
            ElasticsearchLocalTestEnvironmentProperties properties) {
        this.host = properties.getHost();
        this.port = properties.getPort();
    }

    @Override
    public String getElasticsearchHost() {
        return host;
    }

    @Override
    public int getElasticsearchPort() {
        return port;
    }
}