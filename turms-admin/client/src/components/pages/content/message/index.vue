<template>
    <a-tabs default-active-key="1">
        <a-tab-pane
            key="1"
            :tab="$t('messageInfo')"
        >
            <content-message-pane />
        </a-tab-pane>
        <a-tab-pane
            key="2"
            :tab="$t('messageStatus')"
        >
            <content-message-status-pane />
        </a-tab-pane>
    </a-tabs>
</template>

<script>
import ContentMessagePane from './content-message-pane';
import ContentMessageStatusPane from './content-message-status-pane';

export default {
    name: 'content-message-tabs',
    components: {
        ContentMessagePane,
        ContentMessageStatusPane
    }
};
</script>