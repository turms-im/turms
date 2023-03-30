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

import java.util.LinkedHashMap;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import im.turms.server.common.infra.property.metadata.Description;
import im.turms.server.common.infra.property.metadata.GlobalProperty;
import im.turms.server.common.infra.property.metadata.MutableProperty;
import im.turms.server.common.infra.task.CronConst;
import im.turms.server.common.infra.validation.ValidCron;

/**
 * @author James Chen
 */
@Data
public class TieredStorageProperties {

    private boolean enabled = true;

    @Description("The storage properties for tiers from hot to cold. "
            + "Note that the order of the tiers is important")
    private LinkedHashMap<String, StorageTierProperties> tiers = new LinkedHashMap<>();

    @NestedConfigurationProperty
    private AutoRangeUpdaterProperties autoRangeUpdater = new AutoRangeUpdaterProperties();

    public TieredStorageProperties() {
        tiers.put("hot", new StorageTierProperties(30));
        tiers.put("warm", new StorageTierProperties(30 * 2));
        tiers.put("cold", new StorageTierProperties(30 * 9));
        tiers.put("frozen", new StorageTierProperties());
    }

    @Data
    @NoArgsConstructor
    public static class StorageTierProperties {
        private boolean enabled = true;

        private int days;

        private List<String> shards = List.of("");

        public StorageTierProperties(int days) {
            this.days = days;
        }
    }

    @Data
    public static class AutoRangeUpdaterProperties {
        @GlobalProperty
        @MutableProperty
        private boolean enabled = true;

        @ValidCron
        private String cron = CronConst.DEFAULT_TIERED_STORAGE_TIER_RANGE_UPDATING_CRON;
    }

}