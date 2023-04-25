# Configuration

## Importance

There are many business scenarios for instant messaging, so different businesses have vastly different requirements for hardware resources (for example: architecture that requires a database and architecture that does not require a database). In order to effectively utilize server resources, please be sure to carefully understand the configuration parameters provided by the Turms server.

* Scenario 1: 100% message reachability vs actively discarding messages

    * In social applications, messages are generally required to have a 100% reachability rate. Conversely, for live chat room applications, the server will even actively discard user messages or send messages to only some users in the chat room according to message priority and server load.
    * For the former, Turms uses Redis to pull the incremental `sequence ID` at the session level to achieve 100% delivery of messages. For the latter, Turms will actively discard messages based on messages in memory and server load information. The two have completely different but reasonable requirements for message reachability, so the implementation of the two also has completely different requirements for hardware configuration.

* Scenario 2: Read-diffused message storage vs zero-message storage

    * Application A is an instant messaging application mainly for business customers. This application has a requirement: when a user sends a message in the business group, the user can know whether other **every user** in the group has read the message, even if the user finishes sending the message When it goes offline, when it goes online again, it can still check the read status of other people's messages.

      Therefore, if a business group has 100 users, when one of the users sends a message, Turms needs to store 1 Message and 1 Conversation (Turms adopts the read diffusion message model, and please note: this Conversation record will carry 99 last read time of a group member).

    * Application B is a live barrage chat application, which handles messages very casually. When a user sends a message on a live channel, the user not only does not need to know the read status of other users, but even the message itself does not require storage (that is, no offline message requirement).

      Therefore, if a live channel has 100 members, when one of the users sends a message, Turms needs to store 0 Message and 0 Conversation records.

    * Contrast that application A requires the message storage function, while application B does not. Therefore, the table for storing messages is not even used in the architecture design of the B application (of course, in practical applications, user messages are generally stored for user behavior analysis). Therefore, the hardware requirements of the two are also quite different.

## Local configuration and global configuration

The Turms server has two types of configurations: local configuration and global configuration, among which:

|                    | Local Configuration                                          | Global Configuration                                         |
| ------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| Application Domain | Valid only for the current node                              | Valid for all nodes in the cluster                           |
| Storage location   | Stored in the local `application-[profile].yaml` file        | Stored in the `turms-config/shared-cluster-properties` collection in the MongoDB database |
| Mutable            | For properties marked with the `MutableProperty` annotation, users can perform real-time updates with zero downtime when the Turms cluster is running through the dedicated API interface for administrators | Same as the left                                             |

## Configuration Categories

The configuration is divided into two categories, one is the configuration of the JVM, and the other is the configuration of the Turms server.

### JVM configuration

The JVM default configuration file of turms-gateway is: `turms-gateway/dist/config/jvm.options`

The default JVM configuration file for turms-service is: `turms-service/dist/config/jvm.options`.

Users generally use the default JVM configuration and do not need to modify the JVM configuration by themselves.

If the user wants to modify the JVM configuration, there are two ways:

1. Modify the environment variable `TURMS_GATEWAY_JVM_CONF` (for turms-gateway) or `TURMS_SERVICE_JVM_CONF` (for turms-service) and point to the custom JVM configuration file to use a fully custom JVM configuration. The following takes modifying the JVM configuration of turms-gateway as an example, the specific modification method:

    1. If you start via the `run.sh` script, you can use something like `export TURMS_GATEWAY_JVM_CONF=<your-jvm-options-file-path> && sh run.sh -f` to set the environment variable and start.

    2. If you start from a Docker image, you can use something like:

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

       Note: The above `TURMS_GATEWAY_JVM_CONF` path points to the path inside the mirror, not the path of the host. If you want to use the configuration file in the host, you need to use Docker's mounting mechanism, such as:

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

    3. If via Docker Compose, you can use something like:

      ::: code-group

      ```shell [Unix]
      TURMS_GATEWAY_JVM_CONF=<your-jvm-options-file-path> docker compose -f docker-compose.standalone.yml up --force-recreate
      ```

      ```powershell [PowerShell]
      $env:TURMS_GATEWAY_JVM_CONF=<your-jvm-options-file-path>;docker compose -f docker-compose.standalone.yml up --force-recreate
      ```

      :::

       Note: The above `TURMS_GATEWAY_JVM_CONF` path points to the path inside the mirror, not the path of the host. If you want to use the configuration file in the host machine, you need to modify the `docker-compose.standalone.yml` configuration file to use Docker's mounting mechanism, such as:

       ```yaml
       turms-gateway:
         volumes:
           - <your-jvm-options-file-path>:/opt/turms/turms-gateway/config/jvm.options:ro
       ```

