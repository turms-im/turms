<template>
    <div class="cluster-dashboard-actions">
        <a-breadcrumb>
            <a-breadcrumb-item>
                {{ ' ' }}
            </a-breadcrumb-item>
            <a-breadcrumb-item>
                <a @click="goBack">
                    {{ $t('overview') }}
                </a>
            </a-breadcrumb-item>
            <a-breadcrumb-item v-if="nodeId">
                {{ nodeId }}
            </a-breadcrumb-item>
        </a-breadcrumb>
        <div class="cluster-dashboard-actions__action-group">
            <a-button
                class="cluster-dashboard-actions__action"
                :loading="exporting"
                @click="exportData"
            >
                <template #icon>
                    <icon type="export" />
                </template>
                {{ $t('export') }}
            </a-button>
            <a-button
                class="cluster-dashboard-actions__action"
                :loading="refreshing"
                @click="refresh"
            >
                <template #icon>
                    <icon type="sync" />
                </template>
                {{ $t('refresh') }}
            </a-button>
        </div>
    </div>
</template>
<script>
import Icon from '../../../common/icon';

export default {
    name: 'cluster-dashboard-actions',
    components: {
        Icon
    },
    props: {
        nodeId: {
            type: String,
            default: null
        },
        exporting: {
            type: Boolean,
            required: true
        },
        refreshing: {
            type: Boolean,
            required: true
        }
    },
    emits: ['export', 'goBack', 'refresh'],
    methods: {
        exportData() {
            this.$emit('export');
        },
        goBack() {
            this.$emit('goBack');
        },
        refresh() {
            this.$emit('refresh');
        }
    }
};
</script>
<style lang="scss">
.cluster-dashboard-actions {
    display: flex;
    justify-content: space-between;

    &__action {
        // FIXME: Set a fixed width, otherwise the buttons will jiggle when toggling loading.
        // This seems a bug of ant-design-vue, try it again after upgrading to v3.0
        width: 96px;
        margin-left: 12px;
    }
}
</style>