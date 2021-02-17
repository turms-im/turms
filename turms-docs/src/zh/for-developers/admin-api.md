# 管理员API接口

Turms的API文档基于[Springdoc](https://github.com/springdoc/springdoc-openapi) 依赖实现，并采用OpenAPI 3.0标准。

Turms在生产环境下默认关闭OpenAPI的UI与API接口（dev profile下该接口默认开启），因此如果您需要查阅API接口文档，则您需要将dist/config/application.yaml配置文件下的springdoc.*.enabled=false属性删除。在启动turms服务端后，访问 http://localhost:8510/swagger-ui.html 即可查阅API接口。如果您需要OpenAPI标准下API接口的JSON数据，可访问 http://localhost:8510/v3/api-docs 获取。

## 接口设计准则

为了让接口顾名思义，保证开发者能一目了然，turms的管理接口设计在参考[RESTful](https://en.wikipedia.org/wiki/Representational_state_transfer)设计上做了进一步优化与统一，具体遵循以下准则：

* URL的路径部分代表目标资源（如“/users/relationships”），或是资源的表现形式（如“/users/relationships/page”表示以分页的形式返回资源）
* POST方法用于Create资源，DELETE方法用于Delete资源，PUT方法用于Update资源，GET方法用于Query资源，以及比较特殊的HEAD方法用于Check资源（类似于GET，但无Response body，仅通过HTTP状态码交互）
* 请求的Query string用于定位资源或是附加指令。如：“?ids=1,2,3”（定位资源）或“?reset=true”（附加指令）
* 请求的Body用于描述要创建或更新的数据

## 使用管理接口的对象

* 您的前端管理系统或后端服务端发出HTTP(S)请求进行调用

* 作为内容统计管理系统与集群监控管理系统的[turms-admin](https://github.com/turms-im/turms/tree/develop/turms-admin)使用

注意：管理接口不是给终端用户使用的，而是您团队内部进行调用的。因此您通常不需要给Turms服务端开放外网IP与端口。

## 类别

### 非业务相关类

| **种类**       | **Controller**      | 路径             | **补充**                                                     |
| :------------- | :------------------ | ---------------- | ------------------------------------------------------------ |
| 管理员管理     | AdminController     | /admins          | 每个Turms集群默认存在一个角色为“ROOT”，账号与密码均为“turms” |
| 管理员角色管理 | AdminRoleController | /admins/roles    | 每个Turms集群默认存在一个角色为“ROOT”的超级管理员角色，其具有所有权限 |
| 集群配置管理   | ConfigController    | /cluster/config  |                                                              |
| 集群节点管理   | MemberController    | /cluster/members |                                                              |

## 业务相关类

### 用户类

| **职责**         | **Controller**                  | 路径                                 |
| :--------------- | :------------------------------ | ------------------------------------ |
| 用户信息管理     | UserController                  | /users                               |
| 用户在线状态管理 | UserOnlineInfoController        | /users/online-infos                  |
| 用户权限组管理   | UserPermissionGroupController   | /users/permission-groups             |
| 用户关系管理     | UserRelationshipController      | /users/relationships                 |
| 用户关系组管理   | UserRelationshipGroupController | /users/relationships/groups          |
| 用户好友请求管理 | UserFriendRequestController     | /users/relationships/friend-requests |
### 群组类

| 职责             | Controller                 | 路径                      |
| ---------------- | -------------------------- | ------------------------- |
| 群组管理         | GroupController            | /groups                   |
| 群组类型管理     | GroupTypeController        | /groups/types             |
| 群组入群问题管理 | GroupQuestionController    | /groups/questions         |
| 群组成员管理     | GroupMemberController      | /groups/members           |
| 群组黑名单管理   | GroupBlacklistController   | /groups/blacklisted-users |
| 群组邀请管理     | GroupInvitationController  | /groups/invitations       |
| 群组入群请求管理 | GroupJoinRequestController | /groups/join-requests     |

### 消息类

| 职责         | Controller              | 路径               |
| ------------ | ----------------------- | ------------------ |
| 消息管理     | MessageController       | /messages          |
| 消息状态管理 | MessageStatusController | /messages/statuses |

## 统计

当前对外暴露的统计相关接口多为Legacy API，不推荐使用。具体原因请查阅[数据分析](https://turms-im.github.io/docs/for-developers/data-analytics.html)章节。
