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

package im.turms.plugin.push.core.smtp.model;

import java.util.List;

import im.turms.server.common.infra.lang.LongUtil;
import im.turms.server.common.infra.lang.StringUtil;

/**
 * @author James Chen
 */
public record EhloResponse(
        boolean isAuthPlainSupported,
        boolean isAuthLoginSupported,
        boolean isAuthXoauth2Supported,
        boolean isStartTlsSupported,
        boolean isPipeliningSupported,
        boolean is8BitMimeSupported,
        long maxAllowedMessageSize
) {

    public static EhloResponse create(List<String> lines) {
        boolean isAuthPlainSupported = false;
        boolean isAuthLoginSupported = false;
        boolean isAuthXoauth2Supported = false;
        boolean isStartTlsSupported = false;
        boolean isPipeliningSupported = false;
        boolean is8BitMimeSupported = false;
        long maxAllowedMessageSize = Long.MAX_VALUE;
        for (String line : lines) {
            List<String> parts = StringUtil.splitToList(line, ' ');
            String command = parts.get(0)
                    .toLowerCase();
            switch (command) {
                case "auth" -> {
                    for (String s : parts) {
                        if (s.equalsIgnoreCase("plain")) {
                            isAuthPlainSupported = true;
                        } else if (s.equalsIgnoreCase("login")) {
                            isAuthLoginSupported = true;
                        } else if (s.equalsIgnoreCase("xoauth2")) {
                            isAuthXoauth2Supported = true;
                        }
                    }
                }
                case "pipelining" -> isPipeliningSupported = true;
                case "size" -> {
                    if (parts.size() > 1) {
                        Long size = LongUtil.tryParse(parts.get(1));
                        if (size != null && size > 0) {
                            maxAllowedMessageSize = size;
                        }
                    }
                }
                case "8bitmime" -> is8BitMimeSupported = true;
            }
        }
        return new EhloResponse(
                isAuthPlainSupported,
                isAuthLoginSupported,
                isAuthXoauth2Supported,
                isStartTlsSupported,
                isPipeliningSupported,
                is8BitMimeSupported,
                maxAllowedMessageSize);
    }

}