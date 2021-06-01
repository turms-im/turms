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

package im.turms.server.common.cluster.service;

import im.turms.server.common.cluster.service.config.SharedConfigService;
import im.turms.server.common.cluster.service.discovery.DiscoveryService;
import im.turms.server.common.cluster.service.idgen.IdService;
import im.turms.server.common.cluster.service.rpc.RpcService;
import im.turms.server.common.cluster.service.serialization.SerializationService;

/**
 * @author James Chen
 */
public interface ClusterService {

    default void start() {
    }

    default void stop() {
    }

    default void lazyInit(DiscoveryService discoveryService,
                          IdService idService,
                          RpcService rpcService,
                          SerializationService serializationService,
                          SharedConfigService sharedConfigService) {
    }

}
