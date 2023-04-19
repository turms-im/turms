# 消息相关功能

## 相关路径与模型

* 管理员API路径：`/messages`。具体API细节请参考OpenAPI文档
* 客户端接口：请查阅`MessageServiceController`类
* 底层请求模型：请查阅`https://github.com/turms-im/proto/tree/master/request/message`目录下的接口描述文件
* 配置类：`im.turms.server.common.infra.property.env.service.business.message.MessageProperties`

## 功能列表

| **<div style="min-width:70px">消息功能</div>** | **功能描述**                                                 | **相关配置**                                                 |
| :--------------------------------------------- | :----------------------------------------------------------- | ------------------------------------------------------------ |
| 离线消息                                       | 实现思路：您可以在Turms客户端每次登陆时，都<主动>向Turms服务端请求关于<该用户在离线状态时，收到的所有私聊与群聊各自具体的离线消息数量，以及各自具体的最后N条消息（默认为1条）>的数据，以此同时兼顾消息的实时性与服务的性能。 默认情况下，Turms服务端<不会>定时删除寄存在Turms服务端的任何离线消息 | turms.service.message.default-available-messages-number-with-total |
| 漫游消息                                       | ✍在新设备登录时，由开发者自行调用Turms客户端的消息查询接口，指定数量与时段等条件，向Turms服务端请求漫游消息。<br />漫游消息的实现本质与“历史消息”的实现一样<br />（✍原因：Turms无法自行判断什么是“新设备登陆”） |                                                              |
| 多端同步                                       | 当一名用户有多客户端同时在线时，Turms服务端会将消息下发给该用户所有在线的客户端 |                                                              |
| 历史消息                                       | 支持查询用户的历史消息。默认Turms永久存储消息（包括用户消息或系统消息）<br />历史消息的实现本质与“漫游消息”的实现一样 | turms.service.message.message-retention-period-hours<br />turms.service.message.expired-messages-cleanup-cron |
| 发送消息                                       |                                                              | turms.service.message.time-type<br />turms.service.message.persist-message<br />turms.service.message.persist-record<br />turms.service.message.persist-pre-message-id<br />turms.service.message.persist-sender-ip<br />turms.service.message.check-if-target-active-and-not-deleted<br />turms.service.message.max-text-limit<br />turms.service.message.max-records-size-bytes<br />turms.service.message.allow-send-messages-to-oneself<br />turms.service.message.allow-send-messages-to-stranger<br />turms.service.message.delete-message-logically-by-default<br />turms.service.message.send-message-to-other-sender-online-devices<br />turms.service.message.use-conversation-id<br />turms.service.message.sequence-id.use-sequence-id-for-group-conversation<br />turms.service.message.sequence-id.use-sequence-id-for-private-conversation |
| 消息撤回                                       | 撤回投递成功的消息，默认允许发信人撤回距投递成功时间 5 分钟内的消息 | turms.service.message.allow-recall-message<br />turms.service.message.available-recall-duration-seconds |
| 消息编辑                                       | 编辑已发送成功的消息                                         | turms.service.message.allow-edit-message-by-sender           |
| 阅后即焚                                       | 收信人接收到发信人的消息后，收信人客户端会根据发信人预先设定（或默认）的时间按时自动销毁 |                                                              |
| 已读回执                                       | ✍通知私聊对象或群组成员中，当前用户已读某条消息<br />查看私聊、群组会话中对方的已读/未读状态<br />（✍原因：Turms无法得知您的用户在什么情况下算是“已读某条消息”。开发者需要自行调用turmsClient.messageService.readMessage()来告知对方，当前用户已读某条消息） | turms.service.conversation.read-receipt.enabled<br />allow-move-read-date-forward<br />turms.service.conversation.read-receipt.update-read-date-after-message-sent<br />turms.service.conversation.read-receipt.update-read-date-when-user-querying-message<br />turms.service.conversation.read-receipt.use-server-time |
| 消息转发                                       | 将消息转发给其他用户或群组                                   |                                                              |
| @某人                                          | 用于特别提醒某用户。如果Turms客户端检测到已接收的消息中被@的用户为当前登陆中的用户，Turms客户端则会触发@回调函数。开发者可自行实现后续相关业务逻辑。常用于给被@的用户提醒通知。<br />群内 @ 消息与普通消息没有本质区别，仅是在被 @ 的人在收到消息时，需要做特殊处理（触发回调函数） |                                                              |
| 正在输入                                       | ✍当通信中的一方正在键入文本时，告知收信人（一名或多名用户），该用户正在输入消息<br />（✍原因：Turms无法得知您的用户是否正在键入文本） | turms.service.conversation.typing-status.enabled             |

