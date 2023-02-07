<template>
    <a-tabs
        default-active-key="1"
        :animated="true"
    >
        <a-tab-pane
            key="1"
            :tab="$t('messageInfo')"
        >
            <content-message-pane />
        </a-tab-pane>
    </a-tabs>
</template>

<script>
import ContentMessagePane from './content-message-pane.vue';

export default {
    name: 'content-message-tabs',
    components: {
        ContentMessagePane
    }
};
</script>