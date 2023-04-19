# Security

## Client Security

For security reasons, this article does not describe CC attacks for which Turms does not provide a special defense mechanism.

### Client blacklist mechanism

#### The server's handling of banned clients

When turms-gateway detects that a new IP or user ID is blocked, it will first send a shutdown notification of the Turms business layer to the established and blocked session. The notification has the `USER_IS_BLOCKED` status code, telling the client that it is Banned. After the data is flushed, the Turms server automatically disconnects the underlying TCP connection.

When turms-gateway checks that the peer IP of the newly established TCP connection has been banned, or detects that the user ID sending the login request has been banned, by default, turms-gateway will directly close the TCP connection with it, and will not A notification of the reason for connection closure will be sent, such as "Your IP/User ID has been blocked for XX time".

There are two points to note:

* turms-gateway itself cannot refuse to connect with the banned IP before the TCP connection is established. If you want the server to refuse to connect to the banned IP before the TCP handshake, you can use the callback plug-in provided after Turms to notify the cloud service security system to ban the IP, so as to completely realize the IP ban .

  In addition, the reason why we do not call the system service to completely block the IP is because: when the server is forcibly shut down, the blocked IP will not be automatically removed; modifying the underlying network configuration by ourselves may interfere with the cloud service's own network The management service conflicts, causing the server to be abnormal.

* When the client connects or logs in, turms-gateway will actively disconnect the connection with the banned IP or user, but it will not send a notification of the reason for the connection closure. The advantages of doing this are: 1. The bandwidth of the cloud service is charged according to the outbound bandwidth, and the inbound bandwidth is not charged, so turms-gateway does not send a response on the business layer, which can slow down the bandwidth cost incurred when being attacked by DDoS; 2. Reduce information exposure and try not to provide effective information to hackers

#### Automatic ban mechanism

The timings that currently support automatic detection and banning of clients are:

* When the user sends requests frequently and reaches a certain number of times
* When the WebSocket frame sent by the user does not conform to the specification or is too large, and reaches a certain number of times. The size of the request is based on the Payload Length value in the WebSocket Frame Header
* When the Turms client request sent by the user cannot be parsed or is too large, and reaches a certain number of times. The size of the request is based on the Payload Length value of the client request Header in the TCP byte stream

  Replenish:

  * When the server detects that the data frame or client request is "too large", it will not continue to parse the subsequent Payload part. If the Payload Length of the client does not match the actual Payload length, it will be judged as an illegal request
  * The specific request size limit can be configured through `turms.gateway.client-api.max-request-size-bytes`

In other words, after the TCP connection is established, any behavior of the user may trigger a ban.

The automatic ban mechanism of Turms adopts a grading system, and provides 3 levels by default. The ban durations of these 3 levels are: 1 minute, 30 minutes, and 60 minutes. Under the default configuration, when the client triggers 5 illegal behaviors, the server will block the IP and user ID of the client with the configuration of level 1. If a certain number of illegal behaviors are triggered within the blocking time, it will enter the next block grade, and so on.

If you want to modify the default configuration, you can pass `turms.security.blocklist.ip.auto-block` and ``turms.security.blocklist.user-id.auto-block`` prefix, and cooperate with IDEA's smart prompt Modify the default configuration. Its specific configuration items are declared in the `im.turms.server.common.infra.property.env.common.security.AutoBlockItemProperties` class.

#### Ban related API

