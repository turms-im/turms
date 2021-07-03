<p align="center">
  <img height="100" src="https://raw.githubusercontent.com/turms-im/assets/9dbc34a9d78a68f9f7df2430b4066c82bf8a458f/logo/logo.svg">
</p>

[简体中文](https://github.com/turms-im/turms/blob/develop/README_zh.md)

## What is Turms

Turms is the most advanced open-source instant messaging engine for 100K~10M concurrent users in the world.
Please refer to [Turms Documentation](https://turms-im.github.io/docs) (no English version for now) for details.

## Playground
(Version of demo servers: ghcr.io/turms-im/turms:latest, ghcr.io/turms-im/turms-gateway:latest, ghcr.io/turms-im/turms-admin:latest)

* turms-admin: http://playground.turms.im:6510

  Both the account and the password are: guest. (The account is allowed to query and add data, but is not allowed to update and delete data.)

* turms (Admin API in dev environment with fake data supported): http://playground.turms.im:8510

  (Note that if you open the link (API) directly in the browser, it will get a response with 401 (no permission))

* turms-gateway: http://playground.turms.im:10510 (port for WebSocket access) and http://playground.turms.im:11510 (port for TCP access)

  You can use any turms-client-(java/js/swift) implementation to send requests to turms-gateway and interact with other users.

In addition, Playground is set up automatically by just one command: `ENV=dev docker-compose -f docker-compose.standalone.yml up --force-recreate -d`

## Quick Start
Running the following commands to setup a minimum viable cluster (including turms, turms-gateway and turms-admin) and its dependent servers (MongoDB sharded cluster and Redis) automatically:
```sh
git clone --depth 1 https://github.com/turms-im/turms.git
cd turms
docker-compose -f docker-compose.standalone.yml up --force-recreate
```
After the cluster is set up, you can visit turms-admin at http://localhost:6510, and enter the account and password ("turms" by default). If you have logged in successfully, it will indicate that turms has been setup successfully.

## Intro

The architecture of Turms depends on the fanout read design for creating inboxes (or message timelines), and Turms supports push model, pull model, and push-pull model to be aware of the changes of business data (For details, [Business Data Change Awareness](https://turms-im.github.io/docs/for-developers/status-aware.html)). Most of the other design details also come from commercial IM projects.

And compared to many projects with obsolete technology stacks, Turms is also the only open source IM solution that is based on modern architecture and modern technology and is suitable for medium to large scale applications.

In addition, architecture design is an art of trade-off. Some IM products take rich features as their slogan at the cost of no support for medium to large scale applications (they are only suitable for team communications). However, Turms takes extreme performance as the first priority and supports complete (rather than rich) IM features. Please refer to [Turms Schema Design](https://turms-im.github.io/docs/for-developers/schema.html) for details. And Turms Monitoring System (TODO).

### Business Features

1. Support a complete set of [IM features](https://turms-im.github.io/docs/features). Turms supports almost all IM features supported by commercial instant messaging products and no restrictions on business features.
    (The data analysis feature will be supported when turms-data is released in the future. Please refer to [Turms Data Analysis](https://turms-im.github.io/docs/for-developers/data-analytics.html ) for details)
2. (Extensibility) Turms supports two approaches to extend: configuration properties and custom plugins. Of course, you can also modify the source code. For example, the plugin turms-plugin-minio based on turms-plugin is used to interact with MinIO server.
2. (Flexibility) Turms provides hundreds of configuration properties for developers to meet various requirements. And most of the properties can be updated at the cluster level when the cluster is running without performance loss.

### Common Architecture Features

1. (Agility) Support updating turms server without the users' awareness of shutdown to support rapid iteration
2. (Scalability) Turms server is stateless to be scaled out; Support multi-active across data centers
3. (Deployability) Support container deployment to facilitate integration (CI/CD) with cloud services
4. (Observability) Support relatively complete features of observability for business analysis and troubleshoot
5. (Scalability) Support medium to large scale instant messaging applications, and there is no need to refactor even if the application becomes large from medium-scale (There is still a lot of optimization work to be done for large applications, but turms server is easy to upgrade)
6. (Simplicity) The Turms architecture is lightweight, which makes Turms easy to learn and redevelop. Please refer to [Turms Architecture Design](https://turms-im.github.io/docs/for-developers/architecture.html) for details)
7. Turms depends on the MongoDB sharded cluster to support request routing (such as read-write separation) for medium to large scale applications

### IM-specific Architecture Features (TODO)

### Other Features

1. Observable system (Please refer to Turms Monitoring System (TODO) for details)
   * Log (for events): Turms provides three types of logs: monitoring log, business log, and statistics log
   
   * Metrics (for aggregable data). It reflects the real-time status of the system and business data
   
   * Tracing
     
   
   Note that the turms server will provide more monitoring features that can be implemented efficiently as much as possible, but will not provide some common features that have a great impact on performance (such as DAU). For this kind of extended feature, you can implement them by offline or real-time analysis of the logs or metrics of turms servers.
2. Extreme performance
    We always try to archive extreme performance in the implementation of all business workflows. Please refer to the source code for details.
  * (Network)
    * (I/O) turms server is a reactive application. All network I/O operations (database call, Redis call, service discovery call, RPC) are based on Netty to achieve non-blocking I/O. Therefore, the turms server can make full use of system resources in each module (while traditional servers can't)
    * (Encoding) Protobuf is used to encode the traffic data between the turms server and turms clients; Custom encoding without any redundant data is used to encode the RPC requests and responses between turms servers.
  * (Thread) turms server has an excellent thread model, and its thread number is constant, which is independent of the size of online users and the number of requests. Since the default number of threads in the access layer of the turms server is the same as that of the CPU, the turms server can make full use of the CPU cache, and greatly reduce the cost of thread context switching compared with traditional servers
  * (Memory) Turms allocates heap or direct memory smartly according to its usage to reduce the memory footprint.
  * (Cache) Each module of the turms server make full use of the local memory cache
## Subprojects

|Name | Summary |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| <span style="white-space:nowrap;"> turms</span> |Implements IM business logic, and provides admins with business data management, RBAC, cluster management|
| <span style="white-space:nowrap;"> turms-gateway </span> | A gateway (push server) interacting with clients, and responsible for user authentication, session management, push notification, and load balancing of turms servers |
| <span style="white-space:nowrap;"> turms-client-js</span> | Exposes APIs to interact with the turms server to implement IM features and underlying driver logic (such as heartbeat). You don't need to know its implementations because it's transparent for developers |
| <span style="white-space:nowrap;"> turns-client-kotlin</span> | ditto|
| <span style="white-space:nowrap;"> turns-client-swift </span> | ditto|
| <span style="white-space:nowrap;"> turms-admin</span> |Provides features such as business data management and cluster management for turms server cluster|
| ~~<span style="white-space:nowrap;"> turms-apm </span>~~ |(This project will be removed and its features will be integrated into turms-admin) Provides monitoring for turms cluster|
| <span style="white-space:nowrap;"> turms-plugin </span> | When events (such as user going online/offline, message receiving and forwarding, etc) are fired, turms and turms-gateway will trigger corresponding custom plugins to facilitate developers to implement custom features |
| <span style="white-space:nowrap;"> turms-plugin-minio</span> |A plugin based on turms-plugin for the storage service, and is used to interact with MinIO server|
| <span style="white-space:nowrap;"> turms-data (TODO)</span> | Not yet published. An independent data analysis system based on Flink ecosystem is responsible for business data analysis, and provides underlying data support for the statistics APIs of turms for admins and operational reports of turms-admin |
| <span style="white-space:nowrap;"> turms-client-cpp (TODO)</span> | Not yet published |
## Reference Architecture

The architecture design of Turms is derived from commercial instant messaging architectures. The following figure shows the reference architecture of Turms. The services framed by dotted lines are optional services, while the services framed by solid lines are required services. Please refer to [Turms Architecture Design](https://turms-im.github.io/docs/for-developers/architecture.html) for details.

![](https://raw.githubusercontent.com/turms-im/assets/master/turms/reference-architecture.png)

## Product Comparison

|                       | [Rocket.Chat](https://github.com/RocketChat/Rocket.Chat)     | Turms                                                        |
| --------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| Application scenarios | Team communications                                          | General medium to large scale IM scenarios (Making Turms possible for redevelopment) |
| Advantages            | 1. The client implementation is cross-platform and out-of-the-box for users<br/>2. Support a complete and unified UI suite<br/>3. Support rich advanced instant messaging features, such as audio and video conference, file sharing, screen sharing <br/>4. Provide commercial users with technical support | The advantages are the features described above              |
| Disadvantages         | 1. Only suitable for small-scale applications<br/>2. Narrow application scenarios and hard to customize | 1. Only meets the general instant messaging needs, and does not provide some advanced features (for example, no support for audio and video conferencing)<br/>2. The admin system does not provide advanced operation features currently <br/>3. No support for specific business logic and UI<br />4. Servers are reactive, which is challenging for some developers |
| Comment               | It is highly recommended to use Rocket.Chat for team communications | Although both are open source IM projects, they have completely different application scenarios. Turms is a general instant messaging engine for medium to large scale instant messaging applications. You cannot just hand Turms to your customers (just as most products don't let customers write SQL statements to query business data in the database). <br/>However, based on Turms, you can implement all the open-source instant messaging projects on GitHub more efficiently, comprehensively, and extensively |
## Demo with Specific Business Implementation

For the positioning of the Turms, Turms does not plan to provide client demo with UI and specific business logic in the near future because:

* It's easy for developers to verify the business features supported by Turms. If you just want to test the business features of Turms, you can run the turms server without even typing a line of code. Only ten lines of code can realize the login, sending messages, sending friends' requests and other business features, or modify properties to customize various requirements.
* The design and implementation of the demo are closely related to the specific business scenarios, specific programming language, specific technical architecture, and specific OS while Turms has been committed to efficiently meeting various complex and challenging instant messaging scenarios, and we don't want to publish a demo that limits the imagination of developers. And developing and maintaining a demo is also very time-consuming and will slow down the progress of the development of Turms.
