# 群组相关功能

群成员类型包括：群主、管理员、普通成员、游客、匿名游客

## 相关路径与模型

* 管理员API路径：`/groups`。具体API细节请参考OpenAPI文档
* 客户端接口：请查阅`GroupServiceController`类。
* 底层请求模型：请查阅`https://github.com/turms-im/proto/tree/master/request/group`目录下的接口描述文件
* 配置类：`im.turms.server.common.infra.property.env.service.business.group.GroupProperties`

## 功能列表

| **<div style="min-width:100px">功能</div>** | **描述**                                                     | **相关配置属性名**                                           |
| :------------------------------------------ | :----------------------------------------------------------- | ------------------------------------------------------------ |
| 新建群组                                    | 新建群组                                                     | turms.service.group.activate-group-when-created              |
| 群主解散群                                  | 群主可以解散群                                               | turms.service.group.delete-group-logically-by-default        |
| 主动退群                                    | 除群主外，其他用户均可以主动退群。群主需先将群转让给其他群成员才可以进行退群操作 |                                                              |
| 群主转让群                                  | 群主可以将群的拥有者权限转给群内的其他成员，转移后， 被转让者变为新的群主，原群主变为普通成员。群主还可以选择在转让的同时，直接退出该群 |                                                              |
| 修改群组资料                                | 支持群组名，群组头像，群组介绍，群组通知，群组类型等字段     |                                                              |
| 群组禁言                                    | 群组普通成员在禁言时段无法发送消息，仅有群主与管理员能发送消息 |                                                              |
| 获取群组信息                                | 根据过滤条件（如群组ID），查找群组                           |                                                              |
| 增加群组成员                                | 增加群组成员                                                 |                                                              |
| 发送入群邀请                                | 拥有邀请权限角色的群组成员可向指定用户发送入群邀请           | turms.service.group.invitation.content-limit<br />turms.service.group.invitation.expire-after-seconds<br />turms.service.group.invitation.expired-invitations-cleanup-cron<br />turms.service.group.invitation.delete-expired-invitations-when-cron-triggered<br /> |
| 撤销入群邀请                                | 群主、管理员与入群邀请发起者可撤销入群邀请                   | turms.service.group.invitation.allow-recall-pending-invitation-by-owner-and-manager |
| 发送入群请求                                |                                                              | turms.service.group.join-request.content-limit<br />turms.service.group.join-request.expire-after-seconds<br />turms.service.group.join-request.expired-join-requests-cleanup-cron<br />turms.service.group.join-request.delete-expired-join-requests-when-cron-triggered |
| 撤销入群请求                                |                                                              | turms.service.group.join-request.allow-recall-join-request-sent-by-oneself |
| 设置入群问题                                | 对于入群策略为“入群请求者回答问题正确后加入”的群组，群主与管理员可以设置入群问题。入群问题可以有多个，一个问题可以多个答案 | turms.service.group.question.answer-content-limit<br />turms.service.group.question.max-answer-count<br />turms.service.group.question.question-content-limit |
| 删除入群问题                                | 删除入群问题                                                 |                                                              |
| 移除群组成员                                | 群主和管理员可以移除群组成员，且管理员不能移除群主和其他管理员 |                                                              |
| 更新群组成员信息                            | 根据对应的“群组类型”，指定角色的群组成员可以修改其他群组成员的成员信息（如：群主为群组成员赋予管理员角色） |                                                              |
| 群组成员禁言                                | 禁言用户可以在群组内，但无法发送消息                         |                                                              |
| 群组成员坐标实时共享                        | 群组成员可以将自己的坐标实时地分享给其他群组成员             |                                                              |
| 群组黑名单                                  | 用户被拉黑后，将无法再进入群组。如果被拉黑用户在被拉黑之前是当前群组成员，则在拉黑后该用户会自动在群组成员列表中移除 |                                                              |

## 群组类型配置

在群组配置方面，Turms使用了“群组类型”这一概念。默认情况下，Turms提供了一种通用的群组类型，同时您也可以通过对“群组类型”做增删改查操作，以满足您定制化的群组类型需求。

对应的管理员API路径：`/groups/types`。具体API细节请查阅OpenAPI文档
对应的配置模型：`im.turms.service.domain.group.po.GroupType`

### 配置列表

