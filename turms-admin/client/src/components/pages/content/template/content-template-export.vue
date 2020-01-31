<template>
    <a-button
        :disabled="disabled"
        :loading="exporting"
        type="primary"
        @click="fetchAndExport"
    >
        {{ $t('exportData') }}
    </a-button>
</template>
<script>
import exportExcel from '../../../../utils/excel-export-util';

export default {
    name: 'content-template-export',
    props: {
        url: {
            type: String,
            required: true
        },
        params: {
            type: Object,
            required: true
        },
        disabled: {
            type: Boolean,
            default: false
        },
        fileName: {
            type: String,
            required: true
        }
    },
    data() {
        return {
            records: [],
            total: 0,
            exporting: false
        };
    },
    computed: {
        headers() {
            const headers = [];
            const width = this.$rs.excel.width;
            for (const entry of Object.entries(this.records[0])) {
                const key = entry[0];
                headers.push({
                    header: key,
                    key,
                    width: width
                });
            }
            return headers;
        },
        rows() {
            const rows = [];
            for (const record of this.records) {
                const row = {};
                for (const entry of Object.entries(record)) {
                    row[entry[0]] = entry[1];
                }
                rows.push(row);
            }
            return rows;
        }
    },
    methods: {
        fetchAndExport() {
            if (this.exporting) {
                return;
            }
            this.exporting = true;
            const hide = this.$message.loading(this.$t('exportingData'), 0);
            this.fetchData()
                .then(() => {
                    if (this.records.length === 0) {
                        this.$message.info(this.$t('noRecordsToExport'));
                    } else {
                        return this.exportData();
                    }
                })
                .then(() => {
                    if (this.total === this.records.length) {
                        this.$message.success(this.$t('exportAllRecordsSuccessfully'));
                    } else {
                        this.$message.success(this.$t('exportLimitedRecordsSuccessfully', {number: this.records.length}));
                    }
                })
                .catch(() => {
                    this.$message.error(this.$t('failedToExport'));
                })
                .finally(() => {
                    setTimeout(hide);
                    this.exporting = false;
                });
        },
        fetchData() {
            return this.$client.get(this.url, {params: this.params})
                .then(response => {
                    if (response.status === 204) {
                        this.records = [];
                        this.total = 0;
                    } else {
                        this.records = response.data.data.records;
                        this.total = response.data.data.total;
                    }
                });
        },
        exportData() {
            exportExcel(this.fileName, this.fileName, this.headers, this.rows);
        }
    }
};
</script>

<style lang="scss" scoped>
    .content-template-export {
        display: flex;
        &__range {
            margin-left: 24px;
        }
        &__from {
            width: 100px;
            text-align: center
        }
        &__between {
            width: 30px;
            border-left: 0;
            pointer-events: none;
            background-color: #fff
        }
        &__to {
            width: 100px;
            text-align: center;
            border-left: 0;
        }
    }
</style>