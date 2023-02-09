# 配置参数

## 重要性

即时通讯的业务场景繁多，因此不同业务对硬件资源有着**天差地别**的要求（比如：需要数据库的架构与不需要数据库的架构）。 为了有效利用服务器资源，请务必细心了解Turms服务端所提供的配置参数。

* 场景一：100%的消息可达率 vs 主动抛弃消息

  * 在社交应用中，一般都会要求消息有100%的可达率。反之，对于在直播聊天室应用中，服务端甚至会专门根据消息优先级与服务端负载，来主动地抛弃用户消息或是把消息只发送给聊天室中的部分用户。
  * 对于前者，Turms使用Redis来拉取会话级别的递增`sequence ID`来实现消息的100%必达。对于后者，Turms会根据内存中的消息与服务端负载信息来主动抛弃消息。二者对于消息可达率有着完全不同但又都合理的需求，因此二者的实现对于硬件配置也有着截然不同的要求。

* 场景二：读扩散消息存储 vs 零消息存储

  * A应用是一款主要面向商务客户的即时通讯应用。这款应用有一个需求：当一名用户在商务群里发送了一条消息，该用户能够得知群组中其他**每一名用户**是否已读该消息，就算该用户发完消息就下线了，当其再次上线时，仍能查询其他人对该消息的已读状态。 

    因此，如果一个商务群有100名用户，当其中一名用户发出一条消息时，Turms需要存储1条Message与1条Conversation（Turms采用读扩散消息模型，另外请注意：该条Conversation记录会携带99个群成员的最后已读时间）。

  * B应用是一款直播弹幕聊天应用，它对消息的处理非常随意。当一名用户在一个直播频道发出一条消息后，该用户不仅无需得知其他用户的已读状态，甚至连消息本身都不要求存储（即无离线消息需求）。

    因此，如果一个直播频道有100名，当其中一名用户发出一条消息时，Turms需要存储0条Message与0条Conversation记录。

  * 对比A应用需要消息存储功能，而B应用不用。因此在B应用的架构设计中甚至都不要用到存储消息的表（当然，实际应用中一般还是会存储用户消息来做用户行为分析）。因此二者对硬件需求也截然不同。

## 本地配置与全局配置

Turms服务端具有本地配置与全局配置两大类配置，其中：

|          | 本地配置                                                     | 全局配置                                                     |
| -------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| 应用域   | 仅对当前节点生效                                             | 对集群中所有节点生效                                         |
| 存储位置 | 存储在本地的`application-[profile].yaml`文件中               | 存储在MongoDB数据库中`turms-config/shared-cluster-properties`集合中 |
| 可变     | 对于标有`MutableProperty`注释的属性，用户都能通过供管理员专用的API接口在Turms集群运行时进行零停机实时更新 | 同左                                                         |

## 配置分类

配置分为两大类，一类是JVM的配置，一类是Turms服务端的配置。

### JVM配置

turms-gateway的JVM默认配置文件为：`turms-gateway/dist/config/jvm.options`

turms-service的JVM默认配置文件为：`turms-service/dist/config/jvm.options`。

用户一般使用默认的JVM配置即可，不需要自行修改JVM配置。

如果用户想修改JVM配置，可以通过以下两种方式：

