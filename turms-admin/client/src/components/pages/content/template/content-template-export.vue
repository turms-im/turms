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
import JSONBig from 'json-bigint';

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
        },
        transform: {
            type: Function,
            required: false,
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
    computed: {
        searchParams() {
            const params = JSONBig.parse(JSONBig.stringify(this.params));
            return Object.assign(params, {
                page: 0,
                size: 1000
            });
        },
        headers() {
            return Object.keys(this.records[0])
                .map(key => ({
                    header: key,
                    key,
                    width: this.$rs.excel.width
                }));
        },
        rows() {
            return this.records;
        }
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
                .catch(e => {
                    this.$message.error(this.$t('failedToExport', e));
                })
                .finally(() => {
                    setTimeout(hide);
                    this.exporting = false;
                });
        },
        fetchData() {
            return this.$http.get(this.url, {params: this.searchParams})
                .then(response => {
                    if (response.status === 204) {
                        this.records = [];
                        this.total = 0;
                    } else {
                        const data = this.transform
                            ? this.transform(response.data.data)
                            : response.data.data;
                        this.records = data.records.map(record => {
                            Object.entries(record).forEach(([key, val]) => {
                                if (val._isBigNumber) {
                                    record[key] = val.toFixed();
                                } else if (val instanceof Array) {
                                    record[key] = val.join(',');
                                }
                            });
                            return record;
                        });
                        this.total = data.total;
                    }
                });
        },
        exportData() {
            exportExcel(this.fileName, this.fileName, this.headers, this.rows);
        }
    }
};
</script>