| **属性**           | **描述**                                                     | **配置属性名**           |
| :----------------- | :----------------------------------------------------------- | ------------------------ |
| 群成员上限人数     | 有效值为1~∞                                                  | groupSizeLimit           |
| 邀请入群策略       | 支持配置：<br />①仅群主可邀请：`OWNER`、`OWNER_REQUIRING_APPROVAL`；<br />②群主+管理员可邀请：`OWNER_MANAGER`、`OWNER_MANAGER_REQUIRING_APPROVAL`；<br />③群主+管理员与群成员可邀请：`OWNER_MANAGER_MEMBER`、`OWNER_MANAGER_MEMBER_REQUIRING_APPROVAL`；<br />④所有人可邀请：`ALL`、`ALL_REQUIRING_APPROVAL` | invitationStrategy       |
| 被邀请人同意模式   | 支持配置：<br />①需要被邀请人同意：邀请者给被邀请者发送邀请。如果被邀请者同意邀请，则自动加入群：带`_REQUIRING_APPROVAL`的策略；<br />②不需要被邀请人同意：邀请者禁止给被邀请者发送邀请。邀请者可以直接把被邀请者加入群中：不带`_REQUIRING_APPROVAL`的策略 | invitationStrategy       |
| 入群策略           | 支持配置：<br />①在群主或管理员批准入群请求后，入群请求者方可加入：`JOIN_REQUEST`；<br />②入群请求者回答问题正确后，自动加入：`QUESTION`；<br />③允许未被拉黑的用户主动加入：`MEMBERSHIP_REQUEST`；<br />④不允许任何用户主动加入，需要群主或管理员发送邀请或直接拉入群中：`INVITATION` | joinStrategy             |
| 群信息更新策略     | 支持配置：<br />①仅群主可修改；<br />②群主+管理员可修改；<br />③群主+管理员+群成员可修改；<br />④所有人可修改 | groupInfoUpdateStrategy  |
| 群成员信息更新策略 | 群主可以修改所有人的在群组内的成员信息，管理员只能修改群组中普通成员的成员信息 | memberInfoUpdateStrategy |
| 游客发言           | 可禁止、可允许                                               | guestSpeakable           |
| 群成员修改自身信息 | 可禁止、可允许                                               | selfInfoUpdatable        |
| 群消息已读回执     | 可开启、可关闭                                               | enableReadReceipt        |
| 修改已发送消息     | 可开启、可关闭                                               | messageEditable          |

提醒：

* 上述的“邀请入群策略”、“被邀请人同意模式”与“入群策略”之间没有互斥关系，都是彼此兼容的，因此开发者可以根据自身的应用场景，对其进行搭配。

* 如果管理员修改了一个群组类型的邀请策略或入群策略，进而导致群组所对应的策略发生变化，那么原本对应旧策略的数据会被封存，而不会被系统删除，但原本就有权限的用户仍然可以删除、修改与查询这些数据。

  举例而言，一个群原本是基于“审批入群请求”策略让新用户入群的，并且该群已经接收到了一些入群请求，如果此时系统管理员（注意：用户是没权限修改群组类型的）将群组策略修改为“基于问答”策略让新用户入群，那么之前收到的入群请求并不会被系统删除。当群管理员试图批准这些入群请求，服务端也会告知群策略以发生变化，并拒绝批准。但是群管理员仍然可以删除、修改与查询这些入群请求。

  额外一提，可能部分用户会觉得Turms的群组策略比较复杂，但这种“复杂”跟用户没什么关系，用户只需要按照自己的应用场景做配置即可，使用起来非常简单，只是Turms的开发者实现这些动态的组合策略比较复杂。

* 咱无计划支持“用户拉黑群组，以拒绝接收入群邀请与被拉入群中”特性。

## 场景介绍

### 用户加入一个群

1. 客户端通过`turmsClient.groupService.queryGroups(...)`查询指定群的群信息。

2. 基于本地硬编码的群类型ID与群类型信息的关系，获得群类型信息。

   补充：

   * 这里不支持客户端动态查询群组类型信息是因为大部分应用的群组类型很固定，没有动态拉取信息的必要。
   * 如果您的应用本来就只使用一种群类型，那直接在客户端硬编码群类型信息就可以了，直接跳过①②两个步骤，直接进入下一个步骤。

3. 根据群类型信息中的入群策略，判断需要调用哪个客户端API加群：

   * 如果是`JOIN_REQUEST`策略，则需要调用`turmsClient.groupService.createJoinRequest(...)`来发送入群请求，并等待群管理员审批。
   * 如果是`QUESTION`策略，则需要调用`turmsClient.groupService.queryGroupJoinQuestions(...)`查询群问题，再通过`turmsClient.groupService.answerGroupQuestions(...)`来回答群问题，当分值达到群管理员设置的入群分值门槛后，即可自动加入群中。
   * 如果是`MEMBERSHIP_REQUEST`策略，则调用`turmsClient.groupService.joinGroup(...)`即可直接加入群中，不需要任何审批。
   * 如果是`INVITATION`策略，则需要等待群管理员给当前用户发送入群邀请。