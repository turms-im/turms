# API

Turms client currently supports four programming languages, JavaScript, Kotlin, Swift and Dart, exposing a consistent interface and behaving in a consistent manner. Some interface parameters may be inconsistent across languages, mainly due to: 1. The interface uses parameters and syntax that are closer to current language characteristics and conventions; 2. Unique parameters and interfaces of turms-client-js.

Since Turms client behavior is highly consistent across languages, you can easily translate your written business code into the other three languages without changing the code logic (see the examples at the end of this article) if you develop your application based on either language.

## External Logic Structure

- `TurmsClient`: `TurmsClient` is the only class exposed directly to the public. A `TurmsClient` instance represents a session between a client and a server. The following variables are the external member variables of `TurmsClient`.
  
  - `driver`: `TurmsClient`'s runtime driver. It is responsible for the basic operations such as opening and closing the connection, sending and receiving the underlying data and heartbeat control. The following service layer classes are all driver-based.
  
  - `userService`: A user-related service. It is responsible for such operations as user login, adding friends, adding relationship groups, sending/processing friend requests, querying nearby users, etc.
  
  - `groupService`: A group-related service. It is responsible for operations such as creating groups, changing group owners, modifying group members' roles, modifying group information, etc.
  
  - `messageService`：A message-related service. It is responsible for operations such as sending messages, modifying sent messages, querying various messages and their status, recalling messages, etc.
  
  - `notificationService`: A notification-related service. It is responsible for receiving and responding to business-level notifications (e.g., other users sending friend requests to the user, group members going up and down, etc.).
    Reminder: messages are not considered as business-level notifications, so `notificationService` does not handle user messages, and user messages are only handled by `messageService`. The concept of "notification" in `TurmsNotification` in driver refers to the notification from the Turms server to the Turms client at the network level, so the `notificationService` does not handle the underlying `TurmsNotification` data.
  
    Addendum: You can change the notification function on and off in real-time at `im.turms.server.common.infra.properties.env.service.business.NotificationProperties` on the Turms server.
  
  - `storageService`: A storage-related service (optional extension). It is responsible for upload and download operations of user avatars, group avatars and message attachments. Note: This service is an extension of turms, so if you want to use this feature, you need to integrate turms-plugin-minio or your own storage plugin into the Turms server.

### Return Value of Methods in Services

All Turms client service methods that interact with the Turms server are **written based on the asynchronous model**. turms-client-js uses the Promise model, turms-client-kotlin uses the Coroutines model, and turms-client-swift uses the Promise model (provided by PromiseKit).

Various Services can add, delete, update and query the business data provided by Turms. You need to understand their return value types in order to develop your own business code.

#### Deep Dive - For Responses with Status Code 10xx

* For methods that add business data, if the return value of the method is declared as an asynchronous model (e.g., `Promise<Response<string>>`), the return value of the generic type (such as the string type in the previous section) must not be null, otherwise an error with status code `INVALID_RESPONSE` will be thrown `ResponseError` or `ResponseException`, indicating that a data that should exist is missing. If this error occurs, it means there is a bug of inconsistency in the behavior of either the Turms server or client.

* For methods that delete and update business data, they both return Void types wrapped by asynchronous models (e.g., Promise\<Response\<Void>>).

* For functions that find business models.

  If the function of this class returns a List type wrapped by an asynchronous model, the lookup operation function returns an empty List instead of null or undefined when the server returns empty data.

  If the wrapped type is not a List, the lookup function returns an `undefined` (JavaScript) or `null` (Kotlin) or `nil` (Swift) when the server returns null data. Special case: the `answerGroupQuestions` method can be counted as a query method, but its return data is never null.

#### Deep Dive - For Responses with Status Code Other Than 10xx

These types of responses are all regarded as "error" status responses. The methods in the Service will throw `ResponseError` or `ResponseException` through the asynchronous model, and these error or exception instances will carry a specific response status code and an error reason.

### Deep Dive - Main Interface Differences

Normally, you don't need to care about the differences between client interfaces, but if your team needs to have one developer working on the upper layers based on multiple Turms clients, or if you need to compare the similarities and differences between the upper layer client code implementations for your project, you can learn about the differences in the main interfaces between the clients.

In early Turms client implementations, the interface parameters and data model between the clients were kept as uniform as possible in terms of configuration and meaning, such as time-related configuration parameters. However, this forced uniformity was written in a way that did not conform to the target language conventions. Also, considering that in most cases, the upper-level business code of each client usually has a dedicated person in charge of it, rather than all by one developer, the uniform meaning is not significant, and these differences are also in line with the target language habits, so no mandatory uniformity is made.

