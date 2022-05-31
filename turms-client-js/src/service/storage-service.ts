import unfetch from 'unfetch';
import { ContentType } from '../model/proto/constant/content_type';
import Response from '../model/response';
import ResponseError from '../error/response-error';
import ResponseStatusCode from '../model/response-status-code';
import TurmsClient from '../turms-client';

export default class StorageService {

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

    // Profile picture

    public queryProfilePictureUrlForAccess(userId: string): Promise<Response<string>> {
        const url = `${this._serverUrl}/${StorageService._getBucketName(ContentType.PROFILE)}/${userId}`;
        return Promise.resolve(Response.value(url));
    }

    public queryProfilePicture(userId: string): Promise<Response<Uint8Array>> {
        return this.queryProfilePictureUrlForAccess(userId)
            .then(response => this._getBytesFromGetUrl(response.data));
    }

    public queryProfilePictureUrlForUpload(pictureSize: number): Promise<Response<string>> {
        const userId = this._turmsClient.userService.userInfo.userId;
        if (userId) {
            return this._getSignedPutUrl(ContentType.PROFILE, pictureSize, null, userId);
        } else {
            return Promise.reject(ResponseError.fromCode(ResponseStatusCode.QUERY_PROFILE_URL_TO_UPDATE_BEFORE_LOGIN));
        }
    }

    public uploadProfilePicture(bytes: Uint8Array): Promise<Response<string>> {
        return this.queryProfilePictureUrlForUpload(bytes.length)
            .then(response => this._upload(response.data, bytes));
    }

    public deleteProfile(): Promise<Response<void>> {
        return this._deleteResource(ContentType.PROFILE);
    }

    // Group profile picture

    public queryGroupProfilePictureUrlForAccess(groupId: string): Promise<Response<string>> {
        const url = `${this._serverUrl}/${StorageService._getBucketName(ContentType.GROUP_PROFILE)}/${groupId}`;
        return Promise.resolve(Response.value(url));
    }

    public queryGroupProfilePicture(groupId: string): Promise<Response<Uint8Array>> {
        return this.queryGroupProfilePictureUrlForAccess(groupId)
            .then(response => this._getBytesFromGetUrl(response.data));
    }

    public queryGroupProfilePictureUrlForUpload(pictureSize: number, groupId: string): Promise<Response<string>> {
        return this._getSignedPutUrl(ContentType.GROUP_PROFILE, pictureSize, null, groupId);
    }

    public uploadGroupProfilePicture(bytes: Uint8Array, groupId: string): Promise<Response<string>> {
        return this.queryGroupProfilePictureUrlForUpload(bytes.length, groupId)
            .then(response => this._upload(response.data, bytes));
    }

    public deleteGroupProfile(groupId: string): Promise<Response<void>> {
        return this._deleteResource(ContentType.GROUP_PROFILE, null, groupId);
    }

    // Message attachment

    public queryAttachmentUrlForAccess(messageId: string, name?: string): Promise<Response<string>> {
        return this._getSignedGetUrl(ContentType.ATTACHMENT, name, messageId);
    }

    public queryAttachment(messageId: string, name?: string): Promise<Response<Uint8Array>> {
        return this.queryAttachmentUrlForAccess(messageId, name)
            .then(response => this._getBytesFromGetUrl(response.data));
    }

    public queryAttachmentUrlForUpload(messageId: string, attachmentSize: number): Promise<Response<string>> {
        return this._getSignedPutUrl(ContentType.ATTACHMENT, attachmentSize, null, messageId);
    }

    public uploadAttachment(messageId: string, bytes: Uint8Array): Promise<Response<string>> {
        return this.queryAttachmentUrlForUpload(messageId, bytes.length)
            .then(response => this._upload(response.data, bytes));
    }

    // Base

    private _getSignedGetUrl(contentType: ContentType, keyStr?: string, keyNum?: string): Promise<Response<string>> {
        return this._turmsClient.driver.send({
            querySignedGetUrlRequest: {
                contentType: contentType,
                keyStr,
                keyNum
            }
        }).then(n => Response.fromNotification(n, data => data.url));
    }

    private _getSignedPutUrl(contentType: ContentType, size: number, keyStr?: string, keyNum?: string): Promise<Response<string>> {
        return this._turmsClient.driver.send({
            querySignedPutUrlRequest: {
                contentType: contentType,
                contentLength: `${size}`,
                keyStr,
                keyNum
            }
        }).then(n => Response.fromNotification(n, data => data.url));
    }

    private _deleteResource(contentType: ContentType, keyStr?: string, keyNum?: string): Promise<Response<void>> {
        return this._turmsClient.driver.send({
            deleteResourceRequest: {
                contentType: contentType,
                keyStr,
                keyNum
            }
        }).then(n => Response.fromNotification(n));
    }

    private _getBytesFromGetUrl(url: string): Promise<Response<Uint8Array>> {
        return new Promise((resolve, reject) => {
            try {
                unfetch(url)
                    .then(res => {
                        if (res.status === 200) {
                            return res.blob();
                        } else {
                            throw ResponseError.fromCode(ResponseStatusCode.INVALID_RESPONSE);
                        }
                    })
                    .then(data => {
                        const reader = new FileReader();
                        reader.onload = function (e): void {
                            resolve(Response.value(new Uint8Array(e.target.result as ArrayBuffer)));
                        };
                        reader.readAsArrayBuffer(data);
                    })
                    .catch(e => reject(e));
            } catch (e) {
                reject(e);
            }
        });
    }

    private _upload(url: string, bytes: Uint8Array): Promise<Response<string>> {
        return new Promise((resolve, reject) => {
            try {
                unfetch(url, { method: 'PUT', body: bytes }).then(res => {
                    if (res.status === 200) {
                        resolve(Response.value(res.url));
                    } else {
                        throw ResponseError.fromCode(ResponseStatusCode.INVALID_RESPONSE);
                    }
                }).catch(e => reject(e));
            } catch (e) {
                reject(e);
            }
        });
    }

    private static _getBucketName(contentType: ContentType): string {
        return ContentType[contentType].toString().toLowerCase().replace(/_/g, '-');
    }
}
