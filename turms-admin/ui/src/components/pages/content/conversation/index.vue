<template>
    <a-tabs
        default-active-key="1"
        :animated="true"
    >
        <a-tab-pane
            key="1"
            :tab="$t('privateConversationInfo')"
        >
            <content-private-conversation-pane />
        </a-tab-pane>
        <a-tab-pane
            key="2"
            :tab="$t('groupConversationInfo')"
        >
            <content-group-conversation-pane />
        </a-tab-pane>
    </a-tabs>
</template>

<script>
import ContentPrivateConversationPane from './content-private-conversation-pane.vue';
import ContentGroupConversationPane from './content-group-conversation-pane.vue';

export default {
    name: 'content-conversation-tabs',
    components: {
        ContentPrivateConversationPane,
        ContentGroupConversationPane
    }
};
</script>