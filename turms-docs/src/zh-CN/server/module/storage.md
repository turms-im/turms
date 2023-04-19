# 存储服务

Turms自身并不直接提供存储服务，而是在服务端侧开放了存储服务中常见的接口，以供开发者自行实现，而Turms客户端也提供了相对应的存储服务`turmsClient.storageService`的API，以供开发者自行调用。

注意：

* 开发者完全可以不用Turms客户端与服务端提供的任何接口，而是自己实现一套应用客户端与您自己服务端的交互存储逻辑。Turms只是自己维护了一套常见存储服务的实现，这样大部分开发者就不用自己从零开发了。即便开发者不打算用Turms的存储实现，由于各存储服务实现都是大同小异的，开发者也可以参考Turms的存储实现流程来实现自己的存储逻辑，以节省自研的时间。
* Turms客户端存储服务提供的功能是Turms服务端官方存储服务插件功能的超集，即：Turms客户端存储服务被设计成既可以与Turms服务端官方存储服务插件进行交互，也可以被拓展与其他第三方插件进行交互。

## 插件接口与配置

存储资源目前一共分为三个类型，分别是：`User Profile Picture`（用户资料图片）、`Group Profile Picture`（群组资料图片）与`Message Attachment`（消息附件）。而每个资源都有其对应的增（改）删查三个函数接口，以供开发者实现。

### 接口

插件接口：`im.turms.service.infra.plugin.extension.StorageServiceProvider`

接口函数介绍：

| 资源类型     | 函数名                                                | 预期作用                         | 返回值说明                                                   |
| ------------ | ----------------------------------------------------- | -------------------------------- | ------------------------------------------------------------ |
| 用户资料图片 | deleteUserProfilePicture                              | 删除用户资料图片                 |                                                              |
|              | queryUserProfilePictureUploadInfo                     | 查询用户资料图片上传信息         | 返回值格式为`Map<String, String>`，插件实现者可以自定义任意返回值 |
|              | queryUserProfilePictureDownloadInfo                   | 查询用户资料图片下载信息         | 返回值格式为`Map<String, String>`，插件实现者可以自定义任意返回值 |
| 群组资料图片 | deleteGroupProfilePicture                             | 删除群组资料图片                 |                                                              |
|              | queryGroupProfilePictureUploadInfo                    | 查询群组资料图片上传信息         | 返回值格式为`Map<String, String>`，插件实现者可以自定义任意返回值 |
|              | queryGroupProfilePictureDownloadInfo                  | 查询群组资料图片下载信息         | 返回值格式为`Map<String, String>`，插件实现者可以自定义任意返回值 |
| 消息附件     | deleteMessageAttachment                               | 删除消息附件                     |                                                              |
|              | shareMessageAttachmentWithUser                        | 将消息附件分享给指定用户         |                                                              |
|              | shareMessageAttachmentWithGroup                       | 将消息附件分享给指定群组         |                                                              |
|              | unshareMessageAttachmentWithUser                      | 不再将消息附件分享给指定用户     |                                                              |
|              | unshareMessageAttachmentWithGroup                     | 不再将消息附件分享给指定群组     |                                                              |
|              | queryMessageAttachmentUploadInfo                      | 查询消息附件上传信息             | 返回值格式为`Map<String, String>`，插件实现者可以自定义任意返回值 |
|              | queryMessageAttachmentUploadInfoInPrivateConversation | 查询私聊会话中的消息附件上传信息 |                                                              |
|              | queryMessageAttachmentUploadInfoInGroupConversation   | 查询群聊会话中的消息附件上传信息 |                                                              |
|              | queryMessageAttachmentDownloadInfo                    | 查询消息附件下载信息             | 返回值格式为`Map<String, String>`，插件实现者可以自定义任意返回值 |
|              | queryMessageAttachmentInfosUploadedByRequester        | 查询请求者上传的消息附件         |                                                              |
|              | queryMessageAttachmentInfosInPrivateConversations     | 查询私聊会话中的消息附件         |                                                              |
|              | queryMessageAttachmentInfosInGroupConversations       | 查询群聊会话中的消息附件         |                                                              |

