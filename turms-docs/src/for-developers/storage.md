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

| 资源类型     | 函数名                               | 预期作用                 | 参数                                                         | 返回值                                                       |
| ------------ | ------------------------------------ | ------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| 用户资料图片 | deleteUserProfilePicture             | 删除用户资料图片         | `requesterId`请求发送者ID；<br />`resourceKeyStr`表明资源的字符串ID。该值由客户端指定，无格式要求。在Turms客户端默认实现下，其值为空；<br />`resourceKeyNum`表明资源的整型ID。该值由客户端指定，无格式要求。在Turms客户端默认实现下，其值为空。 | 空                                                           |
|              | queryUserProfilePictureUploadInfo    | 查询用户资料图片上传信息 | 同上                                                         | 返回值格式为`Map<String, String>`，插件实现者可以自定义任意返回值 |
|              | queryUserProfilePictureDownloadInfo  | 查询用户资料图片下载信息 | `requesterId`请求发送者ID；<br />`resourceKeyStr`表明资源的字符串ID。该值由客户端指定，无格式要求。在Turms客户端默认实现下，其值为空；<br />`resourceKeyNum`表明资源的整型ID。该值由客户端指定，无格式要求。在Turms客户端默认实现下，其值为客户端函数调用者提供的用户ID。 | 返回值格式为`Map<String, String>`，插件实现者可以自定义任意返回值 |
| 群组资料图片 | deleteGroupProfilePicture            | 删除群组资料图片         | `requesterId`请求发送者ID；<br />`resourceKeyStr`表明资源的字符串ID。该值由客户端指定，无格式要求。在Turms客户端默认实现下，其值为空；<br />`resourceKeyNum`表明资源的整型ID。该值由客户端指定，无格式要求。在Turms客户端默认实现下，其值为客户端函数调用者提供的群组ID。 | 空                                                           |
|              | queryGroupProfilePictureUploadInfo   | 查询群组资料图片上传信息 | 同上                                                         | 返回值格式为`Map<String, String>`，插件实现者可以自定义任意返回值 |
|              | queryGroupProfilePictureDownloadInfo | 查询群组资料图片下载信息 | 同上                                                         | 返回值格式为`Map<String, String>`，插件实现者可以自定义任意返回值 |
| 消息附件     | deleteMessageAttachment              | 删除消息附件             | `requesterId`请求发送者ID；<br />`resourceKeyStr`表明资源的字符串ID。该值由客户端指定，无格式要求。在Turms客户端默认实现下，其值为客户端函数调用者提供的消息附件名；<br />`resourceKeyNum`表明资源的整型ID。该值由客户端指定，无格式要求。在Turms客户端默认实现下，其值为客户端函数调用者提供的消息ID。 | 空                                                           |
|              | queryMessageAttachmentUploadInfo     | 查询消息附件上传信息     | 同上                                                         | 返回值格式为`Map<String, String>`，插件实现者可以自定义任意返回值 |
|              | queryMessageAttachmentDownloadInfo   | 查询消息附件下载信息     | 同上                                                         | 返回值格式为`Map<String, String>`，插件实现者可以自定义任意返回值 |

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

### turms-plugin-minio

#### 安装

* [MinIO服务端的下载与安装](https://min.io/download)
* [插件的加载方式](https://turms-im.github.io/docs/for-developers/plugin.html#%E6%8F%92%E4%BB%B6%E5%8A%A0%E8%BD%BD%E6%96%B9%E5%BC%8F)

当插件在服务端`Start`之后，客户端即可调用`turmsClient.storageService`下对应的API，对存储资源进行增删改查操作。

注意：当调用`queryMessageAttachment`接口时，参数`fetchDownloadInfo`必须为`true`；当调用`queryMessageAttachmentDownloadInfo`接口时，参数`fetch`必须为`true`。

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
| turms-plugin.minio.resource-key.mac.enabled      | false                      | 是否对资源的Object Key进行MAC算法加密，以生成不规律的URL。<br />最终资源URL为：`<bucket>/<base62(mac(object key))>`。如`message-attachment/123456789` => `message-attachment/1aEllpuvXRV09grkIEtD4R`<br />注意：如果开启MAC算法，则客户端在调用`queryXXXDownloadInfo`系列接口时，要将参数`fetch`设置为`true`；在调用`queryXXX`系列接口时，要将参数`fetchDownloadInfo`设置为`true` |
| turms-plugin.minio.resource-key.mac.base64-key   | "AHR1cm1zLWltL3R1cm1zgA==" | Base64编码的MAC算法密钥                                      |
| turms-plugin.minio.resource-key.base62.enabled   | false                      | 是否对资源的Object Key进行Base62算法编码，以缩短Object Key的URL长度。<br />最终资源URL为：`<bucket>/<base62(object key)>`，或`<bucket>/<base62(mac(object key))>`。如`message-attachment/123456789` => `message-attachment/8M0kX`或`message-attachment/1aEllpuvXRV09grkIEtD4R`<br />注意：1. 当`turms-plugin.minio.resource-key.mac.enabled`为`true`时，Base62算法会始终被应用。<br />2. 如果开启Base62算法，则客户端在调用`queryXXXDownloadInfo`系列接口时，要将参数`fetch`设置为`true`；在调用`queryXXX`系列接口时，要将参数`fetchDownloadInfo`设置为`true` |
| turms-plugin.minio.resource-key.base62.charset   | ...                        | Base62算法的字符集                                           |
