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

package im.turms.server.common.infra.property.env.service.env.database;

import com.mongodb.WriteConcern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author James Chen
 */
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class AdminMongoProperties extends TurmsMongoProperties {

    @NestedConfigurationProperty
    private OptionalIndexProperties optionalIndex = new OptionalIndexProperties();

    @NestedConfigurationProperty
    private transient WriteConcernProperties writeConcern = new WriteConcernProperties();

    @Data
    public static class OptionalIndexProperties {
        @NestedConfigurationProperty
        private AdminOptionalIndexProperties admin = new AdminOptionalIndexProperties();
    }

    @Data
    public static class AdminOptionalIndexProperties {
        private boolean roleId;
        private boolean registrationDate;
    }

    @Data
    public static class WriteConcernProperties {
        private WriteConcern admin = WriteConcern.MAJORITY;
        private WriteConcern adminRole = WriteConcern.MAJORITY;
    }

}