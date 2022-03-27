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

import com.google.common.collect.Iterators;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.Iterator;

/**
 * A parent-last ClassLoader. It loads the classes from the plugin's jars
 * before delegating to the parent class loader.
 *
 * @author James Chen
 */
public class PluginClassLoader extends URLClassLoader {

    private static final String PACKAGE_PREFIX_JAVA = "java.";
    private static final String PACKAGE_PREFIX_TURMS = "im.turms.";

    public PluginClassLoader(URL jarUrl) {
        super(new URL[] {jarUrl}, ClassLoader.getSystemClassLoader());
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        synchronized (getClassLoadingLock(name)) {
            if (name.startsWith(PACKAGE_PREFIX_JAVA)) {
                return findSystemClass(name);
            }
            if (name.startsWith(PACKAGE_PREFIX_TURMS)) {
                return getParent().loadClass(name);
            }
            Class<?> loadedClass = findLoadedClass(name);
            if (loadedClass != null) {
                return loadedClass;
            }
            try {
                return findClass(name);
            } catch (ClassNotFoundException ignored) {
            }
            return super.loadClass(name);
        }
    }

    @Override
    public URL getResource(String name) {
        URL url = findResource(name);
        if (url != null) {
            return url;
        }
        return super.getResource(name);
    }

    @Override
    public Enumeration<URL> getResources(String name) throws IOException {
        Iterator<URL> urlIterator = Iterators.concat(
                findResources(name).asIterator(),
                getParent().getResources(name).asIterator());
        return Iterators.asEnumeration(urlIterator);
    }

}