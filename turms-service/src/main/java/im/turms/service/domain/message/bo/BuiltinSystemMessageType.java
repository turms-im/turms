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

package im.turms.service.domain.message.bo;

/**
 * @author James Chen
 */
public class BuiltinSystemMessageType {

    private BuiltinSystemMessageType() {
    }

    /**
     * NORMAL is only used as a placeholder and won't be set for normal messages because the client
     * implementations consider a system message as a normal message if no message type specified
     */
    public static final int NORMAL = 0;
    public static final int RECALL_MESSAGE = 1;
}