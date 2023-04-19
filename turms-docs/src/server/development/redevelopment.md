# About Secondary Development

## Reasons for Secondary Development Based on Turms

### Objective Reasons

* uniqueness. The Turms solution is the only solution based on modern architecture and modern engineering technology in the global instant messaging open source field, and suitable for medium and large-scale deployment. Dozens of other IM open source projects are still in the slash-and-burn era, most of which emphasize enterprise communication or end-to-end security IM projects, and usually only win the favor of enterprise users. Except for Turms, there is no medium-to-large IM open source project designed for conventional Internet applications in the global open source community.

* Normative. Since the architecture design of Turms is a variant of the standard commercial instant messaging architecture, if your professional team is based on common commercial standards, the architecture designed by your team is similar to the current architecture of Turms, and there is no need to start from scratch. Zero self-development.

* Simplicity. The entire architecture of Turms and the implementation of each module are actually relatively simple and lightweight, and the secondary development is not difficult.

* Controllability. Turms is developed based on the Apache V2 protocol, 100% open source, and has self-developed many basic middleware, which ensures the controllability of the underlying technology and avoids insufficient development momentum in the later stage of the project.

* Documentation is complete. It includes design documents for modules such as message awareness, observability system, sensitive word filtering, anti-brush current limiting, global blacklist, etc. When we write the Turms document, we write it with the attitude of "I'm afraid I don't understand it". The Turms document will not only write "what to do", "how to do it", but also "why to do it", by providing Design concepts and ideas and core points help developers understand various functional modules, which is actually relatively rare in the open source circle. In order to earn consulting fees and worry about being plagiarized, some personnel of open source IM projects write with the attitude of "I am afraid that users will understand", so they are unwilling to write good documents.

  Reminder: The importance of design documents to developers and architects is self-evident. When readers use various open source IM projects, they can check whether the documents of a project are "afraid that they are not clearly written" or "afraid that users I can understand."

* The IM system itself has many details, and the level of developers is uneven, so it is difficult to guarantee the quality of the projects produced. Realizing that user A can send messages to user B/group B is at most only 1% of the functions of the IM system, and these functional modules are not like some general dependency libraries that can be plugged in at will, but need to be customized. For example, Turms is based on dual The sensitive word filtering function of the array Trie AC automaton algorithm, and each implementation is interlocking (in fact, even the documents of Turms are mutually referenced and interlocking), so each module must be self-developed, requiring designers to work with Developers have a strong foundation.

  (If you want to know how many detailed functions a complete IM system has, you can continue to read the Turms document. Of course, the functions of the IM system can be more abundant, these are what we said above: IM is not only complex, but also can be almost seamless Endlessly complicated.)

  And Turms has basically implemented a complete IM server system. We have already implemented, or laid a solid foundation for, what basic users can think of and what they didn't expect. Even if the functions that are not implemented, we have generally written Explain why it is deliberately not implemented, and ensure transparency.

  In addition, some implementation schemes of Turms may seem to be "natural" schemes, but in fact, when we design and implement a scheme, we have usually overturned many other schemes. Behind it is a lot of derivation and practice. Users see What I want is just a final solution, and then I feel that "this is a natural solution". Regarding this point, the design documents of each module of Turms have related instructions.

* Code quality is high. The Turms server can always maintain the level that a senior engineer should have in terms of code implementation, and can strike a balance between code performance and readability. For details, please refer to the Turms server source code and the design documents of each module. The reason why we dare to say that the Turms server can reach the limit of the Java ecosystem is that, in addition to the fact that the Turms server itself is very efficient, we have many inefficient but critical dependencies (such as `mongo-java-driver` and `lettuce` ) has been refactored, and even self-developed (such as log implementation/cluster implementation) to ensure the ultimate performance.

  In particular, some open source projects claim to have good performance, but in fact, the code reveals the truth. Here are three general, fast and practical methods for judging the coding level of open source authors for readers' reference:

  * Reasonable use of (elementary) syntax, data structures, and programming paradigms.
  * (Intermediate) Through class names, variable names, function names, etc., observe the author's vocabulary + word accuracy. Vocabulary and word accuracy are things that are difficult to disguise. It is generally easy to deduce the technical background, technical level and coding experience of the project author through this method. If the author has a rich vocabulary and uses words more accurately, then the coding level is usually not bad.
  * (Advanced) anti-paradigm design (such as anti-design pattern design, anti-conventional algorithm design and Unsafe operation, etc.). Reasonable use of design patterns can tell whether the author has design thinking, and daring to design anti-paradigms usually means that the author has a clear coding goal in mind, and is very familiar with related designs and underlying codes, has insight into the shortcomings of conventional designs, and has Have the courage to answer the question of "why not design according to the standard routine", and then dare to design against the paradigm.

  Of course, the above methods are only for readers' reference, and there can be more actual investigation points.