2. Modify the environment variable `TURMS_GATEWAY_JVM_OPTS` (for turms-gateway) or `TURMS_SERVICE_JVM_OPTS` (for turms-service) to append a custom JVM configuration based on the JVM configuration file and override the declared JVM configuration. The specific modification method is the same as above, so it will not be repeated.

   Note: The format of this variable is: `-D<name>=<value> -D<name>=<value>`, such as: `-Dspring.profiles.active=DEV -Dturms.cluster.discovery.address.advertise -host=myturms`.

### Turms server configuration

Turms configurations fall into four broad categories:

* Turms Gateway configuration: corresponding to the unique configuration of the turms-gateway server
* Turms Service configuration: corresponding to the unique configuration of the turms-service server.
* Common general configuration: Common general configuration can be shared by turms-gateway and turms-service servers.
* The configuration of the plug-in itself: the configuration provided by the Turms server plug-in itself.

#### Configuration method

1. The aforementioned `TURMS_GATEWAY_JVM_CONF` or `TURMS_SERVICE_JVM_CONF`, and `TURMS_GATEWAY_JVM_OPTS` or `TURMS_SERVICE_JVM_OPTS` can also be used to configure the parameters of the Turms server.
2. Modify the configuration file under `application.yaml`. specific method:
    1. Directly modify the `application.yaml` file under the server in the warehouse. Because if the configuration source file is modified, the user cannot use the official Turms Docker image, and needs to package it into a JAR package and create an image. Therefore, this method is generally only used for local development and testing, not for online use. environment.
    2. Use the Docker mounting method mentioned above to mount the custom server configuration file to the path `/opt/turms/turms-gateway/config/application.yaml`.
3. Call the Admin HTTP API to modify, the path is: `PUT /cluster/settings`.

Reminder: For the configuration of the plug-in itself, its configuration method is the same as that of the Turms server, except that it does not support dynamic modification using the Admin HTTP API for the time being, it can also be configured based on the above two methods ①②. For example, if a plug-in is a plug-in for the turms-gateway server, then the user can put the configuration of the plug-in itself into the `TURMS_GATEWAY_JVM_OPTS` environment variable of the turms-gateway server.

#### Profiles

If developers need to use different configurations for the same Turms server configuration and switching, configuration sets can be used.

By default, the configuration hard-coded in the source code of the Turms server and the configuration specified in the `application.yaml` file is the configuration of the default production environment. If developers want to switch to use other configuration sets, they can use other configuration sets by modifying the `spring.profiles.active` configuration in the `application.yaml` file.

For example, a common use case: when developing and debugging locally, if you want to switch the production environment configuration to the default development environment configuration, the developer can change the `spring.profiles.active` value in the `application.yaml` file to `dev `, so that the Turms server will adopt the configuration specified in the two files `application.yaml` and `application-dev.yaml` (default development environment configuration), and the configuration priority in the `application-dev.yaml` file Higher, will override the default configuration.

#### Introduction to Configuration Parameters

Since there are hundreds of configuration items on the Turms server, this section only briefly introduces the configuration categories. If readers want to refer to the specific configuration items, they can refer to the codes of each configuration class under the `im.turms.server.common.infra.property` package, or continue to browse the configuration item descriptions provided in the `Configuration Items` section below.

Reminder: After you compile the `turms/turms-gateway` server project locally, the compiler will generate the `target/classes/META-INF/spring-configuration-metadata.json` file. IntelliJ IDEA can automatically detect this file, and provide configuration prompts and completion functions when you enter Turms-related configuration, as shown in the following figure:

