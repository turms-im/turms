# Version Requirements

The minimum requirements for the version of the Turms client are mainly based on three factors: the global market share of the platform, the minimum supported version of TLSv1.2 on the platform, and the elegance of code implementation. In addition, Turms does not provide official support for obsolete protocols such as TLSv1 and TLSv1.1.

| Platform | Minimum supported version                                    | Reason                                                       |
| -------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| Android  | 21+                                                          | Considering the market share of 21+ and the elegance of code implementation, it supports 21+ |
| iOS      | 12.0+                                                        | Considering the global market share of [iOS 12.0+](https://developer.apple.com/support/app-store/) and the habits of Apple product users, turms-client-swift adopts `NWConnection` to implement the TCP protocol, so the device version requirements are equivalent to those of devices supporting `NWConnection`. <br />In addition, turms-client-swift will not consider using the ancient `CFStreamCreatePairWithSocketToHost` to implement the TCP protocol. |
| Browser  | [Browser that supports WebSocket protocol](https://caniuse.com/?search=websocket) | For IE browsers, turms-client-js only provides official support for IE 11. <br />Also, turms-client-js will not downgrade WebSocket to polling. |
| Desktop  | turms-client-kotlin(JDK8+)<br />turms-client-js(Node.js 8+)  | If you use turms-client-kotlin, the JDK version is required to be 8(+), because JDK 8+ provides support for TLSv1.2 by default. <br />Turms provides official support for Node.js 8+ if you use turms-client-js. |

Note:

* turms-client-kotlin uses `Socket` instead of `SocketChannel`. The main reason is that the Android SDK does not provide a set of standard TLS protocol implementations for `SocketChannel`, which needs to be implemented by itself. Considering the variety of Android systems and the limited system functions (especially compared to server-side implementations), self-implementation of the TLS protocol can easily lead to various unexpected bugs, so use `Socket` to implement the official TLS protocol .