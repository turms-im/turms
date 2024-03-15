// The original API comments are based on the Kotlin comments
// from turms-client-kotlin because:
// 1. It uses the Markdown format and is concise;
// 2. It has the best support of IDE,
// so we can have a good writing experience
// and verify our doc (e.g. check code reference) easily.

const path = require('path');
const fs = require('fs');

// region constants
const CommentType = {
    brief: 'brief',
    description: 'description',
    author: 'author',
    param: 'param',
    return: 'return',
    throws: 'throws'
};
const funcParams = 'params';
const blockTagStart = '* @';
const symbolRegex = /\[.*?\]/g;
const markdownLinkRegex = /\[(.*?)\]\((.*?)\)/g;
// endregion

// region utils

function trimArray(items) {
    const length = items.length;
    let startIndex = 0;
    let endIndex = length;
    for (let i = 0; i < length; i++) {
        if (items[i].trim() !== '') {
            startIndex = i;
            break;
        }
    }
    for (let i = length - 1; i >= 0; i--) {
        if (items[i].trim() !== '') {
            endIndex = i + 1;
            break;
        }
    }
    return items.slice(startIndex, endIndex);
}

function capitalizeFirstLetter(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
}

function isCharLowercase(char) {
    return char.toLowerCase() === char;
}

function upperCaseToCamelCase(text) {
    return text.toLowerCase().split('_')
        .map((value, index) => index === 0 ? value : capitalizeFirstLetter(value))
        .join('');
}

function isFunctionName(str, firstCharIndex) {
    const index = str.indexOf('.');
    return (index >= 0 && isCharLowercase(str[index + 1]))
        || (isCharLowercase(str[firstCharIndex])
            && (str.includes('create')
                || str.includes('add')
                || str.includes('remove')
                || str.includes('delete')
                || str.includes('update')
                || str.includes('query')
            ));
}
// endregion

// region parse

function parseFunction(fileLines, lineCount, startLineIndex) {
    let consumeLines = false;
    const func = {};
    let currentType = CommentType.brief;
    let currentParamName;
    let blockLines = [];

    let i = startLineIndex;
    for (; i < lineCount; i++) {
        const line = fileLines[i].trim();
        if (line.startsWith('/**')) {
            consumeLines = true;
            continue;
        }
        if (!consumeLines) {
            continue;
        }
        if (line.startsWith('*/')) {
            if (currentParamName == null) {
                func[currentType] = trimArray(blockLines);
            } else {
                func[funcParams][currentParamName] = trimArray(blockLines);
                currentParamName = null;
            }
            const functionDeclaration = fileLines[i + 1];
            const index = functionDeclaration.indexOf(' fun ');
            if (index < 0) {
                return {
                    lineIndex: i
                };
            }
            const start = index + 5;
            const end = functionDeclaration.indexOf('(', start);
            const funcName = functionDeclaration.substring(start, end);
            if (funcName == null) {
                throw new Error('Invalid function name');
            }
            func['method'] = funcName;
            return {
                lineIndex: i,
                function: func
            };
        } else if (line.startsWith(blockTagStart)) {
            if (currentParamName == null) {
                func[currentType] = trimArray(blockLines);
            } else {
                func[funcParams][currentParamName] = trimArray(blockLines);
                currentParamName = null;
            }
            blockLines = [];

            const tagEndIndex = line.indexOf(' ', blockTagStart.length);
            const tag = line.substring(blockTagStart.length, tagEndIndex);
            currentType = CommentType[tag];
            if (currentType == null) {
                throw new Error(`Invalid comment type: ${tag}`);
            } else if (currentType === CommentType.param) {
                func[funcParams] = func[funcParams] ?? {};
                const paramStartIndex = tagEndIndex + 1;
                const paramEndIndex = line.indexOf(' ', paramStartIndex);
                currentParamName = line.substring(paramStartIndex, paramEndIndex);
                blockLines.push(line.substring(paramEndIndex + 1));
            } else if (currentType === CommentType.throws || currentType === CommentType.return) {
                blockLines.push(line.substring(tagEndIndex + 1));
            }
        } else if (line === '*') {
            if (currentType === CommentType.brief) {
                func[currentType] = trimArray(blockLines);
                blockLines = [];
                currentType = CommentType.description;
            } else {
                blockLines.push('');
            }
        } else {
            blockLines.push(line.substring(2));
        }
    }
    return {
        lineIndex: i
    };
}

