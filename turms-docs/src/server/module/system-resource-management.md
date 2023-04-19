# System Resource Management

The importance of memory and CPU resources to the server is self-evident. Each module of Turms uses memory and CPU to the extreme. For details, please refer to the documents and codes implemented by each module. On the other hand, in order to ensure the normal operation of the server, it also provides a set of health detection mechanism internally. This mechanism cooperates with the "denial of service" mechanism of the upper layer to do its best to ensure the normal operation of the server.

Turms provides a system resource monitoring configuration class: `im.turms.server.common.infra.property.env.common.healthcheck.HealthCheckProperties`, to allow users to configure available memory usage and CPU usage. The `HealthCheckManager` on the Turms server will continuously detect the available physical memory and CPU usage. If it detects that the available physical memory is too low or the CPU usage is too high, it will:

* Mark the `isHealthy` information in the service registry as `false`. Since the RPC sender will only select the RPC response server from the server whose `isHealthy` is `true`, it can achieve a similar back pressure effect
* Refusal to provide external services. Specifically: if it is the turms-gateway server, refuse to establish a new session and process user requests; if it is the turms-service server, refuse to process the RPC request sent by the turms-gateway server (note: even in "Unhealthy" status, turms-service will still provide services for the admin API)

## Memory management

### JVM basic memory knowledge

The memory area of the JVM HotSpot virtual machine can be divided into:

* Heap Memory: Eden area, Survivor area, Old Generation (Old Generation)

* Non-heap Memory (Non-heap Memory)

  * Direct memory (Direct Memory): Direct Buffer Pool
  * JVM internal memory (JVM Specific Memory): local method stack, metaspace, Code Cache, etc.

  Special attention: `NonHeapMemory` obtained by the function `java.lang.management.MemoryMXBean#getNonHeapMemoryUsage` does not include `Direct Buffer Pool` (direct memory buffer pool). Specifically, the memory space referred to by this function in JDK 17 is:

  * CodeHeap 'non-nmethods'
  * CodeHeap 'non-profiled nmethods'
  * CodeHeap 'profiled nmethods'
  * Compressed Class Space
  * Metaspace

