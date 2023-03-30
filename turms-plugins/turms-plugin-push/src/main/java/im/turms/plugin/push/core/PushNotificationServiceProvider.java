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

package im.turms.plugin.push.core;

import java.util.Map;
import jakarta.annotation.Nullable;

import im.turms.server.common.infra.lang.ClassUtil;

/**
 * @author James Chen
 */
public enum PushNotificationServiceProvider {
    FCM,
    APN;

    private static final Map<String, PushNotificationServiceProvider> NAME_TO_PROVIDER;

    static {
        PushNotificationServiceProvider[] providers =
                ClassUtil.getSharedEnumConstants(PushNotificationServiceProvider.class);
        Map.Entry[] entries = new Map.Entry[providers.length];
        for (int i = 0, length = providers.length; i < length; i++) {
            PushNotificationServiceProvider provider = providers[i];
            entries[i] = Map.entry(provider.name()
                    .toLowerCase(), provider);
        }
        NAME_TO_PROVIDER = Map.ofEntries(entries);
    }

    @Nullable
    public static PushNotificationServiceProvider get(String value) {
        return NAME_TO_PROVIDER.get(value);
    }

}