function parseFile(file) {
    const fileLines = file.split('\n');
    const lineCount = fileLines.length;
    const functions = [];
    let i = 0;
    while (i < lineCount) {
        const result = parseFunction(fileLines, lineCount, i);
        i = result.lineIndex + 1;
        if (result.function) {
            functions.push(result.function);
        }
    }
    return functions;
}

function parseFiles(dir) {
    // list all files in dir
    const fileNames = fs.readdirSync(dir);
    const fileToFunctions = {};
    for (const fileName of fileNames) {
        const file = fs.readFileSync(path.join(dir, fileName), 'utf8');
        const currentFileComments = parseFile(file);
        if (currentFileComments.length === 0) {
            continue;
        }
        const name = path.parse(fileName).name;
        fileToFunctions[name] = currentFileComments;
    }
    return fileToFunctions;
}

// endregion

// region write

function writeCppLines(lines, method, brief, description, params, returns, throws) {
    const formatLines = (lines) => lines
        .map(line => line.replaceAll(symbolRegex, (substring) => {
            const index = substring.indexOf('.');
            if (substring.includes('ResponseStatusCode.')) {
                const className = substring.substring(1, index);
                const statusCode = capitalizeFirstLetter(upperCaseToCamelCase(substring.substring(index + 1, substring.length - 1)));
                return `${className}::k${statusCode}`;
            }
            if (isFunctionName(substring, 1)) {
                substring = substring.replaceAll('.', '::');
                return `${substring.substring(1, substring.length - 1)}()`;
            }
            substring = substring.replaceAll('.', '::');
            return substring.substring(1, substring.length - 1);
        }))
        .join('\n * ');

    lines.push(method);
    lines.push('');
    lines.push('/**');
    lines.push(` * ${formatLines(brief)}`);
    if (description?.length) {
        lines.push(' *');
        lines.push(` * ${formatLines(description)}`);
    }
    const hasParam = Object.keys(params).length;
    const hasReturns = returns?.length;
    const hasThrows = throws?.length;
    if (hasParam || hasReturns || hasThrows) {
        lines.push(' *');
        if (hasParam) {
            for (const paramName in params) {
                lines.push(` * @param ${paramName} ${formatLines(params[paramName])}`);
            }
        }
        if (hasReturns) {
            lines.push(` * @return ${formatLines(returns)}`);
        }
        if (hasThrows) {
            lines.push(` * @throws ${formatLines(throws)}`);
        }
    }
    lines.push(' */');
    lines.push('\n\n//============ end ============//\n\n');
}

function writeDartLines(lines, method, brief, description, params, returns, throws) {
    const formatLines = (lines) => lines
        .map(line => line.replaceAll(symbolRegex, (substring) => {
            if (!substring.includes('ResponseStatusCode.')) {
                return substring;
            }
            const index = substring.indexOf('.');
            return substring.substring(0, index) + '.' + upperCaseToCamelCase(substring.substring(index + 1));
        }))
        .join('\n/// ');

    lines.push(method);
    lines.push('');
    lines.push(`/// ${formatLines(brief)}`);
    if (description?.length) {
        lines.push('///');
        lines.push(`/// ${formatLines(description)}`);
    }
    if (Object.keys(params).length) {
        lines.push('///');
        lines.push('/// **Params**:');
        for (const paramName in params) {
            lines.push(`/// * \`${paramName}\`: ${capitalizeFirstLetter(formatLines(params[paramName]))}`);
        }
    }
    if (returns?.length) {
        lines.push('///');
        lines.push(`/// **Returns**: ${capitalizeFirstLetter(formatLines(returns))}`);
    }
    if (throws?.length) {
        lines.push('///');
        lines.push(`/// **Throws**: ${capitalizeFirstLetter(formatLines(throws)).replace('ResponseException', '[ResponseException]')}`);
    }
    lines.push('\n\n//============ end ============//\n\n');
}