Reference document: [How to Monitor VM Internal Memory](https://docs.oracle.com/en/java/javase/17/troubleshoot/diagnostic-tools#GUID-FB0581EA-2F91-4093-B2FA-46687F7AB081)

### Use of Managed Memory

The controllable memory of the Turms server refers to the two areas of `heap memory (Heap Memory)` and `direct memory (Direct Memory)`.

#### Heap memory

##### Practical significance

The practical significance of heap memory is relatively easy to understand, which is to configure as large a heap memory as possible to reduce the number of GC and the occurrence of `stop-the-world` events.

##### Configuration

The default heap configuration of the JVM is as follows:

```
-XX:MaxRAMPercentage=75
-XX:InitialRAMPercentage=75
```

in:

* `InitialRAMPercentage` and `MaxRAMPercentage` specify the size of the memory that needs to be reserved, but a page fault will still occur when the Turms server accesses this memory area. Although the JVM can directly convert the reserved memory into committed memory by configuring `AlwaysPreTouch` to avoid page fault exceptions on the server side during runtime. But because it is difficult for the server to monitor the actual used heap memory after enabling this option, it is not recommended to add this configuration at present.
* `InitialRAMPercentage` and `MaxRAMPercentage` are set to the same value mainly to ensure the continuity of the memory as much as possible, and avoid repeated GC and `stop-the-world` operations on the server due to memory expansion and shrinkage.
* The heap memory is not configured to a value close to 100%. This is to give the remaining physical memory to the JVM's own off-heap memory (such as the largest direct memory, CodeCache, Metaspace, etc.), the system kernel (such as maintaining the TCP connection time buffer) and sidecar services (such as: log collection service).

In addition, it is recommended not to allocate more than 32GB of memory to the Turms server in the production environment. because:

* Turn on the pointer compression technology of the JVM to reduce unnecessary memory usage
* Avoid a single server carrying too much load, slow down the shocking group effect during shutdown, and improve user experience

#### Direct Memory

All `direct memory` described below are allocated by `PooledByteBufAllocator.DEFAULT` in the actual code, that is, they are all direct memory cached and managed by Netty.

##### Practical significance

The upper limit of direct memory capacity affects the peak value of client requests and admin API requests that the Turms server can handle at the same time

##### Main users

* Network I/O operations. For example, based on Netty: the third party relies on drivers such as `mongo-driver-java` and `Lettuce`; the Turms server itself implements the client-oriented TCP/HTTP server.
* Log printing. The log printing developed by Turms directly writes Java basic data into the direct memory block, and then writes it into the file descriptor.

In other words, basically all memory areas that need to be accessed by the system kernel, we use direct memory directly to avoid meaningless heap memory copies.

Note: In the Linux system, the direct memory used by Turms is still in the user space, so when writing the direct memory to the device (such as network card and hard disk), it still needs to be copied twice from the user space to the kernel space and from the kernel space to the device , and these two copy operations cannot be avoided by the upper server.

##### life cycle

Because in the Turms server, the life cycle of direct memory is highly consistent with the life cycle of client requests and admin API requests, a piece of direct memory usually only exists in part or all of the life cycle of a request. Specifically, its life cycle is roughly as follows:

* The life cycle of a request begins when Netty cuts the TCP byte stream. Netty cuts the TCP byte stream according to the varint encoded header (the length of the Payload represented by its value), and when this memory is cut When it comes out (note: there is no memory copy here), the life cycle of this piece of direct memory representing the request begins.

* After the Turms server parses this memory into a specific request model, Turms will determine whether this type of request needs to use its own direct memory. If the processing logic of the request does not need to use this memory, the memory will be immediately reclaimed back to Netty's memory cache pool. Otherwise, requests such as "forwarding user messages" need to use this memory, and this memory will not be reclaimed immediately. Turms will then perform business logic processing on the request.

* In the process of business processing, other network I/O operations (such as sending requests to MongoDB/Redis) or log printing operations may be involved. These two types of operations need to take out new direct from the memory buffer pool managed by Netty Memory block for MongoDB/Redis client request encoding and response decoding operations, or log printing operations.

* After the Turms server finally flushes the direct memory of the request response to the network card, in addition to the direct memory representing the log record, other direct memory involved in the process will also be recycled.

  The only exception is: if the direct memory of a request needs to be forwarded to multiple clients, Turms will use the reference counter to separate the life cycle of the request from the life cycle of the direct memory to ensure that the same piece of direct memory Forwarded to multiple clients to avoid memory copies.

  Notice:

  1. The `direct memory reclamation` mentioned above does not reclaim the memory to the system, but reclaims it back to the memory pool managed by Netty, and the memory will not be actually released at this time.
  2. Direct memory is mainly through: When Pooled ByteBuf is `release`, Netty will detect whether the Chunk it belongs to is idle (0% usage). If yes, the memory is actually freed by the function `io.netty.buffer.PoolArena#destroyChunk`.

Due to the existence of this life cycle, the real usage rate of heap memory and direct memory is actually related. The increase in heap memory is mainly due to a series of logics processed by the Turms server after receiving client requests or admin API requests. In a process, the usage rate of direct memory increases because of request decoding and response encoding, network I/O operation encoding and decoding and log printing in the logic. When the life cycle of the request ends, both the heap memory and the direct memory can be reclaimed.

### Memory health check

#### Configuration

Configuration class: `im.turms.server.common.infra.property.env.common.healthcheck.MemoryHealthCheckProperties`

As mentioned above, it is very difficult or even unrealistic for the operation and maintenance personnel to accurately estimate how much memory the server should use, especially the memory occupied by some key system kernels (such as TCP connections) changes dynamically, so `MemoryHealthCheckProperties` not only provides configurations such as `maxAvailableMemoryPercentage` and `maxAvailableDirectMemoryPercentage` that limit the upper limit of the memory that the Turms server can use, but also provides the configuration `minFreeSystemMemoryBytes`, which allows the Turms server to detect the available physical memory of the system in real time, and Do your best to reserve this memory.

#### Memory monitoring implementation - MemoryHealthChecker

effect:

* When it is detected that the system's physical memory is insufficient, notify the upper layer service to refuse to process user sessions and requests, so as to do its best to ensure that the physical memory will not be exhausted and avoid using Swap memory
* If it is detected that the system has insufficient physical memory and the used heap memory exceeds `heapMemoryGcThresholdPercentage`, call `System.gc()` to suggest JVM to perform Full GC

pay attention

* As mentioned above, the life cycle of the direct memory is highly consistent with the life cycle of the request, so even if `MemoryHealthChecker` detects that `the total memory used has exceeded XX`, it will not actively try to release the direct memory, but wait Netty's internal memory management mechanism releases it
* In summary, although the Turms server will try its best not to run out of physical memory, for a large number of extremely sudden requests, the Turms server may still run out of physical memory, and Swap memory will be used at this time. If the Swap memory is closed by the system or the Swap memory is insufficient, the Turms server will directly throw an `OutOfMemoryError` exception. Therefore, we can use Swap memory as the last line of defense, so it is not recommended to turn off Swap memory in a production environment.

### About the Valhalla project - Codes like a class, works like an int

The memory usage of Java has always been criticized by people. For example, the memory required by the object header stored by an Integer object (12 bytes in the case of a 64-bit system and the compressed pointer is turned on) is several times larger than the actual int data, and because Such design flaws lead to the need for some workarounds when programming. For example, when using `Integer` objects, the JVM will preferentially use the object cache in the `java.lang.Integer.IntegerCache` class. Compared with many C++ server projects (such as Nginx and Redis) that pursue performance optimization (even register-level optimization), due to Java's own design flaws and conservatism, Java's waste of memory makes people feel a little "self-defeating". And what's worse: this spirit has also been transmitted to the entire Java ecosystem. By reading the source code, we can find that many well-known Java projects also have the attitude of "the function can be used, the code is comfortable to write, and the performance is about the same. Anyway, the JVM will help the GC". Repeated memory copying (such as the most common `String` and `StringBuilder` are usually copied back and forth many times in practice, and the source code is shocking), only a very few projects such as Netty have the awareness of performance optimization and excellence. We have already explained this point in other chapters, so I won’t repeat it here.

The Valhalla project reconstructs the existing Java Object system. The original `Object` is called `IdentityObject` in the new Java system, and the `Object` under the new system has become the parent class of `IdentityObject` and `ValueObject` (note: the Valhalla team has not yet finalized, so the concept may not yet be finalized. will change), the two are somewhat similar to C#'s `Reference types` and `Value types`. Among them, `ValueObject` is divided into two categories, namely `primitive class` and `value class`. `primitive class` allows developers to customize data structures that are as efficient as the eight traditional Java basic types, without object headers, without pointer lookup, stack allocation, and naturally without GC. At the same time, these classes can also be declared fields and define functions. The traditional eight basic types of Java will also be redesigned based on the new object system, such as `int` such `primitive type` will become `primitive class` (`primitive class` is a type of `value class`, its value cannot be `null`), and its `wrapper class (Wrapper Class)` `Integer` and `int.ref` that may be supported will become `value class` (value can be `null`), so the future will not There will be the concept of `wrapper class`.

For example, the primitive instance object of the class `primitive class Point { private double x; private double y; }` only needs to occupy 2 double bytes, that is, 16 bytes, and no object header is required.

After the Valhalla project releases the Preview version, we will introduce `ValueObject` and transform code implementations such as DTO objects and various wrapper classes (such as `Date` and `ByteArrayWrapper`) to greatly reduce memory overhead and the number of objects and speed up GC speed. And because we have been waiting for this project for several years and are very familiar with its design, we can complete the fitting and testing work within a week. This is also the only feature that we will green light for the `Preview` feature.

Replenish:

* In fact, the development history of Java also confirms what we have talked about "[IM has rich functions to pay a fatal price](https://turms-im.github.io/docs/design/schema#%E5%8A%9F%E8%83%BD%E4%B8%B0%E5%AF%8C%E7%9A%84%E8%87%B4%E5%91%BD%E4%BB%A3%E4%BB%B7)", that is, the characteristics that a project is proud of, may hide abyss behind it.

  Java was once proud of `Everything is an object`, and emphasized that `Java has no structures or unions as complex data types. You don't need structures and unions when you have classes` (quoted from Sun's release in 1995 The Java white paper: [Simple, Object Oriented, and Familiar](https://www.stroustrup.com/1995_Java_whitepaper.pdf)) to promote Java is far easier to use than C and C++.

  (Additional supplement: Looking at the development history of Java, developers will also lament the powerful vitality shown by Java's ability to continuously adapt to the development of the times, adjust its own development direction, and overcome five obstacles)

  But in today's programming practice, advocating "everything is an object" without providing `structure` is more like a curse, such as when we put an `int` into a `List<Integer>`, we need a `new` New object, increasing the object header. In other words, as long as we use common data structures such as `List` and `Map` provided by Java, a lot of memory will be wasted in vain, and these collection classes are unavoidable in actual projects. (Supplement: In fact, internal data structures such as `HashSet` and `LinkedList` are more wasteful than the memory waste that many developers can imagine. The memory occupied by the object header is more than the actual data, so we see It will use "shocking" to evaluate its source code).

  Today, the Valhalla project hopes to change this situation by introducing the `primitive/value class` language feature, but because it needs to be forward compatible with the huge Java ecosystem, and let Java get rid of the traditional curse of `everything is an object`, the Valhalla project The development of Java is on thin ice, and the design draft alone has been overturned many times. It has taken nearly 8 years to release the Preview feature, and it will take a long time for developers to re-acquaint themselves with the new Java language model in the future. It can be seen that a feature that a project is proud of at the beginning may become a "curse" in the middle and late stages of project development, causing headaches for both project maintainers and users.

  The same is true for the design of IM functions. A design with strong vitality should follow the design concept of `Less is more`. "Rich IM functions" seems to be a feature to be proud of. At the beginning, developers thought that open source IM projects had all the functions for themselves, and they basically didn't have to do anything. But there is a price behind this, and the scalability of the project may be extremely poor. It is better to rewrite it yourself if you do expansion in the middle and late stages.

* If there is no Valhalla project in Java, it is possible that the Turms server will initially be established in C# language.

Reference document: [Java language model under the Valhalla project](https://openjdk.java.net/projects/valhalla/design-notes/state-of-valhalla/02-object-model)

## thread

Since the Turms server does not have blocking I/O, network requests such as RPC, MongoDB, and Redis are all implemented asynchronously based on Netty. If you look further down, on the Linux system, they are all epoll-related operations. The number of threads required is far less than that of traditional Java web applications.

Taking a 16-core CPU as an example, the peak number of threads of turms-gateway and turms-service ranges from 80 to 140 (including JVM internal threads). The specific peak number depends on the number of CPU cores of the server and the running server. It depends on the number (for example, one turms-gateway can start the TCP/WebSocket/UDP server at the same time).

It is particularly worth mentioning that the peak number of threads in Turms has nothing to do with the scale of concurrent online users and the requested QPS.

Supplement: Because the number of threads used by the Turms server itself is not much compared to the number of CPU cores, we directly use `ThreadLocal` to cache some relatively large and thread-unsafe objects in individual codes, and compared with traditional On the server side, Turms also greatly reduces the overhead caused by thread context switching.

### CPU health monitoring

Configuration class: `im.turms.server.common.infra.property.env.common.healthcheck.CpuHealthCheckProperties`

Function: Monitor the CPU usage. If the CPU usage exceeds the threshold for N times, set `isHealthy` of the node to `false`, and share this state with other nodes, and refuse to provide services until the CPU usage is healthy. For specific configuration, see the configuration class above.
### Turms thread list

| scope of use | category | thread name | quantity | function |
| ------------- | -------------------- | ------------------------------------------- | ------- | ----------------------------------------------------------- |
| General | Admin HTTP Server Thread | turms-admin-http-accptor | 1 | Admin HTTP Server Acceptor Thread |
| | | turms-admin-http-worker | Number of CPU cores | Admin HTTP server worker thread |
| | User blacklist | turms-client-blocklist-sync | 1 | Used to synchronize blacklist data between clusters |
| | health checker | turms-health-checker | 1 | |
| | Logging | turms-log-processor | 1 | Used for log formatting and output |
| | Shutdown | turms-shutdown | 1 | When the server is shut down, schedule the Shutdown task of each component |
| | Scheduled tasks | turms-task-manager | 1 | Used to schedule scheduled tasks |
| | Cluster implementation | turms-node-connection-client-io | Number of CPU cores | Node communication I/O threads |
| | | turms-node-connection-keepalive | 1 | Used to regularly send heartbeats between nodes, and remove peer nodes with expired heartbeats |
| | | turms-node-connection-retry | 1 | Node connection retry thread |
| | | turms-node-connection-server-acceptor | 1 | Node connection server Acceptor thread |
| | | turms-node-connection-server-worker | Number of CPU cores | Node connection server Worker thread |
| | | turms-node-discovery-change-notifier | 1 | Node addition, deletion and modification event notification thread |
| | | turms-node-discovery-heartbeat-refresher | 1 | Used for the Leader node to refresh the heartbeat time in the service registry, |
| | Redis client | lettuce-event-loop | | Redis client I/O thread |
| | MongoDB | turms-mongo-change-watcher | 1 | Used to execute the MongoDB Change Stream callback function |
| | | mongo-event-loop | | MongoDB client I/O thread |
| turms-gateway | Fake client | turms-fake-client | Number of CPU cores | Fake Turms client I/O threads |
| | | turms-fake-client-manager | 1 | Schedule Fake Turms client to send requests |
| | | turms-client-heartbeat-refresher | 1 | Used to periodically refresh client heartbeats in batches |
| | Gateway server | turms-gateway-udp-acceptor | 1 | UDP server Acceptor thread |
| | | turms-gateway-udp-worker | Number of CPU cores | UDP server worker thread |
| | | turms-gateway-tcp-acceptor | 1 | TCP server Acceptor thread |
| | | turms-gateway-tcp-worker | Number of CPU cores | TCP server worker thread |
| | | turms-gateway-ws-acceptor | 1 | WebSocket server Acceptor thread |
| | | turms-gateway-ws-worker | Number of CPU cores | WebSocket server worker threads |
| | | turms-gateway-idle-connection-timeout-timer | 1 | Used to monitor and close the network connection that has not established an application layer user session with the server for a long time |
| | Client current limit and anti-swipe | turms-ip-request-token-bucket-cleaner | 1 | Used to clear expired Token Bucket data |
### Threading Model

(Related documents: [Linux System Reference Configuration](https://turms-im.github.io/docs/server/deployment/distribution#linux%E7%B3%BB%E7%BB%9F%E7%9A%84%E5%8F%82%E8%80%83%E9%85%8D%E7%BD%AE), [source code-network configuration](https://turms-im.github.io/docs/server/development/code#%E7%BD%91%E7%BB%9C%E5%B1%82%E9%85%8D%E7%BD%AE))

#### Business processing TCP/WebSocket server and HTTP background management API server

Both the implementation of the business processing TCP/WebSocket server and the HTTP background management API server adopt the `master-slave Reactor multithreading model`. Specifically, an Acceptor thread (main Reactor group, Boss EventLoopGroup) and a Worker thread group with the number of CPU cores (slave Reactor group, Worker EventLoopGroup) are used. in:

* The Acceptor thread listens to the connection event of the TCP client from the `ServerSocketChannel` through the `io.netty.channel.nio.NioEventLoop#run` function, and creates a corresponding `SocketChannel` for the connected TCP client, and assigns it to a Worker thread for subsequent processing.

  Acceptor thread name: `turms-gateway-tcp-acceptor`, `turms-gateway-ws-acceptor` or `turms-admin-http-acceptor`.

  Mainly related to Linux system configuration: `net.core.somaxconn` (the maximum length of the TCP accept queue).

* A Worker thread can bind and process multiple `SocketChannel`, and use `io.netty.channel.nio.NioEventLoop#run` to continuously monitor `SocketChannel` read events and need to process write tasks, and read and write words Execute a series of encoding and decoding functions of `ChannelHandler` in `ChannelPipeline` when throttling, and complete the task of byte encoding and decoding.

  After the Worker thread completes the decoding work of the client request, the Worker thread will execute the [source code-client request processing logic](https://turms-im.github.io/docs/server/development/code#%E5%AE%A2%E6%88%B7%E7%AB%AF%E8%AF%B7%E6%B1%82%E5%A4%84%E7%90%86%E6%B5%81%E7%A8%8B) (note: there is no need to switch threads here). In the process of processing this business request, the most time-consuming is the Protobuf decoding requested by the client and the encoding operation requested by MongoDB and Redis, while the IM logic only completes the scheduling of the IM business logic, so it is not time-consuming. In particular, during the processing of business requests, if a string needs to be [sensitive word filtering](https://turms-im.github.io/docs/server/module/anti-spam.html ) detection, and using the `MASK_TEXT` strategy, its performance can be simply equal to Java`s `String#getBytes("UTF-8")`, so it is not time-consuming.

  Worker thread name: `turms-gateway-tcp-worker`, `turms-gateway-ws-worker` or `turms-admin-http-worker`.

  Main Linux system configuration: net.ipv4.tcp_mem, net.ipv4.tcp_rmem, net.ipv4.tcp_wmem

#### Node server and client

TODO

#### Lettuce and MongoDB client

TODO

### The method of judging which thread group any line of code is executed on

After understanding the above-mentioned threading model of the Turms server, readers can easily determine which thread group any line of code on the Turms server will be executed on.

Taking the processing of client business requests as an example, starting from the fact that Netty's Worker thread reads the TurmsRequest byte stream sent by a Turms client, the entire business processing process will be executed on the Worker thread. After the logic, you can return to process other business requests.

During business process processing, Worker threads may trigger various network I/O operations, such as sending MongoDB and Redis client requests. When these network I/O operations are completed, there will be a series of business-related callback functions that need to be executed, and these callback functions will be executed on the MongoDB or Redis client NIO thread.

In short, all non-callback business processing codes seen by developers at the Service layer are executed on Worker threads, while various callback business processing codes are usually executed on NIO threads of MongoDB or Redis clients to execute. The admin API is the same.

### About the Loom project - Codes like sync, works like async

#### background

On the one hand, many relatively long-lived technical solutions benefit from their rich ecology and longevity, on the other hand, because of their rich ecology, they are too big to lose their tails, and eventually withdraw from the stage of history because they cannot adapt to the development of the times. In the Java ecosystem, the blocking implementation of various technical solutions is actually a major obstacle that endangers the development of Java in the new era. Among them, the implementation of JDBC blocking is the biggest obstacle to the implementation of Java asynchronous ecology. One of the reasons why Turms did not adopt the traditional SQL database is that there was no mature asynchronous JDBC implementation in the Java ecosystem at that time, and even some projects did not use Java as a project. For languages such as Go or C#, only one sentence is left: "Java's threading model is not "cloud-native" enough, and the ecosystem is too backward."

The revolutionary aspect of the Loom project is that it officially introduces virtual threads into the Java world, allowing seemingly synchronous code to be executed asynchronously.

#### From the perspective of the Turms server, talk about our attitude towards the Loom project

Although the revolution of the Loom project is mentioned above, the Turms project will not adopt the coroutine provided by the Loom project in the future, because for the Turms server project, the coroutine can only add new problems (such as stack copying) and cannot Solve existing problems. The specific reasons are as follows:

* The revolutionary of the coroutine is that it tries to solve the status quo of the heavy use of blocking APIs (such as JDBC) in the Java ecosystem, allowing seemingly synchronous code to be executed asynchronously. However, the Turms server does not block I/O when processing client business requests, and the revolutionary nature of coroutines does not work on the Turms server. And if there is a third-party library that uses blocking I/O, then we usually have doubts about the technical level of its author and will not use its implementation.

* The Loom project introduces a StackCopy-based coroutine, which needs to save the call stack to the heap when it is parked, and retrieve the call stack from the heap when it unparks and executes the thaw operation, but this does not affect the Turms server. It is superfluous, because the Turms server does not block I/O when processing client business requests, and does not need to park. Some articles promoting the Loom project will mention that coroutines have the advantage of "even if you open tens of thousands of coroutines, you only need to occupy such a small amount of memory", but the Turms server only needs to open 0 coroutines, and use more than 0 bytes of memory. Memory can also achieve the same effect.

  In addition, although saving the call stack can solve a major fatal shortcoming of reactor-core "abnormal stack information is basically useless and difficult to debug", reactor-core has overcome this shortcoming under the optimization of the Turms server (see below for details` Supplement: Disadvantages of reactor-core `).

* The learning difficulty of coroutine is "1+1>2", and its learning curve is actually higher than `reactor-core`. It is said that the difficulty of learning coroutines is "1+1>2" because: developers must master the use, principle and optimization of threads and coroutines at the same time, and at the same time ensure that traditional code modeled on threads can run correctly Among coroutines, mastering `reactor-core` only requires the most basic knowledge of threads.

  Some developers may think that the use of `reactor-core` is more complicated than coroutines, but such statements are usually only from the perspective of beginners. For junior engineers, whether it is a coroutine or `reactor-core`, the superficial use of both is actually very simple without learning its principles. Only in the early stages of developer learning, coroutines can ensure that junior engineers can easily write high-performance code at the Java level, and `reactor-core` is best written by senior engineers with junior programmers, otherwise the code may be maintained Poor performance, even logic errors. But as long as this short initial stage is passed, learning coroutines will face the problem of "learning difficulty 1+1>2" just mentioned, and `reactor-core` only requires engineers to master the most basic thread knowledge.

  As described in `The method of judging which thread group any line of code is executed on`, for *any line of code** on the Turms server (including third-party libraries), we only need to rely on the most basic thread knowledge to accurately Infer which thread group this line of code will execute on, and who, where, and why this thread group was created, and what its life cycle is.

  In addition, when we write Turms server code, we hardly consider "how to write asynchronous code with reactor-core", just as many developers do not consider "how to write synchronous code".

* The compatibility of coroutines to the Java ecosystem is still a question mark. The Loom project itself still has a long way to go, and a large number of projects are needed to step on and verify. If a basic network library such as Netty, which is closely related to threads, has any negative optimization, explicit errors, or hidden unexpected behaviors when interacting with coroutines, its impact on upper-layer applications will be shaken.

* Coroutines introduce a new abstraction layer (coroutines), and this layer of abstraction is redundant for the Turms server, which will only increase resource overhead and learning difficulty. Especially when we write performance-related key codes, we usually write the Java layer code from the perspective of system calls. Java just helps to cover the system calls with a layer of skin, and the thinner the skin, the better. In this way, we can quickly understand what syscall is called by the JVM to evaluate whether our Java layer code is efficient enough and whether there is room for optimization.

* Java asynchronous implementation has about ten solutions so far, but in fact, no matter how tossed about the skin of the asynchronous model of Java, no matter how the ecology changes, no matter how "revolutionary", the calling function of the system layer remains unchanged. For example, whether to use epoll or epoll, whether to use off-heap memory or off-heap memory. There is no need for the Turms server to use coroutines and introduce an additional abstraction layer because coroutines are more "fashionable".

* reactor-core not only implements asynchronous calls, but also has stronger expressive capabilities than coroutines. For example, if we want to know the measurement data such as the success rate and execution time of a link, we only need to call a function such as `metrics(...)`; The number of automatic retries only needs to call `retry(...)`; if you want to switch the execution thread of the data stream, you only need to execute a function like `publishOn(...)`, and the scheduling logic of the thread is under control among.


To sum up, stacked coroutines can't play a role on the Turms server, and the performance will not be better than the reactor-core under the Turms server. There are still countless pits in the ecology that need to be stepped on and verified by projects. For the Turms service The meaningless abstraction of coroutines on the end is also redundant, which only increases the difficulty of learning. The expressive ability of reactor-core is also better than that of coroutines. It is difficult for the Turms server to have a reason to use coroutines.

Of course, the content mentioned above is mainly for the Turms server project. For most Java projects, the benefits of Loom outweigh the disadvantages. In particular, third-party library authors no longer need to maintain two sets of synchronous and asynchronous implementations.
#### Supplement: Disadvantages of reactor-core

As we are [about the use of dependent libraries](https://turms-im.github.io/docs/server/development/rules#%E5%85%B3%E4%BA%8E%E4%BE%9D%E8%B5%96%E5%BA%93%E7%9A%84%E4%BD%BF%E7%94%A8) As mentioned in the chapter, the asynchronous implementation library such as `reactor-core` is the best The fatal shortcoming is that when it is combined with some dependent libraries that advocate "more encapsulation, more abstraction, and users do not need to close the implementation logic", developers can only hope that the server can always run normally, otherwise once a bug is encountered , developers will soon be unable to help but have a series of questions: "Can an asynchronous framework like reactor-core be used in a production environment? I can't even find where the exception is thrown. Can such code really be maintained?" Therefore, some technicians of the project team regretted using reactor-core, and even adopted other languages, such as Go, to rewrite the current Java project.

For example, the console now reports an error "Netty Prompt: The reference count of ByteBuf has reached 0, and the release operation cannot be performed again". Pay special attention, this does not omit any useful log information, this is all the useful information that developers can really see from the log. Even this log has been stripped of misleading information, namely its stack information. If developers go to Debug according to the stack information, they will never be able to find the real Root Cause. And can developers know why this exception occurs and locate which module caused this exception based on this line of logs? This is a bug that actually happened in Turms, and it is also the only one that spent more than 6 hours reading all the network I/O related source codes that Turms depends on, and troubleshooting the most difficult bug of Root Cause: [Memory leaks when Turms uses the previous buffer reference to release a recycled pooled buffer](https://github.com/turms-im/turms/issues/786).

In short, to use `reactor-core` well, three conditions must be met:

1. All key codes must be controllable, otherwise when something goes wrong, you can only hope for:

  * The developers of the third-party library have high technical level and solid code design skills. If the third-party dependency is also based on asynchronous programming, the requirements are even higher. The author must be able to predict the exceptions that upper-layer developers may encounter, and throw the exceptions to the upper-layer application through asynchronous means.

  * The third-party library is not complicated, and you can quickly read the relevant source code.

    A good example is: reactor-netty. Its developers have a high technical level and solid design skills. The code is also relatively streamlined and easy to read.

2. The exception and print log must be passed in a standardized manner. Even for asynchronous programming, as long as exceptions are transmitted and logs are printed in a standardized manner, we can immediately see the cause of most bugs through a single log. Only a few bugs may need to be associated with multiple logs for troubleshooting. If you can't do this, you can only resign yourself to fate when things go wrong.

3. There must be engineers in the team who are proficient in asynchronous programming.

As long as one of the above conditions is missing, developers will sooner or later encounter such difficult bugs as "Netty prompt: the reference count of ByteBuf has reached 0 and cannot be released again". Therefore, for general technical teams, we recommend The Loom project, not reactor-core. Of course, it may be more recommended to switch programming languages. However, the Turms project can now meet the above conditions, and there is no longer the situation of "extremely difficult to debug".

Extras:

* Some articles will say that asynchronous frameworks like reactor-core are easy to write callback hell. However, as mentioned above, reactor-core itself has a strong expressive ability. In fact, developers "can write several layers of call levels if they want to design several layers". In other words, if the highest calling level of a function is 5 levels, then reactor-core can be used to write 5/4/3/2/1 level codes. In practice, the nested callback functions of the Turms server are all nested to reduce intermediate objects or implement stack allocation (rather than heap allocation). For details, see the source code of the Turms server.
* When developing the turms-admin management system, we usually try to avoid using `await/async` as much as possible. The reason is that turms-admin will eventually transpile into ES5 syntax, and the functions modified by `await/async` are in the `source map `After closing, it is very difficult to debug, so try to avoid `await/async`.