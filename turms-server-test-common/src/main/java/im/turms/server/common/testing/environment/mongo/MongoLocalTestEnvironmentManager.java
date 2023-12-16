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

package im.turms.server.common.testing.environment.mongo;

import im.turms.server.common.testing.properties.MongoLocalTestEnvironmentProperties;

/**
 * @author James Chen
 */
public class MongoLocalTestEnvironmentManager implements MongoTestEnvironmentAware {

    private final String host;
    private final int port;
    private final String password;

    public MongoLocalTestEnvironmentManager(MongoLocalTestEnvironmentProperties properties) {
        this.host = properties.getHost();
        this.port = properties.getPort();
        this.password = properties.getPassword();
    }

    @Override
    public String getMongoHost() {
        return host;
    }

    @Override
    public int getMongoPort() {
        return port;
    }

    @Override
    public String getMongoPassword() {
        return password;
    }

}