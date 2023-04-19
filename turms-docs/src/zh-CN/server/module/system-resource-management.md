# 系统资源管理

内存与CPU资源对服务端的重要性不言而喻，Turms各模块都比较极致地使用内存与CPU，具体可参考各模块实现的文档与代码。而在另一方面，为保证服务端的正常运行，其内部也提供了一套健康检测机制，该机制配合上层的“拒绝服务”机制，以尽最大努力保证服务端能够正常运行。

Turms提供系统资源监控配置类：`im.turms.server.common.infra.property.env.common.healthcheck.HealthCheckProperties`，来允许用户配置可用内存占用率与CPU占用率。Turms服务端的`HealthCheckManager`会持续检测可用物理内存与CPU占用率，如果检测到可用物理内存过低或CPU占用率过高，则会：

* 将自身在服务注册中心的`isHealthy`信息标记为`false`。由于RPC发送端只会从`isHealthy`为`true`的服务端中，挑选RPC的响应服务端，因此能实现类似背压的效果
* 拒绝对外提供服务。具体而言：如果是turms-gateway服务端，则拒绝新会话的建立与用户请求的处理；如果是turms-service服务端，则拒绝处理turms-gateway服务端发来的RPC请求（注意：就算处于“不健康”状态，turms-service仍然会为管理员API提供服务）

## 内存管理

### JVM基础内存知识

JVM HotSpot虚拟机的内存区域可以划分为：

* 堆内存（Heap Memory）：Eden区、Survivor区、老年代（Old Generation）

* 非碓内存（Non-heap Memory）

  * 直接内存（Direct Memory）：Direct Buffer Pool
  * JVM内部内存（JVM Specific Memory）：本地方法栈、元空间、Code Cache等

  特别注意：通过函数`java.lang.management.MemoryMXBean#getNonHeapMemoryUsage`获得的`NonHeapMemory`并不包括`Direct Buffer Pool`（直接内存缓存池）。具体而言，该函数在JDK 17中所指的内存空间为：

  * CodeHeap 'non-nmethods'
  * CodeHeap 'non-profiled nmethods'
  * CodeHeap 'profiled nmethods'
  * Compressed Class Space
  * Metaspace

