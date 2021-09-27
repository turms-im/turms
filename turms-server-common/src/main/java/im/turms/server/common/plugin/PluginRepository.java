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

package im.turms.server.common.plugin;

import com.google.common.collect.ArrayListMultimap;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author James Chen
 */
public class PluginRepository {

    private final Map<String, PluginWrapper> wrapperMap = new HashMap<>(16);
    private final ArrayListMultimap<Class<? extends ExtensionPoint>, ExtensionPoint> extensionPointMap =
            ArrayListMultimap.create(10, 3);

    public void register(PluginWrapper wrapper) {
        for (TurmsExtension extension : wrapper.extensions()) {
            for (Class<?> interfaceClass : extension.getClass().getInterfaces()) {
                if (!ExtensionPoint.class.isAssignableFrom(interfaceClass)) {
                    continue;
                }
                if (interfaceClass == ExtensionPoint.class) {
                    throw new RuntimeException("Extension cannot implement ExtensionPoint directly," +
                            "and should implement its subclasses defined in Turms");
                }
                Class<? extends ExtensionPoint> clazz = (Class<? extends ExtensionPoint>) interfaceClass;
                extensionPointMap.put(clazz, (ExtensionPoint) extension);
            }
        }
    }

    public <T extends ExtensionPoint> List<T> getExtensionPoints(Class<T> clazz) {
        return (List<T>) extensionPointMap.get(clazz);
    }

    public Collection<PluginWrapper> getWrappers() {
        return wrapperMap.values();
    }

}