Administrators can use the API: `/blocked-clients/ips` and `/blocked-clients/users` to add, delete, modify and query blocked IPs and blocked user IDs respectively. The specific operations follow [Turms HTTP interface design general rules](https://turms-im.github.io/docs/reference/admin-api#%E6%8E%A5%E5%8F%A3%E8%AE%BE%E8%AE%A1%E5%87%86%E5%88%99), so I won’t go into details.

#### Implementation principle of ban (expand knowledge)

The principle of synchronizing banned client data is similar to that of common distributed Replicated Map implementations. That is, each server holds a weakly consistent copy of the Map, and one or more Redis servers store a reference copy, and also records the logs of each blocking and unblocking behavior for each server Do incremental sync. When a new server goes online or a server's local logs data lags behind by 100,000 records, these servers will request full synchronization from Redis, otherwise the server only needs to request incremental logs from Redis at the default interval of 10 seconds to synchronize the local copy .

In addition, the causal consistency implementation currently adopted by Turms is: the order of blocking and unblocking actions is based on the insertion order of the blocked logs queue in Redis, and each server performs causal synchronization based on the order of logs in the queue to ensure that the client is blocked The eventual consistency of the data.

##### Why not use Bloom Filter

The theoretical solution to realize the blacklist function based on Bloom Filter is widely known, but in fact, Bloom Filter has many pitfalls in this scenario, specifically:

* The features and engineering practices supported by Bloom Filter are limited. such as:

  * In a distributed environment, how to judge the order of "ban operation" and "unblock operation", and how to ensure the final consistency
  * How to set different ban durations for different banned users (such as five minutes/half an hour)
  * How to add additional information to additional banned users, such as the reason for being blocked
  * How to synchronize the blacklist list between nodes, how to do incremental synchronization
  * How to implement "unblocking operation" and what is the cost

  To sum up, even the most basic functions of the blacklist system cannot be realized by Bloom Filter in a distributed environment. Even if Bloom Filter is barely realized in conjunction with other engineering practices, then the advantages of Bloom Filter itself will not exist.

* The amount of blocked user data itself is very small, and Bloom Filter cannot give full play to its advantages. And if it is just to judge whether the user has been blocked, according to the 1 million banned user IDs, a total of 12MiB or 61.4MiB of memory is needed (additional supplement: this example also confirms that we are in [About the Valhalla project](https://turms-im.github.io/docs/server/module/system-resource-management#%E5%85%B3%E4%BA%8Evalhalla%E9%A1%B9%E7%9B%AE) Mentioned in the article: `Java's waste of memory makes people feel a little "self-defeating"`). Because thread-safe collections are usually used in actual programming, and most thread-safe Sets are generally implemented based on Map, so the thread-safe Map is uniformly used below:

  ```java
  public static void main(String[] args) {
      int number = 1_000_000;
      var map1 = ConcurrentHashMap.newKeySet((int)(number / 0.75F + 1.0F));
      var map2 = new NonBlockingHashMapLong<>(number);
      for (long i = 0; i < number; i++) {
          map1.add(i);
          map2.put(i, Boolean.TRUE);
      }
      System.out.println(GraphLayout.parseInstance(map1).toFootprint());
      System.out.println(GraphLayout.parseInstance(map2).toFootprint());
  }
  ```
  The output of its memory usage is as follows (calculated based on `org.openjdk.jol.jol-core` library):
  ```text
  java.util.concurrent.ConcurrentHashMap$KeySetView@593634add footprint:
       COUNT       AVG       SUM   DESCRIPTION
           1   8388624   8388624   [Ljava.util.concurrent.ConcurrentHashMap$Node;
           1        16        16   java.lang.Boolean
     1000000        24  24000000   java.lang.Long
           1        64        64   java.util.concurrent.ConcurrentHashMap
           1        24        24   java.util.concurrent.ConcurrentHashMap$KeySetView
     1000000        32  32000000   java.util.concurrent.ConcurrentHashMap$Node
     2000004            64388728   (total)
  
  org.jctools.maps.NonBlockingHashMapLong@51ca57d6d footprint:
       COUNT       AVG       SUM   DESCRIPTION
           3   2796304   8388912   [J
           1   4194320   4194320   [Ljava.lang.Object;
           1        16        16   java.lang.Boolean
           2        16        32   org.jctools.maps.ConcurrentAutoTable
           2        40        80   org.jctools.maps.ConcurrentAutoTable$CAT
           1        40        40   org.jctools.maps.NonBlockingHashMapLong
           1        64        64   org.jctools.maps.NonBlockingHashMapLong$CHM
          11            12583464   (total)
  ```
* There is an error

### Client interface anti-brush current limit

The current limiting implementation of turms-gateway adopts the mainstream algorithm "token bucket algorithm" (for example, the API Gateway of AWS provides traffic integer implementation using the token bucket algorithm).

#### Basics

No matter what the algorithm is, it needs to calculate the "allowed number of requests". The following is a unified description, and the word "token" is used to refer to the "allowed number of requests". In addition, the following table is the general implementation of this type of algorithm, and its variants will not affect the essence of the algorithm, so it will not be discussed.

| | Fixed time window algorithm | Sliding time window algorithm | Token bucket algorithm | Leaky bucket algorithm |
| ------------------ | ------------------------------------------------------------ | --------------------------------------------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| Token Cap | Fixed or Dynamic Token Cap (usually Fixed Cap) | Fixed or Dynamic Token Cap (usually Fixed Cap) | Fixed or Dynamic Token Cap (usually Fixed Cap) | Fixed or Dynamic Token Cap (usually Fixed upper limit) |
| Current number of available tokens | Calculated by a single time interval | Calculated by multiple time intervals | Calculated by the current number of tokens in stock | Calculated by the current number of tokens in stock |
| Token issuance interval | Emphasize coarse-grained interval issuance (such as interval 1 minute) | Emphasize fine-grained interval issuance (such as interval 15 seconds) | Emphasize fine-grained interval issuance (such as interval 1 second) | Emphasize fine-grained interval Release (such as interval 1 second) |
| Clear count on token issue | Yes | Yes. But generally only the earliest few windows are cleared | No | No |
| Resource overhead | No timer required, minimal overhead | No timer required, minimal overhead | No timer required, minimal overhead | Each session needs to maintain an MPSC synchronization queue, and a timer to time the Poll queue, with very high overhead big |
| Difficulty of implementation | Very simple | Very simple | Very simple | Relatively troublesome |
| General Comment| Due to the need to clear the count and the granularity is too large, the client can burst a large number of requests before each token issuance, causing the problem of "double burst traffic" | avoiding the problem of "double burst traffic" , but because of the "clear count" operation, its control precision is not as good as the token bucket algorithm and the leaky bucket algorithm | It can not only handle burst requests through stock tokens, but also through fine-grained interval commands Cards are issued to smoothly throttle requests. <br />In fact, the CPU credit mechanism of cloud services is similar to this | The length is slightly longer, see below |

Both the leaky bucket algorithm and the token bucket algorithm have the ability to handle burst requests and smoothly limit the flow of requests. But a special function of the leaky bucket algorithm is that it can limit the flow of downstream services (the most important one is the database). However, there is also a price for downstream flow limiting. It requires the operation and maintenance personnel to accurately estimate the downstream service throughput, otherwise it may cause the downstream service to be idle while the upstream service is limiting the flow.

In addition, the use of MPSC queues to cache requests not only reduces throughput, but also increases memory overhead and GC times, resulting in poor user experience and exacerbating the effect of DDoS attacks. (Supplement: By reading the source code of the Turms server, you will find that in the process of processing client requests in Turms, the code is as "light" as possible, so using the MPSC queue for each user session is considered a heavy operation)

In summary, the Turms server finally uses the `token bucket algorithm`.

In particular, compared to the traditional HTTP server, the CPU and memory system resources required to receive and process a regular HTTP request and response may be hundreds of times the system resources required for the interaction between the Turms server and its client (for example: except The network layer protocol header, the average size of a request from the Turms client is about 32B). Therefore, there is no need to take the sudden Turms client requests of a small number of users too seriously. The system resources used to process hundreds of Turms client requests may be similar to processing one HTTP request (of course, there are other forms of CC attacks that will cause a lot of resource consumption).

other:

* turms-gateway does not support and currently does not plan to support the implementation of global current limiting. The reason is: global current limiting is usually over-designed. Global current limiting is to alleviate DDoS attacks at all times, increase Redis failure points, and reduce the request processing of the entire system Throughput, many times you lose sight of the other, the gain outweighs the gain
* Turms does not currently support assigning different weights to different types of requests. For example, a login request requires 3 tokens, and a message request requires 1 token
* turms-gateway supports the configuration of the runtime zero-downtime update token bucket algorithm

## User Information Security

For most domestic groups with a little Internet age, unless they have a strong sense of security, their plaintext passwords are very likely to have been leaked (the specific content can be found out through `social engineering database`). Combined with the fact that the passwords used by most users are relatively fixed, so no matter how encrypted the server is, the security of the "password" is still relatively low.

TODO

## Admin Security

### Administrator authentication and authorization

#### Authentication

Authentication: The server is implemented based on the common HTTP Basic authentication to confirm which administrator is the sender of the HTTP request.

Configuration item: `turms.security.password.admin-password-encoding-algorithm`, its optional values are: `bcrypt` (default), `salted_sha256` and `noop`.

##### Supported key encryption algorithms

*BCrypt. Its `cost` is a hard-coded `10` (2^10 rounds), which is used to prevent hackers from easily deciphering the plaintext password through the rainbow table when it is removed from the database.

For the specific algorithm implementation, please refer to the source code implementation of `Bouncy Castle` of Fork under the `turms-server-common` subproject: `org.bouncycastle.crypto.generators.BCrypt#generate`

* Salted SHA-256

* NOOP (plaintext storage)

Special mention: The `password` field in the `admin` collection is not stored in `string` (such as the common Base64 encoded string), but the original `byte[]` byte data.

#### Authorization

Authorization: The server confirms what authority the sender of the HTTP request has to do

Because Turms's own permission management requirements are very simple, its design and implementation are also relatively simple. For example, there are no concepts such as user groups, group roles, and role inheritance, and there is no many-to-many relationship between users and roles. Specifically, Turms uses RBAC (Role-Based Access Control) design scheme.

##### Turms' RBAC Model

Turms' RBAC model consists of three subjects: `Admin`, `Role` and `Permission`. A user can have only one role, and a role can have multiple permissions. in:

* Each role also has a field `rank`, only relatively high-rank administrators can add, delete and modify relatively low-rank administrator account information, such as passwords.
* Permissions are used to describe what operations a role can perform on what resources, such as adding, deleting, modifying and querying user resources

##### Special Character - Root

`Root` is a built-in administrator role in Turms, which has all administrator privileges and cannot be modified or deleted.

##### Special root account - turms

The root account `turms` user has the `Root` root role authority, and its account name does not support modification (but can be changed by modifying the hard-coded `im.turms.server.common.domain.admin.constant.AdminConst#ROOT_ADMIN_ACCOUNT` value) Modify the root account name), its initial password is `turms` by default, but users can use the configuration item `turms.security.password.initial-root-password` when the `admin` collection has not been created and the turms-service is started Defined initial password.

## Log desensitization

TODO