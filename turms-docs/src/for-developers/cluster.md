# 集群的设计与实现

Turms的集群代码实现比较清晰，也很容易理解。代码实现包为：`src/main/java/im/turms/server/common/cluster`；配置包为：`src/main/java/im/turms/server/common/property/env/common/cluster`

## 纯自研的原因

|            | 自研                                                         | 第三方服务                                                   |
| ---------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| 定制化功能 | Turms有很多定制化的细节需求，各功能环环相扣，自研的话可以保证新需求立马实现（可能就几分钟的事情），无需写Hacky代码 | 别人不一定给做定制化功能。就算给做通常也是几周、几个月、甚至几年后，才把新功能发布到新版本。如此低的效率是绝对不能接受的 |
| 学习难度   | 服务划分清晰，代码精简，方面快速学习与掌握。花10~30分钟对有点基础的新人进行培训，新人即可掌握Turms集群服务 | 像ZooKeeper或Eureka这样仅仅关于微服务某个功能的项目，其源码就已经远远多于Turms下述的六大服务源码之和。且第三方服务项目还涉及一些相对复杂但对Turms又完全无用的累赘功能，如Zookeeper的zab协议，徒增学习难度。要想把握其实现细节需要大量的实践与源码阅读 |
| 实现难度   | 集群服务代码实现难度低。举例而言，Turms的集群服务实现的难度总和远远低于“敏感词过滤”功能里提到的“基于双数组Trie的AC自动机算法”。<br />另外，其实集群服务的实现难度也远低于IM业务逻辑的实现 | 需要针对第三方服务特点，编写Adaptor代码。难度虽低，第三方服务的源码复杂度导致想保证Adaptor代码永远如预期那样执行并不是件容易的事情 |
| 性能       | Turms能结合业务代码特点，让集群服务的实现互相照应配合，保证整个流程下来无冗余数据的生成。同时所有网络操作都基于Netty实现，性能极高 | 由于第三方服务都是基于通用需求，我们为此一方面要写大量的Adaptor代码，徒增资源开销，也增加了学习难度。另一方面，其自身的实现无法保证极致的高效，甚至还有些服务竟然还使用阻塞API |

另外，其实稍有实力且稍有点定制化需求的公司都会选择自研，原因同上。

## 节点

实现类：`im.turms.server.common.cluster.node.Node`

配置类：`im.turms.server.common.property.env.common.cluster.NodeProperties`

节点类对内管理节点信息与节点生命周期事件，并调度各节点服务。对外承接用户自定义配置，并暴露节点服务与提供一些常用的Util函数供业务实现代码使用。

## 服务

### 分布式配置中心服务（Config）

服务类：`im.turms.server.common.cluster.service.config.SharedConfigService`

配置类：`im.turms.server.common.property.env.common.cluster.SharedConfigProperties`

如今微服务领域的基础服务实现方案百花齐放。以配置中心的实现方案为例，其实现方案就有：K8S的ConfigMaps、云服务厂商的配置服务（如AWS的AppConfig）、开源实现（如Zookeeper）。作为Turms作为一个技术中立的开源项目，其技术栈绝不能被厂商所绑定。但与此同时，又要保证这些实现能够很方便地获得云服务厂商的支持，以让运维人员“点点鼠标就能实现与部署了”。同时又要满足容灾、高可用、可监控、易操作等多种关键特性，因此Turms通过MongoDB自研实现配置中心实现，以满足上述的所有要求。

具体配置的增删改查操作实现即为常规的MongoDB数据库的增删改查操作，非常常规，故不累赘。唯一值得特别注意的是：Turms通过MongoDB的Change Stream机制来监听配置的变化，而官方客户端实现`mongo-java-driver`采用轮询机制来监听配置变化，而不是MongoDB服务端主动通知MongoDB的客户端。

补充：因为服务注册中心的“服务信息”本质上来说也是一种配置，因此下述的服务注册与发现也是基于该配置中心实现的。

#### 相关Admin API

TODO

### 服务注册与发现服务（Discovery）

服务类：`im.turms.server.common.cluster.service.discovery.DiscoveryService`