### 通用配置

| 配置项                                                       | 默认值 | 说明                                                  |
| ------------------------------------------------------------ | ------ | ----------------------------------------------------- |
| turms.service.storage.user-profile-picture.expire-after-days | 0      | 自创建时间开始，资源的有效时长（天）。0值代表不会过期 |
| turms.service.storage.user-profile-picture.allowed-referrers | 空     | 只允许指定的Referrers访问资源                         |
| turms.service.storage.user-profile-picture.allowed-content-type | `*/*`  | 允许上传的资源`Content-Type`。`*/*`值代表无限制       |
| turms.service.storage.user-profile-picture.min-size-bytes    | 0      | 允许上传的资源最小值。0值代表无限制                   |
| turms.service.storage.user-profile-picture.max-size-bytes    | 1MB    | 允许上传的资源最大值。0值代表无限制                   |
| turms.service.storage.user-profile-picture.download-url-expire-after-seconds | 300    | 资源下载URL的有效时长（秒）                           |
| turms.service.storage.user-profile-picture.upload-url-expire-after-seconds | 300    | 资源上传URL的有效时长（秒）                           |
| turms.service.storage.group-profile-picture....              |        | 同turms.service.storage.user-profile-picture          |
| turms.service.storage.message-attachment....                 |        | 同turms.service.storage.user-profile-picture          |

## 官方插件实现

### Bucket的基础设计准则

由于对象存储服务提供的功能都大同小异，Turms当前与未来提供的基于对象存储服务的官方插件都会遵循下述的Bucket设计准则。

如上所述，Turms目前包括三类存储资源，分别是`User Profile Picture`（用户资料图片）、`Group Profile Picture`（群组资料图片）与`Message Attachment`（消息附件），它们各自所对应的Bucket名分别为`user-profile-picture`、`group-profile-picture`与`message-attachment`。其中：

* `user-profile-picture`与`group-profile-picture`为公开Buckets。对于这些资源的URL，Turms既支持生成规律的URL，以支持客户端自行预测资源URL，避免向Turms服务端发送查询资源URL的请求，也支持生成不规律的URL，以用于反爬虫。具体您的应用需要使用哪种URL，则要根据您产品自身的需求决定。
* `message-attachment`为私有Bucket，通过Presigned URL为授权的用户提供临时访问消息附件用的URL。
* 所有资源的上传流程都是基于通过Presigned URL为授权的用户提供临时的Multipart Upload接口实现的。

当然，以上只是默认配置，当前主流对象存储服务都支持许多实用特性，如数据冷热分离存储（如Amazon S3 Intelligent-Tiering Storage Class）、加密、复杂的权限控制等等，用户可以在Turms创建的Buckets基础上，再自行通过对象存储服务做进行进一步的配置。

### turms-plugin-minio

#### 简介

