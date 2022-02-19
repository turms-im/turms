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
        this.history = [];
        this.historyIndex = 0;

        this.fitAddon = new FitAddon();
        this.loadAddon(this.fitAddon);

        this.open(container);
        this.fit();

        this.onKey(async (e) => {
            if (options.disableStdin || this.waitForResponse) {
                return;
            }
            const event = e.domEvent;
            const isControlKey = await this.handleControlKey(event);
            if (!isControlKey) {
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
    }

    // Cursor

    cursorTo(pos) {
        this.cursor = pos;
        this.write(escapes.cursorTo(PREFIX.length + pos));
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
        } else {
            this.eraseLine();
            this.write(this.currentLine);
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
        const data = styles[color].open + msg + styles[color].close;
        this.writeln(data);
        if (newLine) {
            this.writeln('');
        }
    }

    // Event

    async handleControlKey(event) {
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
                this.cursor = 0;
                break;
            case 'ArrowUp':
                if (this.history.length) {
                    this.currentLine = this.history[this.historyIndex];
                    if (this.historyIndex) {
                        this.historyIndex--;
                    }
                    this.eraseLine();
                    this.write(this.currentLine);
                    this.cursorTo(this.currentLine.length);
                }
                break;
            case 'ArrowDown':
                if (this.historyIndex < this.history.length - 1) {
                    this.historyIndex++;
                    this.currentLine = this.history[this.historyIndex];
                } else {
                    this.currentLine = '';
                }
                this.eraseLine();
                this.write(this.currentLine);
                this.cursorTo(this.currentLine.length);
                break;
            case 'ArrowLeft':
                if (this.cursor > 0) {
                    this.cursor -= 1;
                    this.write(escapes.cursorBackward(1));
                }
                break;
            case 'ArrowRight':
                if (this.cursor >= 0 && this.cursor < this.currentLine.length) {
                    this.cursor += 1;
                    this.write(escapes.cursorForward(1));
                }
                break;
            case 'Backspace':
                if (this.cursor > 0) {
                    this.currentLine = this.currentLine.substr(0, this.cursor - 1)
                        + this.currentLine.substr(this.cursor);
                    this.cursor -= 1;
                    this.eraseLine();
                    this.write(this.currentLine);
                    this.cursorTo(this.cursor);
                }
                break;
            case 'Delete':
                if (this.cursor >= 0) {
                    this.currentLine = this.currentLine.substr(0, this.cursor)
                        + this.currentLine.substr(this.cursor + 1);
                    this.eraseLine();
                    this.write(this.currentLine);
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
                        this.cursor = 0;
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