# Cluster Design and Implementation

The cluster code implementation of Turms is relatively clear and easy to understand. The code implementation package is: `src/main/java/im/turms/server/common/infra/cluster`; the configuration package is: `src/main/java/im/turms/server/common/infra/property/env/ common/cluster`

## The reason for pure self-development

| | Self-developed | Third-party services |
| -------------- | ------------------------------------------------------------ | ---------------------------------------------------------------- |
| Customized functions| Turms has a lot of customized detailed requirements, and each function is linked together. If you develop it yourself, you can ensure that new requirements are realized immediately. The time required to complete a new requirement is roughly 5-60 minutes, and there is no need to write Hacky Code | Others do not necessarily provide customized functions. Even if it is done, it is usually weeks, months, or even years before new features are released to new versions. Such low efficiency is absolutely unacceptable |
| Difficulty of Learning | Services are clearly divided, codes are streamlined, and aspects can be quickly learned and mastered. Spend 10 to 30 minutes to train some basic newcomers, and newcomers can master Turms cluster services | Projects like ZooKeeper or Eureka, which are only about a certain function of microservices, have far more source code than the six major Turms below The sum of service source code. Moreover, third-party services also involve some relatively complex but completely useless functions for Turms, such as Zookeeper's Zab protocol, which only increases the difficulty of learning. To grasp the details of its implementation requires a lot of practice and source code reading |
| Implementation Difficulty | The implementation difficulty of the cluster service code is low. For example, the total difficulty of Turms' cluster service implementation is far lower than the "AC automaton algorithm based on double array Trie" mentioned in the "sensitive word filtering" function. <br />In addition, the implementation difficulty of cluster services is much lower than the implementation of IM business logic | Need to write Adapter code according to the characteristics of third-party services. Although the difficulty is low, due to the complexity of the source code of the third-party service, it is not easy to ensure that the Adapter code will always execute as expected (using the time of learning the source code of various third-party services + writing the Adapter code, it has been possible to start from Zero self-developed several sets of cluster services have been realized) |
| Deployment and O&M Difficulty | In Turms' cluster services, only the "Configuration Center Service" and "Service Registry" require MongoDB services for deployment, and both share MongoDB services. Therefore:<br />1. Since business data storage also uses MongoDB service, operation and maintenance personnel can choose to share a MongoDB service without additional deployment<br />2. Both domestic and foreign cloud vendors provide MongoDB service deployment services. You can deploy a single instance or a clustered MongoDB service with just a few clicks of the mouse, and directly realize disaster recovery in the same city | Most of the "configuration center service" and "service registration center" services supported by domestic and foreign cloud vendors are bound to specific vendors, allowing deployment flexibility very bad. On the other hand, if Turms adopts an open source solution such as Eureka, since various vendors do not provide cloud services for open source solutions such as Eureka, the operation and maintenance personnel have to purchase cloud servers for deployment and operation and maintenance by themselves. , greatly participated in the difficulty of operation and maintenance |
| Performance | Turms can combine the characteristics of business code, so that the implementation of cluster services can coordinate with each other, ensuring that no redundant data is generated throughout the process. At the same time, all network operations are implemented based on Netty, with extremely high performance | Since third-party services are based on general requirements, we have to write a lot of Adapter code for this, which increases resource overhead and makes learning more difficult. On the other hand, its own implementation cannot guarantee extreme efficiency, and some services even use blocking API |

In summary, there is almost no advantage in using third-party services, so Turms adopts a purely self-developed solution. In addition, in fact, companies with a little strength and a little customization need will choose self-research for the same reason as above.

## node

Implementation class: `im.turms.server.common.infra.cluster.node.Node`

Configuration class: `im.turms.server.common.infra.property.env.common.cluster.NodeProperties`

Each server has one and only one node class instance. The node class internally manages node information and node life cycle events, and schedules the services of each node. Undertake user-defined configuration externally, expose node services and provide some commonly used Util functions for business implementation codes to use.

## Serve

### Distributed configuration center service (Config)

Service class: `im.turms.server.common.infra.cluster.service.config.SharedConfigService`

Configuration class: `im.turms.server.common.infra.property.env.common.cluster.SharedConfigProperties`

