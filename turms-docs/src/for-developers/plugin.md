# 自定义插件

## 插件拓展点列表

| 类别                       | 拓展点                                        | 描述                                                         |
| -------------------------- | --------------------------------------------- | ------------------------------------------------------------ |
| 用户类                     | UserAuthenticator                             | 用户登陆认证。当客户端向turms-gateway请求登录时，turms-gateway会调用该插件以实现自定义的登录认证逻辑。通过该插件，您就不需要（可选）将您业务系统中的用户信息同步到Turms当中了 |
|                            | UserOnlineStatusChangeHandler                 | 用户在线状态变更Handler。当任意一位用户进入上线或离线状态时，turms-gateway会调用该接口 |
| 请求类                     | ClientRequestHandler                          | 客户端业务请求处理器。用于修改请求参数（甚至可以转变成其他业务请求）与自定义请求实现。当turms收到客户端业务请求时会调用该Handler。通过该插件，您可以实现敏感词过滤等功能 |
| 通知与消息类               | NotificationHandler                           | 通知Handler。当由于某行为的发生需要通知给相关用户时，turms-gateway会调用该Handler。常用于集成自定义的第三方推送服务 |
|                            | ExpiredMessageAutoDeletionNotificationHandler | 过期消息自动删除通知处理器。当Turms自动定期删除过期消息时，Turms服务端会调用该接口，告知该插件实现方所有将要被删除的消息。常用于开发者备份消息 |
| 服务实现类                 | StorageServiceProvider                        | 存储服务Provider。Turms项目本身没有存储服务的具体实现，仅对外暴露了存储服务相关的接口，供该插件实现。（可参考turms-plugin-minio） |
| 业务模型生命周期类（TODO） |                                               |                                                              |

## 插件实现

Turms服务端支持基于Java或JavaScript语言的插件实现。

|          | Java插件                                                     | JavaScript插件                                               |
| -------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| 语言版本 | Java 17                                                      | ECMAScript 2021                                              |
| 优点     | 适合实现逻辑复杂的功能。<br />比如Turms项目的官方插件`turms-plugin-antispam`敏感词过滤插件 | 只需新建一个JavaScript文件，就可以直接编写自定义逻辑，无需编译，无需打包；<br />方便支持热更新 |
| 缺点     | 如果只是实现一点自定义逻辑，依旧需要先搭个插件项目，然后基于构建工具将代码打包成Jar包，流程繁琐 | 如果需要实现复杂的逻辑，则不如基于Java插件实现；<br />内存开销比Java插件大；<br />解释执行，运行效率低 |
| 总评     | 更适合做实现复杂、偏重且实现相对固定的插件。<br />该类插件更像是一个“工程” | 更适合小巧轻量、需要支持热更新的插件。<br />该类插件更像是一个“小补丁” |

