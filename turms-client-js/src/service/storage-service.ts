import unfetch from 'unfetch';
import { StorageResourceType } from '../model/proto/constant/storage_resource_type';
import Response from '../model/response';
import ResponseError from '../error/response-error';
import ResponseStatusCode from '../model/response-status-code';
import TurmsClient from '../turms-client';
import StorageResource from '../model/storage-resource';
import NotificationUtil from '../util/notification-util';
import StorageUpdateResult from '../model/storage-update-result';

const isResponseSuccessful = (res): boolean => res.statusText.startsWith('2');

export default class StorageService {
    private static readonly _RESOURCE_KEY_NAME = 'key';
    private static readonly _DEFAULT_URL_KEY_NAME = 'url';
    private static readonly _RESOURCE_TYPE_TO_BUCKET_NAME = Object
        .keys(StorageResourceType)
        .filter(value => value !== StorageResourceType[StorageResourceType.UNRECOGNIZED])
        .map(value => value.toLowerCase().replace(/_/g, '-'));
    private readonly _turmsClient: TurmsClient;
    private _serverUrl = 'http://localhost:9000';

    constructor(turmsClient: TurmsClient, storageServerUrl?: string) {
        this._turmsClient = turmsClient;
        if (storageServerUrl != null) {
            this._serverUrl = storageServerUrl;
        }
    }

    get serverUrl(): string {
        return this._serverUrl;
    }

    set serverUrl(serverUrl: string) {
        this._serverUrl = serverUrl;
    }

    // User profile picture

    public uploadUserProfilePicture({
        mediaType,
        data,
        urlKeyName
    }: {
        mediaType: string,
        data: Uint8Array,
        urlKeyName?: string
    }): Promise<Response<StorageUpdateResult>> {
        if (!data.length) {
            return Promise.reject(ResponseError.from({
                code: ResponseStatusCode.ILLEGAL_ARGUMENT,
                reason: 'The data of user profile picture must not be empty'
            }));
        }
        const userId = this._turmsClient.userService.userInfo?.userId;
        if (userId == null) {
            return Promise.reject(ResponseError.from({
                code: ResponseStatusCode.UPLOAD_USER_PROFILE_PICTURE_BEFORE_LOGIN
            }));
        }
        return this.queryUserProfilePictureUploadInfo().then(uploadInfo => {
            const url = StorageService._getAndRemoveResourceUrl(uploadInfo.data, urlKeyName);
            const resourceName = StorageService._getAndRemoveResourceKey(uploadInfo.data) ?? userId;
            return this._upload({
                url,
                formData: uploadInfo.data,
                resourceName,
                mediaType,
                data
            });
        });
    }

    public deleteUserProfilePicture(): Promise<Response<void>> {
        return this._deleteResource({
            type: StorageResourceType.USER_PROFILE_PICTURE
        });
    }

    public queryUserProfilePicture({
        userId,
        urlKeyName,
        fetchDownloadInfo
    }: {
        userId: string,
        urlKeyName?: string,
        fetchDownloadInfo?: boolean
    }): Promise<Response<StorageResource>> {
        return this.queryUserProfilePictureDownloadInfo({
            userId,
            fetch: fetchDownloadInfo,
            urlKeyName
        }).then(downloadInfo => {
            const url = StorageService._getAndRemoveResourceUrl(downloadInfo.data, urlKeyName);
            return this._queryResource(url);
        });
    }

    public queryUserProfilePictureUploadInfo(): Promise<Response<Record<string, string>>> {
        const userId = this._turmsClient.userService.userInfo?.userId;
        if (userId == null) {
            return Promise.reject(ResponseError.from({
                code: ResponseStatusCode.QUERY_USER_PROFILE_PICTURE_BEFORE_LOGIN
            }));
        }
        return this._queryResourceUploadInfo({
            type: StorageResourceType.USER_PROFILE_PICTURE
        });
    }

