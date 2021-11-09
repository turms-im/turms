# 集群的设计与实现

Turms的集群代码实现比较清晰，也很容易理解。代码实现包为：`src/main/java/im/turms/server/common/cluster`；配置包为：`src/main/java/im/turms/server/common/property/env/common/cluster`

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

* 尽最大努力，保证当前节点注册在服务注册中心中
* 监听服务注册中心的节点增删改事件，以通知“网络连接服务”去连接或断开对应的TCP连接
* 选举Leader

#### Leader选举

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

TODO

#### 背压

turms-gateway服务端对turms-service服务端的背压实现（TODO，暂未实现）比较取巧，具体而言：每个节点都会根据当前节点的CPU与内存负载状态，计算出当前节点的“可服务值”（区间为[0, 100]），并通过“服务注册中心”向其他节点同步该信息。turms-gateway会根据已知的turms-service的“可服务值”按权重比例分发RPC请求。如果turms-gateway发现当前所有turms-service的“可服务值”均小于10，则不再进行RPC下发，而是直接抛出异常。

### 分布式ID生成服务（IdGen）

服务类：`im.turms.server.common.cluster.service.idgen.IdService`

分布式ID生成器用于为各业务场景快速提供集群唯一的ID。生成一个集群唯一的ID只需要节点进行本地运算操作（具体代码：`im.turms.server.common.cluster.service.idgen.SnowflakeIdGenerator#nextLargeGapId`），效率极高。

#### 原理

Turms的分布式ID生成器基于主流的[雪花ID算法](https://en.wikipedia.org/wiki/Snowflake_ID)实现，生成的ID是`long`数据类型，具体而言：

* 最高位（1 bit）始终为0，表示正数
* 41 bits表示以毫秒为单位的时间戳，可表示约69年时间。具体UTC时间区间为：`[2020-10-13, 2090-06-19]`。`2020-10-13`为硬编码的Epoch时间，如果您想修改该时间，修改`im.turms.server.common.cluster.service.idgen.SnowflakeIdGenerator#EPOCH`的值即可
* 4 bits表示数据中心ID，ID区间为[0, 15]。在实际运用中，该ID通常以`云服务中的区域`划分，即每个区域都有一个ID。Turms会根据节点的`im.turms.server.common.property.env.common.cluster.NodeProperties#zone`“区域名”，自动将区域名映射为[0, 15]区间中的值。注意：如果有16个以上的区域名，虽然这些区域名仍会被映射为[0, 15]区间中的值，但这也意味着会出现重复的数据中心ID，有集群节点生成相同ID的风险。并且，被降级处理的节点会打印警告日志，提醒生成相同ID的风险。
* 8 bits表示工作节点ID，ID区间为[0, 255]。Turms会根据节点的`im.turms.server.common.property.env.common.cluster.NodeProperties#zone`“区域名”，自动将区域名映射为[0, 255]区间中的值。注意：如果在一个数据中心中有256个以上的节点，虽然这些节点ID仍会被映射为[0, 255]区间中的值，但这也意味着会出现重复的工作节点ID，有集群节点生成相同ID的风险。并且，被降级处理的节点会打印警告日志，提醒生成相同ID的风险。
* 10 bits表示序列号。在单位时间戳字段内（1毫秒）可表示至多1024个序列号，即1毫秒中最多可生成1024个唯一ID。注意：换言之，1秒内至多可以表示1024000个唯一ID，因此在实际使用中，是不可能出现重复ID的情况。

补充：根据节点信息，更新数据中心ID与工作节点ID信息的代码在：`im.turms.server.common.cluster.service.idgen.IdService#IdService`的`addOnMembersChangeListener`中

#### 变种实现

具体实现：`im.turms.server.common.cluster.service.idgen.SnowflakeIdGenerator#nextLargeGapId`

常规雪花算法生成的ID是递增的，但在大部分情况下，Turms的业务实现采用的是大间距ID，以避免ID单调递增。这么做是因为：使用大间距ID，以保证当这些数据存储到MongoDB数据库时，MongoDB能够根据这些ID，生成足够多的Chunks，并将这些Chunks负载均衡分配给各MongoDB服务端，让其进行存储。而单调递增ID会导致所有新数据始终分配到唯一的热点MongoDB服务端，导致数据库的负载均衡失效。

大间距ID的实现也很简单，仅仅是把各字段进行重排，具体顺序为：`序列号、时间戳、数据中心ID、工作节点ID`（常规雪花算法的ID顺序为`时间戳、数据中心ID、工作节点ID、序列号`）。由于序列号的区间为[0, 1023]
