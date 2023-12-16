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

import org.springframework.util.StringUtils;

import im.turms.server.common.testing.environment.ServiceTestEnvironmentAware;
import im.turms.server.common.testing.environment.ServiceTestEnvironmentType;

/**
 * @author James Chen
 */
public interface MongoTestEnvironmentAware extends ServiceTestEnvironmentAware {

    default ServiceTestEnvironmentType getMongoTestEnvironmentType() {
        throw new UnsupportedOperationException();
    }

    default boolean isMongoRunning() {
        return isRunning();
    }

    String getMongoHost();

    int getMongoPort();

    String getMongoPassword();

    default String getMongoUri() {
        return getMongoUri("turms-test");
    }

    default String getMongoUri(String dbName) {
        String mongoPassword = getMongoPassword();
        if (StringUtils.hasText(mongoPassword)) {
            return "mongodb://root:"
                    + mongoPassword
                    + "@"
                    + getMongoHost()
                    + ":"
                    + getMongoPort()
                    + "/"
                    + dbName
                    + "?authSource=admin";
        }
        return "mongodb://"
                + getMongoHost()
                + ":"
                + getMongoPort()
                + "/"
                + dbName
                + "?authSource=admin";
    }
}