Nowadays, basic service implementation schemes in the field of microservices are flourishing. Taking the implementation of the configuration center as an example, the implementation solutions include: ConfigMaps of K8S, configuration services of cloud service vendors (such as AppConfig of AWS), and open source implementations (such as Zookeeper). As Turms is a technology-neutral open source project, its technology stack must not be bound by vendors. But at the same time, it is necessary to ensure that these implementations can be easily supported by cloud service vendors, so that operation and maintenance personnel can "implement and deploy with a click of a mouse." At the same time, it must meet various key features such as disaster tolerance, high availability, monitorability, and easy operation. Therefore, Turms implements the configuration center through MongoDB self-development to meet all the above requirements.

The addition, deletion, modification, and query operations of the specific configuration are implemented as the addition, deletion, modification, and query operations of the conventional MongoDB database, which is very routine, so I won’t go into details. The only thing worthy of special attention is: Turms monitors configuration changes through MongoDB's Change Stream mechanism, while the official client implementation `mongo-java-driver` uses a polling mechanism to monitor configuration changes, rather than actively notifying MongoDB from the MongoDB server client.

Replenish:

* Because the "service information" of the service registry is essentially a configuration, the following service registration and discovery are also implemented based on the configuration center.
* The configuration center of the MongoDB cluster itself is also implemented based on the MongoDB server, that is, the Config server.

#### Related Admin APIs

TODO

### Service registration and discovery service (Discovery)

Service class: `im.turms.server.common.infra.cluster.service.discovery.DiscoveryService`

Configuration class: `im.turms.server.common.infra.property.env.common.cluster.DiscoveryProperties`
#### Responsibilities

The service is primarily responsible for:

* Do our best to ensure that the current node is registered in the service registry. When each node starts on the server side, it will register the information of the current node with the service registry. If the registration fails at startup (for example, the node information has been registered), it will actively shut down the server process and report the failed exception information. If the registration information of the node is abnormally deleted by the service registration center during the operation of the node (for example, the administrator deletes the data by mistake), the node will automatically re-register its information
* When the server is shut down gracefully, delete the registration information of the current node in the service registry. Note: If the server is forcibly shut down (such as when the system is directly powered off), the registration information of the node will not be deleted by the current node, but will be automatically removed after the service registration center detects a heartbeat timeout of 60 seconds. registration message. In addition, during this period, other nodes will continue to try to establish a TCP connection with this node until its registration information is removed by the service registration center
* Listen to the node addition, deletion and modification events of the service registration center to notify the "network connection service" to connect or disconnect the corresponding TCP connection
* Election Leader

#### Register node record format

There are two types of record formats for registration nodes: Member and Leader

#####Member

Class: `im.turms.server.common.infra.cluster.service.config.domain.discovery.Member`

| Field Category | Field Name | Description |
| ------------ | ----------------- | --------------------------------------------------------------- |
| Key | clusterId | Cluster ID |
| | nodeId | Node ID |
| General Information | zone | The zone where the node is located. Used as the `data center ID` in `Snowflake ID Algorithm` |
| | nodeVersion | Node version number. Used to ensure that the operations between nodes can be version compatible |
| | nodeType | The node type. Used to ensure that RPC requests can be sent to the correct node |
| | isSeed | If a node's `lastHeartbeatDate` times out by 60 seconds and `isSeed` is `false`, the node will be automatically removed from the service registry. If `isSeed` is `true`, the node will not be removed even if the heartbeat times out |
| | registrationDate | node registration time |
| | isLeaderEligible | Used to determine whether a node can participate in the election |
| | priority | Priority. Mainly used in the Leader election, the node with a high value can be preferentially elected as the Leader |
| RPC address information | memberHost | RPC host number. It is used to ensure that other nodes can communicate with it through the host number |
| | memberPort | RPC port number. It is used to ensure that other nodes can communicate with it through this port number |
| Supplementary address information | adminApiAddress | No practical effect. It is only used for administrators to know the address information of Admin API through Admin API |
| | wsAddress | No effect. It is only used for the administrator to know the address information of the client WebSocket service through the Admin API |
| | tcpAddress | No effect. It is only used for the administrator to know the address information of the client TCP service through the Admin API |
| | udpAddress | No effect. It is only used for the administrator to know the address information of the client UDP service through the Admin API |
| Status information | hasJoinedCluster | When it is True, it means that the node has successfully completed the heartbeat refresh operation. This field has no practical effect, it is only used as an indicator to indicate the node's heartbeat health status. Even if a node is unhealthy, it can still handle client requests. <br />In addition, the value of this field of each cluster node is updated by the Leader node according to `lastHeartbeatDate` of each node |
| | isHealthy | Deny service when False. Specifically, it includes: if it is the turms-gateway server, refuse to establish a new session and process user requests; if it is the turms-service server, refuse to process the RPC request sent by the turms-gateway server; When the client chooses RPC to respond to the server, it only selects from healthy nodes |
| | isActive | When it is False, it means that the node is prohibited from processing client requests. The value of this field can only be updated through the Admin API. It can be used to gradually cut off the flow of nodes during the grayscale release, and then perform shutdown update operations |
| | lastHeartbeatDate | Record the last heartbeat refresh time, used by the Leader node to update `hasJoinedCluster` information based on this value |

