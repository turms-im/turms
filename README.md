### Turms 是什么

Turms是一套全球范围内最为先进的开源即时通讯引擎。

若您需要基于Turms做二次开发，建议您阅读：[Turms文档](https://turms-im.github.io/docs/)

### Playground

（当前Demo的版本为：[turms-v0.9.0-SNAPSHOT.20200406](https://github.com/turms-im/turms/releases/tag/v0.9.0-SNAPSHOT.20200406)）

turms服务端的Demo地址（自带Mock数据）：http://120.24.57.206:9510/

turms-admin的Demo地址（PROD配置）：http://47.99.56.54:9512/
（登陆turms-admin时，在turms服务端地址栏处输入：http://120.24.57.206:9510/ ，且账号与密码均为：guest。该账号有查询与增加领域模型的权限，无更新与删除领域模型的权限）

您还可以使用任意turms-client(java/js/swift)客户端，来登录该turms服务端，并与其他用户进行各种交互

### 特性

几乎所有的大中小型商用即时通讯解决方案的核心实现就是Turms一整套方案的实现。并且Turms解决方案也是全球即时通讯开源领域内唯一一个基于现代化架构与现代化工程技术，并且适合中大规模部署的解决方案。

另外，Turms作为通用的即时通讯开源项目很难能可贵的一点是：Turms知道什么功能该做，什么功能不该做。具体原因可查阅 [Turms集合设计](https://turms-im.github.io/docs/for-developers/schema.html)。

### 组合

| 名称                                                         | 描述                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| <span style="white-space:nowrap;">turms</span>               | Turms服务端。对用户提供各种IM业务逻辑的实现，对管理员提供基础数据管理、权限控制、集群配置等功能 |
| <span style="white-space:nowrap;">turms-gateway</span>       | Turms客户端网关（推送服务端）。负责用户鉴权与会话保持、消息推送，以及Turms服务端的负载均衡等功能 |
| <span style="white-space:nowrap;">turms-client-js</span>     | 对外暴露IM业务相关的API接口，并在底层实现与Turms服务端的各种交互逻辑（如路由跳转、自动重连、心跳检查等）。您在使用该库时，无需关心背后的逻辑 |
| <span style="white-space:nowrap;">turms-client-java</span>   | 同上                                                         |
| <span style="white-space:nowrap;">turms-client-swift</span>  | 同上                                                         |
| <span style="white-space:nowrap;">turms-admin</span>         | 为Turms服务端集群提供：内容管理、集群配置等功能              |
| <span style="white-space:nowrap;">turms-apm</span>           | 为Turms服务端集群提供监控与报警功能                          |
| <span style="white-space:nowrap;">turms-plugin</span>        | 当指定事件（如用户上下线、消息接收与转发等）被触发时，turms和turms-gateway会调用对应的自定义插件以方便开发者实现各种各样定制化功能 |
| <span style="white-space:nowrap;">turms-plugin-minio</span>  | 基于turms-plugin实现的存储服务插件。用于与MinIO服务端进行交互 |
| <span style="white-space:nowrap;">turms-cli（TODO）</span>   | 尚未发布。运维工具。负责运维整个Turms的生态，如Turms运行环境检测、集群自动搭建、Turms各类服务端的启动与守护等功能 |
| <span style="white-space:nowrap;">turms-data（TODO）</span>  | 尚未发布。基于Flink生态的独立数据分析系统，负责实时ETL与业务数据统计分析，为turms的管理员统计接口与turms-admin报表等运营功能提供底层数据支持 |
| <span style="white-space:nowrap;">turms-client-cpp（TODO）</span> | 尚未发布。                                                   |

### 参考架构

Turms的架构设计脱胎于标准的大中型商用即时通讯架构。下图为Turms的参考架构图，虚线部分为可选服务，实线部分为必选服务（补充：额外的日志系统与数据分析系统不在v1.0.0计划的体系当中）。Turms的整个架构设计中还有许多创新之处，具体架构细节请查阅该[Turms架构设计](https://turms-im.github.io/docs/for-developers/architecture.html)。

![](https://raw.githubusercontent.com/turms-im/assets/master/turms/reference-architecture.png)

### 产品对比

|          | [Rocket.Chat](https://github.com/RocketChat/Rocket.Chat)     | 大量具有高关注度的低质即时通讯项目                           | Turms                                                        |
| -------- | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| 应用场景 | 企业内部通讯                                                 | 企业内部通讯                                                 | 通用的大中小规模商用即时通讯场景（为二次开发提供实际操作的可能） |
| 优点     | 1. 客户端支持众多平台且开箱即用<br />2. 带有完整且风格统一的UI套件<br />3. 具有大量的拓展即时通讯功能，包括音视频会议、文件传输、桌面共享等高级功能<br />4. 商业版有技术团队支持 | 1. 部分开发者具有开源奉献精神                                | 1. 技术格局大，架构设计与实现脱胎于标准商用架构<br />2. 技术掌控能力与落地能力强，具备相对完善的生态圈。Turms不仅包括服务端与客户端实现，还包括配套的管理员后台系统，并注重项目的自动化运维与可观察性建设（TODO）<br />3. 代码设计功底扎实，代码质量高<br />（篇幅有限，更多具体的优点请查阅：[Turms特性](https://turms-im.github.io/docs/intro/features.html)） |
| 缺点     | 1. 只适合小规模部署（千人以下）<br />2. 面向场景窄，功能可定制性差 | 1. 项目技术人员技术视野窄，代码质量过低<br />2. 项目大多具有玩票性质。通常随着维护者技术格局的不断提高，维护者会发现当前服务架构混乱不堪，但又没能力重构，或者发现同领域内还有其他开源的且对方项目具有碾压性优势的时候，热情大减，放弃继续维护项目<br />3. 部分项目仅公开部分源码，以假借开源名义来推广低质的收费服务。但其收费服务远不如免费的Rocket.Chat，跟融云、网易云信等成熟的商业服务相比就更无优势可言了 | 1. 只满足通用的即时通讯需求，不提供拓展功能的实现<br />2. 配套的管理员运维系统与监控系统的功能丰富性与成熟的商业方案仍有一段差距<br />3. 不提供客户端具体的上层业务逻辑实现与UI支持<br />4. 不具备常用的音视频会议功能。TODO：Turms后期会基于SFU媒体服务器为Turms主服务端定制一套信令服务端，目前您可自行选择其他音视频会议解决方案与Turms进行集成<br />5. 服务端与客户端的测试力度低<br />6. 服务端的代码几乎都基于响应式编程开发，对二次开发者的技术水平要求相对高 |
| 总评     | 几乎是开源届中企业内部通讯实现的最优开源项目，非常推荐       | 受众主要是不了解即时通讯领域的初级程序员，Rocket.Chat跟这类产品相比具有碾压性的优势 | Rocket.Chat和Turms虽然同为即时通讯领域的开源项目，但二者在应用场景上几乎没有交集。<br />因为Tumrs是面向通用的大中小型即时通讯应用场景，且相对底层的即时通讯引擎。您无法直接将Turms引擎交给您的客户使用（就像大部分产品不会让客户直接写SQL语句来查询数据库里的业务模型一样）。<br />但基于Turms，您可以更高效、更全方位、更定制化地实现GitHub上目前所有开源的即时通讯解决方案 |

### 关于带具体业务实现的Demo

出于Turms引擎的定位，Turms并不打算在近期提供带UI与具体业务逻辑的客户端Demo。

一方面，在业务层面，Turms已经足够简单易用了，若您仅是想自行测试Turms的业务功能，您甚至无需敲一行代码，即可运行Turms服务端。仅需十来行代码就可以实现客户端的登陆、发送消息、发送好友请求等等多种业务操作，修改下业务相关配置，即可定制各种业务。

另一方面，Demo的设计与实现与具体业务场景、具体的编程语言、具体的技术架构、具体的运行平台都密切相关。而Turms引擎一直是致力于高效地满足各种复杂多变的即时通讯业务场景，不希望因为Demo限制了开发者的想象力。并且开发与维护Demo也非常地费时费力，会拖慢Turms服务端的工作进度。

因此，近期不打算做具体业务场景相关的Demo。