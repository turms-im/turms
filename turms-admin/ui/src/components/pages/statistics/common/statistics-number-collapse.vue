<template>
    <a-collapse
        accordion
        default-active-key="1"
        @change="onKeyChanged"
    >
        <a-collapse-panel
            key="1"
            :header="title"
        >
            <div class="statistics-number-collapse__header">
                <div>
                    <a-select
                        v-model:value="dateMode"
                        class="statistics-number-collapse__header__select"
                    >
                        <a-select-option value="day">
                            {{ $t('byDay') }}
                        </a-select-option>
                        <a-select-option value="month">
                            {{ $t('byMonth') }}
                        </a-select-option>
                    </a-select>
                    <date-range-picker
                        v-model:value="dateRange"
                        class="search-filter"
                        :is-month-mode="dateMode === 'month'"
                        :use-default-date="true"
                        :allow-clear="false"
                    />
                    <a-button
                        type="primary"
                        class="search-button"
                        @click="search"
                    >
                        {{ $t('search') }}
                    </a-button>
                </div>
                <div>
                    <a-dropdown-button
                        class="statistics-number-collapse__header__item"
                        type="primary"
                        @click="exportSvg"
                    >
                        {{ dataFormat === 'png' ? $t('exportPng') : $t('exportSvg') }}
                        <template #overlay>
                            <a-menu @click="changeDataFormat">
                                <a-menu-item key="png">
                                    {{ $t('exportPng') }}
                                </a-menu-item>
                                <a-menu-item key="svg">
                                    {{ $t('exportSvg') }}
                                </a-menu-item>
                            </a-menu>
                        </template>
                    </a-dropdown-button>
                    <export-button
                        class="statistics-number-collapse__header__item"
                        :headers="headers"
                        :rows="rows"
                        :file-name="fileName"
                        :worksheet-name="worksheetName"
                    />
                </div>
            </div>
            <a-spin :spinning="isLoading">
                <statistics-chart-line
                    ref="chart"
                    :statistics-data="records"
                    :is-month-mode="dateMode === 'month'"
                />
            </a-spin>
        </a-collapse-panel>
    </a-collapse>
</template>

<script>
import {saveSvg, saveSvgAsPng} from 'save-svg-as-png';
import DateRangePicker from '../../../common/date-range-picker.vue';
import ExportButton from '../../../common/export-button.vue';
import StatisticsChartLine from './statistics-chart-line.vue';

export default {
    name: 'statistics-number-collapse',
    components: {
        DateRangePicker,
        StatisticsChartLine,
        ExportButton
    },
    props: {
        url: {
            type: String,
            required: true
        },
        name: {
            type: String,
            required: true
        },
        params: {
            type: Object,
            default: () => ({})
        },
        worksheetName: {
            type: String,
            required: true
        },
        title: {
            type: String,
            required: true
        },
        resources: {
            type: Array,
            required: true
        },
        options: {
            type: Array,
            required: true
        }
    },
    data() {
        return {
            dateRange: [],
            records: [],
            dateMode: 'day',
            headers: [],
            rows: [],
            dataFormat: 'png',
            isLoading: false
        };
    },
    computed: {
        admin() {
            return this.$store.getters.admin;
        },
        fileName() {
            if (this.dateRange.length) {
                const format = this.dateMode === 'day' ? 'Y-MM-DD' : 'Y-MM';
                const startDate = this.$date(this.dateRange[0]).format(format);
                const endDate = this.$date(this.dateRange[1]).format(format);
                return `turms-statistics-${this.name}(${startDate}~${endDate})`;
            } else {
                return `turms-statistics-${this.name}`;
            }
        }
    },
    watch: {
        admin() {
            if (this.admin) {
                this.search();
            }
        }
    },
    mounted() {
        if (this.admin) {
            this.search();
        }
    },
    methods: {
        onKeyChanged(key) {
            if (key) {
                this.refresh();
            }
        },
        changeDataFormat(item) {
            this.dataFormat = item.key;
        },
        exportSvg() {
            const svg = this.$refs.chart.$el.querySelector('svg');
            if (this.dataFormat === 'png') {
                saveSvgAsPng(svg, `${this.fileName}.png`);
            } else {
                saveSvg(svg, `${this.fileName}.svg`);
            }
        },
        refresh() {
            this.$refs.chart.refresh();
        },
        search() {
            this.isLoading = true;
            const hide = this.$message.loading(this.$t('refreshingData'), 0);
            if (this.dateRange.length === 2) {
                const startDate = this.$date(this.dateRange[0]).startOf(this.dateMode);
                const endDate = this.$date(this.dateRange[1]).add(1, `${this.dateMode}s`).startOf(this.dateMode);
                const params = this.$rq.generateDateRangeParams(
                    this.resources,
                    startDate.utcOffset(-new Date().getTimezoneOffset()).format(),
                    endDate.utcOffset(-new Date().getTimezoneOffset()).format());
                params.divideBy = this.dateMode.toUpperCase();
                Object.assign(params, this.params || {});
                this.$http.get(this.url, {params})
                    .then(response => {
                        this.records = this.transformDate(response.data.data);
                        const headers = [];
                        const width = this.$rs.excel.width;
                        headers.push({
                            header: this.$t('date'),
                            key: 'date',
                            width
                        });
                        Object.keys(this.records[0]).forEach(value => {
                            if (value !== 'date') {
                                headers.push({
                                    header: this.$t(`${value}Number`),
                                    key: value,
                                    width
                                });
                            }
                        });
                        this.headers = headers;
                        this.rows = [];
                        const format = this.dateMode === 'day' ? 'Y-MM-DD' : 'Y-MM';
                        for (const record of this.records) {
                            const row = {};
                            for (const header of headers) {
                                if (header.key === 'date') {
                                    row['date'] = this.$date(record.date)
                                        .format(format);
                                } else {
                                    row[header.key] = record[header.key];
                                }
                            }
                            this.rows.push(row);
                        }
                        this.$message.success(this.$t('fetchDataSuccessfully'));
                    })
                    .catch(error => {
                        this.$error(this.$t('failedToFetchData'), error);
                    })
                    .finally(() => {
                        setTimeout(hide);
                        this.isLoading = false;
                    });
            }
        },
        transformDate(data) {
            const result = {};
            Object.keys(data).forEach(key => {
                data[key].forEach(record => {
                    const source = {};
                    source[key] = record.total;
                    result[record.date] = Object.assign(result[record.date] || {}, source);
                });
            });
            return Object.entries(result)
                .map(([date, value]) => {
                    const record = {};
                    record.date = date;
                    for (const [itemKey, itemValue] of Object.entries(value)) {
                        record[itemKey.replace(/Records$/, '')] = itemValue;
                    }
                    return record;
                });
        }
    }
};
</script>
<style lang="scss" scoped>
.statistics-number-collapse__header {
    display: flex;
    justify-content: space-between;
    margin-top: 8px;

    & &__item {
        margin-bottom: 18px;
        margin-left: 16px;
    }

    & &__select {
        width: 100px;
        margin-right: 16px;
    }
}

.search-button {
    margin-bottom: 18px;
}
</style>