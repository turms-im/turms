<template>
    <a-tabs
        v-model:active-key="activeKey"
        :animated="true"
    >
        <a-tab-pane
            key="private-chat"
            :tab="$t('privateChat')"
        >
            <statistics-message-private-chat-pane />
        </a-tab-pane>
        <a-tab-pane
            key="group-chat"
            :tab="$t('groupChat')"
        >
            <statistics-message-group-chat-pane />
        </a-tab-pane>
    </a-tabs>
</template>

<script>
import StatisticsMessagePrivateChatPane from './statistics-message-private-chat-pane.vue';
import StatisticsMessageGroupChatPane from './statistics-message-group-chat-pane.vue';

export default {
    name: 'statistics-message-tabs',
    components: {
        StatisticsMessagePrivateChatPane,
        StatisticsMessageGroupChatPane
    },
    data() {
        return {
            activeKey: 'private-chat'
        };
    },
    watch: {
        activeKey() {
            this.$es.$emit('tabChanged', `/statistics/message/${this.activeKey}`);
        }
    }
};
</script>