# 接口

Turms客户端目前支持JavaScript、Kotlin、Swift与Dart这四种语言，对外暴露一致的接口，并且表现为一致的行为。各语言版本之间的部分接口参数可能出现不完全一致的情况，这主要体现在：1. 接口采用更贴近当前语言特性及习惯的参数与语法；2. turms-client-js独有的参数与接口。

由于Turms各语言客户端行为具有高度的一致性，因此如果您基于上述任意一种语言进行业务开发，您可以在代码逻辑不做改变的情况下，轻松将已写好的业务代码翻译为另外三种语言（具体可参考在本文结尾处的示例）。

## 客户端的对外逻辑结构

- `TurmsClient`：Turms客户端唯一直接对外暴露的类，一个`TurmsClient`实例代表着一个客户端与服务端之间的会话连接。以下变量是`TurmsClient`对外的成员变量。
  
  - `driver`：TurmsClient的运行驱动。负责连接的开起关闭、底层数据的发送接收与心跳控制等基础性操作。以下介绍到的Service层类都基于driver运作。
  
  - `userService`：用户相关服务。负责如用户登陆、添加好友、添加关系人分组、发送/处理好友请求、查询附近的用户等操作。
  
  - `groupService`：群组相关服务。负责如创建群组、变更群主、修改群成员角色、修改群信息等操作。
  
  - `messageService`：消息相关服务。负责如发送消息、修改已发送消息、查询各类消息与其状态、撤回消息等操作。
  
  - `notificationService`：通知相关服务。负责接受与响应业务层面上的通知（比如：其他用户向该用户发送好友请求、群组成员上下线等通知）。
    提醒：消息（message）不算做业务层面上的“通知”（notification），因此`notificationService`不会处理用户消息，用户消息仅由`messageService`进行处理。而driver中`TurmsNotification`的“通知”概念指的是网络层面上的Turms服务端给Turms客户端的通知，因此`notificationService`也不会处理底层的TurmsNotification数据。
    
    补充：关于通知功能的开启与关闭，您可以在turms服务端`im.turms.server.common.infra.property.env.service.business.NotificationProperties`处，实时地进行修改。
    
  - `storageService`：存储相关服务（可选拓展）。负责用户头像、群组头像与消息附件的上传与下载操作。补充：该服务为Turms的拓展服务，因此若您希望使用该功能，您需要将turms-plugin-minio或您自行实现的存储插件集成到turms服务端当中。

### Service中方法的返回值

与Turms服务端交互的所有Turms客户端接口都**基于异步模型编写**。turms-client-js使用Promise模型，turms-client-kotlin使用Coroutines模型，而turms-client-swift使用Promise模型（由PromiseKit提供）。

各种Service可以对Turms所提供的业务数据进行增删改查操作。您需要了解其返回值种类，以开发您自己的业务代码。

#### 对于状态码为10xx的响应（拓展知识）

* 对于增加业务数据的方法，如果该方法的返回值被声明为一个异步模型（如：`Promise<Response<string>>`），则返回的泛型（如前文的string类型）的值必定不为空，否则会抛出一个状态码为`INVALID_RESPONSE`的错误`ResponseError`或`ResponseException`，表明本应该存在的数据丢失。若出现该错误，则意味着Turms服务端或客户端自身存在行为不一致的Bug。

* 对于删除与更新业务数据的方法，它们均返回被异步模型包裹的Void类型（如：`Promise<Response<Void>>`）。

* 对于查找业务数据的方法：

  如果该类方法返回被异步模型包裹的List类型，则当服务端返回空数据时，该查找操作方法会返回一个空List，而非null或undefined。

  如果被包裹的类型不是List类型，则当服务端返回空数据时，该查找操作方法会返回一个`undefined`（JavaScript）或`null`（Kotlin）或`nil`（Swift）。特例：`answerGroupQuestions`方法可以算做查询方法，但其返回数据永不为空。

#### 对于状态非10xx的响应（拓展知识）

这类响应均被认作是“错误”状态响应。Service中的方法会通过异步模型抛出`ResponseError`或`ResponseException`，并且这些错误或异常实例均会携带具体的响应状态码与错误原因。

### 主要接口差异（拓展知识）

通常情况下，您并不需要关心各客户端接口之间的差异，但如果您的团队需要由一名开发者基于多个Turms客户端进行上层的开发工作，或者您需要对照您项目的上层客户端代码实现的异同，您可以了解一下客户端间主要接口的不同。

在早期Turms客户端实现中，各客户端之间的接口参数与数据模型是尽量保持统一的参数配置与含义，如时间相关的参数。但这种强行统一的写法不符合目标语言习惯。同时考虑到在大部分情况下，各客户端的上层业务代码通常有专人负责，而非全由一名开发者负责，统一含义意义不大，并且这些差异也符合目标语言习惯，故不进行强制统一。

客户端主要接口的差异如下表：