function writeSwiftLines(lines, method, brief, description, params, returns, throws) {
    const formatLines = (lines, indent = 1) => lines
        .map(line => {
            line = line
                .replaceAll('[ResponseException]', '``ResponseError``')
                .replaceAll('ResponseException', '``ResponseError``');
            return line.replaceAll(symbolRegex, (substring) => {
                if (substring.includes('ResponseStatusCode.')) {
                    const index = substring.indexOf('.');
                    const className = substring.substring(1, index);
                    const statusCode = upperCaseToCamelCase(substring.substring(index + 1, substring.length - 1));
                    return `\`\`${className}/${statusCode}\`\``;
                }
                substring = substring.replaceAll('.', '/');
                const symbol = substring.substring(1, substring.length - 1);
                if (params[symbol]) {
                    return `\`${symbol}\``;
                }
                return `\`\`${symbol}\`\``;
            });
        })
        .join('\n///' + ' '.repeat(indent));

    lines.push(method);
    lines.push('');
    lines.push(`/// ${formatLines(brief)}`);
    if (description?.length) {
        lines.push('///');
        lines.push(`/// ${formatLines(description)}`);
    }
    if (Object.keys(params).length) {
        lines.push('///');
        lines.push('/// - Parameters:');
        for (const paramName in params) {
            lines.push(`///   - ${paramName}: ${capitalizeFirstLetter(formatLines(params[paramName], 5))}`);
        }
    }
    if (returns?.length) {
        lines.push('///');
        lines.push(`/// - Returns: ${capitalizeFirstLetter(formatLines(returns))}`);
    }
    if (throws?.length) {
        lines.push('///');
        lines.push(`/// - Throws: ${capitalizeFirstLetter(formatLines(throws))}`);
    }
    lines.push('\n\n//============ end ============//\n\n');
}

function writeTypeScriptLines(lines, method, brief, description, params, returns, throws) {
    const formatLines = (lines) => lines
        .map(line => line.replaceAll('ResponseException', '{@link ResponseError}')
            .replaceAll(markdownLinkRegex, (substring, match1, match2) => `{@link ${match2} | ${match1}}`)
            .replaceAll(symbolRegex, (substring) => {
                substring = substring.replaceAll('.', '#');
                return `{@link ${substring.substring(1, substring.length - 1)}}`;
            })
        )
        .join('\n * ');

    lines.push(method);
    lines.push('');
    lines.push('/**');
    lines.push(` * ${formatLines(brief)}`);
    if (description?.length) {
        lines.push(' *\n * @remarks');
        lines.push(` * ${formatLines(description)}`);
    }
    if (Object.keys(params).length) {
        lines.push(' *');
        for (const paramName in params) {
            lines.push(` * @param ${paramName} - ${formatLines(params[paramName])}`);
        }
    }
    if (returns?.length) {
        lines.push(` * @returns ${formatLines(returns)}`);
    }
    if (throws?.length) {
        lines.push(` * @throws ${formatLines(throws)}`);
    }
    lines.push(' */');
    lines.push('\n\n//============ end ============//\n\n');
}

// endregion

// region main

let root = __dirname;
if (path.basename(root) === 'tools') {
    root = path.join(root, '..');
}

console.info(`Root dir: ${root}`);

const dir = path.join(root, 'turms-client-kotlin/src/main/kotlin/im/turms/client/service');

if (!fs.existsSync(dir)) {
    throw new Error(`Directory not found: ${dir}`);
}

const fileToFunctions = parseFiles(dir);
const fileAndFunctionsEntries = Object.entries(fileToFunctions);

const outputDir = path.join(root, 'generated-docs');
if (fs.existsSync(outputDir)) {
    fs.rmSync(outputDir, {recursive: true});
}
fs.mkdirSync(outputDir, {recursive: true});
for (const entry of fileAndFunctionsEntries) {
    const cppLines = [];
    const dartLines = [];
    const swiftLines = [];
    const typescriptLines = [];

    const functions = entry[1];
    for (const func of functions) {
        const {method, brief, description, params = {}, return: returns, throws} = func;

        writeCppLines(cppLines, method, brief, description, params, returns, throws);
        writeDartLines(dartLines, method, brief, description, params, returns, throws);
        writeSwiftLines(swiftLines, method, brief, description, params, returns, throws);
        writeTypeScriptLines(typescriptLines, method, brief, description, params, returns, throws);
    }
    fs.writeFileSync(path.join(outputDir, `${entry[0]}.h`), cppLines.join('\n'));
    fs.writeFileSync(path.join(outputDir, `${entry[0]}.dart`), dartLines.join('\n'));
    fs.writeFileSync(path.join(outputDir, `${entry[0]}.swift`), swiftLines.join('\n'));
    fs.writeFileSync(path.join(outputDir, `${entry[0]}.ts`), typescriptLines.join('\n'));
}

console.info(`All files generated in the directory: ${outputDir}`);

// endregion