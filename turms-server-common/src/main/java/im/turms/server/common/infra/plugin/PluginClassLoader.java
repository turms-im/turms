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
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import im.turms.server.common.BaseTurmsApplication;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.io.InputOutputException;
import im.turms.server.common.infra.lang.PackageConst;

/**
 * @author James Chen
 */
public class PluginClassLoader extends ClassLoader implements AutoCloseable {

    /**
     * @implNote The parent class loader isn't always the system class loader: When running in IDE
     *           or as a thin jar, the main class is the concrete class of
     *           {@link BaseTurmsApplication}, the plugin parent class loader is the system class
     *           loader. But when running in a fat jar, the main class is
     *           {@link org.springframework.boot.loader.launch.JarLauncher}, the plugin parent class
     *           loader is {@link org.springframework.boot.loader.LaunchedURLClassLoader}.
     */
    private static final ClassLoader PARENT_CLASS_LOADER = PluginClassLoader.class.getClassLoader();

    /**
     * We don't use {@link java.util.jar.JarFile} as we don't want to support multi-version JARs on
     * purpose, which just waste unnecessary resources.
     */
    private final ZipFile zipFile;

    public PluginClassLoader(ZipFile zipFile) {
        super(PARENT_CLASS_LOADER);
        this.zipFile = zipFile;
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        // The classes under the package "unit.im.turms.server.common.infra.plugin.hidden"
        // are defined in tests to serve as if they were the classes ONLY defined in a plugin.
        // So we need to make sure the class can only be loaded from the JAR file, not the parent
        // class loader.
        if (name.startsWith("unit.im.turms.server.common.infra.plugin.hidden.")) {
            synchronized (getClassLoadingLock(name)) {
                Class<?> c = findLoadedClass(name);
                if (c == null) {
                    c = findClass(name);
                }
                if (resolve) {
                    resolveClass(c);
                }
                return c;
            }
        }
        Class<?> loadedClass = super.loadClass(name, resolve);
        if (loadedClass.getClassLoader() == this) {
            return loadedClass;
        }
        // Check if the class loaded by the parent class loader is also defined in the JAR file.
        // If so, throw an exception to prevent duplicate classes
        // because defining different classes of the same name is an error-prone practice:
        // 1. If we use the class loaded by the parent class loader, the plugin developer may be
        // confused as their defined class in plugin doesn't work.
        // 2. If we use the class defined in the JAR file, JVM will throw an error if an instance of
        // the class is passed from the plugin to Turms
        // (Same class name but different class loaders).
        // As a result, we don't use either of them, but throw.
        String binaryName = name.replace('.', '/')
                .concat(".class");
        if (zipFile.getEntry(binaryName) != null) {
            throw new ConflictedClassException(
                    "The class ("
                            + name
                            + ") in the JAR file conflicts with the class defined by Turms server. "
                            + "The plugin developer needs to rename the class in the JAR file to avoid conflicts");
        }
        return loadedClass;
    }

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        String path = name.replace('.', '/')
                .concat(".class");
        ZipEntry entry = zipFile.getEntry(path);
        if (entry == null) {
            if (name.startsWith(PackageConst.PREFIX_TURMS_BASE)) {
                throw new ClassNotFoundException(
                        "Could not find the class ("
                                + name
                                + ") of Turms. This may happen if the plugin descriptor specifies a wrong server type, or the class has been removed by Turms");
            }
            throw new ClassNotFoundException(
                    "Could not find the class: "
                            + name);
        }
        byte[] bytes = new byte[(int) entry.getSize()];
        int size;
        try (InputStream inputStream = zipFile.getInputStream(entry)) {
            size = inputStream.read(bytes);
        } catch (IOException e) {
            throw new InputOutputException(
                    "Failed to load class: "
                            + name,
                    e);
        }
        return defineClass(name, bytes, 0, size);
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
    public InputStream getResourceAsStream(String name) {
        ZipEntry entry = zipFile.getEntry(name);
        if (entry == null) {
            return null;
        }
        try {
            return zipFile.getInputStream(entry);
        } catch (IOException e) {
            throw new InputOutputException(
                    "Failed to load resource: "
                            + name,
                    e);
        }
    }

    @Override
    public Enumeration<URL> getResources(String name) throws IOException {
        return Collections.enumeration(CollectionUtil.newList(findResources(name).asIterator(),
                PARENT_CLASS_LOADER.getResources(name)
                        .asIterator()));
    }

    @Override
    protected URL findResource(String name) {
        ZipEntry entry = zipFile.getEntry(name);
        if (entry == null) {
            return null;
        }
        String url = Path.of(zipFile.getName())
                .toAbsolutePath()
                .normalize()
                .toString()
                .replace('\\', '/');
        try {
            return URI.create((url.startsWith("/")
                    ? "jar:file:"
                    : "jar:file:/")
                    + url
                    + "!/"
                    + name)
                    .toURL();
        } catch (MalformedURLException e) {
            throw new RuntimeException(
                    "Failed to create a URL for: \""
                            + url
                            + "\"",
                    e);
        }
    }

    @Override
    public void close() throws Exception {
        zipFile.close();
    }
}