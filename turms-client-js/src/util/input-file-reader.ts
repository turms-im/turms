export enum ErrorCode {
    ELEMENT_IS_NULL = 1,
    IS_NOT_FILE_LIST,
    NO_FILES_SELECTED
}

export default class InputFileReader {
    private static _reader: FileReader;

    static readFileFromHTMLInputElement(element: HTMLInputElement, fileIndex = 0): Promise<string | Uint8Array> {
        // element.files instanceof FileList && element.files.length > 0
        if (element == null) {
            return Promise.reject(ErrorCode.ELEMENT_IS_NULL);
        } else if (!(element.files instanceof FileList)) {
            return Promise.reject(ErrorCode.IS_NOT_FILE_LIST);
        } else if (element.files.length === 0) {
            return Promise.reject(ErrorCode.NO_FILES_SELECTED);
        }
        return this.readFile(element.files[fileIndex]);
    }

    static readFile(file: File): Promise<string | Uint8Array> {
        if (!this._reader) {
            this._reader = new FileReader();
        }
        const promise = new Promise<string | Uint8Array>((resolve, reject): void => {
            this._reader.onload = (event): void => {
                if (typeof event.target.result === 'string') {
                    resolve(event.target.result);
                } else {
                    resolve(new Uint8Array(event.target.result));
                }
            };
            this._reader.onerror = (error): void => {
                reject(error);
            };
        });
        this._reader.readAsArrayBuffer(file);
        return promise;
    }
}
