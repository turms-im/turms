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

Flutter状态管理方案众多，至少有几十种方案。对于应用级的状态管理：turms-chat-demo-flutter采用主流的、Flutter官方推荐的、更符合Flutter自身设计的、更新勤快的库，即Riverpod。

尽管Flutter还有其他状态管理方案，但要么是引入不必要的复杂（如：Bloc），要么是侵入性过强（如：GetX），要么是跟Flutter原生风格差异过大，要么是长期不更新，要么是偏实验性的，因此均不采用。

另外，本应用除了用Riverpod实现状态管理，还顺便用它来实现依赖注入（Dependency Injection）。

### 架构

不仅是Flutter的应用架构设计模式本身就很多，而且同一个架构设计也有多种实践方式。本项目基于Flutter应用的设计传统，选择最适合自身情况的架构设计模式：

对于应用级的架构设计：基于Riverpod，采用MVC+S与MVVM混合架构设计。

* Model => Repository。负责增删改查外部数据源接口交互的仓储层。
* View => Widget：负责UI展示。
* Controller + View Model：
  * Controller：负责接收用户输入，并基于Service执行业务处理逻辑；管理业务组件的业务状态（State），供UI层进行展示。
  * View Model：负责存储各种状态，并在状态变化时通知监听器（观察者模式）。

* Service：负责执行业务处理逻辑，上接Controller，下接Repository。不叫常见的`domain`是因为`domain`是一个指代含糊的词，不仅能指代service，也能指代repository，甚至还能同时指代controller、service与repository等，即指代“业务域”。

提醒：

* 本章节所述的Controller是应用架构分层中的Controller，而不是Flutter组件的Controller，如AnimationController。

* 复杂的项目可能会采用5层架构，即：View、Controller、Service、Repository、Data Source。但本应用逻辑相对简单，因此只3层与4层架构，即View、Controller、Service（可选）、Repository。

* 如果读者有阅读过有10年以上历史的桌面端开源项目，就经常能发现这类项目的`Model`类可能会包含比较复杂的业务逻辑。

  这是因为在早期桌面端开发与面向对象设计中，`Model`是一个更综合的概念，常常同时指代现今更为常见的`Model/Entity`（数据模型。不包含数据处理逻辑，或者只包含基本的数据处理逻辑）与`Repository`（获取、处理、响应数据的仓储层）这两个概念。
  
  但一方面，这样的设计明显不符合于关注点分离（Separation of Concerns）的设计理念。
  
  另一方面，我们常说类的设计要高内聚，低耦合。而如果以类为点，以类的分层为面（一种体现方式就是目录结构），将数据模型与仓储层逻辑混合在一起，也会让代码分层低内聚，高耦合，是一种由无知导致的反范式设计，而不是一种有意识地反范式设计。
  
  因此靠谱的现代项目已经不会采用这样的设计了。
  
* 有些Flutter项目的Controller不仅仅是Controller，还是（is）View Model。但其缺点如上所述。

  因此在本应用中，Controller只是Controller，并且可以包含（has）零至多个View Models，即状态（States）。

* 很多优秀的设计理念都是互通的，只是具体的叫法可能不同。在Web应用领域中，我们在设计UI组件时，通常会区分设计Smart Components（包含逻辑，尤其是业务逻辑）与Dumb Components（不包含逻辑，或只包含非常简单的逻辑）。上述turms-chat-demo所说的`View`就是Dumb Component，而`View`与`Controller`共同构成了Smart Components。

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

UI组件与域（Domain）是多对多关系，这也是为什么turms-chat-demo不像很多应用把UI组件与业务域放置在同级目录的原因。

### IPC

turms-chat-demo的IPC采用WebSocket协议与JSON-RPC 2.0传输格式实现，用于实现单例应用、自动更新、与第三方应用通信等功能。

不用Unix Domain Socket的原因是：

* Windows平台自身有关Unix Socket的Bugs非常多。

  如：

  * https://github.com/golang/go/issues/33357
  * 至今（2024年3月），Windows的官方文章虽然自称其支持Abstract Namespace，但实际上并不支持（相关Bug Issue：https://github.com/microsoft/WSL/issues/4240）。

* 减少不必要的开发与维护成本。由于MacOS与Windows都不支持Abstract Namespace功能，因此需要借助文件系统来实现：

  * Unix Socket Domain要求文件的路径长度字符不得超过108个字符（null-terminated string）。而我们为了保证程序的健壮性，自然需要做各种兜底处理，导致代码繁琐。
  * 如果出现turms-chat-demo没有被正常关闭的情况（如用户电脑死机），文件系统中的Unix Domain Socket并不会被自动删除，因此需要检测Unix Domain Socket对应的文件是否还有效，对于不同平台需要采用不同的实现。如对于Linux与MacOS平台，则在服务端Socket bind之前，先unlink这个文件。而对于Windows平台，则先创建一个临时文件，并在程序运行中，对其加锁，而如果Unix Domain Socket文件存在，且这个临时文件也有锁，则说明是有效Socket，否则无效。当然，这里说的只是大致实现思路，由于平台的实现细节不同，且Windows平台不同版本有的Bugs也不同，实际还需要进行大量测试与适配。
  * 至今（2024年3月），Dart SDK自身并不支持Windows平台的Unix Domain Socket，需要自行基于`win32`库实现Unix Socket Domain。

* 拓展性差。具体原因见下文。

采用WebSocket + JSON-RPC 2.0的原因是：

