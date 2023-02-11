<template>
    <div
        class="cluster-dashboard-overview"
    >
        <a-table
            class="cluster-dashboard-overview__table turms-card"
            :columns="columns"
            :data-source="members"
            :pagination="false"
            :scroll="{ x: 2400, y: 500 }"
            bordered
            size="small"
        >
            <template
                #bodyCell="{ column, text, record }"
            >
                <template v-if="column.dataIndex === 'nodeId'">
                    <a
                        :key="column.dataIndex"
                        @click="selectNodeId(text)"
                    >
                        {{ text }}
                    </a>
                </template>
                <template v-else-if="column.dataIndex === 'healthy'">
                    <span :key="column.dataIndex">
                        <icon
                            class="cluster-dashboard-overview__health-status-icon"
                            :type="record.status.healthy ? 'check-circle' : 'close-circle'"
                        />
                        <span> {{ $t(record.status.healthy ? 'healthy' : 'unhealthy') }}</span>
                    </span>
                </template>
                <template v-else-if="column.dataIndex === 'resources'">
                    <table
                        :key="column.dataIndex"
                        class="cluster-dashboard-overview__metrics-container"
                    >
                        <tr
                            v-for="metric in (record.resources || [])"
                            :key="metric.title"
                            class="cluster-dashboard-overview__metric"
                        >
                            <th class="cluster-dashboard-overview__metric-name">
                                {{ $t(metric.title) }}
                            </th>
                            <td>
                                {{
                                    `${metric.used?.toFixed(2) ?? '-'} / ${metric.max?.toFixed(2) ?? '-'} ${metric.unit}`
                                }}
                            </td>
                            <td>
                                <progress-bar :progress="metric.usedPercentage" />
                            </td>
                            <td> {{ `${metric.usedPercentage?.toFixed(2) ?? '-'}%` }}</td>
                        </tr>
                    </table>
                </template>
                <template v-else-if="column.dataIndex === 'operation'">
                    <a-dropdown
                        :key="column.dataIndex"
                        :trigger="['click']"
                    >
                        <a
                            class="ant-dropdown-link"
                        >
                            {{ $t('operation') }}
                            <icon type="down" />
                        </a>
                        <template #overlay>
                            <a-menu>
                                <a-menu-item>
                                    <a @click="generateThreadDump(record.nodeId)">
                                        {{ $t('generateThreadDump') }}
                                    </a>
                                </a-menu-item>
                                <a-menu-item>
                                    <a @click="generateHeapDump(record.nodeId)">
                                        {{ $t('generateHeapDump') }}
                                    </a>
                                </a-menu-item>
                            </a-menu>
                        </template>
                    </a-dropdown>
                </template>
                <template v-else>
                    <span :key="column.dataIndex">{{ text }}</span>
                </template>
            </template>
        </a-table>
    </div>
</template>

<script>
import icon from '../../../../common/icon.vue';
import ProgressBar from '../progress-bar.vue';
import COLUMNS from './default-overview-table-columns';

export default {
    name: 'cluster-dashboard-overview',
    components: {
        icon,
        ProgressBar
    },
    props: {
        members: {
            type: Array,
            required: true
        }
    },
    computed: {
        columns() {
            return COLUMNS.map(column => {
                column = {
                    ...column,
                    title: this.$t(column.title || column.dataIndex),
                    sorter: this.members.length > 1 ? this.$util.sort : null
                };
                if (column.needFilters) {
                    let filters = this.members
                        .flatMap(item => {
                            const value = item[column.dataIndex];
                            if (value == null) {
                                return [];
                            }
                            if (typeof value === 'boolean') {
                                return {
                                    text: this.$t(value ? 'yes' : 'no'),
                                    value
                                };
                            }
                            return {
                                text: value,
                                value
                            };
                        });
                    filters = this.$util.unique(filters, v => v.value);
                    if (filters.length > 1) {
                        column.filters = filters;
                    }
                }
                return column;
            });
        }
    },
    methods: {
        generateHeapDump(nodeId) {
            const member = this.members.find(m => m.nodeId === nodeId);
            this.$emit('generateHeapDump', member);
        },
        generateThreadDump(nodeId) {
            const member = this.members.find(m => m.nodeId === nodeId);
            this.$emit('generateThreadDump', member);
        },
        selectNodeId(nodeId) {
            this.$emit('selectNodeId', nodeId);
        }
    }
};
</script>

<style lang="scss">
.cluster-dashboard-overview {
    &__health-status-icon {
        margin-right: 4px;
    }

    &__metrics-container {
        width: 370px !important;
        min-width: inherit !important;

        // Don't use "table-layout: auto !important;"
        // because it may have different padding on different browsers
        table-layout: fixed;

        tr th {
            width: 64px;
        }

        tr td:nth-child(2) {
            width: 135px;
        }

        tr td:nth-child(3) {
            width: 110px;
        }

        tr td:nth-child(4) {
            width: 60px;
        }
    }

    &__metric-name {
        font-weight: 600;
    }
}
</style>