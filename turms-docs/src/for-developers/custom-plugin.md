# 自定义插件

## 目前支持的插件

* ### 用户类

  * UserAuthenticator：用户登陆认证。当客户端向turms-gateway请求登录时，turms-gateway会调用该插件以实现自定义的登录认证逻辑。
  * UserLoginActionLogHandler。用户登录行为日志Handler。当用户登录成功或下线时，turms-gateway会调用该Handler。
  * UserOnlineStatusChangeHandler： 用户在线状态变更Handler。当任意一位用户进入上线或离线状态时，turms-gateway会调用该接口。
* ### 请求类
  
  * ClientRequestHandler：客户端业务请求处理器。用于修改请求参数（甚至可以转变成其他业务请求）与自定义请求实现。当turms收到客户端业务请求时会调用该Handler。
* ### 通知与消息类
  
  * NotificationHandler。通知Handler。当由于某行为的发生需要通知给相关用户时，turms-gateway会调用该Handler。常用于集成自定义的第三方推送服务。
  * ExpiredMessageAutoDeletionNotificationHandler： 过期消息自动删除通知处理器。当Turms自动定期删除过期消息时，Turms服务端会调用该接口，告知该插件实现方所有将要被删除的消息。常用于开发者备份消息。
* ### 服务实现类
  
  * StorageServiceProvider：存储服务Provider。Turms项目本身没有存储服务的具体实现，仅对外暴露了存储服务相关的接口，供该插件实现。（可参考turms-plugin-minio）
* ### 业务模型生命周期类（TODO）

## 实现步骤

1. 搭建插件项目
   * 方案一（推荐）：克隆turms-plugin-demo项目到本地，基于该模板进行开发，以减少不必要的重复工作。
   * 方案二：手动搭建。具体步骤如下：
     
     1. Clone Turms的仓库，并在Turms服务端项目的目录下，通过`mvn install`命令将其安装到本地的Maven仓库中
     
     2. 新建一个Maven项目，并在pom.xml中添加依赖（实现turms服务端的插件，则添加turms依赖。实现turms-gateway的插件，则添加turms-gateway的依赖）：
     
        ```xml
        <dependency>
            <groupId>im.turms</groupId>
            <artifactId>turms</artifactId>
            <version>0.10.0-SNAPSHOT</version>
            <scope>provided</scope>
        </dependency>
        
        <dependency>
            <groupId>im.turms</groupId>
            <artifactId>turms-gateway</artifactId>
            <version>0.10.0-SNAPSHOT</version>
            <scope>provided</scope>
        </dependency>
        ```
     
     3. 新建一个extends自TurmsPlugin的类（例如：public class MyPlugin extends TurmsPlugin）
     
     4. 在resources目录下，新建一个名称为`plugin.properties`的文件，并添加以下插件描述信息（具体的参数值是您自定义插件的信息）
     
        ```properties
        plugin.class=com.mydomain.MyPlugin
        plugin.dependencies=
        plugin.id=com.mydomain.MyPlugin
        plugin.provider=
        plugin.version=0.0.1
        ```

5. 在自定义的TurmsPlugin子类内部，定义您想要实现的接口插件静态子类（例如：public static class MyTurmsRequestHandler extends ClientRequestHandler），并在该静态类上添加@Extension注释，之后就可以编写您自定义的逻辑代码。

3. 用Maven打包项目，并将打包好的jar包放入turms的plugins目录下

4. 启动turms或turms-gateway服务端，它们会在服务端启动时自动加载plugins目录下的所有插件
