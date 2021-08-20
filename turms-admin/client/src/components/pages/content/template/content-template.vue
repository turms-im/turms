<template>
    <skeleton v-if="!initialized" />
    <div
        v-show="initialized"
        class="content-template"
    >
        <filter-group
            :filters="filters"
            :loading="loading"
            @onSearchClicked="onSearchClicked"
        />
        <action-group
            :actions="actions"
            :deletion="deletion"
            :export-file-name="'turms-' + name"
            :has-records="records.length"
            :query-key="queryKey"
            :query-params="queryParams"
            :resource-url="url + '/page'"
            :selected-record-keys="selectedRecordKeys"
            :submit-url="url"
            :transform="transform"
            @onRecordCreated="onRecordCreated"
            @onRecordsUpdated="onRecordsUpdated"
            @requestDelete="requestDelete"
        />
        <content-table
            ref="table"
            :columns="tableColumns"
            :data-source="tableData"
            :loading="loading"
            :pageable="pageable"
            :selectable="selectable"
            :row-selection="rowSelection"
            @onPaginationChanged="onPaginationChanged"
            @requestDelete="requestDelete"
        />
    </div>
</template>

<script>
import JSONBig from 'json-bigint';
import ActionGroup from './action-group';
import ContentTable from './table';
import Skeleton from '../../../common/skeleton';
import UiMixin from './ui-mixin';
import FilterGroup from './filter-group';

