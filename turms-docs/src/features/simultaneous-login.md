### 登陆设备识别策略

1. 如果您在调用客户端login接口时，配置了具体的登录设备类型，则Turms服务端识别到的最终设备类型以您的配置为准。因此您甚至可以对于iOS设备，指定以Android的身份进行登录，这种操作是被允许的。
2. 如果您未配置具体登录设备类型，则在默认情况下，对于turms-client-js，会判断当前运行环境是浏览器还是Node.js，如果是浏览器，则以Browser身份登录，否则以Desktop身份登录。对于turms-client-java，会判断当前的运行环境是Android还是桌面系统，分别以Android、Desktop的身份登录。对于turms-client-swift，则默认以iOS身份进行登录。
3. 如果您在调用客户端login接口时，指定登录类型为UNKNOWN，则最终登录设备类型将由Turms服务端自行根据WebSocket在握手阶段的HTTP请求头“User-Agent”来判断当前登陆用户的设备类型。

相关参数：shouldUseOsAsDefaultDeviceType

### 多端登录类型

提醒：Turms允许您通过参数来自己定制哪些设备能同时登陆，哪些设备不能同时登陆。以下仅是常见的多端登陆类型搭配，供您快速挑选实现。

“设备类型”指的是：Android、iOS、Desktop、Browser、Unknown、Others

配置属性：im.turms.turms.property.business.User#simultaneousLogin
相关配置：allowUnknownDeviceCoexistsWithKnownDevice

| **自带类型**                                                 | 对应Enum值                                                   |
| :----------------------------------------------------------- | ------------------------------------------------------------ |
| 允许每种设备类型的一个设备都能同时在线                       | ALLOW_ONE_DEVICE_OF_ONE_DEVICE_TYPE_ONLINE                   |
| 仅允许一个设备类型的一个设备同时在线                         | ALLOW_ONE_DEVICE_OF_EVERY_DEVICE_TYPE_ONLINE                 |
| 允许Desktop端的一个设备与手机端的一个设备同时在线            | ALLOW_ONE_DEVICE_OF_DESKTOP_AND_ONE_DEVICE_OF_MOBILE_ONLINE  |
| 允许Desktop端或者Browser端的一个设备，与手机端的一个设备同时在线 | ALLOW_ONE_DEVICE_OF_DESKTOP_OR_WEB_AND_ONE_DEVICE_OF_MOBILE_ONLINE |
| 允许Desktop端的一个设备，与Browser端的一个设备，与手机端的一个设备同时在线 | ALLOW_ONE_DEVICE_OF_DESKTOP_AND_ONE_DEVICE_OF_WEB_AND_ONE_DEVICE_OF_MOBILE_ONLINE |
| 允许Desktop端或手机端的一个设备同时在线                      | ALLOW_ONE_DEVICE_OF_DESKTOP_OR_MOBILE_ONLINE                 |
| 允许Desktop端或手机端或Browser端的一个设备同时在线           | ALLOW_ONE_DEVICE_OF_DESKTOP_OR_WEB_OR_MOBILE_ONLINE          |

#### 注意

- 任何多端登陆类型都不允许一个用户账号在一种设备上有多个同时登陆的设备
- 当用户登陆设备的类型有Unkown或Others情况时，需进行额外配置：允许Unkown/Others设备与其他已知设备同时登陆、不允许Unkown/Others设备与其他已知设备同时登陆

### 多端登陆冲突解决策略

配置属性：im.turms.turms.property.business.User.SimultaneousLogin#conflictStrategy

| **类型**           | **功能描述**               | 对应Enum值                   |
| :----------------- | -------------------------- | ---------------------------- |
| 已上线设备掉线     | 已上线的设备掉线           | FORCE_LOGGED_IN_DEVICES_OFFLINE |
| 预上线设备上线失败 | 准备上线的一方直接上线失败 | LOGGING_IN_DEVICE_OFFLINE       |