配置类：`im.turms.server.common.property.env.common.cluster.DiscoveryProperties`

#### 职责

该服务主要负责：

* 尽最大努力，保证当前节点注册在服务注册中心中。每个节点在服务端启动时，都会向服务注册中心注册当前节点的信息。如果注册失败（如该节点信息已被注册），则会主动关闭服务端进程并报告失败异常信息。如果在节点运行过程中，其注册信息被服务注册中心异常删除（如管理员错误地删除了数据），则该节点会自动重新注册其信息
* 监听服务注册中心的节点增删改事件，以通知“网络连接服务”去连接或断开对应的TCP连接
* 选举Leader

#### 注册节点的记录格式

注册节点的记录格式一共有两种类型：Member与Leader

##### Member

类：`im.turms.server.common.cluster.service.config.domain.discovery.Member`

| 字段类别     | 字段名            | 描述                                                         |
| ------------ | ----------------- | ------------------------------------------------------------ |
| Key          | clusterId         | 集群ID                                                       |
|              | nodeId            | 节点ID                                                       |
| 一般信息     | zone              | 节点所在区域。用于充当`雪花ID算法`中的`数据中心ID`           |
|              | nodeVersion       | 节点版本号。用来保证节点之间的操作能够版本兼容               |
|              | nodeType          | 节点类型。用来保证RPC请求能够被发送给正确的节点              |
|              | isSeed            | 如果一个节点的`lastHeartbeatDate`超时60秒，且`isSeed`为`false`，则该节点会被自动移除服务注册中心。如果`isSeed`为`true`，则就算心跳超时，该节点也不会被移除 |
|              | registrationDate  | 节点注册时间                                                 |
|              | isLeaderEligible  | 用于判断节点是否可以参与选举                                 |
|              | priority          | 优先级。主要用于在Leader选举时，高位值的节点能被优先选举为Leader |
| RPC地址信息  | memberHost        | RPC主机号。用于保证其他节点能够通过该主机号，与其进行通信    |
|              | memberPort        | RPC端口号。用于保证其他节点能够通过该端口号，与其进行通信    |
| 补充地址信息 | metricsApiAddress | 无实际作用。仅用于管理员能够通过Admin API得知该地址信息      |
|              | adminApiAddress   | 同上                                                         |
|              | wsAddress         | 同上                                                         |
|              | tcpAddress        | 同上                                                         |
|              | udpAddress        | 同上                                                         |
| 状态信息     | isHealthy         | 为True时表示该节点成功完成心跳刷新操作。该字段并无实际作用，仅仅作为指示器表明节点健康状态。即便一个节点处于不健康状态，它仍然可以处理客户端请求。<br />另外，各集群节点的该字段值由Leader节点根据各节点的`lastHeartbeatDate`进行更新 |
|              | isActive          | 为False时表明禁止该节点处理客户端请求。该字段的值有且仅能通过Admin API进行更新。可用于在灰度发布时，先将节点逐步断流，再进行停机更新操作 |
|              | lastHeartbeatDate | 记录上一次心跳刷新时间，用于Leader节点根据该值更新`isHealthy`信息 |

##### Leader

| 字段类别 | 字段名     | 描述                                                         |
| -------- | ---------- | ------------------------------------------------------------ |
| Key      | clusterId  | 集群ID                                                       |
|          | nodeId     | Leader节点ID                                                 |
| 一般信息 | renewDate  | 租约刷新时间。如果超过60s未进行刷新，则服务注册中心会自动删除该Leader记录信息 |
|          | generation | 代。主要用于拒绝前代Leader因为没有检测到新Leader的诞生，而尝试进行租约操作的行为 |

#### Leader选举

节点参与选举的条件：

* 节点类型必须为`turms-service`，而不是`turms-gateway`。这是因为一些Leader行为只能由turms-service执行，turms-gateway没有能力执行这些操作。
* `im.turms.server.common.property.env.common.cluster.NodeProperties#leaderEligible`为`true`（默认为`true`）
* 节点状态必须为`active`

##### 自动选举

TODO

##### 手动选举（Admin API）

TODO

#### Leader的职责

