// Reference: https://github.com/doowb/ansi-colors

function toColor(msg: string, open: number) {
    return `\u001b[${open}m${msg}\u001b[39m`;
}

export default {
    redBright: (msg: string) => toColor(msg, 91),
    greenBright: (msg: string) => toColor(msg, 92),
    yellowBright: (msg: string) => toColor(msg, 93),
    blueBright: (msg: string) => toColor(msg, 94)
};