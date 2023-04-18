export default (md) => {
    const fence = md.renderer.rules.fence.bind(md.renderer.rules);
    md.renderer.rules.fence = (tokens, idx, options, env, self) => {
        const token = tokens[idx];
        const dsl = token.content.trim();
        if (token.info === 'mermaid') {
            return `<mermaid dsl="${dsl}"/>`;
        } else {
            return fence(tokens, idx, options, env, self);
        }
    };
}