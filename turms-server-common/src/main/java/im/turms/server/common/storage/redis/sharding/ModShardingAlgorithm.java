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

package im.turms.server.common.storage.redis.sharding;

/**
 * @author James Chen
 */
public class ModShardingAlgorithm extends ShardingAlgorithm {

    /**
     * @implNote Don't use "(int) (userId & (serverCount - 1))" because it requires that the
     *           serverCount should be a power of 2.
     */
    @Override
    public int doSharding(long userId, int serverCount) {
        return (int) (userId % serverCount);
    }

}