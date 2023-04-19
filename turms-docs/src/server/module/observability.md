# Observability

In order to achieve high reliability of the system and enable the system to have the ability to predict capacity and troubleshoot abnormalities (such as detecting DDoS attacks), the construction of the system's observability system is very important. If a server does not provide support for the observability system, no matter how rich its functions are, it is just a toy project.

Moreover, the derivative products generated under the observability system are also an important asset of the enterprise. If business operators ignore the construction of the observability system, they will not be able to effectively analyze user behavior and preferences, let alone optimize business strategies , It also means that the company has given up a considerable amount of wealth.

Turms, like other conventional servers, divides the specific implementation of observability into three categories, namely: `metrics (aggregated values)`, `logs (events)`, `link tracking (request-oriented)`.

## metrics

Metrics are composed of aggregated values, and are generally divided into `system metrics`, `application metrics` and `business metrics`. System metrics are used to observe the running status and trends of the system or container; application metrics are used to observe the running status and trends of the JVM and Turms application layer; business metrics are used to observe the status and trends of business development. In the case of non-displacement sampling by default, it only takes up a very small part of the memory space.

In addition, in terms of specific code implementation, Turms' measurement system is implemented based on the mainstream measurement sampling library [Micrometer](https://github.com/micrometer-metrics/micrometer). And provide interface `/metrics` to export JSON format, `/metrics/prometheus` to export OpenMetrics format, `/metrics/csv` to export CSV format.

Note: There is also a type of statistical data that consumes system resources relatively, such as daily/weekly/monthly active, user retention rate, etc. The implementation of these functions is very routine. However, such relatively high-latitude functions are suitable for specialized log services or products, so Turms does not directly provide such data.

### System Metrics

Cloud service vendors also provide this type of measurement, and their measurement points are usually more abundant, and functions such as storage, display and analysis are also available out of the box. Turms provides the following important metrics mainly to fulfill the responsibility of a server, which cannot meet the customization needs of cloud users and some users. For users who can use cloud services, priority should be given to using cloud services.

| Category | Name | Type | Meaning |
| ------------------- | ------------------------ | --------- | ------------------------ |
| Uptime (running time) | process.uptime | TimeGauge | How long the process has been running |
| | process.start.time | TimeGauge | Process start time |
| Processor | system.cpu.count | Gauge | Number of available CPU cores for a process |
| | system.load.average.1m | Gauge | System CPU load in the last minute |
| | system.cpu.usage | Gauge | Recent system CPU usage |
| | process.cpu.usage | Gauge | Recent process CPU usage |
| Memory (memory) | system.memory.total | Gauge | system physical memory size |
| | system.memory.free | Gauge | Available physical memory size of the system |
| | system.memory.swap.total | Gauge | System Swap memory size |
| | system.memory.swap.free | Gauge | Available Swap memory size of the system |
| Storage | disk.total | Gauge | Total storage capacity |
| | disk.free | Gauge | Available storage capacity |
| FileDescriptor | process.files.open | Gauge | Number of open file descriptors |
| | process.files.max | Gauge | The maximum number of open file descriptors |

### Apply Metrics

#### JVM Metrics

The following description is based on the HotSpot virtual machine. Turms does not provide official support for other virtual machines.

| Category | Name | Type | Meaning |
| ------ | ------------------------- | ------- | ------------------------------------------------------------ |
| GC | jvm.gc.max.data.size | Gauge | Maximum available heap memory in the old generation |
| | jvm.gc.live.data.size | Gauge | After GC, the memory space occupied by the old generation |
| | jvm.gc.memory.allocated | Counter | Total allocated memory space in Eden |
| | jvm.gc.memory.promoted | Counter | Total allocated memory space in the old generation |
| | jvm.gc.pause | Timer | GC time-consuming |
| Memory | jvm.buffer.count | Gauge | The number of memory buffers in each memory buffer pool |
| | jvm.buffer.memory.used | Gauge | The used memory of each memory buffer pool<br />Note: The off-heap memory used by the Turms application layer is recorded here |
| | jvm.buffer.total.capacity | Gauge | The total capacity of each memory buffer pool |
| | jvm.memory.used | Gauge | Used memory of each memory pool<br />Note: The off-heap memory used by the Turms application layer will not be recorded here |
| | jvm.memory.committed | Gauge | Available memory for each memory pool |
| | jvm.memory.max | Gauge | Maximum memory of each memory pool |
| Thread | jvm.threads.peak | Gauge | Peak number of threads |
| | jvm.threads.daemon | Gauge | Number of daemon threads |
| | jvm.threads.live | Gauge | Number of currently active threads |
| | jvm.threads.states | Gauge | Number of threads in each thread state |
| Class | jvm.classes.loaded | Gauge | Number of loaded classes |
| | jvm.classes.unloaded | Counter | Number of unloaded classes |

