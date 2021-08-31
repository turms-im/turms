# 群组相关功能

群成员类型包括：群主、管理员、普通成员、游客、匿名游客

## 相关路径与模型

* 管理员API路径：`/groups`。具体API细节请参考OpenAPI文档
* 客户端接口：请查阅`GroupServiceController`类。
* 底层请求模型：请查阅`https://github.com/turms-im/proto/tree/master/request/group`目录下的接口描述文件
* 配置类：`im.turms.server.common.property.env.service.business.GroupProperties`

## 功能列表

| **<div style="min-width:100px">功能</div>** | **描述**                                                     | **相关配置属性名**                                           |
| :------------------------------------------ | :----------------------------------------------------------- | ------------------------------------------------------------ |
| 新建群组                                    | 新建群组                                                     | activateGroupWhenCreated                                     |
| 群主解散群                                  | 群主可以解散群                                               | shouldDeleteGroupLogicallyByDefault                          |
| 主动退群                                    | 除群主外，其他用户均可以主动退群。群主需先将群转让给其他群成员才可以进行退群操作 |                                                              |
| 群主转让群                                  | 群主可以将群的拥有者权限转给群内的其他成员，转移后， 被转让者变为新的群主，原群主变为普通成员。群主还可以选择在转让的同时，直接退出该群 |                                                              |
| 修改群组资料                                | 支持群组名，群组头像，群组介绍，群组通知，群组类型等字段     |                                                              |
| 群组禁言                                    | 群组普通成员在禁言时段无法发送消息，仅有群主与管理员能发送消息 |                                                              |
| 获取群组信息                                | 根据过滤条件（如群组ID），查找群组                           |                                                              |
| 增加群组成员                                | 增加群组成员                                                 |                                                              |
| 发送入群邀请                                | 拥有邀请权限角色的群组成员可向指定用户发送入群邀请           | groupInvitationContentLimit<br />groupInvitationTimeToLiveHours<br />expiredGroupInvitationsCheckerCron<br />deleteExpiredGroupInvitationsWhenCronTriggered |
| 撤销入群邀请                                | 群主、管理员与入群邀请发起者可撤销入群邀请                   | allowRecallingPendingGroupInvitationByOwnerAndManager<br />shouldDeleteExpiredGroupInvitationsAutomatically |
| 发送入群请求                                |                                                              | groupJoinRequestContentLimit<br />groupJoinRequestTimeToLiveHours<br />expiredGroupJoinRequestsCheckerCron<br />deleteExpiredGroupJoinRequestsWhenCronTriggered |
| 撤销入群请求                                |                                                              | allowRecallingJoinRequestSentByOneself<br />shouldDeleteExpiredGroupJoinRequestsAutomatically |
| 设置入群问题                                | 对于入群策略为“入群请求者回答问题正确后加入”的群组，群主与管理员可以设置入群问题。入群问题可以有多个，一个问题可以多个答案 |                                                              |
| 删除入群问题                                | 删除入群问题                                                 |                                                              |
| 移除群组成员                                | 群主和管理员可以移除群组成员，且管理员不能移除群主和其他管理员 |                                                              |
| 更新群组成员信息                            | 根据对应的“群组类型”，指定角色的群组成员可以修改其他群组成员的成员信息（如：群主为群组成员赋予管理员角色） |                                                              |
| 群组成员禁言                                | 禁言用户可以在群组内，但无法发送消息                         |                                                              |
| 群组成员坐标实时共享                        | 群组成员可以将自己的坐标实时地分享给其他群组成员             |                                                              |
| 群组黑名单                                  | 用户被拉黑后，将无法再进入群组。如果被拉黑用户在被拉黑之前是当前群组成员，则在拉黑后该用户会自动在群组成员列表中移除 |                                                              |

## 群组类型配置

在群组配置方面，Turms使用了“群组类型”这一概念。默认情况下，Turms提供了一种通用的群组类型，同时您也可以通过对“群组类型”做增删改查操作，以满足您定制化的群组类型需求。

对应的管理员API路径：`/groups/types`。具体API细节请查阅OpenAPI文档
对应的配置模型：`im.turms.service.workflow.dao.domain.GroupType`

### 配置列表

| **属性**           | **描述**                                                     | **配置属性名**           |
| :----------------- | :----------------------------------------------------------- | ------------------------ |
| 群成员上限人数     | 有效值为1~∞                                                  | groupSizeLimit           |
| 邀请入群策略       | 支持配置：①仅群主可邀请；②群主+管理员可邀请；③群主+管理员与群成员可邀请；④所有人可邀请 | invitationStrategy       |
| 被邀请人同意模式   | 支持配置：①需要被邀请人同意；②不需要被邀请人同意             | invitationStrategy       |
| 入群策略           | 支持配置：①群主与管理员审批入群请求；②入群请求者回答问题正确后加入；③允许任何人加入；④不允许任何人加入 | joinStrategy             |
| 群信息更新策略     | 支持配置：①仅群主可修改；②群主+管理员可修改；③群主+管理员+群成员可修改；④所有人可修改 | groupInfoUpdateStrategy  |
| 群成员信息更新策略 | 群主可以修改所有人的在群组内的成员信息，管理员只能修改群组中普通成员的成员信息 | memberInfoUpdateStrategy |
| 游客发言           | 可禁止、可允许                                               | guestSpeakable           |
| 群成员修改自身信息 | 可禁止、可允许                                               | selfInfoUpdatable        |
| 群消息已读回执     | 可开启、可关闭                                               | enableReadReceipt        |
| 修改已发送消息     | 可开启、可关闭                                               | messageEditable          |
