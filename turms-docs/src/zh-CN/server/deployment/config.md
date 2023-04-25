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

      ::: code-group

      ```shell [Unix]
      TURMS_GATEWAY_JVM_CONF=<your-jvm-options-file-path> docker compose -f docker-compose.standalone.yml up --force-recreate
      ```

      ```powershell [PowerShell]
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

Turms配置分为四大类：

* Turms Gateway配置：对应turms-gateway服务端独有的配置
* Turms Service配置：对应turms-service服务端独有的配置。
* Common通用配置：Common通用配置可以被turms-gateway和turms-service服务端共用。
* 插件自身的配置：Turms服务端插件自身提供的配置。

#### 配置方法

1. 前文提到的`TURMS_GATEWAY_JVM_CONF`或`TURMS_SERVICE_JVM_CONF`，与`TURMS_GATEWAY_JVM_OPTS`或`TURMS_SERVICE_JVM_OPTS`也都可以被用来配置Turms服务端的参数。
2. 修改`application.yaml`下的配置文件。具体方法：
   1. 直接修改仓库内服务端下的`application.yaml`文件。因为如果修改了配置源文件，那用户就不能使用Turms官方提供的Docker镜像了，并且还需要自行打包成JAR包并制作镜像，因此这种方式一般只用于本地开发测试用，不用于线上环境。
   2. 使用前文提到的Docker挂载的方式，将自定义的服务端配置文件挂载到`/opt/turms/turms-gateway/config/application.yaml`路径上。
3. 调用Admin HTTP API进行修改，其路径为：`PUT /cluster/settings`。

提醒：对于插件自身的配置，其配置方法跟Turms服务端的配置方法一样，除了暂时不支持使用Admin HTTP API动态修改外，同样可以基于上述的①②两个方法进行配置。举例来说，如果一个插件是给turms-gateway服务端使用的插件，那么用户可以将插件自身的配置放到turms-gateway服务端的`TURMS_GATEWAY_JVM_OPTS`环境变量当中。

#### 配置集（Profiles）

如果开发者需要对同一个Turms服务端配置与切换使用不同的配置，则可以使用配置集。

默认情况下，Turms服务端源码中硬编码的配置与`application.yaml`文件中指定的配置就是默认生产环境的配置。如果开发者想要切换使用其他配置集，则可以通过修改`application.yaml`文件中的`spring.profiles.active`配置来使用其他配置集。

比如常见的用例：在本地开发调试时，想将生产环境配置，切换成默认的开发环境配置，则开发者可以将`application.yaml`文件中的`spring.profiles.active`值修改为`dev`，这样Turms服务端就会采用`application.yaml`与`application-dev.yaml`（默认开发环境配置）两个文件中指定的配置，且`application-dev.yaml`文件中的配置优先级更高，将覆盖默认配置。

#### 配置参数介绍

由于Turms服务端的配置项高达上百个，本小节仅对配置类别做简要的介绍。如果读者想查阅具体的配置项，可以查阅`im.turms.server.common.infra.property`包下的各配置类代码，或者继续浏览下文`配置项`小节所提供的配置项说明。

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

##### 插件自身的配置

如果用户想查阅Turms服务端官方插件的配置项，可以阅读对应的插件文档，这些文档都会罗列该插件所提供的配置项。

#### 服务端端口号配置

| 服务端                      | 配置项                               | 端口               | 作用                                                         |
| --------------------------- | ------------------------------------ | ------------------ | ------------------------------------------------------------ |
| turms-admin                 |                                      | 6510（HTTP）       | 提供后台管理员系统的Web页面                                  |
| turms-service/turms-gateway | turms.cluster.connection.server.port | 7510（TCP）        | 供turms-service与turms-gateway服务端的RPC使用                |
| turms-service               | turms.service.admin-api.http.port    | 8510（HTTP）       | 提供admin API与metrics API                                   |
| turms-gateway               | turms.gateway.admin-api.http.port    | 9510（HTTP）       | 提供metrics API                                              |
| turms-gateway               | turms.gateway.websocket.port         | 10510（WebSocket） | 与turms-client-js客户端交互                                  |
| turms-gateway               | turms.gateway.tcp.port               | 11510（TCP）       | 与客户端交互                                                 |
| turms-gateway               | turms.gateway.udp.port               | 12510（UDP）       | 与客户端交互（客户端均暂不支持）。<br />注意：UDP服务端为实验性功能，并不在第一版发布计划中 |

## 配置项

注意：下表不包括Turms服务端插件的配置。

|配置项|全局属性|可变属性|数据类型|默认值|说明|
|----|----|----|----|----|----|
|turms.cluster.connection.client.keepalive-interval-seconds|||int|5||
|turms.cluster.connection.client.keepalive-timeout-seconds|||int|15||
|turms.cluster.connection.client.reconnect-interval-seconds|||int|15||
|turms.cluster.connection.server.host|||string|0.0.0.0||
|turms.cluster.connection.server.port|||int|7510||
|turms.cluster.connection.server.port-auto-increment|||boolean|false||
|turms.cluster.connection.server.port-count|||int|100||
|turms.cluster.discovery.address.advertise-host||✅|string||The advertise address of the local node exposed to admins. (e.g. 100.131.251.96)|
|turms.cluster.discovery.address.advertise-strategy||✅|enum|PRIVATE_ADDRESS|The advertise strategy is used to decide which type of address should be used so that admins can access admin APIs and metrics APIs|
|turms.cluster.discovery.address.attach-port-to-host||✅|boolean|true|Whether to attach the local port to the host. e.g. The local host is 100.131.251.96, and the port is 9510 so the service address will be 100.131.251.96:9510|
|turms.cluster.discovery.delay-to-notify-members-change-seconds|||int|3|Delay notifying listeners on members change. Waits for seconds to avoid thundering herd|
|turms.cluster.discovery.heartbeat-interval-seconds|||int|10||
|turms.cluster.discovery.heartbeat-timeout-seconds|||int|30||
|turms.cluster.id|||string|turms||
|turms.cluster.node.active-by-default|||boolean|true||
|turms.cluster.node.id|||string||The node ID must start with a letter or underscore, and matches zero or more of characters [a-zA-Z0-9_] after the beginning. e.g. "turms001", "turms_002"|
|turms.cluster.node.leader-eligible|||boolean|true|Only works when it is a turms-service node|
|turms.cluster.node.priority|||int|0|The priority to be a leader|
|turms.cluster.node.zone|||string||e.g. "us-east-1" and "ap-east-1"|
|turms.cluster.rpc.request-timeout-millis|||int|30000|The timeout for RPC requests in milliseconds|
|turms.flight-recorder.closed-recording-retention-period|||int|0|A closed recording will be retained for the given period and will be removed from the file system after the retention period. 0 means no retention. -1 means unlimited retention.|
|turms.gateway.admin-api.address.advertise-host||✅|string||The advertise address of the local node exposed to admins. (e.g. 100.131.251.96)|
|turms.gateway.admin-api.address.advertise-strategy||✅|enum|PRIVATE_ADDRESS|The advertise strategy is used to decide which type of address should be used so that admins can access admin APIs and metrics APIs|
|turms.gateway.admin-api.address.attach-port-to-host||✅|boolean|true|Whether to attach the local port to the host. e.g. The local host is 100.131.251.96, and the port is 9510 so the service address will be 100.131.251.96:9510|
|turms.gateway.admin-api.enabled|||boolean|true|Whether to enable the APIs for administrators|
|turms.gateway.admin-api.http.host|||string|0.0.0.0||
|turms.gateway.admin-api.http.max-request-body-size-bytes|||int|10485760||
|turms.gateway.admin-api.http.port|||int|9510||
|turms.gateway.admin-api.log.enabled|✅|✅|boolean|true|Whether to log API calls|
|turms.gateway.admin-api.log.log-request-params|✅|✅|boolean|true|Whether to log the parameters of requests|
|turms.gateway.admin-api.rate-limiting.capacity|✅|✅|int|50|The maximum number of tokens that the bucket can hold|
|turms.gateway.admin-api.rate-limiting.initial-tokens|✅|✅|int|50|The initial number of tokens for new session|
|turms.gateway.admin-api.rate-limiting.refill-interval-millis|✅|✅|int|1000|The time interval to refill. 0 means never refill|
|turms.gateway.admin-api.rate-limiting.tokens-per-period|✅|✅|int|50|Refills the bucket with the specified number of tokens per period if the bucket is not full|
|turms.gateway.admin-api.use-authentication|||boolean|true|Whether to use authentication. If false, all HTTP requesters will personate the root user and all HTTP requests will be passed. You may set it to false when you want to manage authentication via security groups, NACL, etc|
|turms.gateway.client-api.logging.excluded-notification-categories|||Set-enum|[]|Turms will get the notifications to log from the union of "includedNotificationCategories" and "includedNotifications" except the notifications included in "excludedNotificationCategories" and "excludedNotificationTypes"|
|turms.gateway.client-api.logging.excluded-notification-types|||Set-enum|[]|Turms will get the notifications to log from the union of "includedNotificationCategories" and "includedNotifications" except the notifications included in "excludedNotificationCategories" and "excludedNotificationTypes"|
|turms.gateway.client-api.logging.excluded-request-categories|||Set-enum|[]|Turms will get the requests to log from the union of "includedRequestCategories" and "includedRequests" except the requests included in "excludedRequestCategories" and "excludedRequestTypes"|
|turms.gateway.client-api.logging.excluded-request-types|||Set-enum|[]|Turms will get the requests to log from the union of "includedRequestCategories" and "includedRequests" except the requests included in "excludedRequestCategories" and "excludedRequestTypes"|
|turms.gateway.client-api.logging.heartbeat-sample-rate|||float|0||
|turms.gateway.client-api.logging.included-notification-categories|||LinkedHashSet-LoggingCategoryProperties|[]|Turms will get the notifications to log from the union of "includedNotificationCategories" and "includedNotifications" except the notifications included in "excludedNotificationCategories" and "excludedNotificationTypes"|
|turms.gateway.client-api.logging.included-notifications|||LinkedHashSet-LoggingRequestProperties|[]|Turms will get the notifications to log from the union of "includedNotificationCategories" and "includedNotifications" except the notifications included in "excludedNotificationCategories" and "excludedNotificationTypes"|
|turms.gateway.client-api.logging.included-request-categories|||LinkedHashSet-LoggingCategoryProperties|[<br/>  {<br/>    "category": "ALL",<br/>    "sampleRate": 1<br/>  }<br/>]|Turms will get the requests to log from the union of "includedRequestCategories" and "includedRequests" except the requests included in "excludedRequestCategories" and "excludedRequestTypes"|
|turms.gateway.client-api.logging.included-requests|||LinkedHashSet-LoggingRequestProperties|[]|Turms will get the requests to log from the union of "includedRequestCategories" and "includedRequests" except the requests included in "excludedRequestCategories" and "excludedRequestTypes"|
|turms.gateway.client-api.max-request-size-bytes|||int|16384|The client session will be closed and may be blocked if it tries to send a request larger than the size. Note: The average size of turms requests is 16~64 bytes|
|turms.gateway.client-api.rate-limiting.capacity|✅|✅|int|50|The maximum number of tokens that the bucket can hold|
|turms.gateway.client-api.rate-limiting.initial-tokens|✅|✅|int|50|The initial number of tokens for new session|
|turms.gateway.client-api.rate-limiting.refill-interval-millis|✅|✅|int|1000|The time interval to refill. 0 means never refill|
|turms.gateway.client-api.rate-limiting.tokens-per-period|✅|✅|int|1|Refills the bucket with the specified number of tokens per period if the bucket is not full|
|turms.gateway.client-api.return-reason-for-server-error|||boolean|false|Whether to return the reason for the server error to the client. Note: 1. It may reveal sensitive data like the IP of internal servers if true; 2. turms-gateway never return the information of stack traces no matter it is true or false.|
|turms.gateway.fake.enabled|||boolean|false|Whether to fake clients. Note that faking only works in non-production environments|
|turms.gateway.fake.first-user-id|||long|100||
|turms.gateway.fake.request-count-per-interval|||int|10|The number of requests to send per interval. If requestIntervalMillis is 1000, requestCountPerInterval is TPS in fact|
|turms.gateway.fake.request-interval-millis|||int|1000|The interval to send request|
|turms.gateway.fake.user-count|||int|10|Run the number of real clients as faked users with an ID from [firstUserId, firstUserId + userCount) to connect to turms-gateway. So please ensure you have set "turms.service.fake.userCount" to a number larger than or equal to (firstUserId + userCount)|
|turms.gateway.notification-logging.enabled|||boolean|false|Whether to parse the buffer of TurmsNotification to log. Note that the property has an impact on performance|
|turms.gateway.service-discovery.advertise-host||✅|string||The advertise address of the local node exposed to the public. The property can be used to advertise the DDoS Protected IP address to hide the origin IP address (e.g. 100.131.251.96)|
|turms.gateway.service-discovery.advertise-strategy||✅|enum|PRIVATE_ADDRESS|The advertise strategy is used to help clients or load balancing servers to access the local node. Note: For security, do NOT use "PUBLIC_ADDRESS" in production to prevent from exposing the origin IP address for DDoS attack.|
|turms.gateway.service-discovery.attach-port-to-host||✅|boolean|true|Whether to attach the local port to the host. For example, if the local host is 100.131.251.96, and the port is 10510, so the service address will be 100.131.251.96:10510|
|turms.gateway.service-discovery.identity||✅|string||The identity of the local node will be sent to clients as a notification if identity is not blank and "turms.gateway.session.notifyClientsOfSessionInfoAfterConnected" is true (e.g. "turms-east-0001")|
|turms.gateway.session.client-heartbeat-interval-seconds|✅|✅|int|60|The client heartbeat interval. Note that the value will NOT change the actual heartbeat behavior of clients, and the value is only used to facilitate related operations of turms-gateway|
|turms.gateway.session.close-idle-session-after-seconds|✅|✅|int|180|A session will be closed if turms server does not receive any request (including heartbeat request) from the client during closeIdleSessionAfterSeconds. References: https://mp.weixin.qq.com/s?__biz=MzAwNDY1ODY2OQ==&mid=207243549&idx=1&sn=4ebe4beb8123f1b5ab58810ac8bc5994&scene=0#rd|
|turms.gateway.session.identity-access-management.enabled|✅|✅|boolean|true|Whether to authenticate and authorize users when logging in. Note that user ID is always required even if enabled is false. If false at startup, turms-gateway will not connect to the MongoDB server for user records|
|turms.gateway.session.identity-access-management.http.authentication.response-expectation.body-fields|||Map|{<br/>  "authenticated": true<br/>}||
|turms.gateway.session.identity-access-management.http.authentication.response-expectation.headers|||Map|{}||
|turms.gateway.session.identity-access-management.http.authentication.response-expectation.status-codes|||Set-string|[<br/>  "2??"<br/>]||
|turms.gateway.session.identity-access-management.http.request.headers|||Map|{}||
|turms.gateway.session.identity-access-management.http.request.http-method|||enum|GET||
|turms.gateway.session.identity-access-management.http.request.timeout-millis|||int|30000||
|turms.gateway.session.identity-access-management.http.request.url|||string|||
|turms.gateway.session.identity-access-management.jwt.algorithm.ecdsa256.p12.file-path|||string|||
|turms.gateway.session.identity-access-management.jwt.algorithm.ecdsa256.p12.key-alias|||string|||
|turms.gateway.session.identity-access-management.jwt.algorithm.ecdsa256.p12.password|||string|||
|turms.gateway.session.identity-access-management.jwt.algorithm.ecdsa256.pem-file-path|||string|||
|turms.gateway.session.identity-access-management.jwt.algorithm.ecdsa384.p12.file-path|||string|||
|turms.gateway.session.identity-access-management.jwt.algorithm.ecdsa384.p12.key-alias|||string|||
|turms.gateway.session.identity-access-management.jwt.algorithm.ecdsa384.p12.password|||string|||
|turms.gateway.session.identity-access-management.jwt.algorithm.ecdsa384.pem-file-path|||string|||
|turms.gateway.session.identity-access-management.jwt.algorithm.ecdsa512.p12.file-path|||string|||
|turms.gateway.session.identity-access-management.jwt.algorithm.ecdsa512.p12.key-alias|||string|||
|turms.gateway.session.identity-access-management.jwt.algorithm.ecdsa512.p12.password|||string|||
|turms.gateway.session.identity-access-management.jwt.algorithm.ecdsa512.pem-file-path|||string|||
|turms.gateway.session.identity-access-management.jwt.algorithm.hmac256.file-path|||string|||
|turms.gateway.session.identity-access-management.jwt.algorithm.hmac256.p12.file-path|||string|||
|turms.gateway.session.identity-access-management.jwt.algorithm.hmac256.p12.key-alias|||string|||
|turms.gateway.session.identity-access-management.jwt.algorithm.hmac256.p12.password|||string|||
|turms.gateway.session.identity-access-management.jwt.algorithm.hmac384.file-path|||string|||
|turms.gateway.session.identity-access-management.jwt.algorithm.hmac384.p12.file-path|||string|||
|turms.gateway.session.identity-access-management.jwt.algorithm.hmac384.p12.key-alias|||string|||
|turms.gateway.session.identity-access-management.jwt.algorithm.hmac384.p12.password|||string|||
|turms.gateway.session.identity-access-management.jwt.algorithm.hmac512.file-path|||string|||
|turms.gateway.session.identity-access-management.jwt.algorithm.hmac512.p12.file-path|||string|||
|turms.gateway.session.identity-access-management.jwt.algorithm.hmac512.p12.key-alias|||string|||
|turms.gateway.session.identity-access-management.jwt.algorithm.hmac512.p12.password|||string|||
|turms.gateway.session.identity-access-management.jwt.algorithm.ps256.p12.file-path|||string|||
|turms.gateway.session.identity-access-management.jwt.algorithm.ps256.p12.key-alias|||string|||
|turms.gateway.session.identity-access-management.jwt.algorithm.ps256.p12.password|||string|||
|turms.gateway.session.identity-access-management.jwt.algorithm.ps256.pem-file-path|||string|||
|turms.gateway.session.identity-access-management.jwt.algorithm.ps384.p12.file-path|||string|||
|turms.gateway.session.identity-access-management.jwt.algorithm.ps384.p12.key-alias|||string|||
|turms.gateway.session.identity-access-management.jwt.algorithm.ps384.p12.password|||string|||
|turms.gateway.session.identity-access-management.jwt.algorithm.ps384.pem-file-path|||string|||
|turms.gateway.session.identity-access-management.jwt.algorithm.ps512.p12.file-path|||string|||
|turms.gateway.session.identity-access-management.jwt.algorithm.ps512.p12.key-alias|||string|||
|turms.gateway.session.identity-access-management.jwt.algorithm.ps512.p12.password|||string|||
|turms.gateway.session.identity-access-management.jwt.algorithm.ps512.pem-file-path|||string|||
|turms.gateway.session.identity-access-management.jwt.algorithm.rsa256.p12.file-path|||string|||
|turms.gateway.session.identity-access-management.jwt.algorithm.rsa256.p12.key-alias|||string|||
|turms.gateway.session.identity-access-management.jwt.algorithm.rsa256.p12.password|||string|||
|turms.gateway.session.identity-access-management.jwt.algorithm.rsa256.pem-file-path|||string|||
|turms.gateway.session.identity-access-management.jwt.algorithm.rsa384.p12.file-path|||string|||
|turms.gateway.session.identity-access-management.jwt.algorithm.rsa384.p12.key-alias|||string|||
|turms.gateway.session.identity-access-management.jwt.algorithm.rsa384.p12.password|||string|||
|turms.gateway.session.identity-access-management.jwt.algorithm.rsa384.pem-file-path|||string|||
|turms.gateway.session.identity-access-management.jwt.algorithm.rsa512.p12.file-path|||string|||
|turms.gateway.session.identity-access-management.jwt.algorithm.rsa512.p12.key-alias|||string|||
|turms.gateway.session.identity-access-management.jwt.algorithm.rsa512.p12.password|||string|||
|turms.gateway.session.identity-access-management.jwt.algorithm.rsa512.pem-file-path|||string|||
|turms.gateway.session.identity-access-management.jwt.authentication.expectation.custom-payload-claims|||Map|{<br/>  "authenticated": true<br/>}||
|turms.gateway.session.identity-access-management.jwt.verification.audience|||string|||
|turms.gateway.session.identity-access-management.jwt.verification.custom-payload-claims|||Map|{}||
|turms.gateway.session.identity-access-management.jwt.verification.issuer|||string|||
|turms.gateway.session.identity-access-management.type|||enum|PASSWORD|Note that if the type is not PASSWORD, turms-gateway will not connect to the MongoDB server for user records|
|turms.gateway.session.min-heartbeat-interval-seconds|✅|✅|int|18|The minimum interval to refresh the heartbeat status by client requests to avoid refreshing the heartbeat status frequently|
|turms.gateway.session.notify-clients-of-session-info-after-connected|✅|✅|boolean|true|Whether to notify clients of the session information after connected with the server|
|turms.gateway.session.switch-protocol-after-seconds|✅|✅|int|540|If the turms server only receives heartbeat requests from the client during switchProtocolAfterSeconds, the TCP/WebSocket connection will be closed with the close status "SWITCH" to indicate the client should keep sending heartbeat requests over UDP if they want to keep online. Note: 1. The property only works if UDP is enabled; 2. For browser clients, UDP is not supported|
|turms.gateway.simultaneous-login.allow-device-type-others-login|✅|✅|boolean|true|Whether to allow the devices of DeviceType.OTHERS to login|
|turms.gateway.simultaneous-login.allow-device-type-unknown-login|✅|✅|boolean|true|Whether to allow the devices of DeviceType.UNKNOWN to login|
|turms.gateway.simultaneous-login.login-conflict-strategy|✅|✅|enum|DISCONNECT_LOGGED_IN_DEVICES|The login conflict strategy is used for servers to know how to behave if a device is logging in when there are conflicted and logged-in devices|
|turms.gateway.simultaneous-login.strategy|✅|✅|enum|ALLOW_ONE_DEVICE_OF_EACH_DEVICE_TYPE_ONLINE|The simultaneous login strategy is used to control which devices can be online at the same time|
|turms.gateway.tcp.backlog|||int|4096|The maximum number of connection requests waiting in the backlog queue. Large enough to handle bursts and GC pauses but do not set too large to prevent SYN-Flood attacks|
|turms.gateway.tcp.close-idle-connection-after-seconds|||int|300|A TCP connection will be closed on the server side if a client has not established a user session in a specified time. Note that the developers on the client side should take the responsibility to close the TCP connection according to their business requirements|
|turms.gateway.tcp.connection-timeout|||int|30||
|turms.gateway.tcp.enabled|||boolean|true||
|turms.gateway.tcp.host|||string|0.0.0.0||
|turms.gateway.tcp.port|||int|-1||
|turms.gateway.tcp.wiretap|||boolean|false||
|turms.gateway.udp.enabled|||boolean|true||
|turms.gateway.udp.host|||string|0.0.0.0||
|turms.gateway.udp.port|||int|-1||
|turms.gateway.websocket.backlog|||int|4096|The maximum number of connection requests waiting in the backlog queue. Large enough to handle bursts and GC pauses but do not set too large to prevent SYN-Flood attacks|
|turms.gateway.websocket.close-idle-connection-after-seconds|||int|300|A WebSocket connection will be closed on the server side if a client has not established a user session in a specified time. Note that the developers on the client side should take the responsibility to close the WebSocket connection according to their business requirements|
|turms.gateway.websocket.connect-timeout|||int|30|Used to mitigate the Slowloris DoS attack by lowering the timeout for the TCP connection handshake|
|turms.gateway.websocket.enabled|||boolean|true||
|turms.gateway.websocket.host|||string|0.0.0.0||
|turms.gateway.websocket.port|||int|-1||
|turms.health-check.check-interval-seconds|||int|3||
|turms.health-check.cpu.retries|||int|5||
|turms.health-check.cpu.unhealthy-load-threshold-percentage|||int|95||
|turms.health-check.memory.direct-memory-warning-threshold-percentage|||int|50|Log warning messages if the used direct memory exceeds the max direct memory of the percentage|
|turms.health-check.memory.heap-memory-gc-threshold-percentage|||int|60|If the used memory has used the reserved memory specified by maxAvailableMemoryPercentage and minFreeSystemMemoryBytes, try to start GC when the used heap memory exceeds the max heap memory of the percentage|
|turms.health-check.memory.heap-memory-warning-threshold-percentage|||int|95|Log warning messages if the used heap memory exceeds the max heap memory of the percentage|
|turms.health-check.memory.max-available-direct-memory-percentage|||int|95|The server will refuse to serve when the used direct memory exceeds the max direct memory of the percentage to try to avoid OutOfMemoryError|
|turms.health-check.memory.max-available-memory-percentage|||int|95|The server will refuse to serve when the used memory (heap memory + JVM internal non-heap memory + direct buffer pool) exceeds the physical memory of the percentage. The server will try to reserve max(maxAvailableMemoryPercentage of the physical memory, minFreeSystemMemoryBytes) for kernel and other processes. Note that the max available memory percentage does not conflict with the usage of limiting memory in docker because docker limits the memory of the container, while this memory percentage only limits the available memory for JVM|
|turms.health-check.memory.min-free-system-memory-bytes|||int|134217728|The server will refuse to serve when the free system memory is less than minFreeSystemMemoryBytes|
|turms.health-check.memory.min-heap-memory-gc-interval-seconds|||int|10||
|turms.health-check.memory.min-memory-warning-interval-seconds|||int|10||
|turms.ip.cached-private-ip-expire-after-millis||✅|int|60000|The cached private IP will expire after the specified time has elapsed. 0 means no cache|
|turms.ip.cached-public-ip-expire-after-millis||✅|int|60000|The cached public IP will expire after the specified time has elapsed. 0 means no cache|
|turms.ip.public-ip-detector-addresses||✅|List-string|[<br/>  "https://checkip.amazonaws.com",<br/>  "https://whatismyip.akamai.com",<br/>  "https://ifconfig.me/ip",<br/>  "https://myip.dnsomatic.com"<br/>]|The public IP detectors will only be used to query the public IP of the local node if needed (e.g. If the node discovery property "advertiseStrategy" is "PUBLIC_ADDRESS". Note that the HTTP response body must be a string of IP instead of a JSON|
|turms.location.enabled|||boolean|true|Whether to handle users' locations|
|turms.location.nearby-user-request.default-max-distance-meters|✅|✅|int|10000|The default maximum allowed distance in meters|
|turms.location.nearby-user-request.default-max-nearby-user-count|✅|✅|short|20|The default maximum allowed number of nearby users|
|turms.location.nearby-user-request.max-distance-meters|✅|✅|int|10000|The maximum allowed distance in meters|
|turms.location.nearby-user-request.max-nearby-user-count|✅|✅|short|100|The maximum allowed number of nearby users|
|turms.location.treat-user-id-and-device-type-as-unique-user|||boolean|false|Whether to treat the pair of user ID and device type as a unique user when querying users nearby. If false, only the user ID is used to identify a unique user|
|turms.logging.console.enabled|||boolean|false||
|turms.logging.console.level|||enum|INFO||
|turms.logging.file.compression.enabled|||boolean|true||
|turms.logging.file.enabled|||boolean|true||
|turms.logging.file.file-path|||string|@HOME/@SERVICE_TYPE_NAME.log||
|turms.logging.file.level|||enum|INFO||
|turms.logging.file.max-file-size-mb|||int|32||
|turms.logging.file.max-files|||int|320||
|turms.plugin.dir|||string|plugins|The relative path of plugins|
|turms.plugin.enabled|||boolean|true|Whether to enable plugins|
|turms.plugin.java.allow-save|||boolean|false|Whether to allow to save plugins using HTTP API|
|turms.plugin.js.allow-save|||boolean|false|Whether to allow to save plugins using HTTP API|
|turms.plugin.js.debug.enabled|||boolean|false|Whether to enable debugging|
|turms.plugin.js.debug.inspect-host|||string|localhost|The inspect host|
|turms.plugin.js.debug.inspect-port|||int|24242|The inspect port|
|turms.plugin.network.plugins|||List-NetworkPluginProperties|[]||
|turms.plugin.network.proxy.connect-timeout-millis|||int|60000|The HTTP proxy connect timeout in millis|
|turms.plugin.network.proxy.enabled|||boolean|false|Whether to enable HTTP proxy|
|turms.plugin.network.proxy.host|||string||The HTTP proxy host|
|turms.plugin.network.proxy.password|||string||The HTTP proxy password|
|turms.plugin.network.proxy.port|||int|8080|The HTTP proxy port|
|turms.plugin.network.proxy.username|||string||The HTTP proxy username|
|turms.security.blocklist.ip.auto-block.corrupted-frame.block-levels|||List-BlockLevel|[<br/>  {<br/>    "blockDurationSeconds": 600,<br/>    "goNextLevelTriggerTimes": 1,<br/>    "reduceOneTriggerTimeIntervalMillis": 60000<br/>  },<br/>  {<br/>    "blockDurationSeconds": 1800,<br/>    "goNextLevelTriggerTimes": 1,<br/>    "reduceOneTriggerTimeIntervalMillis": 60000<br/>  },<br/>  {<br/>    "blockDurationSeconds": 3600,<br/>    "goNextLevelTriggerTimes": 0,<br/>    "reduceOneTriggerTimeIntervalMillis": 60000<br/>  }<br/>]||
|turms.security.blocklist.ip.auto-block.corrupted-frame.block-trigger-times|||int|5|Block the client when the block condition is triggered the times|
|turms.security.blocklist.ip.auto-block.corrupted-frame.enabled|||boolean|false||
|turms.security.blocklist.ip.auto-block.corrupted-request.block-levels|||List-BlockLevel|[<br/>  {<br/>    "blockDurationSeconds": 600,<br/>    "goNextLevelTriggerTimes": 1,<br/>    "reduceOneTriggerTimeIntervalMillis": 60000<br/>  },<br/>  {<br/>    "blockDurationSeconds": 1800,<br/>    "goNextLevelTriggerTimes": 1,<br/>    "reduceOneTriggerTimeIntervalMillis": 60000<br/>  },<br/>  {<br/>    "blockDurationSeconds": 3600,<br/>    "goNextLevelTriggerTimes": 0,<br/>    "reduceOneTriggerTimeIntervalMillis": 60000<br/>  }<br/>]||
|turms.security.blocklist.ip.auto-block.corrupted-request.block-trigger-times|||int|5|Block the client when the block condition is triggered the times|
|turms.security.blocklist.ip.auto-block.corrupted-request.enabled|||boolean|false||
|turms.security.blocklist.ip.auto-block.frequent-request.block-levels|||List-BlockLevel|[<br/>  {<br/>    "blockDurationSeconds": 600,<br/>    "goNextLevelTriggerTimes": 1,<br/>    "reduceOneTriggerTimeIntervalMillis": 60000<br/>  },<br/>  {<br/>    "blockDurationSeconds": 1800,<br/>    "goNextLevelTriggerTimes": 1,<br/>    "reduceOneTriggerTimeIntervalMillis": 60000<br/>  },<br/>  {<br/>    "blockDurationSeconds": 3600,<br/>    "goNextLevelTriggerTimes": 0,<br/>    "reduceOneTriggerTimeIntervalMillis": 60000<br/>  }<br/>]||
|turms.security.blocklist.ip.auto-block.frequent-request.block-trigger-times|||int|5|Block the client when the block condition is triggered the times|
|turms.security.blocklist.ip.auto-block.frequent-request.enabled|||boolean|false||
|turms.security.blocklist.ip.enabled|||boolean|true||
|turms.security.blocklist.ip.sync-blocklist-interval-millis|||int|10000||
|turms.security.blocklist.user-id.auto-block.corrupted-frame.block-levels|||List-BlockLevel|[<br/>  {<br/>    "blockDurationSeconds": 600,<br/>    "goNextLevelTriggerTimes": 1,<br/>    "reduceOneTriggerTimeIntervalMillis": 60000<br/>  },<br/>  {<br/>    "blockDurationSeconds": 1800,<br/>    "goNextLevelTriggerTimes": 1,<br/>    "reduceOneTriggerTimeIntervalMillis": 60000<br/>  },<br/>  {<br/>    "blockDurationSeconds": 3600,<br/>    "goNextLevelTriggerTimes": 0,<br/>    "reduceOneTriggerTimeIntervalMillis": 60000<br/>  }<br/>]||
|turms.security.blocklist.user-id.auto-block.corrupted-frame.block-trigger-times|||int|5|Block the client when the block condition is triggered the times|
|turms.security.blocklist.user-id.auto-block.corrupted-frame.enabled|||boolean|false||
|turms.security.blocklist.user-id.auto-block.corrupted-request.block-levels|||List-BlockLevel|[<br/>  {<br/>    "blockDurationSeconds": 600,<br/>    "goNextLevelTriggerTimes": 1,<br/>    "reduceOneTriggerTimeIntervalMillis": 60000<br/>  },<br/>  {<br/>    "blockDurationSeconds": 1800,<br/>    "goNextLevelTriggerTimes": 1,<br/>    "reduceOneTriggerTimeIntervalMillis": 60000<br/>  },<br/>  {<br/>    "blockDurationSeconds": 3600,<br/>    "goNextLevelTriggerTimes": 0,<br/>    "reduceOneTriggerTimeIntervalMillis": 60000<br/>  }<br/>]||
|turms.security.blocklist.user-id.auto-block.corrupted-request.block-trigger-times|||int|5|Block the client when the block condition is triggered the times|
|turms.security.blocklist.user-id.auto-block.corrupted-request.enabled|||boolean|false||
|turms.security.blocklist.user-id.auto-block.frequent-request.block-levels|||List-BlockLevel|[<br/>  {<br/>    "blockDurationSeconds": 600,<br/>    "goNextLevelTriggerTimes": 1,<br/>    "reduceOneTriggerTimeIntervalMillis": 60000<br/>  },<br/>  {<br/>    "blockDurationSeconds": 1800,<br/>    "goNextLevelTriggerTimes": 1,<br/>    "reduceOneTriggerTimeIntervalMillis": 60000<br/>  },<br/>  {<br/>    "blockDurationSeconds": 3600,<br/>    "goNextLevelTriggerTimes": 0,<br/>    "reduceOneTriggerTimeIntervalMillis": 60000<br/>  }<br/>]||
|turms.security.blocklist.user-id.auto-block.frequent-request.block-trigger-times|||int|5|Block the client when the block condition is triggered the times|
|turms.security.blocklist.user-id.auto-block.frequent-request.enabled|||boolean|false||
|turms.security.blocklist.user-id.enabled|||boolean|true||
|turms.security.blocklist.user-id.sync-blocklist-interval-millis|||int|10000||
|turms.security.password.admin-password-encoding-algorithm|||enum|BCRYPT|The password encoding algorithm for admins|
|turms.security.password.initial-root-password|||string||The initial password of the root user|
|turms.security.password.user-password-encoding-algorithm|||enum|SALTED_SHA256|The password encoding algorithm for users|
|turms.service.admin-api.address.advertise-host||✅|string||The advertise address of the local node exposed to admins. (e.g. 100.131.251.96)|
|turms.service.admin-api.address.advertise-strategy||✅|enum|PRIVATE_ADDRESS|The advertise strategy is used to decide which type of address should be used so that admins can access admin APIs and metrics APIs|
|turms.service.admin-api.address.attach-port-to-host||✅|boolean|true|Whether to attach the local port to the host. e.g. The local host is 100.131.251.96, and the port is 9510 so the service address will be 100.131.251.96:9510|
|turms.service.admin-api.allow-delete-without-filter|✅|✅|boolean|false|Whether to allow administrators to delete data without any filter. Better false to prevent administrators from deleting all data by accident|
|turms.service.admin-api.default-available-records-per-request|✅|✅|int|10|The default available records per query request|
|turms.service.admin-api.enabled|||boolean|true|Whether to enable the APIs for administrators|
|turms.service.admin-api.http.host|||string|0.0.0.0||
|turms.service.admin-api.http.max-request-body-size-bytes|||int|10485760||
|turms.service.admin-api.http.port|||int|8510||
|turms.service.admin-api.log.enabled|✅|✅|boolean|true|Whether to log API calls|
|turms.service.admin-api.log.log-request-params|✅|✅|boolean|true|Whether to log the parameters of requests|
|turms.service.admin-api.max-available-online-users-status-per-request|✅|✅|int|20|The maximum available online users' status per query request|
|turms.service.admin-api.max-available-records-per-request|✅|✅|int|1000|The maximum available records per query request|
|turms.service.admin-api.max-day-difference-per-count-request|✅|✅|int|31|The maximum day difference per count request|
|turms.service.admin-api.max-day-difference-per-request|✅|✅|int|90|The maximum day difference per query request|
|turms.service.admin-api.max-hour-difference-per-count-request|✅|✅|int|24|The maximum hour difference per count request|
|turms.service.admin-api.max-month-difference-per-count-request|✅|✅|int|12|The maximum month difference per count request|
|turms.service.admin-api.rate-limiting.capacity|✅|✅|int|50|The maximum number of tokens that the bucket can hold|
|turms.service.admin-api.rate-limiting.initial-tokens|✅|✅|int|50|The initial number of tokens for new session|
|turms.service.admin-api.rate-limiting.refill-interval-millis|✅|✅|int|1000|The time interval to refill. 0 means never refill|
|turms.service.admin-api.rate-limiting.tokens-per-period|✅|✅|int|50|Refills the bucket with the specified number of tokens per period if the bucket is not full|
|turms.service.admin-api.use-authentication|||boolean|true|Whether to use authentication. If false, all HTTP requesters will personate the root user and all HTTP requests will be passed. You may set it to false when you want to manage authentication via security groups, NACL, etc|
|turms.service.client-api.disabled-endpoints|||Set-enum|[]|The disabled endpoints for client requests. Return ILLEGAL_ARGUMENT if a client tries to access them|
|turms.service.client-api.logging.excluded-notification-categories|||Set-enum|[]|Turms will get the notifications to log from the union of "includedNotificationCategories" and "includedNotifications" except the notifications included in "excludedNotificationCategories" and "excludedNotificationTypes"|
|turms.service.client-api.logging.excluded-notification-types|||Set-enum|[]|Turms will get the notifications to log from the union of "includedNotificationCategories" and "includedNotifications" except the notifications included in "excludedNotificationCategories" and "excludedNotificationTypes"|
|turms.service.client-api.logging.excluded-request-categories|||Set-enum|[]|Turms will get the requests to log from the union of "includedRequestCategories" and "includedRequests" except the requests included in "excludedRequestCategories" and "excludedRequestTypes"|
|turms.service.client-api.logging.excluded-request-types|||Set-enum|[]|Turms will get the requests to log from the union of "includedRequestCategories" and "includedRequests" except the requests included in "excludedRequestCategories" and "excludedRequestTypes"|
|turms.service.client-api.logging.included-notification-categories|||LinkedHashSet-LoggingCategoryProperties|[]|Turms will get the notifications to log from the union of "includedNotificationCategories" and "includedNotifications" except the notifications included in "excludedNotificationCategories" and "excludedNotificationTypes"|
|turms.service.client-api.logging.included-notifications|||LinkedHashSet-LoggingRequestProperties|[]|Turms will get the notifications to log from the union of "includedNotificationCategories" and "includedNotifications" except the notifications included in "excludedNotificationCategories" and "excludedNotificationTypes"|
|turms.service.client-api.logging.included-request-categories|||LinkedHashSet-LoggingCategoryProperties|[<br/>  {<br/>    "category": "ALL",<br/>    "sampleRate": 1<br/>  }<br/>]|Turms will get the requests to log from the union of "includedRequestCategories" and "includedRequests" except the requests included in "excludedRequestCategories" and "excludedRequestTypes"|
|turms.service.client-api.logging.included-requests|||LinkedHashSet-LoggingRequestProperties|[]|Turms will get the requests to log from the union of "includedRequestCategories" and "includedRequests" except the requests included in "excludedRequestCategories" and "excludedRequestTypes"|
|turms.service.conversation.read-receipt.allow-move-read-date-forward|✅|✅|boolean|false|Whether to allow to move the last read date forward|
|turms.service.conversation.read-receipt.enabled|✅|✅|boolean|true|Whether to allow to update the last read date|
|turms.service.conversation.read-receipt.update-read-date-after-message-sent|✅|✅|boolean|true|Whether to update the read date after a user sent a message|
|turms.service.conversation.read-receipt.update-read-date-when-user-querying-message|✅|✅|boolean|false|Whether to update the read date when a user queries messages|
|turms.service.conversation.read-receipt.use-server-time|✅|✅|boolean|true|Whether to use the server time to set the last read date when updating|
|turms.service.conversation.typing-status.enabled|✅|✅|boolean|true|Whether to notify users of typing statuses sent by other users|
|turms.service.fake.clear-all-collections-before-faking|||boolean|false|Whether to clear all collections before faking at startup|
|turms.service.fake.enabled|||boolean|false|Whether to fake data. Note that faking only works in non-production environments|
|turms.service.fake.fake-if-collection-exists|||boolean|false|Whether to fake data even if the collection has already existed|
|turms.service.fake.user-count|||int|1000|the total number of users to fake|
|turms.service.group.activate-group-when-created|✅|✅|boolean|true|Whether to activate a group when created by default|
|turms.service.group.delete-group-logically-by-default|✅|✅|boolean|true|Whether to delete groups logically by default|
|turms.service.group.invitation.allow-recall-pending-invitation-by-owner-and-manager|✅|✅|boolean|false|Whether to allow the owner and managers of a group to recall pending group invitations|
|turms.service.group.invitation.delete-expired-invitations-when-cron-triggered|✅|✅|boolean|false|Whether to delete expired group invitations when the cron expression is triggered|
|turms.service.group.invitation.expire-after-seconds|✅|✅|int|2592000|A group invitation will become expired after the specified time has passed|
|turms.service.group.invitation.expired-invitations-cleanup-cron|||string|0 15 2 * * *|Clean the expired group invitations when the cron expression is triggered if "deleteExpiredInvitationsWhenCronTriggered" is true|
|turms.service.group.invitation.max-content-length|✅|✅|int|200|The maximum allowed length for the text of a group invitation|
|turms.service.group.join-request.allow-recall-join-request-sent-by-oneself|✅|✅|boolean|false|Whether to allow users to recall the join requests sent by themselves|
|turms.service.group.join-request.delete-expired-join-requests-when-cron-triggered|✅|✅|boolean|false|Whether to delete expired group join requests when the cron expression is triggered|
|turms.service.group.join-request.expire-after-seconds|✅|✅|int|2592000|A group join request will become expired after the specified time has elapsed|
|turms.service.group.join-request.expired-join-requests-cleanup-cron|||string|0 30 2 * * *|Clean the expired group join requests when the cron expression is triggered if "deleteExpiredJoinRequestsWhenCronTriggered" is true|
|turms.service.group.join-request.max-content-length|✅|✅|int|200|The maximum allowed length for the text of a group join request|
|turms.service.group.member-cache-expire-after-seconds|✅||int|15|The group member cache will expire after the specified seconds. If 0, no group member cache|
|turms.service.group.question.answer-content-limit|✅|✅|int|50|The maximum allowed length for the text of a group question's answer|
|turms.service.group.question.max-answer-count|✅|✅|int|10|The maximum number of answers for a group question|
|turms.service.group.question.question-content-limit|✅|✅|int|200|The maximum allowed length for the text of a group question|
|turms.service.message.allow-edit-message-by-sender|✅|✅|boolean|true|Whether to allow the sender of a message to edit the message|
|turms.service.message.allow-recall-message|✅|✅|boolean|true|Whether to allow users to recall messages. Note: To recall messages, more system resources are needed|
|turms.service.message.allow-send-messages-to-oneself|✅|✅|boolean|false|Whether to allow users to send messages to themselves|
|turms.service.message.allow-send-messages-to-stranger|✅|✅|boolean|true|Whether to allow users to send messages to a stranger|
|turms.service.message.available-recall-duration-seconds|✅|✅|int|300|The available recall duration for the sender of a message|
|turms.service.message.cache.sent-message-cache-max-size|||int|10240|The maximum size of the cache of sent messages.|
|turms.service.message.cache.sent-message-expire-after|||int|30|The retention period of sent messages in the cache. For a better performance, it is a good practice to keep the value greater than the allowed recall duration|
|turms.service.message.check-if-target-active-and-not-deleted|✅|✅|boolean|true|Whether to check if the target (recipient or group) of a message is active and not deleted|
|turms.service.message.default-available-messages-number-with-total|✅|✅|int|1|The default available messages number with the "total" field that users request|
|turms.service.message.delete-message-logically-by-default|✅|✅|boolean|true|Whether to delete messages logically by default|
|turms.service.message.expired-messages-cleanup-cron|||string|0 45 2 * * *|Clean the expired messages when the cron expression is triggered|
|turms.service.message.is-recalled-message-visible|✅|✅|boolean|false|Whether to respond with recalled messages to clients' message query requests|
|turms.service.message.max-records-size-bytes|✅|✅|int|15728640|The maximum allowed size for the records of a message|
|turms.service.message.max-text-limit|✅|✅|int|500|The maximum allowed length for the text of a message|
|turms.service.message.message-retention-period-hours|✅|✅|int|0|A message will be retained for the given period and will be removed from the database after the retention period|
|turms.service.message.persist-message|✅|✅|boolean|true|Whether to persist messages in databases. Note: If false, senders will not get the message ID after the message has sent and cannot edit it|
|turms.service.message.persist-pre-message-id|✅|✅|boolean|false|Whether to persist the previous message ID of messages in databases|
|turms.service.message.persist-record|✅|✅|boolean|false|Whether to persist the records of messages in databases|
|turms.service.message.persist-sender-ip|✅|✅|boolean|false|Whether to persist the sender IP of messages in databases|
|turms.service.message.send-message-to-other-sender-online-devices|✅|✅|boolean|true|Whether to send the message to the other sender's online devices when sending a message|
|turms.service.message.sequence-id.use-sequence-id-for-group-conversation|✅||boolean|false|Whether to use the sequence ID for group conversations so that the client can be aware of the loss of messages. Note that the property has a significant impact on performance|
|turms.service.message.sequence-id.use-sequence-id-for-private-conversation|✅||boolean|false|Whether to use the sequence ID for private conversations so that the client can be aware of the loss of messages. Note that the property has a significant impact on performance|
|turms.service.message.time-type|✅|✅|enum|LOCAL_SERVER_TIME|The time type for the delivery time of message|
|turms.service.message.use-conversation-id|✅||boolean|false|Whether to use conversation ID so that a user can query the messages sent by themselves in a conversation quickly|
|turms.service.mongo.admin.optional-index.admin.registration-date|||boolean|false||
|turms.service.mongo.admin.optional-index.admin.role-id|||boolean|false||
|turms.service.mongo.group.optional-index.group-blocked-user.block-date|||boolean|false||
|turms.service.mongo.group.optional-index.group-blocked-user.requester-id|||boolean|false||
|turms.service.mongo.group.optional-index.group-invitation.group-id|||boolean|true||
|turms.service.mongo.group.optional-index.group-invitation.inviter-id|||boolean|false||
|turms.service.mongo.group.optional-index.group-invitation.response-date|||boolean|false||
|turms.service.mongo.group.optional-index.group-join-request.creation-date|||boolean|false||
|turms.service.mongo.group.optional-index.group-join-request.group-id|||boolean|true||
|turms.service.mongo.group.optional-index.group-join-request.responder-id|||boolean|false||
|turms.service.mongo.group.optional-index.group-join-request.response-date|||boolean|false||
|turms.service.mongo.group.optional-index.group-member.join-date|||boolean|false||
|turms.service.mongo.group.optional-index.group-member.mute-end-date|||boolean|false||
|turms.service.mongo.group.optional-index.group.creation-date|||boolean|false||
|turms.service.mongo.group.optional-index.group.creator-id|||boolean|false||
|turms.service.mongo.group.optional-index.group.deletion-date|||boolean|true||
|turms.service.mongo.group.optional-index.group.mute-end-date|||boolean|false||
|turms.service.mongo.group.optional-index.group.owner-id|||boolean|true||
|turms.service.mongo.group.optional-index.group.type-id|||boolean|false||
|turms.service.mongo.message.optional-index.message.deletion-date|||boolean|true||
|turms.service.mongo.message.optional-index.message.reference-id|||boolean|false||
|turms.service.mongo.message.optional-index.message.sender-id|||boolean|false||
|turms.service.mongo.message.optional-index.message.sender-ip|||boolean|true||
|turms.service.mongo.message.tiered-storage.auto-range-updater.cron|||string|0 0 3 * * *||
|turms.service.mongo.message.tiered-storage.auto-range-updater.enabled|✅|✅|boolean|true||
|turms.service.mongo.message.tiered-storage.enabled|||boolean|true||
|turms.service.mongo.message.tiered-storage.tiers|||LinkedHashMap|{<br/>  "cold": {<br/>    "days": 270,<br/>    "enabled": true,<br/>    "shards": [<br/>      ""<br/>    ]<br/>  },<br/>  "frozen": {<br/>    "days": 0,<br/>    "enabled": true,<br/>    "shards": [<br/>      ""<br/>    ]<br/>  },<br/>  "hot": {<br/>    "days": 30,<br/>    "enabled": true,<br/>    "shards": [<br/>      ""<br/>    ]<br/>  },<br/>  "warm": {<br/>    "days": 60,<br/>    "enabled": true,<br/>    "shards": [<br/>      ""<br/>    ]<br/>  }<br/>}|The storage properties for tiers from hot to cold. Note that the order of the tiers is important|
|turms.service.mongo.user.optional-index.user-friend-request.recipient-id|||boolean|false||
|turms.service.mongo.user.optional-index.user-friend-request.requester-id|||boolean|false||
|turms.service.mongo.user.optional-index.user-friend-request.response-date|||boolean|false||
|turms.service.mongo.user.optional-index.user-relationship-group-member.group-index|||boolean|false||
|turms.service.mongo.user.optional-index.user-relationship-group-member.join-date|||boolean|false||
|turms.service.mongo.user.optional-index.user-relationship-group-member.related-user-id|||boolean|false||
|turms.service.mongo.user.optional-index.user-relationship.establishment-date|||boolean|false||
|turms.service.notification.notify-group-conversation-participants-after-read-date-updated|✅|✅|boolean|false|Whether to notify the group conversation participants after the read receipt of a conversation has been updated by recipients|
|turms.service.notification.notify-invitee-after-group-invitation-recalled|✅|✅|boolean|true|Whether to notify the invitee after a group invitation has been recalled|
|turms.service.notification.notify-member-after-info-updated-by-others|✅|✅|boolean|true|Whether to notify members after its member information has been updated by others|
|turms.service.notification.notify-member-after-removed-from-relationship-group-by-others|✅|✅|boolean|false|Whether to notify the member after removed from a one-sided relationship group by others|
|turms.service.notification.notify-members-after-group-deleted|✅|✅|boolean|true|Whether to notify members after a group has been removed|
|turms.service.notification.notify-members-after-group-updated|✅|✅|boolean|true|Whether to notify members after a group has been updated|
|turms.service.notification.notify-members-after-one-sided-relationship-group-updated-by-others|✅|✅|boolean|false|Whether to notify members after a one-sided relationship group has been updated by others|
|turms.service.notification.notify-members-after-other-member-info-updated|✅|✅|boolean|false|Whether to notify members after other group member's information has been updated|
|turms.service.notification.notify-members-after-other-member-online-status-updated|✅|✅|boolean|false|Whether to notify members after other group member's online status has been updated|
|turms.service.notification.notify-owner-and-managers-after-group-join-request-recalled|✅|✅|boolean|true|Whether to notify the owner and managers after a group invitation has been recalled|
|turms.service.notification.notify-owner-and-managers-after-receiving-join-request|✅|✅|boolean|true|Whether to notify the owner and managers after a join request has been received|
|turms.service.notification.notify-private-conversation-participant-after-read-date-updated|✅|✅|boolean|false|Whether to notify the private conversation participant after the read receipt of a conversation has been updated by the recipient|
|turms.service.notification.notify-recipient-when-receiving-friend-request|✅|✅|boolean|true|Whether to notify the recipient when receiving a friend request|
|turms.service.notification.notify-recipients-after-message-updated-by-sender|✅|✅|boolean|true|Whether to notify the recipients after a message has been updated by the sender|
|turms.service.notification.notify-related-user-after-added-to-one-sided-relationship-group-by-others|✅|✅|boolean|false|Whether to notify the related user after added to a one-sided relationship group by others|
|turms.service.notification.notify-related-user-after-one-sided-relationship-updated-by-others|✅|✅|boolean|false|Whether to notify the related user after a one-sided relationship has benn updated by others|
|turms.service.notification.notify-related-users-after-other-related-user-info-updated|✅|✅|boolean|false|Whether to notify related users after other related user's information has been updated|
|turms.service.notification.notify-related-users-after-other-related-user-online-status-updated|✅|✅|boolean|false|Whether to notify related users after other related user's online status has been updated|
|turms.service.notification.notify-requester-after-friend-request-updated|✅|✅|boolean|true|Whether to notify the requester after a friend request has been updated|
|turms.service.notification.notify-user-after-added-to-group-by-others|✅|✅|boolean|true|Whether to notify the user after added to a group by others|
|turms.service.notification.notify-user-after-blocked-by-group|✅|✅|boolean|false|Whether to notify the user after blocked by a group|
|turms.service.notification.notify-user-after-invited-by-group|✅|✅|boolean|true|Whether to notify the user after invited by a group|
|turms.service.notification.notify-user-after-removed-from-group-by-others|✅|✅|boolean|true|Whether to notify the user after removed from a group by others|
|turms.service.notification.notify-user-after-unblocked-by-group|✅|✅|boolean|false|Whether to notify the user after unblocked by a group|
|turms.service.push-notification.apns.bundle-id|||string|||
|turms.service.push-notification.apns.enabled|||boolean|false||
|turms.service.push-notification.apns.key-id|||string|||
|turms.service.push-notification.apns.sandbox-enabled|||boolean|false||
|turms.service.push-notification.apns.signing-key|||string|||
|turms.service.push-notification.apns.team-id|||string|||
|turms.service.push-notification.fcm.credentials|||string|||
|turms.service.push-notification.fcm.enabled|||boolean|false||
|turms.service.statistics.log-online-users-number|✅|✅|boolean|true|Whether to log online users number|
|turms.service.statistics.online-users-number-logging-cron|||string|0/15 * * * * *|The cron expression to specify the time to log online users' number|
|turms.service.storage.group-profile-picture.allowed-content-type|||string|image/*|The allowed "Content-Type" of the resource that the client can upload|
|turms.service.storage.group-profile-picture.allowed-referrers|||List-string|[]|Restrict access to the resource to only allow the specific referrers (e.g. "https://github.com/turms-im/turms/*")|
|turms.service.storage.group-profile-picture.download-url-expire-after-seconds|||int|300|The presigned URLs are valid only for the specified duration. 0 means no expiration|
|turms.service.storage.group-profile-picture.expire-after-days|||int|0|Delete the resource the specific days after creation. 0 means no expiration|
|turms.service.storage.group-profile-picture.max-size-bytes|||int|1048576|The maximum size of the resource that the client can upload. 0 means no limit|
|turms.service.storage.group-profile-picture.min-size-bytes|||int|0|The minimum size of the resource that the client can upload. 0 means no limit|
|turms.service.storage.group-profile-picture.upload-url-expire-after-seconds|||int|300|The presigned URLs are valid only for the specified duration. 0 means no expiration|
|turms.service.storage.message-attachment.allowed-content-type|||string|*/*|The allowed "Content-Type" of the resource that the client can upload|
|turms.service.storage.message-attachment.allowed-referrers|||List-string|[]|Restrict access to the resource to only allow the specific referrers (e.g. "https://github.com/turms-im/turms/*")|
|turms.service.storage.message-attachment.download-url-expire-after-seconds|||int|300|The presigned URLs are valid only for the specified duration. 0 means no expiration|
|turms.service.storage.message-attachment.expire-after-days|||int|0|Delete the resource the specific days after creation. 0 means no expiration|
|turms.service.storage.message-attachment.max-size-bytes|||int|1048576|The maximum size of the resource that the client can upload. 0 means no limit|
|turms.service.storage.message-attachment.min-size-bytes|||int|0|The minimum size of the resource that the client can upload. 0 means no limit|
|turms.service.storage.message-attachment.upload-url-expire-after-seconds|||int|300|The presigned URLs are valid only for the specified duration. 0 means no expiration|
|turms.service.storage.user-profile-picture.allowed-content-type|||string|image/*|The allowed "Content-Type" of the resource that the client can upload|
|turms.service.storage.user-profile-picture.allowed-referrers|||List-string|[]|Restrict access to the resource to only allow the specific referrers (e.g. "https://github.com/turms-im/turms/*")|
|turms.service.storage.user-profile-picture.download-url-expire-after-seconds|||int|300|The presigned URLs are valid only for the specified duration. 0 means no expiration|
|turms.service.storage.user-profile-picture.expire-after-days|||int|0|Delete the resource the specific days after creation. 0 means no expiration|
|turms.service.storage.user-profile-picture.max-size-bytes|||int|1048576|The maximum size of the resource that the client can upload. 0 means no limit|
|turms.service.storage.user-profile-picture.min-size-bytes|||int|0|The minimum size of the resource that the client can upload. 0 means no limit|
|turms.service.storage.user-profile-picture.upload-url-expire-after-seconds|||int|300|The presigned URLs are valid only for the specified duration. 0 means no expiration|
|turms.service.user.activate-user-when-added|✅|✅|boolean|true|Whether to activate a user when added by default|
|turms.service.user.delete-two-sided-relationships|✅|✅|boolean|false|Whether to delete the two-sided relationships when a user requests to delete a relationship|
|turms.service.user.delete-user-logically|✅|✅|boolean|true|Whether to delete a user logically|
|turms.service.user.friend-request.allow-send-request-after-declined-or-ignored-or-expired|✅|✅|boolean|false|Whether to allow resending a friend request after the previous request has been declined, ignored, or expired|
|turms.service.user.friend-request.delete-expired-requests-when-cron-triggered|✅|✅|boolean|false|Whether to delete expired when the cron expression is triggered|
|turms.service.user.friend-request.expired-user-friend-requests-cleanup-cron|||string|0 0 2 * * *|Clean expired friend requests when the cron expression is triggered if deleteExpiredRequestsWhenCronTriggered is true|
|turms.service.user.friend-request.friend-request-expire-after-seconds|✅|✅|int|2592000|A friend request will become expired after the specified time has elapsed|
|turms.service.user.friend-request.max-content-length|✅|✅|int|200|The maximum allowed length for the text of a friend request|
|turms.service.user.max-intro-length|✅|✅|int|100|The maximum allowed length for a user's intro|
|turms.service.user.max-name-length|✅|✅|int|20|The maximum allowed length for a user's name|
|turms.service.user.max-password-length|✅|✅|int|16|The maximum allowed length for a user's password|
|turms.service.user.max-profile-picture-length|✅|✅|int|100|The maximum allowed length for a user's profile picture|
|turms.service.user.min-password-length|✅|✅|int|-1|The minimum allowed length for a user's password. If 0, it means the password can be an empty string "". If -1, it means the password can be null|
|turms.service.user.respond-offline-if-invisible|✅|✅|boolean|false|Whether to respond to client with the OFFLINE status if a user is in INVISIBLE status|
|turms.shutdown.job-timeout-millis|||long|120000|Wait for a job 2 minutes at most for extreme cases by default. Though it is a long time, graceful shutdown is usually better than force shutdown.|
|turms.user-status.cache-user-sessions-status|||boolean|true|Whether to cache the user sessions status|
|turms.user-status.user-sessions-status-cache-max-size|||int|-1|The maximum size of the cache of users' sessions status|
|turms.user-status.user-sessions-status-expire-after|||int|60|The life duration of each remote user's sessions status in the cache. Note that the cache will make the presentation of users' sessions status inconsistent during the time|