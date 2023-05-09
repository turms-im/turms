<template>
    <a-tabs
        default-active-key="1"
        :animated="true"
    >
        <a-tab-pane
            key="1"
            :tab="$t('ipBlocklist')"
        >
            <blocklist-ip-pane />
        </a-tab-pane>
        <a-tab-pane
            key="2"
            :tab="$t('userBlocklist')"
        >
            <blocklist-user-pane />
        </a-tab-pane>
    </a-tabs>
</template>

<script>
import BlocklistIpPane from './blocklist-ip-pane.vue';
import BlocklistUserPane from './blocklist-user-pane.vue';

export default {
    name: 'blocklist-tabs',
    components: {
        BlocklistIpPane,
        BlocklistUserPane
    }
};
</script>