Note: Turms uses the off-heap memory in the memory pool (that is, allocates off-heap memory through Netty's PooledByteBufAllocator) when performing network IO operations. By deliberately not releasing the off-heap memory and caching these off-heap memory, To avoid inefficient off-heap memory allocation and release operations, the memory usage rate of Turms will continue to rise, and there is no overall downward trend. It's not a memory leak, it's just that Turms is caching this off-heap memory.

#### Inter-cluster TCP connection metrics

In the connection measurement, because the number of nodes on the server side is limited, each measurement will use the remote address of the TCP terminal as a tag to distinguish the respective measurement data of each TCP terminal, so as to observe the communication between nodes in more detail.

##### TCP server

| type | name | type | meaning |
| ------------------------ | ---------------------------------------- | ------------------- | ---------------- |
| Connection | turms.node.tcp.server.data.received | DistributionSummary | Bytes Received |
| | turms.node.tcp.server.data.sent | DistributionSummary | Number of bytes sent |
| | turms.node.tcp.server.errors | Counter | Connection exception trigger times |
| | turms.node.tcp.server.tls.handshake.time | Timer | TLS handshake time |
| ByteBufAllocator(memory) | TODO | | |

##### TCP client

| type | name | type | meaning |
| ------------------------ | ---------------------------------------- | ------------------- | ---------------- |
| Connection | turms.node.tcp.client.data.received | DistributionSummary | Bytes Received |
| | turms.node.tcp.client.data.sent | DistributionSummary | Number of bytes sent |
| | turms.node.tcp.client.errors | Counter | Connection exception trigger times |
| | turms.node.tcp.client.tls.handshake.time | Timer | TLS handshake time |
| | turms.node.tcp.client.connect.time | Timer | TCP connection establishment time |
| | turms.node.tcp.client.address.resolver | Timer | Address resolution time |
| ByteBufAllocator(memory) | TODO | | |

#### RPC metrics

| Name | Type | Meaning |
| ------------------------- | ------- | ------------------------- |
| rpc.request.subscribed | Counter | The number of times a certain type of RPC request has been processed |
| rpc.request.flow.duration | Timer | The processing time of a certain type of RPC request |

#### Admin API Metrics

Because the administrator's IP can be unlimited, each measurement **does not** use the remote address of the peer end as a tag to distinguish the respective measurement data of each end.

| type | name | type | meaning |
| ------------------ | ----------------------------- | - ------------------ | ---------------- |
| Connection | admin.api.data.received | DistributionSummary | Bytes Received |
| | admin.api.data.sent | DistributionSummary | Bytes Sent |
| | admin.api.errors | Counter | Connection exception trigger times |
| | admin.api.tls.handshake.time | Timer | TLS handshake time |

#### Turms Client Metrics

In the connection measurement, because the number of clients is infinite, each measurement **does not** use the remote address of the peer end as a tag to distinguish the respective measurement data of each end. In addition, the connection metrics use the tag `uri` to distinguish the respective measurement data of the three types of TCP/UDP/WebSocket connections.

| type | name | type | meaning |
| ---------------------------- | --------------------------------------- | ------------------- | ---------------------------- |
| Connection | turms.client.network.data.received | DistributionSummary | Bytes Received |
| | turms.client.network.data.sent | DistributionSummary | Bytes Sent |
| | turms.client.network.errors | Counter | Connection exception trigger times |
| | turms.client.network.tls.handshake.time | Timer | TLS handshake time |
| | turms.client.network.connect.time | Timer | Connection establishment time |
| | turms.client.network.address.resolver | Timer | Domain name resolution time |
| Request | turms.client.request.subscribed | Counter | Number of times a certain type of client request has been processed |
| | turms.client.request.flow.duration | Timer | The processing time of a certain type of client request |
| ConnectionProvider (connection pool) | TODO | | |
| ByteBufAllocator(memory) | TODO | | |


### Business Metrics

| Server | Name | Type | Meaning |
| ------------- | --------------- | ------- | ----------- -|
| turms-gateway | user.logged_in | Counter | Number of logged in users |
| | user.online | Gauge | Number of online users |
| turms-service | user.registered | Counter | Number of registered users |
| | user.deleted | Counter | Number of deleted users |
| | group.created | Counter | Number of created groups |
| | group.deleted | Counter | Number of deleted groups |
| | message.sent | Counter | Number of sent messages |

## logs

Each log corresponds to the events that occur when the Turms server is running, and is used to track the running status of the system and generate high-latitude statistical data. There are two categories of logs in Turms, namely `application logs` and `business logs`. The number of application running logs is small and takes up little space, and follows the principle of precision and accuracy. However, the client API access log designed for business analysis is different. It is the basic data of most statistical data and an important asset of the enterprise. Therefore, Turms defaults and recommends 100% sampling of it, which consumes a lot of storage.

Notice

* The data format design of all logs, metrics and link tracking in Turms is designed with "simple and fast, convenient and fast query" and "accurate sampling, convenient for log service analysis", but Turms itself does not provide any log analysis function.

* The log timestamp and log cutting of Turms are based on UTC time, not system time.

* When Turms has `FATAL` level logs, manual intervention is required to fix them. The currently existing `FATAL` level log types are:

  * Detects that a table in the database has been dropped, or renamed.

  * It is detected that the file system storing the log is full and cannot continue to print the log.

    Note: Turms cannot continue to print logs when it detects that the file system is full, so Turms will not print this `FATAL` level log until the user has not made enough space. Turms will optimize this later to ensure that the log can be printed out in a timely manner. Of course, since the current system is equipped with a monitoring system, when the operation and maintenance personnel receive a warning that the storage space exceeds the custom threshold, they should deal with it in advance.

* Turms will continuously print the log and print the log into a file for storage in the file system. When the storage space of the file system is insufficient, **Turms server will stop printing logs**, but will not discard the logs, but will accumulate the logs in the memory, so when too many logs accumulated in the memory and the memory is insufficient, **It will also trigger the automatic protection mechanism of the Turms server to reject all user requests**, so as to prevent the Turms server from going down due to insufficient memory. Therefore, the operation and maintenance personnel must ensure that the system where the Turms server is located has sufficient storage space at all times.

  Further reading: [Memory health detection mechanism of Turms server](https://turms-im.github.io/docs/server/module/system-resource-management#%E5%86%85%E5%AD%98%E5%81%A5%E5%BA%B7%E6%A3%80%E6%B5%8B)

### Self-developed implementation (expanding knowledge)

#### reason

1. Turms defaults and highly recommends 100% sampling of the client API, which requires efficient implementation of Logging
2. The implementation of third-party Logging is too redundant, with low performance and high memory usage
3. Avoid third-party Logging developers writing critical bugs like [Remote code injection in Log4j](https://github.com/advisories/GHSA-jfh8-c2jp-5v3q) due to lack of security common sense
4. The log implementation of Turms is "almost no function implemented", and the implemented functions are also implemented according to almost the highest performance standard (we directly write the basic data of Java into `DirectByteBuf`, and directly write to the file descriptor , there is no string copy), so the throughput of this implementation can be several times higher than log4j2 async logger, and the memory overhead is several times higher

#### Implementation

The Turms log implementation is very streamlined, and only implements a few percent of the core functions of the standard log library. The main steps for printing logs are:

For regular logs:

    * Call `im.turms.server.common.infra.logging.core.logger.AsyncLogger#doLog` function
    * The `doLog` function internally allocates a block of off-heap memory through `PooledByteBufAllocator.DEFAULT`, and traverses the message, writes non-placeholders directly into this memory, skips placeholders and writes specific parameters, and finally writes this memory Put it in the MPSC queue for log processing (based on `MpscUnboundedArrayQueue` of jctools)
    * When the log processing thread detects that there is a new log (that is, the `ByteBuffer` object), it will write the off-heap memory to the `FileChannel` of the NIO package (it can be a console or a file). Under the system, `pwrite` will be called to directly write the off-heap memory into the file descriptor

For various API logs (such as client API logs), we use a more customized implementation, namely:

* The caller directly writes API information (such as client IP, request size, etc.) into `DirectByteBuf`, and passes this Buffer to `AsyncLogger#doLog` function
* The `doLog` function writes the general template information of the log (such as timestamp, node ID, etc.) into another `DirectByteBuf`, and splices it with the above `DirectByteBuf` to form a `CompositeByteBuf`
* When the log processing thread detects that there is a new log (that is, the `CompositeByteBuf` object), it will write the off-heap memory to the `FileChannel` of the NIO package (it can be a console or a file). Under the system, `pwrite` will be called to directly write the off-heap memory into the file descriptor

Of course, the performance of Turms writing logs can reach the extreme.

Replenish

* Although there is a more efficient way of writing, that is, across the Java implementation, instead of using the `FileChannel` of the NIO package, it directly calls the underlying JNI implementation. Write to the file descriptor. However, considering the maintainability of the code, and Java does not open these underlying functions by default, this method is not adopted.

* The memory mentioned above is allocated through `PooledByteBufAllocator.DEFAULT`, and there is no limit on the upper limit of memory usage, and "dare" to use `MpscUnboundedArrayQueue` to store logs without limiting the maximum capacity. This is because the Turms server has its own [memory management mechanism](https://turms-im.github.io/docs/server/module/system-resource-management), which can guarantee the upper limit of memory usage, At the same time, the used memory is gradually released.

* Turms does not support and will not support in the future: add console text styling. Because you need to use `ANSI escape codes` to style the console text, and the log file does not need to store these characters, so to achieve this function, we need to maintain a ByteBuf for the console and the log file respectively, and a log needs to consume double memory, so the implementation is not considered.

  In addition, developers can use third-party tools or plug-ins, such as the `Grep Console` plug-in of `Intellij IDEA`, to add styles to the logs of the Turms server console.

* About "why there are garbled characters when printing non-ASCII characters", this is because:

  background:

  * The `byte[] value` inside the Java 17 `String` class has and can only store `LATIN-1` or `UTF-16` encoded data
  * **Turms server itself has and only prints ASCII characters** (Turms server will not print any text entered by users or administrators)
  * Log printing is a frequently used function, meaningless memory copying is absolutely prohibited.

  In the above background, when Turms prints `String`, it does not get its byte data through `getBytes("UTF-8")`, but directly obtains the internal `LATIN-1` of `String` through `Unsafe` Or `UTF-16` encoded byte data, so the log file may be `LATIN-1` and `UTF-16` mixed encoding.

  When users view log files in `UTF-8` encoding, ASCII characters in `LATIN-1` encoding can be displayed correctly, and ASCII characters in `UTF-16` encoding can also be displayed, but each ASCII character will be more With a null character (binary encoding `0000 0000`), characters that are not compatible with other encodings will be displayed as garbled characters, so if the Turms server prints non-ASCII characters, the user will see garbled characters.

  In addition, unless Java supports storing UTF-8 encoded byte data in the future, the Turms server will not consider using an inefficient implementation such as `getBytes("UTF-8")`.

In summary, the supplementary content has once again verified what we have repeatedly mentioned in each chapter: "multiple functions" is likely to be a shortcoming for servers that pursue performance.


### Reasons for not using JSON format

With the development of microservices, logs in JSON format are becoming more and more popular. For example, MongoDB began to support logs in JSON format in version 4.4. There are three main advantages of using the JSON format:

* Greatly unified the log format of each server. Especially for companies with dozens/hundreds/thousands of heterogeneous servers, it is mandatory for each project to use the JSON log format
* Each programming language has good support for JSON, and there is almost no difficulty in log printing and analysis
* The log services of various cloud vendors have good support for logs in JSON format, which can be used out of the box

The reasons why the Turms server does not use the JSON format are:

* The structure of the Turms server is very simple, and there is no need to unify the log format through JSON.
* JSON serialization requires additional memory and CPU resources, and has a large storage overhead. If compression technology is used, additional CPU resources will be occupied. In particular, the CPU resources required for serialization plus compression are even higher than the CPU resources required by the Turms server to process business requests, which is unacceptable for Turms.
* JSON format is actually not good in readability of raw data. Because the original log is displayed in a single line, a line represents an event. When the JSON format is displayed in a single line, it will bring a lot of "noise". A large amount of JSON metadata, JSON keys and JSON values are criss-crossed. It is more laborious to read the original data directly. On the other hand, the client API access log of the Turms server uses `|` delimiters to split each field. Users only need to read a few more logs for the first time, and then they can reflect what information each field represents.

Of course, adopting the traditional single-line format will cause relatively complicated cloud service parsing and inflexible configuration. However, considering that this kind of thing is configured once and for all, considering the above situation, the Turms server log does not use the JSON format, but still uses the traditional single-line format.

### Category

#### GC log

It is used for JVM performance testing, analysis and tuning, and troubleshooting and positioning problems.

The server JVM GC configuration of turms-gateway is: `-Xlog:gc*,gc+age=trace,safepoint:file=${TURMS_GATEWAY_HOME}/log/turms-gateway-gc.log:utctime,pid,tags:filecount =32,filesize=32m`

The server JVM GC configuration of turms-service is: `-Xlog:gc*,gc+age=trace,safepoint:file=${TURMS_SERVICE_HOME}/log/turms-service-gc.log:utctime,pid,tags:filecount =32,filesize=32m`

#### Server running log

Describe the main events that occur in the Turms server, such as the transition of the RPC connection state, the occurrence of server-side errors in request processing, etc.

File name: `turms-gateway.log` (turms-gateway server); `turms-service.log` (turms-service server)

Composition: event sending time, log level, server type, node ID, trace ID, thread, class, message. Among them, the main function of the server information is to distinguish the source node of the log during the distributed log collection process. Other types of logs also use this log format (except for client API access logs and notification logs that do not record "class" information), they just use a customized message format in the "message" section.

Format: `%d{${sys:LOG_DATEFORMAT_PATTERN}}{GMT+0} ${sys:LOG_LEVEL_PATTERN} ${myctx:NODE_TYPE} ${myctx:NODE_ID} %-19.19X{traceId} %t %-40.40c{ 1.} : %m%n${sys:LOG_EXCEPTION_CONVERSION_WORD}`

Parsing Regex: `(?P<time>\d{4}-\d{2}-\d{2}\s\d{1,2}\:\d{2}\:\d{2} \.\d{3})\s+(?P<level>[A-Z]{4,5})\s+(?P<node_type>[A-Z])\s+(?P<node_id>\S*)\ s+\[(?P<trace_id>.{19})\]\s+(?P<thread>\S*)\s+(?P<class>\S*)\s+:\s(?P<msg >.*)`

Example:

```spreadsheet

2021-08-08 14:02:53.123 INFO S xyzjjrhv parallel-2 i.t.s.c.c.s.c.ConnectionService : [Client] Connecting to member: fqfgnyop[192.168.3.2:7511]. Retry times: 0
```

#### Admin API access log (audit log)

Record various operations of the administrator on the Turms server.

File name: `turms-service-admin-api.log`

Format: `Administrator Account|Administrator IP|Request ID|Request Time|Request API|Request Parameters|Processing Result|Processing Time|Processing Exception Information`. in:

* Session information: administrator account, administrator IP
* Request information: request ID, request time, request API, request parameters. Among them, the administrator can obtain the `request ID` through the header `X-Request-ID` in the HTTP response, and cooperate with the log for troubleshooting or behavior tracking
* Response information: processing result, processing time, processing exception information

Example:

```spreadsheet
2021-09-02 07:19:27.219 INFO S wzocsebz 3501287524626242885 Thread-28 : turms|0:0:0:0:0:0:0:1|db612e82-199|2021-09-02 07:30:30.414 |updateUser|1|{ids=[1], updateUserDTO=UpdateUserDTO[password=******, name=null, intro=null, profileAccess=null, permissionGroupId=null, registrationDate=null, isActive=null]} |TRUE|
```

#### Client API access log

Since the client API access log data is an important asset of the enterprise, it is emphasized again that the log may seem simple and routine, but the operational data derived from it can be as high as hundreds of items. It is better to reduce the throughput of the server due to 100% sampling, and it is not recommended to modify the relevant configuration. Unless you clearly know and can bear the consequences of modifying the parameters.

##### turms-gateway server

File name: `turms-gateway-client-api.log`

Format: `session ID|user ID|device|version|IP|request ID|request type|request size|request time|response status code|response data type|response size|processing time`. in:

* Session information: session ID, user ID, device, version, IP
* Request information: request ID, request type, request size, request time
* Response information: response status code, response data type, response size, processing time

Example:

```spreadsheet
2021-08-17 13:21:10.082 INFO G ocnpinxk 4073578036035627538 gateway-tcp-worker-18-2 : 1669286372|100|DESKTOP|1|0:0:0:0:0:0:0:1|62757346898281199 CREATE_GROUP_MEMBER_REQUEST|32|2021-08-17 13:21:10.079|1201||21|3
2021-08-17 13:21:10.086 INFO G ocnpinxk 8485909300068121199 gateway-tcp-worker-18-1 : 315622910|101|DESKTOP|1|0:0:0:0:0:0:0:1|89817887200149996 QUERY_GROUP_JOIN_REQUESTS_REQUEST|17|2021-08-17 13:21:10.082|1201||21|4
2021-08-17 13:21:10.087 INFO G ocnpinxk 195568170846055794 gateway-tcp-worker-18-2 : 1669286372|100|DESKTOP|1|0:0:0:0:0:0:0:1|787502382083874281 CREATE_GROUP_JOIN_QUESTION_REQUEST|181|2021-08-17 13:21:10.083|1201||21|4
```

##### turms-service server

File name: `turms-service-client-api.log`

Format: `user ID|device|IP|request ID|request type|request size|request time|response status code|response data type|processing time`. in:

* Session information: user ID, device, IP
* Request information: request ID, request type, request size, request time
* Response information: response status code, response data type, processing time

Example:

```spreadsheet
2021-08-17 13:25:11.809 INFO S lkumxlpd 1650561895646191481 Thread-13 : 101|DESKTOP|::1|6798130843268792999|QUERY_MESSAGES_REQUEST|28|2021-08-17|150|1080 13.8

0
```

Replenish:

* In the client API access log of the Turms server, the "start time" of a request actually refers to the moment when "the server successfully receives the data stream contained in a request, but has not yet parsed it", not "the server The moment the first byte of the request was received".
* The execution of the request is asynchronous. Assume that the execution time of a request is 1 second, but the CPU time it occupies on the Turms server may only be a few milliseconds, and the CPU is processing other requests at other times, and the CPU will not be idle and waiting.


##### Special request log processing (expanding knowledge)

In terms of logging, the most specific API request is the `delete session request`. Specifically reflected in:

The delete session request is the only request that can not be issued by the user, but is recorded in the client API access log. Specifically, it will be sent: If the client disconnects the underlying TCP connection before sending the "delete session request", then the corresponding turms-gateway will actively generate a message with the same effect as the "delete session request" when the TCP connection is closed. "The same log, in this way to ensure the logical consistency of the client API access log.

In addition, in the implementation of the client, unless the developer specifies to close the session through `DeleteSessionRequest`, by default the client will directly close the TCP connection to close the upper layer session. The current `DeleteSessionRequest` actually acts as a "placeholder". One is to maintain consistent business logic processing through the "request" model, and the other is to reserve it for more flexible closing session logic in the future.

#### Notification Log

Certain client requests and admin API requests trigger notifications to other users, such as "typing" and "friend added" notifications. This log is used for this type of notification event.

Replenish:

* There is a one-to-one correspondence between `notification logging` and `client API access logging`. Specifically, the two can be correlated through the `Trace ID` or `Request ID` field in the notification log record.
* The notification initiation operation will only be performed by turms-service. turms-service delegates the notification operation to turms-gateway through the RPC request `SendNotificationRequest`, allowing it to perform the actual notification push-down operation

##### turms-gateway server

File name: `turms-gateway-notification.log`

Format: `notification trigger user ID|send status|number of notification target users|session close status code|notification size|request type for notification`. in:

* Notification trigger user information: Notification trigger user ID
* Notification receiving user information: the number of notification receiving users, the number of online notification receiving users
* Notification information: session close status code, notification size
* Request information for notification forwarding: request type for notification forwarding

Example:

```spreadsheet
2021-09-03 00:08:22.537 INFO G hkivjeav 3166178398923546492 -client-io-15-3 : 149|1|1||75|UPDATE_FRIEND_REQUEST_REQUEST
2021-09-03 00:08:37.636 INFO G hkivjeav 8332948877634499289 -client-io-15-3 : 190|1|0||19|UPDATE_TYPING_STATUS_REQUEST
```

##### turms-service server

File name: `turms-service-notification.log`

Format: `notification trigger user ID|sending status|number of notification target users|session close status code|notification size|notification forwarding request ID|notification forwarding request type`. in:

* Notification trigger user information: Notification trigger user ID
* Notification receiving user information: the number of notification receiving users, the number of online notification receiving users
* Notification information: session close status code, notification size
* Request information for notification forwarding: request ID for notification forwarding, request type for notification forwarding

Example:

```spreadsheet
2021-09-03 00:08:22.537 INFO Shkivjeav 3166178398923546492 -client-io-15-3 : 149|1|1||75|4971734074638762694|UPDATE_FRIEND_REQUEST_REQUEST
2021-09-03 00:08:37.636 INFO Shkivjeav 8332948877634499289 -client-io-15-3 : 190|1|0||19|6469201046445182337|UPDATE_TYPING_STATUS_REQUEST
```

#### Slow log

TODO

### Collection and Analysis

Turms only provides raw data, does not provide and does not plan to provide log collection and analysis functions.

#### reason

* Now cloud vendors support advanced services such as log collection, parsing, storage, retrieval, analysis and alarm. Through SQL retrieval, obtain various high-latitude statistical data and charts (such as: daily activity, monthly activity, daily message sending volume, session retention time, new session proportion, retention rate, etc. operational data). It is precisely because this solution has become one of the best practices in the industry that Turms itself does not provide some relatively complex functions that are more suitable for big data projects.
* Log collection related techniques are routine. However, from the perspective of business value, it is difficult to reasonably plan which logs should be collected, which fields should be indexed, which logs should be analyzed in real time, and which logs should be analyzed offline. These issues are directly linked to business value and cost. Therefore, in terms of commercial value considerations, Turms can only give suggestions, rather than directly intervene.
* Log-related services and products contend, and the log-related implementation of the Turms server should remain neutral. Therefore, the Turms server itself does not connect to any SDK, and only provides original logs for log-related service collection.
* From the perspective of microservice responsibilities, the functions of the Turms server should not be too coupled.

## link tracking

### role

Request-oriented, used to quickly track the execution of requests between nodes and within specific nodes.

### accomplish

In the link tracking implementation specification [OpenTracing] (https://opentracing.io/specification), it stipulates that Trace and Span should be used as the unit of link tracking. However, compared with dozens, hundreds or even thousands of microservice applications, Turms' call link is extremely simple, and there is no need to track requests through Span information. Also, if Turms is implemented using standard OpenTracing, the link tracing additional information for many requests will be even larger than the body of most RPC requests.

Therefore, Turms only adds a field for `trace ID` to all logs. When developers are performing link tracing, they only need to query the `trace ID` field to understand all the nodes that the request passes through. , with the implementation inside the node.

## Monitoring and alarming

In the observable system, the system needs to monitor the running status of the server in real time based on metrics and logs, and give an alarm notification when an abnormality is found in the system.

Turms does not provide and does not plan to provide an alarm function. On the one hand, cloud services such as AWS CloudWatch or other related products provide extremely rich, mature and out-of-the-box functions such as collection, analysis and alarm of metrics and logs. If users are familiar with cloud service products, it usually only takes 3 to 10 minutes to purchase cloud services from scratch and implement Turms monitoring and alarming. On the other hand, from the perspective of microservice responsibilities, the functions of the Turms server should not be too coupled, and there is no need to integrate these monitoring and alarm functions.

Even if users do not plan to use the cloud server, they can also use professional and mature open source technology solutions such as `Prometheus Alertmanager`. If the user is familiar with the relevant operations, it usually only takes 10 to 60 minutes to build such a system from scratch.