# 管理员API接口

Turms服务端的接口文档采用[OpenAPI 3.0](https://swagger.io/specification)标准，并通过HTTP服务对外提供当前服务端的OpenAPI接口文档。

如果您需要查阅API接口文档，您可以在启动Turms服务端后，访问 http://localhost:端口号/openapi/ui 查阅API接口。如果您需要API接口的JSON格式数据，则可访问 http://localhost:端口号/openapi/docs 获取。其中，turms-gateway管理员HTTP服务端的默认端口号是9510，而turms-service则使用8510端口。

注意：在将Turms服务端部署到生产环境时，通常不需要将Turms服务端的Admin API端口开放给公网，以避免不必要的攻击。

## 接口设计准则

为了让接口能够顾名思义，保证开发者能一目了然，Turms的Admin API接口设计参考[RESTful](https://en.wikipedia.org/wiki/Representational_state_transfer)设计风格，并做了进一步优化与统一，具体遵循以下准则：

* URL的路径部分代表目标资源，如`/users/relationships`；或是资源的表现形式，如`/users/relationships/page`表示以分页的形式返回资源。一个URI有且仅可能返回一种格式的Response。

* POST方法用于Create资源，DELETE方法用于Delete资源，PUT方法用于Update资源，GET方法用于Query资源，以及比较特殊的HEAD方法用于Check资源（类似于GET，但无Response body，仅通过HTTP状态码交互）

* 请求的Query string用于定位资源，如`?ids=1,2,3`；或是附加指令，如`?reset=true`

  注意：与RESTful风格不同，Turms服务端不使用请求URL路径（Path）做资源定位。如`GET /flight-recordings/jfr`下载JFR文件接口，在RESTful风格中应该是`GET /flight-recordings/jfr/{id}`，但在Turms服务端中是`GET /flight-recordings/jfr?id={id}`

* 请求的Body用于描述要创建或更新的数据

## 使用管理接口的对象

* 您的前端管理系统或后端服务端发出HTTP(S)请求进行调用

* 管理员后台管理Web项目的[turms-admin](https://github.com/turms-im/turms/tree/develop/turms-admin)使用

注意：管理接口不是给终端用户使用的，而是您团队内部进行调用的。因此通常情况下，您不需要给turms-service服务端开放外网IP与端口。

## 类别

### 非业务相关类

#### 监控类

| **种类**     | **Controller**            | 路径               | 支持该接口的服务 |
| :----------- | :------------------------ | ------------------ | ---------------- |
| 度量信息管理 | MetricsController         | /metrics           | 均支持           |
| 飞行记录管理 | FlightRecordingController | /flight-recordings | 均支持           |

#### 插件类

| **种类** | **Controller**   | 路径     | 支持该接口的服务 |
| :------- | :--------------- | -------- | ---------------- |
| 插件管理 | PluginController | /plugins | 均支持           |

#### 管理员类

| **种类**       | **Controller**      | 路径          | 通用 | **补充**                                                     |
| :------------- | :------------------ | ------------- | ---- | ------------------------------------------------------------ |
| 管理员管理     | AdminController     | /admins       |      | 每个Turms集群默认存在一个角色为`ROOT`，账号名与密码均为`turms`的账号 |
| 管理员角色管理 | AdminRoleController | /admins/roles |      | 每个Turms集群默认存在一个角色为`ROOT`的超级管理员角色，其具有所有权限 |

#### 集群类

| **种类**     | **Controller**   | 路径             | 支持该接口的服务 |
| :----------- | :--------------- | ---------------- | ---------------- |
| 集群节点管理 | MemberController | /cluster/members | turms-service |
| 集群配置管理 | SettingController | /cluster/settings  | turms-service |

#### 黑名单类

| **种类**       | **Controller**          | 路径                   | 支持该接口的服务 |
| :------------- | :---------------------- | ---------------------- | ---------------- |
| IP黑名单管理   | IpBlocklistController   | /blocked-clients/ips   | turms-service    |
| 用户黑名单管理 | UserBlocklistController | /blocked-clients/users | turms-service    |

#### 用户会话类

| **种类**     | **Controller**    | 路径      | 支持该接口的服务 |
| :----------- | :---------------- | --------- | ---------------- |
| 用户会话管理 | SessionController | /sessions | turms-gateway    |

### 业务相关类

下表所有API端口仅存在于turms-service服务端，turms-gateway服务端没有这些API端口。

#### 用户类

| **职责**         | **Controller**                  | 路径                                 |
| :--------------- | :------------------------------ | ------------------------------------ |
| 用户信息管理     | UserController                  | /users                               |
| 用户在线状态管理 | UserOnlineInfoController        | /users/online-infos                  |
| 用户权限组管理   | UserPermissionGroupController   | /users/permission-groups             |
| 用户关系管理     | UserRelationshipController      | /users/relationships                 |
| 用户关系组管理   | UserRelationshipGroupController | /users/relationships/groups          |
| 用户好友请求管理 | UserFriendRequestController     | /users/relationships/friend-requests |

#### 群组类

| 职责             | Controller                 | 路径                  |
| ---------------- | -------------------------- | --------------------- |
| 群组管理         | GroupController            | /groups               |
| 群组类型管理     | GroupTypeController        | /groups/types         |
| 群组入群问题管理 | GroupQuestionController    | /groups/questions     |
| 群组成员管理     | GroupMemberController      | /groups/members       |
| 群组黑名单管理   | GroupBlocklistController   | /groups/blocked-users |
| 群组邀请管理     | GroupInvitationController  | /groups/invitations   |
| 群组入群请求管理 | GroupJoinRequestController | /groups/join-requests |

#### 聊天会话类

| 职责         | Controller             | 路径           |
| ------------ | ---------------------- | -------------- |
| 聊天会话管理 | ConversationController | /conversations |

#### 消息类

| 职责     | Controller        | 路径      |
| -------- | ----------------- | --------- |
| 消息管理 | MessageController | /messages |

## 统计

当前对外暴露的统计相关接口多为Legacy API，不推荐使用。我们会在之后对其进行调整与重构。具体原因请查阅[数据分析](https://turms-im.github.io/docs/zh-CN/server/module/data-analytics)章节。

## 管理员API接口安全

用户向Turms服务端发送的每个HTTP请求都会经过Turms服务端的认证与授权流程，具体内容可见[管理员安全](https://turms-im.github.io/docs/zh-CN/server/module/security#%E7%AE%A1%E7%90%86%E5%91%98%E5%AE%89%E5%85%A8)。