### 查询会话消息时的注意事项

默认配置下，Turms不支持“在私聊会话中，消息发送者能够查询他自己发送的消息”（具体原因：[消息索引设计](https://turms-im.github.io/docs/zh-CN/design/schema#%E7%B4%A2%E5%BC%95)。注意：在群聊会话中，消息发送者始终能够查询他自己发送的消息），开发者可以通过在turms-service服务端的配置文件中配置`turms.service.message.use-conversation-id=true`来启用`会话ID`。

之后`turmsClient.messageService.queryMessages({areGroupMessages: false, fromIds: [10,11,12]})`的语义会由原来的“查询私聊会话中，由用户ID为11、12与13的用户发给当前用户的消息”变为“查询私聊会话中，由用户ID为11、12与13的用户发给当前用户的消息，与当前用户发送给用户ID为11、12与13的用户的消息”。

## 业务消息类型

从开发者角度看，Turms客户端在发送消息时内部有且仅使用一种数据模型，即`CreateMessageRequest`。由于它带有string与List<byte[]>类型的字段，因此您实际上能在发送消息时传递任何形式的数据。只是Turms为方便开发者快速实现各种业务消息类型，Turms客户端对常见消息类型做了划分，以方便开发者快速上手。

提醒：Turms的消息（所有业务类型的消息）均可以标记为系统消息。但系统消息只能通过turms管理员API发送，Turms客户端无法发送系统消息。

| **<div style="min-width:100px">业务消息类型</div>** | **描述**                                                     |
| :-------------------------------------------------- | :----------------------------------------------------------- |
| 文本消息                                            | 消息内容为文本<br />提醒：文本也可以是JSON，编码成Base64的二进制数据 |
| 图片消息                                            | 消息内容为描述部分（可选）：图片 URL 地址、尺寸、图片大小<br />图片数据（可选） |
| 语音消息                                            | 消息内容为描述部分（可选）：语音文件的 URL 地址、时长、大小、格式<br />语音数据（可选）<br /> |
| 视频消息                                            | 消息内容为描述部分（可选）：视频文件的 URL 地址、时长、大小、格式<br />视频数据（可选）<br /> |
| 文件消息                                            | 消息内容为描述部分（可选）：文件的 URL 地址、大小、格式<br />文件数据（可选）<br /> |
| 地理位置消息                                        | 消息内容为地理位置标题、地址、经度、纬度信息                 |
| 组合消息                                            | 消息内容为文本信息与任意个数的其他任意内容类消息类型的消息（如：一条消息既包含了文本，也包含了图片与音频） |
| 自定义消息                                          | Turms在传输时仅使用一种数据结构，它自身可以携带string与List<byte[]>数据结构。因此开发者可以自由实现任意的自定义消息类型，例如红包消息、石头剪子布等形式的消息 |

### 二进制数据的传输实现

二进制数据（文件）的传输实现方案主要有以下两种：

|      | 使用Turms客户端发送消息API的records字段（极不推荐）          | 使用对象存储服务（AWS S3、阿里云OSS等）                      |
| ---- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| 简介 | Turms默认支持传递与存储消息附带的二进制数据`records`，因此您可以将图片、视频、文件等二进制数据存储在`records`当中 | 您应用的客户端（注意：这里的“客户端”不是Turms的客户端，是您IM应用的客户端）向您的服务服务端程序请求OSS操作许可Token，由客户端将带着这个Token找到OSS服务并上传文件至OSS，接着拿着从OSS那返回的文件URL传递给Turms服务端，由Turms保存这个URL文本，而不保留文件的二进制数据。<br/>由于Turms插件支持开发者自行实现文件管理服务，因此您也可以通过实现插件的方式实现该功能。比如Turms官方提供的MinIO对象存储服务端的集成实现`turms-plugin-minio`就是基于Turms插件实现的，供您参考 |
| 优点 | 实现简单                                                     | 无限容量；<br />支持CDN加速，优化用户体验；<br />支持UI可视化管理，并提供各种运维管理功能。云存储服务一般都支持诸如冗余存储、服务器端加密、冷热数据分层存储（极大地减低数据存储成本）等实用功能特性 |
| 缺点 | 一个Turms客户端有且仅与服务端建立一个TCP连接，因此如果用户使用Turms客户端自带的`records`字段传输较大的文件，则会阻塞其他业务请求的数据传输；<br />MongoDB在查询消息数据时，会把整条消息记录加载到内存中，极大地拖慢消息查询速度 |                                                              |

参考资料：[存储服务](https://turms-im.github.io/docs/zh-CN/server/module/storage)