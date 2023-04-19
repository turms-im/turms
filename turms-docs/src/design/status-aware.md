# Status Awareness

Status awareness is divided into two categories, one is "user online status awareness", and the other is "business data change awareness" (such as receiving new messages, group members sending changes).

Since the specific implementation of state awareness is closely related to specific product requirements, you need to be able to grasp the following two points:

1. Determine whether the product demand is reasonable. Usually unreasonable requirements, such as: there can be 10,000 users in a group, when a user sends a message, it is necessary to ensure that the message can be 100% sent to other 9999 users, and the user can pull a few years ago Chat information.
2. Distinguish primary and secondary requirements, and try to strike a balance between quality attributes. There are many details in the implementation of IM services. Is it really necessary to design a large number of back-and-forth strategies (such as message session-level auto-increment IDs) in order to be compatible with extreme situations, which not only greatly increases the development cost and failure points, but also makes the overall server throughput drops.

## User online status awareness

In short, Turms detects the health status of the user's TCP connection through the heartbeat packet and judges whether the user is "online". Also, if you don't care about the underlying implementation, you only need to read: [Client API - Session Lifecycle](https://turms-im.github.io/docs/client/api#%E4%BC%9A%E8%AF%9D%E7%9A%84%E7%94%9F%E5%91%BD%E5%91%A8%E6%9C%9F).

### Specific principles (expanding knowledge)

#### background

From the perspective of the network transport layer, TCP is just a virtual connection, which needs to simulate a physical connection through two-way message delivery and message confirmation. In the case of waving for the first time (that is, the specified message transmission and confirmation is not completed), TCP still determines that the connection is in a hold state (if you try to read data from the TCP connection at this time, it will throw a message similar to "An existing connection was forcibly closed by the remote host" message). Therefore, for the upper-layer instant messaging application developed based on the TCP protocol, if we do not do extra work, the server can only mistakenly believe that "the user is online".

#### Common reasons why TCP did not complete the four wave

* Client: The client application is forcibly closed
* Server: The load continues to be too high to respond; the server directly goes down, causing the server application to be forcibly closed
* Link intermediate routing: unexpected interruption (eg: mobile access network NAT timeout)

#### Solutions for abnormal disconnection

In order to ensure that the server can perceive the state of "user offline", the Turms client will, after a certain time interval from the last request of any type (such as a request to send a message) (for now, it does not support the configuration of smart heartbeat according to network conditions), Send heartbeat packets to the server to maintain its "online status". After the server receives the heartbeat packet or other business requests from the client, it will refresh the online status of the client on the Redis server to keep alive.

## Business data change perception

In order to allow users to perceive changes in business data (addition, deletion, modification), Turms supports push mode (server-side active notification), pull mode (client-side active pull mechanism. Support pull by Timeline) and push-pull combination mode to achieve real-time Balance between real-time performance and resource consumption, and allow developers to adjust the weight between real-time performance and resource consumption.

### Perception

#### Method 1: push mode (active notification from the server)

The push mode means that when a certain business model changes (due to addition, deletion and modification operations), the server will actively notify the relevant online users of the occurrence of the event. When the client receives the notification, the Turms client will trigger the `onNotification` callback function in `NotificationService`. The parameter of this function is a `TurmsRequest` object, indicating the request that triggered the event.

Notification-related behaviors can be configured according to the `im.turms.server.common.infra.property.env.service.business.NotificationProperties` class. Each notification type can be configured individually, and all notification-related configurations can be dynamically updated while the cluster is running.

##### Example

Take the property `im.turms.server.common.infra.property.env.service.business.NotificationProperties#notifyMembersAfterGroupUpdated` as an example. This attribute is used to control "whether to notify group members when group information changes". The group information here refers to global group information such as group name, group type, and group silence time.

If you set this attribute value to true, when the group information changes, the clients of the group members will receive a notification that triggers the change. Otherwise, group member clients will not receive any notifications.

##### evaluate

The notification mechanism can ensure that notifications can be delivered to relevant users in real time, but its disadvantage is that it can easily lead to meaningless resource consumption (subject to specific business scenarios). For example, user A has joined 100 groups, but the user usually only checks the information of 3 of them. In this scenario, if the notification mechanism is enabled for all status changes of 100 groups, both the server and the client need to waste a lot of resources to deal with these meaningless notifications (because the user never reads these notifications) .

In order to solve this type of problem and meet other common needs (such as: requiring offline users to detect whether the business model has changed when they go online; requiring online users to perceive changes in the business model even when the notification is turned off) , Turms also provides a pull mode (the client actively pulls) to allow users to perceive changes in the business model.

#### Method 2: Pull mode (the client actively pulls. Supports pulling by Timeline)

In order to make up for the deficiencies of the push mode mentioned above, Turms also provides a pull mode.

##### About to achieve

Each business model of Turms has a version information, which records the time when the business model was last updated. When the client requests resources from the server, it can carry the time when the client last updated the business model (or not). The Turms server will compare this version information with the version information of the current business model. If the client If the version information sent by the client is earlier than the version information of the current business model, the Turms server will return the latest business model data, otherwise the status code `NO_CONTENT` will be thrown, and the client will receive empty data.

##### Common pull timing (synchronization timing)

* When your app is switched to the foreground
* When the session is reconnected
* Depends on specific business (see example below)

##### Example

Continuing with the example above. Assume that we want group members to be able to perceive changes in the profile information of other group members in real time. Then if we adopt the notification mechanism, assuming that each group has 100 other online users besides user A, then user A’s profile information needs to be notified to other 10,000 (100 groups*100 people/group) group members, This is absolutely undesirable in practical applications.

In practice, usually at a specific time (for example, when the user opens a user's personal information UI interface, or opens a chat window with someone), the client will actively request the server for the user's information. At the same time, use version comparison to reduce meaningless waste of resources.

This kind of design that always pays attention to real-time and resource consumption should be kept in mind, so as not to design unrealistic application scenarios.

### The real-time perception of user behavior by the client and the delay of the server

Taking the related implementation of blocking users as an example, Turms caches user relationships for 1 minute by default to avoid frequent database queries, which is a reasonable behavior. If user A "blocks" user B at this time, it may appear that although user A has blocked user B, user B may still be able to send messages to user A during the cached period (because The Turms server is a distributed cluster, and the relational cache and the server that receives the blacklist request are not necessarily the same server). **This behavior is acceptable to the Turms server, not a bug**.

Its reasonable and ideal reference solution is: on the business level of the client (the business logic is controlled by you, not by the Turms client), even if the Turms server sends a message to the Turms client, your client should also follow the The business logic of your product itself, judge whether the user has been blocked again, and if so, hide it or not.

## Message awareness

### Read flooding and write flooding

The architecture of Turms is designed based on the read diffusion message model. The following table compares the advantages and disadvantages of read flooding and write flooding for readers' reference:

|                           | Read Diffusion                                               | Write Diffusion                                              |
| ------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| Meaning                   | 1. Each user has an individual conversation (also known as mailbox or Timeline) with other users or groups that they chat with. <br />2. When a user sends a message, regardless of private chat or group chat, the database only needs to store a message record. <br />3. When the user queries the message, the client needs to send a request to the server to pull the message of the specified session ID list; Chat session messages, and then use a request to specify the group chat session ID list to pull group chat messages | 1. Each user has and only one mailbox. <br />2. When a user sends a message, the message needs to be written to the mailboxes of all members in the session, that is, if there are 100 other members in the group chat, the message needs to be written 100 times. <br />3. When the user queries the message, the client does not need to specify the session ID list, but only needs to send a request to the server to read the message in its own mailbox |
| Advantageous Scenarios    | Scenarios where there are relatively few user sessions (private chat sessions and group chat sessions) and a large number of groups. <br />Note: If the application only has private chat sessions and no group chat sessions, then under the implementation of the Turms server, the advantages and disadvantages of read flooding and write flooding are not much different, because both message models only require When a user sends a message, the database writes a message; when a user reads a message, the database looks up the table once based on the index (Turms uses a composite index of `message sending time + recipient ID`, see [Message Collection Design](https://turms-im.github.io/docs/design/schema#%E9%9B%86%E5%90%88%E8%AF%A6%E8%A7%A3)) | To avoid too many Message copying, so write diffusion is relatively more suitable for scenarios where there are many group chats but few group members |
| Disadvantageous scenarios | Because the client needs to specify the ID list of group chat sessions, the disadvantageous scenario of read diffusion is: there are many group chat sessions, and users read messages frequently. <br />Reminder: The Turms server uses a MongoDB client request to complete the above query operations based on the index, so the performance is actually very efficient. Only compared to write flooding, this scenario is a disadvantageous scenario for read flooding | because the more group members, the more times the message is copied, so the disadvantageous scenario of write flooding is: a single group has many members, and the group members are frequent send message |
| Technical Implementation  | 1. Read requests can be load-balanced through MongoDB's shard copy architecture<br />2. All read requests are implemented based on indexes, with high performance | 1. Write operations are difficult to load-balance<br /> br />2. The cost of implementing IM functions such as updating messages and withdrawing messages is huge, and distributed consistency issues and message storms need to be considered |
| Message reliability       | If the product has high requirements on message reliability, that is, to ensure that the message is not lost and that the content of the message is consistent, then the implementation of read diffusion is much simpler, because the database only needs to store one message, and the user only needs to Read this message | Because it is necessary to ensure that the message is written to the mailbox of each group member, it is necessary to introduce a weak distributed consistency transaction (or a strong distributed consistency transaction), otherwise the message may be lost, but the distributed consistency Transactions cause poor throughput |
| General Comments          | 1. Read diffusion is applicable to a wide range of products. For the characteristics of huge cost to implement write diffusion, based on the implementation of read diffusion, usually only the client needs to customize the query conditions and send a query statement to the Turms server. (such as group new member message sharing, multi-terminal message synchronization), the server does not need to change a line of code, and these query tasks are completed based on the index. <br />2. Read diffusion can still rely on indexes to ensure high efficiency in disadvantageous scenarios | Since write diffusion requires writing a large number of messages, any update operations (withdrawal/update) also need to use distributed transactions, and The implementation of IM features (such as group new member message sharing, multi-terminal synchronization) is very complicated. <br />In summary, the business expansion of writing diffusion is extremely poor, and its usage scenarios are basically limited to: applications are basically private chats, no group chats, and the business functions are simple, but for applications that only have private chats, as mentioned above , the performance of read flooding or write flooding is similar. <br />If your team's product manager asks to add business functionality, your development team will quickly realize how fatal the design of an IM system is to only support write flooding. Read diffusion can be a very efficient and easy-to-implement function, but for write diffusion, this becomes an inefficient and difficult-to-implement function |

Emphasize again: unless you are very clear that the use case of your product is as simple and limited as above (the number of private chat sessions does not matter, but the number of group chat sessions is large and the number of group members is small), and the future business needs will basically remain unchanged, otherwise use Understanding the write diffusion message model basically means that your product will one day need to refactor the readback diffusion model, or support both reading and writing models. Of course, write diffusion can also be retained for a long time as "technical debt".

remind:

* Changing from a write-diffusion implementation to a read-diffusion implementation almost means recreating the design and implementation of the entire project from scratch. Also because the impact of the message model on the IM architecture is so great, when we talk about the Turms architecture, the first sentence is always "Turms architecture is designed based on the read diffusion message model".
* In the implementation of the Turms server, the "withdrawal message" is also a message, that is, a special system message.

### Message reception, message update and message withdrawal

Turms implements message reception, update and withdrawal on the client side based on the above-mentioned "push mode" and "pull mode". in:

* Combining the above "common pull timing" and the following "About message accessibility, order and repeatability", Turms can achieve 100% message arrival, message consistency sorting and deduplication

* The notification of message update and withdrawal is essentially a message, that is, a special system message. After receiving the message update or cancellation request from the user, the Turms server will first judge whether the function is enabled, whether the user has permission, whether it is within a certain time interval, etc. If the verification passes, it will (hereinafter referred to as the withdrawal message Take the process as an example, the update message is the same):

  * The Turms server first modifies the target original message record stored in the database, and marks it with a timestamp of "message withdrawn".

  * Then generate a "withdrawal message" system message (note that it is a `message`, not a notification `notification`), and insert it into the message collection.

  * Finally, send the above-mentioned "withdrawal message" system message to the corresponding online users to inform these clients that some messages have been withdrawn before.

    After the client receives the system message, the developer needs to do the processing on the corresponding business layer (the Turms client will not do any other logical processing except for parsing which messages are withdrawn), such as physically deleting the message locally. message, or just hide it, or replace a retracted message with something like "This message was retracted at XX time", etc.

    Supplement: As mentioned above, when the current Turms server processes a withdrawal message, it will send a "withdrawal message" system message to the corresponding online client to ensure that the online client can quickly withdraw the locally received message. Configuration items will also be added to support applications that do not want the Turms server to actively send this system message.

  * If the user is already offline and has not received the "withdrawal message" system message, then when the user logs in next time, it still needs to actively pull the message received when offline, so in the process of pulling By the way, the "withdrawal message" system message inserted above will also be pulled down. When developers detect such system messages, they can do specific business layer processing.

    Reminder: Developers can use the `addMessageListener` interface in the message service provided by the client side to determine whether the received message is a system message of "withdrawal message". Take the turms-client-js client as an example:

    ```js
    turmsClient. messageService. addMessageListener((message, addition) => {
        if (addition. recalledMessageIds. length) {
            // is a system message to recall messages
        } else {
            //not
        }
    });
    ```

in addition:

* Regarding the process of deleting messages on the Turms server, the Turms server currently only performs soft delete or hard delete on the messages, and does not perform any logic related to "withdrawing messages". We will add corresponding configuration items to Turms in the future to support applications that want to withdraw messages when they want to delete messages.
* At present, the Turms server does not provide complete support for "update message" like "withdraw message", and the optimization of this part will be completed in the near future.

### About the reachability, orderliness and repeatability of messages

Architectural design is always the art of balance, and blindly promising 100% news is just a sales rhetoric. For example, most Internet applications will only use weakly distributed transactions with better performance in the technical implementation of distributed transactions, rather than strong distributed transactions that are more reliable but have low performance. Whether it is necessary to achieve 100% message delivery depends on the business scenario. For example, in the live chat room scenario, not only does it not require that the message must arrive, but it even requires the server to actively discard user messages according to the load situation and message priority, or only send the message to some users.

The live broadcast scene may not require the order of messages, but requires "how to design a message with a high throughput. Try to ensure the order of the messages, but do not provide additional auxiliary resources for support." Some designed IM applications can also "in order to achieve a balance between high throughput and high reachability, use the non-message must reach mechanism for free groups, and use the message must reach mechanism for VIP groups". The needs of practical applications are always varied.

Therefore, it is emphasized again: when doing functional design, it is necessary to distinguish between primary and secondary requirements, and to strike a balance between quality attributes as much as possible. Never leave the business scene and work behind closed doors.

#### Summarize

Since the specific implementation comparison of various message features below is relatively complicated, this summary section quickly summarizes the final solution for you.

In general, Turms is designed to follow the principle that the client can implement it itself, and the Turms server does not implement it, so as to achieve maximum throughput and flexible business implementation. If the feature must be implemented by the server and has little impact on throughput, it is enabled by default, otherwise it is disabled by default`, specifically:

* Accessibility

  * Solution 1: If you want to achieve almost 100% message delivery, you can enable `use-sequence-id-for-group-conversation` and `use-sequence` under `turms.service.message.sequence-id` -id-for-private-conversation` (default configuration, all closed), this mechanism will request a session-level auto-increment `sequence ID` from Redis every time a message record is generated, and assign this ID to the current In the message record, the client can judge whether the message is lost through the auto-increment of the ID and the message sending time (the need to judge the message sending time is because: if Redis crashes and the serial number data is lost, the serial ID will be calculated from the beginning, and when If the client detects that the serial number has become smaller, it can then determine which message is the latest message based on the message sending time).

    Note: `sequence ID` has nothing to do with `message ID`.

  * Option 2 (default implementation): If you do not require messages to be 100% guaranteed, turn off the above configuration to obtain greater message push throughput.

* Orderliness

  * Sequential eventual consistency
    * Option 1: Use the self-incrementing `sequence ID` mentioned above to realize the order of messages "by the way"
    * Solution 2: (default implementation) Use server time to ensure message order. Reminder: Not only messages need to use the system time, but also various functional modules of the Turms server heavily use the system time, such as the ID generated based on the Snowflake algorithm, the timestamp of the log, and the current limiting and anti-scraping mechanism based on the timestamp.

  * Consistency in receiving order: Some IM systems will delay sending messages or displaying messages on the client side to avoid as much as possible "the client first receives the message sent later, and then receives the message sent earlier", resulting in a message UI Need to rearrange. However, Turms has no plans to provide relevant support

  * Causal consistency: When the client sends a message, it can carry the `preMessageId` field, which is used to indicate what the last message ID displayed on the message sending client UI is. This record has no practical effect on Turms itself, but other clients can refer to this value for upper-level message UI display to achieve causal consistency of message logic between clients.

    Note: `preMessageId` has nothing to do with the implementation of "message reachability", it is only used for your product to sort the message UI

* Repeatability. In this regard, the Turms server only provides message records with unique global IDs. The deduplication of messages needs to be implemented by the developer on the client side: if your application needs to achieve 100% deduplication of messages, you need to consider the received The message ID. If your application only needs to ensure deduplication of messages within the life cycle of an application, you only need to store the received message ID in memory, and whenever the server pushes a new message, you only need to judge whether the message with this ID has been processed That's it.

  Reminder: usually only need to store the message ID of the latest local time (such as the last 1 day), there is no need for full storage

In addition, the following will explain a common but often very failed design scheme in the industry, that is, the scheme of "message confirmation mechanism requiring server participation" as a negative case. It achieves the worst "reachability" and "repeatability" effects at the highest cost, and its performance and scalability are also extremely poor. **(TODO: This part of the documentation has not been updated)**

### Message Confirmation Mechanism (Acknowledge)

It is worth noting that:

1. The message confirmation mechanism of Turms does not require the participation of the Turms server
2. The message confirmation mechanism is completely independent from the "read message" function at the business level, and there is no relationship between the two.

|               | Ack mechanism that requires server participation             | Ack mechanism that does not require server participation     |
| ------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| Introduction  | In some instant messaging architecture designs, the client is required to send a message confirmation request to the server at a certain interval (such as 5 seconds, 10 seconds, etc.) after receiving the message (instead of confirming as soon as the message is received. One is to improve the efficiency of confirmation processing, and the other is to reduce the probability of losing messages due to network delays). <br />The server records the latest confirmation time of each session, so that when the user pulls messages from all sessions (such as when the user goes online), he can pull all the messages from the confirmation time to the present through a simple request. | The client stores the last confirmation time of each session locally. If the client wants to obtain any session message to which it belongs, it sends the corresponding session ID and confirmation time to the server, and the server returns all messages from the confirmation time to the present. |
| Advantages    | 1. The client is simple to implement and does not need to store session information locally | 1. The client can customize the range of message fetching. The business is more applicable and can easily support multi-terminal message synchronization<br />2. The server does not need to check the confirmation time of all sessions first, and then pull the message according to the Ack time, which has better performance<br />3. The client does not need to send confirmation requests to the server regularly, which can completely save the performance overhead caused by a large number of confirmation operations |
| Disadvantages | 1. The server needs to check the confirmation time of all sessions first, and then pull the message according to the confirmation time. The performance is relatively poor<br />2. For each message received, the client needs to send a confirmation to the server 1. When the client sends a request, it needs to carry all the session IDs of the message to be requested and the corresponding confirmation time, and the request body is relatively large (but it also corresponds to the above ② Advantages)<br />2. Developers are required to implement the client's local database (such as: Realm database. Turms may help developers implement local storage functions in the future in an extended form) |                                                              |

### About message reachability

Architectural design is always the art of balance, and blindly promising 100% news is just a sales rhetoric. For example, most Internet applications will only use weakly distributed transactions with better performance in the technical implementation of distributed transactions, rather than strong distributed transactions that are more reliable but have low performance. Whether it is necessary to achieve 100% message delivery or not depends on the business scenario (for example, in the live chat room scenario, not only is the message not required to be delivered, but the server is even required to actively discard user messages according to the load situation).

The solution to achieve 100% delivery of messages is also relatively simple. A session-level self-incrementing ID generation server can be implemented through Redis to ensure that message IDs are incremented within a session. The client can judge whether there is a message missing through the incrementality of the ID. If it finds that the message is missing, it can send a request to the server to get the specified message.

Turms will also support the above-mentioned session-level message auto-increment ID implementation to ensure 100% message delivery (TODO), and also provide a global auto-increment ID implementation based on the Snowflake algorithm to provide the best throughput (the cost is that the message cannot guarantee 100% % must reach).

### About the realization of the number of unread messages

#### Business needs

* When used as a desktop badge (Badge Number), display the total number of unread messages (iOS must calculate the total number on the server side). Need to support offline update, or do not need to support offline update
* When used as a conversation badge in the app, it displays the number of unread messages for each conversation

#### plan

|                                   | Does not support offline message push with unread message count (default implementation) | supports offline message push with unread message count (TODO) |
| --------------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| Implementation                    | When the client receives and pulls messages, it sends a request to the server to calculate the "unread messages" in real time. <br />In this solution, the Turms server does not actually have the concept of `unread message count`, the server only calculates the number of messages within a certain message sending time interval according to the client's request | Use Redis to support offline messages Carry the number of unread messages when pushing: carry the number of unread messages in the session and the total number of unread messages; only carry the total number of unread messages Add 1 to the number of unread messages, and add 1 to the total<br />When the user reads the message, or when the user or group is deleted, do the opposite subtraction operation in the Redis record<br />(**Note: The total number of unread messages must be calculated by the server **) |
| Advantages                        | 1. The implementation is simple and can flexibly support various business needs, without the need to introduce a Redis server<br />2. When sending a message, there is no need to send a request to Redis to calculate the number of unread messages, and the write throughput is higher | 1. Support offline message push to carry the number of unread messages<br />2. When reading unread messages, no real-time calculation is required, and the read throughput is higher |
| Disadvantages                     | 1. Does not support the number of unread messages carried when offline messages are pushed<br />2. When the client reads the number of unread messages, real-time calculation is required, and the read throughput is lower (supplement: index support) | 1 . Redis server needs to be introduced to increase the cost and difficulty of operation and maintenance<br />2. Every time the server receives a new message, Redis needs to send a request to calculate the number of unread messages, and the write throughput is lower |
| Relationship with unread messages | `Unread messages` and `Number of unread messages` both take the terminal as the dimension, and the client sends the local message to the service through the above-mentioned client to confirm the last confirmation time to obtain this time point The number of "unread" messages and "unread" messages after that. <br />Therefore, the `unread message` and `unread message number` obtained by different terminals may be inconsistent | `unread message` still takes the terminal as the dimension, but `unread message number` takes the user as the dimension . If message A is "read" on the desktop side, the mobile phone side can still consider it "unread", but the number of unread messages pushed to all clients of the user is uniformly reduced by 1<br />So the different ends get `Unread Messages` may be inconsistent, but `Unread Message Count` is consistent |
| Supplement                        | As mentioned above, this solution can actually "forcibly" support the number of unread messages when pushing offline messages. <br />But because this solution is not designed for frequently reading the number of unread messages, if the server calculates the number of unread messages in real time every time a message is pushed, its performance is obviously not advisable. Therefore, it is not supported in practice | The above solutions have their own advantages and disadvantages, and which solution to use depends on the business requirements of the specific application. If you do not need to support offline message push and carry the number of unread messages, use the solution on the left, and if you need to support it, use the solution on the right. <br />If the customer has additional requirements on the basis of these two solutions, they need to do secondary development by themselves<br />TODO: This implementation will be supported in the near future |

#### Implementation

TODO

### About the implementation of offline push

For online users, developers can use the notification attribute to configure whether to allow the server to actively push messages to online users (the default is true). For offline users, the implementation of offline push usually needs to use the push SDK provided by the mobile phone operator to perform offline push through its channel.

However, since Turms itself does not connect to any operator and does not plan to connect, you need to implement custom offline push logic through the `NotificationHandler` plug-in. The Handler provides a handle function and accepts four parameters: message information, online user ID, offline user ID, and optional number of unread messages. You can use this function to call the push SDK provided by the manufacturer to implement offline push logic .

### Message batch pull

TODO: Not supported yet. Since message fetching is controlled by the client itself, this feature can be easily implemented efficiently and flexibly, and we will provide support before the official release.

### extra large group

It is not difficult to implement a very large group, but its business requirements and scenarios are very different from those of general social applications, so a set of special strategies is required to support very large groups.

Strategy (TODO)

1. Messages are sent according to priority
2. Intelligently limit the peak value of messages, and actively discard messages according to the server status and message priority
3. Send messages in buckets (subgroups)
4. Message roaming is usually not required