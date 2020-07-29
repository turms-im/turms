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

package im.turms.server.common.plugin.base;

import org.pf4j.ExtensionPoint;
import org.springframework.context.ApplicationContext;

/**
 * @author James Chen
 */
public abstract class TurmsExtension implements ExtensionPoint {

    private ApplicationContext context;
    private boolean isServing;

    public ApplicationContext getContext() {
        return context;
    }

    public void setContext(ApplicationContext context) throws Exception {
        this.context = context;
    }

    public boolean isServing() {
        return isServing;
    }

    public void setServing(boolean serving) {
        isServing = serving;
    }

}
