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

package unit.im.turms.server.common.plugin;

import im.turms.plugin.MyExtensionPoint;
import im.turms.server.common.cluster.service.rpc.RpcService;
import im.turms.server.common.plugin.PluginClassLoader;
import im.turms.server.common.plugin.PluginManager;
import im.turms.server.common.plugin.TurmsExtension;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import util.JarUtil;

import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

/**
 * @author James Chen
 */
class JavaPluginManagerTests {

    private static final String JAR_NAME = "MyPluginForTest.jar";
    private static final Path JAR_FILE;

    static {
        JAR_FILE = JarUtil.createJarFile(JAR_NAME,
                List.of(SpringApplication.class, MyExtension.class, MyExtensionPoint.class, MyPlugin.class),
                List.of("plugin.properties"));
    }

    @Test
    void shouldLoadPluginWithPluginLoader() {
        MyExtensionPoint myExtensionPoint = getMyExtensionPoint();

        assertThat(myExtensionPoint.getClass())
                .isNotEqualTo(MyExtension.class);
        assertThat(myExtensionPoint.getClass().getName())
                .isEqualTo(MyExtension.class.getName());
        assertThat(myExtensionPoint.getClass().getClassLoader())
                .isInstanceOf(PluginClassLoader.class);
    }

    @SneakyThrows
    @Test
    void shouldLoadTurmsClassInPluginWithSystemLoader() {
        MyExtensionPoint myExtensionPoint = getMyExtensionPoint();
        // We don't declare "MyExtension" here because
        // "MyExtension" will be loaded by the system class loader
        // while "MyExtension" in the plugin will use "PluginClassLoader"
        // so they cannot cast to each other
        TurmsExtension myExtension = (TurmsExtension) myExtensionPoint;
        Class<?> rpcServiceClass = (Class<?>) myExtension.getClass()
                .getDeclaredField("rpcServiceClass")
                .get(myExtension);

        assertThat(rpcServiceClass)
                .isEqualTo(RpcService.class);
        assertThat(rpcServiceClass.getClassLoader())
                .isEqualTo(ClassLoader.getSystemClassLoader());
    }

    @SneakyThrows
    @Test
    void shouldPreferClassesInPlugin() {
        MyExtensionPoint myExtensionPoint = getMyExtensionPoint();
        TurmsExtension myExtension = (TurmsExtension) myExtensionPoint;
        Object application = myExtension.getClass()
                .getDeclaredField("application")
                .get(myExtension);

        assertThat(application.getClass())
                .isNotEqualTo(SpringApplication.class);
        assertThat(application.getClass().getName())
                .isEqualTo(SpringApplication.class.getName());
        assertThat(application.getClass().getClassLoader())
                .isInstanceOf(PluginClassLoader.class);

        boolean testMethodRetVal = (boolean) application.getClass()
                .getDeclaredMethod("test")
                .invoke(application);

        assertThat(testMethodRetVal)
                .isTrue();
    }

    @SneakyThrows
    @Test
    void shouldRunMethodInPluginSuccessfully() {
        MyExtensionPoint myExtensionPoint = getMyExtensionPoint();
        TurmsExtension myExtension = (TurmsExtension) myExtensionPoint;
        boolean testMethodRetVal = (boolean) myExtension.getClass()
                .getDeclaredMethod("testBool")
                .invoke(myExtension);
        assertThat(testMethodRetVal)
                .isTrue();
    }

    private MyExtensionPoint getMyExtensionPoint() {
        ApplicationContext context = mock(ApplicationContext.class);
        PluginManager manager = new PluginManager(JAR_FILE.getParent().toAbsolutePath(), context);
        return manager.getExtensionPoints(MyExtensionPoint.class).get(0);
    }

}