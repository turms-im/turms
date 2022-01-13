const { path } = require('@vuepress/utils');

function mermaidPlugin(markdown) {
    const fence = markdown.renderer.rules.fence.bind(markdown.renderer.rules);
    markdown.renderer.rules.fence = (tokens, idx, options, env, self) => {
        const token = tokens[idx];
        const dsl = token.content.trim();
        if (token.info === 'mermaid') {
            return `<mermaid dsl="${dsl}"/>`;
        } else {
            return fence(tokens, idx, options, env, self);
        }
    };
}

module.exports = {
    name: 'mermaid-plugin',
    clientAppEnhanceFiles: [path.resolve(__dirname, './client-app-enhance.js')],
    extendsMarkdown: (markdown) => {
        markdown.use(mermaidPlugin);
    }
};