|              | JavaScript客户端                  | Kotlin客户端                       | Swift客户端              | Dart客户端  | 示例            |
| ------------ | --------------------------------- | -------------------------------------- | ------------------------ | ------------------------ | ------------------------ |
| 时间单位 | 一律为毫秒                        | 一律为毫秒 | 采用TimeInterval（即秒） | 一律为毫秒 | connectTimeout |
| 响应异常模型 | ResponseError（继承自Error） | ResponseException（继承自RuntimeException） | ResponseError（继承自Error） | ResponseException（继承自Exception） |    |
| 异步模型     | Promise                       | Coroutines | 由PromiseKit提供的Promise | Future |                          |

补充：对于对外暴露的回调函数实现，Turms的Swift客户端没有采用Swift常见的delegate代理模式，而是和其他语言客户端一样通过函数传递逃逸闭包。

## 理解接口（重点）

Turms所有客户端的接口都非常容易理解与使用。开发者甚至不需要看Turms客户端有什么接口，只需要凭借基本的IM业务知识就能反推Turms会有什么接口。

开发者一般只需要记住：

* 通过`new TurmsClient(...)`创建Turms客户端实例
* 在上文`客户端的对外逻辑结构`提到的：Turms客户端分为五个服务：`userService`（用户相关服务）、`groupService`（群组相关服务）、`messageService`（消息相关服务）、`notificationService`（通知相关服务）、`storageService`（存储相关服务、可选拓展）。

之后我们就能凭借业务知识反推Turms客户端会有什么接口了，比如：

* 用户首先要能登陆，于是先想到其对应的服务`userService`用户相关服务。既然是`登陆`所以找找有没有`login`方法，于是自然地就找到了`client.userService.login(...)`方法。
* 登陆后，用户需要能够发消息，那就先想到`messageService`消息相关服务，再看看有没有类似`sendMessage`的方法，于是找到了`client.messageService.sendMessage(...)`方法。
* 既然能发消息，那有什么方法能监听收到的消息呢？既然跟消息有关，那依旧想到的是`messageService`，于是想到方法可能是`onMessage`、`subscribeMessage`或`addMessageListener`，代码里找一找，找到了`client.messageService.addMessageListener(...)`。
* 既然能监听收到的消息，那怎么监听接收到的通知呢？既然跟通知有关，那想到的就是`notificationService`通知相关类服务，并且既然监听收到的消息的方法叫`addMessageListener`，那监听通知的方法就应该是`addNotificationListener`了，于是找到了`client.notification.addNotificationListener`。

综上，开发者一般只需凭借基本的业务知识就能反推Turms客户端提供的接口，甚至不需要读Turms客户端的源码。

而对于高级开发者，Turms客户端也开放了`driver`对象，让开发者自行实现一些相对底层的操作。另外，如在`会话的生命周期`提到的，Turms客户端是故意设计的清晰易懂，故意不提供诸如自动重连、自动路由跳转等操作，因为一方面开发者可以很容易地自行实现该类逻辑，另一方面，这类“隐藏”的内部逻辑会使得上层开发者难以把控底层驱动行为，在一些时候反而会成为绊脚石。

## 具体示例

以下示例包括turms-client-js/kotlin/swift/dart四个版本，并且其作用等价。具体包括了以下业务操作：初始化客户端、登录、监听会话连接断开（下线）、监听通知、监听新消息、查询附近的用户、发送消息、创建群组操作。

### 体验示例前的服务端准备工作

* 方案一：无需在本地搭建Turms服务端，用户直接在本地通过客户端API连接Playground上的turms-gateway服务端（WebSocket端口：http://playground.turms.im:10510；TCP端口：http://playground.turms.im:11510）。但注意及时将本地客户端升级到最新版本，以避免出现因为服务端侧的接口更新，导致数据不一致的问题。
* 方案二：在`application.yaml`配置文件中更新以下配置：
   1. 将`turms.gateway.session.enable-authentication`设置为`false`（取消用户登录认证）
   2. 将`turms.service.message.allow-sending-messages-to-stranger`设置为`true`（允许没有用户关系的用户互相发送消息）
* 方案三：使用自带`dev` profile配置。因为Turms提供的`dev`profile已做了上述配置。默认情况下，Turms发布包中的`application.yaml`的`profile`字段为空，即默认的profile不是`dev`，需要您手动配置为`dev`。

### 代码示例

::: code-group