![](https://raw.githubusercontent.com/turms-im/assets/master/turms/configuration-code-completion.png)

##### Tumrs Service configuration

| Category          | Class                  | Field Name   | Description                                          | Supplement                                                   |
| ----------------- | ---------------------- | ------------ | ---------------------------------------------------- | ------------------------------------------------------------ |
| Admin API         | AdminApiProperties     | adminApi     | Related configuration of administrator API interface |                                                              |
| Client API        | ClientApiProperties    | clientApi    | Related configuration of client API interface        |                                                              |
| Fake data         | FakeProperties         | fake         | Fake data related configuration                      |                                                              |
| Data Source       | MongoProperties        | mongo        | MongoDB database related configuration               | Turms completely reuses the URI configuration of MongoDB. Reference document: <br />https://docs.mongodb.com/manual/reference/connection-string/ |
|                   | TurmsRedisProperties   | redis        | Redis database configuration                         |                                                              |
| Statistics        | StatisticsProperties   | statistics   | Statistics related configuration                     |                                                              |
| Notification      | NotificationProperties | notification | Notification related configuration                   |                                                              |
| File storage      | StorageProperties      | storage      | Storage related configuration                        |                                                              |
| Business behavior | UserProperties         | user         | User-related configuration                           |                                                              |
|                   | GroupProperties        | group        | Group related configuration                          |                                                              |
|                   | ConversationProperties | conversation | Message conversation service related configuration   |                                                              |
|                   | MessageProperties      | message      | Message service related configuration                |                                                              |

##### Turms Gateway configuration

| Category          | Class                         | Field Name          | Description                                                  |
| ----------------- | ----------------------------- | ------------------- | ------------------------------------------------------------ |
| Admin API         | AdminApiProperties            | adminApi            | Related configuration of admin API                           |
| Client API        | ClientApiProperties           | clientApi           | Client-oriented HTTP access layer related configuration (that is, ReasonController related configuration) |
|                   | NotificationLoggingProperties | notificationLogging | Notification log related configuration                       |
| Service interface | UdpProperties                 | udp                 | UDP server related configuration                             |
|                   | TcpProperties                 | tcp                 | TCP server configuration                                     |
|                   | WebSocketProperties           | websocket           | WebSocket server related configuration                       |
|                   | DiscoveryProperties           | serviceDiscovery    | Service discovery related configuration                      |
| Fake data         | FakeProperties                | fake                | Fake data related configuration                              |
| Data source       | MongoProperties               | mongo               | MongoDB database related configuration                       |
|                   | TurmsRedisProperties          | redis               | Redis database configuration                                 |
| Business Behavior | SimultaneousLoginProperties   | simultaneousLogin   | Multi-login related configuration                            |
|                   | SessionProperties             | session             | session related configuration                                |

##### Common general configuration

| class                 | field name  | description                                                  |
| --------------------- | ----------- | ------------------------------------------------------------ |
| ClusterProperties     | cluster     | Cluster related configuration. Including configuring current running node information, service discovery registration information, configuration center information, RPC parameters |
| HealthCheckProperties | healthCheck | Monitor node health status                                   |
| IpProperties          | ip          | Public network IP detection related configuration            |
| LocationProperties    | location    | User coordinate related configuration                        |
| LoggingProperties     | logging     | Basic logging configuration                                  |
| PluginProperties      | plugin      | Plugin related configuration                                 |
| SecurityProperties    | security    | User and administrator password encryption related configuration |
| UserStatusProperties  | userStatus  | User session (connection) status related configuration       |

##### The configuration of the plugin itself

If users want to check the configuration items of the official Turms server plugin, they can read the corresponding plugin documentation, which will list the configuration items provided by the plugin.

#### server port number configuration

| Server                      | Configuration Item                   | Port              | Function                                                     |
| --------------------------- | ------------------------------------ | ----------------- | ------------------------------------------------------------ |
| turms-admin                 |                                      | 6510 (HTTP)       | Provides the web page of the background administrator system |
| turms-service/turms-gateway | turms.cluster.connection.server.port | 7510 (TCP)        | Used for RPC of turms-service and turms-gateway servers      |
| turms-service               | turms.service.admin-api.http.port    | 8510 (HTTP)       | Provide admin API and metrics API                            |
| turms-gateway               | turms.gateway.admin-api.http.port    | 9510 (HTTP)       | Provide metrics API                                          |
| turms-gateway               | turms.gateway.websocket.port         | 10510 (WebSocket) | Interact with the turms-client-js client                     |
| turms-gateway               | turms.gateway.tcp.port               | 11510 (TCP)       | Interact with clients                                        |
| turms-gateway               | turms.gateway.udp.port               | 12510 (UDP)       | Interact with clients (clients are not supported yet). <br />Note: UDP server is an experimental function, not in the first release plan |

## configuration items

Note: The table below does not include the configuration of the Turms server plugin.

|Configuration Items|Global Attributes|Variable Attributes|Data Type|Default Value|Description|
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