    public queryUserProfilePictureDownloadInfo({
        userId,
        fetch,
        urlKeyName
    }: {
        userId: string
        fetch?: boolean,
        urlKeyName?: string
    }): Promise<Response<Record<string, string>>> {
        if (fetch) {
            return this._queryResourceDownloadInfo({
                type: StorageResourceType.USER_PROFILE_PICTURE,
                keyNum: userId
            });
        }
        const url = `${this._serverUrl}/${StorageService._getBucketName(StorageResourceType.USER_PROFILE_PICTURE)}/${userId}`;
        const info = {};
        info[urlKeyName || StorageService._DEFAULT_URL_KEY_NAME] = url;
        return Promise.resolve(Response.value(info));
    }

    // Group profile picture

    public uploadGroupProfilePicture({
        groupId,
        mediaType,
        data,
        urlKeyName
    }: {
        groupId: string
        mediaType: string
        data: Uint8Array,
        urlKeyName?: string
    }): Promise<Response<StorageUpdateResult>> {
        if (!data.length) {
            return Promise.reject(ResponseError.from({
                code: ResponseStatusCode.ILLEGAL_ARGUMENT,
                reason: 'The data of group profile picture must not be empty'
            }));
        }
        return this.queryGroupProfilePictureUploadInfo({
            groupId
        })
            .then(uploadInfo => {
                const url = StorageService._getAndRemoveResourceUrl(uploadInfo.data, urlKeyName);
                const resourceName = StorageService._getAndRemoveResourceKey(uploadInfo.data) ?? groupId;
                return this._upload({
                    url,
                    formData: uploadInfo.data,
                    resourceName,
                    mediaType,
                    data
                });
            });
    }

    public deleteGroupProfilePicture({
        groupId
    }: {
        groupId: string
    }): Promise<Response<void>> {
        return this._deleteResource({
            type: StorageResourceType.GROUP_PROFILE_PICTURE,
            keyNum: groupId
        });
    }

    public queryGroupProfilePicture({
        groupId,
        fetchDownloadInfo,
        urlKeyName
    }: {
        groupId: string
        fetchDownloadInfo?: boolean,
        urlKeyName?: string
    }): Promise<Response<StorageResource>> {
        return this.queryGroupProfilePictureDownloadInfo({
            groupId,
            fetch: fetchDownloadInfo,
            urlKeyName
        }).then(downloadInfo => {
            const url = StorageService._getAndRemoveResourceUrl(downloadInfo.data, urlKeyName);
            return this._queryResource(url);
        });
    }

    public queryGroupProfilePictureUploadInfo({
        groupId
    }: {
        groupId: string
    }): Promise<Response<Record<string, string>>> {
        return this._queryResourceUploadInfo({
            type: StorageResourceType.GROUP_PROFILE_PICTURE,
            keyNum: groupId
        });
    }

    public queryGroupProfilePictureDownloadInfo({
        groupId,
        fetch,
        urlKeyName
    }: {
        groupId: string,
        fetch?: boolean,
        urlKeyName?: string
    }): Promise<Response<Record<string, string>>> {
        if (fetch) {
            return this._queryResourceDownloadInfo({
                type: StorageResourceType.GROUP_PROFILE_PICTURE,
                keyNum: groupId
            });
        }
        const url = `${this._serverUrl}/${StorageService._getBucketName(StorageResourceType.GROUP_PROFILE_PICTURE)}/${groupId}`;
        const info = {};
        info[urlKeyName || StorageService._DEFAULT_URL_KEY_NAME] = url;
        return Promise.resolve(Response.value(info));
    }

    // Message attachment

