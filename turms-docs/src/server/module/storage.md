# Storage Service

Turms itself does not directly provide storage services, but opens common interfaces in storage services on the server side for developers to implement themselves, and the Turms client also provides the corresponding storage service `turmsClient.storageService` API , for developers to call by themselves.

Notice:

* Developers can completely implement a set of interactive storage logic between the application client and your own server without any interface provided by the Turms client and server. Turms just maintains a set of implementations of common storage services, so that most developers don't have to develop from scratch. Even if the developer does not intend to use Turms' storage implementation, since each storage service implementation is similar, developers can refer to the Turms storage implementation process to implement their own storage logic to save time for self-development.
* The function provided by the Turms client storage service is a superset of the functions of the official storage service plug-in of the Turms server, namely: the Turms client storage service is designed to interact with the official storage service plug-in of the Turms server, and can also be extended to interact with other third-party plugins to interact.

## Plugin interface and configuration

Storage resources are currently divided into three types, namely: `User Profile Picture` (user profile picture), `Group Profile Picture` (group profile picture) and `Message Attachment` (message attachment). Each resource has its corresponding three function interfaces for adding (modifying), deleting, and checking for developers to implement.

### interface

Plugin interface: `im.turms.service.infra.plugin.extension.StorageServiceProvider`

Interface function introduction:

| Resource type | Function name | Expected function | Return value description |
| ------------ | ----------------------------------------------------- | -------------------------------- | ------------------------------------------------------------- |
| User Profile Picture | deleteUserProfilePicture | Delete User Profile Picture | |
| | queryUserProfilePictureUploadInfo | Query user profile picture upload information | The return value format is `Map<String, String>`, the plug-in implementer can customize any return value |
| | queryUserProfilePictureDownloadInfo | Query user profile picture download information | The return value format is `Map<String, String>`, the plug-in implementer can customize any return value |
| Group Profile Picture | deleteGroupProfilePicture | Delete Group Profile Picture | |
| | queryGroupProfilePictureUploadInfo | Query group profile picture upload information | The return value format is `Map<String, String>`, plug-in implementers can customize any return value |
| | queryGroupProfilePictureDownloadInfo | Query group profile picture download information | The return value format is `Map<String, String>`, plug-in implementers can customize any return value |
| message attachment | deleteMessageAttachment | delete message attachment | |
| | shareMessageAttachmentWithUser | share message attachment with specified user | |
| | shareMessageAttachmentWithGroup | Share the message attachment with the specified group | |
| | unshareMessageAttachmentWithUser | No longer share the message attachment with the specified user | |
| | unshareMessageAttachmentWithGroup | No longer share the message attachment with the specified group | |
| | queryMessageAttachmentUploadInfo | Query message attachment upload information | The return value format is `Map<String, String>`, and plug-in implementers can customize any return value |
| | queryMessageAttachmentUploadInfoInPrivateConversation | Query message attachment upload information in a private chat session | |
| | queryMessageAttachmentUploadInfoInGroupConversation | Query message attachment upload information in a group chat session | |
| | queryMessageAttachmentDownloadInfo | Query message attachment download information | The return value format is `Map<String, String>`, and plug-in implementers can customize any return value |
| | queryMessageAttachmentInfosUploadedByRequester | Query the message attachment uploaded by the requester | |
| | queryMessageAttachmentInfosInPrivateConversations | Query message attachments in private chat sessions | |
| | queryMessageAttachmentInfosInGroupConversations | Query message attachments in group chat conversations | |
### General configuration

| Configuration item | Default value | Description |
| ------------------------------------------------------------- | ------ | --------------------------------------------------- |
| turms.service.storage.user-profile-picture.expire-after-days | 0 | The expiration time (in days) of the resource since the creation time. A value of 0 means no expiration |
| turms.service.storage.user-profile-picture.allowed-referrers | Empty | Only allow specified Referrers to access resources |
| turms.service.storage.user-profile-picture.allowed-content-type | `*/*` | Allowed resource `Content-Type` for upload. `*/*` values represent unlimited |
| turms.service.storage.user-profile-picture.min-size-bytes | 0 | The minimum size of resources allowed to be uploaded. A value of 0 means unlimited |
| turms.service.storage.user-profile-picture.max-size-bytes | 1MB | The maximum size of resources allowed to be uploaded. A value of 0 means unlimited |
| turms.service.storage.user-profile-picture.download-url-expire-after-seconds | 300 | Expiration time of resource download URL (seconds) |
| turms.service.storage.user-profile-picture.upload-url-expire-after-seconds | 300 | Expiration time of resource upload URL (seconds) |
| turms.service.storage.group-profile-picture.... | | Same as turms.service.storage.user-profile-picture |
| turms.service.storage.message-attachment.... | | Same as turms.service.storage.user-profile-picture |

