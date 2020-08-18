# 多端登录

## 登陆设备识别策略

1. 如果您在调用客户端的turmsClient.userService.login()接口时，手动配置了具体的登录设备类型，则Turms服务端识别到的最终设备类型以您的配置为准。因此您甚至可以对于iOS设备，指定以Android的身份进行登录，这种操作是被允许的。
2. 如果您未手动配置具体登录设备类型，则在默认情况下，各Turms客户端会自动识别当前运行环境，并指定当前登录设备类型。
   * turms-client-js 会判断当前运行环境是浏览器还是Node.js，如果是浏览器，则以Browser身份登录，否则以Desktop身份登录
   * turms-client-java 会判断当前的运行环境是Android还是桌面系统，分别以Android、Desktop的身份登录
   * turms-client-swift 则默认以iOS身份进行登录
4. 如果您在调用客户端turmsClient.userService.login()接口时，指定登录类型为UNKNOWN，则最终登录设备类型将由turms-gateway服务端自行根据WebSocket在握手阶段的HTTP请求头“User-Agent”来判断当前登陆用户的设备类型。如果turms-gateway无法判断登录设备类型，则保持登陆类型为UNKNOWN。

相关配置类：im.turms.server.common.property.env.gateway.SessionProperties

## 多端登录类型

“设备类型”指的是：Android、iOS、Desktop、Browser、Others、Unknown。
以下是常见的多端登陆类型搭配，供您快速挑选实现。

配置属性：im.turms.server.common.property.env.gateway.SimultaneousLoginProperties#strategy。其他相关配置：allowDeviceTypeUnknownLogin、allowDeviceTypeOthersLogin

| **自带类型**                                                 | 对应Enum值                                                   |
| :----------------------------------------------------------- | ------------------------------------------------------------ |
| 允许每种设备类型的一个设备都能同时在线                       | ALLOW_ONE_DEVICE_OF_EACH_DEVICE_TYPE_ONLINE                  |
| 仅允许一个设备类型的一个设备同时在线                         | ALLOW_ONE_DEVICE_FOR_ALL_DEVICE_TYPES_ONLINE                 |
| 允许Desktop端的一个设备与手机端的一个设备同时在线            | ALLOW_ONE_DEVICE_OF_DESKTOP_AND_ONE_DEVICE_OF_MOBILE_ONLINE  |
| 允许Desktop端或者Browser端的一个设备，与手机端的一个设备同时在线 | ALLOW_ONE_DEVICE_OF_DESKTOP_OR_BROWSER_AND_ONE_DEVICE_OF_MOBILE_ONLINE |
| 允许Desktop端的一个设备，与Browser端的一个设备，与手机端的一个设备同时在线 | ALLOW_ONE_DEVICE_OF_DESKTOP_AND_ONE_DEVICE_OF_BROWSER_AND_ONE_DEVICE_OF_MOBILE_ONLINE |
| 允许Desktop端或手机端的一个设备同时在线                      | ALLOW_ONE_DEVICE_OF_DESKTOP_OR_MOBILE_ONLINE                 |
| 允许Desktop端或手机端或Browser端的一个设备同时在线           | ALLOW_ONE_DEVICE_OF_DESKTOP_OR_BROWSER_OR_MOBILE_ONLINE      |

**注意**

- 任何多端登陆类型都不允许一个用户账号在一种设备上有多个同时登陆的设备
- 当用户登陆设备的类型有Unkown或Others情况时，需进行额外配置：允许Unkown/Others设备与其他已知设备同时登陆、不允许Unkown/Others设备与其他已知设备同时登陆

## 多端登陆冲突解决策略

配置属性：im.turms.server.common.property.env.gateway.SimultaneousLoginProperties#loginConflictStrategy

| **类型**           | 对应Enum值                   |
| :----------------- | ---------------------------- |
| 下线已上线设备     | DISCONNECT_LOGGED_IN_DEVICES |
| 拒绝预上线设备上线 | DISCONNECT_LOGGING_IN_DEVICE |
