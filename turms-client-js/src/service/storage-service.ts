import TurmsClient from "../turms-client";
import TurmsStatusCode from "../model/turms-status-code";
import {im} from "../model/proto-bundle";
import TurmsBusinessError from "../model/turms-business-error";
import RequestUtil from "../util/request-util";
import NotificationUtil from "../util/notification-util";
// @ts-ignore
import fetch from 'unfetch';
import ContentType = im.turms.proto.ContentType;

export default class StorageService {

    private _turmsClient: TurmsClient;
    private _serverUrl = "http://localhost:9000";

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

    public queryProfilePictureUrlForAccess(userId: string): Promise<string> {
        const url = `${this._serverUrl}/${StorageService._getBucketName(ContentType.PROFILE)}/${userId}`;
        return Promise.resolve(url);
    }

    public queryProfilePicture(userId: string): Promise<Uint8Array> {
        return this.queryProfilePictureUrlForAccess(userId)
            .then(url => this._getBytesFromGetUrl(url));
    }

    public queryProfilePictureUrlForUpload(pictureSize): Promise<string> {
        const userId = this._turmsClient.userService.userId;
        if (userId) {
            return this._getSignedPutUrl(ContentType.PROFILE, pictureSize, null, userId);
        } else {
            return Promise.reject(TurmsBusinessError.fromCode(TurmsStatusCode.UNAUTHORIZED.valueOf()));
        }
    }

    public uploadProfilePicture(bytes: Uint8Array): Promise<string> {
        return this.queryProfilePictureUrlForUpload(bytes.length)
            .then(url => this._upload(url, bytes))
    }

    public deleteProfile(): Promise<void> {
        return this._deleteResource(ContentType.PROFILE);
    }

// Group profile picture

    public queryGroupProfilePictureUrlForAccess(groupId: string): Promise<string> {
        const url = `${this._serverUrl}/${StorageService._getBucketName(ContentType.GROUP_PROFILE)}/${groupId}`;
        return Promise.resolve(url);
    }

    public queryGroupProfilePicture(groupId: string): Promise<Uint8Array> {
        return this.queryGroupProfilePictureUrlForAccess(groupId)
            .then(url => this._getBytesFromGetUrl(url));
    }

    public queryGroupProfilePictureUrlForUpload(pictureSize: number, groupId: string): Promise<string> {
        return this._getSignedPutUrl(ContentType.GROUP_PROFILE, pictureSize, null, groupId);
    }

    public uploadGroupProfilePicture(bytes: Uint8Array, groupId: string): Promise<string> {
        return this.queryGroupProfilePictureUrlForUpload(bytes.length, groupId)
            .then(url => this._upload(url, bytes));
    }

    public deleteGroupProfile(groupId: string): Promise<void> {
        return this._deleteResource(ContentType.GROUP_PROFILE, null, groupId);
    }

// Message attachment

    public queryAttachmentUrlForAccess(messageId: string, name?: string): Promise<string> {
        return this._getSignedGetUrl(ContentType.ATTACHMENT, name, messageId);
    }

    public queryAttachment(messageId: string, name?: string): Promise<Uint8Array> {
        return this.queryAttachmentUrlForAccess(messageId, name).then(url => this._getBytesFromGetUrl(url));
    }

    public queryAttachmentUrlForUpload(messageId: string, attachmentSize: number): Promise<string> {
        return this._getSignedPutUrl(ContentType.ATTACHMENT, attachmentSize, null, messageId);
    }

    public uploadAttachment(messageId: string, bytes: Uint8Array): Promise<string> {
        return this.queryAttachmentUrlForUpload(messageId, bytes.length)
            .then(url => this._upload(url, bytes));
    }

// Base

    private _getSignedGetUrl(contentType: ContentType, keyStr?: string, keyNum?: string): Promise<string> {
        return this._turmsClient.driver.send({
            querySignedGetUrlRequest: {
                contentType: contentType,
                keyStr: RequestUtil.wrapValueIfNotNull(keyStr),
                keyNum: RequestUtil.wrapValueIfNotNull(keyNum)
            }
        }).then(n => NotificationUtil.getVal(n, 'url'));
    }

    private _getSignedPutUrl(contentType: ContentType, size: number, keyStr?: string, keyNum?: string): Promise<string> {
        return this._turmsClient.driver.send({
            querySignedPutUrlRequest: {
                contentType: contentType,
                contentLength: `${size}`,
                keyStr: RequestUtil.wrapValueIfNotNull(keyStr),
                keyNum: RequestUtil.wrapValueIfNotNull(keyNum)
            }
        }).then(n => NotificationUtil.getVal(n, 'url'));
    }

    private _deleteResource(contentType: ContentType, keyStr?: string, keyNum?: string): Promise<void> {
        return this._turmsClient.driver.send({
            deleteResourceRequest: {
                contentType: contentType,
                keyStr: RequestUtil.wrapValueIfNotNull(keyStr),
                keyNum: RequestUtil.wrapValueIfNotNull(keyNum)
            }
        }).then(() => null);
    }

    private _getBytesFromGetUrl(url: string): Promise<Uint8Array> {
        return new Promise((resolve, reject) => {
            try {
                fetch(url)
                    .then(res => {
                        if (res.status === 200) {
                            return res.blob();
                        } else {
                            reject(res);
                        }
                    })
                    .then(data => {
                        const reader = new FileReader();
                        reader.onload = function (e): void {
                            resolve(new Uint8Array(e.target.result as ArrayBuffer));
                        }
                        reader.readAsArrayBuffer(data);
                    });
            } catch (e) {
                reject(e);
            }
        });
    }

    private _upload(url: string, bytes: Uint8Array): Promise<string> {
        return new Promise((resolve, reject) => {
            try {
                fetch(url, {method: 'PUT', body: bytes}).then(res => {
                    if (res.status === 200) {
                        resolve(res.url)
                    } else {
                        reject(res);
                    }
                });
            } catch (e) {
                reject(e);
            }
        });
    }

    private static _getBucketName(contentType: ContentType): string {
        return ContentType[contentType].toString().toLowerCase().replace("_", "-");
    }
}
