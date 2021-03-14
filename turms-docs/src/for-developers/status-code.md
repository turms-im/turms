# 状态码

需要开发者了解的状态码有两种，一种是TurmsStatusCode，另一种是SessionCloseStatus。

## TurmsStatusCode

具体的状态码细节可查阅im.turms.common.TurmsStatusCode类。

### 类别

为方便记忆，Turms的业务状态码（Business status code）参考HTTP状态码设计：

* 2XXX。表明请求成功
* 3XXX。表明需要客户端进行重定向操作
* 4XXX。表明客户端错误
* 5XXX。表明服务端错误

## SessionCloseStatus

SessionCloseStatus在网络协议上的表现即为WebSocket的Close状态码。具体的状态码细节可查阅im.turms.common.constant.statuscode.SessionCloseStatus类。