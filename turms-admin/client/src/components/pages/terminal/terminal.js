import { Terminal as XTerm } from 'xterm';
import escapes from 'ansi-escapes';
import colors from 'ansi-colors';
import { FitAddon } from 'xterm-addon-fit';
import 'xterm/css/xterm.css';

const styles = colors.styles;
const PREFIX = '$ ';
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

function findFavoriteFont() {
    const isSupportedFont = (family) => document.fonts?.check('12px ' + family);
    for (const font of FAVORITE_FONTS) {
        if (isSupportedFont(font)) {
            return font;
        }
    }
}

export default class Terminal extends XTerm {
    constructor(container, options = {}) {
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
        this.onCursorChanged = options.onCursorChanged;
        this.onInputChanged = options.onInputChanged;
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
    }

    // Lifecycle

    dispose() {
        window.removeEventListener('resize', this._onWinResize);
        super.dispose();
    }

    // Style

    fit() {
        this.fitAddon.fit();
        this._triggerOnCursorChangedListeners();
    }

    // Cursor

    cursorTo(pos) {
        this.cursor = pos;
        this.write(escapes.cursorTo(PREFIX.length + pos));
        this._triggerOnCursorChangedListeners();
    }

    // Line

    eraseLine() {
        this.write(escapes.eraseLine);
        this.write(escapes.cursorLeft);
        this.startNewLine();
    }

    startNewLine() {
        this.write(PREFIX);
    }

    clear() {
        this.currentLine = '';
        this.write(escapes.clearScreen);
        this.startNewLine();
        this.onInputChanged?.(this.currentLine);
        this._triggerOnCursorChangedListeners();
    }

    // Text

    getNextChar() {
        return this.currentLine[this.cursor];
    }

    insertText(text) {
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
            this.onInputChanged?.(this.currentLine);
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

    writeMsg({type, msg, newLine}) {
        if (msg == null) {
            return;
        }
        const color = MESSAGE_TYPE_COLORS[type || 'info'];
        const style = styles[color];
        const data = style.open + msg + style.close;
        this.writeln(data);
        if (newLine) {
            this.writeln('');
        }
    }

    writeLine(line, updateCursor) {
        this.currentLine = line;
        this.eraseLine();
        this.write(line);
        this.onInputChanged?.(this.currentLine);
        if (updateCursor) {
            this.cursorTo(this.currentLine.length);
        }
    }

    // Event

    _triggerOnCursorChangedListeners() {
        setTimeout(() => {
            const rect = this.textarea.getBoundingClientRect();
            this.onCursorChanged?.(rect.right, rect.bottom);
        });
    }

    async handleControlKey(event) {
        if (this.handleInputEvent?.(event)) {
            return true;
        }
        const key = event.key;
        switch (key) {
            case 'Enter':
                this.writeln('');
                if (this.currentLine) {
                    this.history.push(this.currentLine);
                    this.historyIndex = this.history.length - 1;
                    if (this.onLine) {
                        this.waitForResponse = true;
                        const result = await this.onLine(this.currentLine);
                        this.waitForResponse = false;
                        this.writeMsg(result);
                    }
                }
                this.startNewLine();
                this.currentLine = '';
                this.cursorTo(0);
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
            case 'c':
                if (event.ctrlKey) {
                    const selection = this.getSelection();
                    if (selection) {
                        await navigator.clipboard.writeText(selection);
                    } else {
                        this.currentLine = '';
                        this.cursorTo(0);
                        this.writeln('');
                        this.startNewLine();
                    }
                } else {
                    return false;
                }
                break;
            case 'v':
                if (event.ctrlKey) {
                    const text = await navigator.clipboard.readText();
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