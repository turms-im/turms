<template>
    <div
        class="client-terminal"
        :class="$attrs.class"
    >
        <div class="client-terminal__header">
            <span>{{ title }}</span>
            <span class="client-terminal__header-settings">
                <span
                    class="client-terminal__header-setting"
                    @click="clear"
                >
                    <icon type="clear" />
                </span>
                <span
                    v-if="showSetting"
                    class="client-terminal__header-setting"
                    @click="onSettingClick"
                >
                    <icon type="setting" />
                </span>
            </span>
        </div>
        <div
            ref="terminal"
            class="client-terminal__container"
        />
        <autocomplete
            v-if="options.ast"
            ref="autocomplete"
            :items="astItems"
            @selectItem="selectItem"
        />
    </div>
</template>
<script>
import TrieSearch from 'trie-search';
import Icon from '../../common/icon.vue';
import AstMixin from './ast-mixin';
import Autocomplete from './autocomplete.vue';
import Terminal from './terminal';

export default {
    name: 'client-terminal',
    components: {
        Autocomplete,
        Icon
    },
    mixins: [AstMixin],
    props: {
        title: {
            type: String,
            required: true
        },
        showSetting: {
            type: Boolean,
            default: false
        },
        options: {
            type: Object,
            required: true
        }
    },
    data() {
        return {
            astItems: []
        };
    },
    mounted() {
        if (this.options.ast) {
            this.allDeclarations = {};
            for (const declaration of this.options.ast) {
                this.allDeclarations[declaration.name] = declaration;
            }
            this.rootTrie = new TrieSearch(null, {
                keepAll: true,
                cache: false
            });
            for (const declaration of this.options.astRoot) {
                this.rootTrie.map(declaration.name.toLowerCase(), declaration);
            }
        }
        let options;
        if (this.options.disableStdin) {
            options = this.options;
        } else {
            options = {
                ...this.options,
                onCursorChanged: this.onCursorChanged,
                onInputChanged: this.onInputChanged,
                handleInputEvent: this.handleInputEvent
            };
        }
        this.terminal = new Terminal(this.$refs.terminal, options);
    },
    beforeUnmount() {
        this.terminal.dispose();
    },
    methods: {
        selectItem(item) {
            const terms = this.terminal.currentLine.split('.');
            terms.pop();
            const line = terms.concat(item.name).join('.');
            this.terminal.writeLine(line, true);
            this.terminal.focus();
        },
        clear() {
            this.terminal.clear();
        },
        getTerminal() {
            return this.terminal;
        },
        handleInputEvent(event) {
            const autocomplete = this.$refs.autocomplete;
            if (autocomplete?.isVisible) {
                if (event.key === 'Enter') {
                    autocomplete.select();
                    return true;
                } else if (event.key === 'ArrowDown') {
                    autocomplete.scrollDown();
                    return true;
                } else if (event.key === 'ArrowUp') {
                    autocomplete.scrollUp();
                    return true;
                }
            }
            return false;
        },
        onCursorChanged(x, y, rowHeight) {
            this.$refs.autocomplete?.updatePosition(x, y, rowHeight);
        },
        onInputChanged(text) {
            // TODO: support param suggestion
            const terms = text.split('.');
            let trie = this.rootTrie;
            const length = terms.length;
            for (let i = 0; i < length; i++) {
                const term = terms[i];
                const matches = trie.search(term.toLowerCase());
                if (i === length - 1) {
                    if (trie && matches[0]?.name !== term) {
                        this.astItems = term ? matches : trie.indexed.all;
                    } else {
                        this.astItems = [];
                    }
                } else {
                    const match = matches.find(item => item.name === term);
                    const declaration = match == null ? null : this.allDeclarations[match.type];
                    if (declaration) {
                        trie = this.parseTrie(declaration);
                    } else {
                        break;
                    }
                }
            }
        },
        onSettingClick() {
            this.$emit('settingClick');
        }
    }
};
</script>
<style lang="scss">
.client-terminal {
    display: flex;
    flex-direction: column;
    height: 100%;

    &__header {
        display: flex;
        flex-direction: row;
        align-items: center;
        justify-content: space-between;
        padding: 8px 12px;
        font-size: 16px;
        font-weight: 500;
        color: #777;
        background-color: #fafafa;
        border: 1px solid #d9d9d9;
        border-radius: 4px 4px 0 0;
    }

    &__header-setting {
        margin-left: 16px;
        cursor: pointer;
    }

    &__container {
        height: 100%;
    }

    .xterm {
        padding: 8px;
    }

    .xterm-viewport {
        width: 100% !important;
        overflow: hidden;
    }
}

</style>