## Official plugin implementation

### Bucket Basic Design Guidelines

Since the functions provided by the object storage service are similar, the official plug-ins based on the object storage service provided by Turms at present and in the future will follow the following Bucket design guidelines.

As mentioned above, Turms currently includes three types of storage resources, namely `User Profile Picture` (user profile picture), `Group Profile Picture` (group profile picture) and `Message Attachment` (message attachment), which correspond to The bucket names are `user-profile-picture`, `group-profile-picture` and `message-attachment` respectively. in:

* `user-profile-picture` and `group-profile-picture` are public Buckets. For the URLs of these resources, Turms not only supports the generation of regular URLs to allow the client to predict resource URLs by itself, avoiding sending requests to the Turms server to query resource URLs, but also supports the generation of irregular URLs for anti-crawlers. Which URL your application needs to use depends on your product requirements.
* `message-attachment` is a private Bucket that provides authorized users with a URL for temporary access to message attachments through Presigned URLs.
* The upload process of all resources is based on providing authorized users with a temporary Multipart Upload interface through the Presigned URL.

Of course, the above are only the default configurations. The current mainstream object storage services support many practical features, such as separate storage of hot and cold data (such as Amazon S3 Intelligent-Tiering Storage Class), encryption, complex permission control, etc., users can create in Turms On the basis of Buckets, further configuration is performed through the object storage service.

###turms-plugin-minio

#### Introduction