##### Leader

| Field Category | Field Name | Description |
| -------- | ---------- | --------------------------------------------------------------- |
| Key | clusterId | Cluster ID |
| | nodeId | Leader node ID |
| General Information | renewDate | Lease renewal time. If there is no refresh for more than 60s, the service registration center will automatically delete the Leader record information |
| | generation | generation. It is mainly used to reject the previous generation Leader's attempt to perform lease operations because the birth of a new Leader has not been detected |

#### Leader Election

Conditions for nodes to participate in the election:

* The node type must be `turms-service`, not `turms-gateway`. This is because some Leader actions can only be performed by turms-service, and turms-gateway has no ability to perform these operations.
* `im.turms.server.common.infra.property.env.common.cluster.NodeProperties#leaderEligible` is `true` (default `true`)
* Node status must be `active`

##### Automatic Election

Each node eligible for election: 1. When the server starts; 2. When the Leader information of the service registration center is deleted through the Change Stream; 3. When it finds that its `isLeaderEligible` information changes from False to True:

The current node will first pull all the node information in the service registry at this moment, and find a batch of nodes with the highest `priority` that are eligible for election. If the current node is in this batch of nodes and there is no Leader in the local node information snapshot, it will send a Leader registration request to the service registration center and try to select itself as the Leader. If there is no Leader in the service registry, the registration is successful. Otherwise, registration fails.

Note: If a node with a higher `priority` joins the cluster, the node will not snatch the Leader role.

##### Manual election (Admin API)

The API interface `POST /cluster/members/leader` allows to force the cluster to re-elect the Leader. This API has an `id` parameter. If the `id` parameter is empty, the node with the highest `priority` in the current cluster and eligible for election will be forced to be the leader. If the `id` parameter is not empty, the node whose node ID is `id` is elected as the leader, regardless of its `priority`. An exception is thrown if the node does not exist or is not eligible for election.

#### Leader's Responsibilities

Generally speaking, it is necessary to ensure that only one node triggers or executes an action, which is usually executed by the Leader node. In addition, in some server implementations, this kind of behavior will be realized by nodes preempting distributed locks, but the reliability, controllability and performance of this implementation are far inferior to the solution of using a unified leader, so Turms does not use preempting distributed locks. lock scheme.

In terms of specific actions:

* One of the most important actions of the Leader is to update the latest status of each node according to the heartbeat refresh time of other nodes in the service registry (MongoDB) (the specific code is in: `im.turms.server.common.infra.cluster.service .discovery.LocalNodeStatusManager#updateMembersStatus`)
* "Periodic cron sends instructions to Redis to clear expired blacklist records" This action only needs one node, that is, the Leader to execute regularly.
* "Periodic cron deletes expired database data operations, such as user messages", and will only be executed by the Leader (Supplement: The code of this type of operation is actually a "legacy code" and is reserved "by the way". After all, very few applications will really get it Delete user data, so the default disabled state can be ignored)

#### Related Admin APIs

TODO
### Network connection service (Connection)

Service class: `im.turms.server.common.infra.cluster.service.connection.ConnectionService`

Configuration class: `im.turms.server.common.infra.property.env.common.cluster.connection.ConnectionProperties`

