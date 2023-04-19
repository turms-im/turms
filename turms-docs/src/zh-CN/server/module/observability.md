# 可观测性体系

为了实现系统的高可靠、让系统具备容量可预估与异常可排查（如检测DDoS攻击）的能力，系统的可观测性体系建设至关重要。一个服务端如果没有对可观测性体系提供支持，那无论其功能有多么丰富，也只是玩具项目。

并且，在可观测性体系下生成的衍生产物也是企业的一项重要资产，企业经营者如果无视可观测性体系的建设，就无法有效分析用户行为与喜好，经营策略的优化就更无从谈起，同时也意味着企业放弃了一笔可观的财富。

Turms与其他常规服务端一样，将可观测性的具体实现分为三类，即：`度量（可聚合数值）`、`日志（事件）`、`链路追踪（面向请求）`。

## 度量

度量由可聚合数值构成，总体分为`系统度量`、`应用度量`与`业务度量`。系统度量用于观察系统或容器的运行状态与趋势；应用度量用于观察JVM与Turms应用层的运行状态和趋势；业务度量用于观察业务发展的状态和趋势。在默认不落盘采样的情况下，只占用极小部分的内存空间。

另外，在具体的代码实现上，Turms的度量体系基于主流度量采样库[Micrometer](https://github.com/micrometer-metrics/micrometer)来实现。并提供接口`/metrics`来导出JSON格式，`/metrics/prometheus`来导出OpenMetrics格式，`/metrics/csv`来导出CSV格式。

注意：还有一类实现起来相对耗系统资源的统计数据，诸如日活/周活/月活，用户留存率等，这些功能的实现都很常规。但这类相对高纬度的功能适合专门的日志服务或产品来实现，故Turms不直接提供该类数据。

### 系统度量

云服务厂商也都有提供该类度量，并且其度量点通常更丰富，存储、展示与分析等功能也是开箱即用。Turms提供以下重要度量主要是尽一个服务端应尽的责任，满足不上云用户以及部分用户的定制化需求。对于能使用云服务的用户，应该优先考虑使用云服务。

| 类别                | 名称                     | 类型      | 含义                     |
| ------------------- | ------------------------ | --------- | ------------------------ |
| Uptime（运行时间）  | process.uptime           | TimeGauge | 进程已运行时长           |
|                     | process.start.time       | TimeGauge | 进程启动时间             |
| Processor（处理器） | system.cpu.count         | Gauge     | 进程可用CPU核数          |
|                     | system.load.average.1m   | Gauge     | 最近一分钟系统CPU负载    |
|                     | system.cpu.usage         | Gauge     | 最近系统CPU使用率        |
|                     | process.cpu.usage        | Gauge     | 最近进程CPU使用率        |
| Memory（内存）      | system.memory.total      | Gauge     | 系统物理内存大小         |
|                     | system.memory.free       | Gauge     | 系统可用物理内存大小     |
|                     | system.memory.swap.total | Gauge     | 系统Swap内存大小         |
|                     | system.memory.swap.free  | Gauge     | 系统可用Swap内存大小     |
| Storage（存储）     | disk.total               | Gauge     | 总存储容量               |
|                     | disk.free                | Gauge     | 可用存储容量             |
| FileDescriptor      | process.files.open       | Gauge     | 打开的文件描述符数       |
|                     | process.files.max        | Gauge     | 可打开的最大文件描述符数 |

### 应用度量

#### JVM度量

以下基于HotSpot虚拟机进行含义描述，Turms不对其他虚拟机提供官方支持。

| 类别   | 名称                      | 类型    | 含义                                                         |
| ------ | ------------------------- | ------- | ------------------------------------------------------------ |
| GC     | jvm.gc.max.data.size      | Gauge   | 老年代最大可用堆内内存                                       |
|        | jvm.gc.live.data.size     | Gauge   | GC后，老年代占用的内存空间                                   |
|        | jvm.gc.memory.allocated   | Counter | Eden区一共被分配的内存空间                                   |
|        | jvm.gc.memory.promoted    | Counter | 老年代一共被分配的内存空间                                   |
|        | jvm.gc.pause              | Timer   | GC耗时                                                       |
| Memory | jvm.buffer.count          | Gauge   | 各内存缓冲区池内，内存缓冲区的个数                           |
|        | jvm.buffer.memory.used    | Gauge   | 各内存缓冲区池的已使用内存<br />注意：Turms应用层使用的堆外内存都记录在这 |
|        | jvm.buffer.total.capacity | Gauge   | 各内存缓冲区池的总容量                                       |
|        | jvm.memory.used           | Gauge   | 各内存池的已使用内存<br />注意：Turms应用层使用的堆外内存不会被记录在这 |
|        | jvm.memory.committed      | Gauge   | 各内存池的可用内存                                           |
|        | jvm.memory.max            | Gauge   | 各内存池的最大内存                                           |
| Thread | jvm.threads.peak          | Gauge   | 峰值线程数                                                   |
|        | jvm.threads.daemon        | Gauge   | 守护线程数                                                   |
|        | jvm.threads.live          | Gauge   | 当前活跃线程数                                               |
|        | jvm.threads.states        | Gauge   | 各线程状态下的线程数                                         |
| Class  | jvm.classes.loaded        | Gauge   | 已加载classes数                                              |
|        | jvm.classes.unloaded      | Counter | 已卸载classes数                                              |

注意：Turms在进行网络IO操作时，使用的都是内存池中的堆外内存（即通过Netty的PooledByteBufAllocator分配堆外内存），通过故意不释放堆外内存，并将这些堆外内存缓存起来，来避免低效的堆外内存分配与释放操作，因此Turms的内存占用率会持续走高，并且总体没有下降趋势。这不是内存泄漏，只是Turms在缓存这些堆外内存。

#### 集群间TCP连接度量

在连接度量中，因为服务端的节点数有限，所以每个度量都会把TCP端的远程地址作为tag，来区分每个TCP端各自的度量数据，以更细致地观察节点之间的通信情况。

##### TCP服务端

| 类型                     | 名称                                     | 类型                | 含义             |
| ------------------------ | ---------------------------------------- | ------------------- | ---------------- |
| Connection（连接）       | turms.node.tcp.server.data.received      | DistributionSummary | 已接收字节数     |
|                          | turms.node.tcp.server.data.sent          | DistributionSummary | 已发送字节数     |
|                          | turms.node.tcp.server.errors             | Counter             | 连接异常触发次数 |
|                          | turms.node.tcp.server.tls.handshake.time | Timer               | TLS握手用时      |
| ByteBufAllocator（内存） | TODO                                     |                     |                  |

##### TCP客户端

| 类型                     | 名称                                     | 类型                | 含义             |
| ------------------------ | ---------------------------------------- | ------------------- | ---------------- |
| Connection（连接）       | turms.node.tcp.client.data.received      | DistributionSummary | 已接收字节数     |
|                          | turms.node.tcp.client.data.sent          | DistributionSummary | 已发送字节数     |
|                          | turms.node.tcp.client.errors             | Counter             | 连接异常触发次数 |
|                          | turms.node.tcp.client.tls.handshake.time | Timer               | TLS握手用时      |
|                          | turms.node.tcp.client.connect.time       | Timer               | TCP连接建立用时  |
|                          | turms.node.tcp.client.address.resolver   | Timer               | 地址解析用时     |
| ByteBufAllocator（内存） | TODO                                     |                     |                  |

#### RPC度量

| 名称                      | 类型    | 含义                      |
| ------------------------- | ------- | ------------------------- |
| rpc.request.subscribed    | Counter | 某类型RPC请求的已处理次数 |
| rpc.request.flow.duration | Timer   | 某类型RPC请求的处理时长   |

#### Admin API度量

因为管理员的IP可以无限多，所以每个度量**不会**把对端的远程地址作为tag，来区分每个端各自的度量数据。

| 类型               | 名称                         | 类型                | 含义             |
| ------------------ | ---------------------------- | ------------------- | ---------------- |
| Connection（连接） | admin.api.data.received      | DistributionSummary | 已接收字节数     |
|                    | admin.api.data.sent          | DistributionSummary | 已发送字节数     |
|                    | admin.api.errors             | Counter             | 连接异常触发次数 |
|                    | admin.api.tls.handshake.time | Timer               | TLS握手用时      |

#### Turms客户端度量

在连接度量中，因为客户端的数量无限多，所以每个度量**不会**把对端的远程地址作为tag，来区分每个端各自的度量数据。另外，连接度量通过tag `uri`来区分TCP/UDP/WebSocket三类连接各自的度量数据。

| 类型                         | 名称                                    | 类型                | 含义                         |
| ---------------------------- | --------------------------------------- | ------------------- | ---------------------------- |
| Connection（连接）           | turms.client.network.data.received      | DistributionSummary | 已接收字节数                 |
|                              | turms.client.network.data.sent          | DistributionSummary | 已发送字节数                 |
|                              | turms.client.network.errors             | Counter             | 连接异常触发次数             |
|                              | turms.client.network.tls.handshake.time | Timer               | TLS握手用时                  |
|                              | turms.client.network.connect.time       | Timer               | 连接建立用时                 |
|                              | turms.client.network.address.resolver   | Timer               | 域名解析用时                 |
| Request（请求）              | turms.client.request.subscribed         | Counter             | 某类型客户端请求的已处理次数 |
|                              | turms.client.request.flow.duration      | Timer               | 某类型客户端请求的处理时长   |
| ConnectionProvider（连接池） | TODO                                    |                     |                              |
| ByteBufAllocator（内存）     | TODO                                    |                     |                              |


### 业务度量

| 服务端        | 名称            | 类型    | 含义         |
| ------------- | --------------- | ------- | ------------ |
| turms-gateway | user.logged_in  | Counter | 登录用户数   |
|               | user.online     | Gauge   | 在线用户数   |
| turms-service | user.registered | Counter | 注册用户数   |
|               | user.deleted    | Counter | 注销用户数   |
|               | group.created   | Counter | 创建群组数   |
|               | group.deleted   | Counter | 注销群组数   |
|               | message.sent    | Counter | 已发送消息数 |

## 日志

每条日志都对应着Turms服务端运行时发生的事件，用于追踪系统的运行状态与生成高纬度的统计数据。Turms中的日志分类两大类，即`应用日志`与`业务日志`。应用运行日志本身数量不多，占用空间不大，遵循精与准原则。但为业务分析而设计的客户端API访问日志则不同，它是大部分统计数据的基础数据，是企业的重要资产，因此Turms默认且推荐对其进行100%采样，存储消耗巨大。

注意

* Turms的所有日志、度量与链路追踪的数据格式设计，都是兼顾“简单快捷，方便快速查询”与“精准采样，方便日志服务分析”设计的，但Turms本身不提供任何日志分析功能。

* Turms的日志时间戳与日志切割都是根据UTC时间，而非系统时间。

* 当Turms出现`FATAL`级别的日志时，需要人工介入修复。目前已有的`FATAL`级别日志类型有：

  * 检测到数据库的表被删除，或被重命名。

  * 检测到存储日志的文件系统已满，无法继续打印日志。

    注意：当检测到文件系统已满时，Turms就已经无法继续打印日志了，因此在用户没有腾出足够空间之前，Turms其实是不会打印这条`FATAL`级别的日志的。Turms之后会对这点进行优化，以保证该日志能及时地被打印出来。当然，由于现在的系统都配备了监控系统，因此运维人员在接到存储空间超过自定义阈值的警告时，就应该事先进行处理了。

* Turms会不断地打印日志，并将日志打印成文件，以存储在文件系统当中。当文件系统存储空间不足时，**Turms服务端会停止打印日志**，但不会丢弃日志，而会将日志堆积在内存当中，所以当内存中堆积的日志过多而导致内存不足时，**又会触发Turms服务端的自动保护机制，拒绝所有的用户请求**，以避免Turms服务端因为内存不足而宕机。所以运维人员务必要保证Turms服务端所在的系统时刻有足够的存储空间。

  拓展阅读：[Turms服务端的内存健康检测机制](https://turms-im.github.io/docs/zh-CN/server/module/system-resource-management#%E5%86%85%E5%AD%98%E5%81%A5%E5%BA%B7%E6%A3%80%E6%B5%8B)

### 自研实现（拓展知识）

#### 原因

1. Turms默认且非常推荐对客户端API进行100%采样，需要Logging的实现高效
2. 第三方Logging实现过于冗余，性能低下且内存占用高
3. 避免第三方Logging的开发人员由于缺乏安全常识，写出类似[Remote code injection in Log4j](https://github.com/advisories/GHSA-jfh8-c2jp-5v3q)的Critical bug
4. Turms的日志实现通过“几乎什么功能都没实现”，并且实现了的功能也照着几乎最高性能标准实现（我们直接将Java的基础数据写入`DirectByteBuf`，并直接写入文件描述符，不存在字符串拷贝），因此该实现的吞吐量能比log4j2 async logger高数倍，同时内存开销小数倍

#### 具体实现

Turms日志实现非常精简，大概只实现了标准日志库的百分之几的核心功能，打印日志的主要步骤为：

对于常规日志：

   * 调用`im.turms.server.common.infra.logging.core.logger.AsyncLogger#doLog`函数
   * `doLog`函数内部通过`PooledByteBufAllocator.DEFAULT`分配一块堆外内存，并遍历一遍message，将非占位符直接写入该内存，跳过占位符并写入具体参数，最后将这块内存放到日志处理的MPSC队列中（基于jctools的`MpscUnboundedArrayQueue`）
   * 日志处理线程检测到有新的日志（即`ByteBuffer`对象）时，会将该堆外内存写入NIO包的`FileChannel`（可以是控制台、也可以是文件）中，该对象在Linux系统下，会最终调用`pwrite`直接将堆外内存写入文件描述符中

对于各种API日志（如客户端API日志），我们采用了更为定制的实现，即：

* 调用方直接将API信息（如客户端IP、请求大小等）写入`DirectByteBuf`中，并将这个Buffer传递给`AsyncLogger#doLog`函数
* `doLog`函数将日志通用的模板信息（如时间戳、节点ID等）写入另一个`DirectByteBuf`，并与上述的`DirectByteBuf`，拼接成一个`CompositeByteBuf`
* 日志处理线程检测到有新的日志（即`CompositeByteBuf`对象）时，会将该堆外内存写入NIO包的`FileChannel`（可以是控制台、也可以是文件）中，该对象在Linux系统下，会最终调用`pwrite`直接将堆外内存写入文件描述符中

理所当然的，Turms写日志的性能能达到极致。

补充

* 虽然还有更高效的写法，即跨过Java实现，不使用NIO包的`FileChannel`，而是直接调用底层JNI实现，如在Linux操作系统下，直接通过Linux的`pwrite`将`DirectByteBuffer`写入文件描述符中。但考虑到代码的可维护性，且Java默认不开放这些底层函数，故不采纳该写法。

* 上述中提到的内存都是通过`PooledByteBufAllocator.DEFAULT`分配的，且没限制内存使用上限，并且“敢”用`MpscUnboundedArrayQueue`存储日志，而没限制最大容量。这是因为Turms服务端自己有一套[内存管理机制](https://turms-im.github.io/docs/zh-CN/server/module/system-resource-management)，它能保证内存使用的上限，同时又让使用了的内存逐步释放。

* Turms不支持且未来也不会支持：添加控制台文本样式。因为给控制台文本加样式需要使用`ANSI escape codes`，而日志文件不需要存储这些字符，因此若要实现该功能，我们需要给控制台与日志文件分别维护一个ByteBuf，一条日志需要消耗双倍的内存，故不考虑该实现。

  另外，开发者可以自行使用第三方工具或插件，如`Intellij IDEA`的`Grep Console`插件，给Turms服务端控制台的日志添加样式。

* 关于“为什么打印非ASCII字符时，会出现乱码”，这是因为：

  背景：
  
  * Java 17 `String`类内部的`byte[] value`有且仅会存储`LATIN-1`或`UTF-16`编码的数据
  * **Turms服务端自身有且仅打印ASCII字符**（Turms服务端不会打印任何用户或管理员输入的文本）
  * 日志打印这种频繁使用的功能，无意义的内存拷贝是绝对禁止的。
  
  在上述背景下，Turms在打印`String`时，并不是通过`getBytes("UTF-8")`取其字节数据，而是通过`Unsafe`直接获取`String`的内部`LATIN-1`或`UTF-16`编码的字节数据，因此日志文件可能是`LATIN-1`与`UTF-16`混合编码。
  
  而当用户以`UTF-8`编码查看日志文件时，`LATIN-1`编码中的ASCII字符可以正确显示，`UTF-16`编码中的ASCII字符也能显示，只是每个ASCII字符会多带上一个空字符（二进制编码`0000 0000`），对于其他编码不兼容的字符，则会以乱码形式显示，因此如果Turms服务端打印了非ASCII字符，则用户会看到乱码。
  
  另外，除非Java未来支持存储UTF-8编码的字节数据，否则Turms服务端不会考虑使用`getBytes("UTF-8")`这样低效的实现。

综上补充内容，也再次验证了我们在各篇章中反复提及的：“功能多”对于追求性能表现的服务端而言，很可能是缺点。


### 不使用JSON格式的原因

随着微服务的发展，JSON格式日志逐渐流行，比如MongoDB就在4.4版本时开始支持JSON格式日志。使用JSON格式主要有以下三大优点：

* 极大地统一了各服务端的日志格式。尤其对于具有数十/百/千个异构服务端的公司而言，是必须强制要求各项目使用JSON日志格式的
* 各编程语言均对JSON有良好支持，日志打印与解析几乎无难度可言
* 各云厂商的日志服务对JSON格式日志都有着良好的支持，可以实现开箱即用

Turms服务端不使用JSON格式的原因是：

* Turms服务端构成很简单，不需要通过JSON来统一日志格式。
* JSON序列化需要占用额外内存与CPU资源，且存储开销大，如果使用压缩技术，还要额外占用CPU资源。特别是，序列化加上压缩时所需的CPU资源甚至比Turms服务端处理业务请求所需CPU资源还高，这对Turms来说是难以接受的。
* JSON格式其实在原始数据可读性上并不好。因为原始日志是以单行形式进行展示，一行即表明一个事件。JSON格式在单行显示时，会带来大量“噪音”，大量的JSON元数据、JSON键与JSON值纵横交错，直接阅读原始数据的话就比较费力。而Turms服务端的客户端API访问日志通过`|`分隔符拆分各字段。用户初次只需要多看几个日志，之后就能反应出各字段是代表什么信息。

当然，采用传统的单行格式会造成云服务解析相对复杂，且配置不灵活。但考虑到这种东西配一次即一劳永逸，综合考虑以上情况，Turms服务端日志不采用JSON格式，而仍采用传统的单行格式。

### 类别

#### GC日志

用于JVM性能测试、分析调优、排查定位问题。

turms-gateway的服务端JVM GC配置为：`-Xlog:gc*,gc+age=trace,safepoint:file=${TURMS_GATEWAY_HOME}/log/turms-gateway-gc.log:utctime,pid,tags:filecount=32,filesize=32m`

turms-service的服务端JVM GC配置为：`-Xlog:gc*,gc+age=trace,safepoint:file=${TURMS_SERVICE_HOME}/log/turms-service-gc.log:utctime,pid,tags:filecount=32,filesize=32m`

#### 服务端运行日志

描述Turms服务端内发生的主要事件，如RPC连接状态的转变、请求处理中服务端错误的发生等。

文件名：`turms-gateway.log`（turms-gateway服务端）；`turms-service.log`（turms-service服务端）

构成：事件发送时间、日志等级、服务端类型、节点ID、Trace ID、线程、类、消息。其中，服务端信息的主要作用是在分布式日志采集过程中，用于区分日志的来源节点。其他类型日志也都使用这样的日志格式（除了客户端API访问日志与通知日志不记录“类”信息），它们只是在“消息”部分使用了定制化的消息格式。

格式：`%d{${sys:LOG_DATEFORMAT_PATTERN}}{GMT+0} ${sys:LOG_LEVEL_PATTERN} ${myctx:NODE_TYPE} ${myctx:NODE_ID} %-19.19X{traceId} %t %-40.40c{1.} : %m%n${sys:LOG_EXCEPTION_CONVERSION_WORD}`

解析Regex：`(?P<time>\d{4}-\d{2}-\d{2}\s\d{1,2}\:\d{2}\:\d{2}\.\d{3})\s+(?P<level>[A-Z]{4,5})\s+(?P<node_type>[A-Z])\s+(?P<node_id>\S*)\s+\[(?P<trace_id>.{19})\]\s+(?P<thread>\S*)\s+(?P<class>\S*)\s+:\s(?P<msg>.*)`

示例：

```spreadsheet
2021-08-08 09:52:15.602 ERROR S idanvacg 6404110606919452669 AsyncGetter-1-thread-1 i.t.s.c.c.s.r.RpcService                 : Cannot send response to disposed connection: ServiceResponse{dataForRequester=null, code=SERVER_INTERNAL_ERROR, reason='The pool is closed'}
2021-08-08 14:02:53.123  INFO S xyzjjrhv                     parallel-2 i.t.s.c.c.s.c.ConnectionService          : [Client] Connecting to member: fqfgnyop[192.168.3.2:7511]. Retry times: 0
```

#### Admin API访问日志（审计日志）

记录管理员对Turms服务端的各种操作。

文件名：`turms-service-admin-api.log`

格式：`管理员账号|管理员IP|请求ID|请求时间|请求API|请求参数|处理结果|处理时间|处理异常信息`。其中：

* 会话信息：管理员账号、管理员IP
* 请求信息：请求ID、请求时间、请求API、请求参数。其中，管理员可以通过HTTP响应中的Header `X-Request-ID`获得`请求ID`，并配合日志来进行故障排查或行为追踪
* 响应信息：处理结果、处理时间、处理异常信息

示例：

```spreadsheet
2021-09-02 07:19:27.219  INFO S wzocsebz 3501287524626242885 Thread-28 : turms|0:0:0:0:0:0:0:1|db612e82-199|2021-09-02 07:30:30.414|updateUser|1|{ids=[1], updateUserDTO=UpdateUserDTO[password=******, name=null, intro=null, profileAccess=null, permissionGroupId=null, registrationDate=null, isActive=null]}|TRUE|
```

#### 客户端API访问日志

由于客户端API访问日志数据是企业的重要资产，因此再次强调：该日志看似简单常规，但其衍生出的运营数据可以高达上百项，既是企业的宝库，也是指引产品发展方向的灯塔。宁可因为100%采样落盘导致服务端吞吐量大减，也不建议您修改相关配置。除非您明确知道且能承受修改参数后会带来的后果。

##### turms-gateway服务端

文件名：`turms-gateway-client-api.log`

格式：`会话ID|用户ID|设备|版本|IP|请求ID|请求类型|请求大小|请求时间|响应状态码|响应数据类型|响应大小|处理时间`。其中：

* 会话信息：会话ID、用户ID、设备、版本、IP
* 请求信息：请求ID、请求类型、请求大小、请求时间
* 响应信息：响应状态码、响应数据类型、响应大小、处理时间

示例：

```spreadsheet
2021-08-17 13:21:10.082  INFO G ocnpinxk 4073578036035627538 gateway-tcp-worker-18-2 : 1669286372|100|DESKTOP|1|0:0:0:0:0:0:0:1|6275734689527119988|CREATE_GROUP_MEMBER_REQUEST|32|2021-08-17 13:21:10.079|1201||21|3
2021-08-17 13:21:10.086  INFO G ocnpinxk 8485909300068121199 gateway-tcp-worker-18-1 : 315622910|101|DESKTOP|1|0:0:0:0:0:0:0:1|8981788720014999664|QUERY_GROUP_JOIN_REQUESTS_REQUEST|17|2021-08-17 13:21:10.082|1201||21|4
2021-08-17 13:21:10.087  INFO G ocnpinxk 195568170846055794  gateway-tcp-worker-18-2 : 1669286372|100|DESKTOP|1|0:0:0:0:0:0:0:1|7875023820838742819|CREATE_GROUP_JOIN_QUESTION_REQUEST|181|2021-08-17 13:21:10.083|1201||21|4
```

##### turms-service服务端

文件名：`turms-service-client-api.log`

格式：`用户ID|设备|IP|请求ID|请求类型|请求大小|请求时间|响应状态码|响应数据类型|处理时间`。其中：

* 会话信息：用户ID、设备、IP
* 请求信息：请求ID、请求类型、请求大小、请求时间
* 响应信息：响应状态码、响应数据类型、处理时间

示例：

```spreadsheet
2021-08-17 13:25:11.809  INFO S lkumxlpd 1650561895646191481 Thread-13 : 101|DESKTOP|::1|6798130843268792999|QUERY_MESSAGES_REQUEST|28|2021-08-17 13:25:11.807|1001||2
2021-08-17 13:25:11.809  INFO S lkumxlpd 2979813149711907727 Thread-9 : 100|DESKTOP|::1|5095384146247218867|QUERY_GROUP_JOIN_QUESTIONS_REQUEST|17|2021-08-17 13:25:11.807|1002||2
2021-08-17 13:25:11.809  INFO S lkumxlpd 7231219143674352809 ver-worker-14-1 : 101|DESKTOP|::1|358075665001342897|QUERY_SIGNED_GET_URL_REQUEST|40|2021-08-17 13:25:11.809|6000||0
```

补充：

* 在Turms服务端的客户端API访问日志中，一个请求的“开始时间”实际指的是“服务端成功接收一个请求所包含的数据流，但尚未进行解析”这一时刻，而非“服务端接收到请求的第一个字节”这一时刻。
* 请求的执行是异步的。假设一个请求执行时间是1秒，但其占用Turms服务端的CPU时间可能就只有几毫秒，其他时间CPU都在处理其他请求，不会出现CPU闲置等待的情况。


##### 特殊请求日志处理（拓展知识）

在日志方面，最为特殊API请求是`删除会话请求`。具体体现在：

删除会话请求是唯一一个可以不由用户发出，但却被记录在客户端API访问日志的请求。具体会发送在：如果客户端在没发送“删除会话请求”之前，就断开了底层TCP连接，那么对应的turms-gateway就会在TCP连接关闭之时，主动生成一条效果与“删除会话请求”一样的日志，通过这种方式保证客户端API访问日志的逻辑一致性。

另外，在客户端的实现中，除非开发者指定通过`DeleteSessionRequest`进行会话关闭，默认情况下客户端会直接关闭TCP连接来关闭上层会话。当前的`DeleteSessionRequest`其实是起“占位符”的作用，一是通过“请求”这一模型保持业务逻辑处理的一致，二是为了预留给未来做更灵活的关闭会话逻辑。

#### 通知日志

部分客户端请求与管理员API请求会触发对其他用户的通知，如“正在输入”与“添加好友”通知。该日志用于该类通知事件。

补充：

* `通知日志记录`与`客户端API访问日志记录`可以一一对应起来。具体而言，可以通过通知日志记录中的`Trace ID`或`Request ID`字段将二者关联。
* 通知的发起操作只会由turms-service执行。turms-service通过`SendNotificationRequest`这一RPC请求，将通知操作代理给turms-gateway，让其进行实际的通知下推操作

##### turms-gateway服务端

文件名：`turms-gateway-notification.log`

格式：`通知触发用户ID|发送状态|通知目标用户数|会话关闭状态码|通知大小|通知转发的请求类型`。其中：

* 通知触发用户信息：通知触发用户ID
* 通知接收用户信息：通知接收用户数量、在线的通知接收用户数量
* 通知信息：会话关闭状态码、通知大小
* 通知转发的请求信息：通知转发的请求类型

示例：

```spreadsheet
2021-09-03 00:08:22.537  INFO G hkivjeav 3166178398923546492 -client-io-15-3 : 149|1|1||75|UPDATE_FRIEND_REQUEST_REQUEST
2021-09-03 00:08:37.636  INFO G hkivjeav 8332948877634499289 -client-io-15-3 : 190|1|0||19|UPDATE_TYPING_STATUS_REQUEST
```

##### turms-service服务端

文件名：`turms-service-notification.log`

格式：`通知触发用户ID|发送状态|通知目标用户数|会话关闭状态码|通知大小|通知转发的请求ID|通知转发的请求类型`。其中：

* 通知触发用户信息：通知触发用户ID
* 通知接收用户信息：通知接收用户数量、在线的通知接收用户数量
* 通知信息：会话关闭状态码、通知大小
* 通知转发的请求信息：通知转发的请求ID、通知转发的请求类型

示例：

```spreadsheet
2021-09-03 00:08:22.537  INFO S hkivjeav 3166178398923546492 -client-io-15-3 : 149|1|1||75|4971734074638762694|UPDATE_FRIEND_REQUEST_REQUEST
2021-09-03 00:08:37.636  INFO S hkivjeav 8332948877634499289 -client-io-15-3 : 190|1|0||19|6469201046445182337|UPDATE_TYPING_STATUS_REQUEST
```

#### 慢日志

TODO

### 采集与分析

Turms只提供原始数据，不提供也没计划提供日志采集与分析功能。

#### 原因

* 现在云厂商都支持日志的采集、解析、存储、检索、分析报警等等高级服务。通过SQL检索，来获取各种高纬度统计数据与图表（诸如：日活、月活、日消息发送量、会话存留时长、新会话占比、留存率等等运营数据）。正是因为该方案已成为行业最佳实践之一，所以Turms自身不提供一些相对复杂、更适合大数据项目来做的功能。
* 日志收集相关技术都很常规。但从商业价值角度去合理规划什么日志应该收集，什么字段应该索引、什么日志应该实时分析、什么日志应该离线分析，这些与商业价值与成本直接挂钩的问题才是难点所在。因此在商业价值考量方面，Turms只能给建议，而非直接插手干预。
* 日志相关服务与产品百家争鸣，而Turms服务端的日志相关实现应当保持中立，因此Turms服务端自身不接入任何的SDK，只提供原始日志供日志相关服务采集。
* 从微服务职责划分的角度来看，Turms服务端的功能也不应该过于耦合。

## 链路追踪

### 作用

面向请求，用于快速追踪请求在节点之间与具体节点内的执行情况。

### 实现

在链路追踪实现规范[OpenTracing](https://opentracing.io/specification)中，其规定了要使用Trace与Span作为链路追踪的单位。但与动辄数十个、上百个甚至上千个微服务应用相比，Turms的调用链路极为简单，完全不需要通过Span信息来追踪请求。并且，如果Turms采用标准OpenTracing实现，那么很多请求的链路追踪附加信息甚至会比大部分的RPC请求正文还大。

因此，Turms仅仅是在所有日志中添加了一个用于表示`trace ID`的字段，开发者在进行链路追踪时，仅需要通过查询`trace ID`字段，即可明白该请求经过的所有节点，与在节点内的执行情况。

## 监控与报警

在可观测体系中，系统需要根据度量与日志来实时监控服务端运行状态，并在发现系统异常时进行报警通知。

Turms不提供且也没计划提供报警功能。一方面，诸如AWS CloudWatch这样的云服务或其他相关产品都提供了极为丰富、成熟且开箱即用的度量与日志的采集、分析与报警等功能。如果用户熟悉云服务产品，从头开始购买云服务并实现Turms的监控与报警，通常也只需要3~10分钟。另一方面，从微服务职责划分的角度来看，Turms服务端的功能也不应该过于耦合，没必要把这些监控报警功能都集成进来。

即便用户没有计划使用云服务端，那也可以使用诸如`Prometheus Alertmanager`这样专业且成熟的开源技术方案。如果用户熟悉相关操作，从零搭建这样的一个系统通常也只需要10~60分钟。