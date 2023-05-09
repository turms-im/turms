import { saveAs } from 'file-saver';

export default class FileSaver {

    static saveAs({fileName, data, type = 'application/octet-stream'}: {
        fileName: string,
        data: Blob | BlobPart,
        type: string
    }): void {
        let blob = data;
        if (!(blob instanceof Blob)) {
            blob = new Blob([data], {type});
        }
        saveAs(blob, fileName);
    }

}