* Dart官方提供了现成`json_rpc_2 `库，支持WebSocket + JSON-RPC 2.0，因此采用该方案对我们来说几乎没有维护成本。
* turms-chat-demo自身虽然需要使用到IPC操作，但使用频率极低，因此无需像Turms服务端代码那样追求极致的性能。
* 方便第三方应用基于WebSocket + JSON-RPC 2.0调用turms-chat-demo。
* 互联网上有大量支持WebSocket协议与JSON格式的客户端工具，基于这些工具可以很方便地调试turms-chat-demo的IPC功能。

### 文本编辑

#### 文本编辑器的库生态

* appflowy_editor。appflowy_editor无疑是Flutter社区中，功能最全，质量最高的文本编辑器。但可惜它采用双开源协议，而其中一个就是AGPL。因此不考虑。

* flutter_quill：如果撇开该依赖库Bugs极多的问题，该库基本满足turms-chat-demo主要的功能需求，但维护者的编程功底太差，代码是典型的面条式代码（Spaghetti code），总体水平尚未入门，因此没有对其进行功能拓展的意愿，不考虑采用该项目。

* super_editor：该项目代码质量高，但很多文本编辑的基础功能都不支持（2024年3月），如：redo/undo、inline image。用它不如直接用Flutter自带的`TextField`。

综上，考虑到没有可靠的开源文本编辑器可用，因此采用Flutter自带的`TextField`。

#### 聊天文本协议

##### 关于是否要支持单条消息能够展示多个视频与图片

支持单条消息展示多个视频与图片，对UI设计与软件性能都不太友好。

一方面，在UI上展示一条消息时，消息的尺寸必须是不变，否则如果随着消息加载状态的变化，如加载中，加载失败，消息的尺寸会发生变化，那对用户体验来说是非常糟糕的。因此为了保证消息的尺寸不会随着消息状态的改变而改变，需要在展示消息UI时，就已经确认消息的整体尺寸。

而为了确认富文本消息的尺寸，要么在消息发送的过程中，由消息的发送方或服务端确认消息中所有图片与视频的缩略图的尺寸，并将这个尺寸记录下来，之后作为消息的一部分传给消息接收方，但这个方案的缺点就是实现不灵活，只要缩略图尺寸改变，那么之前的尺寸记录就都失效了。

要么是要求只有当消息的接收方把一个消息中的所有图片与视频的缩略图都下载完成之后，再根据缩略图去确定消息的尺寸，最后做UI展示。但这个方案有两个比较大的缺点，一是消息接收方必须等到所有缩略图都下载完成了，客户端才能展示这条消息，因此消息的延迟会增加。二是，如果单条消息中的部分缩略图下载失败，则客户端无法获取到消息的完整尺寸，此时为了依旧展示消息，需要使用占位文本或占位图。而既然下载失败，那自然是要给用户提供重新下载缩略图的渠道，但重新下载之前下载失败的缩略图时，这条消息的整体尺寸会发现变化，进而导致整个消息列表的尺寸发生变化，这都不是优雅的UI设计。

同时考虑到文本编辑器的库生态，以及自研文本编辑器的开发成本与工期考虑，因此暂不支持富文本。

特别一提的是，可能读者见过一些初级工程师开发的聊天应用反而敢做各种复杂的UI展示。这是因为：初级工程师通常只会考虑自己这块业务的简单功能需求，尤其不知道有“非功能需求”这一概念的存在。而高级工程师会从产品需求、前端UI设计、后端架构设计、运维成本、当下系统架构与架构演变方向，甚至合规的角度去综合考虑需求。

##### 文本协议

提醒：turms-chat-demo的文本编辑框是WYSIWYG编辑器，因此不管应用的文本协议如何设计，对于用户来说都是无感知的。

turms-chat-demo的传输文本协议设计参考了Markdown，其大体设计思路是：如果某个样式已经被标准Markdown定义过了，则复用Markdown的文本协议格式。如果某个样式没有被标准Markdown定义，则参考Markdown的设计思路，添加自定义的文本协议格式。

而消息中的各种文件，如图像、音频、视频等，即对应着资源对应的URL。

turms-chat-demo自身会根据资源URL的后缀，将其展示为对应UI组件。另外，turms-chat-demo不会判断资源的URL是否属于指定的服务端，因为我们的本意就是希望展示任意来源的资源。如果读者就是希望只解析来自某服务端的资源，将不是来自该服务端的资源展示为纯文本，则需要修改源码，根据资源的URL或者资源提供方的SSL证书来判断资源的来源。

#### Emoji

turms-chat-demo使用系统自带的Emoji字体，即：

* 在Linux平台，采用`Noto Color Emoji'`字体。
* 在苹果平台，采用`Apple Color Emoji`字体。
* 在Windows平台，采用`Segoe UI Emoji`字体。

采用这个方案的原因是：至今为止（2024年3月），整个互联网上没有一个高质量的、明确可免费商用的Emoji字体（提醒：Turms的所有项目，包括turms-chat-demo自身都是开源的，且商用免费的）。

因此采用各个系统自带的Emoji字体来展示Emoji，以避免版权相关问题。其唯一的缺点就是：同一个的Emoji字符在不同平台的展示效果不太一样。

#### Sticky

基于目前全球范围内最流行的表情库[GIPHY](https://giphy.com/)的开发者HTTP接口实现。

#### 缩略图

由于Turms目前没有专门的媒体服务端（Media Service），并且让服务端生成缩略图也需要大量的资源与成本。因此turms-chat-demo采用的是一个折中的方案，即：turms-chat-demo在上传消息的图片或视频时，会先向Turms服务端请求上传信息，Turms服务端会在响应中指导turms-chat-demo需要如何生成对应的缩略图，turms-chat-demo则根据指示去生成对应要求的缩略图，并将生成的缩略图与原始图片或视频都上传到对应的OSS服务。