<template>
    <skeleton v-if="!initialized" />
    <div
        v-show="initialized"
        class="content-template"
    >
        <filter-group
            :filters="extendedFilters"
            :loading="loading"
            @onSearchClicked="onSearchClicked"
        />
        <action-group
            :members="members"
            :actions="actions"
            :deletion="deletion"
            :export-file-name="'turms-' + name"
            :has-records="!!records.length"
            :query-key="queryKey"
            :query-params="queryParams"
            :records-to-export="useTableDataForExport ? records : null"
            :resource-url="url + '/page'"
            :selected-records="selectedRecords"
            :selected-record-keys="selectedRecordKeys"
            :submit-url="url"
            :transform="transformExportData"
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
import Skeleton from '../../../common/skeleton.vue';
import ActionGroup from './action-group.vue';
import ContentTable from './table.vue';
import UiMixin from './ui-mixin';
import FilterGroup from './filter-group.vue';

const BUILTIN_NODE_ID_FILTER_NAME = '__nodeId__';

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
        clusterMode: {
            type: Boolean,
            default: false
        },
        disablePaginationIfFilterExists: {
            type: Boolean,
            default: false
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
        },
        transformExportData: {
            type: Function,
            required: false,
            default: null
        },
        useTableDataForExport: {
            type: Boolean,
            default: false
        }
    },
    emits: ['onDataInited', 'onRecordsDeleted', 'onRecordsUpdated'],
    data() {
        return {
            loading: false,
            initialData: [],
            records: [],
            sorter: null,
            selectedRecords: [],
            selectedRecordKeys: [],
            loaded: false,
            initialized: false,
            rowSelection: {
                onChange: (selectedRecordKeys) => {
                    this.updateSelectedRecordKeys(selectedRecordKeys);
                }
            },
            extendedFilters: this.filters,
            members: [],
            selectedMember: null
        };
    },
    computed: {
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
        records() {
            this.updateSelectedRecordKeys();
        },
        '$store.getters.tab'(val) {
            if (this.myTab === val) {
                setTimeout(() => this.$refs.table?.refreshTableUi());
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
        parseResponseRecords(data, nodeId = '') {
            data = this.transform ? this.transform(data) : data;
            const records = data instanceof Array
                ? data
                : data.records || [];
            records.forEach(record => {
                Object.entries(record).forEach(([key, value]) => {
                    if (this.$util.isBigNumber(value)) {
                        record[key] = value.toFixed();
                    } else if (key === this.recordKey && typeof value === 'object') {
                        Object.keys(value).forEach(subKey => {
                            record[`${key}.${subKey}`] = value[subKey];
                        });
                    }
                });
                let key = record[this.recordKey];
                if (nodeId) {
                    key = {
                        nodeId,
                        key
                    };
                }
                record.rowKey = JSONBig.stringify(key);
            });
            return records;
        },
        requestDelete(recordKeys) {
            if (!this.url) {
                return;
            }
            this.loading = true;
            let request;
            if (this.clusterMode) {
                const addressToKeys = {};
                for (const record of this.selectedRecords) {
                    const adminApiAddress = this.members.find(m => m.nodeId === record.nodeId).adminApiAddress;
                    const keys = addressToKeys[adminApiAddress] || [];
                    keys.push(record.rowKey);
                    addressToKeys[adminApiAddress] = keys;
                }
                const requests = Object.entries(addressToKeys).map(([address, keys]) => {
                    const params = this.$rq.getQueryParams(this.queryKey, keys);
                    return this.$http.delete(`${address}${this.url}${params}`);
                });
                request = Promise.all(requests);
            } else {
                const params = this.$rq.getQueryParams(this.queryKey, recordKeys);
                request = this.$http.delete(`${this.url}${params}`);
            }
            return request
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
                .catch(error => {
                    this.$error(this.$t('deleteFailed'), error);
                })
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
            if (this.clusterMode) {
                return this.requestRecordsForCluster();
            }
            this.loading = true;
            const enablePagination = !this.disablePaginationIfFilterExists
                || !Object.keys(this.getQueryParamsFromFilters()).length;
            const queryUrl = this.pageable && enablePagination
                ? this.url + '/page'
                : this.url;
            this.$http.get(queryUrl, { params: this.getQueryParams() })
                .then(response => {
                    const data = response.data?.data;
                    this.records = data ? this.parseResponseRecords(data) : [];
                    this.$refs.table.updatePaginateTotal(data?.total ?? this.records.length);
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
                    this.loading = false;
                });
        },
        requestRecordsForCluster() {
            this.loading = true;
            this.requestMembers()
                .then(() => {
                    this.updateExtendedFilters();
                    const selectedMemberId = this.extendedFilters
                        .find(filter => filter.id === BUILTIN_NODE_ID_FILTER_NAME).model;
                    const members = selectedMemberId === 'ALL'
                        ? this.members
                        : [this.members.find(member => member.nodeId === selectedMemberId)];
                    const config = { params: this.getQueryParams() };
                    const requests = members.map(member => {
                        const queryUrl = `${member.adminApiAddress}${this.url}`;
                        return this.$http.get(queryUrl, config)
                            .then(response => {
                                response.nodeId = member.nodeId;
                                return response;
                            });
                    });
                    return Promise.all(requests);
                })
                .then(responses => {
                    const records = [];
                    let count = 0;
                    for (const response of responses) {
                        const data = response.data?.data;
                        const nodeId = response.nodeId;
                        const items = data ? this.parseResponseRecords(data, nodeId) : [];
                        for (const item of items) {
                            item.nodeId = nodeId;
                            records.push(item);
                        }
                        count += data?.total ?? items.length;
                    }
                    this.records = records;
                    this.$refs.table.updatePaginateTotal(count);
                    this.loaded = true;
                })
                .catch(error => {
                    this.$error(this.$t('failedToFetchData'), error);
                })
                .finally(() => {
                    this.loading = false;
                });
        },
        requestMembers() {
            if (this.members.length) {
                return Promise.resolve();
            }
            return this.$apis.fetchMembersInfo()
                .then(members => {
                    this.members = members;
                    this.selectedMember = this.members[0];
                })
                .catch(error => {
                    this.$error(this.$t('failedToFetchClusterMembers'), error);
                });
        },
        updateExtendedFilters() {
            if (!this.clusterMode || this.extendedFilters?.[0].id === BUILTIN_NODE_ID_FILTER_NAME) {
                return;
            }
            const nodeIdFilter = {
                id: BUILTIN_NODE_ID_FILTER_NAME,
                name: 'nodeId',
                type: 'SELECT',
                model: 'ALL',
                options: {
                    base: [{
                        id: 'ALL',
                        label: 'allNodes'
                    }],
                    values: this.members.map(member => {
                        const nodeId = member.nodeId;
                        return {
                            id: nodeId,
                            rawLabel: nodeId
                        };
                    })
                }
            };
            this.extendedFilters = [
                nodeIdFilter,
                ...this.filters
            ];
        },
        updateSelectedRecordKeys(keys) {
            let records;
            if (!keys) {
                records = this.records
                    .filter(record => this.selectedRecordKeys.includes(record.rowKey));
                keys = records.map(record => record.rowKey);
            } else {
                records = keys.map(key => this.records.find(record => record.rowKey === key));
            }
            this.selectedRecords = records;
            this.selectedRecordKeys = keys;
            this.rowSelection.selectedRowKeys = keys;
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