### Java版本

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
            <version>3.2.4</version>
            <executions>
                <execution>
                    <phase>package</phase>
                    <goals>
                        <goal>shade</goal>
                    </goals>
                    <configuration>
                        <minimizeJar>true</minimizeJar>
                    </configuration>
                </execution>
            </executions>
        </plugin>
        ```
     
     3. 在`resources`目录下，新建一个名称为`plugin.properties`的文件，并添加以下插件描述信息（具体的参数值是您自定义插件的信息）
     
        ```properties
        id=com.mydomain.MyPlugin
        class=com.mydomain.MyPlugin
        version=0.0.1
        provider=com.mydomain
        license=MIT
        description=
        ```
        其中：
     
        * `id`字段用于区分插件。无格式要求，但是必须不为空
        * `class`字段用于引导Turms服务端加载JAR包内的`TurmsPlugin`实现类，必须不为空
        * 其他字段起描述作用，暂无实际作用，均可为空

3. 新建一个继承自`TurmsPlugin`的类，如`public class MyPlugin extends TurmsPlugin`，然后即可编写插件实现。

   其中：

   * `TurmsPlugin`的自定义子类是插件的入口类，且必须带有public的无参构造函数。在`plugin.properties`的引导下，Turms服务端会找到这个类，并对其进行初始化
   * `TurmsPlugin`带有一个用于指定的`TurmsExtension`类的函数`getExtensions()`。它用于引导Turms服务端加载并初始化插件内自定义的`TurmsExtension`实现
   * `TurmsExtension`是拓展功能点的具体实现类，一个`TurmsExtension`可`implement`一个或多个`ExtensionPoint`，且必须具有public的无参构造函数
   * `ExtensionPoint`是Turms服务端定义的具体拓展功能点的接口，如`UserAuthenticator`与`ClientRequestHandler`

4. （可选）创建插件配置类，写上插件所需配置，并配置上`org.springframework.boot.context.properties.ConfigurationProperties`注释，用于定义配置名前缀。之后，您可以在`TurmsExtension`的实现类下，通过`loadProperties`函数，初始化并自动填充该配置类。

   以具体代码为例：

   ```java
   public class MyPlugin extends TurmsPlugin {
       @Override
       protected Set<Class<? extends TurmsExtension>> getExtensions() {
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
   ├─plugin.properties
   ├─META-INF
   │  └─MANIFEST.MF
   └─com
     └─mydomain
       ├─MyStorageServiceProvider.class
       ├─MyPlugin.class
       └─MyPluginProperties.class
   ```

注意事项：

* Turms服务端只会检测`plugins`目录下，以`jar`结尾的JAR包是否为插件实现，因此如果您将插件JAR包放到`lib`目录下，则这些插件将不会被识别与使用。
* Turms不对插件进行访问控制，您需要自行确保插件中没有恶意代码。注意：恶意插件不仅可以调用函数直接强制关闭Turms服务端，甚至可以直接控制操作系统。
* 由于Turms服务端未来将引入Valhalla项目，因此开发过程需要特别注意不要使用`synchronized`关键字来锁八大基本包装类的对象，否则将直接抛出异常。

#### Class Loaders

Turms服务端中的插件实现相对灵活，既允许插件使用独立类环境，也允许插件与Turms服务端共享类环境。具体而言，在插件实现需要加载依赖包（如Netty）的时候，会让插件的class loader优先加载并使用插件JAR包内的实现，并且插件JAR包内的classes仅会被当前插件使用，不会被其他插件或Turms服务端内部使用。如果JAR包内无该依赖实现，则会让class loader将类加载工作委派给parent，并与Turms服务端共享类实现。如果Turms服务端自己也没加载该类，则会抛出`NoClassDefFoundError`异常。

#### 插件Debug步骤（基于IntelliJ IDEA）

1. 在您插件项目下，按`Ctrl + F9`来自动编译并打包您的插件JAR包到`<您插件项目根目录>/target`目录下

2. 在`turms-server-common`项目下，把在`src/main/java/im/turms/server/common/plugin/AbstractTurmsPluginManager.java`类内的`getPluginDir`函数实现改成：

   ```java
   private Path getPluginDir(Path home) {
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
function getPluginDescriptor() {
    return {
        id: 'com.mydomain.myplugin',
        version: '0.0.1',
        provider: 'com.mydomain',
        license: 'MIT',
        description: ''
    };
}

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
```

其中：

* `getPluginDescriptor`函数必须存在，且是固定的函数名，它返回的对象是插件的描述信息：
* `id`字段用于区分插件。无格式要求，但是必须不为空。
  
* 其他字段起描述作用，暂无实际作用，均可为空。


* `MyTurmsExtension`类是开发者自定义的`TurmsExtension`拓展，其中`getExtensionPoints`函数必须存在，用于返回该拓展实现了的插件拓展点名称。如果开发者指定了拓展点，但并没有提供对应的实现函数，则Turms服务端在执行插件回调函数时，会跳过该插件，并不会报错。

注意事项：

* Turms服务端只会检测`plugins`目录下，以`js`结尾的文件是否为插件实现，因此如果您将插件JAR包放到`lib`目录下，则这些插件将不会被识别与使用。
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

#### 插件Debug步骤（基于IntelliJ IDEA）

TODO

## 插件相关API接口

TODO
