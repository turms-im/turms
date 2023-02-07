import TrieSearch from 'trie-search';

export default {
    methods: {
        addDeclaration(trie, nodes): void {
            const registered = {};
            (nodes || []).forEach(node => {
                const name = node.name.toLowerCase();
                if (registered[name]) {
                    return;
                }
                registered[name] = true;
                trie.map(name, node);
            });
        },
        parseTrie(declaration: Record<string, string>) {
            const isSupported = declaration.syntax === 'class' || declaration.syntax === 'interface';
            if (!isSupported) {
                return;
            }
            const trie = new TrieSearch(null, {
                keepAll: true,
                cache: false
            });
            this.addDeclaration(trie, declaration.properties);
            this.addDeclaration(trie, declaration.accessors);
            this.addDeclaration(trie, declaration.methods);
            return trie;
        }
    }
};