总体而言，需要保证只有一个节点触发或执行的动作，通常就由Leader节点执行。另外，该类行为在一些服务端实现中，会通过节点抢占分布式锁来实现，但该实现的可靠性、可控性与性能都远不如使用统一Leader的方案，故Turms不采用抢占分布式锁方案。

具体动作而言：

* Leader最重要的动作之一就是根据其他节点在服务注册中心（MongoDB）的心跳刷新时间，来更新各节点的最新状态（具体代码在：`im.turms.server.common.cluster.service.discovery.LocalNodeStatusManager#updateMembersStatus`）
* “定期cron向Redis发送清除过期黑名单记录的指令”这一动作只需一个节点，即Leader来定期执行。
* “定期cron删除过期数据库数据操作，如用户消息”，也有且仅会被Leader执行（补充：这类操作的代码其实是“历史遗留代码”，“顺便”保留的。毕竟极少应用会真得删除用户数据，因此默认disabled状态，可以忽略）

#### 相关Admin API

TODO

### 网络连接服务（Connection）

服务类：`im.turms.server.common.cluster.service.connection.ConnectionService`

配置类：`im.turms.server.common.property.env.common.cluster.connection.ConnectionProperties`

#### 职责

* 根据`服务注册与发现服务`的请求，基于TCP连接其他集群节点。注意：两个节点之间有且仅会存在一个TCP连接
* 如果意外地与其他集群节点断连，则尽最大努力进行重连操作
* 发送心跳请求，以确认节点之间的TCP连接确实有效

#### 网络连接的生命周期

 * 建立TCP连接
 * 进行握手操作，交换节点的基础必要信息
 * 在握手成功后，节点之间即可进行网络数据的收发操作
 * 在关闭TCP网络连接之前，先发送挥手操作，通知对端该节点要主动与其断连，以区别TCP意外断连
 * 关闭TCP连接

### 编解码服务（Codec）

服务类：`im.turms.server.common.cluster.service.codec.CodecService`

该服务主要为RPC服务提供数据的编解码实现。特别地，Turms并没有采用反射机制来统一实现序列化与反序列化逻辑，而是为每个数据定制实现，这主要是因为：1. 定制化实现，保证绝对地高效。如Set\<DeviceType\>可以用一个Byte，按Bit表示值的存在与否，而不是用一组Byte表示；2. 避免反射，保证高效；3. 代码所见即所得，避免隐晦操作的存在

### RPC服务

服务类：`im.turms.server.common.cluster.service.rpc.RpcService`

配置类：`im.turms.server.common.property.env.common.cluster.RpcProperties`

该服务基于“网络连接服务”提供的底层TCP网络连接与“编解码服务”提供的数据序列化与反序列化能力，来实现RPC操作的相关逻辑。

#### 编码格式

请求格式：

1. Varint编码的正文长度。对大部分RPC请求而言，该部分通常占1~2 bytes。
2. 请求头：数据类型ID（2 bytes） + 请求ID（4 bytes）
3. 请求体：不同请求的编码方式不同，但都采用定制编码，以保证极致的高效。另外，请求体中最大的数据为“用户自定义文本”，如“聊天消息”。Turms目前没计划采用各种压缩技术，这是因为：一方面，各种压缩技术的压缩率都不太理想，二是压缩与解压需要消耗大量的内存与CPU资源，故不采用压缩技术。

响应格式：

1. Varint编码的正文长度。对大部分RPC响应而言，该部分通常占1 byte。
2. 响应头：数据类型ID（2 bytes） + 被响应的请求ID（4 bytes）
3. 响应体：响应体可以分为两大类：正常响应与异常响应。正确响应即各种数据类型，如八大基本类型与其他组合的数据类型。异常响应本质上也仅仅是一种“组合的数据类型”，它的表现形式为`RpcException`数据类型，通过`RpcErrorCode`、`TurmsStatusCode`、`description (String)`字段，来描述异常信息。

补充：部分请求（如通知）会被发送给多个不同的RPC节点，它们的请求体都是共享堆外直接内存的，不需要进行内存拷贝。

#### 背压