```javascript [turms-client-js]
// Initialize client
const client = new TurmsClient(); // new TurmsClient('ws://any-turms-gateway-server.com');

// Listen to the offline event
client.userService.addOnOfflineListener(info => {
    console.info(`onOffline: ${info.closeStatus}:${info.businessStatus}:${info.reason}`);
});

// Listen to inbound notifications
client.notificationService.addNotificationListener(notification => {
    console.info(`onNotification: Receive a notification from other users or server: ${JSON.stringify(notification)}`);
});

// Listen to inbound messages
client.messageService.addMessageListener(message => {
    console.info(`onMessage: Receive a message from other users or server: ${JSON.stringify(message)}`);
});

client.userService.login({
    userId: '1',
    password: '123'
})
    .then(() => {
        client.userService.queryNearbyUsers({
            latitude: 139.667651,
            longitude: 35.792657,
            maxCount: 10,
            maxDistance: 1000
        })
            .then(response => {
                console.log(`nearby users: ${JSON.stringify(response.data)}`);
            });
        client.messageService.sendMessage({
            isGroupMessage: false,
            targetId: '1',
            deliveryDate: new Date(),
            text: 'Hello Turms',
            burnAfter: 30
        })
            .then(response => {
                console.log(`message ${response.data} has been sent`);
            });
        client.groupService.createGroup({
            name: 'Turms Developers Group',
            intro: 'This is a group for the developers who are interested in Turms',
            announcement: 'nope'
        })
            .then(response => {
                console.log(`group ${response.data} has been created`);
            });
    })
    .catch(reason => {
        console.error(reason);
    });
```

```kotlin [turms-client-kotlin]
// Initialize client
val client = TurmsClient() // TurmsClient("127.0.0.1", 11510)

// Listen to the offline event
client.userService.addOnOfflineListener { info ->
    println("onOffline: ${info.closeStatus}:${info.businessStatus}:${info.reason}")
}

// Listen to inbound notifications
client.notificationService.addNotificationListener { notification ->
    println("onNotification: Receive a notification from other users or server: $notification")
}

// Listen to inbound messages
client.messageService.addMessageListener { message, _ ->
    println("onMessage: Receive a message from other users or server: $message")
}

client.userService.login(1, "123")

val users = client.userService.queryNearbyUsers(
    35.792657f,
    139.667651f,
    10,
    1000
).data
println("nearby users: [${users.joinToString(", ")}]")

val msgId = client.messageService.sendMessage(
    false,
    1,
    Date(),
    "Hello Turms",
    null,
    30
).data
println("message $msgId has been sent")

val groupId = client.groupService.createGroup(
    "Turms Developers Group",
    "This is a group for the developers who are interested in Turms",
    "nope"
).data
println("group $groupId has been created")
```

```swift [turms-client-swift]
// Initialize client
let client = TurmsClient() // TurmsClient("127.0.0.1", 11510)

// Listen to the offline event
client.userService.addOfflineListener { (info: SessionCloseInfo) -> () in
    print("onSessionDisconnected: \(info.closeStatus):\(info.businessStatus ?? ""):\(info.reason ?? "")")
}

// Listen to inbound notifications
client.notificationService.addNotificationListener { (notification: TurmsRequest) -> () in
    print("onNotification: Receive a notification from other users or server: \(try! notification.jsonString())")
}

// Listen to inbound messages
client.messageService.addMessageListener{ (message: Message, _: MessageAddition) -> () in
    print("onMessage: Receive a message from other users or server: \(try! message.jsonString())")
}

client.userService.login(userId: 1, password: "123")
    .done {
        client.userService.queryNearbyUsers(
                latitude: 35.792657,
                longitude: 139.667651,
                maxCount: 10,
                maxDistance: 1000)
            .done {
                print("nearby users: \($0.data)")
            }
        client.messageService.sendMessage(
                isGroupMessage: false,
                toId: 1,
                deliveryDate: Date(),
                text: "Hello Turms",
                records: nil,
                burnAfter: 30)
            .done {
                print("message \($0.data) has been sent")
            }
        client.groupService.createGroup(
                name: "Turms Developers Group",
                intro: "This is a group for the developers who are interested in Turms",
                announcement: "nope")
            .done {
                print("group \($0.data) has been created")
            }
    }.catch {
        print($0)
    }
```

```dart [turms-client-dart]
// Initialize client
final client = TurmsClient(); // TurmsClient(host: '127.0.0.1', port: 11510)

// Listen to the offline event
client.userService.addOnOfflineListener((info) => 
    print('onOffline: ${info.closeStatus}:${info.businessStatus}:${info.reason}'));

// Listen to inbound notifications
client.notificationService.addNotificationListener((notification) => 
    print('onNotification: Receive a notification from other users or server: $notification'));

// Listen to inbound messages
client.messageService.addMessageListener((message, _) => 
    print('onMessage: Receive a message from other users or server: $message'));

await client.userService.login(Int64(1), password: '123');

final users = (await client.userService.queryNearbyUsers(
        35.792657, 139.667651,
        maxCount: 10, maxDistance: 1000))
    .data;
print('nearby users: $users');

final msgId = (await client.messageService
        .sendMessage(false, Int64(1), text: 'Hello Turms', burnAfter: 30))
    .data;
print('message $msgId has been sent');

final groupId = (await client.groupService.createGroup(
        'Turms Developers Group',
        announcement:
            'This is a group for the developers who are interested in Turms',
        intro: 'nope'))
    .data;
print('group $groupId has been created');
```

:::