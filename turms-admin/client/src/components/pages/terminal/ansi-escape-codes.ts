// Reference: https://github.com/sindresorhus/ansi-escapes

const ESC = '\u001B[';
const SEP = ';';

export default {
    cursorTo: (x: number, y?: number) => {
        return y == null
            ? `${ESC + (x + 1)}G`
            : `${ESC + (y + 1) + SEP + (x + 1)}H`;
    },
    cursorHide: `${ESC}?25l`,
    cursorLeft: `${ESC}G`,
    eraseLine: `${ESC}2K`,
    clearScreen: '\u001Bc'
};