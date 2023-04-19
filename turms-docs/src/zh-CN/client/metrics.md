# 度量数据

参考文档：[可观测性体系](https://turms-im.github.io/docs/zh-CN/server/module/observability#%E5%BA%A6%E9%87%8F)

## 网络连接度量

Turms各客户端会提供网络连接相关的度量数据。开发者可以通过`turmsClient.driver.connectionMetrics`获取该度量数据对象。该对象包含以下数据：

| 数据点名称          | 单位 | 含义                                                         |
| ------------------- | ---- | ------------------------------------------------------------ |
| addressResolverTime | 毫秒 | 域名解析用时。<br />turms-client-js不提供该数据              |
| connectTime         | 毫秒 | 对于非turms-client-js客户端，该数据指TCP握手用时；<br />对于turms-client-js客户端，该数据指域名解析、TCP握手、TLS握手、建立WebSocket连接的总用时 |
| tlsHandshakeTime    | 毫秒 | TLS握手用时。<br />turms-client-js/swift不提供该数据         |
| dataReceived        | 字节 | 对于非turms-client-js客户端，该数据指TCP接收到的数据字节数；<br />对于turms-client-js客户端，该数据指WebSocket连接接收到的Binary帧Payload数据字节数 |
| dataSent            | 字节 | 对于非turms-client-js客户端，该数据指TCP已发送的数据字节数；<br />对于turms-client-js客户端，该数据指WebSocket连接已发送的Binary帧Payload数据字节数 |

## 业务请求度量

TODO