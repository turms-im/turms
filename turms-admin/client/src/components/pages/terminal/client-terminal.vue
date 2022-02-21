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
    </div>
</template>
<script>
import Terminal from './terminal';
import Icon from '../../common/icon';

export default {
    name: 'client-terminal',
    components: {
        Icon
    },
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
    mounted() {
        this.terminal = new Terminal(this.$refs.terminal, this.options);
    },
    beforeUnmount() {
        this.terminal.dispose();
    },
    methods: {
        clear() {
            this.terminal.clear();
        },
        onSettingClick() {
            this.$emit('settingClick');
        },
        getTerminal() {
            return this.terminal;
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

    .xterm-screen {
        canvas {
            padding: 8px;
        }
    }

    .xterm-viewport {
        width: 100% !important;
        overflow: hidden;
    }
}

</style>