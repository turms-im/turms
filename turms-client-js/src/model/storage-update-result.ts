export default class StorageUpdateResult {
    public readonly uri: string;
    public readonly data: string;

    constructor(uri: string, data: string) {
        this.uri = uri;
        this.data = data;
    }
}
