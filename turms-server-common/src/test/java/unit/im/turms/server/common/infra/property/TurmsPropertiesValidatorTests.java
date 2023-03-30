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

package unit.im.turms.server.common.infra.property;

import org.junit.jupiter.api.Test;

import im.turms.server.common.infra.property.InvalidPropertyException;
import im.turms.server.common.infra.property.TurmsProperties;

import static org.assertj.core.api.Assertions.assertThat;

import static im.turms.server.common.infra.property.TurmsPropertiesValidator.validate;

/**
 * @author James Chen
 */
class TurmsPropertiesValidatorTests {

    @Test
    void test() {
        InvalidPropertyException exception = validate(new TurmsProperties());
        assertThat(exception).isNull();
    }

    @Test
    void test_min() {
        TurmsProperties properties = new TurmsProperties();
        properties.getUserStatus()
                .setUserSessionsStatusExpireAfter(0);
        InvalidPropertyException exception = validate(properties);
        InvalidPropertyException expected = new InvalidPropertyException(
                "The property \"userSessionsStatusExpireAfter\" must be greater than or equal to 1");
        assertThat(exception).hasSuppressedException(expected);

        properties.getUserStatus()
                .setUserSessionsStatusExpireAfter(1);
        exception = validate(properties);
        assertThat(exception).isNull();

        properties.getUserStatus()
                .setUserSessionsStatusExpireAfter(2);
        exception = validate(properties);
        assertThat(exception).isNull();
    }

    @Test
    void test_max() {
        TurmsProperties properties = new TurmsProperties();
        properties.getHealthCheck()
                .getCpu()
                .setUnhealthyLoadThresholdPercentage(100);
        InvalidPropertyException exception = validate(properties);
        assertThat(exception).isNull();

        properties.getHealthCheck()
                .getCpu()
                .setUnhealthyLoadThresholdPercentage(101);
        exception = validate(properties);
        InvalidPropertyException expected = new InvalidPropertyException(
                "The property \"unhealthyLoadThresholdPercentage\" must be less than or equal to 100");
        assertThat(exception).hasSuppressedException(expected);

        properties.getHealthCheck()
                .getCpu()
                .setUnhealthyLoadThresholdPercentage(99);
        exception = validate(properties);
        assertThat(exception).isNull();
    }

    @Test
    void test_validCron() {
        TurmsProperties properties = new TurmsProperties();
        properties.getService()
                .getMessage()
                .setExpiredMessagesCleanupCron("");
        InvalidPropertyException exception = validate(properties);
        InvalidPropertyException expected = new InvalidPropertyException(
                "The property \"expiredMessagesCleanupCron\" has an invalid cron \"\"");
        assertThat(exception).hasSuppressedException(expected);

        properties.getService()
                .getMessage()
                .setExpiredMessagesCleanupCron("0 45 2 * * *");
        exception = validate(properties);
        assertThat(exception).isNull();
    }

}
