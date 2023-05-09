import { defineComponent } from 'vue';
import exportExcel from '../../../../utils/excel-export-util';

export default defineComponent({
    props: {
        queryParams: {
            type: Object,
            required: true
        },
        exportFileName: {
            type: String,
            required: true
        },
        recordsToExport: {
            type: Array,
            default: null
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
            this.$loading({
                promise: this.fetchData(),
                loading: 'exportingDataAsExcel',
                error: 'failedToExport',
                successCb: () => {
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
                },
                finallyCb: () => this.exporting = false
            });
        },
        fetchData() {
            if (this.recordsToExport) {
                this.records = this.parseResponseRecords(this.recordsToExport);
                this.total = this.recordsToExport.length;
                return Promise.resolve();
            }
            const queryParams = {
                ... this.queryParams,
                page: 0,
                size: 1000
            };
            // @ts-ignore
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
            return exportExcel(this.exportFileName, this.exportFileName, headers, rows);
        },
        parseResponseRecords(records) {
            records = this.transform ? this.transform(records) : records;
            if (!records) {
                return [];
            }
            return records.map(record => {
                Object.entries(record).forEach(([key, val]) => {
                    if (this.$util.isBigNumber(val)) {
                        record[key] = (val as any).toFixed();
                    } else if (val instanceof Array) {
                        record[key] = val.join(',');
                    }
                });
                return record;
            });
        }
    }
});