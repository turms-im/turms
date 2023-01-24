import unfetch from 'unfetch';
import { StorageResourceType } from '../model/proto/constant/storage_resource_type';
import Response from '../model/response';
import ResponseError from '../error/response-error';
import ResponseStatusCode from '../model/response-status-code';
import TurmsClient from '../turms-client';
import StorageResource from '../model/storage-resource';
import NotificationUtil from '../util/notification-util';
import StorageUpdateResult from '../model/storage-update-result';
import CollectionUtil from '../util/collection-util';
import DataParser from '../util/data-parser';
import Validator from '../util/validator';
import { ParsedModel } from '../model/parsed-model';

const isResponseSuccessful = (res): boolean => res.statusText.startsWith('2');

export default class StorageService {
    private static readonly _RESOURCE_ID_KEY_NAME = 'id';
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
        data,
        name,
        mediaType,
        extra,
        urlKeyName
    }: {
        data: Uint8Array,
        name?: string,
        mediaType?: string,
        extra?: Record<string, string>
        urlKeyName?: string
    }): Promise<Response<StorageUpdateResult>> {
        if (!data.length) {
            return Promise.reject(ResponseError.from({
                code: ResponseStatusCode.ILLEGAL_ARGUMENT,
                reason: 'The data of user profile picture must not be empty'
            }));
        }
        return this.queryUserProfilePictureUploadInfo({
            name,
            mediaType,
            extra
        }).then(uploadInfo => {
            const url = StorageService._getAndRemoveResourceUrl(uploadInfo.data, urlKeyName);
            const id = StorageService._getAndRemoveResourceId(uploadInfo.data);
            if (id == null) {
                throw ResponseError.from({
                    code: ResponseStatusCode.DATA_NOT_FOUND,
                    reason: `Could not get the resource ID because the key "${StorageService._RESOURCE_ID_KEY_NAME}" does not exist in the data: ${JSON.stringify(uploadInfo.data)}`
                });
            }
            return this._upload({
                url,
                formData: uploadInfo.data,
                data,
                id,
                name,
                mediaType
            });
        });
    }

    public deleteUserProfilePicture({
        extra
    }: {
        extra?: Record<string, string>
    } = {}): Promise<Response<void>> {
        return this._deleteResource({
            type: StorageResourceType.USER_PROFILE_PICTURE,
            extra
        });
    }

    public queryUserProfilePicture({
        userId,
        extra,
        urlKeyName,
        fetchDownloadInfo
    }: {
        userId: string,
        extra?: Record<string, string>
        urlKeyName?: string,
        fetchDownloadInfo?: boolean
    }): Promise<Response<StorageResource>> {
        return this.queryUserProfilePictureDownloadInfo({
            userId,
            extra,
            fetch: fetchDownloadInfo,
            urlKeyName
        }).then(downloadInfo => {
            const url = StorageService._getAndRemoveResourceUrl(downloadInfo.data, urlKeyName);
            return this._queryResource(url);
        });
    }

    public queryUserProfilePictureUploadInfo({
        name,
        mediaType,
        extra
    }: {
        name?: string,
        mediaType?: string,
        extra?: Record<string, string>
    } = {}): Promise<Response<Record<string, string>>> {
        return this._queryResourceUploadInfo({
            type: StorageResourceType.USER_PROFILE_PICTURE,
            name,
            mediaType,
            extra
        });
    }

    public queryUserProfilePictureDownloadInfo({
        userId,
        extra,
        fetch,
        urlKeyName
    }: {
        userId: string
        extra?: Record<string, string>,
        fetch?: boolean,
        urlKeyName?: string
    }): Promise<Response<Record<string, string>>> {
        if (fetch) {
            return this._queryResourceDownloadInfo({
                type: StorageResourceType.USER_PROFILE_PICTURE,
                idNum: userId,
                extra
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
        data,
        name,
        mediaType,
        extra,
        urlKeyName
    }: {
        groupId: string
        data: Uint8Array,
        name?: string,
        mediaType?: string,
        extra?: Record<string, string>,
        urlKeyName?: string
    }): Promise<Response<StorageUpdateResult>> {
        if (!data.length) {
            return Promise.reject(ResponseError.from({
                code: ResponseStatusCode.ILLEGAL_ARGUMENT,
                reason: 'The data of group profile picture must not be empty'
            }));
        }
        return this.queryGroupProfilePictureUploadInfo({
            groupId,
            name,
            mediaType,
            extra
        })
            .then(uploadInfo => {
                const url = StorageService._getAndRemoveResourceUrl(uploadInfo.data, urlKeyName);
                const id = StorageService._getAndRemoveResourceId(uploadInfo.data) ?? groupId;
                if (id == null) {
                    throw ResponseError.from({
                        code: ResponseStatusCode.DATA_NOT_FOUND,
                        reason: `Could not get the resource ID because the key "${StorageService._RESOURCE_ID_KEY_NAME}" does not exist in the data: ${JSON.stringify(uploadInfo.data)}`
                    });
                }
                return this._upload({
                    url,
                    formData: uploadInfo.data,
                    data,
                    id,
                    name,
                    mediaType
                });
            });
    }

    public deleteGroupProfilePicture({
        groupId,
        extra
    }: {
        groupId: string,
        extra?: Record<string, string>
    }): Promise<Response<void>> {
        return this._deleteResource({
            type: StorageResourceType.GROUP_PROFILE_PICTURE,
            idNum: groupId,
            extra
        });
    }

    public queryGroupProfilePicture({
        groupId,
        extra,
        fetchDownloadInfo,
        urlKeyName
    }: {
        groupId: string
        extra?: Record<string, string>,
        fetchDownloadInfo?: boolean,
        urlKeyName?: string
    }): Promise<Response<StorageResource>> {
        return this.queryGroupProfilePictureDownloadInfo({
            groupId,
            extra,
            fetch: fetchDownloadInfo,
            urlKeyName
        }).then(downloadInfo => {
            const url = StorageService._getAndRemoveResourceUrl(downloadInfo.data, urlKeyName);
            return this._queryResource(url);
        });
    }

    public queryGroupProfilePictureUploadInfo({
        groupId,
        name,
        mediaType,
        extra
    }: {
        groupId: string,
        name?: string,
        mediaType?: string,
        extra?: Record<string, string>
    }): Promise<Response<Record<string, string>>> {
        return this._queryResourceUploadInfo({
            type: StorageResourceType.GROUP_PROFILE_PICTURE,
            idNum: groupId,
            name,
            mediaType,
            extra
        });
    }

    public queryGroupProfilePictureDownloadInfo({
        groupId,
        extra,
        fetch,
        urlKeyName
    }: {
        groupId: string,
        extra?: Record<string, string>,
        fetch?: boolean,
        urlKeyName?: string
    }): Promise<Response<Record<string, string>>> {
        if (fetch) {
            return this._queryResourceDownloadInfo({
                type: StorageResourceType.GROUP_PROFILE_PICTURE,
                idNum: groupId,
                extra
            });
        }
        const url = `${this._serverUrl}/${StorageService._getBucketName(StorageResourceType.GROUP_PROFILE_PICTURE)}/${groupId}`;
        const info = {};
        info[urlKeyName || StorageService._DEFAULT_URL_KEY_NAME] = url;
        return Promise.resolve(Response.value(info));
    }

    // Message attachment

    public uploadMessageAttachment({
        data,
        name,
        mediaType,
        extra,
        urlKeyName
    }: {
        data: Uint8Array,
        name?: string,
        mediaType?: string,
        extra?: Record<string, string>,
        urlKeyName?: string
    }): Promise<Response<StorageUpdateResult>> {
        return this._uploadMessageAttachment({
            data,
            name: name,
            mediaType,
            extra,
            urlKeyName
        });
    }

    public uploadMessageAttachmentInPrivateConversation({
        userId,
        data,
        name,
        mediaType,
        extra,
        urlKeyName
    }: {
        userId: string,
        data: Uint8Array,
        name?: string,
        mediaType?: string,
        extra?: Record<string, string>,
        urlKeyName?: string
    }): Promise<Response<StorageUpdateResult>> {
        return this._uploadMessageAttachment({
            userId,
            data,
            name: name,
            mediaType,
            extra,
            urlKeyName
        });
    }

    public uploadMessageAttachmentInGroupConversation({
        groupId,
        data,
        name,
        mediaType,
        extra,
        urlKeyName
    }: {
        groupId: string,
        data: Uint8Array,
        name?: string,
        mediaType?: string,
        extra?: Record<string, string>,
        urlKeyName?: string
    }): Promise<Response<StorageUpdateResult>> {
        return this._uploadMessageAttachment({
            groupId,
            data,
            name: name,
            mediaType,
            extra,
            urlKeyName
        });
    }

    private _uploadMessageAttachment({
        userId,
        groupId,
        data,
        name,
        mediaType,
        extra,
        urlKeyName
    }: {
        userId?: string,
        groupId?: string,
        data: Uint8Array,
        name?: string,
        mediaType?: string,
        extra?: Record<string, string>,
        urlKeyName?: string
    }): Promise<Response<StorageUpdateResult>> {
        if (!data.length) {
            return Promise.reject(ResponseError.from({
                code: ResponseStatusCode.ILLEGAL_ARGUMENT,
                reason: 'The data of message attachment must not be empty'
            }));
        }

        let queryUploadInfo: Promise<Response<Record<string, string>>>;
        if (userId == null && groupId == null) {
            queryUploadInfo = this.queryMessageAttachmentUploadInfo({
                name,
                mediaType,
                extra
            });
        } else if (userId != null) {
            if (groupId != null) {
                return Promise.reject(ResponseError.from({
                    code: ResponseStatusCode.ILLEGAL_ARGUMENT,
                    reason: 'The user ID and the group ID must not both be non-null'
                }));
            }
            queryUploadInfo = this.queryMessageAttachmentUploadInfoInPrivateConversation({
                userId,
                name,
                mediaType,
                extra
            });
        } else {
            queryUploadInfo = this.queryMessageAttachmentUploadInfoInGroupConversation({
                groupId,
                name,
                mediaType,
                extra
            });
        }
        return queryUploadInfo.then(uploadInfo => {
            const url = StorageService._getAndRemoveResourceUrl(uploadInfo.data, urlKeyName);
            const id = StorageService._getAndRemoveResourceId(uploadInfo.data);
            if (id == null) {
                throw ResponseError.from({
                    code: ResponseStatusCode.DATA_NOT_FOUND,
                    reason: `Could not get the resource ID because the key "${StorageService._RESOURCE_ID_KEY_NAME}" does not exist in the data: ${JSON.stringify(uploadInfo.data)}`
                });
            }
            return this._upload({
                url,
                formData: uploadInfo.data,
                data,
                id,
                name,
                mediaType
            });
        });
    }

    public deleteMessageAttachment({
        attachmentIdNum,
        attachmentIdStr,
        extra
    }: {
        attachmentIdNum?: string,
        attachmentIdStr?: string,
        extra?: Record<string, string>
    }): Promise<Response<void>> {
        if (Validator.areAllNullOrNonNull([attachmentIdNum, attachmentIdStr])) {
            return Promise.reject(ResponseError.from({
                code: ResponseStatusCode.ILLEGAL_ARGUMENT,
                reason: 'One and only one attachment ID must be specified'
            }));
        }
        return this._deleteResource({
            type: StorageResourceType.MESSAGE_ATTACHMENT,
            idNum: attachmentIdNum,
            idStr: attachmentIdStr,
            extra
        });
    }

    public shareMessageAttachmentWithUser({
        userId,
        attachmentIdNum,
        attachmentIdStr
    }: {
        userId: string,
        attachmentIdNum?: string,
        attachmentIdStr?: string
    }): Promise<Response<void>> {
        if (Validator.areAllNullOrNonNull([attachmentIdNum, attachmentIdStr])) {
            return Promise.reject(ResponseError.from({
                code: ResponseStatusCode.ILLEGAL_ARGUMENT,
                reason: 'One and only one attachment ID must be specified'
            }));
        }
        return this._turmsClient.driver.send({
            updateMessageAttachmentInfoRequest: {
                attachmentIdNum,
                attachmentIdStr,
                userIdToShareWith: userId
            }
        }).then(n => Response.fromNotification(n));
    }

    public shareMessageAttachmentWithGroup({
        groupId,
        attachmentIdNum,
        attachmentIdStr
    }: {
        groupId: string,
        attachmentIdNum?: string,
        attachmentIdStr?: string
    }): Promise<Response<void>> {
        if (Validator.areAllNullOrNonNull([attachmentIdNum, attachmentIdStr])) {
            return Promise.reject(ResponseError.from({
                code: ResponseStatusCode.ILLEGAL_ARGUMENT,
                reason: 'One and only one attachment ID must be specified'
            }));
        }
        return this._turmsClient.driver.send({
            updateMessageAttachmentInfoRequest: {
                attachmentIdNum,
                attachmentIdStr,
                groupIdToShareWith: groupId
            }
        }).then(n => Response.fromNotification(n));
    }

    public unshareMessageAttachmentWithUser({
        userId,
        attachmentIdNum,
        attachmentIdStr
    }: {
        userId: string,
        attachmentIdNum?: string,
        attachmentIdStr?: string
    }): Promise<Response<void>> {
        if (Validator.areAllNullOrNonNull([attachmentIdNum, attachmentIdStr])) {
            return Promise.reject(ResponseError.from({
                code: ResponseStatusCode.ILLEGAL_ARGUMENT,
                reason: 'One and only one attachment ID must be specified'
            }));
        }
        return this._turmsClient.driver.send({
            updateMessageAttachmentInfoRequest: {
                attachmentIdNum,
                attachmentIdStr,
                userIdToUnshareWith: userId
            }
        }).then(n => Response.fromNotification(n));
    }

    public unshareMessageAttachmentWithGroup({
        groupId,
        attachmentIdNum,
        attachmentIdStr
    }: {
        groupId: string,
        attachmentIdNum?: string,
        attachmentIdStr?: string
    }): Promise<Response<void>> {
        if (Validator.areAllNullOrNonNull([attachmentIdNum, attachmentIdStr])) {
            return Promise.reject(ResponseError.from({
                code: ResponseStatusCode.ILLEGAL_ARGUMENT,
                reason: 'One and only one attachment ID must be specified'
            }));
        }
        return this._turmsClient.driver.send({
            updateMessageAttachmentInfoRequest: {
                attachmentIdNum,
                attachmentIdStr,
                groupIdToUnshareWith: groupId
            }
        }).then(n => Response.fromNotification(n));
    }

    public queryMessageAttachment({
        attachmentIdNum,
        attachmentIdStr,
        extra,
        fetchDownloadInfo,
        urlKeyName
    }: {
        attachmentIdNum?: string,
        attachmentIdStr?: string,
        extra?: Record<string, string>,
        fetchDownloadInfo?: boolean,
        urlKeyName?: string
    }): Promise<Response<StorageResource>> {
        return this.queryMessageAttachmentDownloadInfo({
            attachmentIdNum,
            attachmentIdStr,
            extra,
            fetch: fetchDownloadInfo,
            urlKeyName
        }).then(downloadInfo => {
            const url = StorageService._getAndRemoveResourceUrl(downloadInfo.data, urlKeyName);
            return this._queryResource(url);
        });
    }

    public queryMessageAttachmentUploadInfo({
        name,
        mediaType,
        extra
    }: {
        name?: string,
        mediaType?: string,
        extra?: Record<string, string>
    } = {}): Promise<Response<Record<string, string>>> {
        return this._queryResourceUploadInfo({
            type: StorageResourceType.MESSAGE_ATTACHMENT,
            name,
            mediaType,
            extra
        });
    }

    public queryMessageAttachmentUploadInfoInPrivateConversation({
        userId,
        name,
        mediaType,
        extra
    }: {
        userId: string,
        name?: string,
        mediaType?: string,
        extra?: Record<string, string>
    }): Promise<Response<Record<string, string>>> {
        return this._queryResourceUploadInfo({
            type: StorageResourceType.MESSAGE_ATTACHMENT,
            idNum: userId,
            name,
            mediaType,
            extra
        });
    }

    public queryMessageAttachmentUploadInfoInGroupConversation({
        groupId,
        name,
        mediaType,
        extra
    }: {
        groupId: string,
        name?: string,
        mediaType?: string,
        extra?: Record<string, string>
    }): Promise<Response<Record<string, string>>> {
        return this._queryResourceUploadInfo({
            type: StorageResourceType.MESSAGE_ATTACHMENT,
            idNum: `-${groupId}`,
            name,
            mediaType,
            extra
        });
    }

    public queryMessageAttachmentDownloadInfo({
        attachmentIdNum,
        attachmentIdStr,
        extra,
        fetch,
        urlKeyName
    }: {
        attachmentIdNum?: string,
        attachmentIdStr?: string,
        extra?: Record<string, string>,
        fetch?: boolean,
        urlKeyName?: string
    }): Promise<Response<Record<string, string>>> {
        if (Validator.areAllNullOrNonNull([attachmentIdNum, attachmentIdStr])) {
            return Promise.reject(ResponseError.from({
                code: ResponseStatusCode.ILLEGAL_ARGUMENT,
                reason: 'One and only one attachment ID must be specified'
            }));
        }
        if (fetch) {
            return this._queryResourceDownloadInfo({
                type: StorageResourceType.MESSAGE_ATTACHMENT,
                idNum: attachmentIdNum,
                idStr: attachmentIdStr,
                extra
            });
        }
        const url = `${this._serverUrl}/${StorageService._getBucketName(StorageResourceType.MESSAGE_ATTACHMENT)}/${attachmentIdNum || attachmentIdStr}`;
        const info = {};
        info[urlKeyName || StorageService._DEFAULT_URL_KEY_NAME] = url;
        return Promise.resolve(Response.value(info));
    }

    public queryMessageAttachmentInfosUploadedByMe({
        creationDateStart = null,
        creationDateEnd = null
    }: {
        creationDateStart?: Date,
        creationDateEnd?: Date
    } = {}): Promise<Response<ParsedModel.StorageResourceInfo[]>> {
        return this._turmsClient.driver.send({
            queryMessageAttachmentInfosRequest: {
                userIds: [],
                groupIds: [],
                creationDateStart: DataParser.getDateTimeStr(creationDateStart),
                creationDateEnd: DataParser.getDateTimeStr(creationDateEnd),
            }
        }).then(n => Response.fromNotification(n, data => NotificationUtil.transform(data.storageResourceInfos.infos)));
    }

    public queryMessageAttachmentInfosInPrivateConversations({
        userIds,
        areSharedByMe,
        creationDateStart,
        creationDateEnd
    }: {
        userIds: string[],
        areSharedByMe?: boolean,
        creationDateStart?: Date,
        creationDateEnd?: Date
    }): Promise<Response<ParsedModel.StorageResourceInfo[]>> {
        return this._turmsClient.driver.send({
            queryMessageAttachmentInfosRequest: {
                userIds: CollectionUtil.uniqueArray(userIds),
                groupIds: [],
                areSharedByMe,
                creationDateStart: DataParser.getDateTimeStr(creationDateStart),
                creationDateEnd: DataParser.getDateTimeStr(creationDateEnd),
            }
        }).then(n => Response.fromNotification(n, data => NotificationUtil.transform(data.storageResourceInfos.infos)));
    }

    public queryMessageAttachmentInfosInGroupConversations({
        groupIds,
        userIds,
        creationDateStart,
        creationDateEnd
    }: {
        groupIds: string[],
        userIds?: string[],
        creationDateStart?: Date,
        creationDateEnd?: Date
    }): Promise<Response<ParsedModel.StorageResourceInfo[]>> {
        return this._turmsClient.driver.send({
            queryMessageAttachmentInfosRequest: {
                groupIds: CollectionUtil.uniqueArray(groupIds),
                userIds: userIds == null ? undefined:CollectionUtil.uniqueArray(userIds),
                creationDateStart: DataParser.getDateTimeStr(creationDateStart),
                creationDateEnd: DataParser.getDateTimeStr(creationDateEnd),
            }
        }).then(n => Response.fromNotification(n, data => NotificationUtil.transform(data.storageResourceInfos.infos)));
    }

    // Base

    private _upload({
        url,
        formData,
        data,
        id,
        name,
        mediaType
    }: {
        url: string,
        formData: Record<string, string>,
        data: Uint8Array
        id: string,
        name?: string,
        mediaType?: string,
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
        requestFormData.set('key', id);
        let options;
        if (mediaType) {
            requestFormData.set('Content-Type', mediaType);
            options = { type: mediaType };
        }
        requestFormData.set('file', new Blob([data], options), name ?? id);
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
                .then(text => {
                    let idNum;
                    let idStr;
                    if (isNaN(parseInt(id))) {
                        idStr = id;
                        idNum = null;
                    } else {
                        idStr = null;
                        idNum = id;
                    }
                    return Response.value(new StorageUpdateResult(url, text, idNum, idStr));
                }));
    }

    private _deleteResource({
        type,
        idNum,
        idStr,
        extra
    }: {
        type: StorageResourceType,
        idNum?: string,
        idStr?: string,
        extra?: Record<string, string>
    }): Promise<Response<void>> {
        return this._turmsClient.driver.send({
            deleteResourceRequest: {
                type,
                idNum,
                idStr,
                extra
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
        idNum,
        idStr,
        name,
        mediaType,
        extra
    }: {
        type: StorageResourceType,
        idNum?: string,
        idStr?: string,
        name?: string,
        mediaType?: string,
        extra?: Record<string, string>
    }): Promise<Response<Record<string, string>>> {
        return this._turmsClient.driver.send({
            queryResourceUploadInfoRequest: {
                type,
                idNum,
                idStr,
                name,
                mediaType,
                extra
            }
        }).then(n => Response.fromNotification(n, data => NotificationUtil.toMap(data.stringsWithVersion.strings)));
    }

    private _queryResourceDownloadInfo({
        type,
        idNum,
        idStr,
        extra
    }: {
        type: StorageResourceType,
        idNum?: string,
        idStr?: string,
        extra?: Record<string, string>
    }): Promise<Response<Record<string, string>>> {
        return this._turmsClient.driver.send({
            queryResourceDownloadInfoRequest: {
                type,
                idNum,
                idStr,
                extra
            }
        }).then(n => Response.fromNotification(n, data => NotificationUtil.toMap(data.stringsWithVersion.strings)));
    }

    private static _getBucketName(resourceType: StorageResourceType): string {
        return StorageService._RESOURCE_TYPE_TO_BUCKET_NAME[resourceType];
    }

    private static _getAndRemoveResourceId(data: Record<string, string>): string | undefined {
        const name = data[StorageService._RESOURCE_ID_KEY_NAME];
        delete data[StorageService._RESOURCE_ID_KEY_NAME];
        return name;
    }

    private static _getAndRemoveResourceUrl(data: Record<string, string>, urlKeyName?: string): string {
        if (!urlKeyName) {
            urlKeyName = StorageService._DEFAULT_URL_KEY_NAME;
        }
        const url = data[urlKeyName];
        if (url == null) {
            throw ResponseError.from({
                code: ResponseStatusCode.DATA_NOT_FOUND,
                reason: `Could not get the resource URL because the key "${urlKeyName}" does not exist in the data: ${JSON.stringify(data)}`
            });
        }
        delete data[urlKeyName];
        return url;
    }
}