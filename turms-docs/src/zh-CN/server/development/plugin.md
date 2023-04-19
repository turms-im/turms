# 自定义插件

## 插件拓展点列表

| 类别                       | 拓展点                         | 描述                                                         |
| -------------------------- | ------------------------------ | ------------------------------------------------------------ |
| 管理员类                   | AdminActionHandler             | 管理员行为Handler。用于监听管理员的API操作                   |
| 用户类                     | UserAuthenticator              | 用户登陆认证。当客户端向turms-gateway请求登录时，turms-gateway会调用该插件以实现自定义的登录认证逻辑。通过该插件，您就不需要（可选）将您业务系统中的用户信息同步到Turms当中了 |
|                            | UserOnlineStatusChangeHandler  | 用户在线状态变更Handler。当任意一位用户进入上线或离线状态时，turms-gateway会调用该接口 |
| 请求类                     | ClientRequestHandler           | 客户端业务请求处理器。用于修改请求参数（甚至可以转变成其他业务请求）与自定义请求实现。当turms收到客户端业务请求时会调用该Handler。通过该插件，您可以实现敏感词过滤等功能 |
| 通知与消息类               | NotificationHandler            | 通知Handler。当由于某行为的发生需要通知给相关用户时，turms-gateway会调用该Handler。常用于集成自定义的第三方推送服务 |
|                            | ExpiredMessageDeletionNotifier | 过期消息自动删除通知处理器。当Turms自动定期删除过期消息时，Turms服务端会调用该接口，告知该插件实现方所有将要被删除的消息。常用于开发者备份消息 |
| 服务实现类                 | StorageServiceProvider         | 存储服务Provider。Turms项目本身没有存储服务的具体实现，仅对外暴露了存储服务相关的接口，供该插件实现。（可参考turms-plugin-minio） |
| 业务模型生命周期类（TODO） |                                |                                                              |

## 插件加载方式

