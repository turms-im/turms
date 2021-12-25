# 系统资源管理

内存与CPU资源对服务端的重要性不言而喻，Turms各模块都比较极致地使用内存与CPU，具体可参考各模块实现的文档与代码。而在另一方面，为保证服务端的正常运行，其内部也提供了一套健康检测机制，该机制配合上层的“拒绝服务”机制，以尽最大努力保证服务端能够正常运行。

Turms提供系统资源监控配置类：`im.turms.server.common.property.env.common.healthcheck.HealthCheckProperties`，来允许用户配置可用内存占用率与CPU占用率。Turms服务端的`HealthCheckManager`会持续检测可用物理内存与CPU占用率，如果检测到可用物理内存过低或CPU占用率过高，则会：

* 将自身在服务注册中心的`isHealthy`信息标记为`false`。由于RPC发送端只会从`isHealthy`为`true`的服务端中，挑选RPC的响应服务端，因此能实现类似背压的效果
* 拒绝对外提供服务。具体而言：如果是turms-gateway服务端，则拒绝新会话的建立与用户请求的处理；如果是turms-service服务端，则拒绝处理turms-gateway服务端发来的RPC请求（注意：就算处于“不健康”状态，turms-service仍然会为管理员API提供服务）

## 内存管理

### JVM基础内存知识

JVM HotSpot虚拟机的内存区域可以划分为：

* 堆内存（Heap Memory）：Eden区、Survivor区、老年代（Old Generation）

* 非碓内存（Non-heap Memory）

  * 直接内存（Direct Memory）：Direct Buffer Pool
  * JVM内部内存（JVM Specific Memory）：本地方法栈、元空间、Code Cache等

  特别注意：通过JDK函数`java.lang.management.MemoryMXBean#getNonHeapMemoryUsage`获得的`NonHeapMemory`并不包括`Direct Buffer Pool`（直接内存缓存池）。具体而言，该函数在JDK 17中所指的内存空间为：

  * CodeHeap 'non-nmethods'
  * CodeHeap 'non-profiled nmethods'
  * CodeHeap 'profiled nmethods'
  * Compressed Class Space
  * Metaspace

