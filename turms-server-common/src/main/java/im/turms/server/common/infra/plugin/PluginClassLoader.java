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

package im.turms.server.common.infra.plugin;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Collections;
import java.util.Enumeration;

import im.turms.server.common.BaseTurmsApplication;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.lang.PackageConst;

/**
 * @author James Chen
 */
public class PluginClassLoader extends URLClassLoader {

    /**
     * @implNote The parent class loader isn't always the system class loader: When running in IDE
     *           or as a thin jar, the main class is the concrete class of
     *           {@link BaseTurmsApplication}, the plugin parent class loader is the system class
     *           loader. But when running in a fat jar, the main class is
     *           {@link org.springframework.boot.loader.JarLauncher}, the plugin parent class loader
     *           is {@link org.springframework.boot.loader.LaunchedURLClassLoader}.
     */
    private static final ClassLoader PARENT_CLASS_LOADER = PluginClassLoader.class.getClassLoader();

    public PluginClassLoader(URL jarUrl) {
        super(new URL[]{jarUrl}, PARENT_CLASS_LOADER);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        try {
            return super.loadClass(name);
        } catch (ClassNotFoundException | NoClassDefFoundError e) {
            if (name.startsWith(PackageConst.PREFIX_TURMS_BASE)) {
                throw new ClassNotFoundException(
                        "Could not find the class ("
                                + name
                                + ") of Turms. This may happen if the plugin is loaded by a wrong Turms server",
                        e);
            }
            throw e;
        }
    }

    @Override
    public URL getResource(String name) {
        URL url = findResource(name);
        if (url != null) {
            return url;
        }
        return PARENT_CLASS_LOADER.getResource(name);
    }

    @Override
    public Enumeration<URL> getResources(String name) throws IOException {
        return Collections.enumeration(CollectionUtil.newList(findResources(name).asIterator(),
                PARENT_CLASS_LOADER.getResources(name)
                        .asIterator()));
    }

}