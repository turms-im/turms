# turms-client-js Shared Context

## Background

Since the Turms server does not support and does not plan to support: a user establishes multiple sessions at the same time on the same platform. Therefore, if a user opens multiple tabs in the browser and tries to log in with **the same user ID and device type**, then there is one and only one session that can be successfully established. From the perspective of the browser, there is one and only one tab page that can log in successfully. This scenario is suitable for general social applications.

## Application Scenarios

However, some instant messaging scenarios require support for: from a user's perspective, the user only needs to log in once on one page, so that clients in other tabs are also logged in. The Turms clients in all tabs should be able to send and receive requests, messages, and notifications with the same user identity. It is suitable for scenarios such as customer service systems.

To support the above scenarios, a `Shared Context` needs to be used. Specifically, for Turms clients of the same domain (same protocol; same domain name; same port), same user ID, and same device type in different tabs, they can share the WebSocket connection with the Turms server and logged-in user information.

Note: Only Turms clients with the same domain, user ID, and device type can share context. Therefore, your client can log in with different user identities in different tabs to support features such as "some tabs share A user's session, while others share B user's session."

## Usage

turms-client-js does not enable the shared context by default, but if your application needs to use this feature, you can enable it by passing the parameter `useSharedContext: true` when creating a `TurmsClient` instance as follows:

```javascript
var client = new TurmsClient({
     useSharedContext: true
});
```

If `useSharedContext` is `true`, but the user's browser does not support `Shared Web Workers`, `new TurmsClient()` will directly throw an error.

If you want to know in advance whether the current browser supports shared context, you can call: `TurmsClient.isSharedContextSupported()`, this method returns a `boolean` value, `true` means support, `false` means no support.

Precautions:

* If the `Shared Context` is enabled, then you will no longer be able to directly call the `client.driver.connectionService#connect` method by yourself (usually, users do not need to directly call the underlying method). Other than that, you don't need to modify other logic codes to adapt the shared context.

## Supported Browser Versions

Since turms-client-js uses `Shared Web Workers` to implement shared context, the version requirements are the same as [Shared Web Workers](https://caniuse.com/sharedworkers).

## Code Example

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