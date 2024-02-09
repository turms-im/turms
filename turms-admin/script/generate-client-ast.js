import fs from 'fs';
import path from 'path';
import { fileURLToPath } from 'node:url';
import parser from './typescript-parser.js';

const filename = fileURLToPath(import.meta.url);
const rootDir = path.dirname(path.dirname(filename));

const declarations = parser.parse(path.join(rootDir, 'node_modules/turms-client-js/dist/turms-client.d.ts'));
const data = `${JSON.stringify(declarations, null, 2)}`;
fs.writeFileSync(path.join(rootDir, 'ui/src/assets/turms-client-ast.json'), data);

console.info('Done');