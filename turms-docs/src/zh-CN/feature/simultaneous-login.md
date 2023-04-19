# 多端登录

## 登陆设备识别策略

1. 手动配置。开发者在调用客户端的`turmsClient.userService.login()`接口时，可以手动配置具体的登录设备类型。如给实际为iOS的设备，指定以`Android`的身份进行登录（这种操作是被允许的）。另外，您也能手动配置登录设备类型为`UNKNOWN`。
2. 默认配置。如果您未手动配置具体登录设备类型，则在默认情况下，各Turms客户端会自动识别当前运行环境，并指定当前登录设备类型。
   * turms-client-js 会判断当前运行环境是浏览器还是Node.js，如果是浏览器，则以`Browser`身份登录，否则以`Desktop`身份登录
   * turms-client-kotlin 会判断当前的运行环境是Android还是桌面系统，分别以`Android`、`Desktop`的身份登录
   * turms-client-swift 则默认以`iOS`身份进行登录

相关配置类：`im.turms.server.common.infra.property.env.gateway.SessionProperties`

## 多端登录类型

“设备类型”指的是：Android、iOS、Desktop、Browser、Others、Unknown。
以下是常见的多端登陆类型搭配，供您快速挑选实现。

配置属性：`im.turms.server.common.infra.property.env.gateway.SimultaneousLoginProperties#strategy`

其他相关配置：`allowDeviceTypeUnknownLogin`、`allowDeviceTypeOthersLogin`

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
- 当用户登陆设备的类型有`Unkown`或`Others`情况时，需进行额外配置，即是否允许`Unkown`/`Others`设备与其他已知设备同时登陆

## 多端登陆冲突解决策略

配置属性：`im.turms.server.common.infra.property.env.gateway.SimultaneousLoginProperties#loginConflictStrategy`

| **类型**           | 对应Enum值                   |
| :----------------- | ---------------------------- |
| 下线已上线设备     | DISCONNECT_LOGGED_IN_DEVICES |
| 拒绝预上线设备上线 | DISCONNECT_LOGGING_IN_DEVICE |