* 本地加载：Turms服务端会检测发布包`plugins`目录下，以`.jar`文件名结尾的JAR包，以及以`.js`文件名结尾的JavaScript文件是否为插件实现，如果是插件，则会在Turms服务端启动时加载它们。

  注意：Turms服务端不会加载存放在`lib`目录下的插件。

  拓展资料：[Turms服务端发布包的目录结构](https://turms-im.github.io/docs/zh-CN/server/deployment/distribution#%E6%9C%8D%E5%8A%A1%E7%AB%AF%E5%8F%91%E5%B8%83%E5%8C%85%E7%9A%84%E7%9B%AE%E5%BD%95%E7%BB%93%E6%9E%84)

* 通过HTTP接口加载：

  * 添加Java插件的API接口：`POST /plugins/java`
  * 添加JavaScript插件的API接口：`POST /plugins/js`

  拓展资料：[插件相关API接口](https://turms-im.github.io/docs/zh-CN/server/development/plugin#%E6%8F%92%E4%BB%B6%E7%9B%B8%E5%85%B3api%E6%8E%A5%E5%8F%A3)

* 通过turms-admin加载（基于“通过HTTP加载”实现）：在`/cluster/plugin`页面，管理员也能通过UI的方式上传Java插件与JavaScript插件。

## 插件实现

Turms服务端支持基于JVM或JavaScript语言的插件实现。

|          | JVM语言插件                                                  | JavaScript插件                                               |
| -------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| 语言版本 | Java 17 （Bytecode 61.0）                                    | ECMAScript 2022                                              |
| 优点     | 适合实现逻辑复杂的功能。<br />比如Turms项目的官方插件`turms-plugin-antispam`敏感词过滤插件 | 只需新建一个JavaScript文件，就可以直接编写自定义逻辑，无需编译，无需打包；<br />方便支持热更新 |
| 缺点     | 如果只是实现一点自定义逻辑，依旧需要先搭个插件项目，然后基于构建工具将代码打包成Jar包，流程繁琐 | 如果需要实现复杂的逻辑，则不如基于Java插件实现；<br />内存开销比Java插件大；<br />解释执行，运行效率低 |
| 总评     | 更适合做实现复杂、偏重且实现相对固定的插件。<br />该类插件更像是一个“工程” | 更适合小巧轻量、需要支持热更新的插件。<br />该类插件更像是一个“小补丁” |

### JVM语言版本（以Java为例）

#### 实现步骤

1. 安装Turms项目的JAR包依赖，供您插件编译时使用

   * Clone Turms的仓库。参考命令：`git clone --depth 1 https://github.com/turms-im/turms.git`
   * 在Turms项目的根目录（即`.git`目录的父目录）下，通过执行`mvn install -DskipUTs -DskipITs -DskipSTs`命令来编译Turms项目源码，并将生成的JAR包自动安装到本地的Maven仓库中，供您插件编译时使用

2. 搭建插件项目

   * 方案一（推荐）：将`turms/turms-plugin-demo`目录克隆一份到本地，并基于该模板进行开发。该方案可以减少不必要的重复配置工作。
   * 方案二：手动搭建。具体步骤如下：

     1. 新建一个Maven项目，并在`pom.xml`中添加依赖（实现turms-gateway服务端的插件，则添加turms-gateway依赖。实现turms-service的插件，则添加turms-service的依赖）：

        ```xml
        <dependency>
            <groupId>im.turms</groupId>
            <artifactId>turms-gateway</artifactId>
            <version>0.10.0-SNAPSHOT</version>
            <scope>provided</scope>
        </dependency>
        
        <dependency>
            <groupId>im.turms</groupId>
            <artifactId>turms-service</artifactId>
            <version>0.10.0-SNAPSHOT</version>
            <scope>provided</scope>
        </dependency>
        ```

     2. （可选）添加`maven-shade-plugin`用于将插件打包成uber JAR包。如果您不希望打包依赖JAR包，则不需要添加该插件，但需要您自行确认插件用到的依赖包已存在于Turms服务端当中。

        ```xml
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-shade-plugin</artifactId>
            <version>3.4.0</version>
            <executions>
                <execution>
                    <phase>package</phase>
                    <goals>
                        <goal>shade</goal>
                    </goals>
                    <configuration>
                        <outputDirectory>${project.build.outputDirectory}</outputDirectory>
                        <!-- Prevent generating the "original" jar file -->
                        <finalName>${project.artifactId}-${project.version}</finalName>
                        <minimizeJar>true</minimizeJar>
                    </configuration>
                </execution>
            </executions>
        </plugin>
        ```
     
     3. 在`resources`目录下，新建一个名称为`plugin.yaml`的文件，并添加以下插件描述信息（具体的参数值是您自定义插件的信息）
     
        ```yaml
        id: com.mydomain.MyPlugin
        class: com.mydomain.MyPlugin
        version: 0.0.1
        provider: com.mydomain
        license: MIT
        description: ''
        ```
        其中：
     
        * `id`字段用于区分插件。无格式要求，但是必须不为空
        * `class`字段用于引导Turms服务端加载JAR包内的`TurmsPlugin`实现类，必须不为空
        * 其他字段起描述作用，暂无实际作用，均可为空

3. 新建一个继承自`TurmsPlugin`的类，如`public class MyPlugin extends TurmsPlugin`，然后即可编写插件实现。

   其中：

   * `TurmsPlugin`的自定义子类是插件的入口类，且必须带有public的无参构造函数。在`plugin.yaml`的引导下，Turms服务端会找到这个类，并对其进行初始化
   * `TurmsPlugin`带有一个用于指定的`TurmsExtension`类的函数`getExtensions()`。它用于引导Turms服务端加载并初始化插件内自定义的`TurmsExtension`实现
   * `TurmsExtension`是拓展功能点的具体实现类，一个`TurmsExtension`可`implement`一个或多个`ExtensionPoint`，且必须具有public的无参构造函数
   * `ExtensionPoint`是Turms服务端定义的具体拓展功能点的接口，如`UserAuthenticator`与`ClientRequestHandler`

4. （可选）创建插件配置类，写上插件所需配置，并配置上`org.springframework.boot.context.properties.ConfigurationProperties`注释，用于定义配置名前缀。之后，您可以在`TurmsExtension`的实现类下，通过`loadProperties`函数，初始化并自动填充该配置类。

   以具体代码为例：

   ```java
   public class MyPlugin extends TurmsPlugin {
       @Override
       public Set<Class<? extends TurmsExtension>> getExtensions() {
           return Set.of(MyStorageServiceProvider.class);
       }
   }
   
   public class MyStorageServiceProvider extends TurmsExtension implements StorageServiceProvider {
       @Override
       public void onStarted() {
           MyPluginProperties properties = loadProperties(MyPluginProperties.class);
           // your business logic
       }
       // your business logic. e.g.:
       // Mono<Void> deleteResource(Long requesterId, ContentType contentType, String keyStr, keyNum) {
       //     return Mono.empty();
       // }
   }
   
   @ConfigurationProperties("my-plugin")
   @Data
   public class MyPluginProperties {
       private String whatever = "default";
   }
   ```

5. 用构建工具（如Maven与Gradle）将源码编译并打包成JAR包，并将JAR包放到Turms服务端的`plugins`目录下。注意，Turms不支持加载`plugins`子目录的JAR包。

   插件JAR包内的目录结构类似于：

   ```text
   ├─plugin.yaml
   ├─META-INF
   │  └─MANIFEST.MF
   └─com
     └─mydomain
       ├─MyStorageServiceProvider.class
       ├─MyPlugin.class
       └─MyPluginProperties.class
   ```

注意事项：

* Turms服务端只会检测`plugins`目录下，以`.jar`文件名结尾的JAR包是否为插件实现，因此如果您将插件JAR包放到`lib`目录下，则这些插件将不会被识别与使用。
* Turms不对插件进行访问控制，您需要自行确保插件中没有恶意代码。注意：恶意插件不仅可以调用函数直接强制关闭Turms服务端，甚至可以直接控制操作系统。
* 由于Turms服务端未来将引入Valhalla项目，因此开发过程需要特别注意不要使用`synchronized`关键字来锁八大基本包装类的对象，否则将直接抛出异常。

#### 类加载器

Turms服务端的插件类加载器使用parent-first类加载机制。具体而言，在插件实现需要使用一个类（如第三方依赖类：SLF4J、Netty等）时，插件类加载器会将类的加载工作委派给父类加载器，让父类加载器优先尝试加载，以与宿主Turms服务端共享类实现。如果宿主Turms服务端无该依赖的实现，则再尝试加载插件JAR包内的类实现。如果插件JAR包内也没有该类的实现，则会抛出`NoClassDefFoundError`异常。

如果插件使用的第三方依赖包版本与宿主Turms服务端使用的依赖包版本发生了不兼容冲突，则插件开发者可以自行通过`maven-shade-plugin`的[Relocating Classes](https://maven.apache.org/plugins/maven-shade-plugin/examples/class-relocation)方法来解决依赖包冲突。

补充：

* 因为每个插件都使用独立的插件类加载器，因此插件JAR包内的类有且仅会被当前插件使用，而不会被其他插件或宿主Turms服务端内部使用。
* Turms不使用child-first类加载机制的原因是：如果插件的类加载器优先加载插件JAR包内的类，则当这些类被传给宿主Turms服务端时，由于这些相同全限定名的类分别被插件的类加载器与宿主Turms服务端使用的类加载器（即application class loader）加载，因此JVM会抛出`java.lang.LinkageError`。

#### 插件Debug步骤（基于IntelliJ IDEA）

1. 在您插件项目下，按`Ctrl + F9`来自动编译并打包您的插件JAR包到`<您插件项目根目录>/target`目录下

2. 在`turms-server-common`项目下，把`im.turms.server.common.infra.plugin.PluginManager`类内的`getPluginDir`函数实现改成：

   ```java
   private Path getPluginDir(Path home, String pluginsDir) {
       return Path.of("<您插件项目根目录>/target");
   }
   ```

3. 在您的插件源码上打上断点

4. 通过IDEA启动turms-service或turms-gateway服务端，服务端将自动加载`<您插件项目根目录>/target`内的插件JAR包，并且当服务端执行到您插件源码的断点时，IDEA会暂停服务端运行供您调试

### JavaScript版本

为了实现Java插件需要搭一个工程环境，而实现JavaScript插件只需要新建一个JavaScript文件。

#### 实现步骤

以实现`StorageServiceProvider`插件为例：

```javascript
class MyTurmsExtension extends TurmsExtension {
    getExtensionPoints() {
        return ['im.turms.plugin.MyExtensionPoint'];
    }

    testBool() {
        return true;
    }

    async testNotification(builders) {
        const builder = builders.get(0);
        const notification = builder.setCode(123)
            .setReason('reason')
            .build();
        const List = Java.type('java.util.List');
        return await List.of(notification);
    }
}

class MyTurmsPlugin extends TurmsPlugin {
    getDescriptor() {
    	return {
        	id: 'com.mydomain.myplugin',
	        version: '0.0.1',
    	    provider: 'com.mydomain',
        	license: 'MIT',
	        description: ''
    	};
	}

    getExtensions() {
        return [MyTurmsExtension];
    }
}

export default MyTurmsPlugin;
```

其中：

* `MyTurmsExtension`类是开发者自定义的`TurmsExtension`拓展，开发者可以自定义类名。其中：
  * `getExtensionPoints`函数必须存在，用于返回该拓展类实现了的插件拓展点名称。如果开发者指定了拓展点，但没有实现拓展点的接口函数，则Turms服务端在执行插件回调函数时，会跳过该插件，并不会报错。

* `MyTurmsPlugin`类是开发者自定义的`TurmsPlugin`插件，开发者可以自定义类名。其中：
  * `getDescriptor`函数必须存在，它返回的对象是插件的描述信息：
    * `id`字段用于区分插件。无格式要求，但是必须不为空。

    * 其他字段起描述作用，暂无实际作用，均可为空。

  * `getExtensions`函数必须存在，它返回的对象是拓展类数组，如上文的`MyTurmsExtension`。

* `export default`用于导出开发者自定的插件，如上文的`MyTurmsPlugin`。

注意事项：

* Turms服务端只会检测`plugins`目录下，以`.js`文件名结尾的文件是否为插件实现，因此如果您将插件JAR包放到`lib`目录下，则这些插件将不会被识别与使用。
* Turms不对插件进行访问控制，您需要自行确保插件中没有恶意代码。注意：恶意插件不仅可以调用函数直接强制关闭Turms服务端，甚至可以直接控制操作系统。
* 上下文环境以插件为单位，即每个插件都有它独立的上下文环境，并且一个插件的所有函数公用一个上下文环境。换言之，下次执行的函数可以查看上次执行的函数对上下文环境的改动。
* JavaScript插件也能像Java插件那样访问Turms服务端的Java类与实例，甚至直接调用`System.exit()`，只是不推荐用JavaScript写复杂的插件
* 不支持调用`Node.js`模块。

#### 主要全局对象

* `load`函数是GraalVM的[全局函数](https://www.graalvm.org/22.0/reference-manual/js/JavaScriptCompatibility/#global-properties)，用于加载外部JavaScript资源。
* `turms`对象。该对象挂载了：
  * `log`对象，用于日志打印
  * `fetch`函数，用于发送HTTP请求

TODO

#### 插件Debug步骤

在Debug模式下（配置`turms.plugin.js.debug.enabled`为`true`，可以启动Debug模式）：

1. 当插件宿主Turms Java服务端调用由Java `Proxy`类代理后的JavaScript插件函数实现时（其代理实现源码在：`im.turms.server.common.infra.plugin.JsExtensionPointInvocationHandler`），监听JavaScript插件的WebSocket Debugger服务端会等待开发者启动Chrome浏览器的Debugger，以保证在开发者绑定完Debugger后，才开始执行JavaScript插件代码。此时调用JavaScript插件函数的Java调用线程会进入`WAITING`状态，并等待JavaScript插件函数执行完成。

2. 为了监听JavaScript插件代码实现，开发者需要自行打开Chrome浏览器，并输入监听JavaScript插件的WebSocket Debugger服务端监听地址，开发者可以在该页面上给JavaScript插件代码打上断点供调试。其中，服务端监听地址会被Turms服务端打印在控制台上，类似于：

   > Debugger listening on ws://127.0.0.1:24242/bd62b7be-bdec-48a6-9ad0-9314af33d531
   > For help, see: https://www.graalvm.org/tools/chrome-debugger
   > E.g. in Chrome open: devtools://devtools/bundled/js_app.html?ws=127.0.0.1:24242/bd62b7be-bdec-48a6-9ad0-9314af33d531

   其中的`devtools://devtools/bundled/js_app.html?ws=127.0.0.1:24242/bd62b7be-bdec-48a6-9ad0-9314af33d531`即是监听地址。

3. 在绑定完Chrome Debugger后，JavaScript插件函数就会开始执行

4. 等JavaScript插件函数执行完毕后，Java调用线程会进入`RUNNABLE`状态，而Java的代理函数也会接着返回JavaScript插件函数返回的数据。

## 配置项

| 配置名                                                  | 默认值    | 说明                                                         |
| ------------------------------------------------------- | --------- | ------------------------------------------------------------ |
| turms.plugin.enabled                                    | true      | 是否开启插件机制                                             |
| turms.plugin.dir                                        | `plugins` | 本地插件所在目录。Turms服务端将从该目录中加载插件            |
| turms.plugin.network.proxy.enabled                      | false     | 下载网络插件时，是否开启HTTP代理                             |
| turms.plugin.network.proxy.username                     |           | HTTP代理用户名                                               |
| turms.plugin.network.proxy.password                     |           | HTTP代理密码                                                 |
| turms.plugin.network.proxy.host                         |           | HTTP代理主机名                                               |
| turms.plugin.network.proxy.port                         | 8080      | HTTP代理端口号                                               |
| turms.plugin.network.proxy.connect-timeout-millis       | 60_000    | HTTP代理连接超时时长（毫秒）                                 |
| turms.plugin.network.plugins[?].url                     |           | 插件URL                                                      |
| turms.plugin.network.plugins[?].type                    | `AUTO`    | 插件类型。<br />当值为`AUTO`时，Turms服务端会根据URL的路径检测插件的类型：如果URL以`.jar`结尾，则判断为Java插件，如果URL以`.js`结尾，则判断为JavaScript插件，否则Turms服务端会抛出无法识别插件类型的异常。<br />当值为`JAVA`时，则为Java插件类型<br />当值为`JAVA_SCRIPT`时，则为JavaScript插件类型 |
| turms.plugin.network.plugins[?].use-local-cache         | false     | 是否使用本地插件缓存。如果`false`，Turms服务端会在每次启动时都重新下载插件 |
| turms.plugin.network.plugins[?].download.http-method    | `GET`     | 请求插件URL时，HTTP请求的方法类型                            |
| turms.plugin.network.plugins[?].download.timeout-millis | 60_000    | 下载插件的超时时间（毫秒）                                   |

## 插件相关API接口

OpenAPI地址：http://playground.turms.im:8510/openapi/ui#/plugin-controller


| **Controller**   | 路径               | 作用               | 通用 |
| :--------------- | ------------------ | ------------------ | ---- |
| PluginController | GET /plugins       | 查询插件           | ✅    |
|                  | PUT /plugins       | 更新插件           | ✅    |
|                  | DELETE /plugins    | 删除插件           | ✅    |
|                  | POST /plugins/java | 添加Java插件       | ✅    |
|                  | POST /plugins/js   | 添加JavaScript插件 | ✅    |