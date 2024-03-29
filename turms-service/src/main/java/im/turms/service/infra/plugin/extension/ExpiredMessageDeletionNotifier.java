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

package im.turms.service.infra.plugin.extension;

import java.util.List;
import jakarta.validation.constraints.NotEmpty;

import reactor.core.publisher.Mono;

import im.turms.server.common.infra.plugin.ExtensionPoint;
import im.turms.service.domain.message.po.Message;

/**
 * The plugin is useful when developers needing to persist messages in other places while deleting
 * them in the databases for turms servers.
 *
 * @author James Chen
 */
public interface ExpiredMessageDeletionNotifier extends ExtensionPoint {

    /**
     * @return 1. Return a {@link Mono} that completes without emitting any list, or emits an empty
     *         list if you don't want to delete any message.
     *         <p>
     *         2. Return a {@link Mono} that emits a non-empty list if you want to delete messages
     *         in the list.
     */
    Mono<List<Message>> getMessagesToDelete(@NotEmpty List<Message> messages);

}