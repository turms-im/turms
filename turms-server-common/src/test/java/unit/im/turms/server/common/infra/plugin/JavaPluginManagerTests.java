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

import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import helper.ClassResource;
import helper.JarUtil;
import helper.ResourceUtil;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;

import im.turms.plugin.MyExtensionPoint;
import im.turms.server.common.infra.application.TurmsApplicationContext;
import im.turms.server.common.infra.cluster.node.Node;
import im.turms.server.common.infra.cluster.node.NodeType;
import im.turms.server.common.infra.cluster.service.rpc.RpcService;
import im.turms.server.common.infra.plugin.ConflictedClassException;
import im.turms.server.common.infra.plugin.PluginClassLoader;
import im.turms.server.common.infra.plugin.PluginManager;
import im.turms.server.common.infra.plugin.TurmsExtension;
import im.turms.server.common.infra.property.TurmsProperties;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.property.env.common.plugin.PluginProperties;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author James Chen
 */
class JavaPluginManagerTests {

    private static final String JAR_NAME = "MyPluginForTest.jar";
    private static final Path JAR_FILE;
    private static final String MY_EXTENSION_CLASS =
            "unit.im.turms.server.common.infra.plugin.hidden.MyExtension";
    private static final List<ClassResource> PLUGIN_CLASS_RESOURCES;
    private static final List<String> PLUGIN_RESOURCES = List.of("plugin.yaml");

    static {
        PLUGIN_CLASS_RESOURCES = Stream
                .of(MY_EXTENSION_CLASS,
                        "unit.im.turms.server.common.infra.plugin.hidden.MyPlugin",
                        // Used to test class conflict.
                        "im.turms.server.common.infra.thread.ThreadUtil")
                .map(ResourceUtil::findTestClass)
                .toList();
        JAR_FILE = JarUtil.createJarFile(JAR_NAME, PLUGIN_RESOURCES, PLUGIN_CLASS_RESOURCES);
    }

    @Test
    void shouldLoadClasses_absentFromTurmsServer_definedInPluginJar_withPluginClassLoader() {
        MyExtensionPoint myExtensionPoint = getMyExtensionPoint();

        assertThat(myExtensionPoint.getClass()
                .getName()).isEqualTo(MY_EXTENSION_CLASS);
        assertThat(myExtensionPoint.getClass()
                .getClassLoader()).isInstanceOf(PluginClassLoader.class);
    }

    @SneakyThrows
    @Test
    void shouldLoadClasses_definedInTurmsServer_usedInPluginJar_withSystemLoader() {
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
    void shouldThrow_ifSameClassDefinedInBothTurmsServerAndPluginJar() {
        MyExtensionPoint myExtensionPoint = getMyExtensionPoint();
        TurmsExtension myExtension = (TurmsExtension) myExtensionPoint;
        assertThatThrownBy(() -> {
            myExtension.getClass()
                    .getDeclaredMethod("getThreadUtilClass")
                    .invoke(myExtension);
        }).hasRootCauseExactlyInstanceOf(ConflictedClassException.class);
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

    @SneakyThrows
    @Test
    void shouldFindResourceInPluginSuccessfully() {
        MyExtensionPoint myExtensionPoint = getMyExtensionPoint();
        ClassLoader classLoader = myExtensionPoint.getClass()
                .getClassLoader();
        for (ClassResource resource : PLUGIN_CLASS_RESOURCES) {
            URL url = classLoader.getResource(resource.binaryName());
            assertThat(url).isNotNull();
        }
        for (String resource : PLUGIN_RESOURCES) {
            URL url = classLoader.getResource(resource);
            assertThat(url).isNotNull();

            InputStream inputStream = classLoader.getResourceAsStream(resource);
            assertThat(inputStream).isNotEmpty();
        }
    }

    @SneakyThrows
    @Test
    void shouldFindResourceInTurmsServerSuccessfully() {
        MyExtensionPoint myExtensionPoint = getMyExtensionPoint();
        ClassLoader classLoader = myExtensionPoint.getClass()
                .getClassLoader();

        URL url = classLoader.getResource("plugin.js");
        assertThat(url).isNotNull();

        InputStream inputStream = classLoader.getResourceAsStream("plugin.yaml");
        assertThat(inputStream).isNotEmpty();
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