1. 修改环境变量`TURMS_GATEWAY_JVM_CONF`（对于turms-gateway）或`TURMS_SERVICE_JVM_CONF`（对于turms-service），并指向自定义的JVM配置文件，以使用完全自定义的JVM配置。下文以修改turms-gateway的JVM配置为例，具体修改方法：

   1. 如果通过`run.sh`脚本启动，则可以使用类似`export TURMS_GATEWAY_JVM_CONF=<your-jvm-options-file-path> && sh run.sh -f`来设置环境变量并启动。

   2. 如果通过Docker镜像启动，则可以使用类似：

      ```shell
      docker run -d --name turms-gateway --ulimit nofile=1048576 \
        --memory-swappiness=0 \
        -p 7510:7510 -p 9510:9510 -p 10510:10510 -p 11510:11510 -p 12510:12510 \
        --health-cmd="curl -I --silent $${HOST}:9510/health || exit 1" \
        --health-interval=5s \
        --health-timeout=5s \
        --health-retries=3 \
        --health-start-period=60s \
        -e TURMS_GATEWAY_JVM_CONF=<your-jvm-options-file-path> \
        ghcr.io/turms-im/turms-gateway
      ```

      注意：上述的`TURMS_GATEWAY_JVM_CONF`路径指向的是镜像内部的路径，而非宿主机的路径。如果想使用宿主机里的配置文件，则需要使用Docker的挂载机制，如：

      ```shell
      docker run -d --name turms-gateway --ulimit nofile=1048576 \
        --memory-swappiness=0 \
        -p 7510:7510 -p 9510:9510 -p 10510:10510 -p 11510:11510 -p 12510:12510 \
        --health-cmd="curl -I --silent $${HOST}:9510/health || exit 1" \
        --health-interval=5s \
        --health-timeout=5s \
        --health-retries=3 \
        --health-start-period=60s \
        -v <your-jvm-options-file-path>:/opt/turms/turms-gateway/config/jvm.options:ro \
        ghcr.io/turms-im/turms-gateway
      ```

   3. 如果通过Docker Compose，则可以使用类似：

      ::: code-tabs#example

      @tab Unix:

      ```shell
      TURMS_GATEWAY_JVM_CONF=<your-jvm-options-file-path> docker compose -f docker-compose.standalone.yml up --force-recreate
      ```

      @tab PowerShell

      ```powershell
      $env:TURMS_GATEWAY_JVM_CONF=<your-jvm-options-file-path>;docker compose -f docker-compose.standalone.yml up --force-recreate
      ```

      :::

      注意：上述的`TURMS_GATEWAY_JVM_CONF`路径指向的是镜像内部的路径，而非宿主机的路径。如果想使用宿主机里的配置文件，则需要修改`docker-compose.standalone.yml`配置文件，以使用Docker的挂载机制，如：

      ```yaml
      turms-gateway:
        volumes:
          - <your-jvm-options-file-path>:/opt/turms/turms-gateway/config/jvm.options:ro
      ```

2. 修改环境变量`TURMS_GATEWAY_JVM_OPTS`（对于turms-gateway）或`TURMS_SERVICE_JVM_OPTS`（对于turms-service），以在JVM配置文件的基础上，附加自定义的JVM配置，并覆盖已声明的JVM配置。具体修改方法同上，故不赘述。

   注意：该变量的格式为：`-D<name>=<value> -D<name>=<value>`，如：`-Dspring.profiles.active=DEV -Dturms.cluster.discovery.address.advertise-host=myturms`。

### Turms服务端配置

Turms配置分为三大类：Turms Service配置、Turms Gateway配置，以及Common通用配置。Turms Service配置对应turms服务端独有的配置，Turms Gateway配置对应turms-gateway服务端独有的配置，而Common通用配置可以被turms和turms-gateway服务端共用。

#### 配置方法

1. 前文提到的`TURMS_GATEWAY_JVM_CONF`或`TURMS_SERVICE_JVM_CONF`，与`TURMS_GATEWAY_JVM_OPTS`或`TURMS_SERVICE_JVM_OPTS`也都可以被用来配置Turms服务端的参数。
2. 修改`application.yaml`下的配置文件。具体方法：
   1. 直接修改仓库内服务端下的`application.yaml`文件。因为如果修改了配置源文件，那用户就不能使用Turms官方提供的Docker镜像了，并且还需要自行打包成JAR包并制作镜像，因此这种方式一般只用于本地开发测试用，不用于线上环境。
   2. 使用前文提到的Docker挂载的方式，将自定义的服务端配置文件挂载到`/opt/turms/turms-gateway/config/application.yaml`路径上。

#### 配置集（Profiles）

如果开发者需要对同一个Turms服务端配置与切换使用不同的配置，则可以使用配置集。

默认情况下，Turms服务端源码中硬编码的配置与`application.yaml`文件中指定的配置就是默认生产环境的配置。如果开发者想要切换使用其他配置集，则可以通过修改`application.yaml`文件中的`spring.profiles.active`配置来使用其他配置集。

比如常见的用例：在本地开发调试时，想将生产环境配置，切换成默认的开发环境配置，则开发者可以将`application.yaml`文件中的`spring.profiles.active`值修改为`dev`，这样Turms服务端就会采用`application.yaml`与`application-dev.yaml`（默认开发环境配置）两个文件中指定的配置，且`application-dev.yaml`文件中的配置优先级更高，将覆盖默认配置。

#### 配置参数介绍

由于所有的配置项高达上百个，直接看代码比看文档更加直观，因此推荐您直接查阅`im.turms.server.common.infra.property`目录下各配置类，下文仅对大的分类做简要介绍。

提醒：您在本地编译`turms/turms-gateway`服务端项目后，编译器会生成`target/classes/META-INF/spring-configuration-metadata.json`文件。IntelliJ IDEA 能够自动检测到该文件，并在您输入Turms相关配置的时提供配置提示与补全功能，如下图所示：

