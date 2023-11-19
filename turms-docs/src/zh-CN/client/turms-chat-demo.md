# Turms Chat Demo

## 背景

最初，我们是计划先通过让turms-gateway支持XMPP协议来让用户能够自行复用世界上已有的XMPP客户端。但是不管是收费，还是免费的XMPP客户端质量基本都不高，主要体现在：

1. 大多XMPP客户端项目代码质量差，尤其是很多早期客户端工程师的代码功底很差，甚至会把复杂的UI逻辑与业务逻辑杂糅在一起写（比如著名开源项目JMeter），二次开发不如自己重写。
2. 不管是商业还是开源的UI设计水平基本都停留在业余爱好者水平。如果一个客户端项目没有专业的UI，我们会对其团队的前端工程师与UI设计师的能力表示怀疑（团队中只要有一位靠谱的、中级水平的前端工程师，就应该有独立设计单一产品UI的能力），也不会推荐用户去用他们的方案。
3. 几乎没有一个开源的XMPP客户端支持完整的跨平台方案。
4. 很多质量不高的XMPP客户端甚至需要收费。

考虑到提供一套跨桌面端与移动端IM应用的开发难度不高，主要是体力活，并且IM应用的UI与功能通用性强（在市面上找10款IM商业应用调研，会发现至少有9款IM的UI与功能是基本类似的），因此决定先提供IM客户端Demo`turms-chat-demo-flutter`，让Turms的用户能够自己使用或二次开发，之后再支持XMPP协议。

## RoadMap

* 2023年11月~12月：完成桌面端UI设计；搭建Flutter项目框架；完成桌面端基础组件开发与测试；完整Windows桌面端UI开发与测试。
* 2023年12月~2024年1月：完成MacOS桌面端的UI适配工作；完成移动端基础组件开发与测试；完成Android手机端的UI开发与测试。
* 2024年1月~2024年2月：完成iOS手机端的UI适配工作。
* 2024年2月~3月：完成Web端的UI开发。
* 2024年3月~4月：集成turms-client-dart与实现IM业务逻辑（上述任务只有UI开发与测试，不包括业务逻辑）。

另外：

* 考虑到Turms的其他任务、节假日与工作情况，上述时间可能会略有变动。
* 无计划支持小程序。

## 简介

我们想着重提醒项目名中的一词——`demo`。该词主要有以下几种含义：

1. 不管是从产品角度，还是技术角度，该客户端`demo`也只不过是其中`可能的`的方案之一，用户不应该因为该`demo`而限制设计自身IM产品的能力，尤其不要认为Turms的服务端是为该`demo`定制的，正如Turms文档中反复提及Turms是一个通用IM解决方案，致力于解决各种IM场景。
2. 为用户的二次开发做准备。这主要分为三个方面：
   1. UI与业务逻辑分离。方便需要二次开发的团队复用UI来实现自己的业务逻辑，读者甚至可以只用`turms-chat-demo-flutter`项目，不使用Turms服务端，而是使用自研的IM服务端。
   2. 依旧采用宽松的Apache 2.0，而不是客户端开源项目常见的、更加严格的GPL协议。
   3. 由于全球范围的IM应用的UI设计都非常类似，因此该`demo`也会实现大部分IM的通用UI与逻辑，一般不提供更为定制化的逻辑，以方面其他团队二次开发。

注意：`demo`没有`质量低`的含义，这点读者之后看代码质量与UI设计就可明白。

## 关于二次开发

由于Flutter应用的设计模式众多，很多应用缺乏统一的设计，导致一个应用中存在众多互斥的设计，架构看起来非常混乱。

为了统一本应用的架构与代码设计，方便读者阅读代码，也方便工程师添加代码，本章节对项目的状态管理与架构进行讲解。

### 状态管理

Flutter状态管理方案众多，至少有几十种方案。对于应用级的状态管理：turms-chat-demo-flutter采用主流的、Flutter官方推荐的、更符合Flutter自身设计的、更新勤快的方案，即Riverpod。

尽管Flutter还有其他状态管理方案，但要么是引入不必要的复杂（如：Bloc），要么是侵入性过强（如：GetX），要么是跟Flutter原生风格差异过大，要么是长期不更新，要么是偏实验性的，因此均不采用。

另外，本应用除了用Riverpod实现状态管理，还顺便用它来实现依赖注入（Dependency Injection）。

### 架构

不仅是Flutter的应用架构设计模式本身就很多，而且同一个架构设计也有多种实践方式。本项目基于Flutter应用的设计传统，选择最适合自身情况的架构设计模式：

对于应用级的架构设计：基于Riverpod，采用MVC+S与MVVM混合架构设计。

* Model => Repository。负责增删改查外部数据源接口交互的仓储层。
* View => Widget：负责UI展示。
* Controller + View Model => Controller：负责接收用户输入，并基于Service执行业务处理逻辑；管理业务组件的业务状态（State），供UI层进行展示。
* Service：负责执行业务处理逻辑，上接Controller，下接Repository。不叫常见的`domain`是因为`domain`是一个指代含糊的词，不仅能指代service，也能指代repository，甚至还能同时指代controller、service与repository等，即指代“业务域”。

提醒：

* 本章节所述的Controller是应用架构分层中的Controller，而不是Flutter组件的Controller，如AnimationController。

* 有些Flutter项目的Controller不仅仅是Controller，还是（is）View Model。在本应用中，Controller只是Controller，但同时又包含（has）View Model，即状态（State）。

* 复杂的项目可能会采用5层架构，即：View、Controller、Service、Repository、Data Source。但本应用逻辑相对简单，因此只3层与4层架构，即View、Controller、Service（可选）、Repository。

* 如果读者有阅读过有10年以上历史的桌面端开源项目，就经常能发现这类项目的`Model`类可能会包含比较复杂的业务逻辑。

  这是因为在早期桌面端开发与面向对象设计中，`Model`是一个更综合的概念，常常同时指代现今更为常见的`Model/Entity`（数据模型。不包含数据处理逻辑，或者只包含基本的数据处理逻辑）与`Repository`（获取、处理、响应数据的仓储层）这两个概念。但由于这样的设计明显不符合于关注点分离（Separation of Concerns）的设计理念，因此靠谱的现代项目已经不会采用这样的设计了。

### 目录结构

基于上述的架构设计，该项目的目录结构大体如下：

* ui
  * components：共享UI组件（Widgets），如按钮、标签页等。
  * screens：应用页面。每个页面除了包括Widgets，还包括各自的Controllers。
  * themes：主题。
* domain
  * user
    * services
    * repositories

  * message
    * services
    * repositories

  * ...

* infra：
  * preferences：管理应用本地配置。
  * routes：路由。
  * window：管理桌面端窗口。
  * ...