    public uploadMessageAttachment({
        messageId,
        mediaType,
        data,
        name,
        urlKeyName
    }: {
        messageId: string,
        mediaType: string,
        data: Uint8Array
        name?: string,
        urlKeyName?: string
    }): Promise<Response<StorageUpdateResult>> {
        if (!data.length) {
            return Promise.reject(ResponseError.from({
                code: ResponseStatusCode.ILLEGAL_ARGUMENT,
                reason: 'The data of message attachment must not be empty'
            }));
        }
        return this.queryMessageAttachmentUploadInfo({
            messageId,
            name
        }).then(uploadInfo => {
            const url = StorageService._getAndRemoveResourceUrl(uploadInfo.data, urlKeyName);
            const resourceName = StorageService._getAndRemoveResourceKey(uploadInfo.data)
                ?? (name == null ? messageId : `${messageId}/${name}`);
            return this._upload({
                url,
                formData: uploadInfo.data,
                resourceName,
                mediaType,
                data
            });
        });
    }

    public deleteMessageAttachment({
        messageId,
        name
    }: {
        messageId: string,
        name?: string
    }): Promise<Response<void>> {
        return this._deleteResource({
            type: StorageResourceType.MESSAGE_ATTACHMENT,
            keyNum: messageId,
            keyStr: name
        });
    }

    public queryMessageAttachment({
        messageId,
        name,
        fetchDownloadInfo,
        urlKeyName
    }: {
        messageId: string,
        name?: string,
        fetchDownloadInfo?: boolean,
        urlKeyName?: string
    }): Promise<Response<StorageResource>> {
        return this.queryMessageAttachmentDownloadInfo({
            messageId,
            name,
            fetch: fetchDownloadInfo,
            urlKeyName
        }).then(downloadInfo => {
            const url = StorageService._getAndRemoveResourceUrl(downloadInfo.data, urlKeyName);
            return this._queryResource(url);
        });
    }

    public queryMessageAttachmentUploadInfo({
        messageId,
        name
    }: {
        messageId: string,
        name?: string,
    }): Promise<Response<Record<string, string>>> {
        return this._queryResourceUploadInfo({
            type: StorageResourceType.MESSAGE_ATTACHMENT,
            keyStr: name,
            keyNum: messageId
        });
    }

    public queryMessageAttachmentDownloadInfo({
        messageId,
        name,
        fetch,
        urlKeyName
    }: {
        messageId: string,
        name?: string,
        fetch?: boolean,
        urlKeyName?: string
    }): Promise<Response<Record<string, string>>> {
        if (fetch) {
            return this._queryResourceDownloadInfo({
                type: StorageResourceType.MESSAGE_ATTACHMENT,
                keyStr: name,
                keyNum: messageId
            });
        }
        const url = `${this._serverUrl}/${StorageService._getBucketName(StorageResourceType.MESSAGE_ATTACHMENT)}/${name == null ? messageId : `${messageId}/${name}`}`;
        const info = {};
        info[urlKeyName || StorageService._DEFAULT_URL_KEY_NAME] = url;
        return Promise.resolve(Response.value(info));
    }

    // Base

    private _upload({
        url,
        formData,
        resourceName,
        mediaType,
        data
    }: {
        url: string,
        formData: Record<string, string>,
        resourceName: string,
        mediaType: string,
        data: Uint8Array
    }): Promise<Response<StorageUpdateResult>> {
        if (!data.length) {
            return Promise.reject(ResponseError.from({
                code: ResponseStatusCode.ILLEGAL_ARGUMENT,
                reason: 'The data of resource must not be empty'
            }));
        }
        const requestFormData = new FormData();
        for (const [key, value] of Object.entries(formData)) {
            requestFormData.set(key, value);
        }
        requestFormData.set('key', resourceName);
        requestFormData.set('Content-Type', mediaType);
        requestFormData.set('file', new Blob([data], { type: mediaType }));
        return unfetch(url, {
            method: 'POST',
            body: requestFormData
        })
            .catch(e => {
                throw ResponseError.from({
                    code: ResponseStatusCode.HTTP_ERROR,
                    reason: 'Caught an error while sending an HTTP POST request to update the resource',
                    cause: e
                });
            })
            .then(res => res.text()
                .catch(e => {
                    throw ResponseError.from({
                        code: ResponseStatusCode.INVALID_RESPONSE,
                        reason: 'Failed to get the response body as a string',
                        cause: e
                    });
                })
                .then(text => Response.value(new StorageUpdateResult(url, text))));
    }

