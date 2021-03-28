<template>
    <skeleton v-if="!initialized" />
    <div
        v-else
        class="content-template"
    >
        <div class="content-template__filter-group">
            <template v-for="(filter, index) in filters">
                <custom-input
                    v-if="filter.type.toUpperCase() === 'INPUT'"
                    :key="index"
                    v-model:value="filter.model"
                    :placeholder="$t(filter.placeholder)"
                    :only-number-and-comma="filter.rules == null || filter.rules.onlyNumberAndComma"
                    :non-space="filter.rules?.nonSpace"
                    class="search-filter search-filter__input"
                />
                <a-select
                    v-if="filter.type.toUpperCase() === 'SELECT'"
                    :key="index"
                    v-model:value="filter.model"
                    class="search-filter search-filter__select"
                >
                    <a-select-option
                        v-for="option in (filter.options.base || []).concat(filter.options.values || [])"
                        :key="option.id.toString()"
                        :value="option.id.toString()"
                    >
                        {{ $t(option.label) }}
                    </a-select-option>
                </a-select>
                <date-range-picker
                    v-if="filter.type.toUpperCase() === 'DATE-RANGE'"
                    :key="index"
                    v-model:value="filter.model"
                    :include-today="true"
                    :show-time="true"
                    :placeholder="getDatePickerPlaceholder(filter)"
                    class="search-filter search-filter__date-picker"
                />
                <a-date-picker
                    v-if="filter.type.toUpperCase() === 'DATE'"
                    :key="index"
                    v-model:value="filter.model"
                    :show-time="filter.showTime ?? true"
                />
            </template>
            <a-button
                class="search-filter"
                type="primary"
                :loading="loading"
                @click="search"
            >
                {{ filters.length ? $t('search') : $t('refresh') }}
            </a-button>
            <a-button
                v-if="filters.length"
                class="search-filter"
                type="danger"
                :loading="loading"
                @click="clearFilters"
            >
                {{ $t('clearFilters') }}
            </a-button>
        </div>
        <div class="action-groups">
            <div
                v-for="(actions, index) in actionGroups"
                :key="index"
                class="action-group"
            >
                <button-modal-template
                    v-for="(action, actionIndex) in actions"
                    :key="actionIndex"
                    :button-label="$t(action.buttonLabel || action.title)"
                    :title="$t(action.title)"
                    :fields="action.fields"
                    :type="action.type"
                    :query-key="queryKey"
                    :keys="selectedRowKeys"
                    :url="url"
                    :disabled="action.type === 'UPDATE' && !hasSelectedRows"
                    :params="action.params || {}"
                    :size="action.size"
                    class="action-button"
                    @onDataUpdated="onRecordsUpdated"
                />
                <a-popconfirm
                    v-if="!deletion.disabled"
                    class="action-button"
                    :visible="popconfirmVisible"
                    :title="$t('confirmDeletion')"
                    @visibleChange="hanlePopconfirmVisibleChange"
                    @confirm="deleteSelectedRows"
                >
                    <a-button
                        type="danger"
                        :disabled="!hasSelectedRows"
                    >
                        {{ $t('deleteSelectedRecords') }}
                    </a-button>
                </a-popconfirm>
            </div>
            <div class="action-group">
                <content-template-export
                    :url="queryUrl"
                    :params="searchParams"
                    :disabled="!records.length"
                    :file-name="'turms-' + name"
                    :transform="transform"
                    class="action-button"
                />
            </div>
        </div>
        <a-spin :spinning="loading">
            <a-table
                ref="table"
                size="small"
                row-key="rowKey"
                :columns="columnsData"
                :data-source="tableData"
                :pagination="pageable ? pagination : false"
                :row-selection="selectable ? rowSelection : undefined"
                :scroll="{ y: 400 }"
                bordered
                class="content-table"
                @change="handleTableChange"
            >
                <template
                    v-for="column in columnsData"
                    #[column.dataIndex]="{ text, record }"
                >
                    <div
                        v-if="column.type === 'tree'"
                        :key="column.dataIndex"
                    >
                        <a-tree
                            :tree-data="text"
                            :show-icon="true"
                        >
                            <template #bars>
                                <span class="content-template__list-icon">
                                    <icon type="bars" />
                                </span>
                            </template>
                            <template #close>
                                <span class="content-template__close-icon">
                                    <icon type="close" />
                                </span>
                            </template>
                            <template #check>
                                <span class="content-template__check-icon">
                                    <icon type="check" />
                                </span>
                            </template>
                        </a-tree>
                    </div>
                    <div
                        v-else-if="column.dataIndex === 'operation'"
                        :key="column.dataIndex"
                        class="editable-row-operations"
                    >
                        <span>
                            <a-popconfirm
                                :title="$t('confirmDeletion')"
                                @confirm="requestDelete([record.rowKey])"
                            >
                                <a>{{ $t('delete') }}</a>
                            </a-popconfirm>
                        </span>
                    </div>
                    <div
                        v-else
                        :key="column.dataIndex"
                    >
                        {{ getColumnValue(column, record) }}
                    </div>
                </template>
            </a-table>
        </a-spin>
    </div>