参考文档：[How to Monitor VM Internal Memory](https://docs.oracle.com/en/java/javase/17/troubleshoot/diagnostic-tools.html#GUID-FB0581EA-2F91-4093-B2FA-46687F7AB081)

### 可控内存（Managed Memory）的使用

Turms服务端可控内存指的是`堆内存（Heap Memory）`与`直接内存（Direct Memory）`这两块区域。

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

另外，推荐生产环境不要给Turms服务端分配超过32GB内存，一是为了启动JVM的指针压缩技术，二是避免单个服务端承载太多负荷，在停机时减缓惊群效应，提升用户体验。

#### 直接内存

下文所述的所有`直接内存`在实际代码中，都是由`PooledByteBufAllocator.DEFAULT`分配，即它们都是被Netty缓存与管理的直接内存。

##### 实践意义

直接内存的容量上限影响Turms服务端在同一时刻能够处理的客户端请求与管理员API请求的峰值

##### 主要使用方

* TCP的读写操作。如基于Netty的：第三方依赖`mongo-driver-java`与`Lettuce`等驱动；Turms服务端自身面向客户端的TCP/HTTP/UDP服务端实现
* 日志打印。Turms自研的日志打印实现直接将Java基础数据写入直接内存块中，再将其写入文件描述符

  特别一提的是：Java常规异常日志格式所需内存是非常夸张，异常的一个Cause格式化后约占1000~2000字符，换言之，一个异常只要多几个Causes，打印一个异常就需要花费几MB的内存。

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

配置类：`im.turms.server.common.property.env.common.healthcheck.MemoryHealthCheckProperties`

如上文所述，要想让运维人员准确评估服务端应该使用多少内存其实是非常困难，甚至不现实的事，尤其是一些关键系统内核（如TCP连接）所占内存是动态变化的，因此`MemoryHealthCheckProperties`除了提供诸如`maxAvailableMemoryPercentage`与`maxAvailableDirectMemoryPercentage`这样限定Turms服务端可使用内存上限的配置，同时也提供了`minFreeSystemMemoryBytes`这一配置，让Turms服务端能够实时检测系统的可用物理内存，并尽最大努力预留这些内存出来。

#### 内存监控实现——MemoryHealthChecker

作用：

* 检测到系统物理内存不足时，通知上层服务拒绝处理用户会话与请求，以尽最大努力保证不会耗尽物理内存，并避免使用Swap内存
* 如果检测到系统物理内存不足时，且已用堆内存超过`heapMemoryGcThresholdPercentage`，则调用`System.gc()`来建议JVM进行Full GC

特别注意

* 如上文所述，直接内存的生命周期与请求的生命周期高度一致，因此就算`MemoryHealthChecker`检测到了`已用总内存已经超过XX`，它也不会主动尝试去释放直接内存，而是等待Netty内部的内存管理机制对其进行释放
* 综上，尽管Turms服务端会尽最大努力不去耗尽物理内存，但对于极端突发的大量请求，Turms服务端还是有可能会耗尽物理内存，此时会采用Swap内存。如果Swap内存被系统关闭或Swap内存不足，则Turms服务端将直接抛出`OutOfMemoryError`异常。因此我们可以把使用Swap内存当作最后一道防线，故非常不推荐在生产环境中关闭Swap内存。

### 关于Valhalla

Java的内存占用一直为人所诟病，诸如一个Integer对象所存放的对象头所需的内存空间大于实际int数据数倍。相比很多追求性能优化（甚至是寄存器级别的优化）的C++服务端项目（如Nginx、Redis），由于Java自身的设计缺陷与保守，它对内存的浪费就让人感觉有些“自暴自弃”了，并且更糟糕的是这样的精神也传导给了整个Java生态圈。通过阅读源码，能发现很多知名Java项目也是“功能能用，功能写着舒服，性能差不多就行，反正JVM会帮忙GC”的态度，诸如可以很容易做Cache的地方不Cache、基础数据结构乱用、反复内存拷贝（如最常见的`String`与`StringBuilder`在实践中，通常来来回回拷贝很多次，源码让人触目惊心），关于这点我们已经在其他章节重点讲解了，故不赘述。

而Valhalla项目则对现有的Java Object体系进行了重构。原有的`Object`在新的Java体系中叫做`IdentityObject`，而新体系下的`Object`则成了`IdentityObject`与`ValueObject`的父类（注意：Valhalla团队尚未定稿，因此概念可能还会变），其中`ValueObject`让用户能够自定义性能如传统Java八大基础类型一样高效的数据结构，无需对象头、访问时无需通过指针查找、栈上分配，甚至直接存储在CPU寄存器之中。等Valhalla项目发布Preview版本后，我们将引入`ValueObject`，并且由于我们已等待该项目数年，非常熟悉其设计，故可在一周内完成适配与测试工作。这也是我们会为`Preview`特性开绿灯的唯一特性。

## 线程

由于Turms服务端不存在阻塞I/O，诸如RPC、MongoDB与Redis的网络请求都是基于Netty异步实现的。如果更往下看，在Linux系统上，即都为epoll相关操作，因此服务端所需的线程数远远少于传统Java Web应用。以16核CPU为例，turms-gateway与turms-service的线程数峰值的范围约在80~150（含JVM内部线程）之间，具体峰值数要根据服务器的CPU内核数与所运行的服务端个数（如一个turms-gateway可以同时启动TCP/WebSocket/UDP服务端）而定。

特别值得一提的是：Turms的线程峰值数与同时在线用户规模与请求QPS无关。

补充：正因为Turms服务端自身使用的线程数相比CPU核数而言并不算多，因此在个别代码中我们直接使用`ThreadLocal`缓存一些相对大且线程不安全的对象，并且相比传统服务端，Turms也极大地减少了线程上下文切换带来的开销

### CPU健康监控

配置类：`im.turms.server.common.property.env.common.healthcheck.CpuHealthCheckProperties`

作用：监控CPU使用率，如果N次检测到CPU使用率超过阈值，则将节点的`isHealthy`设为`false`，并与其他节点共享该状态，同时拒绝提供服务，直到CPU使用率健康。具体配置见上述的配置类。

### 线程模型

TODO
