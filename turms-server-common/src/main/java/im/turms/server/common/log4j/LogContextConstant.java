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

package im.turms.server.common.log4j;

/**
 * @author James Chen
 */
public final class LogContextConstant {
    private LogContextConstant() {
    }

    public static final String LOG_TYPE = "TYPE";
    public static final String NODE_TYPE = "NODE_TYPE";
    public static final String NODE_ID = "NODE_ID";

    public static final class LogType {
        private LogType() {
        }

        public static final String ADMIN_API = "ADMIN_API";
        public static final String CLIENT_API = "USER_API";
        public static final String USER_ACTIVITY = "USER_ACTIVITY";
    }

    public static final class NodeType {
        private NodeType() {
        }

        public static final String SERVICE = "S";
        public static final String GATEWAY = "G";
    }

}