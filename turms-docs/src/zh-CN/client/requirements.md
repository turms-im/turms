# 版本要求

Turms客户端对版本的最低要求，主要是根据：平台全球市场占有率、平台TLSv1.2最低支持版本与代码实现的优雅程度，三个因素来考量。另外，Turms不提供对TLSv1与TLSv1.1等被时代淘汰协议的官方支持。

| 平台    | 支持的最低版本                                               | 原因                                                         |      |
| ------- | ------------------------------------------------------------ | ------------------------------------------------------------ | ---- |
| Android | 21+                                                          | 考虑到21+的市场占有率与代码实现优雅程度，故支持21+           |      |
| iOS     | 12.0+                                                        | 考虑到[iOS 12.0+在全球的市场占有率](https://developer.apple.com/support/app-store/)以及苹果产品用户的习惯，turms-client-swift采用NWConnection实现TCP协议，因此设备版本的要求等同于支持`NWConnection`设备的版本要求。<br />另外，turms-client-swift不会考虑用古老的`CFStreamCreatePairWithSocketToHost`来实现TCP协议。 |      |
| 浏览器  | [支持WebSocket协议的浏览器](https://caniuse.com/?search=websocket) | 对于IE浏览器，turms-client-js仅对IE 11提供官方支持。<br />另外，turms-client-js不会将WebSocket降级为轮询机制 |      |
| 桌面端  | turms-client-kotlin(JDK8+)<br />turms-client-js(Node.js 8+)  | 如果您采用turms-client-kotlin实现，则要求JDK版本为8(+)，因为JDK 8+默认提供对TLSv1.2的支持。<br />如果您采用turms-client-js实现，则Turms提供对Node.js 8+的官方支持 |      |

补充

* turms-client-kotlin采用的是`Socket`，而非`SocketChannel`。其中最主要的原因是：Android SDK不对`SocketChannel`提供一套标准的TLS协议实现，需要自行实现。考虑到安卓系统的五花八门且系统功能本身就比较受限（尤其相比服务端实现），自行实现TLS协议极易导致各种意料之外的Bugs，故使用`Socket`以采用官方的TLS协议实现。