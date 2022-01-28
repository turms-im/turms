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

import java.util.List;

/**
 * {@link TurmsPlugin} is the plugin class for plugin developers,
 * while {@link Plugin} is the plugin class for us and internal development.
 * <p>
 * From the perspective of the developers of Turms,
 * It's more accurate to call {@link TurmsPlugin} "CustomPlugin" or "JavaPlugin".
 * But from the perspective of Java plugin developers, both "JavaPlugin" and "CustomPlugin" are fluffy
 * because it's obvious that they are developing a java custom plugin while "TurmsPlugin" can ensure developers
 * know that they are developing a plugin for Turms to distinguish with other plugin classes
 * in their classpath.
 *
 * @author James Chen
 */
public record Plugin(
        PluginDescriptor descriptor,
        List<TurmsExtension> extensions
) {
}