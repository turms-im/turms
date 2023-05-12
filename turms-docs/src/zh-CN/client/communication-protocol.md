# 与服务端通信时使用的数据格式

对于一般请求与响应而言：

* 基于纯TCP协议实现的客户端：varint编码的正文长度 + 正文（Protobuf编码的`TurmsNotification`或`TurmsRequest`）
* 基于WebSocket协议实现的客户端：正文（Protobuf编码的`TurmsNotification`或`TurmsRequest`）。正文的字节长度信息通过底层的WebSocket Frame传输

对于心跳请求而言：

* 基于纯TCP协议实现的客户端：一个长度为一字节的`[0]`字节数组。这里的数值`0`其实是指“该Payload的长度在varint编码下为一字节长度的0”，即Payload为0字节。
* 基于WebSocket协议实现的客户端：一个正文为空（0字节）的Binary类型消息

补充：Turms不通过WebSocket的PING/PONG来实现心跳的原因是：

* 各浏览器WebSocket实现的PING消息发送时间间隔不同
* 上层代码无法控制PING/PONG的行为，甚至无法感知行为的发生
* 网络层面的心跳逻辑不应该和应用层的心跳耦合