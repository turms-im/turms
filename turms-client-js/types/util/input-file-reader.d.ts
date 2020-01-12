export default class InputFileReader {
    private static _reader;
    static readFileFromHTMLInputElement(element: HTMLInputElement, fileIndex?: number): Promise<string | Uint8Array>;
    static readFile(file: File): Promise<string | Uint8Array>;
}
