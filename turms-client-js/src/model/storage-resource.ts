export default class StorageResource {
    public readonly uri: string;
    public readonly data: Uint8Array;

    constructor(uri: string, data: Uint8Array) {
        this.uri = uri;
        this.data = data;
    }
}
