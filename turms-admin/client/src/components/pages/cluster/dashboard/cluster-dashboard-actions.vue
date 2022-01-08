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
    </div>
</template>

<script>
export default {
    name: 'cluster-dashboard-actions',
    props: {
        nodeId: {
            type: String,
            default: null
        }
    },
    emits: ['goBack'],
    methods: {
        goBack() {
            this.$emit('goBack');
        }
    }
};
</script>