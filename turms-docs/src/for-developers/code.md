# 源码

本文讲解服务端各主要功能模块的大致源码实现，以帮忙开发者更好地理解相关流程。

注意事项：

1. Turms服务端重度使用[reactor-core](https://projectreactor.io/docs/core/release/reference)这一响应式框架，本文默认读者已经熟练掌握响应式编程，如果读者还没掌握响应式编程，则建议先自行学习并掌握[reactor-core](https://projectreactor.io/docs/core/release/reference)。
1. Turms会不定期优化代码，因此一些函数名或函数实现可能会稍微改变，但其思想是不会变的。
2. 各模块源码所做的事情通常比下文讲得多得多，但为了方便读者理解，**本文只挑选主要流程进行讲解，并略去了大量细节**。如果读者对其中的细节感兴趣，可以在阅读完本文的相关讲解，并对主要流程有大概的认识后，再去阅读源码，了解其具体实现细节。

## 客户端请求处理

阅读下文前，建议读者先行阅读[客户端访问服务端标准流程](https://turms-im.github.io/docs/for-developers/architecture.html#%E5%AE%A2%E6%88%B7%E7%AB%AF%E8%AE%BF%E9%97%AE%E6%9C%8D%E5%8A%A1%E7%AB%AF%E6%A0%87%E5%87%86%E6%B5%81%E7%A8%8B)，先从架构角度理解其背后的设计思路，这样在读源码的时候就不容易“迷路”。

### turms-gateway

简介：用于维护与客户端的网络连接，维护应用层会话，并将大部分的业务请求下发给turms-service服务端。

#### 网络层配置

1. 启动接收客户端请求的服务端

   TCP服务端：`im.turms.gateway.access.tcp.factory.TcpServerFactory#create`

   WebSocket服务端：`im.turms.gateway.access.websocket.factory.WebSocketFactory#create`

   这两个函数主要做的就是：基于reactor-netty，绑定监听地址、配置EventLoop线程池、（可选）配置SSL、开启相关度量等常规工作。

2. 对于纯TCP连接（而非预备的WebSocket连接），给新确立的TCP连接绑上编解码Handlers

   在`im.turms.gateway.access.tcp.factory.TcpServerFactory#create`函数内，通过下述回调，给新TCP连接绑上对应的`TurmsRequest`与`TurmsNotification`的编解码实例。

   ```java
   .doOnConnection(handlerConfig::configureConnection);
   ```

   其中，`configureConnection`函数来自：`im.turms.gateway.access.tcp.handler.TcpHandlerConfig`

   ```java
void configureConnection(Connection connection) {
       connection.addHandlerLast("varintLengthBasedFrameDecoder", CodecFactory.getExtendedVarintLengthBasedFrameDecoder(maxFrameLength));
       connection.addHandlerLast("varintLengthFieldPrepender", CodecFactory.getVarintLengthFieldPrepender());
       connection.addHandlerLast("protobufFrameEncoder", CodecFactory.getProtobufFrameEncoder());
}
   ```

3. 对于WebSocket连接，则先监听并校验HTTP Upgrade请求，然后再Upgrade成WebSocket连接

   在`im.turms.gateway.access.websocket.factory.WebSocketFactory#create`函数内，通过下述代码绑定HTTP监听回调：

   ```
   .handle(getHttpRequestHandler(handler, serverSpec))
   ```

   其中`getHttpRequestHandler`函数来自`im.turms.gateway.access.websocket.factory.WebSocketFactory#getHttpRequestHandler`，该回调用于校验HTTP请求，如果监听到是合法的HTTP Upgrade请求，则将该连接升级为WebSocket连接。

至此，纯网络层的操作基本就完成了，下一步就是衔接网络层与业务逻辑层。

#### 网络层与业务逻辑层的衔接

1. 网络层与业务逻辑层，通过函数接口：`im.turms.gateway.access.common.function.ConnectionHandler#handle`进行绑定，主要绑定的内容就是：输入字节流、输出字节流、连接关闭时的回调函数。

   对于TCP服务端，在`im.turms.gateway.access.tcp.factory.TcpServerFactory#create`函数下，通过`.handle((in, out) -> handler.handle((Connection) in, false, in.receive(), out, ((Connection) in).onDispose()))`进行绑定。

   对于WebSocket服务端，在`im.turms.gateway.access.websocket.factory.WebSocketFactory#create`函数下，通过`handler.handle((Connection) in, true, inbound, out, onClose)`绑定。

2. 上述`handler.handle`会调用下述的`im.turms.gateway.access.common.UserSessionDispatcher#bindConnectionWithSessionWrapper`回调函数，用于协调处理输入字节流与输出字节流的逻辑，而从全局视角来看，其本质就是上层业务层的`请求与响应`、`通知`这两套逻辑，其源码如下：

   ```java
   ConnectionHandler bindConnectionWithSessionWrapper() {
       return (connection, isWebSocketConnection, in, out, onClose) -> {
           InetSocketAddress address = (InetSocketAddress) connection.address();
           NetConnection netConnection = NetConnection.create(connection);
           UserSessionWrapper sessionWrapper = new UserSessionWrapper(netConnection, address, closeIdleConnectionAfterSeconds,
                   userSession -> userSession.setNotificationConsumer((turmsNotificationBuffer, tracingContext) -> {
                       turmsNotificationBuffer.touch(turmsNotificationBuffer);
                       NettyOutbound outbound = isWebSocketConnection
                               ? out.sendObject(new BinaryWebSocketFrame(turmsNotificationBuffer))
                               : out.sendObject(turmsNotificationBuffer);
                       Mono.from(outbound)
                               .subscribe(null, t -> handleConnectionError(t, netConnection, userSession, tracingContext));
                   }));
           respondWithRequests(connection, isWebSocketConnection, in, out, sessionWrapper);
           return tryRemoveSessionInfoOnConnectionClosed(onClose, sessionWrapper);
       };
   }
   ```

   其中，`userSession.setNotificationConsumer`用于监听`通知`，并向客户端发送`通知`输出字节流；而`respondWithRequests`用于监听`请求`输入字节流，并对返回对应的`响应`输出字节流，该函数源码如下：

   ```java
   void respondWithRequests(Connection connection,
                            boolean isWebSocketConnection,
                            Flux<ByteBuf> in,
                            NettyOutbound out,
                            UserSessionWrapper sessionWrapper) {
       in
               .doOnNext(requestData -> {
                   if (connection.isDisposed()) {
                       return;
                   }
                   requestData.retain();
                   TracingContext ctx = new TracingContext();
                   clientRequestDispatcher.handleRequest(sessionWrapper, requestData)
                           .onErrorResume(throwable -> {
                               ctx.updateThreadContext();
                               handleNotificationError(throwable, sessionWrapper.getUserSession());
                               return Mono.empty();
                           })
                           .flatMap(turmsNotificationBuffer -> {
                               NettyOutbound outbound = isWebSocketConnection
                                       ? out.sendObject(new BinaryWebSocketFrame(turmsNotificationBuffer))
                                       : out.sendObject(turmsNotificationBuffer);
                               return Mono.from(outbound);
                           })
                           .contextWrite(context -> context.put(TracingContext.CTX_KEY_NAME, ctx))
                           .doFinally(signal -> ctx.clearThreadContext())
                           .subscribe(null, t -> handleConnectionError(t, sessionWrapper.getConnection(),
                                   sessionWrapper.getUserSession(), ctx));
               })
               .then()
               .subscribe(null, t -> handleConnectionError(t, sessionWrapper.getConnection(),
                       sessionWrapper.getUserSession(), TracingContext.NOOP));
   }
   ```

   其中，`respondWithRequests`在`clientRequestDispatcher.handleRequest(sessionWrapper, requestData)`处，将`请求`的字节流`ByteBuf`，递交给上层的业务逻辑层进行处理；在`.flatMap(turmsNotificationBuffer -> {`回调处，将请求的`响应`字节流进行输出。

   至此，网络层的工作就结束了，接下来就都是业务逻辑层相关操作。

#### 业务层——请求调度层

经由网络层的操作，来到了`im.turms.gateway.access.common.ClientRequestDispatcher#handleRequest`。该函数完成：调度心跳请求请求、业务请求；简单校验请求，如果是非法请求，则尝试拉黑等。

该函数的代码虽多，但我们这里主要关注的是`handleServiceRequest(sessionWrapper, request, serviceRequestBuffer, tracingContext)`这行代码，`handleServiceRequest`函数的主要源码如下：

```java
return switch (requestType) {
    case CREATE_SESSION_REQUEST -> sessionController
            .handleCreateSessionRequest(sessionWrapper, request.createSessionRequest())
            .map(result -> getNotificationFromHandlerResult(result, request.requestId()));
    case DELETE_SESSION_REQUEST -> sessionController.handleDeleteSessionRequest(sessionWrapper);
    default -> {
        serviceRequestBuffer.retain();
        yield handleServiceRequestForTurms(sessionWrapper, request, serviceRequestBuffer);
    }
};
```

* 将`CREATE_SESSION_REQUEST`与`DELETE_SESSION_REQUEST`这两个turms-gateway能自行处理的请求交由Controller层进行处理，即`im.turms.gateway.access.common.controller.SessionController`，该Controller主要就是借由`im.turms.gateway.service.impl.session.SessionService`与Redis服务端交互，完成客户端的相关`登陆`与`登出`。由于该逻辑并非本篇的重点，这里就不展开讲了。等这Controller与Service的逻辑都处理完，则返回一个`TurmsNotification`对象，并借由上述的编码流程，最终将字节数据发送给客户端。

* 对于其他所有请求，turms-gateway通过`handleServiceRequestForTurms`函数，最终经由RPC，将客户端请求下发给turms-service进行处理。大致调用流程如下：经过几层简单的调用，来到`im.turms.gateway.service.impl.message.InboundRequestService#processServiceRequest0`，该函数会调用`im.turms.gateway.service.impl.message.InboundRequestService#sendServiceRequest`通过自研RPC框架，将包装有客户端请求字节数据的RPC请求`im.turms.server.common.rpc.request.HandleServiceRequest`下发给turms-service进行处理。其中，具体RPC的实现并非本篇重点，这里就不展开讲了。等turms-service处理完请求，会返回一个`im.turms.server.common.dto.ServiceResponse`，该对象会在上述的`im.turms.gateway.service.impl.message.InboundRequestService#processServiceRequest0`函数的代码：

  ```java
  return sendServiceRequest(serviceRequest)
          .defaultIfEmpty(REQUEST_RESPONSE_NO_CONTENT)
          .map(response -> getNotificationFromResponse(response, serviceRequest.getRequestId()));
  ```

  转换为`TurmsNotification`，并借由上述的编码流程，最终将字节数据发送给客户端。

至此，turms-gateway的客户端请求的处理逻辑就讲解完了，下文主要讲解turms-service是如何处理上游turms-gateway发来的RPC请求的。

## turms-service

（RPC实现属于“集群服务”实现内容，这里不做相关讲解）

1. 请求调度层

   经由RPC层的处理，turms-service会首先通过`im.turms.service.workflow.access.servicerequest.dispatcher.ServiceRequestDispatcher#dispatch`拿到客户端请求的字节数据。该函数会调用`im.turms.service.workflow.access.servicerequest.dispatcher.ServiceRequestDispatcher#dispatch0`函数，完成诸如：请求校验、客户端拉黑、判断服务端监控状态、触发插件与调用Controller层接口函数、触发上游等任务。代码虽多，但其实还是比较易读的，这里我们主要看到其中的`result = handler.handle(lastClientRequest);`这行代码，该`handler#handle`其实是`im.turms.service.workflow.access.servicerequest.dispatcher.ClientRequestHandler#handle`，而`handle`函数的实现，其实就是各Controller层接口的实现

2. 请求Controller层
   
   各Controller通过上述的`handle`函数，拿到了传来的`im.turms.service.workflow.access.servicerequest.dto.ClientRequest`对象后，就开始执行相关的业务逻辑，并向MongoDB服务端发送各种CRUD请求。这块内容并非本篇重点，这里就不展开讲解了。等Controller层处理完相关业务逻辑，就会返回一个`im.turms.service.workflow.access.servicerequest.dto.RequestHandlerResult`对象。简单来说，该对象描述了：要发回给客户端的`响应`，与要发给其他用户的`通知`（如发送群聊消息，对于消息的接收客户端，这些发送给它们的输出字节流就是`通知`）。
   
   对于`响应`，会借由上述的RPC操作，将字节数据发回给turms-gateway，而turms-gateway再通过上述已经提及过的`respondWithRequests`函数里的`.flatMap(turmsNotificationBuffer -> {`，最终将响应字节数据发送给客户端。

至此，一个请求就被处理完了。

## 通知下发

TODO

## 集群实现

### RPC

TODO