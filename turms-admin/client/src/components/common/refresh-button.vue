<template>
    <a-button
        :disabled="refreshing"
        type="primary"
        @click.stop="refresh"
    >
        {{ $t('refreshData') }}
    </a-button>
</template>
<script>
export default {
    name: 'refresh-button',
    props: {
        refreshFunc: {
            type: Function,
            required: true
        }
    },
    data() {
        return {
            refreshing: false
        };
    },
    methods: {
        refresh() {
            if (this.refreshing) {
                return;
            }
            this.refreshing = true;
            const hide = this.$message.loading(this.$t('refreshingData'), 0);
            this.refreshFunc()
                .then(() => {
                    this.$message.success(this.$t('refreshedDataSuccessfully'));
                })
                .catch(error => {
                    this.$error(this.$t('failedToRefreshData'), error);
                })
                .finally(() => {
                    setTimeout(hide);
                    this.refreshing = false;
                });
        }
    }
};
</script>