export default class StorageUpdateResult {
    public readonly uri: string;
    public readonly data: string;
    public readonly resourceIdNum?: string;
    public readonly resourceIdStr?: string;

    constructor(uri: string, data: string, resourceIdNum?: string, resourceIdStr?: string) {
        this.uri = uri;
        this.data = data;
        this.resourceIdNum = resourceIdNum;
        this.resourceIdStr = resourceIdStr;
    }
}