![](https://raw.githubusercontent.com/turms-im/assets/master/turms/configuration-code-completion.png)

##### Tumrs Service配置

| 类别      | 类                     | 字段名       | 描述                  | 补充                                                         |
| --------- | ---------------------- | ------------ | --------------------- | ------------------------------------------------------------ |
| 管理员API | AdminApiProperties     | adminApi     | 管理员API接口相关配置 |                                                              |
| 客户端API | ClientApiProperties    | clientApi    | 客户端API接口相关配置 |                                                              |
| Fake数据  | FakeProperties         | fake         | Fake数据相关配置      |                                                              |
| 数据源    | MongoProperties        | mongo        | MongoDB数据库相关配置 | Turms完全复用MongoDB的URI配置。参考文档：<br />https://docs.mongodb.com/manual/reference/connection-string/ |
|           | TurmsRedisProperties   | redis        | Redis数据库相关配置   |                                                              |
| 统计      | StatisticsProperties   | statistics   | 统计相关配置          |                                                              |
| 通知      | NotificationProperties | notification | 通知相关配置          |                                                              |
| 文件存储  | StorageProperties      | storage      | 存储相关配置          |                                                              |
| 业务行为  | UserProperties         | user         | 用户相关配置          |                                                              |
|           | GroupProperties        | group        | 群组相关配置          |                                                              |
|           | ConversationProperties | conversation | 消息会话服务相关配置  |                                                              |
|           | MessageProperties      | message      | 消息服务相关配置      |                                                              |

##### Turms Gateway配置

| 类别      | 类                            | 字段名              | 描述                                                         |
| --------- | ----------------------------- | ------------------- | ------------------------------------------------------------ |
| 管理员API | AdminApiProperties            | adminApi            | 管理员API接口相关配置                                        |
| 客户端API | ClientApiProperties           | clientApi           | 面向客户端的HTTP接入层相关配置（即ReasonController的相关配置） |
|           | NotificationLoggingProperties | notificationLogging | 通知日志相关配置                                             |
| 服务接口  | UdpProperties                 | udp                 | UDP服务端相关配置                                            |
|           | TcpProperties                 | tcp                 | TCP服务端相关配置                                            |
|           | WebSocketProperties           | websocket           | WebSocket服务端相关配置                                      |
|           | DiscoveryProperties           | serviceDiscovery    | 服务发现相关配置                                             |
| Fake数据  | FakeProperties                | fake                | Fake数据相关配置                                             |
| 数据源    | MongoProperties               | mongo               | MongoDB数据库相关配置                                        |
|           | TurmsRedisProperties          | redis               | Redis数据库相关配置                                          |
| 业务行为  | SimultaneousLoginProperties   | simultaneousLogin   | 多端登录相关配置                                             |
|           | SessionProperties             | session             | 会话相关配置                                                 |

##### Common通用配置

| 类                    | 字段名      | 描述                                                         |
| --------------------- | ----------- | ------------------------------------------------------------ |
| ClusterProperties     | cluster     | 集群相关配置。包括配置当前运行节点信息、服务发现注册信息、配置中心信息、RPC参数 |
| HealthCheckProperties | healthCheck | 监控节点健康状态                                             |
| IpProperties          | ip          | 公网IP探测相关配置                                           |
| LocationProperties    | location    | 用户坐标相关配置                                             |
| LoggingProperties     | logging     | 基础日志配置                                                 |
| PluginProperties      | plugin      | 插件相关配置                                                 |
| SecurityProperties    | security    | 用户与管理员密码加密相关配置                                 |
| UserStatusProperties  | userStatus  | 用户会话（连接）状态相关配置                                 |

## 补充

### 服务端默认端口号

| 服务端                      | 端口               | 作用                                                         |
| --------------------------- | ------------------ | ------------------------------------------------------------ |
| turms-admin                 | 6510（HTTP）       | 提供后台管理员系统的Web页面                                  |
| turms-service/turms-gateway | 7510（TCP）        | 供turms-service与turms-gateway服务端的RPC使用                |
| turms-service               | 8510（HTTP）       | 提供admin API与metrics API                                   |
| turms-gateway               | 9510（HTTP）       | 提供metrics API                                              |
| turms-gateway               | 10510（WebSocket） | 与客户端交互                                                 |
| turms-gateway               | 11510（TCP）       | 与客户端交互（Swift客户端暂不支持）                          |
| turms-gateway               | 12510（UDP）       | 与客户端交互（客户端均暂不支持）。<br />注意：UDP服务端为实验性功能，并不在第一版发布计划中 |