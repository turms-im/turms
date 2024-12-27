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

package im.turms.server.common.infra.property.constant;

/**
 * We don't allow child-first visibility as it is more error-prone: For example, if the plugin
 * contains the {@link reactor.core.publisher.Mono} class, and pass its instance to the Turms
 * server, which will cause the JVM to throw an error as the "same" class are loaded by different
 * class loaders: the application classloader and the plugin classloader.
 *
 * @author James Chen
 */
public enum DuplicateClassLoadStrategy {
    /**
     * Consider this strategy as the "strict" mode.
     * <p>
     * Throw an exception if the class is defined by both the Turms server and the plugin.
     * <p>
     * 1. Pros: There are won't be any duplicate class, and the classes in the plugin should always
     * work as expected.
     * <p>
     * 2. Cons: The plugin developers need to reallocate the classes in the plugin, which is
     * troublesome and error-prone as there are usually many transitive dependencies.
     */
    THROW_EXCEPTION,
    /**
     * Consider this strategy as the "lenient" mode.
     * <p>
     * Parent-first visibility:
     * <p>
     * 1. Pros: The plugin developers don't need to reallocate the classes in the plugin, which is
     * troublesome and error-prone as there are usually many transitive dependencies.
     * <p>
     * 2. Cons: The plugin developer may be confused as the classes in their plugin may not work as
     * expected. Though the plugin developers can always fix the issues by introducing Turms server
     * as the provided dependency to use the same classes used by the Turms server.
     */
    PARENT_FIRST,
}