turms-plugin-minio is a turms-service storage service implementation plugin developed based on the open source object storage service [MinIO](https://min.io).

#### Install

* [Download and installation of MinIO server](https://min.io/download)
* [How to load the plugin](https://turms-im.github.io/docs/server/development/plugin#%E6%8F%92%E4%BB%B6%E5%8A%A0%E8%BD%BD%E6%96%B9%E5%BC%8F)

After the plug-in is `Started` on the server side, the client can call the corresponding API under `turmsClient.storageService` to add, delete, modify, and query storage resources.

#### Precautions when the client calls the storage-related interface

Since the storage interface of the Turms client adopts a general-purpose interface design and is not customized for turms-plugin-minio, you need to pay attention to the following when calling the client API:

* When calling the `queryMessageAttachment` interface, the parameter `fetchDownloadInfo` must be `true`; when calling the `queryMessageAttachmentDownloadInfo` interface, the parameter `fetch` must be `true`.

#### Business functions

##### Message attachment function

###### Upload message attachment
| Features | Support |
| ------------------------------ | ---- |
| Do not specify any session, upload message attachment | TODO |
| Upload message attachments to a specific private chat session | ✔ |
| Upload message attachments to multiple private chat sessions | |
| Upload message attachments to a specific group chat session | ✔ |
| Upload message attachments to specified multiple group chat sessions | |

###### Delete message attachment

| Features | Support |
| ------------------------ | ---- |
| Delete message attachments in any conversation | TODO |

###### Share and Unshare

| Features | Support |
| ------------------------------------------------------- | ---- |
| Share uploaded message attachments to a single private chat session | ✔ |
| Share uploaded message attachments to multiple private chat sessions | |
| Share uploaded message attachments to a single group chat session | ✔ |
| Share uploaded message attachments to multiple group chat sessions | |
| Cancel sharing of uploaded message attachments to a single private chat session | TODO |
| Unshare uploaded message attachments to multiple private chat sessions | |
| Share uploaded message attachments to a single group chat session | TODO |
| Share uploaded message attachments to multiple group chat sessions | |

For more advanced sharing functions, such as detailed permission control, custom sharing duration, encrypted sharing and other functions, there is no plan to support them in the near future.

##### Inquire

| Features | Support |
| ------------------------------------------------------------- | ------------------------------------------------------------ |
| Specify the attachments that the other party shared with me in a single private chat session | ✔ |
| Specify the attachments I send to the other party in a single private chat session | ✔ |
| Specify the attachments that the other party shared with me and the attachments I sent to the other party in a single private chat session | ✔ |
| Specify the attachments that the other party shared with me in multiple private chat sessions | |
| Specify the attachments I send to each other in multiple private chat sessions | |
| Specify the attachments shared by the other party to me and the attachments I send to the other party in multiple private chat sessions | |
| Attachments shared to me in all private chat sessions | |
| The attachments I sent to the other party in all private chat sessions | Does not support "only query the attachments I sent to the other party in the private chat session", but supports "in all sessions, the attachments I shared" |
| In all private chat sessions, the attachments shared by the other party to me and the attachments I sent to the other party | |
| | |
| Specify attachments shared by a single user (can be myself) in a single group chat session | ✔ |
| Specify attachments shared by multiple users (including myself) in a single group chat session | ✔ |
| Specifies attachments shared by all users (including myself) in a single group chat session | ✔ |
| Specify attachments shared by a single user (can be myself) in multiple group chat sessions | |
| Specify attachments shared by multiple users (including myself) in multiple group chat sessions | |
| Specify attachments shared by all users (including myself) in multiple group chat sessions | |
| In all group chat sessions, specify the attachments shared by a single user | Does not support "In all group chat sessions, specify the attachments I share", but supports "In all sessions, the attachments I share" |
| In all group chat sessions, specify attachments shared by multiple users (can include myself) | |
| Attachments shared by all users (including myself) in all group chat sessions | |
| | |
| Across all conversations, my shared attachments | ✔ |
| Across all sessions, various other query objects | |
#### Permission Control

* View message attachments

  * Regardless of whether users who send message attachments log out of the private chat or group chat session, they always have the right to query the message attachments they uploaded.

    And even if the user who uploaded the message attachment exits the session, all other users in the session still have the right to view the message attachment uploaded by the user.

  * Users have and can only view message attachments shared by other users in the private chat or group chat session they have joined. In other words, if a user joins a session and then logs out, the logged out user cannot view attachments in that session. Only when the user joins the session again can he have the right to view the attachments in the session again.

#### Safety

Upload limit: TODO

#### Store file data verification

If the data verification of stored files is implemented based on cloud services, the implementation of the logic will be relatively simple. For example, on AWS, you can trigger a custom Lambda function to verify the data uploaded by the user through the S3 event notification, or add a Lambda@Edge function that listens to the `origin-response` event on the CloudFront side for verification, except The custom verification logic needs to write some codes, and other functions can basically be realized by clicking the mouse.

However, as an independent storage service, MinIO does not support serverless architecture features such as Lambda functions. Compared with serverless solutions, it is much more troublesome to implement low-cost and highly available data verification logic based on MinIO's event mechanism. Therefore, Turms does not currently support data verification of stored files. Support will follow.

#### Configuration

| Configuration item | Default value | Description |
| --------------------------------------------------- | -------------------------- | ---------------------------------------------------------------- |
| turms-plugin.minio.enabled | true | Whether to enable the plugin |
| turms-plugin.minio.endpoint | "http://localhost:9000" | MinIO server address |
| turms-plugin.minio.region | "" | MinIO server region |
| turms-plugin.minio.access-key | minioadmin | Access Key for MinIO server |
| turms-plugin.minio.secret-key | minioadmin | Secret Key of MinIO server |
| turms-plugin.minio.retry.enabled | true | Whether to retry when initialization of Buckets fails |
| turms-plugin.minio.retry.initial-interval-millis | 30_000 | Initial retry interval when buckets initialization fails |
| turms-plugin.minio.retry.interval-millis | 30_000 | The retry interval when initializing Buckets fails |
| turms-plugin.minio.retry.max-attempts | 3 | When buckets initialization fails, the maximum number of retries |
| turms-plugin.minio.resource-id.mac.enabled | false | Whether to encrypt the Object Key of the resource with the MAC algorithm to generate unpredictable URLs to prevent crawlers. <br />If this item is not enabled, the user can obtain the corresponding image URL through the user ID or group ID<br />The final resource URL is: `<bucket>/<base62(object key)><base62( mac(object key))>`. Such as `user-profile-picture/123456789` => `user-profile-picture/8M0kX1aEllpuvXRV09grkIEtD4R`<br />Note: If the MAC algorithm is enabled, the client must pass the parameter `fetch` when calling `queryXXXDownloadInfo` series interfaces Set to `true`; when calling `queryXXX` series interfaces, the parameter `fetchDownloadInfo` must be set to `true` |
| turms-plugin.minio.resource-id.mac.base64-key | "AHR1cm1zLWltL3R1cm1zgA==" | Base64 encoded MAC algorithm key |
| turms-plugin.minio.resource-id.base62.enabled | false | Whether to encode the Object Key of the resource with Base62 algorithm to shorten the length of the URL. <br />The final resource URL is: `<bucket>/<base62(object key)>`, or `<bucket>/<base62(object key)><base62(mac(object key))>`. Such as `user-profile-picture/123456789` => `message-attachment/8M0kX` or `user-profile-picture/8M0kX1aEllpuvXRV09grkIEtD4R`<br />Note: 1. When `turms-plugin.minio.resource-key.mac When .enabled` is `true`, the Base62 algorithm will always be applied. <br />2. If the Base62 algorithm is enabled, the client must set the parameter `fetch` to `true` when calling the `queryXXXDownloadInfo` series interface; when calling the `queryXXX` series interface, it must set the parameter `fetchDownloadInfo` set to `true` |
| turms-plugin.minio.resource-id.base62.charset | ... | Character set for Base62 algorithm |