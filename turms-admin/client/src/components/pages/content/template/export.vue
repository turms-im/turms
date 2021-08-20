<template>
    <a-button
        class="content-template-export"
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
    name: 'content-export',
    props: {
        resourceUrl: {
            type: String,
            required: true
        },
        queryParams: {
            type: Object,
            required: true
        },
        disabled: {
            type: Boolean
        },
        fileName: {
            type: String,
            required: true
        },
        transform: {
            type: Function,
            default: null
        }
    },
    data() {
        return {
            records: [],
            total: 0,
            exporting: false
        };
    },
    methods: {
        fetchAndExport() {
            if (this.exporting) {
                return;
            }
            this.exporting = true;
            const hide = this.$message.loading(this.$t('exportingDataAsExcel'), 0);
            this.fetchData()
                .then(() => {
                    if (!this.records.length) {
                        this.$message.info(this.$t('noRecordsToExport'));
                        return;
                    }
                    return this.exportData()
                        .then(() => {
                            if (this.total === this.records.length) {
                                this.$message.success(this.$t('exportAllRecordsSuccessfully'));
                            } else {
                                this.$message.success(this.$t('exportLimitedRecordsSuccessfully', {number: this.records.length}));
                            }
                        });
                })
                .catch(e => {
                    this.$message.error(this.$t('failedToExport', e));
                })
                .finally(() => {
                    setTimeout(hide);
                    this.exporting = false;
                });
        },
        fetchData() {
            const queryParams = Object.assign(this.queryParams, {
                page: 0,
                size: 1000
            });
            return this.$http.get(this.resourceUrl, {params: queryParams})
                .then(response => {
                    this.records = this.parseResponseRecords(response.data?.data?.records);
                    this.total = response.data?.total ?? 0;
                });
        },
        exportData() {
            const headers = Object.keys(this.records[0])
                .map(key => ({
                    header: key,
                    key,
                    width: this.$rs.excel.width
                }));
            const rows = this.records;
            return exportExcel(this.fileName, this.fileName, headers, rows);
        },
        parseResponseRecords(records) {
            records = this.transform ? this.transform(records) : records;
            if (!records) {
                return [];
            }
            return records.map(record => {
                Object.entries(record).forEach(([key, val]) => {
                    if (this.$util.isBigNumber(val)) {
                        record[key] = val.toFixed();
                    } else if (val instanceof Array) {
                        record[key] = val.join(',');
                    }
                });
                return record;
            });
        }
    }
};
</script>