# Simultaneous Login

## Login device identification strategy

1. Manual configuration. The developer can manually configure the specific login device type when calling the `turmsClient.userService.login()` interface of the client. For an actual iOS device, specify to log in as `Android` (this operation is allowed). In addition, you can also manually configure the login device type as `UNKNOWN`.
2. Default configuration. If you do not manually configure the specific login device type, by default, each Turms client will automatically identify the current operating environment and specify the current login device type.
   * turms-client-js will determine whether the current operating environment is a browser or Node.js, if it is a browser, log in as `Browser`, otherwise log in as `Desktop`
   * turms-client-kotlin will judge whether the current operating environment is Android or desktop system, and log in as `Android` and `Desktop` respectively
   * turms-client-swift logs in as `iOS` by default

Related configuration class: `im.turms.server.common.infra.property.env.gateway.SessionProperties`

## Multi-terminal login type

"Device Type" means: Android, iOS, Desktop, Browser, Others, Unknown.
The following are common multi-device login types for you to quickly select and implement.

Configuration property: `im.turms.server.common.infra.property.env.gateway.SimultaneousLoginProperties#strategy`

Other related configurations: `allowDeviceTypeUnknownLogin`, `allowDeviceTypeOthersLogin`

| **self-contained type**                                      | corresponding Enum value                                     |
| :----------------------------------------------------------- | ------------------------------------------------------------ |
| Allow one device of each device type to be online at the same time | ALLOW_ONE_DEVICE_OF_EACH_DEVICE_TYPE_ONLINE                  |
| Allow only one device of one device type to be online at the same time | ALLOW_ONE_DEVICE_FOR_ALL_DEVICE_TYPES_ONLINE                 |
| Allow one device on Desktop and one device on mobile to be online at the same time | ALLOW_ONE_DEVICE_OF_DESKTOP_AND_ONE_DEVICE_OF_MOBILE_ONLINE  |
| Allow a device on Desktop or Browser to be online at the same time as a device on mobile | ALLOW_ONE_DEVICE_OF_DESKTOP_OR_BROWSER_AND_ONE_DEVICE_OF_MOBILE_ONLINE |
| Allow a device on the Desktop side, a device on the Browser side, and a device on the mobile phone side to be online at the same time | ALLOW_ONE_DEVICE_OF_DESKTOP_AND_ONE_DEVICE_OF_BROWSER_AND_ONE_DEVICE_OF_MOBILE_ONLINE |
| Allow one device on Desktop or Mobile to be online at the same time | ALLOW_ONE_DEVICE_OF_DESKTOP_OR_MOBILE_ONLINE                 |
| Allow one device on Desktop, Mobile or Browser to be online at the same time | ALLOW_ONE_DEVICE_OF_DESKTOP_OR_BROWSER_OR_MOBILE_ONLINE      |

**Notice**

- Any type of multi-terminal login does not allow a user account to have multiple simultaneous login devices on one device
- When the type of user login device has `Unkown` or `Others`, additional configuration is required, that is, whether to allow `Unkown`/`Others` devices to log in with other known devices at the same time

## Multi-terminal login conflict resolution strategy

Configuration property: `im.turms.server.common.infra.property.env.gateway.SimultaneousLoginProperties#loginConflictStrategy`

| **Type**                                    | Corresponding Enum value     |
| :------------------------------------------ | ---------------------------- |
| Offline devices that are already online     | DISCONNECT_LOGGED_IN_DEVICES |
| Refuse to go online with pre-logged devices | DISCONNECT_LOGGING_IN_DEVICE |