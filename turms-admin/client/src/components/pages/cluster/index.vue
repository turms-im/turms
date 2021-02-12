<template>
    <a-tabs default-active-key="1">
        <a-tab-pane
            key="1"
            :tab="$t('clusterConfig')"
        >
            <cluster-config-panel />
        </a-tab-pane>
    </a-tabs>
</template>

<script>
import ClusterConfigPanel from './config';

export default {
    name: 'cluster-management-tabs',
    components: {
        ClusterConfigPanel
    }
};
</script>