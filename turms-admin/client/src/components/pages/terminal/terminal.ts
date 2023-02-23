import {Terminal as XTerm} from 'xterm';
import {FitAddon} from 'xterm-addon-fit';
import codes from './ansi-escape-codes';
import colors from './ansi-colors';
import 'xterm/css/xterm.css';

const NEW_LINE_PREFIX = '$ ';
const LINE_BREAK = '\r\n';
const DUPLICATE_MAP = {
    "'": "'",
    '"': '"',
    '(': ')',
    '[': ']'
};
const MESSAGE_TYPE_COLORS = {
    error: 'redBright',
    warn: 'yellowBright',
    info: 'blueBright',
    success: 'greenBright'
};
const CHARS_TO_SKIP = Object.values(DUPLICATE_MAP)
    .reduce((result, val) => {
        result[val] = true;
        return result;
    }, {});
const FAVORITE_FONTS = [
    'Consolas', // Windows
    'Monaco' // MacOS
];

function findFavoriteFont(): string | null {
    const isSupportedFont = (family) => document.fonts?.check('12px ' + family);
    for (const font of FAVORITE_FONTS) {
        if (isSupportedFont(font)) {
            return font;
        }
    }
}

export default class Terminal extends XTerm {
    private cursor: number;
    private currentLine: string;
    private readonly history: Array<string>;
    private historyIndex: number;
    private clipboard: string;
    private readonly disableStdin: boolean;
    private readonly onCursorChanged?: Function;
    private readonly onInputChanged?: Function;
    private readonly onLine?: Function;
    private readonly handleInputEvent?: Function;

    private readonly fitAddon: FitAddon;
    private waitForResponse: boolean;
    private readonly _onWinResize: () => void;

    constructor(container: HTMLElement, options: {
        fontFamily?: string,
        history?: Array<string>,
        onCursorChanged?: Function
        onInputChanged?: Function,
        onLine?: Function,
        handleInputEvent?: Function
        disableStdin?: boolean
    } = {}) {
        if (!options.fontFamily) {
            options.fontFamily = findFavoriteFont();
        }
        super({...options, ...{
            altClickMovesCursor: false,
            cursorBlink: true,
            cursorStyle: 'bar',
            cursorWidth: 2,
            theme: {
                background: '#1c1c1c'
            }
        }});

        this.cursor = 0;
        this.currentLine = '';
        this.history = options.history || [];
        this.historyIndex = this.history.length > 0 ? this.history.length - 1 : 0;
        this.clipboard = '';
        this.disableStdin = options.disableStdin;
        this.onCursorChanged = options.onCursorChanged;
        this.onInputChanged = options.onInputChanged;
        this.onLine = options.onLine;
        this.handleInputEvent = options.handleInputEvent;

        this.fitAddon = new FitAddon();
        this.loadAddon(this.fitAddon);

        this.open(container);
        this.fit();

        this.onKey(async (e) => {
            if (options.disableStdin || this.waitForResponse) {
                return;
            }
            const event = e.domEvent;
            const handled = await this.handleControlKey(event);
            if (!handled) {
                const key = event.key;
                this.insertText(key);
            }
        });
        // We don't use "onData" because we cannot distinguish
        // it's a paste event or an input event
        this.textarea.addEventListener('paste', event => {
            const text = event.clipboardData?.getData('text/plain');
            this.insertText(text);
        });
        this._onWinResize = () => {
            this.fit();
        };
        window.addEventListener('resize', this._onWinResize);
        if (options.disableStdin) {
            this.hideCursor();
        }
    }

    // Lifecycle

    override dispose(): void {
        window.removeEventListener('resize', this._onWinResize);
        super.dispose();
    }

    // Style

    fit(): void {
        this.fitAddon.fit();
        this._triggerOnCursorChangedListeners();
    }

    // Cursor

    cursorTo(pos: number): void {
        this.cursor = pos;
        this.write(codes.cursorTo(NEW_LINE_PREFIX.length + pos));
        this._triggerOnCursorChangedListeners();
    }

    hideCursor(): void {
        this.write(codes.cursorHide);
    }

    // Line

    resetLine(data?: string): void {
        this.currentLine = '';
        if (data) {
            this.write(data);
        }
        if (!this.disableStdin) {
            this.writeNewLinePrefix();
        }
        this.cursorTo(0);
    }

    eraseLine(): void {
        this.write(codes.eraseLine);
        this.write(codes.cursorLeft);
        this.writeNewLinePrefix();
    }

    writeNewLinePrefix(): void {
        this.write(NEW_LINE_PREFIX);
    }

    startNewLine(): void {
        this.resetLine(LINE_BREAK);
    }

    override clear(): void {
        this.resetLine(codes.clearScreen);
        this.onInputChanged?.(this.currentLine, this.cursor);
    }

    // Text

    getNextChar(): string {
        return this.currentLine[this.cursor];
    }

    insertText(text?: string): void {
        if (!text) {
            return;
        }
        text = text.replace(/\r?\n/g, '');
        if (CHARS_TO_SKIP[text] && text === this.getNextChar()) {
            this.cursor++;
            this.cursorTo(this.cursor);
            return;
        }
        const duplicate = DUPLICATE_MAP[text];
        const output = duplicate ? text + duplicate : text;
        const isCursorAtEnd = this.cursor === this.currentLine.length;
        this.currentLine = this.currentLine.slice(0, this.cursor) + output + this.currentLine.slice(this.cursor);
        if (isCursorAtEnd) {
            this.write(output);
            this.onInputChanged?.(this.currentLine, this.cursor);
        } else {
            this.writeLine(this.currentLine);
        }
        if (duplicate) {
            this.cursor++;
        } else {
            this.cursor += output.length;
        }
        this.cursorTo(this.cursor);
    }

