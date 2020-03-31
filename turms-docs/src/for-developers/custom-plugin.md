### 自定义插件

#### 步骤

推荐您直接基于turms-plugin-demo进行开发，以减少不必要的操作。

1. 新建一个Maven项目
2. 在pom.xml中引入：

~~添加仓库：~~

```
<repository>
    <id>sonatype-nexus-snapshots</id>
    <name>Sonatype Nexus Snapshots</name>
    <url>https://oss.sonatype.org/content/repositories/snapshots/</url>
</repository>
```

Clone Turms的仓库，并在Turms服务端项目的目录下，通过mvn install命令将其安装到本地的Maven仓库中

添加依赖：

```
<dependency>
    <groupId>im.turms</groupId>
    <artifactId>turms</artifactId>
    <version>0.9.0-SNAPSHOT</version>
</dependency>
```

3. 新建一个extends自TurmsPlugin的类（例如：public class MyPlugin extends TurmsPlugin）
4. 在这个新建类的内部，定义一个您想要实现的接口插件静态实现类（例如：public static class MyTurmsRequestHandler implements ClientRequestHandler），并在该注释上@Extension
5. 将resources目录下的plugin.properties文件修改为您自己的插件说明
6. 用Maven打包项目，并将打包好的jar包放入turms的plugins目录下
7. 启动Turms服务端，Turms将自动加载plugins目录下的所有插件

目前支持的插件：

①ClientRequestHandler：客户端请求处理器。它可以拦截客户端发出的所有TurmsRequest请求，您可以通过它：1. transform：转换TurmsRequest中的参数，甚至转换成其他类型的请求；2. 自定义处理实现，等同于直接修改Turms的源代码。例如，您可以将任何请求转发给任何用户（仍需要关系认证）。

②UserAuthenticator：用户登陆认证。Turms会解析用户发来的握手请求，并解析成UserLoginInfo对象，供您判断是否允许用户进行登陆。

③ExpiryMessageAutoDeletionNotificationHandler： 过期消息自动删除通知处理器。当Turms自动定期删除过期消息时，Turms服务端会调用该接口，告知该插件实现方所有将要被删除的消息（完整的im.turms.turms.pojo.domain.Message对象）。常用于开发者异地备份消息。

④UserOnlineStatusChangeHandler： 用户在线状态变更处理器。当任意一位用户进入上线或离线状态时，Turms会调用该接口