* The technical solution is forward-looking. As software engineers, we have a deep understanding of one point: Maybe today's well-known technical solutions with stars and moons will become yesterday's yellow flowers tomorrow and become "technical debt". Such as Hadoop on the server side, Bootstrap, Backbone.js and Ember.js on the Web side. When Turms makes technology selection, it will not only consider the current status quo, such as [cluster design and implementation](https://turms-im.github.io/docs/server/module/cluster#%E7%BA%AF%E8%87%AA%E7%A0%94%E7%9A%84%E5%8E%9F%E5%9B%A0), will also consider the development process of future technologies, such as [system resource management](https://turms-im.github.io/docs/server/module/system-resource-management#%E5%86%85%E5%AD%98%E7%AE%A1%E7%90%86) Project Valhalla and Project Loom mentioned.

* There is a large market demand for self-developed IM services. Even if you go to various recruitment websites to inquire about IM engineer-related positions, you can find that there are still a large number of companies recruiting IM-related talents at home and abroad. Companies invest hundreds or tens of millions of self-developed IM services from scratch or based on ancient IM open source projects. , The utilization rate of social resources is low.

In addition, if you are still hesitating whether to adopt other open source IM projects, then we highly recommend that you compare Turms with them. After you have probably read the documents and source codes of Turms and other open source IM projects, I believe you will have a clear understanding in your mind. Answer.

### Subjective Reasons

* The core business of your project is related to instant messaging, or you have plans to further develop instant messaging business.
* The extended functions required by your project are not currently available in Turms, especially the extended functions that need to be implemented through auxiliary index tables (for auxiliary index tables, please refer to [Turms Collection Design](https://turms-im.github.io/docs/design/schema)).
* Your project has a large number of project-specific IM implementation details. Although Turms provides hundreds of configuration items, these are only general configurations. According to the specific business needs, the specific implementation of IM-related functions is extremely rich, but it is impossible for Turms to directly provide the realization of these relatively niche business functions, otherwise the amount of code will increase exponentially, so you need to do secondary development by yourself.

## Project Import

1. Pull the Turms repository: `git clone https://github.com/turms-im/turms.git`

2. Since the proto model files of each sub-project of Turms are placed in an independent warehouse, you also need to pull the code in the submodule through the following command in the root directory of the Turms project.

```
git submodule update --init --recursive
git submodule foreach git pull origin master
```

3. (Optional) If you are using IntelliJ IDEA, you can import the entire Turms project through `File` -> `New` -> `Project from Existing Source`. IDEA will automatically recognize the directory structure of the entire Turms project and import the corresponding Maven dependency library.

## Build a Development Environment

Except for the Turms server, the construction of other sub-projects of Turms is very routine and simple, so I won’t go into details.

The construction of the Turms server development environment is actually very simple. The specific steps include:

1. Install [JDK 17](https://adoptium.net/) to develop Turms server

2. Download, install and start the Redis server. Take RHEL/CentOS as an example:

    ```bash
    yum install epel-release
    yum update
    yum install redis
    systemctl start redis
    systemctl enable redis
    ```

   For the Windows platform, you can download the Windows version from [tporadowski/redis](https://github.com/tporadowski/redis/releases) for local development and testing.

3. Download, install and start the MongoDB shard cluster

    * Download [MongoDB 4.4](https://www.mongodb.com/try/download/community)
    * Start MongoDB shard cluster: It is recommended to install `mtools` to build a MongoDB shard cluster automatically. The installation command is: `pip3 install mtools[mlaunch]`. After installing `mtools`, just run `mlaunch init --replicaset --sharded 1 --nodes 1 --config 1 --hostname localhost --port 27017 --mongos 1` and wait for a few seconds. The construction of the MongoDB shard cluster can be completed

4. After confirming that the Redis server and the MongoDB shard cluster are running normally, you can start the Turms server

Notes:

* For the startup of Redis and MongoDB, it can be set as a self-starting service at startup, so that there is no need to restart the computer every time and then manually build it. In addition, even if it is built manually, developers can basically complete the building of Redis and MongoDB shard clusters in 10 to 30 seconds after a few more operations. The process of building and starting is very simple.
* When doing server-side development, it is recommended to change `spring.profiles.active=prod` in `application.yaml` under `turms-gateway` and `turms-service` to `dev`. This is because:
    * Under the default production environment configuration, the Turms server will not print logs on the console, so it is not convenient for developers to debug
    * In the `dev` environment, turms-service will automatically generate fake data to the MongoDB database, and turms-gateway will also automatically create TCP-based fake clients, and these clients will randomly (random request type, random request parameters) send turms-gateway sends real client requests to facilitate testing by developers.
* If you want to replace the port of the MongoDB server, you only need to globally replace `27017` with your target port under the Turms project.

## About Task Difficulty

For teams that are planning to do secondary development based on Turms (change the source code of the Turms project itself), you can refer to the task difficulty table below to assign tasks to members.

The difficulty value of the task ranges from 0 to 10, where:

* 0 means extremely simple
* 1~3 means simple
* 4~6 means medium
* 7~9 means difficult
* 10 means unachievable

### Server

"Code implementation difficulty" is mainly considered from two perspectives, one is the logic complexity, and the other is the workload (the degree of tediousness, mainly relying on "physical strength" to achieve). For example, the same amount of self-developed implementation of `spring-webflux`, its logical complexity is 1~3, but the workload is 5~6, and the combination of the two is 5~6. The algorithm implementation is generally high logic complexity and low workload.

|                                             | Requirements Analysis                                        | Relevant Process Design                                      | Code Implementation Difficulty (Prerequisite: Code Implementation Must Be Efficient) |
| ------------------------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| IM basic business functions                 | 3~7. It is necessary to consider whether all IM service features are logically consistent, and whether they can be implemented efficiently (reverse inference or restriction of IM service requirements), etc. | 4~6: Initial stage. For example, messages use read-diffusion, write-diffusion, and read-write hybrid technology selection. Various notification push, pull, and push-pull hybrid technology selection<br />1~2: current stage | 1~3. Most of them are regular CRUD operations. The difficulty of the individual 3 tasks is to balance the contradiction between code elegance and efficient implementation, and it is more of a code design problem. |
| Expanded functions                          | 2~5                                                          | 3~4: Initial stage<br />1~2: Current stage                   | 2: Current limiting and anti-brush mechanism<br />4~5: Global blacklist<br />7~8 : Realization of sensitive words |
| Middleware implementation and basic library | 1~3                                                          | 1~3                                                          | 1~4. <br />1: such as metrics, distributed snowflake ID distributor<br />2~3: such as logs, distributed configuration center<br />3~4: such as plug-in mechanism, RPC, service registration and discovery<br /> /> |
| Bug correction                              | 0~3                                                          | 0~3                                                          | 1: Most of the regular bugs<br />Turms seldom fix bugs in isolation. Generally, before fixing bugs, it is necessary to deduce whether the business process design that caused the bug is reasonable. Is there any? Optimizing the space, followed by fixing this bug. <br />And hard-to-correct bugs generally have nothing to do with code implementation. Generally, hard-to-correct bugs are due to loopholes in the process design. <br />For example, if there is a problem with the architecture design, the architecture of read diffusion should be used, but write diffusion is used. If the design of the bottom layer is wrong, no matter how the upper layer is changed, it will only scratch the surface. |
| Custom Algorithms and Data Structures       | 1                                                            | 1~2                                                          | 1: General custom data structures. Such as `im.turms.server.common.infra.collection.FastEnumMap`<br />2: lock-free thread-safe custom data structure, such as: `im.turms.server.common.infra.collection.ConcurrentEnumMap`,` im.turms.server.common.infra.throttle.TokenBucket`<br />4~5: lock-free thread-safe custom Growable data structure, such as `im.turms.server.common.infra.collection.SpscGrowableLongRingBuffer`<br />8: `im.turms.plugin.antispam.ac.AhoCorasickDoubleArrayTrie` in sensitive words |

General comments:

* The difficulty of the IM function lies in requirement analysis and outline design. Adding a new IM feature should not only consider whether it is logically consistent with other IM business features, but also consider whether the current architecture can implement it efficiently, whether distributed transactions are required, Do you need to add collection fields in the database and many other issues. As for code subcontracting and layering, it is more complicated in the early stage, but these problems have been solved and are relatively stable, so new tasks generally do not encounter difficulties in code flow design. The specific code implementation is generally very routine, and individual implementations may be relatively cumbersome.
* There is basically no difficulty in the implementation of custom middleware and basic library, and the main thing to pay attention to is requirement analysis (of course, the difficulty of middleware requirement analysis is much simpler than that of IM business functions).
* Most of the bugs themselves are not difficult, but you need to reverse the root cause that caused the bug, and think about whether the business process has the ability to optimize the space (in fact, it is still difficult to analyze the requirements)
* Except for the AC automaton algorithm based on the double array Trie, which is difficult to implement, most other custom algorithms are relatively easy to implement. And in fact, there are very few algorithms and data structures that need to be customized, so the Erkai team should not encounter problems related to algorithms and data structures.

Special mention: Requirements analysis is required for not doing a function. For example, the process of some functions of Turms has been designed, and its code implementation has also been written. But in the end, considering that this requirement may conflict with other requirement logics, or that the requirement is dispensable due to a large performance loss, these functions will always be in a pending state that has been implemented but not released.

### turms-admin

turms-admin itself has no technical difficulties, the code level and implementation are relatively standardized, and there is no nesting problem of a large number of heterogeneous sub-projects in medium and large front-end projects due to historical reasons (for example, the root project uses Backbone, and nested in this The sub-projects of the root project mix Vue, Angular, React, etc., and various dependency version conflicts), so junior front-end engineers should be able to get started and do second development.

The proportion of time to make a new UI feature is generally: requirements analysis (40%) > UI design (30%) >= code implementation (30%)

### turms-client

Turms-client itself has no technical difficulties, and the code level and implementation are relatively standardized. Junior engineers should be able to get started and do second development.

Relatively speaking, the difficulty of turms-client is that the API interface design "try to make the interface as the name suggests, while ensuring that developers have the ability to expand the underlying layer".