In the implementation of Turms server cluster, `Connection` is a concept between `Transport` and `RPC`, because `Connection` needs to maintain the TCP connection between nodes on the one hand, and on the other hand needs to pass `RpcService `To complete the heartbeat operation between nodes (used to detect whether the TCP connection between nodes is healthy). The reason why `ConnectionService` and `RpcService` are not merged into one Service is because both of them have a lot of their own logic. In order to follow the principle of single responsibility as much as possible, to avoid mixing a large number of TCP connection maintenance and RPC capability realization logic, Therefore the two services are not merged.

#### Responsibilities

* According to the request of `service registration and discovery service`, connect to other cluster nodes based on TCP. Note: There is and only one TCP connection between two nodes
* If accidentally disconnected from other cluster nodes, do a best-effort reconnect operation
* Send a heartbeat request to confirm that the TCP connection between nodes is indeed valid

#### Network connection life cycle

* Establish a TCP connection
* Carry out the handshake operation of the application layer, and exchange the basic necessary information of the nodes, such as the node ID, to know which node the TCP peer is. Note: The `handshake` here is not the handshake in the TCP protocol.
* After the handshake is successful, the nodes can send and receive network data
* Before closing the TCP network connection, send the wave operation of the application layer to notify the peer that the node should actively disconnect from it, so as to distinguish TCP from accidental disconnection. Note: The `waving` here is not the wave in the TCP protocol.
* Close the TCP connection

### Codec service (Codec)

Service class: `im.turms.server.common.infra.cluster.service.codec.CodecService`

This service mainly provides data codec implementation for RPC services. In particular, Turms does not use the reflection mechanism to uniformly implement the serialization and deserialization logic, but customizes the implementation for each data. This is mainly because: 1. The customized implementation ensures absolute efficiency. For example, Set\<DeviceType\> can use a Byte to represent the existence of a value by Bit instead of a group of Bytes; 2. Avoid reflection and ensure high efficiency; 3. What you see is what you get in the code, avoiding the existence of obscure operations

### RPC service

Service class: `im.turms.server.common.infra.cluster.service.rpc.RpcService`

Configuration class: `im.turms.server.common.infra.property.env.common.cluster.RpcProperties`

This service is based on the underlying TCP network connection provided by the "network connection service" and the data serialization and deserialization capabilities provided by the "codec service" to implement the relevant logic of the RPC operation.

#### Encoding format

The components of an RPC request:

1. The length of the text encoded by Varint, which is used to distinguish the byte range of each RPC request data in the TCP byte stream. For most RPC requests, this part usually occupies 1~2 bytes.
2. Request header: data type ID (2 bytes) + request ID (4 bytes)
3. Request body: Different requests have different encoding methods, but they all use custom encoding to ensure extreme efficiency. In addition, the largest data in the request body is "user-defined text", such as "chat message"

Components of an RPC response:

1. The text length of Varint encoding, which is used to distinguish the byte range of each RPC response data in the TCP byte stream. For most RPC responses, this part usually occupies 1 byte.
2. Response header: data type ID (2 bytes) + response request ID (4 bytes)
3. Response body: The response body can be divided into two categories: normal response and abnormal response. The correct response is various data types, such as the eight basic types and other combined data types. The exception response is essentially just a "combined data type", which is expressed as the `RpcException` data type, and the exception information is described through the `RpcErrorCode`, `ResponseStatusCode`, and `description (String)` fields.

Replenish

* Some requests (such as user chat messages in "Notification") will be sent to multiple different RPC nodes, and their request bodies all share direct memory outside the heap, and memory copying is not required

* Turms currently does not plan to use compression technology for RPC request and response data, mainly because: the compression ratio of various compression algorithms is not ideal, and compression and decompression need to consume a lot of memory and CPU resources. In general, the price/performance ratio of compression is too low, and the gain outweighs the gain, so compression technology is not used.

  What’s more, for data transfer between server and client, support for compression will be considered in the future. The fundamental motivation is: at the cost of more memory and CPU usage (opening up new memory space when compressing/decompressing) ) Improve data accessibility by compressing data (especially in weak network environments)

#### Backpressure

The turms-gateway server’s implementation of back pressure on the turms-service server is quite tricky. Specifically: each node will judge the health status of the current node according to the CPU and memory load status of the current node, and send Other nodes synchronize the health information. The turms-gateway will find out the node whose "isHealthy" is True from the known turms-service node list, and send an RPC request to it. If turms-gateway finds that the "isHealthy" of all turms-services is False, it will no longer send RPC, but will directly throw an exception.

