# turms-client-js共享上下文

## 背景

由于Turms服务端不支持也没计划支持：一个用户在同一个端同时建立多个会话。因此如果一个用户在浏览器打开多个标签页，并试图以**相同的用户ID与设备类型**进行登陆时，那么有且仅有一个会话可以建立成功。从浏览器角度来看，就是有且仅有一个标签页能够登陆成功。该场景适用于一般的社交应用。

## 适用场景

但部分IM场景需要支持：从用户角度来看，用户只需在一个页面登陆一次，那么其他标签页也就处于已登陆状态了，在所有标签页里的Turms客户端都能以相同的用户身份，发送请求、接收消息与通知。适用于客服系统等场景。

为了支持上述场景，需要使用`共享上下文`。具体而言，对于在不同标签页的同域（同协议；同域名；同端口）、同用户ID且同设备类型的Turms客户端，它们可以共享与Turms服务端的WebSocket连接与一些已登陆用户的信息。

提醒：因为只有同域名、同用户ID且同设备类型的Turms客户端才共享上下文，因此您的客户端可以以不同的用户身份登陆不同的标签页，以支持类似“部分标签页共享A用户的会话，部分标签页共享B用户的会话”的特性。

## 使用方法

turms-client-js默认不开启共享上下文功能，而如果您的应用需要使用该功能，可以通过在创建`TurmsClient`实例时，传递一个`useSharedContext: true`开启。具体代码如下：

```javascript
var client = new TurmsClient({
    useSharedContext: true
});
```

如果`useSharedContext`为`true`，但用户的浏览器并不支持`Shared Web Workers`，则`new TurmsClient()`会直接抛错误。

如果您想要提前知道当前浏览器是否支持共享上下文，您可以调用：`TurmsClient.isSharedContextSupported()`，该方法返回一个`boolean`值，`true`即支持，`false`即不支持。

注意事项：

* 如果开启`共享上下文`功能，那么您将不再能自行直接调用`client.driver.connectionService#connect`方法（通常情况下，用户也不需要直接调用该底层方法）。除此之外，您无需为适配共享上下文功能修改其他逻辑代码。

## 支持的浏览器版本

由于turms-client-js采用`Shared Web Workers`实现共享上下文，因此版本要求同[Shared Web Workers](https://caniuse.com/sharedworkers)。

## 具体代码示例

```javascript
// On the first tab of the same origin
// The client will create a new WebSocket connection
var client = new TurmsClient({
    useSharedContext: true
});
client.userService.login({
    userId: 1,
    password: "123",
    deviceType: DeviceType.BROWSER
});

// On the second tab of the same origin
// The client will share the WebSocket connection with the first tab
var client = new TurmsClient({
    useSharedContext: true
});
client.userService.login({
    userId: 1,
    password: "123",
    deviceType: DeviceType.BROWSER
});

// On the third tab of the same origin
// The client will create a new WebSocket connection because it uses a new device type
var client = new TurmsClient({
    useSharedContext: true
});
client.userService.login({
    userId: 1,
    password: "123",
    deviceType: DeviceType.ANDROID
});
```