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

package im.turms.service.workflow.access.http.util;

import im.turms.server.common.cluster.node.Node;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;

/**
 * @author James Chen
 */
@Component
public class PageUtil {

    private final Node node;

    public PageUtil(Node node) {
        this.node = node;
    }

    public int getSize(@Nullable Integer size) {
        if (size == null || size <= 0) {
            return node.getSharedProperties().getService().getAdminApi().getDefaultAvailableRecordsPerRequest();
        } else {
            int maxLimit = node.getSharedProperties().getService().getAdminApi().getMaxAvailableRecordsPerRequest();
            return size > maxLimit ? maxLimit : size;
        }
    }

}