turms-plugin-minio是一个基于开源对象存储服务[MinIO](https://min.io)而开发的turms-service存储服务实现插件。

#### 安装

* [MinIO服务端的下载与安装](https://min.io/download)
* [插件的加载方式](https://turms-im.github.io/docs/zh-CN/server/development/plugin#%E6%8F%92%E4%BB%B6%E5%8A%A0%E8%BD%BD%E6%96%B9%E5%BC%8F)

当插件在服务端`Start`之后，客户端即可调用`turmsClient.storageService`下对应的API，对存储资源进行增删改查操作。

#### 客户端调用存储相关接口时的注意事项

由于Turms客户端的存储接口采用的是通用接口设计，并不是为turms-plugin-minio定制的，因此在调用客户端API时，需要注意以下事项：

* 当调用`queryMessageAttachment`接口时，参数`fetchDownloadInfo`必须为`true`；当调用`queryMessageAttachmentDownloadInfo`接口时，参数`fetch`必须为`true`。

#### 业务功能

##### 消息附件功能

###### 上传消息附件

| 功能                           | 支持 |
| ------------------------------ | ---- |
| 不指定任何会话，上传消息附件   | TODO |
| 上传消息附件给指定单个私聊会话 | ✔    |
| 上传消息附件给指定多个私聊会话 |      |
| 上传消息附件给指定单个群聊会话 | ✔    |
| 上传消息附件给指定多个群聊会话 |      |

###### 删除消息附件

| 功能                     | 支持 |
| ------------------------ | ---- |
| 删除任意会话中的消息附件 | TODO |

###### 分享与取消分享

| 功能                                                   | 支持 |
| ------------------------------------------------------ | ---- |
| 分享已上传的消息附件给单个私聊会话                     | ✔    |
| 分享已上传的消息附件给多个私聊会话                     |      |
| 分享已上传的消息附件给单个群聊会话                     | ✔    |
| 分享已上传的消息附件给多个群聊会话                     |      |
| 取消与单个私聊会话的分享已上传的消息附件给单个私聊会话 | TODO |
| 取消分享已上传的消息附件给多个私聊会话                 |      |
| 分享已上传的消息附件给单个群聊会话                     | TODO |
| 分享已上传的消息附件给多个群聊会话                     |      |

对于更高级的分享功能，诸如细致的权限控制、自定义分享时长、加密分享等功能，近期暂无计划支持。

##### 查询

| 功能                                                         | 支持                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| 指定单个私聊会话中，对方分享给我的附件                       | ✔                                                            |
| 指定单个私聊会话中，我发送给对方的附件                       | ✔                                                            |
| 指定单个私聊会话中，对方分享给我的附件与我发送给对方的附件   | ✔                                                            |
| 指定多个私聊会话中，对方分享给我的附件                       |                                                              |
| 指定多个私聊会话中，我发送给对方的附件                       |                                                              |
| 指定多个私聊会话中，对方分享给我的附件与我发送给对方的附件   |                                                              |
| 所有私聊会话中，对方分享给我的附件                           |                                                              |
| 所有私聊会话中，我发送给对方的附件                           | 不支持“只查询私聊会话中，我发送给对方的附件”，<br />但支持“在所有会话中，我分享的附件” |
| 所有私聊会话中，对方分享给我的附件与我发送给对方的附件       |                                                              |
|                                                              |                                                              |
| 指定单个群聊会话中，指定单个用户（可以是我自己）分享的附件   | ✔                                                            |
| 指定单个群聊会话中，指定多个用户（可以包括我自己）分享的附件 | ✔                                                            |
| 指定单个群聊会话中，所有用户分享（包括我自己）的附件         | ✔                                                            |
| 指定多个群聊会话中，指定单个用户（可以是我自己）分享的附件   |                                                              |
| 指定多个群聊会话中，指定多个用户（可以包括我自己）分享的附件 |                                                              |
| 指定多个群聊会话中，所有用户分享（包括我自己）的附件         |                                                              |
| 所有群聊会话中，指定单个用户分享的附件                       | 不支持“所有群聊会话中，指定我分享的附件”，<br />但支持“在所有会话中，我分享的附件” |
| 所有群聊会话中，指定多个用户（可以包括我自己）分享的附件     |                                                              |
| 所有群聊会话中，所有用户分享（包括我自己）的附件             |                                                              |
|                                                              |                                                              |
| 在所有会话中，我分享的附件                                   | ✔                                                            |
| 在所有会话中，其他各种查询对象                               |                                                              |

#### 权限控制

* 查看消息附件

  * 发送消息附件的用户无论有没有退出私聊或群聊会话，他们始终都有权限查询自己上传的消息附件。

    并且即使上传消息附件的用户退出该会话，该会话中的其他所有用户仍有权限查看该用户上传的消息附件。

  * 用户有且仅能查看在已加入的私聊或群聊会话中其他用户分享的消息附件。换言之，如果一位用户先加入了一个会话，而后又退出，则退出后的用户无法查看该会话中的附件。只有当该用户又再次加入该会话，才又有权限查看该会话中的附件。

#### 安全

上传限制：TODO

#### 存储文件数据校验

如果基于云服务来实现存储文件的数据校验，那逻辑的实现会相对简单。如在AWS上，可以通过S3的事件通知来触发自定义的Lambda函数对用户上传的数据做检验，又或者通过在CloudFront侧添加监听`origin-response `事件的Lambda@Edge函数做校验，除了自定义的校验逻辑需要写一些代码外，其他功能基本靠点鼠标就能实现了。

但由于MinIO作为独立的存储服务不支持诸如Lambda函数这样的Serverless架构特性，因此相对于Serverless的方案，基于MinIO的事件机制来实现低成本又高可用的数据校验逻辑就麻烦得多了。因此Turms暂不支持对存储文件做数据校验。之后会提供支持。

#### 配置

| 配置项                                           | 默认值                     | 说明                                                         |
| ------------------------------------------------ | -------------------------- | ------------------------------------------------------------ |
| turms-plugin.minio.enabled                       | true                       | 是否启动插件                                                 |
| turms-plugin.minio.endpoint                      | "http://localhost:9000"    | MinIO服务端的地址                                            |
| turms-plugin.minio.region                        | ""                         | MinIO服务端的区域                                            |
| turms-plugin.minio.access-key                    | minioadmin                 | MinIO服务端的Access Key                                      |
| turms-plugin.minio.secret-key                    | minioadmin                 | MinIO服务端的Secret Key                                      |
| turms-plugin.minio.retry.enabled                 | true                       | 初始化Buckets失败时，是否重试                                |
| turms-plugin.minio.retry.initial-interval-millis | 30_000                     | 初始化Buckets失败时，首次重试间隔                            |
| turms-plugin.minio.retry.interval-millis         | 30_000                     | 初始化Buckets失败时，重试间隔                                |
| turms-plugin.minio.retry.max-attempts            | 3                          | 初始化Buckets失败时，最多重试次数                            |
| turms-plugin.minio.resource-id.mac.enabled       | false                      | 是否对资源的Object Key进行MAC算法加密，以生成不可预测的URL来反爬虫。<br />如果不开启该项，则用户可以通过用户ID或群组ID来获得对应的图片URL<br />最终资源URL为：`<bucket>/<base62(object key)><base62(mac(object key))>`。如`user-profile-picture/123456789` => `user-profile-picture/8M0kX1aEllpuvXRV09grkIEtD4R`<br />注意：如果开启MAC算法，则客户端在调用`queryXXXDownloadInfo`系列接口时，要将参数`fetch`设置为`true`；在调用`queryXXX`系列接口时，要将参数`fetchDownloadInfo`设置为`true` |
| turms-plugin.minio.resource-id.mac.base64-key    | "AHR1cm1zLWltL3R1cm1zgA==" | Base64编码的MAC算法密钥                                      |
| turms-plugin.minio.resource-id.base62.enabled    | false                      | 是否对资源的Object Key进行Base62算法编码，以缩短URL的长度。<br />最终资源URL为：`<bucket>/<base62(object key)>`，或`<bucket>/<base62(object key)><base62(mac(object key))>`。如`user-profile-picture/123456789` => `message-attachment/8M0kX`或`user-profile-picture/8M0kX1aEllpuvXRV09grkIEtD4R`<br />注意：1. 当`turms-plugin.minio.resource-key.mac.enabled`为`true`时，Base62算法会始终被应用。<br />2. 如果开启Base62算法，则客户端在调用`queryXXXDownloadInfo`系列接口时，要将参数`fetch`设置为`true`；在调用`queryXXX`系列接口时，要将参数`fetchDownloadInfo`设置为`true` |
| turms-plugin.minio.resource-id.base62.charset    | ...                        | Base62算法的字符集                                           |