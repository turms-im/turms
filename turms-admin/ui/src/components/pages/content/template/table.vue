<template>
    <a-table
        ref="table"
        bordered
        class="content-table"
        row-key="rowKey"
        size="small"
        :columns="columns"
        :data-source="dataSource"
        :loading="loading"
        :pagination="pageable ? pagination : false"
        :row-selection="selectable ? rowSelection : undefined"
        :scroll="{ y: scrollMaxHeight }"
        @change="onTableChanged"
    >
        <template
            #bodyCell="{ column, text, record }"
        >
            <div
                v-if="column.type === 'TREE'"
                :key="column.dataIndex"
            >
                <a-tree
                    :tree-data="text"
                    :show-icon="true"
                >
                    <template #bars>
                        <span class="content-table__list-icon">
                            <icon type="bars" />
                        </span>
                    </template>
                    <template #close>
                        <span class="content-table__close-icon">
                            <icon type="close" />
                        </span>
                    </template>
                    <template #check>
                        <span class="content-table__check-icon">
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
</template>

<script>
import Icon from '../../../common/icon.vue';
import UiMixin from './ui-mixin';

export default {
    name: 'content-table',
    components: {
        Icon
    },
    mixins: [UiMixin],
    props: {
        columns: {
            type: Array,
            default: () => []
        },
        dataSource: {
            type: Array,
            default: () => []
        },
        loading: {
            type: Boolean,
            default: false
        },
        pageable: {
            type: Boolean,
            default: true
        },
        selectable: {
            type: Boolean,
            default: true
        },
        rowSelection: {
            type: Object,
            default: null
        }
    },
    emits: ['onPaginationChanged', 'requestDelete'],
    data() {
        return {
            pagination: {
                current: 1,
                pageSize: 20,
                showSizeChanger: false
            },
            scrollMaxHeight: '100%'
        };
    },
    watch: {
        dataSource: {
            handler() {
                // refresh in the next tick to ensure table is loaded first
                this.$nextTick(() => this.refreshTableUi());
            },
            immediate: true
        }
    },
    mounted() {
        window.addEventListener('resize', this.refreshTableUi);
    },
    unmounted() {
        window.removeEventListener('resize', this.refreshTableUi);
    },
    methods: {
        getColumnValue(column, record) {
            let value = this.$util.get(record, column.dataIndex, column.default);
            if (value instanceof Array) {
                value = value.join(',');
            } else if (column.dataIndex.endsWith('Date') && value) {
                value = this.$date(value).format();
            }
            return value;
        },
        goToFirst() {
            this.updatePaginateCurrent(1);
        },
        onTableChanged(pagination) {
            const isPaginationChanged = JSON.stringify(pagination) !== JSON.stringify(this.pagination);
            if (isPaginationChanged) {
                this.updatePaginateCurrent(pagination.current);
                if (this.pageable) {
                    this.$emit('onPaginationChanged', this.pagination);
                }
            }
        },
        requestDelete(keys) {
            this.$emit('requestDelete', keys);
        },
        updatePaginateCurrent(current) {
            this.pagination = {...this.pagination, current};
        },
        updatePaginateTotal(total) {
            this.pagination = {...this.pagination, total};
        }
    }
};
</script>

<style>
.content-table__close-icon {
    color: #cf1322;
}

.content-table__check-icon {
    color: #52c41a;
}

</style>