const fs = require('fs');
const path = require('path');
const parser = require('./typescript-parser.js');

const rootDir = path.dirname(path.dirname(__filename));

const declarations = parser.parse(path.join(rootDir, 'node_modules/turms-client-js/dist/turms-client.d.ts'));
const data = `${JSON.stringify(declarations, null, 2)}`;
fs.writeFileSync(path.join(rootDir, 'client/src/assets/turms-client-ast.json'), data);

console.info('Done');