    private _deleteResource({
        type,
        keyStr,
        keyNum
    }: {
        type: StorageResourceType,
        keyStr?: string,
        keyNum?: string
    }): Promise<Response<void>> {
        return this._turmsClient.driver.send({
            deleteResourceRequest: {
                type,
                keyStr,
                keyNum
            }
        }).then(n => Response.fromNotification(n));
    }

    private _queryResource(url: string): Promise<Response<StorageResource>> {
        return unfetch(url)
            .catch(e => {
                throw ResponseError.from({
                    code: ResponseStatusCode.HTTP_ERROR,
                    reason: 'Caught an error while sending an HTTP GET request to retrieve the resource',
                    cause: e
                });
            })
            .then(res => {
                if (isResponseSuccessful(res)) {
                    return res.blob();
                } else {
                    throw ResponseError.from({
                        code: ResponseStatusCode.HTTP_NOT_SUCCESSFUL_RESPONSE,
                        reason: `Failed to retrieve the resource because the HTTP response status code is: ${res.status}`
                    });
                }
            })
            .then(data => new Promise((resolve, reject) => {
                const reader = new FileReader();
                reader.onload = (e): void => {
                    const data = new Uint8Array(e.target.result as ArrayBuffer);
                    resolve(Response.value(new StorageResource(url, data)));
                };
                reader.onerror = (): void => {
                    reject(ResponseError.from({
                        code: ResponseStatusCode.INVALID_RESPONSE,
                        reason: 'Failed to get the response body as an array buffer',
                        cause: reader.error
                    }));
                };
                reader.readAsArrayBuffer(data);
            }));
    }

    private _queryResourceUploadInfo({
        type,
        keyStr,
        keyNum
    }: {
        type: StorageResourceType,
        keyStr?: string,
        keyNum?: string
    }): Promise<Response<Record<string, string>>> {
        return this._turmsClient.driver.send({
            queryResourceUploadInfoRequest: {
                type,
                keyStr,
                keyNum
            }
        }).then(n => Response.fromNotification(n, data => NotificationUtil.toMap(data.stringsWithVersion.strings)));
    }

    private _queryResourceDownloadInfo({
        type,
        keyStr,
        keyNum
    }: {
        type: StorageResourceType,
        keyStr?: string,
        keyNum?: string
    }): Promise<Response<Record<string, string>>> {
        return this._turmsClient.driver.send({
            queryResourceDownloadInfoRequest: {
                type,
                keyStr,
                keyNum
            }
        }).then(n => Response.fromNotification(n, data => NotificationUtil.toMap(data.stringsWithVersion.strings)));
    }

    private static _getBucketName(resourceType: StorageResourceType): string {
        return StorageService._RESOURCE_TYPE_TO_BUCKET_NAME[resourceType];
    }

    private static _getAndRemoveResourceKey(data: Record<string, string>): string | undefined {
        const name = data[StorageService._RESOURCE_KEY_NAME];
        delete data[StorageService._RESOURCE_KEY_NAME];
        return name;
    }

    private static _getAndRemoveResourceUrl(data: Record<string, string>, urlKeyName?: string): string {
        if (!urlKeyName) {
            urlKeyName = StorageService._DEFAULT_URL_KEY_NAME;
        }
        const url = data[urlKeyName];
        if (url == null) {
            throw ResponseError.from({
                code: ResponseStatusCode.INVALID_RESPONSE,
                reason: `Cannot get the resource URL because the key "${urlKeyName}" doesn't exist`
            });
        }
        delete data[urlKeyName];
        return url;
    }
}
