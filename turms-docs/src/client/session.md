# Session Lifecycle

The session lifecycle of the Turms client is relatively easy to understand. Specifically: first set up a connection on the network layer through `driver.connect(...)`, and then log in on the business level through `userService.login(...)` , after successful login, the corresponding session is established. Finally, the session close notification is sent to the server through the `userService.logout(...)` method, and the network layer connection is also closed.

In order to keep the logic simple, it is also convenient for upper-level developers to combine various logics by themselves. Turms does not provide operations such as automatic reconnection and automatic routing, because on one hand developers can easily implement such logic themselves, and on the other hand, such "hidden" internal logic can make it difficult for upper-level developers to control low-level driver behavior and can sometimes become a stumbling block.

Note: Similar to the session close mechanism based on the close frame in WebSocket, when Turms server closes a session, it also notifies the client that the session has been closed through a session close signal, and after the signal is flushed, it notifies the underlying WebSocket/TCP to close the connection. Turms server does not need to wait for any response from the client regarding the session close signal, and the client does not send any response to the server regarding the session close signal.

## Lifecycle Callback Hooks

| Layer                | Name                             | Invocation Timing                                            | Reminder                                                     |
| -------------------- | -------------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| Network layer        | driver.addOnConnectedListener    | When the network layer connection is established             | Usually you don't need to add connection event listeners through `addOnConnectedListener`,<br />but run custom code after the successful asynchronous execution of `driver.connect(...)`. |
| Network layer        | driver.addOnDisconnectedListener | When the network layer connection is disconnected            |                                                              |
| Business logic layer | userService.addOnOnlineListener  | When the session is established, i.e., when the user logs in | Usually you don't need to add online event listeners through `addOnOnlineListener`,<br />but run custom code after the successful asynchronous execution of `userService.login(...)`. |
| Business logic layer | userService.addOnOfflineListener | When the session is disconnected, i.e., when the user logs out |                                                              |