turms-gateway服务端对turms-service服务端的背压实现（TODO，暂未实现）比较取巧，具体而言：每个节点都会根据当前节点的CPU与内存负载状态，计算出当前节点的“可服务值”（区间为[0, 100]），并通过“服务注册中心”向其他节点同步该信息。turms-gateway会根据已知的turms-service的“可服务值”按权重比例分发RPC请求。如果turms-gateway发现当前所有turms-service的“可服务值”均小于10，则不再进行RPC下发，而是直接抛出异常。

### 分布式ID生成服务（IdGen）

服务类：`im.turms.server.common.cluster.service.idgen.IdService`

分布式ID生成器用于为各业务场景快速提供集群唯一的ID。生成一个集群唯一的ID只需要节点进行本地运算操作（具体代码：`im.turms.server.common.cluster.service.idgen.SnowflakeIdGenerator#nextLargeGapId`），效率极高。

#### 原理

Turms的分布式ID生成器基于主流的[雪花ID算法](https://en.wikipedia.org/wiki/Snowflake_ID)实现，生成的ID为`long`数据类型，具体而言：

* 最高位（1 bit）始终为0，表示正数
* 41 bits表示以毫秒为单位的时间戳，可表示约69年时间。具体UTC时间区间为：`[2020-10-13, 2090-06-19]`。`2020-10-13`为硬编码的Epoch时间，如果您想修改该时间，修改`im.turms.server.common.cluster.service.idgen.SnowflakeIdGenerator#EPOCH`的值即可
* 4 bits表示数据中心ID，ID区间为[0, 15]。在实际运用中，该ID通常以`云服务中的区域`划分，即每个区域都有一个ID。Turms会根据节点的`im.turms.server.common.property.env.common.cluster.NodeProperties#zone`“区域名”，自动将区域名映射为[0, 15]区间中的值。注意：如果有16个以上的区域名，虽然这些区域名仍会被映射为[0, 15]区间中的值，但这也意味着会出现重复的数据中心ID，有集群节点生成相同ID的风险。并且，被降级处理的节点会打印警告日志，提醒有生成相同ID的风险。
* 8 bits表示工作节点ID，ID区间为[0, 255]。Turms会根据节点的`im.turms.server.common.property.env.common.cluster.NodeProperties#zone`“区域名”，自动将区域名映射为[0, 255]区间中的值。注意：如果在一个数据中心中有256个以上的节点，虽然这些节点ID仍会被映射为[0, 255]区间中的值，但这也意味着会出现重复的工作节点ID，有集群节点生成相同ID的风险。并且，被降级处理的节点会打印警告日志，提醒有生成相同ID的风险。
* 10 bits表示序列号。在单位时间戳字段内（1毫秒）可表示至多1024个序列号，即1毫秒中最多可生成1024个唯一ID。换言之，1秒内至多可以表示1024000个唯一ID，因此在实际使用中，是不可能出现重复ID的情况。

补充：根据节点信息，更新数据中心ID与工作节点ID信息的代码在：`im.turms.server.common.cluster.service.idgen.IdService#IdService`的`addOnMembersChangeListener`中

#### 变种实现

具体实现：`im.turms.server.common.cluster.service.idgen.SnowflakeIdGenerator#nextLargeGapId`

常规雪花算法生成的ID是单调递增的。但在大部分情况下，Turms的业务实现采用的是大间距ID，以避免ID单调递增。这么做是因为：使用大间距ID，以保证当这些数据存储到MongoDB数据库时，MongoDB能够根据这些ID，生成足够多的Chunks，并将这些Chunks负载均衡分配给各MongoDB服务端，让其进行存储。而单调递增ID会导致所有新数据始终分配到唯一的热点MongoDB服务端，导致数据库的负载均衡失效。

大间距ID的实现也很简单，仅仅是把各字段进行重排，具体顺序为：`序列号、时间戳、数据中心ID、工作节点ID`（常规雪花算法的ID顺序为`时间戳、数据中心ID、工作节点ID、序列号`）。由于序列号占据ID的最高位，且生成的序列号在区间[0, 1023]内单调递增，因此能保证生成的ID快速占据大范围的数值，并被MongoDB分为多个Chunks负载均衡存储在不同的MongoDB服务端内。
