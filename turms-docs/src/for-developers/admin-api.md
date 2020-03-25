### 管理接口

具体API接口文档，请查阅[Turms的Swagger文档](https://github.com/turms-im/turms/blob/develop/turms/docs/html/swagger.html)，或在dist/config/application.yaml配置文件下，添加“spring.profiles.active=dev”属性，并在服务端重启后，访问http://localhost:9510/swagger-ui.html

#### 接口设计准则

为了让接口顾名思义，保证开发者能一目了然，turms的管理接口设计在参考RESTful设计上做了进一步优化与统一，具体遵循以下准则：

* URL的路径部分代表目标资源（如“/users/relationships”），或是资源的表现形式（如“/users/relationships/page”表示以分页的形式返回资源）
* POST方法用于Create资源，DELETE方法用于Delete资源，PUT方法用于Update资源，GET方法用于Query资源，以及比较特殊的HEAD方法用于Check资源（类似于GET，但无Response body，仅通过HTTP状态码交互）
* 请求的Query string用于定位资源或是附加指令。如：“?ids=1,2,3”（定位资源）或“?shouldReset=true”（附加指令）
* 请求的Body用于描述要创建或更新的数据

#### 管理接口使用对象

①您的前端或后端程序发出HTTP(S)请求进行调用；

②作为内容统计管理系统与集群监控管理系统的[turms-admin](https://github.com/turms-im/turms/tree/develop/turms-admin)使用。

### 类别

#### 非业务相关类

| **种类**           | **Controller**           | **补充**                                                     |
| :----------------- | :----------------------- | ------------------------------------------------------------ |
| 管理员管理         | AdminController          | 每个Turms集群默认存在一个角色为“ROOT”，userId为“turms”，且password随机生成的超级管理员账号 |
| 管理员操作日志管理 | AdminActionLogController |                                                              |
| 管理员权限管理     | AdminRoleController      | 每个Turms集群默认存在一个角色为“ROOT”的超级管理员角色，其具有所有权限 |
| 集群管理           | ClusterController        |                                                              |
| 原因管理           | ReasonController         | （对用户开放）服务降级。主要用于当浏览器客户端登陆/连接失败时，其可通过该Controller所申明的接口查询失败原因 |
| 路由管理           | RouterController         | （对用户开放）服务降级。当浏览器客户端因试图连接不对其负责的Turms服务端而登录失败时，其可通过该Controller所申明的接口来查询应对该用户负责的其他Turms服务端IP |

#### 业务相关类

| **种类** | **Controller**                                               |
| :------- | :----------------------------------------------------------- |
| 用户管理 | UserController<br />UserOnlineInfoController<br />UserRelationshipController<br />UserRelationshipGroupController<br />UserFriendRequestController |
| 群组管理 | GroupController<br /><br />GroupTypeController<br /><br />GroupQuestionController<br />GroupMemberController<br /><br />GroupBlacklistController<br /><br />GroupInvitationController<br />GroupJoinRequestController |
| 消息管理 | MessageController<br />MessageStatusController               |