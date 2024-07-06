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

package unit.im.turms.server.common.infra.plugin;

import java.nio.file.Path;
import java.util.List;

import helper.JarUtil;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import im.turms.plugin.MyExtensionPoint;
import im.turms.server.common.infra.application.TurmsApplicationContext;
import im.turms.server.common.infra.cluster.node.Node;
import im.turms.server.common.infra.cluster.node.NodeType;
import im.turms.server.common.infra.cluster.service.rpc.RpcService;
import im.turms.server.common.infra.plugin.PluginManager;
import im.turms.server.common.infra.plugin.TurmsExtension;
import im.turms.server.common.infra.property.TurmsProperties;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.property.env.common.plugin.PluginProperties;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author James Chen
 */
class JavaPluginManagerTests {

    private static final String JAR_NAME = "MyPluginForTest.jar";
    private static final Path JAR_FILE;
    private static final ClassLoader SYSTEM_CLASS_LOADER = ClassLoader.getSystemClassLoader();

    static {
        JAR_FILE = JarUtil.createJarFile(JAR_NAME,
                List.of(SpringApplication.class,
                        MyExtension.class,
                        MyExtensionPoint.class,
                        MyPlugin.class),
                List.of("plugin.yaml"));
    }

    @Test
    void shouldLoadPluginWithSystemClassLoader() {
        MyExtensionPoint myExtensionPoint = getMyExtensionPoint();

        assertThat(myExtensionPoint.getClass()).isEqualTo(MyExtension.class);
        assertThat(myExtensionPoint.getClass()
                .getClassLoader()).isEqualTo(SYSTEM_CLASS_LOADER);
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

        assertThat(rpcServiceClass).isEqualTo(RpcService.class);
        assertThat(rpcServiceClass.getClassLoader()).isEqualTo(ClassLoader.getSystemClassLoader());
    }

    @SneakyThrows
    @Test
    void shouldPreferClassesInParentClassLoader() {
        MyExtensionPoint myExtensionPoint = getMyExtensionPoint();
        TurmsExtension myExtension = (TurmsExtension) myExtensionPoint;
        Object application = myExtension.getClass()
                .getDeclaredField("application")
                .get(myExtension);

        assertThat(application.getClass()).isEqualTo(SpringApplication.class);
        assertThat(application.getClass()
                // Should not be an instance of PluginClassLoader.
                .getClassLoader()).isEqualTo(SYSTEM_CLASS_LOADER);
    }

    @SneakyThrows
    @Test
    void shouldRunMethodInPluginSuccessfully() {
        MyExtensionPoint myExtensionPoint = getMyExtensionPoint();
        TurmsExtension myExtension = (TurmsExtension) myExtensionPoint;
        boolean testMethodRetVal = (boolean) myExtension.getClass()
                .getDeclaredMethod("testBool")
                .invoke(myExtension);
        assertThat(testMethodRetVal).isTrue();
    }

    private MyExtensionPoint getMyExtensionPoint() {
        Node node = mock(Node.class);
        when(node.getNodeType()).thenReturn(NodeType.MOCK);

        ApplicationContext context = mock(ApplicationContext.class);
        TurmsApplicationContext applicationContext = mock(TurmsApplicationContext.class);
        when(applicationContext.getHome()).thenReturn(JAR_FILE.getParent()
                .toAbsolutePath());

        TurmsPropertiesManager propertiesManager = mock(TurmsPropertiesManager.class);
        when(propertiesManager.getLocalProperties()).thenReturn(new TurmsProperties().toBuilder()
                .plugin(new PluginProperties().toBuilder()
                        .enabled(true)
                        .dir(".")
                        .build())
                .build());

        PluginManager manager =
                new PluginManager(node, context, applicationContext, propertiesManager);
        return manager.getExtensionPoints(MyExtensionPoint.class)
                .getFirst();
    }

}