export default {
    name: 'content-template',
    components: {
        ActionGroup,
        ContentTable,
        FilterGroup,
        Skeleton
    },
    mixins: [UiMixin],
    props: {
        name: {
            type: String,
            required: true
        },
        initialDataUrls: {
            type: Array,
            required: false,
            default: () => []
        },
        url: {
            type: String,
            default: ''
        },
        selectable: {
            type: Boolean,
            default: true
        },
        pageable: {
            type: Boolean,
            default: true
        },
        recordKey: {
            type: String,
            default: 'id'
        },
        queryKey: {
            type: [String, Object],
            default: 'ids'
        },
        fetchOnStatusChanged: {
            type: Boolean,
            default: true
        },
        queryParams: {
            type: Object,
            required: false,
            default: () => ({})
        },
        deletion: {
            type: Object,
            default: () => ({})
        },
        filters: {
            type: Array,
            default: () => []
        },
        actions: {
            type: Array,
            default: () => []
        },
        table: {
            type: Object,
            default: () => ({})
        },
        transform: {
            type: Function,
            required: false,
            default: null
        }
    },
    emits: ['onDataInited', 'onRecordsDeleted', 'onRecordsUpdated'],
    data() {
        return {
            loading: false,
            initialData: [],
            records: [],
            sorter: null,
            selectedRecordKeys: [],
            loaded: false,
            initialized: false,
            rowSelection: {
                onChange: (selectedRecordKeys) => {
                    this.selectedRecordKeys = selectedRecordKeys;
                }
            }
        };
    },
    computed: {
        queryUrl() {
            return this.pageable
                ? this.url + '/page'
                : this.url;
        },
        tableColumns() {
            return this.table.columns.map(column => {
                const fields = column.key.split('.');
                let sorter;
                if (column.key !== 'operation' && !['TREE'].includes(String(column.type).toUpperCase())) {
                    sorter = (a, b) => this.$util.sort(a[column.key], b[column.key]);
                }
                return {
                    title: (column.title && this.$t(column.title)) || this.$t(`${fields[fields.length - 1]}`),
                    dataIndex: column.key,
                    default: column.default,
                    type: column.type,
                    slots: {
                        customRender: column.key
                    },
                    width: column.width,
                    sorter
                };
            });
        },
        tableData() {
            // Must copy records, or <a-table> will cause "Error: Maximum recursive updates exceeded."
            return this.$util.copy(this.records);
        },
        admin() {
            return this.$store.getters.admin;
        },
        hasSelectedRows() {
            return this.selectedRecordKeys.length;
        }
    },
    watch: {
        admin() {
            if (this.fetchOnStatusChanged) {
                this.load();
            }
        },
        '$store.getters.tab'(val) {
            if (this.myTab === val) {
                setTimeout(() => this.$refs.table.refreshTableUi());
            }
        }
    },
    mounted() {
        this.myTab = this.$store.getters.tab;
        if (!this.myTab) {
            throw new Error('Failed to get the tab of the current page');
        }
        if (this.fetchOnStatusChanged) {
            this.load();
        } else {
            this.loaded = true;
            this.initialized = true;
        }
    },
    methods: {
        load() {
            if (this.admin) {
                if (!this.loaded) {
                    this.requestRecords();
                }
                if (!this.initialized) {
                    this.requestInitialData();
                }
            }
        },
        getQueryParams() {
            const params = this.getQueryParamsFromFilters();
            const pageParams = this.getQueryParamsFromPagination();
            return Object.assign(params, pageParams, this.queryParams);
        },
        getQueryParamsFromFilters() {
            const params = {};
            this.filters.forEach(filter => {
                let data = this.getIfValid(filter.model);
                if (data == null) {
                    return;
                }
                if (filter.type.toUpperCase() === 'SELECT' && typeof data === 'string') {
                    data = data.toUpperCase();
                    if (data === 'ALL') {
                        return;
                    }
                }
                if (filter.type.toUpperCase() === 'DATE-RANGE' && data && !filter.name.endsWith('StartDate') && !filter.name.endsWith('EndDate')) {
                    params[`${filter.name}Start`] = this.getTimeIfNotNull(data[0]);
                    params[`${filter.name}End`] = this.getTimeIfNotNull(data[1]);
                } else {
                    params[filter.name] = data;
                }
            });
            return params;
        },
        getQueryParamsFromPagination() {
            const pageParams = {};
            if (this.pageable) {
                const pagination = this.$refs.table.pagination || {
                    current: 1,
                    pageSize: 20
                };
                pageParams.page = pagination.current - 1;
                pageParams.size = pagination.pageSize;
            }
            return pageParams;
        },
        getIfValid(val) {
            if (typeof val === 'number' || typeof val === 'boolean') {
                return val;
            } else if (val instanceof Array) {
                if (val.length) {
                    return val;
                }
            } else if (val) {
                return val;
            }
        },
        getTimeIfNotNull(object) {
            return object?.format() || '';
        },
        onPaginationChanged() {
            this.requestRecords();
        },
        onRecordCreated() {
            this.requestRecords();
        },
        onRecordsUpdated(recordKeys, updatedFields) {
            this.records = this.records.map(record => {
                if (recordKeys.includes(record.rowKey)) {
                    Object.assign(record, updatedFields);
                }
                return record;
            });
            this.$emit('onRecordsUpdated', recordKeys, updatedFields);
        },
        onSearchClicked() {
            this.$refs.table.goToFirst();
            this.requestRecords();
        },
        parseResponseRecords(data) {
            data = this.transform ? this.transform(data) : data;
            return data.records.map(record => {
                Object.entries(record).forEach(([key, value]) => {
                    if (this.$util.isBigNumber(value)) {
                        record[key] = value.toFixed();
                    } else if (key === this.recordKey && typeof value === 'object') {
                        Object.keys(value).forEach(subKey => {
                            record[`${key}.${subKey}`] = value[subKey];
                        });
                    }
                });
                record.rowKey = JSONBig.stringify(record[this.recordKey]);
                return record;
            });
        },
        requestDelete(recordKeys) {
            if (!this.url) {
                return;
            }
            this.loading = true;
            const params = this.$rq.getQueryParams(this.queryKey, recordKeys);
            return this.$http.delete(`${this.url}${params}`)
                .then(() => {
                    this.$message.success(this.$t('deletedSuccessfully'));
                    if (this.deletion.refresh) {
                        this.requestRecords();
                    } else {
                        this.records = this.records
                            .filter(record => !recordKeys.includes(record.rowKey));
                    }
                    this.$emit('onRecordsDeleted', recordKeys);
                })
                .catch(error => this.$error(this.$t('deleteFailed'), error))
                .finally(() => this.loading = false);
        },
        requestInitialData() {
            if (!this.initialDataUrls.length) {
                this.initialized = true;
                return;
            }
            const promises = this.initialDataUrls.map(url => this.$http.get(url));
            Promise.all(promises)
                .catch(() => {
                    setTimeout(() => this.requestInitialData(), 3000);
                })
                .then(responseList => {
                    this.initialized = true;
                    this.$emit('onDataInited', responseList);
                });
        },
        requestRecords() {
            this.loading = true;
            this.$http.get(this.queryUrl, {params: this.getQueryParams()})
                .then(response => {
                    const data = response.data?.data || {};
                    this.records = this.parseResponseRecords(data);
                    this.$refs.table.updatePaginateTotal(data.total ?? 0);
                    this.loaded = true;
                })
                .catch(error => {
                    if (error.response?.status === 404) {
                        this.records = [];
                        this.total = 0;
                    }
                    this.$error(this.$t('failedToFetchData'), error);
                })
                .finally(() => {
                    this.selectedRecordKeys = this.selectedRecordKeys
                        .filter(key => this.records.some(record => record.rowKey === key));
                    this.loading = false;
                });
        }
    }
};
</script>
<style lang="scss">
.content-template {
    display: flex;
    flex-direction: column;
}

</style>