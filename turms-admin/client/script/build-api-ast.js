const fs = require('fs');
const {join, resolve} = require('path');
const {TypescriptParser, ClassDeclaration} = require('typescript-parser');

function getApiPaths(apiPath) {
    const paths = [];
    const items = fs.readdirSync(apiPath);
    items.forEach(item => {
        const fPath = join(apiPath, item);
        const stat = fs.statSync(fPath);
        if (stat.isFile()) {
            paths.push(fPath);
        }
    });
    return paths;
}

const apiPaths = getApiPaths('api');
const parser = new TypescriptParser();

parser.parseFiles(apiPaths, __dirname).then(files => {
    const ast = [];
    for (const file of files) {
        for (const declaration of file.declarations) {
            if (declaration instanceof ClassDeclaration) {
                const methods = {};
                for (const method of declaration.methods) {
                    const parameters = [];
                    for (const parameter of method.parameters) {
                        parameters.push({
                            name: parameter.name,
                            type: parameter.type
                        });
                    }
                    methods[method.name] = {
                        type: method.type,
                        parameters
                    };
                    ast.push({
                        class: declaration.name[0].toLowerCase() + declaration.name.slice(1),
                        method: method.name,
                        type: method.type,
                        parameters
                    });
                }
            }
        }
    }
    fs.writeFileSync(resolve(__dirname, '../src/assets/ast.json'), `${JSON.stringify(ast, null, 2)}`);
});