</template>

<script>
import JSONBig from 'json-bigint';
import Icon from '../../../common/icon';
import Skeleton from '../../../common/skeleton';
import CustomInput from '../../../common/custom-input';
import ButtonModalTemplate from './button-modal-template';
import DateRangePicker from '../../../common/date-range-picker';
import ContentTemplateExport from './content-template-export';

export default {
    name: 'content-template',
    components: {
        Icon,
        Skeleton,
        ButtonModalTemplate,
        CustomInput,
        DateRangePicker,
        ContentTemplateExport
    },
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
        params: {
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
        actionGroups: {
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
    emits: ['onDataInited'],
    data() {
        return {
            loading: false,
            initialData: [],
            records: [],
            activeKey: 1,
            sorter: null,
            pagination: {
                current: 1,
                pageSize: 20
            },
            popconfirmVisible: false,
            selectedRowKeys: [],
            loaded: false,
            initialized: false,
            rowSelection: {
                onChange: (selectedRowKeys) => {
                    this.selectedRowKeys = selectedRowKeys;
                }
            },
            lastFilters: {}
        };
    },
    computed: {
        queryUrl() {
            return this.pageable
                ? this.url + '/page'
                : this.url;
        },
        searchParams() {
            const params = {};
            this.filters.forEach(filter => {
                let data = this.getIfValid(filter.model);
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
            if (JSONBig.stringify(this.lastFilters) !== JSONBig.stringify(params)) {
                // eslint-disable-next-line vue/no-side-effects-in-computed-properties
                this.pagination.current = 1;
            }

            const pageParams = this.pageable ? {
                page: this.pagination.current - 1,
                size: this.pagination.pageSize
            } : {};
            Object.assign(params, pageParams, this.params);
            return params;
        },
        columnsData() {
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
            return JSON.parse(JSON.stringify(this.records));
        },
        admin() {
            return this.$store.getters.admin;
        },
        hasSelectedRows() {
            return !!this.selectedRowKeys.length;
        }
    },
    watch: {
        admin() {
            if (this.fetchOnStatusChanged) {
                this.load();
            }
        }
    },
    mounted() {
        if (this.fetchOnStatusChanged) {
            this.load();
        } else {
            this.loaded = true;
            this.initialized = true;
        }
    },
    methods: {
        clearFilters() {
            for (const filter of this.filters) {
                switch (filter.type) {
                case 'SELECT': {
                    let model = null;
                    for (const value of filter.options.base) {
                        if (value.id === 'ALL') {
                            model = 'ALL';
                            break;
                        }
                    }
                    filter.model = model;
                }
                    break;
                case 'DATE-RANGE':
                    filter.model = [];
                    break;
                default:
                    filter.model = null;
                }
            }
        },
        getDatePickerPlaceholder(filter) {
            if (filter.placeholder) {
                return this.$t(filter.placeholder);
            }
            return [this.$t(`${filter.name}Range.start`), this.$t(`${filter.name}Range.end`)];
        },
        load() {
            if (this.admin) {
                if (!this.loaded) {
                    this.search();
                }
                if (!this.initialized) {
                    this.init();
                }
            }
        },
        getColumnValue(column, record) {
            let value = this.$_.get(record, column.dataIndex, column.default);
            if (value instanceof Array) {
                value = value.join(',');
            } else if (column.dataIndex.endsWith('Date') && value) {
                value = this.$moment(value).format();
            }
            return value;
        },
        hanlePopconfirmVisibleChange(visible) {
            this.popconfirmVisible = visible && this.hasSelectedRows;
        },
        handleTableChange(pagination, filters, sorter) {
            if (JSON.stringify(sorter) === JSON.stringify(this.sorter)) {
                this.pagination = {...this.pagination, current: pagination.current};
                if (this.pageable) {
                    this.search();
                }
            }
        },
        init() {
            if (this.initialDataUrls.length) {
                const promises = this.initialDataUrls.map(url => this.$http.get(url));
                Promise.all(promises)
                    .catch(() => {
                        setTimeout(() => this.init(), 3000);
                    })
                    .then(responseList => {
                        this.initialized = true;
                        this.$emit('onDataInited', responseList);
                    });
            } else {
                this.initialized = true;
            }
        },
        getIfValid(object) {
            if (typeof object === 'number' || typeof object === 'boolean') {
                return object;
            } else if (object instanceof Array) {
                if (object.length) {
                    return object;
                }
            } else if (object) {
                return object;
            }
        },
        getTimeIfNotNull(object) {
            return object?.format();
        },
        search() {
            this.loading = true;
            this.$http.get(this.queryUrl, {params: this.searchParams})
                .then(response => {
                    if (response.status === 204) {
                        this.records = [];
                        this.pagination = {...this.pagination, total: 0};
                    } else {
                        this.loaded = true;
                        const data = this.transform
                            ? this.transform(response.data.data)
                            : response.data.data;
                        this.records = data.records.map(record => {
                            Object.entries(record).forEach(([key, value]) => {
                                if (value._isBigNumber) {
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
                        this.pagination = {...this.pagination, total: data.total};
                    }
                })
                .catch(error => {
                    if (error.response?.status === 404) {
                        this.records = [];
                        this.total = 0;
                    }
                    this.$error(this.$t('failedToFetchData'), error);
                })
                .finally(() => {
                    this.selectedRowKeys = this.selectedRowKeys
                        .filter(key => this.records.some(record => record.rowKey === key));
                    this.loading = false;
                });
        },
        deleteSelectedRows() {
            this.requestDelete(this.selectedRowKeys);
        },
        requestDelete(deleteKeys) {
            if (this.url) {
                this.loading = true;
                const params = this.$rq.getQueryParams(this.queryKey, deleteKeys);
                return this.$http.delete(`${this.url}${params}`)
                    .then(() => {
                        this.$message.success(this.$t('deletedSuccessfully'));
                        if (this.deletion.refresh) {
                            this.search();
                        } else {
                            this.records = this.records
                                .filter(record => !deleteKeys.includes(record.rowKey));
                        }
                        this.$emit('onDataDeleted', deleteKeys);
                    })
                    .catch(error => {
                        this.$error(this.$t('deleteFailed'), error);
                    })
                    .finally(() => this.loading = false);
            }
        },
        onRecordsUpdated(keys, values) {
            this.records.forEach(record => {
                if (keys.includes(record.rowKey)) {
                    Object.assign(record, values);
                }
            });
        }
    }
};
</script>
<style lang="scss" scoped>
.content-template {
    display: flex;
    flex-direction: column;

    .content-template__filter-group {
        display: flex;
        flex-wrap: wrap;
    }

    .content-template__close-icon {
        color: #cf1322;
    }

    .content-template__check-icon {
        color: #52c41a;
    }
}

.action-groups {
    display: flex;
    flex-wrap: wrap;

    .action-group {
        display: flex;
        flex-wrap: wrap;

        &:not(:first-child) {
            margin-left: 24px;
        }
    }
}
</style>