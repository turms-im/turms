<p align="center">
  <img height="100" src="https://raw.githubusercontent.com/turms-im/assets/9dbc34a9d78a68f9f7df2430b4066c82bf8a458f/logo/logo.svg">
</p>

[English](https://github.com/turms-im/turms/blob/develop/README.md)

## Turms 是什么

Turms是一套全球范围内最为先进的、为同时在线用户数为100K~10M应用而设计的开源即时通讯引擎。

若想详细了解Turms项目，您可以阅读[Turms文档](https://turms-im.github.io/docs/)。下文为Turms项目的概要。

## Playground

（当前Demo的服务端版本：ghcr.io/turms-im/turms:latest、ghcr.io/turms-im/turms-gateway:latest、ghcr.io/turms-im/turms-admin:latest）

* turms-admin服务端地址：http://120.24.57.206:6510

  登录账号与密码均为：guest（该账号有查询与增加领域模型的权限，无更新与删除领域模型的权限）

* turms服务端的管理员API地址（DEV配置，带Mock数据）：http://120.24.57.206:8510

  （提醒：如果您直接打开该页面，会提示401无权限”）

* turms-gateway服务端地址：http://120.24.57.206:10510 （WebSocket端口）、http://120.24.57.206:11510 （TCP端口）

您可以使用任意turms-client-(java/js/swift)客户端，向turms-gateway服务端发送请求，并与其他用户进行交互。

另外，Playground由一条指令全自动搭建：`ENV=dev docker-compose -f docker-compose.standalone.yml up --force-recreate -d`

## Quick Start

通过以下命令，可以全自动地搭建一套完整的Turms最小集群（包含turms、turms-gateway与turms-admin）及其依赖服务端（MongoDB分片集群与Redis）

```sh
git clone --depth 1 https://github.com/turms-im/turms.git
cd turms
docker-compose -f docker-compose.standalone.yml up --force-recreate
```

等集群完成搭建后，可以通过 http://localhost:6510 访问turms-admin后台管理系统，并输入账号密码（默认均为“guest”）。如果登录成功，则说明turms服务端也已经成功启动。

## 简介

Turms基于读扩散消息模型进行架构设计，对业务数据变化感知同时支持推模式、拉模式与推拉模式（详细文档：[Turms业务数据变化感知](https://turms-im.github.io/docs/for-developers/status-aware.html)），其他大部分的设计细节也源自商用即时通讯项目。并且相比很多技术栈落后的开源项目或闭源商用项目，Turms解决方案也是全球即时通讯开源领域内唯一一个基于现代化架构与现代化工程技术，并且适合中大规模部署的解决方案。

另外，架构设计是权衡的艺术，部分IM产品以功能丰富为口号，但功能丰富的代价就是只适用于小体量的用户规模（如企业内部通讯）。而Turms以极限性能为第一要义，同时支持完整的（而非丰富的）IM业务功能。具体原因可查阅[Turms集合设计](https://turms-im.github.io/docs/for-developers/schema.html)以及Turms监控系统（TODO）相关文档。

### 业务功能相关特性

1. （业务功能完善性）Turms支持几乎所有商用即时通信产品所支持的[即时通信相关功能](https://turms-im.github.io/docs/features/)（甚至还有更多的业务功能），且无业务功能限制。
   （数据分析与挖掘功能会在之后发布turms-data的时候提供，具体细节可查阅 [Turms数据分析](https://turms-im.github.io/docs/for-developers/data-analytics.html)）
2. （功能拓展性）Turms同时支持两种拓展模式：配置参数与开发插件。当然您也完全可以对源码进行修改。目前用于接入的MinIO对象存储服务的插件turms-plugin-minio就是基于turms-plugin实现的。
3. （配置灵活性）Turms提供了上百个配置参数供用户定制，以满足各种需求。并且大部分配置都可以在集群运作时（不需要停机），进行集群级别的同步更新，并且无性能损失。

### 通用架构特性

1. （敏捷性）支持在用户无感知的情况下，对turms服务端进行停机更新，为快速迭代提供可能
2. （可伸缩性）无状态架构，支持Turms集群横向扩展；支持异地多活的部署实现
3. （可部署性）支持容器化部署，方便与云服务对接，以实现全自动化部署与运维
4. （可观察性）具备相对完善的可观察性体系设计，为业务统计与错误排查提供可能
5. （可拓展性）能同时支持中大型即时通讯场景，即便用户体量由小变大也无需重构（当然，对于大型运用场景还有很多优化的工作需要做，但当前架构不影响后期的无痛升级）
6. （简单性）核心架构“轻量”，方便学习与二次开发（原因请查阅 [Turms架构设计](https://turms-im.github.io/docs/for-developers/architecture.html)）
7. Turms使用MongoDB分片副本架构，以支持请求路由（如读写分离），为大规模部署提供实际操作的可能

### IM架构特性（TODO）

### 其他特性

1. 重视可观察性体系建设（详细文档：Turms监控系统（TODO））。具体而言包括以下三个维度：

   * 日志（针对事件）：共提供了三大类日志：监控日志、业务日志、统计日志
   * 度量（针对可聚合数据）。包括实时的系统运行状态信息，以及实时的业务数据
   * 链路追踪

   补充：Turms服务端自身会在实现高效的前提下尽可能提供更多监控数据，对于一些尽管常见但对性能影响较大的功能不予提供（如：日活）。对于这类拓展功能，您可以通过对Turms的日志与度量数据进行离线或实时分析来实现该类功能。

2. 运作极为高效。

   Turms服务端在所有业务流程的代码实现上，都对性能有着极致追求，具体请查阅代码实现。

   - （网络）
     - （I/O）Turms服务端基于响应式编程，Turms服务端的所有网络请求在底层都是基于Netty的异步无阻塞模型实现的（包括数据库调用、Redis调用、服务发现注册、RPC等）。因此Turms服务端在各个功能模块上都能充分利用硬件资源（而传统服务端不能）
     - （编码）Turms服务端与Turms客户端间的通信数据采用Protobuf编码；Turms服务端之间的RPC请求与响应均采用定制化的二进制编码（无冗余数据）。
   - （线程）Turms服务端具有优秀的线程模型，其线程数恒定，与在线用户规模以及请求数无关。由于Turms接入层默认线程数与CPU数一致，因此Turms服务端能充分利用CPU缓存，并相比传统服务端而言，极大地减少了现场上下文切换开销
   - （内存）在划分内存空间时，合理且充分地循环利用堆内内存与堆外内存。因此Turms能够将内存占用降到最低。
   - （缓存）Turms服务端各功能模块充分利用本地内存缓存

## 子项目

| 名称                                                         | 描述                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| <span style="white-space:nowrap;">turms</span>               | Turms服务端。对用户提供各种IM业务逻辑的实现，对管理员提供基础数据管理、权限控制、集群配置等功能 |
| <span style="white-space:nowrap;">turms-gateway</span>       | Turms客户端网关（推送服务端）。负责用户鉴权与会话保持、消息推送，以及Turms服务端的负载均衡等功能 |
| <span style="white-space:nowrap;">turms-client-js</span>     | 对外暴露IM业务相关的API接口，并在底层实现与Turms服务端的各种交互逻辑（如心跳检查）。您在使用该库时，无需关心背后的逻辑 |
| <span style="white-space:nowrap;">turms-client-kotlin</span> | 同上                                                         |
| <span style="white-space:nowrap;">turms-client-swift</span>  | 同上                                                         |
| <span style="white-space:nowrap;">turms-admin</span>         | 为Turms服务端集群提供：内容管理、集群配置等功能              |
| ~~<span style="white-space:nowrap;">turms-apm</span>~~       | （该项目将会被移除，其功能将会集成到turms-admin中）为Turms服务端集群提供监控与报警功能 |
| <span style="white-space:nowrap;">turms-plugin</span>        | 当指定事件（如用户上下线、消息接收与转发等）被触发时，turms和turms-gateway会调用对应的自定义插件以方便开发者实现各种各样定制化功能 |
| <span style="white-space:nowrap;">turms-plugin-minio</span>  | 基于turms-plugin实现的存储服务插件。用于与MinIO服务端进行交互 |
| <span style="white-space:nowrap;">turms-data（TODO）</span>  | 尚未发布。基于Flink生态的独立数据分析系统，负责业务数据统计与分析，为turms的管理员统计API与turms-admin运营报表提供底层数据支持 |
| <span style="white-space:nowrap;">turms-client-cpp（TODO）</span> | 尚未发布。                                                   |

## 参考架构

Turms的架构设计脱胎于商用即时通讯架构。下图为Turms的参考架构图，由虚线框起来的服务为可选服务，由实线框起来的服务则为必选服务。具体架构细节请查阅该[Turms架构设计](https://turms-im.github.io/docs/for-developers/architecture.html)。

![参考架构图](https://raw.githubusercontent.com/turms-im/assets/master/turms/reference-architecture.png)

## 产品对比

|          | [Rocket.Chat](https://github.com/RocketChat/Rocket.Chat)     | 大量具有高关注度的低质即时通讯项目                           | Turms                                                        |
| -------- | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| 应用场景 | 企业内部通讯                                                 | 企业内部通讯                                                 | 通用的中大规模即时通讯场景（为二次开发提供实际操作的可能）   |
| 优点     | 1. 客户端支持众多平台且开箱即用<br />2. 带有完整且风格统一的UI套件<br />3. 具有大量的拓展即时通讯功能，包括音视频会议、文件传输、桌面共享等高级功能<br />4. 商业版有技术团队支持 | 1. 部分开发者具有开源奉献精神                                | 优点即上文所述特性                                           |
| 缺点     | 1. 只适合小规模部署（千人以下）<br />2. 适用场景窄，功能可定制性差 | 1. 项目技术人员技术视野窄，代码质量过低，无软件工程思维，总体水平业余<br />2. 项目大多具有玩票性质。通常维护者在长期维护过程中，会发现当前服务架构混乱不堪，但又没能力重构，或者发现同领域内还有其他开源的且对方项目具有碾压性优势的时候，热情大减，放弃继续维护项目<br />3. 项目大多哗众取宠（通常伴随互刷关注度）。由于吸引初级程序员更容易快速获取关注度，该类项目多会提供一些业余水平的UI界面与外强中干的产品功能，最终积重难返，彻底沦为玩具项目<br />4. 部分项目仅公开部分源码，以假借开源名义来推广低质的收费服务。但其收费服务远不如免费的Rocket.Chat，跟融云、网易云信等成熟的商业服务相比就更无优势可言了<br />5. KPI项目或面试用项目。目的完成后便抛弃项目 | 1. 只满足通用的即时通讯需求，不提供拓展功能的实现（如：不具备常用的音视频会议功能。TODO：Turms后期会基于SFU媒体服务器为Turms主服务端定制一套信令服务端，目前您可自行选择其他音视频会议解决方案与Turms进行集成）<br />2. 配套的管理员运维系统不提供专业的运维功能（注意：Turms配套的管理员系统和商用的运维系统是相辅相成的）。<br />3. 不提供客户端具体的上层业务逻辑实现与UI支持<br />4. 服务端与客户端的自动化测试力度低<br />5. 服务端基于响应式编程，对二次开发者的技术水平要求相对高 |
| 总评     | 几乎是开源届中企业内部通讯实现的最优开源项目，非常推荐       | 受众主要是不了解即时通讯领域的初级程序员，Rocket.Chat跟这类产品相比具有碾压性的优势 | Rocket.Chat和Turms虽然同为即时通讯领域的开源项目，但二者在应用场景上几乎没有交集。<br />因为Tumrs是面向通用的中大型即时通讯应用场景，且相对底层的即时通讯引擎。您无法直接将Turms引擎交给您的客户使用（就像大部分产品不会让客户直接写SQL语句来查询数据库里的业务模型一样）。<br />但基于Turms，您可以更高效、更全方位、更扩展地实现目前GitHub上所有开源的即时通讯项目 |

## 关于带具体业务实现的Demo

出于Turms引擎的定位，Turms并不打算在近期提供带UI与具体业务逻辑的客户端Demo。

一方面，在业务层面，Turms已经足够简单易用了，若您仅是想自行测试Turms的业务功能，您甚至无需敲一行代码，即可运行Turms服务端。仅需十来行代码就可以实现客户端的登陆、发送消息、发送好友请求等等多种业务操作，修改下业务相关配置，即可定制各种业务。

另一方面，Demo的设计与实现与具体业务场景、具体的编程语言、具体的技术架构、具体的运行平台都密切相关。而Turms引擎一直是致力于高效地满足各种复杂多变的即时通讯业务场景，不希望因为Demo限制了开发者的想象力。并且开发与维护Demo也非常地费时费力，会拖慢Turms服务端的工作进度。