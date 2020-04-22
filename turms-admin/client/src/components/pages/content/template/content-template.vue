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
                    v-model="filter.model"
                    :placeholder="filter.placeholder"
                    :only-number-and-comma="typeof filter.decorator === 'undefined' || filter.decorator.onlyNumberAndComma"
                    :non-space="filter.decorator && filter.decorator.nonSpace"
                    class="search-filter search-filter__input"
                />
                <a-select
                    v-if="filter.type.toUpperCase() === 'SELECT'"
                    :key="index"
                    v-model="filter.model"
                    class="search-filter search-filter__select"
                >
                    <a-select-option
                        v-for="option in (filter.options.base || []).concat(filter.options.values || [])"
                        :key="option.id.toString()"
                        :value="option.id.toString()"
                    >
                        {{ option.label }}
                    </a-select-option>
                </a-select>
                <date-range-picker
                    v-if="filter.type.toUpperCase() === 'DATE-RANGE'"
                    :key="index"
                    v-model="filter.model"
                    :include-today="true"
                    :show-time="true"
                    :placeholder="filter.placeholder || [$t(`${filter.name}Range.start`), $t(`${filter.name}Range.end`)]"
                    class="search-filter search-filter__date-picker"
                />
                <a-date-picker
                    v-if="filter.type.toUpperCase() === 'DATE'"
                    :key="index"
                    v-model="filter.model"
                    :show-time="typeof filter.showTime === 'undefined' ? true : filter.showTime"
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
                    :button-label="action.buttonLabel || action.title"
                    :title="action.title"
                    :fields="action.fields"
                    :type="action.type"
                    :query-key="queryKey"
                    :record-key="recordKey"
                    :keys="selectedRowKeys"
                    :url="url"
                    :disabled="action.type === 'UPDATE' && !hasSelectedRows"
                    :params="action.params || {}"
                    :size="action.size"
                    class="action-button"
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
                    :url="url + '/page'"
                    :params="searchParams"
                    :disabled="records.length === 0"
                    :file-name="'turms-' + name"
                    class="action-button"
                />
            </div>
        </div>
        <a-spin :spinning="loading">
            <a-table
                ref="table"
                size="middle"
                :columns="columnsData"
                :data-source="tableData"
                :pagination="pagination"
                :row-selection="selectable ? rowSelection : undefined"
                :scroll="{ y: 400 }"
                bordered
                class="content-table"
                @change="handleTableChange"
            >
                <template
                    v-for="(column, index) in columnsData"
                    :slot="column.dataIndex"
                    slot-scope="text, record"
                >
                    <div
                        v-if="column.type === 'tree'"
                        :key="index"
                    >
                        <a-tree
                            :tree-data="text"
                            show-icon
                        >
                            <a-icon
                                slot="bars"
                                type="bars"
                                class="content-template__list-icon"
                            />
                            <a-icon
                                slot="close"
                                type="close"
                                class="content-template__close-icon"
                            />
                            <a-icon
                                slot="check"
                                type="check"
                                class="content-template__check-icon"
                            />
                        </a-tree>
                    </div>
                    <div
                        v-else-if="column.dataIndex === 'operation'"
                        :key="index"
                        class="editable-row-operations"
                    >
                        <span>
                            <a-popconfirm
                                :title="$t('confirmDeletion')"
                                @confirm="requestDelete([record.key])"
                            >
                                <a>{{ $t('delete') }}</a>
                            </a-popconfirm>
                        </span>
                    </div>
                    <div
                        v-else
                        :key="index"
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
import Skeleton from '../../../common/skeleton';
import CustomInput from '../../../common/custom-input';
import ButtonModalTemplate from './button-modal-template';
import DateRangePicker from '../../../common/date-range-picker';
import ContentTemplateExport from './content-template-export';