The differences in the main interfaces of the clients are listed below.

|                          |      | JavaScript Client                    | Kotlin Client                                       | Swift Client                         | Dart Client                                  | Examples       |
| ------------------------ | ---- | ------------------------------------ | --------------------------------------------------- | ------------------------------------ | -------------------------------------------- | -------------- |
| Time Unit                |      | Consistent with milliseconds         | Consistent with milliseconds                        | Uses TimeInterval (i.e., seconds)    | Consistent with milliseconds                 | connectTimeout |
| Response Exception Model |      | ResponseError (inherited from Error) | ResponseException (inherited from RuntimeException) | ResponseError (inherited from Error) | ResponseException (inherited from Exception) |                |
| Asynchronous Model       |      | Promise                              | Coroutines                                          | Promise provided by PromiseKit       | Future                                       |                |

Note: For the externally exposed callback function implementation, Turms Swift client does not use the delegate proxy common to Swift, but escapes the closure via function passing like other language clients.

## Understanding interfaces (Important)

The interfaces of all Turms clients are very easy to understand and use. Developers don't even need to look at what interfaces Turms clients have. They can simply deduce what interfaces Turms will have based on basic IM business knowledge.

Developers generally only need to remember:

* Create a Turms client instance through `new TurmsClient(...)`
* As mentioned in the previous section on `External Logic Structure`, the Turms client is divided into five services: `userService` (related to user), `groupService` (related to group), `messageService` (related to message), `notificationService` (related to notification), and `storageService` (related to storage, optional).

Afterwards, based on business knowledge, we can infer what interfaces the Turms client will have, such as:

* If a user needs to log in first, we naturally think of the `userService` related to users. Since it is "logging in," we look for a `login` method and naturally find the `client.userService.login(...)` method.
* After logging in, the user needs to be able to send messages. We would then think of the `messageService` related to messages and look for an method similar to `sendMessage`, which leads us to the `client.messageService.sendMessage(...)` method.
* Since we can send messages, what method can we use to listen for received messages? Since it is still related to messages, we still think of the `messageService`, so we might consider methods like `onMessage`, `subscribeMessage`, or `addMessageListener`. Looking through the code, we find the `client.messageService.addMessageListener(...)` method.
* If we can listen for received messages, how do we listen for received notifications? Since it is related to notifications, we naturally think of the `notificationService` related to notifications. Since the method for listening for received messages is called `addMessageListener`, the method for listening to notifications should be `addNotificationListener`, which leads us to the `client.notification.addNotificationListener` method.

In summary, developers generally only need basic business knowledge to infer the interfaces provided by the Turms client, and do not even need to read the source code of the Turms client.

For advanced developers, the Turms client also provides a `driver` for implementing relatively low-level operations. In addition, as mentioned in the section on `Session Lifecycle," the Turms client is intentionally designed to be clear and easy to understand, deliberately not providing operations such as automatic reconnection or automatic routing, because on one hand developers can easily implement such logic themselves, and on the other hand, such "hidden" internal logic can make it difficult for upper-level developers to control low-level driver behavior and can sometimes become a stumbling block.

## Examples

The following examples include four versions of turms-client-js/kotlin/swift/dart and have equivalent functionalities. The following business operations are included: client initialization, login, listen for session disconnections (offline), listen for notifications, listen for new messages, query nearby users, send messages, and create groups.

### Server-side Preparation before Trying Examples

* Option 1: No need to build Turms servers locally, users connect to turms-gateway on Playground directly locally via the client API (WebSocket endpoint: http://playground.turms.im:10510; TCP endpoint: http://playground.turms.im:11510). However, pay attention to upgrade the local client to the latest version in time to avoid the problem of inconsistent data because of server-side interface updates.
* Option 2: Update the following configuration in the `application.yaml` configuration file.
  1. Set `turms.gateway.session.enable-authentication` to `false` (disable user login authentication)
  2. Set `turms.service.message.allow-sending-messages-to-stranger` to `true` (allow users without relationship to send messages to each other)
* Option 3: Use the built-in `dev` profile configuration. This is because the `dev` profile provided by Turms already has the above configuration. By default, the `profile` of `application.yaml` in the Turms distribution package is empty, i.e. the default profile is not `dev` and you need to configure it to `dev` manually.

### Code example

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