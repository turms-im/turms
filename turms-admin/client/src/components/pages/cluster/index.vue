<template>
    <a-tabs
        default-active-key="1"
        :animated="true"
    >
        <a-tab-pane
            key="1"
            :tab="$t('clusterConfig')"
        >
            <cluster-config-panel />
        </a-tab-pane>
    </a-tabs>
</template>

<script>
import ClusterConfigPanel from './config.vue';

export default {
    name: 'cluster-management-tabs',
    components: {
        ClusterConfigPanel
    }
};
</script>