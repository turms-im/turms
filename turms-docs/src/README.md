### Turms 是什么

Turms是一套全球范围内最为先进且全能的开源即时通讯引擎。

[文档地址：https://turms-im.github.io/turms/](https://turms-im.github.io/turms/)

### Playground

（以下Demo的版本始终为 https://github.com/turms-im/turms/releases 处发布的最新版）

turms服务端的Demo地址（DEV配置，自带Mock数据）：http://120.24.57.206:9510/

turms-admin的Demo地址（PROD配置）：http://47.99.56.54:9512/
（登陆turms-admin时，在turms服务端地址栏处输入：http://120.24.57.206:9510/ ，且账号与密码均为：guest。该账号有查询与增加领域模型的权限，无更新与删除领域模型的权限）

您还可以使用任意turms-client(java/js/swift)客户端，来登录该turms服务端，并与其他用户进行各种交互

### 组合

| 名称                                                        | 描述                                                         |
| ----------------------------------------------------------- | ------------------------------------------------------------ |
| <span style="white-space:nowrap;">turms</span>              | Turms服务端。本质是一个易于集群部署、健壮、方便拓展、运作高效且业务独立的Java即时通信服务端程序 |
| <span style="white-space:nowrap;">turms-client-js</span>    | 除了实现即时通讯的业务功能外，在底层还实现了与Turms服务端的交互逻辑（如路由跳转、自动重连、心跳检查等）。您在使用该库时，无需关心背后的逻辑 |
| <span style="white-space:nowrap;">turms-client-java</span>  | 同上                                                         |
| <span style="white-space:nowrap;">turms-client-swift</span> | 同上                                                         |
| <span style="white-space:nowrap;">turms-admin</span>        | 为Turms服务端集群提供：运营数据统计、内容管理、集群配置等功能 |
| <span style="white-space:nowrap;">turms-apm</span>          | 为Turms服务端集群提供监控功能                                |
| <span style="white-space:nowrap;">turms-plugin</span>       | 事件（如用户上下线、消息接收与转发等事件）触发或服务被调用的时候，对应的自定义插件将被触发以实现各种各样定制功能 |
| <span style="white-space:nowrap;">turms-plugin-minio</span> | 基于turms-plugin实现的存储服务插件。用于与MinIO服务端进行交互。 |

### 参考架构

虚线部分为可选服务，实线部分为必选服务。具体架构细节请参考该[文档](https://turms-im.github.io/turms/for-developers/architecture.html)。

特别值得一提是的：在不引入服务发现服务端与负载均衡服务端的情况下，Turms服务端集群自身也能够完成服务发现与负载均衡功能。

![](https://raw.githubusercontent.com/turms-im/assets/master/turms/reference-architecture.png)

### 关于Demo

出于Turms引擎的定位，Turms并不打算在近期提供带UI与具体业务逻辑的客户端Demo。

一方面，在业务层面，Turms已经足够简单易用了，若您仅是想自行测试Turms的业务功能，您甚至无需敲一行代码，即可运行Turms服务端。仅需十来行代码就可以实现客户端的登陆、发送消息、发送好友请求等等多种业务操作，修改下业务相关配置，即可定制各种业务。

另一方面，Demo的设计与实现与具体业务场景、具体的编程语言、具体的技术架构、具体的运行平台都密切相关。而Turms引擎一直是致力于高效地满足各种复杂多变的即时通讯业务场景，不希望因为Demo限制了开发者的想象力。并且开发与维护Demo也非常地费时费力，会拖慢Turms服务端的工作进度。

因此，近期不打算做具体业务场景相关的Demo。

### 补充

如果您所开发的即时通讯产品对定制化要求低，并希望所使用的即时通讯解决方案带有完整的UI套件，并有直接面向客户开箱即用的可执行程序。推荐您尝试开源的：

* [Rocket.Chat](https://github.com/RocketChat/Rocket.Chat) 。非常推荐。可以在所有平台上实现各种即时通讯功能（包括音视频会议、文件传输、桌面共享等高级功能），并且带有非常完善且开箱即用的UI客户端程序，您甚至可以直接将这些程序交给您的客户使用。
* Telegram解决方案

Tumrs的定位是更为底层的通用即时通讯引擎，您无法直接将Turms引擎交给您的客户使用（就像大部分产品不会让客户直接写SQL语句来查询数据库里的业务模型）。但基于Turms，您可以更为高效、更为全能、更为定制化地实现GitHub上目前所有开源的即时通讯解决方案（除音视频会议功能。Turms后期会基于SFU媒体服务器为Turms主服务端定制一套信令服务端，目前您可自行选择其他音视频会议解决方案与Turms进行集成）。