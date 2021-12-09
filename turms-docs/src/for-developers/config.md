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

## 配置分类

Turms属性分为三大类配置：Turms Service配置、Turms Gateway配置，以及Common通用配置。Turms Service配置对应turms服务端独有的配置，Turms Gateway配置对应turms-gateway服务端独有的配置，而Common通用配置可以被turms和turms-gateway服务端共用。

每个类别中的属性都能通过`application.yaml`配置，并且对于标有`MutablePropertiesView`注释的属性，您都能通过供管理员专用的API接口在Turms集群运行时进行零停机实时更新。

由于所有的配置项高达上百个，直接看代码比看文档更加直观，因此推荐您直接查阅`im.turms.server.common.property`目录下各配置类，下文仅对大的分类做简要介绍。

提醒：您在本地编译`turms/turms-gateway`服务端项目后，编译器会生成`target/classes/META-INF/spring-configuration-metadata.json`文件。IntelliJ IDEA 能够自动检测到该文件，并在您输入Turms相关配置的时提供配置提示与补全功能，如下图所示：

![](https://raw.githubusercontent.com/turms-im/assets/master/turms/configuration-code-completion.png)

### Tumrs Service配置

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

### Turms Gateway配置

| 类别      | 类                            | 字段名              | 描述                                                         |
| --------- | ----------------------------- | ------------------- | ------------------------------------------------------------ |
| 管理员API | AddressProperties             | metricsApiAddress   | 度量API地址相关配置                                          |
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

### Common通用配置

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

| 服务端                      | 端口               | 作用                                          |
| --------------------------- | ------------------ | --------------------------------------------- |
| turms-admin                 | 6510（HTTP）       | 提供后台管理员系统的Web页面                   |
| turms-service/turms-gateway | 7510（TCP）        | 供turms-service与turms-gateway服务端的RPC使用 |
| turms-service               | 8510（HTTP）       | 提供admin API与metrics API                    |
| turms-gateway               | 9510（HTTP）       | 提供metrics API                               |
| turms-gateway               | 10510（WebSocket） | 与客户端交互                                  |
| turms-gateway               | 11510（TCP）       | 与客户端交互（Swift客户端暂不支持）           |
| turms-gateway               | 12510（UDP）       | 与客户端交互（客户端均暂不支持）              |