# 用户相关功能

## 相关路径与模型

* 管理员API路径：`/users`。具体API细节请参考OpenAPI文档
* 客户端接口：请查阅`UserServiceController`类
* 底层请求模型：请查阅`https://github.com/turms-im/proto/tree/master/request/user`目录下的接口描述文件
* 配置类：`im.turms.server.common.infra.property.env.service.business.user.UserProperties`

## 用户信息功能

| **功能**             | **功能描述**                                                 | **相关配置**                                                 |
| :------------------- | :----------------------------------------------------------- | ------------------------------------------------------------ |
| 新增用户             |                                                              | turms.service.user.activate-user-when-added                  |
| 删除用户             |                                                              | turms.service.user.delete-user-logically                     |
| 修改用户资料         | 用户修改自己的昵称、介绍、头像URL                            |                                                              |
| 获取用户资料         | 用户查看自己或其他用户的资料                                 |                                                              |
| 设置用户资料访问权限 | 用户可以针对个人的每项资料设置访问权限。访问权限有：所有人可见、好友可见、仅自己可见 |                                                              |
| 用户权限组           | 管理员可以针对不同的用户给予不同的权限                       | 配置模型：im.turms.service.domain.user.po.UserPermissionGroup |

## 用户关系托管

概念：

- 关系：关系分为单向关系与双向关系。单向关系指的是：关系的Owner（关系拥有者）对Related User（关系人）具有某种具体的关系，如“单向好友”（允许对方发消息、好友请求过来）或是“拉黑用户”（禁止对方发消息、好友请求过来等）。单向关系的建立不需要进行权限认证。双向关系指的是：用户A对用户B有一个单向关系，用户B对用户A也有一个单向关系。如用户A屏蔽了用户B，而用户B可以指明不屏蔽用户A。
- 关系人（Related Users）：指的是具有单向或双向关系（指明对方为好友或拉黑用户）的用户。如果两名用户不具有任意一种关系，则其为Strangers。
- 关系人分组：关系人分组由分组名与一组关系人组成，每个关系必然存在于至少一个关系人分组当中。如果客户端在创建关系时，未对该关系进行分组操作，则该关系会被放进该用户的默认关系组当中。因此要特别注意的就是：在“一个关系人分组”里可以同时有“好友”与“被拉黑”的用户。当然您可以通过业务限制，限制一个分组里只能有某一类的关系人。

额外补充：实际上，在Turms领域模型中并没有“好友/拉黑用户”这样的概念，其实质是一个叫“isBlocked”的bool。

| **<div style="min-width:100px">功能</div>** | **功能描述**                                                 | **相关配置**                                                 |
| :------------------------------------------ | :----------------------------------------------------------- | ------------------------------------------------------------ |
| 获取关系                                    | 根据可选的过滤（如指定用户ID、“是否是联系人”、“是否是好友/拉黑用户”等）与分组条件，获取当前用户所拥有的关系 |                                                              |
| 添加关系人(+发起好友请求)                   | ①若是添加关系为“好友”的关系人，则根据您自定义的Turms服务端配置，用户既可直接添加"好友"关系，也可以先发起好友请求，待获得被请求人批准后，才自动执行添加“好友”关系操作。<br />②若是添加关系为“拉黑用户”的关系人，则无需批准，直接生效。用户将不再收到拉黑用户发来的任何消息或者请求。 | turms.service.user.friend-request.content-limit<br />turms.service.user.friend-request.delete-expired-requests-when-cron-triggered<br />turms.service.user.friend-request.allow-send-request-after-declined-or-ignored-or-expired<br />turms.service.user.friend-request.friend-request-expire-after-seconds<br />turms.service.user.friend-request.expired-user-friend-requests-cleanup-cron<br />turms.service.user.friend-request.delete-expired-requests-when-cron-triggered |
| 通过/拒绝好友请求                           | 用户可以通过或者拒绝好友请求。若同意好友请求，则二者将建立双向的“好友”关系 |                                                              |
| 删除关系人                                  | 根据可选删除条件（如“是/不是关系人”、“是好友/拉黑用户”），删除某类关系人或指定关系人。 | deleteTwoSidedRelationships                                  |
| 修改与关系人的关系                          | 修改用户关系（好友/拉黑用户）信息。在修改关系为“好友”时，默认需要先发送好友请求（您可以取消此步骤） |                                                              |
| 创建关系人分组                              | 创建分组时，可以同时指定分组名与被添加的关系人。同一关系人可以被添加到多个分组 |                                                              |
| 删除关系人分组                              | 删除关系人分组，同时可以可选是否转移被删除关系人分组中的关系人到其他分组（若不指定，则默认分配到“默认分组”） |                                                              |
| 重命名关系人分组                            | 重命名关系人分组                                             |                                                              |
| 获取用户自己的关系人分组信息                | 获取用户自己的关系人分组信息                                 |                                                              |
| 添加关系人到某分组                          | 将关系人添加到/移到关系人分组。若分组不存在，则操作失败      |                                                              |
| 从某分组中删除关系人                        | 将关系人从关系人分组中删除                                   |                                                              |

## 定位功能
配置类：`im.turms.server.common.infra.property.env.common.location.LocationProperties`

| **功能**     | **功能描述**                       | **相关配置**                                                 |
| ------------ | ---------------------------------- | ------------------------------------------------------------ |
| 用户位置记录 | 定期记录用户所在位置               | turms.location.enabled<br />turms.location.treat-user-id-and-device-type-as-unique-user |
| 附近的人     | 根据当前实时坐标搜寻附近的其他用户 | turms.location.users-nearby-request.default-max-available-nearby-users-number<br />turms.location.users-nearby-request.default-max-distance-meters<br />turms.location.users-nearby-request.max-available-users-nearby-number-limit<br />turms.location.users-nearby-request.max-distance-meters |

## 统计功能

配置类：`im.turms.server.common.infra.property.env.service.env.StatisticsProperties`

尽管Turms提供一些基础的统计功能，但推荐用户通过云服务采集各种统计数据，如Amazon CloudWatch。

| **功能**       | **功能描述**                                                 | **相关配置**                                                 |
| -------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| 在线用户数统计 | Turms集群中的Master节点会定期将集群中的在线用户数以日志形式进行记录 | turms.service.statistics.log-online-users-number<br />turms.service.statistics.online-users-number-logging-cron |