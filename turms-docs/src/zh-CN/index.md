<p align="center">
  <img height="100" src="https://raw.githubusercontent.com/turms-im/assets/9dbc34a9d78a68f9f7df2430b4066c82bf8a458f/logo/logo.svg">
</p>

[English](https://github.com/turms-im/turms/blob/develop/README.md)

## Turms 是什么

Turms是一套全球范围内最为先进的、为同时在线用户数为100K~10M应用而设计的开源即时通讯引擎。

若想详细了解Turms项目，您可以阅读[Turms文档](https://turms-im.github.io/docs/zh-CN/)。下文为Turms项目的概要。

## Playground

（当前Demo的服务端版本：`ghcr.io/turms-im/turms-admin:latest`、`ghcr.io/turms-im/turms-gateway:latest`、`ghcr.io/turms-im/turms-service:latest`）

* turms-admin服务端地址：http://playground.turms.im:6510

  登录账号与密码均为：`guest`（该账号有查询与增加领域模型的权限，无更新与删除领域模型的权限）

* turms-gateway服务端地址：http://playground.turms.im:10510 （WebSocket端口）、http://playground.turms.im:11510 （TCP端口）

* turms-service服务端的管理员API地址（DEV配置，带Mock数据）：http://playground.turms.im:8510

* Prometheus服务端地址：http://playground.turms.im:9090；Grafana服务端：http://playground.turms.im:3000

您可以使用任意turms-client-(java/js/swift)客户端，向turms-gateway服务端发送请求，并与其他用户进行交互。

另外，Playground由一条指令全自动搭建：`ENV=dev,demo docker compose -f docker-compose.standalone.yml --profile monitoring up --force-recreate -d`

## Quick Start

通过以下命令，可以在本地全自动地搭建一套完整的Turms最小集群（包含turms-gateway、turms-service与turms-admin）及其依赖服务端（MongoDB分片集群与Redis）

```sh
git clone --depth 1 https://github.com/turms-im/turms.git
cd turms
docker plugin install grafana/loki-docker-driver:latest --alias loki --grant-all-permissions
docker compose -f docker-compose.standalone.yml up --force-recreate
```

等集群完成搭建后，可以通过[http://localhost:6510](http://localhost:6510)访问turms-admin后台管理系统，并输入账号密码（默认均为`turms`）。如果登录成功，则说明Turms服务端也已经成功启动。

另外您也可以通过Turms提供的Terraform module，来快速购买/搭建云环境（默认使用抢占式实例）并在服务器上搭建Turms集群与依赖服务端。在`terraform apply`命令执行完毕后，等待约3~15分钟（阿里云ECS拉取ghcr镜像很慢），然后再访问`http://公网IP:6510`，如果可以访问turms-admin后台管理系统，则表明搭建成功。

**（特别注意：以下命令会自动购买云服务，并在您账号中扣除相应费用）**

```sh
git clone --depth 1 https://github.com/turms-im/turms.git
cd turms/terraform/alicloud/playground
export ALICLOUD_ACCESS_KEY=<your_access_key>
export ALICLOUD_SECRET_KEY=<your_secret_key>
terraform init
terraform apply
```

## 简介

Turms基于读扩散消息模型进行架构设计，对业务数据变化感知同时支持推模式、拉模式与推拉模式（详细文档：[Turms业务数据变化感知](https://turms-im.github.io/docs/zh-CN/design/status-aware)），其他大部分的设计细节也源自商用即时通讯项目。并且相比很多技术栈落后的开源项目或闭源商用项目，Turms解决方案也是全球即时通讯开源领域内唯一一个基于现代化架构与现代化工程技术，并且适合中大规模部署的解决方案。

另外，架构设计是权衡的艺术，部分IM产品以功能丰富为口号，但功能丰富的代价就是只适用于小体量的用户规模（如企业内部通讯）。而Turms以极限性能为第一要义，同时支持完整的（而非丰富的）IM业务功能，以支持中大规模即时通讯场景。具体原因可查阅[Turms集合设计](https://turms-im.github.io/docs/zh-CN/design/schema)以及[Turms可观测性体系](https://turms-im.github.io/docs/zh-CN/server/module/observability)相关文档。

当您需要将Turms与其他开源IM项目做具体特性的比对时，您可以先照着Turms下述的特性与其他开源IM项目进行比对。通常情况下，您能通过这样的比对，发现专业IM项目与业余IM项目之间的区别。另外，在`产品对比`章节下，我们也提到了Turms项目的缺点供您参考。

注意：当前Turms项目的主要缺点是不对直播/聊天室业务场景提供支持。直播/聊天室业务场景的技术实现并不难，但其产品需求、质量属性要求与约束性条件与一般的社交场景存在着较大差异，故Turms第一版设计不对其提供支持；另外，Turms也不太适用于小规模的企业通讯场景，用Turms往企业通讯场景上套就有点“杀鸡用牛刀”，因为企业通讯更强调功能丰富而非极限性能，与Turms的目标不符，所以二者的上层设计也不同。如果希望支持企业通讯场景，您还需要对Turms进行二次开发。

### 业务功能相关特性

1. （业务功能完善性）Turms支持几乎所有商用即时通讯产品所支持的[即时通讯相关功能](https://turms-im.github.io/docs/zh-CN/feature/)（甚至还有更多的业务功能），且无业务功能限制，同时也支持一些诸如敏感词过滤（基于双数组Trie的AC自动机算法实现）、消息冷热分离存储等高级IM功能。
2. （功能拓展性）Turms同时支持两种拓展模式：配置参数与开发插件。当然您也完全可以对源码进行修改。目前用于接入的MinIO对象存储服务的插件turms-plugin-minio就是基于turms-plugin实现的。
3. （配置灵活性）Turms提供了上百个配置参数供用户定制，以满足各种需求。并且大部分配置都可以在集群运作时（不需要停机），进行集群级别的同步更新，并且无性能损失。

### 通用架构特性

1. （敏捷性）支持在用户无感知的情况下，对Turms服务端进行停机更新，为快速迭代提供可能
2. （可伸缩性）无状态架构，Turms集群支持弹性扩展与异地多活的部署实现，用户可通过DNS就近接入
3. （可部署性）支持容器化部署，方便与云服务对接，以实现全自动化部署与运维。Turms默认提供了docker镜像、docker-compose脚本、Terraform模块三套容器化部署方案
4. （可观测性）具备相对完善的可观测性体系设计，为业务统计与错误排查提供可能
5. （可拓展性）能同时支持中大型即时通讯场景，即便用户体量由小变大也无需重构（当然，对于大型运用场景还有很多优化的工作需要做，但当前架构不影响后期的无痛升级）
6. （安全性）提供限流防刷机制与全局用户/IP黑名单机制，以抵御大部分CC攻击
7. （简单性）核心架构“轻量”，方便学习与二次开发（原因请查阅 [Turms架构设计](https://turms-im.github.io/docs/zh-CN/design/architecture)）
8. Turms使用MongoDB分片架构，并支持请求路由（如读写分离）、冷热数据分离，同时也支持跨地域多活部署与数据主主同步，为大规模跨国部署提供实际操作的可能

### 其他特性

1. 重视可观测性体系建设（详细文档：[Turms可观测性体系](https://turms-im.github.io/docs/zh-CN/server/module/observability)）。具体而言包括以下三个维度：

   * 日志（针对事件）：共提供了三大类日志：监控日志、业务日志、统计日志
   * 度量（针对可聚合数据）。包括实时的系统运行状态信息，以及实时的业务数据
   * 链路追踪

   补充：Turms服务端自身会在实现高效的前提下尽可能提供更多监控数据，但不提供一些尽管常见但对性能影响较大，且更适合第三方服务实现的功能（如：日活）。对于这类拓展功能，您可以通过对Turms的日志与度量数据进行离线或实时分析来实现该类功能。

2. 运作极为高效。

   Turms服务端在所有业务流程的代码实现上，都对性能有着极致追求，具体请查阅代码实现。

   - 网络
     - I/O：Turms服务端基于响应式编程，Turms服务端的所有网络请求在底层都是基于Netty的异步无阻塞模型实现的（包括数据库调用、Redis调用、服务发现注册、RPC等）。因此Turms服务端在各个功能模块上都能充分利用硬件资源（而传统服务端不能）
     - 编码：Turms服务端与Turms客户端间的通信数据采用Protobuf编码；Turms服务端之间的RPC请求与响应均采用定制化的二进制编码，以保证极致的高效。
   - 线程
     - Turms服务端具有优秀的线程模型，其线程峰值数恒定，与在线用户规模以及请求数无关。由于Turms接入层默认线程数与CPU数一致，因此Turms服务端能充分利用CPU缓存，并相比传统服务端，极大地减少了线程上下文切换开销与线程争用
     - 业务逻辑处理过程中，几乎无加锁操作，只有CAS操作
   - 内存
     - 在划分内存空间时，合理且充分地循环利用堆内存与直接内存
     - Turms通过重写MongoDB/Redis客户端依赖的部分实现，保证了Turms服务端中无冗余的内存分配，极大地提高了内存的有效使用率
   - 缓存：Turms服务端各功能模块充分利用本地内存缓存

## 子项目

| 名称                                                         | 描述                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| <span style="white-space:nowrap;">turms-gateway</span>       | Turms客户端网关（推送服务端）。负责用户鉴权与会话保持、消息推送，以及为turms-service服务端提供的负载均衡等功能 |
| <span style="white-space:nowrap;">turms-service</span>       | Turms业务处理服务端。对用户提供各种IM业务逻辑的实现，对管理员提供基础数据管理、权限控制、集群配置等功能 |
| <span style="white-space:nowrap;">turms-admin</span>         | 为Turms服务端集群提供：内容管理、集群配置等功能              |
| <span style="white-space:nowrap;">turms-client-js</span>     | 对外暴露IM业务相关的API接口，内部实现与Turms服务端的各种交互逻辑（如心跳检查），并且支持浏览器标签页共享WebSocket连接的高级特性。您在使用该库时，通常无需关心背后的逻辑 |
| <span style="white-space:nowrap;">turms-client-kotlin</span> | 对外暴露IM业务相关的API接口，内部实现与Turms服务端的各种交互逻辑（如心跳检查）。您在使用该库时，通常无需关心背后的逻辑 |
| <span style="white-space:nowrap;">turms-client-swift</span>  | 同上                                                         |
| <span style="white-space:nowrap;">turms-client-dart</span>   | 同上                                                         |
| <span style="white-space:nowrap;">turms-plugin</span>        | 当指定事件（如用户上下线、消息接收与转发等）被触发时，turms-gateway和turms-service会调用对应的自定义插件以方便开发者实现各种各样定制化功能 |
| <span style="white-space:nowrap;">turms-plugin-antispam</span> | 基于双数组Trie的AC自动机算法实现的反垃圾机制（检测的时间复杂度为O(n)，n为目标字符串code points的长度） |
| <span style="white-space:nowrap;">turms-plugin-minio</span>  | 基于turms-plugin实现的存储服务插件。用于与MinIO服务端进行交互 |
| <span style="white-space:nowrap;">turms-plugin-rasa</span>   | 基于turms-plugin实现的聊天机器人插件。用于与Rasa服务端进行交互 |
| <span style="white-space:nowrap;">turms-data（TODO）</span>  | 尚未发布。基于Flink生态的独立数据分析系统，负责业务数据统计与分析，为turms的管理员统计API与turms-admin运营报表提供底层数据支持 |

## 参考架构

Turms的架构设计脱胎于商用即时通讯架构。下图为Turms的参考架构图，由虚线框起来的服务为可选服务，由实线框起来的服务则为必选服务。具体架构细节请查阅该[Turms架构设计](https://turms-im.github.io/docs/zh-CN/design/architecture)。

![参考架构图](https://raw.githubusercontent.com/turms-im/assets/master/turms/reference-architecture.png)

## 产品对比

全球开源的IM项目虽多，但为中大型IM应用场景设计的开源IM项目，目前有且仅有Turms这一个项目。

|          | [Rocket.Chat](https://github.com/RocketChat/Rocket.Chat)     | 大量具有高关注度的低质IM项目                                 | 闭源的即时通讯云                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             | Turms                                                        |
| -------- | ------------------------------------------------------------ | ------------------------------------------------------------ |--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------| ------------------------------------------------------------ |
| 应用场景 | 企业内部通讯                                                 | 企业内部通讯                                                 | 通用的即时通讯场景                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            | 通用的中大规模即时通讯场景（为二次开发提供实际操作的可能）<br />（注：第一版设计不对直播/聊天室业务场景提供支持） |
| 优点     | 1. 提供云服务，点点鼠标即可启动集群，并对外提供服务<br />2. 客户端支持众多平台且开箱即用<br />3. 带有完整且风格统一的UI套件<br />4. 具有大量的拓展即时通讯功能，包括音视频会议、文件传输、桌面共享等高级功能<br />5. 商业版有技术团队支持 | 1. 部分开发者具有开源奉献精神                                | 1. 提供云服务，点点鼠标即可启动集群，并对外提供服务<br />2. 客户端支持众多平台且开箱即用<br />3. 带有完整且风格统一的UI套件<br />4. 具有大量的拓展即时通讯功能，包括音视频会议、文件传输、桌面共享等高级功能<br />5. 商业版有技术团队支持                                                                                                                                                                                                                                                                                                                                                                                                                                            | 优点即上文所述特性。<br />补充：外网自建，无需公安备案       |
| 缺点     | 1. 只适合小规模部署（千人以下）<br />2. 适用场景窄，功能可定制性差 | 1. 项目技术人员技术视野窄，代码质量过低，无软件工程思维，总体水平业余。如：系统不具备可观察性、无防刷与黑名单机制，被CC攻击时，只能停机<br />2. 项目大多具有玩票性质。通常维护者在长期维护过程中，会发现当前服务架构混乱不堪，但又没能力重构，或者发现同领域内还有其他开源的且对方项目具有碾压性优势的时候，热情大减，放弃继续维护项目<br />3. 项目大多哗众取宠，通常还伴随互刷关注度。由于吸引初级程序员更容易快速获取关注度，该类项目多会提供一些业余水平的UI界面与外强中干的产品功能，最终积重难返，彻底沦为玩具项目<br />4. 部分项目仅公开部分源码（如只公开客户端代码，不公开服务端代码），以假借开源名义来推广低质的收费服务。但其收费服务远不如免费的Rocket.Chat，跟融云、网易云信等成熟的商业服务相比就更无优势可言了<br />5. KPI项目或面试用项目。目的完成后便抛弃项目 | 1. 闭源，无法自定义实现。任何项目在业务增长之后必将出现新的业务需求，需要进行定制。但通讯云要么不提供定制服务，要么需要高昂的定制费用，且第三方平台可能会对您业务理解出现偏差，造成定制功能不能很好地满足您业务需求，二者需要长期的磨合。<br />而基于Turms自研就可以快速开发并快速上线，成本也低。另：即时通讯的复杂性可以参考[集合结构设计](https://turms-im.github.io/docs/zh-CN/design/schema#%E9%9C%80%E6%B1%82%E5%88%86%E6%9E%90%E4%B8%8E%E9%9B%86%E5%90%88%E7%BB%93%E6%9E%84%E8%AE%BE%E8%AE%A1)<br />2. 数据泄露。您所有的用户信息与聊天记录都存储在第三方平台，其可以偷窥和使用您的数据。<br />特别是一些IM小公司，数据的安全性完全没有保障，您甚至需要承受数据丢失无法恢复的风险<br/>3. 越用越依赖，越用越贵。多数通讯云提供一定的免费额度或试用期，但在您产品的用户规模增长之后，您需要支付高昂的使用费或放弃使用开始自研<br/>4. 技术支持不及时。通讯云需要同时对多个客户提供技术支持，对您产品的支持可能滞后<br />5. 需要公安备案 | 1. 只满足通用的即时通讯需求，不提供拓展功能的实现（如：不具备常用的音视频会议功能。TODO：Turms后期会基于SFU媒体服务器为Turms主服务端定制一套信令服务端，目前您可自行选择其他音视频会议解决方案与Turms进行集成）<br />2. Turms第一版设计不对直播/聊天室业务场景提供支持<br />3. 服务端只提供度量/日志等原始数据，不提供分析与报警等功能<br />4. 配套的Web后台管理系统`turms-admin`不提供专业的运维功能（注意：Turms配套的后台系统和商用的运维系统是相辅相成的）。<br />5. 不提供客户端具体的上层业务逻辑实现与UI支持<br />6. 服务端基于响应式编程，对二次开发者的技术水平要求相对高 |
| 总评     | 几乎是开源届中企业内部通讯实现的最优开源项目，非常推荐       | 受众主要是不了解即时通讯领域的初级程序员，Rocket.Chat跟这类产品相比具有碾压性的优势 | 如果您产品所涉及的IM业务场景非常常规，没有定制化需求，且IM业务也不是您产品的主营业务，则推荐使用成熟的即时通讯云。另外，如果没有特殊原因，尽量不要使用小公司的通讯云服务，否则您的数据安全性将毫无保障                                                                                                                                                                                                                                                                                                                                                                                                                                                                                | Rocket.Chat和Turms虽然同为即时通讯领域的开源项目，但二者在应用场景上几乎没有交集。<br />因为Tumrs是面向通用的中大型即时通讯应用场景，且相对底层的即时通讯引擎。您无法直接将Turms引擎交给您的客户使用（就像大部分产品不会让客户直接写SQL语句来查询数据库里的业务模型一样）。<br />但基于Turms，您可以更高效、更全方位、更扩展地实现目前GitHub上所有开源的即时通讯项目 |

## 关于带具体业务实现的Demo

考虑到Turms项目的定位，Turms并不打算在近期提供带UI与具体业务逻辑的客户端Demo。因为：

1. 在业务层面，Turms已经足够简单易用了，若您仅是想自行测试Turms的业务功能，您甚至无需敲一行代码，即可运行Turms服务端。仅需十来行代码就可以实现客户端的登陆、发送消息、发送好友请求等等多种业务操作，修改下业务相关配置，即可定制各种业务。

2. Demo的设计与实现与具体业务场景、具体的编程语言、具体的技术架构、具体的运行平台都密切相关。而Turms引擎一直是致力于高效地满足各种复杂多变的即时通讯业务场景，不希望因为Demo限制了开发者的想象力。并且开发与维护Demo也非常地费时费力，会拖慢Turms项目的开发进度。

3. 目前，您只需跟GPT-3.5与GPT-4“聊天”，即可实现定制技术方案与UI设计了。以文字稿为例（另外，GPT-4支持图片输入，您也可以绘制UI线框，以提示它要如何设计UI）：

   > 请基于Vue3、Vite、Eslint等技术来实现一个运行在Web端的客服聊天窗口。具体要求： 
   >
   > 1. UI设计风格需要参考：Ant Design
   > 2. 该聊天窗口大体分为三个部分：在顶部，需要显示客服的名称信息；在中部，需要显示用户与客服的聊天消息；在底部提供一个文字输入框与发送按钮，以让用户能够输入文本与发送消息。
   > 3. 该聊天窗口需要始终在网页的右小角置顶显示
   > 4. 你需要假定该聊天窗口基于WebSocket协议与后台服务端进行通信，以完成登陆、发送消息、接受消息等操作
   > 5. 你需要基于UI组件化设计方案，给出项目结构、项目中的所有具体代码实现
   
   GPT能马上提供对应的代码实现，并且您还能在各种方案（您可以让GPT提供与比对多套方案）的基础上不断地跟它“聊天”，以细化它的UI设计与代码实现，让最终实现贴近您的想法。

## 开源协议

Turms项目采用`Apache License 2.0`协议，因此我们并不关心使用者是否计划靠Turms项目盈利，我们只要求使用者遵守`Apache License 2.0`协议，在您的作品处，如文档、视频、代码等处，注明Turms源项目的信息，如：

```
源项目：turms-im/turms
源项目地址：https://github.com/turms-im/turms
源项目文档地址：https://turms-im.github.io/docs
```

### Q & A

1. Turms项目如何盈利？

   我们目前不需要盈利。当然，我们也不排斥盈利，但我们不会为了赚取咨询、培训等费用，而故意不写好文档或不做好项目。另外一提的是，确实有很多（半）开源项目是通过故意不写好文档与不做好开源项目，来赚取服务支持费用。

2. 如果有盈利组织，如培训机构或公司，引用了Turms的文档，甚至把Turms项目做成SaaS服务出售，这些盈利组织需要注意什么吗？

   我们对您的团队是否计划从Turms项目中盈利毫不关心，您的团队只需遵守`Apache License 2.0`协议，注明上述的Turms源项目信息。

3. Turms项目适合做成SaaS服务，那Turms项目为什么不采用AGPL或SSPL协议？

   我们目前不需要盈利，且也没计划盈利，我们只要求使用者遵守`Apache License 2.0`协议。

4. Turms项目如果不盈利，那它的项目质量如何？

   我们的文档与源码已经替我们回答了这个问题，并且在全球开源界，暂时还没有一个开源IM项目能跟Turms项目在中大型IM应用场景中竞争。另外一提的是，商业项目不代表质量高，甚至很多商业项目的文档与代码质量是令人触目惊心的水平。  
   
5. Turms有使用双授权协议或带有隐藏收费条目吗？

   没有。一些开源项目对个人使用免费，对商业使用收费，采用双授权协议，或带有很多隐藏收费条目。而整个Turms项目有且仅使用`Apache License 2.0`协议，也不存在任何收费环节。部分项目自称开源软件，但其实并不是，具体可参考`开源软件`真正的定义：[中文版](https://gitee.com/opensource-guide/guide/%E7%AC%AC%E4%B8%80%E9%83%A8%E5%88%86%EF%BC%9A%E5%88%9D%E8%AF%86%E5%BC%80%E6%BA%90/%E7%AC%AC%201%20%E5%B0%8F%E8%8A%82%EF%BC%9A%E4%BB%80%E4%B9%88%E6%98%AF%E5%BC%80%E6%BA%90/#%E5%BC%80%E6%BA%90%E8%BD%AF%E4%BB%B6)、[英文版](https://en.wikipedia.org/wiki/The_Open_Source_Definition)。

## 特别感谢

Turms项目主要在IntelliJ IDEA与CLion这两个IDE上进行开发。

感谢[JetBrains Community Support Team](https://www.jetbrains.com/community/opensource/#support)为非商业开源项目提供的License。

<p align="center">
  <img height="100" src="https://resources.jetbrains.com/storage/products/company/brand/logos/jb_beam.svg?_gl=1*1xnabwe*_ga*NTkwMTMxNTcuMTY0MTM3MjM3MQ..*_ga_V0XZL7QHEB*MTY0NjQwMDU3OS43LjEuMTY0NjQwMTE5NS42MA..&_ga=2.246007808.508918265.1646398289-59013157.1641372371">
  <img height="100" src="https://resources.jetbrains.com/storage/products/company/brand/logos/IntelliJ_IDEA.svg?_gl=1*1lzm1us*_ga*NTkwMTMxNTcuMTY0MTM3MjM3MQ..*_ga_V0XZL7QHEB*MTY0NjQwMDU3OS43LjEuMTY0NjQwMTM5NC4w&_ga=2.207800214.508918265.1646398289-59013157.1641372371">
  <img height="100" src="https://resources.jetbrains.com/storage/products/company/brand/logos/CLion.svg?_gl=1*1lzm1us*_ga*NTkwMTMxNTcuMTY0MTM3MjM3MQ..*_ga_V0XZL7QHEB*MTY0NjQwMDU3OS43LjEuMTY0NjQwMTM5NC4w&_ga=2.207800214.508918265.1646398289-59013157.1641372371">
</p>