#### Failover

For an RPC request without a specific target, if one Turms server sends an RPC request to another Turms server, and the peer responds abnormally, the sender will automatically send the RPC request to another Turms server. For example, if the client sends a request to turms-gateway, turms-gateway will first randomly select a turms-service to process the user request, if the turms-service responds abnormally, turms-gateway will automatically search again Another turms-service to handle the user request.

### Distributed ID Generation Service (IdGen)

Service class: `im.turms.server.common.infra.cluster.service.idgen.IdService`

The distributed ID generator is used to quickly provide the unique ID of the cluster for each business scenario. Generating a unique ID for a cluster only requires nodes to perform local operations (specific code: `im.turms.server.common.infra.cluster.service.idgen.SnowflakeIdGenerator#nextLargeGapId`), which is extremely efficient.

#### Principle

Turms' distributed ID generator is implemented based on the mainstream [Snowflake ID algorithm](https://en.wikipedia.org/wiki/Snowflake_ID), and the generated ID is of `long` data type, specifically:

* The highest bit (1 bit) is always 0, indicating a positive number
* 41 bits represent the time stamp in milliseconds, which can represent about 69 years. The specific UTC time interval is: `[2020-10-13, 2090-06-19]`. `2020-10-13` is the hard-coded Epoch time, if you want to modify the time, just modify the value of `im.turms.server.common.infra.cluster.service.idgen.SnowflakeIdGenerator#EPOCH`
* 4 bits represent the data center ID, and the ID range is [0, 15]. In practice, the ID is usually divided into `regions in the cloud service`, that is, each region has an ID. Turms will automatically map the zone name to a value in the interval [0, 15] according to the `NodeProperties#zone` "zone name" of the node. Note: If there are more than 16 region names, although these region names will still be mapped to values in the interval [0, 15], this also means that there will be duplicate data center IDs, and cluster nodes that generate the same ID risk. Also, the downgraded node will print a warning log to warn of the risk of generating the same ID.
* 8 bits represent the ID of the working node, and the ID range is [0, 255]. Turms will automatically map the zone name to a value in the interval [0, 255] according to the `im.turms.server.common.infra.property.env.common.cluster.NodeProperties#zone` "zone name" of the node. Note: If there are more than 256 nodes in a data center, although these node IDs will still be mapped to values ​​in the interval [0, 255], this also means that there will be duplicate worker node IDs, and there are cluster nodes Risk of generating the same ID. Also, the downgraded node will print a warning log to warn of the risk of generating the same ID.
* 10 bits represent the serial number. A maximum of 1024 serial numbers can be represented in the unit timestamp field (1 millisecond), that is, a maximum of 1024 unique IDs can be generated in 1 millisecond. In other words, a maximum of 1,024,000 unique IDs can be represented within 1 second, so in actual use, it is impossible to have duplicate IDs.

Supplement: According to the node information, the code to update the data center ID and the working node ID information is in: `addOnMembersChangeListener` of `im.turms.server.common.infra.cluster.service.idgen.IdService#IdService`

#### Variation implementation

Concrete implementation: `im.turms.server.common.infra.cluster.service.idgen.SnowflakeIdGenerator#nextLargeGapId`

The IDs generated by the conventional snowflake algorithm are monotonically increasing. But in most cases, Turms' business implementation uses IDs with large intervals to avoid monotonically increasing IDs. The reason for this is to use large-spacing IDs to ensure that when these data are stored in the MongoDB database, MongoDB can generate enough Chunks based on these IDs, and load-balance these Chunks to each MongoDB server for storage. . The monotonically increasing ID will cause all new data to be always allocated to the only hotspot MongoDB server, causing the load balancing of the database to fail.

The implementation of large-spacing ID is also very simple, just rearrange the fields, the specific order is: `serial number, time stamp, data center ID, work node ID` (the ID order of the conventional snowflake algorithm is `time stamp, data Center ID, worker node ID, serial number`). Since the serial number occupies the highest bit of the ID, and the generated serial number is monotonically increasing in the interval [0, 1023], it can ensure that the generated ID quickly occupies a large range of values, and is divided into multiple Chunks by MongoDB and stored in a load-balanced manner. In different MongoDB servers.