export default {
    name: 'content-template',
    components: {
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
            default: () => {
                return [];
            }
        },
        url: {
            type: String,
            default: ''
        },
        selectable: {
            type: Boolean,
            default: true
        },
        recordKey: {
            type: String,
            default: 'id'
        },
        queryKey: {
            type: String,
            default: 'ids'
        },
        fetchOnStatusChanged: {
            type: Boolean,
            default: true
        },
        params: {
            type: Object,
            required: false,
            default: () => {
                return {};
            }
        },
        deletion: {
            type: Object,
            default: function() {
                return {};
            }
        },
        filters: {
            type: Array,
            default: function() {
                return [];
            }
        },
        actionGroups: {
            type: Array,
            default: function() {
                return [];
            }
        },
        table: {
            type: Object,
            default: function() {
                return {};
            }
        },
        transform: {
            type: Function,
            required: false,
            default: null
        }
    },
    data() {
        return {
            loading: false,
            initialData: [],
            records: [],
            activeKey: 1,
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
            Object.assign(params, {
                page: this.pagination.current - 1,
                size: this.pagination.pageSize
            }, this.params);
            return params;
        },
        columnsData() {
            return this.table.columns.map(column => {
                const fields = column.key.split('.');
                return {
                    title: column.title || this.$t(`${fields[fields.length - 1]}`),
                    dataIndex: column.key,
                    default: column.default,
                    type: column.type,
                    scopedSlots: {
                        customRender: column.key
                    },
                    width: column.width
                };
            });
        },
        tableData() {
            const recordsCopy = JSONBig.parse(JSONBig.stringify(this.records));
            recordsCopy.forEach(recordCopy => {
                Object.entries(recordCopy).forEach(entry => {
                    if (entry[1]._isBigNumber) {
                        recordCopy[entry[0]] = entry[1].toFixed();
                    }
                });
                const key = recordCopy[this.recordKey];
                recordCopy.originalKey = key;
                if (typeof key === 'object') {
                    recordCopy.key = JSON.stringify(key);
                } else {
                    recordCopy.key = key;
                }
            });
            return recordsCopy;
        },
        admin() {
            return this.$store.getters.admin;
        },
        hasSelectedRows() {
            return this.selectedRowKeys.length !== 0;
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
            const indexes = column.dataIndex.split('.');
            let value;
            for (let index of indexes) {
                if (index === 'key') {
                    index = 'originalKey';
                }
                if (typeof value !== 'undefined') {
                    value = value[index];
                } else {
                    value = record[index];
                    if (value instanceof Array) {
                        value = value.join(',');
                    } else if (column.dataIndex.endsWith('Date') && value) {
                        value = this.$moment(value).format();
                    }
                }
            }
            if (typeof value === 'undefined' && typeof column.default !== 'undefined') {
                value = column.default;
            }
            return value;
        },
        hanlePopconfirmVisibleChange (visible) {
            this.popconfirmVisible = visible && this.hasSelectedRows;
        },
        handleTableChange (pagination) {
            this.pagination = { ...this.pagination, current: pagination.current };
            this.search();
        },
        init() {
            if (this.initialDataUrls.length) {
                const promises = [];
                for (const url of this.initialDataUrls) {
                    promises.push(this.$client.get(url));
                }
                Promise.all(promises)
                    .then(responseList => {
                        this.initialized = true;
                        this.$emit('afterDataInitialized', responseList);
                    })
                    .catch(() => {
                        setTimeout(() => this.init(), 3000);
                    });
            } else {
                this.initialized = true;
            }
        },
        getIfValid(object) {
            if (typeof object === 'number' || typeof object === 'boolean') {
                return object;
            } else if (object instanceof Array) {
                if (object.length > 0) {
                    return object;
                }
            } else if (object) {
                return object;
            }
        },
        getTimeIfNotNull (object) {
            if (object) {
                return object.format();
            }
        },
        search() {
            this.loading = true;
            this.selectedRowKeys.splice(0, this.selectedRowKeys.length);
            this.$client.get(this.url + '/page', { params: this.searchParams })
                .then(response => {
                    if (response.status === 204) {
                        this.records = [];
                        this.pagination = {...this.pagination, total: 0};
                    } else {
                        this.loaded = true;
                        if (this.transform) {
                            response.data.data = this.transform(response.data.data);
                        }
                        response.data.data.records.forEach(record => {
                            record.key = record[this.recordKey];
                        });
                        this.records = response.data.data.records;
                        this.pagination = {...this.pagination, total: response.data.data.total};
                    }
                })
                .catch(error => {
                    if (error.response && error.response.status === 404) {
                        this.records = [];
                        this.total = 0;
                    }
                    this.$error(this.$t('failedToFetchData'), error);
                })
                .finally(() => this.loading = false);
        },
        deleteSelectedRows() {
            this.requestDelete(this.selectedRowKeys);
        },
        requestDelete(deleteKeys) {
            if (this.url) {
                this.loading = true;
                const params = this.$rq.getQueryParams(this.queryKey, deleteKeys);
                return this.$client.delete(`${this.url}${params}`)
                    .then(() => {
                        this.$message.success(this.$t('deletedSuccessfully'));
                        if (this.deletion.refresh) {
                            this.search();
                        } else {
                            this.records = this.records.filter(item => {
                                let itemKey;
                                if (this.recordKey === 'key') {
                                    itemKey = JSONBig.stringify(item.key);
                                } else {
                                    if (item.key._isBigNumber) {
                                        itemKey = item.key.toString();
                                    } else {
                                        itemKey = item.key;
                                    }
                                }
                                return !deleteKeys.includes(itemKey);
                            });
                        }
                        this.$emit('afterDataDeleted', deleteKeys);
                    })
                    .catch(error => {
                        this.$error(this.$t('deleteFailed'), error);
                    })
                    .finally(() => this.loading = false);
            }
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