参考文档：[How to Monitor VM Internal Memory](https://docs.oracle.com/en/java/javase/17/troubleshoot/diagnostic-tools#GUID-FB0581EA-2F91-4093-B2FA-46687F7AB081)

### 可控内存（Managed Memory）的使用

Turms服务端的可控内存指的是`堆内存（Heap Memory）`与`直接内存（Direct Memory）`这两块区域。

#### 堆内存

##### 实践意义

堆内存的实践意义比较容易理解，就是尽可能配置大的堆内存，以减少GC次数与`stop-the-world`事件的发生。

##### 配置

JVM默认的堆配置如下：

```
-XX:MaxRAMPercentage=75
-XX:InitialRAMPercentage=75
```

其中：

* `InitialRAMPercentage`与`MaxRAMPercentage`指定了需要reserve内存的大小，但Turms服务端访问该内存区域时仍会发生缺页异常。虽然JVM可以通过配置`AlwaysPreTouch`，将reserved内存直接转换成committed内存，来避免服务端在运行时发生缺页异常。但因为开启该选项后，服务端很难监控真正被使用了的堆内存，因此目前不推荐添加该配置。
* `InitialRAMPercentage`与`MaxRAMPercentage`设成一样的值主要是为了尽可能保证内存的连续性，避免服务端因为内存扩容与缩容，反复进行GC与`stop-the-world`操作。
* 堆内存没有配置为接近100%的值，这是为了把剩余的物理内存让给JVM自身的堆外内存（如占最大头的直接内存、CodeCache、Metaspace等）、系统内核（如维护TCP连接时的缓冲区）与边车服务（如：日志采集服务）使用。

另外，推荐生产环境不要给Turms服务端分配超过32GB内存。因为：

* 开启JVM的指针压缩技术，以减少不必要的内存占用
* 避免单个服务端承载太多负荷，在停机时减缓惊群效应，提升用户体验

#### 直接内存

下文所述的所有`直接内存`在实际代码中，都是由`PooledByteBufAllocator.DEFAULT`分配，即它们都是被Netty缓存与管理的直接内存。

##### 实践意义

直接内存的容量上限影响Turms服务端在同一时刻能够处理的客户端请求与管理员API请求的峰值

##### 主要使用方

* 网络I/O操作。如基于Netty的：第三方依赖`mongo-driver-java`与`Lettuce`等驱动；Turms服务端自身面向客户端的TCP/HTTP服务端实现。
* 日志打印。Turms自研的日志打印实现直接将Java基础数据写入直接内存块中，再将其写入文件描述符。

换言之，基本上所有需要系统内核访问的内存区域，我们都是直接使用直接内存，以避免无意义的堆内存拷贝。

注意：在Linux系统中，Turms使用的直接内存仍处于用户空间内，因此将直接内存写入设备（如网卡与硬盘）时，仍需要进行用户空间到内核空间、内核空间到设备的两次拷贝，而这两次拷贝操作是上层服务端无法避免的。

##### 生命周期

因为在Turms服务端中，直接内存的生命周期与客户端请求与管理员API请求的生命周期高度一致，一块直接内存通常只会在一个请求的部分或全部生命周期中存在。具体而言，其生命周期大体如下：

* 一个请求的生命周期开始于Netty对TCP字节流进行切割的阶段，Netty根据varint编码的header（其值表示的Payload长度），来对TCP字节流进行切割，而当这块内存被切割出来时（注意：这里没有发生内存拷贝），这块代表请求的直接内存的生命周期也就此开始了。

* 在Turms服务端将这块内存解析成具体的请求模型之后，Turms会判断该类型的请求是否需要使用代表它自己的直接内存。如果该请求的处理逻辑不需要使用这块内存，则这块内存会被马上回收回Netty的内存缓存池中。否则，诸如“转发用户消息”这样的请求需要使用这块内存，则该块内存不会被马上回收。接着Turms会对该请求进行业务逻辑处理。

* 在业务处理的过程中，可能会涉及到其他网络I/O操作（如向MongoDB/Redis发请求）或日志打印操作，这两类操作都需要从Netty管理的内存缓冲池中取出新的直接内存块，以进行MongoDB/Redis客户端请求的编码与响应解码操作、或日志打印操作。

* 等Turms服务端最终将请求响应的直接内存Flush到网卡后，除了代表日志记录的直接内存外，该过程所涉及的其他直接内存也都会被回收。

  唯一一种例外情况是：如果一个请求的直接内存需要转发给多个客户端，那么Turms会通过引用计数器将该请求的生命周期与其直接内存的生命周期分离，以保证能够将同一块直接内存转发给多个客户端，以避免内存拷贝。

  注意：
  
  1. 上文所述的`直接内存回收`并不是将内存回收给系统，而是回收回由Netty管理的内存池中，该内存并不会在这时被真正释放。
  2. 直接内存主要是通过：当Pooled ByteBuf被`release`时，Netty会检测其所属Chunk是否已闲置（使用率为0%）。如果是，则通过函数`io.netty.buffer.PoolArena#destroyChunk`真正释放该内存。

由于该生命周期的存在，堆内存与直接内存的真实使用率其实具有关联性。堆内存的增长主要是因为Turms服务端接收到了客户端请求或管理员API请求后处理的一系列逻辑。而在一过程中，直接内存的使用率增高是因为请求的解码与响应的编码、逻辑中的网络I/O操作的编解码与日志打印。当请求的生命周期结束时，堆内存与直接内存也就都可以被回收了。

### 内存健康检测

#### 配置

配置类：`im.turms.server.common.infra.property.env.common.healthcheck.MemoryHealthCheckProperties`

如上文所述，要想让运维人员准确评估服务端应该使用多少内存其实是非常困难，甚至不现实的事，尤其是一些关键系统内核（如TCP连接）所占内存是动态变化的，因此`MemoryHealthCheckProperties`除了提供诸如`maxAvailableMemoryPercentage`与`maxAvailableDirectMemoryPercentage`这样限定Turms服务端可使用内存上限的配置，同时也提供了`minFreeSystemMemoryBytes`这一配置，让Turms服务端能够实时检测系统的可用物理内存，并尽最大努力预留这些内存出来。

#### 内存监控实现——MemoryHealthChecker

作用：

* 检测到系统物理内存不足时，通知上层服务拒绝处理用户会话与请求，以尽最大努力保证不会耗尽物理内存，并避免使用Swap内存
* 如果检测到系统物理内存不足时，且已用堆内存超过`heapMemoryGcThresholdPercentage`，则调用`System.gc()`来建议JVM进行Full GC

特别注意

* 如上文所述，直接内存的生命周期与请求的生命周期高度一致，因此就算`MemoryHealthChecker`检测到了`已用总内存已经超过XX`，它也不会主动尝试去释放直接内存，而是等待Netty内部的内存管理机制对其进行释放
* 综上，尽管Turms服务端会尽最大努力不去耗尽物理内存，但对于极端突发的大量请求，Turms服务端还是有可能会耗尽物理内存，此时会采用Swap内存。如果Swap内存被系统关闭或Swap内存不足，则Turms服务端将直接抛出`OutOfMemoryError`异常。因此我们可以把使用Swap内存当作最后一道防线，故非常不推荐在生产环境中关闭Swap内存。

### 关于Valhalla项目——Codes like a class, works like an int

Java的内存占用一直为人所诟病，诸如一个Integer对象所存放的对象头所需的内存（在64位系统且开启了压缩指针的情况下，为12字节）大于实际int数据数倍，也因为这样的设计缺陷，导致编程时还需要一些变通手段，如在使用`Integer`对象时，JVM会优先使用`java.lang.Integer.IntegerCache`类里的对象缓存。相比很多追求性能优化（甚至是寄存器级别的优化）的C++服务端项目（如Nginx、Redis），由于Java自身的设计缺陷与保守，Java对内存的浪费就让人感觉有些“自暴自弃”了，并且更糟糕的是：这样的精神也传导给了整个Java生态圈。通过阅读源码，能发现很多知名Java项目也是“功能能用，代码写着舒服，性能差不多就行，反正JVM会帮忙GC”的态度，诸如可以很容易做Cache的地方不Cache、基础数据结构乱用、反复内存拷贝（如最常见的`String`与`StringBuilder`在实践中，通常来来回回拷贝很多次，源码让人触目惊心），只有诸如Netty这样极个别项目会有性能优化与精益求精的意识，关于这点我们已经在其他章节重点讲解了，故不赘述。

而Valhalla项目对现有的Java Object体系进行了重构。原有的`Object`在新的Java体系中叫做`IdentityObject`，而新体系下的`Object`则成了`IdentityObject`与`ValueObject`的父类（注意：Valhalla团队尚未定稿，因此概念可能还会变），二者有些类似于C#的`Reference types`与`Value types`。其中`ValueObject`下分两大类，即`primitive class`与`value class`。`primitive class`可以让开发者自定义性能如Java传统八大基础类型一样高效的数据结构，无需对象头、访问时无需通过指针查找、栈上分配，自然也无需进行GC，同时这些类也能声明字段并定义函数。而Java传统的八大基本类型也将基于新的对象体系重新进行设计，如`int`这样的`primitive type`将成为`primitive class`（`primitive class`是`value class`的一种类型，其值不可为`null`），而其`包装类（Wrapper Class）` `Integer`与可能会支持的`int.ref`将成为`value class`（值可为`null`），因此未来也不会有`包装类`这一概念了。

举例来说，类`primitive class Point { private double x; private double y; }`的primitive实例对象只需占用2个double的字节，即16字节，无需对象头。

等Valhalla项目发布Preview版本后，我们将引入`ValueObject`，并改造诸如DTO对象与各种包装类（如`Date`与`ByteArrayWrapper`）等代码实现，以极大地减少内存开销与对象数量并加快GC速度。并且由于我们已等待该项目数年，非常熟悉其设计，故可在一周内完成适配与测试工作。这也是我们会为`Preview`特性开绿灯的唯一特性。

补充：

* 其实Java的发展历程也印证了我们谈到过的“[IM功能丰富要付出致命的代价](https://turms-im.github.io/docs/zh-CN/design/schema#%E5%8A%9F%E8%83%BD%E4%B8%B0%E5%AF%8C%E7%9A%84%E8%87%B4%E5%91%BD%E4%BB%A3%E4%BB%B7)”的观点，即一个项目引以为傲的特性，其背后可能藏着万丈深渊。

  Java曾引以为傲的`Everything is an object`，并强调`Java has no structures or unions as complex data types. You don't need structures and unions when you have classes`（引用自Sun公司在1995年发布的Java白皮书：[Simple, Object Oriented, and Familiar](https://www.stroustrup.com/1995_Java_whitepaper.pdf)）来宣传Java远比C与C++简单易用。

  （额外补充：纵观Java的发展史，开发者也会感叹因Java能够不断顺应时代发展，调整自身发展方向，过五关斩六将而展现出来的强大生命力）

  但在当今的编程实践中，提倡“万物皆对象”而不提供`structure`更像是诅咒，诸如当我们将一个`int`放进一个`List<Integer>`时，还需要`new`一个新对象，徒增对象头。换言之，只要我们使用了Java提供的`List`与`Map`等常用数据结构，就得白白浪费非常多的内存，而这些集合类在实际项目中又是无法避免的，它就像诅咒一样挥之不去（补充：其实诸如`HashSet`与`LinkedList`的内部数据结构比很多开发者能想象到的内存浪费还要浪费，对象头占的内存比实际数据占的还多，也因此我们看其源码时会使用“触目惊心”来评价）。

  如今，Valhalla项目希望通过引入`primitive/value class`语言特性来改变这现状，但因为其既要向前兼容庞大的Java生态，又要让Java摆脱传统`万物皆对象`的诅咒，导致Valhalla项目的发展如履薄冰，光是设计稿就推翻了非常多次，至今花了近8年时间也没发布Preview特性，且未来还得花很长时间让开发者重新认识新的Java语言模型。可见，一个项目初期引以为傲的特性，可能会在项目发展的中后期就成“诅咒”了，既让项目的维护者头疼，也让使用者头疼。

  IM功能设计也是同样的道理，具备强生命力的设计应该遵循`Less is more`的设计理念。“IM功能丰富”看似是值得引以为傲的特性，开发者初期以为开源IM项目都为自己把功能都做好了，自己基本什么也不用做了。但这背后都是有代价的，项目拓展性可能极差，中后期做拓展还不如自己重写。

* 如果Java没有Valhalla这个项目，可能Turms服务端最初会以C#语言立项。

参考文档：[Valhalla项目下的Java语言模型](https://openjdk.java.net/projects/valhalla/design-notes/state-of-valhalla/02-object-model)

## 线程

由于Turms服务端不存在阻塞I/O，诸如RPC、MongoDB与Redis的网络请求都是基于Netty异步实现的，如果更往下看，在Linux系统上，即都为epoll相关操作，因此服务端所需的线程数远远少于传统Java Web应用。

以16核CPU为例，turms-gateway与turms-service的线程数峰值的范围约在80~140（含JVM内部线程）之间，具体峰值数要根据服务器的CPU内核数与所运行的服务端个数（如一个turms-gateway可以同时启动TCP/WebSocket/UDP服务端）而定。

特别值得一提的是：Turms的线程峰值数与同时在线用户规模与请求QPS无关。

补充：正因为Turms服务端自身使用的线程数相比CPU核数而言并不算多，因此在个别代码中我们直接使用`ThreadLocal`缓存一些相对大且线程不安全的对象，并且相比传统服务端，Turms也极大地减少了线程上下文切换带来的开销。

### CPU健康监控

配置类：`im.turms.server.common.infra.property.env.common.healthcheck.CpuHealthCheckProperties`

作用：监控CPU使用率，如果N次检测到CPU使用率超过阈值，则将节点的`isHealthy`设为`false`，并与其他节点共享该状态，同时拒绝提供服务，直到CPU使用率健康。具体配置见上述的配置类。

### Turms线程列表

| 使用范围      | 类别                 | 线程名                                      | 数量    | 作用                                                     |
| ------------- | -------------------- | ------------------------------------------- | ------- | -------------------------------------------------------- |
| 通用          | Admin HTTP服务端线程 | turms-admin-http-accptor                    | 1       | Admin HTTP服务端Acceptor线程                             |
|               |                      | turms-admin-http-worker                     | CPU核数 | Admin HTTP服务端Worker线程                               |
|               | 用户黑名单           | turms-client-blocklist-sync                 | 1       | 用于同步集群间的黑名单数据                               |
|               | 健康检测             | turms-health-checker                        | 1       |                                                          |
|               | 日志                 | turms-log-processor                         | 1       | 用于日志格式化与输出                                     |
|               | Shutdown             | turms-shutdown                              | 1       | 服务端关闭时，调度各组件的Shutdown任务                   |
|               | 定时任务             | turms-task-manager                          | 1       | 用于调度定时任务                                         |
|               | 集群实现             | turms-node-connection-client-io             | CPU核数 | 节点通信I/O线程                                          |
|               |                      | turms-node-connection-keepalive             | 1       | 用于定时发送节点间的心跳，剔除心跳过期的对端节点         |
|               |                      | turms-node-connection-retry                 | 1       | 节点连接重连线程                                         |
|               |                      | turms-node-connection-server-acceptor       | 1       | 节点连接服务端Acceptor线程                               |
|               |                      | turms-node-connection-server-worker         | CPU核数 | 节点连接服务端Worker线程                                 |
|               |                      | turms-node-discovery-change-notifier        | 1       | 节点增删改事件通知线程                                   |
|               |                      | turms-node-discovery-heartbeat-refresher    | 1       | 用于Leader节点在服务注册中心刷新心跳时间，               |
|               | Redis客户端          | lettuce-event-loop                          |         | Redis客户端I/O线程                                       |
|               | MongoDB              | turms-mongo-change-watcher                  | 1       | 用于执行MongoDB Change Stream回调函数                    |
|               |                      | mongo-event-loop                            |         | MongoDB客户端I/O线程                                     |
| turms-gateway | Fake客户端           | turms-fake-client                           | CPU核数 | Fake Turms客户端I/O线程                                  |
|               |                      | turms-fake-client-manager                   | 1       | 调度Fake Turms客户端发送请求                             |
|               |                      | turms-client-heartbeat-refresher            | 1       | 用于定时批量刷新客户端心跳                               |
|               | Gateway服务端        | turms-gateway-udp-acceptor                  | 1       | UDP服务端Acceptor线程                                    |
|               |                      | turms-gateway-udp-worker                    | CPU核数 | UDP服务端Worker线程                                      |
|               |                      | turms-gateway-tcp-acceptor                  | 1       | TCP服务端Acceptor线程                                    |
|               |                      | turms-gateway-tcp-worker                    | CPU核数 | TCP服务端Worker线程                                      |
|               |                      | turms-gateway-ws-acceptor                   | 1       | WebSocket服务端Acceptor线程                              |
|               |                      | turms-gateway-ws-worker                     | CPU核数 | WebSocket服务端Worker线程                                |
|               |                      | turms-gateway-idle-connection-timeout-timer | 1       | 用于监听并关闭长期没与服务端建立应用层用户会话的网络连接 |
|               | 客户端限流防刷       | turms-ip-request-token-bucket-cleaner       | 1       | 用于清除过期了的Token Bucket数据                         |

### 线程模型

（相关文档：[Linux系统参考配置](https://turms-im.github.io/docs/zh-CN/server/deployment/distribution#linux%E7%B3%BB%E7%BB%9F%E7%9A%84%E5%8F%82%E8%80%83%E9%85%8D%E7%BD%AE)、[源码-网络配置](https://turms-im.github.io/docs/zh-CN/code#%E7%BD%91%E7%BB%9C%E5%B1%82%E9%85%8D%E7%BD%AE)）

#### 业务处理TCP/WebSocket服务端与HTTP后台管理API服务端

业务处理TCP/WebSocket服务端与HTTP后台管理API服务端的实现均采用`主从Reactor多线程模型`。具体而言，均使用一个Acceptor线程（主Reactor组、Boss EventLoopGroup）与CPU核数个数的Worker线程组（从Reactor组、Worker EventLoopGroup）。其中：

* Acceptor线程通过`io.netty.channel.nio.NioEventLoop#run`函数，从`ServerSocketChannel`监听TCP客户端的连接事件，并为已连接的TCP客户端创建对应的`SocketChannel`，将其分配给一个Worker线程进行后续处理。

  Acceptor线程名为：`turms-gateway-tcp-acceptor`、`turms-gateway-ws-acceptor`或`turms-admin-http-acceptor`。

  主要相关Linux系统配置：`net.core.somaxconn`（TCP accept队列最大长度）。

* 一个Worker线程可以绑定并处理多个`SocketChannel`，并通过`io.netty.channel.nio.NioEventLoop#run`来不断监听`SocketChannel`的read事件与需要处理write任务，并在读写字节流时执行`ChannelPipeline`中一系列`ChannelHandler`的编解码函数，完成字节编解码任务。

  在Worker线程完成客户端请求的解码工作后，Worker线程就会执行Turms服务端的[源码-客户端请求处理逻辑](https://turms-im.github.io/docs/zh-CN/server/development/code#%E5%AE%A2%E6%88%B7%E7%AB%AF%E8%AF%B7%E6%B1%82%E5%A4%84%E7%90%86%E6%B5%81%E7%A8%8B)了（注意：这里并不需要切换线程）。而在这个业务请求处理过程中，最耗时的是客户端请求的Protobuf解码与MongoDB与Redis请求的编码操作，而IM逻辑只是完成IM业务逻辑的调度，因此并不耗时。特别一提的是，在业务请求的处理过程中，如果需要对一个字符串进行[敏感词过滤](https://turms-im.github.io/docs/zh-CN/anti-spam)检测，并采用`MASK_TEXT`策略，则其性能表现可以简单约等于Java的`String#getBytes("UTF-8")`，因此也不耗时。

  Worker线程名为：`turms-gateway-tcp-worker`、`turms-gateway-ws-worker`或`turms-admin-http-worker`。

  主要Linux系统配置：net.ipv4.tcp_mem、net.ipv4.tcp_rmem、net.ipv4.tcp_wmem

#### Node服务端与客户端

TODO

#### Lettuce与MongoDB客户端

TODO

### 判断任意一行代码在哪个线程组上执行的方法

在了解了上述Turms服务端的线程模型后，读者可以很容易地判断Turms服务端任意一行代码会执行在哪个线程组上。

以处理客户端业务请求为例，从Netty的Worker线程读完一个Turms客户端发来的TurmsRequest字节流开始，这一整条业务处理流程都会在该Worker线程上执行，该线程在处理完业务逻辑后就可以返回去处理其他业务请求了。

而在业务流程处理过程中，Worker线程可能会触发各种网络I/O操作，诸如发送MongoDB与Redis的客户端请求。当这些网络I/O操作完成后，会有一系列的业务相关的回调函数需要执行，而这些回调函数都会执行在MongoDB或Redis客户端NIO线程上。

简而言之，开发者在Service层看到的所有非回调形式的业务处理代码都是在Worker线程上执行的，而各种回调形式的业务处理代码通常都是在MongoDB或Redis客户端的NIO线程上执行。管理员API同理。

### 关于Loom项目——Codes like sync, works like async

#### 背景

很多相对长寿的技术方案一方面即得益于其丰富的生态而长寿，另一方面又因为其丰富的生态而尾大不掉，由于不能顺应时代发展，而最终退出历史舞台。而在Java生态中，各种技术方案的阻塞实现其实就是危及Java在新时代发展的一大拦路虎。其中，JDBC阻塞实现就是Java异步生态实现的最大障碍，Turms没有采用传统SQL数据库的原因之一就是：当时的Java生态圈没有成熟的异步JDBC实现，甚至一些项目因此不以Java立项，而改用Go或C#等语言，只留下一句“Java的线程模型不够“云原生”，生态圈太落后”。

而Loom项目的革命性就在于它正式地将协程（Virtual Thread）引入了Java的世界，让看似同步的代码也能以异步方式执行。

#### 从Turms服务端角度，谈我们对Loom项目的态度

尽管上面说了Loom项目的革命之处，但Turms项目未来也不会采用Loom项目提供的协程，因为对于Turms服务端项目来说，协程只能增加新问题（如栈拷贝），并且不能解决已有的问题。具体原因如下：

* 协程的革命性在于其试图解决Java生态重度使用阻塞API（如JDBC）的现状，让看似同步的代码以异步方式执行。但Turms服务端在处理客户端业务请求时没有阻塞I/O，协程的革命性在Turms服务端这发挥不了作用。且如果有第三方库使用了阻塞I/O，那我们通常会对其作者的技术水平产生怀疑，并不会使用其实现。

* Loom项目引入了基于StackCopy的协程，该协程在park的时候需要保存调用栈到堆上，在unpark并执行thaw操作的时候又要从堆上取回调用栈，但这对Turms服务端来说就多此一举了，因为Turms服务端在处理客户端业务请求时没有阻塞I/O，不需要park。一些推广Loom项目的文章会讲到协程具有“就算开数万个协程，也只需占用这么一点内存”的优点，但Turms服务端只需开0个协程，多使用0字节的内存也能实现同样的效果。

  另外，尽管保存调用栈能解决reactor-core的一大致命缺点“异常的栈信息基本没用，很难Debug”，但reactor-core在Turms服务端的优化下已经克服了这个缺点（具体见下文`补充：reactor-core的缺点`）。

* 协程的学习难度是“1+1>2”，其学习曲线其实高于`reactor-core`。说协程的学习难度是“1+1>2”是因为：开发者同时要掌握线程与协程的使用、原理和优化，同时还要能保证以线程为模型的传统代码要能正确地运行在协程当中，而掌握`reactor-core`只需要最基本的线程知识。

  一些开发者可能会认为`reactor-core`的使用会比协程复杂，但这样的说法通常只是从初学者角度来看的。对于初级工程师而言，其实不管是协程，还是`reactor-core`，在不学习其原理的情况下，二者表面的使用其实都很简单。只是在开发者学习的初期，协程可以在Java层面保证了初级工程师很容易写出高性能的代码，而`reactor-core`最好要有高级工程师带着初级程序员写，否则代码可能维护性极差、甚至出现逻辑错误。但只要过了这短暂的初学阶段，学习协程就会面临刚刚提到的“学习难度1+1>2”的问题，而`reactor-core`只要求工程师掌握最基本的线程知识。

  如`判断任意一行代码在哪个线程组上执行的方法`所述，对于Turms服务端（含第三方库）的**任意一行代码**，我们只需要凭借最基本的线程知识，就能准确推断出这行代码会在哪个线程组上执行，并且这个线程组是谁、从哪、为什么被创建出来的，其生命周期又是如何。

  另外，我们在编写Turms服务端代码的时候，几乎不会考虑“该如何用reactor-core编写异步的代码”，如同很多开发者不会考虑“同步的代码该怎么写”。

* 协程对Java大生态的兼容性还是个问号。Loom项目自身其实还有很长的路要走，需要有大量项目来踩坑与验证。诸如像Netty这样与线程紧密相关的基础网络库如果在协程交互时，出现任何负优化、显式错误、或隐藏非预期行为，其对上层应用的影响都是地动山摇的。

* 协程引入了新的抽象层（协程），而这层抽象层对于Turms服务端来说是多余的，只会徒增资源开销与学习难度。尤其是在我们编写性能相关的关键代码时，我们通常是以系统调用的视角来写Java层的代码，Java只是帮忙给系统调用套了层皮，而这层皮应该越“薄”越好，这样我们才能快速明白JVM到底是调用了什么syscall，以评估我们Java层的代码是否足够高效，还有没有优化的空间。

* Java异步实现至今约有十个方案，但其实Java这层异步模型的皮再怎么折腾，生态再怎么变化，再怎么具有“革命性”，系统层的调用函数还是没变。诸如该用epoll还用epoll，该用堆外内存还用堆外内存。Turms服务端没有必要因为协程更“时尚”，而使用协程，多引入一个抽象层。

* reactor-core不仅实现了异步调用，还具备比协程更强的表达能力。举例来说，如果我们想要知道一个链路的成功率、执行时间等度量数据，只需要调用`metrics(...)`这么一个函数；想要在数据流出现错误时，按条件进行一定次数的自动重试，只需要调用`retry(...)`；想要将切换数据流的执行线程，只需要执行`publishOn(...)`这么一个函数，线程的调度逻辑尽在掌握之中。


综上，有栈协程既在Turms服务端这发挥不了作用，性能表现也不会比Turms服务端下的reactor-core优秀，生态还有无数的坑要有项目去踩与验证，对Turms服务端无意义的协程抽象也是冗余，徒增学习难度的，reactor-core的表达能力也比协程优秀，Turms服务端很难有理由会去使用协程。

当然，上文所述内容主要是针对Turms服务端项目而言的，Loom对于绝大多数Java项目来说还是利大于弊，尤其是第三方库作者不用再需要维护同步与异步两套实现。

#### 补充：reactor-core的缺点

如同我们在[关于依赖库的使用](https://turms-im.github.io/docs/zh-CN/server/development/rules#%E5%85%B3%E4%BA%8E%E4%BE%9D%E8%B5%96%E5%BA%93%E7%9A%84%E4%BD%BF%E7%94%A8)章节已经提到过的，`reactor-core`这样的异步实现库最致命的缺点在于，当它结合一些提倡“多做封装、多做抽象、用户无需关闭实现逻辑”的依赖库时，开发者只能寄希望于服务端能够始终正常运行，否则一旦遇到了一个Bug，开发者很快就会情不自禁地产生一连串的疑问：“reactor-core这样的异步框架能用在生产环境吗？我连异常是哪里抛的都找不到，这样的代码真得能维护吗？”，部分项目组的技术人员因此后悔使用了reactor-core，甚至采用其他语言，如Go，来重写当前Java项目。

举例而言，控制台现在报了一个错“Netty提示：ByteBuf的引用计数已经为0，无法再次进行释放操作”。特别注意，这里并没有省去任何有用的日志信息，这就是开发者真正能从日志看到的所有有用信息。甚至这条日志去除了误导信息，即其堆栈信息。如果开发者根据堆栈信息去Debug，那永远都无法找到真实的Root Cause。而开发者能仅凭这行日志，知道为什么会发生这个异常，并定位出哪个模块导致的这个异常吗？这是Turms真实发生过的一个Bug，也是唯一一个花费6小时以上时间，去阅读Turms所有依赖的所有网络I/O相关源码，并排查Root Cause的最难解决的Bug：[Memory leaks when Turms uses the previous buffer reference to release a recycled pooled buffer](https://github.com/turms-im/turms/issues/786)。

总之，想要用好`reactor-core`必须满足三个条件：

1. 所有关键代码必须可控，否则出错的时候只能寄希望于：

   * 第三方库的开发人员技术水平高，代码设计功底扎实。如果第三方依赖也是基于异步编程，那这个要求就更高，作者要能够预判上层开发者可能会遇到的异常，并通过异步手段，把异常抛给上层应用。

   * 第三方库不复杂，能快速阅读完相关源码。

     一个优秀的例子就是：reactor-netty。其开发人员的技术水平高，设计功底扎实。代码也比较精简，容易阅读。

2. 必须规范地传递异常与打印日志。就算是异步编程，只要规范地传递异常与打印日志，我们通过单条日志也能马上看出绝大部分Bug的缘由，只有个别Bug可能需要关联多条日志进行排查。如果做不到这点，出错时只能听天由命。

3. 团队里必须要有工程师熟练掌握异步编程。

只要缺少上面的一个条件，开发者迟早会遇到类似上述的“Netty提示：ByteBuf的引用计数已经为0，无法再次进行释放操作”这样难度的Bug，也因此对于一般的技术团队，我们更推荐Loom项目，而不是reactor-core。当然，更推荐的可能是切换编程语言。但Turms项目如今已经能满足上述条件，不再存在“异常难以Debug”的情况。

额外补充：

* 部分文章会说reactor-core这样的异步框架很容易写出回调地狱。但如上文所述，reactor-core自身有很强的表达能力，实际上是开发者“想设计几层，就能写出几层的调用层级”。换言之，如果一个函数的最高调用层级是5层，那用reactor-core可以写出5/4/3/2/1层级的代码。而在实践中，Turms服务端的嵌套回调函数都是为了减少中间对象或实现栈分配（而非堆分配）而做的嵌套，具体可以看Turms服务端源码。
* 在开发turms-admin管理系统的时候，我们通常也是尽量避免使用`await/async`，其原因是turms-admin最终会transpile成ES5语法，而被`await/async`修饰的函数在`source map`关闭之后，非常难Debug，故尽量避免`await/async`。