const RAW_PROPERTIES = require('../../turms-server-common/src/test/resources/turms-properties-metadata-with-property-value.json');
const ROOT_PROPERTY = 'turms';
const HEADERS = [{
    key: 'name',
    zh: '配置项'
}, {
    key: 'global',
    zh: '全局属性'
}, {
    key: 'mutable',
    zh: '可变属性'
}, {
    key: 'type',
    zh: '数据类型'
}, {
    key: 'value',
    zh: '默认值'
}, {
    key: 'description',
    zh: '说明'
}];

function toKebabCase(str) {
    return str.replace(/[A-Z]/g, m => `-${m.toLowerCase()}`);
}

function collectProperties(parentPropertyName, properties, rows) {
    Object.entries(properties).forEach(([key, value]) => {
        let name = `${parentPropertyName}.${key}`;
        if (value.type == null || value.deprecated == null) {
            collectProperties(name, value, rows);
        } else {
            name = toKebabCase(`${parentPropertyName}.${key}`);
            const row = {
                ...value,
                name
            };
            rows.push(row);
        }
    });
}

const rows = [];
collectProperties(ROOT_PROPERTY, RAW_PROPERTIES, rows);
rows.sort((a, b) => a.name.localeCompare(b.name));

const title = `|${HEADERS.map(h => h.zh).join('|')}|`;
const separator = `|${'----|'.repeat(HEADERS.length)}`;
let content = '';

for (const row of rows) {
    content += '|';
    for (const header of HEADERS) {
        let value = row[header.key];
        if (header.key === 'value') {
            if (typeof value === 'object') {
                value = JSON.stringify(value, null, 2)
                    .replaceAll('\n', '<br/>');
            }
        } else if (header.key === 'type') {
            let index = value.lastIndexOf('.');
            value = value.substring(index + 1);
            const elementType = row.elementType;
            if (elementType) {
                index = Math.max(elementType.lastIndexOf('$'), elementType.lastIndexOf('.'));
                value += `-${elementType.substring(index + 1)}`;
            }
        } else if (header.key === 'description') {
            value = value?.replaceAll('\n', ' ') || '';
        } else {
            if (value === true) {
                value = '✅';
            } else if (value === false) {
                value = '';
            }
        }
        const text = `${value}|`;
        content += text;
    }
    content += '\n';
}

const data = [title, separator, content].join('\n');

console.info(data);