    writeMsg({type, msg}: {
        type?: string,
        msg?: string
    } = {}): void {
        if (msg == null) {
            return;
        }
        const color = MESSAGE_TYPE_COLORS[type || 'info'];
        const data = colors[color](msg);
        this.writeln(data);
    }

    writeLine(line: string, updateCursor: boolean = false): void {
        this.currentLine = line;
        this.eraseLine();
        this.write(line);
        this.onInputChanged?.(this.currentLine, this.cursor);
        if (updateCursor) {
            this.cursorTo(this.currentLine.length);
        }
    }

    // Clipboard
    _writeToClipboard(text: string): Promise<void> {
        this.clipboard = text;
        const clipboard = navigator.clipboard;
        if (clipboard?.writeText) {
            return clipboard.writeText(text);
        }
        return new Promise((resolve, reject) => {
            const textarea = document.createElement('textarea');
            textarea.style.display = 'none';
            textarea.value = text;
            document.body.appendChild(textarea);
            textarea.select();
            try {
                document.execCommand('copy');
            } catch (e: any) {
                reject(`Failed to copy: ${e?.message ?? e}`);
                document.body.removeChild(textarea);
                return;
            }
            document.body.removeChild(textarea);
            resolve();
        });
    }

    _readFromClipboard(): Promise<string> {
        const clipboard = navigator.clipboard;
        // e.g. Firefox doesn't support readText()
        if (clipboard?.readText) {
            return clipboard.readText();
        }
        // No need to use "document.execCommand('paste')" because
        // modern browsers don't support for security reasons.
        // Fallback to the clipboard in the page scope
        return Promise.resolve(this.clipboard);
    }

    // Event

    _triggerOnCursorChangedListeners(): void {
        setTimeout(() => {
            const rect = this.textarea.getBoundingClientRect();
            this.onCursorChanged?.(rect.right, rect.bottom, rect.height);
        });
    }

    async handleControlKey(event: KeyboardEvent): Promise<boolean> {
        if (this.handleInputEvent?.(event)) {
            return true;
        }
        // Reference: https://developer.mozilla.org/en-US/docs/Web/API/UI_Events/Keyboard_event_code_values
        // Use "key" instead of "code", taking into consideration the state of modifier keys and the keyboard locale and layout
        const key = event.key;
        switch (key) {
            case 'Enter': {
                this.writeln('');
                let result;
                if (this.currentLine) {
                    this.history.push(this.currentLine);
                    this.historyIndex = this.history.length - 1;
                    if (this.onLine) {
                        this.waitForResponse = true;
                        result = await this.onLine(this.currentLine);
                        this.waitForResponse = false;
                        this.writeMsg(result?.message);
                    }
                }
                if (result?.clear) {
                    this.clear();
                } else if (result?.newLine) {
                    this.startNewLine();
                } else {
                    this.resetLine();
                }
            }
                break;
            case 'ArrowUp':
                if (this.history.length) {
                    const currentLine = this.history[this.historyIndex];
                    if (this.historyIndex) {
                        this.historyIndex--;
                    }
                    this.writeLine(currentLine, true);
                }
                break;
            case 'ArrowDown': {
                let currentLine = '';
                if (this.historyIndex < this.history.length - 1) {
                    this.historyIndex++;
                    currentLine = this.history[this.historyIndex];
                }
                this.writeLine(currentLine, true);
            }
                break;
            case 'ArrowLeft':
                if (this.cursor > 0) {
                    this.cursorTo(this.cursor - 1);
                }
                break;
            case 'ArrowRight':
                if (this.cursor >= 0 && this.cursor < this.currentLine.length) {
                    this.cursorTo(this.cursor + 1);
                }
                break;
            case 'Backspace':
                if (this.cursor > 0) {
                    const currentLine = this.currentLine.substring(0, this.cursor - 1)
                        + this.currentLine.substring(this.cursor);
                    this.writeLine(currentLine);
                    this.cursorTo(this.cursor - 1);
                }
                break;
            case 'Delete':
                if (this.cursor >= 0) {
                    const currentLine = this.currentLine.substring(0, this.cursor)
                        + this.currentLine.substring(this.cursor + 1);
                    this.writeLine(currentLine);
                    this.cursorTo(this.cursor);
                }
                break;
            case 'Home':
                this.cursorTo(0);
                break;
            case 'End':
                this.cursorTo(this.currentLine.length);
                break;
            case 'C':
            case 'c':
                if (event.ctrlKey) {
                    const selection = this.getSelection();
                    if (selection) {
                        await this._writeToClipboard(selection);
                    } else {
                        this.resetLine(LINE_BREAK);
                    }
                } else {
                    return false;
                }
                break;
            case 'V':
            case 'v':
                if (event.ctrlKey) {
                    const text = await this._readFromClipboard();
                    this.insertText(text);
                } else {
                    return false;
                }
                break;
            default:
                return event.altKey || event.ctrlKey || event.metaKey;
        }
        return true;
    }
}