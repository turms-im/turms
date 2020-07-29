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

package im.turms.turms.plugin.extension.handler;

import im.turms.server.common.plugin.base.TurmsExtension;
import im.turms.turms.workflow.dao.domain.Message;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * The plugin is useful when developers needing to persist messages in other places
 * while deleting them in the databases for turms servers.
 *
 * @author James Chen
 */
public abstract class ExpiredMessageAutoDeletionNotificationHandler extends TurmsExtension {

    public abstract Mono<Boolean> allowDeleting(@NotEmpty List<Message> message);

}