import TurmsClient from "../turms-client";
export default class StorageService {
    private _turmsClient;
    private _serverUrl;
    constructor(turmsClient: TurmsClient, storageServerUrl?: string);
    get serverUrl(): string;
    set serverUrl(serverUrl: string);
    queryProfilePictureUrlForAccess(userId: string): Promise<string>;
    queryProfilePicture(userId: string): Promise<Uint8Array>;
    queryProfilePictureUrlForUpload(pictureSize: any): Promise<string>;
    uploadProfilePicture(bytes: Uint8Array): Promise<string>;
    deleteProfile(): Promise<void>;
    queryGroupProfilePictureUrlForAccess(groupId: string): Promise<string>;
    queryGroupProfilePicture(groupId: string): Promise<Uint8Array>;
    queryGroupProfilePictureUrlForUpload(pictureSize: number, groupId: string): Promise<string>;
    uploadGroupProfilePicture(bytes: Uint8Array, groupId: string): Promise<string>;
    deleteGroupProfile(groupId: string): Promise<void>;
    queryAttachmentUrlForAccess(messageId: string, name?: string): Promise<string>;
    queryAttachment(messageId: string, name?: string): Promise<Uint8Array>;
    queryAttachmentUrlForUpload(messageId: string, attachmentSize: number): Promise<string>;
    uploadAttachment(messageId: string, bytes: Uint8Array): Promise<string>;
    private _getSignedGetUrl;
    private _getSignedPutUrl;
    private _deleteResource;
    private _getBytesFromGetUrl;
    private _upload;
    private static _getBucketName;
}
